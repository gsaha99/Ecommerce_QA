package PageObjectRepo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestSuite.WileyPLUS_Prod_Test_Suite;
import TestSuite.Wiley_Prod_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class app_WileyPLUS_Repo {

	public String SS_path = WileyPLUS_Prod_Test_Suite.SS_path;

	//Homepage 

	@FindBy(xpath = "//a[@href='/']/img[@class='brand-logo']")
	WebElement WileyLogo;
	@FindBy(id = "searchbar")
	WebElement HomePageSearchBar;

	//Search Result Page

	@FindBy(xpath="//div[@class='product-date wileyProductDateGroup wileyProductPubDate']")
	WebElement PublicationDateSRP;
	@FindBy(xpath="(//span[@class='wileyProductDateGroup wileyProductDateGroupPipe' and contains(text(),'WileyPLUS')])[2]")
	WebElement WileyPLUSFormatInSRP;
	@FindBy(xpath="(//div[@class='wileyProductPriceFormate'])[2]")
	WebElement PriceInSRP;
    @FindBy(xpath = "(//div[@class='products-list']//section//div//a//img)//following::div//h3//a")
	WebElement SRP_WileyProduct;
	@FindBy(xpath="//h3[contains(text(),'FORMAT')]")
	WebElement FormatFacet;
	@FindBy(xpath="//div[@data-facet-code='productType']/div/div/span[@class='all js-all-facet-values']/a[contains(text(),'See more')]")
	WebElement SeeMoreLinkUnderFormat;
	@FindBy(xpath="//span[@title='WileyPLUS']")
	WebElement WileyPLUSUnderFormatFacet;
	@FindBy(xpath="//div[@class='applied-facets']/div/span[contains(text(),'WileyPLUS')]")
	WebElement WileyPLUSUnderAppliedFormatFacet;
	
	

	//Product details page for WileyPLUS products

	@FindBy(xpath="//div[contains(text(),'WileyPLUS')]")
	WebElement WileyPLUSTabPDP;
	@FindBy(xpath="//span[@class='item-price item-price-value']")
	WebElement PriceInPDP;
	@FindBy(xpath="//button[contains(text(),'Single Term')]")
	WebElement SingleTermWileyPLUSTab;
	@FindBy(xpath="//button[contains(text(),'Multiple Terms')]")
	WebElement MultipleTermsWileyPLUSTab;
	@FindBy(xpath="//*[contains(text(),'Standard Pricing')]")
	WebElement StandardPricingText;
	@FindBy(xpath="//h5[contains(text(),\"View your school's pricing \")]")
	WebElement GreyBoxHeaderWileyPLUSPDP;
	@FindBy(xpath="//button[contains(text(),'Log in to WileyPLUS')]")
	WebElement LoginToWileyPLUSButton;
	@FindBy(xpath="//span[text()='Multiple Term Access to WileyPLUS']/parent::span/parent::div/following-sibling::div/following-sibling::div/span[@class='item-price item-price-value']")
	WebElement MultiTermAccessPrice;
	@FindBy(xpath="//span[text()='Single Term Access to WileyPLUS']/parent::span/parent::div/following-sibling::div/following-sibling::div/span[@class='item-price item-price-value']")
	WebElement SingleTermAccessPrice;	
	@FindBy(xpath="//button[@class='wileyPlusTabLink wileyPlusActive']")
	WebElement ByDefaultSelecetedWileyPLUSTab;
	@FindBy(xpath="//p[@class='pr-price']")
	WebElement ProductPriceInPDP;

	//Cart page 

	@FindBy(xpath="//button[contains(text(),'Add to cart')]")
	WebElement AddToCartButton;
	@FindBy(xpath="//span[@class='cartItem-brand-blue']")
	WebElement BrandNameOfProduct;
	@FindBy(xpath = "//button[contains(text(),'View Cart')]")
	WebElement ViewCartButton;
	@FindBy(xpath="(//button[@id='continue-shopping-button'])[2]")
	WebElement ContinueShoppingButton;
	@FindBy(xpath="//div[@class='col-xs-6 noPadding price orderDetailCommonVal']")
	WebElement OrderSubtotalInCartPage;
	@FindBy(xpath="//div[@class='row no-margin cartTotalVoucherApply']/div[@class='col-xs-6 noPadding price navyBlueVal']")
	WebElement DiscountValue;
	@FindBy(xpath="//a[@class='cartItem-title']")
	WebElement CartItemTitle;
	@FindBy(xpath="(//div[@class='productPriceLabel']/span)[1]")
	WebElement FirstProductsPrice;
	@FindBy(xpath="(//div[@class='productPriceLabel']/span)[2]")
	WebElement SecondProductsPrice;

	//Login or Create account page during checkout

	@FindBy(xpath = "(//span[contains(text(),'Proceed to Checkout')])[2]")
	WebElement ProceedToCheckoutButton;
	@FindBy(id="email")
	WebElement EmailIdInCreateAccount;
	@FindBy(xpath="//button/span[text()='CREATE AN ACCOUNT']")
	WebElement CreateAccountButton;
	@FindBy(xpath="//input[@data-input_description='confirmemail']")
	WebElement ConfirmEmailId;
	@FindBy(id="pwd")
	WebElement PasswordInCreateAccount;
	@FindBy(xpath="//div[@class='guestCreateAccountBtnDiv']/button/span[text()='Continue as Guest']")
	WebElement GuestCheckoutButton;

	//Shipping information step during checkout

	@FindBy(xpath = "//span[contains(text(),'Save and Continue')]")
	WebElement SaveAndContinueButton;
	@FindBy(id = "address.country")
	WebElement SelectCountryDropDown;
	@FindBy(xpath="//span[contains(text(),'Shipping Address')]")
	WebElement ShippingText;
	@FindBy(id="line1")
	WebElement ShippingAddressLine1;	
	@FindBy(id="phone")
	WebElement ShippingPhoneNumber;	
	@FindBy(xpath="(//button[@id='wel_use_suggested_address_button']/span[text()='Use Selected Address'])[2]")
	WebElement UseSelectedShippingAddressButtonAddressDoctorPopUp;
	@FindBy(xpath = "(//input[@id='postcode'])[1]")
	WebElement ShippingZIPCode;
	@FindBy(xpath = "(//input[@id='townCity'])[1]")
	WebElement ShippingCity;
	@FindBy(xpath = "(//input[@id='address.region'])[1]")
	WebElement SelectStateDropDown;
	@FindBy(xpath="//span[@class='delivery-item-title deliveryItemTitle' and contains(text(),'Standard Shipping')]/following-sibling::span")
	WebElement StandardShippingCharge;

	//Billing information during checkout

	@FindBy(xpath="//span[text()='Billing Address']")
	WebElement BillingText;
	@FindBy(xpath="//label[@id='sameAsBillingLabel']")
	WebElement ShippingSameAsBillingCheckBox;
	@FindBy(id = "number")
	WebElement CardNumber;
	@FindBy(id="nameOnCard")
	WebElement CardHolderName;
	@FindBy(id = "expiryMonth")
	WebElement ExpirationDateForMonth;
	@FindBy(id = "expiryYear")
	WebElement ExpirationDateForYear;
	@FindBy(id = "securityCode")
	WebElement CVV_Number;
	@FindBy(id="firstName")
	WebElement AddressFirstName;
	@FindBy(id="lastName")
	WebElement AddressLastName;
	@FindBy(id="street1")
	WebElement BillingAddressLine1;
	@FindBy(id="city")
	WebElement CityBilling;
	@FindBy(id="postalCode")
	WebElement BillingZipCode;
	@FindBy(id="phoneNumber")
	WebElement BillingPhoneNumber;	
	@FindBy(xpath="(//button[@id='wel_billing_use_suggested_address_button']/span[text()='Use Selected Address'])[2]")
	WebElement UseSelectedBillingAddressButtonAddressDoctorPopUp;
	
	//New Search Result page
	@FindBy(xpath="(//div[@class='product-dt'])[1]")
	WebElement PublicationDateSRPNewSearchPage;
	@FindBy(xpath="(//div[@class='product-options d-none d-md-block'])[1]")
	WebElement WileyPLUSFormatInSRPNewSearchPage;
	@FindBy(xpath="(//div[@class='product-price'])[1]")
	WebElement PriceInSRPNewSearchPage;
	@FindBy(xpath="(//div[@class='product-card'])[1]")
	WebElement SRP_WileyProductNewSearchPage;
	@FindBy(xpath="//h2[@class='accordion-header']/button[contains(text(),'format')]")
	WebElement FormatFacetNewSearchPage;
	@FindBy(xpath="//label[@for='format|WileyPlus']")
	WebElement WileyPLUSUnderFormatFacetNewSearchPage;
	@FindBy(xpath="//ul[@class='search-controls-removable_filter_pill']/li/span/span[contains(text(),'WileyPlus')]")
	WebElement WileyPLUSUnderAppliedFormatFacetNewSearchPage;

	/*
	 * @Date: 04/04/23
	 * @Description: Concatenates the url with username, password and the environment URL
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
	 * @Date: 04/04/23
	 * @Description: Enters data to be searched in HomePage search bar
	 */
	public void searchProductInHomePageSearchBar(String data) throws IOException {
		try {
			Thread.sleep(1000);
			HomePageSearchBar.sendKeys(data+Keys.ENTER);
			Thread.sleep(1000);
			Reporting.updateTestReport(data + " text seached in the search bar",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Data couldn't be entered in the search bar",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if the publication date is present or not in the Search Result Page of a WileyPLUS product
	 */
	public void checkPublicationDateInSRP_PLP() throws IOException{
		try {
			if(PublicationDateSRP.isDisplayed())
				Reporting.updateTestReport("Publication date: "+PublicationDateSRP.getText() + " was displayed in the Search Result Page WileyPLUS",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Publication date was not displayed in the Search Result Page WileyPLUS",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Publication date was not displayed in the Search Result Page WileyPLUS",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if the WileyPLUS Format is present or not in the Search Result Page of a WileyPLUS product
	 */
	public void checkWileyPLUSFormatInSRP_PLP(WebDriver driver) throws IOException{
		try {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(WileyPLUSFormatInSRP));
				Reporting.updateTestReport("WileyPLUS Format was displayed in the Search Result Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("WileyPLUS Format was not displayed in the Search Result Page "+e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if the Lowest price of the base product variant is present or not in the Search Result Page of a WileyPLUS product
	 */
	public String checkPriceInSRP_PLP(WebDriver driver) throws IOException{
		try {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(PriceInSRP));
			String[] A=PriceInSRP.getText().split(" ");
			String price=A[1].trim();
			Reporting.updateTestReport("Lowest price of the base product variant: "+price+" was displayed in the Search Result Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return price;
		}
		catch(Exception e) {
			Reporting.updateTestReport("Lowest price of the base product variant was not displayed in the Search Result Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Logs out the user from wiley.com through hitting the logout URL
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
	 * @description: Clicks on the first product which came in the search Result
	 */
	public void clickOnSRP_WileyProduct() throws IOException {

		try {
			SRP_WileyProduct.click();
			Reporting.updateTestReport("Search result was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport(
					"Search result was not Clicked properly with the error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if the Lowest price of the base product variant is present or not in the Search Result Page of a WileyPLUS product
	 */
	public String checkPriceInPDP() throws IOException{
		try {
			if(PriceInPDP.isDisplayed())
			{String price=PriceInPDP.getText().trim();
			Reporting.updateTestReport("Lowest price of the base product variant: "+price+" was displayed in the Product Details Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return price;}
			else
			{Reporting.updateTestReport("Lowest price of the base product variant was not displayed in the Product Details Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Lowest price of the base product variant was not displayed in the Product Details Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
	 * @Description: Clicks on the See more link under the format facet
	 */
	public void clickOnSeeMoreLinkUnderFormat() throws IOException{
		try {
			SeeMoreLinkUnderFormat.click();
			SeeMoreLinkUnderFormat.click();
			Reporting.updateTestReport("See more link under the format facet was clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("See more link under the format facet couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if WileyPLUS is present under format facet
	 */
	public Boolean checkWileyPLUSInFormatFacet() throws IOException{
		try {
			if(WileyPLUSUnderFormatFacet.isDisplayed()) {
				Reporting.updateTestReport("WileyPLUS was present under format facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("WileyPLUS was not present under format facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("WileyPLUS was not present under format facet",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Clicks on the WileyPLUS format under facet
	 */
	public void clickOnWileyPLUSInFormatFacet() throws IOException{
		try {
			WileyPLUSUnderFormatFacet.click();
			Reporting.updateTestReport("WileyPLUS under the format facet was clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("WileyPLUS under the format facet couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if WileyPLUS is present under applied format facet
	 */
	public Boolean checkWileyPLUSInAppliedFacet() throws IOException{
		try {
			if(WileyPLUSUnderAppliedFormatFacet.isDisplayed()) {
				Reporting.updateTestReport("WileyPLUS was present under applied format facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("WileyPLUS was not present under applied format facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("WileyPLUS was not present under applied format facet",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if WileyPLUS tab is present in PDP
	 */
	public Boolean checkWileyPLUSTabInPDP() throws IOException{
		try {
			if(WileyPLUSTabPDP.isDisplayed()) {
				Reporting.updateTestReport("WileyPLUS tab was present in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("WileyPLUS was not present in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("WileyPLUS was not present in PDP",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Clicks on the WileyPLUS tab in PDP
	 */
	public void clickOnWileyPLUSTabPDP() throws IOException{
		try {
			WileyPLUSTabPDP.click();
			Reporting.updateTestReport("WileyPLUS tab in PDP was clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("WileyPLUS tab in PDP couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if Single term option is present in the WileeyPLUS tab
	 */
	public Boolean checkSingleTermWileyPLUSTab(WebDriver driver) throws IOException{
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(SingleTermWileyPLUSTab));
			if(SingleTermWileyPLUSTab.isDisplayed()) {
				Reporting.updateTestReport("Single Term WileyPLUS tab was present in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("Single Term WileyPLUS tab was not present in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Single Term WileyPLUS tab was not present in PDP",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if Multiple term option is present in the WileeyPLUS tab
	 */
	public Boolean checkMultipleTermsWileyPLUSTab(WebDriver driver) throws IOException{
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(MultipleTermsWileyPLUSTab));
			if(MultipleTermsWileyPLUSTab.isDisplayed()) {
				Reporting.updateTestReport("Multiple Terms WileyPLUS tab was present in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("Multiple Terms WileyPLUS tab was not present in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Multiple Terms WileyPLUS tab was not present in PDP",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if "Standard Pricing" text is present  in WileyPLUS tab 
	 */
	public Boolean checkStandardPricicngTextWileyPLUSTab() throws IOException{
		try {
			if(StandardPricingText.isDisplayed()) {
				Reporting.updateTestReport("Standard Pricing Text was present in WileyPLUS tab ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
			else {
				Reporting.updateTestReport("Standard Pricing Text was not present in WileyPLUS tab",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Standard Pricing Text was not present in WileyPLUS tab",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return true;
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Clicks on Single term WileyPLUS button
	 */
	public void clickOnSingleTermWileyPLUSButton() throws IOException{
		try {
			SingleTermWileyPLUSTab.click();
			Reporting.updateTestReport("Single term WileyPLUS button was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Single term WileyPLUS button was not clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Verify the Grey box in WileyPLUS PDP containing one message regarding the login to WileyPLUS
	 */
	public void checkGreyBoxWileyPLUSTab(WebDriver driver,String greyBoxText) throws IOException{
		try {
			if(GreyBoxHeaderWileyPLUSPDP.isDisplayed()) 
				Reporting.updateTestReport("The header text: "+GreyBoxHeaderWileyPLUSPDP.getText()+" was present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("The header text in grey box was not present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			try {
				WebElement GreyBoxTextWileyPLUSPDP=driver.findElement(By.xpath("//p[contains(text(),\""+greyBoxText+"\")]"));
				if(GreyBoxTextWileyPLUSPDP.isDisplayed()) 
					Reporting.updateTestReport("The text: "+GreyBoxTextWileyPLUSPDP.getText()+" was present",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else 
					Reporting.updateTestReport("The text in grey box was not present",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The text in grey box was not present",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("The header text in grey box was not present",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if "Login to WileyPLUS" button is present  in WileyPLUS tab 
	 */
	public void checkLoginToWileyPLUSButton() throws IOException{
		try {
			if(LoginToWileyPLUSButton.isDisplayed()) 
				Reporting.updateTestReport("Login to WileyPLUS  button was present in WileyPLUS tab ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Login to WileyPLUS  button was not present in WileyPLUS tab",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Login to WileyPLUS  button was present in WileyPLUS tab",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if Single Term WileyPLUS Text (Message regarding the duration of Access) is present  
	 */
	public void checkSingleTermWileyPLUSText(WebDriver driver,String singleTermText) throws IOException{
		try {
			WebElement SingleTermWileyPLUSText=driver.findElement(By.xpath("//p[contains(text(),'"
					+singleTermText+"')]"));
			if(SingleTermWileyPLUSText.isDisplayed()) 
				Reporting.updateTestReport("Single Term WileyPLUS Text: "+SingleTermWileyPLUSText.getText()+" was present in WileyPLUS tab ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Single Term WileyPLUS Text was not present in WileyPLUS tab",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Single Term WileyPLUS Text was not present in WileyPLUS tab",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if Multiple Term WileyPLUS Text (Message regarding the duration of Access and savings as compared to Single term) is present  
	 */
	public void checkMultipleTermWileyPLUSText(WebDriver driver,String multiTermText) throws IOException{
		try {
			WebElement MultipleTermWileyPLUSText=driver.findElement(By.xpath("//p[contains(text(),'"
					+multiTermText+"')]"));
			if(MultipleTermWileyPLUSText.isDisplayed()) 
				Reporting.updateTestReport("Multiple Term WileyPLUS Text: "+MultipleTermWileyPLUSText.getText()+" was present in WileyPLUS tab ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else 
				Reporting.updateTestReport("Multiple Term WileyPLUS Text was not present in WileyPLUS tab",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Multiple Term WileyPLUS Text was not present in WileyPLUS tab",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Fetches the percentage from Multiple Term WileyPLUS Text 
	 */
	public String fetchPercentageMultiTerm(WebDriver driver, String multiTermText) throws IOException{
		try {
			WebElement MultipleTermWileyPLUSText=driver.findElement(By.xpath("//p[contains(text(),'"
					+multiTermText+"')]"));
			String multipleTermText=MultipleTermWileyPLUSText.getText();
			String[] A=multipleTermText.split(" ");
			String[] B=A[1].split("%");
			String value=B[0];
			Reporting.updateTestReport("Multiple Term percentage: "+value+" was returned", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return value;
		}
		catch(Exception e){
			Reporting.updateTestReport("Multiple Term percentage couldn't be returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Fetches the price of the Multi term standalone WileyPLUS Product
	 */
	public String fetchMultiTermAccessPrice() throws IOException{
		try {
			String price=MultiTermAccessPrice.getText().substring(1);
			Reporting.updateTestReport("Multiple Term Access standalone price: "+MultiTermAccessPrice.getText()+" was returned", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return price;
		}
		catch(Exception e){
			Reporting.updateTestReport("Multiple Term Access standalone price couldn't be returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Fetches the price of the Single term standalone WileyPLUS Product
	 */
	public String fetchSingleTermAccessPrice() throws IOException{
		try {
			String price=SingleTermAccessPrice.getText().substring(1);
			Reporting.updateTestReport("Single Term Access standalone price: "+SingleTermAccessPrice.getText()+" was returned", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return price;
		}
		catch(Exception e){
			Reporting.updateTestReport("Single Term Access standalone price couldn't be returned",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if the Add to cart button is present or not
	 */
	public boolean checkAddToCartButton() throws IOException{
		try {
			if(AddToCartButton.isDisplayed())
				return true;
			else
				return false;
		}
		catch(Exception e) {
			return false;
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if the Multiple term button is set as default when we open the WileyPLUS tab in Product details page
	 */
	public Boolean checkMultiTermDefault() throws IOException{
		try {
			String selectedTerm=ByDefaultSelecetedWileyPLUSTab.getText();
			if(selectedTerm.equalsIgnoreCase("Multiple Terms"))
				return true;
			else
				return false;
		}
		catch(Exception e) {
			return false;
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Clicks on Login to WileyPLUS button in WileyPLUS PDP
	 */
	public void clickOnLoginToWileyPLUSButton() throws IOException{
		try {
			LoginToWileyPLUSButton.click();
			Reporting.updateTestReport("The Login To WileyPLUS Button was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("The Login To WileyPLUS Button was not clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if user is on the cart page or not
	 */
	public void checkIfUserIsOnCartPage(WebDriver driver) throws IOException{
		try {
			if(driver.getTitle().equalsIgnoreCase("Cart Page | Wiley"))
				Reporting.updateTestReport("User was present on the cart page", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("User was not present on the cart page", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("User was not present on the cart page", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 04/04/23
	 * @Description: Checks if the brand of the product is WileyPLUS in cart page after we add it to cart
	 */
	public void checkBrandNameWileyPLUS() throws IOException{
		try {
			String brandName=BrandNameOfProduct.getText();
			if(brandName.equalsIgnoreCase("WileyPLUS"))
				Reporting.updateTestReport("WileyPLUS product was added to cart through link with its brand name displayed", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("WileyPLUS brand name was not displayed ", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("WileyPLUS brand name was not displayed ", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 04/04/23 
	 * @description: Clicks on the Proceed to checkout button in cart page
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
	 * @Description: Fetches the the order Subtotal from cart page
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
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
	 * @Description: Clicks on the create account button
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
	 * @Description: Enters the email id in Create account form Confirm email id field
	 * @Date: 04/04/23
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
	 * @Description: Enters the password in Create account form 
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
	 * 
	 * @description:This method using for clicking on ADD To CART Button
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
	 * @Description: Clicks on the country drop down in shipping
	 */
	public void checkGlobalCountryList(WebDriver driver,String countryList) throws IOException{
		try {
			//Total 6 countries are present in the datasheet
			String[] countries=countryList.split(",");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			for(String country:countries) {
				try {
					String xpathOfCountry="//option[contains(text(),'"+country+"')]";
					if(driver.findElement(By.xpath(xpathOfCountry)).isDisplayed()) {
						try 
						{
							Select countryDropDown = new Select(SelectCountryDropDown);
							countryDropDown.selectByVisibleText(country);
							/*WebElement selectedOption = countryDropDown.getFirstSelectedOption();
							wait.until(ExpectedConditions.textToBePresentInElement(selectedOption, country));*/
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							Reporting.updateTestReport(country+" was present in the country dropdown and was selected",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						}
						catch(Exception e){
							Reporting.updateTestReport(country+" was present in the country dropdown"
									+ " but couldn't be selected", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					else
						Reporting.updateTestReport(country+" was not present in the country dropdown",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				catch(Exception e) {
					Reporting.updateTestReport("Global country list was not present in the country drop down ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}

		}
		catch(Exception e) {
			Reporting.updateTestReport("Global country list couldn't be validated ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}


	/*
	 * @Date: 04/04/23
	 * @Description: Clicks on the country drop down in billing
	 */
	public void checkGlobalCountryListBilling(WebDriver driver,String countryList) throws IOException{
		try {
			//Total 6 countries are present in the datasheet
			String[] countries=countryList.split(",");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			for(String country:countries) {
				try {
					String xpathOfCountry="//option[contains(text(),'"+country+"')]";
					if(driver.findElement(By.xpath(xpathOfCountry)).isDisplayed()) {
						try 
						{
							Select countryDropDown = new Select(SelectCountryDropDown);
							countryDropDown.selectByVisibleText(country);
							/*WebElement selectedOption = countryDropDown.getFirstSelectedOption();
							wait.until(ExpectedConditions.textToBePresentInElement(selectedOption, country));*/
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
							Reporting.updateTestReport(country+" was present in the country dropdown and was selected",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						}
						catch(Exception e){
							Reporting.updateTestReport(country+" was present in the country dropdown"
									+ " but couldn't be selected", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
						}
					}
					else
						Reporting.updateTestReport(country+" was not present in the country dropdown",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				catch(Exception e) {
					Reporting.updateTestReport("Global country list was not present in the country drop down ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}

		}
		catch(Exception e) {
			Reporting.updateTestReport("Global country list couldn't be validated ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 04/04/23
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
	 * @Date: 04/04/23
	 * @Description: Checks if user is in the shipping step or not
	 */
	public void checkIfUserInShippingStep() throws IOException{
		try {
			if(ShippingText.isDisplayed())
				Reporting.updateTestReport("User was in the shipping information step",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("User was not in the shipping information step",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("User was not in the shipping information step",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 04/4/23
	 * @Description : Select Country From Country drop down in both shipping and billing step during checkout
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
	 * @Description: Checks if user is in the Billing Address step or not
	 */
	public void checkIfUserInBillingStep() throws IOException{
		try {
			if(BillingText.isDisplayed())
				Reporting.updateTestReport("User was in the Billing information step",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("User was not in the Billing information step",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("User was not in the Billing information step",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 04/04/23
	 * @Description: Checks if the ISBN is present in PDP or not 
	 */
	public boolean checkISBN_InPDP(WebDriver driver,String ISBN) throws IOException{
		try {
			String xpathIsbn="//*[contains(text(),'"+ISBN+"')]";
			if(driver.findElement(By.xpath(xpathIsbn)).isDisplayed())
				return true;
			else
				return false;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	//New Search page
	/*
	 * @Date: 20/4/23
	 * @Description: Checks if the publication date is present or not in the Search Result Page of a WileyPLUS product
	 */
	public void checkPublicationDateInSRP_PLPNewSearchPage() throws IOException{
		try {
			if(PublicationDateSRPNewSearchPage.isDisplayed())
				Reporting.updateTestReport("Publication date: "+PublicationDateSRPNewSearchPage.getText() + " was displayed in the Search Result Page WileyPLUS",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Publication date was not displayed in the Search Result Page WileyPLUS",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Publication date was not displayed in the Search Result Page WileyPLUS",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 20/4/23
	 * @Description: Checks if the WileyPLUS Format is present or not in the Search Result Page of a WileyPLUS product
	 */
	public void checkWileyPLUSFormatInSRP_PLPNewSearchPage(WebDriver driver) throws IOException{
		try {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(WileyPLUSFormatInSRPNewSearchPage));
			if(WileyPLUSFormatInSRPNewSearchPage.getText().contains("WileyPLUS"))
				Reporting.updateTestReport("WileyPLUS Format was displayed in the Search Result Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("WileyPLUS Format was not displayed in the Search Result Page ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("WileyPLUS Format was not displayed in the Search Result Page "+e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 20/4/23
	 * @Description: Checks if the Lowest price of the base product variant is present or not in the Search Result Page of a WileyPLUS product
	 */
	public String checkPriceInSRP_PLPNewSearchPage(WebDriver driver) throws IOException{
		try {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(PriceInSRPNewSearchPage));
			String[] A=PriceInSRPNewSearchPage.getText().split(" ");
			String price=A[2].trim();
			Reporting.updateTestReport("Lowest price of the base product variant: "+price+" was displayed in the Search Result Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return price;
		}
		catch(Exception e) {
			Reporting.updateTestReport("Lowest price of the base product variant was not displayed in the Search Result Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	/*
	 * @Date: 20/4/23
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
	 * @Date: 20/4/23
	 * @Description: Clicks on the format facet
	 */
	public void clickOnFormatFacetNewSearchPage() throws IOException{
		try {
			FormatFacetNewSearchPage.click();
			Reporting.updateTestReport("Format under the facet was clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Format under the facet couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 20/4/23
	 * @Description: Clicks on the WileyPLUS format under facet
	 */
	public void clickOnWileyPLUSInFormatFacetNewSearchPage() throws IOException{
		try {
			
			WileyPLUSUnderFormatFacetNewSearchPage.click();
			Reporting.updateTestReport("WileyPLUS under the format facet was clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("WileyPLUS under the format facet couldn't be clicked",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 20/4/23
	 * @Description: Checks if WileyPLUS is present under applied format facet
	 */
	public Boolean checkWileyPLUSInAppliedFacetNewSearchPage() throws IOException{
		try {
			if(WileyPLUSUnderAppliedFormatFacetNewSearchPage.isDisplayed()) {
				Reporting.updateTestReport("WileyPLUS was present under applied format facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return true;
			}
			else {
				Reporting.updateTestReport("WileyPLUS was not present under applied format facet",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("WileyPLUS was not present under applied format facet",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return false;
		}
	}
	
	/*
	 * @Date: 20/4/23
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
	 * @Date: 20/4/23
	 * @Description: Checks if the standard shipping is free for US
	 */
	public boolean validateStandardShippingCharge() throws IOException{
		try {
			String charge=StandardShippingCharge.getText();
			if(charge.equalsIgnoreCase("FREE"))
				return true;
			else
				return false;
		}
		catch(Exception e) {
			Reporting.updateTestReport("Standard Shipping charge couldn't be fetched", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			return false;
		}
	}

}
