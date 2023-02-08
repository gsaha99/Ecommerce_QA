package Test_Suite;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

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
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.dbConnect;
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
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_Admin", "ClientPortal_SignIN", "EmailID"));
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
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_Admin", "ClientPortal_SignIN", "PWD"));
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
			CPortal.WPSAdmin_ClickHome();
			CPortal.WPSAdmin_ClickNewClientApp();
			CPortal.WPSAdmin_ClickRegisterNewUser();
			CPortal.WPSAdmin_ClickMyWorklist();
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
		    
		    CPortal.WPSAdmin_ClickTransactionSearch();
		    CPortal.WPSAdmin_ClickLogOut();
			
		}
		catch (Exception e) 
		{
			System.out.println("Client Portal Log In with WPS Admin Role Failed" + e.getMessage());
			Reporting.updateTestReport("Client Portal Log In with WPS Admin Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	 /*
     * @Author: Jayanta
     * @Description: Validation of New Client App add for WPS Admin
     */
	@Test
	public void TC02_AddNewClientApp_WPSAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC02_Client Portal: "
					+ "Verify whether WPS admin is able to create New Client Application"
					);
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_Admin", "ClientPortal_SignIN", "EmailID"));
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
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_Admin", "ClientPortal_SignIN", "PWD"));
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
			CPortal.WPSAdmin_ClickNewClientApp();
			CPortal.WPSAdmin_SelectSOAP();
			CPortal.WPSAdmin_SelectBusinessUnit();
			String uuid = Integer.toString(((new Random().nextInt(100))+1));
			String ClientAppName="TestAuto_"+uuid;
			CPortal.WPSAdmin_Enter_ClientApp_Name(ClientAppName);
			CPortal.WPSAdmin_Enter_ClientApp_ShortName("TA"+uuid);
			CPortal.WPSAdmin_SelectUserID();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			CPortal.WPSAdmin_ClickPaymentMethod();
			CPortal.WPSAdmin_Click_DebitCard();
			CPortal.WPSAdmin_Click_Add();
			String clientappname=dbConnect.DB_Select("wps_client_app_qa","client_app","name","client_app_id");
			System.out.println(clientappname);
			if(clientappname.compareTo(ClientAppName)==0)
		       {
			
			      Reporting.updateTestReport("DB Validation is done and Client App Name is: " + clientappname,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		       }
		      else 
		       {
			      Reporting.updateTestReport("DB Validation is not done and Client App Name is not: " + ClientAppName,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		       }
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(clientappname);
			String FetchName=CPortal.WPSAdmin_Fetch_ClientApp_HomeName();
			if(ClientAppName.compareTo(FetchName)==0) 
		      {
			
			      Reporting.updateTestReport("Filtering is working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		      }
		     else 
		      {
			      Reporting.updateTestReport("Filtering is working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		      }
			
		}
		catch (Exception e) 
		{
			System.out.println("Adding new client app with WPS Admin Role Failed" + e.getMessage());
			Reporting.updateTestReport("Adding new client app with WPS Admin Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Jayanta
     * @Description: Validation of Register a New User for WPS Admin
     */
	@Test
	public void TC03_RegisterNewUser_WPSAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC03_Client Portal: "
					+ "Verify whether WPS admin able to Register New User"
					);
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_Admin", "ClientPortal_SignIN", "EmailID"));
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
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_Admin", "ClientPortal_SignIN", "PWD"));
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
			CPortal.WPSAdmin_ClickRegisterNewUser();
			CPortal.WPSAdmin_Enter_First_Name("TestAutoFirstName");
			CPortal.WPSAdmin_Enter_Last_Name("TestAutoLastName");
			String uuid = Integer.toString(((new Random().nextInt(100))+1));
			String SSOID = "TestUser"+uuid+"@wiley.com";
			CPortal.WPSAdmin_Enter_SSO_ID(SSOID);
			CPortal.WPSAdmin_SelectRole();
			CPortal.WPSAdmin_ClickRegister();
			String ssoid=dbConnect.DB_Select("wps_access_qa","user","sso_id","user_id");
			System.out.println(ssoid);
			if(ssoid.compareTo(SSOID)==0)
		       {
			
			      Reporting.updateTestReport("DB Validation is done and SSO ID is: " + ssoid,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		       }
		      else 
		       {
			      Reporting.updateTestReport("DB Validation is not done and SSO ID is not: " + SSOID,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		       }
			
			
		}
		catch (Exception e) 
		{
			System.out.println("Register new user with WPS Admin Role Failed" + e.getMessage());
			Reporting.updateTestReport("Register new user with WPS Admin Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Jayanta
     * @Description: Validation of New Client App add for WPS Admin
     */
	@Test
	public void TC07_EditClientApp_WPSAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC07_Client Portal: "
					+ "Verify the Edit option in the home page"
					);
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_Admin", "ClientPortal_SignIN", "EmailID"));
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
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_Admin", "ClientPortal_SignIN", "PWD"));
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
			CPortal.WPSAdmin_ClickNewClientApp();
			CPortal.WPSAdmin_SelectSOAP();
			CPortal.WPSAdmin_SelectBusinessUnit();
			String uuid = Integer.toString(((new Random().nextInt(100))+1));
			String ClientAppName="TestAuto_"+uuid;
			CPortal.WPSAdmin_Enter_ClientApp_Name(ClientAppName);
			CPortal.WPSAdmin_Enter_ClientApp_ShortName("TA"+uuid);
			CPortal.WPSAdmin_SelectUserID();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			CPortal.WPSAdmin_ClickPaymentMethod();
			CPortal.WPSAdmin_Click_DebitCard();
			CPortal.WPSAdmin_Click_Add();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
			String FetchName=CPortal.WPSAdmin_Fetch_ClientApp_HomeName();
			if(ClientAppName.compareTo(FetchName)==0) 
		      {
			
			      Reporting.updateTestReport("Filtering is working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		      }
		     else 
		      {
			      Reporting.updateTestReport("Filtering is working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		      }
			CPortal.WPSAdmin_ClickEditIcon();
			String NewClientAppName=ClientAppName+"_Edit";
			System.out.println(NewClientAppName);
			CPortal.WPSAdmin_ClientApp_EditName();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			CPortal.WPSAdmin_Click_Save();
			String clientappname=dbConnect.DB_Select("wps_client_app_qa","client_app","name","client_app_id");
			System.out.println(clientappname);
			if(clientappname.compareTo(NewClientAppName)==0)
		       {
			
			      Reporting.updateTestReport("DB Validation is done and Client App Name is: " + clientappname,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		       }
		      else 
		       {
			      Reporting.updateTestReport("DB Validation is not done and Client App Name is not: " + NewClientAppName,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		       }
			
		}
		catch (Exception e) 
		{
			System.out.println("Edit client app with WPS Admin Role Failed" + e.getMessage());
			Reporting.updateTestReport("Edit client app with WPS Admin Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	 /*
     * @Author: Jayanta
     * @Description: Validation of footer for WPS Admin
     */
	@Test
	public void TC09_Footer_WPSAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC09_Client Portal: "
					+ "Verify whether system display Footer as \"\"Copyright Â© 2000-2021 by John Wiley & Sons, Inc., or related companies. All rights reserved.\","
					+ " in all the screens of Client portal and Verify whether System allow user to navigate,"
					+ "to the separate tab when user clicks on \"\"Copyright Â© 2000-2021\" hyper link on footer.");
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_Admin", "ClientPortal_SignIN", "EmailID"));
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
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_Admin", "ClientPortal_SignIN", "PWD"));
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
			CPortal.WPSAdmin_ClickHome();
			CPortal.WPSAdmin_ClickFooter();
			CPortal.WPSAdmin_ClickNewClientApp();
			CPortal.WPSAdmin_ClickFooter();
			CPortal.WPSAdmin_ClickRegisterNewUser();
			CPortal.WPSAdmin_ClickFooter();
			CPortal.WPSAdmin_ClickMyWorklist();
			CPortal.WPSAdmin_ClickFooter();
			
		}
		catch (Exception e) 
		{
			System.out.println("Footer click with WPS Admin Role Failed" + e.getMessage());
			Reporting.updateTestReport("Footer click with WPS Admin Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of login functionality for WPS Support
     */
	@Test
	public void TC10_Login_WPSSupport() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC10_Client Portal: "
					+ "Validate that the user should be able to see the user name on top right corner,"
					+ " WPS Support user contains the Home,Create New Application, Register new User, My Work List,"
					+ "Transaction search  options  on header of the page,"
					+ " user should be able to logout from the application on top right corner there is a down arrow"
					+ "on click of that Logout is visible.");
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_Support", "ClientPortal_SignIN", "EmailID"));
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
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_Support", "ClientPortal_SignIN", "PWD"));
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
			CPortal.WPSAdmin_ClickHome();
			CPortal.WPSAdmin_ClickNewClientApp();
			CPortal.WPSAdmin_ClickRegisterNewUser();
			CPortal.WPSAdmin_ClickMyWorklist();
			String actualUserName = driver.findElement(By.xpath("//div[@class='userNameCircle']")).getText();
		    String expectedUserName = excelOperation.getTestData("WPS_Support", "ClientPortal_SignIN", "Initial");
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
		    
		    CPortal.WPSAdmin_ClickTransactionSearch();
		    CPortal.WPSAdmin_ClickLogOut();
			
		}
		catch (Exception e) 
		{
			System.out.println("Client Portal Log In with WPS Support Role Failed" + e.getMessage());
			Reporting.updateTestReport("Client Portal Log In with WPS Support Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of New Client App add for WPS Support
     */
	@Test
	public void TC16_RegisterNewClientApp_WPSSupport() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC16_Client Portal: "
					+ "Verify whether system allows user to register a new client app by entering values in all the mandatory fields and also verify below," 
					+ "system should display the entry in Client_app table when client_admin register a new client app using client portal," 
					+ "system should navigate user back to the Home Screen when user enter values in all the mandatory fields and click on Register Button," 
					+ "whether system navigate user back to the Home Screen with confirmation message - Client Application has been registered successfully!."

					);
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_Support", "ClientPortal_SignIN", "EmailID"));
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
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_Support", "ClientPortal_SignIN", "PWD"));
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
			CPortal.WPSAdmin_ClickNewClientApp();
			CPortal.WPSAdmin_SelectHTTP();
			CPortal.WPSAdmin_SelectBusinessUnit();
			String uuid = Integer.toString(((new Random().nextInt(100))+1));
			String ClientAppName="WPSSupportTestAuto_"+uuid;
			CPortal.WPSAdmin_Enter_ClientApp_Name(ClientAppName);
			CPortal.WPSAdmin_Enter_ClientApp_ShortName("WPSSTA"+uuid);
			CPortal.WPSAdmin_SelectUserID();
			CPortal.WPSAdmin_Enter_Template(excelOperation.getTestData("Template", "Generic_Dataset", "Data"));
			CPortal.WPSAdmin_Enter_TargetURL(excelOperation.getTestData("Target URL", "Generic_Dataset", "Data"));
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			CPortal.WPSAdmin_ClickPaymentMethod();
			CPortal.WPSAdmin_Click_DebitCard();
			//ScrollingWebPage.PageScrolldown(driver, 0, 600);
			CPortal.WPSAdmin_ClickRegister();
			String clientappname=dbConnect.DB_Select("wps_client_app_qa","client_app","name","client_app_id");
			System.out.println(clientappname);
			if(clientappname.compareTo(ClientAppName)==0)
		       {
			
			      Reporting.updateTestReport("DB Validation is done and Client App Name is: " + clientappname,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		       }
		      else 
		       {
			      Reporting.updateTestReport("DB Validation is not done and Client App Name is not: " + ClientAppName,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		       }
			/*CPortal.WPSAdmin_Enter_ClientApp_HomeName(clientappname);
			String FetchName=CPortal.WPSAdmin_Fetch_ClientApp_HomeName();
			if(ClientAppName.compareTo(FetchName)==0) 
		      {
			
			      Reporting.updateTestReport("Filtering is working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		      }
		     else 
		      {
			      Reporting.updateTestReport("Filtering is not working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		      }*/
			
		}
		catch (Exception e) 
		{
			System.out.println("Register new client app with WPS Support Role Failed" + e.getMessage());
			Reporting.updateTestReport("Register new client app with WPS Support Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}


}
