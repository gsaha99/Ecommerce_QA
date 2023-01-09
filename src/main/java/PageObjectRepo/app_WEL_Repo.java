package PageObjectRepo;

import Test_Suite.WEL_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.DriverModule;

import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

public class app_WEL_Repo {

	WEL_Test_Suite WEL_Test;
	public String SS_path = WEL_Test.SS_path;

	@FindBy(xpath = "//a[@class='icon-color nav-login']")
	WebElement StandaloneLogin;

	@FindBy(xpath = "//div[@class='deliveryMethodMainFirstDiv']//span[contains(text(),'$40')]")
	WebElement UkShipMethod;

	@FindBy(xpath = "//div[@class='deliveryMethodMainFirstDiv']//span[contains(text(),'$40')]")
	WebElement SingaporeShipMethod;

	@FindBy(xpath = "//div[@class='deliveryMethodMainFirstDiv']//span[contains(text(),'$40')]")
	WebElement IndiaShipMethod;

	@FindBy(xpath = "//div[@class='deliveryMethodMainFirstDiv']//span[contains(text(),'$5')]")
	WebElement CanadaShipMethod;

	@FindBy(xpath = "//div[@class='deliveryMethodMainExceptFirstDiv']//span[contains(text(),'$12')]")
	WebElement CanadaShipMethod2;

	@FindBy(xpath = "(//img[@class='js-responsive-image'])[1]")
	WebElement WELIconOrderConfirmationPage;

	@FindBy(xpath = "//input[@value='LOG IN']")
	WebElement LoginButton;

	@FindBy(xpath = "(//span[@id='shoppingCartItemsCountNumber'])[1]")
	WebElement CartIcon;

	@FindBy(xpath = "//button[contains(text(),'SHOP')]")
	WebElement ClickShopCourses;

	@FindBy(xpath = "(//div[@class='row banner-position']//div[3]//button)[1]")
	WebElement Level1TestBank;

	@FindBy(xpath = "//div[@class='package-selection-container platinum-package']//button")
	WebElement CAIAAddToCart;

	@FindBy(xpath = "//input[@id='nameOnCard']")
	WebElement CardHolderName;

	@FindBy(xpath = "(//a[@href='/my-account'])[1]")
	WebElement AccountIconOnCartPage;

	@FindBy(xpath = "//a[contains(text(),'ACCOUNT')]")
	WebElement AccountIconfFromOrderConfifrmationPage;

	@FindBy(xpath = "(//div[@id='welSiteExceptLogoMainDiv']/div[@class='welGlobalHeaderNavbar'])[1]/span[3]/span/a")
	WebElement BackToCart;

	@FindBy(xpath = "//button[@id='removeEntry_0']")
	WebElement RemoveIcon;

	@FindBy(xpath = "//a[contains(text(),'Create')]")
	WebElement CreateOne;

	@FindBy(xpath = "//input[@id='register.email']")
	WebElement EmailAddress;

	@FindBy(xpath = "//input[@id='register.confirmEmail']")
	WebElement ConfirmEmailAddress;

	@FindBy(xpath = "//input[@name='studentFirstName']")
	WebElement FirstName;

	@FindBy(xpath = "//input[@name='studentLastName']")
	WebElement LastName;

	@FindBy(xpath = "//input[@name='pwd']")
	WebElement Password;

	@FindBy(xpath = "//label[@for='termsCheck']")
	WebElement TermsCheckbox;

	@FindBy(xpath = "//label[@for='agreement']")
	WebElement AgreementCheckbox;

	@FindBy(xpath = "(//span[@class='welCheckoutContBtnText'])[2]")
	WebElement CreateAccountButton;

	@FindBy(xpath = "//span[contains(text(),'Save and Continue')]")
	WebElement SaveContinue;

	@FindBy(xpath = "//a[@aria-label='account']")
	WebElement AccountIcon;

	@FindBy(xpath = "//div[@class='account d-none d-lg-inline-block']")
	WebElement AccountMyIcon;

	@FindBy(xpath = "//button[@class='btn-primary btn-privacy']")
	WebElement IAcceptButton;

	@FindBy(xpath = "//div[@class='left-panel']//li[4]/label[contains(text(),'Sign')]")
	WebElement SingOut;

	@FindBy(xpath = "//a[contains(text(),'ACCOUNT')]")
	WebElement AccountText;

	@FindBy(xpath = "//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_1']/a[3]")
	WebElement CIAProduct;

	@FindBy(xpath = "//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_3']/a[2]")
	WebElement CAIAProduct;

	@FindBy(xpath = "//div[@class='product-list-page']//div[@class='comparison-table-title-container header-sticky']//div[2]/a")
	WebElement CIAViewCourse;

