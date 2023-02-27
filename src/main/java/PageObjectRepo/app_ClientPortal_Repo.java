package PageObjectRepo;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Test_Suite.ClientPortal_RegressionSuite;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.StatusDetails;

public class app_ClientPortal_Repo extends DriverModule {
	
	public String SS_path=ClientPortal_RegressionSuite.SS_path;
	Actions act = new Actions(driver);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	
	/* 
	 * Author : Jayanta
	 * Description : Object repo for Client portal Sign In
	 */
	
	@FindBy(name="loginfmt")
    WebElement signInEmailAddress;
	
	@FindBy(id="idSIButton9")
    WebElement ClickNext;
	
	@FindBy(name="passwd")
    WebElement signInPWD;
	
	@FindBy(xpath = "//a[contains(text(),'Home')]")
	WebElement ClickHome;
	
	@FindBy(xpath = "//a[contains(text(),'New Client Application')]")
	WebElement ClickNewClientApp;
	
	@FindBy(xpath = "//a[contains(text(),'Register New User')]")
	WebElement ClickRegisterNewUser;
	
	@FindBy(xpath = "//a[contains(text(),'My Worklist')]")
	WebElement ClickMyWorklist;
	
	@FindBy(xpath = "//a[contains(text(),'Transaction Search')]")
	WebElement ClickTransactionSearch;
	
	@FindBy(xpath = "//*[local-name()='svg' and @class='fa-icon' and @stroke-linecap='round']/*[local-name()='path']")
    WebElement ClickLogOut;
	
	@FindBy(xpath = "(//img[@class='tile-img'])[1]")
	WebElement ClickLogOutImage;
	
	@FindBy(xpath = "//button[@type='button' and @class='btn blue-btn']")
	WebElement ClickLogIn;
	
	@FindBy(xpath = "//div[@id='otherTileText']")
	WebElement ClickAnotherUserAccount;
	
	/* 
	 * Author : Jayanta
	 * Description : Object repo for Client portal WPS Admin to add new client app
	 */
	
	@FindBy(xpath = "//option[@value='SOAP']")
    WebElement selectSOAP;
	
	@FindBy(xpath = "//option[@value='HTTP']")
    WebElement selectHTTP;
	
	@FindBy(xpath = "//option[@value='APL']")
    WebElement selectBusinessUnit;
	
	@FindBy(xpath = "//option[@value='Research']")
    WebElement editBusinessUnit;
	
	@FindBy(name="name")
    WebElement enterClientAppName;
	
	@FindBy(name="appShortName")
    WebElement enterClientAppShortName;
	
	@FindBy(xpath = "//option[@value='671']")
    WebElement selectUserID;
	
	@FindBy(xpath = "//span[contains(text(),'Card Payment')]")
   	WebElement ClickPaymentMethod;
	
	@FindBy(name="Debit/Credit Card")
    WebElement selectDebitCard;
	
	@FindBy(xpath = "//button[@type='submit' and @class='btn blue-btn']")
	WebElement ClickAdd;
	
	@FindBy(name="template")
    WebElement enterTemplate;
	
	@FindBy(name="targetUrl")
    WebElement entertargetUrl;
	
	@FindBy(xpath = "//li[contains(text(),'Promote to Production')]")
    WebElement Click_PromoteToProd;
	
	@FindBy(xpath = "//div[@class='colAction']")
    WebElement Click_ViewIcon;
	

	
	/* 
	 * Author : Jayanta
	 * Description : Object repo for Client portal WPS Admin to register new user
	 */
	
	@FindBy(name="firstName")
    WebElement enterFirstName;
	
	@FindBy(name="lastName")
    WebElement enterLastName;
	
	@FindBy(name="ssoId")
    WebElement enterSSOID;
	
	@FindBy(xpath = "//option[@value='WPS_Admin']")
    WebElement selectRole;
	
	@FindBy(xpath = "//option[@value='Client_Admin']")
    WebElement selectRoleSDUser;
	
	@FindBy(xpath = "//button[@type='submit' and @class='btn blue-btn']")
	WebElement ClickRegister;
	
