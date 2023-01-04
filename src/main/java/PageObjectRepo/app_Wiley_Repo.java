package PageObjectRepo;

import java.io.IOException;
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

import Test_Suite.Vet_Test_Suite;
import Test_Suite.Wiley_NA_Cart_Test_Suite;
import Test_Suite.Wiley_NA_Cart_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

public class app_Wiley_Repo {
	Wiley_NA_Cart_Test_Suite wiley_NA_Cart_Test;
	public String SS_path = wiley_NA_Cart_Test.SS_path;

	WebDriver driver;



	@FindBy(xpath = "//button[contains(text(),'View Cart')]")
	WebElement ViewCartButton;

	@FindBy(xpath = "(//span[contains(text(),'Proceed to Checkout')])[2]")
	WebElement ProceedToCheckoutButton;

	




	@FindBy(xpath = "//span[contains(text(),'Save and Continue')]")
	WebElement SaveAndContinueButton;


	@FindBy(xpath = "//select[@id='address.country']")
	WebElement SelectCountryDropDown;

	

	

	@FindBy(xpath = "(//input[@id='postcode'])[1]")
	WebElement ShippingZIPCode;

	@FindBy(xpath = "(//input[@id='townCity'])[1]")
	WebElement ShippingCity;

	@FindBy(xpath = "(//input[@id='address.region'])[1]")
	WebElement SelectStateDropDown;



	@FindBy(xpath = "(//input[@name='deliveryMode'])[2]")
	WebElement ShippingMethod;




	@FindBy(xpath = "//select[@id='expiryMonth']")
	WebElement ExpirationDateForMonth;

	@FindBy(xpath = "//select[@id='expiryYear']")
	WebElement ExpirationDateForYear;

	@FindBy(xpath = "//input[@id='securityCode']")
	WebElement CVV_Number;

	@FindBy(xpath = "//button[@id='placeOrder']")
	WebElement Place_OrderButton;

	

	@FindBy(xpath = "(//div[@class='products-list']//section//div//a//img)//following::div//h3//a")
	WebElement SRP_WileyProduct;


	@FindBy(xpath = "//input[@id='j_username']")
	WebElement ExistingWileyUserMailID;

	@FindBy(xpath = "//input[@id='j_password']")
	WebElement ExistingWileyUserPassword;

	@FindBy(xpath = "//span[contains(text(),'Log In & Continue')]")
	WebElement LogInAndContinueButton;

	@FindBy(xpath = "//select[@class='cartItemBookQty']")
	WebElement QuantityDropDown;

	

	@FindBy(xpath = "(//span[contains(text(),'Use')])[2]")
	WebElement USEOptionForExistingAddress;

	@FindBy(xpath = "((//div[contains(text(),'Total')])//following::div)[1]")
	WebElement OrderTotalAmount;

	
	
	
	
	@FindBy(xpath = "//button[@id='yesBtn']")
	WebElement YesBtnForSchoolInfo;
	
	@FindBy(xpath = "//select[@id='region']")
	WebElement StateDropDownForSchoolInfo;
	
	
	@FindBy(xpath = "//select[@id='school']")
	WebElement SchoolDropDown;
	
	/*
	 * @Author:Vishnu
	 */
	
	@FindBy(xpath = "//a[@href='/en-us/Job+Ready+Java-p-00099153']")
	WebElement jobreadyjava;
		
	@FindBy(xpath = "//input[@id='number']")
	WebElement CardNumber;


	
	/*
	 * @FindBy(xpath = "//button[@id='placeOrder']") WebElement placeorder;
	 */
	@FindBy(xpath = "//button[@class='button button-main large shipping-button-group']")
	WebElement EnterNewAddress;
	@FindBy(xpath = "(//div[@class='orderConfirmationLabelVal textTransCap marginTop10'])[2]")
	WebElement OrderId;
	
	
	@FindBy(xpath = "(((//div[contains(text(),'Shipping')])[2])//following::div)[1]")
	WebElement Shippingcharge;
	
	@FindBy(xpath = "(//input[@name='purchasedProduct'])[1]")
	WebElement EbookRental;

	@FindBy(xpath = "//button[@class='btn schoolNoBtn']")
	WebElement SchoolInfo;

	@FindBy(xpath = "(//div[@class='productButtonGroupName'])[2]")
	WebElement PrintProduct;

	@FindBy(xpath = "(//span[@class='typeOfProductSpan'])[4]")
	WebElement SelectingTextbookRental;
	
	@FindBy(xpath = "//span[@id='promoCodeLink']")
	WebElement PromoCodeLink;

	@FindBy(xpath = "//input[@id='js-voucher-code-text']")
	WebElement EnterPromoCode;
	
	@FindBy(xpath = "//span[@class='cartDiscountBtnText']")
	WebElement ApplyPromoButton;
	


	// Footer Page
	@FindBy(xpath = "//span[contains(text(),'Site M')]")
	WebElement SiteMap;
	


	@FindBy(xpath = "//span[contains(text(),'Rights & Permissions')]")
	WebElement RightsAndPermissions;

	@FindBy(xpath = "//span[contains(text(),'Privacy Policy')]")
	WebElement PrivacyPolicy;

	@FindBy(xpath = "//span[contains(text(),'Terms of Use')]")
	WebElement TermsofUse;

	// PDP Page
	@FindBy(xpath = "//a[@href='/sitemap']")
	WebElement SitemapPdpPage;
	@FindBy(xpath = "//a[@href='/permissions']")
	WebElement PermissionsonPdpPage;
	@FindBy(xpath = "//a[@href='/privacy']")
	WebElement PrivacyonPdpPage;
	@FindBy(xpath = "//a[@href='/terms-of-use']")
	WebElement TermsofUseonPdpPage;
	@FindBy(xpath = "(//a[@class='area-name'])[4]")
	WebElement EducationalResources;
	@FindBy(xpath = "//input[@id='searchbar']")
	WebElement SearchbarHomepage;
	
	@FindBy(xpath = "(//a[@href='/about'])[2]")
	WebElement AboutWiley;
	
	@FindBy(xpath = "//a[contains(text(),'PRODUCTS')]")
	WebElement PlpProducts;

	@FindBy(xpath = "//a[contains(text(),'CONTENT')]")
	WebElement PlpContectText;
	
	@FindBy(xpath="(//div[@class='facet-list facets-panel-list js-facet-list '])[3]//span[@class='facet-text']")
	WebElement Facettext;

	@FindBy(xpath = "//span[@class='search-highlight']")
	WebElement VetConsultText;
	@FindBy(xpath = "//span[@class='glyphicon glyphicon-chevron-right']")
	WebElement ClickingOnVetSoultIcon;
	
	@FindBy(xpath = "(//a[contains(text(),'Shop')])[2]")
	WebElement ShopLinkCLPPage;

	@FindBy(xpath = "(//a[@class='link-corner'])[1]")
	WebElement ViewAllOnCLPPage;

	@FindBy(xpath = "//form[@id='sortForm']")
	WebElement ClickonSortBy;
	@FindBy(xpath = "(//header[@class='wiley-product-list-component-header']/h1)[1]")
	WebElement FeaturedProducts;
	@FindBy(xpath = "(//header[@class='wiley-product-list-component-header']/h1)[2]")
	WebElement RecentlyReleasesProducts;
	@FindBy(xpath = "(//header[@class='wiley-product-list-component-header']/h1)[3]")
	WebElement UpcomingProducts;

	@FindBy(xpath = "//div[@class='content cke-content']/h1[contains(text(),'Page')]")
	WebElement PagenotFoundErrortext;
	
	@FindBy(xpath = "//a[@href='/']/img[@class='brand-logo']")
	WebElement HomePageLogo;
	
	@FindBy(xpath = "//a[@class='see-all-results']")
	WebElement SearchAllResults;
	
   //Anindita
	
	@FindBy(xpath = "//input[@id='searchbar']")
	WebElement HomePageSearchBar;
	@FindBy(xpath = "//input[@id='search-bar']")
	WebElement CartPageSearchBar;
	@FindBy(xpath = "//i[@class='wl-search-bar__icon']")
	WebElement SearchBarIconHomePage;
	
	@FindBy(xpath="//button[text()='Add to cart']")
	WebElement AddToCartButton;
	@FindBy(xpath="//div[@class='productButtonGroupName' and text()='Print']")
	WebElement PrintTab;

