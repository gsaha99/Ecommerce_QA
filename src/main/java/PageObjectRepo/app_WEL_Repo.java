package PageObjectRepo;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestSuite.WEL_Prod_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

public class app_WEL_Repo {
	WEL_Prod_Test_Suite WEL_Test;
	public String SS_path = WEL_Test.SS_path;

	// Homepage
	@FindBy(xpath = "//a[@aria-label='login']")
	WebElement LoginButtonHomepage;

	// Login/ Registration page

	@FindBy(xpath = "//a[@href='https://www.efficientlearning.com/register']")
	WebElement CreateOneLinkInLoginPage;
	@FindBy(id = "register.studentFirstName")
	WebElement FirstNameFieldInRegistrationForm;
	@FindBy(id = "register.studentLastName")
	WebElement LastNameFieldInRegistrationForm;
	@FindBy(id = "register.email")
	WebElement EmailIdFieldInRegistrationForm;
	@FindBy(id = "register.confirmEmail")
	WebElement ConfirmEmailIdFieldInRegistrationForm;
	@FindBy(id = "password")
	WebElement PasswordFieldInRegistrationForm;
	@FindBy(xpath = "//label[@class='control-label welCustomCheckbox createNewAccountCheckbox' and @for='termsCheck']")
	WebElement CheckboxInRegistrationForm;
	@FindBy(xpath = "//button[@data-form_id='wileyRegisterForm']")
	WebElement CreateAccountButtonInRegistrationForm;
	@FindBy(id = "username")
	WebElement UserNameInLoginPage;
	@FindBy(id = "password")
	WebElement UserPasswordInLoginPage;
	@FindBy(xpath = "//input[@value='LOG IN']")
	WebElement StandaloneLoginButton;

	// My Account page

	@FindBy(xpath = "//a[@aria-label='account']")
	WebElement AccountButtonOnHomepage;
	@FindBy(xpath = "//button[contains(text(),'I Accept')]")
	WebElement AcceptButtonOnWileyWELPrivacyAgreement;
	@FindBy(id = "signout")
	WebElement SignOutButtonInMyAccountPage;

	// Products related xpaths

	@FindBy(xpath = "//a[@data-for='productTooltipCMA' and @data-key='1']")
	WebElement CMALinkOnHomepage;
	@FindBy(xpath = "//a[@data-for='productTooltipCIA' and @data-key='2']")
	WebElement CIALinkOnHomepage;
	@FindBy(xpath = "//a[@data-for='productTooltipCFA' and @data-key='0']")
	WebElement CFALinkOnHomepage;
	@FindBy(xpath = "//a[@data-for='productTooltipCPA' and @data-key='0']")
	WebElement CPALinkOnHomepage;
	@FindBy(xpath = "//button[@class='shop-courses-btn  ']")
	WebElement ExploreCourseButton;
	@FindBy(xpath = "//a[@href='/cma/products/platinum-cma-review-course/']/button[contains(text(),'VIEW COURSE OPTIONS')]")
	WebElement CMAViewCourseButton;
	@FindBy(xpath = "(//a[@href='/cia/products/cia-review-course/' and contains(text(),'View Course')])[1]")
	WebElement CIAViewCourseButton;
	@FindBy(xpath = "(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")
	WebElement GetCMACourseButton;
	@FindBy(xpath = "//button[@type='submit' and contains(text(),'ADD TO CART')]")
	WebElement AddToCartButton;
	@FindBy(xpath = "//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 col-xl-4 shop-courses-btn-container']/a/button")
	WebElement ShopCourseButton;
	@FindBy(xpath = "//span[@class='apply-discount-link']")
	WebElement ApplyStudentDiscountLink;
	@FindBy(xpath = "//p[@class='current-price-link']/a[contains(text(),'Switch to student discount price')]")
	WebElement SwitchToStudentDiscountLink;
	@FindBy(xpath = "//a[@href='/cfa/products/']/button[contains(text(),'VIEW COURSE OPTIONS')]")
	WebElement CFAViewCourseButton;
	@FindBy(xpath = "//a[@href='/cpa/products/platinum-cpa-review-course/']/button[contains(text(),'VIEW COURSE')]")
	WebElement CPAViewCourse;
	@FindBy(xpath = "//a[@href='/cfa/products/level-1/platinum-cfa-course/' and contains(text(),'View Course')]")
	WebElement CFAViewCourseLink;
	@FindBy(xpath = "//div[@class='btn-group btn-group-toggle']/label[2]")
	WebElement CMAeBook;
	@FindBy(xpath = "//button[@type='submit' and @class='add-to-cart-btn  ']")
	WebElement AddToCartButtonOnPDP;
	@FindBy(xpath = "//div[@class='btn-group btn-group-toggle']/label[@class='btn btn-secondary print-ebook active']")
	WebElement CPAPrinteBook;
	@FindBy(xpath = "//div[@class='btn-group btn-group-toggle']/label[1]")
	WebElement PrintEbook;
	@FindBy(xpath = "//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_2']/a[1]")
	WebElement CFAProduct;

	// Cart page

	@FindBy(id = "cartCheckoutBtn")
	WebElement CheckoutButtonOnCartPage;
	@FindBy(id = "voucher-remove-button_0")
	WebElement CouponRemoveButton;
	@FindBy(id = "cartPageAddDiscountLink")
	WebElement AddDiscountLink;
	@FindBy(xpath = "(//input[@id='discountCodeValue'])[1]")
	WebElement DiscountValueInCartPage;
	@FindBy(xpath = "(//input[@id='discountCodeValue'])[2]")
	WebElement Discount;
	@FindBy(id = "discountApplyBtn")
	WebElement DiscountApplyButtonInCartPage;
	@FindBy(id="backTocartNavbarMainId")
	WebElement BackToCartButton;
	@FindBy(xpath = "//select[@id='quantity_0']")
	WebElement SelectQuantityDropdown;

	// Checkout login/ registration page

	@FindBy(id = "guest.email")
	WebElement NewUserIdInCheckoutLoginRegistration;
	@FindBy(xpath = "//div[@id='guestCheckoutRegBtn']/button[@data-form_id='guestForm']")
	WebElement CreateAccountButtonInCheckoutLoginRegistration;
	@FindBy(id = "guest.confemail")
	WebElement ConfirmEmailIdInCheckout;
	@FindBy(id = "pwd")
	WebElement PasswordInCheckout;
	@FindBy(xpath = "//span[@class='welCheckoutContBtnText' and contains(text(),'Save and Continue')]")
	WebElement SaveAndContinueButton;
	@FindBy(xpath = "//button[@data-form_id='loginForm']")
	WebElement LoginButtonInCheckoutLoginPage;
	@FindBy(id = "j_username")
	WebElement ExistingUserNameInCheckoutLoginPage;
	@FindBy(id = "j_password")
	WebElement ExistingUserPasswordInCheckoutLoginPage;
	@FindBy(xpath = "//a[@class='recover-password pull-left checkoutForgotPwdLinkCss']")
	WebElement ForgotPasswordLinkInCheckOutLoginPage;

