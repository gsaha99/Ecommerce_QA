package PageObjectRepo;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestResult;

import TestSuite.CoD_Regression_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.Reporting;
import utilities.StatusDetails;


/**
 * @author mkaushik. 
 * @description This class contains objects and methods of Registration Page
 *
 */
public class CreateAccountPageObject {
	
	public String SS_path = CoD_Regression_Test_Suite.SS_path;
		
	private String headerPageExpectedTest = "Wiley";

	@FindBy(xpath = "(//*[@id=\"add-to-cart-btn\"])[2]")
	private WebElement subscribeButton;	

	@FindBy(css = ".create-account-btn")
	private WebElement createANewButtonLink;
	
	@FindBy(xpath = "//span[text()='Create Account']")
	private WebElement createAccountHeader;
	
	@FindBy(name = "firstName")
	private WebElement firstName;
	
	@FindBy(name = "lastName")
	private WebElement lastName;
	
	@FindBy(name = "email")
	private WebElement emailAddress;
	
	@FindBy(name = "confirmEmail")
	private WebElement confirmEmailAddress;
	
	@FindBy(name = "password")
	private WebElement password;
	
	@FindBy(name = "confirmPassword")
	private WebElement confirmPassword;
	
	@FindBy(css = ".checkbox-wrapper > label")
	private WebElement offersCheckbox;
	
	@FindBy(css = "#continue-to-payment-btn")
	private WebElement createAccountButton;
	
	@FindBy(xpath = "//*[text()='Your account was successfully created and you have been logged in.']")
	private WebElement getSuccessMessage;
	
	@FindBy(css = ".firstName-field > span")
	private WebElement firstNameFieldRequiredValidation;
	
	@FindBy(css = ".lastName-field > span")
	private WebElement lastNameFieldRequiredValidation;
	
	@FindBy(css = ".email-field > span")
	private WebElement emailFieldRequiredValidation;
	
	@FindBy(css = ".confirmEmail-field > span")
	private WebElement confirmEmailFieldRequiredValidation;
	
	@FindBy(css = ".password-field > span")
	private WebElement passwordFieldRequiredValidation;
	
	@FindBy(css = ".confirmPassword-field > span")
	private WebElement confirmPasswordFieldRequiredValidation;
	
	@FindBy (xpath = "//button[@class='existing-login-btn']")
	private WebElement logIntoExistingAccountLink;
	
	@FindBy(css = ".checkout__edit-btn")
	private WebElement editButton;
		
	
	

