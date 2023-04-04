package utilities;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import PageObjectRepo.app_Wiley_Repo;

public class PaymentGateway {

	/*
	 * @Date: 16/3/23
	 * @Description: This is a payment method which is used for entering card details in Wiley NA Cart test suite
	 */
	public static void paymentWiley(WebDriver driver, app_Wiley_Repo wiley, String tcNo, String path) throws IOException {
		try{
			wiley.enterCardHolderName(excelOperation.getTestData(tcNo, "WILEY_NA_Cart_Test_Data", "First_Name"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
			wiley.enterCardNumber(excelOperation.getTestData(tcNo, "WILEY_NA_Cart_Test_Data", "Card_Number"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
			wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData(tcNo, "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
			wiley.selectExpirationYearFromDropDown(excelOperation.getTestData(tcNo, "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
			wiley.enterCVV_Number(excelOperation.getTestData(tcNo, "WILEY_NA_Cart_Test_Data", "CVV"));
			driver.switchTo().defaultContent();			
		}
		catch(Exception e) {
			Reporting.updateTestReport("Payment Details could not be entered for Wiley", CaptureScreenshot.getScreenshot(path),
					StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 16/3/23
	 * @Description: This is a payment method which is used for entering card details in WileyPLUS test suite
	 */
	/*public static void paymentWileyPLUS(WebDriver driver, app_WileyPLUS_Repo WileyPLUS, String tcNo, String path) throws IOException {
		try{
			WileyPLUS.enterCardHolderName(excelOperation.getTestData(tcNo, "WileyPLUS_Test_Data", "First_Name"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
			WileyPLUS.enterCardNumber(excelOperation.getTestData(tcNo, "WileyPLUS_Test_Data", "Card_Number"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
			WileyPLUS.selectExpirationMonthFromDropDown(excelOperation.getTestData(tcNo, "WileyPLUS_Test_Data", "Expiry_Month"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
			WileyPLUS.selectExpirationYearFromDropDown(excelOperation.getTestData(tcNo, "WileyPLUS_Test_Data", "Expiry_Year"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
			WileyPLUS.enterCVV_Number(excelOperation.getTestData(tcNo, "WileyPLUS_Test_Data", "CVV"));
			driver.switchTo().defaultContent();			
		}
		catch(Exception e) {
			Reporting.updateTestReport("Payment Details could not be entered for WileyPLUS", CaptureScreenshot.getScreenshot(path),
					StatusDetails.FAIL);
		}
	}*/


}
