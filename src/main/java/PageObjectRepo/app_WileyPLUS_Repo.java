package PageObjectRepo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Test_Suite.WileyPLUS_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

public class app_WileyPLUS_Repo {
	public String SS_path = WileyPLUS_Test_Suite.SS_path;
	
	@FindBy(xpath = "//a[@href='/']/img[@class='brand-logo']")
	WebElement WileyLogo;
	@FindBy(id = "searchbar")
	WebElement HomePageSearchBar;
	@FindBy(xpath="//div[@class='product-date wileyProductDateGroup wileyProductPubDate']")
	WebElement PublicationDateSRP;
	@FindBy(xpath="//div[@class='products-list']/section/div/span[contains(text(),'WileyPLUS')]")
	WebElement WileyPLUSFormatInSRP;
	@FindBy(xpath="//div[@class='wileyProductPriceFormate']")
	WebElement PriceInSRP;
	@FindBy(xpath = "(//div[@class='products-list']//section//div//a//img)//following::div//h3//a")
	WebElement SRP_WileyProduct;
	@FindBy(xpath="//span[@class='item-price item-price-value']")
	WebElement PriceInPDP;
	@FindBy(xpath="//h3[contains(text(),'FORMAT')]")
	WebElement FormatFacet;
	@FindBy(xpath="//div[@data-facet-code='productType']/div/div/span[@class='all js-all-facet-values']/a[contains(text(),'See more')]")
	WebElement SeeMoreLinkUnderFormat;
	@FindBy(xpath="//span[@title='WileyPLUS']")
	WebElement WileyPLUSUnderFormatFacet;
	@FindBy(xpath="//div[@class='applied-facets']/div/span[contains(text(),'WileyPLUS')]")
	WebElement WileyPLUSUnderAppliedFormatFacet;
	@FindBy(xpath="//div[contains(text(),'WileyPLUS')]")
	WebElement WileyPLUSTabPDP;
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
	@FindBy(xpath="//button[contains(text(),'Add to cart')]")
	WebElement AddToCartButton;
	@FindBy(xpath="//button[@class='wileyPlusTabLink wileyPlusActive']")
	WebElement ByDefaultSelecetedWileyPLUSTab;
	@FindBy(xpath="//span[@class='cartItem-brand-blue']")
	WebElement BrandNameOfProduct;
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
	@FindBy(xpath = "//span[contains(text(),'Save and Continue')]")
	WebElement SaveAndContinueButton;
	@FindBy(id = "address.country")
	WebElement SelectCountryDropDown;
	@FindBy(xpath="//span[contains(text(),'Shipping Address')]")
	WebElement ShippingText;
	@FindBy(xpath="//span[text()='Billing Address']")
	WebElement BillingText;
	@FindBy(xpath="//label[@id='sameAsBillingLabel']")
	WebElement ShippingSameAsBillingCheckBox;
	@FindBy(xpath="//a[@class='cartItem-title']")
	WebElement CartItemTitle;
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
	@FindBy(id = "placeOrder")
	WebElement Place_OrderButton;
	@FindBy(xpath = "(//div[@class='orderConfirmationLabelVal textTransCap marginTop10'])[2]")
	WebElement OrderId;
	@FindBy(xpath = "((//div[contains(text(),'Total')])//following::div)[1]")
	WebElement OrderTotalAmount;
	@FindBy(xpath="(//div[contains(text(),'Taxes')]//following::div)[1] ")
	WebElement TotalTax;
	@FindBy(xpath = "//input[@placeholder='Enter your inbox here']")
	WebElement EnterEmailIdInYopmail;	
	@FindBy(xpath = "//button[@title='Check Inbox @yopmail.com']")
	WebElement clickOnCheckInboxButton;
	@FindBy(xpath="(//div[@class='col-xs-9 noPadding orderReviewDetailsLabel'])[1]")
	WebElement ItemTitleOrderReview;
	@FindBy(xpath="//div[@class='col-xs-9 noPadding orderReviewDetailsLabel']")
	WebElement ItemTitleInConfirmationPage;
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
	@FindBy(xpath = "//button[contains(text(),'View Cart')]")
	WebElement ViewCartButton;
	@FindBy(xpath="(//button[@id='continue-shopping-button'])[2]")
	WebElement ContinueShoppingButton;
	@FindBy(xpath="//a[contains(text(),' Create a new account')]")
	WebElement CreateAccountLinkOnboarding;
	@FindBy(id="fname")
	WebElement OnboardingCreateAccountFirstName;
	@FindBy(id="lname")
	WebElement OnboardingCreateAccountLastName;
	@FindBy(id="email")
	WebElement OnboardingEmailId;
	@FindBy(xpath="//label[contains(text(),'Institution')]/following-sibling::div/input")
	WebElement OnboardingCreateAccountInstitution;
	@FindBy(id="filled-adornment-password")
	WebElement OnboardingPassword;
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement OnboardingCreateAccountCheckbox;
	@FindBy(xpath="//span[contains(text(),'Create Account')]")
	WebElement OnboardingCreateAccountButton;
	@FindBy(xpath="//a[contains(text(),'Log in to finish registration')]")
	WebElement FinishRegistrationLinkInMail;
	@FindBy(xpath="//span[contains(text(),'Log in')]")
	WebElement OnboardingLoginButton;
	@FindBy(xpath="//span[contains(text(),'add course')]")
	WebElement OnboardingAddCourseButton;
	@FindBy(id="courseID")
	WebElement CourseSectionId;
	@FindBy(xpath="//button/span[contains(text(),'Continue')]")
	WebElement ContinueButtonInOnboarding;
	@FindBy(xpath="//p[contains(text(),'Purchase access for a single term')]")
	WebElement SingleTermRadioButtonInJoinCourse;
	@FindBy(xpath="(//input[@name='purchaseOption'])[1]")
	WebElement FirstPurchaseOption;
	@FindBy(xpath="(//span[contains(text(),'continue to checkout')])[1]")
	WebElement ContinueToCheckoutInOnboarding;
	
	
	//Description: Concatenates the url with username, password and the env
	public String wileyURLConcatenation(String testCaseNumber, String sheetName, String field) throws IOException{
		try {
			String userNamePassword=excelOperation.getTestData("WILEY_Username_Password", "Generic_Dataset", "Data");
			String envURL=excelOperation.getTestData("WILEY_Env_URL", "Generic_Dataset", "Data");
			String pdp=excelOperation.getTestData(testCaseNumber, sheetName,field);
			return "https://"+userNamePassword+"@"+envURL+"/"+pdp;
			
		}
		catch(Exception e) {
			Reporting.updateTestReport("Concatenated url was returned", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	//Description: Clicks On homepage logo
	public void clickOnHomePage() throws IOException {
		try {
			WileyLogo.click();
			Reporting.updateTestReport("Wiley logo was clicked Successfully", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);

		} catch (Exception e) {
			Reporting.updateTestReport("Failed to click on the Wiley Logo ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);

		}

	}
	
	/*
	 * @Description: Enters data in HomePage search bar
	 */
	public void searchProductInHomePageSearchBar(String data) throws IOException {
		try {
			HomePageSearchBar.sendKeys(data+Keys.ENTER);
			Thread.sleep(2000);
			Reporting.updateTestReport(data + " text seached in the search bar",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Data couldn't be entered in the search bar",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 21/12/22
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
	 * @Date: 21/12/22
	 * @Description: Checks if the WileyPLUS Format is present or not in the Search Result Page of a WileyPLUS product
	 */
	public void checkWileyPLUSFormatInSRP_PLP() throws IOException{
		try {
			if(WileyPLUSFormatInSRP.isDisplayed())
				Reporting.updateTestReport("WileyPLUS Format was displayed in the Search Result Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("WileyPLUS Format was not displayed in the Search Result Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("WileyPLUS Format was not displayed in the Search Result Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 21/12/22
	 * @Description: Checks if the Lowest price of the base product variant is present or not in the Search Result Page of a WileyPLUS product
	 */
	public String checkPriceInSRP_PLP() throws IOException{
		try {
			if(PriceInSRP.isDisplayed())
				{String[] A=PriceInSRP.getText().split(" ");
				String price=A[1].trim();
				Reporting.updateTestReport("Lowest price of the base product variant: "+price+" was displayed in the Search Result Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return price;}
			else
				{Reporting.updateTestReport("Lowest price of the base product variant was not displayed in the Search Result Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Lowest price of the base product variant was not displayed in the Search Result Page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	/*
	 * @Date: 22/12/22
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
	 * @Date: 22/12/22
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
	 * @Date: 22/12/22
	 * @description: This using for Clicking on SRP_WileyProduct
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
	 * @Date: 22/12/22
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
	 * @Description: Clicks on the format facet
	 * @Date: 23/12/22
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
	 * @Description: Clicks on the See more link under the format facet
	 * @Date: 23/12/22
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
	 * @Description: Checks if WileyPLUS is present under format facet
	 * @Date: 23/12/22
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
	 * @Description: Clicks on the WileyPLUS format under facet
	 * @Date: 23/12/22
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
	 * @Description: Checks if WileyPLUS is present under applied format facet
	 * @Date: 23/12/22
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
	 * @Date: 23/12/22
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
	 * @Description: Clicks on the WileyPLUS tab in PDP
	 * @Date: 23/12/22
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
	 * @Date: 23/12/22
	 * @Description: Checks if WileyPLUS tab is present in PDP
	 */
	public Boolean checkSingleTermWileyPLUSTab() throws IOException{
		try {
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
	 * @Date: 23/12/22
	 * @Description: Checks if WileyPLUS tab is present in PDP
	 */
	public Boolean checkMultipleTermsWileyPLUSTab() throws IOException{
		try {
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
	 * @Date: 27/12/22
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
	 * @Date: 27/12/22
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
	 * @Date:27/12/22
	 * @Description: Verfy the Grey box in WileyPLUS PDP
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
	 * @Date: 27/12/22
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
	 * @Date: 27/12/22
	 * @Description: Checks if Single Term WileyPLUS Text is present  
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
	 * @Date: 27/12/22
	 * @Description: Checks if Multiple Term WileyPLUS Text is present  
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
	 * @Date: 27/12/22
	 * @Description: Fetches the percentage from Multiterm page
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
	 * @Date: 27/12/22
	 * @Description: Fetches the Multiple Term access price from Multiterm page
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
	 * @Date: 27/12/22
	 * @Description: Fetches the Single Term access price from Single term page
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
	 * @Date: 29/12/22
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
	 * @Date: 29/12/22
	 * @Description: Checks if the Multi term button is set as default
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
	 * @Date: 29/12/22
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
	 * @Date: 29/12/22
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
	 * @Date: 29/12/22
	 * @Description: Checks if the brand of the product is WileyPLUS 
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
	 * @Date: 02/02/23
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
	 * @Description: Enters the email id in Create account form
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
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
	 * @Date: 2/1/23
	 * @Description: Checks if user is inthe shipping step or not
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
	 * @Date: 2/1/23
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
	 * @Date: 02/01/23
	 * @Description: Clicks on the billing same as shipping address checkbox
	 */
	public void clickOnShippingSameAsBillingCheckBox() throws IOException{
		try {
			ShippingSameAsBillingCheckBox.click();
			Reporting.updateTestReport("Billing same as shipping address checkbox was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Billing same as shipping address checkbox couldn't be clicked ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 2/1/23
	 * @Description: Checks if the course name is present in the cart page or not
	 */
	public void checkCourseNameInCartPage(String courseName) throws IOException{
		try {
			String title=CartItemTitle.getText();
			if(title.equalsIgnoreCase(courseName))
				Reporting.updateTestReport("Course name: "+courseName+" was displayed on the cart page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Course name: "+courseName+" was not same as the title: "+title+" displayed on the cart page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Course name was not displayed on the cart page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
	 * @Description : Entering the CVV Number
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
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
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
	 *@Date: 02/01/23
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
	 * @Date: 02/01/23
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
	 * @Description: Clicks on the Use Selected Address Button in Address Doctor PopUp
	 * @Date: 02/01/23
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
	 * @Description: Returns the Billing address doctor pop up button 
	 * @Date: 02/01/23
	 */
	public WebElement returnUseSelectedBillingAddressButtonAddressDoctorPopUp() {
		return UseSelectedBillingAddressButtonAddressDoctorPopUp;
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the Place Order button 
	 * @Date: 02/01/23
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
     * @Date: 02/01/23
     * @Description:This method is used for fetching the OrderId
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
	 * @Description: Fetches the Order Total
	 * @Date: 02/01/23
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
	 * @Date: 02/01/23
	 * @Description : This Method using for TaxAmount Fetching From Order Confirmation page
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
	 * @Description: Enters the email id in Yopmail
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
	 * @Description: Clicks on check inbox in yopmail after entering user id
	 *@Date: 02/01/23
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
	 * @Date: 2/1/23
	 * @Description: Checks if the course name is present in the Order Review page or not
	 */
	public void checkCourseNameInOrderReviewPage(String courseName) throws IOException{
		try {
			String title=ItemTitleOrderReview.getText();
			String correctTitle=title.substring(0, title.length()-1);
			if(correctTitle.equalsIgnoreCase(courseName))
				Reporting.updateTestReport("Course name: "+courseName+" was displayed on the Order Review page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Course name: "+courseName+" was not same as the title: "+correctTitle+" displayed on the Order Review page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Course name was not displayed on the Order Review page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 2/1/23
	 * @Description: Checks if the course name is present in the Order Confirmation page or not
	 */
	public void checkCourseNameInOrderConfirmationPage(String courseName) throws IOException{
		try {
			String title=ItemTitleInConfirmationPage.getText();
			if(title.equalsIgnoreCase(courseName))
				Reporting.updateTestReport("Course name: "+courseName+" was displayed on the Order Confirmation page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Course name: "+courseName+" was not same as the title: "+title+" displayed on the Order Confirmation page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Course name was not displayed on the Order Confirmation page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 6/1/23
	 * @Description: Checks if global addresses are getting displayed as a saved address or not
	 */
	public void checkGlobalSavedAddress(WebDriver driver, String xpathOfGloablADddress) throws IOException {
		try {
			driver.findElement(By.xpath(xpathOfGloablADddress));
			Reporting.updateTestReport("Global saved address was getting displayed", 
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Global saved address couldn't be validated",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 6/1/23
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
	 * @Date: 6/1/23
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
	 * @Description: Clicks on the Use Selected Address Button in Address Doctor PopUp
	 * @Date: 6/1/23
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
	 * @Date: 6/1/23
	 * @Description : Postal code updating in shipping page.
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
	 * @Date: 6/1/23
	 * @Description : Entering the City in Shipping page.
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
	 * @Date: 6/1/23
	 * @Description :Selecting country from Dropdpwn in Shipping Page
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
	 * @Description: Returns the Shipping address doctor pop up button 
	 * @Date: 6/1/23
	 */
	public WebElement returnUseSelectedShippingAddressButtonAddressDoctorPopUp() {
		return UseSelectedShippingAddressButtonAddressDoctorPopUp;
	}
	
	/*
	 * @Date: 6/1/23
	 * @Description: Checks if the ISBN is present in PDP or not is present or not
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
	
	/*
	 * @Description: Clicks on the Add to cart button in the pdp page
	 * @Date: 06/01/23
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
	 * @Description: Clicks on the View Cart button in the pop up
	 * @Date: 06/01/23
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
	 * @Description: Clicks on the Continue Shopping Button in the cart page
	 * @Date: 06/01/23
	 */
	public void clickOnContinueShoppingButton() throws IOException{
		try {
			ContinueShoppingButton.click();
			Reporting.updateTestReport("Continue Shopping Button was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Continue Shopping Button couldn't be clicked",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Description: Clicks on the Onboarding Create Account Link 
	 * @Date: 06/01/23
	 */
	public void clickOnCreateAccountLinkOnboarding() throws IOException{
		try {
			CreateAccountLinkOnboarding.click();
			Reporting.updateTestReport("Onboarding Create Account Link was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Onboarding Create Account Link couldn't be clicked",CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 6/1/23
	 * @Description: Enters First name in onboarding create account page
	 */
	public void enterFirstNameInOnboardingCreateAccount(String firstName) throws IOException {
		try {
			OnboardingCreateAccountFirstName.sendKeys(firstName);
			Reporting.updateTestReport("First name: "+firstName+" was entered in onboarding create account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("First name couldn't be entered in onboarding create account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 6/1/23
	 * @Description: Enters Last name in onboarding create account page
	 */
	public void enterLastNameInOnboardingCreateAccount(String lastName) throws IOException {
		try {
			OnboardingCreateAccountLastName.sendKeys(lastName);
			Reporting.updateTestReport("Last name: "+lastName+" was entered in onboarding create account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Last name couldn't be entered in onboarding create account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 6/1/23
	 * @Description: Enters Email Id in onboarding create account page
	 */
	public String enterEmailIdInOnboardingCreateAccount() throws IOException {
		try {
			String dateTime= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			String emailId="onboardinguser"+dateTime+"@yopmail.com";
			OnboardingEmailId.sendKeys(emailId);
			Reporting.updateTestReport("Email Id: "+emailId+" was entered in onboarding create account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			return emailId;
		}
		catch(Exception e) {
			Reporting.updateTestReport("Email Id couldn't be entered in onboarding create account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return "";
		}
	}
	
	/*
	 * @Date: 6/1/23
	 * @Description: Enters Institution name in onboarding create account page
	 */
	public void enterInstitutionNameInOnboardingCreateAccount(WebDriver driver,String institution) throws IOException {
		try {
			OnboardingCreateAccountInstitution.sendKeys(institution);
			Thread.sleep(3000);
			Actions at = new Actions(driver);
			at.sendKeys(Keys.PAGE_DOWN).build().perform();
			at.sendKeys(Keys.ENTER).build().perform();
			Reporting.updateTestReport("Institution: "+OnboardingCreateAccountInstitution.getAttribute("value")+" was entered in onboarding create account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Institution couldn't be entered in onboarding create account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 6/1/23
	 * @Description: Enters password in onboarding create account page
	 */
	public void enterPasswordInOnboarding(String password) throws IOException {
		try {
			OnboardingPassword.sendKeys(password);
			Reporting.updateTestReport("Password: "+password+" was entered in onboarding page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Password couldn't be entered in onboarding page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 6/1/23
	 * @Description: Clicks on the privacy policy checkbox on onboarding create account page
	 */
	public void clickOnOnboardingCreateAccountCheckbox() throws IOException{
		try {
			OnboardingCreateAccountCheckbox.click();
			Reporting.updateTestReport("Privacy policy checkbox was clicked in onboarding create account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Privacy policy checkbox couldn't be clicked in onboarding create account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 6/1/23
	 * @Description: Clicks on the Onboarding Create Account Button on onboarding create account page
	 */
	public void clickOnOnboardingCreateAccountButton() throws IOException{
		try {
			OnboardingCreateAccountButton.click();
			Reporting.updateTestReport("Create Account Button was clicked in onboarding create account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Create Account Button couldn't be clicked in onboarding create account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 10/01/23
	 * @Description: Clicks on Finish registration link in Onboarding registration mail
	 */
	public void clickOnFinishRegistrationLinkInMail() throws IOException{
		try {
			FinishRegistrationLinkInMail.click();
			Reporting.updateTestReport("Finish registration link was clicked"
					+ " in the onboarding registration mail",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Finish registration link couldn't be clicked"
					+ " in the onboarding registration mail",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 10/1/23
	 * @Description: Enters Email Id in onboarding login page
	 */
	public void enterEmailIdInOnboardingLogin(String emailId) throws IOException {
		try {
			OnboardingEmailId.sendKeys(emailId);
			Reporting.updateTestReport("Email Id: "+emailId+" was entered in onboarding Login page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Email Id couldn't be entered in onboarding Login page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			
		}
	}
	
	/*
	 * @Date: 6/1/23
	 * @Description: Clicks on the Onboarding Login Button on onboarding login page
	 */
	public void clickOnOnboardingLoginButton() throws IOException{
		try {
			OnboardingLoginButton.click();
			Reporting.updateTestReport("Login Button was clicked in onboarding Login page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Login Button couldn't be clicked in onboarding Login page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 6/1/23
	 * @Description: Clicks on the Onboarding Add course Button on onboarding my account page
	 */
	public void clickOnOnboardingAddCourseButton() throws IOException{
		try {
			OnboardingAddCourseButton.click();
			Reporting.updateTestReport("Add Course Button was clicked in onboarding my account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Add Course Button couldn't be clicked in onboarding my account page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 27/1/23
	 * @DEscription: Enters course section id in the add course page
	 */
	public void enterCourseSectionId(String courseId) throws IOException{
		try {
			CourseSectionId.sendKeys(courseId);
			Reporting.updateTestReport(courseId+" was entered as Course Section Id",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport(courseId+" coudn't be entered as Course Section Id",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 27/1/23
	 * @Description: Clicks on continue button on add course page
	 */
	public void clickOnContinueButtonInOnboarding() throws IOException{
		try {
			ContinueButtonInOnboarding.click();
			Reporting.updateTestReport("Continue Button was clicked in onboarding Add Course /Join Course page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Continue Button couldn't be clicked in onboarding Add Course /Join Course page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 27/1/23
	 * @Description: Clicks on Single Term Radio Button In Join Course page
	 */
	public void clickOnSingleTermRadioButtonInJoinCourse() throws IOException{
		try {
			SingleTermRadioButtonInJoinCourse.click();
			Reporting.updateTestReport("Single Term Radio Button was clicked in onboarding Join Course page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Single Term Radio Button couldn't be clicked in onboarding Join Course page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 27/1/23
	 * @Description: Clicks on First purchase option in Purchase options page
	 */
	public void clickOnFirstPurchaseOption() throws IOException{
		try {
			FirstPurchaseOption.click();
			Reporting.updateTestReport("First purchase option was selected in onboarding  Purchase options page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("First purchase option couldn't be selected in onboarding  Purchase options page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 27/1/23
	 * @Description: Clicks on Continue to checkout button in Purchase options page
	 */
	public void clickOnContinueToCheckoutButton() throws IOException{
		try {
			ContinueToCheckoutInOnboarding.click();
			Reporting.updateTestReport("Continue to checkout button was clicked in onboarding  Purchase options page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Continue to checkout button couldn't be clicked in onboarding  Purchase options page",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	
}