	@FindBy(xpath = "//button[@class='btn white-btn back-btn']")
	WebElement ClickCancel;
	
	/* 
	 * Author : Jayanta
	 * Description : Object repo for Client portal WPS Admin home page filter
	 */
	
	@FindBy(xpath="//th[contains(text(),'Name')]/div/div/input")
    WebElement enterHomeName;
	
	@FindBy(xpath="//div[@class='more-less-content']")
    WebElement fetchHomeName;
	
	@FindBy(xpath="(//div[@class='nxtbutton']/button)[1]")
    WebElement ClickNextPagination;
	
	@FindBy(xpath="(//div[@class='prevbutton']/button)[2]")
	WebElement ClickPrevPagination;
	
	
	/* 
	 * Author : Jayanta
	 * Description : Object repo for Client portal WPS Admin edit client app page
	 */
	
	@FindBy(xpath = "//*[local-name()='svg' and @class='fa-icon2' and @viewBox='0 0 512 512']/*[local-name()='path']")
    WebElement ClickEditIcon;
	
	@FindBy(xpath = "//button[@type='submit' and @class='btn blue-btn']")
	WebElement ClickSave;
	
	/* 
	 * Author : Jayanta
	 * Description : Object repo for footer in every page of Client portal for WPS Admin 
	 */
	
	@FindBy(xpath = "//a[contains(text(),'Copyright © 2000-2023')]")
	WebElement ClickFooter;
	
	 /*
	  * Author : Varisa
	  * Description : Method to click on Approve button in my Approve client App page
	  */

	 @FindBy(xpath = "(//div[@class='btn-grp'])[3]/button[@class='btn blue-btn']")
	 WebElement Click_Approve;
	 
	 /*
	  * Author : Jayanta
	  * Description : Object Repo for decline button in Approve client App page
	  */

	 @FindBy(xpath = "(//div[@class='btn-grp'])[3]/button[@class='btn white-btn']")
	 WebElement Click_Decline;

	 /*
	  * Author : Varisa
	  * Description : Method to click on Confirm button
	  */

	  @FindBy(xpath = "(//div[@class='btn-grp'])[2]/button[@class='btn blue-btn']")
	  WebElement Click_Confirm;
	  
	  /*
	   * Author : Varisa
	   * Description : Method to click on Confirm button
	   */
	  @FindBy(xpath = "//option[@value='WPS_Support']")
	    WebElement selectWPSSupportRole;
	  
	  /* 
	   * Author : Varisa
	   * Description : Object repo for Type error in register New Client App page. 
	   */
		
		@FindBy(xpath = "//span[contains(text(),'Type is required')]")
		WebElement ClickTypeErrorMessage;
		
		@FindBy(xpath = "//span[contains(text(),'Business Unit is required')]")
		WebElement ClickBusinessUnitErrorMessage;
		
		@FindBy(xpath = "//span[contains(text(),'Name is required')]")
		WebElement ClickNameErrorMessage;
		
		@FindBy(xpath = "//span[contains(text(),'Application Short Name is required')]")
		WebElement ClickAppShortNameErrorMessage;
		
		@FindBy(xpath = "//span[contains(text(),'User ID is required')]")
		WebElement ClickUserIdErrorMessage;
		
		@FindBy(xpath = "//span[contains(text(),'Please select at least one payment method')]")
		WebElement ClickpaymentsErrorMessage;
		
		@FindBy(xpath = "//span[contains(text(),'Template is required')]")
		WebElement ClickTemplateErrorMessage;
		
		@FindBy(xpath = "//span[contains(text(),'Target URL is required')]")
		WebElement ClickTargetURLErrorMessage;
		
		@FindBy(xpath = "//span[contains(text(),'Name can not exceed 35 characters')]")
		WebElement ClickName1ErrorMessage;
		
		@FindBy(xpath = "//span[contains(text(),'Special character/whitespace is not allowed for application shortname. Please provide another value.')]")
		WebElement ClickAppShortName1ErrorMessage;
		
		/* 
		 * Author : Jayanta
		 * Description : Object repo for  error in register New User page. 
		 */
			
