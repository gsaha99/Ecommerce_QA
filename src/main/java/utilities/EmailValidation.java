package utilities;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjectRepo.app_AGS_Repo;
import PageObjectRepo.app_VET_Repo;
import PageObjectRepo.app_WileyPLUS_Repo;
import PageObjectRepo.app_Wiley_Repo;
import PageObjectRepo.app_WEL_Repo;

public class EmailValidation {

	private static String MailinatorURL=excelOperation.getTestData("Mailinator_URL", "Generic_Dataset", "Data");


	//Methods applicable for Mailinator

	/*
	 * @Author: Anindita
	 * @Description: Checks if order confirmation mail was received
	 * @Date: 28/11/22
	 */
	public static boolean checkIfOrderConfirmationMailReceived(String userName,WebDriver driver, String SS_path, String expectedElementXapth) throws IOException{
		try {
			driver.get(MailinatorURL);
			try {
				WebElement EmailIdFieldInMailinator=driver.findElement(By.id("inbox_field"));
				EmailIdFieldInMailinator.clear();
				EmailIdFieldInMailinator.sendKeys(userName);
				Reporting.updateTestReport("Email Id : " + userName + " was entered successfully in Mailinator page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				try {

					WebElement GoButtonInMailinator=driver.findElement(By.xpath("//button[@class='primary-btn']"));
					GoButtonInMailinator.click();
					Reporting.updateTestReport("GO button in Mailinator was clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.PASS);
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
					int timeOutSeconds=100;
					int flag=0;
					WebElement OrderConfirmationMail = null;

					/* The purpose of this loop is to wait for maximum of 60 seconds */
					for (int i = 0; i < timeOutSeconds / 5; i++) {

						try {
							OrderConfirmationMail=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expectedElementXapth)));

							if(OrderConfirmationMail.isDisplayed()==true)
							{
								flag=1;
								driver.findElement(By.xpath(expectedElementXapth)).click();
								break;
							}

						} catch (Exception e) {
							driver.navigate().refresh();

						}
					}

					if(flag==1)  return true;
					else return false;

				}
				catch(Exception e) {
					Reporting.updateTestReport("Failed to click on GO button in Mailinator", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
					return false;
				}
			}
			catch (Exception e) {
				Reporting.updateTestReport("Failed to enter Email Id  in Mailinator page", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Order Confirmation mail was not received with exception: "+e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}



	}

	/*
	 * @Date: 3/1/23
	 * @Description: Calls the email validation methods based on the storefront
	 */
	public static void validateOrderConfirmationMailContent(String storeFront, WebDriver driver, String SS_path, String tax, String shipping, String total) throws IOException{
		try {
			if (storeFront.equalsIgnoreCase("AGS") || storeFront.equalsIgnoreCase("VET"))
				validateOrderConfirmationMailContentAGS_VET(driver,SS_path,tax,total);
			else if(storeFront.equalsIgnoreCase("Wiley"))
				validateOrderConfirmationMailContentWiley(driver,SS_path,tax,shipping,total);
			else if(storeFront.equalsIgnoreCase("WEL"))
				validateOrderConfirmationMailContentWEL(driver,SS_path,tax,shipping,total);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Order Confirmation email validation method couldn't be called: "+e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Fetches the mail content of Order Confirmation mail, order total, tax and shipping
	 * @Date: 29/11/22
	 */

	public static void validateOrderConfirmationMailContentWiley(WebDriver driver,String SS_path,String tax,String shipping, String total) throws IOException {
		try {
			ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
			driver.switchTo().frame("texthtml_msg_body");
			String orderConfirmationMail=driver.findElement(By.xpath("//br/ancestor::body")).getText();
			String[] A=orderConfirmationMail.split("Tax:");
			String[] B=A[1].split("Delivery:");
			String taxInMail=B[0].trim();
			String[] C=B[1].split("TOTAL:");
			String shippingChargeInMail=C[0].trim();
			String[] D=C[1].trim().split(" ");
			String[] E=D[0].trim().split("\\R");
			String orderTotalInMail=E[0].trim();
			System.out.println("Printed Tax: "+taxInMail);
			System.out.println("Printed shippingChargeInMail: "+shippingChargeInMail);
			System.out.println("Printed orderTotalInMail: "+orderTotalInMail);
			//Validation of tax
			if(tax.contentEquals(taxInMail)) 
				Reporting.updateTestReport(taxInMail+" : shown as tax in Order Confirmation mail was same as tax in Order Confirmation page: "+tax,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else{
				BigDecimal taxDouble=new BigDecimal(tax.substring(1));
				BigDecimal taxInMailDouble=new BigDecimal(taxInMail.substring(1));
				if((taxDouble.subtract(taxInMailDouble).compareTo(new BigDecimal("0.01") )==0 ) || (taxDouble.subtract(taxInMailDouble).compareTo(new BigDecimal("-0.01") )==0 )) {
					Reporting.updateTestReport(taxInMail+" : shown as tax in Order Confirmation mail has just 0.01 difference with tax in Order Confirmation page: "+tax,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				else {
					Reporting.updateTestReport(taxInMail+" : shown as tax in Order Confirmation mail was not same as tax in Order Confirmation page: "+tax,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			}
			//Validation of shipping
			if(!shipping.contentEquals(" ")) {
				if(shipping.contentEquals(shippingChargeInMail))
					Reporting.updateTestReport(shippingChargeInMail+" : shown as shipping in Order Confirmation mail was same as Shipping charge in Order Confirmation page: "+shipping,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(shippingChargeInMail+" : shown as shipping in Order Confirmation mail was not same as Shipping charge in Order Confirmation page: "+shipping,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			else {
				Reporting.updateTestReport("Shipping was not applicable for this order as it is a digital order",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
			}

			//Validation of Order Total
			if(total.contentEquals(orderTotalInMail)) 
				Reporting.updateTestReport(orderTotalInMail+" : shown as tax in Order Confirmation mail was same as tax in Order Confirmation page: "+total,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else{
				BigDecimal totalDouble=new BigDecimal(total.substring(1));
				BigDecimal totalInMailDouble=new BigDecimal(orderTotalInMail.substring(1));
				if((totalDouble.subtract(totalInMailDouble).compareTo(new BigDecimal("0.01") )==0 ) || (totalDouble.subtract(totalInMailDouble).compareTo(new BigDecimal("-0.01") )==0 )) {
					Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail has just 0.01 difference with total in Order Confirmation page: "+total,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				else {
					Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail was not same as total in Order Confirmation page: "+total,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			}

			driver.switchTo().defaultContent();

		}
		catch(Exception e){
			Reporting.updateTestReport("Order total and tax validation couldn't be done: "+e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Description: Fetches the mail content of Order Confirmation mail, order total, tax and shipping
	 * @Date: 20/12/22
	 */

	public static void validateOrderConfirmationMailContentAGS_VET(WebDriver driver,String SS_path,String tax, String total) throws IOException {
		try {
			ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
			driver.switchTo().frame("html_msg_body");
			String orderTotalInMail=driver.findElement(By.xpath("//td[contains(text(),'Total:')]/following-sibling::td")).getText();
			String taxInMail=driver.findElement(By.xpath("//td[contains(text(),'Tax:')]/following-sibling::td")).getText();
			//Validation of tax
			if(tax.contentEquals(taxInMail))
				Reporting.updateTestReport(taxInMail+" : shown as Tax in Order Confirmation mail was same as tax in Order Confirmation page: "+tax,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport(taxInMail+" : shown as Tax in Order Confirmation mail was not same as tax in Order Confirmation page: "+tax,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			//Validation of Order Total
			if(total.contentEquals(orderTotalInMail)) 
				Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail was same as tax in Order Confirmation page: "+total,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail was not same as total in Order Confirmation page: "+total,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);			
			driver.switchTo().defaultContent();

		}
		catch(Exception e){
			Reporting.updateTestReport("Order total and tax validation couldn't be done: "+e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Description: Fetches the mail content of Order Confirmation mail, order total, tax and shipping
	 * @Date: 20/12/22
	 */

	public static void validateOrderConfirmationMailContentWEL(WebDriver driver,String SS_path,String tax,String shipping, String total) throws IOException {
		try {
			ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
			driver.switchTo().frame("html_msg_body");
			String orderTotalInMail=driver.findElement(By.xpath("//td[contains(text(),'Total:')]/following-sibling::td")).getText();
			String taxInMail=driver.findElement(By.xpath("//td[contains(text(),'Tax:')]/following-sibling::td")).getText();
			String shippingInMail=driver.findElement(By.xpath("//td[contains(text(),'Shipping:')]/following-sibling::td")).getText();
			//Validation of tax
			if(tax.contentEquals(taxInMail))
				Reporting.updateTestReport(taxInMail+" : shown as Tax in Order Confirmation mail was same as tax in Order Confirmation page: "+tax,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport(taxInMail+" : shown as Tax in Order Confirmation mail was not same as tax in Order Confirmation page: "+tax,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			//Validation of shipping
			if(!shipping.equalsIgnoreCase("")) {
				if(shippingInMail.contentEquals(shipping))
					Reporting.updateTestReport(shippingInMail+" : shown as Shipping charge in Order Confirmation mail was same as shipping charge in Order Confirmation page: "+shipping,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(shippingInMail+" : shown as Shipping charge in Order Confirmation mail was not same as Shipping charge in Order Confirmation page: "+shipping,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			else
				Reporting.updateTestReport("Shipping charge is not applicable for this order",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);

			//Validation of Order Total
			if(total.contentEquals(orderTotalInMail)) 
				Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail was same as tax in Order Confirmation page: "+total,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail was not same as total in Order Confirmation page: "+total,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);			
			driver.switchTo().defaultContent();

		}
		catch(Exception e){
			Reporting.updateTestReport("Order total and tax validation couldn't be done: "+e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Waits for the finish registration mail from Onboarding and clicks on that
	 */
	public static void clickOnFinishRegistrationMail(String userName,WebDriver driver,String SS_path,app_WileyPLUS_Repo WileyPLUS) throws IOException{
		try {
			driver.get(MailinatorURL);
			try {
				WebElement EmailIdFieldInMailinator=driver.findElement(By.id("inbox_field"));
				EmailIdFieldInMailinator.clear();
				EmailIdFieldInMailinator.sendKeys(userName);
				Reporting.updateTestReport("Email Id : " + userName + " was entered successfully in Mailinator page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				try {

					WebElement GoButtonInMailinator=driver.findElement(By.xpath("//button[@class='primary-btn']"));
					GoButtonInMailinator.click();
					Reporting.updateTestReport("GO button in Mailinator was clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.PASS);
					WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
					int timeOutSeconds=10;
					int flag=0;
					WebElement FinishRegistrationMail = null;
					/* The purpose of this loop is to wait for maximum of 60 seconds */
					for (int i = 0; i < timeOutSeconds / 5; i++) {

						try {
							FinishRegistrationMail=wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//td[contains(text(),'Welcome to Wiley')]")));

							if(FinishRegistrationMail.isDisplayed()==true)
							{
								flag=1;
								FinishRegistrationMail.click();
								break;
							}

						} catch (Exception e) {
							driver.navigate().refresh();
						}
					}
					if(flag==1) {
						ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
						driver.switchTo().frame("html_msg_body");
						WileyPLUS.clickOnFinishRegistrationLinkInMail();
						driver.switchTo().defaultContent();
						Set<String> allWindowHandles = driver.getWindowHandles();
						java.util.Iterator<String> iterator = allWindowHandles.iterator();
						String mailHandle = iterator.next();
						String ChildWindow=iterator.next();
						driver.switchTo().window(mailHandle);
						driver.close();
						driver.switchTo().window(ChildWindow);
					}
					else {
						Reporting.updateTestReport("No finish registration mail  was recieved in yopmail inbox", 
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Failed to click on GO button in Mailinator", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);

				}
			}
			catch (Exception e) {
				Reporting.updateTestReport("Failed to enter Email Id  in Mailinator page", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);

			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Finish registration mail couldn't be validated", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Waits for the forgot password mail for VET and clicks on the reset password link
	 */
	public static void forgotPasswordMailForVet(String userName,WebDriver driver, String SS_path, app_VET_Repo VET) throws IOException {
		try {
			driver.get(MailinatorURL);
			try {
				WebElement EmailIdFieldInMailinator=driver.findElement(By.id("inbox_field"));
				EmailIdFieldInMailinator.clear();
				EmailIdFieldInMailinator.sendKeys(userName);
				Reporting.updateTestReport("Email Id : " + userName + " was entered successfully in Mailinator page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				try {

					WebElement GoButtonInMailinator=driver.findElement(By.xpath("//button[@class='primary-btn']"));
					GoButtonInMailinator.click();
					Reporting.updateTestReport("GO button in Mailinator was clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.PASS);
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
					int timeOutSeconds=60;
					int flag=0;
					WebElement ResetPasswordLink = null;


					/* The purpose of this loop is to wait for maximum of 60 seconds */
					for (int i = 0; i < timeOutSeconds / 5; i++) {

						try {
							ResetPasswordLink=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Password Reset Request')]")));

							if(ResetPasswordLink.isDisplayed()==true)
							{
								flag=1;
								ResetPasswordLink.click();
								break;
							}

						} catch (Exception e) {
							driver.navigate().refresh();
						}
					}

					if(flag==1)
					{driver.switchTo().frame("html_msg_body");
					ScrollingWebPage.PageDown(driver, SS_path);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(
								driver.findElement(By.xpath("//a[contains(text(),'Click here to change your password')]"))));
						driver.switchTo().defaultContent();
						WebElement LinkTabInMailinator=driver.findElement(By.id("pills-links-tab"));
						LinkTabInMailinator.click();
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath
									("//td[contains(text(),'Click here to change your password')]/following-sibling::td/a")));
							driver.findElement(By.xpath
									("//td[contains(text(),'Click here to change your password')]/following-sibling::td/a")).click();
							Reporting.updateTestReport("Reset password link was clicked in the Link tab inside mailinator", 
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
							Set<String> allWindowHandles = driver.getWindowHandles();
							java.util.Iterator<String> iterator = allWindowHandles.iterator();
							String mailHandle = iterator.next();
							String ChildWindow=iterator.next();
							driver.switchTo().window(ChildWindow);
							VET.ResetPwd(excelOperation.getTestData("TC06", "VET_Test_Data", "New_Password"));
							VET.ResetConfirmPassword(excelOperation.getTestData("TC06", "VET_Test_Data", "Confirm_Password"));
							VET.ResetPassSubmit();
							VET.PasswordResetSuccess();
							driver.switchTo().window(mailHandle);
							driver.close();
							driver.switchTo().window(ChildWindow);
						}
						catch(Exception e) {
							Reporting.updateTestReport("Reset password link was not cliackable in Link tab inside mailinator", 
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Reset password link was not cliackable in the mail", 
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					}
					else {
						Reporting.updateTestReport("No reset password mail was recieved in inbox", 
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Failed to click on GO button in Mailinator", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);

				}
			}
			catch (Exception e) {
				Reporting.updateTestReport("Failed to enter Email Id  in Mailinator page", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);

			}

		}
		catch(Exception e) {
			Reporting.updateTestReport("Forgot password mail couldn't be validated for VET", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Author: Anindita
	 * @Description: Waits for the forgot password mail for AGS and clicks on the reset password link
	 */
	public static void forgotPasswordMailForAGS(String userName,WebDriver driver, String SS_path, app_AGS_Repo AGS) throws IOException {
		try {
			driver.get(MailinatorURL);
			try {
				WebElement EmailIdFieldInMailinator=driver.findElement(By.id("inbox_field"));
				EmailIdFieldInMailinator.clear();
				EmailIdFieldInMailinator.sendKeys(userName);
				Reporting.updateTestReport("Email Id : " + userName + " was entered successfully in Mailinator page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				try {

					WebElement GoButtonInMailinator=driver.findElement(By.xpath("//button[@class='primary-btn']"));
					GoButtonInMailinator.click();
					Reporting.updateTestReport("GO button in Mailinator was clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.PASS);
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
					int timeOutSeconds=60;
					int flag=0;
					WebElement ResetPasswordLink = null;

					/* The purpose of this loop is to wait for maximum of 60 seconds */
					for (int i = 0; i < timeOutSeconds / 5; i++) {

						try {
							ResetPasswordLink=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Password Reset Request')]")));

							if(ResetPasswordLink.isDisplayed()==true)
							{
								flag=1;
								ResetPasswordLink.click();
								break;
							}

						} catch (Exception e) {
							driver.navigate().refresh();
						}
					}

					if(flag==1)
					{driver.switchTo().frame("html_msg_body");
					ScrollingWebPage.PageDown(driver, SS_path);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(
								driver.findElement(By.xpath("//a[contains(text(),'Click here to change your password')]"))));
						driver.switchTo().defaultContent();
						WebElement LinkTabInMailinator=driver.findElement(By.id("pills-links-tab"));
						LinkTabInMailinator.click();
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath
									("//td[contains(text(),'Click here to change your password')]/following-sibling::td/a")));
							driver.findElement(By.xpath
									("//td[contains(text(),'Click here to change your password')]/following-sibling::td/a")).click();
							Reporting.updateTestReport("Reset password link was clicked in the Link tab inside mailinator", 
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
							Set<String> allWindowHandles = driver.getWindowHandles();
							java.util.Iterator<String> iterator = allWindowHandles.iterator();
							String mailHandle = iterator.next();
							String ChildWindow=iterator.next();
							driver.switchTo().window(ChildWindow);
							AGS.enterNewPasswordInResetPassword(excelOperation.getTestData("TC05", "AGS_Test_Data", "Password"));
							AGS.enterConfirmPasswordInResetPassword(excelOperation.getTestData("TC05", "AGS_Test_Data", "Password"));
							AGS.clickOnResetPasswordSubmit();
							AGS.checkResetPasswordSuccessMessage();
							driver.switchTo().window(mailHandle);
							driver.close();
							driver.switchTo().window(ChildWindow);
						}
						catch(Exception e) {
							Reporting.updateTestReport("Reset password link was not cliackable in Link tab inside mailinator", 
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Reset password link was not cliackable in the mail", 
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}

					}
					else {
						Reporting.updateTestReport("No reset password mail was recieved in yopmail inbox", 
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Failed to click on GO button in Mailinator", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);

				}
			}
			catch (Exception e) {
				Reporting.updateTestReport("Failed to enter Email Id  in Mailinator page", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);

			}

		}
		catch(Exception e) {
			Reporting.updateTestReport("Forgot password mail couldn't be validated", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Validates the forgot password email functionality in Wiley
	 */
	public static int forgotPasswordEmailForWiley(String userName,WebDriver driver, String SS_path, app_Wiley_Repo wiley) throws IOException {
		try {
			driver.get(MailinatorURL);
			try {
				WebElement EmailIdFieldInMailinator=driver.findElement(By.id("inbox_field"));
				EmailIdFieldInMailinator.clear();
				EmailIdFieldInMailinator.sendKeys(userName);
				Reporting.updateTestReport("Email Id : " + userName + " was entered successfully in Mailinator page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				try {

					WebElement GoButtonInMailinator=driver.findElement(By.xpath("//button[@class='primary-btn']"));
					GoButtonInMailinator.click();
					Reporting.updateTestReport("GO button in Mailinator was clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.PASS);
					WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
					int timeOutSeconds=60;
					int flag=0;
					WebElement ResetPasswordLink = null;

					/* The purpose of this loop is to wait for maximum of 60 seconds */
					for (int i = 0; i < timeOutSeconds / 5; i++) {

						try {
							ResetPasswordLink=wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Reset Password for Wiley.com')]")));

							if(ResetPasswordLink.isDisplayed()==true)
							{
								flag=1;
								ResetPasswordLink.click();
								Reporting.updateTestReport("Forgot password mail was received", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								break;
							}

						} catch (Exception e) {
							driver.navigate().refresh();
						}
					}

					if(flag==1)
					{driver.switchTo().frame("html_msg_body");
					try {
						wait.until(ExpectedConditions.elementToBeClickable(
								driver.findElement(By.xpath("//a[text()='Reset Password']"))));
						driver.switchTo().defaultContent();
						WebElement LinkTabInMailinator=driver.findElement(By.id("pills-links-tab"));
						LinkTabInMailinator.click();
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath
									("//td[contains(text(),'Reset Password')]/following-sibling::td/a")));
							driver.findElement(By.xpath
									("//td[contains(text(),'Reset Password')]/following-sibling::td/a")).click();
							Reporting.updateTestReport("Reset password link was clicked in the Link tab inside mailinator", 
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

							Set<String> allWindowHandles = driver.getWindowHandles();
							java.util.Iterator<String> iterator = allWindowHandles.iterator();

							String mailHandle = iterator.next();
							String ChildWindow=iterator.next();
							driver.switchTo().window(mailHandle);
							driver.close();
							driver.switchTo().window(ChildWindow);
							return flag;

						}
						catch(Exception e) {
							Reporting.updateTestReport("Reset password link was not cliackable in Link tab inside mailinator", 
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							return 0;
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Reset password link was not cliackable in the mail", 
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						return 0;
					}
					}
					else {
						Reporting.updateTestReport("No reset password mail was recieved in inbox", 
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						return flag;
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Failed to click on GO button in Mailinator", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
					return 0;

				}
			}
			catch (Exception e) {
				Reporting.updateTestReport("Failed to enter Email Id  in Mailinator page", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
				return 0;

			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Forgot password mail couldn't be validated", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return 0;
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Validates the forgot password email functionality in WEL
	 */
	public static int forgotPasswordEmailForWEL(String userName,WebDriver driver, String SS_path, app_WEL_Repo WEL) throws IOException {
		try {
			driver.get(MailinatorURL);
			try {
				WebElement EmailIdFieldInMailinator=driver.findElement(By.id("inbox_field"));
				EmailIdFieldInMailinator.clear();
				EmailIdFieldInMailinator.sendKeys(userName);
				Reporting.updateTestReport("Email Id : " + userName + " was entered successfully in Mailinator page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				try {

					WebElement GoButtonInMailinator=driver.findElement(By.xpath("//button[@class='primary-btn']"));
					GoButtonInMailinator.click();
					Reporting.updateTestReport("GO button in Mailinator was clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.PASS);
					WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
					int timeOutSeconds=60;
					int flag=0;
					WebElement ResetPasswordLink = null;

					/* The purpose of this loop is to wait for maximum of 60 seconds */
					for (int i = 0; i < timeOutSeconds / 5; i++) {

						try {
							ResetPasswordLink = wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//div[contains(text(),'Password Reset Request')]")));

							if (ResetPasswordLink.isDisplayed() == true) {
								flag = 1;
								ResetPasswordLink.click();
								break;
							}

						} catch (Exception e) {
							driver.switchTo().defaultContent();
						}
					}

					if(flag==1)
					{driver.switchTo().frame("html_msg_body");
					try {
						wait.until(ExpectedConditions.elementToBeClickable(
								driver.findElement(By.xpath("//a[contains(text(),'Reset Password')]"))));
						driver.switchTo().defaultContent();
						WebElement LinkTabInMailinator=driver.findElement(By.id("pills-links-tab"));
						LinkTabInMailinator.click();
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath
									("//td[contains(text(),'Reset Password')]/following-sibling::td/a")));
							driver.findElement(By.xpath
									("//td[contains(text(),'Reset Password')]/following-sibling::td/a")).click();
							Reporting.updateTestReport("Reset password link was clicked in the Link tab inside mailinator", 
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
							return flag;
						}
						catch(Exception e) {
							Reporting.updateTestReport("Reset password link was not cliackable in Link tab inside mailinator", 
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							return 0;
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Reset password link was not cliackable in the mail", 
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						return 0;
					}
					}

					else {
						Reporting.updateTestReport("No reset password mail was recieved in yopmail inbox", 
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						return flag;
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Failed to click on GO button in Mailinator", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
					return 0;

				}
			}
			catch (Exception e) {
				Reporting.updateTestReport("Failed to enter Email Id  in Mailinator page", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
				return 0;

			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Forgot password mail couldn't be validated", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return 0;
		}
	}

	//Commenting out the below methods as they were used for yopmail

	/*
	 * @Author: Anindita
	 * @Description: Checks if order confirmation mail was received
	 * @Date: 28/11/22
	 */
	/*public static boolean checkIfOrderConfirmationMailReceived(WebDriver driver, String SS_path, String expectedElementXapth) throws IOException{
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				int timeOutSeconds=90;
				int flag=0;
				WebElement RefreshButtonInYopmail = driver.findElement(By.xpath("//button[@id='refresh']"));
				WebElement OrderConfirmationMail = null;

				// The purpose of this loop is to wait for maximum of 60 seconds 
				for (int i = 0; i < timeOutSeconds / 5; i++) {

					try {
						driver.switchTo().frame("ifinbox");
						OrderConfirmationMail=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expectedElementXapth)));

						if(OrderConfirmationMail.isDisplayed()==true)
						{
							flag=1;
							driver.findElement(By.xpath(expectedElementXapth)).click();
							driver.switchTo().defaultContent();
							break;
						}

					} catch (Exception e) {
						driver.switchTo().defaultContent(); 
						RefreshButtonInYopmail.click();

					}
				}

				if(flag==1)  return true;
				else return false;
			}
			catch(Exception e) {
				Reporting.updateTestReport("Order Confirmation mail was not received with exception: "+e.getMessage(),
	                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}



			}*/



	/*
	 * @Author: Anindita
	 * @Description: Fetches the mail content of Order Confirmation mail, order total, tax and shipping
	 * @Date: 29/11/22
	 */

	/*public static void validateOrderConfirmationMailContentWiley(WebDriver driver,String SS_path,String tax,String shipping, String total) throws IOException {
			try {
				driver.switchTo().frame("ifmail");
				String orderConfirmationMail=driver.findElement(By.xpath("//div[@id='mail']/pre")).getText();

				String[] A=orderConfirmationMail.split("Tax:");
				String[] B=A[1].split("Delivery:");
				String taxInMail=B[0].trim();
				String[] C=B[1].split("TOTAL:");
				String shippingChargeInMail=C[0].trim();
				String[] D=C[1].trim().split(" ");
				String[] E=D[0].trim().split("\\R");
				String orderTotalInMail=E[0].trim();
				System.out.println("Printed Tax: "+taxInMail);
				System.out.println("Printed shippingChargeInMail: "+shippingChargeInMail);
				System.out.println("Printed orderTotalInMail: "+orderTotalInMail);
				//Validation of tax
				if(tax.contentEquals(taxInMail)) 
					Reporting.updateTestReport(taxInMail+" : shown as tax in Order Confirmation mail was same as tax in Order Confirmation page: "+tax,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else{
					BigDecimal taxDouble=new BigDecimal(tax.substring(1));
					BigDecimal taxInMailDouble=new BigDecimal(taxInMail.substring(1));
					if((taxDouble.subtract(taxInMailDouble).compareTo(new BigDecimal("0.01") )==0 ) || (taxDouble.subtract(taxInMailDouble).compareTo(new BigDecimal("-0.01") )==0 )) {
						Reporting.updateTestReport(taxInMail+" : shown as tax in Order Confirmation mail has just 0.01 difference with tax in Order Confirmation page: "+tax,
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					}
					else {
						Reporting.updateTestReport(taxInMail+" : shown as tax in Order Confirmation mail was not same as tax in Order Confirmation page: "+tax,
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}

				}
				//Validation of shipping
				if(!shipping.contentEquals(" ")) {
					if(shipping.contentEquals(shippingChargeInMail))
						Reporting.updateTestReport(shippingChargeInMail+" : shown as shipping in Order Confirmation mail was same as Shipping charge in Order Confirmation page: "+shipping,
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport(shippingChargeInMail+" : shown as shipping in Order Confirmation mail was not same as Shipping charge in Order Confirmation page: "+shipping,
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				else {
					Reporting.updateTestReport("Shipping was not applicable for this order as it is a digital order",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}

				//Validation of Order Total
				if(total.contentEquals(orderTotalInMail)) 
					Reporting.updateTestReport(orderTotalInMail+" : shown as tax in Order Confirmation mail was same as tax in Order Confirmation page: "+total,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else{
					BigDecimal totalDouble=new BigDecimal(total.substring(1));
					BigDecimal totalInMailDouble=new BigDecimal(orderTotalInMail.substring(1));
					if((totalDouble.subtract(totalInMailDouble).compareTo(new BigDecimal("0.01") )==0 ) || (totalDouble.subtract(totalInMailDouble).compareTo(new BigDecimal("-0.01") )==0 )) {
						Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail has just 0.01 difference with total in Order Confirmation page: "+total,
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					}
					else {
						Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail was not same as total in Order Confirmation page: "+total,
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}

				}

				driver.switchTo().defaultContent();

			}
			catch(Exception e){
				Reporting.updateTestReport("Order total and tax validation couldn't be done: "+e.getMessage(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}*/

	/*
	 * @Description: Fetches the mail content of Order Confirmation mail, order total, tax and shipping
	 * @Date: 20/12/22
	 */

	/*public static void validateOrderConfirmationMailContentAGS_VET(WebDriver driver,String SS_path,String tax, String total) throws IOException {
			try {
				driver.switchTo().frame("ifmail");
				String orderTotalInMail=driver.findElement(By.xpath("//td[contains(text(),'Total:')]/following-sibling::td")).getText();
				String taxInMail=driver.findElement(By.xpath("//td[contains(text(),'Tax:')]/following-sibling::td")).getText();
				//Validation of tax
				if(tax.contentEquals(taxInMail))
					Reporting.updateTestReport(taxInMail+" : shown as Tax in Order Confirmation mail was same as tax in Order Confirmation page: "+tax,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(taxInMail+" : shown as Tax in Order Confirmation mail was not same as tax in Order Confirmation page: "+tax,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

				//Validation of Order Total
				if(total.contentEquals(orderTotalInMail)) 
					Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail was same as tax in Order Confirmation page: "+total,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail was not same as total in Order Confirmation page: "+total,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);			
				driver.switchTo().defaultContent();

			}
			catch(Exception e){
				Reporting.updateTestReport("Order total and tax validation couldn't be done: "+e.getMessage(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}*/

	/*
	 * @Description: Fetches the mail content of Order Confirmation mail, order total, tax and shipping
	 * @Date: 20/12/22
	 */

	/*public static void validateOrderConfirmationMailContentWEL(WebDriver driver,String SS_path,String tax,String shipping, String total) throws IOException {
			try {
				driver.switchTo().frame("ifmail");
				String orderTotalInMail=driver.findElement(By.xpath("//td[contains(text(),'Total:')]/following-sibling::td")).getText();
				String taxInMail=driver.findElement(By.xpath("//td[contains(text(),'Tax:')]/following-sibling::td")).getText();
				String shippingInMail=driver.findElement(By.xpath("//td[contains(text(),'Shipping:')]/following-sibling::td")).getText();
				//Validation of tax
				if(tax.contentEquals(taxInMail))
					Reporting.updateTestReport(taxInMail+" : shown as Tax in Order Confirmation mail was same as tax in Order Confirmation page: "+tax,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(taxInMail+" : shown as Tax in Order Confirmation mail was not same as tax in Order Confirmation page: "+tax,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

				//Validation of shipping
				if(!shipping.equalsIgnoreCase("")) {
					if(shippingInMail.contentEquals(shipping))
						Reporting.updateTestReport(shippingInMail+" : shown as Shipping charge in Order Confirmation mail was same as shipping charge in Order Confirmation page: "+shipping,
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport(shippingInMail+" : shown as Shipping charge in Order Confirmation mail was not same as Shipping charge in Order Confirmation page: "+shipping,
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				else
					Reporting.updateTestReport("Shipping charge is not applicable for this order",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);

				//Validation of Order Total
				if(total.contentEquals(orderTotalInMail)) 
					Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail was same as tax in Order Confirmation page: "+total,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail was not same as total in Order Confirmation page: "+total,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);			
				driver.switchTo().defaultContent();

			}
			catch(Exception e){
				Reporting.updateTestReport("Order total and tax validation couldn't be done: "+e.getMessage(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}*/

	/*
	 * @Author: Anindita
	 * @Description: Waits for the finish registration mail from Onboarding and clicks on that
	 */
	/*public static void clickOnFinishRegistrationMail(WebDriver driver,String SS_path,app_WileyPLUS_Repo WileyPLUS) throws IOException{
			try {
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
				int timeOutSeconds=10;
				int flag=0;
				WebElement RefreshButtonInYopmail = driver.findElement(By.xpath("//button[@id='refresh']"));
				WebElement FinishRegistrationMail = null;
				// The purpose of this loop is to wait for maximum of 60 seconds 
				for (int i = 0; i < timeOutSeconds / 5; i++) {

					try {
						driver.switchTo().frame("ifinbox");
						FinishRegistrationMail=wait1.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[contains(text(),'Welcome to Wiley')]")));

						if(FinishRegistrationMail.isDisplayed()==true)
						{
							flag=1;
							FinishRegistrationMail.click();
							driver.switchTo().defaultContent();
							break;
						}

					} catch (Exception e) {
						driver.switchTo().defaultContent();        
						RefreshButtonInYopmail.click();
					}
				}
				if(flag==1) {
					driver.switchTo().frame("ifmail");
					WileyPLUS.clickOnFinishRegistrationLinkInMail();
					driver.switchTo().defaultContent();
					Set<String> allWindowHandles = driver.getWindowHandles();
					java.util.Iterator<String> iterator = allWindowHandles.iterator();
					String yopmailHandle = iterator.next();
					String ChildWindow=iterator.next();
					driver.switchTo().window(yopmailHandle);
					driver.close();
					driver.switchTo().window(ChildWindow);
				}
				else {
					Reporting.updateTestReport("No finish registration mail  was recieved in yopmail inbox", 
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Finish registration mail couldn't be validated", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}*/

	/*
	 * @Author: Anindita
	 * @Description: Waits for the forgot password mail for VET and clicks on the reset password link
	 */
	/*public static void forgotPasswordMailForVet(WebDriver driver, String SS_path, app_VET_Repo VET) throws IOException {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				int timeOutSeconds=60;
				int flag=0;
				WebElement RefreshButtonInYopmail = driver.findElement(By.xpath("//button[@id='refresh']"));
				WebElement ResetPasswordLink = null;

				// The purpose of this loop is to wait for maximum of 60 seconds 
				for (int i = 0; i < timeOutSeconds / 5; i++) {

					try {
						driver.switchTo().frame("ifinbox");
						ResetPasswordLink=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Password Reset Request')]")));

						if(ResetPasswordLink.isDisplayed()==true)
						{
							flag=1;
							ResetPasswordLink.click();
							driver.switchTo().defaultContent();
							break;
						}

					} catch (Exception e) {
						driver.switchTo().defaultContent();        
						RefreshButtonInYopmail.click();
					}
				}

				if(flag==1)
				{driver.switchTo().frame("ifmail");
				try {
					wait.until(ExpectedConditions.elementToBeClickable(
							driver.findElement(By.xpath("(//main[@class='yscrollbar']"
									+ "/div/div/div/table/tbody/tr/td/center/table/tbody/tr/td)[2]/p[3]/"
									+ "a[contains(text(),'Click here to change your password')]"))));
					VET.clickOnResetPasswordLink();
					Set<String> allWindowHandles = driver.getWindowHandles();
					java.util.Iterator<String> iterator = allWindowHandles.iterator();
					String yopmailHandle = iterator.next();
					String ChildWindow=iterator.next();
					driver.switchTo().window(ChildWindow);
					VET.ResetPwd(excelOperation.getTestData("TC06", "VET_Test_Data", "New_Password"));
					VET.ResetConfirmPassword(excelOperation.getTestData("TC06", "VET_Test_Data", "Confirm_Password"));
					VET.ResetPassSubmit();
					VET.PasswordResetSuccess();
					driver.switchTo().window(yopmailHandle);
					driver.close();
					driver.switchTo().window(ChildWindow);
				}
				catch(Exception e) {
					Reporting.updateTestReport("Reset password link was not cliackable in the mail", 
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				}
				else {
					Reporting.updateTestReport("No reset password mail was recieved in yopmail inbox", 
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Forgot password mail couldn't be validated for VET", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}*/

	/*
	 * @Author: Anindita
	 * @Description: Waits for the forgot password mail for AGS and clicks on the reset password link
	 */
	/*public static void forgotPasswordMailForAGS(WebDriver driver, String SS_path, app_AGS_Repo AGS) throws IOException {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				int timeOutSeconds=60;
				int flag=0;
				WebElement RefreshButtonInYopmail = driver.findElement(By.xpath("//button[@id='refresh']"));
				WebElement ResetPasswordLink = null;

				// The purpose of this loop is to wait for maximum of 60 seconds 
				for (int i = 0; i < timeOutSeconds / 5; i++) {

					try {
						driver.switchTo().frame("ifinbox");
						ResetPasswordLink=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Password Reset Request')]")));

						if(ResetPasswordLink.isDisplayed()==true)
						{
							flag=1;
							ResetPasswordLink.click();
							driver.switchTo().defaultContent();
							break;
						}

					} catch (Exception e) {
						driver.switchTo().defaultContent();        
						RefreshButtonInYopmail.click();
					}
				}

				if(flag==1)
				{
					driver.switchTo().frame("ifmail");
					Thread.sleep(5000);
					AGS.clickOnResetPasswordLink();
					Set<String> allWindowHandles = driver.getWindowHandles();
					java.util.Iterator<String> iterator = allWindowHandles.iterator();
					String yopmailHandle = iterator.next();
					String ChildWindow=iterator.next();
					driver.switchTo().window(ChildWindow);
					AGS.enterNewPasswordInResetPassword(excelOperation.getTestData("TC05", "AGS_Test_Data", "Password"));
					AGS.enterConfirmPasswordInResetPassword(excelOperation.getTestData("TC05", "AGS_Test_Data", "Password"));
					AGS.clickOnResetPasswordSubmit();
					AGS.checkResetPasswordSuccessMessage();
					driver.switchTo().window(yopmailHandle);
					driver.close();
					driver.switchTo().window(ChildWindow);

				}
				else {
					Reporting.updateTestReport("No reset password mail was recieved in yopmail inbox", 
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Forgot password mail couldn't be validated", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}*/

	/*
	 * @Author: Anindita
	 * @Description: Validates the forgot password email functionality in Wiley
	 */
	/*public static int forgotPasswordEmailForWiley(WebDriver driver, String SS_path, app_Wiley_Repo wiley) throws IOException {
			try {
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
				int timeOutSeconds=60;
				int flag=0;
				WebElement RefreshButtonInYopmail = driver.findElement(By.xpath("//button[@id='refresh']"));
				WebElement ResetPasswordLink = null;

				// The purpose of this loop is to wait for maximum of 60 seconds 
				for (int i = 0; i < timeOutSeconds / 5; i++) {

					try {
						driver.switchTo().frame("ifinbox");
						ResetPasswordLink=wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Reset Password for Wiley.com')]")));

						if(ResetPasswordLink.isDisplayed()==true)
						{
							flag=1;
							ResetPasswordLink.click();
							Reporting.updateTestReport("Forgot password mail was received", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
							driver.switchTo().defaultContent();
							break;
						}

					} catch (Exception e) {
						driver.switchTo().defaultContent();        
						RefreshButtonInYopmail.click();
					}
				}

				if(flag==1)
				{
					driver.switchTo().frame("ifmail");
					driver.findElement(By.xpath("//a[text()='Reset Password']")).click();
					Reporting.updateTestReport("Reset password link was clicked successfully in the forgot password mail", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					driver.switchTo().defaultContent();

					Set<String> allWindowHandles = driver.getWindowHandles();
					java.util.Iterator<String> iterator = allWindowHandles.iterator();

					String yopmailHandle = iterator.next();
					String ChildWindow=iterator.next();
					driver.switchTo().window(yopmailHandle);
					driver.close();
					driver.switchTo().window(ChildWindow);
					return flag;
				}
				else {
					Reporting.updateTestReport("No reset password mail was recieved in yopmail inbox", 
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return flag;
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Forgot password mail couldn't be validated", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return 0;
			}
		}*/

	/*
	 * @Author: Anindita
	 * @Description: Validates the forgot password email functionality in WEL
	 */
	/*public static int forgotPasswordEmailForWEL(WebDriver driver, String SS_path, app_WEL_Repo WEL) throws IOException {
			try {
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
				int timeOutSeconds=60;
				int flag=0;
				WebElement RefreshButtonInYopmail = driver.findElement(By.xpath("//button[@id='refresh']"));
				WebElement ResetPasswordLink = null;

				// The purpose of this loop is to wait for maximum of 60 seconds 
				for (int i = 0; i < timeOutSeconds / 5; i++) {

					try {
						driver.switchTo().frame("ifinbox");
						ResetPasswordLink = wait1.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[contains(text(),'Password Reset Request')]")));

						if (ResetPasswordLink.isDisplayed() == true) {
							flag = 1;
							ResetPasswordLink.click();
							driver.switchTo().defaultContent();
							break;
						}

					} catch (Exception e) {
						driver.switchTo().defaultContent();
						RefreshButtonInYopmail.click();
					}
				}

				if(flag==1)
				{
					driver.switchTo().frame("ifmail");
					WEL.clickOnResetPasswordLink();
					driver.switchTo().defaultContent();
					Set<String> allWindowHandles = driver.getWindowHandles();
					java.util.Iterator<String> iterator = allWindowHandles.iterator();

					String yopmailHandle = iterator.next();
					String ChildWindow=iterator.next();
					driver.switchTo().window(yopmailHandle);
					driver.close();
					driver.switchTo().window(ChildWindow);
					return flag;
				}
				else {
					Reporting.updateTestReport("No reset password mail was recieved in yopmail inbox", 
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return flag;
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Forgot password mail couldn't be validated", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return 0;
			}
		}*/

}
