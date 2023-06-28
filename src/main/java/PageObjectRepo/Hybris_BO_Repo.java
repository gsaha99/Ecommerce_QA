package PageObjectRepo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

public class Hybris_BO_Repo {
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	
	@FindBy(name="j_username")
	WebElement UserNameHybrisBO;
	@FindBy(name="j_password")
	WebElement PasswordHybrisBO;
	@FindBy(xpath="//button[@type='submit']")
	WebElement HybrisBOLoginButton;
	@FindBy(xpath="//span[text()='Administration']")
	WebElement AdministrationTextAfterLogin;
	@FindBy(xpath="//input[@placeholder='Filter Tree entries']")
	WebElement SearchFieldInHybrisBO;
	@FindBy(xpath="//span[text()='Wiley subscription']")
	WebElement WileySubscriptionField;
	@FindBy(xpath = "//button[@type='button' and text()='Search']")
	WebElement SearchButtonInAdvancedSearch;
	@FindBy(xpath = "(//div[@class=\"z-listcell-content\"]/span[@class=\"yw-listview-cell-label z-label\"])[6]")
	WebElement SecondSearchResult;
	@FindBy(xpath="(//button[@class='yw-expandCollapse z-button'])[1]")
	WebElement TopArrowButtonForExpand;
	
	@FindBy(xpath="//span[@class='ye-default-reference-editor-bandbox ye-default-reference-editor-bandbox-list-collapsed z-bandbox']/input[@class='z-bandbox-input'][1]")
	WebElement CustomerIdField;
	@FindBy(xpath="//div[@class='yw-buttons-container z-div']/button[@class='yw-textsearch-searchbutton y-btn-primary z-button']")
	WebElement SearchButtonBO;
	@FindBy(xpath="((((((((//div[@class='z-listbox-body']))[7])//table//colgroup//col)//following::tbody)[1]//tr//td)//following::td)//div//span)[1]")
	WebElement Subscription;
	@FindBy(xpath="(//input[@class='z-datebox-input'])[2]")
	WebElement ExpiryDateField;
	@FindBy(xpath="(//button[text()='Save'])[1]")
	WebElement SaveButton;
	@FindBy(xpath="//span[text()='CronJobs']")
	WebElement CronJobs;
	@FindBy(xpath="//input[@class='z-bandbox-input z-bandbox-rightedge']")
	WebElement SearchBar;
	@FindBy(xpath="//img[@title='Run CronJob']")
	WebElement RunButton;
	@FindBy(xpath="//button[text()='Yes']")
	WebElement CronjobRunYesButton;
	@FindBy(xpath="//tr[@title='Products']/td/div/div/span")
	WebElement Products;
	@FindBy(xpath="//span[text()='2 items']")
	WebElement SubscriptionItems;
	@FindBy(xpath="//span[text()='subscriptionRenewalCronJob']")
	WebElement RenewalCronjob;
	@FindBy(xpath="(//input[@class='ye-input-text ye-com_hybris_cockpitng_editor_defaulttext z-textbox'])[3]")
	WebElement NewOrderID;
	@FindBy(xpath="//a[@title='Logout']/span/img")
	WebElement LogOutButton;
	@FindBy(xpath = "(//input[@class='ye-input-text ye-com_hybris_cockpitng_editor_defaulttext z-textbox'])[1]")
	WebElement FirstSearchFieldInAdvancedSearch;
	@FindBy(xpath = "(//span[@class='yw-listview-cell-label z-label'])[1]")
	WebElement FirstSearchResult;
	@FindBy(xpath = "//div[@class='yw-caption-container z-caption']/div[@class='z-caption-content']//span[@class='z-label']")
	WebElement OrderStatus;
	@FindBy(xpath = "//input[@placeholder='Filter Tree entries']")
	WebElement BOSearchText;
	@FindBy(xpath = "//select[@class='z-select']")
	WebElement SelectLanguage;

	@FindBy(xpath = "//button[contains(text(),'PROCEED')]")
	WebElement ProceedButton;


	@FindBy(xpath = "//button[@title='Switch search mode']")
	WebElement SwitchSearchMode;

	@FindBy(xpath = "(//input[@type='text'])[19]")
	WebElement OrderIDField;

	@FindBy(xpath = "(//button[contains(text(),'Search')])[2]")
	WebElement SearchButtonBO1;

	@FindBy(xpath = "//span[contains(text(),'SUBSCRIPTION_CREATED')]")
	WebElement OrderIDDetails;

	@FindBy(xpath = "//span[contains(text(),'Payment Captured')]")
	WebElement OrderIDDetails1;	

	@FindBy(xpath = "//span[contains(text(),'Refund Order')]")
	WebElement RefundOrderTab;

	@FindBy(xpath = "(//input[@placeholder='Choose a Reason'])[2]")
	WebElement RefundReasonDropDown;

	@FindBy(xpath = "(//div[@class='z-combobox-popup oms-widget-cancelorder__template-reason z-combobox-open z-combobox-shadow']/ul/li)[6]")
	WebElement RefundReason;