	// Forgot password page
	@FindBy(id = "forgottenPwd.email")
	WebElement EmailIdOnForgotPassword;
	@FindBy(xpath = "//button[@data-form_id='forgottenPwdForm']")
	WebElement SubmitButtonOnForgotPassword;
	@FindBy(id = "updatePwd.checkPwd")
	WebElement ConfirmNewPasswordField;
	@FindBy(xpath = "(//button[@type='button'])[5]")
	WebElement SubmitButtonInResetPasswordPage;
	@FindBy(id = "updatePwd.pwd")
	WebElement NewPasswordField;
	@FindBy(xpath = "//input[@placeholder='Enter your inbox here']")
	WebElement EnterEmailIdInYopmail;
	@FindBy(xpath = "//button[@title='Check Inbox @yopmail.com']")
	WebElement clickOnCheckInboxButton;

	// Free Trial pages
	@FindBy(xpath = "//button[contains(text(),'FREE TRIAL')]")
	WebElement FreeTrialButton;
	@FindBy(name = "firstName")
	WebElement FreeTrialFname;
	@FindBy(name = "lastName")
	WebElement FreeTrialLname;
	@FindBy(name = "email")
	WebElement FreeTrialEmail;
	@FindBy(name = "country")
	WebElement FreeTrialCountry;
	@FindBy(name = "region")
	WebElement FreeTrialState;
	@FindBy(name = "password")
	WebElement FreeTrialPassword;
	@FindBy(name = "termsAndConditions")
	WebElement FreeTrialTermsAndConditions;
	@FindBy(name = "signUp")
	WebElement FreeTrialSignupCheckbox;
	@FindBy(xpath = "//div[@class='form-box']//button[@class='form-button']")
	WebElement FreeTrialStartButton;
	@FindBy(xpath = "//div[@class='progress-bar-outer-box']//p[contains(text(),'almost')]")
	WebElement CPAFreeTrialConfirmationText;
	@FindBy(id = "j_username")
	WebElement FreeTrialLoginusername;
	@FindBy(id = "j_password")
	WebElement FreeTrialLoginpasssowrd;
	@FindBy(xpath = "//div[@class='modal-content']//button")
	WebElement FreeTrialModelPopUpLogin;
	@FindBy(xpath = "//button[@class='button form-button welCheckoutBtn submitWelForm width100']")
	WebElement FreeTrialSubbmit;
	@FindBy(xpath = "//a[@class='navbar-brand brand-logo-top-desktop']")
	WebElement WELBrandLogonOnFreeTrialPage;

	// Shipping Details
	@FindBy(xpath = "(//div[@id='newAddressBtnDiv']/button[@id='addNewAddressButton'])")
	WebElement EnterNewAddressButton;
	@FindBy(xpath = "//i[contains(text(),'(We cannot ship to PO boxes)')]")
	WebElement DontShipToPOBox;
	@FindBy(id = "firstName")
	WebElement firstName;
	@FindBy(id = "addressSubmit")
	WebElement ShipSaveAndContinue;
	@FindBy(id = "lastName")
	WebElement lastName;
	@FindBy(id = "address.country")
	WebElement ShipCountry;
	@FindBy(id = "street1")
	WebElement AddressLine1;
	@FindBy(id = "address.region")
	WebElement selectState;
	@FindBy(id = "line1")
	WebElement shipAddressLineOne;
	@FindBy(id = "postcode")
	WebElement shipPostCode;
	@FindBy(id = "phone")
	WebElement shipPhonenumber;
	@FindBy(id = "addressSubmit")
	WebElement createAccountButton;
	@FindBy(id = "address.region")
	WebElement EnterState;
	@FindBy(id = "townCity")
	WebElement TownCity;
	@FindBy(xpath = "(//button[@id='wel_use_suggested_address_button']/span[text()='Use Selected Address'])[2]")
	WebElement UseSelectedShippingAddressButtonAddressDoctorPopUp;
	@FindBy(xpath = "//a[@href='/checkout/multi/delivery-address/add/']/span[@class='stepEdit']")
	WebElement ShippingDetailsEditIcon;
	@FindBy(xpath = "//div[@class='row pageMainContainer no-margin']//div[@class='checkout-steps']/a[1]")
	WebElement StudentVerification;

	// Billing Address Detail
	@FindBy(id = "sameAsBillingLabel")
	WebElement ShippingBillingSameAddress;

	@FindBy(id = "address.country")
	WebElement SelectCountryDropDown;
	@FindBy(id = "postalCode")
	WebElement PostalCode;

	@FindBy(id = "city")
	WebElement BillCity;

	@FindBy(id = "phoneNumber")
	WebElement BillPhoneNumber;

	@FindBy(xpath = "(//div[@id='newAddressBtnDiv']/button[@id='addNewBillingAddressButton'])")
	WebElement EnterNewAddressButtonOnBillPage;

	@FindBy(xpath = "(//button[@id='wel_billing_use_suggested_address_button']/span[text()='Use Selected Address'])[2]")
	WebElement UseSelectedBillingAddressButtonAddressDoctorPopUp;

	// Create New Account x paths
	@FindBy(id = "guest.email")
	WebElement GuestUser;

	@FindBy(xpath = "//div[@id='guestCheckoutRegBtn']//span[@class='welCheckoutContBtnText']")
	WebElement CreateAccount;
	@FindBy(id = "guest.confemail")
	WebElement GuestConfinmEmail;
	@FindBy(xpath="(//div[@id='checkoutGuestUserReg'])[1]")
	WebElement LoginCheckoutPageHeading;
	
	@FindBy(xpath="(//div[@class='help-block commonErrorWelStyle'])[1]")
	WebElement ErrorMessageAfterEnteringInvalidEmailId;

	@FindBy(name = "pwd")
	WebElement Password;
	@FindBy(xpath = "//label[@for='agreement']")
	WebElement AgreementCheckbox;

	@FindBy(xpath = "(//span[@class='welCheckoutContBtnText'])[2]")
	WebElement CreateAccountButton;
	@FindBy(xpath = "//label[contains(text(),'Part 1')]")
	WebElement Part1InCIAPDP;