	/*
	 * @FindBy(
	 * xpath="(//div[@id='checkoutButtonDiv']/button/span[text()='Proceed to Checkout'])[2]"
	 * ) WebElement ProceedToCheckOutButton;
	 */
	@FindBy(xpath="//input[@id='email']")
	WebElement EmailIdInCreateAccount;
	@FindBy(xpath="//button/span[text()='CREATE AN ACCOUNT']")
	WebElement CreateAccountButton;
	@FindBy(xpath="//input[@data-input_description='confirmemail']")
	WebElement ConfirmEmailId;
	@FindBy(xpath="//input[@id='pwd']")
	WebElement PasswordInCreateAccount;

	@FindBy(xpath="//input[@id='nameOnCard']")
	WebElement CardHolderName;
	
	
	
	@FindBy(xpath="//input[@id='firstName']")
	WebElement AddressFirstName;
	@FindBy(xpath="//input[@id='lastName']")
	WebElement AddressLastName;
	
	
	@FindBy(xpath="//input[@id='street1']")
	WebElement BillingAddressLine1;
	@FindBy(xpath="//input[@id='city']")
	WebElement CityBilling;
	

	@FindBy(xpath="//input[@id='postalCode']")
	WebElement BillingZipCode;
	@FindBy(xpath="//input[@id='phoneNumber']")
	WebElement BillingPhoneNumber;
	
	@FindBy(xpath="(//button[@id='wel_billing_use_suggested_address_button']/span[text()='Use Selected Address'])[2]")
	WebElement UseSelectedBillingAddressButtonAddressDoctorPopUp;
	/*
	 * @FindBy(xpath="//button[@id='placeOrder']") WebElement PlaceOrderButton;
	 */
	@FindBy(xpath="//span[text()='Thank you for your Order']")
	WebElement ThankYouText;
	
	@FindBy(xpath="(//div[contains(text(),'Taxes')]//following::div)[1] ")
	WebElement TotalTax;
	@FindBy(xpath="//button[@class='btn btn-basic checkout-as-guest-btn width488 wileyCommonBtn guestCreateAccountBtn submitWelForm']")
	WebElement ContinueAsGuestButton;
	@FindBy(xpath="//input[@id='line1']")
	WebElement ShippingAddressLine1;
	
	
	@FindBy(xpath="//input[@id='phone']")
	WebElement ShippingPhoneNumber;
	
	@FindBy(xpath="(//button[@id='wel_use_suggested_address_button']/span[text()='Use Selected Address'])[2]")
	WebElement UseSelectedShippingAddressButtonAddressDoctorPopUp;
	
	@FindBy(xpath="//label[@id='termsCondsCheckbox']")
	WebElement TermsAndConditionCheckBox;
	
	@FindBy(xpath="(//div[@class='col-xs-3 noPadding textRight orderReviewDetailsValue'])[2]")
	WebElement TaxInOrderReview;
	
	@FindBy(xpath="(//span[@class='glyphicon glyphicon-pencil editIcon'])[2]")
	WebElement EditButtonInBilling;
	
	@FindBy(xpath="//div[@class='modal-content']")
	WebElement CountryRestrictionModal;
	
	@FindBy(xpath="(//span[@id='deliveryMethodMessage'])[2]/div/h5")
	WebElement PODMessage;
	
	@FindBy(xpath="//div[@class='applied-facets']/div[@class='facets-panel-item']/span[text()='RGuroo']")
	WebElement RGurooFacet;
	
	@FindBy(xpath="//div[@id='billingMultiPaymentTitle']")
	WebElement PaymentMethodText;
	
	@FindBy(xpath="//a[@id='shoppingCartIcon']")
	WebElement ShoppingCartIconUAT3;
	
	@FindBy(xpath="//h5[@id='shippingAddressTitle']/span[text()='Shipping Address']")
	WebElement ShippingStepText;
	
	@FindBy(xpath="//ul[@class='menu']/li[@class='nav-btn']/a[text()='Shop ']")
	WebElement ShopLinkInCartPageHeader;
	
	@FindBy(xpath="//ul[@class='menu']/li[@class='nav-btn']/a[text()='Research Libraries ']")
	WebElement ResearchLibrariesLinkInCartPageHeader;
	
	@FindBy(xpath="//ul[@class='menu']/li[@class='nav-btn']/a[text()='Publishing Services ']")
	WebElement PublishingServicesLinkInCartPageHeader;
	
	@FindBy(xpath="//ul[@class='menu']/li[@class='nav-btn']/a[text()='Professional Development ']")
	WebElement ProfessionalDevelopmentLinkInCartPageHeader;
	
	@FindBy(xpath="//div[@class='cart header-icon']")
	WebElement CartIcon;
	
	@FindBy(xpath="//span[@id='cart-total-badge']")
	WebElement CartItemQuantity;
	
	@FindBy(xpath="//ul[@id='breadcrumbStyle']")
	WebElement BreadCrumbCartPage;
	
	@FindBy(xpath="//div/i[text()='Shipping and Tax will be calculated during checkout']")
	WebElement TextInOrderSummaryTab;
	
	@FindBy(xpath="//span[text()='We require an address on file to create your account and place your order']")
	WebElement TextInZeroDollarFlow;
	
	@FindBy(xpath="(//span[text()='Next Available Date: '])[1]")
	WebElement NextAvailabilityDateForPreorderProductInCartPage;
	
	@FindBy(xpath="(//span[text()='Next Available Date: '])[2]")
	WebElement NextAvailabilityDateForBackorderProductInCartPage;
	
	@FindBy(xpath="(//div[@class='outOfStockErrMsgDesc'])[1]")
	WebElement PreOrderNotificationMessageInCartPage;
	
	@FindBy(xpath="(//div[@class='outOfStockErrMsgDesc'])[2]")
	WebElement BackOrderNotificationMessageInCartPage;
	
	@FindBy(xpath="(//div[text()='Next Available Date: '])[1]/following-sibling::div")
	WebElement PreOrderNextAvailabilityDateOrderReview;
	
	@FindBy(xpath="(//div[text()='Next Available Date: '])[2]/following-sibling::div")
	WebElement BackOrderNextAvailabilityDateOrderReview;
	
	@FindBy(xpath="//span[contains(text(),'Pre-order. ')]")
	WebElement PreOrderBlueTextInOrderConfirmation;
	
	@FindBy(xpath="//span[contains(text(),'Back-order. ')]")
	WebElement BackOrderBlueTextInOrderConfirmation;
	
	@FindBy(xpath="//span[text()='Pre-order. ']/ancestor::div[@class='col-md-9 col-xs-12 noPadding orderReviewDetailsLabel']")
	WebElement PreOrderDateTextOrderConfirmation;
	
	@FindBy(xpath="//span[text()='Back-order. ']/ancestor::div[@class='col-md-9 col-xs-12 noPadding orderReviewDetailsLabel']")
	WebElement BackOrderDateTextOrderConfirmation;
	
	@FindBy(xpath="//a[@class='icon-print']/span[@class='glyphicon glyphicon-print']")
	WebElement PrintRecieptIconInOrderConfirmation;
	
	@FindBy(xpath="(//a[@id='loginForgotPasswordLink'])[2]")
	WebElement ForgotPasswordLink;
	
	@FindBy(xpath="//input[@id='login.email']")
	WebElement EmailIdFieldInForgotPassword;
	
	@FindBy(xpath="//button[@id='wiley-forgotPasswordSubmitBtn']")
	WebElement SubmitButtonInForgotPassword;
	
	@FindBy(xpath="//h2[text()='Password reset instructions have been sent to your e-mail address. Please contact customer support if you require additional assistance.']")
	WebElement ResetPasswordInstructions;
	
	@FindBy(xpath = "//input[@placeholder='Enter your inbox here']")
	WebElement EnterEmailIdInYopmail;
	
	@FindBy(xpath = "//button[@title='Check Inbox @yopmail.com']")
	WebElement clickOnCheckInboxButton;
	
	@FindBy(xpath="//div[@class='guestCreateAccountBtnDiv']/button/span[text()='Continue as Guest']")
	WebElement GuestCheckoutButton;
	
	@FindBy(xpath="//span[text()='! An account already exists for this email address. Please log in.']")
	WebElement ErrorMessageAfterEnteringExistingUserInCreateAccount;
	
	@FindBy(xpath="//form[@id='loginForm']/div[@class='form-cell has-error errorMessage']")
	WebElement ErrorMessageAfterEnteringWrongPassword;
	
	@FindBy(xpath="//a/span[text()='Shop']")
	WebElement ShopLinkInHomePageHeader;
	
	@FindBy(xpath="//a/span[text()='Research Libraries']")
	WebElement ResearchLibrariesLinkInHomePageHeader;
	
	@FindBy(xpath="//a/span[text()='Publishing Services']")
	WebElement PublishingServicesLinkInHomePageHeader;
	
