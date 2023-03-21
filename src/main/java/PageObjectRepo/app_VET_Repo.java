package PageObjectRepo;
import Test_Suite.Vet_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.DriverModule;

import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

public class app_VET_Repo {
	
	
	Vet_Test_Suite Vet_Test;
	public String SS_path=Vet_Test.SS_path;
	
	@FindBy(id = "register.firstName")
	WebElement FirstName;
	@FindBy(id = "register.lastName")
	WebElement LastName;
	@FindBy(id = "register.email")
	WebElement EmailId;
	@FindBy(id = "register.confirmEmail")
	WebElement ConfirmEmailId;
	@FindBy(id = "password")
	WebElement Password;
	@FindBy(id = "register.checkPwd")
	WebElement ConfirmPasswordInCreateAccount;
	@FindBy(xpath="//button[text()='Create Account'] ")
	WebElement CreateAccountButton;
	@FindBy(name="j_username")
	WebElement ExistingUserId;
	@FindBy(name="j_password")
	WebElement ExistingUserPassword;
	@FindBy(xpath="//*[@id='loginForm']/div[2]/div/button")
	WebElement LoginButton;
	@FindBy(xpath="//span[text()='Change Password']")
	WebElement ChangePassword;
	@FindBy(id="currentPassword")
	WebElement ExistingPassword;
	@FindBy(id="newPassword")
	WebElement NewPassword;
	@FindBy(id="checkNewPassword")
	WebElement ConfirmResetPassword;
	@FindBy(xpath="//form[@id='updatePasswordForm']/div[2]/div/button")
	WebElement SaveButtonResetPassword;
	@FindBy(xpath="//div[@class='alert alert-info alert-dismissable vetAlertBoxInfo']")
	WebElement PasswordResetAlert;
	@FindBy(xpath="//a[@href='/logout']")
	WebElement LogoutButton;	
	@FindBy(id="discountCodeValue")
	WebElement DiscountCode;
	@FindBy(xpath="//button[@class='button form-button button-apply discount-code-apply vet-orange-shade-bg vet-pencil-gray-color']")
	WebElement DiscountApply;
	@FindBy(xpath="//button[@class='button form-button button-continue checkoutButton vet-orange-shade-bg vet-pencil-gray-color']")
	WebElement CartContinue;
	@FindBy(id="addressFirstName")
	WebElement AddressFirstName;
	@FindBy(id="addressSurname")
	WebElement AddressLastName;
	@FindBy(id="addressLine1")
	WebElement AddressLine1;
	@FindBy(id="addressTownCity")
	WebElement City;
	@FindBy(id="addressRegion")
	WebElement StateDropDown;
	@FindBy(id="addressPostcode")
	WebElement ZipCode;
	@FindBy(id="addressPhone")
	WebElement PhoneNumber;
	@FindBy(id="consentGivenFlag")
	WebElement CheckBox;
	@FindBy(id="saveBilling")
	WebElement BillingContinue;
	@FindBy(name="number")
	WebElement CardNumber;
	@FindBy(name="expiry")
	WebElement ExpiryDate;
	@FindBy(name="cvc")
	WebElement CVC;
	@FindBy(id="submit")
	WebElement PaymentSubmit;
	@FindBy(xpath="//div[@class='table-order-row']/div[1]")
	WebElement OrderId;
	@FindBy(xpath="//span[text()=' My Account']")
	WebElement MyAccountText;
	@FindBy(xpath="//span[text()='Change Password']")
	WebElement ChangePasswordText;
	@FindBy(xpath="//*[@id='root']/div/div[1]/div[2]/div[2]/form/button")
	WebElement GetStarted;
	@FindBy(xpath="//span[text()='Edit Payment']")
	WebElement EditPayment;
	@FindBy(xpath="//button[text()='Update Credit Card']")
	WebElement UpdateCreditCardButton;
	@FindBy(xpath="//div[text()='Change the credit card on file by entering a new one ']")
	WebElement EditCardDetailsText;
	@FindBy(xpath="//div[@class='edit-payment-card']/table[@class='card-info']/tbody/tr[2]/td/span")
	WebElement UpdatedCardName;
	@FindBy(xpath="//input[@name='j_username']")
	WebElement UserNameHybrisBO;
	@FindBy(xpath="//input[@name='j_password']")
	WebElement PasswordHybrisBO;
	@FindBy(xpath="//button[text()='Login']")
	WebElement HybrisBOLoginButton;
	@FindBy(xpath="//input[@placeholder='Filter Tree entries']")
	WebElement SearchFieldInHybrisBO;
	@FindBy(xpath="//span[text()='Wiley subscription']")
	WebElement WileySubscriptionField;
	@FindBy(xpath="//button[@title='Switch search mode']")
	WebElement SearchButton;
	@FindBy(id="saveBilling")
	WebElement BillingAddressSaveButton;
	@FindBy(xpath="//div[@class='alert alert-info alert-dismissable vetAlertBoxInfo']")
	WebElement BillingAddresAltert;
	@FindBy(xpath="//div[@class='yw-buttons-container z-div']/button[@class='yw-textsearch-searchbutton y-btn-primary z-button']")
	WebElement SearchButtonBO;
    @FindBy(xpath="//span[text()='Manage Subscriptions']")
	WebElement ManageSubscription;
	@FindBy(xpath="//input[@checked='checked']")
	WebElement AutoRenewToggleOn;
	@FindBy(id="auto_renew")
	WebElement AutoRenewToggleButton;
	@FindBy(id="number")
	WebElement CardNumberWPG;
	@FindBy(id="expiryMonth")
	WebElement ExpiryMonthDropdownWPG;
	@FindBy(xpath="//option[@value='09']")
	WebElement OptionExpiryMonthWPG;
	@FindBy(id="expiryYear")
	WebElement ExpiryYearDropdownWPG;
	@FindBy(xpath="//option[@value='22']")
	WebElement OptionExpiryYearWPG;
	@FindBy(id="securityCode")
	WebElement SecurityCodeWPG;
	@FindBy(xpath="//input[@value='Make Payment']")
	WebElement MakePaymentButtonWPG;
	@FindBy(xpath = "//a[@class='recover-password']")
	WebElement ForgotChangePassword;
	@FindBy(id = "forgottenPwd.email")
	WebElement ForgottenEmail;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement Submit;
	@FindBy(xpath = "//div[@class='alert alert-info alert-dismissable vetAlertBoxInfo']//button[@type='button']")
	WebElement AlertMessage;
	@FindBy(xpath = "//input[@placeholder='Enter your inbox here']")
	WebElement Enteryopmail;
	@FindBy(xpath = "//button[@title='Check Inbox @yopmail.com']")
	WebElement clickonbutton;
	@FindBy(id = "updatePwd.pwd")
	WebElement updatepassword;
	@FindBy(id = "updatePwd.checkPwd")
	WebElement ConfirmPasswordInPasswordUpdatePage;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement Resetsubmit;
	@FindBy(xpath = "//div[@class='alert alert-info alert-dismissable vetAlertBoxInfo']/button[@type='button']")
	WebElement PasswordResetMessage;
	@FindBy(xpath = "(//button[contains(text(),'Get Started')])[2]")
	WebElement clickOnGetStarted;
	@FindBy(xpath = "(//button[contains(text(),'Continue')])[2]")
	WebElement clickOnContinueButton;
	@FindBy(id = "submit")
	WebElement SumbitButton;		
	@FindBy(name = "firstName")
	WebElement ProfileFirstName;	
	@FindBy(name = "lastName")
	WebElement ProfileLastName;	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement ProfileSaveButton;	
	@FindBy(id = "login")
	WebElement YOPUserMailID;	
	@FindBy(id = "refreshbut")
	WebElement RefreshButton;	
	@FindBy(xpath = "//a[@class='wmlogoclick']")
	WebElement YOPMailLogo;	
	@FindBy(xpath = "(((((//td[@align='center'])[1]//table//tbody//tr//td//table//tbody//tr//td)//following::td)[1]//table//tbody//tr//td//p)//following::p)[1]")
	WebElement RefundAmount;
	@FindBy(xpath="//div[contains(text(),'Tax:')]/following-sibling::div[@class='table-order-cell vet-black-gray-color']")
	WebElement Tax;
	@FindBy(xpath="//div[contains(text(),'Total:')]/following-sibling::div[@class='table-order-cell vet-black-gray-color']")
	WebElement OrderTotal;
	@FindBy(xpath = "//input[@placeholder='Enter your inbox here']")
	WebElement YopmailEmailIdField;
	@FindBy(xpath = "//button[@title='Check Inbox @yopmail.com']")
	WebElement ArrowButton;
	@FindBy(xpath = "(//main[@class='yscrollbar']/div/div/div/table/tbody/tr/td/center/table/tbody/tr/td)[2]/p[3]/a[contains(text(),'Click here to change your password')]")
	WebElement ResetPasswordLink;
	@FindBy(id = "address.country")
	WebElement SelectCountryDropDown;
	
	
	/*
	 * @Author: Anindita
	 * @Description: Enters the first name in Create account form
	 */
	public void enterFirstName(String firstName) throws IOException {
		try {
			FirstName.sendKeys(firstName);
			Reporting.updateTestReport("First name: "+firstName+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("First name was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Enters the last name in Create account form
	 */
	public void enterLastName(String lastName) throws IOException {
		try {
			LastName.sendKeys(lastName);
			Reporting.updateTestReport("Last name: "+lastName+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Last name was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Enters the email id in Create account form
	 */
	public String enterEmailId() throws IOException {
		try {
			String dateTime= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String emailId="autovet"+dateTime+"@yopmail.com";
			EmailId.sendKeys(emailId);
			ConfirmEmailId.sendKeys(emailId);
			Reporting.updateTestReport("Email Id: "+emailId+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return emailId;
		}
		catch(Exception e){
			Reporting.updateTestReport("Email Id was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Enters the password in Create account form
	 */
	public void enterPassword(String password) throws IOException
	{
		try {
			Password.sendKeys(password);
			ConfirmPasswordInCreateAccount.sendKeys(password);
			Reporting.updateTestReport("Password: "+password+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Password was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on Create account button
	 */
	public void clickCreateAccountButton() throws IOException
	{
		try {
			CreateAccountButton.click();
			Reporting.updateTestReport("Create Account button was clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Create Account button was not licked with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on logout button
	 */
	public void logOut(WebDriver driver) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			LogoutButton.click();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'VetConsult')]")));
				Reporting.updateTestReport("User was logged out successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Couldn't log out with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Couldn't log out with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Enters the email id in the login form
	 */
	public void enterExistingUserId(String userId) throws IOException{
		try {
			ExistingUserId.sendKeys(userId);
			Reporting.updateTestReport("Existing UserId: "+userId+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Existing UserId couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Enters the existing user password in the login form
	 */
	public void enterExistingUserPassword(String userPassword) throws IOException{
		try {
			ExistingUserPassword.sendKeys(userPassword);
			Reporting.updateTestReport("Existing User Password: "+userPassword+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Existing UserId couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the login button
	 */
	public void clickOnLoginButton() throws IOException{
		try {
			LoginButton.click();
			Reporting.updateTestReport("Login button was clicked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Login button couldn't be clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Checks if user is in the MyAccount page or not after login
	 */
	public void isMyAccountPage() throws IOException{
		try {
			if(MyAccountText.isDisplayed()) Reporting.updateTestReport("User is in My Account page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); 
			else Reporting.updateTestReport("User is not in My Account page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("User is not in My Account page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on th Edit password tab in MyAccount page
	 */
	public void clickEditPasswordPage() throws IOException{
		try {
			ChangePassword.click();
			Reporting.updateTestReport("Change Password button was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Change Password page couldn't be clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Checks if user is present in the Edit password page or not
	 */
	public void isChangePasswordPage() throws IOException{
		try {
			if(ChangePasswordText.isDisplayed()) Reporting.updateTestReport("User is in Change Password page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); 
			else Reporting.updateTestReport("User is not in Change Password page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("User is not in Change Password page with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Enters the old password for the user in Edit password page
	 */
	public void enterPreviousPassword(String previousPassWord) throws IOException{
		try {
			ExistingPassword.sendKeys(previousPassWord);
			Reporting.updateTestReport("Previous Password: "+previousPassWord+" was entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Previous Password couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Enters the new password for the user in Edit password page
	 */
	public void enterNewPassword(String newPassWord) throws IOException{
		try {
			NewPassword.sendKeys(newPassWord);
			ConfirmResetPassword.sendKeys(newPassWord);
			Reporting.updateTestReport("New Password: "+newPassWord+" was entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("New Password couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the Save button in Edit password page in My account
	 */
	public void clickPasswordSaveButton() throws IOException{
		try {
			SaveButtonResetPassword.click();
			Reporting.updateTestReport("Password reset save button was clicked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Password reset save button was not clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Checks if the password updation alert is present or not
	 */
	public boolean isPasswordResetAlertPresent() throws IOException{
		try {
			if(PasswordResetAlert.isDisplayed()) return true;
			else return false;
		}
		catch(Exception e){
			return false;
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the subscription to add it to cart
	 */
	public void addSubscriptionToCart() throws IOException{
		try {
			GetStarted.click();
			Reporting.updateTestReport("GET STARTED button was clicked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("GET STARTED button was not clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Adds a promo code in the cart page
	 */
	public void addPromoToCart(String promo) throws IOException{
		try {
			DiscountCode.sendKeys(promo);
			DiscountApply.click();
			Reporting.updateTestReport("Promo code: "+promo+" was added to cart successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Promo code was not applied to cart with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the Continue button from cart page
	 */
	public void continueToCheckout() throws IOException{
		try {
			CartContinue.click();
			Reporting.updateTestReport("Cart continue button was clicked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Cart continue button was not clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Enters the first name in the billing address form
	 */
	public void enterBillingFirstName(String firstName) throws IOException{
		try {
			AddressFirstName.clear();
			AddressFirstName.sendKeys(firstName);
			Reporting.updateTestReport("First name: "+firstName+" in billing address form was entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("First name was not entered in billing address form with error message  "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Enters the last name in the billing address form
	 */
	public void enterBillingLastName(String lastName) throws IOException{
		try {
			AddressLastName.clear();
			AddressLastName.sendKeys(lastName);
			Reporting.updateTestReport("Last name: "+lastName+" in billing address form was entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("First name was not entered in billing address form with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Clears the Address line 1 field if anything is present and then Enters the new value in the billing address form
	 */
	public void enterAddressLine1(String line1) throws IOException{
		try {
			AddressLine1.clear();
			Thread.sleep(3000);
			AddressLine1.sendKeys(line1);
			Reporting.updateTestReport("Address line 1: "+line1+" was entered",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Address line 1 couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clears the City field if anything is present and then Enters the new value in the billing address form
	 */
	public void enterCity(String city) throws IOException{
		try {
			City.clear();
			City.sendKeys(city);
			Reporting.updateTestReport("City: "+city+" was entered",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("City couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Clears the Zip code field if anything is present then Enters the value in the billing address form
	 */
	public void enterZip(String zip) throws IOException{
		try {
			ZipCode.clear();
			ZipCode.sendKeys(zip);
			Reporting.updateTestReport("Zip code: "+zip+" was entered", CaptureScreenshot.getScreenshot(SS_path),StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Zip code couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clears the Phone number if anything is present and then Enters the new value in the billing address form
	 */
	public void enterPhoneNumber(String phone) throws IOException{
		try {
			PhoneNumber.clear();
			PhoneNumber.sendKeys(phone);
			Reporting.updateTestReport("Phone number: "+phone+" was entered",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Phone number couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the checkbox in the billing address form
	 */
	public void checkBoxBilling() throws IOException {
		try {
			CheckBox.click();
			Reporting.updateTestReport("Checkbox in the billing address page was checked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Checkbox in the billing address page couldn't be checked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the Continue button in the billing address form so that it gets redirected to Payment page
	 */
	public void continueToCardDetailsPage() throws IOException {
		try {
			BillingContinue.click();
			Reporting.updateTestReport("Continue To Card DetailsPage was clicked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Continue To Card DetailsPage couldn't be clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	//WPS Method
	/*
	 * @Author: Anindita
	 * @Description: Enters the card number in the card details page in WPS
	 */
	public void enterCardNumber(String cardNumber) throws IOException {
		try {
			CardNumber.sendKeys(cardNumber);
			Reporting.updateTestReport("Card Number: "+cardNumber+" was entered", CaptureScreenshot.getScreenshot(SS_path),StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Card Number couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	//WPS Method
	/*
	 * @Author: Anindita
	 * @Description: Enters the expiry date in the card details page in WPS
	 */
	public void enterExpiryDate(String expiryDate) throws IOException {
		try {
			ExpiryDate.sendKeys(expiryDate);
			Reporting.updateTestReport("Expiry Date: "+expiryDate+" was entered", CaptureScreenshot.getScreenshot(SS_path),StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Expiry Date couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	//WPS Method
	/*
	 * @Author: Anindita
	 * @Description: Enters the CVC in the card details page in WPS
	 */
	public void enterCVC(String cvc) throws IOException {
		try {
			CVC.sendKeys(cvc);
			Reporting.updateTestReport("CVC: "+cvc+" was entered",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("CVC couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	//WPS Method
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the Submit button in the card details page so that the payment details get submitted in WPS
	 */
	public void paymentDetailsSubmit() throws IOException
	{
		try {
			PaymentSubmit.click();
			Reporting.updateTestReport("Payment details was submitted successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Payment details couldn't be submitted with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Fetches the Order Id from the Order Confirmation page
	 */
	public String fetchOrderId() throws IOException {
		try {
			String id=OrderId.getText();
			String order=id.substring(7, 15);
			Reporting.updateTestReport("Order id was fetched successfully", CaptureScreenshot.getScreenshot(SS_path),StatusDetails.PASS);
			return order;
		}
		catch(Exception e) {
			Reporting.updateTestReport("Order id was not fetched with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Added on 08/07/22
	 * @Description: Clicks on the Edit payment button in My Account page
	 */
	public void clickOnEditPayment() throws IOException{
		try {
			EditPayment.click();
			Reporting.updateTestReport("Edit Payment button was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Edit Payment button was not clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	/*
	 * @Author: Anindita
	 * @Added on 08/07/22
	 * @Description: Checks if user is in Edit payment tab in My Account page
	 */
	public void isEditPaymentPage() throws IOException{
		try {
			if(EditPayment.isDisplayed()) Reporting.updateTestReport("User is in Edit payment page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); 
			else Reporting.updateTestReport("User is not in CEdit payment page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("User is not in Edit payment with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Added on 08/07/22
	 * @Description: Clicks on the edit card details button in Edit payment tab in My Account page
	 */
	public void clickOnUpdateCreditCardButton() throws IOException{
		try {
			UpdateCreditCardButton.click();
			Reporting.updateTestReport("Update Credit Card Button was clicked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); 
		}
		catch(Exception e) {
			Reporting.updateTestReport("Update Credit Card Button was not clicked  "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Added on 08/07/22
	 * @Description: Checks if user is on the edit card details page
	 */
	public void isEditCardDetailsPage() throws IOException{
		try {
			if(EditCardDetailsText.isDisplayed()) Reporting.updateTestReport("User is in Edit Card Details page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); 
			else Reporting.updateTestReport("User is not in Edit Card Details page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("User is not in Edit Card Details page with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Added on 08/07/22
	 * @Description: Checks if the card details was updated or not
	 */
	public void isUpdatedCardLogoDisplayed() throws IOException{
		try {
			if(UpdatedCardName.getText().contentEquals("Visa")) Reporting.updateTestReport("Card Details was updated successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); 
			else Reporting.updateTestReport("Card Details was not updated ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Card Details was not updated "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	
	/*
	 * @Author: Anindita
	 * @Added on 11/07/22
	 * @Description: Click on the billing address save button
	 */
	public void clickOnBillingSaveButton() throws IOException{
		try {
			BillingAddressSaveButton.click();
			Reporting.updateTestReport("Billing address save button was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Billing address save button was not clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Added on 11/07/22
	 * @Description: Checks if the billing address updated alert is present or not
	 */
	public void checkIfAlertBoxDisplayedOnBillingAddressPage() throws IOException{
		try {
			if(BillingAddresAltert.isDisplayed()) Reporting.updateTestReport(BillingAddresAltert.getText()+" Alert message was dispalyed",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else Reporting.updateTestReport("Alert box was not displayed with error message ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e){
			Reporting.updateTestReport("Alert box was not displayed with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	
	/*
	 * @Author: Anindita
	 * @Added on 29/07/22
	 * @Description: Checks if Order Got Placed 
	 */
	public boolean checkIfOrderPlaced() throws IOException{
		try {
			WebDriver driver=DriverModule.getWebDriver();
			String title=driver.getTitle();
			if (title.equalsIgnoreCase("Order Confirmation | VET online Site"))
                {Reporting.updateTestReport("User was present in the order confirmation page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return true;}
            else
                {Reporting.updateTestReport("User was not on the order confirmation page" , CaptureScreenshot.getScreenshot(SS_path),
                        StatusDetails.FAIL);
                return false;}
		}
		catch(Exception e) {
			Reporting.updateTestReport("User was not on the order confirmation page with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}

    
    /*
	 * @Author: Anindita
	 * @Added on 01/08/22
	 * @Description: Clicks on the manage subscription tab in my account page
	 */
    public void clickOnManageSubscription() throws IOException{
    	try {
    		
    		ManageSubscription.click();
    		Reporting.updateTestReport("Manage Subscription was successfully clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
    	}
    	catch(Exception e){
			Reporting.updateTestReport("Manage Subscription was not clicked.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
    }
    /*
   	 * @Author: Anindita
   	 * @Added on 01/08/22
   	 * @Description: Checks if the auto renew toggle is on in my account page
   	 */
    public boolean checkIfAutoRenewToggleOn() throws IOException{
    try {
    	if(AutoRenewToggleOn.isDisplayed()) {
    		Reporting.updateTestReport("Auto Renew toggle is on",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
    		return true;
    	}
    	else {
    		Reporting.updateTestReport("Auto Renew toggle is off",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
    		return false;
    	}
    }
    catch(Exception e){
		Reporting.updateTestReport("There was some error.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		return false;
	}
    }
    /*
   	 * @Author: Anindita
   	 * @Added on 01/08/22
   	 * @Description: Clicks on the auto renew toggle button in my account page
   	 */
    public void clickOnAutoRenewToggle() throws IOException{
    	try {
    		AutoRenewToggleButton.click();
    		Reporting.updateTestReport("Auto Renew toggle was successfully clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
    	}
    	catch(Exception e){
    		Reporting.updateTestReport("Auto Renew toggle couldn't be clicked.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
    	}
    }
    //WPG Card methods
    /*
     * @Author: Anindita
     * @Description: Enters the Card Number in WPG iframe
     */
    public void enterCardNumberWPG(String cardNumber) throws IOException{
    	try {
    		CardNumberWPG.sendKeys(cardNumber);
    		Reporting.updateTestReport("Card number: "+cardNumber+" was entered in WPG iframe",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
    	}
    	catch(Exception e){
    		Reporting.updateTestReport("Card number couldn't be entered in WPG iframe.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
    	}
    }
    /*
     * @Author: Anindita
     * @Description: Selects the Expiry month in WPG iframe
     */
    public void selectExpiryMonthWPG() throws IOException{
    	try {
    		ExpiryMonthDropdownWPG.click();
    		OptionExpiryMonthWPG.click();
    		Reporting.updateTestReport("September was selected in WPG iframe",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
    	}
    	catch(Exception e){
    		Reporting.updateTestReport("Expiry month couldn't be selected in WPG iframe.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
    	}
    }
    /*
     * @Author: Anindita
     * @Description: Selects the Expiry year in WPG iframe
     */
    public void selectExpiryYearWPG() throws IOException{
    	try {
    		ExpiryYearDropdownWPG.click();
    		OptionExpiryYearWPG.click();
    		Reporting.updateTestReport("2022 was selected in WPG iframe",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
    	}
    	catch(Exception e){
    		Reporting.updateTestReport("Expiry year couldn't be selected in WPG iframe.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
    	}
    }
    /*
     * @Author: Anindita
     * @Description: Enters the security code in WPG iframe
     */
    public void enterSecurityCodeWPG(String cvv) throws IOException{
    	try {
    		SecurityCodeWPG.sendKeys(cvv);
    		Reporting.updateTestReport(cvv+" Security code was entered in WPG iframe",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
    	}
    	catch(Exception e){
    		Reporting.updateTestReport("Security code couldn't be entered in WPG iframe.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
    	}
    }
    /*
     * @Author: Anindita
     * @Description: Clicks on the make payment button in WPG iframe
     */
    public void clickOnMakePaymentButtonWPG() throws IOException{
    	try {
    		MakePaymentButtonWPG.click();
    		Reporting.updateTestReport("MakePayment Button was clicked in WPG iframe",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
    	}
    	catch(Exception e){
    		Reporting.updateTestReport("MakePayment Button couldn't be clicked in WPG iframe.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
    	}
    }
    /*
     * @Author: Anindita
     * Description: Returns the DiscountCode element
     */
    public WebElement returnDiscountCodeField()
    {
    	try {
    		return DiscountCode;
    	}
    	catch(Exception e) {
    	return null;}
    }
    
    /*
	 * @Author: Vishnu
	 */
	public void ForgotchangePassword() {
		try {
			ForgotChangePassword.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/*
	 * @Author: Vishnu
	 * 
	 */
	public void RetriveLoginInfo(String forgottememail) throws IOException {
		try {
			ForgottenEmail.sendKeys(forgottememail);
			Reporting.updateTestReport("Email: " + forgottememail + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Please enter a Valid Email: " + forgottememail + " address ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
	}
	/*
	 * @Author: Vishnu
	 * 
	 */
	public void clickOnSubmit() throws IOException {
		try {
			Submit.click();
			Reporting.updateTestReport("Submit button was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Login button couldn't be clicked with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Vishnu
	 * 
	 */
	public String AlertMessage() throws IOException {
		try {
			String str = AlertMessage.getText();
			Reporting.updateTestReport(
					"Password reset instructions have been sent to your e-mail address. Please contact customer support if you require additional assistance. ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return str;

		} catch (Exception e) {
			Reporting.updateTestReport("Invalid Instructions" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		}
		return "";
	}
	/*
	 * @Author: Vishnu
	 * 
	 */
	public void enteryopmail(String username) throws IOException {
		try {
			Enteryopmail.sendKeys(username);
			Reporting.updateTestReport("Email entered : " + username + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter Email Id", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		}

	}
	/*
	 * @Author: Vishnu
	 * 
	 */
	public void clickonbutton() throws IOException {
		try {
			clickonbutton.click();
			Reporting.updateTestReport("Arrow button clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on arrowbutton", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);

		}
	}
	

	
	/*
	 * @Author: Vishnu
	 * 
	 */
	public void ResetPwd(String upassword) throws IOException {
		try {
			updatepassword.sendKeys(upassword);
			Reporting.updateTestReport("Password : " + upassword + " was entered successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter Password " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Author: Vishnu
	 * 
	 */
	public void ResetConfirmPassword(String cpassword) throws IOException {
		try {
			ConfirmPasswordInPasswordUpdatePage.sendKeys(cpassword);
			Reporting.updateTestReport("Confirm Password : " + cpassword + " was entered successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter ConfirmPassword " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	
	/*
	 * @Author: Vishnu
	 * 
	 */
	public void ResetPassSubmit() throws IOException {
		try {
			Resetsubmit.click();
			Reporting.updateTestReport("Submit was clicked successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed click on Submit button on ResetPassword Page " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 */
	public String PasswordResetSuccess() throws IOException {
		try {
			String rmessage = PasswordResetMessage.getText();
			Reporting.updateTestReport("Success! You can login using your new Password",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return rmessage;

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to updated the Password",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		return "";
	}
	
	
	//Arun
	/*
	 * Author : Arun 
	 * Description :This clicking YOPMailLogo for Entering the New YOP MailID
	 */
	public void clickOnYOPMailLogo() throws IOException {

		try {
			YOPMailLogo.click();
			Reporting.updateTestReport("YOPMailLogo has been clicked", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch (Exception e) {
			Reporting.updateTestReport("YOPMailLogo is not clicked with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * Author : Arun 
	 * Description :This clicking the Refresh Button after entered the YOP MailID
	 */
	public void clickOnRefreshButton() throws IOException {

		try {
			RefreshButton.click();
			Reporting.updateTestReport("Mail has been received successfully for Refund.", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch (Exception e) {
			Reporting.updateTestReport("Mail not recived properly for Refund. with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	
	
	/*
	 * Author : Arun 
	 * Description :Entering the User MailID in YOPMailInbox
	 */
	public void enterYOPUserMailID(String yopUserMailID) throws IOException {

		try {
			YOPUserMailID.clear();
			YOPUserMailID.sendKeys(yopUserMailID);
			Reporting.updateTestReport("yopUserMailID: " + yopUserMailID + " has been entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("yopUserMailID did not entered properly with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	//********End yopmail*********
	
	

	/*
	 * Author : Arun 
	 * Description :This flow using for Save the profile after edited.
	 */
	public void clickOnProfileSaveButton() throws IOException {

		try {
			ProfileSaveButton.click();
			Reporting.updateTestReport("ProfileSaveButton was clicked successfully", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch (Exception e) {
			Reporting.updateTestReport("ProfileSaveButton was not clicked with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * Author : Arun 
	 * Description :This flow used to edit profile LastName
	 */
	public void editProfileLastName(String profilelastname) throws IOException {

		try {
			ProfileLastName.clear();
			ProfileLastName.sendKeys(profilelastname);
			Reporting.updateTestReport("ProfileLastName is : " + profilelastname + " has been changed successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("ProfileLastName is not changed properly with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	
	/*
	 * Author : Arun 
	 * Description :This flow used to edit profile Firstname
	 */
	public void editProfileFirstname(String profileFirstname) throws IOException {

		try {
			ProfileFirstName.clear();
			ProfileFirstName.sendKeys(profileFirstname);
			Reporting.updateTestReport("profileFirstname : " + profileFirstname + " has been changed successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("profileFirstname is not changed properly with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	
	/*
	 * Author : Arun 
	 * Description :This Clicking on GetStartedButton
	 */
	public void clickOnGetStarted() throws IOException {

		try {
			clickOnGetStarted.click();
			Reporting.updateTestReport("Get Started button was clicked successfully", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch (Exception e) {
			Reporting.updateTestReport("Get Started button was not licked with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * Author : Arun 
	 * Description :clicking On ContinueButton to LoginPage
	 */
	public void clickOnContinueButton() throws IOException {

		try {
			clickOnContinueButton.click();
			Reporting.updateTestReport("Continue button was clicked successfully", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch (Exception e) {
			Reporting.updateTestReport("Continue button was not licked with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
		
	
	/*
	 * Author : Arun 
	 * Description : This clicking On SubmitButton
	 */
	public void clickOnSubmitButton() throws IOException
	{
		try {
			SumbitButton.click();
			Reporting.updateTestReport("SubmitButton was clicked successfully ", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("SubmitButton was not licked with the error message "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	

	/*
	 * @Date: 02/01/23
	 * @Author: Anindita
	 * @Description: Fetches the tax from Order Confirmation page
	 */
	public String fetchTax() throws IOException{
		try {
			String tax=Tax.getText();
			Reporting.updateTestReport("Tax: "+tax+" was fetched successfully", CaptureScreenshot.getScreenshot(SS_path),
                    StatusDetails.PASS);
			return tax;
		}
		catch(Exception e) {
			Reporting.updateTestReport("Tax was not fetched with error message " + e.getClass().toString(),
                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
            return "";
		}
	}
	
	/*
	 * @Date: 02/01/23
	 * @Author: Anindita
	 * @Description: Fetches the Order total from Order Confirmation page
	 */
	public String fetchTotal() throws IOException{
		try {
			String total=OrderTotal.getText();
			Reporting.updateTestReport("Order Total: "+total+" was fetched successfully", CaptureScreenshot.getScreenshot(SS_path),
                    StatusDetails.PASS);
			return total;
		}
		catch(Exception e) {
			Reporting.updateTestReport("Order Total was not fetched with error message " + e.getClass().toString(),
                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
            return "";
		}
	}
	
	/*
	 * @Date: 02/01/23
	 * @Author: Anindita
	 * @Description: Enters the email id in yopmail
	 */
	public void enterEmailIdInYopmail(String username) throws IOException {
		try {
			YopmailEmailIdField.clear();
			Thread.sleep(1000);
			YopmailEmailIdField.sendKeys(username);
			Reporting.updateTestReport("Email entered : " + username + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter Email Id", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		}

	}
	
	/*
	 * @Date: 02/01/23
	 * @Author: Anindita
	 * @Description: Clicks on the arrow button in yopmail
	 */
	public void clickOnArrowButton() throws IOException {
		try {
			ArrowButton.click();
			Reporting.updateTestReport("Arrow button clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on arrow button", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Date: 16/12/22
	 * @Description: Clicks on the reset password link in the mail
	 */
	public void clickOnResetPasswordLink() throws IOException {
		try {
			ResetPasswordLink.click();
			Reporting.updateTestReport("Reset password link was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Click on Reset Password link " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 15/12/22
	 * Description : Select Country From DropDown in billing
	 */
	public void selectCountry(String country) throws IOException {
		try {
			
			Select selExpirationMonth = new Select(SelectCountryDropDown);
			selExpirationMonth.selectByVisibleText(country);
			Reporting.updateTestReport("Country has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select country " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * Date: 15/12/22
	 * Description :Selecting State from Dropdpwn in Shipping Page
	 */
	public void enterState(String state) throws IOException {
		try {
			Select selState = new Select(StateDropDown);
			selState.selectByVisibleText(state);
			Reporting.updateTestReport("State: "+state+" has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select State " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
		

}

   





	