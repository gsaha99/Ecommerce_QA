package PageObjectRepo;

import Test_Suite.WEL_Test_Suite;
import utilities.CaptureScreenshot;
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

public class app_WEL_Repo {

	WEL_Test_Suite WEL_Test;
	public String SS_path = WEL_Test.SS_path;

	@FindBy(xpath = "//a[@class='icon-color nav-login']")
	WebElement StandaloneLogin;

	@FindBy(xpath = "//div[@class='deliveryMethodMainFirstDiv']/label")
	WebElement ShipMethod;

	@FindBy(xpath = "//label[@class='deliveryModeLabel']/span")
	WebElement ShipValue;

	@FindBy(xpath = "(//button[@id='wel_use_suggested_address_button']/span[text()='Use Selected Address'])[2]")
	WebElement UseSelectedShippingAddressButtonAddressDoctorPopUp;

	@FindBy(xpath = "(//button[@id='wel_billing_use_suggested_address_button']/span[text()='Use Selected Address'])[2]")
	WebElement UseSelectedBillingAddressButtonAddressDoctorPopUp;

	@FindBy(xpath = "(//label[@class='deliveryModeLabel deliveryModeLabelExceptFirst']/span)[1]")
	WebElement SecondShippingCharge;

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

	@FindBy(id = "nameOnCard")
	WebElement CardHolderName;

	@FindBy(xpath = "(//a[@href='/my-account'])[1]")
	WebElement AccountIconOnCartPage;

	@FindBy(xpath = "//a[contains(text(),'ACCOUNT')]")
	WebElement AccountIconfFromOrderConfifrmationPage;

	@FindBy(xpath = "(//div[@id='welSiteExceptLogoMainDiv']/div[@class='welGlobalHeaderNavbar'])[1]/span[3]/span/a")
	WebElement BackToCart;

	@FindBy(id = "removeEntry_0")
	WebElement RemoveIcon;

	@FindBy(xpath = "//a[contains(text(),'Create')]")
	WebElement CreateOne;

	@FindBy(id = "register.email")
	WebElement EmailAddress;

	@FindBy(id = "register.confirmEmail")
	WebElement ConfirmEmailAddress;

	@FindBy(name = "studentFirstName")
	WebElement FirstName;

	@FindBy(name = "studentLastName")
	WebElement LastName;

	@FindBy(name = "pwd")
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
	
	@FindBy(id = "address.region")
	WebElement SelectState;

	@FindBy(xpath = "//button[contains(text(),'I Accept')]")
	WebElement AcceptButtonOnWileyWELPrivacyAgreement;

	@FindBy(xpath = "//div[@class='left-panel']//li[4]/label[contains(text(),'Sign')]")
	WebElement SingOut;

	@FindBy(xpath = "//a[contains(text(),'ACCOUNT')]")
	WebElement AccountText;

	@FindBy(xpath = "(//div[@id='newAddressBtnDiv']/button[@id='addNewAddressButton'])")
	WebElement EnterNewAddressButton;

	@FindBy(xpath = "(//div[@id='newAddressBtnDiv']/button[@id='addNewBillingAddressButton'])")
	WebElement EnterNewAddressButtonOnBillPage;

	@FindBy(xpath = "//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_1']/a[3]")
	WebElement CIAProduct;

	@FindBy(xpath = "//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_3']/a[2]")
	WebElement CAIAProduct;

	@FindBy(xpath = "//div[@class='product-list-page']//div[@class='comparison-table-title-container header-sticky']//div[2]/a")
	WebElement CIAViewCourse;

	@FindBy(xpath = "((//div[@class='col-xs-12 col-sm-6 package-selection-left-col'])[2]//form//label)[2]")
	WebElement CIAeBook;

	@FindBy(xpath = "//div[@class='col-xs-12 col-sm-6 package-selection-left-col'])[2]//form//label)[1])")
	WebElement CIAPrint;

	@FindBy(xpath = "//div[@class='helpButton']")
	WebElement HelpButton;

	@FindBy(id = "addressSubmit")
	WebElement SaveAndContinueShippingAddressPage;

	@FindBy(id = "quantity_0")
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

	@FindBy(name = "firstName")
	WebElement FreeTrailFname;

	@FindBy(name = "lastName")
	WebElement FreeTrailLname;

	@FindBy(name = "email")
	WebElement FreeTrailEmail;

	@FindBy(name = "country")
	WebElement FreeTrailCountry;

	@FindBy(name = "region")
	WebElement FreeTrailState;

	@FindBy(name = "password")
	WebElement FreeTrailPassword;

	@FindBy(name = "termsAndConditions")
	WebElement FreeTrailTermsAndConditions;

	@FindBy(name = "signUp")
	WebElement FreeTrailSignupCheckbox;

	@FindBy(xpath = "//div[@class='form-box']//button[@class='form-button']")
	WebElement FreeTrailStartButton;

	@FindBy(xpath = "//div[@class='progress-bar-outer-box']//p[contains(text(),'almost')]")
	WebElement CPAFreeTrailConfirmationText;

	@FindBy(id = "addNewAddressButton")
	WebElement AddNewAddress;

	@FindBy(xpath = "(//div[@class='container-fluid course-pkg-background']//div[@class='card-footer bg-transparent course-pkg-cards-footer'])[1]//button[contains(text(),'VIEW COURSE OPTIONS')]")
	WebElement ViewCourseOptions;

