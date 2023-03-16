package utilities;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjectRepo.app_ClientPortal_Repo;
import Test_Suite.TestHarness_RegressionSuite;

public class CommonPage {
	//public static String SS_path=TestHarness_RegressionSuite.SS_path;

	public static void LogIN(WebDriver driver,app_ClientPortal_Repo CPortal,String EmailID, String PWD,String SS_path) throws IOException 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//boolean loginValue=driver.findElement(By.id("otherTileText")).isDisplayed();
		try {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("otherTileText")));
					CPortal.WPSAdmin_ClickAnotherUserAccount();
					try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
					CPortal.WPSAdmin_LogIN_EnterSignInEmail(EmailID);
					}
					catch (Exception e) 
					{
						System.out.println("Element not found due to timeout" + e.getMessage());
						Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					try 
					{
					wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
					CPortal.WPSAdmin_LogIN_ClickNext();
					}
					catch (Exception e) 
					{
						System.out.println("Element not found due to timeout" + e.getMessage());
						Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					try 
					{
					wait.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
					CPortal.WPSAdmin_LogIN_EnterPWD(PWD);
					}
					catch (Exception e) 
					{
						System.out.println("Element not found due to timeout" + e.getMessage());
						Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
					CPortal.WPSAdmin_LogIN_ClickNext();
					}
					catch (Exception e) 
					{
						System.out.println("Element not found due to timeout" + e.getMessage());
						Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
					CPortal.WPSAdmin_LogIN_ClickNext();
					}
					catch (Exception e) 
					{
						System.out.println("Element not found due to timeout" + e.getMessage());
						Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				
		}
		catch(Exception e)
		{
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
						CPortal.WPSAdmin_LogIN_EnterSignInEmail(EmailID);
						}
						catch (Exception e1) 
						{
							System.out.println("Element not found due to timeout" + e1.getMessage());
							Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
						try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
						CPortal.WPSAdmin_LogIN_ClickNext();
						}
						catch (Exception e1) 
						{
							System.out.println("Element not found due to timeout" + e1.getMessage());
							Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
						try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
						CPortal.WPSAdmin_LogIN_EnterPWD(PWD);
						}
						catch (Exception e1) 
						{
							System.out.println("Element not found due to timeout" + e1.getMessage());
							Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
						try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
						CPortal.WPSAdmin_LogIN_ClickNext();
						}
						catch (Exception e1) 
						{
							System.out.println("Element not found due to timeout" + e1.getMessage());
							Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
						try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
						CPortal.WPSAdmin_LogIN_ClickNext();
						}
						catch (Exception e1) 
						{
							System.out.println("Element not found due to timeout" + e1.getMessage());
							Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}	
		}
	}

}
