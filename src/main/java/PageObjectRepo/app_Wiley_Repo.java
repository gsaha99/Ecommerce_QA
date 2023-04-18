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

import TestSuite.Wiley_Prod_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class app_Wiley_Repo {
	Wiley_Prod_Test_Suite Wiley_Prod_Test;
	public String SS_path = Wiley_Prod_Test.SS_path;

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

	//Header and Footer related

	@FindBy(xpath="//ul[@class='menu']/li[@class='nav-btn']/a[text()='Shop ']")
	WebElement ShopLinkInCartPageHeader;
	@FindBy(xpath="//ul[@class='menu']/li[@class='nav-btn']/a[text()='Research Libraries ']")
	WebElement ResearchLibrariesLinkInCartPageHeader;
	@FindBy(xpath="//ul[@class='menu']/li[@class='nav-btn']/a[text()='Publishing Services ']")
	WebElement PublishingServicesLinkInCartPageHeader;
	@FindBy(xpath="//ul[@class='menu']/li[@class='nav-btn']/a[text()='Professional Development ']")
	WebElement ProfessionalDevelopmentLinkInCartPageHeader;

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
	@FindBy(xpath = "(//a[@href='/en-us/about'])[2]")
	WebElement AboutWiley;
	@FindBy(xpath = "//a[contains(text(),'PRODUCTS')]")
	WebElement PlpProducts;
	@FindBy(xpath = "//a[contains(text(),'CONTENT')]")
	WebElement PlpContectText;
	@FindBy(xpath = "(//a[contains(text(),'Shop')])[2]")
	WebElement ShopLinkCLPPage;
	@FindBy(xpath = "(//a[@class='link-corner'])[1]")
	WebElement ViewAllOnCLPPage;

	//Cart page and View cart pop up

	@FindBy(id = "search-bar")
	WebElement CartPageSearchBar;
	@FindBy(xpath = "//button[contains(text(),'View Cart')]")
	WebElement ViewCartButton;
	@FindBy(xpath="//div[@class='cart header-icon']")
	WebElement CartIcon;
	@FindBy(xpath="//span[@id='cart-total-badge']")
	WebElement CartItemQuantity;
	@FindBy(xpath="//ul[@id='breadcrumbStyle']")
	WebElement BreadCrumbCartPage;
	@FindBy(xpath="//div[@class='col-xs-6 noPadding price orderDetailCommonVal']")
	WebElement OrderSubtotalInCartPage;
	@FindBy(xpath="(//div[@class='col-xs-6 noPadding orderDetailTotalVal'])[2]")
	WebElement OrderTotalInCartPage;
	@FindBy(xpath="//div[@class='row no-margin cartTotalVoucherApply']/div[@class='col-xs-6 noPadding price navyBlueVal']")
	WebElement DiscountValue;
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
	@FindBy(xpath="//a[@aria-label='information icon']")
	WebElement VATTooltip;
	@FindBy(xpath="//p[@class='pr-price']")
	WebElement ProductPriceInPDP;

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
	@FindBy(xpath="(//li[@class='pagination-quantity-text'])[1]")
	WebElement TotalNumberOfPages;
	@FindBy(xpath = "(//header[@class='wiley-product-list-component-header']/h1)[1]")
	WebElement FeaturedProducts;

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
	@FindBy(xpath="//div[@id='billingMultiPaymentTitle']")
	WebElement PaymentMethodText;

	//Card information in billing step during checkout

	@FindBy(xpath = "//select[@id='expiryMonth']")
	WebElement ExpirationDateForMonth;
	@FindBy(xpath = "//select[@id='expiryYear']")
	WebElement ExpirationDateForYear;
	@FindBy(xpath = "//input[@id='securityCode']")
	WebElement CVV_Number;
	@FindBy(xpath = "//input[@id='number']")
	WebElement CardNumber;
	@FindBy(xpath="//input[@id='nameOnCard']")
	WebElement CardHolderName;
	
	//New Search Result page
	@FindBy(xpath="//button[@id='nav-products-tab']/span")
	WebElement PlpProductTabNewSearch;
	@FindBy(xpath="//div[@id='nav-tab']/a[contains(text(),'Content')]")
	WebElement PlpContentTabNewSearch;
	@FindBy(xpath="(//div[@class='product-card'])[1]")
	WebElement SRP_WileyProductNewSearchPage;
	@FindBy(xpath="//h2[@class='accordion-header']/button[contains(text(),'format')]")
	WebElement FormatFacetNewSearchPage;
	@FindBy(xpath="//h2[@class='accordion-header']/button[contains(text(),'subjects')]")
	WebElement SubjectFacetNewSearchPage;
	@FindBy(xpath="//h2[@class='accordion-header']/button[contains(text(),'author')]")
	WebElement AuthorFacetNewSearchPage;
	@FindBy(xpath="//h2[@class='accordion-header']/button[contains(text(),'Publish Date')]")
	WebElement PublishedDateFacetNewSearchPage;
	@FindBy(xpath="//h2[@class='accordion-header']/button[contains(text(),'featured')]")
	WebElement FeaturedFacetNewSearchPage;
	@FindBy(xpath="(//ul[@class='ul-text-author facet-options-list virtualised-facet-options-list']/li/label[@class='facet-option-label'])[1]")
	WebElement FirstFacetValueNewSearchPage;
	@FindBy(xpath="(//ul[@class='ul-text-author facet-options-list virtualised-facet-options-list']/li/span[@class='facet-checkbox-wrapper']/label)[1]")
	WebElement FirstFacetItemQuantityNewSearchPage;
	@FindBy(xpath="//span[@class='page-display']")
	WebElement NumberOfProductsAfterFilteringNewSearchPage;
	

	/*
	 * @Date: 04/03/23
	 * @Description: Concatenates the parts of URL (storerfront url with env and the rest of the part specific to some products)
	 */
	public String wileyURLConcatenation(String testCaseNumber, String sheetName, String field) throws IOException{
		try {
			String envURL=excelOperation.getTestData("WILEY_Env_URL", "Generic_Dataset", "Data");
			String pdp=excelOperation.getTestData(testCaseNumber, sheetName,field);
			return "https://"+envURL+"/"+pdp;

		}
		catch(Exception e) {
			Reporting.updateTestReport("Concatenated url was returned", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23 
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
	 * @Date: 04/03/23
	 * @Description: Checks sitemaps in footer
	 */
	public void checkSiteMaponFooter() throws IOException {
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
	 * @Description: Checks sitemaps on pdp page
	 */

	public void checkSiteMaponPDP() throws IOException {
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
	 * @Description: Checks RightsandPermission on pdp page
	 */

	public void checkRighrtAndPermissonsonPDP() throws IOException {
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
	 * @Description: Checks privacy policy on pdp page
	 */
	public void checkPrivacyPolicyonPDP() throws IOException {
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
	 * @Description: Checks terms of use in pdp page
	 */
	public void checkTermsofUseonPDP() throws IOException {
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
	 * @Date: 04/03/23
	 * @Description: Enter the text in text box field
	 * 
	 */
	public void searchTextInSearchBar(String text) throws IOException {
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
	 * @Date: 04/03/23
	 * @Description: Clicks on the About Us link in footer
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 04/03/23
	 * @Description: Clicks on the Add to cart button in the pdp page
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
	 * @Description: Fetch all the search result web element
	 */
	public List<WebElement> getAllFilteredResultProducts(WebDriver driver) throws IOException{
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
	 * @Date: 03/04/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 04/03/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @@Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 03/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23 
	 * @Description:This method is used to enter the FirstName
	 */
	public void enterFirstName(String Fname) throws IOException {
		try {
			AddressFirstName.clear();
			AddressFirstName.sendKeys(Fname);
			Reporting.updateTestReport("First Name was entered successfully on paymentpage",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the First Name with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Date: 04/04/23 
	 * @Description:This method is used to enter the LastName
	 */
	public void enterLastName(String Lname) throws IOException {
		try {
			AddressLastName.clear();
			AddressLastName.sendKeys(Lname);
			Reporting.updateTestReport("Last Name  was entered successfully on paymentpage",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter the Last name with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 04/04/23 
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23 
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
	 * @Date: 04/04/23 
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
	 * @Date: 04/04/23 
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
	 * @Date: 04/04/23
	 * @Description : Entering the CVV Number in payment information step
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
	 * @Date: 04/04/23 
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23 
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
	 * @Date: 04/04/23 
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
	 * @Date: 04/04/23 
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
	 * @Date: 04/04/23 
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
	 * @Description: Concatenates the parts of URL (devmonkey part, storerfront url with env and the rest of the part specific to regions)
	 */
	public String wileyURLConcatenationwithRegions(String region, String pdpURL) throws IOException{
		try {
			String envURL="www.wiley.com/en-"+region;
			Reporting.updateTestReport("Concatenated url was returned as: "+
					"https://"+envURL+"/"+pdpURL, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return "https://"+envURL+"/"+pdpURL;

		}
		catch(Exception e) {
			Reporting.updateTestReport("Concatenated url couldn't be returned", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	/*
	 * @Date: 04/04/23
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
	 * @Date: 4/4/23
	 * @Description: Returns the text shown in the first point upon hovering the generic info VAT Tooltip
	 */
	public String checkVAT_Tooltip(WebDriver driver) throws IOException{
		try {
			Actions action = new Actions(driver);
			action.moveToElement(VATTooltip).perform();
			Thread.sleep(2000);
			String A=VATTooltip.getAttribute("data-content").trim();
			Reporting.updateTestReport("The VAT Tooltip info : "+A+" was returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
			return A;
		}
		catch(Exception e) {
			Reporting.updateTestReport("The VAT Tooltip info waws not present",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
			return "";
		}
	}
	
	//Constructor search result page
	
	/*
	 * @Date: 17/04/23
	 * @Description: This method is verifying the text in PLP page
	 */

	public String checkPlpProductTabNewSearch() throws IOException {
		try {

			String plpproducts = PlpProductTabNewSearch.getText();
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
	 * @Date: 17/04/23
	 * @Description: This method is verifying the text in PLP page
	 */

	public String checkPlpContentTabNewSearch() throws IOException {
		try {

			String plpContents = PlpContentTabNewSearch.getText();
			Reporting.updateTestReport("Content landing page was successfully loaded",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return plpContents;
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to load the Content landing page " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	/*
	 * @Date: 17/04/23
	 * @Description: This method is Clicking on content Section in PDP page
	 */
	public void clickOnContentTabNewSearch() throws IOException {
		try {
			PlpContentTabNewSearch.click();
			Reporting.updateTestReport("Content section was clicked on PDP Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on Content section in PDP page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Date: 03/04/23
	 * @Description: Checks all the products in the search result page
	 */
	public void checkProductsWithSearchedTermNewSearchPage(WebDriver driver,String text) throws IOException {
		try {
			List<WebElement> productList = driver.findElements(By.xpath("//div[@class='product-title']/h3/a"));
			for(WebElement product: productList) {
				if(product.getText().contains(text))
					Reporting.updateTestReport(product.getText()+" --> this product contained the searched term : "+text,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(product.getText()+" --> this product didn't contain the searched term : "+text,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("No products were present in the search result page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
		}
	}
	
	/*
	 * @Date: 03/04/23
	 * @description: This using for Clicking on SRP_WileyProduct
	 */
	public void clickOnSRP_WileyProductNewSearchPage() throws IOException {

		try {
			SRP_WileyProductNewSearchPage.click();
			Reporting.updateTestReport("SRP_WileyProduct was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"SRP_WileyProduct was not Clicked properly with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
	
	/*
	 * @Date: 04/03/23
	 * @Description: Checks if Subject is present under  facet
	 */
	public Boolean checkSubjectFacetNewSearchPage() throws IOException{
		try {
			if(SubjectFacetNewSearchPage.isDisplayed()) {
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
	 * @Date: 04/03/23
	 * @Description: Checks if Author is present under  facet
	 */
	public Boolean checkAuthorFacetNewSearchPage() throws IOException{
		try {
			if(AuthorFacetNewSearchPage.isDisplayed()) {
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
	 * @Date: 04/03/23
	 * @Description: Checks if Format is present under  facet
	 */
	public Boolean checkFormatFacetNewSearchPage() throws IOException{
		try {
			if(FormatFacetNewSearchPage.isDisplayed()) {
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
	 * @Date: 04/03/23
	 * @Description: Checks if PublishedDate is present under  facet
	 */
	public Boolean checkPublishedDateFacetNewSearchPage() throws IOException{
		try {
			if(PublishedDateFacetNewSearchPage.isDisplayed()) {
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
	 * @Date: 04/03/23
	 * @Description: Checks if Brands is present under  facet
	 */
	public Boolean checkFeaturedFacetNewSearchPage() throws IOException{
		try {
			if(FeaturedFacetNewSearchPage.isDisplayed()) {
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
	 * @Date: 04/03/23
	 * @Description: Clicks on the format facet
	 */
	public void clickOnAuthorFacetNewSearchPage() throws IOException{
		try {
			AuthorFacetNewSearchPage.click();
			Reporting.updateTestReport("Author under the facet was clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Author under the facet couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 04/03/23
	 * @Description: Selects First Facet Item
	 */
	public String clickOnFirstFacetValueNewSearchPage() throws IOException{
		try {
			FirstFacetValueNewSearchPage.click();
			Reporting.updateTestReport("First Facet Item was clicked with Text: "
					+FirstFacetValueNewSearchPage.getText()+" and Quantity of that Filter: "+FirstFacetItemQuantityNewSearchPage.getText(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return FirstFacetValueNewSearchPage.getText()+"#"+FirstFacetItemQuantityNewSearchPage.getText();
		}
		catch(Exception e) {
			Reporting.updateTestReport("First Facet Item couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	/*
	 * @Date: 04/03/23
	 * @Description: Selects First Facet Item
	 */
	public String clickOnMaxFacetValueNewSearchPage(WebDriver driver) throws IOException{
		try {
			List<WebElement> quantityList = driver.findElements(By.xpath(
					"//ul[@class='ul-text-author facet-options-list virtualised-facet-options-list']/li/span/label[1]"));
			WebElement maxQuantityAuthor=null;
			WebElement Author=null;
			int max=0;
			for(int i=1;i<=quantityList.size();i++) {
				if(Integer.parseInt(quantityList.get(i-1).getText())>max) {
					max=Integer.parseInt(quantityList.get(i-1).getText());
					Author=driver.findElement(By.xpath("//ul[@class='ul-text-author facet-options-list virtualised-facet-options-list']/li["+i+"]/label"));
					maxQuantityAuthor=driver.findElement(By.xpath("//ul[@class='ul-text-author facet-options-list virtualised-facet-options-list']/li["+i+"]/span/label[1]"));
				}
			}
			String authorName=Author.getText();
			String quantity=maxQuantityAuthor.getText();
			Author.click();
			Reporting.updateTestReport(" Facet Item was clicked with Text: "
					+authorName+" and Quantity of that Filter: "+quantity,
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return authorName+"#"+quantity;
		}
		catch(Exception e) {
			Reporting.updateTestReport("Facet Item couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	/*
	 * @Date: 04/03/23
	 * @Description: Checks number of items after filtering
	 */
	public void checkNumberOfProductsAfterFilteringNewSearchPage(String numbers) throws IOException{
		try {
			String[] texts=NumberOfProductsAfterFilteringNewSearchPage.getText().split(" of ");
			System.out.println(texts.toString()+ texts.length);
			String totalNumber=texts[texts.length-1];
			if(totalNumber.equalsIgnoreCase(numbers)) {
				Reporting.updateTestReport("The number of products after filtering is correctly shown as: "+numbers,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

			}
			else {
				Reporting.updateTestReport("The number of products after filtering is: "+totalNumber+
						" which is not matching with: "+numbers,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("The number of products after filtering couldn't be fetched "+NumberOfProductsAfterFilteringNewSearchPage.getText(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Date: 04/03/23
	 * @Description: Fetches the total number of pages in pagination
	 */
	public int fetchNumberOfPagesAfterFilteringNewSearchPage(WebDriver driver) throws IOException{
		try {
			List<WebElement> pages=driver.findElements(By.xpath("//ul[@class='pagination']/li/a"));
			WebElement totalPage=pages.get(pages.size()-2);
			String page=totalPage.getText();
			Reporting.updateTestReport("Number of total pages: "+page+" was returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return Integer.parseInt(page);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Number of total pages couldn't be returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return 0;
		}
	}

	
}