	/*
	 * @Author : Vishnu
	 * 
	 * @Description : Object repo for WEL Credit Card and Paypal, PaypalCredit
	 * Details
	 */

	@FindBy(id = "nameOnCard")
	WebElement CardHolderName;

	@FindBy(id = "number")
	WebElement CardNumber;

	@FindBy(id = "expiryMonth")
	WebElement ExpirationDateForMonth;

	@FindBy(id = "expiryYear")
	WebElement ExpirationDateForYear;

	@FindBy(id = "securityCode")
	WebElement CVV_Number;
	@FindBy(id = "paymentBilling")
	WebElement SaveAndContinueOnCheckOutPage;

	@FindBy(xpath = "//div[@id='billingMultiPaymentOptionValues']/ul/li[3]/a/span[@class='billingPaymentMultiNavTitle']")
	WebElement Paypalpayment;
	@FindBy(xpath = "//div[@id='billingMultiPaymentOptionValues']/ul/li[4]/a/span")
	WebElement PaypalCredit;

	@FindBy(xpath = "//div[@id='discountCodeMessage']")
	WebElement DiscountMessage;

	@FindBy(xpath = "//div[@class='form-group']//input[@id='inputPartnerSearch']")
	WebElement PartnerInputSearch;
	@FindBy(xpath = "//div[@class='container product-categories-container']//div[2]//dd/a[contains(text(),'CMA')]")
	WebElement DeanDortonCMAProduct;

