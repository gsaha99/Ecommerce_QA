package PageObjectRepo;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import Test_Suite.ClientPortal_RegressionSuite;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.StatusDetails;

public class app_ClientPortal_Repo extends DriverModule {
	
	public String SS_path=ClientPortal_RegressionSuite.SS_path;
	
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
	
	@FindBy(xpath = "//*[local-name()='svg' and @class='fa-icon']/*[local-name()='path']")
    WebElement ClickLogOut;
	
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
			Reporting.updateTestReport("Register New User is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Register New User is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click Register New User for WPS Admin
	 */
	
	public void WPSAdmin_ClickMyWorklist() throws IOException {
		try {
			ClickMyWorklist.click();
			Reporting.updateTestReport("My Worklist is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("My Worklist is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author : Jayanta
	 * Description : Method to click log out for WPS Admin
	 */
	
	public void WPSAdmin_ClickLogOut() throws IOException {
		try {
		
			Actions act = new Actions(driver);
		    act.moveToElement(ClickLogOut).
		    click().build().perform();
			Reporting.updateTestReport("Log out is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Log out is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

}
