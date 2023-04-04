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
import PageObjectRepo.app_VET_Repo;

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
	 * @Description: Waits for the forgot password mail for VET and clicks on the reset password link
	 */
	public static void forgotPasswordMailForVet(WebDriver driver, String SS_path, app_VET_Repo VET) throws IOException {
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
				VET.ResetPwd(excelOperation.getTestData("TC04", "VET_Test_Data", "Password"));
				VET.ResetConfirmPassword(excelOperation.getTestData("TC04", "VET_Test_Data", "Password"));
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
	}

}