	@FindBy(xpath = "((((//span[contains(text(),'Subtotal')])[2])//following::td)//following::input)[1]")
	WebElement RefundValueField;

	@FindBy(xpath = "//button[contains(text(),'Recalculate Tax')]")
	WebElement RecalculateTax;

	@FindBy(xpath = "//button[contains(text(),'Confirm Selected')]")
	WebElement ConfirmSelectedTab;

	@FindBy(xpath = "//span[contains(text(),'Completed')]")
	WebElement OrderIDDetails2;
	
	@FindBy(xpath = "(((//div[@class='z-listbox-body'])[10]//table//colgroup)//following::tbody)[1]//tr")
	WebElement OrderIDDetails3;


	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	WebElement YesConfirmationButton;

	// **************Renew Subscription

	@FindBy(xpath = "//span[contains(text(),'Wiley subscription')]")
	WebElement WileySubscription;

	@FindBy(xpath = "(//input[@type='text'])[1]")
	WebElement SearchField;

//	@FindBy(xpath = "(//input[@type='text'])[8]")
//	WebElement CustomerEmailField;
	
	@FindBy(xpath="//span[@class='ye-default-reference-editor-bandbox ye-default-reference-editor-bandbox-list-collapsed z-bandbox']/input[@class='z-bandbox-input'][1]")
	WebElement CustomerEmailField;

	@FindBy(xpath = "//span[contains(text(),'yopmail.com')]")
	WebElement OriginalEmailId;


	@FindBy(xpath = "((((((((//div[@class='z-listbox-body']))[7])//table//colgroup//col)//following::tbody)[1]//tr//td)//following::td)//div//span)[1]")
	WebElement OrderDetails;

	@FindBy(xpath = "(//div[@class='z-listbox-body'])[12]//table//tbody//tr/td/div//span")
	WebElement SuggestmailID;

	@FindBy(xpath = "((((//div[@class='ye-validation-panel z-div'])[5]//following::table)[1]//tbody//tr//td//table//tbody)//following::td//td//table//tbody//tr//td//span//input)[1]")
	WebElement ExpiryDate;
	
	@FindBy(xpath = "(//button[contains(text(),'Save')])[1]")
	WebElement SaveButton1;
	
	@FindBy(xpath = "//span[contains(text(),'CronJobs')]")
	WebElement CronJobs1;


	
	//User Details page
	
	@FindBy(xpath="//span[@class='ye-default-reference-editor-selected-item-label z-label']")
	WebElement UserSectionInOrderDetails;
	@FindBy(xpath="//span[@title='firstName']/parent::div/following-sibling::div/input")
	WebElement UserFirstName;
	@FindBy(xpath="//span[@title='lastName']/parent::div/following-sibling::div/input")
	WebElement UserLastName;
	@FindBy(xpath="//span[@title='uid']/parent::div/following-sibling::div/input")
	WebElement UserID;
	
	//Generic elements
	@FindBy(xpath="(//i[@class='z-icon-times'])[1]")
	WebElement CloseBackofficePopUp;
	@FindBy(xpath="//div[@class='z-tabbox-content']")
	WebElement SideScrollBar;
	@FindBy(xpath="(//i[@class='z-icon-chevron-right'])[3]")
	WebElement RightScrollIcon;
	
	//Position and price tab
	@FindBy(xpath="//li[@title='Positions and Prices']/span/div/div/span")
	WebElement PositionAndPricesTab;
	@FindBy(xpath="//span[@title='totalTax']/parent::div/following-sibling::div/input")
	WebElement TotalTax;
	@FindBy(xpath="//span[@title='deliveryCost']/parent::div/following-sibling::div/input")
	WebElement ShippingCost;
	
	//Payment and delivery tab
	@FindBy(xpath="//li[@title='Payment and Delivery']/span/div/div/span")
	WebElement PaymentAndDeliveryTab;
	@FindBy(xpath="//span[@title='paymentAddress']/parent::div/following-sibling::div/div[2]/div/div/table/tbody/tr/td/div/div/span")
	WebElement PaymentAddressSectionInOrderDetails;
	@FindBy(xpath="//span[@title='streetname']/parent::div/following-sibling::div/input")
	WebElement StreetName;
	@FindBy(xpath="//span[@title='postalcode']/parent::div/following-sibling::div/input")
	WebElement PostalCode;
	@FindBy(xpath="//span[@title='town']/parent::div/following-sibling::div/input")
	WebElement Town;
	@FindBy(xpath="//span[@title='country']/parent::div/following-sibling::div/div[2]/div/div/table/tbody/tr/td/div/div/span")
	WebElement Country;
	