	@FindBy(xpath = "(//div[@class='container-fluid course-pkg-background']//div[@class='card-footer bg-transparent course-pkg-cards-footer'])[1]//button")
	WebElement CPAViewCourse;

	@FindBy(xpath = "(//div[@class='container-fluid course-pkg-background']//div[@class='card-footer bg-transparent course-pkg-cards-footer'])[2]//button")
	WebElement CFAViewCourse;

	@FindBy(xpath = "(//a[@href='/cfa/products/level-2/platinum-cfa-course/'])[3]")
	WebElement CFAViewCoursePage;

	@FindBy(xpath = "(//div[@class='btn-group btn-group-toggle'])/label[2]")
	WebElement CFAeBook;

	@FindBy(xpath = "//div[@class='btn-group btn-group-toggle']/label[2]")
	WebElement CMAeBook;

	@FindBy(xpath = "//div[@class='btn-group btn-group-toggle']/label[1]")
	WebElement PrintEbook;

	@FindBy(xpath = "//button[contains(text(),'SHOP COURSES')]")
	WebElement ShopCourses;

	@FindBy(id = "voucher-remove-button_0")
	WebElement CouponRemove;

	@FindBy(xpath = "(//span[@class='welCheckoutContBtnText'])[2]")
	WebElement ClickOnCheckout;

	@FindBy(id = "username")
	WebElement username;

	@FindBy(id = "password")
	WebElement passsowrd;

	@FindBy(id = "j_username")
	WebElement FreeTrailLoginusername;

	@FindBy(id = "j_password")
	WebElement FreeTrailLoginpasssowrd;

	@FindBy(xpath = "//a[@class='recover-password pull-left checkoutForgotPwdLinkCss']")
	WebElement StandaloneForgotpwd;

	@FindBy(id = "forgottenPwd.email")
	WebElement EmailOnForgotPassword;

	@FindBy(xpath = "//button[@data-form_id='forgottenPwdForm']")
	WebElement SubmitbuttonOnForgotPassword;

	@FindBy(id = "guest.email")
	WebElement GuestUser;

	@FindBy(xpath = "//div[@id='guestCheckoutRegBtn']//span[@class='welCheckoutContBtnText']")
	WebElement CreateAccount;

	@FindBy(id = "guest.confemail")
	WebElement GuestConfinmEmail;

	@FindBy(id = "firstName")
	WebElement GuestFirstName;

	@FindBy(id = "lastName']")
	WebElement GuestLastName;

	@FindBy(id = "address.country")
	WebElement SelectCountryDropDown;

	@FindBy(id = "address.country")
	WebElement ShipCountry;

	@FindBy(id = "street1")
	WebElement AddressLine1;

	@FindBy(id = "line1")
	WebElement ShipAddressLineOne;

	@FindBy(id = "street2")
	WebElement AddressLine2;

	@FindBy(id = "postalCode")
	WebElement PostalCode;

	@FindBy(id = "postcode")
	WebElement ShipPostcode;

	@FindBy(id = "city")
	WebElement BillCity;

	@FindBy(id = "townCity")
	WebElement TownCity;

	@FindBy(id = "phoneNumber")
	WebElement BillPhoneNumber;

	@FindBy(id = "phone")
	WebElement ShipPhoneNumber;

	@FindBy(id = "j_username")
	WebElement LoginUser;

	@FindBy(id = "j_password")
	WebElement LoginPassword;

	@FindBy(xpath = "//button[@data-form_id='loginForm']")
	WebElement LoginAndContinue;

	@FindBy(xpath = "//a[@href='/login/pw/request?t=ch']")
	WebElement ForgotpwdOnCheckoutPage;

	@FindBy(xpath = "//span[@class='addDiscountLabelOnCart']")
	WebElement AddaDiscountCode;

	@FindBy(id = "discountCodeValue")
	WebElement DiscountLabel;

	@FindBy(xpath = "(//input[@id='discountCodeValue'])[2]")
	WebElement ExtraDiscount;

	@FindBy(id = "discountCodeValue")
	WebElement ExtraDiscountCode;

	@FindBy(id = "discountApplyBtn")
	WebElement ApplyDiscountButton;

	@FindBy(xpath = "//div[@class='float-left my-account-header']/label")
	WebElement WELIconHomePage;

	@FindBy(xpath = "//a[@class='navbar-brand brand-logo-top-desktop']")
	WebElement WELBrandLogonOnDeskTop;

	@FindBy(xpath = "(//div[@class='simple-responsive-banner-component'])[1]/a")
	WebElement WELIconOnCheckOutPage;

	@FindBy(id = "addressSubmit")
	WebElement ShipSaveAndContinue;

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

	@FindBy(id = "sameAsBillingLabel")
	WebElement ShippingBillingSameAddress;

	@FindBy(xpath = "(//button[@id='placeOrder'])[1]")
	WebElement PlaceOrder;

	@FindBy(xpath = "(//div[@class='orderConfirmationLabelVal marginTop10'])[2]")
	WebElement OrderId;

	@FindBy(xpath = "(//div[@class='col-md-5 col-6 noPadding orderReviewDetailsValue'])[2]")
	WebElement OrderTax;

	@FindBy(xpath = "//div[contains(text(),'Taxes')]/following-sibling::div")
	WebElement TaxAmount;

