package Test_Suite;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;

import PageObjectRepo.app_AGS_Repo;
import PageObjectRepo.app_Riskified_Repo;
import utilities.CaptureScreenshot;
import utilities.OrderConfirmationMail;
import utilities.DriverModule;
import utilities.PaymentGateway;
import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

public class AGS_Test_Suite extends DriverModule {
	app_AGS_Repo AGS;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	public static String EmailConfirmationText="//button/div[contains(text(),'Order Confirmation')]";
	app_Riskified_Repo RiskifiedRepo;

	@BeforeTest
	public void initializeRepo() {
		AGS = PageFactory.initElements(driver, app_AGS_Repo.class);
		RiskifiedRepo = PageFactory.initElements(driver, app_Riskified_Repo.class);

	}

	/*
	 * @Description: Places an order with AGS Yearly subscription New User
	 * @Note: This user id has been used in TC09, TC11 and TC12
	 * @Date: 13/12/22
	 */
	@Test
	public void TC01_Anonymous_User_Registration() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC01_Anonymous_User_Registration");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL", "Generic_Dataset", "Data"));
			AGS.clickOnYearlySubscriptionButton();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'My Cart')]")));
				System.out.println("Cart page came");
				AGS.clickOnContinueButtonCartPage();
				try {
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//h1[contains(text(),"
									+ "'Create an Account or Login')]")));
					AGS.enterFirstName(excelOperation.getTestData("TC01", "AGS_Test_Data", "First_Name"));
					AGS.enterLastName(excelOperation.getTestData("TC01", "AGS_Test_Data", "Last_Name"));
					String email = AGS.enterEmailId();
					AGS.enterPassword(excelOperation.getTestData("TC01", "AGS_Test_Data", "Password"));
					AGS.clickCreateAccountButton();
					try {
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//div[contains(text(),'Billing Address')]")));
						AGS.enterBillingFirstName(excelOperation.getTestData("TC01", "AGS_Test_Data", "First_Name"));
						AGS.enterBillingLastName(excelOperation.getTestData("TC01", "AGS_Test_Data", "Last_Name"));
						AGS.selectCountry(excelOperation.getTestData("TC01", "AGS_Test_Data", "Country"));
						try{
							wait.until(ExpectedConditions.elementToBeClickable(By.id("addressLine1")));
							AGS.enterAddressLine1(excelOperation.getTestData("TC01", "AGS_Test_Data", "Address_line1"));
							AGS.enterCity(excelOperation.getTestData("TC01", "AGS_Test_Data", "City"));
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'State')]")));
								AGS.enterState(excelOperation.getTestData("TC01", "AGS_Test_Data", "State"));
							}
							catch(Exception e){
								Reporting.updateTestReport("State field was not present for this country: "
										+excelOperation.getTestData("TC01", "AGS_Test_Data", "Country"),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}
							AGS.enterZip(excelOperation.getTestData("TC01", "AGS_Test_Data", "Zip_Code"));
							AGS.enterPhoneNumber(excelOperation.getTestData("TC01", "AGS_Test_Data", "Phone_Number"));
							AGS.checkBoxBilling();
							AGS.continueToCardDetailsPage();
							String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
							System.out.println(payment_gateway);
							if (payment_gateway.contains("WPG")) {
								/* WPG Code */
								PaymentGateway.paymentWPG_AGS(driver, AGS, "TC01", SS_path);
							} else {
								/** WPS Code **/
								PaymentGateway.paymentWPS_AGS(driver, AGS, "TC01", SS_path);
							}
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed. A confirmation email')]")));
								String OrderConfirmationPage = driver.getTitle().trim();
								if (OrderConfirmationPage.equalsIgnoreCase("Order Confirmation | AGS Site")) {
									String orderID = AGS.fetchOrderId();
									String tax = AGS.fetchTax();
									String total = AGS.fetchTotal();
									excelOperation.updateTestData("TC01", "AGS_Test_Data", "Order_Id", orderID);
									excelOperation.updateTestData("TC01", "AGS_Test_Data", "Tax", tax);
									excelOperation.updateTestData("TC01", "AGS_Test_Data", "Total", total);
									excelOperation.updateTestData("TC01", "AGS_Test_Data", "Email_Id", email);
									excelOperation.updateTestData("TC09", "AGS_Test_Data", "Email_Id", email);
									excelOperation.updateTestData("TC11", "AGS_Test_Data", "Email_Id", email);
									excelOperation.updateTestData("TC12", "AGS_Test_Data", "Email_Id", email);
									AGS.logOut(driver);
									driver.get(excelOperation.getTestData("Yopmail_URL",
											"Generic_Dataset", "Data"));
									AGS.enterEmailIdInYopmail(email);
									AGS.clickOnArrowButton();
									if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
										Reporting.updateTestReport("Order Confirmation mail was received",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
										OrderConfirmationMail.validateOrderConfirmationMailContent("AGS",driver,SS_path,tax,"",total);
									}
									else {
										Reporting.updateTestReport("Order Confirmation mail was not received",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

									}
								}
								else
									Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
							}

							catch(Exception e)
							{Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
							}

						}
						catch(Exception e) {
							Reporting.updateTestReport("Address line 1 was not loaded and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Billing address page was not loaded and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Create Account / Login page was not loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Cart page was not loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	/*
	 * @Description: Places an order with AGS Monthly subscription New User Non Us
	 * (India)
	 * 
	 * @Date: 13/12/22
	 */
	@Test
	public void TC02_User_Submit_Order_Monthly() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC02_User_Submit_Order_Monthly");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL", "Generic_Dataset", "Data"));
			AGS.clickOnMonthlySubscriptionButton();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'My Cart')]")));
				System.out.println("Cart page came");
				AGS.clickOnContinueButtonCartPage();
				try {
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Create an Account or Login')]")));
					AGS.enterFirstName(excelOperation.getTestData("TC02", "AGS_Test_Data", "First_Name"));
					AGS.enterLastName(excelOperation.getTestData("TC02", "AGS_Test_Data", "Last_Name"));
					String email = AGS.enterEmailId();
					AGS.enterPassword(excelOperation.getTestData("TC02", "AGS_Test_Data", "Password"));
					AGS.clickCreateAccountButton();
					try {
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//div[contains(text(),'Billing Address')]")));
						AGS.enterBillingFirstName(excelOperation.getTestData("TC02", "AGS_Test_Data", "First_Name"));
						AGS.enterBillingLastName(excelOperation.getTestData("TC02", "AGS_Test_Data", "Last_Name"));
						AGS.selectCountry(excelOperation.getTestData("TC02", "AGS_Test_Data", "Country"));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("addressLine1")));
							AGS.enterAddressLine1(excelOperation.getTestData("TC02", "AGS_Test_Data", "Address_line1"));
							AGS.enterCity(excelOperation.getTestData("TC02", "AGS_Test_Data", "City"));
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'State')]")));
								AGS.enterState(excelOperation.getTestData("TC02", "AGS_Test_Data", "State"));
							}
							catch(Exception e){
								Reporting.updateTestReport("State field was not present for this country: "
										+excelOperation.getTestData("TC02", "AGS_Test_Data", "Country"),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}
							AGS.enterZip(excelOperation.getTestData("TC02", "AGS_Test_Data", "Zip_Code"));
							AGS.enterPhoneNumber(excelOperation.getTestData("TC02", "AGS_Test_Data", "Phone_Number"));
							AGS.checkBoxBilling();
							AGS.continueToCardDetailsPage();
							String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
							System.out.println(payment_gateway);
							if (payment_gateway.contains("WPG")) {
								/* WPG Code */
								PaymentGateway.paymentWPG_AGS(driver, AGS, "TC02", SS_path);
							} else {
								/** WPS Code **/
								PaymentGateway.paymentWPS_AGS(driver, AGS, "TC02", SS_path);
							}
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed. A confirmation email')]")));
								String OrderConfirmationPage = driver.getTitle().trim();
								if (OrderConfirmationPage.equalsIgnoreCase("Order Confirmation | AGS Site")) {
									String orderID = AGS.fetchOrderId();
									String tax = AGS.fetchTax();
									String total = AGS.fetchTotal();
									excelOperation.updateTestData("TC02", "AGS_Test_Data", "Order_Id", orderID);
									excelOperation.updateTestData("TC02", "AGS_Test_Data", "Tax", tax);
									excelOperation.updateTestData("TC02", "AGS_Test_Data", "Total", total);
									excelOperation.updateTestData("TC02", "AGS_Test_Data", "Email_Id", email);
									AGS.logOut(driver);
									driver.get(excelOperation.getTestData("Yopmail_URL",
											"Generic_Dataset", "Data"));
									AGS.enterEmailIdInYopmail(email);
									AGS.clickOnArrowButton();
									if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
										Reporting.updateTestReport("Order Confirmation mail was received",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
										OrderConfirmationMail.validateOrderConfirmationMailContent("AGS",driver,SS_path,tax,"",total);
									}
									else {
										Reporting.updateTestReport("Order Confirmation mail was not received",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								else
									Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
							}
							catch(Exception e)
							{Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport("Billing address page was not loaded and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Address line 1 was not loaded and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Create Account / Login page was not loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Cart page was not loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	/*
	 * @Date: 16/12/22
	 * @Description: Standalone registration happens and then user logs in with the same account
	 * @Note: This new user has been used in TC04 and TC05 for password reset functionalities
	 */
	@Test
	public void TC03_Standalone_Registration_and_Login() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_Standalone_Registration_and_Login");
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Login_URL", "Generic_Dataset", "Data"));
			AGS.enterFirstName(excelOperation.getTestData("TC03", "AGS_Test_Data", "First_Name"));
			AGS.enterLastName(excelOperation.getTestData("TC03", "AGS_Test_Data", "Last_Name"));
			String emailId = AGS.enterEmailId();
			AGS.enterPassword(excelOperation.getTestData("TC03", "AGS_Test_Data", "Password"));
			AGS.clickCreateAccountButton();
			String title = driver.getTitle().trim();
			if (title.equalsIgnoreCase("Edit Profile | AGS Site")) {
				Reporting.updateTestReport("Account was created successfully", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
				excelOperation.updateTestData("TC03", "AGS_Test_Data", "Email_Id", emailId);
				excelOperation.updateTestData("TC04", "AGS_Test_Data", "Email_Id", emailId);
				excelOperation.updateTestData("TC05", "AGS_Test_Data", "Email_Id", emailId);
				AGS.logOut(driver);
				driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
				driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
				driver.get(excelOperation.getTestData("AGS_Login_URL", "Generic_Dataset", "Data"));
				AGS.enterExistingUserId(excelOperation.getTestData("TC03", "AGS_Test_Data", "Email_Id"));
				AGS.enterExistingUserPassword(excelOperation.getTestData("TC03", "AGS_Test_Data", "Password"));
				AGS.clickOnLoginButton();
				String newTitle = driver.getTitle().trim();
				if (newTitle.equalsIgnoreCase("Edit Profile | AGS Site")) {
					Reporting.updateTestReport("User was able to login successfully with the newly created account",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				} else {
					Reporting.updateTestReport("User was not able to login with the newly created account",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} else {
				Reporting.updateTestReport("Account couldn't be  created", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 16/12/22
	 * 
	 * @Description: Resets the password from My Account page
	 */
	@Test
	public void TC04_Reset_Password_My_Account() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC04_Reset_Password_My_Account");
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Login_URL", "Generic_Dataset", "Data"));
			AGS.enterExistingUserId(excelOperation.getTestData("TC04", "AGS_Test_Data", "Email_Id"));
			AGS.enterExistingUserPassword(excelOperation.getTestData("TC04", "AGS_Test_Data", "Previous_Password"));
			AGS.clickOnLoginButton();
			AGS.isMyAccountPage();
			AGS.clickEditPasswordPage();
			AGS.isChangePasswordPage();
			AGS.enterPreviousPassword(excelOperation.getTestData("TC04", "AGS_Test_Data", "Previous_Password"));
			AGS.enterNewPassword(excelOperation.getTestData("TC04", "AGS_Test_Data", "Password"));
			AGS.clickPasswordSaveButton();
			if (!AGS.isPasswordResetAlertPresent().equalsIgnoreCase(""))
				Reporting.updateTestReport("Password reset was successful", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			else
				Reporting.updateTestReport("Password reset was not successful",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Password reset was not successful with error message " + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 16/12/22
	 * @Description: Reset the Password From Login Page
	 */
	@Test
	public void TC05_ResetPasswordFromLoginPage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_ResetPasswordFromLoginPage");
			driver.get(excelOperation.getTestData("AGS_Login_URL", "Generic_Dataset", "Data"));
			AGS.clickOnForgotPassword();
			AGS.enterEmailIdToGetResetPasswordMail(excelOperation.getTestData("TC05", "AGS_Test_Data", "Email_Id"));
			AGS.clickOnSubmit();
			AGS.checkAlertMessage();
			driver.get(excelOperation.getTestData("Yopmail_URL",
					"Generic_Dataset", "Data"));
			AGS.enterEmailIdInYopmail(excelOperation.getTestData("TC05", "AGS_Test_Data","Email_Id"));
			AGS.clickOnArrowButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			int timeOutSeconds=60;
			int flag=0;
			WebElement element1 = driver.findElement(By.xpath("//button[@id='refresh']"));
			WebElement element2 = null;

			/* The purpose of this loop is to wait for maximum of 60 seconds */
			for (int i = 0; i < timeOutSeconds / 5; i++) {

				try {
					driver.switchTo().frame("ifinbox");
					element2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Password Reset Request')]")));

					if(element2.isDisplayed()==true)
					{
						flag=1;
						element2.click();
						driver.switchTo().defaultContent();
						break;
					}

				} catch (Exception e) {
					driver.switchTo().defaultContent();        
					element1.click();
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
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}



	}

	/*
	 * @Date: 16/12/22
	 * @Description: Places one order zero dollar amount
	 */
	@Test
	public void TC06_Zero_Dollar_Flow() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC06_Zero_Dollar_Flow");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL", "Generic_Dataset", "Data"));
			AGS.clickOnYearlySubscriptionButton();

			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'My Cart')]")));
				AGS.addPromoToCart(excelOperation.getTestData("100_Percent_Coupon_AGS",
						"Generic_Dataset", "Data"));
				AGS.clickOnContinueButtonCartPage();
				try {
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//h1[contains(text(),"
									+ "'Create an Account or Login')]")));
					AGS.enterFirstName(excelOperation.getTestData("TC06", "AGS_Test_Data", "First_Name"));
					AGS.enterLastName(excelOperation.getTestData("TC06", "AGS_Test_Data", "Last_Name"));
					String email = AGS.enterEmailId();
					AGS.enterPassword(excelOperation.getTestData("TC06", "AGS_Test_Data", "Password"));
					AGS.clickCreateAccountButton();
					try {
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),"
								+ "'Your order is being processed. A confirmation email')]")));
						String OrderConfirmationPage = driver.getTitle().trim();
						if (OrderConfirmationPage.equalsIgnoreCase("Order Confirmation | AGS Site")) {
							String orderID = AGS.fetchOrderId();
							String total = AGS.fetchTotal();
							excelOperation.updateTestData("TC06", "AGS_Test_Data", "Order_Id", orderID);
							excelOperation.updateTestData("TC06", "AGS_Test_Data", "Total", total);
							excelOperation.updateTestData("TC06", "AGS_Test_Data", "Email_Id", email);
						}

						else
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						
					}
					catch(Exception e) {
						Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
						
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Create Account / Login page was not loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Cart page was not loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		}
		catch(Exception e) {
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 19/12/22
	 * @Description: Places one order with canada address and validates the tax
	 * @Note: This user Id has been used in TC14(Auto renew toggle validation)
	 */
	@Test
	public void TC07_Verify_tax_Non_US_Address() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC07_Verify_tax_Non_US_Address");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL", "Generic_Dataset", "Data"));
			AGS.clickOnYearlySubscriptionButton();

			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),"
						+ "'My Cart')]")));	
				AGS.clickOnContinueButtonCartPage();
				try {
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//h1[contains(text(),"
									+ "'Create an Account or Login')]")));
					AGS.enterFirstName(excelOperation.getTestData("TC07", "AGS_Test_Data", "First_Name"));
					AGS.enterLastName(excelOperation.getTestData("TC07", "AGS_Test_Data", "Last_Name"));
					String email = AGS.enterEmailId();
					AGS.enterPassword(excelOperation.getTestData("TC07", "AGS_Test_Data", "Password"));
					AGS.clickCreateAccountButton();
					try {
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//div[contains(text(),'Billing Address')]")));
						AGS.enterBillingFirstName(excelOperation.getTestData("TC07", "AGS_Test_Data", "First_Name"));
						AGS.enterBillingLastName(excelOperation.getTestData("TC07", "AGS_Test_Data", "Last_Name"));
						AGS.selectCountry(excelOperation.getTestData("TC07", "AGS_Test_Data", "Country"));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("addressLine1")));
							AGS.enterAddressLine1(excelOperation.getTestData("TC07", "AGS_Test_Data", "Address_line1"));
							AGS.enterCity(excelOperation.getTestData("TC07", "AGS_Test_Data", "City"));
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'State')]")));
								AGS.enterState(excelOperation.getTestData("TC07", "AGS_Test_Data", "State"));
							}
							catch(Exception e){
								Reporting.updateTestReport("State field was not present for this country: "
										+excelOperation.getTestData("TC01", "AGS_Test_Data", "Country"),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}
							AGS.enterZip(excelOperation.getTestData("TC07", "AGS_Test_Data", "Zip_Code"));
							AGS.enterPhoneNumber(excelOperation.getTestData("TC07", "AGS_Test_Data", "Phone_Number"));
							AGS.checkBoxBilling();
							AGS.continueToCardDetailsPage();
							String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
							System.out.println(payment_gateway);
							if (payment_gateway.contains("WPG")) {
								/* WPG Code */
								PaymentGateway.paymentWPG_AGS(driver, AGS, "TC07", SS_path);
							} else {
								/** WPS Code **/
								PaymentGateway.paymentWPS_AGS(driver, AGS, "TC07", SS_path);

							}
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed. A confirmation email')]")));
								String OrderConfirmationPage = driver.getTitle().trim();
								if (OrderConfirmationPage.equalsIgnoreCase("Order Confirmation | AGS Site")) {
									String orderID = AGS.fetchOrderId();
									String tax = AGS.fetchTax();
									String total = AGS.fetchTotal();
									excelOperation.updateTestData("TC07", "AGS_Test_Data", "Order_Id", orderID);
									excelOperation.updateTestData("TC07", "AGS_Test_Data", "Tax", tax);
									excelOperation.updateTestData("TC07", "AGS_Test_Data", "Total", total);
									excelOperation.updateTestData("TC07", "AGS_Test_Data", "Email_Id", email);
									excelOperation.updateTestData("TC14", "AGS_Test_Data", "Email_Id", email);
									AGS.logOut(driver);
									driver.get(excelOperation.getTestData("Yopmail_URL",
											"Generic_Dataset", "Data"));
									AGS.enterEmailIdInYopmail(email);
									AGS.clickOnArrowButton();
									if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
										Reporting.updateTestReport("Order Confirmation mail was received",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
										OrderConfirmationMail.validateOrderConfirmationMailContent("AGS",driver,SS_path,tax,"",total);
									}
									else {
										Reporting.updateTestReport("Order Confirmation mail was not received",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}

								else
								{Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
								}

							}
							catch(Exception e) {
								Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}

						}
						catch(Exception e) {
							Reporting.updateTestReport("Address line 1 was not loaded and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Billing address page was not loaded and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Create Account / Login page was not loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Cart page was not loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 19/12/22
	 * @Description: Places one order with partial discount code
	 */
	@Test
	public void TC08_Place_Order_With_Discount_Code() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC08_Place_Order_With_Discount_Code");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL", "Generic_Dataset", "Data"));
			AGS.clickOnYearlySubscriptionButton();			
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'My Cart')]")));	
				AGS.addPromoToCart(excelOperation.getTestData("Partial _Coupon_Code_AGS",
						"Generic_Dataset", "Data"));
				AGS.clickOnContinueButtonCartPage();
				try {
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//h1[contains(text(),"
									+ "'Create an Account or Login')]")));
					AGS.enterFirstName(excelOperation.getTestData("TC08", "AGS_Test_Data", "First_Name"));
					AGS.enterLastName(excelOperation.getTestData("TC08", "AGS_Test_Data", "Last_Name"));
					String email = AGS.enterEmailId();
					AGS.enterPassword(excelOperation.getTestData("TC08", "AGS_Test_Data", "Password"));
					AGS.clickCreateAccountButton();
					try {
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//div[contains(text(),'Billing Address')]")));
						AGS.enterBillingFirstName(excelOperation.getTestData("TC08", "AGS_Test_Data", "First_Name"));
						AGS.enterBillingLastName(excelOperation.getTestData("TC08", "AGS_Test_Data", "Last_Name"));
						AGS.selectCountry(excelOperation.getTestData("TC08", "AGS_Test_Data", "Country"));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("addressLine1")));
							AGS.enterAddressLine1(excelOperation.getTestData("TC08", "AGS_Test_Data", "Address_line1"));
							AGS.enterCity(excelOperation.getTestData("TC08", "AGS_Test_Data", "City"));
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'State')]")));
								AGS.enterState(excelOperation.getTestData("TC08", "AGS_Test_Data", "State"));
							}
							catch(Exception e){
								Reporting.updateTestReport("State field was not present for this country: "
										+excelOperation.getTestData("TC08", "AGS_Test_Data", "Country"),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}
							AGS.enterZip(excelOperation.getTestData("TC08", "AGS_Test_Data", "Zip_Code"));
							AGS.enterPhoneNumber(excelOperation.getTestData("TC08", "AGS_Test_Data", "Phone_Number"));
							AGS.checkBoxBilling();
							AGS.continueToCardDetailsPage();
							String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
							System.out.println(payment_gateway);
							if (payment_gateway.contains("WPG")) {
								/* WPG Code */
								PaymentGateway.paymentWPG_AGS(driver, AGS, "TC08", SS_path);
							} else {
								/** WPS Code **/
								PaymentGateway.paymentWPS_AGS(driver, AGS, "TC08", SS_path);

							}
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed. A confirmation email')]")));
								String OrderConfirmationPage = driver.getTitle().trim();
								if (OrderConfirmationPage.equalsIgnoreCase("Order Confirmation | AGS Site")) {
									String orderID = AGS.fetchOrderId();
									String tax = AGS.fetchTax();
									String total = AGS.fetchTotal();
									excelOperation.updateTestData("TC08", "AGS_Test_Data", "Order_Id", orderID);
									excelOperation.updateTestData("TC08", "AGS_Test_Data", "Tax", tax);
									excelOperation.updateTestData("TC08", "AGS_Test_Data", "Total", total);
									excelOperation.updateTestData("TC08", "AGS_Test_Data", "Email_Id", email);
									excelOperation.updateTestData("TC13", "AGS_Test_Data", "Email_Id", email);
									AGS.logOut(driver);
									driver.get(excelOperation.getTestData("Yopmail_URL",
											"Generic_Dataset", "Data"));
									AGS.enterEmailIdInYopmail(email);
									AGS.clickOnArrowButton();
									if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
										Reporting.updateTestReport("Order Confirmation mail was received",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
										OrderConfirmationMail.validateOrderConfirmationMailContent("AGS",driver,SS_path,tax,"",total);
									}
									else {
										Reporting.updateTestReport("Order Confirmation mail was not received",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								else
									Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
							}
							catch(Exception e)
							{Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
							}

						}
						catch(Exception e) {
							Reporting.updateTestReport("Address line 1 was not loaded and caused"
									+ "timeout exception", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Billing address page was not loaded and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Create Account / Login page was not loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Cart page was not loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 19/12/22
	 * @Description: Places one order with an existing user and Australia address
	 */
	@Test
	public void TC09_Place_Order_With_Existing_User() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC09_Place_Order_With_Existing_User");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL", "Generic_Dataset", "Data"));
			AGS.clickOnYearlySubscriptionButton();			
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//h2[contains(text(),'My Cart')]")));	
				AGS.clickOnContinueButtonCartPage();
				try {
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//h1[contains(text(),"
									+ "'Create an Account or Login')]")));
					AGS.enterExistingUserId(excelOperation.getTestData("TC03", "AGS_Test_Data", "Email_Id"));
					AGS.enterExistingUserPassword(excelOperation.getTestData("TC03", "AGS_Test_Data", "Password"));
					AGS.clickOnLoginButton();
					try {
						wait.until(ExpectedConditions.presenceOfElementLocated(
								By.xpath("//h2[contains(text(),'My Cart')]")));	
						AGS.clickOnContinueButtonCartPage();
						try {
							wait.until(ExpectedConditions
									.presenceOfElementLocated(By.xpath("//div[contains(text(),'Billing Address')]")));
							AGS.enterBillingFirstName(excelOperation.getTestData("TC09", "AGS_Test_Data", "First_Name"));
							AGS.enterBillingLastName(excelOperation.getTestData("TC09", "AGS_Test_Data", "Last_Name"));
							AGS.selectCountry(excelOperation.getTestData("TC09", "AGS_Test_Data", "Country"));
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.id("addressLine1")));
								AGS.enterAddressLine1(excelOperation.getTestData("TC09", "AGS_Test_Data", "Address_line1"));
								AGS.enterCity(excelOperation.getTestData("TC09", "AGS_Test_Data", "City"));
								try {
									wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'State')]")));
									AGS.enterState(excelOperation.getTestData("TC09", "AGS_Test_Data", "State"));
								}
								catch(Exception e){
									Reporting.updateTestReport("State field was not present for this country: "
											+excelOperation.getTestData("TC09", "AGS_Test_Data", "Country"),
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								AGS.enterZip(excelOperation.getTestData("TC09", "AGS_Test_Data", "Zip_Code"));
								AGS.enterPhoneNumber(excelOperation.getTestData("TC09", "AGS_Test_Data", "Phone_Number"));
								AGS.checkBoxBilling();
								AGS.continueToCardDetailsPage();
								String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
								System.out.println(payment_gateway);
								if (payment_gateway.contains("WPG")) {
									/* WPG Code */
									PaymentGateway.paymentWPG_AGS(driver, AGS, "TC09", SS_path);
								} else {
									/** WPS Code **/
									PaymentGateway.paymentWPS_AGS(driver, AGS, "TC09", SS_path);

								}
								try {
									wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed. A confirmation email')]")));
									String OrderConfirmationPage = driver.getTitle().trim();
									if (OrderConfirmationPage.equalsIgnoreCase("Order Confirmation | AGS Site")) {
										String orderID = AGS.fetchOrderId();
										String tax = AGS.fetchTax();
										String total = AGS.fetchTotal();
										excelOperation.updateTestData("TC09", "AGS_Test_Data", "Order_Id", orderID);
										excelOperation.updateTestData("TC09", "AGS_Test_Data", "Tax", tax);
										excelOperation.updateTestData("TC09", "AGS_Test_Data", "Total", total);
									}
									else 
										Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.FAIL);
									
								}
								catch(Exception e) {
									Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
								}

							}
							catch(Exception e) {
								Reporting.updateTestReport("Address line 1 was not loaded"
										+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Billing address page was not loaded and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Cart page was not loaded and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Create Account / Login page was not loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Cart page was not loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		}}

	/*
	 * @Description: Places an order with AGS monthly subscription New User and Non US Address (UK)
	 * 
	 * @Date: 19/12/22
	 */
	@Test
	public void TC10_Place_Order_Non_US_Address() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC10_Place_Order_Non_US_Address");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL", "Generic_Dataset", "Data"));
			AGS.clickOnMonthlySubscriptionButton();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'My Cart')]")));
				System.out.println("Cart page came");
				AGS.clickOnContinueButtonCartPage();
				try {
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//h1[contains(text(),"
									+ "'Create an Account or Login')]")));
					AGS.enterFirstName(excelOperation.getTestData("TC10", "AGS_Test_Data", "First_Name"));
					AGS.enterLastName(excelOperation.getTestData("TC10", "AGS_Test_Data", "Last_Name"));
					String email = AGS.enterEmailId();
					AGS.enterPassword(excelOperation.getTestData("TC10", "AGS_Test_Data", "Password"));
					AGS.clickCreateAccountButton();
					try {
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//div[contains(text(),'Billing Address')]")));
						AGS.enterBillingFirstName(excelOperation.getTestData("TC10", "AGS_Test_Data", "First_Name"));
						AGS.enterBillingLastName(excelOperation.getTestData("TC10", "AGS_Test_Data", "Last_Name"));
						AGS.selectCountry(excelOperation.getTestData("TC10", "AGS_Test_Data", "Country"));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("addressLine1")));
							AGS.enterAddressLine1(excelOperation.getTestData("TC10", "AGS_Test_Data", "Address_line1"));
							AGS.enterCity(excelOperation.getTestData("TC10", "AGS_Test_Data", "City"));
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'State')]")));
								AGS.enterState(excelOperation.getTestData("TC10", "AGS_Test_Data", "State"));
							}
							catch(Exception e){
								System.out.println("State field was not present for this country: "+excelOperation.getTestData("TC10", "AGS_Test_Data", "Country"));
							}
							AGS.enterZip(excelOperation.getTestData("TC10", "AGS_Test_Data", "Zip_Code"));
							AGS.enterPhoneNumber(excelOperation.getTestData("TC10", "AGS_Test_Data", "Phone_Number"));
							AGS.checkBoxBilling();
							AGS.continueToCardDetailsPage();
							String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
							System.out.println(payment_gateway);
							if (payment_gateway.contains("WPG")) {
								/* WPG Code */
								PaymentGateway.paymentWPG_AGS(driver, AGS, "TC01", SS_path);
							} else {
								/** WPS Code **/
								PaymentGateway.paymentWPS_AGS(driver, AGS, "TC01", SS_path);
							}
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed. A confirmation email')]")));

								String OrderConfirmationPage = driver.getTitle().trim();
								if (OrderConfirmationPage.equalsIgnoreCase("Order Confirmation | AGS Site")) {
									String orderID = AGS.fetchOrderId();
									String tax = AGS.fetchTax();
									String total = AGS.fetchTotal();
									excelOperation.updateTestData("TC10", "AGS_Test_Data", "Order_Id", orderID);
									excelOperation.updateTestData("TC10", "AGS_Test_Data", "Tax", tax);
									excelOperation.updateTestData("TC10", "AGS_Test_Data", "Total", total);
									excelOperation.updateTestData("TC10", "AGS_Test_Data", "Email_Id", email);
									AGS.logOut(driver);
									driver.get(excelOperation.getTestData("Yopmail_URL",
											"Generic_Dataset", "Data"));
									AGS.enterEmailIdInYopmail(email);
									AGS.clickOnArrowButton();
									if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
										Reporting.updateTestReport("Order Confirmation mail was received",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
										OrderConfirmationMail.validateOrderConfirmationMailContent("AGS",driver,SS_path,tax,"",total);
									}
									else {
										Reporting.updateTestReport("Order Confirmation mail was not received",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								else
									Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
							}

							catch(Exception e)
							{Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
							}




						}
						catch(Exception e) {
							Reporting.updateTestReport("Address line 1 was not loaded and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Billing address page was not loaded and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Create Account / Login page was not loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Cart page was not loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	/*
	 * @Description: Edits the name in edit profile, my account section
	 * 
	 * @Date: 19/12/22
	 */
	@Test
	public void TC11_Edit_Profile() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC11_Edit_Profile");
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Login_URL", "Generic_Dataset", "Data"));
			AGS.enterExistingUserId(excelOperation.getTestData("TC11", "AGS_Test_Data", "Email_Id"));
			AGS.enterExistingUserPassword(excelOperation.getTestData("TC11", "AGS_Test_Data", "Password"));
			AGS.clickOnLoginButton();
			AGS.isMyAccountPage();
			String editProfilePage=driver.getTitle().trim();
			if (editProfilePage.equalsIgnoreCase("Edit Profile | AGS Site")) {
				AGS.editProfileLastName(excelOperation.getTestData("TC11", "AGS_Test_Data", "Last_Name"));
				AGS.clickOnMyAcountSaveButton();
				Thread.sleep(1000);
				AGS.checkAlertMessageAfterUserDataUpdation();
			}
			else
				Reporting.updateTestReport("User was not on edit profile page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	/*
	 * @Description: Edits the billing address in edit payment, my account section
	 * 
	 * @Date: 19/12/22
	 */
	@Test
	public void TC12_Edit_Billing_Address() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC12_Edit_Billing_Address");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Login_URL", "Generic_Dataset", "Data"));
			AGS.enterExistingUserId(excelOperation.getTestData("TC12", "AGS_Test_Data", "Email_Id"));
			AGS.enterExistingUserPassword(excelOperation.getTestData("TC12", "AGS_Test_Data", "Password"));
			AGS.clickOnLoginButton();
			AGS.isMyAccountPage();
			AGS.clickOnEditPayment();
			AGS.enterBillingFirstName(excelOperation.getTestData("TC12", "AGS_Test_Data", "First_Name"));
			AGS.enterBillingLastName(excelOperation.getTestData("TC12", "AGS_Test_Data", "Last_Name"));
			AGS.selectCountry(excelOperation.getTestData("TC12", "AGS_Test_Data", "Country"));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("addressLine1")));
				AGS.enterAddressLine1(excelOperation.getTestData("TC12", "AGS_Test_Data", "Address_line1"));
				AGS.enterCity(excelOperation.getTestData("TC12", "AGS_Test_Data", "City"));
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'State')]")));
					AGS.enterState(excelOperation.getTestData("TC12", "AGS_Test_Data", "State"));
				}
				catch(Exception e){
					System.out.println("State field was not present for this country: "+excelOperation.getTestData("TC12", "AGS_Test_Data", "Country"));
				}
				AGS.enterZip(excelOperation.getTestData("TC12", "AGS_Test_Data", "Zip_Code"));
				AGS.enterPhoneNumber(excelOperation.getTestData("TC12", "AGS_Test_Data", "Phone_Number"));
				Thread.sleep(1000);
				AGS.clickOnMyAcountSaveButton();
				AGS.checkAlertMessageAfterUserDataUpdation();



			}
			catch(Exception e)
			{
				Reporting.updateTestReport("Address line 1 was not loaded and caused"
						+ "timeout exception", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	/*
	 * @Description: Edits the card details in edit payment, my account section
	 * 
	 * @Date: 19/12/22
	 */
	@Test
	public void TC13_Edit_Card_Details() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC13_Edit_Card_Details");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Login_URL", "Generic_Dataset", "Data"));
			AGS.enterExistingUserId(excelOperation.getTestData("TC13", "AGS_Test_Data", "Email_Id"));
			AGS.enterExistingUserPassword(excelOperation.getTestData("TC13", "AGS_Test_Data", "Password"));
			AGS.clickOnLoginButton();
			AGS.isMyAccountPage();
			AGS.clickOnEditPayment();
			Thread.sleep(1000);
			AGS.clickOnUpdateCreditCardButton();
			try{
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//div[contains(text(),'Change the credit card on"
								+ " file by entering a new one ')]")));
				String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
				if (payment_gateway.contains("WPG")) {

					PaymentGateway.paymentWPG_AGS(driver, AGS, "TC13", SS_path);
				} else {

					PaymentGateway.paymentWPS_AGS(driver, AGS, "TC13", SS_path);
				}
				String lastFourFetchedFromEditPayment=AGS.fetchLastFourCardNumberDigit();
				String input = excelOperation.getTestData("TC13", "AGS_Test_Data", "Card_Number");
				String lastFourChars = "";  

				if (input.length() > 4) {
					lastFourChars = input.substring(input.length() - 4);
				} else {
					lastFourChars = input;
				}
				if(lastFourFetchedFromEditPayment.equalsIgnoreCase(lastFourChars)) {
					Reporting.updateTestReport("The credit card details were updated successfully", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				else 
					Reporting.updateTestReport("The credit card details was not updated updated ", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);


			}
			catch(Exception e) {
				Reporting.updateTestReport("Edit card details was not loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	/*
	 * @Date: 20/12/22
	 * @Description: If the auto- renew toggle was on, then it turns it off and if it was off, it turns the toggle on 
	 */
	@Test
	public void TC14_Manage_Subscription_Auto_Renew_Toggle_validation() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC14_Manage_Subscription_Auto_Renew_Toggle_validation");
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Login_URL", "Generic_Dataset", "Data"));
			AGS.enterExistingUserId(excelOperation.getTestData("TC14", "AGS_Test_Data", "Email_Id"));
			AGS.enterExistingUserPassword(excelOperation.getTestData("TC14", "AGS_Test_Data", "Password"));
			AGS.clickOnLoginButton();
			AGS.isMyAccountPage();
			AGS.clickOnManageSubscription();
			Boolean toggle = AGS.checkIfAutoRenewToggleOn();
			if (!toggle) {
				AGS.clickOnAutoRenewToggle();
				Thread.sleep(1000);
				Reporting.updateTestReport("Auto Renew toggle was successfully switched on",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				AGS.clickOnAutoRenewToggle();
				Thread.sleep(1000);
				Reporting.updateTestReport("Auto Renew toggle was successfully switched off",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 20/12/22
	 * @Description: Places an order with last name "RiskifiedDenie" which results in riskified declined  order
	 */
	@Test
	public void TC15_Riskified_Declined_Order() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC15_Riskified_Declined_Order");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_DEV", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL_UAT3", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("AGS_Subscription_URL", "Generic_Dataset", "Data"));
			AGS.clickOnYearlySubscriptionButton();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'My Cart')]")));
				System.out.println("Cart page came");
				AGS.clickOnContinueButtonCartPage();
				try {
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//h1[contains(text(),"
									+ "'Create an Account or Login')]")));
					AGS.enterFirstName(excelOperation.getTestData("TC15", "AGS_Test_Data", "First_Name"));
					AGS.enterLastName(excelOperation.getTestData("TC15", "AGS_Test_Data", "Last_Name"));
					String email = AGS.enterEmailId();
					AGS.enterPassword(excelOperation.getTestData("TC15", "AGS_Test_Data", "Password"));
					AGS.clickCreateAccountButton();
					try {
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//div[contains(text(),'Billing Address')]")));
						AGS.enterBillingFirstName(excelOperation.getTestData("TC15", "AGS_Test_Data", "First_Name"));
						AGS.enterBillingLastName(excelOperation.getTestData("TC15", "AGS_Test_Data", "Last_Name"));
						AGS.selectCountry(excelOperation.getTestData("TC15", "AGS_Test_Data", "Country"));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("addressLine1")));
							AGS.enterAddressLine1(excelOperation.getTestData("TC15", "AGS_Test_Data", "Address_line1"));
							AGS.enterCity(excelOperation.getTestData("TC15", "AGS_Test_Data", "City"));
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'State')]")));
								AGS.enterState(excelOperation.getTestData("TC15", "AGS_Test_Data", "State"));
							}
							catch(Exception e){
								Reporting.updateTestReport("State field was not present for this country: "
										+excelOperation.getTestData("TC15", "AGS_Test_Data", "Country"),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}
							AGS.enterZip(excelOperation.getTestData("TC15", "AGS_Test_Data", "Zip_Code"));
							AGS.enterPhoneNumber(excelOperation.getTestData("TC15", "AGS_Test_Data", "Phone_Number"));
							AGS.checkBoxBilling();
							AGS.continueToCardDetailsPage();
							String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
							System.out.println(payment_gateway);
							if (payment_gateway.contains("WPG")) {
								/* WPG Code */
								PaymentGateway.paymentWPG_AGS(driver, AGS, "TC15", SS_path);
							} else {
								/** WPS Code **/
								PaymentGateway.paymentWPS_AGS(driver, AGS, "TC15", SS_path);
							}
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed. A confirmation email')]")));

								String OrderConfirmationPage = driver.getTitle().trim();
								if (OrderConfirmationPage.equalsIgnoreCase("Order Confirmation | AGS Site")) {
									String orderID = AGS.fetchOrderId();
									String tax = AGS.fetchTax();
									String total = AGS.fetchTotal();
									excelOperation.updateTestData("TC15", "AGS_Test_Data", "Order_Id", orderID);
									excelOperation.updateTestData("TC15", "AGS_Test_Data", "Tax", tax);
									excelOperation.updateTestData("TC15", "AGS_Test_Data", "Total", total);
									excelOperation.updateTestData("TC15", "AGS_Test_Data", "Email_Id", email);
									AGS.logOut(driver);
									driver.get(excelOperation.getTestData("Riskified_URL", "Generic_Dataset", "Data"));
									RiskifiedRepo.enterRiskifiedUserId(
											excelOperation.getTestData("Riskified_User_ID", "Generic_Dataset", "Data"),SS_path);
									RiskifiedRepo.enterRiskifiedPassword(
											excelOperation.getTestData("Riskified_Password", "Generic_Dataset", "Data"),SS_path);
									RiskifiedRepo.clickOnRiskifiedSignInButton(SS_path);
									try {
										wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Account Settings')]")));
										RiskifiedRepo.selectAGSFromDropdown(SS_path);
										try {
											wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'graphicstandards.com')]")));
											RiskifiedRepo.searchOrderIdInRiskified(
													excelOperation.getTestData("TC15", "AGS_Test_Data", "Order_Id"),SS_path);
											RiskifiedRepo.checkIfOrderIdIsPresentInRiskified(driver,SS_path);
											RiskifiedRepo.checkIfOrderIdIsDeclinedInRiskified(driver,SS_path);
										}
										catch(Exception e){
											Reporting.updateTestReport("AGS order search page of Riskified couldn't be loaded and caused timeout exception ", CaptureScreenshot.getScreenshot(SS_path),
													StatusDetails.FAIL);
										}
									}
									catch(Exception e) {
										Reporting.updateTestReport("Riskified homepage couldn't be loaded and caused timeout exception ", CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.FAIL);
									}
								}

								else
									Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);				
							}
							catch(Exception e) {
								Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);				
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Billing address page was not loaded and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Address line 1 was not loaded"
								+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);				
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Create Account / Login page was not loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Cart page was not loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		}
	}






}