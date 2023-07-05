package PageObjectRepo;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestResult;

import TestSuite.CoD_Regression_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;


/**
 * @author shprasad. 
 * @description This class contains objects and methods of login page
 *
 */
public class LoginPageObject {
	
//	LoginOnCheckoutPageAndOnLoginPage loginOnCheckoutPageAndOnLoginPage;
	public String SS_path=CoD_Regression_Test_Suite.SS_path;
	
	private String subscriptionNotPurchasedMessageExpectedTest = "You haven’t purchased a Wiley Chemistry on Demand subscription for your account";
	private String blankEmailFieldExpectedMessage = "Email is required.";
	private String blankPasswordFieldExpectedMessage = "Password does not meet all the requirements.";
	private String invalidEmailAddressExpectedMessage = "Email address is not valid.";
	private String invalidPasswordExpectedMessage = "Password does not meet all the requirements.";
	private String invalidCredentialExpectedMessage = "There was an unexpected problem with your login. Please check your credentials.";

	@FindBy(xpath = "//div[@class='nav-menu']")
	private WebElement navigationMenu;	
	
	@FindBy(xpath = "(//button[@class='user-dropdown__logout-btn'])[1]")
	private WebElement logoutButton;

	@FindBy(xpath = "//button[@class='user-dropdown__login-btn']")
	private WebElement loginButton;

	@FindBy(xpath = "//span[text()='You haven’t purchased a Wiley Chemistry on Demand subscription for your account']")
	private WebElement subscriptionNotPurchasedMessage;

	@FindBy(name = "email")
	private WebElement emailAddress;

	@FindBy(name = "password")
	private WebElement loginpwd;
	
	@FindBy(xpath = "//button[contains(@class,'login-account__submit-btn')]")
	private WebElement loginBtn;
	
	@FindBy(xpath = "(//button[@id='add-to-cart-btn'])[2]")
	private WebElement subscribeButton;
	
	@FindBy(xpath = "//*[text()='Email is required.']")
	private WebElement blankEmailFieldMessage;
	
	@FindBy(xpath = "//*[text()='Password does not meet all the requirements.']")
	private WebElement blankPasswordFieldMessage;
	
	@FindBy(xpath = "//*[text()='Email address is not valid.']")
	private WebElement invalidEmailAddressMessage;
	
	@FindBy(xpath = "//*[text()='Password does not meet all the requirements.']")
	private WebElement invalidPasswordMessage;
	
	@FindBy(xpath = "//*[text()='There was an unexpected problem with your login. Please check your credentials.']")
	private WebElement invalidCredentailMessage;	
	
	@FindBy(xpath = "//div[@class='no-sub__info-wrapper']/a")
	private WebElement testSubscriptionDetailsSubscribeButton;
	