	@FindBy(xpath = "//div[@class='product-detail-page']//div[@class='container-fluid banner-container product-detail-banner mt-4']//button")
	WebElement CIAAddToCart;

	@FindBy(xpath = "//div[@id='Ultimate_CPA Review Course 2022 (Mentorship & Tutoring Bundle)']//form//button")
	WebElement CPAAddToCart;

	@FindBy(xpath = "((//div[@class='col-xs-12 col-sm-6 package-selection-left-col'])[2]//form//label)[2]")
	WebElement CIAeBook;

	@FindBy(xpath = "//div[@class='col-xs-12 col-sm-6 package-selection-left-col'])[2]//form//label)[1])")
	WebElement CIAPrint;

	@FindBy(xpath = "//div[@class='helpButton']")
	WebElement HelpButton;

	@FindBy(xpath = "//button[@id='addressSubmit']")
	WebElement SaveAndContinueShippingAddressPage;

	@FindBy(xpath = "//select[@id='quantity_0']")
	WebElement SelectQuantity;

	@FindBy(xpath = "//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_1']/a[2]")
	WebElement CMAProduct;

	@FindBy(xpath = "//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_1']/a[1]")
	WebElement CPAProduct;

	@FindBy(xpath = "//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_2']/a[1]")
	WebElement CFAProduct;

	@FindBy(xpath = "(//button[contains(text(),'EXPLORE COURSES')])[1]")
	WebElement CMAExploreCourse;

	@FindBy(xpath = "//button[@class='free-trial-btn  ']")
	WebElement CPAFreeTrail;

	@FindBy(xpath = "//button[contains(text(),'FREE')]")
	WebElement CIAFreeTrial;

	@FindBy(xpath = "//div[@class='form-box']//input[@name='firstName']")
	WebElement FreeTrailFname;

	@FindBy(xpath = "//div[@class='form-box']//input[@name='lastName']")
	WebElement FreeTrailLname;

	@FindBy(xpath = "//div[@class='form-box']//input[@name='email']")
	WebElement FreeTrailEmail;

	@FindBy(xpath = "//div[@class='form-box']//select[@name='country']")
	WebElement FreeTrailCountry;

	@FindBy(xpath = "//div[@class='form-box']//select[@name='region']")
	WebElement FreeTrailState;

	@FindBy(xpath = "//div[@class='form-box']//input[@name='password']")
	WebElement FreeTrailPassword;

	@FindBy(xpath = "//div[@class='form-box']//input[@name='termsAndConditions']")
	WebElement FreeTrailTermsAndConditions;

	@FindBy(xpath = "//div[@class='form-box']//input[@name='signUp']")
	WebElement FreeTrailSignupCheckbox;

	@FindBy(xpath = "//div[@class='form-box']//button[@class='form-button']")
	WebElement FreeTrailStartButton;

	@FindBy(xpath = "//div[@class='progress-bar-outer-box']//p[contains(text(),'almost')]")
	WebElement CPAFreeTrailConfirmationText;

	@FindBy(xpath = "//button[@id='addNewAddressButton']")
	WebElement AddNewAddress;

	@FindBy(xpath = "(//div[@class='container-fluid course-pkg-background']//div[@class='card-footer bg-transparent course-pkg-cards-footer'])[1]//button[contains(text(),'VIEW COURSE OPTIONS')]")
	WebElement ViewCourseOptions;

	@FindBy(xpath = "(//div[@class='container-fluid course-pkg-background']//div[@class='card-footer bg-transparent course-pkg-cards-footer'])[1]//button")
	WebElement CPAViewCourse;

	@FindBy(xpath = "(//div[@class='container-fluid course-pkg-background']//div[@class='card-footer bg-transparent course-pkg-cards-footer'])[2]//button")
	WebElement CFAViewCourse;

	@FindBy(xpath = "//div[@class='comparison-table-title-container header-sticky']//div[2]/a")
	WebElement CFAViewCoursePage;

	@FindBy(xpath = "(//div[@class='btn-group btn-group-toggle'])/label[2]")
	WebElement CFAeBook;

	@FindBy(xpath = "//div[@class='btn-group btn-group-toggle']/label[2]")
	WebElement CMAeBook;

	@FindBy(xpath = "//div[@class='btn-group btn-group-toggle']/label[1]")
	WebElement PrintEbook;

	@FindBy(xpath = "//button[contains(text(),'SHOP COURSES')]")
	WebElement ShopCourses;

	@FindBy(xpath = "//div[@class='container-fluid banner-container product-detail-banner mt-4']//div[7]//button")
	WebElement AddToCart;

	@FindBy(xpath = "//button[contains(text(),'ADD')]")
	WebElement CMAAddtoCart;

	@FindBy(xpath = "//span[@id='voucher-remove-button_0']")
	WebElement CouponRemove;