	    @FindBy(xpath = "//span[contains(text(),'First Name is required')]")
	    WebElement ClickFNameErrorMessage;
	    
	    @FindBy(xpath = "//span[contains(text(),'Last Name is required')]")
	    WebElement ClickLNameErrorMessage;
	    
	    @FindBy(xpath = "(//span[@class='input-err-msg'])[3]")
	    WebElement ClickSSOIDErrorMessage;
	    
	    @FindBy(xpath = "//span[contains(text(),'Role is required')]")
	    WebElement ClickRoleErrorMessage;
	    
	    @FindBy(xpath = "//span[contains(text(),'First Name can not exceed 35 characters')]")
	    WebElement ClickFNameSizeErrorMessage;
	    
	    @FindBy(xpath = "//span[contains(text(),'Last Name can not exceed 35 characters')]")
	    WebElement ClickLNameSizeErrorMessage;
	    
	    @FindBy(xpath = "(//span[@class='input-err-msg'])[3]")
	    WebElement ClickSSOIDSizeErrorMessage;
	    
	    /* 
		 * Author : Jayanta
		 * Description : Object repo to click revert in Home page. 
		 */
	    
	    @FindBy(xpath = "//a[contains(text(),'revert')]")
	    WebElement Click_Revert;
	    
	
	/* 
	 * Author : Jayanta
	 * Description : Method to enter email address in Sign In for Client portal
	 */
	