	@FindBy(xpath = "//div[@class='col-md-5 col-6 noPadding orderReviewTotalPrice']")
	WebElement OrderTotal;

	@FindBy(xpath = "(//div[contains(text(),'Shipping')])[2]/following-sibling::div")
	WebElement ShippingChargeInOrderConfirmation;

	@FindBy(xpath = "(//button[@class='useThisAddressBtn'])[1]")
	WebElement UseButton;

	@FindBy(xpath = "//div[@class='modal-content']//button")
	WebElement FreeTrailModelPopUpLogin;

	@FindBy(xpath = "//button[@class='button form-button welCheckoutBtn submitWelForm width100']")
	WebElement FreeTrailSubbmit;

	@FindBy(id = "address.region")
	WebElement EnterState;

	@FindBy(xpath = "//div[@class='welCheckoutBtnDiv multiPaymentPaypalBtn']/button")
	WebElement ProccedtoPaypal;

	@FindBy(xpath = "//div[@class='welCheckoutBtnDiv multiPaymentPaypalCreditBtn']/button")
	WebElement ProceedtoPaypalCredit;

	@FindBy(name = "login_email")
	WebElement PaypalUsername;

	@FindBy(xpath = "//div[@class='actions']/button[@name='btnNext']")
	WebElement NextButtonOnPaypalPage;

	@FindBy(name = "login_password")
	WebElement Paypalpassword;

	@FindBy(xpath = "//div[@class='actions']/button[@name='btnLogin']")
	WebElement PaypalLoginButton;

	@FindBy(xpath = "//div[@class='CheckoutButton_buttonWrapper_2VloF']/button")
	WebElement PaypalReviewOrderbutton;

	@FindBy(xpath = "((//div[@class='FundingInstrument_container_16IeJ'])[2]//span)[1]")
	WebElement PaypalCreditRadioButton;

	@FindBy(xpath = "//div[@id='billingMultiPaymentOptionValues']/ul/li[3]/a/span[@class='billingPaymentMultiNavTitle']")
	WebElement Paypalpayment;

	@FindBy(xpath = "//div[@id='billingMultiPaymentOptionValues']/ul/li[4]/a/span")
	WebElement PaypalCredit;

	@FindBy(xpath = "//i[contains(text(),'(We cannot ship to PO boxes)')]")
	WebElement DontShipToPOBox;

	@FindBy(xpath = "//div[@class='cart']")
	WebElement CartIconOnMyAccount;

	@FindBy(xpath = "(//img[@title='Wiley EL'])[1]")
	WebElement WELHomePageLogoOnCartPage;

	@FindBy(xpath = "//button[contains(text(),'ADD TO CART')]")
	WebElement AddToCartButtonOnPDP;

	@FindBy(id = "recommendTitle")
	WebElement RecommendationTitle;

	@FindBy(xpath = "//div[@class='recommendIndividualProductSummary']")
	WebElement ProductSummaryOnRecommendationTitle;

	@FindBy(xpath = "//span[contains(text(),'Add to Cart')]")
	WebElement AddToCartButtonOnRecommendationTitle;

	@FindBy(xpath = "//a[contains(text(),'View Product')]")
	WebElement ViewProductLinkOnRecommendationTitle;

	@FindBy(xpath = "(//a[@class='cart-link'])[2]")
	WebElement CartIconOnPDP;

	@FindBy(xpath = "//a[@class='subscription-title-link']")
	WebElement ProductLinkOnCartPage;

	@FindBy(xpath = "//label[@for='eBook0']")
	WebElement EBookButtonPDP;

	@FindBy(xpath = "(//p[@class='modal-notice'])[2]")
	WebElement ErrorModal;

	@FindBy(xpath = "//button[contains(text(),'KEEP SHOPPING')]")
	WebElement KeepShoppingButtonOnErrorModal;

	@FindBy(xpath = "//p[contains(text(),'PACKAGE')]")
	WebElement PackageButtonOnCIAPDP;

	@FindBy(xpath = "//div[@class='row pageMainContainer no-margin']//div[@class='checkout-steps']/a[1]")
	WebElement StudentVerification;

	@FindBy(xpath = "(//div[@id='shippingSavedAddressShowBtn'])[1]")
	WebElement showmore;

	@FindBy(xpath = "//div[@id='savedAddressDiv_6']//div[@id='savedAddressButtonsDiv']/button[1]")
	WebElement USEbuttonForExistingAddress;

	@FindBy(xpath = "//div[@class='step-head checkoutCompletedStep']//div[@class='edit']/a")
	WebElement EditIcononShippingPage;

	@FindBy(xpath = "(//div[@class='row navitem-div-medium navitem-div'])[2]//a")
	WebElement Productdropdown;

	@FindBy(xpath = "//div[@class='col dropdown-item product-dropdown-desktop-item item1']//li[2]/a")
	WebElement CPATestBank;

	@FindBy(xpath = "//div[@class='partner-detail partner-list-container']//div[@class='col-xs-12 col-sm-6 package-selection-col']//button")
	WebElement PartnerProductAddToCartButton;

	@FindBy(xpath = "//div[@class='form-group']//input[@id='inputPartnerSearch']")
	WebElement PartnerInputSearch;

	@FindBy(xpath = "//div[@class='row taxCalculationDetails orderReviewRightPanel']/"
			+ "div[@class='col-6 noPadding price orderDetailCommonVal']")
	WebElement TaxInOrderSummaryTab;