	@FindBy(xpath =  "//h1[text()='Login']")
	private WebElement loginPageVerificationText;
	
	
	public void verifyLoginPageText() throws IOException {
		try {
			String loginPageVerificationActualText = loginPageVerificationText.getText();
			if (loginPageVerificationActualText.equals("Login")) {
				Reporting.updateTestReport("Login page verification message is correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Login page verification message is not correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Login page verification message is not correct" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}	
	
	public void clickOnTestSubscriptionDetailsSubscribeButon() throws IOException {
		try {
			utilities.Helper.click(testSubscriptionDetailsSubscribeButton);
			Reporting.updateTestReport("Click On Test Subscription Details Subscribe Button", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Clicking On Test Subscription Details Subscribe Button is not working", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}	
	}
	
	public void verifyInvalidCredentialMessageText() throws IOException {
		try {
			String invalidCredentailActualMessage = invalidCredentailMessage.getText();
			if (invalidCredentailActualMessage.equals(invalidCredentialExpectedMessage)) {
				Reporting.updateTestReport("Invalid credentials message is correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Invalid credentials message is not correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Invalid credentials message is not correct" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public void enterRandomGenerationEmailForEmailAddressField() throws IOException {		
		try {
			utilities.Helper.enterText(emailAddress, utilities.Helper.generateRandomString()+"@gmail.com");
			utilities.Helper.waitForElementVisibility(emailAddress, 2);
			Reporting.updateTestReport("Generated random email", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);			
		}
		catch(Exception e) {
			Reporting.updateTestReport("Generated random email got failed",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public void enterRandomStringForPasswordAddressField() throws IOException {
		try {
			utilities.Helper.enterText(loginpwd, utilities.Helper.generateRandomString());
			utilities.Helper.waitForElementVisibility(loginpwd, 2);
			Reporting.updateTestReport("Enter random string in passowrd field", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Random string in passowrd field was not entered",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
		
	}
	
	public void enterSymbolsInPasswordField() throws IOException {		
		try {
			utilities.Helper.enterText(loginpwd, utilities.Helper.generateRandomSymbols());
			utilities.Helper.waitForElementVisibility(loginpwd, 2);
			Reporting.updateTestReport("Enter symbols in passowrd field", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Enter symbols in passowrd field got failed"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
	}
	
	public void enterAlphabetsInPassswordField() throws IOException {
		try {
			utilities.Helper.enterText(loginpwd, utilities.Helper.generateRandomAlphabets());
			utilities.Helper.waitForElementVisibility(blankPasswordFieldMessage, 2);
			Reporting.updateTestReport("Enter alphabets in passowrd field", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Enter alphabets in passowrd field got failed"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	public void enterNumbersInPasswordField() throws IOException {
		try {
			utilities.Helper.enterText(loginpwd, utilities.Helper.generateRandomNumbersLessThanFive());
			utilities.Helper.waitForElementVisibility(loginpwd, 2);
			Reporting.updateTestReport("Enter numbers in passowrd field", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Enter numbers in passowrd field got failed"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
		
	}
	
	public void enterSymbolsInEmailAddressField() throws IOException {
		try {
			utilities.Helper.enterText(emailAddress, utilities.Helper.generateRandomSymbols());
			utilities.Helper.waitForElementVisibility(invalidEmailAddressMessage, 2);
			Reporting.updateTestReport("Enter symbols in email address field", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Enter symbols in email address field got failed"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
	}
	
	public void enterAlphabetsInEmailAddressField() throws IOException {
		try {
			utilities.Helper.enterText(emailAddress, utilities.Helper.generateRandomAlphabets());
			utilities.Helper.waitForElementVisibility(invalidEmailAddressMessage, 2);
			Reporting.updateTestReport("Enter alphabets in email address field", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Enter alphabets in email address field got failed"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
	}
	
	public void enterNumbersInEmailAddressField() throws IOException {
		try {
			utilities.Helper.enterText(emailAddress, utilities.Helper.generateRandomNumbersLessThanFive());
			utilities.Helper.waitForElementVisibility(invalidEmailAddressMessage, 2);
			Reporting.updateTestReport("Enter numbers in email address field", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Enter numbers in email address field got failed"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
	}
	
	public void verifyInvalidPasswordMessageText() throws IOException {				
		try {
			String invalidPasswordActualMessage = invalidPasswordMessage.getText();
			if (invalidPasswordActualMessage.equals(invalidPasswordExpectedMessage)) {
				Reporting.updateTestReport("Invalid password message text is correct", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);				
			} else {
				Reporting.updateTestReport("Invalid password message text is not correct", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Invalid password message text is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);			
		}		
	}
	
	public void verifyInvalidEmailAddressMessageText() throws IOException {		
		try {
			String invalidEmailAddressActualMessage = invalidEmailAddressMessage.getText();
			if (invalidEmailAddressActualMessage.equals(invalidEmailAddressExpectedMessage)) {
				Reporting.updateTestReport("Invalid email address message text is correct", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);				
			} else {
				Reporting.updateTestReport("Invalid email address text is not correct", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Invalid email address text is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);			
		}		
	}
	
	public void verifyBlankPasswordFieldMessageText() throws IOException {
		try {
			String blankPasswordFieldActualMessage = blankPasswordFieldMessage.getText();
			if (blankPasswordFieldActualMessage.equals(blankPasswordFieldExpectedMessage)) {
				Reporting.updateTestReport("blank password message text is correct", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);				
			} else {
				Reporting.updateTestReport("blank password message text is in-correct", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}
		} catch (Exception e) {
			Reporting.updateTestReport("blank password message text is in-correct"+e.getClass().toString(), 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);			
		}
	}
	
	public void verifyEmailAddressBlankMessageText() throws IOException {
		try {
			String blankEmailFieldActualMessage = blankEmailFieldMessage.getText();	
			if(blankEmailFieldActualMessage.equals("Blank field Email Address is not correct")) {
				Reporting.updateTestReport("Blank email message text is correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Blank email message text is in-correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}		
		} catch (Exception e) {
			Reporting.updateTestReport("Blank email message text is in-correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);			
		}		
	}
	
	public void clickOnPasswordInputField() throws InterruptedException, IOException {
		try {			
				Thread.sleep(2000);			
				utilities.Helper.click(loginpwd);
				Reporting.updateTestReport("Password input field is clicked", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
			Reporting.updateTestReport("Password input field is not clicked"+e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	public void clickOnEmailInputField() throws InterruptedException, IOException {		
		try {		
			utilities.Helper.click(emailAddress);
			Thread.sleep(2000);
			Reporting.updateTestReport("Email input field is clicked", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);			
		} catch (Exception e) {
			Reporting.updateTestReport("Email input field is not clicked"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);			
		}	
	}

	public void clickOnLogoutButton() throws IOException {
		try {
			utilities.Helper.click(logoutButton);
			Reporting.updateTestReport("Logout button is clicked", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Logout button is not clicked", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}
	
	public boolean clickOnSubscribeButton() throws IOException {
		try {
			if (subscribeButton.isDisplayed()) {
				utilities.Helper.click(subscribeButton);
				Reporting.updateTestReport("Subscribe button is clicked", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			} else {
				Reporting.updateTestReport("Subscribe button is not clicked", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Subscribe button is not clicked", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
		
	}
	
	
	public void clickOnLoginButton() throws IOException {	
		try {
			 utilities.Helper.scrollWindow(loginBtn);
			  utilities.Helper.click(loginBtn);	
			  Reporting.updateTestReport("Click on login button", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Click on login button"+e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}			 		
	}
	
	public boolean enterpassword() throws IOException {
		try {
			if (loginpwd.isDisplayed()) {			
				utilities.Helper.enterText(loginpwd, excelOperation.getTestData("Password", "CoD_Test_Data", "Test_Data"));
				Reporting.updateTestReport("Enter password", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			} else {		
				Reporting.updateTestReport("Enter password got failed", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Enter password got failed"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}		
	}
	
	public void enterPassword() throws IOException { 
		try {
			utilities.Helper.enterText(loginpwd, excelOperation.getTestData("Password", "CoD_Test_Data", "Test_Data"));
			Reporting.updateTestReport("Enter password", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Enter password got failed", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	public void enterEmailAddress() throws IOException { 
		try {
			utilities.Helper.enterText(emailAddress, excelOperation.getTestData("EmailID", "CoD_Test_Data", "Test_Data"));
			Reporting.updateTestReport("Enter email Address", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Enter Address was not entered", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
		
	}
	
	public boolean enterEmailID() throws IOException {	
		try {
			if (emailAddress.isDisplayed()) {			
				utilities.Helper.enterText(emailAddress, excelOperation.getTestData("EmailID", "CoD_Test_Data", "Test_Data"));
				Reporting.updateTestReport("Enter email Address", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			} else {			
				Reporting.updateTestReport("Enter email Address got failed", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Enter email Address got failed", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}		
	}
	
	public void clickOnNavigationMenu() throws IOException {
		try {
			utilities.Helper.click(navigationMenu);
			Reporting.updateTestReport("Click on Navigation Menu", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Navigation menu is not clicked" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public void selectLoginButton() throws IOException {	
		try {
			utilities.Helper.click(loginButton);
			Reporting.updateTestReport("Select Login button", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Login button is not selected", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
	}
	
	public void veifyLoginButtonIsDisabled() throws IOException {
		try {
			if (!loginBtn.isEnabled()) {
				Reporting.updateTestReport("Login button is disabled", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);				
			} else {
				Reporting.updateTestReport("Login button is disabled", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}
		} catch (Exception e) {
			// TODO: handle exception
			Reporting.updateTestReport("Login button is not present"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);		
		}		
	}	

	public boolean verifyLoginPage() throws IOException {
		try {
			String subscriptionNotPurchasedMsg = subscriptionNotPurchasedMessage.getText();
			int index = subscriptionNotPurchasedMsg.lastIndexOf(" ");
			String subscriptionNotPurchasedMsgActualMessage = subscriptionNotPurchasedMsg.substring(0, index);
			if (subscriptionNotPurchasedMsgActualMessage.equals(subscriptionNotPurchasedMessageExpectedTest)) {
				Reporting.updateTestReport("Login page is visible", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
				return true;
			} else {
				Reporting.updateTestReport("Login page is not visible", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
				return false;
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Login page is not visible"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}
	
}
