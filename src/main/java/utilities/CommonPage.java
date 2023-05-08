package utilities;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjectRepo.app_ClientPortal_Repo;

public class CommonPage {
	//public static String SS_path=TestHarness_RegressionSuite.SS_path;
	

	public static void LogIN(WebDriver driver,app_ClientPortal_Repo CPortal,String EmailID, String PWD,String SS_path) throws IOException 
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			int Flag=0;
		//boolean loginValue=driver.findElement(By.id("otherTileText")).isDisplayed();
		       try {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("otherTileText")));
					Flag=1;
		           }
		       catch (Exception e) 
				{
					Reporting.updateTestReport("Use another account link is not present" + e.getMessage(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
		       if (Flag==1)
		          {
					CPortal.WPSAdmin_ClickAnotherUserAccount();
					try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
					CPortal.WPSAdmin_LogIN_EnterSignInEmail(EmailID);
					}
					catch (Exception e) 
					{
						
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
						
						Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
					CPortal.WPSAdmin_LogIN_ClickNext();
					}
					catch (Exception e) 
					{
						Reporting.updateTestReport("Element not found due to timeout" + e.getClass().toString(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
					CPortal.WPSAdmin_LogIN_ClickNext();
					}
					catch (Exception e) 
					{
						Reporting.updateTestReport("Login with different user ID" + e.getClass().toString(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					}
				}
		   else
		          {
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
						CPortal.WPSAdmin_LogIN_EnterSignInEmail(EmailID);
						}
						catch (Exception e) 
						{
							
							Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
						try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
						CPortal.WPSAdmin_LogIN_ClickNext();
						}
						catch (Exception e) 
						{
							
							Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
						try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
						CPortal.WPSAdmin_LogIN_EnterPWD(PWD);
						}
						catch (Exception e) 
						{
							
							Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
						try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
						CPortal.WPSAdmin_LogIN_ClickNext();
						}
						catch (Exception e) 
						{
							
							Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
						try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
						CPortal.WPSAdmin_LogIN_ClickNext();
						}
						catch (Exception e) 
						{
							
							Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}	
		           }
		}
		       catch (Exception e) 
				{
					
					Reporting.updateTestReport("Element not found due to timeout" + e.getMessage(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to check pagination in Client portal home screen for WPS Admin
	 */
	
	public static void WPSAdmin_CheckPagination(WebDriver driver,app_ClientPortal_Repo CPortal,String SS_path) throws IOException 
	{
		try {
			Actions act = new Actions(driver);
			String Pagination=driver.findElement(By.xpath("//div[@class='nctable-info']")).getText();
			String splitPaginationFirst[] = Pagination.split("of",2);
			for (String s : splitPaginationFirst)
			     System.out.println(s);
			String s1=splitPaginationFirst[1];
			String splitPaginationSecond[]=s1.split(" ");
			for (String s2 : splitPaginationSecond)
			     System.out.println(s2);
			int TotalNumber= Integer.parseInt(splitPaginationSecond[1]);
			System.out.println(TotalNumber);
			int pagenumber=TotalNumber/10;
			if (TotalNumber%10 > 0)
		    {
				pagenumber=pagenumber+1;
			    System.out.println(pagenumber);
			}
			else
			{
				Reporting.updateTestReport("Pagination is working but no records are found ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			driver.findElement(By.xpath("//input[@type='text']")).click();
			driver.findElement(By.xpath("//input[@type='text']")).clear();
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Integer.toString(pagenumber));
			//act.moveToElement(ClickNextPagination).click().build().perform();
			if (driver.findElement(By.xpath("(//div[@class='nxtbutton']/button)[1]")).isEnabled())
			Reporting.updateTestReport("Pagination is not working ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			else
				Reporting.updateTestReport("Pagination is working: ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			act.moveToElement(driver.findElement(By.xpath("(//div[@class='prevbutton']/button)[2]"))).click().build().perform();
			String currentPage = driver.findElement(By.xpath("//div[@class='pageNum']/input")).getAttribute("value");
			System.out.println(currentPage);
			int actualcurrentpgnumber=Integer.parseInt(currentPage);
			System.out.println(actualcurrentpgnumber);
			int expectedcurrentpgnumber=pagenumber-1;
			if (actualcurrentpgnumber==expectedcurrentpgnumber)
			{
				Reporting.updateTestReport("Pagination is working and previous button is clicked ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);	
			}
			else
			{
				Reporting.updateTestReport("Pagination is not working and previous button is not clicked ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
				
	    }
		catch(Exception e)
		{
			Reporting.updateTestReport("Pagination is not working: "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

}
