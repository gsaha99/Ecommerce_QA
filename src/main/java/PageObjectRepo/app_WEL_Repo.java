package PageObjectRepo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
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

public class app_WEL_Repo {
	WEL_Prod_Test_Suite WEL_Test;
	public String SS_path=WEL_Test.SS_path;
	
	//Homepage
	@FindBy(xpath="//a[@aria-label='login']")
	WebElement LoginButtonHomepage;
	
	//Login/ Registration page
	
	@FindBy(xpath="//a[@href='https://www.efficientlearning.com/register']")
	WebElement CreateOneLinkInLoginPage;
	@FindBy(id="register.studentFirstName")
	WebElement FirstNameFieldInRegistrationForm;
	@FindBy(id="register.studentLastName")
	WebElement LastNameFieldInRegistrationForm;
	@FindBy(id="register.email")
	WebElement EmailIdFieldInRegistrationForm;
	@FindBy(id="register.confirmEmail")
	WebElement ConfirmEmailIdFieldInRegistrationForm;
	@FindBy(id="password")
	WebElement PasswordFieldInRegistrationForm;
	@FindBy(xpath="//label[@class='control-label welCustomCheckbox createNewAccountCheckbox' and @for='termsCheck']")
	WebElement CheckboxInRegistrationForm;
	@FindBy(xpath="//button[@data-form_id='wileyRegisterForm']")
	WebElement CreateAccountButtonInRegistrationForm;
	@FindBy(id="username")
	WebElement UserNameInLoginPage;
	@FindBy(id="password")
	WebElement UserPasswordInLoginPage;
	@FindBy(xpath="//input[@value='LOG IN']")
	WebElement StandaloneLoginButton;
	
	//My Account page
	
	@FindBy(xpath="//a[@aria-label='account']")
	WebElement AccountButtonOnHomepage;
	@FindBy(xpath = "//button[contains(text(),'I Accept')]")
	WebElement AcceptButtonOnWileyWELPrivacyAgreement;
	@FindBy(id = "signout")
	WebElement SignOutButtonInMyAccountPage;
	
	//Products related xpaths
	
	@FindBy(xpath="//a[@data-for='productTooltipCMA' and @data-key='1']")
	WebElement CMALinkOnHomepage;
	@FindBy(xpath="//a[@data-for='productTooltipCIA' and @data-key='2']")
	WebElement CIALinkOnHomepage;
	@FindBy(xpath="//a[@data-for='productTooltipCFA' and @data-key='0']")
	WebElement CFALinkOnHomepage;
	@FindBy(xpath="//a[@data-for='productTooltipCPA' and @data-key='0']")
	WebElement CPALinkOnHomepage;
	@FindBy(xpath="//button[@class='shop-courses-btn  ']")
	WebElement ExploreCourseButton;
	@FindBy(xpath="//a[@href='/cma/products/platinum-cma-review-course/']/button[contains(text(),'VIEW COURSE OPTIONS')]")
	WebElement CMAViewCourseButton;
	@FindBy(xpath="(//a[@href='/cia/products/cia-review-course/' and contains(text(),'View Course')])[1]")
	WebElement CIAViewCourseButton;
	@FindBy(xpath="(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")
	WebElement GetCMACourseButton;
	@FindBy(xpath="//button[@type='submit' and contains(text(),'ADD TO CART')]")
	WebElement AddToCartButton;
	@FindBy(xpath="//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 col-xl-4 shop-courses-btn-container']/a/button")
	WebElement ShopCourseButton;
	@FindBy(xpath="//span[@class='apply-discount-link']")
	WebElement ApplyStudentDiscountLink;
	@FindBy(xpath="//p[@class='current-price-link']/a[contains(text(),'Switch to student discount price')]")
	WebElement SwitchToStudentDiscountLink;
	@FindBy(xpath="//a[@href='/cfa/products/']/button[contains(text(),'VIEW COURSE OPTIONS')]")
	WebElement CFAViewCourseButton;
	@FindBy(xpath="//a[@href='/cfa/products/level-1/platinum-cfa-course/' and contains(text(),'View Course')]")
	WebElement CFAViewCourseLink;
	
	//Cart page
	
