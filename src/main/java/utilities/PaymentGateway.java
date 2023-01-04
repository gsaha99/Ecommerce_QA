package utilities;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjectRepo.app_AGS_Repo;
import PageObjectRepo.app_VET_Repo;

public class PaymentGateway {
	public static void paymentWPG_AGS(WebDriver driver, app_AGS_Repo AGS, String tcNo, String path) throws IOException {
		try{
			driver.switchTo().frame(0);
            driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
            AGS.enterCardNumberWPG(excelOperation.getTestData(tcNo, "AGS_Test_Data", "Card_Number"));
            driver.switchTo().parentFrame();
            driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
            AGS.selectExpiryMonthWPG();
            driver.switchTo().parentFrame();
            driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
            AGS.selectExpiryYearWPG();
            driver.switchTo().parentFrame();
            driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='security code']")));
            AGS.enterSecurityCodeWPG(excelOperation.getTestData(tcNo, "AGS_Test_Data", "CVV"));
            driver.switchTo().parentFrame();
            AGS.clickOnMakePaymentButtonWPG();
            driver.switchTo().defaultContent();
		}
		catch(Exception e) {
			Reporting.updateTestReport("Payment Details could not be entered for WPG", CaptureScreenshot.getScreenshot(path),
					StatusDetails.FAIL);
		}
	}
	
	public static void paymentWPG_VET(WebDriver driver, app_VET_Repo VET, String tcNo, String path) throws IOException {
		try{
			driver.switchTo().frame(0);
            driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
            VET.enterCardNumberWPG(excelOperation.getTestData(tcNo, "VET_Test_Data", "Card_Number"));
            driver.switchTo().parentFrame();
            driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
            VET.selectExpiryMonthWPG();
            driver.switchTo().parentFrame();
            driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
            VET.selectExpiryYearWPG();
            driver.switchTo().parentFrame();
            driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='security code']")));
            VET.enterSecurityCodeWPG(excelOperation.getTestData(tcNo, "VET_Test_Data", "CVV"));
            driver.switchTo().parentFrame();
            VET.clickOnMakePaymentButtonWPG();
            driver.switchTo().defaultContent();
		}
		catch(Exception e) {
			Reporting.updateTestReport("Payment Details could not be entered for WPG", CaptureScreenshot.getScreenshot(path),
					StatusDetails.FAIL);
		}
	}
	
   public static void paymentWPS_AGS(WebDriver driver, app_AGS_Repo AGS, String tcNo, String path) throws IOException {
	   try{
		   driver.switchTo().frame("tokenFrame");
           driver.switchTo().frame(0);
           AGS.enterCardNumber(excelOperation.getTestData(tcNo, "AGS_Test_Data", "Card_Number"));
           AGS.enterExpiryDate(excelOperation.getTestData(tcNo, "AGS_Test_Data", "Expiry_Date"));
           AGS.enterCVC(excelOperation.getTestData(tcNo, "AGS_Test_Data", "CVV"));
           driver.switchTo().parentFrame();
           AGS.paymentDetailsSubmit();
           driver.switchTo().defaultContent();
		
	   }
	   catch(Exception e) {
		   Reporting.updateTestReport("Payment Details could not be entered for WPS", CaptureScreenshot.getScreenshot(path),
					StatusDetails.FAIL);
	   }
   }
   
   public static void paymentWPS_VET(WebDriver driver, app_VET_Repo VET, String tcNo, String path) throws IOException {
	   try{
		   driver.switchTo().frame("tokenFrame");
           driver.switchTo().frame(0);
           VET.enterCardNumber(excelOperation.getTestData(tcNo, "VET_Test_Data", "Card_Number"));
           VET.enterExpiryDate(excelOperation.getTestData(tcNo, "VET_Test_Data", "Expiry_Date"));
           VET.enterCVC(excelOperation.getTestData(tcNo, "VET_Test_Data", "CVV"));
           driver.switchTo().parentFrame();
           VET.paymentDetailsSubmit();
           driver.switchTo().defaultContent();
	   }
	   catch(Exception e) {
		   Reporting.updateTestReport("Payment Details could not be entered for WPS", CaptureScreenshot.getScreenshot(path),
					StatusDetails.FAIL);
	   }
   }

}
