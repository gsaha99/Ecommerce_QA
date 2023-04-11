package PageObjectRepo;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import Test_Suite.Wiley_NA_Cart_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class app_Wiley_Repo {
	Wiley_NA_Cart_Test_Suite wiley_NA_Cart_Test;
	public String SS_path = wiley_NA_Cart_Test.SS_path;

	WebDriver driver;

	//Home page 

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

	@FindBy(xpath = "//a[@href='/']/img[@class='brand-logo']")
	WebElement HomePageLogo;

	@FindBy(xpath = "//input[@id='searchbar']")
	WebElement HomePageSearchBar;

	@FindBy(xpath = "//i[@class='wl-search-bar__icon']")
	WebElement SearchBarIconHomePage;

	//View cart pop up and Cart page  

	@FindBy(xpath = "//button[contains(text(),'View Cart')]")
	WebElement ViewCartButton;

	@FindBy(xpath = "(//span[contains(text(),'Proceed to Checkout')])[2]")
	WebElement ProceedToCheckoutButton;

	@FindBy(xpath = "//select[@class='cartItemBookQty']")
	WebElement QuantityDropDown;

	@FindBy(xpath = "//span[@id='promoCodeLink']")
	WebElement PromoCodeLink;

	@FindBy(xpath = "//input[@id='js-voucher-code-text']")
	WebElement EnterPromoCode;

	@FindBy(xpath = "//span[@class='cartDiscountBtnText']")
	WebElement ApplyPromoButton;

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

	@FindBy(xpath = "//input[@id='search-bar']")
	WebElement CartPageSearchBar;

	@FindBy(xpath="//div[@class='col-xs-6 noPadding price orderDetailCommonVal']")
	WebElement OrderSubtotalInCartPage;

	@FindBy(xpath="(//div[@class='col-xs-6 noPadding orderDetailTotalVal'])[2]")
	WebElement OrderTotalInCartPage;

	@FindBy(xpath="//div[@class='row no-margin cartTotalVoucherApply']/div[@class='col-xs-6 noPadding price navyBlueVal']")
	WebElement DiscountValue;

	@FindBy(xpath="//a[@id='shoppingCartIcon']")
	WebElement ShoppingCartIconUAT3;

	// Login or Create Account page during checkout

	@FindBy(xpath = "//input[@id='j_username']")
	WebElement ExistingWileyUserMailID;

	@FindBy(xpath = "//input[@id='j_password']")
	WebElement ExistingWileyUserPassword;

	@FindBy(xpath = "//span[contains(text(),'Log In & Continue')]")
	WebElement LogInAndContinueButton;

	@FindBy(xpath="//input[@id='email']")
	WebElement EmailIdInCreateAccount;

	@FindBy(xpath="//button/span[text()='CREATE AN ACCOUNT']")
	WebElement CreateAccountButton;

	@FindBy(xpath="//input[@data-input_description='confirmemail']")
	WebElement ConfirmEmailId;

	@FindBy(xpath="//input[@id='pwd']")
	WebElement PasswordInCreateAccount;

	@FindBy(xpath="//div[@class='guestCreateAccountBtnDiv']/button/span[text()='Continue as Guest']")
	WebElement GuestCheckoutButton;

	@FindBy(xpath="//span[text()='! An account already exists for this email address. Please log in.']")
	WebElement ErrorMessageAfterEnteringExistingUserInCreateAccount;

	@FindBy(xpath="//form[@id='loginForm']/div[@class='form-cell has-error errorMessage']")
	WebElement ErrorMessageAfterEnteringWrongPassword;

	// Shipping information during checkout 

	@FindBy(xpath="//input[@id='firstName']")
	WebElement AddressFirstName;

	@FindBy(xpath="//input[@id='lastName']")
	WebElement AddressLastName;

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

	@FindBy(xpath="//input[@id='line1']")
	WebElement ShippingAddressLine1;

	@FindBy(xpath="//input[@id='phone']")
	WebElement ShippingPhoneNumber;

	@FindBy(xpath="(//button[@id='wel_use_suggested_address_button']/span[text()='Use Selected Address'])[2]")
	WebElement UseSelectedShippingAddressButtonAddressDoctorPopUp;

	@FindBy(xpath = "(//span[contains(text(),'Use')])[2]")
	WebElement USEOptionForExistingAddress;

	@FindBy(xpath = "//button[@class='button button-main large shipping-button-group']")
	WebElement EnterNewAddress;

	@FindBy(xpath = "//span[contains(text(),'Save and Continue')]")
	WebElement SaveAndContinueButton;

	@FindBy(xpath="//div[@class='modal-content']")
	WebElement CountryRestrictionModal;

	@FindBy(xpath="(//span[@id='deliveryMethodMessage'])[2]/div/h5")
	WebElement PODMessage;

	@FindBy(xpath="//h5[@id='shippingAddressTitle']/span[text()='Shipping Address']")
	WebElement ShippingStepText;

	//Card information in billing step during checkout

	@FindBy(xpath = "//select[@id='expiryMonth']")
	WebElement ExpirationDateForMonth;

	@FindBy(xpath = "//select[@id='expiryYear']")
	WebElement ExpirationDateForYear;

	@FindBy(xpath = "//input[@id='securityCode']")
	WebElement CVV_Number;

	@FindBy(xpath = "//input[@id='number']")
	WebElement CardNumber;

	@FindBy(xpath = "//button[@id='placeOrder']")
	WebElement Place_OrderButton;

	@FindBy(xpath="//input[@id='nameOnCard']")
	WebElement CardHolderName;

	//School Information during checkout

	@FindBy(xpath = "//button[@id='yesBtn']")
	WebElement YesBtnForSchoolInfo;

	@FindBy(xpath = "//select[@id='region']")
	WebElement StateDropDownForSchoolInfo;

	@FindBy(xpath = "//select[@id='school']")
	WebElement SchoolDropDown;

	@FindBy(xpath = "//button[@class='btn schoolNoBtn']")
	WebElement SchoolInfo;

	//Billing address information during checkout

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

	@FindBy(xpath="(//span[@class='glyphicon glyphicon-pencil editIcon'])[2]")
	WebElement EditButtonInBilling;

	@FindBy(id="wrongCardValidation")
	WebElement WrongCardDetailsErrorMessage;

	@FindBy(id="addNewAddressButton")
	WebElement EnterNewAddressButton;

	@FindBy(xpath="//span[text()='We require an address on file to create your account and place your order']")
	WebElement TextInZeroDollarFlow;

	@FindBy(xpath="//div[@id='billingMultiPaymentTitle']")
	WebElement PaymentMethodText;

	//Order Review step during checkout

	@FindBy(xpath="//label[@id='termsCondsCheckbox']")
	WebElement TermsAndConditionCheckBox;

	@FindBy(xpath="//div[@class='col-xs-9 noPadding orderReviewDetailsLabel' and contains(text(),'Taxes:')]/following-sibling::div")
	WebElement TaxInOrderReview;

	@FindBy(xpath="((//div[@class='row no-margin orderReviewDetailsRow'])[1]/div[@class='col-xs-3 noPadding textRight orderReviewDetailsValue'])[1]")
	WebElement PriceOfFirstProductInOrderReview;

	@FindBy(xpath="((//div[@class='row no-margin orderReviewDetailsRow'])[2]/div[@class='col-xs-3 noPadding textRight orderReviewDetailsValue'])[1]")
	WebElement PriceOfSecondProductInOrderReview;

	@FindBy(xpath="//div[@class='col-xs-9 noPadding orderReviewSummaryTitle' and contains(text(),'Total:')]/following-sibling::div")
	WebElement TotalInOrderReview;

	@FindBy(xpath="//div[@class='col-xs-9 noPadding orderReviewDetailsLabel' and contains(text(),'Shipping:')]/following-sibling::div")
	WebElement ShippingChargeInOrderReview;

	@FindBy(xpath="//div[@class='col-xs-9 noPadding orderReviewDetailsLabel' and contains(text(),'Discount:')]/following-sibling::div")
	WebElement DiscountInOrderReview;

	//Order confirmation page

	@FindBy(xpath = "(//div[@class='orderConfirmationLabelVal textTransCap marginTop10'])[2]")
	WebElement OrderId;

	@FindBy(xpath = "(((//div[contains(text(),'Shipping')])[2])//following::div)[1]")
	WebElement Shippingcharge;

	@FindBy(xpath = "((//div[contains(text(),'Total')])//following::div)[1]")
	WebElement OrderTotalAmount;

	@FindBy(xpath="//span[text()='Thank you for your Order']")
	WebElement ThankYouText;

	@FindBy(xpath="(//div[contains(text(),'Taxes')]//following::div)[1] ")
	WebElement TotalTax;

	@FindBy(xpath="//a[@class='icon-print']/span[@class='glyphicon glyphicon-print']")
	WebElement PrintRecieptIconInOrderConfirmation;

	//Product details page tabs for selecting variants and prices

	@FindBy(xpath = "(//input[@name='purchasedProduct'])[1]")
	WebElement EbookRental;

	@FindBy(xpath = "(//div[@class='productButtonGroupName'])[2]")
	WebElement PrintProduct;

	@FindBy(xpath = "(//span[@class='typeOfProductSpan'])[4]")
	WebElement SelectingTextbookRental;

	@FindBy(xpath="//button[text()='Add to cart']")
	WebElement AddToCartButton;

	@FindBy(xpath="//div[@class='productButtonGroupName' and text()='Print']")
	WebElement PrintTab;

	@FindBy(xpath="//a[contains(text(),'Request Digital Evaluation Copy')]")
	WebElement RequestDigitalEvaluationCopyLink;

	@FindBy(xpath="//i[@aria-label='Purchase option description']")
	WebElement GenericHoverInfo;

	@FindBy(xpath="//p[@class='pr-price']")
	WebElement ProductPriceInPDP;

	// Footer Page

	@FindBy(xpath = "//span[contains(text(),'Site M')]")
	WebElement SiteMap;

	@FindBy(xpath = "//span[contains(text(),'Rights & Permissions')]")
	WebElement RightsAndPermissions;

	@FindBy(xpath = "//span[contains(text(),'Privacy Policy')]")
	WebElement PrivacyPolicy;

	@FindBy(xpath = "//span[contains(text(),'Terms of Use')]")
	WebElement TermsofUse;

	// Product details page and product listing Page

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

	@FindBy(xpath = "(//a[contains(text(),'Shop')])[2]")
	WebElement ShopLinkCLPPage;

	@FindBy(xpath = "(//a[@class='link-corner'])[1]")
	WebElement ViewAllOnCLPPage;

	//Search Result Page

	@FindBy(xpath = "(//div[@class='products-list']//section//div//a//img)//following::div//h3//a")
	WebElement SRP_WileyProduct;

	@FindBy(xpath = "//a[@class='see-all-results']")
	WebElement SearchAllResults;

	@FindBy(xpath="//h3[contains(text(),'FORMAT')]")
	WebElement FormatFacet;

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

	@FindBy(xpath="//div[@class='wileyProductPriceFormate']")
	WebElement PriceInSRP;

	@FindBy(xpath="(//div[@class='facet-list facets-panel-list js-facet-list '])[3]//span[@class='facet-text']")
	WebElement Facettext;

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

	@FindBy(xpath="(//li[@class='pagination-quantity-text'])[1]")
	WebElement TotalNumberOfPages;

	@FindBy(id="sortOptions-button")
	WebElement SortDropDown;

	@FindBy(id="ui-id-10")
	WebElement PublicationDateFromSortDropDown;

	@FindBy(xpath="//div[@class='applied-facets']/div[@class='facets-panel-item']/span[text()='RGuroo']")
	WebElement RGurooFacet;

	@FindBy(xpath="(//span[@class='facet-label'])[1]")
	WebElement FirstFacetItem;

	@FindBy(xpath="(//span[@class='facet-label'])[1]/span[@class='facet-text']")
	WebElement FirstFacetItemText;

	@FindBy(xpath="(//span[@class='facet-label'])[1]/span[@class='facet-text']/span[@class='facet-value-count']/i")
	WebElement FirstFacetItemQuantity;

	@FindBy(xpath="((//span[@class='nav-tabs-results'])[1]/i)[2]")
	WebElement NumberOfProductsAfterFiltering;

	@FindBy(xpath="//i[@class='clear-facets active']/a")
	WebElement ResetFilter;

	@FindBy(xpath="//span[contains(text(),'E-Book')]//parent::span[@class='facet-label']")
	WebElement EBookFormatFacet;

	@FindBy(xpath="//span[contains(text(),'E-Book')]/span[@class='facet-value-count']/i")
	WebElement EBookFormatFacetQuantity;

	//Vetconsult product details page

	@FindBy(xpath = "//span[@class='search-highlight']")
	WebElement VetConsultText;

	@FindBy(xpath = "//span[@class='glyphicon glyphicon-chevron-right']")
	WebElement ClickingOnVetSoultIcon;

	@FindBy(xpath="//span[@class='item-price item-price-value']")
	WebElement PriceInPDPVetConsult;

	@FindBy(xpath="//span[contains(text(),'VetConsult')]")
	WebElement VetConsultUnderFormatFacet;


	// Pre-Order and Back-Order products' specific xpaths

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

	//Forgot Password functionality

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

	@FindBy(xpath="//input[@id='updatePwd.pwd']")
	WebElement NewPasswordFieldInResetPasswordPage;

	@FindBy(xpath="//input[@id='updatePwd.checkPwd']")
	WebElement ConfirmPasswordFieldInResetPasswordPage;

	@FindBy(xpath="//button/span[text()='Submit']")
	WebElement SubmitButtonInResetPasswordPage;




	/*
	 * @Author: Anindita
	 * @Description: Enters a data in search bar
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
	 */
	public void clickOnAddToCartButton(WebDriver driver) throws IOException{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(AddToCartButton));
			AddToCartButton.click();
			Reporting.updateTestReport("Add To Cart Button was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			try {
				driver.navigate().refresh();
				AddToCartButton.click();
				Reporting.updateTestReport("Add To Cart Button was clicked after refreshing the page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e1) {
			Reporting.updateTestReport("Add To Cart couldn't be clicked",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
			}
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the View Cart button in the pop up
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
	 */
	public void enterPasswordInCreateAccountForm(String password) throws IOException {
		try {
			PasswordInCreateAccount.clear();
			Thread.sleep(1000);
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
	 */
	public void clickOnContinueAsGuestButton() throws IOException{
		try {
			GuestCheckoutButton.click();
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
	public void WileyLogOut(WebDriver driver) throws IOException {
		try {
			driver.get(excelOperation.getTestData("WILEY_LogOut_URL", "Generic_Dataset", "Data"));
			driver.manage().deleteAllCookies();
			if(driver.getTitle().contentEquals("Wiley | Global Leader in Publishing, Education and Research")) Reporting.updateTestReport("Logged out successfully",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Couldn't logout",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}


	/*
	 * @Author: Arun
	 * @Description:This method using for clicking on ProceedToCheckout Button
	 */
	public void clickOnProceedToCheckoutButton() throws IOException {

		try {
			ProceedToCheckoutButton.click();
			Reporting.updateTestReport("Proceed To Checkout Button was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Proceed To Checkout Button was not Clicked with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}




	/*
	 * @Author: Arun
	 * @description: Clicks on the create account button in the checkout login page
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
	 * @Description:This method clicks on the save and continue button
	 */
	public void clickOnSaveAndContinueButton() throws IOException {

		try {
			SaveAndContinueButton.click();
			Reporting.updateTestReport("Save And Continue Button was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Save And Continue Button was not Clicked with the error message " + e.getClass().toString(),
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
	 * Arun Description : Entering the CVV Number in payment information step
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
	 * Author : Arun 
	 * description : This Method using for clicking on "USE" button for Existing User
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
	 * Author : Arun
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
	 * Author : Arun  
	 * Description : THis Method using for TaxAmount Fetching From Order Confirmation page
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
			Reporting.updateTestReport("Card Number was entered successfully on paymentpage",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the card Number with error message " + e.getClass().toString(),
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
			Reporting.updateTestReport("First Name was entered successfully in checkout",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the First Name in checkout with error message " + e.getClass().toString(),
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
			Reporting.updateTestReport("Last Name  was entered successfully in checkout",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the Last name with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}


	/*
	 * @Author:Vishnu
	 * @Description:This method is used to click on the NewAddress button
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
			String shippingCharge = Shippingcharge.getText();
			System.out.println();
			Reporting.updateTestReport("Shipping Charge: "+shippingCharge+" was fetched successfully from Order Confirmation Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return shippingCharge;
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Failed to fetch the Shipping Charge with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		return "";
	}


	/*
	 * @Author: Anindita
	 * @Description: Returns the Billing address doctor pop up button 
	 */
	public WebElement returnUseSelectedBillingAddressButtonAddressDoctorPopUp() {
		return UseSelectedBillingAddressButtonAddressDoctorPopUp;
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Returns the Shipping address doctor pop up button 
	 */
	public WebElement returnUseSelectedShippingAddressButtonAddressDoctorPopUp() {
		return UseSelectedShippingAddressButtonAddressDoctorPopUp;
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the print tab in PDP
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

	/*
	 * @Author: Anindita
	 * @Description: Selects the eBook rental variant in PDP
	 */
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

	/*
	 * @Author: Anindita
	 * @Description: Clicks on the Promotion code link in cart page
	 */
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

	/*
	 * @Author: Anindita
	 * @Description: Enters the promo code in the cart page
	 */
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

	/*
	 * @Author: Anindita
	 * @Description: Clicks on the Apply button in the cart page
	 */
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

	/*
	 * @Author: Anindita
	 * @Description: Selects School information step option yes/ no
	 */
	public void selectingSchoolInfo() throws IOException {
		try {
			SchoolInfo.click();
			Reporting.updateTestReport("School Information was selected successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to select the school Information",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Selects the textbook rental variant in PDP
	 */
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
	 */
	public String fetchTaxInOrderReview() throws IOException{
		try {
			String tax=TaxInOrderReview.getText();
			Reporting.updateTestReport("Tax: "+tax.trim()+" was fetched in order review step successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
			return tax.trim();
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
	 * @Description: Checks if Text In Order Summary Tab during checkout
	 */
	public void checkTextInOrderSummaryTab(String message, WebDriver driver) throws IOException{
		try {
			if(driver.findElement(By.xpath
					("//div/i[contains(text(),'"+message+"')]")).isDisplayed()) 
				Reporting.updateTestReport("Text: "+message+" In Order Summary Tab is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Text: "+message+" In Order Summary Tab is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Text: "+message+" In Order Summary Tab is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Concatenates the parts of URL (devmonkey part, storerfront url with env and the rest of the part specific to some products)
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
	 */
	public void checkNextAvailabilityDatePreorderInCartPage() throws IOException{
		try {
			if(NextAvailabilityDateForPreorderProductInCartPage.isDisplayed()) 
				Reporting.updateTestReport("Nextavailability date was present for pre-order product",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			//Reporting.updateTestReport("Next Availibility date is present for pre-order product as: "+driver.findElement(By.xpath("(//span[@class='cart-item-info-val'])[2]")).getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("NextAvailibilityDate was not present for Pre-order product. "
					+ "Probable reason might be, the date is not set up in backoffice",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
		}

	}
	/*
	 * @Author: Anindita
	 * @Description: Checks the next availability date for second added back-order product in cart page
	 */
	public void checkNextAvailabilityDateBackorderInCartPage() throws IOException{
		try {
			if(NextAvailabilityDateForBackorderProductInCartPage.isDisplayed()) 
				Reporting.updateTestReport("Nextavailability date was present for backorder product", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			//Reporting.updateTestReport("Next Availibility date is present for back-order product as: "+driver.findElement(By.xpath("(//span[@class='cart-item-info-val'])[4]")).getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("NextAvailibilityDate was not present for Back-order product. "
					+ "Probable reason might be, the date is not set up in backoffice",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
		}

	}

	/*
	 * @Author: Anindita
	 * @Description: Checks the notification message for first added pre-order product in cart page
	 */
	public void checkNotificationMessagePreorderInCartPage() throws IOException{
		try {
			if(PreOrderNotificationMessageInCartPage.isDisplayed()) 
				Reporting.updateTestReport("notification message is present for pre-order product as: "+PreOrderNotificationMessageInCartPage.getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("notification message was not presentfor pre order product "
					+ "probably because of data set up issue in backoffice", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
		}

	}

	/*
	 * @Author: Anindita
	 * @Description: Checks the notification message for first added back-order product in cart page
	 */
	public void checkNotificationMessageBackorderInCartPage() throws IOException{
		try {
			if(BackOrderNotificationMessageInCartPage.isDisplayed()) 
				Reporting.updateTestReport("notification message is present for back-order product as: "+BackOrderNotificationMessageInCartPage.getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("notification message was not present for back order product "
					+ "probably because of data set up issue in backoffice", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
		}

	}

	/*
	 * @Author: Anindita
	 * @Description: Checks the next availability date for second added pre-order product in Order review page
	 */
	public void checkNextAvailabilityDatePreorderInOrderReviewPage() throws IOException{
		try {
			if(PreOrderNextAvailabilityDateOrderReview.isDisplayed()) 
				Reporting.updateTestReport("Next availability date was present for preorder product "+PreOrderNextAvailabilityDateOrderReview.getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			//Reporting.updateTestReport("Next Availibility date is present for back-order product as: "+driver.findElement(By.xpath("(//span[@class='cart-item-info-val'])[4]")).getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Next AvailibilityDate was not present for Pre-order product"
					+ " because of data setup issue", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
		}

	}

	/*
	 * @Author: Anindita
	 * @Description: Checks the next availability date for second added back-order product in Order review page
	 */
	public void checkNextAvailabilityDateBackorderInOrderReviewPage() throws IOException{
		try {
			if(BackOrderNextAvailabilityDateOrderReview.isDisplayed()) 
				Reporting.updateTestReport("Next availability date was present for backorder product"+BackOrderNextAvailabilityDateOrderReview.getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			//Reporting.updateTestReport("Next Availibility date is present for back-order product as: "+driver.findElement(By.xpath("(//span[@class='cart-item-info-val'])[4]")).getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Next AvailibilityDate was not present for backrder product"
					+ " because of data setup issue.", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
		}

	}

	/*
	 * @Author: Anindita
	 * @Description: Checks the blue pre-order text and the date for second added back-order product in Order review page
	 */
	public void checkNextAvailabilityDatePreorderInOrderConfirmationPage() throws IOException{
		try {
			if(PreOrderBlueTextInOrderConfirmation.isDisplayed()) {
				Reporting.updateTestReport("Blue preorder text was present for backorder product", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				Reporting.updateTestReport("The complete line with next availability date is: "+PreOrderDateTextOrderConfirmation.getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}

		}
		catch(Exception e) {
			Reporting.updateTestReport("The complete line with next availability date was not present in order confirmation page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
		}

	}

	/*
	 * @Author: Anindita
	 * @Description: Checks the blue back-order text and the date for second added back-order product in Order review page
	 */
	public void checkNextAvailabilityDateBackorderInOrderConfirmationPage() throws IOException{
		try {
			if(BackOrderBlueTextInOrderConfirmation.isDisplayed()) {
				Reporting.updateTestReport("Blue backorder text was present for backorder product", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				Reporting.updateTestReport("The complete line with next availability date is: "+BackOrderDateTextOrderConfirmation.getText(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}

		}
		catch(Exception e) {
			Reporting.updateTestReport("The complete line with next availability date was not present in order confirmation page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
		}

	}

	/*
	 * Author:Anindita
	 * @Description: Checks on the print receipt link in order confirmation page
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
	 */
	public void enterEmailIdInYopmail(String username) throws IOException {
		try {
			EnterEmailIdInYopmail.clear();
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
	 * @Description: Checks if Publishing Services link is present in cart page
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
	 * @Description: Checks sitemaps in footer
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
	 * @Description: Checks sitemaps in cart page
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
	 * @Description: Checks sitemaps on pdp page
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
	 * @Description: Checks RightsandPermission on footer
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
	 * @Description: Checks RightsandPermission on pdp page
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
	 * @Description: Checks privacy policy on footer
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
	 * @Description: Checks privacy policy on pdp page
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
	 * @Description: Checks terms of use on footer
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
	 * @Description: Checks terms of use in pdp page
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
	 */
	public void enterNewPasswordFieldInResetPasswordPage(String password) throws IOException {
		try {
			NewPasswordFieldInResetPasswordPage.clear();
			Thread.sleep(1000);
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
	 */
	public void enterConfirmPasswordFieldInResetPasswordPage(String password) throws IOException {
		try {
			ConfirmPasswordFieldInResetPasswordPage.clear();
			Thread.sleep(1000);
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
	 * @Description: Enter the text in textbox field
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
	 * @Description: This method is used to enter the text in textbox field 
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

	/*
	 * @Author: Anindita
	 * @Description: Validates that the price for vetconsult standalone is not present in SRP
	 */
	public void checkPriceInSRPForVetConsult() throws IOException{
		try {

			String price=PriceInSRP.getText();
			if(price.equalsIgnoreCase(""))
				Reporting.updateTestReport("Price for VetConsult standalone product was not present in SRP as expected",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Price for VetConsult standalone product was present in SRP: "+price,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Price for VetConsult standalone product was not present in SRP as expected",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Validates that the price for vetconsult standalone is not present in PDP
	 */
	public void checkPriceInPDPForVetConsult() throws IOException{
		try {

			String price=PriceInPDPVetConsult.getText();
			if(price.equalsIgnoreCase(""))
				Reporting.updateTestReport("Price for VetConsult standalone product was not present in PDP as expected",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Price for VetConsult standalone product was present in PDP: "+price,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Price for VetConsult standalone product was not present in PDP as expected",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Concatenates the parts of URL (devmonkey part, storerfront url with env and the rest of the part specific to regions)
	 */
	public String wileyURLConcatenationwithRegions(String region, String pdpURL) throws IOException{
		try {
			String userNamePassword=excelOperation.getTestData("WILEY_Username_Password", "Generic_Dataset", "Data");
			String envURL="uat.store.wiley.com/en-"+region;
			Reporting.updateTestReport("Concatenated url was returned as: "+
					"https://"+userNamePassword+"@"+envURL+"/"+pdpURL, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return "https://"+userNamePassword+"@"+envURL+"/"+pdpURL;

		}
		catch(Exception e) {
			Reporting.updateTestReport("Concatenated url couldn't be returned", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Clicks on the the Request digital evaluation copy link
	 */
	public void clickOnRequestDigitalEvaluationCopyLink() throws IOException{
		try {
			RequestDigitalEvaluationCopyLink.click();
			Reporting.updateTestReport("Request digital evaluation copy link was successfully clicked in PDP",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Request digital evaluation copy link couldn't be clicked in PDP",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Returns the hyperlink for the Request Digital Evaluation copy
	 */
	public String fetchHyperlinkForRequestDigitalEvaluationCopy() throws IOException{
		try {
			String hyperlink=RequestDigitalEvaluationCopyLink.getAttribute("href");
			Reporting.updateTestReport("Request digital evaluation copy hyperlink was returned successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return hyperlink;
		}
		catch(Exception e) {
			Reporting.updateTestReport("Request digital evaluation copy hyperlink couldn't be fetched",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Returns the text shown in the first point upon hovering the generic info for E-Book
	 */
	public String fetchGenericHoverInfo(WebDriver driver) throws IOException{
		try {
			Actions action = new Actions(driver);
			action.moveToElement(GenericHoverInfo).perform();
			Thread.sleep(2000);
			String A=GenericHoverInfo.getAttribute("data-content").split("<ul>")[1];
			String B=A.split("<li>")[1].trim();
			Reporting.updateTestReport("The first line of generic hover info: "+B+" was returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return B;
		}
		catch(Exception e) {
			Reporting.updateTestReport("The first line of generic hover info couldn't be returned"+e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Fetches the product's price from PDP
	 */
	public String fetchPriceInPDP() throws IOException {
		try {
			Reporting.updateTestReport(
					"Price of the product in PDP: "+ProductPriceInPDP.getText().trim()+" was returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return ProductPriceInPDP.getText().trim();
		}
		catch(Exception e) {
			Reporting.updateTestReport(
					"Price of the product in PDP could not be returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Fetches the the order subtotal from cart page
	 */
	public String fetchOrderSubTotalInCartPage() throws IOException {
		try {
			Reporting.updateTestReport(
					"Subtotal of the order: "+OrderSubtotalInCartPage.getText().trim()+" was returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return OrderSubtotalInCartPage.getText().trim();
		}
		catch(Exception e) {
			Reporting.updateTestReport(
					"Subtotal of the order in cart page could not be returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Fetches the Order total in cart page
	 */
	public String fetchOrderTotalInCartPage() throws IOException {
		try {
			Reporting.updateTestReport(
					"Order total in cart page : "+OrderTotalInCartPage.getText().trim()+" was returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return OrderTotalInCartPage.getText().trim();
		}
		catch(Exception e) {
			Reporting.updateTestReport(
					"Order total in cart page could not be returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Fetches the the discount amount from cart page
	 */
	public String fetchDiscountAmountInCartPage() throws IOException {
		try {
			Reporting.updateTestReport(
					"Discount amount: "+DiscountValue.getText().split("-")[1]+" was returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return DiscountValue.getText().split("-")[1];
		}
		catch(Exception e) {
			Reporting.updateTestReport(
					"Discount amount in cart page could not be returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Fetches the total from Order Review Step
	 */
	public String fetchTotalInOrderReview() throws IOException{
		try {
			String total=TotalInOrderReview.getText();
			Reporting.updateTestReport("Order total: "+total.trim()+" was fetched in order review step successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
			return total.trim();
		}
		catch(Exception e) {
			Reporting.updateTestReport("Order total couldn't be fetched in order review step ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			return "";

		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Fetches the First Product's Price from Order Review Step
	 */
	public String fetchFirstProductPriceInOrderReview() throws IOException{
		try {
			String price=PriceOfFirstProductInOrderReview.getText();
			Reporting.updateTestReport("First Product Price: "+price.trim()+" was fetched in order review step successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
			return price.trim();
		}
		catch(Exception e) {
			Reporting.updateTestReport("First Product Price couldn't be fetched in order review step ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			return "";

		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Fetches the Second Product's Price from Order Review Step
	 */
	public String fetchSecondProductPriceInOrderReview() throws IOException{
		try {
			String price=PriceOfSecondProductInOrderReview.getText();
			Reporting.updateTestReport("Second Product Price: "+price.trim()+" was fetched in order review step successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
			return price.trim();
		}
		catch(Exception e) {
			Reporting.updateTestReport("Second Product Price couldn't be fetched in order review step ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			return "";

		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Fetches the shipping charge from Order Review Step
	 */
	public String fetchShippingChargeInOrderReview() throws IOException{
		try {
			String shipping=ShippingChargeInOrderReview.getText();
			Reporting.updateTestReport("Shipping charge: "+shipping.trim()+" was fetched in order review step successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
			return shipping.trim();
		}
		catch(Exception e) {
			Reporting.updateTestReport("Shipping charge couldn't be fetched in order review step ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			return "";

		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Fetches the discount from Order Review Step
	 */
	public String fetchDiscountInOrderReview() throws IOException{
		try {
			String discount=DiscountInOrderReview.getText().split("(-)")[1].substring(1);
			Reporting.updateTestReport("Discount: "+discount.trim()+" was fetched in order review step successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
			return discount.trim();
		}
		catch(Exception e) {
			Reporting.updateTestReport("Discount couldn't be fetched in order review step ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			return "";

		}
	}


	/*
	 * @Author:Anindita
	 * @Description: Checks if Text in Order confirmation page
	 */
	public void checkTextInOrderConfirmationPage(String message, WebDriver driver) throws IOException{
		try {
			if(driver.findElement(By.xpath
					("//div[@id=\"orderConfirmationProgressAboveText\" and contains(text(),\""+message+"\")]")).isDisplayed()) 

				Reporting.updateTestReport("Text: "+message+" In Order confirmation page is present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Text: "+message+" In Order confirmation page is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Text: "+message+" In Order confirmation page is not present", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	
	/* 
	 * @Author: Anindita
	 * @Description: Clicks on the format facet
	 */
	public void clickOnAuthorFacet() throws IOException{
		try {
			AuthorFacet.click();
			Reporting.updateTestReport("Author under the facet was clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Author under the facet couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Selects First Facet Item
	 */
	public String clickOnFirstFacetValue() throws IOException{
		try {
			FirstFacetItem.click();
			Reporting.updateTestReport("First Facet Item was clicked with Text: "
					+FirstFacetItemText.getText().replace(FirstFacetItemQuantity.getText(), "")+" and Quantity of that Filter: "+FirstFacetItemQuantity.getText(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return FirstFacetItemText.getText().trim().replace(FirstFacetItemQuantity.getText(), "")+"#"+FirstFacetItemQuantity.getText();
		}
		catch(Exception e) {
			Reporting.updateTestReport("First Facet Item couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Checks number of items after filtering
	 */
	public void checkNumberOfProductsAfterFiltering(String numbers) throws IOException{
		try {
			if(NumberOfProductsAfterFiltering.getText().equalsIgnoreCase(numbers)) {
				Reporting.updateTestReport("The number of products after filtering is correctly shown as: "+numbers,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

			}
			else {
				Reporting.updateTestReport("The number of products after filtering is: "+NumberOfProductsAfterFiltering.getText()+
						" which is not matching with: "+numbers,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("The number of products after filtering couldn't be fetched",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Fetch all the search result web element
	 */
	public List<WebElement> getAllFilteredResultProducts() throws IOException{
		try {
			List<WebElement> products=driver.findElements(By.xpath("//h3[@class='product-title']"));
			Reporting.updateTestReport("List of filtered products was returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return products;

		}
		catch(Exception e) {
			Reporting.updateTestReport("List of filtered products couldn't be returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return null;
		}
	}

	/*
	 * @Author:Anindita
	 * @Description: Clicks on rest to rest all formats
	 */
	public void clickOnResetFilter() throws IOException {
		try {
			ResetFilter.click();
			Reporting.updateTestReport("Reset all button for facet was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Reset all button for facet couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Selects the E-Book format
	 */
	public String clickOnEBookFormatFacetValue() throws IOException{
		try {
			EBookFormatFacet.click();
			Reporting.updateTestReport("EBook Format Facet was clicked with Text: "
					+EBookFormatFacet.getText().replace(EBookFormatFacetQuantity.getText(), "")+" and Quantity of that Filter: "+EBookFormatFacetQuantity.getText(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return EBookFormatFacet.getText().trim().replace(EBookFormatFacetQuantity.getText(), "")+"#"+EBookFormatFacetQuantity.getText();
		}
		catch(Exception e) {
			Reporting.updateTestReport("EBook Format Facet couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Fetches the total number of pages in pagination
	 */
	public int fetchNumberOfPagesAfterFiltering() throws IOException{
		try {
			String page=TotalNumberOfPages.getText();
			String number=page.split("of")[1].substring(1).split(" ")[0];
			Reporting.updateTestReport("Number of total pages: "+number+" was returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return Integer.parseInt(number);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Number of total pages couldn't be returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return 0;
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Checks if wrong card details error message is present or not
	 */
	public void checkWrongCardErrorMessage() throws IOException{
		try {
			Reporting.updateTestReport("Wrong card details error was displayed with message: "
					+WrongCardDetailsErrorMessage.getText() , CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Wrong card details error was not displayed ", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Fetches the shipping charge for shipping methods by passing the shipping method name
	 */
	public BigDecimal fetchShippingCharge(WebDriver driver,String shippingMethod) throws IOException{
		try {
			String xpathOfShippingCharge="//span[@class='delivery-item-title deliveryItemTitle' and contains(text(),'"+
					shippingMethod+"')]/following-sibling::span/span[@class='textBold']";
			String xpathOfShippingMethodName="//span[@class='delivery-item-title deliveryItemTitle' and contains(text(),'"+
					shippingMethod+"')]";
			String charge=driver.findElement(By.xpath(xpathOfShippingCharge)).getText();
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
	 * @Description: Clicks on enter new address button in shipping page
	 */
	public void clickOnEnterNewAddresButtonInShipping() throws IOException{
		try {
			EnterNewAddressButton.click();
			Reporting.updateTestReport("Enter new Address button was successfully clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Enter new Address button was couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Clicks on Sorting drop down in search result drop down
	 */
	public void clickOnSortDropDown() throws IOException{
		try {
			SortDropDown.click();
			Reporting.updateTestReport("Sort Drop Down button was successfully clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Sort Drop Down button was couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Clicks on Publication Date From Sort Drop Down
	 */
	public void clickOnPublicationDateFromSortDropDown() throws IOException{
		try {
			PublicationDateFromSortDropDown.click();
			Reporting.updateTestReport("Publication Date From Sort Drop Down was successfully clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Publication Date From Sort Drop Down was couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Checks At least 10 characters -> this criteria in password page
	 */
	public void checkAtLeast10Characters(WebDriver driver,String colour) throws IOException {
		try {
			String xpathOfCriteria;
			if(colour.equalsIgnoreCase("blue")) {
				xpathOfCriteria="//span[@id='registerCheckLength']/span[@style='color: rgb(0, 82, 116);']";
				try {
					if(driver.findElement(By.xpath(xpathOfCriteria)).isDisplayed())
						Reporting.updateTestReport("At least 10 characters was satisfied and was in blue colour",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("At least 10 characters was not satisfied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			else
			{
				xpathOfCriteria="//span[@id='registerCheckLength']/span[@style='color: rgb(164, 47, 19);']";
				try {
					if(driver.findElement(By.xpath(xpathOfCriteria)).isDisplayed())
						Reporting.updateTestReport("At least 10 characters was not satisfied and was in red colour",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("At least 10 characters was satisfied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}		

		}
		catch(Exception e) {
			Reporting.updateTestReport("At least 10 charactersthis condition couldn't be verified",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Checks At least 3 of the following: -> this criteria in password page
	 */
	public void checkAtLeast3ofTheFollowing(WebDriver driver,String colour) throws IOException {
		try {
			String xpathOfCriteria;
			if(colour.equalsIgnoreCase("blue")) {
				xpathOfCriteria="//span[@id='registerCheckAttribute']/span[@style='color: rgb(0, 82, 116);']";
				try {
					if(driver.findElement(By.xpath(xpathOfCriteria)).isDisplayed())
						Reporting.updateTestReport("At least 3 criterias was satisfied and was in blue colour",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("At least  3 criterias was not satisfied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			else
			{
				xpathOfCriteria="//span[@id='registerCheckAttribute']/span[@style='color: rgb(164, 47, 19);']";
				try {
					if(driver.findElement(By.xpath(xpathOfCriteria)).isDisplayed())
						Reporting.updateTestReport("At least  3 criterias was not satisfied and was in red colour",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("At least  3 criterias was satisfied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}		

		}
		catch(Exception e) {
			Reporting.updateTestReport("At least  3 criterias this condition couldn't be verified",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Checks UpperCase -> this criteria in password page
	 */
	public void checkUpperCase(WebDriver driver,String colour) throws IOException {
		try {
			String xpathOfCriteria;
			if(colour.equalsIgnoreCase("blue")) {
				xpathOfCriteria="//div[@id='registerCheckUpperCaseDiv']/span[@style='color: rgb(0, 82, 116);']";
				try {
					if(driver.findElement(By.xpath(xpathOfCriteria)).isDisplayed())
						Reporting.updateTestReport("UpperCase was satisfied and was in blue colour",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("UpperCase was not satisfied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			else
			{
				xpathOfCriteria="//div[@id='registerCheckUpperCaseDiv']/span[@style='color: rgb(164, 47, 19);']";
				try {
					if(driver.findElement(By.xpath(xpathOfCriteria)).isDisplayed())
						Reporting.updateTestReport("UpperCase was not satisfied and was in red colour",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("UpperCase was satisfied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}		

		}
		catch(Exception e) {
			Reporting.updateTestReport("UpperCase this condition couldn't be verified",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Checks Lower Case -> this criteria in password page
	 */
	public void checkLowerCase(WebDriver driver,String colour) throws IOException {
		try {
			String xpathOfCriteria;
			if(colour.equalsIgnoreCase("blue")) {
				xpathOfCriteria="//div[@id='registerCheckLowerCaseDiv']/span[@style='color: rgb(0, 82, 116);']";
				try {
					if(driver.findElement(By.xpath(xpathOfCriteria)).isDisplayed())
						Reporting.updateTestReport("Lower Case was satisfied and was in blue colour",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("Lower Case was not satisfied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			else
			{
				xpathOfCriteria="//div[@id='registerCheckLowerCaseDiv']/span[@style='color: rgb(164, 47, 19);']";
				try {
					if(driver.findElement(By.xpath(xpathOfCriteria)).isDisplayed())
						Reporting.updateTestReport("Lower Case was not satisfied and was in red colour",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("Lower Case was satisfied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}		

		}
		catch(Exception e) {
			Reporting.updateTestReport("Lower Case this condition couldn't be verified",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Checks Number -> this criteria in password page
	 */
	public void checkNumber(WebDriver driver,String colour) throws IOException {
		try {
			String xpathOfCriteria;
			if(colour.equalsIgnoreCase("blue")) {
				xpathOfCriteria="//div[@id='registerCheckNumCaseDiv']/span[@style='color: rgb(0, 82, 116);']";
				try {
					if(driver.findElement(By.xpath(xpathOfCriteria)).isDisplayed())
						Reporting.updateTestReport("Number was satisfied and was in blue colour",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("Number was not satisfied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			else
			{
				xpathOfCriteria="//div[@id='registerCheckNumCaseDiv']/span[@style='color: rgb(164, 47, 19);']";
				try {
					if(driver.findElement(By.xpath(xpathOfCriteria)).isDisplayed())
						Reporting.updateTestReport("Number was not satisfied and was in red colour",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("Number was satisfied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}		

		}
		catch(Exception e) {
			Reporting.updateTestReport("Number this condition couldn't be verified",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Checks Special Character -> this criteria in password page
	 */
	public void checkSpecialCharacter(WebDriver driver,String colour) throws IOException {
		try {
			String xpathOfCriteria;
			if(colour.equalsIgnoreCase("blue")) {
				xpathOfCriteria="//div[@id='registerCheckSpclCharDiv']/span[@style='color: rgb(0, 82, 116);']";
				try {
					if(driver.findElement(By.xpath(xpathOfCriteria)).isDisplayed())
						Reporting.updateTestReport("Special Character was satisfied and was in blue colour",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("Special Character was not satisfied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			else
			{
				xpathOfCriteria="//div[@id='registerCheckSpclCharDiv']/span[@style='color: rgb(164, 47, 19);']";
				try {
					if(driver.findElement(By.xpath(xpathOfCriteria)).isDisplayed())
						Reporting.updateTestReport("Special Character was not satisfied and was in red colour",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("Special Character was satisfied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}		

		}
		catch(Exception e) {
			Reporting.updateTestReport("Special Character this condition couldn't be verified",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 15/3/23
	 * @Description: Checks the password criteria insuffiency message
	 */
	public void checkPasswordCriteriaInsufficiencyMessage(String message) throws IOException{
		try {
			String xpath="//div[@class='help-block commonErrorWelStyle' and contains(text(),'"+message+"')]";
			try {
				if(driver.findElement(By.xpath(xpath)).isDisplayed())
					Reporting.updateTestReport("Password criteria insuffiency message: "+message+" was displayed",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Password criteria insuffiency message was not displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Password criteria insuffiency message was not displayed with exception "+e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Removes all the products from cart
	 */
	public void removeProductsFromCart(WebDriver driver) throws IOException {
		try {
			driver.get(excelOperation.getTestData("Wiley_Cart_Page_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//a[@class='remove-item remove-entry-button removeCartEntryBtn']"))));
			ScrollingWebPage.PageScrolldown(driver, 0, 250, SS_path);
			List<WebElement> removeList = driver.findElements(By.xpath("//a[@class='remove-item remove-entry-button removeCartEntryBtn']"));
			while(!removeList.isEmpty()){
				removeList.get(0).click();
				Thread.sleep(200);
				removeList = driver.findElements(By.xpath("//a[@class='remove-item remove-entry-button removeCartEntryBtn']"));
			}
			Reporting.updateTestReport("Few products were removed from cart",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("No products were present in cart to remove",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Fetches the cookie value of ALM Token
	 */
	public void getALMTokenCookieValue(WebDriver driver) throws IOException {
		try {
			Reporting.updateTestReport(
					"ALM-token cookie was present with value: "
							+ driver.manage().getCookieNamed("ALM-token").getValue(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("ALM-token cookie was not present",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
		}
	}

}






