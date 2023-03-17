package PageObjectRepo;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Test_Suite.TestHarness_RegressionSuite;
import utilities.CaptureScreenshot;
import utilities.Reporting;
import utilities.StatusDetails;

public class app_TestHarness_Repo {
	
	public String SS_path=TestHarness_RegressionSuite.SS_path;
	
	@FindBy(xpath = "//div[@class='http-link']")
	WebElement ClickHTTP_Interface;
	
	@FindBy(xpath = "//a[contains(text(),'Tokenise')]")
	WebElement ClickHTTP_Tokenise;
	
	/*@FindBy(xpath = "//input[@name='WPG_vendorID']")
	WebElement Tokenise_enterClientID;*/
	
	//Added by Gourab
	@FindBy(name = "WPG_vendorID")
	WebElement Tokenise_enterClientID;
	
	@FindBy(name="vendorPwd")
	WebElement Tokenise_enterClientPWD;
	
	@FindBy(name="WPG_description")
	WebElement Tokenise_enterClientDescription;
	
	/* 
	 * Author : Jayanta
	 * Description : Object repo for Tokenise operation
	 */
	
	@FindBy(xpath = "//option[@value='NA']")
    WebElement Tokenise_selectRegion;

    @FindBy(name="WPG_address")
    WebElement Tokenise_enterAddress;
    
    @FindBy(name="WPG_postcode")
    WebElement Tokenise_enterPostCode;
    
    @FindBy(name="WPG_countryCode")
    WebElement Tokenise_enterCountryCode;
    
    @FindBy(xpath = "//option[@value='Y']")
    WebElement Tokenise_selectAVSYes;
    
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    WebElement Tokenise_clickContinue;
    
    /* 
	 * Author : Jayanta
	 * Description : Object Repo for Payment details
	 */
    
    @FindBy(name="number")
    WebElement CardNumber;
    
    @FindBy(name="expiry")
    WebElement CardExpiry;
    
    @FindBy(name="cvc")
    WebElement CardCVC;
    
    @FindBy(xpath="//button[@id='submit']")
	WebElement PaymentSubmit;
    
    /* 
	 * Author : Jayanta
	 * Description : Object repo for Tokenise operation Response
	 */
    
