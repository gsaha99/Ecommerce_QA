package Test_Suite;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
			Reporting.test = Reporting.extent.createTest("TC001_Client Portal: "
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
					+ "Verify whether WPS admin is able to create New Client Application,"
					+ "and verify the filter functionality at home screen"
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
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
			String ClientAppName="TestAuto_"+uuid;
			CPortal.WPSAdmin_Enter_ClientApp_Name(ClientAppName);
			CPortal.WPSAdmin_Enter_ClientApp_ShortName("TA"+uuid);
			CPortal.WPSAdmin_SelectUserID();
			driver.findElement(By.xpath("//h1[contains(text(),'Add New Client Application')]")).click();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			CPortal.WPSAdmin_ClickPaymentMethod();
			CPortal.WPSAdmin_Click_DebitCard();
			CPortal.WPSAdmin_Click_Add();
			String DBName=excelOperation.getTestData("TC02", "DB_Query", "DB_Name");
			String query=excelOperation.getTestData("TC02", "DB_Query", "Query");
			String datatype=excelOperation.getTestData("TC02", "DB_Query", "Data_Type");
			String clientappname=dbConnect.DB_Select(DBName,query,datatype);
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
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
			String SSOID = "TestUser"+uuid+"@wiley.com";
			CPortal.WPSAdmin_Enter_SSO_ID(SSOID);
			CPortal.WPSAdmin_SelectRole();
			CPortal.WPSAdmin_ClickRegister();
			String DBName=excelOperation.getTestData("TC03", "DB_Query", "DB_Name");
			String query=excelOperation.getTestData("TC03", "DB_Query", "Query");
			String datatype=excelOperation.getTestData("TC03", "DB_Query", "Data_Type");
			String ssoid=dbConnect.DB_Select(DBName,query,datatype);
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
     * @Description: Validation of New Client App decline for WPS Admin
     */
	@Test
	public void TC04_DeclineNewApp_WPSAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC04_Client Portal: "
					+ "Verify whether user can View under the Action option in My Worklist and decline the app"
					);
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_ClientAdmin", "ClientPortal_SignIN", "EmailID"));
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
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_ClientAdmin", "ClientPortal_SignIN", "PWD"));
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
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
			String ClientAppName="ClientAdminTestAuto_"+uuid;
			CPortal.WPSAdmin_Enter_ClientApp_Name(ClientAppName);
			CPortal.WPSAdmin_Enter_ClientApp_ShortName("CATA"+uuid);
			CPortal.WPSAdmin_SelectUserID();
			CPortal.WPSAdmin_Enter_Template(excelOperation.getTestData("Template", "Generic_Dataset", "Data"));
			CPortal.WPSAdmin_Enter_TargetURL(excelOperation.getTestData("Target URL", "Generic_Dataset", "Data"));
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			CPortal.WPSAdmin_ClickPaymentMethod();
			CPortal.WPSAdmin_Click_DebitCard();
			CPortal.WPSSuppport_Click_Register();
			CPortal.WPSAdmin_ClickLogOut();
			CPortal.WPSAdmin_ClickLogOutImage();
			CPortal.WPSAdmin_ClickLogIN();
			CPortal.WPSAdmin_ClickAnotherUserAccount();
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
			CPortal.WPSAdmin_ClickMyWorklist();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
			CPortal.WPSAdmin_Click_ViewIcon();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			CPortal.WPSAdmin_Click_Decline();
			CPortal.WPSAdmin_Decline_Click_Confirm();
			String DBName=excelOperation.getTestData("TC04", "DB_Query", "DB_Name");
			String query=excelOperation.getTestData("TC04", "DB_Query", "Query");
			String datatype=excelOperation.getTestData("TC04", "DB_Query", "Data_Type");
			String expectedStatusID=dbConnect.DB_Select(DBName,query,datatype);
			if(expectedStatusID.equalsIgnoreCase(excelOperation.getTestData("Decline", "Generic_Dataset", "Data")))
		       {
			
			      Reporting.updateTestReport("DB Validation is done, app is declined and app status is: " + expectedStatusID,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		       }
		      else 
		       {
			      Reporting.updateTestReport("DB Validation is failed and app status is not: " + expectedStatusID,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		       }
			
		}
		catch (Exception e) 
		{
			System.out.println("Declining new client app with WPS Admin Role Failed" + e.getMessage());
			Reporting.updateTestReport("Declining new client app with WPS Admin Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of Promote to Prod functionality for WPS Admin
     */
	@Test
	public void TC05_PromoteToProd_WPSAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC05_Client Portal: "
					+ "Verify whether View under the Action option available in Promote to Production," 
					+ "and Promote to Prod functionality is working," 
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
			CPortal.WPSAdmin_SelectHTTP();
			CPortal.WPSAdmin_SelectBusinessUnit();
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
			String ClientAppName="TestAuto_"+uuid;
			CPortal.WPSAdmin_Enter_ClientApp_Name(ClientAppName);
			CPortal.WPSAdmin_Enter_ClientApp_ShortName("TA"+uuid);
			CPortal.WPSAdmin_SelectUserID();
			CPortal.WPSAdmin_Enter_Template(excelOperation.getTestData("Template", "Generic_Dataset", "Data"));
			CPortal.WPSAdmin_Enter_TargetURL(excelOperation.getTestData("Target URL", "Generic_Dataset", "Data"));
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			CPortal.WPSAdmin_ClickPaymentMethod();
			CPortal.WPSAdmin_Click_DebitCard();
			CPortal.WPSAdmin_Click_Add();
			CPortal.WPSAdmin_ClickMyWorklist();
			CPortal.WPSAdmin_Click_PromoteToProd();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
			CPortal.WPSAdmin_Click_ViewIcon();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			CPortal.WPSAdmin_Click_Promote();
			CPortal.WPSAdmin_Click_Promote_Confirm();
			String DBName=excelOperation.getTestData("TC07", "DB_Query", "DB_Name");
			String query=excelOperation.getTestData("TC07", "DB_Query", "Query");
			String datatype=excelOperation.getTestData("TC07", "DB_Query", "Data_Type");
			String expectedstatusid="";
			do 
			{
			expectedstatusid=dbConnect.DB_Select(DBName,query,datatype);
			System.out.println(expectedstatusid);
			}
			while ((Integer.parseInt(expectedstatusid)!=4)&&(Integer.parseInt(expectedstatusid)!=5));
			if(expectedstatusid.equalsIgnoreCase(excelOperation.getTestData("Promoted", "Generic_Dataset", "Data")))
		       {
			
			      Reporting.updateTestReport("DB Validation is done, app is promoted to prod and Promotion Status ID: " + expectedstatusid,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		       }
		      else if(expectedstatusid.equalsIgnoreCase(excelOperation.getTestData("Promotion_Failed", "Generic_Dataset", "Data")))
		       {
			      Reporting.updateTestReport("Promotion is failed and Promotion Status ID: " + expectedstatusid,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		       }
			CPortal.WPSAdmin_ClickHome();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
			CPortal.WPSAdmin_ClickEditIcon();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			CPortal.WPSAdmin_Click_Save();
			CPortal.WPSAdmin_ClickMyWorklist();
			CPortal.WPSAdmin_Click_PromoteToProd();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
			CPortal.WPSAdmin_Click_ViewIcon();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			CPortal.WPSAdmin_Click_Promote();
			CPortal.WPSAdmin_Click_Promote_Confirm();
			do 
			{
			expectedstatusid=dbConnect.DB_Select(DBName,query,datatype);
			System.out.println(expectedstatusid);
			}
			while ((Integer.parseInt(expectedstatusid)!=4)&&(Integer.parseInt(expectedstatusid)!=5));
			if(expectedstatusid.equalsIgnoreCase(excelOperation.getTestData("Promoted", "Generic_Dataset", "Data")))
		       {
			
			      Reporting.updateTestReport("DB Validation is done, app is promoted to prod and Promotion Status ID: " + expectedstatusid,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		       }
		      else if(expectedstatusid.equalsIgnoreCase(excelOperation.getTestData("Promotion_Failed", "Generic_Dataset", "Data")))
		       {
			      Reporting.updateTestReport("Promotion is failed and Promotion Status ID: " + expectedstatusid,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		       }
			CPortal.WPSAdmin_ClickHome();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
			CPortal.WPSAdmin_Click_RevertIcon();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			CPortal.WPSAdmin_Click_RevertConfirm();
			CPortal.WPSAdmin_Click_Promote_Confirm();
			do 
			{
			expectedstatusid=dbConnect.DB_Select(DBName,query,datatype);
			System.out.println(expectedstatusid);
			}
			while ((Integer.parseInt(expectedstatusid)!=6));
			if(expectedstatusid.equalsIgnoreCase(excelOperation.getTestData("Promotion_Rollback", "Generic_Dataset", "Data")))
		       {
			
			      Reporting.updateTestReport("DB Validation is done, Promotion is reverted and Promotion Status ID: " + expectedstatusid,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		       }
		      else if(expectedstatusid.equalsIgnoreCase(excelOperation.getTestData("Promotion_Failed", "Generic_Dataset", "Data")))
		       {
			      Reporting.updateTestReport("Promotion is not reverted and Promotion Status ID: " + expectedstatusid,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		       }
				
		}
		catch (Exception e) 
		{
			System.out.println("Promote to Prod with WPS Support Role Failed" + e.getMessage());
			Reporting.updateTestReport("Promote to Prod with WPS Support Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Jayanta
     * @Description: Validation of New Client App edit for WPS Admin
     */
	@Test
	public void TC06_EditClientApp_WPSAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC06_Client Portal: "
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
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
			String ClientAppName="TestAuto_"+uuid;
			CPortal.WPSAdmin_Enter_ClientApp_Name(ClientAppName);
			CPortal.WPSAdmin_Enter_ClientApp_ShortName("TA"+uuid);
			CPortal.WPSAdmin_SelectUserID();
			driver.findElement(By.xpath("//h1[contains(text(),'Add New Client Application')]")).click();
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
			String DBName=excelOperation.getTestData("TC02", "DB_Query", "DB_Name");
			String query=excelOperation.getTestData("TC02", "DB_Query", "Query");
			String datatype=excelOperation.getTestData("TC02", "DB_Query", "Data_Type");
			String clientappname=dbConnect.DB_Select(DBName,query,datatype);
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
    * @Description: Validation of Pagination functionality for WPS Admin
    */
	@Test
	public void TC07_Pagination_WPSAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC07_Client Portal: "
					+ "Verify whether system is showing the count of the client application in home page,"
					+ " and in My Worklist and pagination is working properly for WPS Admin role.");
			
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
			CPortal.WPSAdmin_CheckPagination();
			
		}
		catch (Exception e) 
		{
			System.out.println("Pagination with WPS Admin Role Failed" + e.getMessage());
			Reporting.updateTestReport("Pagination with WPS Admin Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	
	 /*
     * @Author: Jayanta
     * @Description: Validation of footer for WPS Admin
     */
	@Test
	public void TC08_Footer_WPSAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC08_Client Portal: "
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
	public void TC09_Login_WPSSupport() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC09_Client Portal: "
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
	public void TC10_ViewNewClientApp_WPSSupport() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC10_Client Portal: "
					+ "Verify the View option in the home page"

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
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
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
			CPortal.WPSSuppport_Click_Register();
			/*String DBName=excelOperation.getTestData("TC13", "DB_Query", "DB_Name");
			String query=excelOperation.getTestData("TC13", "DB_Query", "Query");
			String datatype=excelOperation.getTestData("TC13", "DB_Query", "Data_Type");
			String clientappname=dbConnect.DB_Select(DBName,query,datatype);
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
		       }*
			
			CPortal.WPSAdmin_ClickMyWorklist();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
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
			
			
			
			/*
			 * *Author :Jayanta
			 * *Description : Logout from current role and relog in with WPS Admin role
			 */
			CPortal.WPSAdmin_ClickLogOut();
			CPortal.WPSAdmin_ClickLogOutImage();
			CPortal.WPSAdmin_ClickLogIN();
			CPortal.WPSAdmin_ClickAnotherUserAccount();
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
			CPortal.WPSAdmin_ClickMyWorklist();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
			/*String FetchName1=CPortal.WPSAdmin_Fetch_ClientApp_HomeName();
			if(ClientAppName.compareTo(FetchName1)==0) 
		      {
			
			      Reporting.updateTestReport("Filtering is working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		      }
		     else 
		      {
			      Reporting.updateTestReport("Filtering is not working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		      }
			*/
			CPortal.WPSAdmin_Click_ViewIcon();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			CPortal.WPSAdmin_Click_Approve();
			CPortal.WPSAdmin_Click_Confirm();
			CPortal.WPSAdmin_ClickLogOut();
			CPortal.WPSAdmin_ClickLogOutImage();
			CPortal.WPSAdmin_ClickLogIN();
			CPortal.WPSAdmin_ClickAnotherUserAccount();
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
			CPortal.WPSAdmin_ClickHome();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);									
			CPortal.WPSSupport_Click_ViewIcon();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);			
			CPortal.WPSAdmin_ClickLogOut();
			CPortal.WPSAdmin_ClickLogOutImage();
			
		}
			
			
		catch (Exception e) 
		{
			System.out.println("Register new client app with WPS Support Role Failed" + e.getMessage());
			Reporting.updateTestReport("Register new client app with WPS Support Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	    * @Author: Varisa
	    * @Description: Validation of Pagination functionality for WPS Support
	    */
		@Test
		public void TC11_Pagination_WPSSupport() throws IOException
		{
			
			try {
				Reporting.test = Reporting.extent.createTest("TC11_Client Portal: "
						+ "Verify whether system is showing the count of the client application in home page,"
						+ " and in My Worklist and pagination is working properly for WPS Support role.");
				
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
				CPortal.WPSAdmin_CheckPagination();
				
			}
			catch (Exception e) 
			{
				System.out.println("Pagination with WPS Support Role Failed" + e.getMessage());
				Reporting.updateTestReport("Pagination with WPS Support Role Failed" + e.getMessage(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
	
	/*
     * @Author: Varisa
     * @Description: Validation of negative scenario in New Client app register screen for WPS Support
     */
	@Test
	public void TC12_RegisterNewClientApp_Negative_Scenarios_WPSSupport() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC12_Client Portal: "
					+ "Verify the below negative scenarion in Register New Client app screen :"
					+ "whether system display error message when user tries to register new client app without entering values in Type dropdown."
					+ "whether system displays error message when user tries to register new client app without entering values in Name field."
					+ "the system  displays error message when user tries to register new client app without entering values in Template field."
					+ "system should not displays error message when user tries to register new client app without entering values in Target URL field if the type is SOAP."
					+ "whether system  displays * mandatory and error message when user tries to register new client app without entering values in Target URL and LANSA field if the type is HTTP."
					+ "whether system does not displays error message when user tries to register new client app without entering values in LANSA URL field if the type is SOAP."

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
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			//ScrollingWebPage.PageScrolldown(driver, 0, 600);
			CPortal.Click_Register_Error();
			driver.findElement(By.xpath("//h1[contains(text(),'Register New Client Application')]")).click();
            String actualErrorType = CPortal.FetchError_RegisterApp_Type();
            String expectedErrorType=excelOperation.getTestData("Type", "CPortal_Error_Message", "Error_Message");
		    if(actualErrorType.equalsIgnoreCase(expectedErrorType))
		     {

			   Reporting.updateTestReport("Error message for type field is showing and message is: " + actualErrorType,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for Type field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
		     
		    String actualErrorBU = CPortal.FetchError_RegisterApp_BU();
	        String expectedErrorBU=excelOperation.getTestData("BU", "CPortal_Error_Message", "Error_Message");
			if(actualErrorBU.equalsIgnoreCase(expectedErrorBU))
			   {

				   Reporting.updateTestReport("Error message for BU field is showing and message is: " + actualErrorBU,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			   }
			else 
			   {
				   Reporting.updateTestReport("Error message for BU field is not showing",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			    }
	        String actualErrorName = CPortal.FetchError_RegisterApp_Name();
		    String expectedErrorName=excelOperation.getTestData("Name", "CPortal_Error_Message", "Error_Message");
	        if(actualErrorName.equalsIgnoreCase(expectedErrorName))
				   {

					 Reporting.updateTestReport("Error message for Name field is showing and message is: " + actualErrorName,
					   CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				   }
		    else 
				    {
					 Reporting.updateTestReport("Error message for Name field is not showing",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				    }
				     
		    String actualErrorAppShortName = CPortal.FetchError_RegisterApp_App_Short_Name();
			String expectedErrorAppShortName=excelOperation.getTestData("App_Short_Name", "CPortal_Error_Message", "Error_Message");
		    if(actualErrorAppShortName.equalsIgnoreCase(expectedErrorAppShortName))
				  {
                   Reporting.updateTestReport("Error message for App short name field is showing and message is: " + actualErrorAppShortName,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				  }
			else 
				 {
				      Reporting.updateTestReport("Error message for App short name field is not showing",
					   CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
		    String actualErrorUserID = CPortal.FetchError_RegisterApp_User_ID();
			String expectedErrorUserID=excelOperation.getTestData("User_ID", "CPortal_Error_Message", "Error_Message");
		    if(actualErrorUserID.equalsIgnoreCase(expectedErrorUserID))
			       {
			        Reporting.updateTestReport("Error message for User_ID field is showing and message is: " + actualErrorUserID,
					  CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				   }
		    else 
				     {
					   Reporting.updateTestReport("Error message for User_ID field is not showing",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							  
			         }
						  	  
		     String actualErrorPaymentMethod = CPortal.FetchError_RegisterApp_Payment_Method();
		     String expectedErrorPaymentMethod=excelOperation.getTestData("Payment_Method", "CPortal_Error_Message", "Error_Message");
		     if(actualErrorPaymentMethod.equalsIgnoreCase(expectedErrorPaymentMethod))
			     {
                   Reporting.updateTestReport("Error message for payment method field is showing and message is: " + actualErrorPaymentMethod,
					  CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				 }
		     else 
			      {
			       Reporting.updateTestReport("Error message for payment method field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				  }	  
		     CPortal.WPSAdmin_SelectHTTP();
		     CPortal.WPSAdmin_Enter_ClientApp_Name(excelOperation.getTestData("Field_Size", "Generic_Dataset", "Data"));
		     CPortal.WPSAdmin_Enter_ClientApp_ShortName(excelOperation.getTestData("App_Short_Name", "Generic_Dataset", "Data"));   
		     ScrollingWebPage.PageDown(driver, SS_path);
			 ScrollingWebPage.PageDown(driver, SS_path);
		     ScrollingWebPage.PageScrolldown(driver, 0, 600);
		     //ScrollingWebPage.PageScrolldown(driver, 0, 600);
		     CPortal.Click_Register_Error();
			 driver.findElement(By.xpath("//h1[contains(text(),'Register New Client Application')]")).click();
    		  
				
			    String actualErrorName1 = CPortal.FetchError_RegisterApp_Name1();
		        String expectedErrorName1=excelOperation.getTestData("Name1", "CPortal_Error_Message", "Error_Message");
		        if(actualErrorName1.equalsIgnoreCase(expectedErrorName1))
			     {
	              Reporting.updateTestReport("Error message for Name1 field is showing and message is: " + actualErrorName1,
					  CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				 }
		         else 
			      {
			       Reporting.updateTestReport("Error message for Name1 field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				  }	
			
			
			String actualErrorAppShortName1 = CPortal.FetchError_RegisterApp_App_Short_Name1();
		     String expectedErrorAppShortName1=excelOperation.getTestData("App_Short_Name1", "CPortal_Error_Message", "Error_Message");
		     if(actualErrorAppShortName1.equalsIgnoreCase(expectedErrorAppShortName1))
			     {
	              Reporting.updateTestReport("Error message for App_Short_Name1 field is showing and message is: " + actualErrorAppShortName1,
					  CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				 }
		         else 
			      {
			       Reporting.updateTestReport("Error message for App_Short_Name1 field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				  }
		     
		     String actualErrorTemplate = CPortal.FetchError_RegisterApp_Template();
		     String expectedErrorTemplate=excelOperation.getTestData("Template", "CPortal_Error_Message", "Error_Message");
		     if(actualErrorTemplate.equalsIgnoreCase(expectedErrorTemplate))
			     {
	              Reporting.updateTestReport("Error message for Template is showing and message is: " + actualErrorTemplate,
					  CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				 }
		         else 
			      {
			       Reporting.updateTestReport("Error message for TEmplate field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				  }
		     String actualErrorTargetURL = CPortal.FetchError_RegisterApp_TargetURL();
		     String expectedErrorTargetURL=excelOperation.getTestData("Target URL", "CPortal_Error_Message", "Error_Message");
		     if(actualErrorTargetURL.equalsIgnoreCase(expectedErrorTargetURL))
			     {
	              Reporting.updateTestReport("Error message for TargetURL is showing and message is: " + actualErrorTargetURL,
					  CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				 }
		         else 
			      {
			       Reporting.updateTestReport("Error message for TargetURL field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				  }
			
					         		
	  }
		catch (Exception e) 
		{
			System.out.println("Register new client app Negative Scenarios with WPS Support Role Failed" + e.getMessage());
			Reporting.updateTestReport("Register new client app Negative Scenarios with WPS Support Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of New Client App add for WPS Support
     */
	@Test
	public void TC13_RegisterNewClientApp_WPSSupport() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC13_Client Portal: "
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
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
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
			CPortal.WPSSuppport_Click_Register();
			String DBName=excelOperation.getTestData("TC13", "DB_Query", "DB_Name");
			String query=excelOperation.getTestData("TC13", "DB_Query", "Query");
			String datatype=excelOperation.getTestData("TC13", "DB_Query", "Data_Type");
			String clientappname=dbConnect.DB_Select(DBName,query,datatype);
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
			
			CPortal.WPSAdmin_ClickMyWorklist();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(clientappname);
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
		      }
			
			
			
			/*
			 * *Author :Jayanta
			 * *Description : Logout from current role and relog in with WPS Admin role
			 */
			CPortal.WPSAdmin_ClickLogOut();
			CPortal.WPSAdmin_ClickLogOutImage();
			CPortal.WPSAdmin_ClickLogIN();
			CPortal.WPSAdmin_ClickAnotherUserAccount();
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
			CPortal.WPSAdmin_ClickMyWorklist();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(clientappname);
			String FetchName1=CPortal.WPSAdmin_Fetch_ClientApp_HomeName();
			if(ClientAppName.compareTo(FetchName1)==0) 
		      {
			
			      Reporting.updateTestReport("Filtering is working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		      }
		     else 
		      {
			      Reporting.updateTestReport("Filtering is not working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		      }
			
			CPortal.WPSAdmin_Click_ViewIcon();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			CPortal.WPSAdmin_Click_Approve();
			CPortal.WPSAdmin_Click_Confirm();
			CPortal.WPSAdmin_ClickLogOut();
			CPortal.WPSAdmin_ClickLogOutImage();
			CPortal.WPSAdmin_ClickLogIN();
			CPortal.WPSAdmin_ClickAnotherUserAccount();
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
			CPortal.WPSAdmin_ClickHome();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(clientappname);
			String FetchName2=CPortal.WPSAdmin_Fetch_ClientApp_HomeName();
			if(ClientAppName.compareTo(FetchName2)==0) 
		      {
			
			      Reporting.updateTestReport("Filtering is working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		      }
		     else 
		      {
			      Reporting.updateTestReport("Filtering is not working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		      }
			
		}
		catch (Exception e) 
		{
			System.out.println("Register new client app with WPS Support Role Failed" + e.getMessage());
			Reporting.updateTestReport("Register new client app with WPS Support Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	 /*
     * @Author: Varisa
     * @Description: Validation of cancel button in New Client App register screen for WPS Support
     */
	@Test
	public void TC14_RegisterNewClientApp_Cancel_WPS_Support() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC14_Client Portal: "
					+ "Verify whether system navigate user back to the Home Screen when user clicks on Cancel Button in Register New Client app screen");
			
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
			CPortal.WPSAdmin_SelectSOAP();
			CPortal.WPSAdmin_SelectBusinessUnit();
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
			String ClientAppName="ClientAdminTestAuto_"+uuid;
			CPortal.WPSAdmin_Enter_ClientApp_Name(ClientAppName);
			CPortal.WPSAdmin_Enter_ClientApp_ShortName("CATA"+uuid);
			CPortal.WPSAdmin_SelectUserID();
			driver.findElement(By.xpath("//h1[contains(text(),'Register New Client Application')]")).click();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			CPortal.WPSAdmin_ClickPaymentMethod();
			CPortal.WPSAdmin_Click_DebitCard();
			CPortal.ClientAdmin_Click_Cancel();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'No records found!')]")));
			CPortal.WPSAdmin_ClickLogOut();
			CPortal.WPSAdmin_ClickLogOutImage();
			CPortal.WPSAdmin_ClickLogIN();
			CPortal.WPSAdmin_ClickAnotherUserAccount();
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
			CPortal.WPSAdmin_ClickMyWorklist();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'No records found!')]")));
			
		}
		catch (Exception e) 
		{
			System.out.println("Cancelling new client app with WPS Support Role Failed" + e.getMessage());
			Reporting.updateTestReport("Cancelling new client app with WPS Support Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of Register a New User for WPS Support
     */
	@Test
	public void TC15_RegisterNewUser_WPSSupport() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC14_Client Portal: "
					+ "Verify whether WPS Support able to Register New User"
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
			CPortal.WPSAdmin_ClickRegisterNewUser();
			CPortal.WPSAdmin_Enter_First_Name("TestAutoSupportFirstName");
			CPortal.WPSAdmin_Enter_Last_Name("TestAutoSupportLastName");
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
			String SSOID = "TestUserSupport"+uuid+"@wiley.com";
			CPortal.WPSAdmin_Enter_SSO_ID(SSOID);
			CPortal.WPSSupport_SelectWPSSupportRole();
			CPortal.WPSAdmin_ClickRegister();
			String DBName=excelOperation.getTestData("TC16", "DB_Query", "DB_Name");
			String query=excelOperation.getTestData("TC16", "DB_Query", "Query");
			String datatype=excelOperation.getTestData("TC16", "DB_Query", "Data_Type");
			String ssoid=dbConnect.DB_Select(DBName,query,datatype);
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
			System.out.println("Register new user with WPS Support Role Failed" + e.getMessage());
			Reporting.updateTestReport("Register new user with WPS Support Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of footer for WPS Admin
     */
	@Test
	public void TC18_Footer_WPSSupport() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC16_Client Portal: "
					+ "Verify whether system display Footer as \"\"Copyright Â© 2000-2021 by John Wiley & Sons, Inc., or related companies. All rights reserved.\","
					+ " in all the screens of Client portal and Verify whether System allow user to navigate,"
					+ "to the separate tab when user clicks on \"\"Copyright Â© 2000-2021\" hyper link on footer.");
			
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
			System.out.println("Footer click with WPS Support Role Failed" + e.getMessage());
			Reporting.updateTestReport("Footer click with WPS Support Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of login functionality for Application User
     */
	@Test
	public void TC19_Login_ApplicationUser() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC19_Client Portal: "
					+ "Validate that the user should be able to see the user name on top right corner,"
					+ " Client Admin user contains the Home,"
					+ "Transaction search  options  on header of the page,"
					+ " user should be able to logout from the application on top right corner there is a down arrow"
					+ "on click of that Logout is visible.");
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("Application_User", "ClientPortal_SignIN", "EmailID"));
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
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("Application_User", "ClientPortal_SignIN", "PWD"));
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
		    CPortal.WPSAdmin_ClickTransactionSearch();
		    CPortal.WPSAdmin_ClickLogOut();
			
	
		}
		catch (Exception e) 
		{
			System.out.println("Client Portal Log In with Application User Role Failed" + e.getMessage());
			Reporting.updateTestReport("Client Portal Log In with Application User Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of negative scenario in Register a New User screen for WPS Support.
     */
	@Test
	public void TC16_RegisterNewUser_Negative_Scenarios_WPSSupport() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC17_Client Portal: "
					+ "Verify the negative scenarios for Register New User: "
					+ "1.Validate the error message displayed when user tries to register"
					+ " the user without entering values in mandatory field."
					+ "2.Check whether system display error message when user enter values"
					+ " which exceeds 35 characters in Field-First Name ,Last Name, SSO ID."
					+ "3.Validate the error message displayed when service desk user"
					+ " tries to register the user with existing user details."
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
			CPortal.WPSAdmin_ClickRegisterNewUser();
			CPortal.ClickRegister_Negative();
			String actualErrorFName = CPortal.FetchError_RegisterUser_FName();
            String expectedErrorFName=excelOperation.getTestData("FName", "CPortal_Error_Message", "Error_Message");
            if(actualErrorFName.equalsIgnoreCase(expectedErrorFName))
		     {

			   Reporting.updateTestReport("Error message for First Name field is showing and message is: " + actualErrorFName,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for First Name field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
            String actualErrorLName = CPortal.FetchError_RegisterUser_LName();
            String expectedErrorLName=excelOperation.getTestData("LName", "CPortal_Error_Message", "Error_Message");
            if(actualErrorLName.equalsIgnoreCase(expectedErrorLName))
		     {

			   Reporting.updateTestReport("Error message for Last Name field is showing and message is: " + actualErrorLName,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for Last Name field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
            String actualErrorSSOID = CPortal.FetchError_RegisterUser_SSOID();
            String expectedErrorSSOID=excelOperation.getTestData("SSOID", "CPortal_Error_Message", "Error_Message");
            if(actualErrorSSOID.equalsIgnoreCase(expectedErrorSSOID))
		     {

			   Reporting.updateTestReport("Error message for SSO ID field is showing and message is: " + actualErrorSSOID,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for SSO ID field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
            String actualErrorRole = CPortal.FetchError_RegisterUser_Role();
            String expectedErrorRole=excelOperation.getTestData("Role", "CPortal_Error_Message", "Error_Message");
            if(actualErrorRole.equalsIgnoreCase(expectedErrorRole))
		     {

			   Reporting.updateTestReport("Error message for Role field is showing and message is: " + actualErrorRole,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for Role field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
			CPortal.WPSAdmin_Enter_First_Name(excelOperation.getTestData("Field_Size", "Generic_Dataset", "Data"));
			CPortal.WPSAdmin_Enter_Last_Name(excelOperation.getTestData("Field_Size", "Generic_Dataset", "Data"));
			CPortal.WPSAdmin_Enter_SSO_ID(excelOperation.getTestData("Field_Size", "Generic_Dataset", "Data"));
			CPortal.SDUser_SelectRole();
			CPortal.ClickRegister_Negative();
			String actualErrorFNameSize = CPortal.FetchError_RegisterUser_FName_Size();
            String expectedErrorFNameSize=excelOperation.getTestData("Fname_Size", "CPortal_Error_Message", "Error_Message");
            if(actualErrorFNameSize.equalsIgnoreCase(expectedErrorFNameSize))
		     {

			   Reporting.updateTestReport("Error message for First Name field size is showing and message is: " + actualErrorFNameSize,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for First Name field size is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
            String actualErrorLNameSize = CPortal.FetchError_RegisterUser_LName_Size();
            String expectedErrorLNameSize=excelOperation.getTestData("Lname_Size", "CPortal_Error_Message", "Error_Message");
            if(actualErrorLNameSize.equalsIgnoreCase(expectedErrorLNameSize))
		     {

			   Reporting.updateTestReport("Error message for Last Name field size is showing and message is: " + actualErrorLNameSize,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for Last Name field size is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
            String actualErrorSSOIDSize = CPortal.FetchError_RegisterUser_SSOID_Size();
            String expectedErrorSSOIDSize =excelOperation.getTestData("SSOID_Size", "CPortal_Error_Message", "Error_Message");
            if(actualErrorSSOIDSize.equalsIgnoreCase(expectedErrorSSOIDSize))
		     {

			   Reporting.updateTestReport("Error message for SSO ID field size is showing and message is: " + actualErrorSSOIDSize,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for SSO ID field size is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
            CPortal.WPSAdmin_Enter_First_Name("TestAutoFirstName");
			CPortal.WPSAdmin_Enter_Last_Name("TestAutoLastName");
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
			String SSOID = "TestSDUser"+uuid+"@wiley.com";
			CPortal.WPSAdmin_Enter_SSO_ID(SSOID);
			CPortal.SDUser_SelectRole();
			CPortal.WPSAdmin_ClickRegister();
			CPortal.WPSAdmin_Enter_First_Name("TestAutoFirstName");
			CPortal.WPSAdmin_Enter_Last_Name("TestAutoLastName");
			CPortal.WPSAdmin_Enter_SSO_ID(SSOID);
			CPortal.SDUser_SelectRole();
			CPortal.ClickRegister_ExistingUser();
				
		}
		catch (Exception e) 
		{
			System.out.println("Register new user with Service Desk User Role Failed" + e.getMessage());
			Reporting.updateTestReport("Register new user with Service Desk User Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	 /*
     * @Author: Jayanta
     * @Description: Validation of login functionality for Client Admin
     */
	@Test
	public void TC25_Login_ClientAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC25_Client Portal: "
					+ "Validate that the user should be able to see the user name on top right corner,"
					+ " Client Admin user contains the Home,Register New Application,"
					+ "Transaction search  options  on header of the page,"
					+ " user should be able to logout from the application on top right corner there is a down arrow"
					+ "on click of that Logout is visible.");
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_ClientAdmin", "ClientPortal_SignIN", "EmailID"));
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
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_ClientAdmin", "ClientPortal_SignIN", "PWD"));
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
			String actualUserName = driver.findElement(By.xpath("//div[@class='userNameCircle']")).getText();
		    String expectedUserName = excelOperation.getTestData("WPS_ClientAdmin", "ClientPortal_SignIN", "Initial");
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
			System.out.println("Client Portal Log In with Client Admin Role Failed" + e.getMessage());
			Reporting.updateTestReport("Client Portal Log In with Client Admin Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	 /*
     * @Author: Jayanta
     * @Description: Validation of New Client App add for Client Admin
     */
	@Test
	public void TC26_RegisterNewClientApp_ClientAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC26_Client Portal: "
					+ "Verify whether Client admin is able to register New Client Application"
					);
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_ClientAdmin", "ClientPortal_SignIN", "EmailID"));
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
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_ClientAdmin", "ClientPortal_SignIN", "PWD"));
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
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
			String ClientAppName="ClientAdminTestAuto_"+uuid;
			CPortal.WPSAdmin_Enter_ClientApp_Name(ClientAppName);
			CPortal.WPSAdmin_Enter_ClientApp_ShortName("CATA"+uuid);
			CPortal.WPSAdmin_SelectUserID();
			CPortal.WPSAdmin_Enter_Template(excelOperation.getTestData("Template", "Generic_Dataset", "Data"));
			CPortal.WPSAdmin_Enter_TargetURL(excelOperation.getTestData("Target URL", "Generic_Dataset", "Data"));
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			CPortal.WPSAdmin_ClickPaymentMethod();
			CPortal.WPSAdmin_Click_DebitCard();
			CPortal.WPSSuppport_Click_Register();
			String DBName=excelOperation.getTestData("TC02", "DB_Query", "DB_Name");
			String query=excelOperation.getTestData("TC02", "DB_Query", "Query");
			String datatype=excelOperation.getTestData("TC02", "DB_Query", "Data_Type");
			String clientappname=dbConnect.DB_Select(DBName,query,datatype);
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
			CPortal.WPSAdmin_ClickLogOut();
			CPortal.WPSAdmin_ClickLogOutImage();
			CPortal.WPSAdmin_ClickLogIN();
			CPortal.WPSAdmin_ClickAnotherUserAccount();
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
			CPortal.WPSAdmin_ClickMyWorklist();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
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
		      }
			
			CPortal.WPSAdmin_Click_ViewIcon();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			CPortal.WPSAdmin_Click_Approve();
			CPortal.WPSAdmin_Click_Confirm();
			CPortal.WPSAdmin_ClickLogOut();
			CPortal.WPSAdmin_ClickLogOutImage();
			CPortal.WPSAdmin_ClickLogIN();
			CPortal.WPSAdmin_ClickAnotherUserAccount();
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_ClientAdmin", "ClientPortal_SignIN", "EmailID"));
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
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_ClientAdmin", "ClientPortal_SignIN", "PWD"));
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
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
			String FetchName1=CPortal.WPSAdmin_Fetch_ClientApp_HomeName();
			if(ClientAppName.compareTo(FetchName1)==0) 
		      {
			
			      Reporting.updateTestReport("Filtering is working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		      }
		     else 
		      {
			      Reporting.updateTestReport("Filtering is not working",
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		      }
			CPortal.WPSAdmin_ClickEditIcon();
			CPortal.ClientAdmin_EditBusinessUnit();
			driver.findElement(By.xpath("//h1[contains(text(),'Edit Client Application')]")).click();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			CPortal.WPSAdmin_Click_Save();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
			CPortal.WPSAdmin_ClickEditIcon();
			String actualBU=excelOperation.getTestData("Research", "Generic_Dataset", "Data");
			String DBName1=excelOperation.getTestData("TC38", "DB_Query", "DB_Name");
			String query1=excelOperation.getTestData("TC38", "DB_Query", "Query");
			String datatype1=excelOperation.getTestData("TC38", "DB_Query", "Data_Type");
			String expectedBU=dbConnect.DB_Select(DBName1,query1,datatype1);
			if(expectedBU.equalsIgnoreCase(actualBU))
		       {
			
			      Reporting.updateTestReport("DB Validation is done, app is edited and  new Business Unit ID is: " + expectedBU,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		       }
		      else 
		       {
			      Reporting.updateTestReport("DB Validation is failed and new Business Unit ID is not: " + expectedBU,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		       }
			
			
			
		}
		catch (Exception e) 
		{
			System.out.println("Registering new client app with Client Admin Role and edit client app Failed" + e.getMessage());
			Reporting.updateTestReport("Registering new client app with Client Admin Role and edit client app Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Jayanta
     * @Description: Validation of negative scenario in New Client app register screen for Client Admin
     */
	@Test
	public void TC27_RegisterNewClientApp_Negative_Scenarios_ClientAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC27_Client Portal: "
					+ "Verify the negative scenarios for Register New Client App :"
					+ "1.Validate the error message displayed when user tries to register the user without entering values in mandatory field."
					+ "2. Check whether system display error message when user enter values which exceeds 35 characters in Field-Name ,Template, Target URL."
                    );
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_ClientAdmin", "ClientPortal_SignIN", "EmailID"));
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
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_ClientAdmin", "ClientPortal_SignIN", "PWD"));
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
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			CPortal.Click_Register_Error();
			driver.findElement(By.xpath("//h1[contains(text(),'Register New Client Application')]")).click();
            String actualErrorType = CPortal.FetchError_RegisterApp_Type();
            String expectedErrorType=excelOperation.getTestData("Type", "CPortal_Error_Message", "Error_Message");
            if(actualErrorType.equalsIgnoreCase(expectedErrorType))
		     {

			   Reporting.updateTestReport("Error message for type field is showing and message is: " + actualErrorType,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for type field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
		     String actualErrorBU = CPortal.FetchError_RegisterApp_BU();
		     String expectedErrorBU=excelOperation.getTestData("BU", "CPortal_Error_Message", "Error_Message");
		     if(actualErrorBU.equalsIgnoreCase(expectedErrorBU))
			   {

				   Reporting.updateTestReport("Error message for BU field is showing and message is: " + actualErrorBU,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			   }
			else 
			   {
				   Reporting.updateTestReport("Error message for BU field is not showing",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			    }
	        String actualErrorName = CPortal.FetchError_RegisterApp_Name();
		    String expectedErrorName=excelOperation.getTestData("Name", "CPortal_Error_Message", "Error_Message");
	        if(actualErrorName.equalsIgnoreCase(expectedErrorName))
				   {

					 Reporting.updateTestReport("Error message for Name field is showing and message is: " + actualErrorName,
					   CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				   }
		    else 
				    {
					 Reporting.updateTestReport("Error message for Name field is not showing",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				    }
				     
		    String actualErrorAppShortName = CPortal.FetchError_RegisterApp_App_Short_Name();
			String expectedErrorAppShortName=excelOperation.getTestData("App_Short_Name", "CPortal_Error_Message", "Error_Message");
		    if(actualErrorAppShortName.equalsIgnoreCase(expectedErrorAppShortName))
				  {
                 Reporting.updateTestReport("Error message for App short name field is showing and message is: " + actualErrorAppShortName,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				  }
			else 
				 {
				      Reporting.updateTestReport("Error message for App short name field is not showing",
					   CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
		    String actualErrorUserID = CPortal.FetchError_RegisterApp_User_ID();
			String expectedErrorUserID=excelOperation.getTestData("User_ID", "CPortal_Error_Message", "Error_Message");
		    if(actualErrorUserID.equalsIgnoreCase(expectedErrorUserID))
			       {
			        Reporting.updateTestReport("Error message for User_ID field is showing and message is: " + actualErrorUserID,
					  CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				   }
		    else 
				     {
					   Reporting.updateTestReport("Error message for User_ID field is not showing",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							  
			         }
		    ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			String actualErrorPaymentMethod = CPortal.FetchError_RegisterApp_Payment_Method();
			String expectedErrorPaymentMethod=excelOperation.getTestData("Payment_Method", "CPortal_Error_Message", "Error_Message");
			if(actualErrorPaymentMethod.equalsIgnoreCase(expectedErrorPaymentMethod))
		     {
          Reporting.updateTestReport("Error message for payment method field is showing and message is: " + actualErrorPaymentMethod,
				  CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			 }
	        else 
		      {
		       Reporting.updateTestReport("Error message for payment method field is not showing",
				CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			  }
		     
		}
		catch (Exception e) 
		{
			System.out.println("Register new client app Negative Scenarios with Client Admin Role Failed" + e.getMessage());
			Reporting.updateTestReport("Register new client app Negative Scenarios with Client Admin Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	 /*
     * @Author: Jayanta
     * @Description: Validation of cancel button in New Client App register screen for Client Admin
     */
	@Test
	public void TC28_RegisterNewClientApp_Cancel_ClientAdmin() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC28_Client Portal: "
					+ "Validate the functionality of Cancel button displayed in the "
					+ "Register New Client Application Screen with Type SOAP."
					);
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("WPS_ClientAdmin", "ClientPortal_SignIN", "EmailID"));
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
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("WPS_ClientAdmin", "ClientPortal_SignIN", "PWD"));
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
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
			String ClientAppName="ClientAdminTestAuto_"+uuid;
			CPortal.WPSAdmin_Enter_ClientApp_Name(ClientAppName);
			CPortal.WPSAdmin_Enter_ClientApp_ShortName("CATA"+uuid);
			CPortal.WPSAdmin_SelectUserID();
			driver.findElement(By.xpath("//h1[contains(text(),'Register New Client Application')]")).click();
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			CPortal.WPSAdmin_ClickPaymentMethod();
			CPortal.WPSAdmin_Click_DebitCard();
			CPortal.ClientAdmin_Click_Cancel();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'No records found!')]")));
			CPortal.WPSAdmin_ClickLogOut();
			CPortal.WPSAdmin_ClickLogOutImage();
			CPortal.WPSAdmin_ClickLogIN();
			CPortal.WPSAdmin_ClickAnotherUserAccount();
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
			CPortal.WPSAdmin_ClickMyWorklist();
			CPortal.WPSAdmin_Enter_ClientApp_HomeName(ClientAppName);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'No records found!')]")));
			
		}
		catch (Exception e) 
		{
			System.out.println("Cancelling new client app with Client Admin Role Failed" + e.getMessage());
			Reporting.updateTestReport("Cancelling new client app with Client Admin Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Jayanta
     * @Description: Validation of login functionality for Service Desk User
     */
	@Test
	public void TC29_Login_ServiceDeskUser() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC29_Client Portal: "
					+ "Login into Client Portal as Service Desk User"
					+ "and Validate the Header and the Footer of Register New User  when service desk user is logged in."
					+ "Also Validate the Logout Functionality as Service Desk User."
					);
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("Service_Desk_User", "ClientPortal_SignIN", "EmailID"));
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
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("Service_Desk_User", "ClientPortal_SignIN", "PWD"));
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
			String actualUserName = driver.findElement(By.xpath("//div[@class='userNameCircle']")).getText();
		    String expectedUserName = excelOperation.getTestData("Service_Desk_User", "ClientPortal_SignIN", "Initial");
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
		    
		    CPortal.WPSAdmin_ClickFooter();
		    CPortal.WPSAdmin_ClickLogOut();
			
	
		}
		catch (Exception e) 
		{
			System.out.println("Client Portal Log In with Service Desk User Role Failed" + e.getMessage());
			Reporting.updateTestReport("Client Portal Log In with Service Desk User Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Jayanta
     * @Description: Validation of negative scenario in Register a New User screen for Service Desk User
     */
	@Test
	public void TC30_RegisterNewUser_Negative_Scenarios_SDUser() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC30_Client Portal: "
					+ "Verify the negative scenarios for Register New User: "
					+ "1.Validate the error message displayed when user tries to register"
					+ " the user without entering values in mandatory field."
					+ "2.Check whether system display error message when user enter values"
					+ " which exceeds 35 characters in Field-First Name ,Last Name, SSO ID."
					+ "3.Validate the error message displayed when service desk user"
					+ " tries to register the user with existing user details."
					);
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("Service_Desk_User", "ClientPortal_SignIN", "EmailID"));
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
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("Service_Desk_User", "ClientPortal_SignIN", "PWD"));
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
			CPortal.ClickRegister_Negative();
			String actualErrorFName = CPortal.FetchError_RegisterUser_FName();
            String expectedErrorFName=excelOperation.getTestData("FName", "CPortal_Error_Message", "Error_Message");
            if(actualErrorFName.equalsIgnoreCase(expectedErrorFName))
		     {

			   Reporting.updateTestReport("Error message for First Name field is showing and message is: " + actualErrorFName,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for First Name field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
            String actualErrorLName = CPortal.FetchError_RegisterUser_LName();
            String expectedErrorLName=excelOperation.getTestData("LName", "CPortal_Error_Message", "Error_Message");
            if(actualErrorLName.equalsIgnoreCase(expectedErrorLName))
		     {

			   Reporting.updateTestReport("Error message for Last Name field is showing and message is: " + actualErrorLName,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for Last Name field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
            String actualErrorSSOID = CPortal.FetchError_RegisterUser_SSOID();
            String expectedErrorSSOID=excelOperation.getTestData("SSOID", "CPortal_Error_Message", "Error_Message");
            if(actualErrorSSOID.equalsIgnoreCase(expectedErrorSSOID))
		     {

			   Reporting.updateTestReport("Error message for SSO ID field is showing and message is: " + actualErrorSSOID,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for SSO ID field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
            String actualErrorRole = CPortal.FetchError_RegisterUser_Role();
            String expectedErrorRole=excelOperation.getTestData("Role", "CPortal_Error_Message", "Error_Message");
            if(actualErrorRole.equalsIgnoreCase(expectedErrorRole))
		     {

			   Reporting.updateTestReport("Error message for Role field is showing and message is: " + actualErrorRole,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for Role field is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
			CPortal.WPSAdmin_Enter_First_Name(excelOperation.getTestData("Field_Size", "Generic_Dataset", "Data"));
			CPortal.WPSAdmin_Enter_Last_Name(excelOperation.getTestData("Field_Size", "Generic_Dataset", "Data"));
			CPortal.WPSAdmin_Enter_SSO_ID(excelOperation.getTestData("Field_Size", "Generic_Dataset", "Data"));
			CPortal.SDUser_SelectRole();
			CPortal.ClickRegister_Negative();
			String actualErrorFNameSize = CPortal.FetchError_RegisterUser_FName_Size();
            String expectedErrorFNameSize=excelOperation.getTestData("Fname_Size", "CPortal_Error_Message", "Error_Message");
            if(actualErrorFNameSize.equalsIgnoreCase(expectedErrorFNameSize))
		     {

			   Reporting.updateTestReport("Error message for First Name field size is showing and message is: " + actualErrorFNameSize,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for First Name field size is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
            String actualErrorLNameSize = CPortal.FetchError_RegisterUser_LName_Size();
            String expectedErrorLNameSize=excelOperation.getTestData("Lname_Size", "CPortal_Error_Message", "Error_Message");
            if(actualErrorLNameSize.equalsIgnoreCase(expectedErrorLNameSize))
		     {

			   Reporting.updateTestReport("Error message for Last Name field size is showing and message is: " + actualErrorLNameSize,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for Last Name field size is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
            String actualErrorSSOIDSize = CPortal.FetchError_RegisterUser_SSOID_Size();
            String expectedErrorSSOIDSize =excelOperation.getTestData("SSOID_Size", "CPortal_Error_Message", "Error_Message");
            if(actualErrorSSOIDSize.equalsIgnoreCase(expectedErrorSSOIDSize))
		     {

			   Reporting.updateTestReport("Error message for SSO ID field size is showing and message is: " + actualErrorSSOIDSize,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		     }
		     else 
		     {
			   Reporting.updateTestReport("Error message for SSO ID field size is not showing",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		     }
            CPortal.WPSAdmin_Enter_First_Name("TestAutoFirstName");
			CPortal.WPSAdmin_Enter_Last_Name("TestAutoLastName");
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
			String SSOID = "TestSDUser"+uuid+"@wiley.com";
			CPortal.WPSAdmin_Enter_SSO_ID(SSOID);
			CPortal.SDUser_SelectRole();
			CPortal.WPSAdmin_ClickRegister();
			CPortal.WPSAdmin_Enter_First_Name("TestAutoFirstName");
			CPortal.WPSAdmin_Enter_Last_Name("TestAutoLastName");
			CPortal.WPSAdmin_Enter_SSO_ID(SSOID);
			CPortal.SDUser_SelectRole();
			CPortal.ClickRegister_ExistingUser();
				
		}
		catch (Exception e) 
		{
			System.out.println("Register new user with Service Desk User Role Failed" + e.getMessage());
			Reporting.updateTestReport("Register new user with Service Desk User Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of Register a New User for Service Desk User
     */
	@Test
	public void TC31_RegisterNewUser_SDUser() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC31_Client Portal: "
					+ "1.whether service desk user able to register the new user with role as - "
					+ "Client admin, Application User, Service Desk User,"
					+ "2.Validate the entry in DB (Table-User,User_role) once the new user"
					+ "is registered by service desk user in client portal."
					);
			
			driver.get(excelOperation.getTestData("ClientPortal_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));
			CPortal.WPSAdmin_LogIN_EnterSignInEmail(excelOperation.getTestData("Service_Desk_User", "ClientPortal_SignIN", "EmailID"));
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
			CPortal.WPSAdmin_LogIN_EnterPWD(excelOperation.getTestData("Service_Desk_User", "ClientPortal_SignIN", "PWD"));
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
			String uuid = Integer.toString(((new Random().nextInt(10000))+1));
			String SSOID = "TestSDUser"+uuid+"@wiley.com";
			CPortal.WPSAdmin_Enter_SSO_ID(SSOID);
			CPortal.SDUser_SelectRole();
			CPortal.WPSAdmin_ClickRegister();
			String DBName=excelOperation.getTestData("TC03", "DB_Query", "DB_Name");
			String query=excelOperation.getTestData("TC03", "DB_Query", "Query");
			String datatype=excelOperation.getTestData("TC03", "DB_Query", "Data_Type");
			String ssoid=dbConnect.DB_Select(DBName,query,datatype);
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
			System.out.println("Register new user with Service Desk User Role Failed" + e.getMessage());
			Reporting.updateTestReport("Register new user with Service Desk User Role Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
    * @Author: Varisa
    * @Description: Validation of footer for WPS Admin
    */
	

}