	public void onTestStart(ITestResult result) {
		try {
			utilities.Helper.takeScreenShot(result.getMethod().getMethodName());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
	  }

	public void clickOnEditButton() throws InterruptedException, IOException {
		try {
			Thread.sleep(3000); 
			utilities.Helper.click(editButton);			 
			 Reporting.updateTestReport("Click on edit button",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			 
		} catch (Exception e) {
			// TODO: handle exception
			Reporting.updateTestReport("Click on edit button is not working"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}	
	}
	
	public void clickOnLogIntoExistingAccount() throws IOException {
		try {
			utilities.Helper.scrollWindow(logIntoExistingAccountLink);
            utilities.Helper.click(logIntoExistingAccountLink);
            Reporting.updateTestReport("Click on Log into existing button",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			// TODO: handle exception
			Reporting.updateTestReport("Clicking on Log into existing button is not working"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public boolean clickOnSubscribeButton() {    
        if (subscribeButton.isDisplayed()) {            
             utilities.Helper.click(subscribeButton);  
             utilities.Helper.waitForElementVisibility(createANewButtonLink, 40);
            return true;
        } else {            
            return false;
        }
    }
	
	public void clickOnCreateANewAccountLink() throws IOException {
		try {
			if(createANewButtonLink.isDisplayed()) 
				utilities.Helper.click(createANewButtonLink);
				Reporting.updateTestReport("Create a New Button Link is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);			
		}
		catch(Exception e) {
			Reporting.updateTestReport("Create a New Button Link is not present"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
	}		
	
	public String getPageTextCreateAccount() throws IOException {
		try {
		utilities.Helper.waitForElementVisibility(createAccountHeader);
		String text = createAccountHeader.getText();
		Reporting.updateTestReport("Get Page Text Create Account is present",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		return text;
	}
	catch(Exception e){
		Reporting.updateTestReport("Get Page Text Create Account is not present "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		return "";
	}
	}
	
	public void creationOfAccount(String fName, String lName, String emailAddressText, String passwordText) 
			throws IOException , InterruptedException {
		try {
		utilities.Helper.waitForElementVisibility(firstName, 10);
		utilities.Helper.enterText(firstName, fName);
		Reporting.updateTestReport("First Name: "+fName+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		utilities.Helper.enterText(lastName, lName);
		Reporting.updateTestReport("Last Name: "+lName+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		utilities.Helper.enterText(emailAddress, emailAddressText);
		Reporting.updateTestReport("Email Text: "+emailAddressText+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		utilities.Helper.enterText(confirmEmailAddress, emailAddressText);
		Reporting.updateTestReport("Confirm Email Text: "+emailAddressText+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		utilities.Helper.enterText(password, passwordText );
		Reporting.updateTestReport("Password Text: "+passwordText+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		utilities.Helper.enterText(confirmPassword, passwordText);
		Reporting.updateTestReport("Confirm Password Text: "+passwordText+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		utilities.Helper.scrollWindow(offersCheckbox);
        utilities.Helper.click(offersCheckbox);
        Reporting.updateTestReport("Offer checkbox is clicked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        Thread.sleep(3000);
        utilities.Helper.click(createAccountButton);
        Reporting.updateTestReport("Create Account Button is clicked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch(Exception e){
			Reporting.updateTestReport("Account is not created successfully"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public String enterFirstName(String fName) throws IOException {	
		try {
			utilities.Helper.waitForElementVisibility(firstName, 10);
			utilities.Helper.enterText(firstName, fName);
			Reporting.updateTestReport("First Name: "+fName+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return fName;
		}
		catch(Exception e){
			Reporting.updateTestReport("First Name was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	public String enterLastName(String lName) throws IOException {
		try {
		utilities.Helper.enterText(lastName, lName);
		Reporting.updateTestReport("Last Name: "+lName+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		return lName;
		}
	catch(Exception e){
		Reporting.updateTestReport("Last Name: was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		return "";
	    }
	}
	
	public String enterEmailAddress(String emailAddressText) throws IOException { 
		try {
		utilities.Helper.enterText(emailAddress, emailAddressText);
		Reporting.updateTestReport("Email Address "+emailAddressText+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		return emailAddressText;
		}
	catch(Exception e){
		Reporting.updateTestReport("Email Address: was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		return "";
	    }		
	}
	
	public String enterConfirmEmailAddress(String confirmEmailAddressText) throws IOException { 
		try {
		utilities.Helper.enterText(confirmEmailAddress, confirmEmailAddressText);
		Reporting.updateTestReport("Confirm Email Address "+confirmEmailAddressText+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		return confirmEmailAddressText;
		}
	catch(Exception e){
		Reporting.updateTestReport("Confirm Email Address: was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		return "";
	    }
	}
	
	public String enterPassword(String passwordText) throws IOException { 
		try {
		utilities.Helper.enterText(password, passwordText);
		Reporting.updateTestReport("Password "+passwordText+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		return passwordText;
		}
	catch(Exception e){
		Reporting.updateTestReport("Password: was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		return "";
	    }
	}
	
	public String enterConfirmPassword(String confirmPasswordText) throws IOException { 
		try {
		utilities.Helper.enterText(confirmPassword, confirmPasswordText);
		Reporting.updateTestReport("Confirm Password "+confirmPasswordText+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		return confirmPasswordText;
		}
	catch(Exception e){
		Reporting.updateTestReport("Confirm Password: was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		return "";
	    }
	}
	
	
	public boolean clickOnOffersCheckBox() {    
        if (offersCheckbox.isDisplayed()) {   
        	utilities.Helper.scrollWindow(offersCheckbox);
             utilities.Helper.click(offersCheckbox);             
            return true;
        } else {            
            return false;
        }
    }
	
	public boolean clickOnCreateAccountButton() throws InterruptedException {    
        if (createAccountButton.isDisplayed()) { 
        	Thread.sleep(5000);
             utilities.Helper.click(createAccountButton);             
            return true;
        } else {            
            return false;
        }
    }
	
	public void getSuccessMessageForAccountCreation() throws IOException { 
		try {
			utilities.Helper.waitForElementVisibility(getSuccessMessage);
			String successMsg = getSuccessMessage.getText();	
			if(successMsg.equals("Your account was successfully created and you have been logged in.")) {
				Reporting.updateTestReport("Success message for account creation is correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Success message for account creation is not correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}		
		} catch (Exception e) {
			Reporting.updateTestReport("Success message for account creation is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	
	public String verifyFirstNameFieldValidation() throws IOException {
		try {
			String firstName = firstNameFieldRequiredValidation.getText();
			Reporting.updateTestReport("First Name Field Validation is present",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return firstName;
		}
		catch(Exception e){
			Reporting.updateTestReport("First Name Field Validation is not present "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}		
	}
	
	public String verifyLastNameFieldValidation() throws IOException {	
		try {
		String lastName = lastNameFieldRequiredValidation.getText();
		Reporting.updateTestReport("Last Name Field Validation is present",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		return lastName;
	}
	catch(Exception e){
		Reporting.updateTestReport("Last Name Field Validation is not present "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		return "";
	}			
	}
	
	public String verifyEmailFieldValidation() throws IOException {	
		try {
		String emailField = emailFieldRequiredValidation.getText();
		Reporting.updateTestReport("Email Field Validation is present",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		return emailField;
	}
	catch(Exception e){
		Reporting.updateTestReport("Email Field Validation is not present "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		return "";
	}		
	}
	
	public String verifyConfirmEmailFieldValidation() throws IOException {	
		try {
		String confirmEmailField = confirmEmailFieldRequiredValidation.getText();
		Reporting.updateTestReport("Confirm Email Field Validation is present",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		return confirmEmailField;
	}
	catch(Exception e){
		Reporting.updateTestReport("Confirm Email Field Validation is not present "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		return "";
	}			
	}
	
	public String verifyPasswordFieldValidation() throws IOException {	
		try {
		String passwordField = passwordFieldRequiredValidation.getText();
		Reporting.updateTestReport("Password Field Validation is present",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		return passwordField;
	}
	catch(Exception e){
		Reporting.updateTestReport("Password Field Validation is not present "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		return "";
	}		
	}
	
	public String verifyConfirmPasswordFieldValidation() throws IOException {	
		try {
		String confirmPasswordField = confirmPasswordFieldRequiredValidation.getText();		
		Reporting.updateTestReport("Confirm Password Field Validation is present",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		return confirmPasswordField;
	}
	catch(Exception e){
		Reporting.updateTestReport("Confirm Password Field Validation is not present "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		return "";
	}			
	}	

}