	@FindBy(xpath="//a/span[text()='Professional Development']")
	WebElement ProfessionalDevelopmentLinkInHomePageHeader;
	
	@FindBy(xpath="//a/i[@class='wl-util-nav__icon  wl-icon-cart']")
	WebElement CartIconHomePage;
	
	@FindBy(xpath="//input[@id='updatePwd.pwd']")
	WebElement NewPasswordFieldInResetPasswordPage;
	
	@FindBy(xpath="//input[@id='updatePwd.checkPwd']")
	WebElement ConfirmPasswordFieldInResetPasswordPage;
	
	@FindBy(xpath="//button/span[text()='Submit']")
	WebElement SubmitButtonInResetPasswordPage;
	
	@FindBy(xpath="//h3[contains(text(),'FORMAT')]")
	WebElement FormatFacet;
	
	@FindBy(xpath="//span[contains(text(),'VetConsult')]")
	WebElement VetConsultUnderFormatFacet;
	
	@FindBy(xpath="//h3[contains(text(),'SUBJECT')]")
	WebElement SubjectFacet;
	
	@FindBy(xpath="//h3[contains(text(),'COURSE')]")
	WebElement CourseFacet;
	
	@FindBy(xpath="//h3[contains(text(),'AUTHOR')]")
	WebElement AuthorFacet;
	
	@FindBy(xpath="//h3[contains(text(),'PUBLISHED DATE')]")
	WebElement PublishedDateFacet;
	
	@FindBy(xpath="//h3[contains(text(),'BRANDS')]")
	WebElement BrandsFacet;
	
	@FindBy(xpath="//h3[contains(text(),'SERIES')]")
	WebElement SeriesFacet;
	
	@FindBy(xpath="(//span[@class='search-highlight'])[1]")
	WebElement HighlightedSearchedTerm;
	
