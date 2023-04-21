package PageObjectRepo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Test_Suite.AGS_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.StatusDetails;

public class app_AGS_Repo {
	AGS_Test_Suite AGS_Test;
	public String SS_path=AGS_Test.SS_path;

	//Homepage 

	@FindBy(xpath="//a[@class='header-link']")
	WebElement HomepageTitle;
	@FindBy(id="subscribe-tile")
	WebElement SubScribeNowTabInHomePage;
	@FindBy(id="login")
	WebElement LoginTabInHomeopage;
	@FindBy(xpath="//img[@src='https://dev.graphicstandards.com/wp-content/uploads/2016/03/logo.png']")
	WebElement WileyLogoInHomepageFooter;

	//Subscription option (Monthly / Yearly) page

	@FindBy(id = "yearly")
	WebElement YearlySubscriptionButton;
	@FindBy(id = "monthly")
	WebElement MonthlySubscriptionButton;

	//Cart page

	@FindBy(xpath="(//button[contains(text(),'Continue')])[2]")
	WebElement ContinueButtonCartPage;
	@FindBy(id="discountCodeValue")
	WebElement DiscountCode;
	@FindBy(xpath="//button[@class='button form-button button-apply discount-code-apply']")
	WebElement DiscountApply;

	//Login / Registration page

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
	WebElement ConfirmPassword;
	@FindBy(xpath="//button[text()='Create Account'] ")
	WebElement CreateAccountButton;
	@FindBy(name="j_username")
	WebElement ExistingUserId;
	@FindBy(name="j_password")
	WebElement ExistingUserPassword;
	@FindBy(xpath="//*[@id=\"loginForm\"]/div[2]/div/button")
	WebElement LoginButton;

	//Billing address page during checkout

	@FindBy(id="addressFirstName")
	WebElement AddressFirstName;
	@FindBy(id="addressSurname")
	WebElement AddressLastName;
	@FindBy(id = "address.country")
	WebElement SelectCountryDropDown;
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

	//Card Information page

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
	@FindBy(name="number")
	WebElement CardNumber;
	@FindBy(name="expiry")
	WebElement ExpiryDate;
	@FindBy(name="cvc")
	WebElement CVC;
	@FindBy(id="submit")
	WebElement PaymentSubmit;

	//Order confirmation page

	@FindBy(xpath="//div[@class='table-order-row']/div[1]")
	WebElement Order_Id;
	@FindBy(xpath="//div[contains(text(),'Tax:')]/following-sibling::div[@class='table-order-cell']")
	WebElement Tax;
	@FindBy(xpath="//div[contains(text(),'Total:')]/following-sibling::div[@class='table-order-cell']")
	WebElement OrderTotal;
	@FindBy(xpath="//a[@href='/logout']")
	WebElement LogoutButton;

	//Change password tab in my account

	@FindBy(xpath="//span[text()=' My Account']")
	WebElement MyAccountText;
	@FindBy(xpath="//span[text()='Change Password']")
	WebElement ChangePasswordText;
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
	@FindBy(xpath="//div[@class='alert alert-info alert-dismissable']")
	WebElement PasswordResetSuccessMessageInMyAccount;

	//Reset password from login page

	@FindBy(xpath = "//a[@class='recover-password']")
	WebElement ForgotChangePassword;
	@FindBy(id = "forgottenPwd.email")
	WebElement ForgottenEmail;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement Submit;
	@FindBy(xpath = "//div[@class='alert alert-info alert-dismissable']")
	WebElement PasswordResetInstructionSentMessage;
	@FindBy(xpath = "//input[@placeholder='Enter your inbox here']")
	WebElement YopmailEmailIdField;
	@FindBy(xpath = "//button[@title='Check Inbox @yopmail.com']")
	WebElement ArrowButton;
	@FindBy(id = "refresh")
	WebElement RefreshButton;
	@FindBy(xpath = "(//main[@class='yscrollbar']/div/div/div/table/tbody/tr/td/center/table/tbody/tr/td)[2]/p[3]/a[contains(text(),'Click here to change your password')]")
	WebElement ResetPasswordLink;
	@FindBy(id = "updatePwd.pwd")
	WebElement NewPasswordField;
	@FindBy(id = "updatePwd.checkPwd")
	WebElement ConfirmNewPasswordField;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement SubmitButtonInResetPasswordPage;	
	@FindBy(xpath = "//div[@class='alert alert-info alert-dismissable']")
	WebElement PasswordResetSuccessMessage;

