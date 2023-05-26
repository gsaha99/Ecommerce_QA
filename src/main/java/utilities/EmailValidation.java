package utilities;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjectRepo.app_AGS_Repo;
import PageObjectRepo.app_WEL_Repo;

public class EmailValidation {
	/*
	 * @Author: Anindita
	 * @Description: Waits for the forgot password mail for AGS and clicks on the reset password link
	 */
	public static void forgotPasswordMailForAGS(WebDriver driver, String SS_path, app_AGS_Repo AGS) throws IOException {
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
		}
		catch(Exception e) {
			Reporting.updateTestReport("Forgot password mail couldn't be validated", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	

	
	/*
	 * @Author: Anindita
	 * @Description: Validates the forgot password email functionality in WEL
	 */
	public static int forgotPasswordEmailForWEL(WebDriver driver, String SS_path, app_WEL_Repo WEL) throws IOException {
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
			int timeOutSeconds=60;
			int flag=0;
			WebElement element1 = driver.findElement(By.xpath("//button[@id='refresh']"));
			WebElement element2 = null;

			/* The purpose of this loop is to wait for maximum of 60 seconds */
			for (int i = 0; i < timeOutSeconds / 5; i++) {

				try {
					driver.switchTo().frame("ifinbox");
					element2=wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Password Reset Request')]")));

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
				driver.findElement(By.xpath("//a[contains(text(),'Reset Password')]")).click();
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
	}

}
