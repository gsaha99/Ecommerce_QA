package PageObjectRepo;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Test_Suite.Vet_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.Reporting;
import utilities.StatusDetails;

public class app_Eloqua_Repo {
	Vet_Test_Suite Vet_Test;
	public String SS_path=Vet_Test.SS_path;
	@FindBy(xpath="//input[@id='sitename']")
	WebElement EloquaSiteName;
	@FindBy(xpath="//input[@id='username']")
	WebElement EloquaUserName;
	@FindBy(xpath="//input[@id='password']")
	WebElement EloquaPassword;
	@FindBy(xpath="//button[@id='submitButton']")
	WebElement EloquaLoginButton;
	@FindBy(xpath="//*[@id=\"navigation-list-item-audience\"]/a/span/i")
	WebElement EloquaCustomObject;
	@FindBy(xpath="//div[@title='My Eloqua']")
	WebElement MyEloqua;
	@FindBy(xpath="//ul[@id='navigation-list-item-audience-menu']/li[3]")
	WebElement CustomObjectOnHovering;
	//@FindBy(xpath="//span[@name='RecentItems']/table/tbody/tr[2]/td[3]/a")
	@FindBy(xpath="//*[@id='RecentItems']/table/tbody/tr[2]/td[3]/a/nobr")
	WebElement OnlinePurchase;
	//Anindita on 29/07
	@FindBy(xpath="//form[@name='MainForm']/table/tbody/tr/td/table/tbody/tr/td[2]/table/tbody/tr/td/div/span[@id='MenuReference']")
	WebElement CustomObjectOnlinePurchase;
	//Anindita on 1/8
	@FindBy(xpath="//*[@id=\"SearchForDataCards\"]")
	WebElement SearchOnlinePurchase;
	@FindBy(xpath="//select[@name='DataCardFieldID']")
	WebElement EloquaDropDown;
	@FindBy(xpath="//option[text()='Hybris Order Number']")
	WebElement HybrisOrderIdInEloqua;
	@FindBy(xpath="//div[@id='ValueField']/input")
	WebElement HybrisOrderIdFieldInEloqua;
	@FindBy(xpath="//span[@id='spSearchText']")
	WebElement SearchOrderInEloqua;
	@FindBy(xpath="//form[@name='ActionForm']/table/tbody/tr[2]/td/span[@id='MenuReference']")
	WebElement EloquaOrderDropDown;
	@FindBy(xpath="//*[@id='elqContextMenu1']/span[1]")
	WebElement EloquaEditOrderObject;
	@FindBy(xpath="//form[@name='QuickSearchForm']/input[@id='FormName948']")
	WebElement QuickSearchBox;
	@FindBy(xpath="//td[@id='SearchButton948']/table/tbody/tr/td[2]")
	WebElement QuickSearchButton;
	/*
	 * @Author: Anindita
	 * @Added on 13/07/22
	 * @Description: Enters the Eloqua company name in the login page
	 */
	public void enterEloquaSiteName(String siteName) throws IOException {
		try {
			EloquaSiteName.sendKeys(siteName);
			Reporting.updateTestReport("Eloqua site name: "+siteName+" was entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Eloqua site name couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Added on 13/07/22
	 * @Description: Enters the Eloqua user name in the login page
	 */
	public void enterEloquaUserName(String userName) throws IOException {
		try {
			EloquaUserName.sendKeys(userName);
			Reporting.updateTestReport("Eloqua user name: "+userName+" was entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Eloqua user name couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Added on 13/07/22
	 * @Description: Enters the Eloqua password in the login page
	 */
	public void enterEloquaPassword(String eloquaPassword) throws IOException {
		try {
			EloquaPassword.sendKeys(eloquaPassword);
			Reporting.updateTestReport("Eloqua password: "+eloquaPassword+" was entered successfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Eloqua password couldn't be entered with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Added on 13/07/22
	 * @Description: Clicks on the the Eloqua login button in the login page
	 */
	public void clickOnEloquaLoginButton() throws IOException {
		try {
			EloquaLoginButton.click();
			Reporting.updateTestReport("Eloqua Login Button was successfully clicked ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Eloqua Login Button couldn't be clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Added on 13/07/22
	 * @Description: Checks if user was logged in Eloqua 
	 */
	public void checkIfUserLoggedInEloqua()  throws IOException {
		try {
			if(MyEloqua.isDisplayed()) Reporting.updateTestReport("User was successfully logged in Eloqua ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else Reporting.updateTestReport("User was not logged in Eloqua ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			Reporting.updateTestReport("My Eloqua title was not shown with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Added on 13/07/22
	 * @Description: Clicks on the Eloqua custom object 
	 */
	public void clickOnEloquaCustomObject() throws IOException {
		try {
			EloquaCustomObject.click();
			Reporting.updateTestReport("Eloqua Custom Object was successfully clicked ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Eloqua Custom Object couldn't be clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Added on 13/07/22
	 * @Description: Clicks on the Eloqua custom object 
	 */
	public void clickOnCustomObjectOnHovering() throws IOException {
		try {
			CustomObjectOnHovering.click();
			Reporting.updateTestReport("Custom Object was successfully clicked upon hovering ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Custom Object couldn't be clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Added on 13/07/22
	 * @Description: Clicks on the Online purchase in Eloqua
	 */
	public void clickOnOnlinePurchase( ) throws IOException {
		try {
			OnlinePurchase.click();
			Reporting.updateTestReport("Global Online Purchase button was successfully clicked upon hovering ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Global Online Purchase button couldn't be clicked with error message "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Added on 29/07/22
	 * @Description: Click On Custom Object in OnlinePurchase in Eloqua
	 */
	public void clickOnCustomObjectOnlinePurchase() throws IOException{
		try {
			CustomObjectOnlinePurchase.click();
			Reporting.updateTestReport("Custom Object in Online Purchase was successfully clicked.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Custom Object in Online Purchase was not clicked.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Added on 01/08/22
	 * @Description: Clicks on the search button in Eloqua
	 */
	public void clickOnSearchOnlinePurchase() throws IOException{
		try {
			SearchOnlinePurchase.click();
			Reporting.updateTestReport("Search Online Purchase was successfully clicked.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Search Online Purchase was not clicked.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Added on 01/08/22
	 * @Description: Clicks on the drop down in Eloqua
	 */
	public void clickOnEloquaDropDown() throws IOException{
		try {
			EloquaDropDown.click();
			Reporting.updateTestReport("Eloqua Drop Down was successfully clicked.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Eloqua Drop Down was not clicked.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Added on 01/08/22
	 * @Description: Selects order id in online purchase in Eloqua
	 */
	public void selectHybrisOrderIdInEloqua() throws IOException{
		try {
			HybrisOrderIdInEloqua.click();
			Reporting.updateTestReport("Hybris Order Id In Eloqua was successfully selected.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Hybris Order Id In Eloqua was not selected.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Added on 01/08/22
	 * @Description: Selects order id in online purchase in Eloqua
	 */
	public void enterHybrisOrderIdInEloqua(String orderId) throws IOException{
		try {
			HybrisOrderIdFieldInEloqua.sendKeys(orderId);
			Reporting.updateTestReport("Hybris Order Id In Eloqua was successfully entered as: "+orderId,CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Hybris Order Id In Eloqua was not entered.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Added on 01/08/22
	 * @Description: Searches the order id in online purchase in Eloqua
	 */
	public void clickOnSearchOrderInEloqua() throws IOException{
		try {
			SearchOrderInEloqua.click();
			Reporting.updateTestReport("SearchOrderInEloqua was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Search Order In Eloqua In Eloqua was not clicked.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Added on 01/08/22
	 * @Description: Opens the edit order id tab in online purchase in Eloqua
	 */
    public void EloquaOrderEditObject() throws IOException{
		try {
			EloquaOrderDropDown.click();
			EloquaEditOrderObject.click();
			Thread.sleep(2000);
			Reporting.updateTestReport("Eloqua Edit Order Object was clicked",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("Eloqua Edit Order Object was not clicked.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
    /*
	 * @Author: Anindita
	 * @Added on 01/08/22
	 * @Description: Searches the word Online purchase in eloqua in left pane
	 */
    public void searchEloquaOnlinePurchase() throws IOException{
    	try {
    		QuickSearchBox.sendKeys("online purchase");
    		QuickSearchButton.click();
    		Reporting.updateTestReport("Online purachse was successfully searched",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
    	}
    	catch(Exception e){
			Reporting.updateTestReport("Online purachse was not searched.",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
    }

}