	//Administration tab
	@FindBy(xpath="//li[@title='Administration']/span/div/div/span")
	WebElement AdministrationTab;
	@FindBy(xpath="//span[@title='orderProcess']/parent::div/following-sibling::div/div[2]/div/div/table/tbody/tr/td/div/div/span[contains(text(),'WileyOrderProcess')]")
	WebElement WileyOrderProcessSectionInOrderDetails;
	@FindBy(xpath="(//span[@title='taskLogs']/parent::div/following-sibling::div/div[2]/div/div/table/tbody/tr/td/div/div/span)[1]")
	WebElement FirstOrderProcessStep;
	@FindBy(xpath="//span[@title='returnCode']/parent::div/following-sibling::div/input")
	WebElement ReturnCode;
	@FindBy(xpath="//span[@title='logMessages']/parent::div/following-sibling::div/input")
	WebElement LogMessage;
	
	public void enterHybrisBOUserName(String adminUserName) throws IOException{
		try {
			UserNameHybrisBO.sendKeys(adminUserName);
			Reporting.updateTestReport("Hybris username: "+adminUserName+" was entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); 
		}
		catch(Exception e) {
			Reporting.updateTestReport("Hybris username couldn't be entered "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public void enterHybrisBOPassword(String password) throws IOException{
		try {
			PasswordHybrisBO.sendKeys(password);
			Reporting.updateTestReport("Hybris password: "+password+" was entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); 
		}
		catch(Exception e) {
			Reporting.updateTestReport("Hybris password couldn't be entered "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void clickOnHybrisBOLoginButton() throws IOException{
		try {
			HybrisBOLoginButton.click();
			Reporting.updateTestReport("Hybris BO Login button was clicked successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); 
		}
		catch(Exception e) {
			Reporting.updateTestReport("Hybris BO Login button was not clicked "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void checkIfUserLoggedInHybrisBO() throws IOException{
		try {
			AdministrationTextAfterLogin.isDisplayed();
			Reporting.updateTestReport("User was logged in Hybris BO successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("User was not logged in Hybris BO "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void searchFieldInHybrisBO(String field) throws IOException{
		try {
			SearchFieldInHybrisBO.sendKeys(field);
			Reporting.updateTestReport(field+" field was searched in Hybris BO successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("field was not searched in Hybris BO "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	public void clickOnWileySubscriptionField() throws IOException{
		try {
			WileySubscriptionField.click();
			Reporting.updateTestReport("User clicked on Wiley subscription field successfuly ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Wiley subscription field was not clicked "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Enters the customer id in the search for subscription
	 */
	public void enterCustomerIdInHybrisBO(String userId) throws IOException {
		try {
			CustomerIdField.sendKeys(userId);
			Reporting.updateTestReport("Customer id was entered successfuly ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Customer id was not entered "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Clicks on the suggested customer id with name 
	 */
	public void clickOnSuggestedCustomerId() throws IOException {
		try {
			String EmailHybrisBackOffice=excelOperation.getTestData("TC14", "VET_Test_Data", "Email_Id");
			WebDriver driver=DriverModule.getWebDriver();		
			driver.findElement(By.xpath("(//span[contains(text(),'["+EmailHybrisBackOffice+"]')])[2]")).click();
            //driver.findElement(By.xpath("//div[@class='yw-buttons-container z-div']/button[@class='yw-textsearch-searchbutton y-btn-primary z-button']")).click();
			Reporting.updateTestReport("Customer id: "+EmailHybrisBackOffice+" was clicked successfuly ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			Reporting.updateTestReport("Customer id was not clicked "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Added on 22/07/22
	 * @Description: Clicks on the Yellow search button 
	 */
		public void clickOnSearchButtonBO( ) throws IOException {
			try {
				SearchButtonBO.click();
				Reporting.updateTestReport("Search button in BO was clicked ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Search button in BO couldn't be clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		/*
		 * @Author: Anindita
		 * @Added on 08/08/22
		 * @Description: Clicks on the subscription for the user 
		 */	
		public void clickOnSubscription() throws IOException {
			try {
				Subscription.click();
				Reporting.updateTestReport("Subscription for a user in BO was clicked ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Subscription for a user in BO couldn't be clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		/*
		 * @Author: Anindita
		 * @Added on 08/08/22
		 * @Description: Clears the expiration date field in subscription 
		 */	
		public void clearExpirationDateField() throws IOException {
			try {
				ExpiryDateField.clear();
				Reporting.updateTestReport("Expiry Date Field in BO was clear ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Expiry Date Field in BO couldn't be cleared with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		/*
		 * @Author: Anindita
		 * @Added on 08/08/22
		 * @Description: Enters the expiration date in subscription for auto renewal
		 */	
		public void enterExpirationDateField(String date) throws IOException {
			try {
				ExpiryDateField.sendKeys(date);
				Reporting.updateTestReport(date+" was entered in Expiry Date Field ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Expiry Date Field in BO couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		/*
		 * @Author: Anindita
		 * @Added on 08/08/22
		 * @Description: Clicks on the save button for saving the expiry date in subscription
		 */	
		public void clickOnSaveButton() throws IOException {
			try {
				SaveButton.click();
				Reporting.updateTestReport("Save Button was clicked ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Save Button couldn't be clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		/*
		 * @Author: Anindita
		 * @Added on 08/08/22
		 * @Description: Clicks on the Cronjob element in left pane
		 */
		public void clickOnCronjob() throws IOException {
			try {
				CronJobs.click();
				Reporting.updateTestReport("CronJobs was clicked ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("CronJobs couldn't be clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		/*
		 * @Author: Anindita
		 * @Added on 08/08/22
		 * @Description: Enters a value in the search bar
		 */
		public void enterInSearchBar(String value) throws IOException {
			try {
				SearchBar.sendKeys(value);
				Reporting.updateTestReport(value+" was entered in Search Bar ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Search Bar in BO couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		/*
		 * @Author: Anindita
		 * @Added on 08/08/22
		 * @Description: Clears the field in the left pane
		 */
		public void clearFieldInHybrisBO() throws IOException {
			try {
				SearchFieldInHybrisBO.clear();
				Reporting.updateTestReport("Search Field In HybrisBO was cleared ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Search Field In HybrisBO couldn't be cleared with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		/*
		 * @Author: Anindita
		 * @Added on 08/08/22
		 * @Description: Clicks on the run button in the cronjob
		 */
		public void runCronJob()  throws IOException {
			try {
				RunButton.click();
				CronjobRunYesButton.click();
				Reporting.updateTestReport("Cron job was run ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Cronjob couldn't be run with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void clickOnProducts() throws IOException {
			try {
				Products.click();
				Reporting.updateTestReport("Products was clicked ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("Products couldn't be clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		/*
		 * @Author: Anindita
		 * @Added on 08/08/22
		 * @Description: Checks the number of subscription after searching with a customer id
		 */
		public void checkIfTwoItemsDisplayed() throws IOException {
			try {
				if(SubscriptionItems.isDisplayed())
				Reporting.updateTestReport("Two items were displayed ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else Reporting.updateTestReport("Two items were not displayed ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e){
				Reporting.updateTestReport("Two items were not displayed with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		/*
		 * @Author: Anindita
		 * @Added on 08/08/22
		 * @Description: Clicks on the RenewalConjob after searching the job in search bar
		 */
		public void clickOnRenewalCronjob() throws IOException {
			try {
				RenewalCronjob.click();
				Reporting.updateTestReport("RenewalCronjob was clicked ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e){
				Reporting.updateTestReport("RenewalCronjob couldn't be clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		/*
		 * @Author: Anindita
		 * @Added on 08/08/22
		 * @Description: Fetches the order id of a subscription
		 */
		public String getNewSubscriptionOrderId() throws IOException {
			try {
				String newOrder=NewOrderID.getAttribute("value");
				Reporting.updateTestReport("NewOrderID was fetched ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return newOrder;
			}
			catch(Exception e){
				Reporting.updateTestReport("NewOrderID was not fetched with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			}
		}
		/*@Author: Anindita
	     * @Added on 05/09/22
	     * @Description: Returns the XpathOfNewRenewedOrderId
		 */
		public WebElement returnXpathOfNewRenewedOrderId(){
			try {
				return (NewOrderID);
			}
			catch(Exception e) {return null;}
		}
		/*@Author: Anindita
	     * @Added on 12/08/22
	     * @Description: Returns the Subscription element
		 */
		public WebElement returnXpathOfSubscription(){
			try {
				return (Subscription);
			}
			catch(Exception e) {return null;}
		}
		/*@Author: Anindita
	     * @Added on 12/08/22
	     * @Description: Returns the ExpiryDateField element
		 */
		public WebElement returnExpiryDateField(){
			try {
				return (ExpiryDateField);
			}
			catch(Exception e) {return null;}
		}
		
		/*@Author: Anindita
	     * @Added on 12/08/22
	     * @Description: Returns the CronJobs element
		 */
		public WebElement returnCronJobs(){
			try {
				return (CronJobs);
			}
			catch(Exception e) {return null;}
		}
		
		/*@Author: Anindita
	     * @Added on 12/08/22
	     * @Description: Returns the SearchBar element
		 */
		public WebElement returnSearchBar(){
			try {
				return (SearchBar);
			}
			catch(Exception e) {return null;}
		}
		
		/*@Author: Anindita
	     * @Added on 12/08/22
	     * @Description: Returns the RenewalCronjob element
		 */
		public WebElement returnRenewalCronjob(){
			try {
				return (RenewalCronjob);
			}
			catch(Exception e) {return null;}
		}
		
		/*@Author: Anindita
	     * @Added on 12/08/22
	     * @Description: Returns the RunButton element
		 */
		public WebElement returnRunButton(){
			try {
				return (RunButton);
			}
			catch(Exception e) {return null;}
		}
		
		/*@Author: Anindita
	     * @Added on 12/08/22
	     * @Description: Returns the SuggestedCustomerId element
		 */
		public WebElement returnSuggestedCustomerId(){
			try {
				String EmailHybrisBackOffice=excelOperation.getTestData("TC14", "VET_Test_Data", "Email_Id");
				WebDriver driver=DriverModule.getWebDriver();
				WebElement SuggestedCustomerId=driver.findElement(By.xpath("(//span[contains(text(),'["+EmailHybrisBackOffice+"]')])[2]"));
				//WebElement SuggestedCustomerId=driver.findElement(By.xpath("//span[contains(text(),'["+EmailHybrisBackOffice+"]')]"));
				return (SuggestedCustomerId);
			}
			catch(Exception e) {return null;}
		}
		
		
		
		/*@Author: Anindita
	     * @Added on 14/08/22
	     * @Description: Returns the SubscriptionItems element
		 */
		public WebElement returnSubscriptionItems(){
			try {
				return (SubscriptionItems);
			}
			catch(Exception e) {return null;}
		}
		/*@Author: Anindita
	     * @Added on 16/08/22
	     * @Description: Returns the logout button element
		 */
		public WebElement returnLogOutButton(){
			try {
				return (LogOutButton);
			}
			catch(Exception e) {return null;}
		}
		/*@Author: Anindita
	     * @Added on 16/08/22
	     * @Description: Clicks on the logout button element
		 */
		public void clickOnLogOutButton() throws IOException{
			try {
				LogOutButton.click();
				Reporting.updateTestReport("Logout button in BO was successfully clicked ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Logout button in BO couldn't be clicked ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
			}
		}
		/*
		 * @Author: Anindita
		 * @Added on: 31/08/22
		 * @Description: To click on the user while checking the renew subscription 
		 */
		public void clickOnUserIdAfterRenewal() {
			
		}
		
		//Vishnu
		/* 
		 * @Author: Vishnu
		 * 
		 */
		public String OrderStatus() throws IOException {
			try {
				String str = OrderStatus.getText();
				Reporting.updateTestReport("Order Id and Status of the Order fecteched successfully ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				return str;
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to update the Order Id and Status " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return "";
			}
		}

		public void enterValueInAdvancedSearch(String value) throws IOException {
			try {
				FirstSearchFieldInAdvancedSearch.sendKeys(value);
				Reporting.updateTestReport(value+" was entered successfuly in the advanced search", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to enter value in advanced search form " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
	
		public void clickOnSecondSearchResult() throws IOException {
			try {
				SecondSearchResult.click();
				Reporting.updateTestReport("Second search result was clicked successfully", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);

			} catch (Exception e) {
				Reporting.updateTestReport("Failed to Click  the Second search result from Grid " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		}
		
		/*
		 * @Author: Vishnu
		 * 
		 */

		public void OrderSearchInBo() throws IOException {

			try {
				BOSearchText.sendKeys("orders");
				Reporting.updateTestReport("Orders Text entereded successfuly ", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to enter the text " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			}
		}
		
		//Arun
		/*
		 * Author : Arun 
		 * Description : This Flow using for clikcing on CronJobs Option in BackOffice.
		 */
		public void clickOnCronJobs() throws IOException {

			try {
				CronJobs1.click();
				Reporting.updateTestReport("CronJobs has been clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"CronJobs is not clicked properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

		
		/*
		 * Author : Arun 
		 * Description : This Flow using for Save the Data after changed Renewal Date.
		 */
		public void clickOnSaveBtn() throws IOException {

			try {
				SaveButton1.click();
				Reporting.updateTestReport("SaveButton has been clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"SaveButton is not clicked properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

		
		/*
		 * Author : Arun 
		 * Description : This Flow is Clicking on Suggested EmailId after entered UserName.
		 */
		public void clickOnSuggestedmailID() throws IOException {

			try {
				SuggestmailID.click();
				Reporting.updateTestReport("SuggestmailID has been clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"SuggestmailID is not clicked properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

		
		/*
		 * Author : Arun 
		 * Description : This flow Clicking on OrderId to Show UserDetails
		 */
		public void RenewalExpiryDate() throws IOException {

			try {
				ExpiryDate.clear();
				ExpiryDate.sendKeys("Aug 6, 2023 5:29:59 AM");
				Reporting.updateTestReport("", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(" " + e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
		}

		/*
		 * Author : Arun 
		 * Description : This flow Clicking on OrderId to Show UserDetails
		 */
		public void clickOnOrderDetails() throws IOException {

			try {
				OrderDetails.click();
				Reporting.updateTestReport("OrderDetails has been clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"OrderDetails is not clicked properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void clickOnSearchButtonInAdvancedSearch() throws IOException {

			try {
				SearchButtonInAdvancedSearch.click();
				Reporting.updateTestReport("Search Button In Advanced Search was clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Search Button In Advanced Search was not clicked with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

		
		/*
		 * Author : Arun 
		 * Description :This Flow for  Entering the CustomerEmailID after Clicked on WileySubscription 
		 */
		public void enterCustomerEmailID(String customerEmailID) throws IOException {

			try {
				CustomerEmailField.sendKeys(customerEmailID);
				Reporting.updateTestReport("CustomerEmailID: " + customerEmailID + " has been entered successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"CustomerEmailID was not entered properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			}
		}

		/*
		 * Author : Arun 
		 * Description : This Clicking on WileySubscription option after searched with Wiley
		 */
		public void clickOnWileySubscription() throws IOException {

			try {
				WileySubscription.click();
				Reporting.updateTestReport("WileySubscription has been clicked successfully for TaxRefund",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"WileySubscription is not clicked properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

		/*
		 * Author : Arun 
		 * Description : This entering the text in SacrchField.
		 */
		public void enterSearchText(String searchtext) throws IOException {

			try {
				SearchField.clear();
				SearchField.sendKeys(searchtext);
				Reporting.updateTestReport("SearchText has been entered successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"SearchText was not entered properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			}
		}

		/*
		 * Author : Arun 
		 * Description : This Flow is Clicking on YesConfirmation PopUp Button
		 */
		public void clickOnYesConfirmationButton() throws IOException {

			try {
				YesConfirmationButton.click();
				Reporting.updateTestReport("YesConfirmationButton", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"YesConfirmationButton is not clicked properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

		
		/*
		 * Author : Arun 
		 * Description : This Clicking on ConfirmSelectedTab for Refund.
		 */
		public void clickOnConfirmSelectedTab() throws IOException {

			try {
				ConfirmSelectedTab.click();
				Reporting.updateTestReport("ConfirmSelectedTab has been clicked successfully for TaxRefund",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"ConfirmSelectedTab is not clicked properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

		
		/*
		 * Author : Arun 
		 * Description : This Flow using for Clicking on RecalculateTaxTab in BackOffice 
		 */
		public void clickOnRecalculateTaxTab() throws IOException {

			try {
				RecalculateTax.click();
				Reporting.updateTestReport("RecalculateTax has been clicked successfully for TaxRefund",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"RecalculateTax is not clicked properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}


		/*
		 * Author : Arun 
		 * Description : This flow is Selecting Refund Reason for the Customer
		 */
		public void SelectRefundReason() throws IOException {
			try {

				RefundReasonDropDown.click();
				RefundReason.click();

				// Select selRefundReason = new Select(RefundReason);
				// selRefundReason.selectByVisibleText("Good Will");
				// selRefundReason.selectByIndex(3);
				Reporting.updateTestReport("RefundReason has been selected successfully by user",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(" Failed to Select RefundReason " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

		
		/*
		 * Author : Arun 
		 * Description : This flow is Clicking on RefundOrderTab in BackOffice
		 */
		public void clickOnRefundOrderTab() throws IOException {

			try {
				RefundOrderTab.click();
				Reporting.updateTestReport("RefundOrderTab has been clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"RefundOrderTab is not clicked properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

		/*
		 * Author : Arun 
		 * Description : This flow is Clicking the OrderID to showing User Details.
		 */
		public void clickOnOrderID() throws IOException {

			try {
				OrderIDDetails3.click();
			// OrderIDDetails2.click(); // Completed
			//   OrderIDDetails1.click(); // Payment Captured
				Reporting.updateTestReport("OrderID has been clicked successfully for displaying order details",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"OrderID is not clicked properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

		
		/*
		 * Author : Arun 
		 * Description : This Clicking the SearchButton button after entered Order ID
		 */
		public void clickOnSearchBtn() throws IOException {

			try {
				SearchButtonBO1.click();
				Reporting.updateTestReport("SearchButton has been clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"SearchButton is not clicked properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

		/*
		 * Author : Arun 
		 * Description : This Clicking the SearchMode option in the BackOffice
		 */
		public void EnterOrderID(String orderIDField) throws IOException {

			try {
				OrderIDField.sendKeys(orderIDField);
				Reporting.updateTestReport("The orderID: " + orderIDField + " has been entered successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"OrderID is not entered properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			}
		}

		public void clickOnSwitchSearchMode() throws IOException {

			try {
				SwitchSearchMode.click();
				Reporting.updateTestReport("SwitchSearchMode has been clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"SwitchSearchMode is not clicked properly with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

		

		/*
		 * Author : Arun 
		 * Description : This Clicking on ProceedButton in BackOffice
		 */
		public void clickOnProceedButton() throws IOException {

			try {
				ProceedButton.click();
				Reporting.updateTestReport("ProceedButton was clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"ProceedButton was not clicked with the error message " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		public void selectBOLanguage() throws IOException {
			try {
				Select selLanguage = new Select(SelectLanguage);
				selLanguage.selectByVisibleText("English");
				Reporting.updateTestReport("BOLanguage has been selected successfully by user",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport("User failed to select BOLanguage " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		

		public void clickTopArrowButtonForExpand() throws IOException{
			try {
				TopArrowButtonForExpand.click();
				Reporting.updateTestReport("The top arrow up button was clicked successfully to expand the lower order details part",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The top arrow up button couldn't be clicked to expand the lower order details part",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		
		/*
		 * @Description: Clicks on user details button in order details part
		 */
		public void clickOnUserSectionInOrderDetails(WebDriver driver) throws IOException{
			try {
				Actions act = new Actions(driver);
				act.doubleClick(UserSectionInOrderDetails).perform();
				Reporting.updateTestReport("The user details button was clicked successfully in order details part",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The user details button couldn't be clicked in order details part",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Compares the first name entered with the first name in backoffice order section
		 */
		public void checkFirstNameInOrderDetails(String firstName) throws IOException {
			try {
				if(firstName.contentEquals(UserFirstName.getAttribute("value")))
					Reporting.updateTestReport("The first name entered during checkout and the first name"
							+ " saved in backoffice is same: "+firstName, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("The first name entered during checkout: "+firstName+" and the first name"
							+ " saved in backoffice is same: "+UserFirstName.getAttribute("value"), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The first name field in backoffice couldn't be validated", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Compares the last name entered with the last name in backoffice order section
		 */
		public void checkLastNameInOrderDetails(String lastName) throws IOException {
			try {
				if(lastName.contentEquals(UserLastName.getAttribute("value")))
					Reporting.updateTestReport("The last name entered during checkout and the last name"
							+ " saved in backoffice is same: "+lastName, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("The last name entered during checkout: "+lastName+" and the last name"
							+ " saved in backoffice is same: "+UserLastName.getAttribute("value"), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The last name field in backoffice couldn't be validated", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Compares the User ID entered with the User Id in backoffice order section
		 */
		public void checkUserIdInOrderDetails(String id) throws IOException {
			try {
				if(id.contentEquals(UserID.getAttribute("value")))
					Reporting.updateTestReport("The User Id entered during checkout and the User Id"
							+ " saved in backoffice is same: "+id, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("The User Id entered during checkout: "+id+" and the User Id"
							+ " saved in backoffice is same: "+UserID.getAttribute("value"), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The User Id field in backoffice couldn't be validated", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Clicks on the cross icon to close the details page of backoffice
		 */
		public void clickOnCloseBackofficePopUp() throws IOException{
			try {
				CloseBackofficePopUp.click();
				Reporting.updateTestReport("The cross icon was clicked to close the details pop up in backoffice",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The cross icon couldn't be clicked to close the details pop up in backoffice",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Clicks on the positions and prices tab
		 */
		public void clickOnPositionAndPricesTab() throws IOException{
			try {
				PositionAndPricesTab.click();
				Reporting.updateTestReport("Positions and prices tab was clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Positions and prices tab couldn't be clicked",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
		}
		
		/*
		 * @Description: Clicks on the Side Scroll Bar
		 */
		public void clickOnSideScrollBarAndScrollDown(WebDriver driver) throws IOException{
			try {
				SideScrollBar.click();
				Actions at = new Actions(driver);
				at.sendKeys(Keys.END).build().perform();
				Thread.sleep(1000);
				Reporting.updateTestReport("Side Scroll Bar was clicked successfully and the page was scrolled down",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Side Scroll Bar couldn't be clicked and the page couldn't be scrolled down",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
		}
		
		/*
		 * @Description: Compares the tax in prder confirmation with the tax in backoffice order section
		 */
		public void checkTotalTaxInOrderDetails(String tax1) throws IOException {
			try {
				String tax=tax1.substring(1);
				if(tax.contentEquals(TotalTax.getAttribute("value")))
					Reporting.updateTestReport("The total tax in order during checkout and the tax"
							+ " saved in backoffice is same: "+tax, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("The total tax in order during checkout : "+tax+" and the tax"
							+ " saved in backoffice is same: "+TotalTax.getAttribute("value"), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The total tax field in backoffice couldn't be validated", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Compares the shipping cost in order confirmation with the shipping cost in backoffice order section
		 */
		public void checkShippingCostInOrderDetails(String shipping) throws IOException {
			try {
				if (shipping.equalsIgnoreCase(""))
					shipping="0";
				else
					shipping=shipping.substring(1);
				if(shipping.contentEquals(ShippingCost.getAttribute("value")))
					Reporting.updateTestReport("The Shipping Cost in order during checkout and the Shipping Cost"
							+ " saved in backoffice is same: "+shipping, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("The Shipping Cost in order during checkout : "+shipping+" and the Shipping Cost"
							+ " saved in backoffice is same: "+TotalTax.getAttribute("value"), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The ShippingCost field in backoffice couldn't be validated", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Clicks on the Payment And Delivery Tab
		 */
		public void clickOnPaymentAndDeliveryTab() throws IOException{
			try {
				PaymentAndDeliveryTab.click();
				Reporting.updateTestReport("Payment And Delivery tab was clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Payment And Delivery tab couldn't be clicked",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
		}
		
		/*
		 * @Description: Clicks on Payment Address Section In Order Details part
		 */
		public void clickOnPaymentAddressSectionInOrderDetails(WebDriver driver) throws IOException{
			try {
				Actions act = new Actions(driver);
				act.doubleClick(PaymentAddressSectionInOrderDetails).perform();
				Reporting.updateTestReport("The payment address section was clicked successfully in order details part",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The payment address section button couldn't be clicked in order details part",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		
		/*
		 * @Description: Compares the Street Name in order with the street in back office order section
		 */
		public void checkStreetNameInOrderDetails(String street) throws IOException {
			try {
				if(street.contentEquals(StreetName.getAttribute("value")))
					Reporting.updateTestReport("The StreetName order during checkout and the StreetName"
							+ " saved in backoffice is same: "+street, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("The StreetName in order during checkout : "+street+" and the StreetName"
							+ " saved in backoffice is not same: "+StreetName.getAttribute("value"), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The StreetName field in backoffice couldn't be validated", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Compares the Postal Code in order with the Postal Code in back office order section
		 */
		public void checkPostalCodeInOrderDetails(String zip) throws IOException {
			try {
				if(zip.contentEquals(PostalCode.getAttribute("value")))
					Reporting.updateTestReport("The PostalCode order during checkout and the PostalCode"
							+ " saved in backoffice is same: "+zip, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("The PostalCode in order during checkout : "+zip+" and the PostalCode"
							+ " saved in backoffice is not same: "+PostalCode.getAttribute("value"), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The PostalCode field in backoffice couldn't be validated", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Compares the Town in order with the Town in backoffice order section
		 */
		public void checkTownInOrderDetails(String town) throws IOException {
			try {
				if(town.contentEquals(Town.getAttribute("value")))
					Reporting.updateTestReport("The Town in order during checkout and the Town"
							+ " saved in backoffice is same: "+town, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("The Town in order during checkout : "+town+" and the Town"
							+ " saved in backoffice is not same: "+Town.getAttribute("value"), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The Town field in backoffice couldn't be validated", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Compares the country in order with the country in backoffice order section
		 */
		public void checkCountryInOrderDetails(String country) throws IOException {
			try {
				if(Country.getText().contains(country))
					Reporting.updateTestReport("The country in order during checkout and the country"
							+ " saved in backoffice is same: "+country, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("The country in order during checkout : "+country+" and the country"
							+ " saved in backoffice is same: "+Country.getAttribute("value"), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The Country field in backoffice couldn't be validated", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Clicks on the right side scroll icon to the rightest end
		 */
		public void clickOnRightScrollIcon() throws IOException{
			try {
				for(int i=0;i<8;i++) {
					RightScrollIcon.click();
					Thread.sleep(500);
				}
				Reporting.updateTestReport("The right scroll icon was clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The right scroll icon couldn't be clicked ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Clicks on the administration tab
		 */
		public void clickOnAdministrationTab() throws IOException{
			try {
				AdministrationTab.click();
				Reporting.updateTestReport("The Administration Tab was clicked successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The Administration Tab couldn't be clicked ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Clicks on Wiley Order Process Section In Order Details part
		 */
		public void clickOnWileyOrderProcessSectionInOrderDetails(WebDriver driver) throws IOException{
			try {
				Actions act = new Actions(driver);
				act.doubleClick(WileyOrderProcessSectionInOrderDetails).perform();
				Reporting.updateTestReport("The Wiley Order Process Section was clicked successfully in order details part",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The Wiley Order Process Section button couldn't be clicked in order details part",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Clicks on First Order Process Step Section In Order Details part
		 */
		public void clickOnFirstOrderProcessStep(WebDriver driver) throws IOException{
			try {
				Actions act = new Actions(driver);
				act.doubleClick(FirstOrderProcessStep).perform();
				Reporting.updateTestReport("The First Order Process Step section was clicked successfully in order details part",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("The First Order Process Step section button couldn't be clicked in order details part",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Checks if the return code is ok or not
		 */
		public void checkReturnCode(String step) throws IOException {
			try {
				String status=ReturnCode.getAttribute("value");
				if(status.equalsIgnoreCase("OK"))
					Reporting.updateTestReport("The return code was OK for step: "+step,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else {
					Reporting.updateTestReport("The return code was "+status+" for step "+step+" with log message "+LogMessage.getAttribute("value"),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("The return code couldn't be validated for this step"+e.getMessage(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Description: Checks the order process of Wiley orders
		 */
		public void checkWileyOrderProcess(WebDriver driver) throws IOException{
			try {
				
				String[] orderProcessSteps=excelOperation.getTestData("Wiley_Order_Process_Steps", "Generic_Dataset", "Data").split(",");
				System.out.println(orderProcessSteps.length);
				for(int i=0;i<orderProcessSteps.length;i++) {
					List<WebElement> elements=driver.findElements(By.xpath("//div[@class='yw-collection-prev-label z-div']/span[contains(text(),'"+
				orderProcessSteps[i]+"')]"));
					int number=elements.size();
					try {
						driver.findElement(By.xpath("(//div[@class='yw-collection-prev-label z-div']/span[contains(text(),'"+
								orderProcessSteps[i]+"')])["+number+"]")).click();
						Reporting.updateTestReport("The "+orderProcessSteps[i]+" step was clicked successfully in Wiley Order process model",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						checkReturnCode(orderProcessSteps[i]);
					}
					catch(Exception e) {
						Reporting.updateTestReport("The "+orderProcessSteps[i]+" step couldn't be clicked in Wiley Order process model",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("The Wiley Order Process model couldn't be validated for this order "+e.getMessage(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

	}

	