	@FindBy(xpath = "(//span[@class='welCheckoutContBtnText'])[2]")
	WebElement ClickOnCheckout;

	@FindBy(xpath = "//input[@id='username']")
	WebElement username;

	@FindBy(xpath = "//input[@id='password']")
	WebElement passsowrd;

	@FindBy(xpath = "//input[@id='j_username']")
	WebElement FreeTrailLoginusername;

	@FindBy(xpath = "//input[@id='j_password']")
	WebElement FreeTrailLoginpasssowrd;

	@FindBy(xpath = "//a[@class='recover-password pull-left checkoutForgotPwdLinkCss']")
	WebElement StandaloneForgotpwd;

	@FindBy(xpath = "//input[@id='forgottenPwd.email']")
	WebElement EmailOnForgotPassword;

	@FindBy(xpath = "//button[@data-form_id='forgottenPwdForm']")
	WebElement SubmitbuttonOnForgotPassword;

	@FindBy(xpath = "//input[@id='guest.email']")
	WebElement GuestUser;

	@FindBy(xpath = "//div[@id='guestCheckoutRegBtn']//span[@class='welCheckoutContBtnText']")
	WebElement CreateAccount;

	@FindBy(xpath = "//input[@id='guest.confemail']")
	WebElement GuestConfinmEmail;

	@FindBy(xpath = "//input[@id='firstName']")
	WebElement GuestFirstName;

	@FindBy(xpath = "//input[@id='lastName']")
	WebElement GuestLastName;

	@FindBy(xpath = "//select[@id='address.country']")
	WebElement SelectCountryDropDown;

	@FindBy(xpath = "//select[@id='address.country']")
	WebElement ShipCountry;

	@FindBy(xpath = "//input[@id='street1']")
	WebElement AddressLine1;

	@FindBy(xpath = "//input[@id='line1']")
	WebElement ShipAddressLineOne;

	@FindBy(xpath = "//input[@id='street2']")
	WebElement AddressLine2;

	@FindBy(xpath = "//input[@id='postalCode']")
	WebElement PostalCode;

	@FindBy(xpath = "//input[@id='postcode']")
	WebElement ShipPostcode;

	@FindBy(xpath = "//input[@id='city']")
	WebElement BillCity;

	@FindBy(xpath = "//input[@id='city']")
	WebElement TownCity;

	@FindBy(xpath = "//input[@id='phoneNumber']")
	WebElement BillPhoneNumber;

	@FindBy(xpath = "//input[@id='address.region']")
	WebElement ShipState;

	@FindBy(xpath = "//input[@id='phone']")
	WebElement ShipPhoneNumber;

	@FindBy(xpath = "//input[@id='j_username']")
	WebElement LoginUser;

	@FindBy(xpath = "//input[@id='j_password']")
	WebElement LoginPassword;

	@FindBy(xpath = "//button[@data-form_id='loginForm']")
	WebElement LoginAndContinue;

	@FindBy(xpath = "//a[@href=\"/login/pw/request?t=ch\"]")
	WebElement ForgotpwdOnCheckoutPage;

	@FindBy(xpath = "//span[@class='addDiscountLabelOnCart']")
	WebElement AddaDiscountCode;

	@FindBy(xpath = "//input[@id='discountCodeValue']")
	WebElement DiscountLabel;

	@FindBy(xpath = "(//input[@id='discountCodeValue'])[2]")
	WebElement ExtranDiscount;

	@FindBy(xpath = "//button[@id='discountApplyBtn']")
	WebElement ApplyDiscountButton;

	@FindBy(xpath = "//div[@class='float-left my-account-header']/label")
	WebElement WELIconHomePage;
	@FindBy(xpath = "//a[@class='navbar-brand brand-logo-top-desktop']")
	WebElement WELBrandLogonOnDeskTop;

	@FindBy(xpath = "(//div[@class='simple-responsive-banner-component'])[1]/a")
	WebElement WELIconOnCheckOutPage;

	@FindBy(xpath = "//button[@id='addressSubmit']")
	WebElement ShipSaveAndContinue;

	@FindBy(xpath = "//input[@id='number']")
	WebElement CardNumber;

	@FindBy(xpath = "//select[@id='expiryMonth']")
	WebElement ExpirationDateForMonth;

	@FindBy(xpath = "//select[@id='expiryYear']")
	WebElement ExpirationDateForYear;

	@FindBy(xpath = "//input[@id='securityCode']")
	WebElement CVV_Number;

	@FindBy(xpath = "//button[@id='paymentBilling']")
	WebElement SaveAndContinueOnCheckOutPage;

	@FindBy(xpath = "//label[@id='sameAsBillingLabel']")
	WebElement ShippingBillingSameAddress;

	@FindBy(xpath = "(//button[@id='placeOrder'])[1]")
	WebElement PlaceOrder;