    @FindBy(xpath="//div[@class='form-group'][3]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement ReturnMessage;
    
    @FindBy(xpath="//div[@class='form-group'][2]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement ReturnCode;
    
    @FindBy(xpath="//div[@class='form-group'][1]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement Operation;
    
    @FindBy(xpath="//div[@class='form-group'][5]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement transID;
    
    @FindBy(xpath="//div[@class='form-group'][6]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement merchantResponse;
    
    @FindBy(xpath="//div[@class='form-group'][7]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement merchantReference;
    
    @FindBy(xpath="//div[@class='form-group'][12]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement token;
    
    @FindBy(xpath="//div[@class='form-group'][13]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement acquirerID;
    
    @FindBy(xpath="//div[@class='form-group'][14]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement acquirerName;
    
    @FindBy(xpath="//div[@class='form-group'][17]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement maskedCardNumber;
    
    @FindBy(xpath="//div[@class='form-group'][18]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement cardExpiry;
    
    /* 
	 * Author : Jayanta
	 * Description : Object repo for Probe operation
	 */
    
    @FindBy(xpath = "//a[contains(text(),'Probe')]")
	WebElement ClickHTTP_Probe;
    
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    WebElement Probe_clickPerform;
    
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    WebElement Probe_clickBack;
    
    /* 
	 * Author : Jayanta
	 * Description : Object repo for Validate operation
	 */
    
    @FindBy(xpath = "//a[contains(text(),'Validate')]")
	WebElement ClickHTTP_Validate;
    
    /* 
	 * Author : Jayanta
	 * Description : Object repo for Authorise operation
	 */
    
    @FindBy(xpath = "//a[contains(text(),'Authorise')]")
	WebElement ClickHTTP_Authorise;
    
    @FindBy(xpath = "//option[@value='EMEA-02']")
    WebElement Auth_selectRegion;
    
    @FindBy(xpath="//div[@class='form-group'][6]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement authValue;
    
    @FindBy(xpath="//div[@class='form-group'][7]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement authCurrency;
    
    @FindBy(xpath="//div[@class='form-group'][8]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement authMerchantResponse;
    
    @FindBy(xpath="//div[@class='form-group'][9]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement authMerchantReference;
    
    @FindBy(xpath="//div[@class='form-group'][14]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement authToken;
    
    @FindBy(xpath="//div[@class='form-group'][15]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement authCode;
    
    @FindBy(xpath="//div[@class='form-group'][16]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement authAcquireID;
    
    @FindBy(xpath="//div[@class='form-group'][17]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement authAcquirerName;
    
    @FindBy(xpath="//div[@class='form-group'][20]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement authMaskedCardNumber;
    
    @FindBy(xpath="//div[@class='form-group'][21]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement authCardExpiry;
    
    /* 
	 * Author : Varisa
	 * Description : Object repo for Authorise operation
	 */
    
    @FindBy(name="WPG_value")
    WebElement TokenAuth_enterValue;
    
    @FindBy(name="WPG_currency")
    WebElement TokenAuth_entercurrency;
    
    /* 
   	 * Author : Jayanta
   	 * Description : Object repo for Auth & Settle operation
   	 */
       
    @FindBy(xpath = "//a[contains(text(),'Auth & Settle')]")
   	WebElement ClickHTTP_AuthSettle;
       
    @FindBy(xpath="//div[@class='form-group'][8]/div[@class='col-sm-5']/div[@class='response-form-control']")
   	WebElement authSettleMerchantReference;
       
    @FindBy(xpath="//div[@class='form-group'][9]/div[@class='col-sm-5']/div[@class='response-form-control']")
   	WebElement authSettleMerchantResponse;
       
       /* 
      	* Author : Jayanta
      	* Description : Object repo for SOAP Token Auth & Settle operation
      	*/
       
    @FindBy(xpath = "//div[@class='soap-link']")
   	WebElement ClickSOAP_Interface;
    
    @FindBy(xpath = "//a[contains(text(),'Token Auth & Settle')]")
	WebElement ClickSOAP_TokenAuthSettle;
    
    @FindBy(name="WPG_method")
	WebElement TokenAuthSettle_enterMethod;
    
    @FindBy(xpath="//select[@name='WPG_customerPresent']")
	WebElement CustPresentDropDown;
    
	@FindBy(xpath="//option[@value='N']")
	WebElement NO;
	
	@FindBy(name="WPG_token")
	WebElement TokenAuthSettle_token;
	
	@FindBy(xpath="//div[@class='form-group'][14]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement TokenAuthSettle_authCode;
	
	@FindBy(xpath="//div[@class='form-group'][15]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement TAS_acquirerID;
    
    @FindBy(xpath="//div[@class='form-group'][16]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement TAS_acquirerName;
    
    @FindBy(xpath="//div[@class='form-group'][19]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement TAS_maskedCardNumber;
    
    @FindBy(xpath="//div[@class='form-group'][20]/div[@class='col-sm-5']/div[@class='response-form-control']")
	WebElement TAS_cardExpiry;
    
    /* 
  	* Author : Jayanta
  	* Description : Object repo for SOAP Void Auth operation
  	*/
   

    @FindBy(xpath = "//a[contains(text(),'Void Auth')]")
    WebElement ClickSOAP_VoidAuth;
    
    @FindBy(name="WPG_authCode")
	WebElement VoidAuth_AuthCode;
    
    /*
     * Author : Varisa
     * Description : Object repo for Token Auth operation
     */
    
    @FindBy(xpath = "//a[contains(text(),'Token Auth')]")
	WebElement ClickHTTP_TokenAuth;
       
    
    /*
     * Author : Varisa
     * Description : Object repo for Token Settle operation
     */
    
    @FindBy(xpath = "//a[contains(text(),'Token Settle')]")
	WebElement ClickHTTP_TokenSettle;
    
    /*
     * Author : Varisa
     * Description : Object repo for Token Auth&Settle operation
     */
    
    @FindBy(xpath = "//a[contains(text(),'Token Auth & Settle')]")
    WebElement ClickHTTP_TokenAuthSettle;
   
    @FindBy(xpath = "//option[@value='CA']")
    WebElement TokenAuthSettle_selectRegion;
    
    /*
     * Author : Varisa
     * Description : Object repo for Void Auth operation
     */
    
    @FindBy(xpath = "//a[contains(text(),'Void Auth')]")
    WebElement ClickHTTP_VoidAuth;
    
    /* 
     * Author : Varisa
     * Description : Object repo for SOAP Probe operation
     */
    
    @FindBy(xpath = "//a[contains(text(),'Probe')]")
    WebElement ClickSOAP_Probe;
    
    /* 
     * Author : Varisa
     * Description : Object repo for SOAP Token Auth operation
     */
    
    @FindBy(xpath = "//a[contains(text(),'Token Auth')]")
    WebElement ClickSOAP_TokenAuth;
    
    /* 
     * Author : Varisa
     * Description : Object repo for SOAP Token Settle operation
     */
    
    @FindBy(xpath = "//a[contains(text(),'Token Settle')]")
    WebElement ClickSOAP_TokenSettle;
    
   /* 
    * Author : Varisa
    * Description : Object repo for SOAP Token Refund operation
    */
    @FindBy(xpath = "//a[contains(text(),'Token Refund')]")
    WebElement ClickSOAP_TokenRefund;
    
    /* 
	 * Author : Jayanta
	 * Description : Object repo to click Embedded in HTTP Client 
	 */
    
    @FindBy(xpath = "//a[contains(text(),'Embedded')]")
    WebElement Click_Embedded;
    
    /* 
	 * Author : Jayanta
	 * Description : Object repo to enter client app id in embedded flow in HTTP Client 
	 */
    
    @FindBy(name="WPG_appId")
    WebElement enterEmbeddedClientAppID;
    
    /* 
	 * Author : Jayanta
	 * Description : Object repo to enter email id,intent type,Customer Name,IsEmbedded in embedded flow in HTTP Client 
	 */
    
    @FindBy(name="WPG_email")
    WebElement enterEmbeddedEmailID;
    
    @FindBy(xpath = "//option[@value='AUTHORIZE']")
    WebElement Embedded_selectIntentAuth;
    
    @FindBy(xpath = "//option[@value='AUTHORIZE_CAPTURE']")
    WebElement Embedded_selectIntentCapture;
    
    @FindBy(xpath = "//option[@value='SAVE_CARD']")
    WebElement Embedded_selectIntentSaveCard;
    
    @FindBy(name="WPG_customerName")
    WebElement enterEmbeddedCustName;
    
    @FindBy(xpath = "//option[@value='Yes']")
    WebElement isEmbeddedYes;
    
    @FindBy(xpath = "//option[@value='No']")
    WebElement isEmbeddedNo;
    
    @FindBy(xpath = "//button[@class='btn btn-primary w-100']")
    WebElement Embedded_clickProceedToPay;
    
    @FindBy(xpath = "//button[@class='btn btn-primary w-auto']")
    WebElement Embedded_clickProceed;
    
    @FindBy(xpath = "//button[contains(text(),'See More')]")
	WebElement ClickSeeMore;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[6]")
	WebElement HTTP_Embedded_FetchOperation;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[8]")
	WebElement HTTP_Embedded_FetchReturnCode;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[10]")
	WebElement HTTP_Embedded_FetchReturnMessage;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[14]")
	WebElement HTTP_Embedded_FetchTransID;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[16]")
	WebElement HTTP_Embedded_FetchValue;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[16]")
	WebElement HTTP_Embedded_SaveCard_FetchMerchantResponse;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[20]")
	WebElement HTTP_Embedded_FetchMerchantResponse;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[20]")
	WebElement HTTP_Embedded_AuthCapture_FetchMerchantReference;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[18]")
	WebElement HTTP_Embedded_SaveCard_FetchMerchantReference;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[22]")
	WebElement HTTP_Embedded_FetchMerchantReference;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[22]")
	WebElement HTTP_Embedded_AuthCapture_FetchMerchantResponse;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[32]")
	WebElement HTTP_Embedded_FetchToken;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[28]")
	WebElement HTTP_Embedded_SaveCard_FetchToken;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[34]")
	WebElement HTTP_Embedded_FetchAuthCode;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[36]")
	WebElement HTTP_Embedded_FetchAcquirerID;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[30]")
	WebElement HTTP_Embedded_SaveCard_FetchAcquirerID;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[38]")
	WebElement HTTP_Embedded_FetchAcquirerName;
    
    @FindBy(xpath = "(//div[@class='orderReviewSuccess-col'])[32]")
	WebElement HTTP_Embedded_SaveCard_FetchAcquirerName;
    
    
    
    
	

	
	public void ClickHttp_Interface() throws IOException {
		try {
			ClickHTTP_Interface.click();
			Reporting.updateTestReport("Http Interface clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Http Interface is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	public void ClickHttp_Tokenise() throws IOException {
		try {
			ClickHTTP_Tokenise.click();
			Reporting.updateTestReport("Tokenise clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Tokenise is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	public void Http_Tokenise_EnterClientID(String clientID) throws IOException {
		try {
			Tokenise_enterClientID.clear();
			Tokenise_enterClientID.sendKeys(clientID);
			Reporting.updateTestReport("Client ID Enter successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Client ID is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	public void Http_Tokenise_EnterClientPWD(String clientPWD) throws IOException {
		try {
			Tokenise_enterClientPWD.clear();
			Tokenise_enterClientPWD.sendKeys(clientPWD);
			Reporting.updateTestReport("Client Password Enter successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Client Password is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	public void Http_Tokenise_EnterClientDescription(String clientDescription) throws IOException {
		try {
			Tokenise_enterClientDescription.clear();
			Tokenise_enterClientDescription.sendKeys(clientDescription);
			Reporting.updateTestReport("Client Description Enter successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Client Description is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to select region in Tokenise operation
	 */
	
	public void Http_Tokenise_SelectRegion() throws IOException {
		try {
			Tokenise_selectRegion.click();
			Reporting.updateTestReport("Region is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Region is not selected : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to enter address in Tokenise operation
	 */
	
	public void Http_Tokenise_EnterAddress(String address) throws IOException {
		try {
			Tokenise_enterAddress.clear();
			Tokenise_enterAddress.sendKeys(address);
			Reporting.updateTestReport("Address is Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Address is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to enter postal code in Tokenise operation
	 */
	
	public void Http_Tokenise_EnterPostalCode(String pCode) throws IOException {
		try {
			Tokenise_enterPostCode.clear();
			Tokenise_enterPostCode.sendKeys(pCode);
			Reporting.updateTestReport("Postal Code is Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Postal Code is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to enter country code in Tokenise operation
	 */
	
	public void Http_Tokenise_EnterCountryCode(String countryCode) throws IOException {
		try {
			Tokenise_enterCountryCode.clear();
			Tokenise_enterCountryCode.sendKeys(countryCode);
			Reporting.updateTestReport("Country Code is Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Country code is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to select AVS code in Tokenise operation
	 */
	
	public void Http_Tokenise_SelectAVSCode() throws IOException {
		try {
			Tokenise_selectAVSYes.click();
			Reporting.updateTestReport("AVS is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("AVS is not selected : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to click continue in Tokenise operation
	 */
	
	public void Http_Tokenise_ClickContinue() throws IOException {
		try {
			Tokenise_clickContinue.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("Continue button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Continue button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Methods to enter Payment details
	 */
	
	public void Http_EnterCardNumber(String cardNumber) throws IOException {
		try {
			CardNumber.sendKeys(cardNumber);
			Reporting.updateTestReport("Card Number: " + cardNumber + " is Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Card Number is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
	    }
	}
	public void Http_EnterCardExpiry(String cardExpiry) throws IOException {
		try {
			CardExpiry.sendKeys(cardExpiry);
			Reporting.updateTestReport("Card Expiration: " + cardExpiry + " is Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Card Expiration is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
	    }
	}
	public void Http_EnterCardCVC(String cardCVC) throws IOException {
		try {
			CardCVC.sendKeys(cardCVC);
			Reporting.updateTestReport("Card CVC: " +cardCVC+ " is Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Card CVC is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
	    }
	}
	public void paymentDetailsSubmit() throws IOException {
		try {
			PaymentSubmit.click();
			Reporting.updateTestReport("Payment details was submitted successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Payment details couldn't be submitted with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch return message in Tokenise operation Response page
	 */
	
	public String Http_Tokenise_FetchReturnMessage() throws IOException{
	      try {
				String val1=ReturnMessage.getText();
				System.out.println(val1);
				Reporting.updateTestReport("Return message is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return val1;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Return message is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch return code in Tokenise operation Response page
	 */
	
	public String Http_Tokenise_FetchReturnCode() throws IOException{
	      try {
				String val2=ReturnCode.getText();
				System.out.println(val2);
				Reporting.updateTestReport("Return code is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return val2;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Return code is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch operation in Tokenise operation Response page
	 */
	
	public String Http_Tokenise_FetchOperation() throws IOException{
	      try {
				String val3=Operation.getText();
				System.out.println(val3);
				Reporting.updateTestReport("Operation is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return val3;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Operation is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch trans ID in Tokenise operation Response page
	 */
	
	public String Http_Tokenise_FetchTransID() throws IOException{
	      try {
				String val4=transID.getText();
				System.out.println(val4);
				Reporting.updateTestReport("TransID: " +val4+ " is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return val4;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Operation is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch merchant response in Tokenise operation Response page
	 */
	
	public String Http_Tokenise_FetchMerchantResponse() throws IOException{
	      try {
				String val5=merchantResponse.getText();
				System.out.println(val5);
				Reporting.updateTestReport("Merchant Response is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return val5;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Merchant Response is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch merchant reference in Tokenise operation Response page
	 */
	
	public String Http_Tokenise_FetchMerchantReference() throws IOException{
	      try {
				String val6=merchantReference.getText();
				System.out.println(val6);
				Reporting.updateTestReport("Merchant Reference is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return val6;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Merchant Reference is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch token in Tokenise operation Response page
	 */
	
	public String Http_Tokenise_FetchToken() throws IOException{
	      try {
				String val7=token.getText();
				System.out.println(val7);
				Reporting.updateTestReport("Token number: " +val7+ " is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return val7;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Token is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch acquirer ID in Tokenise operation Response page
	 */
	
	public String Http_Tokenise_FetchAcquirerID() throws IOException{
	      try {
				String val8=acquirerID.getText();
				System.out.println(val8);
				Reporting.updateTestReport("AcquirerID is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return val8;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("AcquirerID is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch acquirer name in Tokenise operation Response page
	 */
	
	public String Http_Tokenise_FetchAcquirerName() throws IOException{
	      try {
				String val9=acquirerName.getText();
				System.out.println(val9);
				Reporting.updateTestReport("AcquirerName is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return val9;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("AcquirerName is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch masked card number in Tokenise operation Response page
	 */
	
	public String Http_Tokenise_FetchMaskedCardNumber() throws IOException{
	      try {
				String val10=maskedCardNumber.getText();
				System.out.println(val10);
				Reporting.updateTestReport("MaskedCardNumber is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return val10;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("MaskedCardNumber is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch card expiry in Tokenise operation Response page
	 */
	
	public String Http_Tokenise_FetchCardExpiry() throws IOException{
	      try {
				String val11=cardExpiry.getText();
				System.out.println(val11);
				Reporting.updateTestReport("Card Expiry is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return val11;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Card Expiry is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to click Probe operation
	 */
	
	public void ClickHttp_Probe() throws IOException {
		try {
			ClickHTTP_Probe.click();
			Reporting.updateTestReport("Probe clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Probe is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to click perform in Probe operation
	 */
	
	public void Http_Probe_ClickPerform() throws IOException {
		try {
			Probe_clickPerform.click();
			Reporting.updateTestReport("Perform button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Perform button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to click back in Probe operation
	 */
	
	public void Http_Probe_ClickBack() throws IOException {
		try {
			Probe_clickBack.click();
			Reporting.updateTestReport("Back button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Back button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to click Validate operation
	 */
	
	public void ClickHttp_Validate() throws IOException {
		try {
			ClickHTTP_Validate.click();
			Reporting.updateTestReport("Validate clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Validate is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to click Authorise operation
	 */
	
	public void ClickHttp_Authorise() throws IOException {
		try {
			ClickHTTP_Authorise.click();
			Reporting.updateTestReport("Authorise is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Authorise is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to select region in Authorise operation
	 */
	
	public void Http_Authorise_SelectRegion() throws IOException {
		try {
			Auth_selectRegion.click();
			Reporting.updateTestReport("Region is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Region is not selected : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to enter amount in Authorise operation
	 */
	
	public void Http_Authorise_EnterValue(String value) throws IOException {
		try {
			TokenAuth_enterValue.clear();
			TokenAuth_enterValue.sendKeys(value);
			Reporting.updateTestReport("Value: " +value+ " is Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Value is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to enter currency in Authorise operation
	 */
	
	public void Http_Authorise_EnterCurrency(String currency) throws IOException {
		try {
			TokenAuth_entercurrency.clear();
			TokenAuth_entercurrency.sendKeys(currency);
			Reporting.updateTestReport("Currency: " +currency+ " is Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Currency is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch merchant response in Authorise operation
	 */
	
	public String Http_Auth_FetchMerchantResponse() throws IOException{
	      try {
				String merchantResponse=authMerchantResponse.getText();
				System.out.println(merchantResponse);
				Reporting.updateTestReport("Merchant response is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return merchantResponse;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Merchant response is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch merchant reference in Authorise operation
	 */
	
	public String Http_Auth_FetchMerchantReference() throws IOException{
	      try {
				String merchantReference=authMerchantReference.getText();
				System.out.println(merchantReference);
				Reporting.updateTestReport("Merchant reference is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return merchantReference;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Merchant reference is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch token in Authorise operation
	 */
	
	public String Http_Auth_FetchToken() throws IOException{
	      try {
				String token=authToken.getText();
				System.out.println(token);
				Reporting.updateTestReport("Token number: " +token+ " is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return token;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Token is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch auth code in Authorise operation
	 */
	
	public String Http_Auth_FetchAuthCode() throws IOException{
	      try {
				String AuthCode=authCode.getText();
				System.out.println(AuthCode);
				Reporting.updateTestReport("Auth Code: " +AuthCode+ " is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return AuthCode;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Auth Code is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch acquirer ID in Authorise operation
	 */
	
	public String Http_Auth_FetchAcquirerID() throws IOException{
	      try {
				String AuthAcquirerID=authAcquireID.getText();
				System.out.println(AuthAcquirerID);
				Reporting.updateTestReport("AcquirerID is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return AuthAcquirerID;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("AcquirerID is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch acquirer name in Authorise operation
	 */
	
	public String Http_Auth_FetchAcquirerName() throws IOException{
	      try {
				String AuthAcquirerName=authAcquirerName.getText();
				System.out.println(AuthAcquirerName);
				Reporting.updateTestReport("AcquirerName is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return AuthAcquirerName;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("AcquirerName is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch masked card number in Authorise operation
	 */
	
	public String Http_Auth_FetchMaskedCardNumber() throws IOException{
	      try {
				String AuthMaskedCardNumber=authMaskedCardNumber.getText();
				System.out.println(AuthMaskedCardNumber);
				Reporting.updateTestReport("MaskedCardNumber is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return AuthMaskedCardNumber;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("MaskedCardNumber is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch card expiry in Authorise operation
	 */
	
	public String Http_Auth_FetchCardExpiry() throws IOException{
	      try {
				String CardExpiry=authCardExpiry.getText();
				System.out.println(CardExpiry);
				Reporting.updateTestReport("Card Expiry is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return CardExpiry;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Card Expiry is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to click Auth & Settle operation
	 */
	
	public void ClickHttp_AuthSettle() throws IOException {
		try {
			ClickHTTP_AuthSettle.click();
			Reporting.updateTestReport("Auth & Settle is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Auth & Settle is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch merchant response in Auth & Settle operation
	 */
	
	public String Http_AuthSettle_FetchMerchantResponse() throws IOException{
	      try {
				String merchantResponse=authSettleMerchantResponse.getText();
				System.out.println(merchantResponse);
				Reporting.updateTestReport("Merchant response is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return merchantResponse;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Merchant response is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch merchant reference in Auth & Settle operation
	 */
	
	public String Http_AuthSettle_FetchMerchantReference() throws IOException{
	      try {
				String merchantReference=authSettleMerchantReference.getText();
				System.out.println(merchantReference);
				Reporting.updateTestReport("Merchant reference is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return merchantReference;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Merchant reference is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to click SOAP Interface
	 */
	
	public void ClickSOAP_Interface() throws IOException {
		try {
			ClickSOAP_Interface.click();
			Reporting.updateTestReport("SOAP Interface clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("SOAP Interface is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to click SOAP Token Auth & Settle operation
	 */
	
	public void ClickSOAP_TokenAuthSettle() throws IOException {
		try {
			ClickSOAP_TokenAuthSettle.click();
			Reporting.updateTestReport("SOAP Token Auth & Settle clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("SOAP Token Auth & Settle is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to enter method in SOAP Token Auth & Settle operation
	 */
	
	public void SOAP_TokenAuthSettle_EnterMethod(String method) throws IOException {
		try {
			TokenAuthSettle_enterMethod.clear();
			TokenAuthSettle_enterMethod.sendKeys(method);
			Reporting.updateTestReport("Method is Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Method is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to select customer present in SOAP Token Auth & Settle operation
	 */
	
	public void SOAP_TokenAuthSettle_CustPresent() throws IOException {
		try {
			CustPresentDropDown.click();
			Reporting.updateTestReport("Customer present dropdown is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Customer present dropdown is not selected : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	public void SOAP_TokenAuthSettle_CustPresentValue() throws IOException {
		try {
			NO.click();
			Reporting.updateTestReport("Customer present dropdown value No is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Customer present dropdown value is not selected : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to enter token in SOAP Token Auth & Settle operation
	 */
	
	public void SOAP_TokenAuthSettle_EnterToken(String token) throws IOException {
		try {
			TokenAuthSettle_token.clear();
			TokenAuthSettle_token.sendKeys(token);
			Reporting.updateTestReport("Token " +token+ " is Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Token is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch auth code in SOAP Token Auth & Settle operation
	 */
	
	public String SOAP_TokenAuthSettle_FetchAuthCode() throws IOException{
	      try {
				String AuthCode=TokenAuthSettle_authCode.getText();
				System.out.println(AuthCode);
				Reporting.updateTestReport("Auth Code: " +AuthCode+ " is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return AuthCode;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Auth Code is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch acquirer ID in SOAP Token Auth & Settle operation
	 */
	
	public String SOAP_TokenAuthSettle_FetchAcquirerID() throws IOException{
	      try {
				String AcquirerID=TAS_acquirerID.getText();
				System.out.println(AcquirerID);
				Reporting.updateTestReport("AcquirerID is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return AcquirerID;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("AcquirerID is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch acquirer name in SOAP Token Auth & Settle operation
	 */
	
	public String SOAP_TokenAuthSettle_FetchAcquirerName() throws IOException{
	      try {
				String AcquirerName=TAS_acquirerName.getText();
				System.out.println(AcquirerName);
				Reporting.updateTestReport("AcquirerName is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return AcquirerName;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("AcquirerName is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch masked card number in SOAP Token Auth & Settle operation
	 */
	
	public String SOAP_TokenAuthSettle_FetchMaskedCardNumber() throws IOException{
	      try {
				String MaskedCardNumber=TAS_maskedCardNumber.getText();
				System.out.println(MaskedCardNumber);
				Reporting.updateTestReport("MaskedCardNumber is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return MaskedCardNumber;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("MaskedCardNumber is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to fetch card expiry in SOAP Token Auth & Settle operation
	 */
	
	public String SOAP_TokenAuthSettle_FetchCardExpiry() throws IOException{
	      try {
				String CardExpiry=TAS_cardExpiry.getText();
				System.out.println(CardExpiry);
				Reporting.updateTestReport("Card Expiry is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return CardExpiry;
			    }
			catch(Exception e) {
				Reporting.updateTestReport("Card Expiry is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			   }
		}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to click SOAP Void Auth operation
	 */
	
	public void ClickSOAP_VoidAuth() throws IOException {
		try {
			ClickSOAP_VoidAuth.click();
			Reporting.updateTestReport("SOAP Void Auth clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("SOAP Void Auth is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	 * Author - Jayanta 
	 * Description : Method to enter auth code in SOAP Void Auth operation
	 */
	
	public void SOAP_VoidAuth_EnterAuthCode(String authCode) throws IOException {
		try {
			VoidAuth_AuthCode.clear();
			VoidAuth_AuthCode.sendKeys(authCode);
			Reporting.updateTestReport("Auth Code " +authCode+ " is Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Auth Code is not Entered : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/* 
	   * Author - Varisa 
	   * Description : Methods to click http Token Auth operation
	   */
		
	    public void ClickHttp_TokenAuth() throws IOException {
	    try {
				
	          ClickHTTP_TokenAuth.click();
		  Reporting.updateTestReport("TokenAuth is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
		        Reporting.updateTestReport("TokenAuth is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
	    
	    /* 
		 * Author - Varisa 
		 * Description : Methods to click http Token Settle operation
		 */
		
		public void ClickHttp_TokenSettle() throws IOException {
			try {
				ClickHTTP_TokenSettle.click();
				Reporting.updateTestReport("TokenSettle is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("TokenSettle is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/* 
		 * Author - Varisa 
		 * Description : Methods click http Token Auth & Settle operation
		 */
		
		public void ClickHttp_TokenAuthSettle() throws IOException {
			try {
				ClickHTTP_TokenAuthSettle.click();
				Reporting.updateTestReport("TokenAuthSettle is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("TokenAuthSettle is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			
		}
		
		/* 
		 * Author - Varisa 
		 * Description : Methods select Region for http Token Auth & Settle operation
		 */
		public void Http_TokenAuthSettle_SelectRegion() throws IOException {
			try {
				TokenAuthSettle_selectRegion.click();
				Reporting.updateTestReport("Region is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Region is not selected : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/* 
		 * Author - Varisa 
		 * Description : Methods to click http Void Auth  operation
		 */
		
		public void ClickHttp_VoidAuth() throws IOException {
			try {
				ClickHTTP_VoidAuth.click();
				Reporting.updateTestReport("VoidAuth is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("VoidAuth is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		
		}
		
		/* 
		 * Author - Varisa 
		 * Description : Methods to click SOAP Probe operation
		 */

		public void ClickSOAP_Probe() throws IOException {
		    try {
		        ClickSOAP_Probe.click();
		        Reporting.updateTestReport("SOAP Probe clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		    }
		    catch(Exception e){
		        Reporting.updateTestReport("SOAP Probe is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		    }
		}
		
		/* 
		 * Author - Varisa 
		 * Description : Methods to click  SOAP Token Auth operation
		 */

		public void ClickSOAP_TokenAuth() throws IOException {
		    try {
		        ClickSOAP_TokenAuth.click();
		        Reporting.updateTestReport("SOAP TokenAuth clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		    }
		    catch(Exception e){
		        Reporting.updateTestReport("SOAP TokenAuth is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		    }
		
		}
		
		/* 
		 * Author - Varisa 
		 * Description : Methods to click SOAP Token Settle operation
		 */
		
		public void ClickSOAP_TokenSettle() throws IOException {
		    try {
		        ClickSOAP_TokenSettle.click();
		        Reporting.updateTestReport("SOAP TokenSettle clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		    }
		    catch(Exception e){
		        Reporting.updateTestReport("SOAP TokenSettle is not clicked "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		    }
		
		}
		
		/* 
		 * Author - Varisa 
		 * Description : Methods to click SOAP Token Refund operation
		 */
		
		public void ClickSOAP_TokenRefund() throws IOException {
	        try {
	            ClickSOAP_TokenRefund.click();
	            Reporting.updateTestReport("SOAP TokenRefund clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
	        }
	        catch(Exception e){
	            Reporting.updateTestReport("SOAP TokenRefund is not clicked "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
	        }
        }
		
		/* 
		 * Author - Jayanta 
		 * Description : Method for Embedded flow
		 */
		
		public void ClickHTTP_Embedded() throws IOException {
			try {
				Click_Embedded.click();
				Reporting.updateTestReport("Embedded is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Embedded is not clicked "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void Http_Embedded_EnterClientID(String clientID) throws IOException {
			try {
				enterEmbeddedClientAppID.clear();
				enterEmbeddedClientAppID.sendKeys(clientID);
				Reporting.updateTestReport("Client ID Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Client ID is not Entered "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void Http_Embedded_EnterEmailID(String emailID) throws IOException {
			try {
				enterEmbeddedEmailID.clear();
				enterEmbeddedEmailID.sendKeys(emailID);
				Reporting.updateTestReport("Email ID Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Email ID is not Entered "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void Http_Embedded_SelectIntentAuth() throws IOException {
			try {
				Embedded_selectIntentAuth.click();
				Reporting.updateTestReport("Auth intent type is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Auth intent type is not selected "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void Http_Embedded_SelectIntentCapture() throws IOException {
			try {
				Embedded_selectIntentCapture.click();
				Reporting.updateTestReport("Auth Capture intent type is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Auth Capture intent type is not selected "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void Http_Embedded_SelectIntentSaveCard() throws IOException {
			try {
				Embedded_selectIntentSaveCard.click();
				Reporting.updateTestReport("Save Card intent type is selected successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Save Card intent type is not selected "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void Http_Embedded_EnterCustName(String custName) throws IOException {
			try {
				enterEmbeddedCustName.clear();
				enterEmbeddedCustName.sendKeys(custName);
				Thread.sleep(2000);
				Reporting.updateTestReport("Customer name Entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Customer name is not Entered "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void Http_Embedded_SelectIsEmbeddedYes() throws IOException {
			try {
				isEmbeddedYes.click();
				Thread.sleep(2000);
				Reporting.updateTestReport("IsEmbedded is selected as Yes successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("IsEmbedded is not selected as Yes successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void Http_Embedded_SelectIsEmbeddedNo() throws IOException {
			try {
				isEmbeddedNo.click();
				Reporting.updateTestReport("IsEmbedded is selected as No successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("IsEmbedded is not selected as No successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void Http_Embedded_ClickProceedToPay() throws IOException {
			try {
				Embedded_clickProceedToPay.click();
				Thread.sleep(2000);
				Reporting.updateTestReport("Proceed to Pay button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Proceed to Pay button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void Http_Embedded_ClickProceed() throws IOException {
			try {
				Embedded_clickProceed.click();
				Reporting.updateTestReport("Proceed button is clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Proceed button is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void ClickEmbedded_SeeMore() throws IOException {
			try {
				ClickSeeMore.click();
				Reporting.updateTestReport("See More clicked successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("See More is not clicked : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public String Http_Embedded_FetchOperation() throws IOException{
		      try {
					String operation=HTTP_Embedded_FetchOperation.getText();
					System.out.println(operation);
					Reporting.updateTestReport("Operation is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return operation;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Operation is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_FetchReturnCode() throws IOException{
		      try {
					String returnCode=HTTP_Embedded_FetchReturnCode.getText();
					System.out.println(returnCode);
					Reporting.updateTestReport("Return Code is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return returnCode;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Return Code is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_FetchReturnMessage() throws IOException{
		      try {
					String returnMessage=HTTP_Embedded_FetchReturnMessage.getText();
					System.out.println(returnMessage);
					Reporting.updateTestReport("Return message is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return returnMessage;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Return message is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_FetchTransID() throws IOException{
		      try {
					String transID=HTTP_Embedded_FetchTransID.getText();
					System.out.println(transID);
					Reporting.updateTestReport("TransID is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return transID;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("TransID is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_FetchValue() throws IOException{
		      try {
					String value=HTTP_Embedded_FetchValue.getText();
					System.out.println(value);
					Reporting.updateTestReport("Value is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return value;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Value is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_FetchMerchantResponse() throws IOException{
		      try {
					String merchantResponse=HTTP_Embedded_FetchMerchantResponse.getText();
					System.out.println(merchantResponse);
					Reporting.updateTestReport("Merchant Response is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return merchantResponse;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Merchant Response is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_AuthCapture_FetchMerchantResponse() throws IOException{
		      try {
					String merchantResponse=HTTP_Embedded_AuthCapture_FetchMerchantResponse.getText();
					System.out.println(merchantResponse);
					Reporting.updateTestReport("Merchant Response is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return merchantResponse;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Merchant Response is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_SaveCard_FetchMerchantResponse() throws IOException{
		      try {
					String merchantResponse=HTTP_Embedded_SaveCard_FetchMerchantResponse.getText();
					System.out.println(merchantResponse);
					Reporting.updateTestReport("Merchant Response is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return merchantResponse;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Merchant Response is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_FetchMerchantReference() throws IOException{
		      try {
					String merchantReference=HTTP_Embedded_FetchMerchantReference.getText();
					System.out.println(merchantReference);
					Reporting.updateTestReport("Merchant Reference is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return merchantReference;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Merchant Reference is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_AuthCapture_FetchMerchantReference() throws IOException{
		      try {
					String merchantReference=HTTP_Embedded_AuthCapture_FetchMerchantReference.getText();
					System.out.println(merchantReference);
					Reporting.updateTestReport("Merchant Reference is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return merchantReference;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Merchant Reference is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_SaveCard_FetchMerchantReference() throws IOException{
		      try {
					String merchantReference=HTTP_Embedded_SaveCard_FetchMerchantReference.getText();
					System.out.println(merchantReference);
					Reporting.updateTestReport("Merchant Reference is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return merchantReference;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Merchant Reference is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		public String Http_Embedded_FetchToken() throws IOException{
		      try {
					String token=HTTP_Embedded_FetchToken.getText();
					System.out.println(token);
					Reporting.updateTestReport("Token is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return token;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Token is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_SaveCard_FetchToken() throws IOException{
		      try {
					String token=HTTP_Embedded_SaveCard_FetchToken.getText();
					System.out.println(token);
					Reporting.updateTestReport("Token is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return token;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Token is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_FetchAuthCode() throws IOException{
		      try {
					String authCode=HTTP_Embedded_FetchAuthCode.getText();
					System.out.println(authCode);
					Reporting.updateTestReport("Auth Code is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return authCode;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Auth Code is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_FetchAcquirerID() throws IOException{
		      try {
					String acquirerID=HTTP_Embedded_FetchAcquirerID.getText();
					System.out.println(acquirerID);
					Reporting.updateTestReport("Acquirer ID is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return acquirerID;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Acquirer ID is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_SaveCard_FetchAcquirerID() throws IOException{
		      try {
					String acquirerID=HTTP_Embedded_SaveCard_FetchAcquirerID.getText();
					System.out.println(acquirerID);
					Reporting.updateTestReport("Acquirer ID is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return acquirerID;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Acquirer ID is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_FetchAcquirerName() throws IOException{
		      try {
					String acquirerName=HTTP_Embedded_FetchAcquirerName.getText();
					System.out.println(acquirerName);
					Reporting.updateTestReport("Acquirer Name is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return acquirerName;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Acquirer Name is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
		public String Http_Embedded_SaveCard_FetchAcquirerName() throws IOException{
		      try {
					String acquirerName=HTTP_Embedded_SaveCard_FetchAcquirerName.getText();
					System.out.println(acquirerName);
					Reporting.updateTestReport("Acquirer Name is checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					return acquirerName;
				    }
				catch(Exception e) {
					Reporting.updateTestReport("Acquirer Name is not checked successfully "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					return "";
				   }
			}
		
}