	public void WPSAdmin_LogIN_EnterSignInEmail(String emailAddress) throws IOException {
		try {
			signInEmailAddress.sendKeys(emailAddress);
			Reporting.updateTestReport("Email Address Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Email Address is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click Next after entering Email address Client portal Sign In
	 */
	
	public void WPSAdmin_LogIN_ClickNext() throws IOException {
		try {
			ClickNext.click();
			Reporting.updateTestReport("Next Button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Next button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to enter password in Sign In for Client portal
	 */
	
	public void WPSAdmin_LogIN_EnterPWD(String PWD) throws IOException {
		try {
			signInPWD.sendKeys(PWD);
			Reporting.updateTestReport("Password Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Password is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click Home for WPS Admin
	 */
	
	public void WPSAdmin_ClickHome() throws IOException {
		try {
			ClickHome.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("Home is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Home is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click New Client Application for WPS Admin
	 */
	
	public void WPSAdmin_ClickNewClientApp() throws IOException {
		try {
			ClickNewClientApp.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("New Client Application is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("New Client Application is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click Register New User for WPS Admin
	 */
	
	public void WPSAdmin_ClickRegisterNewUser() throws IOException {
		try {
			ClickRegisterNewUser.click();
			Thread.sleep(2000);
			
			Reporting.updateTestReport("Register New User is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Register New User is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click My Worklist for WPS Admin
	 */
	
	public void WPSAdmin_ClickMyWorklist() throws IOException {
		try {
			ClickMyWorklist.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("My Worklist is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("My Worklist is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click Transaction Search for WPS Admin
	 */
	
	public void WPSAdmin_ClickTransactionSearch() throws IOException {
		try {
			ClickTransactionSearch.click();
			Thread.sleep(5000);
			ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
		    //switch to new tab
		    driver.switchTo().window(newTb.get(1));
		    System.out.println("Page title of new tab: " + driver.getTitle());
		    Reporting.updateTestReport("Transaction Search is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		    
		    //switch to parent window
		    driver.switchTo().window(newTb.get(0));
		    System.out.println("Page title of parent window: " + driver.getTitle());
		    
		}
		catch(Exception e){
			Reporting.updateTestReport("Transaction Search is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click log out for WPS Admin
	 */
	
	public void WPSAdmin_ClickLogOut() throws IOException {
		try {
		    act.moveToElement(ClickLogOut).click().build().perform();
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginHeader")));
		    Thread.sleep(2000);
			Reporting.updateTestReport("Log out is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Log out is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click log out Image for WPS Admin
	 */
	
	public void WPSAdmin_ClickLogOutImage() throws IOException {
		try {
			ClickLogOutImage.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'You have been logged out from WPS Client Portal. Please login to continue.')]")));
			Thread.sleep(2000);
			Reporting.updateTestReport("Log out Image is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Log out Image is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click logIN for WPS Admin
	 */
	
	public void WPSAdmin_ClickLogIN() throws IOException {
		try {
			ClickLogIn.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='heading']")));
			Thread.sleep(2000);
			Reporting.updateTestReport("LogIN button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("LogIN button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click Use Another account for WPS Admin
	 */
	
	public void WPSAdmin_ClickAnotherUserAccount() throws IOException {
		try {
			ClickAnotherUserAccount.click();
			Reporting.updateTestReport("Use Another account is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Use Another account is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to select app type as SOAP in add new client app screen for WPS Admin
	 */
	
	public void WPSAdmin_SelectSOAP() throws IOException {
		try {
			selectSOAP.click();
			Reporting.updateTestReport("SOAP is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("SOAP is not selected : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to select app type as HTTP in add new client app screen for WPS Admin
	 */
	
	public void WPSAdmin_SelectHTTP() throws IOException {
		try {
			selectHTTP.click();
			Reporting.updateTestReport("HTTP is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("HTTP is not selected : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to select Business Unit as APL in add new client app screen for WPS Admin
	 */
	
	public void WPSAdmin_SelectBusinessUnit() throws IOException {
		try {
			selectBusinessUnit.click();
			Reporting.updateTestReport("Business Unit is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Business Unit is not selected : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to edit Business Unit as Research in edit new client app screen for Client Admin
	 */
	
	public void ClientAdmin_EditBusinessUnit() throws IOException {
		try {
			 editBusinessUnit.click();
			Reporting.updateTestReport("Business Unit is edited successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Business Unit is not edited : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to enter name in Add New Client App screen for WPS Admin
	 */
	
	public void WPSAdmin_Enter_ClientApp_Name(String name) throws IOException {
		try {
			enterClientAppName.sendKeys(name);
			Reporting.updateTestReport("Name " +name+ " Entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Name is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to enter app short name in Add New Client App screen for WPS Admin
	 */
	
	public void WPSAdmin_Enter_ClientApp_ShortName(String shortname) throws IOException {
		try {
			enterClientAppShortName.sendKeys(shortname);
			Thread.sleep(2000);
			Reporting.updateTestReport("App Short Name " +shortname+ " Entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("App Short Name is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to select User ID as prrg in add new client app screen for WPS Admin
	 */
	
	public void WPSAdmin_SelectUserID() throws IOException {
		try {
			selectUserID.click();
			Reporting.updateTestReport("User ID is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("User ID is not selected : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	
	 // Author : Jayanta
	 // Description : Method to click payment method as card payment in add new client app screen for WPS Admin
	 
	
	public void WPSAdmin_ClickPaymentMethod() throws IOException {
		try {
		    act.moveToElement(ClickPaymentMethod).click().build().perform();
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Debit/Credit Card")));
			Reporting.updateTestReport("Payment Method is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Payment Method is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description :  Method to select debit/credit card as card payment in add new client app screen for WPS Admin
	 */
	
	public void WPSAdmin_Click_DebitCard() throws IOException {
		try {
		    act.moveToElement(selectDebitCard).click().build().perform();
		    Thread.sleep(2000);
			Reporting.updateTestReport("Debit/Credit card is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Debit/Credit card is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Varisa
	 * Description : Method to enter Template in Add New Client App screen for WPS Admin
	 */
	
	public void WPSAdmin_Enter_Template(String Template) throws IOException {
		try {
			enterTemplate.sendKeys(Template);
			Thread.sleep(2000);
			Reporting.updateTestReport("Template Entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Template is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Varisa
	 * Description : Method to enter TargetURL in Add New Client App screen for WPS Admin
	 */
	
	public void WPSAdmin_Enter_TargetURL(String TargetURL) throws IOException {
		try {
			entertargetUrl.sendKeys(TargetURL);
			Thread.sleep(2000);
			Reporting.updateTestReport("TargetURL Entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("TargetURL is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description :  Method to click add button in add new client app screen for WPS Admin
	 */
	
	public void WPSAdmin_Click_Add() throws IOException {
		try {
			ClickAdd.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'application has been added successfully')]")));
		    driver.findElement(By.xpath("//span[contains(text(),'application has been added successfully')]")).click();
		    Thread.sleep(5000);
			Reporting.updateTestReport("Add button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Add button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to enter first name in RegisterNew User screen for WPS Admin
	 */
	
	public void WPSAdmin_Enter_First_Name(String fname) throws IOException {
		try {
			enterFirstName.clear();
			enterFirstName.sendKeys(fname);
			Reporting.updateTestReport("First Name " +fname+ " Entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("First Name is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to enter last name in RegisterNew User screen for WPS Admin
	 */
	
	public void WPSAdmin_Enter_Last_Name(String lname) throws IOException {
		try {
			enterLastName.clear();
			enterLastName.sendKeys(lname);
			Reporting.updateTestReport("Last Name " +lname+ " Entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Last Name is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to enter sso id in RegisterNew User screen for WPS Admin
	 */
	
	public void WPSAdmin_Enter_SSO_ID(String sso) throws IOException {
		try {
			enterSSOID.clear();
			enterSSOID.sendKeys(sso);
			Reporting.updateTestReport("SSO ID " +sso+ " Entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("SSO ID is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to select Role as WPS Admin in Register New User screen for WPS Admin
	 */
	
	public void WPSAdmin_SelectRole() throws IOException {
		try {
			selectRole.click();
			Reporting.updateTestReport("Role is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Role is not selected : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to select Role as Client Admin in Register New User screen for Service Desk User
	 */
	
	public void SDUser_SelectRole() throws IOException {
		try {
			selectRoleSDUser.click();
			Reporting.updateTestReport("Role is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Role is not selected : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click Register Button in Register New User screen for WPS Admin
	 */
	
	public void WPSAdmin_ClickRegister() throws IOException {
		try {
			ClickRegister.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'User has been registered successfully')]")));
			Reporting.updateTestReport("Register button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Register button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description :  Method to click cancel button in Register new client app screen for Client Admin
	 */
	
	public void ClientAdmin_Click_Cancel() throws IOException {
		try {
			ClickCancel.click();
		    Thread.sleep(5000);
			Reporting.updateTestReport("Cancel button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Cancel button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to enter name in Client App Home screen for WPS Admin
	 */
	
	public void WPSAdmin_Enter_ClientApp_HomeName(String name) throws IOException {
		try {
			enterHomeName.sendKeys(name);
			Thread.sleep(2000);
			Reporting.updateTestReport("Name " +name+ " Entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Name is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch name in Client App Home screen for WPS Admin
	 */
	
	public String WPSAdmin_Fetch_ClientApp_HomeName() throws IOException{
	      try {
				String name=fetchHomeName.getText();
				System.out.println(name);
				Reporting.updateTestReport("Name is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return name;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Name is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click edit icon for WPS Admin in home screen
	 */
	
	public void WPSAdmin_ClickEditIcon() throws IOException {
		try {
		    act.moveToElement(ClickEditIcon).click().build().perform();
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Edit Client Application')]")));
			Reporting.updateTestReport("Edit Icon is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Edit Icon is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	

	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch name in Client App edit screen for WPS Admin
	 */
	
	public void WPSAdmin_ClientApp_EditName() throws IOException{
	      try {
				/*String EditName=enterClientAppName.getAttribute(SS_path);
				System.out.println("Old Name: "+EditName);
				String NewName=EditName + "_Edit";
				System.out.println(NewName);*/
				enterClientAppName.sendKeys("_Edit");
				Reporting.updateTestReport("Name is edited successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				//return NewName;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Name is not edited successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				//return "";
			   }
		}
	
	/* 
	 * Author : Jayanta
	 * Description :  Method to click save button in edit client app screen for WPS Admin
	 */
	
	public void WPSAdmin_Click_Save() throws IOException {
		try {
			ClickSave.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Your changes have been saved successfully')]")));
		    driver.findElement(By.xpath("//span[contains(text(),'Your changes have been saved successfully')]")).click();
		    Thread.sleep(5000);
			Reporting.updateTestReport("Save button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Save button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/* 
	 * Author : Jayanta
	 * Description : Method to click Footer for WPS Admin
	 */
	
	public void WPSAdmin_ClickFooter() throws IOException {
		try {
			ClickFooter.click();
			Thread.sleep(5000);
			ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
		    //switch to new tab
		    driver.switchTo().window(newTb.get(1));
		    System.out.println("Page title of new tab: " + driver.getTitle());
		    Reporting.updateTestReport("Footer is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		    driver.close();
		    //switch to parent window
		    driver.switchTo().window(newTb.get(0));
		    System.out.println("Page title of parent window: " + driver.getTitle());
		    
		}
		catch(Exception e){
			Reporting.updateTestReport("Footer is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Varisa
	 * Description :  Method to click Register button in Register new client app screen for WPS Support
	 */
	
	public void WPSSuppport_Click_Register() throws IOException {
		try {
			ClickRegister.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'application has been submitted for registration. Approval pending.')]")));
		    driver.findElement(By.xpath("//span[contains(text(),'application has been submitted for registration. Approval pending.')]")).click();
		    Thread.sleep(5000);
			Reporting.updateTestReport("Register button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Register button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description :  Method to click Register button for error message in Register new client app screen
	 */
	
	public void Click_Register_Error() throws IOException {
		try {
			ClickRegister.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Register New Client Application')]")));
		    Thread.sleep(5000);
			Reporting.updateTestReport("Register button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Register button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to check pagination in Client portal home screen for WPS Admin
	 */
	
	public void WPSAdmin_CheckPagination() throws IOException {
		try {
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
			if (ClickNextPagination.isEnabled())
			Reporting.updateTestReport("Pagination is not working ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			else
				Reporting.updateTestReport("Pagination is working: ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			act.moveToElement(ClickPrevPagination).click().build().perform();
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
		catch(Exception e){
			Reporting.updateTestReport("Pagination is not working: "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click Promote to Prod tab in My worklist for WPS Admin
	 */
	
	public void WPSAdmin_Click_PromoteToProd() throws IOException {
		try {
			Click_PromoteToProd.click();
			Reporting.updateTestReport("Promote to Prod tab is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Promote to Prod tab is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click view icon in My worklist for WPS Admin
	 */
	
	public void WPSAdmin_Click_ViewIcon() throws IOException {
		try {
			//Click_ViewIcon.click();
			act.moveToElement(Click_ViewIcon).click().build().perform();
			Thread.sleep(5000);
			Reporting.updateTestReport("View Icon is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("View Icon is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Varisa
	 * Description :  Method to click Approve button in Approve client app screen for WPS Admin
	 */
	
	public void WPSAdmin_Click_Approve() throws IOException {
		try {
			Click_Approve.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Do you really want to ')]")));
		    driver.findElement(By.xpath("//h2[contains(text(),'Do you really want to ')]")).click();
		    Thread.sleep(5000);
			Reporting.updateTestReport("Approve button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Approve button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description :  Method to click Decline button in Approve client app screen for WPS Admin
	 */
	
	public void WPSAdmin_Click_Decline() throws IOException {
		try {
			Click_Decline.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Do you really want to ')]")));
		    driver.findElement(By.xpath("//h2[contains(text(),'Do you really want to ')]")).click();
		    Thread.sleep(5000);
			Reporting.updateTestReport("Decline button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Decline button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Varisa
	 * Description :  Method to click Confirm button in Approve client app screen for WPS Admin
	 */
	
	public void WPSAdmin_Click_Confirm() throws IOException {
		try {
			Click_Confirm.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'application has been approved')]")));
		    driver.findElement(By.xpath("//span[contains(text(),'application has been approved')]")).click();
		    Thread.sleep(5000);
			Reporting.updateTestReport("Confirm button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Confirm button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description :  Method to click Confirm button in Decline client app screen for WPS Admin
	 */
	
	public void WPSAdmin_Decline_Click_Confirm() throws IOException {
		try {
			Click_Confirm.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'application has been declined')]")));
		    driver.findElement(By.xpath("//span[contains(text(),'application has been declined')]")).click();
		    Thread.sleep(5000);
			Reporting.updateTestReport("Confirm button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Confirm button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	

	/* 
	 * Author : Jayanta
	 * Description :  Method to click Promote button in Promote client app screen for WPS Admin
	 */
	
	public void WPSAdmin_Click_Promote() throws IOException {
		try {
			Click_Approve.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Do you really want to')]")));
		    driver.findElement(By.xpath("//h2[contains(text(),'Do you really want to')]")).click();
			Reporting.updateTestReport("Promote button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Promote button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Varisa
	 * Description :  Method to click Confirm button in Approve client app screen for WPS Admin
	 */
	
	public void WPSAdmin_Click_Promote_Confirm() throws IOException {
		try {
			Click_Confirm.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'application has been initiated')]")));
		    driver.findElement(By.xpath("//span[contains(text(),'application has been initiated')]")).click();
		    Thread.sleep(2000);
			Reporting.updateTestReport("Confirm button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Confirm button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Varisa 
	 * Description : Method to select Role as WPS Admin in Register New User screen for WPS Admin
	 */
	
	public void WPSSupport_SelectWPSSupportRole() throws IOException {
		try {
			selectWPSSupportRole.click();
			Reporting.updateTestReport("Role is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Role is not selected : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Varisa 
	 * Description : Method to fetch error for type field in Register New Client App page.
	 */
	
	public String FetchError_RegisterApp_Type() throws IOException{
	      try {
				String errorType=ClickTypeErrorMessage.getText();
				System.out.println(errorType);
				Reporting.updateTestReport("Error message for type field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorType;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for type field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	
	public String FetchError_RegisterApp_BU() throws IOException{
	      try {
				String errorType=ClickBusinessUnitErrorMessage.getText();
				System.out.println(errorType);
				Reporting.updateTestReport("Error message for Business Unit field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorType;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for Business Unit field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	public String FetchError_RegisterApp_Name() throws IOException{
	      try {
				String errorType=ClickNameErrorMessage.getText();
				System.out.println(errorType);
				Reporting.updateTestReport("Error message for Name field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorType;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for Name field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	public String FetchError_RegisterApp_App_Short_Name() throws IOException{
	      try {
				String errorType=ClickAppShortNameErrorMessage.getText();
				System.out.println(errorType);
				Reporting.updateTestReport("Error message for App short name field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorType;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for App short name field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	
	public String FetchError_RegisterApp_User_ID() throws IOException{
	      try {
				String errorType=ClickUserIdErrorMessage.getText();
				System.out.println(errorType);
				Reporting.updateTestReport("Error message for User ID field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorType;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for User ID field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	
	public String FetchError_RegisterApp_Payment_Method() throws IOException{
	      try {
				String errorType=ClickpaymentsErrorMessage.getText();
				System.out.println(errorType);
				Reporting.updateTestReport("Error message for Payment Method field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorType;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for Payment Method field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click Register Button for negative scenario in Register New User screen
	 */
	
	public void ClickRegister_Negative() throws IOException {
		try {
			ClickRegister.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Register New User')]")));
			Reporting.updateTestReport("Register button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Register button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch error for First name field in Register New User page.
	 */
	
	public String FetchError_RegisterUser_FName() throws IOException{
	      try {
				String errorFName=ClickFNameErrorMessage.getText();
				System.out.println(errorFName);
				Reporting.updateTestReport("Error message for First Name field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorFName;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for First Name field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch error for Last name field in Register New User page.
	 */
	
	public String FetchError_RegisterUser_LName() throws IOException{
	      try {
				String errorLName=ClickLNameErrorMessage.getText();
				System.out.println(errorLName);
				Reporting.updateTestReport("Error message for Last Name field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorLName;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for Last Name field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch error for SSO ID field in Register New User page.
	 */
	
	public String FetchError_RegisterUser_SSOID() throws IOException{
	      try {
				String errorSSOID=ClickSSOIDErrorMessage.getText();
				System.out.println(errorSSOID);
				Reporting.updateTestReport("Error message for SSO ID field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorSSOID;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for SSO ID field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch error for Role field in Register New User page.
	 */
	
	public String FetchError_RegisterUser_Role() throws IOException{
	      try {
				String errorRole=ClickRoleErrorMessage.getText();
				System.out.println(errorRole);
				Reporting.updateTestReport("Error message for Role field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorRole;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for Role field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch error for First Name field Size in Register New User page.
	 */
	
	public String FetchError_RegisterUser_FName_Size() throws IOException{
	      try {
				String errorFNameSize=ClickFNameSizeErrorMessage.getText();
				System.out.println(errorFNameSize);
				Reporting.updateTestReport("Error message for First Name field Size is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorFNameSize;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for First Name field Size is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch error for Last Name field Size in Register New User page.
	 */
	
	public String FetchError_RegisterUser_LName_Size() throws IOException{
	      try {
				String errorLNameSize=ClickLNameSizeErrorMessage.getText();
				System.out.println(errorLNameSize);
				Reporting.updateTestReport("Error message for Last Name field Size is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorLNameSize;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for Last Name field Size is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch error for SSO ID field Size in Register New User page.
	 */
	
	public String FetchError_RegisterUser_SSOID_Size() throws IOException{
	      try {
				String errorSSOIDSize=ClickSSOIDErrorMessage.getText();
				System.out.println(errorSSOIDSize);
				Reporting.updateTestReport("Error message for SSO ID field Size is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorSSOIDSize;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for SSO ID field Size is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click Register Button in Register New User screen for existing user
	 */
	
	public void ClickRegister_ExistingUser() throws IOException {
		try {
			ClickRegister.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'user already exists')]")));
			Reporting.updateTestReport("Register button is clicked successfully and User Exists ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Register button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click revert icon in Home screen for WPS Admin
	 */
	
	public void WPSAdmin_Click_RevertIcon() throws IOException {
		try {
			Click_Revert.click();
			Reporting.updateTestReport("Revert Icon is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Revert Icon is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description :  Method to click Promote button in Promote client app screen for WPS Admin
	 */
	
	public void WPSAdmin_Click_RevertConfirm() throws IOException {
		try {
			Click_Approve.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Do you really want to')]")));
		    driver.findElement(By.xpath("//h2[contains(text(),'Do you really want to')]")).click();
			Reporting.updateTestReport("Confirm button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Confirm button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Varisa
	 * Description :  Method to enter Name in the Name field.
	 */
	
	public String FetchError_RegisterApp_Name1() throws IOException{
	      try {
				String errorType=ClickName1ErrorMessage.getText();
				System.out.println(errorType);
				Reporting.updateTestReport("Error message for Name1 field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorType;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for Name1 field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	
	/* 
	 * Author : Varisa
	 * Description :  Method to enter AppShortName1 in the Name field.
	 */
	
	public String FetchError_RegisterApp_App_Short_Name1() throws IOException{
	      try {
				String errorType=ClickAppShortName1ErrorMessage.getText();
				System.out.println(errorType);
				Reporting.updateTestReport("Error message for AppShortName1 field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorType;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for AppShortName1 field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	/* 
	 * Author : Varisa
	 * Description :  Method to enter Template in the Name field.
	 */
	
	public String FetchError_RegisterApp_Template() throws IOException{
	      try {
				String errorType=ClickTemplateErrorMessage.getText();
				System.out.println(errorType);
				Reporting.updateTestReport("Error message for Template field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorType;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for Template field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
	/* 
	 * Author : Varisa
	 * Description :  Method to enter Target URL in the Name field.
	 */
	
	public String FetchError_RegisterApp_TargetURL() throws IOException{
	      try {
				String errorType=ClickTargetURLErrorMessage.getText();
				System.out.println(errorType);
				Reporting.updateTestReport("Error message for Target URL field is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return errorType;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Error message for Target URL field is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "null";
			   }
		}
}