	@FindBy(xpath = "(//div[@class='orderConfirmationLabelVal marginTop10'])[2]")
	WebElement OrderId;

	@FindBy(xpath = "(//div[@class='col-md-5 col-6 noPadding orderReviewDetailsValue'])[2]")
	WebElement OrderTax;

	@FindBy(xpath = "//div[@class='col-md-5 col-6 noPadding orderReviewTotalPrice']")
	WebElement OrderTotal;

	@FindBy(xpath = "(//button[@class='useThisAddressBtn'])[1]")
	WebElement UseButton;

	@FindBy(xpath = "//div[@class='modal-content']//button")
	WebElement FreeTrailModelPopUpLogin;

	@FindBy(xpath = "//button[@class='button form-button welCheckoutBtn submitWelForm width100']")
	WebElement FreeTrailSubbmit;

	public void EnterUserNameOnLoginPage(String UserName) throws IOException {
		try {
			username.sendKeys(UserName);
			Reporting.updateTestReport("User Name was enter successfully on Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the username with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterFreeTrailUserNameOnLoginPage(String UserName) throws IOException {
		try {
			FreeTrailLoginusername.sendKeys(UserName);
			Reporting.updateTestReport("User Name was enter successfully on Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the username with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterPasswordOnLoginPage(String Password) throws IOException {
		try {
			passsowrd.sendKeys(Password);
			Reporting.updateTestReport("Password was enter successfully on Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the Password with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterFreeTrailPasswordOnLoginPage(String Password) throws IOException {
		try {
			FreeTrailLoginpasssowrd.sendKeys(Password);
			Reporting.updateTestReport("Password was enter successfully on Login Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the Password with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickingOnLoginButton() throws IOException {
		try {
			StandaloneLogin.click();
			Reporting.updateTestReport("Login clicked successfully and login page is opened ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Login with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ShipSaveAndContinueButton() throws IOException {
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

	public void ShipAndBillAddressSection() throws IOException {
		try {
			// ShippingBillingSameAddress.clear();
			ShippingBillingSameAddress.click();
			Reporting.updateTestReport("ShipAndBillingAddress Checkbox Unchecked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Uncheck ShipAndBillingAddress Checkbox with the error message "
					+ e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void SaveAndContinueCheckOut() throws IOException {
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

	public void ClickOnCreateOne() throws IOException {
		try {
			CreateOne.click();
			Reporting.updateTestReport("CreateOne link Clicked Successfully on login page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Click on CreateOne with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public String enterEmailIdInCreateAccountForm() throws IOException {
		try {
			String dateTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String emailId = "autowel" + dateTime + "@yopmail.com";
			EmailAddress.sendKeys(emailId);
			Reporting.updateTestReport("Email Id: " + emailId + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return emailId;
		} catch (Exception e) {
			Reporting.updateTestReport("Email Id was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public void confirmEmailIdInCreateAccountForm(String emailId) throws IOException {
		try {
			ConfirmEmailAddress.sendKeys(emailId);
			Reporting.updateTestReport(
					"Email Id: " + emailId + " was entered successfully in the Confirm email id section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Email Id was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterFirstName(String Fname) throws IOException {
		try {
			FirstName.sendKeys(Fname);
			Reporting.updateTestReport("FirsName was entered successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to enter the FirstName with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterLastName(String Lname) throws IOException {
		try {
			LastName.sendKeys(Lname);
			Reporting.updateTestReport("LastName was entered successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the LastName with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterPassword(String password) throws IOException {
		try {
			Password.sendKeys(password);
			Reporting.updateTestReport("Password was entered successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the Password with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickonTermsCheckBox() throws IOException {
		try {
			TermsCheckbox.click();
			Reporting.updateTestReport("Terms and Conditions Checbox selected Successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to Terms and Conditions Checbox with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickonAgreementCheckBox() throws IOException {
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

	public void ClickingOnCreateAccoutButton() throws IOException {
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

	public void ClickingOnSaveAndContinue() throws IOException {
		try {
			CreateAccountButton.click();
			Reporting.updateTestReport("SaveAndContinue button clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on SaveAndContinue button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public boolean CheckAccountTextAftreLogin() throws IOException {
		try {
			String username = AccountText.getText();
			if (AccountText.isDisplayed())
				return true;
			else
				return false;
		} catch (Exception e) {
			Reporting.updateTestReport("Error thrown while displaying the Account Link",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}

	}

	public void ClickonCIAProduct() throws IOException {

		try {
			CIAProduct.click();
			Reporting.updateTestReport("CIA product was selected successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to select on CIA Product with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonCMAProduct() throws IOException {

		try {
			CMAProduct.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("CMA product was selected successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to select on CMA Product with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonCPAProduct() throws IOException {

		try {
			CPAProduct.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("CPA product was selected successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to select on CPA Product with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonCFAProduct() throws IOException {

		try {
			CFAProduct.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("CFA product was selected successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to select on CFA Product with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonViewCourseForCMAProduct() throws IOException {

		try {
			ViewCourseOptions.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("ViewCourse Options was selected successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on ViewCourse Options with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonViewCourseForCPAProduct() throws IOException {

		try {
			CPAViewCourse.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("ViewCourse Options was selected successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on ViewCourse Options with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonViewCourseForCFAProduct() throws IOException {

		try {
			CFAViewCourse.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("ViewCourse Options was selected successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on ViewCourse Options with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonViewCourseLinkForCFAProduct() throws IOException {

		try {
			CFAViewCoursePage.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("ViewCourse Options was selected successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on ViewCourse Options with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonExploreCourseCMAProduct() throws IOException {

		try {
			CMAExploreCourse.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("CMA Explore Coursee button clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on CMA Explore Course button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonCMAeBook() throws IOException {

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

	}

	public void ClickonCMAPrinteBook() throws IOException {

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

	public void ClickonCMAAddToCart() throws IOException {

		try {
			AddToCart.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("Add to Cart was slected successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Add To Cart with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonShopCourse() throws IOException {
		try {
			ShopCourses.click();
			Reporting.updateTestReport("Shop Courses was Clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on ShopCourses Product with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonAddToCart() throws IOException {
		try {
			AddToCart.click();
			Reporting.updateTestReport("AddTocart was Clicked successfully ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on AddToCart with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonCouponRemove() throws IOException {
		try {
			CouponRemove.click();
			Reporting.updateTestReport("Coupons Removed successfully ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Remove the the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonCheckOutOnCartPage() throws IOException {
		try {
			ClickOnCheckout.click();
			Reporting.updateTestReport("Checkout Button clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Checkout button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonLoginButton() throws IOException {
		try {
			LoginButton.click();
			Reporting.updateTestReport("Login Button clicked successfully user is on My Account page ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Login button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickonForgotPassword() throws IOException {
		try {
			StandaloneForgotpwd.click();
			Reporting.updateTestReport("Forgot Password link successfully on Login Page ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click ForgotPassword link on Login page with the error message "
					+ e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void EnterEmailAddressOnForgotPassword(String Email) throws IOException {
		try {
			EmailOnForgotPassword.sendKeys(Email);
			Reporting.updateTestReport("Email was entered on email field on ForgotPassword Page ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the Emai with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void ClickSubmitButtonnForgotPasswordPage() throws IOException {
		try {
			SubmitbuttonOnForgotPassword.click();
			Reporting.updateTestReport("Submit button was clicked Successfully on ForgotPassword Page ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click Submit button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public String EnterGuestUser() throws IOException {
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
	}

	public void ClickOnCreateAccountButton() throws IOException {
		try {
			CreateAccount.click();
			Reporting.updateTestReport("Create Account button was clicked Successfully on ForgotPassword Page ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click Create Account button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	public void GuestConfirmEmailId(String emailId) throws IOException {
		try {
			GuestConfinmEmail.sendKeys(emailId);
			Reporting.updateTestReport(
					"Email Id: " + emailId + " was entered successfully in the Confirm email id section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Email Id was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void GuestFirstName(String GNAME) throws IOException {
		try {
			GuestFirstName.sendKeys(GNAME);
			Reporting.updateTestReport("First: " + GNAME + " was entered successfully in the First Name section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("FirstName not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void GuestLastName(String GLNAME) throws IOException {
		try {
			GuestLastName.sendKeys(GLNAME);
			Reporting.updateTestReport("Last: " + GLNAME + " was entered successfully in LastName section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("LastName was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void selectCountry(String country) throws IOException {
		try {

			Select selCountry = new Select(SelectCountryDropDown);
			selCountry.selectByVisibleText(country);
			Reporting.updateTestReport("County " + country + " has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select country " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

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

	public void ShipState(String shipstate) throws IOException {
		try {

			Select state = new Select(ShipState);
			state.selectByVisibleText(shipstate);
			Reporting.updateTestReport("State " + state + " has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select State " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void AddressLineOne(String ALineOne) throws IOException {
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

	public void ShipAddressLineOne(String ALineOne) throws IOException {
		try {
			ShipAddressLineOne.sendKeys(ALineOne);
			Reporting.updateTestReport(
					"AddressLine1: " + ALineOne + " was entered successfully in the AddressLine1 section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("AddressLine1 was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterCity(String City) throws IOException {
		try {
			BillCity.sendKeys(City);
			Reporting.updateTestReport("City: " + City + " was entered successfully in the City section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("City was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ShipTownCity(String City) throws IOException {
		try {
			TownCity.sendKeys(City);
			Reporting.updateTestReport("City: " + City + " was entered successfully in the City section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("City was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterZipecode(String postalCode) throws IOException {
		try {
			PostalCode.sendKeys(postalCode);
			Reporting.updateTestReport("ZipCode: " + postalCode + " was entered successfully in the ZipCode section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("ZipCode was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ShipPostCode(String postalCode) throws IOException {
		try {
			ShipPostcode.sendKeys(postalCode);
			Reporting.updateTestReport("postalcode: " + postalCode + " was entered successfully in the ZipCode section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("ZipCode was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterPhoneNumber(String pohnenumber) throws IOException {
		try {
			BillPhoneNumber.sendKeys(pohnenumber);
			Reporting.updateTestReport(
					"PhoneNumber: " + pohnenumber + " was entered successfully in the PhoneNumber section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("PhoneNumber was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ShipPhoneNumber(String pohnenumber) throws IOException {
		try {
			ShipPhoneNumber.sendKeys(pohnenumber);
			Reporting.updateTestReport(
					"PhoneNumber: " + pohnenumber + " was entered successfully in the PhoneNumber section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("PhoneNumber was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterexistingUserName(String username) throws IOException {
		try {
			LoginUser.sendKeys(username);
			Reporting.updateTestReport("ExistingUserName: " + username + " was entered successfully in the Email field",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"ExistingUserName was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterPasswordLoginPage(String Loginpassword) throws IOException {
		try {
			LoginPassword.clear();
			LoginPassword.sendKeys(Loginpassword);
			Reporting.updateTestReport("Password  was entered successfully in the Password field",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Password was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterInvalidPasswordLoginPage(String Loginpassword) throws IOException {
		try {
			LoginPassword.clear();
			LoginPassword.sendKeys(Loginpassword);
			Reporting.updateTestReport("Invalid Password was entered successfully in the Password field",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Password was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickonLoginAndContinue() throws IOException {
		try {
			LoginAndContinue.click();
			;
			Reporting.updateTestReport("Login and Continue buttons was clicked Successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to Click on Login And Continue Button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickonForgotPasswordOnCheckoutPage() throws IOException {
		try {
			ForgotpwdOnCheckoutPage.click();
			Reporting.updateTestReport("Forgot Password link  was clicked Successfully Checkout Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Click on Forgot Password On Checkout page with the error message "
					+ e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickonAccountIcon() throws IOException {
		try {
			AccountIcon.click();
			Reporting.updateTestReport("Account link  was clicked Successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to Click on Account link with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickonSignOut() throws IOException {
		try {
			SingOut.click();
			Reporting.updateTestReport("SingOut link  was clicked Successfully Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to Click on SingOut link with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickonIAcceptButton() throws IOException {
		try {
			IAcceptButton.click();
			Reporting.updateTestReport("I Accept Button button  was clicked Successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to Click on IAcceptButton button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnAddaDiscountCode() throws IOException {
		try {
			AddaDiscountCode.click();
			Reporting.updateTestReport("Add a discount code link  was clicked Successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to Click on Add a discount code link with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterDiscountCode(String PromoCode) throws IOException {
		try {
			DiscountLabel.sendKeys(PromoCode);
			Reporting.updateTestReport("PromoCode was entered Successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to enter the PromoCode with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterExtraDiscountCode(String PromoCode) throws IOException {
		try {
			ExtranDiscount.sendKeys(PromoCode);
			Reporting.updateTestReport("PromoCode was entered Successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to enter the PromoCode with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnDiscountApplyButton() throws IOException {
		try {
			ApplyDiscountButton.click();
			Reporting.updateTestReport("PromoCode was Applied Successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to apply the PromoCode with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnBackTOCart() throws IOException {
		try {
			Thread.sleep(2000);
			BackToCart.click();
			Reporting.updateTestReport("Back TO Cart Icon clicked Successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Back To Cart with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnRemoveOnCartPage() throws IOException {
		try {
			RemoveIcon.click();
			Reporting.updateTestReport("Product Removed Successfully from Cart Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to remove product from Cart with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickAccountCartPage() throws IOException {
		try {
			AccountIconOnCartPage.click();
			Reporting.updateTestReport("Account Icon clicked Successfully from Cart Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click Account Icon on Cart with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickCartIconOnForgotPasswordPage() throws IOException {
		try {
			CartIcon.click();
			Reporting.updateTestReport("Cart Icon clicked Successfully from Forgot/Change Password Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click Cart Icon Forgot/Change Password Paget with the error message "
					+ e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickWELIconHomePage() throws IOException {
		try {
			WELIconHomePage.click();
			Reporting.updateTestReport("WEL Icon clicked successfully on Account Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click WEL Icon Account Page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickWELIconCheckoutPage() throws IOException {
		try {
			WELIconOnCheckOutPage.click();
			Reporting.updateTestReport("WEL Icon clicked successfully on Account Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click WEL Icon Account Page with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnCIAProduct() throws IOException {
		try {
			CIAProduct.click();
			Reporting.updateTestReport("CIA Product selected successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to select CIA Product with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnCAIAProduct() throws IOException {
		try {
			CAIAProduct.click();
			Reporting.updateTestReport("CAIA Product selected successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to select CAIA Product with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnShopCourseForCIAProduct() throws IOException {
		try {
			ClickShopCourses.click();
			Reporting.updateTestReport("Shop Courses was selected successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Shop Course with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnLevel1TestBankForCAIAProduct() throws IOException {
		try {
			Level1TestBank.click();
			Reporting.updateTestReport("Level1TestBank was selected successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Level1TestBank with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnViewCourseForCIAProduct() throws IOException {
		try {
			CIAViewCourse.click();
			Reporting.updateTestReport("ViewCourses was selected successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on ViewCourse with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnCIAAddProduct() throws IOException {
		try {

			CIAAddToCart.click();
			Reporting.updateTestReport("Add To Cart was selected successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Add To Cart with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnCPAAddProduct() throws IOException {
		try {

			CPAAddToCart.click();
			Reporting.updateTestReport("Add To Cart was selected successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Add To Cart with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnCIAeBook() throws IOException {
		try {
			CIAeBook.click();
			Reporting.updateTestReport("CIAeBook was selected successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on CIAeBook with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnCIAPrint() throws IOException {
		try {
			CIAPrint.click();
			Reporting.updateTestReport("CIAPrint Product was selected successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on CIAPrint with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnCAIAAddProduct() throws IOException {
		try {

			CAIAAddToCart.click();
			Reporting.updateTestReport("Add To Cart was selected successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Add To Cart with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnSelectQuantity() throws IOException {
		try {

			SelectQuantity.click();
			Reporting.updateTestReport("Quantity dropdown was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Quantity dropdown with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickeBookforCFAProduct() throws IOException {
		try {

			CFAeBook.click();
			Reporting.updateTestReport("CFA e-Book was selected successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on CFA e-Book with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickSaveAndContinueOnShippinInfoPage() throws IOException {
		try {

			SaveAndContinueShippingAddressPage.click();
			Reporting.updateTestReport("Sav And Continue button was clicked  successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on Save And Continue button with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

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

	public void clickOnPlaceOrderButton() throws IOException {
		try {
			PlaceOrder.click();
			Reporting.updateTestReport("Place Order button  was clicked", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Place Order button couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public String fetchOrderId() throws IOException {
		try {
			String id = OrderId.getText();
			System.out.println(id);
			Reporting.updateTestReport("Order id: " + id + " was successfully fetched",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return id;
		} catch (Exception e) {
			Reporting.updateTestReport("Order id was not fetched with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public String fetchTaxAmount() throws IOException {
		try {
			String totalTax = OrderTax.getText();
			Reporting.updateTestReport("Total Tax: " + totalTax + " was fetched",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return totalTax;
		} catch (Exception e) {
			Reporting.updateTestReport("Total Tax couldn't fetched", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			return "";
		}
	}

	public String fetchOrderTotal() throws IOException {
		try {
			String orderTotal = OrderTotal.getText();
			Reporting.updateTestReport("Order Total: " + orderTotal + " was fetched",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return orderTotal;
		} catch (Exception e) {
			Reporting.updateTestReport("Order Total couldn't fetched", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			return "";
		}
	}

	public void SelectingUSEButton() throws IOException {
		try {
			UseButton.click();
			Reporting.updateTestReport("Existing Address was used successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Use existing Address " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void SaveContinueCheckOutPage() throws IOException {
		try {
			SaveContinue.click();
			Reporting.updateTestReport("Save And Continue clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click Save And Continue button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnFreeTrail() throws IOException {
		try {
			CPAFreeTrail.click();
			Reporting.updateTestReport("CPA Free-trail was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click Save And Continue button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnCIAFreeTrail() throws IOException {
		try {
			CIAFreeTrial.click();
			Reporting.updateTestReport("CIA Free-trail was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click Save And Continue button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void FreeTrailFirstName(String FFName) throws IOException {
		try {
			FreeTrailFname.sendKeys(FFName);
			Reporting.updateTestReport("First: " + FFName + " was entered successfully in the First Name section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("FirstName not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void FreeTrailLastName(String FLName) throws IOException {
		try {
			FreeTrailLname.sendKeys(FLName);
			Reporting.updateTestReport("Last: " + FLName + " was entered successfully in the First Name section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("LastName not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public String EnterFreeTrailNewtUser() throws IOException {
		try {
			String dateTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String gEmail = "autowel" + dateTime + "@yopmail.com";
			FreeTrailEmail.sendKeys(gEmail);
			Reporting.updateTestReport("Email Id: " + gEmail + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return gEmail;
		} catch (Exception e) {
			Reporting.updateTestReport("Email Id was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public void FreeTrailCountry(String country) throws IOException {
		try {

			Select scountry = new Select(FreeTrailCountry);
			scountry.selectByVisibleText(country);
			Reporting.updateTestReport("County " + country + " has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select country " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void FreeTrailState(String Fstate) throws IOException {
		try {

			Select state = new Select(FreeTrailState);
			state.selectByVisibleText(Fstate);
			Reporting.updateTestReport("State " + Fstate + " has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select country " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void FreeTrailPassword(String FPassword) throws IOException {
		try {
			FreeTrailPassword.sendKeys(FPassword);
			Reporting.updateTestReport("Password: " + FPassword + " was entered successfully in the Password section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Password not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void FreeTrailTermsAndCOndtionsCheckBox() throws IOException {
		try {
			FreeTrailTermsAndConditions.click();
			Reporting.updateTestReport("TermsAndConditions Checbox selected Successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on TermsAndConditions Checbox with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void FreeTrailSignUpCheckBox() throws IOException {
		try {
			FreeTrailSignupCheckbox.click();
			Reporting.updateTestReport("SignupCheckbox Checbox selected Successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on SignupCheckbox Checbox with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void FreeTrailSignInButton() throws IOException {
		try {
			FreeTrailStartButton.click();
			Reporting.updateTestReport("Start Your Free Trail button clicked Successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Start Your Free Trail button  with the error message "
					+ e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void FreeTrailWELIcon() throws IOException {
		try {
			WELBrandLogonOnDeskTop.click();
			Reporting.updateTestReport("WEL Brand Desktop logo was clicked Successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on WEL Brand Desktop logo with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterFreeTrailEmail(String email) throws IOException {
		try {
			FreeTrailEmail.sendKeys(email);
			Reporting.updateTestReport("existing email Id was entered Successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to enter the existing email id with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public String FreeTrailCPAText() throws IOException {
		try {

			String cpaftext = CPAFreeTrailConfirmationText.getText();
			Reporting.updateTestReport("You’re almost set Pages was successfully loaded",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return cpaftext;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to load the You’re almost set page " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public String FreeTrailCIAText() throws IOException {
		try {

			String cpaftext = CPAFreeTrailConfirmationText.getText();
			Reporting.updateTestReport("You’re almost set Pages was successfully loaded",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return cpaftext;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to load the You’re almost set page " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public String ShippingMethodForUK() throws IOException {
		try {
			String ukmethod = UkShipMethod.getText();
			Reporting.updateTestReport("The Shipping method Displayed successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return ukmethod;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Shipping method " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public String ShippingMethodForSingapore() throws IOException {
		try {
			String Singaporemethod = SingaporeShipMethod.getText();
			Reporting.updateTestReport("The Shipping method Displayed successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return Singaporemethod;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Shipping method " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public String ShippingMethodForIndia() throws IOException {
		try {
			String IndiaShpmethod = IndiaShipMethod.getText();
			Reporting.updateTestReport("The Shipping method Displayed successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return IndiaShpmethod;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Shipping method " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public String ShippingMethodOneForCanada() throws IOException {
		try {
			String Canadamethod = CanadaShipMethod.getText();
			Reporting.updateTestReport("The Shipping method Displayed successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return Canadamethod;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Shipping method " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public String ShippingMethodTwoForCanada() throws IOException {
		try {
			String Canadamethod2 = CanadaShipMethod2.getText();
			Reporting.updateTestReport("The Shipping method Displayed successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return Canadamethod2;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Shipping method " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public void FreeTraiModelPopUpLoginButton() throws IOException {
		try {
			FreeTrailModelPopUpLogin.click();
			Reporting.updateTestReport("Login Button was clicked successfully on pop up page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on login button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void FreeTraiModelPopUpSubmitButton() throws IOException {
		try {
			FreeTrailSubbmit.click();
			Reporting.updateTestReport("Login Button was clicked successfully on pop up page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on login button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnAddNewAddressButton() throws IOException {
		try {
			AddNewAddress.click();
			Reporting.updateTestReport("Add New Address Link clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Add New Address link " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnWELIconOrderConfirmationPage() throws IOException {
		try {
			WELIconOrderConfirmationPage.click();
			Reporting.updateTestReport("WEL Icon Link clicked successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on WEL Icon link " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnAccountIconFromOrderConfirmationPage() throws IOException {
		try {
			AccountIconfFromOrderConfifrmationPage.click();
			Reporting.updateTestReport("Account Icon Link clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Account Icon link " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

}