	@FindBy(id="cartCheckoutBtn")
	WebElement CheckoutButtonOnCartPage;
	@FindBy(id="voucher-remove-button_0")
	WebElement CouponRemoveButton;
	@FindBy(id="cartPageAddDiscountLink")
	WebElement AddDiscountLink;
	@FindBy(xpath="(//input[@id='discountCodeValue'])[1]")
	WebElement DiscountValueInCartPage;
	@FindBy(id="discountApplyBtn")
	WebElement DiscountApplyButtonInCartPage;
	
	//Checkout login/ registration page
	
	@FindBy(id="guest.email")
	WebElement NewUserIdInCheckoutLoginRegistration;
	@FindBy(xpath="//div[@id='guestCheckoutRegBtn']/button[@data-form_id='guestForm']")
	WebElement CreateAccountButtonInCheckoutLoginRegistration;
	@FindBy(id="guest.confemail")
	WebElement ConfirmEmailIdInCheckout;
	@FindBy(id="pwd")
	WebElement PasswordInCheckout;
	@FindBy(xpath="//span[@class='welCheckoutContBtnText' and contains(text(),'Save and Continue')]")
	WebElement SaveAndContinueButton;
	@FindBy(xpath="//button[@data-form_id='loginForm']")
	WebElement LoginButtonInCheckoutLoginPage;
	@FindBy(id="j_username")
	WebElement ExistingUserNameInCheckoutLoginPage;
	@FindBy(id="j_password")
	WebElement ExistingUserPasswordInCheckoutLoginPage;
	@FindBy(xpath="//a[@class='recover-password pull-left checkoutForgotPwdLinkCss']")
	WebElement ForgotPasswordLinkInCheckOutLoginPage;
	
	//Forgot password page
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
	
	//Free Trial pages
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
	
	
	
