package PageObjectRepo;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import TestSuite.CoD_Regression_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

public class SubscriptionAccountPageObject {
	
	public String SS_path=CoD_Regression_Test_Suite.SS_path;	
	
	@FindBy(name = "CCname")
	private WebElement cardName;
	
	@FindBy(name = "address1")
	private WebElement addressLine1;
	
	@FindBy(name = "city")
	private WebElement city;
	
	@FindBy(name = "zip")
	private WebElement zipCode;
	
	@FindBy(name = "phone")
	private WebElement phoneNumber;
	
	@FindBy(name = "state")
	private WebElement stateName;
	
	@FindBy(css = ".checkbox-unchecked")
	private WebElement monthSubscriptionFeeCheckbox;
	
	@FindBy(css = "#continue-to-payment-btn")
	private WebElement securePaymentButton;
	
	@FindBy(xpath = "//div[@class='order-summary-desktop']/div/div/div/div/span[@class='order-summary__item-tax--right-bold']")
    private WebElement vat;

    @FindBy(xpath = "//div[@class='order-summary-desktop']/div/div/div/div/span[@class='order-summary__item-total--right-bold']")
    private WebElement recurringMonthlyBill;
    
    @FindBy(xpath = "//p[text()='Your card number is incomplete.']")
    private WebElement inCompleteCardNumberFieldValidation;

    @FindBy(xpath = "//p[text()='Your card number is invalid.']")
    private WebElement inValidCardNumberFieldValidation;
	
	@FindBy(xpath = "//span[text()='First Name is required.']")
	private WebElement creditCardErrorMessageForBlankField;
	
	@FindBy(xpath = "//*[text()='Address Line 1 is required.']")
	private WebElement addressLine1RequiredErrorMessageForBlankField;
	
	@FindBy(xpath = "//*[text()='Please specify your City / Town.']")
	private WebElement cityTownErrorMessageForBlankField;

	@FindBy(xpath = "//*[text()='Postal Code / Postcode / ZIP Code is required.']")
	private WebElement zipCodeErrorMessageForBlankField;
	
	@FindBy(xpath = "//*[text()='Your phone number is required.']")
	private WebElement phoneNumberErrorMessageForBlankField;
	
	@FindBy(xpath = "//*[text()='First Name must contain only letters, apostrophes or dashes.']")
	private WebElement creditCardErrorMessage;
	
	@FindBy(xpath = "//*[text()='Minimum address length is 4 characters.']")
	private WebElement addressLine1RequiredErrorMessage;
	
	@FindBy(xpath = "//*[text()='Please enter your phone number in the following format: +XX XX XXXXXXXX']")
	private WebElement phoneNumberErrorMessage;
	
	@FindBy(name = "cardnumber")
    private WebElement cardNumber;
	
	@FindBy(id = "Field-numberInput")
    private WebElement cardNumber1;
	
	@FindBy(xpath = "//iframe[contains(@name,'privateStripeFrame')]")
	private WebElement securePaymentFrame;
    
    @FindBy(name = "expiry")
    private WebElement expDate;
    
    @FindBy(name = "cvc")
    private WebElement cvv;
    
    @FindBy(css = "#submit")
    private WebElement submitOrderButton;
    
    @FindBy(xpath = "//*[contains(text(),'expiration date is incomplete.')]")
    private WebElement inCompleteExpiryDateFieldValidation;
    
    @FindBy(xpath = "//*[contains(text(),'expiration year is in the past.')]")
    private WebElement inValidExpiryDateFieldValidation;
    
    @FindBy(xpath = "//*[contains(text(),'security code is incomplete')]")
    private WebElement inCompleteCVVFieldValidation;
    
    @FindBy(xpath = "//button[@class='add-address-btn']")
    private WebElement addressLine2;
    
    @FindBy(css = ".checkout__steps > span:nth-of-type(6)")
    private WebElement errorMessageForCards;    
    
    @FindBy(xpath = "(//iframe[contains(@name,'privateStripeFrame')])[1]")
    private WebElement securePaymentFrame3D;
       
    @FindBy(xpath = "//button[@class='LightboxModalClose']")
    private WebElement securePayment3D_CancelButton;
    
    @FindBy(xpath = "//iframe[@id='challengeFrame']")
    private WebElement challengeFrame;
    
