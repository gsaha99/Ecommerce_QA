package Test_Suite;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjectRepo.app_ClientPortal_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

public class ClientPortal_RegressionSuite extends DriverModule {
	
	app_ClientPortal_Repo CPortal;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
		
	@BeforeTest
	public void initializeRepo() {
		CPortal = PageFactory.initElements(driver, app_ClientPortal_Repo.class);
	}
	
	 /*
     * @Author: Jayanta
     * @Description: Validation of login functionality for WPS Admin
     */
	@Test
	public void TC01_Login_WPSAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC01_Client Portal: "
					+ "Validate that the user should be able to see the user name on top right corner,"
					+ " WPS Admin user contains the Home,Create New Application, Register new User, My Work List,"
					+ "Transaction search  options  on header of the page,"
					+ " user should be able to logout from the application on top right corner there is a down arrow"
					+ "on click of that Logout is visible.");
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_Admin", "ClientPortal_SignIN", "EmailID"));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
			CPortal.WPSAdmin_LogIN_ClickNext();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_Admin", "ClientPortal_SignIN", "PWD"));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
			CPortal.WPSAdmin_LogIN_ClickNext();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
			CPortal.WPSAdmin_LogIN_ClickNext();
			CPortal.WPSAdmin_ClickHome();
			Thread.sleep(5000);
			CPortal.WPSAdmin_ClickNewClientApp();
			Thread.sleep(5000);
			CPortal.WPSAdmin_ClickRegisterNewUser();
			Thread.sleep(5000);
			CPortal.WPSAdmin_ClickMyWorklist();
			Thread.sleep(5000);
			String actualUserName = driver.findElement(By.xpath("//div[@class='userNameCircle']")).getText();
		    String expectedUserName = excelOperation.getTestData("WPS_Admin", "ClientPortal_SignIN", "Initial");
		    if(actualUserName.compareTo(expectedUserName)==0) 
		      {
			
			      Reporting.updateTestReport("User Name is: " + actualUserName,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		      }
		     else 
		      {
			      Reporting.updateTestReport("User Name is not: " + expectedUserName,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		      }
		    
		    CPortal.WPSAdmin_ClickLogOut();
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginHeader")));
			
		}
		catch (Exception e) 
		{
			System.out.println("Client Portal Log In with WPS Admin Role Failed" + e.getMessage());
			Reporting.updateTestReport("Probe Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

}