	/*
	 * @Date: 5/4/23
	 * @Description: Clicks on the login button in homepage
	 */
	public void clickOnLoginButtonHomepage() throws IOException {
		try {
			LoginButtonHomepage.click();
			Reporting.updateTestReport("Login button on homepage was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Login button on homepage couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 5/4/23
	 * @Description: Clicks on the Create One Link In Login Page to do standalone registration
	 */
	public void clickOnCreateOneLinkInLoginPage() throws IOException {
		try {
			CreateOneLinkInLoginPage.click();
			Reporting.updateTestReport("Create One Link In Login Page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Create One Link In Login Page couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 5/4/23
	 * @Description: Enters the first name in the registration form
	 */
	public void enterFirstNameInRegistrationForm(String firstname) throws IOException {
		try {
			FirstNameFieldInRegistrationForm.sendKeys(firstname);
			Reporting.updateTestReport("First name: "+firstname+" was entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("First name couldn't be entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 5/4/23
	 * @Description: Enters the last name in the registration form
	 */
	public void enterLastNameInRegistrationForm(String lastname) throws IOException {
		try {
			LastNameFieldInRegistrationForm.sendKeys(lastname);
			Reporting.updateTestReport("Last name: "+lastname+" was entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Last name couldn't be entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 5/4/23
	 * @Description: Enters the email id in the registration form
	 */
	public String enterEmailIdInRegistrationForm() throws IOException {
		try {
			String dateTime= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String emailId="autovet"+dateTime+"@yopmail.com";
			EmailIdFieldInRegistrationForm.sendKeys(emailId);
			ConfirmEmailIdFieldInRegistrationForm.sendKeys(emailId);
			Reporting.updateTestReport("Email Id: "+emailId+" was entered successfully in the registration form",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return emailId;
			
		}
		catch(Exception e) {
			Reporting.updateTestReport("Email Id couldn't be entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	/*
	 * @Date: 5/4/23
	 * @Description: Enters the password in the registration form
	 */
	public void enterPasswordInRegistrationForm(String password) throws IOException {
		try {
			PasswordFieldInRegistrationForm.sendKeys(password);
			Reporting.updateTestReport("Password: "+password+" was entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Password couldn't be entered in the registration form",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 5/4/23
	 * @Description: Clicks on the terms and conditions checkbox
	 */
	public void clickOnCheckboxInRegistrationForm() throws IOException {
		try {
			CheckboxInRegistrationForm.click();
			Reporting.updateTestReport("Terms and conditions checkbox in registration page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Terms and conditions checkbox in registration page couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 5/4/23
	 * @Description: Clicks on the Create Account button in regsitration page
	 */
	public void clickOnCreateAccountButtonInRegistrationForm() throws IOException {
		try {
			CreateAccountButtonInRegistrationForm.click();
			Reporting.updateTestReport("Create Account button in regsitration page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Create Account button in regsitration page couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 5/4/23
	 * @Description: Clicks on the Account button in homepage
	 */
	public void clickOnAccountButtonInHomepage() throws IOException {
		try {
			AccountButtonOnHomepage.click();
			Reporting.updateTestReport("Account button in homepage was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Account button in homepage couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 5/4/23 
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
	 * @Description: User name is entered in standalone login page
	 */
	public void enterUserNameInLoginPage(String UserName) throws IOException {
		try {
			UserNameInLoginPage.sendKeys(UserName);
			Reporting.updateTestReport("User Name: "+UserName+" was entered successfully on Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the username with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Date: 5/4/23
	 * @Description: Password is entered in standalone login page
	 */
	public void enterPasswordInLoginPage(String password) throws IOException {
		try {
			UserPasswordInLoginPage.sendKeys(password);
			Reporting.updateTestReport("Passworde: "+password+" was entered successfully on Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the password with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	
	/*
	 *@Date: 5/4/23
	 * @Description: Method to click on Login button
	 */

	public void clickOnStandaloneLoginButton() throws IOException {
		try {
			StandaloneLoginButton.click();
			Reporting.updateTestReport("Login Button clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Login button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	
	/*
	 *@Date: 5/4/23
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
	 *@Date: 5/4/23
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
	 * @Description: Method to click on View Course Button in CMA Product page
	 */

	public void clickOnCMAViewCourseButton() throws IOException {
		try {
			CMAViewCourseButton.click();
			Reporting.updateTestReport("View Course Button in CMA Product page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on View Course Button in CMA Product page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	
	/*
	 * @Date: 5/4/23
	 * @Description: Method to click on Get CMA Course Button in CMA Product page
	 */

	public void clickOnGetCMACourseButton() throws IOException {
		try {
			GetCMACourseButton.click();
			Reporting.updateTestReport("Get CMA Course Button in CMA Product page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Get CMA Course Button in CMA Product page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	
	/*
	 * @Date: 5/4/23
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
	 *@Date: 5/4/23 
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
	 * @Description: Enters the user id in checkout login/ registration page
	 */

	public String enterNewUserIdInCheckoutLoginRegistration() throws IOException {
		try {
			String dateTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String emailId = "autowel" + dateTime + "@yopmail.com";
			NewUserIdInCheckoutLoginRegistration.sendKeys(emailId);
			Reporting.updateTestReport("Email Id: " + emailId + " was entered successfully in checkout login/ registration page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return emailId;
		} catch (Exception e) {
			Reporting.updateTestReport("Email Id was not entered in checkout login/ registration page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	/*
	 * @Date: 5/4/23 
	 * @Description: Method to click on Create Account button in checkout login/ registration page
	 */
	public void clickOnCreateAccountButtonInCheckoutLoginRegistration() throws IOException {
		try {
			CreateAccountButtonInCheckoutLoginRegistration.click();
			Reporting.updateTestReport("Create Account button in checkout login/ registration page was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Create Account button in checkout login/ registration page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Date: 5/4/23
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
	 * @Description: Method to enter the password for checkout create account
	 */
	public void enterPasswordInCheckout(String password) throws IOException {
		try {
			PasswordInCheckout.sendKeys(password);
			Reporting.updateTestReport("Password "+password+" entered successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the Password with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Date: 5/4/23
	 * @Description: Clicks on the Save and Continue button during checkout
	 */
	public void clickOnSaveAndContinue() throws IOException {
		try {
			SaveAndContinueButton.click();
			Reporting.updateTestReport("Save and continue button was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Save and continue button couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
	}
	
	/*
	 * @Date: 6/4/23
	 * @Description: Method to enter Existing user name on checkout Login Page
	 */

	public void enterExistingUserNameInCheckoutLoginPage(String username) throws IOException {
		try {
			ExistingUserNameInCheckoutLoginPage.sendKeys(username);
			Reporting.updateTestReport("Existing User Name : " + username + " was entered successfully in checkout Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Existing User Name was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Date: 6/4/23
	 * @Description: Method to enter Existing user password on checkout Login Page
	 */

	public void enterExistingUserPasswordInCheckoutLoginPage(String password) throws IOException {
		try {
			ExistingUserPasswordInCheckoutLoginPage.sendKeys(password);
			Reporting.updateTestReport("Existing User password : " + password + " was entered successfully in checkout Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Existing User password was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Date: 6/4/23
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
	 * @Description: Method to enter email address on Forgot Password Page
	 */

	public void enterEmailAddressOnForgotPassword(String Email) throws IOException {
		try {
			EmailIdOnForgotPassword.sendKeys(Email);
			Reporting.updateTestReport("Email Id: "+Email+" was entered on email field on Forgot Password Page ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the Email on Forgot Password Page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	/*
	 * @Date: 6/4/23 
	 * @Description: Method to click on Submit button on ForgotPassword Page
	 */

	public void clickOnSubmitButtonnForgotPasswordPage() throws IOException {
		try {
			SubmitButtonOnForgotPassword.click();
			Reporting.updateTestReport("Submit button was clicked Successfully on ForgotPassword Page ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click Submit button on Forgot Password Page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	
	/*
	 * @Date: 6/4/23 
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
	 * @Description: Clicks on check inbox in yopmail after entering user id
	 */
	public void clickOnCheckInboxButton() throws IOException{
		try {
			clickOnCheckInboxButton.click();
			Reporting.updateTestReport("Arrow button clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Failed to click on arrowbutton", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}
	
	
	
	/*
	 * @Date: 6/4/23
	 * @Description: Clicks on Add Discount link in cart page
	 */
	public void clickOnAddDiscountLink() throws IOException{
		try {
			AddDiscountLink.click();
			Reporting.updateTestReport(" Add Discount link in cart page was clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Failed to click on Add Discount link in cart page", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 6/4/23
	 * @Description: Enters the Discount code in cart page
	 */
	public void enterDiscountValueInCartPage(String coupon) throws IOException {
		try {
			DiscountValueInCartPage.sendKeys(coupon);
			Reporting.updateTestReport("Discount code : " + coupon + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter Discount code "+e.getMessage(), CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}

	}
	
	/*
	 * @Date: 6/4/23
	 * @Description: Clicks on Discount Apply button in cart page
	 */
	public void clickOnDiscountApplyButtonInCartPage() throws IOException{
		try {
			DiscountApplyButtonInCartPage.click();
			Reporting.updateTestReport(" Discount Apply button in cart page was clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Failed to click on Discount Apply button in cart page", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 6/4/23
	 * @Description: Clicks on Discount Remove button in cart page
	 */
	public void clickOnRemoveCouponInCartPage() throws IOException{
		try {
			CouponRemoveButton.click();
			Reporting.updateTestReport(" Discount Remove button in cart page was clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Failed to click on Discount Remove button in cart page", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}
	
	/*
	 *@Date: 14/4/23
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
	 *@Date: 14/4/23
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
	 *@Date: 14/4/23
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
	 *@Date: 14/4/23
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
	 * @Description: Method to click on View Course Button in CIA Product page
	 */

	public void clickOnCIAViewCourseButton() throws IOException {
		try {
			CIAViewCourseButton.click();
			Reporting.updateTestReport("View Course Button in CIA Product page was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on View Course Button in CIA Product page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	
	/*
	 * @Date: 14/4/23
	 * @Description: Method to click on Apply student discount link on products' page
	 */

	public void clickOnApplyStudentDiscountLink() throws IOException {
		try {
			ApplyStudentDiscountLink.click();
			Reporting.updateTestReport("Apply student discount link CIA Product page was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Apply student discount link in CIA Product page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	
	/*
	 * @Date: 14/4/23
	 * @Description: Method to click on Switch to student discount link on products' page
	 */

	public void clickOnSwitchToStudentDiscountLink() throws IOException {
		try {
			SwitchToStudentDiscountLink.click();
			Reporting.updateTestReport("Switch to student discount link CIA Product page was clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Switch to student discount link in CIA Product page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	
	/*
	 * @Date: 14/4/23
	 * @Description: Method to click on View Course Button in CFA Product page
	 */

	public void clickOnCFAViewCourseButton() throws IOException {
		try {
			CFAViewCourseButton.click();
			Reporting.updateTestReport("View Course Button in CFA Product page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on View Course Button in CFA Product page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	
	/*
	 * @Date: 14/4/23
	 * @Description: Method to click on View Course link in CFA Product page
	 */

	public void clickOnCFAViewCourseLink() throws IOException {
		try {
			CFAViewCourseLink.click();
			Reporting.updateTestReport("View Course link in CFA Product page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on View Course link in CFA Product page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	
	/*
	 * @Date: 14/4/23
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
	
	
	
	


}