	@FindBy(xpath = "//a[@href='/checkout/multi/payment-method/add/']/span[@class='stepEdit']")
	WebElement PaymentDetailsEditIcon;

	@FindBy(xpath = "//a[@href='/checkout/multi/delivery-address/add/']/span[@class='stepEdit']")
	WebElement ShippingDetailsEditIcon;

	@FindBy(xpath = "//label[contains(text(),'Part 1')]")
	WebElement Part1InCIAPDP;

	@FindBy(xpath = "//div[@class='navbar-collapse']/ul/li[3]/a")
	WebElement ActiavteCodeIcon;

	@FindBy(id = "pin")
	WebElement PinInput;

	@FindBy(xpath = "//div[@class='pinActivationBodyMainDiv']//button")
	WebElement ActivateButton;

	@FindBy(xpath = "(//div[@id='standaloneLoginMainDiv']//a)[1]")
	WebElement CreateAccountForPin;

	@FindBy(xpath = "//form[@id='loginForm']/div[4]/button")
	WebElement PINLoginButton;

	@FindBy(xpath = "//div[@class='container product-categories-container']//div[2]//dd/a[contains(text(),'CPA')]")
	WebElement DeanDortonCPAProduct;

	@FindBy(xpath = "(//div[@class='col']//button)[2]")
	WebElement DeanDortonAddToCart;

	@FindBy(xpath = "//input[@placeholder='Enter your inbox here']")
	WebElement EnterEmailIdInYopmail;

	@FindBy(xpath = "//button[@title='Check Inbox @yopmail.com']")
	WebElement clickOnCheckInboxButton;

	@FindBy(xpath = "//th[contains(text(),'Quantity')]")
	WebElement QuantityInEmailHeader;

	@FindBy(xpath = "//th[contains(text(),'Price')]")
	WebElement PriceInEmailHeader;

	@FindBy(xpath = "//th[contains(text(),'Order')]/strong")
	WebElement OrderIdEmailHeader;

	@FindBy(xpath = "//th[contains(text(),'Total')]")
	WebElement TotalInEmailHeader;

	@FindBy(xpath = "(//b[contains(text(),'Ship to:')]//parent::div/following-sibling::div)[2]")
	WebElement AddressLine1ShippingInMail;

	@FindBy(xpath = "(//b[contains(text(),'Ship to:')]//parent::div/following-sibling::div)[4]")
	WebElement CityStateShippingInMail;

	@FindBy(xpath = "//b[contains(text(),'Card No.')]//parent::div/following-sibling::div")
	WebElement CardNumberLastFourDigitInMail;

	@FindBy(xpath = "//div[contains(text(),'Next Day Shipping - ')]")
	WebElement NextDayShipping;

	@FindBy(xpath = "(//div[@class='col dropdown-item product-dropdown-desktop-item item2']/ul/li/a)[1]")
	WebElement CPASupplements;

	@FindBy(xpath = "(//div[@class='col dropdown-item product-dropdown-desktop-item item3 break-spaces undefined']/ul/li/a)[1]")
	WebElement CFASupplements;

	@FindBy(xpath = "(//div[@class='col dropdown-item product-dropdown-desktop-item item1']/ul/li/a)[1]")
	WebElement CMASupplements;

	@FindBy(xpath = "(//div[@class='col-xs-12 col-sm-4 supplements-cards'])[2]/div/a")
	WebElement CPASuppelementProduct;

	@FindBy(xpath = "(//div[@class='col-xs-12 col-sm-4 supplements-cards'])[1]/div/a")
	WebElement CMASupplementProduct;

	@FindBy(xpath = "(//div[@class='col-xs-12 col-sm-4 supplements-cards']/div/a)[1]")
	WebElement CFASupplementProduct;

	@FindBy(xpath = "//button[contains(text(),'ADD')]")
	WebElement SupplementAddToCart;

	@FindBy(xpath = "(//div[@class='col-6 noPadding price orderDetailCommonVal'])[2]")
	WebElement ShippingCharge;

