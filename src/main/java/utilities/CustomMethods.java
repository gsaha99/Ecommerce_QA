package utilities;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomMethods {
	/*
	 * @Author: Anindita
	 * @Description: Checks if order confirmation mail was received
	 * @Date: 28/11/22
	 */
	public static boolean checkIfOrderConfirmationMailReceived(WebDriver driver, String SS_path, String expectedElementXapth) throws IOException{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			int timeOutSeconds=60;
			int flag=0;
			WebElement element1 = driver.findElement(By.xpath("//button[@id='refresh']"));
			WebElement element2 = null;

			/* The purpose of this loop is to wait for maximum of 60 seconds */
			for (int i = 0; i < timeOutSeconds / 5; i++) {

				try {
					driver.switchTo().frame("ifinbox");
					element2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expectedElementXapth)));

					if(element2.isDisplayed()==true)
					{
						flag=1;
						driver.findElement(By.xpath(expectedElementXapth)).click();
						driver.switchTo().defaultContent();
						break;
					}

				} catch (Exception e) {
					driver.switchTo().defaultContent(); 
					element1.click();
					
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
	}
	
	/*
	 * @Description: Fetches the mail content of Order Confirmation mail, order total, tax and shipping
	 * @Date: 20/12/22
	 */

	public static void validateOrderConfirmationMailContentAGS_VET(WebDriver driver,String SS_path,String tax, String total) throws IOException {
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
	}

}