	//My profile tab in My account

	@FindBy(name = "firstName")
	WebElement ProfileFirstName;	
	@FindBy(name = "lastName")
	WebElement ProfileLastName;	
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	WebElement MyAccountPageSaveButton;
	@FindBy(xpath="//div[@class='alert alert-info alert-dismissable']")
	WebElement SuccessMessageAfterUserDataUpdation;

	//Edit payment tab in My account

	@FindBy(xpath="//span[text()='Edit Payment']")
	WebElement EditPayment;
	@FindBy(xpath="//button[contains(text(),'Update Credit Card')]")
	WebElement UpdateCreditCardButton;
	@FindBy(xpath="//td[@class='card-number']")
	WebElement CardNumberEditPayment;

	//Manage subscription tab in My account

	@FindBy(xpath="//input[@checked='checked']")
	WebElement AutoRenewToggleOn;
	@FindBy(id="auto_renew")
	WebElement AutoRenewToggleButton;
	@FindBy(xpath="//span[text()='Manage Subscriptions']")
	WebElement ManageSubscription;

	//Methods
	/*
	 * @Description: Clicks on Yearly Subscription button
	 * @Date: 15/12/22
	 */
	public void clickOnYearlySubscriptionButton() throws IOException {
		try {
			YearlySubscriptionButton.click();
			Reporting.updateTestReport("Yearly Subscription button was clicked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Yearly Subscription button was not clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Description: Clicks on Continue button In Cart Page
	 * @Date: 15/12/22
	 */
	public void clickOnContinueButtonCartPage() throws IOException {
		try {
			ContinueButtonCartPage.click();
			Reporting.updateTestReport("Continue Button in Cart Page was clicked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Continue Button in Cart Page was not clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
	 * @Description: Enters the email id in Create account form
	 */
	public String enterEmailId() throws IOException {
		try {
			String dateTime= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String emailId="autoags"+dateTime+"@yopmail.com";
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
	 *@Date: 15/12/22
	 * @Description: Enters the password in Create account form
	 */
	public void enterPassword(String password) throws IOException
	{
		try {
			Password.sendKeys(password);
			ConfirmPassword.sendKeys(password);
			Reporting.updateTestReport("Password: "+password+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Password was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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

	/*
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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

	//WPG Card methods
	/*
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * Date: 15/12/22
	 * Description : Fetching OrderID From Order Confirmation page.
	 */
	public String fetchOrderId() throws IOException {
		try {
			String id = Order_Id.getText();
			String[] A=id.split("#");
			String[] B=A[1].split(" ");
			String orderId=B[0];
			Reporting.updateTestReport("Order id: "+orderId+" was fetched successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
			return orderId;
		} catch (Exception e) {
			Reporting.updateTestReport("Order id was not fetched with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
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
	 * @Date: 15/12/22
	 * @Description: Clicks on logout button
	 */
	public void logOut(WebDriver driver) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			LogoutButton.click();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'ARCHITECTURAL GRAPHIC STANDARDS ONLINE')]")));
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
	 * @Date: 17/02/23
	 * @Description: Hits the logout url
	 */
	public void logOutWithURL(WebDriver driver,String url) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(url);
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'ARCHITECTURAL GRAPHIC STANDARDS ONLINE')]")));
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
	 * @Description: Clicks on Monthly Subscription button
	 * @Date: 15/12/22
	 */
	public void clickOnMonthlySubscriptionButton() throws IOException {
		try {
			MonthlySubscriptionButton.click();
			Reporting.updateTestReport("Monthly Subscription button was clicked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Monthly Subscription button was not clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 16/12/22
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
	 * @Date: 16/12/22
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
	 * @Date: 16/12/22
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
	 * @Date: 16/12/22
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
	 * @Date: 16/12/22
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
	 * @Date: 16/12/22
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
	 * @Date: 16/12/22
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
	 * @Date: 16/12/22
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
	 * @Date: 16/12/22
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
	 * @Date: 16/12/22
	 * @Description: Checks if the password updation alert is present or not in Edit password My Account page
	 */
	public String isPasswordResetSuccessMessagePresent() throws IOException{
		try {
			if(PasswordResetSuccessMessageInMyAccount.isDisplayed()) return PasswordResetSuccessMessageInMyAccount.getText();
			else return "";
		}
		catch(Exception e){
			return "";
		}
	}

	/*
	 * @Date: 16/12/22
	 * @Description: Clicks on the forgot password link in the login page
	 */
	public void clickOnForgotPassword() throws IOException {
		try {
			ForgotChangePassword.click();
			Reporting.updateTestReport("Forgot password link was clicked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Forgot password link couldn't be clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Date: 16/12/22
	 * @Description: Enters the email id to get the forgot password mail
	 */
	public void enterEmailIdToGetResetPasswordMail(String forgottememail) throws IOException {
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
	 * @Date: 16/12/22
	 * @Description: Submits the email id to get the forgot password mail 
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
	 * @Date: 16/12/22
	 * @Description: Checks if the alert message is present after entering the mail id
	 */
	public void checkPasswordResetInstructionSentMessage() throws IOException {
		try {
			String passwordResetInstructionSentMessage = PasswordResetInstructionSentMessage.getText();
			Reporting.updateTestReport(passwordResetInstructionSentMessage,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Invalid Instructions" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		}

	}
	/*
	 * @Date: 16/12/22
	 * @Description: Enters the email id in yopmail to check the inbox
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
	 * @Date: 16/12/22
	 * @Description: Clicks on the arrow button in yopmail to check the mail
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
	 * @Description: Clicks on the refresh button in yopmail to get the new mail
	 */
	public void clickOnRefreshButton() throws IOException {
		try {
			RefreshButton.click();
			Reporting.updateTestReport("mail box refreshed successfully ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to refresh", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}
	/*
	 * @Date: 16/12/22
	 * @Description: Clicks on the reset password link in the mail for forgot password scenario
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
	 * @Date: 16/12/22
	 * @Description: Enters the new password in the reset password page for forgot password scenario
	 */
	public void enterNewPasswordInResetPassword(String password) throws IOException {
		try {
			NewPasswordField.sendKeys(password);
			Reporting.updateTestReport("New Password : " + password + " was entered successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter Password " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 16/12/22
	 * @Description: Enters the new password in the Confirm password field in reset password page for forgot password scenario
	 */
	public void enterConfirmPasswordInResetPassword(String password) throws IOException {
		try {
			ConfirmNewPasswordField.sendKeys(password);
			Reporting.updateTestReport("Confirm Password : " + password + " was entered successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter ConfirmPassword " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}


	/*
	 * @Date: 16/12/22
	 * @Description: Submits the reset password form by clicking on the submit button for forgot password scenario
	 */
	public void clickOnResetPasswordSubmit() throws IOException {
		try {
			SubmitButtonInResetPasswordPage.click();
			Reporting.updateTestReport("Submit was clicked successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed click on Submit button on ResetPassword Page " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 16/12/22
	 * @Description: Returns the success message in the reset password page for forgot password scenario
	 */
	public void checkResetPasswordSuccessMessage() throws IOException {
		try {
			if(PasswordResetSuccessMessage.isDisplayed()) {
				String message = PasswordResetSuccessMessage.getText();
				Reporting.updateTestReport(message,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else {
				Reporting.updateTestReport("Reset password success message was not displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		} catch (Exception e) {
			Reporting.updateTestReport("Reset password success message was not displayed",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 16/12/22
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
	 * @Date: 19/12/22
	 * @Description :This flow is used to edit profile LastName
	 */
	public void editProfileLastName(String profilelastname) throws IOException {

		try {
			ProfileLastName.clear();
			ProfileLastName.sendKeys(profilelastname);
			Reporting.updateTestReport("Profile LastName has been updated to " + profilelastname + " successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("ProfileLastName is not changed properly with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 19/12/22
	 * @Description :Clicks on the Save button after in my account section after editing
	 */
	public void clickOnMyAcountSaveButton() throws IOException {

		try {
			MyAccountPageSaveButton.click();
			Reporting.updateTestReport("Save Button was clicked successfully in the My account page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch (Exception e) {
			Reporting.updateTestReport("Save Button was not clicked in the My account page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 19/12/22
	 * @Description: Checks if the success message after the any data in my account gets updated
	 */
	public void checkSuccessMessageAfterUserDataUpdation() throws IOException {
		try {
			if(SuccessMessageAfterUserDataUpdation.isDisplayed())
				Reporting.updateTestReport("The success message was shown: "+SuccessMessageAfterUserDataUpdation.getText(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("The alert message was not shown ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("The alert message was not shown ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 19/12/22
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
	 * @Date: 19/12/22
	 * @Description :Clicks on the Update Credit card button in edit payment section
	 */
	public void clickOnUpdateCreditCardButton() throws IOException {

		try {
			UpdateCreditCardButton.click();
			Reporting.updateTestReport("Update Credit Card Button was clicked successfully in the edit payment page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch (Exception e) {
			Reporting.updateTestReport("Save Button was not clicked in the edit payment page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 19/12/22
	 * @Description: Fetches the last 4 digits of updated card number
	 */
	public String fetchLastFourCardNumberDigit() throws IOException{
		try {
			String card=CardNumberEditPayment.getText();
			String lastFourChars = "";  

			if (card.length() > 4) {
				lastFourChars = card.substring(card.length() - 4);
			} else {
				lastFourChars = card;
			}
			Reporting.updateTestReport("The last four digit of the card: "+lastFourChars+" were returned successfully", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return lastFourChars;
		}
		catch(Exception e){
			Reporting.updateTestReport("The last four digit of the card couldn't be returned", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Date: 20/12/22
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
			Reporting.updateTestReport("Auto Renew toggle is off",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return false;
		}
	}

	/*
	 * @Date: 20/12/22
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
	 * @Date: 20/12/22
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

	/*
	 * @Date: 31/03/23
	 * @Description: Checks if the title is present or not in home page
	 */
	public void checkHomePageTitle() throws IOException {
		try {
			String title=HomepageTitle.getText();
			Reporting.updateTestReport("Title: "+title+" was present in the homepage", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Title was not present in homepage",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/03/23
	 * @Description: Checks if the Subscribe Now tab is present or not in home page
	 */
	public void checkSubScribeNowTabInHomePage() throws IOException {
		try {
			if(SubScribeNowTabInHomePage.isDisplayed())
				Reporting.updateTestReport("Subscribe now button was present in homepage", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Subscribe now button was not present in homepage",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Subscribe now button was not present in homepage",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/03/23
	 * @Description: Checks if the Login tab is present or not in home page
	 */
	public void checkLoginTabInHomeopage() throws IOException {
		try {
			if(LoginTabInHomeopage.isDisplayed())
				Reporting.updateTestReport("Login button was present in homepage", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Login button was not present in homepage",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Login button was not present in homepage",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/03/23
	 * @Description: Checks if the Wiley logo is present or not in home page footer
	 */
	public void checkWileyLogoInHomepageFooter() throws IOException {
		try {
			if(WileyLogoInHomepageFooter.isDisplayed())
				Reporting.updateTestReport("Wiley Logo was present in homepage footer", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Wiley Logo was not present in homepage footer",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Wiley Logo was not present in homepage footer",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

}