    @FindBy(xpath = "//iframe[@name='acsFrame']")
    private WebElement fullScreenFrame;
    
    @FindBy(id = "test-source-fail-3ds")
    private WebElement failAuthentication;
  
    @FindBy(id = "test-source-authorize-3ds")
    private WebElement completeAuthentication;
    
    @FindBy(xpath = "(//span[@class='payment-form__error-msg'])[2]")
    private WebElement failMessage3d;
    
    public void onTestStart(ITestResult result) {
		try {
			utilities.Helper.takeScreenShot(result.getMethod().getMethodName());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
	  }
    
    public void verify3DErrorMessage() throws IOException {
    	try {
    		String errormsg = failMessage3d.getText();
			if(errormsg.equals("We are unable to authenticate your payment method. Please choose a different payment method and try again.")) {
				Reporting.updateTestReport("3D message is correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}else {
					Reporting.updateTestReport("3D message is not correct",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		} 
    	}catch (Exception e) {
			Reporting.updateTestReport("3D message is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
    }
    
 
    public void clickOnCancelButtonOn3DSecurePopUp(WebDriver driver) throws InterruptedException, IOException {
		try {
			driver.switchTo().frame(securePaymentFrame3D);		
			utilities.Helper.click(securePayment3D_CancelButton);	
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));	
			Reporting.updateTestReport("Clicked on cancel button for 3d secure pop up",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Clicked on cancel button for 3d secure pop up"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
	}
		
	public void verifyPhoneNumberErrorMessage() throws IOException {	
		try {
			String errormsg = phoneNumberErrorMessage.getText();
			if(errormsg.equals("Please enter your phone number in the following format: +XX XX XXXXXXXX")) {
				Reporting.updateTestReport("Phone number message is correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Phone number message is not correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Phone number message is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
	}
	
	public void verifyAddressLine1ErrorMessage() throws IOException {	
		try {
			String errormsg = addressLine1RequiredErrorMessage.getText();
			if(errormsg.equals("Minimum address length is 4 characters.")) {
				Reporting.updateTestReport("verified AddressLine1 Error Message",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Address line 1 message is not verified",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Address line 1 message is not verified"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	public void verifyCreditCardErrorMessage() throws IOException {	
		try {
			String errormsg = creditCardErrorMessage.getText();
			if(errormsg.equals("First Name must contain only letters, apostrophes or dashes.")) {
				Reporting.updateTestReport("Credit card error message is correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Credit card error message is not correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Credit card error message is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}			
	}
	
	public void enterInvalidNumbersInPhoneNumberField() throws IOException {
		try {
			utilities.Helper.enterText(phoneNumber, utilities.Helper.generateRandomNumbersLessThanFive());
			Reporting.updateTestReport("Enter Invalid Numbers in Phone Number Field",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Not able to enter Invalid Numbers in Phone Number Field"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
				
	}
	
	public void enterValidNumbersInPhoneNumberField() throws IOException {
		try {
			utilities.Helper.enterText(phoneNumber, utilities.Helper.generateRandomNumbers());
			Reporting.updateTestReport("Phone Number was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Phone Number was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}			
	}
	
	public void enterThreeCharInAddressLine1Field() throws IOException {
		try {
			utilities.Helper.enterText(addressLine1, excelOperation.getTestData("addressLine1_3char", "CoD_Test_Data", "Test_Data"));
			Reporting.updateTestReport("Enter three characters in address line field ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Not able to enter three characters in address line field"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public void enterAlphaNumericInZipCodeField() throws IOException {
		try {
			utilities.Helper.enterText(zipCode, utilities.Helper.generateRandomNumbersLessThanFive());
			Reporting.updateTestReport("ZipCode was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("ZipCode was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}				
	}
	
	public void enterTextInStateField() {
		utilities.Helper.enterText(stateName, utilities.Helper.generateRandomAlphaNumericChar());		
	}
	
	public void enterAlphaNumericInCityTownField() throws IOException {
		try {
			utilities.Helper.enterText(city, utilities.Helper.generateRandomAlphaNumericChar());
			Reporting.updateTestReport("City:was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("City was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
	}
	
	public void clickOnAddressLine2Field() {
		utilities.Helper.click(addressLine2);
	}
	
	public void enterAlphaNumericInAddressLine2Field() {
		utilities.Helper.enterText(addressLine2, utilities.Helper.generateRandomAlphaNumericChar());		
	}
	
	public void enterAlphaNumericInAddressLine1Field() throws IOException {
		try {
			utilities.Helper.enterText(addressLine1, utilities.Helper.generateRandomAlphaNumericChar());
			Reporting.updateTestReport("Address Line1:was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Address Line1 was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}				
	}
	
	public void enterAlphaNumericInCreditCardField() throws IOException {
		try {
			utilities.Helper.enterText(cardName, utilities.Helper.generateRandomAlphaNumericChar());
			Reporting.updateTestReport("Enter aplhanumeric in credit card field",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Not able to enter aplhanumeric in credit card field "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
				
	}
	
	public void enterAlphabetsInCreditCardField() throws IOException {
		try {
			utilities.Helper.enterText(cardName, utilities.Helper.generateRandomAlphabets());
			Reporting.updateTestReport("Card Name:was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Card Name was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}				
	}
	
	public void enterNumbersInCreditCardField() throws IOException {
		try {
			utilities.Helper.enterText(cardName, utilities.Helper.generateRandomNumbersLessThanFive());
			Reporting.updateTestReport("Enter numbers in credit card field",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Not able to enter numbers in credit card field"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
				
	}
	
	public void enterSymbolsInCreditCardField() throws IOException {
		try {
			utilities.Helper.enterText(cardName, utilities.Helper.generateRandomSymbols());
			Reporting.updateTestReport("Enter symbols in credit card field",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Not able to enter symbols in credit card field"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}				
	}
	
	public void verifyPhoneNumberErrorMessageForBlankField() throws IOException {	
		try {
			String errormsg = phoneNumberErrorMessageForBlankField.getText();
			if(errormsg.equals("Your phone number is required.")) {
				Reporting.updateTestReport("Phone number message is correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Phone number message is not correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Phone number message is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}			
	}
	
	public void verifyZipCodeErrorMessageForBlankField() throws IOException {	
		try {
			String errormsg = zipCodeErrorMessageForBlankField.getText();
			if(errormsg.equals("Postal Code / Postcode / ZIP Code is required.")) {
				Reporting.updateTestReport("Postal Code / Postcode / ZIP Code error message is correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Postal Code / Postcode / ZIP Code error message is not correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Postal Code / Postcode / ZIP Code error message is not correct",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
	}
	
	public void verifyCityTownErrorMessageForBlankField() throws IOException {	
		try {
			String errormsg = cityTownErrorMessageForBlankField.getText();
			if(errormsg.equals("Please specify your City / Town.")) {
				Reporting.updateTestReport("City / Town error message is correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("City / Town error message is not correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}
		} catch (Exception e) {
			Reporting.updateTestReport("City / Town error message is not correct",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public void verifyAddressLine1RequiredErrorMessageForBlankField() throws IOException {	
		try {
			String errormsg = addressLine1RequiredErrorMessageForBlankField.getText();
			if(errormsg.equals("Address Line 1 is required.")) {
				Reporting.updateTestReport("Address Line 1 message is correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Address Line 1 message is not correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Address Line 1 message is not correct",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}			
	}
	
	public void verifyCreditCardErrorMessageForBlankField() throws IOException {		
		try {
			String errormsg = creditCardErrorMessageForBlankField.getText();
			if(errormsg.equals("First Name is required.")) {
				Reporting.updateTestReport("Credit card error message for blank field is correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Credit card error message for blank field is not correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);				
			}
		} catch (Exception e) {
			// TODO: handle exception
			Reporting.updateTestReport("Credit card error message for blank field is not correct",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
	}
	
	public void clickOnStateInputBox() {
		utilities.Helper.click(stateName);
	}
	
	public void clickOnZipCodeInputBox() {
		utilities.Helper.click(zipCode);
	}
	
	public void clickOnPhoneNumberInputBox() {
		utilities.Helper.click(phoneNumber);
	}
	
	public void clickOnAddressLine1InputBox() throws IOException {
		try {
			utilities.Helper.click(addressLine1);
			Reporting.updateTestReport("Click on Address line 1 Input box",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Click  is not happening on Address line 1 Input box"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
	}

	public void clickOnCityTownInputBox() {
		utilities.Helper.click(city);
	}
	
	public void clickOnCreditCardInputBox() throws IOException {
		try {
			utilities.Helper.click(cardName);
			Reporting.updateTestReport("Click on Credit Card input box",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			// TODO: handle exception
			Reporting.updateTestReport("Clicking on Credit Card input box is not working"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
	}
	
	public void enterCreditCardName(String ccName) {		
		utilities.Helper.waitForElementVisibility(cardName, 20);
		utilities.Helper.enterText(cardName, ccName);
	}
	
	public void enterAddressLine1(String address1) {		
		utilities.Helper.enterText(addressLine1, address1);
	}
	
	public void enterCity(String town) {		
		utilities.Helper.enterText(city, town);
	}
	
	public void enterZipCode(String townCode) {		
		utilities.Helper.enterText(zipCode, townCode);
	}
	
	public void enterPhoneNumber(String number) {		
		utilities.Helper.enterText(phoneNumber, number);
	}
	
	
	
	public void clickOnMonthlySubscriptionFeeCheckbox() throws IOException {  		
		try {		
	        	utilities.Helper.waitForElementVisibility(monthSubscriptionFeeCheckbox, 10);
	            utilities.Helper.click(monthSubscriptionFeeCheckbox); 
				Reporting.updateTestReport("Month Subscription Fee Checkbox is present and Clicked", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);			
		}catch(Exception e) {
			Reporting.updateTestReport("Month Subscription Fee Checkbox is not Clicked"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
    }
	
	public boolean isSecurePaymentButtonEnabled() throws IOException { 
		try {
			if (securePaymentButton.isEnabled()) {
				Reporting.updateTestReport("Secure Payment Button is Enabled", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			}
			return true;
		} catch (Exception e) {
			Reporting.updateTestReport("Secure Payment Button is not Enabled" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		return false;
	}

	public void clickOnSecurePaymentButton() throws IOException {	
		try {			
		        	utilities.Helper.waitForElementVisibility(securePaymentButton, 10);
		            utilities.Helper.click(securePaymentButton);  
				Reporting.updateTestReport("Secure Payment button is present and Clicked", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
		catch(Exception e) {
			Reporting.updateTestReport("Secure Payment button is not Clicked"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}		
    }
	
	public String getVatValue() throws IOException {
        try {
        utilities.Helper.waitForElementVisibility(vat, 40);
        Reporting.updateTestReport("vat is visible in UI ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        return vat.getText();
        } catch(Exception e){
            Reporting.updateTestReport("Vat is not visible in UI"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
        }
        return "";
    }

    public String getRecurringMonthlyBillValue() throws IOException {
        try {
            Reporting.updateTestReport("Recurring Monthly Bill is visible in UI ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);    
        return recurringMonthlyBill.getText();
        } catch(Exception e){
            Reporting.updateTestReport("Recurring Monthly Bill is not visible in UI"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
        }
        return "";
    }
	
	public void enterCardNumber(String cardNum, WebDriver driver) throws IOException { 
        try {
        Thread.sleep(15000);        
        driver.switchTo().frame("wpsFrame"); 
        driver.switchTo().frame("tokenFrame");
        driver.switchTo().frame(securePaymentFrame);
        utilities.Helper.enterText(cardNumber1, cardNum);
        Reporting.updateTestReport("Card number: "+cardNum+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        } catch(Exception e){
            Reporting.updateTestReport("Card number is not entered successfully"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
        }
    }
	
	public void enterWPSSecurePaymentDetails(String cardNum, String expiryDate, String cvc, WebDriver driver) 
			throws InterruptedException, IOException {        
		try {
		Thread.sleep(15000);
		driver.switchTo().frame("wpsFrame"); 
		driver.switchTo().frame("tokenFrame");
		//utilities.Helper.waitForElementVisibility(submitOrderButton, 40);
		driver.switchTo().frame(securePaymentFrame);		 
        utilities.Helper.enterText(cardNumber1, cardNum);
        Reporting.updateTestReport("Card Number: "+cardNum+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        utilities.Helper.enterText(expDate, expiryDate);
        Reporting.updateTestReport("Expiry Date: "+expiryDate+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        utilities.Helper.enterText(cvv, cvc);   
        Reporting.updateTestReport("CVC: "+cvc+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        driver.switchTo().defaultContent();
		} catch(Exception e){
			Reporting.updateTestReport("Data is not entered successfully"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
      }
    
	public void enterExpiryDate(String expiryDate) throws IOException { 
        try {
        utilities.Helper.enterText(expDate, expiryDate);
        Reporting.updateTestReport("Expiry Date: "+expiryDate+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        } catch(Exception e){
            Reporting.updateTestReport("Expiry Date is not entered successfully"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
        }
    }
    
	public void enterCVV(String cvvNum) throws IOException {             
        try {
            utilities.Helper.enterText(cvv, cvvNum);;
            Reporting.updateTestReport("CVV "+cvvNum+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
            } catch(Exception e){
                Reporting.updateTestReport("CVV is not entered successfully"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
            }
    }
    
    public void clickOnCompleteAuthentication(WebDriver driver) throws IOException {		
		try {
			driver.switchTo().frame(securePaymentFrame3D);
			driver.switchTo().frame(challengeFrame);
			driver.switchTo().frame(fullScreenFrame);
			utilities.Helper.scrollWindow(completeAuthentication);
			utilities.Helper.click(completeAuthentication);	
			Reporting.updateTestReport("Clicked on complete authentication",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);			
		} catch (Exception e) {			
			Reporting.updateTestReport("Clicking on complete authentication failed"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
    
    public void clickOnSubmitOrderButton(WebDriver driver) throws IOException, InterruptedException {    
    	try {
    	driver.switchTo().frame("wpsFrame");
        driver.switchTo().frame("tokenFrame");
    	utilities.Helper.click(submitOrderButton); 
    	Reporting.updateTestReport("Submit button was clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); 
    	Thread.sleep(3000);
    	}catch(Exception e) {
    		Reporting.updateTestReport("Submit button was not clicked successfully"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
    	}
    }
    
    public String verifyInCompleteCardNumberFieldValidation() throws IOException {  
        try {
        String inCompletecardNumber = inCompleteCardNumberFieldValidation.getText();
        Reporting.updateTestReport("Incomplete Card Number error message is correct",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        return inCompletecardNumber;  
        }catch(Exception e) {
            Reporting.updateTestReport("Incomplete Card Number error message is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
        }
        return "";
    }
    
    public String verifyInValidCardNumberFieldValidation() throws IOException { 
        try {
        String inValidcardNumber = inValidCardNumberFieldValidation.getText();
        Reporting.updateTestReport("InValid Card Number error message is correct",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        return inValidcardNumber;  
        }catch(Exception e) {
            Reporting.updateTestReport("InValid Card Number error message is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
        }
        return "";
    }

    public String verifyInCompleteExpiryDateFieldValidation() throws IOException { 
        try {
        String inCompleteExpiryDate = inCompleteExpiryDateFieldValidation.getText();
        Reporting.updateTestReport("InComplete Expiry Date error message is correct",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        return inCompleteExpiryDate;  
        }catch(Exception e) {
            Reporting.updateTestReport("InComplete Expiry Date error message is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
        }
        return "";    
    }

    public String verifyInValidExpiryDateFieldValidation() throws IOException {    
        try {
        String inValidExpiryDate = inValidExpiryDateFieldValidation.getText();
        Reporting.updateTestReport("Invalid Expiry Date error message is correct",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        return inValidExpiryDate;  
        }catch(Exception e) {
            Reporting.updateTestReport("Invalid Expiry Date error message is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
        }
        return "";
    }

    public String verifyInCompleteCVVFieldValidation() throws IOException { 
        try {
        String inCompleteCVV = inCompleteCVVFieldValidation.getText();
        Reporting.updateTestReport("Invalid CVV error message is correct",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        return inCompleteCVV;  
        }catch(Exception e) {
            Reporting.updateTestReport("Invalid CVV error message is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
        }
        return "";    
    }

    
    public String verifyErrorMessageForCards() throws IOException {    	
    	try {
    		String errorMessage = errorMessageForCards.getText();		
    		Reporting.updateTestReport("Error Message for cards is present",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
    		return errorMessage;
    	}
    	catch(Exception e){
    		Reporting.updateTestReport("Error Message for cards is not present "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
    		return "";
    	}			
    	}	
}