	@FindBy(xpath = "(//div[@class='sc-AxjAm bcMPWx my-accounts-header']//ul/li)[3]")
	WebElement ActivatePIN;

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
			AccountText.getText();
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
			Reporting.updateTestReport("Explore Coursee button clicked successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to click on  Explore Course button with the error message " + e.getClass().toString(),
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
			GuestFirstName.clear();
			Thread.sleep(1000);
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
			GuestLastName.clear();
			Thread.sleep(1000);
			GuestLastName.sendKeys(GLNAME);
			Reporting.updateTestReport("Last: " + GLNAME + " was entered successfully in LastName section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("LastName was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

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
			ShipAddressLineOne.clear();
			Thread.sleep(1000);
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
			BillCity.clear();
			Thread.sleep(1000);
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
			ShipPostcode.clear();
			Thread.sleep(1000);
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
			BillPhoneNumber.clear();
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
			ShipPhoneNumber.clear();
			Thread.sleep(1000);
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
	
	
	public void selectStateFromDropsown(String state) throws IOException {
		try {

			Select stateDropdown = new Select(SelectState);
			stateDropdown.selectByVisibleText(state);
			Reporting.updateTestReport("State " + state + " has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select state " + e.getClass().toString(),
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
			ExtraDiscount.sendKeys(PromoCode);
			Reporting.updateTestReport("PromoCode was entered Successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to enter the PromoCode with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void EnterCouponExtraDiscountCode(String PromoCode) throws IOException {
		try {
			ExtraDiscountCode.sendKeys(PromoCode);
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

	public String fetchTaxValue() throws IOException {
		try {
			String totalTax = TaxAmount.getText();
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

	public void ShippingMethodValidation() throws IOException {
		try {
			ShipMethod.click();
			Reporting.updateTestReport(
					"The Shipping method: " + ShipMethod.getText().split("-")[0] + " was displayed successfully"
							+ " with shipping charge: " + ShipValue.getText(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Shipping method " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

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

	public void ClickOnEnterNewAddressButtonOnShippingPage() throws IOException {
		try {
			EnterNewAddressButton.click();
			Reporting.updateTestReport("Edit button clicked successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Edit button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnEnterNewAddressButtonOnBillingPage() throws IOException {
		try {
			EnterNewAddressButtonOnBillPage.click();
			Reporting.updateTestReport("Edit button clicked successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Edit button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

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

	public WebElement returnUseSelectedShippingAddressButtonAddressDoctorPopUp() {
		return UseSelectedShippingAddressButtonAddressDoctorPopUp;
	}

	public WebElement returnUseSelectedBillingAddressButtonAddressDoctorPopUp() {
		return UseSelectedBillingAddressButtonAddressDoctorPopUp;
	}


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
	}


	public void SelectPayPalOption() throws IOException {
		try {
			Paypalpayment.click();
			Reporting.updateTestReport("PayPal Options has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select Paypal Option " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void SelectPayPalCreditOption() throws IOException {
		try {
			PaypalCredit.click();
			Reporting.updateTestReport("PayPalCredit Options has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select PaypalCredit Option " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnProceedToPayPal() throws IOException {
		try {
			ProccedtoPaypal.click();
			Reporting.updateTestReport("Procced to Paypal button has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to click on Proceed to Paypal button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnProceedToPayPalCredit() throws IOException {
		try {
			ProceedtoPaypalCredit.click();
			Reporting.updateTestReport("Procced to Paypal button has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to click on Proceed to Paypal button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void EnterPayPalUserName(String paypalusername) throws IOException {
		try {
			PaypalUsername.sendKeys(paypalusername);
			;
			Reporting.updateTestReport("Paypal email" + paypalusername + "was entered successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the paypal username " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnNextButtonPayPalLoginPage() throws IOException {
		try {
			NextButtonOnPaypalPage.click();
			;
			Reporting.updateTestReport("Next button clicked successfully on Paypal Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Next button Paypal Page" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void EnterPayPalPassword(String paypalpassword) throws IOException {
		try {
			Paypalpassword.sendKeys(paypalpassword);
			;
			Reporting.updateTestReport("Paypal email" + paypalpassword + "was entered successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the paypal username " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnPaypalLogin() throws IOException {
		try {
			PaypalLoginButton.click();
			;
			;
			Reporting.updateTestReport("Paypal Login button  was Clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Paypal login button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnPaypalReviewOrder() throws IOException {
		try {
			PaypalReviewOrderbutton.click();
			Reporting.updateTestReport("Continue to Review Order  was Clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Continue to Review Order button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnPaypalCreditRadioButton() throws IOException {
		try {
			PaypalCreditRadioButton.click();
			Reporting.updateTestReport("PayPal Credit Radio button was Clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on PayPal Credit Radio button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
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
	 * @Author: Anindita
	 * 
	 * @Description: Clicks on the cart Icon on my account page
	 */
	public void clickOnCartIconOnMyAccountPage() throws IOException {
		try {
			CartIconOnMyAccount.click();
			Reporting.updateTestReport("The cart icon on the my account page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("The cart icon on the my account page couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Clicks on the WEL HomePage Logo On CartPage
	 */
	public void clickOnWELHomePageLogoOnCartPage() throws IOException {
		try {
			WELHomePageLogoOnCartPage.click();
			Reporting.updateTestReport("The WEL HomePage Logo On Cart Page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("The WEL HomePage Logo On Cart Page couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
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
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Checks Recommendation Title on cart page
	 */
	public void checkRecommendationTitle() throws IOException {
		try {
			if (RecommendationTitle.isDisplayed())
				Reporting.updateTestReport("Recommendation Title was present on cart page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Recommendation Title was not present on cart page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		} catch (Exception e) {
			Reporting.updateTestReport("Recommendation Title was not present on cart page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Checks Recommendation Title Summary line on cart page
	 */
	public void getCartUpsellsSummaryLine() throws IOException {
		try {
			if (ProductSummaryOnRecommendationTitle.isDisplayed())
				Reporting.updateTestReport(
						"Recommendation Title was present on cart page with a summary line: "
								+ ProductSummaryOnRecommendationTitle.getText(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Recommendation Title Summary line was not present on cart page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		} catch (Exception e) {
			Reporting.updateTestReport("Recommendation Title Summary line was not present on cart page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Checks the Add to cart button on Recommendation Title on cart
	 * page
	 */
	public void checkAddToCartButtonOnRecommendationTitle() throws IOException {
		try {
			if (AddToCartButtonOnRecommendationTitle.isDisplayed())
				Reporting.updateTestReport("Add to cart button on Recommendation Title was present on cart page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Add to cart button on Recommendation Title was not present on cart page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		} catch (Exception e) {
			Reporting.updateTestReport("Add to cart button on Recommendation Title was not present on cart page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Checks the View Product link on Recommendation Title on cart
	 * page
	 */
	public void checkViewProductLinkOnRecommendationTitle() throws IOException {
		try {
			if (ViewProductLinkOnRecommendationTitle.isDisplayed())
				Reporting.updateTestReport("View Product link on Recommendation Title was present on cart page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("View Product link on Recommendation Title was not present on cart page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		} catch (Exception e) {
			Reporting.updateTestReport("View Product link on Recommendation Title was not present on cart page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Clicks on the View Product Link On Recommendation Title
	 */
	public void clickOnViewProductLinkOnRecommendationTitle() throws IOException {
		try {
			ViewProductLinkOnRecommendationTitle.click();
			Reporting.updateTestReport("The View Product Link On Recommendation Title was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("The View Product Link On Recommendation Title couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Clicks on the Add to cart button On Recommendation Title
	 */
	public void clickOnAddToCartButtonOnRecommendationTitle() throws IOException {
		try {
			AddToCartButtonOnRecommendationTitle.click();
			Reporting.updateTestReport("The Add to cart button On Recommendation Title was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("The Add to cart button On Recommendation Title couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Clicks on the The Cart icon on PDP
	 */
	public void clickOnCartIconOnPDP() throws IOException {
		try {
			CartIconOnPDP.click();
			Reporting.updateTestReport("The Cart icon on PDP was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("The The Cart icon on PDP couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Clicks on Product link on cart page
	 */
	public void clickOnProductLinkOnCartPage() throws IOException {
		try {
			ProductLinkOnCartPage.click();
			Reporting.updateTestReport("The Product link on cart page was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("The Product link on cart page couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Clicks on the EBook button on PDP
	 */
	public void clickOnEBookButtonPDP() throws IOException {
		try {
			EBookButtonPDP.click();
			Reporting.updateTestReport("The EBook button on PDP was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("The EBook button on PDP couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Checks the Error Modal after trying to add multiple quantity
	 * digital product
	 */
	public void checkErrorModal() throws IOException {
		try {
			if (ErrorModal.isDisplayed())
				Reporting.updateTestReport("Error Modal appeared after trying to add multiple quantity digital product",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport(
						"Error Modal didn't appear after trying to add multiple quantity digital product",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Error Modal didn't appear after trying to add multiple quantity digital product",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Clicks on the Keep Shopping Button On Error Modal
	 */
	public void clickOnKeepShoppingButtonOnErrorModal() throws IOException {
		try {
			KeepShoppingButtonOnErrorModal.click();
			Reporting.updateTestReport("The Keep Shopping Button On Error Modal was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("The Keep Shopping Button On Error Modal couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Clicks on the Package Button On CIA PDP
	 */
	public void clickOnPackageButtonOnCIAPDP() throws IOException {
		try {
			PackageButtonOnCIAPDP.click();
			Reporting.updateTestReport("The Package Button On CIA PDP was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("The Package Button On CIA PDP couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void VerificationOfStudentForUS() throws IOException {
		try {

			String studentmessage = StudentVerification.getText();
			Reporting.updateTestReport("The message: " + studentmessage + " Displayed successfully for US Address",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to the message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnShowMoreShippingAddress() throws IOException {
		try {

			showmore.click();
			Reporting.updateTestReport("The show mote clcicked successfully on Shipping Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on show more on Shipping page" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnUseButtonForExistingShippingAddress() throws IOException {
		try {

			USEbuttonForExistingAddress.click();
			Reporting.updateTestReport("The use button was clcicked successfully on Shipping Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Use Icon on Shipping page" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void VerificationOfStudentForNonUS() throws IOException {
		try {

			String studentmessage = StudentVerification.getText();
			Reporting.updateTestReport("The message: " + studentmessage + " Displayed successfully for NON US Address",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to the message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnEditIcononShippingPage() throws IOException {
		try {

			EditIcononShippingPage.click();
			Reporting.updateTestReport("The edit Icon was clcicked successfully on Shipping Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on edit Icon on Shipping page" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void ClickOnProductdropdown() throws IOException {
		try {
			Productdropdown.click();
			Reporting.updateTestReport("Product dropdown  was Clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on product drowndown " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnCPATestBank() throws IOException {
		try {
			CPATestBank.click();
			Reporting.updateTestReport("CPA Test Bank product selected successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to select CPA Test Bank" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}



	public void ClickOnAddToCartUniversityProduct() throws IOException {
		try {
			PartnerProductAddToCartButton.click();
			Reporting.updateTestReport("Add TO Cart was clickedc successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Add To Cart" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void EnterUniversityName(String universityname) throws IOException {
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
	 * @Date: 01/02/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Fetches the tax from order summary tab
	 */
	public String fetchTaxInOrderSummaryTab() throws IOException {
		try {
			Reporting.updateTestReport(
					"Tax with value: " + TaxInOrderSummaryTab.getText() + " was displayed in the order summary tab",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return TaxInOrderSummaryTab.getText();
		} catch (Exception e) {
			Reporting.updateTestReport("Tax couldn't be displayed in the order summary tab",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Date: 01/02/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Clicks on Payment Details Edit Icon
	 */
	public void clickOnPaymentDetailsEditIcon() throws IOException {
		try {
			PaymentDetailsEditIcon.click();
			Reporting.updateTestReport("The Payment Details Edit Icon was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("The Payment Details Edit Icon couldn't be clicked ",
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
	 * @Date: 01/02/23
	 * 
	 * @Author: Anindita
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

	public void ClickOnActiacteCodeIcon() throws IOException {
		try {
			ActiavteCodeIcon.click();
			Reporting.updateTestReport("ActivateCode Icon was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on ActivateCode" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void EnterPinOnInputField(String pin) throws IOException {
		try {
			PinInput.sendKeys(pin);
			Reporting.updateTestReport("PIN was entered successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the PIN" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnActivateButton() throws IOException {
		try {
			ActivateButton.click();
			Reporting.updateTestReport("Activate  button was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Activate button" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnPINLoginButton() throws IOException {
		try {
			PINLoginButton.click();
			Reporting.updateTestReport("Login  button was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Login button" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnCreateNewAccountForPIN() throws IOException {
		try {
			CreateAccountForPin.click();
			Reporting.updateTestReport("CreateNewAccount Link was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click CreateNewAccount Link" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnDeanDortonCPAProduct() throws IOException {
		try {
			DeanDortonCPAProduct.click();
			Reporting.updateTestReport("DeanDorton CPA product was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on  DeanDorton CPA product" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void EntePINUserName(String username) throws IOException {
		try {
			LoginUser.sendKeys(username);
			Reporting.updateTestReport("UserName was entered successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the UserName" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void EntePINPassword(String password) throws IOException {
		try {
			LoginPassword.sendKeys(password);
			Reporting.updateTestReport("Password was entered successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the UserName" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnAddToCartForDeanDortonProduct() throws IOException {
		try {
			DeanDortonAddToCart.click();
			Reporting.updateTestReport("Add TO Cart was clicked successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Add To Cart" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * 
	 * @Description: Enters the email id in Yopmail
	 * 
	 * @Date: 02/01/23
	 */
	public void enterEmailIdInYopmail(String username) throws IOException {
		try {
			EnterEmailIdInYopmail.sendKeys(username);
			Reporting.updateTestReport("Email entered : " + username + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter Email Id", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		}

	}

	/*
	 * @Author: Anindita
	 * 
	 * @Description: Clicks on check inbox in yopmail after entering user id
	 * 
	 * @Date: 02/01/23
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
	 * @Author: Anindita
	 * 
	 * @Description: Fetches the shipping charge from Orderv Confirmation page
	 * 
	 * @Date: 02/01/23
	 */
	public String fetchShippingChargeInOrderConfirmation() throws IOException {
		try {
			String shippingChargeInOrderConfirmation = ShippingChargeInOrderConfirmation.getText();
			Reporting.updateTestReport("Shipping charge: " + shippingChargeInOrderConfirmation + " was fetched",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return shippingChargeInOrderConfirmation;
		} catch (Exception e) {
			Reporting.updateTestReport("Shipping charge from the order confirmation page couldn't fetched",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Author: Anindita
	 * 
	 * @Date: 2/2/23
	 * 
	 * @Description: Validates the order confirmation mail header elements
	 */
	public void checkMailHeaderElements() throws IOException {
		try {
			try {
				if (QuantityInEmailHeader.isDisplayed())
					Reporting.updateTestReport("Quantity was present in the header in email",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Quantity was not present in the header in email",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			} catch (Exception e) {
				Reporting.updateTestReport("Quantity was not present in the header in email",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			try {
				if (PriceInEmailHeader.isDisplayed())
					Reporting.updateTestReport("Price was present in the header in email",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Price was not present in the header in email",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			} catch (Exception e) {
				Reporting.updateTestReport("Price was not present in the header in email",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			try {
				if (TotalInEmailHeader.isDisplayed())
					Reporting.updateTestReport("Total was present in the header in email",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Total was not present in the header in email",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			} catch (Exception e) {
				Reporting.updateTestReport("Total was not present in the header in email",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		} catch (Exception e) {
			Reporting.updateTestReport("Some of the header element" + " was not present in the header in email",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * 
	 * @Date: 2/2/23
	 * 
	 * @Description: Validates the order id in order confirmation mail
	 */
	public void validateOrderIdInMail(String orderId) throws IOException {
		try {
			if (OrderIdEmailHeader.getText().substring(1).equalsIgnoreCase(orderId))
				Reporting.updateTestReport(
						orderId + " was present in the header in email which is same as order confirmation page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport(
						OrderIdEmailHeader.getText().substring(1) + " was  present in the header in email"
								+ "which is not same as Order Id: " + orderId + " order confirmation page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		} catch (Exception e) {
			Reporting.updateTestReport("Order Id " + " was not present in the header in email",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * 
	 * @Date: 2/2/23
	 * 
	 * @Description: Validates the shipping address line 1 in order confirmation
	 * mail
	 */
	public void validateShippingAddressLine1InMail(String line1) throws IOException {
		try {
			if (AddressLine1ShippingInMail.getText().equalsIgnoreCase(line1))
				Reporting.updateTestReport(line1
						+ " was present in the header in email which is same as Address line 1 order confirmation page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport(
						AddressLine1ShippingInMail.getText().substring(1) + " was  present in the header in email"
								+ "which is not same as Address line 1: " + line1 + " order confirmation page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		} catch (Exception e) {
			Reporting.updateTestReport("Address line 1 " + " was not present in the header in email",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * 
	 * @Date: 2/2/23
	 * 
	 * @Description: Validates the shipping address City and zip code in order
	 * confirmation mail
	 */
	public void validateShippingCityZipCodeInMail(String city, String zipCode) throws IOException {
		try {
			String cityInMail = CityStateShippingInMail.getText().split(",")[0];
			String zipStateInMail = CityStateShippingInMail.getText().split(",")[1];
			if (cityInMail.equalsIgnoreCase(city))
				Reporting.updateTestReport(cityInMail
						+ " was present in the header in email which is same as City in order confirmation page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport(
						cityInMail + " was  present in the header in email" + "which is not same as City: " + city
								+ " in order confirmation page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			if (zipStateInMail.contains(zipCode))
				Reporting.updateTestReport(
						zipStateInMail + " was present in email which contains zip Code: " + zipCode
								+ " in order confirmation page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport(
						zipStateInMail + " was present in email which doesn't contain zip Code: " + zipCode
								+ " in order confirmation page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("City and zip code " + " was not present in email body",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * 
	 * @Date: 2/2/23
	 * 
	 * @Description: Validates the shipping address City and zip code in order
	 * confirmation mail
	 */
	public void validateCardNumberInMail(String number) throws IOException {
		try {
			if (CardNumberLastFourDigitInMail.getText().equalsIgnoreCase(number.substring(number.length() - 4)))
				Reporting.updateTestReport(number.substring(number.length() - 4)
						+ " was present in the header in email which is same as last four digit in order confirmation page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport(
						CardNumberLastFourDigitInMail.getText() + " was present in email"
								+ "which is not same as Card number entered: " + number.substring(number.length() - 4),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		} catch (Exception e) {
			Reporting.updateTestReport("Card number " + " was not present in email body",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * 
	 * @Description: Selects the next day shipping method
	 * 
	 * @Date: 02/02/23
	 */
	public void selectNextDayShipping() throws IOException {
		try {
			NextDayShipping.click();
			Reporting.updateTestReport("Next day shipping method was selected successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Next day shipping method couldn't be selected",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	

	/*
	 * @Author: Anindita
	 * 
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

	public void ClickOnViewSupplementsforCPA() throws IOException {
		try {
			CPASupplements.click();
			Reporting.updateTestReport("View All CPA Supplements  was Clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on View All CPA Supplements option " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnViewSupplementsforCMA() throws IOException {
		try {
			CMASupplements.click();
			Reporting.updateTestReport("View All CMA Supplements  was Clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on View All CMA Supplements option " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnViewSupplementsforCFA() throws IOException {
		try {
			CFASupplements.click();
			Reporting.updateTestReport("View All CFA Supplements  was Clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on View All CFA Supplements option " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnSupplementsProductforCPA() throws IOException {
		try {
			CPASuppelementProduct.click();
			Reporting.updateTestReport("Supplement product was Clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Supplement product " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnSupplementsProductforCMA() throws IOException {
		try {
			CMASupplementProduct.click();
			Reporting.updateTestReport("Supplement product was Clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Supplement product " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnSupplementsProductforCFA() throws IOException {
		try {
			CMASupplementProduct.click();
			Reporting.updateTestReport("Supplement product was Clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Supplement product " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnAddToCartForSupplement() throws IOException {
		try {
			SupplementAddToCart.click();
			Reporting.updateTestReport("Add To Cart  was Clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Add TO Cart " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnCMAFreeTrail() throws IOException {
		try {
			CPAFreeTrail.click();
			Reporting.updateTestReport("CMA Free-trail was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click Save And Continue button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void ClickOnCFAFreeTrail() throws IOException {
		try {
			CPAFreeTrail.click();
			Reporting.updateTestReport("CFA Free-trail was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click Save And Continue button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public String FreeTrialFirstName() throws IOException {
		try {

			String cpaftext = FreeTrailFname.getText();
			Reporting.updateTestReport("First Name filed is appeard successfully on  Free Trail from",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return cpaftext;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to appear the FirstName field " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public String FreeTrailLastName() throws IOException {
		try {

			String cpaftext = FreeTrailLname.getText();
			Reporting.updateTestReport("Last Name filed is appeard successfully on S Free Trail from",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return cpaftext;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to appear the LastName field " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public String FreeTrailEmail() throws IOException {
		try {

			String cpaftext = FreeTrailEmail.getText();
			Reporting.updateTestReport("Email filed is appeard successfully on Free Trail from",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return cpaftext;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to appear the Email field " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public String FreeTrailPassword() throws IOException {
		try {

			String cpaftext = FreeTrailPassword.getText();
			Reporting.updateTestReport("Password filed is appeard successfully on Free Trail from",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return cpaftext;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to appear the Password field " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	public void ClickOnActiactePIN() throws IOException {
		try {
			ActivatePIN.click();
			Reporting.updateTestReport("ActivatePIN Icon was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on ActivateCode" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public String ShippingChargeForMultipleProducts() throws IOException {
		try {
			String ShipCharge = ShippingCharge.getText();
			System.out.println(ShipCharge);
			Reporting.updateTestReport("Shipping Charge: " + ShipCharge + " was successfully fetched",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return ShipCharge;
		} catch (Exception e) {
			Reporting.updateTestReport("Order id was not fetched with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

}
