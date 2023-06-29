package PageObjectRepo;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestSuite.CoD_Regression_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

public class AccountLoginPageObject {
	
	public String SS_path=CoD_Regression_Test_Suite.SS_path;
	
	private String SubscriptionCheckoutExpectedText = "Subscription Checkout";
	private String SubscriptionAccountExpectedText = "Subscription Account";
	private String AccountLoginExpectedText = "Account Login1";
	
	
	@FindBy(xpath = "//span[@class='header__subheading']")
	private WebElement subscriptionCheckoutText;	
	
	@FindBy(xpath = "//span[text()='Account Login']")
	private WebElement accountLoginText;
	
	@FindBy(name = "email")
	private WebElement emailAddress;

	@FindBy(name = "password")
	private WebElement loginpwd;
	
	@FindBy(xpath = "//button[contains(@class,'login-account__submit-btn')]")
	private WebElement loginBtn;
	
	@FindBy(xpath = "//span[text()='Subscription Account']")
	private WebElement subscriptionAccountText;
	
	/*Need to remove this in aother page*/
	@FindBy(xpath = "//button[@class='checkout__edit-btn']")
	private WebElement editButtonForSubscriptionCheckoutPage;
	
		
	public void clickOnEditButton() {
		utilities.Helper.scrollWindow(editButtonForSubscriptionCheckoutPage);
		utilities.Helper.click(editButtonForSubscriptionCheckoutPage);
	}
	
	public void enterEmailAddress(String emailAddressText) throws IOException {
		try {
			utilities.Helper.enterText(emailAddress, emailAddressText);
			Reporting.updateTestReport("Email address is entered",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Email address is not entered"+ e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}				
	}
		
	public void enterPassword(String passwordText) throws IOException {
		try {
			utilities.Helper.enterText(loginpwd, passwordText );
			Reporting.updateTestReport("passowrd is entered",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("password is not entered"+ e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}		
	}
	
	public boolean verifyAccountLoginPage() throws IOException {
		try {
			String accountLoginActualText = accountLoginText.getText();
			Reporting.updateTestReport("Account login page verified",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return true;
		} catch (Exception e) {
			Reporting.updateTestReport("Account login page is not present" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		return false;
	}
	
	public boolean verifySubscriptionCheckoutPage() {
		String subscriptionCheckoutActualText = subscriptionCheckoutText.getText();
		if (subscriptionCheckoutActualText.equals(SubscriptionCheckoutExpectedText)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void verifySubscriptionAccountText() throws IOException {
		try {
			String subscriptionAccountActualText = subscriptionAccountText.getText();
			if (subscriptionAccountActualText.equals(SubscriptionAccountExpectedText)) {
				Reporting.updateTestReport("Subscrition account text is correct",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Subscrition account text is not correct",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Subscrition account text is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
		
		
		
	}
	
	public void veifyLoginButtonIsDisabled() throws IOException {
		try {
			if (!loginBtn.isEnabled()) {
				 Reporting.updateTestReport("Login button is disabled",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				 Reporting.updateTestReport("Login button is enabled",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Login button is enabled"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public void clickOnLoginButton() throws InterruptedException, IOException {	
		try {
			utilities.Helper.scrollWindow(loginBtn);
			Thread.sleep(2000);
			utilities.Helper.click(loginBtn);
			Reporting.updateTestReport("Login button is clicked", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Login button is not clicked" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public boolean enterpassword() {	
		if (loginpwd.isDisplayed()) {
			utilities.Helper.enterText(loginpwd, excelOperation.getTestData("Password", "CoD_Test_Data", "Test_Data"));			
			return true;
		} else {			
			return false;
		}
	}
	
	
	public boolean enterEmailID() {	
		if (emailAddress.isDisplayed()) {			
			utilities.Helper.enterText(emailAddress, excelOperation.getTestData("EmailID", "CoD_Test_Data", "Test_Data"));
			return true;
		} else {			
			return false;
		}
	}

}
