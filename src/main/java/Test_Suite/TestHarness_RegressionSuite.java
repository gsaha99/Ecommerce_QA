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

import PageObjectRepo.app_TestHarness_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class TestHarness_RegressionSuite extends DriverModule {

	app_TestHarness_Repo THarness;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
		
	@BeforeTest
	public void initializeRepo() {
		THarness = PageFactory.initElements(driver, app_TestHarness_Repo.class);
		
	}
    /*
     * @Author: Jayanta
     * @Description: Validation of HTTP Probe operation
     */
	@Test
	public void TC01_HTTP_Probe() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC01_HTTP Client: "
					+ "Validate that Probe option is available and on clicking perform,"
					+ " proper response is displayed then click on back button in response page,"
					+ " check whether user is redirected to the probe page of that interface.");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_Probe();
			THarness.Http_Probe_ClickPerform();
			String status = driver.findElement(By.xpath("//p[contains(text(),'ONLINE')]")).getText();
			if (status.equalsIgnoreCase("ONLINE")) {
				Reporting.updateTestReport("Status is: " + status,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}

			else
				Reporting.updateTestReport("Status is: " + status, CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			
			THarness.Http_Probe_ClickBack();
			String status1 = driver.findElement(By.xpath("//p[contains(text(),'Check server available.')]")).getText();
			if (status1.equalsIgnoreCase("Check server available.")) {
				Reporting.updateTestReport("Back button is checked",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}

			else
				Reporting.updateTestReport("Back button is checked", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
		}
		catch (Exception e) 
		{
			System.out.println("Probe Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Probe Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of HTTP Tokenise operation request and response page
     */
	@Test
	public void TC02_HTTP_Tokenise_Request_Response() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC02_HTTP Client: "
					+ "Validate that for Tokenise operation,"
					+ " Request and Result page is displayed successfully with all the required details");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_Tokenise();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC02", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC02", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC02", "TestHarness_Test_Data", "Description"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.Http_Tokenise_EnterAddress(excelOperation.getTestData("TC02", "TestHarness_Test_Data", "Address"));
			THarness.Http_Tokenise_EnterPostalCode(excelOperation.getTestData("TC02", "TestHarness_Test_Data", "Zip_Code"));
			THarness.Http_Tokenise_EnterCountryCode(excelOperation.getTestData("TC02", "TestHarness_Test_Data", "Country_Code"));
			THarness.Http_Tokenise_SelectAVSCode();
			THarness.Http_Tokenise_ClickContinue();
			
			driver.switchTo().frame("tokenFrame");
            driver.switchTo().frame(0);
	
			THarness.Http_EnterCardNumber(excelOperation.getTestData("TC02", "TestHarness_Test_Data", "Card_Number"));
			THarness.Http_EnterCardExpiry(excelOperation.getTestData("TC02", "TestHarness_Test_Data", "Expiry_Date"));
			THarness.Http_EnterCardCVC(excelOperation.getTestData("TC02", "TestHarness_Test_Data", "CVV"));
			
			driver.switchTo().parentFrame();
			
			THarness.paymentDetailsSubmit();
			
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Tokenise')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Tokenise')]")).click();
					
			if(THarness.Http_Tokenise_FetchReturnMessage().compareTo("SUCCESS")==0) {
				
				Reporting.updateTestReport("Return message is: " + THarness.Http_Tokenise_FetchReturnMessage(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + THarness.Http_Tokenise_FetchReturnMessage(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC02", "TestHarness_Test_Data", "Return_Message", THarness.Http_Tokenise_FetchReturnMessage());
	
			if(THarness.Http_Tokenise_FetchReturnCode().compareTo("0")==0) {
				
				Reporting.updateTestReport("Return code is: " + THarness.Http_Tokenise_FetchReturnCode(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + THarness.Http_Tokenise_FetchReturnCode(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC02", "TestHarness_Test_Data", "Return_Code", THarness.Http_Tokenise_FetchReturnCode());
			excelOperation.updateTestData("TC02", "TestHarness_Test_Data", "Operation", THarness.Http_Tokenise_FetchOperation());
			excelOperation.updateTestData("TC02", "TestHarness_Test_Data", "transID", THarness.Http_Tokenise_FetchTransID());
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			excelOperation.updateTestData("TC02", "TestHarness_Test_Data", "merchantResponse", THarness.Http_Tokenise_FetchMerchantResponse());
			excelOperation.updateTestData("TC02", "TestHarness_Test_Data", "merchantReference", THarness.Http_Tokenise_FetchMerchantReference());
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			excelOperation.updateTestData("TC02", "TestHarness_Test_Data", "Token", THarness.Http_Tokenise_FetchToken());
			excelOperation.updateTestData("TC02", "TestHarness_Test_Data", "acquirerID", THarness.Http_Tokenise_FetchAcquirerID());
			excelOperation.updateTestData("TC02", "TestHarness_Test_Data", "acquirerName", THarness.Http_Tokenise_FetchAcquirerName());
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			excelOperation.updateTestData("TC02", "TestHarness_Test_Data", "maskedCardNumber", THarness.Http_Tokenise_FetchMaskedCardNumber());
			excelOperation.updateTestData("TC02", "TestHarness_Test_Data", "cardExpiry", THarness.Http_Tokenise_FetchCardExpiry());
		} 
		catch (Exception e) 
		{
			System.out.println("Tokenise Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Tokenise Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of loading payment portal with proper IFrame for HTTP Tokenise operation
     */
	@Test
	public void TC03_HTTP_Tokenise_IFrame() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC03_HTTP Client: "
					+ "Validate that for Tokenise,"
					+ " on clicking on Continue, payment portal is displayed with proper Iframe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_Tokenise();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC03", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC03", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC03", "TestHarness_Test_Data", "Description"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.Http_Tokenise_EnterAddress(excelOperation.getTestData("TC03", "TestHarness_Test_Data", "Address"));
			THarness.Http_Tokenise_EnterPostalCode(excelOperation.getTestData("TC03", "TestHarness_Test_Data", "Zip_Code"));
			THarness.Http_Tokenise_EnterCountryCode(excelOperation.getTestData("TC03", "TestHarness_Test_Data", "Country_Code"));
			THarness.Http_Tokenise_SelectAVSCode();
			THarness.Http_Tokenise_ClickContinue();
			
            String message = driver.findElement(By.xpath("//p[contains(text(),'Please enter your credit card details.')]")).getText();
			if (message.equalsIgnoreCase("Please enter your credit card details.")) {
				Reporting.updateTestReport("IFrame is loaded properly and Message is showing: " + message,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}

			else
				Reporting.updateTestReport("IFrame is not loaded properly", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL); 
            
		}
		catch (Exception e) 
		{
			System.out.println("Tokenise Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Tokenise Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of HTTP Tokenise operation request and response page data in ES and Stripe
     */
	@Test
	public void TC04_HTTP_Tokenise_ES_Stripe_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC04_HTTP Client: "
					+ "Validate that for Tokenise, proper data is displayed in elastic as well as Stripe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_Tokenise();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC04", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC04", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC04", "TestHarness_Test_Data", "Description"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.Http_Tokenise_EnterAddress(excelOperation.getTestData("TC04", "TestHarness_Test_Data", "Address"));
			THarness.Http_Tokenise_EnterPostalCode(excelOperation.getTestData("TC04", "TestHarness_Test_Data", "Zip_Code"));
			THarness.Http_Tokenise_EnterCountryCode(excelOperation.getTestData("TC04", "TestHarness_Test_Data", "Country_Code"));
			THarness.Http_Tokenise_SelectAVSCode();
			THarness.Http_Tokenise_ClickContinue();
			
			driver.switchTo().frame("tokenFrame");
            driver.switchTo().frame(0);
	
			THarness.Http_EnterCardNumber(excelOperation.getTestData("TC04", "TestHarness_Test_Data", "Card_Number"));
			THarness.Http_EnterCardExpiry(excelOperation.getTestData("TC04", "TestHarness_Test_Data", "Expiry_Date"));
			THarness.Http_EnterCardCVC(excelOperation.getTestData("TC04", "TestHarness_Test_Data", "CVV"));
			
			driver.switchTo().parentFrame();
			
			THarness.paymentDetailsSubmit();
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Tokenise')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Tokenise')]")).click();
			
			if(THarness.Http_Tokenise_FetchReturnMessage().compareTo("SUCCESS")==0) {
				
				Reporting.updateTestReport("Return message is: " + THarness.Http_Tokenise_FetchReturnMessage(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + THarness.Http_Tokenise_FetchReturnMessage(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC04", "TestHarness_Test_Data", "Return_Message", THarness.Http_Tokenise_FetchReturnMessage());
			if(THarness.Http_Tokenise_FetchReturnCode().compareTo("0")==0) {
				
				Reporting.updateTestReport("Return code is: " + THarness.Http_Tokenise_FetchReturnCode(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + THarness.Http_Tokenise_FetchReturnCode(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC04", "TestHarness_Test_Data", "Return_Code", THarness.Http_Tokenise_FetchReturnCode());
			excelOperation.updateTestData("TC04", "TestHarness_Test_Data", "Operation", THarness.Http_Tokenise_FetchOperation());
			excelOperation.updateTestData("TC04", "TestHarness_Test_Data", "transID", THarness.Http_Tokenise_FetchTransID());
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			excelOperation.updateTestData("TC04", "TestHarness_Test_Data", "merchantResponse", THarness.Http_Tokenise_FetchMerchantResponse());
			excelOperation.updateTestData("TC04", "TestHarness_Test_Data", "merchantReference", THarness.Http_Tokenise_FetchMerchantReference());
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			excelOperation.updateTestData("TC04", "TestHarness_Test_Data", "Token", THarness.Http_Tokenise_FetchToken());
			excelOperation.updateTestData("TC04", "TestHarness_Test_Data", "acquirerID", THarness.Http_Tokenise_FetchAcquirerID());
			excelOperation.updateTestData("TC04", "TestHarness_Test_Data", "acquirerName", THarness.Http_Tokenise_FetchAcquirerName());
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			excelOperation.updateTestData("TC04", "TestHarness_Test_Data", "maskedCardNumber", THarness.Http_Tokenise_FetchMaskedCardNumber());
			excelOperation.updateTestData("TC04", "TestHarness_Test_Data", "cardExpiry", THarness.Http_Tokenise_FetchCardExpiry());
		} 
		catch (Exception e) 
		{
			System.out.println("Tokenise Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Tokenise Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of proper error message and error code for HTTP Tokenise operation
     */
	@Test
	public void TC05_HTTP_Tokenise_Error_Message() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC05_HTTP Client: "
					+ "Validate that for Tokenise operation,"
					+ " on giving some incorrect details, proper error message with error code displayed");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_Tokenise();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC05", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC05", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC05", "TestHarness_Test_Data", "Description"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.Http_Tokenise_EnterAddress(excelOperation.getTestData("TC05", "TestHarness_Test_Data", "Address"));
			THarness.Http_Tokenise_EnterPostalCode(excelOperation.getTestData("TC05", "TestHarness_Test_Data", "Zip_Code"));
			THarness.Http_Tokenise_EnterCountryCode(excelOperation.getTestData("TC05", "TestHarness_Test_Data", "Country_Code"));
			THarness.Http_Tokenise_SelectAVSCode();
			THarness.Http_Tokenise_ClickContinue();
			
			driver.switchTo().frame("tokenFrame");
            driver.switchTo().frame(0);
	
			THarness.Http_EnterCardNumber(excelOperation.getTestData("TC05", "TestHarness_Test_Data", "Card_Number"));
			THarness.Http_EnterCardExpiry(excelOperation.getTestData("TC05", "TestHarness_Test_Data", "Expiry_Date"));
			THarness.Http_EnterCardCVC(excelOperation.getTestData("TC05", "TestHarness_Test_Data", "CVV"));
			
			driver.switchTo().parentFrame();
			
			THarness.paymentDetailsSubmit();
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Tokenise')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Tokenise')]")).click();
			
			String actualReturnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			String expectedReturnMessage = excelOperation.getTestData("TC05", "Error_Message", "Return_Message");
			if(actualReturnMessage.compareTo(expectedReturnMessage)==0) {
				
				Reporting.updateTestReport("Return message is: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC05", "TestHarness_Test_Data", "Return_Message", actualReturnMessage);
			String actualReturnCode = THarness.Http_Tokenise_FetchReturnCode();
			String expectedReturnCode = excelOperation.getTestData("TC05", "Error_Message", "Return_Code");
			if(actualReturnCode.compareTo(expectedReturnCode)==0) {
				
				Reporting.updateTestReport("Return code is: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC05", "TestHarness_Test_Data", "Return_Code", actualReturnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC05", "TestHarness_Test_Data", "Operation", operation);
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC05", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String merchantResponse = THarness.Http_Tokenise_FetchMerchantResponse();
			excelOperation.updateTestData("TC05", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			String merchantReference = THarness.Http_Tokenise_FetchMerchantReference();
			excelOperation.updateTestData("TC05", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String token = THarness.Http_Tokenise_FetchToken();
			excelOperation.updateTestData("TC05", "TestHarness_Test_Data", "Token", token);
			String acquirerID = THarness.Http_Tokenise_FetchAcquirerID();
			excelOperation.updateTestData("TC05", "TestHarness_Test_Data", "acquirerID", acquirerID);
			String acquirerName = THarness.Http_Tokenise_FetchAcquirerName();
			excelOperation.updateTestData("TC05", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String maskedCardNumber = THarness.Http_Tokenise_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC05", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			String cardExpiry = THarness.Http_Tokenise_FetchCardExpiry();
			excelOperation.updateTestData("TC05", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		} 
		catch (Exception e) 
		{
			System.out.println("Tokenise Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Tokenise Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of HTTP Validation operation request and response page
     */
	@Test
	public void TC06_HTTP_Validate_Request_Response() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC06_HTTP Client: "
					+ "Validate that for Validate operation,"
					+ " Request and Result page is displayed successfully with all the required details");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_Validate();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC06", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC06", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC06", "TestHarness_Test_Data", "Description"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.Http_Tokenise_EnterAddress(excelOperation.getTestData("TC06", "TestHarness_Test_Data", "Address"));
			THarness.Http_Tokenise_EnterPostalCode(excelOperation.getTestData("TC06", "TestHarness_Test_Data", "Zip_Code"));
			THarness.Http_Tokenise_EnterCountryCode(excelOperation.getTestData("TC06", "TestHarness_Test_Data", "Country_Code"));
			THarness.Http_Tokenise_SelectAVSCode();
			THarness.Http_Tokenise_ClickContinue();
			
			driver.switchTo().frame("tokenFrame");
            driver.switchTo().frame(0);
	
			THarness.Http_EnterCardNumber(excelOperation.getTestData("TC06", "TestHarness_Test_Data", "Card_Number"));
			THarness.Http_EnterCardExpiry(excelOperation.getTestData("TC06", "TestHarness_Test_Data", "Expiry_Date"));
			THarness.Http_EnterCardCVC(excelOperation.getTestData("TC06", "TestHarness_Test_Data", "CVV"));
			
			driver.switchTo().parentFrame();
			
			THarness.paymentDetailsSubmit();
			
			
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Validate Response')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Validate Response')]")).click();
			
			String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			if(returnMessage.compareTo("SUCCESS")==0) {
				
				Reporting.updateTestReport("Return message is: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC06", "TestHarness_Test_Data", "Return_Message", returnMessage);
			String returnCode = THarness.Http_Tokenise_FetchReturnCode();
			if(returnCode.compareTo("0")==0) {
				
				Reporting.updateTestReport("Return code is: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC06", "TestHarness_Test_Data", "Return_Code", returnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC06", "TestHarness_Test_Data", "Operation", operation);
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC06", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String merchantResponse = THarness.Http_Tokenise_FetchMerchantResponse();
			excelOperation.updateTestData("TC06", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			String merchantReference = THarness.Http_Tokenise_FetchMerchantReference();
			excelOperation.updateTestData("TC06", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String token = THarness.Http_Tokenise_FetchToken();
			excelOperation.updateTestData("TC06", "TestHarness_Test_Data", "Token", token);
			String acquirerID = THarness.Http_Tokenise_FetchAcquirerID();
			excelOperation.updateTestData("TC06", "TestHarness_Test_Data", "acquirerID", acquirerID);
			String acquirerName = THarness.Http_Tokenise_FetchAcquirerName();
			excelOperation.updateTestData("TC06", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String maskedCardNumber = THarness.Http_Tokenise_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC06", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			String cardExpiry = THarness.Http_Tokenise_FetchCardExpiry();
			excelOperation.updateTestData("TC06", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		} 
		catch (Exception e) 
		{
			System.out.println("Validate Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Validate Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of loading payment portal with proper IFrame for HTTP Validate operation
     */
	@Test
	public void TC07_HTTP_Validate_IFrame() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC07_HTTP Client: "
					+ "Validate that for Validate,"
					+ " on clicking on Continue, payment portal is displayed with proper Iframe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_Validate();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC07", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC07", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC07", "TestHarness_Test_Data", "Description"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.Http_Tokenise_EnterAddress(excelOperation.getTestData("TC07", "TestHarness_Test_Data", "Address"));
			THarness.Http_Tokenise_EnterPostalCode(excelOperation.getTestData("TC07", "TestHarness_Test_Data", "Zip_Code"));
			THarness.Http_Tokenise_EnterCountryCode(excelOperation.getTestData("TC07", "TestHarness_Test_Data", "Country_Code"));
			THarness.Http_Tokenise_SelectAVSCode();
			THarness.Http_Tokenise_ClickContinue();
			
			/*driver.switchTo().frame("tokenFrame");
            driver.switchTo().frame(0);*/
            String message = driver.findElement(By.xpath("//p[contains(text(),'Please enter your credit card details.')]")).getText();
			if (message.equalsIgnoreCase("Please enter your credit card details.")) {
				Reporting.updateTestReport("IFrame is loaded properly and Message is showing: " + message,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}

			else
				Reporting.updateTestReport("IFrame is not loaded properly", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL); 
            
		}
		catch (Exception e) 
		{
			System.out.println("Validate Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Validate Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of HTTP Validate operation request and response page data in ES and Stripe
     */
	@Test
	public void TC08_HTTP_Validate_ES_Stripe_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC08_HTTP Client: "
					+ "Validate that for Validate Operation, proper data is displayed in elastic as well as Stripe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_Validate();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC08", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC08", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC08", "TestHarness_Test_Data", "Description"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.Http_Tokenise_EnterAddress(excelOperation.getTestData("TC08", "TestHarness_Test_Data", "Address"));
			THarness.Http_Tokenise_EnterPostalCode(excelOperation.getTestData("TC08", "TestHarness_Test_Data", "Zip_Code"));
			THarness.Http_Tokenise_EnterCountryCode(excelOperation.getTestData("TC08", "TestHarness_Test_Data", "Country_Code"));
			THarness.Http_Tokenise_SelectAVSCode();
			THarness.Http_Tokenise_ClickContinue();
			
			driver.switchTo().frame("tokenFrame");
            driver.switchTo().frame(0);
	
			THarness.Http_EnterCardNumber(excelOperation.getTestData("TC08", "TestHarness_Test_Data", "Card_Number"));
			THarness.Http_EnterCardExpiry(excelOperation.getTestData("TC08", "TestHarness_Test_Data", "Expiry_Date"));
			THarness.Http_EnterCardCVC(excelOperation.getTestData("TC08", "TestHarness_Test_Data", "CVV"));
			
			driver.switchTo().parentFrame();
			
			THarness.paymentDetailsSubmit();
			
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Validate Response')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Validate Response')]")).click();
			
			String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			if(returnMessage.compareTo("SUCCESS")==0) {
				
				Reporting.updateTestReport("Return message is: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC08", "TestHarness_Test_Data", "Return_Message", returnMessage);
			String returnCode = THarness.Http_Tokenise_FetchReturnCode();
			if(returnCode.compareTo("0")==0) {
				
				Reporting.updateTestReport("Return code is: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC08", "TestHarness_Test_Data", "Return_Code", returnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC08", "TestHarness_Test_Data", "Operation", operation);
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC08", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String merchantResponse = THarness.Http_Tokenise_FetchMerchantResponse();
			excelOperation.updateTestData("TC08", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			String merchantReference = THarness.Http_Tokenise_FetchMerchantReference();
			excelOperation.updateTestData("TC08", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String token = THarness.Http_Tokenise_FetchToken();
			excelOperation.updateTestData("TC08", "TestHarness_Test_Data", "Token", token);
			String acquirerID = THarness.Http_Tokenise_FetchAcquirerID();
			excelOperation.updateTestData("TC08", "TestHarness_Test_Data", "acquirerID", acquirerID);
			String acquirerName = THarness.Http_Tokenise_FetchAcquirerName();
			excelOperation.updateTestData("TC08", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String maskedCardNumber = THarness.Http_Tokenise_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC08", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			String cardExpiry = THarness.Http_Tokenise_FetchCardExpiry();
			excelOperation.updateTestData("TC08", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		} 
		catch (Exception e) 
		{
			System.out.println("Validate Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Validate Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of proper error message and error code for HTTP Validate operation
     */
	@Test
	public void TC09_HTTP_Validate_Error_Message() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC09_HTTP Client: "
					+ "Validate that for Validate operation,"
					+ " on giving some incorrect details, proper error message with error code displayed");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_Validate();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC09", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC09", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC09", "TestHarness_Test_Data", "Description"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.Http_Tokenise_EnterAddress(excelOperation.getTestData("TC09", "TestHarness_Test_Data", "Address"));
			THarness.Http_Tokenise_EnterPostalCode(excelOperation.getTestData("TC09", "TestHarness_Test_Data", "Zip_Code"));
			THarness.Http_Tokenise_EnterCountryCode(excelOperation.getTestData("TC09", "TestHarness_Test_Data", "Country_Code"));
			THarness.Http_Tokenise_SelectAVSCode();
			THarness.Http_Tokenise_ClickContinue();
			
			driver.switchTo().frame("tokenFrame");
            driver.switchTo().frame(0);
	
			THarness.Http_EnterCardNumber(excelOperation.getTestData("TC09", "TestHarness_Test_Data", "Card_Number"));
			THarness.Http_EnterCardExpiry(excelOperation.getTestData("TC09", "TestHarness_Test_Data", "Expiry_Date"));
			THarness.Http_EnterCardCVC(excelOperation.getTestData("TC09", "TestHarness_Test_Data", "CVV"));
			
			driver.switchTo().parentFrame();
			
			THarness.paymentDetailsSubmit();
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Validate Response')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Validate Response')]")).click();
			
			String actualReturnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			String expectedReturnMessage = excelOperation.getTestData("TC09", "Error_Message", "Return_Message");
			if(actualReturnMessage.compareTo(expectedReturnMessage)==0) {
				
				Reporting.updateTestReport("Return message is: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC09", "TestHarness_Test_Data", "Return_Message", actualReturnMessage);
			String actualReturnCode = THarness.Http_Tokenise_FetchReturnCode();
			String expectedReturnCode = excelOperation.getTestData("TC09", "Error_Message", "Return_Code");
			if(actualReturnCode.compareTo(expectedReturnCode)==0) {
				
				Reporting.updateTestReport("Return code is: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC09", "TestHarness_Test_Data", "Return_Code", actualReturnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC09", "TestHarness_Test_Data", "Operation", operation);
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC09", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String merchantResponse = THarness.Http_Tokenise_FetchMerchantResponse();
			excelOperation.updateTestData("TC09", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			String merchantReference = THarness.Http_Tokenise_FetchMerchantReference();
			excelOperation.updateTestData("TC09", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String token = THarness.Http_Tokenise_FetchToken();
			excelOperation.updateTestData("TC09", "TestHarness_Test_Data", "Token", token);
			String acquirerID = THarness.Http_Tokenise_FetchAcquirerID();
			excelOperation.updateTestData("TC09", "TestHarness_Test_Data", "acquirerID", acquirerID);
			String acquirerName = THarness.Http_Tokenise_FetchAcquirerName();
			excelOperation.updateTestData("TC09", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String maskedCardNumber = THarness.Http_Tokenise_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC09", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			String cardExpiry = THarness.Http_Tokenise_FetchCardExpiry();
			excelOperation.updateTestData("TC09", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		} 
		catch (Exception e) 
		{
			System.out.println("Validate Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Validate Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of HTTP Authorise operation request and response page data in ES and Stripe
     */
	@Test
	public void TC10_HTTP_Authorise_ES_Stripe_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC10_HTTP Client: "
					+ "Validate that for Authorise Operation,"
					+ "Request page, payment portal is displayed with proper Iframe, Result page is displayed successfully with all the required details"
					+ "and proper data is displayed in elastic as well as Stripe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_Authorise();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC10", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC10", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC10", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterValue(excelOperation.getTestData("TC10", "TestHarness_Test_Data", "Value"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC10", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.Http_Tokenise_EnterAddress(excelOperation.getTestData("TC10", "TestHarness_Test_Data", "Address"));
			THarness.Http_Tokenise_EnterPostalCode(excelOperation.getTestData("TC10", "TestHarness_Test_Data", "Zip_Code"));
			THarness.Http_Tokenise_EnterCountryCode(excelOperation.getTestData("TC10", "TestHarness_Test_Data", "Country_Code"));
			THarness.Http_Tokenise_SelectAVSCode();
			THarness.Http_Tokenise_ClickContinue();
			
			driver.switchTo().frame("tokenFrame");
            driver.switchTo().frame(0);
	
			THarness.Http_EnterCardNumber(excelOperation.getTestData("TC10", "TestHarness_Test_Data", "Card_Number"));
			THarness.Http_EnterCardExpiry(excelOperation.getTestData("TC10", "TestHarness_Test_Data", "Expiry_Date"));
			THarness.Http_EnterCardCVC(excelOperation.getTestData("TC10", "TestHarness_Test_Data", "CVV"));
			
			driver.switchTo().parentFrame();
			
			THarness.paymentDetailsSubmit();
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Authorise Response')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Authorise Response')]")).click();
			
			String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			if(returnMessage.compareTo("SUCCESS")==0) {
				
				Reporting.updateTestReport("Return message is: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC10", "TestHarness_Test_Data", "Return_Message", returnMessage);
			String returnCode = THarness.Http_Tokenise_FetchReturnCode();
			if(returnCode.compareTo("0")==0) {
				
				Reporting.updateTestReport("Return code is: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC10", "TestHarness_Test_Data", "Return_Code", returnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC10", "TestHarness_Test_Data", "Operation", operation);
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC10", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC10", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC10", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String token = THarness.Http_Auth_FetchToken();
			excelOperation.updateTestData("TC10", "TestHarness_Test_Data", "Token", token);
			String authCode = THarness.Http_Auth_FetchAuthCode();
			excelOperation.updateTestData("TC10", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			String acquirerID = THarness.Http_Auth_FetchAcquirerID();
			excelOperation.updateTestData("TC10", "TestHarness_Test_Data", "acquirerID", acquirerID);
			String acquirerName = THarness.Http_Auth_FetchAcquirerName();
			excelOperation.updateTestData("TC10", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String maskedCardNumber = THarness.Http_Auth_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC10", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			String cardExpiry = THarness.Http_Auth_FetchCardExpiry();
			excelOperation.updateTestData("TC10", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		} 
		catch (Exception e) 
		{
			System.out.println("Authorise Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Authorise Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of proper error message and error code for HTTP Authorise operation
     */
	@Test
	public void TC11_HTTP_Authorise_Error_Message() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC11_HTTP Client: "
					+ "Validate that for Authorise operation,"
					+ " on giving some incorrect details, proper error message with error code displayed");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_Authorise();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC11", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC11", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC11", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterValue(excelOperation.getTestData("TC11", "TestHarness_Test_Data", "Value"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC11", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Authorise_SelectRegion();
			THarness.Http_Tokenise_EnterAddress(excelOperation.getTestData("TC11", "TestHarness_Test_Data", "Address"));
			THarness.Http_Tokenise_EnterPostalCode(excelOperation.getTestData("TC11", "TestHarness_Test_Data", "Zip_Code"));
			THarness.Http_Tokenise_EnterCountryCode(excelOperation.getTestData("TC11", "TestHarness_Test_Data", "Country_Code"));
			THarness.Http_Tokenise_SelectAVSCode();
			THarness.Http_Tokenise_ClickContinue();
			
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Authorise Response')]")));
			
			String actualReturnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			String expectedReturnMessage = excelOperation.getTestData("TC11", "Error_Message", "Return_Message");
			if(actualReturnMessage.compareTo(expectedReturnMessage)==0) {
				
				Reporting.updateTestReport("Return message is: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC11", "TestHarness_Test_Data", "Return_Message", actualReturnMessage);
			String actualReturnCode = THarness.Http_Tokenise_FetchReturnCode();
			String expectedReturnCode = excelOperation.getTestData("TC11", "Error_Message", "Return_Code");
			if(actualReturnCode.compareTo(expectedReturnCode)==0) {
				
				Reporting.updateTestReport("Return code is: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC11", "TestHarness_Test_Data", "Return_Code", actualReturnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC11", "TestHarness_Test_Data", "Operation", operation);
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC11", "TestHarness_Test_Data", "transID", transID);
			
		} 
		catch (Exception e) 
		{
			System.out.println("Authorise Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Authorise Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of HTTP Auth & Settle operation request and response page data in ES and Stripe
     */
	@Test
	public void TC12_HTTP_AuthSettle_ES_Stripe_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC12_HTTP Client: "
					+ "Validate that for Auth & Settle Operation,"
					+ "Request page, payment portal is displayed with proper Iframe, Result page is displayed successfully with all the required details"
					+ "and proper data is displayed in elastic as well as Stripe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_AuthSettle();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC12", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC12", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC12", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterValue(excelOperation.getTestData("TC12", "TestHarness_Test_Data", "Value"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC12", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.Http_Tokenise_EnterAddress(excelOperation.getTestData("TC12", "TestHarness_Test_Data", "Address"));
			THarness.Http_Tokenise_EnterPostalCode(excelOperation.getTestData("TC12", "TestHarness_Test_Data", "Zip_Code"));
			THarness.Http_Tokenise_EnterCountryCode(excelOperation.getTestData("TC12", "TestHarness_Test_Data", "Country_Code"));
			THarness.Http_Tokenise_SelectAVSCode();
			THarness.Http_Tokenise_ClickContinue();
			
			driver.switchTo().frame("tokenFrame");
            driver.switchTo().frame(0);
	
			THarness.Http_EnterCardNumber(excelOperation.getTestData("TC12", "TestHarness_Test_Data", "Card_Number"));
			THarness.Http_EnterCardExpiry(excelOperation.getTestData("TC12", "TestHarness_Test_Data", "Expiry_Date"));
			THarness.Http_EnterCardCVC(excelOperation.getTestData("TC12", "TestHarness_Test_Data", "CVV"));
			
			driver.switchTo().parentFrame();
			
			THarness.paymentDetailsSubmit();
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Authorise & Settle Response')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Authorise & Settle Response')]")).click();
			
			String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			if(returnMessage.compareTo("SUCCESS")==0) {
				
				Reporting.updateTestReport("Return message is: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC12", "TestHarness_Test_Data", "Return_Message", returnMessage);
			String returnCode = THarness.Http_Tokenise_FetchReturnCode();
			if(returnCode.compareTo("0")==0) {
				
				Reporting.updateTestReport("Return code is: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC12", "TestHarness_Test_Data", "Return_Code", returnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC12", "TestHarness_Test_Data", "Operation", operation);
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC12", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String merchantResponse = THarness.Http_AuthSettle_FetchMerchantResponse();
			excelOperation.updateTestData("TC12", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			String merchantReference = THarness.Http_Auth_FetSettlechMerchantReference();
			excelOperation.updateTestData("TC12", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String token = THarness.Http_Auth_FetchToken();
			excelOperation.updateTestData("TC12", "TestHarness_Test_Data", "Token", token);
			String authCode = THarness.Http_Auth_FetchAuthCode();
			excelOperation.updateTestData("TC12", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			String acquirerID = THarness.Http_Auth_FetchAcquirerID();
			excelOperation.updateTestData("TC12", "TestHarness_Test_Data", "acquirerID", acquirerID);
			String acquirerName = THarness.Http_Auth_FetchAcquirerName();
			excelOperation.updateTestData("TC12", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String maskedCardNumber = THarness.Http_Auth_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC12", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			String cardExpiry = THarness.Http_Auth_FetchCardExpiry();
			excelOperation.updateTestData("TC12", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		} 
		catch (Exception e) 
		{
			System.out.println("Auth & Settle Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Auth & Settle Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of proper error message and error code for HTTP Auth & Settle operation
     */
	@Test
	public void TC13_HTTP_AuthSettle_Error_Message() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC13_HTTP Client: "
					+ "Validate that for Auth & Settle operation,"
					+ " on giving some incorrect details, proper error message with error code displayed");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_AuthSettle();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC13", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC13", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC13", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterValue(excelOperation.getTestData("TC13", "TestHarness_Test_Data", "Value"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC13", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.Http_Tokenise_EnterAddress(excelOperation.getTestData("TC13", "TestHarness_Test_Data", "Address"));
			THarness.Http_Tokenise_EnterPostalCode(excelOperation.getTestData("TC13", "TestHarness_Test_Data", "Zip_Code"));
			THarness.Http_Tokenise_EnterCountryCode(excelOperation.getTestData("TC13", "TestHarness_Test_Data", "Country_Code"));
			THarness.Http_Tokenise_SelectAVSCode();
			THarness.Http_Tokenise_ClickContinue();
			
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Authorise & Settle Response')]")));
			
			String actualReturnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			String expectedReturnMessage = excelOperation.getTestData("TC13", "Error_Message", "Return_Message");
			if(actualReturnMessage.compareTo(expectedReturnMessage)==0) {
				
				Reporting.updateTestReport("Return message is: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC13", "TestHarness_Test_Data", "Return_Message", actualReturnMessage);
			String actualReturnCode = THarness.Http_Tokenise_FetchReturnCode();
			String expectedReturnCode = excelOperation.getTestData("TC13", "Error_Message", "Return_Code");
			if(actualReturnCode.compareTo(expectedReturnCode)==0) {
				
				Reporting.updateTestReport("Return code is: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC13", "TestHarness_Test_Data", "Return_Code", actualReturnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC13", "TestHarness_Test_Data", "Operation", operation);
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC13", "TestHarness_Test_Data", "transID", transID);
			
		} 
		catch (Exception e) 
		{
			System.out.println("Auth & Settle Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Auth & Settle Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of HTTP TokenAuth operation request and response page data in ES and Stripe
     */
	@Test
	
        public void TC14_HTTP_TokenAuth_ES_Stripe_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC14_HTTP Client: "
					+ "Validate that for TokenAuth Operation,"
					+ "Request page, Response page is displayed successfully with all the required details"
					+ "and proper data is displayed in elastic as well as Stripe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_TokenAuth();;
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC14", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC14", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC14", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterValue(excelOperation.getTestData("TC14", "TestHarness_Test_Data", "Value"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC14", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC14", "TestHarness_Test_Data", "Token_number"));
			THarness.Http_Tokenise_EnterAddress(excelOperation.getTestData("TC14", "TestHarness_Test_Data", "Address"));
			THarness.Http_Tokenise_EnterPostalCode(excelOperation.getTestData("TC14", "TestHarness_Test_Data", "Zip_Code"));
			THarness.Http_Tokenise_EnterCountryCode(excelOperation.getTestData("TC14", "TestHarness_Test_Data", "Country_Code"));
		    THarness.Http_Tokenise_ClickContinue();
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Token Authorise Response')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Token Authorise Response')]")).click();
			String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			if(returnMessage.compareTo("SUCCESS")==0) {
				
				Reporting.updateTestReport("Return message is: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC14", "TestHarness_Test_Data", "Return_Message", returnMessage);
			String returnCode = THarness.Http_Tokenise_FetchReturnCode();
			if(returnCode.compareTo("0")==0) {
				
				Reporting.updateTestReport("Return code is: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC14", "TestHarness_Test_Data", "Return_Code", returnCode);
			
            String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC14", "TestHarness_Test_Data", "Operation", operation);
			
            String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC14", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC14", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			
            String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC14", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC14", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			
            String acquirerID = THarness.SOAP_TokenAuthSettle_FetchAcquirerID();
			excelOperation.updateTestData("TC14", "TestHarness_Test_Data", "acquirerID", acquirerID);
			
            String acquirerName = THarness.SOAP_TokenAuthSettle_FetchAcquirerName();
			excelOperation.updateTestData("TC14", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String maskedCardNumber = THarness.SOAP_TokenAuthSettle_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC14", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			
            String cardExpiry = THarness.SOAP_TokenAuthSettle_FetchCardExpiry();
			excelOperation.updateTestData("TC14", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		}
		
		catch (Exception e) 
		{
			System.out.println("TokenAuth Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Tokenise Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of proper error message and error code for HTTP Token Auth operation
     */
	@Test
	
        public void TC15_HTTP_Validate_for_Token_Auth_Error_Message() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC15_HTTP Client: "
					+ "Validate that for Token Auth operation,"
					+ " on giving some incorrect details, proper error message with error code displayed");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_TokenAuth();;
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC15", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC15", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC15", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterValue(excelOperation.getTestData("TC15", "TestHarness_Test_Data", "Value"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC15", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC15", "TestHarness_Test_Data", "Token_number"));
			Thread.sleep(5000);
			
		    THarness.Http_Tokenise_ClickContinue();
			
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Token Authorise Response')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Token Authorise Response')]")).click();
			
			
			String actualReturnMessage = THarness.Http_Tokenise_FetchReturnMessage();
	        String expectedReturnMessage = excelOperation.getTestData("TC15", "Error_Message", "Return_Message");
	            
            if(actualReturnMessage.compareTo(expectedReturnMessage)==0) 
            {
				
			Reporting.updateTestReport("Return message is: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
			
			Reporting.updateTestReport("Return message is not: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC15", "TestHarness_Test_Data", "Return_Message", actualReturnMessage);
			String actualReturnCode = THarness.Http_Tokenise_FetchReturnCode();
			String expectedReturnCode = excelOperation.getTestData("TC15", "Error_Message", "Return_Code");
			if(actualReturnCode.compareTo(expectedReturnCode)==0) {
				
				Reporting.updateTestReport("Return code is: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC15", "TestHarness_Test_Data", "Return_Code", actualReturnCode);
			
            String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC15", "TestHarness_Test_Data", "Operation", operation);
			
            String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC15", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC15", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			
            String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC15", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC15", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			
            String acquirerID = THarness.SOAP_TokenAuthSettle_FetchAcquirerID();
			excelOperation.updateTestData("TC15", "TestHarness_Test_Data", "acquirerID", acquirerID);
			
            String acquirerName = THarness.SOAP_TokenAuthSettle_FetchAcquirerName();
			excelOperation.updateTestData("TC15", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String maskedCardNumber = THarness.SOAP_TokenAuthSettle_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC15", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			
            String cardExpiry = THarness.SOAP_TokenAuthSettle_FetchCardExpiry();
			excelOperation.updateTestData("TC15", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		} 
		catch (Exception e) 
		{
			System.out.println("TokenAuth Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Tokenise Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of HTTP TokenSettle operation request and response page data in ES and Stripe
     */
	@Test
	
        public void TC16_HTTP_TokenSettle_ES_Stripe_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC16_HTTP Client: "
					+ "Validate that for Token Settle Operation,"
					+ "Request page, Response page is displayed successfully with all the required details"
					+ "and proper data is displayed in elastic as well as Stripe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_TokenSettle();;
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC16", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC16", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC16", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterValue(excelOperation.getTestData("TC16", "TestHarness_Test_Data", "Value"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC16", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC16", "TestHarness_Test_Data", "Token_number"));
			THarness.SOAP_VoidAuth_EnterAuthCode(excelOperation.getTestData("TC16", "TestHarness_Test_Data", "Auth_Code"));
			THarness.Http_Tokenise_ClickContinue();
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Token Settle Response')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Token Settle Response')]")).click();
			
			String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			if(returnMessage.compareTo("SUCCESS")==0) {
				
				Reporting.updateTestReport("Return message is: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC16", "TestHarness_Test_Data", "Return_Message", returnMessage);
			String returnCode = THarness.Http_Tokenise_FetchReturnCode();
			if(returnCode.compareTo("0")==0) {
				
				Reporting.updateTestReport("Return code is: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC16", "TestHarness_Test_Data", "Return_Code", returnCode);
			
            String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC16", "TestHarness_Test_Data", "Operation", operation);
			
            String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC16", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC16", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			
            String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC16", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC16", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			
            String acquirerID = THarness.SOAP_TokenAuthSettle_FetchAcquirerID();
			excelOperation.updateTestData("TC16", "TestHarness_Test_Data", "acquirerID", acquirerID);
			
            String acquirerName = THarness.SOAP_TokenAuthSettle_FetchAcquirerName();
			excelOperation.updateTestData("TC16", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String maskedCardNumber = THarness.SOAP_TokenAuthSettle_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC16", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			
            String cardExpiry = THarness.SOAP_TokenAuthSettle_FetchCardExpiry();
			excelOperation.updateTestData("TC16", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		}
		
		catch (Exception e) 
		{
			System.out.println("TokenSettle Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Tokenise Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	
	/*
     * @Author: Varisa
     * @Description: Validation of proper error message and error code for HTTP Token Settle operation
     */
	@Test
	
	public void TC17_HTTP_Validate_for_Token_Settle_Error_Message() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC17_HTTP Client: "
					+ "Validate that for Token Settle operation,"
					+ " on giving some incorrect details, proper error message with error code displayed");
	
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_TokenSettle();;
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC17", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC17", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC17", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterValue(excelOperation.getTestData("TC17", "TestHarness_Test_Data", "Value"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC17", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC17", "TestHarness_Test_Data", "Token_number"));
			THarness.SOAP_VoidAuth_EnterAuthCode(excelOperation.getTestData("TC17", "TestHarness_Test_Data", "Auth_Code"));
			THarness.Http_Tokenise_ClickContinue();
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Token Settle Response')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Token Settle Response')]")).click();
			
			String actualReturnMessage = THarness.Http_Tokenise_FetchReturnMessage();
	        String expectedReturnMessage = excelOperation.getTestData("TC17", "Error_Message", "Return_Message");
	            
	        if(actualReturnMessage.compareTo(expectedReturnMessage)==0) 
	        {
				Reporting.updateTestReport("Return message is: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC17", "TestHarness_Test_Data", "Return_Message", actualReturnMessage);
			String actualReturnCode = THarness.Http_Tokenise_FetchReturnCode();
			String expectedReturnCode = excelOperation.getTestData("TC17", "Error_Message", "Return_Code");
			if(actualReturnCode.compareTo(expectedReturnCode)==0) {
				
				Reporting.updateTestReport("Return code is: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC17", "TestHarness_Test_Data", "Return_Code", actualReturnCode);
			
            String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC17", "TestHarness_Test_Data", "Operation", operation);
			
            String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC17", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC17", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			
            String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC17", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC17", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			
            String acquirerID = THarness.SOAP_TokenAuthSettle_FetchAcquirerID();
			excelOperation.updateTestData("TC17", "TestHarness_Test_Data", "acquirerID", acquirerID);
			
            String acquirerName = THarness.SOAP_TokenAuthSettle_FetchAcquirerName();
			excelOperation.updateTestData("TC17", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String maskedCardNumber = THarness.SOAP_TokenAuthSettle_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC17", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			
            String cardExpiry = THarness.SOAP_TokenAuthSettle_FetchCardExpiry();
			excelOperation.updateTestData("TC17", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		} 
		catch (Exception e) 
		{
			System.out.println("TokenSettle Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Tokenise Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of HTTP TokenAuthSettle operation request and response page data in ES and Stripe
     */
	@Test
	
        public void TC18_HTTP_TokenAuthSettle_ES_Stripe_Validation() throws IOException
	
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC18_HTTP Client: "
					+ "Validate that for TokenAuthSettle Operation,"
					+ "Request page, Response page is displayed successfully with all the required details"
					+ "and proper data is displayed in elastic as well as Stripe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_TokenAuthSettle();;
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC18", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC18", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC16", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterValue(excelOperation.getTestData("TC18", "TestHarness_Test_Data", "Value"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC18", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC18", "TestHarness_Test_Data", "Token_number"));
			THarness.Http_Tokenise_ClickContinue();
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Token Authorise & Settle Response')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Token Authorise & Settle Response')]")).click();
			
			String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			if(returnMessage.compareTo("SUCCESS")==0) 
			{
				
				Reporting.updateTestReport("Return message is: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC18", "TestHarness_Test_Data", "Return_Message", returnMessage);
			String returnCode = THarness.Http_Tokenise_FetchReturnCode();
			if(returnCode.compareTo("0")==0) 
			{
				
				Reporting.updateTestReport("Return code is: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC18", "TestHarness_Test_Data", "Return_Code", returnCode);
			
            String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC18", "TestHarness_Test_Data", "Operation", operation);
			
            String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC18", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC18", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			
            String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC18", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC18", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			
            String acquirerID = THarness.SOAP_TokenAuthSettle_FetchAcquirerID();
			excelOperation.updateTestData("TC18", "TestHarness_Test_Data", "acquirerID", acquirerID);
			
            String acquirerName = THarness.SOAP_TokenAuthSettle_FetchAcquirerName();
			excelOperation.updateTestData("TC18", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String maskedCardNumber = THarness.SOAP_TokenAuthSettle_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC18", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			
            String cardExpiry = THarness.SOAP_TokenAuthSettle_FetchCardExpiry();
			excelOperation.updateTestData("TC18", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		} 
		catch (Exception e) 
		{
			System.out.println("TokenAuthSettle Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Tokenise Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of proper error message and error code for HTTP Token AuthSettle operation
     */
	@Test
	
	public void TC19_HTTP_Validate_for_Token_Auth_Settle_Error_Message() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC19_HTTP Client: "
					+ "Validate that for Token Auth&Settle operation,"
					+ " on giving some incorrect details, proper error message with error code displayed");
	
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_TokenAuthSettle();;
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC19", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC19", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC19", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterValue(excelOperation.getTestData("TC19", "TestHarness_Test_Data", "Value"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC19", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_TokenAuthSettle_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC19", "TestHarness_Test_Data", "Token_number"));
		    THarness.Http_Tokenise_ClickContinue();
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Token Authorise & Settle Response')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Token Authorise & Settle Response')]")).click();
			
			String actualReturnMessage = THarness.Http_Tokenise_FetchReturnMessage();
	        String expectedReturnMessage = excelOperation.getTestData("TC19", "Error_Message", "Return_Message");
	            
		    if(actualReturnMessage.compareTo(expectedReturnMessage)==0) 
		    {
				
				Reporting.updateTestReport("Return message is: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC19", "TestHarness_Test_Data", "Return_Message", actualReturnMessage);
			String actualReturnCode = THarness.Http_Tokenise_FetchReturnCode();
			String expectedReturnCode = excelOperation.getTestData("TC19", "Error_Message", "Return_Code");
			if(actualReturnCode.compareTo(expectedReturnCode)==0) {
				
				Reporting.updateTestReport("Return code is: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC19", "TestHarness_Test_Data", "Return_Code", actualReturnCode);
			
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC19", "TestHarness_Test_Data", "Operation", operation);
			
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC19", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC19", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			
			String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC19", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC19", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			
			String acquirerID = THarness.SOAP_TokenAuthSettle_FetchAcquirerID();
			excelOperation.updateTestData("TC19", "TestHarness_Test_Data", "acquirerID", acquirerID);
			
			String acquirerName = THarness.SOAP_TokenAuthSettle_FetchAcquirerName();
			excelOperation.updateTestData("TC19", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String maskedCardNumber = THarness.SOAP_TokenAuthSettle_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC19", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			
			String cardExpiry = THarness.SOAP_TokenAuthSettle_FetchCardExpiry();
			excelOperation.updateTestData("TC19", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		} 
		catch (Exception e) 
		{
			System.out.println("Token Auth&Settle Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Token Auth&Settle Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of HTTP VOID AUTH operation request and response page data in ES and Stripe
     */
	@Test
	
    public void TC20_HTTP_VoidAuth_ES_Stripe_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC20_HTTP Client: "
					+ "Validate that for VoidAuth Operation,"
					+ "Request page, Response page is displayed successfully with all the required details"
					+ "and proper data is displayed in elastic as well as Stripe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_VoidAuth();;
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC20", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC20", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC20", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC20", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC20", "TestHarness_Test_Data", "Token_number"));
			THarness.SOAP_VoidAuth_EnterAuthCode(excelOperation.getTestData("TC20", "TestHarness_Test_Data", "Auth_Code"));
			THarness.Http_Tokenise_ClickContinue();
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Void Authorise Response')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Void Authorise Response')]")).click();
			
			String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			if(returnMessage.compareTo("SUCCESS")==0) 
			{
				
				Reporting.updateTestReport("Return message is: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC20", "TestHarness_Test_Data", "Return_Message", returnMessage);
			String returnCode = THarness.Http_Tokenise_FetchReturnCode();
			if(returnCode.compareTo("0")==0) 
			{
				
				Reporting.updateTestReport("Return code is: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC20", "TestHarness_Test_Data", "Return_Code", returnCode);
			
            String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC20", "TestHarness_Test_Data", "Operation", operation);
			
            String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC20", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC20", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			
            String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC20", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC20", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			
            String acquirerID = THarness.SOAP_TokenAuthSettle_FetchAcquirerID();
			excelOperation.updateTestData("TC20", "TestHarness_Test_Data", "acquirerID", acquirerID);
			
            String acquirerName = THarness.SOAP_TokenAuthSettle_FetchAcquirerName();
			excelOperation.updateTestData("TC20", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String maskedCardNumber = THarness.SOAP_TokenAuthSettle_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC20", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			
            String cardExpiry = THarness.SOAP_TokenAuthSettle_FetchCardExpiry();
			excelOperation.updateTestData("TC20", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		}
		
		catch (Exception e) 
		{
			System.out.println("VoidAuth Operation Failed" + e.getMessage());
			Reporting.updateTestReport("VoidAuth Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of proper error message and error code for HTTP Void Auth operation
     */
	@Test
	
	public void TC21_HTTP_Validate_for_Void_Auth_Error_Message() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC21_HTTP Client: "
					+ "Validate that for Void Auth operation,"
					+ " on giving some incorrect details, proper error message with error code displayed");
	
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickHttp_Interface();
			THarness.ClickHttp_VoidAuth();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC21", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC21", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC21", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC21", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC21", "TestHarness_Test_Data", "Token_number"));
			THarness.SOAP_VoidAuth_EnterAuthCode(excelOperation.getTestData("TC21", "TestHarness_Test_Data", "Auth_Code"));
			THarness.Http_Tokenise_ClickContinue();
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Void Authorise Response')]")));
			
			driver.findElement(By.xpath("//h2[contains(text(),'Void Authorise Response')]")).click();
			
			String actualReturnMessage = THarness.Http_Tokenise_FetchReturnMessage();
	        String expectedReturnMessage = excelOperation.getTestData("TC21", "Error_Message", "Return_Message");
	            
		    if(actualReturnMessage.compareTo(expectedReturnMessage)==0) 
		    {
				
				Reporting.updateTestReport("Return message is: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC21", "TestHarness_Test_Data", "Return_Message", actualReturnMessage);
			String actualReturnCode = THarness.Http_Tokenise_FetchReturnCode();
			String expectedReturnCode = excelOperation.getTestData("TC21", "Error_Message", "Return_Code");
			if(actualReturnCode.compareTo(expectedReturnCode)==0) {
				
				Reporting.updateTestReport("Return code is: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC21", "TestHarness_Test_Data", "Return_Code", actualReturnCode);
			
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC21", "TestHarness_Test_Data", "Operation", operation);
			
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC21", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC21", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			
			String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC21", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC21", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			
			String acquirerID = THarness.SOAP_TokenAuthSettle_FetchAcquirerID();
			excelOperation.updateTestData("TC21", "TestHarness_Test_Data", "acquirerID", acquirerID);
			
			String acquirerName = THarness.SOAP_TokenAuthSettle_FetchAcquirerName();
			excelOperation.updateTestData("TC21", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String maskedCardNumber = THarness.SOAP_TokenAuthSettle_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC21", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			
			String cardExpiry = THarness.SOAP_TokenAuthSettle_FetchCardExpiry();
			excelOperation.updateTestData("TC21", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		} 
		catch (Exception e) 
		{
			System.out.println("Void Auth Operation Failed" + e.getMessage());
			Reporting.updateTestReport("Void Auth Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of SOAP Probe operation request and response page data in ES and Stripe
     */
	@Test
	
    public void TC22_SOAP_Probe_ES_Stripe_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC22_SOAP Client: "
					+ "Validate that for Probe Operation,"
					+ "Request page, Response page is displayed successfully with all the required details"
					+ "and proper data is displayed in elastic as well as Stripe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickSOAP_Interface();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC22", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterMethod(excelOperation.getTestData("TC22", "TestHarness_Test_Data", "Method"));
			THarness.Http_Tokenise_ClickContinue();
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Probe Response')]")));
			
			String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			if(returnMessage.compareTo("SUCCESS")==0) 
			{
				
				Reporting.updateTestReport("Return message is: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC22", "TestHarness_Test_Data", "Return_Message", returnMessage);
			String returnCode = THarness.Http_Tokenise_FetchReturnCode();
			if(returnCode.compareTo("0")==0) 
			{
				
				Reporting.updateTestReport("Return code is: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC22", "TestHarness_Test_Data", "Return_Code", returnCode);
			
            String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC22", "TestHarness_Test_Data", "Operation", operation);
			
            String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC22", "TestHarness_Test_Data", "transID", transID);
			
		}
		
		catch (Exception e) 
		{
			System.out.println("SOAP_Probe Operation Failed" + e.getMessage());
			Reporting.updateTestReport("VoidAuth Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of proper error message and error code for SOAP Probe operation
     */
	@Test
	
        public void TC23_SOAP_Probe_Error_Message_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC23_SOAP Client: "
					+ "Validate that for Probe Operation,"
					+ "on giving some incorrect details, proper error message with error code displayed");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickSOAP_Interface();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC23", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterMethod(excelOperation.getTestData("TC23", "TestHarness_Test_Data", "Method"));
			THarness.Http_Tokenise_ClickContinue();
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Probe Response')]")));
			
			
			String actualReturnMessage = THarness.Http_Tokenise_FetchReturnMessage();
            String expectedReturnMessage = excelOperation.getTestData("TC23", "Error_Message", "Return_Message");
            
			if(actualReturnMessage.compareTo(expectedReturnMessage)==0) 
			{
			
				Reporting.updateTestReport("Return message is: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC23", "TestHarness_Test_Data", "Return_Message", actualReturnMessage);
			String actualReturnCode = THarness.Http_Tokenise_FetchReturnCode();
			String expectedReturnCode = excelOperation.getTestData("TC23", "Error_Message", "Return_Code");
			if(actualReturnCode.compareTo(expectedReturnCode)==0) 
			{
				
				Reporting.updateTestReport("Return code is: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC23", "TestHarness_Test_Data", "Return_Code", actualReturnCode);
			
            String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC23", "TestHarness_Test_Data", "Operation", operation);
			
            String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC23", "TestHarness_Test_Data", "transID", transID);
			
		}
		
		catch (Exception e) 
		{
			System.out.println("SOAP_Probe Operation Failed" + e.getMessage());
			Reporting.updateTestReport("VoidAuth Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of SOAP TokenAuth operation request and response page data in ES and Stripe
     */
	@Test
	
        public void TC24_SOAP_TokenAuth_ES_Stripe_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC24_SOAP Client: "
					+ "Validate that for TokenAuth Operation,"
					+ "Request page, Response page is displayed successfully with all the required details"
					+ "and proper data is displayed in elastic as well as Stripe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickSOAP_Interface();
			THarness.ClickSOAP_TokenAuth();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC24", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterMethod(excelOperation.getTestData("TC24", "TestHarness_Test_Data", "Method"));
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC24", "TestHarness_Test_Data", "Token_number"));
			THarness.Http_Tokenise_ClickContinue();
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Token Authorise Response')]")));
			
			String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			if(returnMessage.compareTo("SUCCESS")==0) 
			{
				
				Reporting.updateTestReport("Return message is: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC24", "TestHarness_Test_Data", "Return_Message", returnMessage);
			String returnCode = THarness.Http_Tokenise_FetchReturnCode();
			if(returnCode.compareTo("0")==0) 
			{
				
				Reporting.updateTestReport("Return code is: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC24", "TestHarness_Test_Data", "Return_Code", returnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC24", "TestHarness_Test_Data", "Operation", operation);
			
            String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC24", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC24", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			
            String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC24", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC24", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			
            String acquirerID = THarness.SOAP_TokenAuthSettle_FetchAcquirerID();
			excelOperation.updateTestData("TC24", "TestHarness_Test_Data", "acquirerID", acquirerID);
			
            String acquirerName = THarness.SOAP_TokenAuthSettle_FetchAcquirerName();
			excelOperation.updateTestData("TC24", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String maskedCardNumber = THarness.SOAP_TokenAuthSettle_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC24", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			
            String cardExpiry = THarness.SOAP_TokenAuthSettle_FetchCardExpiry();
			excelOperation.updateTestData("TC24", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
			
		}
		
		catch (Exception e) 
		{
			System.out.println("SOAP_Token Auth Operation Failed" + e.getMessage());
			Reporting.updateTestReport("TokenAuth Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of proper error message and error code for SOAP TokenAuth operation
     */
	@Test
	

    public void TC25_SOAP_TokenAuth_Error_Message_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC25_SOAP Client: "
					+ "Validate that for TokenAuth Operation,"
					+ "on giving some incorrect details, proper error message with error code displayed");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickSOAP_Interface();
			THarness.ClickSOAP_TokenAuth();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC25", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterMethod(excelOperation.getTestData("TC25", "TestHarness_Test_Data", "Method"));
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC25", "TestHarness_Test_Data", "Token_number"));
			THarness.Http_Tokenise_ClickContinue();
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Token Authorise Response')]")));
			
			
			String actualReturnMessage = THarness.Http_Tokenise_FetchReturnMessage();
            String expectedReturnMessage = excelOperation.getTestData("TC25", "Error_Message", "Return_Message");
            
			if(actualReturnMessage.compareTo(expectedReturnMessage)==0) 
			{
			
			   Reporting.updateTestReport("Return message is: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC25", "TestHarness_Test_Data", "Return_Message", actualReturnMessage);
			String actualReturnCode = THarness.Http_Tokenise_FetchReturnCode();
			String expectedReturnCode = excelOperation.getTestData("TC25", "Error_Message", "Return_Code");
			if(actualReturnCode.compareTo(expectedReturnCode)==0) 
			{
				
				Reporting.updateTestReport("Return code is: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC25", "TestHarness_Test_Data", "Return_Code", actualReturnCode);
			
            String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC25", "TestHarness_Test_Data", "Operation", operation);
			
            String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC25", "TestHarness_Test_Data", "transID", transID);
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC25", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			
            String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC25", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC25", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			
            String acquirerID = THarness.SOAP_TokenAuthSettle_FetchAcquirerID();
			excelOperation.updateTestData("TC25", "TestHarness_Test_Data", "acquirerID", acquirerID);
			
            String acquirerName = THarness.SOAP_TokenAuthSettle_FetchAcquirerName();
			excelOperation.updateTestData("TC25", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String maskedCardNumber = THarness.SOAP_TokenAuthSettle_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC25", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			
            String cardExpiry = THarness.SOAP_TokenAuthSettle_FetchCardExpiry();
			excelOperation.updateTestData("TC25", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
			
		}
		
		catch (Exception e) 
		{
			System.out.println("SOAP_TokenAuth Operation Failed" + e.getMessage());
			Reporting.updateTestReport("TokenAuth Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of SOAP TokenSettle operation request and response page data in ES and Stripe
     */
	@Test
	
        public void TC26_SOAP_TokenSettle_ES_Stripe_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC26_SOAP Client: "
					+ "Validate that for TokenSettle Operation,"
					+ "Request page, Response page is displayed successfully with all the required details"
					+ "and proper data is displayed in elastic as well as Stripe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickSOAP_Interface();
			THarness.ClickSOAP_TokenSettle();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC26", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterMethod(excelOperation.getTestData("TC26", "TestHarness_Test_Data", "Method"));
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC26", "TestHarness_Test_Data", "Token_number"));
			THarness.SOAP_VoidAuth_EnterAuthCode(excelOperation.getTestData("TC26", "TestHarness_Test_Data", "Auth_Code"));
			THarness.Http_Tokenise_ClickContinue();
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Token Settle Response')]")));
			
			String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			if(returnMessage.compareTo("SUCCESS")==0) 
			{
				
				Reporting.updateTestReport("Return message is: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC26", "TestHarness_Test_Data", "Return_Message", returnMessage);
			String returnCode = THarness.Http_Tokenise_FetchReturnCode();
			if(returnCode.compareTo("0")==0) 
			{
				
				Reporting.updateTestReport("Return code is: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC26", "TestHarness_Test_Data", "Return_Code", returnCode);
			
            String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC26", "TestHarness_Test_Data", "Operation", operation);
			
            String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC26", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC26", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			
            String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC26", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC26", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			
            String acquirerID = THarness.Http_Auth_FetchAcquirerID();
			excelOperation.updateTestData("TC26", "TestHarness_Test_Data", "acquirerID", acquirerID);
			
            String acquirerName = THarness.Http_Auth_FetchAcquirerName();
			excelOperation.updateTestData("TC26", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
            String maskedCardNumber = THarness.Http_Auth_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC26", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			
            String cardExpiry = THarness.Http_Auth_FetchCardExpiry();
			excelOperation.updateTestData("TC26", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
			
		}
		
		catch (Exception e) 
		{
			System.out.println("SOAP_Token Settle Operation Failed" + e.getMessage());
			Reporting.updateTestReport("TokenSettle Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of proper error message and error code for SOAP TokenSettle operation
     */
	@Test
	
        public void TC27_SOAP_TokenSettle_Error_Message_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC27_SOAP Client: "
					+ "Validate that for TokenSettle Operation,"
					+ "on giving some incorrect details, proper error message with error code displayed");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickSOAP_Interface();
			THarness.ClickSOAP_TokenSettle();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC26", "TestHarness_Test_Data", "Client_ID"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterMethod(excelOperation.getTestData("TC26", "TestHarness_Test_Data", "Method"));
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC26", "TestHarness_Test_Data", "Token_number"));
			THarness.SOAP_VoidAuth_EnterAuthCode(excelOperation.getTestData("TC26", "TestHarness_Test_Data", "Auth_Code"));
		    THarness.Http_Tokenise_ClickContinue();
			
		    WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Token Settle Response')]")));
			
			
			String actualReturnMessage = THarness.Http_Tokenise_FetchReturnMessage();
            String expectedReturnMessage = excelOperation.getTestData("TC27", "Error_Message", "Return_Message");
            
			if(actualReturnMessage.compareTo(expectedReturnMessage)==0) 
			{
			
				
				Reporting.updateTestReport("Return message is: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC27", "TestHarness_Test_Data", "Return_Message", actualReturnMessage);
			String actualReturnCode = THarness.Http_Tokenise_FetchReturnCode();
			String expectedReturnCode = excelOperation.getTestData("TC27", "Error_Message", "Return_Code");
			if(actualReturnCode.compareTo(expectedReturnCode)==0) 
			{
				
				Reporting.updateTestReport("Return code is: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC27", "TestHarness_Test_Data", "Return_Code", actualReturnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC27", "TestHarness_Test_Data", "Operation", operation);
			
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC27", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC27", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			
			String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC27", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC27", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			
			String acquirerID = THarness.Http_Auth_FetchAcquirerID();
			excelOperation.updateTestData("TC27", "TestHarness_Test_Data", "acquirerID", acquirerID);
			
			String acquirerName = THarness.Http_Auth_FetchAcquirerName();
			excelOperation.updateTestData("TC27", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String maskedCardNumber = THarness.Http_Auth_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC27", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			
			String cardExpiry = THarness.Http_Auth_FetchCardExpiry();
			excelOperation.updateTestData("TC27", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
			
		}
		
		catch (Exception e) 
		{
			System.out.println("SOAP_TokenAuth Operation Failed" + e.getMessage());
			Reporting.updateTestReport("TokenAuth Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
     * @Author: Jayanta
     * @Description: Validation of SOAP Token Auth & Settle operation request and response page data in ES and Stripe
     */
	@Test
	public void TC28_SOAP_TokenAuthSettle_ES_Stripe_Validation() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC28_SOAP Client: "
					+ "Validate that for Token Auth & Settle Operation,"
					+ "Request page, Result page is displayed successfully with all the required details"
					+ "and proper data is displayed in elastic as well as Stripe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickSOAP_Interface();
			THarness.ClickSOAP_TokenAuthSettle();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC28", "TestHarness_Test_Data", "Client_ID"));
			THarness.SOAP_TokenAuthSettle_EnterMethod(excelOperation.getTestData("TC28", "TestHarness_Test_Data", "Method"));
			THarness.SOAP_TokenAuthSettle_CustPresent();
			THarness.SOAP_TokenAuthSettle_CustPresentValue();
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC28", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterValue(excelOperation.getTestData("TC28", "TestHarness_Test_Data", "Value"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC28", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC28", "TestHarness_Test_Data", "Token_Number"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC28", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Probe_ClickPerform();
			
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Token Authorise & Settle Response')]")));
			
			String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			if(returnMessage.compareTo("SUCCESS")==0) {
				
				Reporting.updateTestReport("Return message is: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC28", "TestHarness_Test_Data", "Return_Message", returnMessage);
			String returnCode = THarness.Http_Tokenise_FetchReturnCode();
			if(returnCode.compareTo("0")==0) {
				
				Reporting.updateTestReport("Return code is: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC28", "TestHarness_Test_Data", "Return_Code", returnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC28", "TestHarness_Test_Data", "Operation", operation);
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC28", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC28", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC28", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC28", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			String acquirerID = THarness.SOAP_TokenAuthSettle_FetchAcquirerID();
			excelOperation.updateTestData("TC28", "TestHarness_Test_Data", "acquirerID", acquirerID);
			String acquirerName = THarness.SOAP_TokenAuthSettle_FetchAcquirerName();
			excelOperation.updateTestData("TC28", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String maskedCardNumber = THarness.SOAP_TokenAuthSettle_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC28", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			String cardExpiry = THarness.SOAP_TokenAuthSettle_FetchCardExpiry();
			excelOperation.updateTestData("TC28", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		} 
		catch (Exception e) 
		{
			System.out.println("SOAP Token Auth & Settle Operation Failed" + e.getMessage());
			Reporting.updateTestReport("SOAP Token Auth & Settle Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	

	/*
     * @Author: Jayanta
     * @Description: Validation of proper error message and error code for SOAP Token Auth & Settle operation
     */
	@Test
	public void TC29_SOAP_TokenAuthSettle_Error_Message() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC29_SOAP Client: "
					+ "Validate that for Token Auth & Settle,"
					+ "on giving some incorrect details, proper error message with error code displayed"
					);
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickSOAP_Interface();
			THarness.ClickSOAP_TokenAuthSettle();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC29", "TestHarness_Test_Data", "Client_ID"));
			THarness.SOAP_TokenAuthSettle_EnterMethod(excelOperation.getTestData("TC29", "TestHarness_Test_Data", "Method"));
			THarness.SOAP_TokenAuthSettle_CustPresent();
			THarness.SOAP_TokenAuthSettle_CustPresentValue();
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC29", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterValue(excelOperation.getTestData("TC29", "TestHarness_Test_Data", "Value"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC29", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC29", "TestHarness_Test_Data", "Token_Number"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC29", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Probe_ClickPerform();
			
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Token Authorise & Settle Response')]")));
			
			String actualReturnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			String expectedReturnMessage = excelOperation.getTestData("TC29", "Error_Message", "Return_Message");
			if(actualReturnMessage.compareTo(expectedReturnMessage)==0) {
				
				Reporting.updateTestReport("Return message is: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC29", "TestHarness_Test_Data", "Return_Message", actualReturnMessage);
			String actualReturnCode = THarness.Http_Tokenise_FetchReturnCode();
			String expectedReturnCode = excelOperation.getTestData("TC29", "Error_Message", "Return_Code");
			if(actualReturnCode.compareTo(expectedReturnCode)==0) {
				
				Reporting.updateTestReport("Return code is: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC29", "TestHarness_Test_Data", "Return_Code", actualReturnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC29", "TestHarness_Test_Data", "Operation", operation);
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC29", "TestHarness_Test_Data", "transID", transID);
			
		} 
		catch (Exception e) 
		{
			System.out.println("SOAP Token Auth & Settle Operation Failed" + e.getMessage());
			Reporting.updateTestReport("SOAP Token Auth & Settle Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	/*
     * @Author: Varisa
     * @Description: Validation of SOAP TokenRefund operation request and response page data in ES and Stripe
     */
    @Test
    public void TC30_SOAP_TokenRefund_ES_Stripe_Validation() throws IOException
    {

        try {
            Reporting.test = Reporting.extent.createTest("TC30_SOAP Client: "
                    + "Validate that for TokenRefund Operation,"
                    + "Request page, Response page is displayed successfully with all the required details"
                    + "and proper data is displayed in elastic as well as Stripe");

            driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
            THarness.ClickSOAP_Interface();
            THarness.ClickSOAP_TokenRefund();
            THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC30", "TestHarness_Test_Data", "Client_ID"));
            THarness.SOAP_TokenAuthSettle_EnterMethod(excelOperation.getTestData("TC30", "TestHarness_Test_Data", "Method"));
            THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC30", "TestHarness_Test_Data", "Description"));
            THarness.Http_Authorise_EnterValue(excelOperation.getTestData("TC30", "TestHarness_Test_Data", "Value"));
            THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC30", "TestHarness_Test_Data", "Currency_Code"));
            THarness.Http_Tokenise_SelectRegion();
            THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC30", "TestHarness_Test_Data", "Token_number"));
            THarness.Http_Tokenise_ClickContinue();

            WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
            pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Token Refund Response')]")));

            String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
            if(returnMessage.compareTo("SUCCESS")==0) 
            {

                Reporting.updateTestReport("Return message is: " + returnMessage,
                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
            }
            else 
            {
                Reporting.updateTestReport("Return message is not: " + returnMessage,
                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
            }
            excelOperation.updateTestData("TC30", "TestHarness_Test_Data", "Return_Message", returnMessage);
            String returnCode = THarness.Http_Tokenise_FetchReturnCode();
            if(returnCode.compareTo("0")==0) 
            {

                Reporting.updateTestReport("Return code is: " + returnCode,
                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
            }
            else 
            {
                Reporting.updateTestReport("Return code is not: " + returnCode,
                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
            }
            excelOperation.updateTestData("TC30", "TestHarness_Test_Data", "Return_Code", returnCode);
            String operation = THarness.Http_Tokenise_FetchOperation();
            excelOperation.updateTestData("TC30", "TestHarness_Test_Data", "Operation", operation);
            String transID = THarness.Http_Tokenise_FetchTransID();
            excelOperation.updateTestData("TC30", "TestHarness_Test_Data", "transID", transID);
            
            ScrollingWebPage.PageDown(driver,SS_path);
            
            String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
            excelOperation.updateTestData("TC30", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
            String merchantReference = THarness.Http_Auth_FetchMerchantReference();
            excelOperation.updateTestData("TC30", "TestHarness_Test_Data", "merchantReference", merchantReference);
            
            ScrollingWebPage.PageDown(driver,SS_path);
            
            String acquirerID = THarness.SOAP_TokenAuthSettle_FetchAcquirerID();
            excelOperation.updateTestData("TC30", "TestHarness_Test_Data", "acquirerID", acquirerID);
            String acquirerName = THarness.SOAP_TokenAuthSettle_FetchAcquirerName();
            excelOperation.updateTestData("TC30", "TestHarness_Test_Data", "acquirerName", acquirerName);
            
            ScrollingWebPage.PageDown(driver,SS_path);
            
            String maskedCardNumber = THarness.SOAP_TokenAuthSettle_FetchMaskedCardNumber();
            excelOperation.updateTestData("TC30", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
            String cardExpiry = THarness.SOAP_TokenAuthSettle_FetchCardExpiry();
            excelOperation.updateTestData("TC30", "TestHarness_Test_Data", "cardExpiry", cardExpiry);


        }

        catch (Exception e) 
        {
            System.out.println("SOAP_Token Refund Operation Failed" + e.getMessage());
            Reporting.updateTestReport("TokenRefund Operation Failed" + e.getMessage(),
                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
        }
    }
	
	/*
     * @Author: Jayanta
     * @Description: Validation of SOAP Void Auth operation request and response page data in ES and Stripe
     */
	@Test
	public void TC31_SOAP_VoidAuth_ES_Stripe_Validation() throws IOException
	{
		
		try {
			TC10_HTTP_Authorise_ES_Stripe_Validation();
			Reporting.test = Reporting.extent.createTest("TC31_SOAP Client: "
					+ "Validate that for Void Auth Operation,"
					+ "Request page,Result page is displayed successfully with all the required details"
					+ "and proper data is displayed in elastic as well as Stripe");
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickSOAP_Interface();
			THarness.ClickSOAP_VoidAuth();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC31", "TestHarness_Test_Data", "Client_ID"));
			THarness.SOAP_TokenAuthSettle_CustPresent();
			THarness.SOAP_TokenAuthSettle_CustPresentValue();
			THarness.SOAP_TokenAuthSettle_EnterMethod(excelOperation.getTestData("TC31", "TestHarness_Test_Data", "Method"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC31", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC31", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC31", "TestHarness_Test_Data", "Token_Number"));
			THarness.SOAP_VoidAuth_EnterAuthCode(excelOperation.getTestData("TC31", "TestHarness_Test_Data", "Auth_Code"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC31", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Probe_ClickPerform();
			
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Void Authorise Response')]")));
			
			String returnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			if(returnMessage.compareTo("SUCCESS")==0) {
				
				Reporting.updateTestReport("Return message is: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + returnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC31", "TestHarness_Test_Data", "Return_Message", returnMessage);
			String returnCode = THarness.Http_Tokenise_FetchReturnCode();
			if(returnCode.compareTo("0")==0) {
				
				Reporting.updateTestReport("Return code is: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + returnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC31", "TestHarness_Test_Data", "Return_Code", returnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC31", "TestHarness_Test_Data", "Operation", operation);
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC31", "TestHarness_Test_Data", "transID", transID);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String merchantResponse = THarness.Http_Auth_FetchMerchantResponse();
			excelOperation.updateTestData("TC31", "TestHarness_Test_Data", "merchantResponse", merchantResponse);
			String merchantReference = THarness.Http_Auth_FetchMerchantReference();
			excelOperation.updateTestData("TC31", "TestHarness_Test_Data", "merchantReference", merchantReference);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String authCode = THarness.SOAP_TokenAuthSettle_FetchAuthCode();
			excelOperation.updateTestData("TC31", "TestHarness_Test_Data", "Res_auth_Code", authCode);
			String acquirerID = THarness.SOAP_TokenAuthSettle_FetchAcquirerID();
			excelOperation.updateTestData("TC31", "TestHarness_Test_Data", "acquirerID", acquirerID);
			String acquirerName = THarness.SOAP_TokenAuthSettle_FetchAcquirerName();
			excelOperation.updateTestData("TC31", "TestHarness_Test_Data", "acquirerName", acquirerName);
			
			ScrollingWebPage.PageDown(driver,SS_path);
			
			String maskedCardNumber = THarness.SOAP_TokenAuthSettle_FetchMaskedCardNumber();
			excelOperation.updateTestData("TC31", "TestHarness_Test_Data", "maskedCardNumber", maskedCardNumber);
			String cardExpiry = THarness.SOAP_TokenAuthSettle_FetchCardExpiry();
			excelOperation.updateTestData("TC31", "TestHarness_Test_Data", "cardExpiry", cardExpiry);
		} 
		catch (Exception e) 
		{
			System.out.println("SOAP Void Auth Operation Failed" + e.getMessage());
			Reporting.updateTestReport("SOAP Void Auth Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	/*
     * @Author: Jayanta
     * @Description: Validation of proper error message and error code for SOAP Void Auth operation
     */
	@Test
	public void TC32_SOAP_VoidAuth_Error_Message() throws IOException
	{
		
		try {
			Reporting.test = Reporting.extent.createTest("TC32_SOAP Client: "
					+ "Validate that for Void Auth,"
					+ "on giving some incorrect details, proper error message with error code displayed"
					);
			
			driver.get(excelOperation.getTestData("TestHarness_URL", "Generic_Dataset", "Data"));
			THarness.ClickSOAP_Interface();
			THarness.ClickSOAP_VoidAuth();
			THarness.Http_Tokenise_EnterClientID(excelOperation.getTestData("TC32", "TestHarness_Test_Data", "Client_ID"));
			THarness.SOAP_TokenAuthSettle_CustPresent();
			THarness.SOAP_TokenAuthSettle_CustPresentValue();
			THarness.SOAP_TokenAuthSettle_EnterMethod(excelOperation.getTestData("TC32", "TestHarness_Test_Data", "Method"));
			THarness.Http_Tokenise_EnterClientDescription(excelOperation.getTestData("TC32", "TestHarness_Test_Data", "Description"));
			THarness.Http_Authorise_EnterCurrency(excelOperation.getTestData("TC32", "TestHarness_Test_Data", "Currency_Code"));
			THarness.Http_Tokenise_SelectRegion();
			THarness.SOAP_TokenAuthSettle_EnterToken(excelOperation.getTestData("TC32", "TestHarness_Test_Data", "Token_Number"));
			THarness.SOAP_VoidAuth_EnterAuthCode(excelOperation.getTestData("TC32", "TestHarness_Test_Data", "Auth_Code"));
			THarness.Http_Tokenise_EnterClientPWD(excelOperation.getTestData("TC32", "TestHarness_Test_Data", "Client_ID_PWD"));
			THarness.Http_Probe_ClickPerform();
			
			WebDriverWait pagewait = new WebDriverWait(driver, Duration.ofSeconds(50));
			pagewait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Void Authorise Response')]")));
			
			String actualReturnMessage = THarness.Http_Tokenise_FetchReturnMessage();
			String expectedReturnMessage = excelOperation.getTestData("TC32", "Error_Message", "Return_Message");
			if(actualReturnMessage.compareTo(expectedReturnMessage)==0) {
				
				Reporting.updateTestReport("Return message is: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return message is not: " + actualReturnMessage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC32", "TestHarness_Test_Data", "Return_Message", actualReturnMessage);
			String actualReturnCode = THarness.Http_Tokenise_FetchReturnCode();
			String expectedReturnCode = excelOperation.getTestData("TC32", "Error_Message", "Return_Code");
			if(actualReturnCode.compareTo(expectedReturnCode)==0) {
				
				Reporting.updateTestReport("Return code is: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else 
			{
				Reporting.updateTestReport("Return code is not: " + actualReturnCode,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			excelOperation.updateTestData("TC32", "TestHarness_Test_Data", "Return_Code", actualReturnCode);
			String operation = THarness.Http_Tokenise_FetchOperation();
			excelOperation.updateTestData("TC32", "TestHarness_Test_Data", "Operation", operation);
			String transID = THarness.Http_Tokenise_FetchTransID();
			excelOperation.updateTestData("TC32", "TestHarness_Test_Data", "transID", transID);
			
		} 
		catch (Exception e) 
		{
			System.out.println("SOAP Void Auth Operation Failed" + e.getMessage());
			Reporting.updateTestReport("SOAP Void Auth Operation Failed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
}