	/*
	 * @Author: Anindita
	 * @Description: Enters a data in search bar
	 * @Date: 06/09/22
	 */
	public void searchDataInSearchBar(String data) throws IOException {
		try {
			CartPageSearchBar.sendKeys(data);
			CartPageSearchBar.sendKeys(Keys.ENTER);
			Reporting.updateTestReport(data+" was seached in the search bar",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Data couldn't be entered in the search bar",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description:Clicks on the search icon in the search bar
	 * @Date: 06/09/22
	 */
	public void clickOnSearchIcon() throws IOException{
		try {
			SearchBarIconHomePage.click();
			Reporting.updateTestReport("Search icon was clicked in the search bar",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Search icon couldn't be clicked in the search bar",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the Add to cart button in the pdp page
	 * @Date: 06/09/22
	 */
	public void clickOnAddToCartButton() throws IOException{
		try {
			AddToCartButton.click();
			Reporting.updateTestReport("Add To Cart Button was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Add To Cart couldn't be clicked",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the View Cart button in the pop up
	 * @Date: 06/09/22
	 */
	public void clickOnViewCartButton() throws IOException{
		try {
			
			ViewCartButton.click();
			Reporting.updateTestReport("View Cart Button was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("View Cart Button couldn't be clicked",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Enters the email id in Create account form
	 * @Date: 06/09/22
	 */
	public String enterEmailIdInCreateAccountForm() throws IOException {
		try {
			String dateTime= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String emailId="autowiley"+dateTime+"@yopmail.com";
			EmailIdInCreateAccount.sendKeys(emailId);
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
	 * @Description: Enters the email id in Create account form Confirm email id field
	 * @Date: 06/09/22
	 */
	public void confirmEmailIdInCreateAccountForm(String emailId) throws IOException {
		try {
			ConfirmEmailId.sendKeys(emailId);
			Reporting.updateTestReport("Email Id: "+emailId+" was entered successfully in the Confirm email id section",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Email Id was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Enters the password in Create account form 
	 * @Date: 06/09/22
	 */
	public void enterPasswordInCreateAccountForm(String password) throws IOException {
		try {
			PasswordInCreateAccount.sendKeys(password);
			Reporting.updateTestReport("Password: "+password+" was entered successfully in the Create Account Form",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Password couldn't be entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			
		}
	}
	
	
	/*
	 * @Author: Anindita
	 * @Description: Clears the Address line 1 field if anything is present and then Enters the new value in the billing address form
	 */
	public void enterAddressLine1Billing(String line1) throws IOException{
		try {
			BillingAddressLine1.clear();
			BillingAddressLine1.sendKeys(line1);
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
	public void enterCityBilling(String city) throws IOException{
		try {
			CityBilling.clear();
			CityBilling.sendKeys(city);
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
	public void enterZipBilling(String zip) throws IOException{
		try {
			BillingZipCode.clear();
			BillingZipCode.sendKeys(zip);
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
	public void enterPhoneNumberBilling(String phone) throws IOException{
		try {
			BillingPhoneNumber.clear();
			BillingPhoneNumber.sendKeys(phone);
			Reporting.updateTestReport("Phone number: "+phone+" was entered",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Phone number couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the Use Selected Address Button in Address Doctor PopUp
	 * @Date: 07/09/22
	 */
	public void clickOnUseSelectedBillingAddressButtonAddressDoctor() throws IOException{
		try {
			UseSelectedBillingAddressButtonAddressDoctorPopUp.click();
			Reporting.updateTestReport("Use Selected Address Button in Address Doctor PopUp was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Use Selected Address Button in Address Doctor PopUp couldn't be clicked",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the Place Order button 
	 * @Date: 07/09/22
	 */
	public void clickOnPlaceOrderButton() throws IOException{
		try {
			Place_OrderButton.click();
			Reporting.updateTestReport("Place Order button  was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Place Order button couldn't be clicked",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Checks if "Thank you for your order" text is present or not
	 * @Date: 07/09/22
	 */
	public boolean checkIfUserIsInOrderConfirmation() throws IOException {
		try {
			if(ThankYouText.isDisplayed()) return true;
			else return false;
		}
		catch(Exception e) {
			Reporting.updateTestReport("Error thrown while checking text in order confirmation",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		    return false;
		}
		
	}
	
	
	/*
	 * @Author: Anindita
	 * @Description: Fetches the Order Total
	 * @Date: 07/09/22
	 */
	public String fetchOrderTotal() throws IOException{
		try {
			String orderTotal=OrderTotalAmount.getText();
			Reporting.updateTestReport("Order Total: "+orderTotal+" was fetched",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return orderTotal;
		}
		catch(Exception e) {
			Reporting.updateTestReport("Order Total couldn't fetched",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
			return "";
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the Continue As Guest  button 
	 * @Date: 07/09/22
	 */
	public void clickOnContinueAsGuestButton() throws IOException{
		try {
			ContinueAsGuestButton.click();
			Reporting.updateTestReport("Continue As Guest button  was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Continue As Guest button couldn't be clicked",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clears the Address line 1 field if anything is present and then Enters the new value in the shipping address form
	 */
	public void enterAddressLine1Shipping(String line1) throws IOException{
		try {
		    ShippingAddressLine1.clear();
		    ShippingAddressLine1.sendKeys(line1);
			Reporting.updateTestReport("Address line 1: "+line1+" was entered",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Address line 1 couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	
	/*
	 * @Author: Anindita
	 * @Description: Clears the Phone number if anything is present and then Enters the new value in the shipping address form
	 */
	public void enterPhoneNumberShipping(String phone) throws IOException{
		try {
			ShippingPhoneNumber.clear();
			ShippingPhoneNumber.sendKeys(phone);
			Reporting.updateTestReport("Phone number: "+phone+" was entered",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Phone number couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the Use Selected Address Button in Address Doctor PopUp
	 * @Date: 07/09/22
	 */
	public void clickOnUseSelectedShippingAddressButtonAddressDoctor() throws IOException{
		try {
			UseSelectedShippingAddressButtonAddressDoctorPopUp.click();
			Reporting.updateTestReport("Use Selected Address Button in Address Doctor PopUp was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Use Selected Address Button in Address Doctor PopUp couldn't be clicked",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Logs out the user from wiley.com
	 */
	public void WileyLogOut() throws IOException {
		try {
			WebDriver driver=DriverModule.getWebDriver();
			driver.get(excelOperation.getTestData("WILEY_LogOut_URL", "Generic_Dataset", "Data"));
			if(driver.getTitle().contentEquals("Wiley | Global Leader in Publishing, Education and Research")) Reporting.updateTestReport("Logged out successfully",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Couldn't logout",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	

	/*
	 * @Author: Arun
	 * 
	 * @description:This method using for clicking on ProceedToCheckout Button
	 */
	public void clickOnProceedToCheckoutButton() throws IOException {

		try {
			ProceedToCheckoutButton.click();
			Reporting.updateTestReport("ProceedToCheckoutButton was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"ProceedToCheckoutButton was not Clicked with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	


	/*
	 * @Author: Arun
	 * @description:
	 */
	public void clickOnCreateAccountButton() throws IOException {

		try {
			CreateAccountButton.click();
			Reporting.updateTestReport("CreateAccountButton was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"CreateAccountButton was not Clicked with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	
	/*
	 * @Author: Arun
	 * 
	 * @description:This method using for clicking on ADD To CART Button
	 */
	public void clickOnSaveAndContinueButton() throws IOException {

		try {
			SaveAndContinueButton.click();
			Reporting.updateTestReport("SaveAndContinueButton was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"SaveAndContinueButton was not Clicked with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}


	/*
	 * Author : Arun 
	 * Description : Select Country From DropDown in shipping an billing
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
	 * Author : Arun 
	 * Description : Postal code updating in shipping page.
	 */
	public void enterShippingZIPCode(String shippingZIPCode) throws IOException {
		try {
			ShippingZIPCode.sendKeys(shippingZIPCode);
			Reporting.updateTestReport("ShippingAddress: " + shippingZIPCode + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"ShippingAddress was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * Author : Arun 
	 * Description : Entering the City in Shipping page.
	 */
	public void enterShippingCity(String shippingCity) throws IOException {
		try {
			ShippingCity.clear();
			ShippingCity.sendKeys(shippingCity);
			Reporting.updateTestReport("ShippingCity: " + shippingCity + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("ShippingCity was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * Author : Arun 
	 * Description :Selecting country from Dropdpwn in Shipping Page
	 */
	public void enterState(String state) throws IOException {
		try {
			
			
			SelectStateDropDown.clear();
			SelectStateDropDown.sendKeys(state);
			SelectStateDropDown.sendKeys(Keys.ENTER);
			Reporting.updateTestReport("State: "+state+" has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select State " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	

	/*
	 * Author : Arun 
	 * Description : THis Method using for Selecting Shipping Method 
	 */
	public void selectShippingMethod() throws IOException {
		try {

			ShippingMethod.click();
			Reporting.updateTestReport("ShippingMethod has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select ShippingMethod " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * Author : Arun 
	 * Description : Entering the CardHolder Name 
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

	

	/*
	 * Author : Arun 
	 * Description :Description :Selecting Expiration Month From DropDown
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
	 * Author : Arun 
	 * Description :Selecting Expiration Year From DropDown
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
	 * Author : 
	 * Arun Description : Entering the CVV Number
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
	 * @Author: Arun
	 * @description: This using for Clicking on SRP_WileyProduct
	 */
	public void clickOnSRP_WileyProduct() throws IOException {

		try {
			SRP_WileyProduct.click();
			Reporting.updateTestReport("SRP_WileyProduct was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"SRP_WileyProduct was not Clicked properly with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	

	/*
	 * @Author: Arun
	 * @description: This Method for using the ExistingWiley UserMailID for Existing User
	 */
	public void enterExistingWileyUserMailID(String existingWileyUserMailID) throws IOException {
		try {
			ExistingWileyUserMailID.sendKeys(existingWileyUserMailID);
			Reporting.updateTestReport(
					"ExistingWileyUserMailID: " + existingWileyUserMailID + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("ExistingWileyUserMailID was not entered properly with the error message "
					+ e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Arun
	 * @description: This is for using the Existing Wiley UserPassword for existing User.
	 */
	public void enterExistingWileyUserPassword(String existingWileyUserPassword) throws IOException {
		try {
			ExistingWileyUserPassword.sendKeys(existingWileyUserPassword);
			Reporting.updateTestReport("WileyUserPassword: " + existingWileyUserPassword + " was entered successfully ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"WileyUserPassword was not entered with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Arun
	 * @description: This is Clicking on LogINContinueButton
	 */
	public void clickOnLogInAndContinueButton() throws IOException {

		try {
			LogInAndContinueButton.click();
			Reporting.updateTestReport("LogInAndContinueButton was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"LogInAndContinueButton was not Clicked properly with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * Author : Arun Description :Selecting
	 * description : THis Method using for clicking on "USE" button for Existing User
	 */
	public void selectUSEOptionForExistingAddress() throws IOException {
		try {

			USEOptionForExistingAddress.click();
			Reporting.updateTestReport("USEOptionTForExistingAddress has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"User failed to select USE Option for ExistingAddress  " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * Author : Arun Description :Selecting
	 * description : Product Quantity increasing in cart page
	 */
	public void selectQuantityDropDown(String quantity) throws IOException {
		try {

			Select Quantity=new Select(QuantityDropDown);
			Quantity.selectByValue(quantity);
			Reporting.updateTestReport("Quantity has been selected successfully by user",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select Quantity " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}


	
	
	/*
	 * Author : Arun Description :
	 * description : THis Method using for TaxAmount Fetching From Order Confirmation page
	 */
	public String fetchTaxAmount() throws IOException {
		try {
			String totalTax=TotalTax.getText();
			Reporting.updateTestReport("Total Tax: "+totalTax+" was fetched",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return totalTax;
		}
		catch(Exception e) {
			Reporting.updateTestReport("Total Tax couldn't fetched",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
			return "";
		}
	}
	
	
	
	/*
	 * Author : Arun 
	 * Description : Select School_Info During the checkout Flow 
	 */
	public void selectSchoolInfo(String school_state, String school_name) throws IOException {
		try {

			YesBtnForSchoolInfo.click();
			StateDropDownForSchoolInfo.click();
			Select selectSchoolState = new Select(StateDropDownForSchoolInfo);
			selectSchoolState.selectByVisibleText(school_state);
			Thread.sleep(2000);
			Select selectSchoolName= new Select(SchoolDropDown);
			selectSchoolName.selectByVisibleText(school_name);
			
			Reporting.updateTestReport("State has been selected successfully by user",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("User failed to select State " + e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	

	


	/*
	 * @Author:Vishnu 
	 * @Description:This method is used enter the CardNumber on Billing AddressForm
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
	 * @Description:This method is used to enter the FirstName
	 */
	public void enterFirstName(String Fname) throws IOException {
		try {
			AddressFirstName.clear();
			AddressFirstName.sendKeys(Fname);
			Reporting.updateTestReport("FirstName was entered successfully on paymentpage",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the FirstName with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu 
	 * @Description:This method is used to enter the LastName
	 */
	public void enterLastName(String Lname) throws IOException {
		try {
			AddressLastName.clear();
			AddressLastName.sendKeys(Lname);
			Reporting.updateTestReport("LastName  was entered successfully on paymentpage",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the Lastname with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}


	/*
     * @Author:Vishnu
     * @Description:This method is used to enter the NewAddress
     */
	public void clickOnEnterNewAddress() throws IOException {
		try {
			EnterNewAddress.click();
			Reporting.updateTestReport("EnterNewAddress  button was clickedsuccessfully on paymentpage",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to Click on EnterNewAddress button with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
     * @Author:Vishnu
     * @Description:This method is used fetching the OrderId
     */
	public String fetchOrderId() throws IOException {
		try {
			String id = OrderId.getText();
			System.out.println(id);
			Reporting.updateTestReport("Order id: "+id+" was successfully fetched", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
			return id;
		} catch (Exception e) {
			Reporting.updateTestReport("Order id was not fetched with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
     * @Author:Vishnu
     * @Description:This method is used performing the operation for user logout
     */
	public void logOut() throws IOException {
		try {
			WebDriver driver = DriverModule.getWebDriver();
			driver.get(excelOperation.getTestData("WILEY_LogOut_URL", "Generic_Dataset", "Data"));
			if (driver.getTitle().contentEquals("Wiley | Global Leader in Publishing, Education and Research"))
				Reporting.updateTestReport("Logged out successfully", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Couldn't logout", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	
	

	/*
     * @Author:Vishnu
     * @Description: This method is to return the ShippingCharge
     */
	public String fetchShippingCharge() throws IOException {
		try {
			String shpcharge = Shippingcharge.getText();
			System.out.println();
			Reporting.updateTestReport("Shipping Charge was selected successfully on OrderConfirmation Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return shpcharge;
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to select the Shipping Charge with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		return "";
	}

	 
	/*
	 * @Author: Anindita
	 * @Description: Returns the Billing address doctor pop up button 
	 * @Date: 14/09/22 
	 */
	public WebElement returnUseSelectedBillingAddressButtonAddressDoctorPopUp() {
		return UseSelectedBillingAddressButtonAddressDoctorPopUp;
	}
	/*
	 * @Author: Anindita
	 * @Description: Returns the Shipping address doctor pop up button 
	 * @Date: 14/09/22 
	 */
	public WebElement returnUseSelectedShippingAddressButtonAddressDoctorPopUp() {
		return UseSelectedShippingAddressButtonAddressDoctorPopUp;
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the print tab
	 * @Date: 15/09/22
	 */
	public void clickOnPrintTab() throws IOException {
		try {
			PrintTab.click();
			Reporting.updateTestReport("Print tab was successfully clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Print tab couldn't be clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public void ebookRentalProduct() throws IOException {
		try {
			EbookRental.click();
			Reporting.updateTestReport("eBook Rental product was opened  successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to open the eBook Rental Product",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	
	public void clickOnPromotionCodelink() throws IOException {
		try {
			PromoCodeLink.click();
			Reporting.updateTestReport("Enter Promocode link was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to clcik on Enter Promo code link",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	public void enterPromoCode(String promo) throws IOException {
		try {
			EnterPromoCode.sendKeys(promo);
			Reporting.updateTestReport("Promocode " + promo + " was enter successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Enter the PromoCode", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}

	public void ApplyPromo() throws IOException {
		try {
			ApplyPromoButton.click();
			Reporting.updateTestReport("Promocode was applied successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to Enter the PromoCode", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}
	
	public void selectingSchoolInfo() throws IOException {
		try {
			SchoolInfo.click();
			Reporting.updateTestReport("School Information was selected successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to select t he school Information",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	
	public void SelectingTextBoolRental() throws IOException{
		try {
			SelectingTextbookRental.click();
			Reporting.updateTestReport("Textbook renatl product was selected successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		}catch(Exception e) {
			Reporting.updateTestReport("Failed to select the textbook Rental product", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	
}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the rentalk terms and conditions checkbox
	 * @Date: 7/11/22
	 */
	public void clickOnRentalTermsCheckbox() throws IOException{
		try {
			TermsAndConditionCheckBox.click();
			Reporting.updateTestReport("Rental terms and conditions checkbos has been clicked successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Rental terms checkbox couldn't be clicked",  CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Fetches the tax from Order Review Step
	 * @Date: 7/11/22
	 */
	public String fetchTaxInOrderReview() throws IOException{
		try {
			String tax=TaxInOrderReview.getText();
			Reporting.updateTestReport("Tax was fetched in order review step successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
			return tax;
		}
		catch(Exception e) {
			Reporting.updateTestReport("Tax couldn't be fetched in order review step ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			return "";
			
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the edit button in billing section
	 * @Date: 7/11/22
	 */
	public void clickOnEditButtonInBilling() throws IOException{
		try {
			EditButtonInBilling.click();
			Reporting.updateTestReport("Edit button in billing section was clicked successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Edit button in billing section couldn't be clicked", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}
	
	/*
	 * 
	 * @Author:Anindita
	 * @Description: Checks if the country restriction modal was displayed
	 * @Date: 7/11/22
	 */
	public boolean checkIfCountryRestrictrionModalIsDisplayed() throws IOException{
		try {
			if(CountryRestrictionModal.isDisplayed())
			{
				Reporting.updateTestReport("Country Restriction modal was displayed", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("Country Restriction modal was not displayed", CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
				return false;}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Country Restriction modal was not displayed", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			return false;
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks and fetches the message in shipping methods for POD Products
	 * @Date: 8/11/22
	 */
	public void validateShippingMethodMessageForPOD() throws IOException{
		try {
			if(PODMessage.isDisplayed()) {
				String text=PODMessage.getText();
				Reporting.updateTestReport("Shipping method related message was displayed: "+text, CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
				
			}
			else Reporting.updateTestReport("Shipping method related message was not displayed: ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Shipping method related message was not displayed", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks if RGuroo has been applied in facet
	 * @Date: 8/11/22
	 */
	public void checkIfRGurooAppliedInFacet() throws IOException{
		try {
			if(RGurooFacet.isDisplayed()) Reporting.updateTestReport("RGuroo was successfully applied under facet after searching", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
			else Reporting.updateTestReport("RGuroo was not applied under facet after searching", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("RGuroo was not applied under facet after searching", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks whether user is on payment page
	 * @Date: 9/11/22
	 */
	public void checkIfUserIsInPaymentMethod() throws IOException{
		try {
			if(PaymentMethodText.isDisplayed())
				Reporting.updateTestReport("User is in payment page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("User is not in payment page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("User is not in payment page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the cart icon so that user gets redirected to cart page
	 * @Date: 9/11/22
	 */
	public void clickOnShoppingCartIcon() throws IOException{
		try {
			ShoppingCartIconUAT3.click();
			Reporting.updateTestReport("Clicks on the shopping cart icon", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Shopping cart icon was not clicked", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Checks if user is on shipping page
	 * @Date: 9/11/22
	 */
	public void checkIfUserIsInShippingStep() throws IOException{
		try {
			if(ShippingStepText.isDisplayed()) 
				Reporting.updateTestReport("User is in shipping step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("User is not in shipping step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e){
			Reporting.updateTestReport("User is not in shipping step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author:Anindita
	 * @Description: Checks if Shop link is present in cart page
	 * @Date: 9/11/22
	 */
	public void checkShopLinkInCartPageHeader() throws IOException{
		try {
			if(ShopLinkInCartPageHeader.isDisplayed()) 
				Reporting.updateTestReport("Shop Link In Cart Page Header is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Shop Link In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Shop Link In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author:Anindita
	 * @Description: Checks if Research Libraries link is present in cart page
	 * @Date: 9/11/22
	 */
	public void checkResearchLibrariesLinkInCartPageHeader() throws IOException{
		try {
			if(ResearchLibrariesLinkInCartPageHeader.isDisplayed()) 
				Reporting.updateTestReport("Research Libraries Link In Cart Page Header is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Research Libraries Link In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Research Libraries Link In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author:Anindita
	 * @Description: Checks if Publishing Services link is present in cart page
	 * @Date: 9/11/22
	 */
	public void checkPublishingServicesLinkInCartPageHeader() throws IOException{
		try {
			if(PublishingServicesLinkInCartPageHeader.isDisplayed()) 
				Reporting.updateTestReport("Publishing Services Link In Cart Page Header is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Publishing Services Link In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Publishing Services Link In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author:Anindita
	 * @Description: Checks if Publishing Services link is present in cart page
	 * @Date: 9/11/22
	 */
	public void checkProfessionalDevelopmentLinkInCartPageHeader() throws IOException{
		try {
			if(ProfessionalDevelopmentLinkInCartPageHeader.isDisplayed()) 
				Reporting.updateTestReport("Professional Development Link In Cart Page Header is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Professional Development Link In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Professional Development Link In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author:Anindita
	 * @Description: Checks if CartIcon link is present in cart page
	 * @Date: 9/11/22
	 */
	public void checkCartIcon() throws IOException{
		try {
			if(CartIcon.isDisplayed()) 
				Reporting.updateTestReport("CartIcon Link In Cart Page Header is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("CartIcon Link In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("CartIcon Link In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author:Anindita
	 * @Description: Checks if Cart Item Quantity is present in cart page
	 * @Date: 9/11/22
	 */
	public String checkCartItemQuantity() throws IOException{
		try {
			if(CartItemQuantity.isDisplayed()) 
				{Reporting.updateTestReport("Cart Item Quantity: "+ CartItemQuantity.getText()+" In Cart Page Header is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return CartItemQuantity.getText();}
			else {
				Reporting.updateTestReport("Cart Item Quantity In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Cart Item Quantity In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
		
	}
	
	/*
	 * @Author:Anindita
	 * @Description: Checks if BreadCrumb is present in cart page
	 * @Date: 9/11/22
	 */
	public boolean checkBreadCrumbCartPage() throws IOException{
		try {
			if(BreadCrumbCartPage.isDisplayed()) {
				Reporting.updateTestReport("BreadCrumb In Cart Page Header is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;}
			else 
				{Reporting.updateTestReport("BreadCrumb In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;}
				
		}
		catch(Exception e) {
			Reporting.updateTestReport("BreadCrumb In Cart Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}
	
	/*
	 * @Author:Anindita
	 * @Description: Checks if Text In Order Summary Tab In Cart Page
	 * @Date: 9/11/22
	 */
	public void checkTextInOrderSummaryTab() throws IOException{
		try {
			if(TextInOrderSummaryTab.isDisplayed()) 
				Reporting.updateTestReport("Text In Order Summary Tab In Cart Page is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Text In Order Summary Tab In Cart Page is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Text In Order Summary Tab In Cart Page is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Concatenates the parts of URL (devmonkey part, storerfront url with env and the rest of the part specific to some products)
	 * @Date: 10/11/22
	 */
	public String wileyURLConcatenation(String testCaseNumber, String sheetName, String field) throws IOException{
		try {
			String userNamePassword=excelOperation.getTestData("WILEY_Username_Password", "Generic_Dataset", "Data");
			String envURL=excelOperation.getTestData("WILEY_Env_URL", "Generic_Dataset", "Data");
			String pdp=excelOperation.getTestData(testCaseNumber, sheetName,field);
			//Reporting.updateTestReport("Concatenated url was returned as: "+userNamePassword+envURL+pdp, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return "https://"+userNamePassword+"@"+envURL+"/"+pdp;
			
		}
		catch(Exception e) {
			Reporting.updateTestReport("Concatenated url was returned", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks if the address information related text is present or not for zero dollar flow
	 * @Date: 14/11/22
	 */
	public void checkTextInZeroDollarFlow() throws IOException{
		try {
			if(TextInZeroDollarFlow.isDisplayed()) {
				Reporting.updateTestReport("Text related to address information step was present for zero dollar flow", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Text related to address information step was not present for zero dollar flow", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks the next availability date for first added pre-order product in cart page
	 * @Date: 14//11/22
	 */
	public void checkNextAvailabilityDatePreorderInCartPage() throws IOException{
		try {
			if(NextAvailabilityDateForPreorderProductInCartPage.isDisplayed()) 
				Reporting.updateTestReport("Nextavailability date was present for pre-order product", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				//Reporting.updateTestReport("Next Availibility date is present for pre-order product as: "+driver.findElement(By.xpath("(//span[@class='cart-item-info-val'])[2]")).getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("NextAvailibilityDate was not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	/*
	 * @Author: Anindita
	 * @Description: Checks the next availability date for second added back-order product in cart page
	 * @Date: 14//11/22
	 */
	public void checkNextAvailabilityDateBackorderInCartPage() throws IOException{
		try {
			if(NextAvailabilityDateForBackorderProductInCartPage.isDisplayed()) 
				Reporting.updateTestReport("Nextavailability date was present for backorder product", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				//Reporting.updateTestReport("Next Availibility date is present for back-order product as: "+driver.findElement(By.xpath("(//span[@class='cart-item-info-val'])[4]")).getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("NextAvailibilityDate was not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks the notification message for first added pre-order product in cart page
	 * @Date: 14//11/22
	 */
	public void checkNotificationMessagePreorderInCartPage() throws IOException{
		try {
			if(PreOrderNotificationMessageInCartPage.isDisplayed()) 
				Reporting.updateTestReport("notification message is present for pre-order product as: "+PreOrderNotificationMessageInCartPage.getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("notification message was not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks the notification message for first added back-order product in cart page
	 * @Date: 14//11/22
	 */
	public void checkNotificationMessageBackorderInCartPage() throws IOException{
		try {
			if(BackOrderNotificationMessageInCartPage.isDisplayed()) 
				Reporting.updateTestReport("notification message is present for back-order product as: "+BackOrderNotificationMessageInCartPage.getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("notification message was not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks the next availability date for second added pre-order product in Order review page
	 * @Date: 14//11/22
	 */
	public void checkNextAvailabilityDatePreorderInOrderReviewPage() throws IOException{
		try {
			if(PreOrderNextAvailabilityDateOrderReview.isDisplayed()) 
				Reporting.updateTestReport("Nextavailability date was present for preorder product"+PreOrderNextAvailabilityDateOrderReview.getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				//Reporting.updateTestReport("Next Availibility date is present for back-order product as: "+driver.findElement(By.xpath("(//span[@class='cart-item-info-val'])[4]")).getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("NextAvailibilityDate was not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks the next availability date for second added back-order product in Order review page
	 * @Date: 14//11/22
	 */
	public void checkNextAvailabilityDateBackorderInOrderReviewPage() throws IOException{
		try {
			if(BackOrderNextAvailabilityDateOrderReview.isDisplayed()) 
				Reporting.updateTestReport("Nextavailability date was present for backorder product"+BackOrderNextAvailabilityDateOrderReview.getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				//Reporting.updateTestReport("Next Availibility date is present for back-order product as: "+driver.findElement(By.xpath("(//span[@class='cart-item-info-val'])[4]")).getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("NextAvailibilityDate was not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks the blue pre-order text and the date for second added back-order product in Order review page
	 * @Date: 14//11/22
	 */
	public void checkNextAvailabilityDatePreorderInOrderConfirmationPage() throws IOException{
		try {
			if(PreOrderBlueTextInOrderConfirmation.isDisplayed()) {
				Reporting.updateTestReport("Blue preorder text was present for backorder product", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			    Reporting.updateTestReport("The complete line with next availability date is: "+PreOrderDateTextOrderConfirmation.getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
		
			}
		catch(Exception e) {
			Reporting.updateTestReport("The complete line with next availability date was not present in order confirmation page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks the blue back-order text and the date for second added back-order product in Order review page
	 * @Date: 14//11/22
	 */
	public void checkNextAvailabilityDateBackorderInOrderConfirmationPage() throws IOException{
		try {
			if(BackOrderBlueTextInOrderConfirmation.isDisplayed()) {
				Reporting.updateTestReport("Blue backorder text was present for backorder product", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			    Reporting.updateTestReport("The complete line with next availability date is: "+BackOrderDateTextOrderConfirmation.getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
		
			}
		catch(Exception e) {
			Reporting.updateTestReport("The complete line with next availability date was not present in order confirmation page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		
	}
	
	/*
	 * Author:Anindita
	 * @Description: Checks on the print receipt link in order confirmation page
	 * @Date: 14/11/22
	 */
	public void checkPrintReciept() throws IOException{
		try {
			if(PrintRecieptIconInOrderConfirmation.isDisplayed()) 
			{
				
				Reporting.updateTestReport("The print reciept icon was present in the order confirmation page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else Reporting.updateTestReport("The print reciept icon was not present in the order confirmation page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("The print reciept icon was not present in the order confirmation page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Clicks on forgot password link in the login checkout page
	 * @Date: 15/11/22
	 */
	public void clickOnForgotPasswordLink() throws IOException{
		try {
			ForgotPasswordLink.click();
			Reporting.updateTestReport("Clicks on the forgot password link",CaptureScreenshot.getScreenshot(SS_path) , StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("forgot password link couldn't be clicked",CaptureScreenshot.getScreenshot(SS_path) , StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Enters one existing user id in for receiving the reset password mail
	 * @Date: 15/11/22
	 */
	public void entersEmailIdForRecievingResetPasswordMail(String emailId) throws IOException{
		try {
			EmailIdFieldInForgotPassword.sendKeys(emailId);
			Reporting.updateTestReport("Eners the mail id for recieving reset password mail", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Mail id couldn't be entered for recieving reset password mail", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	

	/*
	 * @Author: Anindita
	 * @Description: Clicks on the submit button for receiving the reset password mail
	 * @Date: 15/11/22
	 */
	public void clickOnSubmitButtonForRecievingResetPasswordMail() throws IOException{
		try {
			SubmitButtonInForgotPassword.click();
			Reporting.updateTestReport("Clicks on the submit button for recieving reset password mail", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Submit button couldn't be clicked for recieving reset password mail", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks if the password reset instructions get displayed or not
	 * @Date: 15/11/22
	 */
	public boolean checkIfResetPasswordInstructionsIsPresent() throws IOException{
		try {
			if(ResetPasswordInstructions.isDisplayed()) { Reporting.updateTestReport("Reset password instructions was displayed", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return true;}
			else {
				Reporting.updateTestReport("Reset password instructions was displayed", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Reset password instructions was displayed", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Enters the email id in Yopmail
	 * @Date: 15/11/22
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
	 * @Description: Clicks on check inbox in yopmail after entering user id
	 * @Date: 15/11/22
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
	 * @Author:Vishnu
	 * 
	 * @Description: Checks if Publishing Services link is present in cart page
	 * 
	 * @Date: 14/11/22
	 */

	public void checkEducationResourcesoncartPage() throws IOException {
		try {
			if (EducationalResources.isDisplayed())
				Reporting.updateTestReport("Education Resources In Cart Page Header is present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Education Resources Link In Cart Page Header is not present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		} catch (Exception e) {
			Reporting.updateTestReport("Research Libraries Link In Cart Page Header is not present",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Checks sitemaps in footer
	 * 
	 * @Date: 14/11/22
	 */

	public void checkSiteMaponfooter() throws IOException {
		try {
			
			if (SiteMap.isDisplayed()) 
				
				Reporting.updateTestReport("sitemaps in footer is present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			
			else 
				Reporting.updateTestReport("sitemaps in footer is not present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			
		} catch (Exception e) {
			Reporting.updateTestReport("sitemaps in footer is not present",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Checks sitemaps in cart page
	 * 
	 * @Date: 15/11/22
	 */
	public void clickonsitemap() throws IOException {
		try {
			SitemapPdpPage.click();
			Reporting.updateTestReport("sitemaps link is clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on sitemaps link",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}


	

	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Checks sitemapsonpdp page
	 * 
	 * @Date: 15/11/22
	 */

	public void checkSiteMapononpdppage() throws IOException {
		try {
			if (SitemapPdpPage.isDisplayed())
				Reporting.updateTestReport("sitemaps in pdp Page footer is present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("sitemaps in pdp Page footer is not present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		} catch (Exception e) {
			Reporting.updateTestReport("sitemaps in pdp Page footer is not present",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Checks RightsandPermission on footer
	 * 
	 * @Date: 15/11/22
	 */

	public void checkRighrtAndPermissonsOnFooter() throws IOException {
		try {
			if (RightsAndPermissions.isDisplayed())
				Reporting.updateTestReport("Rights and permissions In footer is present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Rights and permissions In footer is not present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		} catch (Exception e) {
			Reporting.updateTestReport("Rights and permissions In footer is not present",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Checks RightsandPermission on pdp page
	 * 
	 * @Date: 15/11/22
	 */
	
	public void checkRighrtAndPermissonsononpdppage() throws IOException {
		try {
			if (PermissionsonPdpPage.isDisplayed())
				Reporting.updateTestReport("Rights and permissions In pdp Page footer is present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Rights and permissions In pdp Page footer is not present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		} catch (Exception e) {
			Reporting.updateTestReport("Rights and permissions In pdp Page footer is not present",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Checks privacy policy on footer
	 * 
	 * @Date: 15/11/22
	 */
	public void checkPrivacypolicyOnFooter() throws IOException {
		try {
			if (PrivacyPolicy.isDisplayed())
				Reporting.updateTestReport("Privacy Policy In  footer is present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Privacy Policy In  footer is not present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		} catch (Exception e) {
			Reporting.updateTestReport("Privacy Policy In footer is not present",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Checks privacy policy on pdp page
	 * 
	 * @Date: 15/11/22
	 */
	public void checkPrivacypolicyonpdppage() throws IOException {
		try {
			if (PrivacyonPdpPage.isDisplayed())
				Reporting.updateTestReport("Privacy Policy In pdp page footer is present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Privacy Policy In pdp page footer is not present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		} catch (Exception e) {
			Reporting.updateTestReport("Privacy Policy In pdp page footer is not present",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Checks terms of use on footer
	 * 
	 * @Date: 15/11/22
	 */
	public void checkTermsofuseOnFooter() throws IOException {
		try {
			if (TermsofUse.isDisplayed())
				Reporting.updateTestReport("Terms of user In Cart Page footer is present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Terms of user In Cart Page footer is present not present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		} catch (Exception e) {
			Reporting.updateTestReport("Terms of user In Cart Page footer is present not present",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: Checks terms of use in pdp page
	 * 
	 * @Date: 15/11/22
	 */
	public void checkTermsofuseonpdptpage() throws IOException {
		try {
			if (TermsofUseonPdpPage.isDisplayed())
				Reporting.updateTestReport("Terms of user In pdp Page footer is present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Terms of user In pdp Page footer is present not present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		} catch (Exception e) {
			Reporting.updateTestReport("Terms of user In pdp Page footer is present not present",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Enter the text in textbox field
	 * 
	 */
	public void Entertextonsearcbar(String text) throws IOException {
		try {
			CartPageSearchBar.sendKeys(text);
			CartPageSearchBar.sendKeys(Keys.ENTER);
			Reporting.updateTestReport("Text: " + text + " was entered on Searh Bar",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the random text: " + text + " was entered",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Logs out the user from wiley.com inside the exception block
	 */
	public void wileyLogOutException() throws IOException {
		try {
			WebDriver driver=DriverModule.getWebDriver();
			driver.get(excelOperation.getTestData("WILEY_LogOut_URL", "Generic_Dataset", "Data"));
			
		}
		catch(Exception e) {
		    System.out.println("Log out was unsuccessful in exception"+e.getMessage());
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks if the Guest checkout button is present or not
	 * @Date: 17/11/22
	 */
	public boolean checkIfGuestCheckoutButtonIsPresent() throws IOException{
		try {
			if(GuestCheckoutButton.isDisplayed()) {
				
				return true;
			}
			else {
				
				return false;
			}
		}
		catch(Exception e) {
			return false;
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the cart icon in header
	 * @Date: 17/11/22
	 */
	public void clickOnCartIcon() throws IOException{
		try {
			CartIcon.click();
			Reporting.updateTestReport("Cart Icon was clicked successfully", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Cart Icon was not clicked ", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Enters the email id in Create account form (It is not auto generating mail id)
	 * @Date: 17/09/22
	 */
	public void enterEmailIdInCreateAccountFormNotAutoGenerated(String emailId) throws IOException {
		try {
			
			EmailIdInCreateAccount.sendKeys(emailId);
			Reporting.updateTestReport("Email Id: "+emailId+" was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			
		}
		catch(Exception e){
			Reporting.updateTestReport("Email Id was not entered with the error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			
		}
	}
	
	/*
	 * Author: Anindita
	 * @Description: Validates the error message after entering an existing user in create account form
	 * @Date: 17/11/22
	 */
	public void checkErrorMessageAfterEnteringExistingUserInCreateAccount() throws IOException{
		try {
			if (ErrorMessageAfterEnteringExistingUserInCreateAccount.isDisplayed())
				Reporting.updateTestReport("Error message was displayed after entering existing user in  create account form",
                                   CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Error message was not displayed after entering existing user in  create account form",
                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Error message was not displayed after entering existing user in  create account form",
                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * Author: Anindita
	 * @Description: Validates the error message after entering an wrong password for one user in login form
	 * @Date: 17/11/22
	 */
	public void checkErrorMessageAfterEnteringWrongPassword() throws IOException{
		try {
			if (ErrorMessageAfterEnteringWrongPassword.isDisplayed())
				Reporting.updateTestReport("Error message was displayed after entering wrong password in  login form",
                                   CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Error message was not displayed after entering wrong password in  login form",
                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Error message was not displayed after entering wrong password in  login form",
                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author:Anindita
	 * @Description: Checks if Shop link is present in Home page
	 * @Date: 17/11/22
	 */
	public void checkShopLinkInHomePageHeader() throws IOException{
		try {
			if(ShopLinkInHomePageHeader.isDisplayed()) 
				Reporting.updateTestReport("Shop Link In Home Page Header is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Shop Link In Home Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Shop Link In Home Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author:Anindita
	 * @Description: Checks if Research Libraries link is present in home page
	 * @Date: 17/11/22
	 */
	public void checkResearchLibrariesLinkInHomePageHeader() throws IOException{
		try {
			if(ResearchLibrariesLinkInHomePageHeader.isDisplayed()) 
				Reporting.updateTestReport("Research Libraries Link In Home Page Header is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Research Libraries Link In Home Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Research Libraries Link In Home Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author:Anindita
	 * @Description: Checks if Publishing Services link is present in home page
	 * @Date: 17/11/22
	 */
	public void checkPublishingServicesLinkInHomePageHeader() throws IOException{
		try {
			if(PublishingServicesLinkInHomePageHeader.isDisplayed()) 
				Reporting.updateTestReport("Publishing Services Link In Home Page Header is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Publishing Services Link In Home Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Publishing Services Link In Home Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author:Anindita
	 * @Description: Checks if Publishing Services link is present in Home page
	 * @Date: 17/11/22
	 */
	public void checkProfessionalDevelopmentLinkInHomePageHeader() throws IOException{
		try {
			if(ProfessionalDevelopmentLinkInHomePageHeader.isDisplayed()) 
				Reporting.updateTestReport("Professional Development Link In Home Page Header is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Professional Development Link In Home Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Professional Development Link In Home Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author:Anindita
	 * @Description: Checks if CartIcon link is present in Home page
	 * @Date: 17/11/22
	 */
	public void checkCartIconHomePage() throws IOException{
		try {
			if(CartIconHomePage.isDisplayed()) 
				Reporting.updateTestReport("CartIcon Link In Home Page Header is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("CartIcon Link In Home Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("CartIcon Link In Home Page Header is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * Author: Anindita
	 * @Description: Enters the new password in reset password page
	 * @Date: 17/11/22
	 */
	public void enterNewPasswordFieldInResetPasswordPage(String password) throws IOException {
		try {
			NewPasswordFieldInResetPasswordPage.sendKeys(password);
			Reporting.updateTestReport("New password: "+password+" was entered in reset password page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("New password couldn't be entered", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * Author: Anindita
	 * @Description: Confirms the  password in reset password page
	 * @Date: 17/11/22
	 */
	public void enterConfirmPasswordFieldInResetPasswordPage(String password) throws IOException {
		try {
			ConfirmPasswordFieldInResetPasswordPage.sendKeys(password);
			Reporting.updateTestReport("Password was confirmed "+password+" was entered in reset password page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("password couldn't be confirmed", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * Author: Anindita
	 * @Description: Enters the new password in reset password page
	 * @Date: 17/11/22
	 */
	public void clickOnSubmitButtonInResetPasswordPage() throws IOException {
		try {
			SubmitButtonInResetPasswordPage.click();
			Reporting.updateTestReport("Submit button was clicked in reset password page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Submit button couldn't be clicked", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Enter the text in textbox field
	 * 
	 */

	public void AboutWileyPage() throws IOException {
		try {
			AboutWiley.click();
			Reporting.updateTestReport("About Wiley was clicked Successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click About Wiley page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: This method is verifying the text in PLP page
	 */

	public String PlpProductText() throws IOException {
		try {
			
			String plpproducts = PlpProducts.getText();
			Reporting.updateTestReport("Products landing page was successfully loaded",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return plpproducts;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to load the Product landing page " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: This method is verifying the text in PLP page
	 */

	public String PlpContentText() throws IOException {
		try {
			String plpcontent = PlpContectText.getText();
			Reporting.updateTestReport("Products landing page was successfully loaded",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return plpcontent;
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to load the Product landing page with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: This method is verifying  VetCosultPurchaseOption on PDP page
	 */
	public void VetCosultPurchaseOption() throws IOException {
		try {
			VetConsultText.click();
			Reporting.updateTestReport("Vet Counsult PDP page is displayed",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to Click on VetConsult page with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Author:Vishnu
	 * 
	 * @Description: This method is Clicking on View on VetCosult Option on PDP page
	 */
	public void ClikingOnVETSoultIconOnPDPPage() throws IOException {
		try {
			ClickingOnVetSoultIcon.click();
			Reporting.updateTestReport("View On VetCosult Arrow Icon clicked on PDP Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to load the page with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the format facet
	 * @Date: 25/11/22
	 */
	public void clickOnFormatFacet() throws IOException{
		try {
			FormatFacet.click();
			Reporting.updateTestReport("Format under the facet was clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Format under the facet couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks if VetConsult is present under format facet
	 * @Date: 25/11/22
	 */
	public Boolean checkVetConsultInFormatFacet() throws IOException{
		try {
			if(VetConsultUnderFormatFacet.isDisplayed()) {
				Reporting.updateTestReport("VetConsult was present under format facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("VetConsult was not present under format facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("VetConsult was not present under format facet",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the VetConsult format under facet
	 * @Date: 25/11/22
	 */
	public void clickOnVetConsultInFormatFacet() throws IOException{
		try {
			VetConsultUnderFormatFacet.click();
			Reporting.updateTestReport("VetConsult under the format facet was clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("VetConsult under the format facet couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks if after searching a term, in the search result, the product names are highlighted with the searched term
	 * @Date: 25/11/22
	 */
	public Boolean checkProductsWithHighlightedSearchedTerm(String text) throws IOException{
		try {
			
			
			if(HighlightedSearchedTerm.isDisplayed()) {
				Reporting.updateTestReport("Products With Highlighted Searched Term was present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("Products With Highlighted Searched Term was not present in else",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Products With Highlighted Searched Term was not present"+text,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks if Subject is present under  facet
	 * @Date: 25/11/22
	 */
	public Boolean checkSubjectFacet() throws IOException{
		try {
			if(SubjectFacet.isDisplayed()) {
				Reporting.updateTestReport("Subject was present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("Subject was not present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Subject was not present under facet",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks if Course is present under  facet
	 * @Date: 25/11/22
	 */
	public Boolean checkCourseFacet() throws IOException{
		try {
			if(CourseFacet.isDisplayed()) {
				Reporting.updateTestReport("Course was present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("Course was not present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Course was not present under facet",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks if Author is present under  facet
	 * @Date: 25/11/22
	 */
	public Boolean checkAuthorFacet() throws IOException{
		try {
			if(AuthorFacet.isDisplayed()) {
				Reporting.updateTestReport("Author was present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("Author was not present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Author was not present under facet",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks if Format is present under  facet
	 * @Date: 25/11/22
	 */
	public Boolean checkFormatFacet() throws IOException{
		try {
			if(FormatFacet.isDisplayed()) {
				Reporting.updateTestReport("Format was present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("Format was not present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Format was not present under facet",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks if PublishedDate is present under  facet
	 * @Date: 25/11/22
	 */
	public Boolean checkPublishedDateFacet() throws IOException{
		try {
			if(PublishedDateFacet.isDisplayed()) {
				Reporting.updateTestReport("PublishedDate was present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("PublishedDate was not present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("PublishedDate was not present under facet",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks if Brands is present under  facet
	 * @Date: 25/11/22
	 */
	public Boolean checkBrandsFacet() throws IOException{
		try {
			if(BrandsFacet.isDisplayed()) {
				Reporting.updateTestReport("Brands was present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("Brands was not present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Brands was not present under facet",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Checks if Series is present under  facet
	 * @Date: 25/11/22
	 */
	public Boolean checkSeriesFacet() throws IOException{
		try {
			if(SeriesFacet.isDisplayed()) {
				Reporting.updateTestReport("Series was present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("Series was not present under facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Series was not present under facet",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}
	
	/*
	 * @Author: Vishnu
	 * 
	 * @Description: This method is used to enter the text in textbox field
	 * 
	 */
	public void SearchingFortheProduct() throws IOException {
		try {
			CartPageSearchBar.sendKeys(Keys.ENTER);
			Reporting.updateTestReport("PDP page is displayed", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Enter", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: This method is Clicking on content Section in PDP page
	 */

	public void ClickOnContentSearchOnPDPPage() throws IOException {
		try {
			PlpContectText.click();
			Reporting.updateTestReport("Content section was clicked on PDP Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Content section in PDP page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: This method is verify ShopLinkHeaderCLPPage
	 */

	public void ShopLinkHeaderCLPPage() throws IOException {
		try {
			String text = ShopLinkCLPPage.getText();
			Reporting.updateTestReport("Category Landing page have " + text + " header Section",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to load Category Landing Page", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);

		}
	}
	
	
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: This method is verify FeaturedProductsOnCLPPage
	 */

	public void checkFeaturedProductsOnCLPPage() throws IOException {
		try {
			if (FeaturedProducts.isDisplayed())
				Reporting.updateTestReport("Feature Products header is displayed on Category Landing page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Feature Products header is displayed on Category Landing page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		} catch (Exception e) {
			Reporting.updateTestReport("Error thrown Feature Products  on header Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}

	
}
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: This method is verify ViewAllOnCLPPage
	 */
	public void ViewAllOnCLPPage() throws IOException {
		try {
			ViewAllOnCLPPage.click();
			Reporting.updateTestReport("View ALL Option Clicked on Category Landing page have ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on ViewAll Option", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: This method is verify ClickingOnHomePage
	 */

	public void ClickingOnHomePage() throws IOException {
		try {
			HomePageLogo.click();
			Reporting.updateTestReport("Home Page was landed Successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to load the Home Page", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);

		}

	}
	
	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Enters a data in HomePage search bar
	 */
	public void HomePageSearchBar(String data) throws IOException {
		try {
			HomePageSearchBar.sendKeys(data);
			Thread.sleep(2000);
			Reporting.updateTestReport(data + " text seached in the search bar",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Data couldn't be entered in the search bar",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: This method is verify ClickingSearchAllResults
	 */
	public void ClickingSearchAllResults() throws IOException {
		try {
			SearchAllResults.click();
			Reporting.updateTestReport("Search All Results successfully clicked on Search Bar Suggestions",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to loaded Search All Results on Search Bar Suggestions",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}

	}
	
	/*
	 * @Author:Vishnu
	 * 
	 * @Description: This method is verify ClickSortByOptionPDPPage
	 */
	public void ClickSortByOptionPDPPage() throws IOException {
		try {
			ClickonSortBy.click();
			Reporting.updateTestReport("Sort By Option was clicked successfully on PDP page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Sort Option on PDP Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}

	}


}