	// Products
	@FindBy(xpath = "//div[@class='fe_flex grid_1']/a[1]")
	WebElement CPALink;

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Clicks on the login button in homepage
	 */
	public void clickOnLoginButtonHomepage() throws IOException {
		try {
			LoginButtonHomepage.click();
			Reporting.updateTestReport("Login button on homepage was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Login button on homepage couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Clicks on the Create One Link In Login Page to do standalone
	 * registration
	 */
	public void clickOnCreateOneLinkInLoginPage() throws IOException {
		try {
			CreateOneLinkInLoginPage.click();
			Reporting.updateTestReport("Create One Link In Login Page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Create One Link In Login Page couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Enters the first name in the registration form
	 */
	public void enterFirstNameInRegistrationForm(String firstname) throws IOException {
		try {
			FirstNameFieldInRegistrationForm.sendKeys(firstname);
			Reporting.updateTestReport("First name: " + firstname + " was entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("First name couldn't be entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Enters the last name in the registration form
	 */
	public void enterLastNameInRegistrationForm(String lastname) throws IOException {
		try {
			LastNameFieldInRegistrationForm.sendKeys(lastname);
			Reporting.updateTestReport("Last name: " + lastname + " was entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Last name couldn't be entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Enters the email id in the registration form
	 */
	public String enterEmailIdInRegistrationForm() throws IOException {
		try {
			String dateTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String emailId = "autovet" + dateTime + "@yopmail.com";
			EmailIdFieldInRegistrationForm.sendKeys(emailId);
			ConfirmEmailIdFieldInRegistrationForm.sendKeys(emailId);
			Reporting.updateTestReport("Email Id: " + emailId + " was entered successfully in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return emailId;

		} catch (Exception e) {
			Reporting.updateTestReport("Email Id couldn't be entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Enters the password in the registration form
	 */
	public void enterPasswordInRegistrationForm(String password) throws IOException {
		try {
			PasswordFieldInRegistrationForm.sendKeys(password);
			Reporting.updateTestReport("Password: " + password + " was entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Password couldn't be entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Clicks on the terms and conditions checkbox
	 */
	public void clickOnCheckboxInRegistrationForm() throws IOException {
		try {
			CheckboxInRegistrationForm.click();
			Reporting.updateTestReport("Terms and conditions checkbox in registration page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Terms and conditions checkbox in registration page couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Clicks on the Create Account button in regsitration page
	 */
	public void clickOnCreateAccountButtonInRegistrationForm() throws IOException {
		try {
			CreateAccountButtonInRegistrationForm.click();
			Reporting.updateTestReport("Create Account button in regsitration page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Create Account button in regsitration page couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Clicks on the Account button in homepage
	 */
	public void clickOnAccountButtonInHomepage() throws IOException {
		try {
			AccountButtonOnHomepage.click();
			Reporting.updateTestReport("Account button in homepage was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Account button in homepage couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Method to click on Accept button on Wiley Privacy Agreement
	 * page
	 */

	public void clickonAcceptButtonOnWileyWELPrivacyAgreement() throws IOException {
		try {
			AcceptButtonOnWileyWELPrivacyAgreement.click();
			Reporting.updateTestReport("I Accept Button button  was clicked Successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to Click on IAcceptButton button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Method to click on SignOut button
	 */

	public void clickOnSignOutButtonInMyAccountPage() throws IOException {
		try {
			SignOutButtonInMyAccountPage.click();
			Reporting.updateTestReport("SignOut button  was clicked Successfully Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to Click on SignOut button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 3/2/23
	 * 
	 * @Description: Logs out the user from WEL and delete all cookies
	 */
	public void logOutWEL(WebDriver driver, String url) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(url);
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'LOG IN')]")));
			Reporting.updateTestReport("User was logged out successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User was couldn't be logged out", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: User name is entered in standalone login page
	 */
	public void enterUserNameInLoginPage(String UserName) throws IOException {
		try {
			UserNameInLoginPage.sendKeys(UserName);
			Reporting.updateTestReport("User Name: " + UserName + " was entered successfully on Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the username with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Password is entered in standalone login page
	 */
	public void enterPasswordInLoginPage(String password) throws IOException {
		try {
			UserPasswordInLoginPage.sendKeys(password);
			Reporting.updateTestReport("Passworde: " + password + " was entered successfully on Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the password with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Method to click on Login button
	 */

	public void clickOnStandaloneLoginButton() throws IOException {
		try {
			StandaloneLoginButton.click();
			Reporting.updateTestReport("Login Button clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Login button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Method to click on CMA Link On Home page
	 */

	public void clickOnCMALinkOnHomepage() throws IOException {
		try {
			CMALinkOnHomepage.click();
			Reporting.updateTestReport("CMA Link On Home page clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on CMA Link On Home page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Method to click on CMA Link On Home page
	 */

	public void clickOnExploreCourseButton() throws IOException {
		try {
			ExploreCourseButton.click();
			Reporting.updateTestReport("Explore Course Button was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Explore Course Button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Method to click on View Course Button in CMA Product page
	 */

	public void clickOnCMAViewCourseButton() throws IOException {
		try {
			CMAViewCourseButton.click();
			Reporting.updateTestReport("View Course Button in CMA Product page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting
					.updateTestReport(
							"Failed to click on View Course Button in CMA Product page with the error message "
									+ e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void clickOnCPAViewCourseButton() throws IOException {
		try {
			CPAViewCourse.click();
			Reporting.updateTestReport("View Course Button in CPA Product page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting
					.updateTestReport(
							"Failed to click on View Course Button in CPA Product page with the error message "
									+ e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Method to click on Get CMA Course Button in CMA Product page
	 */

	public void clickOnGetCMACourseButton() throws IOException {
		try {
			GetCMACourseButton.click();
			Reporting.updateTestReport("Get CMA Course Button in CMA Product page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting
					.updateTestReport(
							"Failed to click on Get CMA Course Button in CMA Product page with the error message "
									+ e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Method to click on Add to cart button
	 */

	public void clickOnAddToCartButton() throws IOException {
		try {
			AddToCartButton.click();
			Reporting.updateTestReport("Add to cart button was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Add to cart button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Method to click on check Out on Cart Page
	 */

	public void clickonCheckOutButtonOnCartPage() throws IOException {
		try {
			CheckoutButtonOnCartPage.click();
			Reporting.updateTestReport("Checkout Button clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Checkout button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Enters the user id in checkout login/ registration page
	 */

	public String enterNewUserIdInCheckoutLoginRegistration() throws IOException {
		try {
			String dateTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String emailId = "autowel" + dateTime + "@yopmail.com";
			NewUserIdInCheckoutLoginRegistration.sendKeys(emailId);
			Reporting.updateTestReport(
					"Email Id: " + emailId + " was entered successfully in checkout login/ registration page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return emailId;
		} catch (Exception e) {
			Reporting
					.updateTestReport(
							"Email Id was not entered in checkout login/ registration page with the error message "
									+ e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Method to click on Create Account button in checkout login/
	 * registration page
	 */
	public void clickOnCreateAccountButtonInCheckoutLoginRegistration() throws IOException {
		try {
			CreateAccountButtonInCheckoutLoginRegistration.click();
			Reporting.updateTestReport(
					"Create Account button in checkout login/ registration page was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Create Account button in checkout login/ registration page with the error message "
							+ e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Method to enter Email address for checkout create account
	 */

	public void enterConfirmEmailIdInCheckout(String emailId) throws IOException {
		try {
			ConfirmEmailIdInCheckout.sendKeys(emailId);
			Reporting.updateTestReport(
					"Email Id: " + emailId + " was entered successfully in the Confirm email id section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Email Id was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date : 5/4/23
	 * 
	 * @Description: Method to enter the password for checkout create account
	 */
	public void enterPasswordInCheckout(String password) throws IOException {
		try {
			PasswordInCheckout.sendKeys(password);
			Reporting.updateTestReport("Password " + password + " entered successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the Password with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Clicks on the Save and Continue button during checkout
	 */
	public void clickOnSaveAndContinue() throws IOException {
		try {
			SaveAndContinueButton.click();
			Reporting.updateTestReport("Save and continue button was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Save and continue button couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
	}

	/*
	 * @Date: 6/4/23
	 * 
	 * @Description: Method to enter Existing user name on checkout Login Page
	 */

	public void enterExistingUserNameInCheckoutLoginPage(String username) throws IOException {
		try {
			ExistingUserNameInCheckoutLoginPage.sendKeys(username);
			Reporting.updateTestReport(
					"Existing User Name : " + username + " was entered successfully in checkout Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Existing User Name was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 6/4/23
	 * 
	 * @Description: Method to enter Existing user password on checkout Login Page
	 */

	public void enterExistingUserPasswordInCheckoutLoginPage(String password) throws IOException {
		try {
			ExistingUserPasswordInCheckoutLoginPage.sendKeys(password);
			Reporting.updateTestReport(
					"Existing User password : " + password + " was entered successfully in checkout Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Existing User password was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 6/4/23
	 * 
	 * @Description: Clicks on Login button in checkout Login Page
	 */

	public void clickOnLoginButtonInCheckoutLoginPage() throws IOException {
		try {
			LoginButtonInCheckoutLoginPage.click();
			Reporting.updateTestReport("Login button was clicked successfully in checkout Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Login button couldn't be clicked with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 6/4/23
	 * 
	 * @Description: Clicks on Forgot password link in checkout Login Page
	 */

	public void clickOnForgotPasswordLinkInCheckOutLoginPage() throws IOException {
		try {
			ForgotPasswordLinkInCheckOutLoginPage.click();
			Reporting.updateTestReport("Forgot password link was clicked successfully in checkout Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Forgot password link couldn't be clicked with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 6/4/23
	 * 
	 * @Description: Method to enter email address on Forgot Password Page
	 */

	public void enterEmailAddressOnForgotPassword(String Email) throws IOException {
		try {
			EmailIdOnForgotPassword.sendKeys(Email);
			Reporting.updateTestReport("Email Id: " + Email + " was entered on email field on Forgot Password Page ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the Email on Forgot Password Page with the error message "
					+ e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	/*
	 * @Date: 6/4/23
	 * 
	 * @Description: Method to click on Submit button on ForgotPassword Page
	 */

	public void clickOnSubmitButtonnForgotPasswordPage() throws IOException {
		try {
			SubmitButtonOnForgotPassword.click();
			Reporting.updateTestReport("Submit button was clicked Successfully on ForgotPassword Page ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click Submit button on Forgot Password Page with the error message "
					+ e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 6/4/23
	 * 
	 * @Description: Method to enter new password in Reset password page
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
	 * @Date: 6/4/23
	 * 
	 * @Description:Method to enter confirm password in Reset password page
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
	 * @Date: 6/4/23
	 * 
	 * @Description: Method to click on Submit button on Reset Password Page
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
	 * @Date: 6/4/23
	 * 
	 * @Description: Enters the email id in Yopmail
	 */
	public void enterEmailIdInYopmail(String username) throws IOException {
		try {
			EnterEmailIdInYopmail.sendKeys(username);
			Reporting.updateTestReport("Email entered : " + username + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter Email Id", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 6/4/23
	 * 
	 * @Description: Clicks on check inbox in yopmail after entering user id
	 */
	public void clickOnCheckInboxButton() throws IOException {
		try {
			clickOnCheckInboxButton.click();
			Reporting.updateTestReport("Arrow button clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on arrowbutton", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 6/4/23
	 * 
	 * @Description: Clicks on Add Discount link in cart page
	 */
	public void clickOnAddDiscountLink() throws IOException {
		try {
			AddDiscountLink.click();
			Reporting.updateTestReport(" Add Discount link in cart page was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Add Discount link in cart page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 6/4/23
	 * 
	 * @Description: Enters the Discount code in cart page
	 */
	public void enterDiscountValueInCartPage(String coupon) throws IOException {
		try {
			DiscountValueInCartPage.sendKeys(coupon);
			Reporting.updateTestReport("Discount code : " + coupon + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter Discount code " + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 6/4/23
	 * 
	 * @Description: Enters the Discount code in cart page
	 */
	public void enterDiscountValue(String coupon) throws IOException {
		try {
			Discount.sendKeys(coupon);
			Reporting.updateTestReport("Discount code : " + coupon + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter Discount code " + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 6/4/23
	 * 
	 * @Description: Clicks on Discount Apply button in cart page
	 */
	public void clickOnDiscountApplyButtonInCartPage() throws IOException {
		try {
			DiscountApplyButtonInCartPage.click();
			Reporting.updateTestReport(" Discount Apply button in cart page was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Discount Apply button in cart page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 6/4/23
	 * 
	 * @Description: Clicks on Discount Remove button in cart page
	 */
	public void clickOnRemoveCouponInCartPage() throws IOException {
		try {
			CouponRemoveButton.click();
			Reporting.updateTestReport(" Discount Remove button in cart page was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Discount Remove button in cart page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to click on CIA Link On Home page
	 */

	public void clickOnCIALinkOnHomepage() throws IOException {
		try {
			CIALinkOnHomepage.click();
			Reporting.updateTestReport("CIA Link On Home page clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on CIA Link On Home page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to click on CFA Link On Home page
	 */

	public void clickOnCFALinkOnHomepage() throws IOException {
		try {
			CFALinkOnHomepage.click();
			Reporting.updateTestReport("CFA Link On Home page clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on CFA Link On Home page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to click on CPA Link On Home page
	 */

	public void clickOnCPALinkOnHomepage() throws IOException {
		try {
			CPALinkOnHomepage.click();
			Reporting.updateTestReport("CPA Link On Home page clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on CPA Link On Home page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to click on Shop courses button in CIA Products
	 */

	public void clickOnShopCourseButton() throws IOException {
		try {
			ShopCourseButton.click();
			Reporting.updateTestReport("Shop Course Button was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Shop Course Button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 5/4/23
	 * 
	 * @Description: Method to click on View Course Button in CIA Product page
	 */

	public void clickOnCIAViewCourseButton() throws IOException {
		try {
			CIAViewCourseButton.click();
			Reporting.updateTestReport("View Course Button in CIA Product page was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting
					.updateTestReport(
							"Failed to click on View Course Button in CIA Product page with the error message "
									+ e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to click on Apply student discount link on products'
	 * page
	 */

	public void clickOnApplyStudentDiscountLink() throws IOException {
		try {
			ApplyStudentDiscountLink.click();
			Reporting.updateTestReport("Apply student discount link CIA Product page was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting
					.updateTestReport(
							"Failed to click on Apply student discount link in CIA Product page with the error message "
									+ e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to click on Switch to student discount link on products'
	 * page
	 */

	public void clickOnSwitchToStudentDiscountLink() throws IOException {
		try {
			SwitchToStudentDiscountLink.click();
			Reporting.updateTestReport("Switch to student discount link CIA Product page was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Switch to student discount link in CIA Product page with the error message "
							+ e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to click on View Course Button in CFA Product page
	 */

	public void clickOnCFAViewCourseButton() throws IOException {
		try {
			CFAViewCourseButton.click();
			Reporting.updateTestReport("View Course Button in CFA Product page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting
					.updateTestReport(
							"Failed to click on View Course Button in CFA Product page with the error message "
									+ e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to click on View Course link in CFA Product page
	 */

	public void clickOnCFAViewCourseLink() throws IOException {
		try {
			CFAViewCourseLink.click();
			Reporting.updateTestReport("View Course link in CFA Product page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on View Course link in CFA Product page with the error message "
					+ e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to enter the Free Trial FirstName
	 */

	public void enterFreeTrialFirstName(String FFName) throws IOException {
		try {
			FreeTrialFname.sendKeys(FFName);
			Reporting.updateTestReport("First: " + FFName + " was entered successfully in the First Name section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("FirstName not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to enter the last name free frail form
	 */

	public void enterFreeTrialLastName(String FLName) throws IOException {
		try {
			FreeTrialLname.sendKeys(FLName);
			Reporting.updateTestReport("Last: " + FLName + " was entered successfully in the First Name section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("LastName not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to enter new user on Free Trial form
	 */

	public String enterFreeTrialNewUser() throws IOException {
		try {
			String dateTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String gEmail = "autowel" + dateTime + "@yopmail.com";
			FreeTrialEmail.sendKeys(gEmail);
			Reporting.updateTestReport("Email Id: " + gEmail + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return gEmail;
		} catch (Exception e) {
			Reporting.updateTestReport("Email Id was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to select the country on free trial form
	 */

	public void selectFreeTrialCountry(String country) throws IOException {
		try {

			Select freeTrialCountry = new Select(FreeTrialCountry);
			freeTrialCountry.selectByVisibleText(country);
			Reporting.updateTestReport("County " + country + " has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select country " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to select the state from Free Trial form
	 */

	public void selectFreeTrialState(String Fstate) throws IOException {
		try {

			Select state = new Select(FreeTrialState);
			state.selectByVisibleText(Fstate);
			Reporting.updateTestReport("State " + Fstate + " has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select country " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to enter the Password on Free Trial form
	 */

	public void enterFreeTrialPassword(String FPassword) throws IOException {
		try {
			FreeTrialPassword.sendKeys(FPassword);
			Reporting.updateTestReport("Password: " + FPassword + " was entered successfully in the Password section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Password not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to click on checkbox for for Free Trial Terms and
	 * Conditions
	 */

	public void clickOnFreeTrialTermsAndCOndtionsCheckBox() throws IOException {
		try {
			FreeTrialTermsAndConditions.click();
			Reporting.updateTestReport("TermsAndConditions Checbox selected Successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on TermsAndConditions Checbox with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to click on SignUp check box on Free Trial form
	 */

	public void clickFreeTrialSignUpCheckBox() throws IOException {
		try {
			FreeTrialSignupCheckbox.click();
			Reporting.updateTestReport("SignupCheckbox Checbox selected Successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on SignupCheckbox Checbox with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to click on Sign In Button on Free Trial form
	 */

	public void clickOnFreeTrialSignInButton() throws IOException {
		try {
			FreeTrialStartButton.click();
			Reporting.updateTestReport("Start Your Free Trial button clicked Successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Start Your Free Trial button  with the error message "
					+ e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to click on WEL Icon on Free Trial form
	 */

	public void clickOnFreeTrialWELIcon() throws IOException {
		try {
			WELBrandLogonOnFreeTrialPage.click();
			Reporting.updateTestReport("WEL Brand Desktop logo was clicked Successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on WEL Brand Desktop logo with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to enter email address on Free Trial Form
	 */

	public void enterFreeTrialEmail(String email) throws IOException {
		try {
			FreeTrialEmail.sendKeys(email);
			Reporting.updateTestReport("existing email Id was entered Successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to enter the existing email id with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to verify the CPA Text on Free Trial form
	 */

	public String checkFreeTrialCPAText() throws IOException {
		try {

			String cpaftext = CPAFreeTrialConfirmationText.getText();
			Reporting.updateTestReport("You’re almost set Pages was successfully loaded",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return cpaftext;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to load the You’re almost set page " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	/*
	 * @Date: 14/4/23
	 * 
	 * @Description: Method to verify the CIA text on Free Trial Form
	 */

	public String checkFreeTrialCIAText() throws IOException {
		try {

			String cpaftext = CPAFreeTrialConfirmationText.getText();
			Reporting.updateTestReport("You’re almost set Pages was successfully loaded",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return cpaftext;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to load the You’re almost set page " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Date: 17/4/23
	 * 
	 * @Description: Method to lick on Free Trial button
	 */

	public void clickOnFreeTrialButton() throws IOException {
		try {
			FreeTrialButton.click();
			Reporting.updateTestReport("Free trial button was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Free_trail button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void clickOnEnterNewAddressButtonOnShippingPage() throws IOException {
		try {
			EnterNewAddressButton.click();
			Reporting.updateTestReport("\"Enter new Address  button clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Enter New Address button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void firstName(String GNAME) throws IOException {
		try {
			firstName.clear();
			Thread.sleep(1000);
			firstName.sendKeys(GNAME);
			Reporting.updateTestReport("First: " + GNAME + " was entered successfully in the First Name section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("FirstName not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to enter the LastName
	 */

	public void lastName(String GLNAME) throws IOException {
		try {
			lastName.clear();
			Thread.sleep(1000);
			lastName.sendKeys(GLNAME);
			Reporting.updateTestReport("Last: " + GLNAME + " was entered successfully in LastName section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("LastName was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to select the country on Shipping Page
	 */

	public void selectShipCountry(String country) throws IOException {
		try {

			Select scountry = new Select(ShipCountry);
			scountry.selectByVisibleText(country);
			Reporting.updateTestReport("County " + country + " has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select country " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to enter the Addrees Line on Shipping Page
	 */

	public void shipAddressLineOne(String ALineOne) throws IOException {
		try {
			shipAddressLineOne.clear();
			Thread.sleep(1000);
			shipAddressLineOne.sendKeys(ALineOne);
			Reporting.updateTestReport(
					"AddressLine1: " + ALineOne + " was entered successfully in the AddressLine1 section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("AddressLine1 was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to enter PostCode on Shipping Page
	 */

	public void shipPostCode(String postalCode) throws IOException {
		try {
			shipPostCode.clear();
			Thread.sleep(1000);
			shipPostCode.sendKeys(postalCode);
			Reporting.updateTestReport("postalcode: " + postalCode + " was entered successfully in the ZipCode section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("ZipCode was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to enter PhoneNumber on Shipping page
	 */

	public void shipPhonenumber(String pohnenumber) throws IOException {
		try {
			shipPhonenumber.clear();
			Thread.sleep(1000);
			shipPhonenumber.sendKeys(pohnenumber);
			Reporting.updateTestReport(
					"PhoneNumber: " + pohnenumber + " was entered successfully in the PhoneNumber section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("PhoneNumber was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void shipTownCity(String City) throws IOException {
		try {
			Thread.sleep(1000);
			TownCity.clear();
			Thread.sleep(1000);
			TownCity.sendKeys(City);
			Reporting.updateTestReport("City: " + City + " was entered successfully in the City section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("City was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}/*
		 * @Author:Vishnu
		 * 
		 * @Description: Method to enter state on Shipping Page
		 */

	public void enterState(String state) throws IOException {
		try {
			EnterState.clear();
			EnterState.sendKeys(state);
			EnterState.sendKeys(Keys.ENTER);

			Reporting.updateTestReport("State " + state + " has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to enter State " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}/*
		 * @Author:Vishnu
		 * 
		 * @Description: Method to select use selected Shipping address on Address
		 * doctor page
		 */

	public WebElement returnUseSelectedShippingAddressButtonAddressDoctorPopUp() {
		return UseSelectedShippingAddressButtonAddressDoctorPopUp;
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to click Use Selected Shipping Address doctor on button
	 * Address doctor page
	 */

	public void clickOnUseSelectedShippingAddressButtonAddressDoctor() throws IOException {
		try {
			UseSelectedShippingAddressButtonAddressDoctorPopUp.click();
			Reporting.updateTestReport("Use Selected Address Button in Address Doctor PopUp was clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Use Selected Address Button in Address Doctor PopUp couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to click on Verification Student for US
	 */

	public void verificationOfStudentForUS() throws IOException {
		try {

			String studentmessage = StudentVerification.getText();
			Reporting.updateTestReport("The message: " + studentmessage + " Displayed successfully for US Address",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to the message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to uncheck the Shipping and Billing Address page
	 */
	public void shipAndBillAddressSection() throws IOException {
		try {
			// ShippingBillingSameAddress.clear();
			ShippingBillingSameAddress.click();
			Reporting.updateTestReport("ShipAndBillingAddress Checkbox Unchecked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Uncheck ShipAndBillingAddress Checkbox with the error message "
					+ e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}/*
		 * @Author:Vishnu
		 * 
		 * @Description: Method to select the Country Billing Address Page
		 */

	public void selectBillCountry(String country) throws IOException {
		try {

			Select selCountry = new Select(SelectCountryDropDown);
			selCountry.selectByVisibleText(country);
			Reporting.updateTestReport("County " + country + " has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select country " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}/*
		 * @Author:Vishnu
		 * 
		 * @Description: Method to enter the Address Line
		 */

	public void addressLineOne(String ALineOne) throws IOException {
		try {
			AddressLine1.sendKeys(ALineOne);
			Reporting.updateTestReport(
					"AddressLine1: " + ALineOne + " was entered successfully in the AddressLine1 section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("AddressLine1 was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to enter Zipcode
	 */

	public void enterZipcode(String postalCode) throws IOException {
		try {
			PostalCode.sendKeys(postalCode);
			Reporting.updateTestReport("ZipCode: " + postalCode + " was entered successfully in the ZipCode section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("ZipCode was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}/*
		 * @Author:Vishnu
		 * 
		 * @Description: Method to enter City Shipping Page
		 */

	public void enterCity(String City) throws IOException {
		try {
			BillCity.clear();
			Thread.sleep(1000);
			BillCity.sendKeys(City);
			Reporting.updateTestReport("City: " + City + " was entered successfully in the City section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("City was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}/*
		 * @Author:Vishnu
		 * 
		 * @Description: Method to enter PhoneNumber on Shipping page
		 */

	public void enterPhoneNumber(String pohnenumber) throws IOException {
		try {
			BillPhoneNumber.clear();
			BillPhoneNumber.sendKeys(pohnenumber);
			Reporting.updateTestReport(
					"PhoneNumber: " + pohnenumber + " was entered successfully in the PhoneNumber section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("PhoneNumber was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}/*
		 * @Author:Vishnu
		 * 
		 * @Description: Method to enter the guest user
		 */

	public String enterGuestuser() throws IOException {
		try {
			String dateTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String gEmail = "autowel" + dateTime + "@yopmail.com";
			GuestUser.sendKeys(gEmail);
			Reporting.updateTestReport("Email Id: " + gEmail + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return gEmail;
		} catch (Exception e) {
			Reporting.updateTestReport("Email Id was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}/*
		 * @Author:Vishnu
		 * 
		 * @Description: Method to click on Create Account button Create Account form
		 */

	public void clickingOnCreateAccoutButton() throws IOException {
		try {
			CreateAccountButton.click();
			Reporting.updateTestReport("Create Account button clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Create Account button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to enter Email address on Guest email page
	 */

	public void guestConfirmEmailId(String emailId) throws IOException {
		try {
			GuestConfinmEmail.sendKeys(emailId);
			Reporting.updateTestReport(
					"Email Id: " + emailId + " was entered successfully in the Confirm email id section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Email Id was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}/*
		 * @Author:Vishnu
		 * 
		 * @Description: Method to enter the password
		 */

	public void enterPassword(String password) throws IOException {
		try {
			Password.sendKeys(password);
			Reporting.updateTestReport("Password " + password + " entered successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the Password with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}/*
		 * @Author:Vishnu
		 * 
		 * @Description: Method to select the check box for Agreement
		 */

	public void clickonAgreementCheckBox() throws IOException {
		try {
			AgreementCheckbox.click();
			Reporting.updateTestReport("Agreement Checbox selected Successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Agreement Checbox with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to click on Save And Continue Button
	 */
	public void clickingOnSaveAndContinue() throws IOException {
		try {
			CreateAccountButton.click();
			Reporting.updateTestReport("SaveAndContinue button clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on SaveAndContinue button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}/*
		 * @Author:Vishnu
		 * 
		 * @Description: Method to click on Save and Continue button on Shipping Address
		 * section
		 */

	public void shipSaveAndContinueButton() throws IOException {
		try {
			ShipSaveAndContinue.click();
			Reporting.updateTestReport("ShipSaveAndContinue clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on ShipSaveAndContinue with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to click on CMA eBook
	 */

	public void clickonCMAeBook() throws IOException {

		try {
			CMAeBook.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("CMA e-Book was slected successfully ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on CMA e-Book with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}/*
		 * @Date: 31/01/23
		 * 
		 * @Author: Anindita
		 * 
		 * @Description: Clicks on the Add To Cart Button On PDP
		 */

	public void clickOnAddToCartButtonOnPDP() throws IOException {
		try {
			AddToCartButtonOnPDP.click();
			Reporting.updateTestReport("The Add To Cart Button On PDP was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("The Add To Cart Button On PDP couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to select use selected Billing address on Address doctor
	 * page
	 */

	public WebElement returnUseSelectedBillingAddressButtonAddressDoctorPopUp() {
		return UseSelectedBillingAddressButtonAddressDoctorPopUp;
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to click Use Selected Billing Address doctor on button
	 * Address doctor page
	 */

	public void clickOnUseSelectedBillingAddressButtonAddressDoctor() throws IOException {
		try {
			UseSelectedBillingAddressButtonAddressDoctorPopUp.click();
			Reporting.updateTestReport("Use Selected Address Button in Address Doctor PopUp was clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Use Selected Address Button in Address Doctor PopUp couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to enter the Card Number on Payment page
	 */

	public void enterCardNumber(String cardNumber) throws IOException {
		try {
			CardNumber.click();
			CardNumber.sendKeys(cardNumber);
			Reporting.updateTestReport("CardNumber was entered successfully on paymentpage",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the cardNumber with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to select exipirationMonth on Payment page
	 */

	public void selectExpirationMonthFromDropDown(String month) throws IOException {
		try {
			Select selExpirationMonth = new Select(ExpirationDateForMonth);
			selExpirationMonth.selectByValue(month);
			Reporting.updateTestReport("ExpirationMonth has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select ExpirationMonth " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to select the Expiration year on payment page
	 */

	public void selectExpirationYearFromDropDown(String year) throws IOException {
		try {
			Select selExpirationYear = new Select(ExpirationDateForYear);
			selExpirationYear.selectByValue(year);
			Reporting.updateTestReport("ExpirationYear has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select ExpirationYear " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to enter the CVV number on Payment page
	 */

	public void enterCVV_Number(String CVVNumber) throws IOException {
		try {
			CVV_Number.sendKeys(CVVNumber);
			Reporting.updateTestReport("CVV_Number: " + CVVNumber + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("CVV_Number was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to Click on Save and Continue button
	 */
	public void saveAndContinueCheckOut() throws IOException {
		try {
			SaveAndContinueOnCheckOutPage.click();
			Reporting.updateTestReport("SaveAndContinue clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on SaveAndContinue with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to enter the Car holder name on Payment page
	 */

	public void enterCardHolderName(String cardHolderName) throws IOException {
		try {
			CardHolderName.sendKeys(cardHolderName);
			Reporting.updateTestReport("Card Holder Name: " + cardHolderName + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Card Holder Name was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void verifyPaypaltext() throws IOException {
		try {
			Reporting.updateTestReport(
					Paypalpayment.getText() + " : Payment Option was displayed in the Billing Section for New user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to display the Paypal text in Billing section for new user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void verifyDiscountText() throws IOException {
		try {
			Reporting.updateTestReport(DiscountMessage.getText() + " :messages is appeared.",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to display the Paypal text in Billing section for new user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to enter the University name on Text field
	 */

	public void verifyPaypalCredittext() throws IOException {
		try {
			Reporting.updateTestReport(
					PaypalCredit.getText() + " : Payment Option was displayed in the Billing Section for New user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to display the Paypal text in Billing section for new user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to enter the University name on Text field
	 */

	public void enterUniversityName(String universityname) throws IOException {
		try {
			PartnerInputSearch.sendKeys(universityname);
			PartnerInputSearch.sendKeys(Keys.ENTER);
			Reporting.updateTestReport("Universityname Name was enter successfully on Partner Input Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to enter the universityname with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}/*
		 * @Author:Vishnu
		 * 
		 * @Description: Method to click on DeanDorton CMA Product
		 */

	public void clickOnDeanDortonCMAProduct() throws IOException {
		try {
			DeanDortonCMAProduct.click();
			Reporting.updateTestReport("DeanDorton CMA product was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on  DeanDorton CMA product" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to enter the University name on Text field
	 */
	public void enteruniversityName(String universityname) throws IOException {
		try {
			PartnerInputSearch.sendKeys(universityname);
			PartnerInputSearch.sendKeys(Keys.ENTER);
			Reporting.updateTestReport("Universityname Name was enter successfully on Partner Input Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to enter the universityname with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Clicks on Part1 button In CIA PDP
	 */
	public void clickOnPart1InCIAPDP() throws IOException {
		try {
			Part1InCIAPDP.click();
			Reporting.updateTestReport("The Part1 button In CIA PDP was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("The Part1 button In CIA PDP couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Vishnu
	 * 
	 * @Decription: Checks if "Don't ship to PO Box" message is present or not
	 */
	public void checkDontShipToPOBoxMessage() throws IOException {
		try {
			Reporting.updateTestReport(
					DontShipToPOBox.getText() + " : this message was displayed in the shipping section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Don't ship to PO Box : this message was displayed in the shipping section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Vishnu
	 * 
	 * @Decription: Checks if "Don't ship to PO Box" message is present or not
	 */
	public void clickonCPALinkOnHomePage() throws IOException {
		try {
			CPALink.click();
			Reporting.updateTestReport("CPA product link clicked Successfully in Home Page ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on CPA Product link on Home Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void clickonCPAPrinteBook() throws IOException {
		try {
			CPAPrinteBook.click();
			Reporting.updateTestReport("CPA Physital product was selected Successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to select CPA Physital Product",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Method to click on Click on CMA PrinteBook
	 */

	public void clickonCMAPrinteBook() throws IOException {

		try {
			PrintEbook.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("CMA e-Book+Print was slected successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on CMA e-Book with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void clickonCFAlinkOnHomePage() throws IOException {

		try {
			CFAProduct.click();
			Reporting.updateTestReport("CFA product was selected successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to select on CFA Product with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on back to cart button
	 */
	public void clickOnBackToCartButton() throws IOException{
		try {
			BackToCartButton.click();
			Reporting.updateTestReport("Back to cart button was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			
		}Reporting.updateTestReport("Back to cart button couldn't be clicked ",
				CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
	}
	/*
	 * @Author:Anindita
	 * @Description: Method to select the quantity on cart Page
	 */

	public void selectQuantity(String quantity) throws IOException {
		try {

			Select scountry = new Select(SelectQuantityDropdown);
			scountry.selectByVisibleText(quantity);
			Reporting.updateTestReport("Quantity " + quantity + " has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select quantity " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Fetches the shipping charge for shipping methods by passing the shipping method name
	 */
	public BigDecimal fetchShippingChargeNonUS(WebDriver driver,String shippingMethod) throws IOException{
		try {
			String xpathOfShippingCharge="//label[@class='deliveryModeLabel' and contains(text(),'"+
					shippingMethod+"')]/span[@class='textBold']";
			String xpathOfShippingMethodName="//label[@class='deliveryModeLabel' and contains(text(),'"+
					shippingMethod+"')]";
			String charge=driver.findElement(By.xpath(xpathOfShippingCharge)).getText().trim();
			Reporting.updateTestReport("Shipping charge value: "+charge+" was returned for shipping method: "+
					driver.findElement(By.xpath(xpathOfShippingMethodName)).getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return new BigDecimal(charge.substring(1));

		}
		catch(Exception e) {
			Reporting.updateTestReport("Shipping charge couldn't be fetched", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return new BigDecimal(0.00);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Fetches the shipping charge for shipping methods by passing the shipping method name
	 */
	public BigDecimal fetchShippingCharge(WebDriver driver,String shippingMethod) throws IOException{
		try {
			String xpathOfShippingCharge="//label[@class='deliveryModeLabel deliveryModeLabelExceptFirst' and contains(text(),'"+
					shippingMethod+"')]/span[@class='textBold']";
			String xpathOfShippingMethodName="//label[@class='deliveryModeLabel deliveryModeLabelExceptFirst' and contains(text(),'"+
					shippingMethod+"')]";
			String charge=driver.findElement(By.xpath(xpathOfShippingCharge)).getText().trim();
			System.out.println("*"+charge);
			Reporting.updateTestReport("Shipping charge value: "+charge+" was returned for shipping method: "+
					driver.findElement(By.xpath(xpathOfShippingMethodName)).getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return new BigDecimal(charge.substring(1));

		}
		catch(Exception e) {
			Reporting.updateTestReport("Shipping charge couldn't be fetched", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return new BigDecimal(0.00);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks outside the email id field in login checkout page
	 */
	public void clickOutsideTheEmailIdField() throws IOException{
		try {
			LoginCheckoutPageHeading.click();
			Reporting.updateTestReport("Outside the email id field was clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Outside the email id field couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Checks error message after entering invalid email id in login checkout page
	 */
	public void checkOnClickErrorMessageInCheckoutLoginRegistrationPage() throws IOException{
		try {
			if(ErrorMessageAfterEnteringInvalidEmailId.getText().contains(excelOperation.getTestData("OnClickErrorMessageForEmailId", "Generic_Messages", "Data")))
				Reporting.updateTestReport("Proper error message was displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Proper error message was not displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Proper error message was not displayed",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Date: 01/02/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Clicks on Shipping Details Edit Icon
	 */
	public void clickOnShippingDetailsEditIcon() throws IOException {
		try {
			ShippingDetailsEditIcon.click();
			Reporting.updateTestReport("The Shipping Details Edit Icon was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("The Shipping Details Edit Icon couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Method to enter a new user id which is not auto generated and passed from datasheet
	 */

	public void enterNewUserIdInCheckoutLoginRegistrationNonAutoGenerated(String emailId) throws IOException {
		try {
			GuestUser.clear();
			GuestUser.sendKeys(emailId);
			Reporting.updateTestReport("Email Id: " + emailId + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Email Id was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
}
