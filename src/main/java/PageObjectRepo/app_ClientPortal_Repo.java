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
	
	@FindBy(xpath = "//*[local-name()='svg' and @class='fa-icon']/*[local-name()='path']")
    WebElement ClickLogOut;
	
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
	
	@FindBy(name="name")
    WebElement enterClientAppName;
	
	@FindBy(name="appShortName")
    WebElement enterClientAppShortName;
	
	@FindBy(xpath = "//option[@value='690']")
    WebElement selectUserID;
	
	@FindBy(xpath = "//span[contains(text(),'Card Payment')]")
   	WebElement ClickPaymentMethod;
	
	@FindBy(name="Debit/Credit Card")
    WebElement selectDebitCard;
	
	@FindBy(xpath = "//button[contains(text(),'Add')]")
	WebElement ClickAdd;
	
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
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginHeader")));
			Reporting.updateTestReport("Log out is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Log out is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click payment method as card payment in add new client app screen for WPS Admin
	 */
	
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
	 * Author : Jayanta
	 * Description :  Method to click add button in add new client app screen for WPS Admin
	 */
	
	public void WPSAdmin_Click_Add() throws IOException {
		try {
		    act.moveToElement(ClickAdd).click().build().perform();
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
			enterLastName.sendKeys(lname);
			Reporting.updateTestReport("Last Name " +lname+ " Entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Last Name is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	

}
