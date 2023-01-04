package Test_Suite;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import PageObjectRepo.app_WileyPLUS_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class WileyPLUS_Test_Suite extends DriverModule{
	app_WileyPLUS_Repo WileyPLUS;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);

	@BeforeTest
	public void launchBrowser() {
		WileyPLUS = PageFactory.initElements(driver, app_WileyPLUS_Repo.class);
		
	}
	
	/*
	 * @Date: 22/12/22
	 * @Description: Checks the SRP page for WileyPLUS products
	 */
	@Test
	public void TC01_SRP_For_WileyPLUS() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC01_SRP_For_WileyPLUS");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC01", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnHomePage();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, Education and Research')]")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC01", "WileyPLUS_Test_Data", "SearchBox_Text"));
			WileyPLUS.checkPublicationDateInSRP_PLP();
			WileyPLUS.checkWileyPLUSFormatInSRP_PLP();
			String priceInSRP=WileyPLUS.checkPriceInSRP_PLP();
			WileyPLUS.clickOnSRP_WileyProduct();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-add-to-cart']")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Product details page couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			String priceInPDP=WileyPLUS.checkPriceInPDP();
			if(priceInSRP.equalsIgnoreCase(priceInPDP))
				Reporting.updateTestReport("The price in SRP was same as price in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("The price in SRP was not same as price in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e){
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 23/12/22
	 * @Description: Checks the PLP page for WileyPLUS products
	 */
	@Test
	public void TC02_PLP_For_WileyPLUS() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC02_PLP_For_WileyPLUS");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC02", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnHomePage();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, Education and Research')]")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC02", "WileyPLUS_Test_Data", "SearchBox_Text"));
			WileyPLUS.clickOnFormatFacet();
			WileyPLUS.clickOnSeeMoreLinkUnderFormat();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='modal-title']")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Format modal couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			if(WileyPLUS.checkWileyPLUSInFormatFacet())
				WileyPLUS.clickOnWileyPLUSInFormatFacet();
			else
				Reporting.updateTestReport("WileyPLUS was not present under format",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			WileyPLUS.checkWileyPLUSInAppliedFacet();			
			WileyPLUS.checkPublicationDateInSRP_PLP();
			WileyPLUS.checkWileyPLUSFormatInSRP_PLP();
			String priceInSRP=WileyPLUS.checkPriceInSRP_PLP();
			WileyPLUS.clickOnSRP_WileyProduct();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-add-to-cart']")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Product details page couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			String priceInPDP=WileyPLUS.checkPriceInPDP();
			if(priceInSRP.equalsIgnoreCase(priceInPDP))
				Reporting.updateTestReport("The price in PLP was same as price in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("The price in PLP was not same as price in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			
		Thread.sleep(3000);
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 23/12/22
	 * @Description: Checks if we are able to search WileyPLUS EGRIP or not
	 */
	@Test
	public void TC03_EGRIP_Search_For_WileyPLUS() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_EGRIP_Search_For_WileyPLUS");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC03", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnHomePage();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, Education and Research')]")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC03", "WileyPLUS_Test_Data", "SearchBox_Text"));
			WileyPLUS.checkWileyPLUSFormatInSRP_PLP();
			WileyPLUS.clickOnSRP_WileyProduct();
			if(WileyPLUS.checkWileyPLUSTabInPDP()){
				WileyPLUS.clickOnWileyPLUSTabPDP();
			}
			else {
				Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.checkSingleTermWileyPLUSTab();
			WileyPLUS.checkMultipleTermsWileyPLUSTab();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 27/12/22
	 * @Description: Checks if we are able to search WileyPLUS bundle or not
	 */
	@Test
	public void TC04_Bundle_Search_For_WileyPLUS() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC04_Bundle_Search_For_WileyPLUS");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC04", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnHomePage();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, Education and Research')]")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC04", "WileyPLUS_Test_Data", "SearchBox_Text"));
			WileyPLUS.checkWileyPLUSFormatInSRP_PLP();
			WileyPLUS.clickOnSRP_WileyProduct();
			if(WileyPLUS.checkWileyPLUSTabInPDP()){
				WileyPLUS.clickOnWileyPLUSTabPDP();
			}
			else {
				Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.checkSingleTermWileyPLUSTab();
			WileyPLUS.checkMultipleTermsWileyPLUSTab();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 27/12/22
	 * @Description: Verify that When we open a wileyplus product, URL should not getting truncated
	 */
	@Test
	public void TC05_Check_If_URL_Not_Truncated_For_WileyPLUS() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_Check_If_URL_Not_Truncated_For_WileyPLUS");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC05", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnHomePage();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, Education and Research')]")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC05", "WileyPLUS_Test_Data", "SearchBox_Text"));
			WileyPLUS.checkWileyPLUSFormatInSRP_PLP();
			WileyPLUS.clickOnSRP_WileyProduct();
			if(WileyPLUS.checkWileyPLUSTabInPDP()){
				WileyPLUS.clickOnWileyPLUSTabPDP();
			}
			else {
				Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			String url=driver.getCurrentUrl();
			if(url.indexOf(excelOperation.getTestData("TC05", "WileyPLUS_Test_Data", "SearchBox_Text"))!=-1) {
				Reporting.updateTestReport("URL: "+url+" Contains the prouct ISBN and URL is not truncated",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else {
				Reporting.updateTestReport("URL: "+url+" doesn't contain the prouct ISBN and URL is truncated",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 27/12/22
	 * @Description: Verify that WileyPlus option is present in the Facet Format
	 */
	@Test
	public void TC06_Format_Facet_WileyPLUS() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC06_Format_Facet_WileyPLUS");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC06", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnHomePage();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, Education and Research')]")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC06", "WileyPLUS_Test_Data", "SearchBox_Text"));
			WileyPLUS.clickOnFormatFacet();
			WileyPLUS.clickOnSeeMoreLinkUnderFormat();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='modal-title']")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Format modal couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			if(WileyPLUS.checkWileyPLUSInFormatFacet())
				WileyPLUS.clickOnWileyPLUSInFormatFacet();
			else
				Reporting.updateTestReport("WileyPLUS was not present under format",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			WileyPLUS.checkWileyPLUSInAppliedFacet();			
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 27/12/22
	 * @Description: Checks the additional UI Adjustments as per ticket 37115
	 */
	@Test
	public void TC07_Verify_Additional_UI_Adjustments_WileyPLUS_PDP() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC07_Verify_Additional_UI_Adjustments_WileyPLUS_PDP");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC07", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnHomePage();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, Education and Research')]")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC07", "WileyPLUS_Test_Data", "SearchBox_Text"));
			WileyPLUS.checkWileyPLUSFormatInSRP_PLP();
			WileyPLUS.clickOnSRP_WileyProduct();
			if(WileyPLUS.checkWileyPLUSTabInPDP())
				WileyPLUS.clickOnWileyPLUSTabPDP();
			else 
				Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WileyPLUS.checkSingleTermWileyPLUSTab();
			WileyPLUS.checkMultipleTermsWileyPLUSTab();
			WileyPLUS.checkStandardPricicngTextWileyPLUSTab();
			WileyPLUS.checkMultipleTermWileyPLUSText();
			WileyPLUS.clickOnSingleTermWileyPLUSButton();
			WileyPLUS.checkSingleTermWileyPLUSText();
			WileyPLUS.checkStandardPricicngTextWileyPLUSTab();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
			WileyPLUS.checkGreyBoxWileyPLUSTab();
			WileyPLUS.checkLoginToWileyPLUSButton();
			
			
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 27/12/22
	 * @Description: Validate the calculated percentage is displayed correctly in the multiple term text
	 */
	@Test
	public void TC08_Validate_Percentage_In_Multiple_Term() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC08_Validate_Percentage_In_Multiple_Term");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC08", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnHomePage();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, Education and Research')]")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC08", "WileyPLUS_Test_Data", "SearchBox_Text"));
			WileyPLUS.checkWileyPLUSFormatInSRP_PLP();
			WileyPLUS.clickOnSRP_WileyProduct();
			if(WileyPLUS.checkWileyPLUSTabInPDP())
				WileyPLUS.clickOnWileyPLUSTabPDP();
			else 
				Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WileyPLUS.checkSingleTermWileyPLUSTab();
			WileyPLUS.checkMultipleTermsWileyPLUSTab();
			String multiTermPercentage=WileyPLUS.fetchPercentageMultiTerm();
			Double multiTermPrice=Double.valueOf(WileyPLUS.fetchMultiTermAccessPrice());
			WileyPLUS.clickOnSingleTermWileyPLUSButton();
			Double singleTermPrice=Double.valueOf(WileyPLUS.fetchSingleTermAccessPrice());
			int calculatedPercentage=(int)Math.round(((2*singleTermPrice-multiTermPrice)/(2*singleTermPrice))*100);
			String calculatedValue=String.valueOf(calculatedPercentage);
			if(calculatedValue.equalsIgnoreCase(multiTermPercentage))
				Reporting.updateTestReport("The calculated percentage value: "+calculatedValue+" was same as the value shown in Multiple Term page: "+multiTermPercentage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("The calculated percentage value: "+calculatedValue+" was not same as the value shown in Multiple Term page: "+multiTermPercentage,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 29/12/22
	 * @Description: Checks the PDP UI for WileyPLUS
	 */
	@Test
	public void TC09_PDP_UI_For_WileyPLUS() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC09_PDP_UI_For_WileyPLUS");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC09", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnHomePage();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, Education and Research')]")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC09", "WileyPLUS_Test_Data", "SearchBox_Text"));
			WileyPLUS.clickOnSRP_WileyProduct();
			if(WileyPLUS.checkAddToCartButton()) {
				Reporting.updateTestReport("Add to cart button was present in the E-book variant page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else
				Reporting.updateTestReport("Add to cart button was not present in the E-book variant page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			if(WileyPLUS.checkWileyPLUSTabInPDP())
				WileyPLUS.clickOnWileyPLUSTabPDP();
			else 
				Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			if(!WileyPLUS.checkAddToCartButton()) {
				Reporting.updateTestReport("Add to cart button was not present in the WileyPLUS PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			else
				Reporting.updateTestReport("Add to cart button was present in the WileyPLUS PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WileyPLUS.checkSingleTermWileyPLUSTab();
			WileyPLUS.checkMultipleTermsWileyPLUSTab();
			
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 29/12/22
	 * @Description: Checks if the multiterm is set as default after opening the PDP
	 */
	@Test
	public void TC10_WileyPLUS_Set_To_MultiTerm_Default() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC10_WileyPLUS_Set_To_MultiTerm_Default");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC10", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnHomePage();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, Education and Research')]")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC10", "WileyPLUS_Test_Data", "SearchBox_Text"));
			WileyPLUS.clickOnSRP_WileyProduct();
			if(WileyPLUS.checkWileyPLUSTabInPDP())
				WileyPLUS.clickOnWileyPLUSTabPDP();
			else 
				Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WileyPLUS.checkSingleTermWileyPLUSTab();
			WileyPLUS.checkMultipleTermsWileyPLUSTab();
			if(WileyPLUS.checkMultiTermDefault())
				Reporting.updateTestReport("The WileyPLUS Multiple Term was selected by deafult",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("The WileyPLUS Multiple Term was not selected by deafult",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
		/*
		 * @Date: 29/12/22
		 * @Description: Checks if it opens a new tab after clicking on the Log in to WileyPLUS button
		 */
		@Test
		public void TC11_New_Tab_Opened_After_Clicking_Login_To_WileyPLUS_Button() throws IOException{
			try {
				Reporting.test = Reporting.extent.createTest("TC11_New_Tab_Opened_After_Clicking_Login_To_WileyPLUS_Button");
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				driver.get(WileyPLUS.wileyURLConcatenation("TC11", "WileyPLUS_Test_Data", "URL"));
				driver.navigate().refresh();
				WileyPLUS.clickOnHomePage();
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, Education and Research')]")));
				}
				catch(Exception e) {
					Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC11", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.clickOnSRP_WileyProduct();
				if(WileyPLUS.checkWileyPLUSTabInPDP())
					WileyPLUS.clickOnWileyPLUSTabPDP();
				else 
					Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				WileyPLUS.clickOnLoginToWileyPLUSButton();
				try {
					Set<String> allWindowHandles = driver.getWindowHandles();
					java.util.Iterator<String> iterator = allWindowHandles.iterator();

					String storefront = iterator.next();
					String ChildWindow=iterator.next();
					driver.switchTo().window(ChildWindow);
					String title=driver.getTitle();
					if(title.equalsIgnoreCase("WileyPLUS"))
						Reporting.updateTestReport("One new window was opened with title WileyPLUS after clicking on Login to WileyPLUS Button", 
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					driver.switchTo().window(storefront);
					driver.close();
					driver.switchTo().window(ChildWindow);
				}
				catch(Exception e) {
					Reporting.updateTestReport("One new window was not opened after clicking on Login to WileyPLUS Button", 
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				
				
			}
			catch(Exception e) {
				WileyPLUS.wileyLogOutException();
				System.out.println(e.getMessage());
				Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Date: 29/12/22
		 * @Description: Adding WileyPLUS product to cart through link
		 */
		@Test
		public void TC12_Add_WileyPLUS_Product_To_Cart() throws IOException{
			try {
				Reporting.test = Reporting.extent.createTest("TC12_Add_WileyPLUS_Product_To_Cart");
				driver.get(WileyPLUS.wileyURLConcatenation("TC12", "WileyPLUS_Test_Data", "URL"));
				driver.navigate().refresh();
				WileyPLUS.checkIfUserIsOnCartPage(driver);
				WileyPLUS.checkBrandNameWileyPLUS();
				WileyPLUS.WileyLogOut();
			}
			catch(Exception e){
				WileyPLUS.wileyLogOutException();
				System.out.println(e.getMessage());
				Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Date: 02/01/23
		 * @Description: Checks if Global country list is present in shipping step
		 */
		@Test
		public void TC13_Global_Country_List_In_Shipping() throws IOException{
			try {
				Reporting.test = Reporting.extent.createTest("TC13_Global_Country_List_In_Shipping");
				driver.get(WileyPLUS.wileyURLConcatenation("TC13", "WileyPLUS_Test_Data", "URL"));
				driver.navigate().refresh();
				WileyPLUS.checkIfUserIsOnCartPage(driver);
				WileyPLUS.checkBrandNameWileyPLUS();
				ScrollingWebPage.PageScrolldown(driver,0,700);
				WileyPLUS.clickOnProceedToCheckoutButton();
				String email=WileyPLUS.enterEmailIdInCreateAccountForm();
				WileyPLUS.clickOnCreateAccountButton();
				WileyPLUS.confirmEmailIdInCreateAccountForm(email);
				WileyPLUS.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC13", "WileyPLUS_Test_Data", "Password"));
				WileyPLUS.clickOnSaveAndContinueButton();
				WileyPLUS.checkIfUserInShippingStep();
				WileyPLUS.checkGlobalCountryList();
				WileyPLUS.WileyLogOut();
			}
			catch(Exception e) {
				WileyPLUS.wileyLogOutException();
				System.out.println(e.getMessage());
				Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Date: 02/01/23
		 * @Description: Checks if Global country list is present in shipping step
		 */
		@Test
		public void TC14_Global_Country_List_In_Billing() throws IOException{
			try {
				Reporting.test = Reporting.extent.createTest("TC14_Global_Country_List_In_Billing");
				driver.get(WileyPLUS.wileyURLConcatenation("TC14", "WileyPLUS_Test_Data", "URL"));
				driver.navigate().refresh();
				WileyPLUS.checkIfUserIsOnCartPage(driver);
				WileyPLUS.checkBrandNameWileyPLUS();
				ScrollingWebPage.PageScrolldown(driver,0,700);
				WileyPLUS.clickOnProceedToCheckoutButton();
				String email=WileyPLUS.enterEmailIdInCreateAccountForm();
				WileyPLUS.clickOnCreateAccountButton();
				WileyPLUS.confirmEmailIdInCreateAccountForm(email);
				WileyPLUS.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC14", "WileyPLUS_Test_Data", "Password"));
				WileyPLUS.clickOnSaveAndContinueButton();
				WileyPLUS.checkIfUserInBillingStep();
				WileyPLUS.checkGlobalCountryList();
				WileyPLUS.WileyLogOut();
			}
			catch(Exception e) {
				WileyPLUS.wileyLogOutException();
				System.out.println(e.getMessage());
				Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
			}
		}
		
		/*
		 * @Date: 02/01/23
		 * @Description: Checks if Course name sgetting displayed during checkout journey
		 */
		@Test
		public void TC15_Course_Details_During_WileyPLUS_Checkout() throws IOException{
			try {
				Reporting.test = Reporting.extent.createTest("TC15_Course_Details_During_WileyPLUS_Checkout");
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				driver.get(WileyPLUS.wileyURLConcatenation("TC15", "WileyPLUS_Test_Data", "URL"));
				driver.navigate().refresh();
				WileyPLUS.checkIfUserIsOnCartPage(driver);
				WileyPLUS.checkBrandNameWileyPLUS();
				WileyPLUS.checkCourseNameInCartPage(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Course"));
				ScrollingWebPage.PageScrolldown(driver,0,700);
				WileyPLUS.clickOnProceedToCheckoutButton();
				String email=WileyPLUS.enterEmailIdInCreateAccountForm();
				WileyPLUS.clickOnCreateAccountButton();
				WileyPLUS.confirmEmailIdInCreateAccountForm(email);
				WileyPLUS.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Password"));
				WileyPLUS.clickOnSaveAndContinueButton();
				WileyPLUS.checkIfUserInBillingStep();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
				wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
				WileyPLUS.enterCardHolderName(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "First_Name"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
				WileyPLUS.enterCardNumber(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Card_Number"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
				WileyPLUS.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Expiry_Month"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
				WileyPLUS.selectExpirationYearFromDropDown(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Expiry_Year"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
				WileyPLUS.enterCVV_Number(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "CVV"));
				driver.switchTo().defaultContent();			
				WileyPLUS.enterFirstName(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "First_Name"));
				WileyPLUS.enterLastName(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Last_Name"));
				WileyPLUS.selectCountry(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Bill_Country"));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
				WileyPLUS.enterAddressLine1Billing(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Bill_Address_line1"));
				WileyPLUS.enterZipBilling(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Bill_Zip_Code"));
				//wiley.enterCityBilling(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_City"));
				//wiley.enterState(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_State"));
				//wiley.selectUState();
				WileyPLUS.enterPhoneNumberBilling(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Bill_Phone_Number"));
				WileyPLUS.clickOnSaveAndContinueButton();
				try {
				if(WileyPLUS.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed()) 
				{WileyPLUS.clickOnUseSelectedBillingAddressButtonAddressDoctor();
				}}
				catch(Exception e) {}
				WileyPLUS.checkCourseNameInOrderReviewPage(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Course"));
				WileyPLUS.clickOnPlaceOrderButton();
				String orderconfirmation = driver.getTitle();
				if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
					ScrollingWebPage.PageScrolldown(driver,0,300);
					WileyPLUS.checkCourseNameInOrderConfirmationPage(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Course"));
					String orderId = WileyPLUS.fetchOrderId();
					excelOperation.updateTestData("TC15", "WileyPLUS_Test_Data", "Order_Id", orderId);
					excelOperation.updateTestData("TC15", "WileyPLUS_Test_Data", "Email_Id", email);
					ScrollingWebPage.PageScrolldown(driver,0,500);
					String ordertotal = WileyPLUS.fetchOrderTotal();
					String taxamount = WileyPLUS.fetchTaxAmount();
					excelOperation.updateTestData("TC15", "WileyPLUS_Test_Data", "Order_Total", ordertotal);
					excelOperation.updateTestData("TC15", "WileyPLUS_Test_Data", "Tax", taxamount);
					driver.get("https://yopmail.com/en/");

					WileyPLUS.enterEmailIdInYopmail(email);
					WileyPLUS.clickOnCheckInboxButton();
					if(checkIfOrderConfirmationMailReceived()) {
						Reporting.updateTestReport("Order Confirmation mail was received",
		                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						validateOrderConfirmationMailContent(taxamount," ",ordertotal);
					}
					else {
						Reporting.updateTestReport("Order Confirmation mail was not received",
		                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}				
				}			
					
				 else {
					Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
				WileyPLUS.WileyLogOut();
			}
			catch(Exception e) {
				WileyPLUS.wileyLogOutException();
				System.out.println(e.getMessage());
				Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
			}
		}
		/*
		 * @Author: Anindita
		 * @Description: Checks if order confirmation mail was received
		 * @Date: 28/11/22
		 */
		public boolean checkIfOrderConfirmationMailReceived() throws IOException{
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				int timeOutSeconds=60;
				int flag=0;
				WebElement element1 = driver.findElement(By.xpath("//button[@id='refresh']"));
				WebElement element2 = null;

				/* The purpose of this loop is to wait for maximum of 60 seconds */
				for (int i = 0; i < timeOutSeconds / 5; i++) {

					try {
						driver.switchTo().frame("ifinbox");
						element2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button/div[contains(text(),'Your Order with Wiley')]")));

						if(element2.isDisplayed()==true)
						{
							flag=1;
							driver.findElement(By.xpath("//button/div[contains(text(),'Your Order with Wiley')]")).click();
							driver.switchTo().defaultContent();
							break;
						}

					} catch (Exception e) {
						driver.switchTo().defaultContent(); 
						element1.click();
						
					}
				}

				if(flag==1)  return true;
				else return false;
			}
			catch(Exception e) {
				Reporting.updateTestReport("Order Confirmation mail was not received with exception: "+e.getMessage(),
	                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				return false;
			}
			
			
			
			}
		
		/*
		 * @Author: Anindita
		 * @Description: Fetches the mail content of Order Confirmation mail, order total, tax and shipping
		 * @Date: 29/11/22
		 */
		
	public void validateOrderConfirmationMailContent(String tax,String shipping, String total) throws IOException {
		try {
			driver.switchTo().frame("ifmail");
			String orderConfirmationMail=driver.findElement(By.xpath("//div[@id='mail']/pre")).getText();
			
			String[] A=orderConfirmationMail.split("Tax:");
			String[] B=A[1].split("Delivery:");
			String taxInMail=B[0].trim();
			String[] C=B[1].split("TOTAL:");
			String shippingChargeInMail=C[0].trim();
			String[] D=C[1].trim().split(" ");
			String[] E=D[0].trim().split("\\R");
			String orderTotalInMail=E[0].trim();
			System.out.println("Printed Tax: "+taxInMail);
			System.out.println("Printed shippingChargeInMail: "+shippingChargeInMail);
			System.out.println("Printed orderTotalInMail: "+orderTotalInMail);
			//Validation of tax
			if(tax.contentEquals(taxInMail)) 
				Reporting.updateTestReport(taxInMail+" : shown as tax in Order Confirmation mail was same as tax in Order Confirmation page: "+tax,
	                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else{
				BigDecimal taxDouble=new BigDecimal(tax.substring(1));
				BigDecimal taxInMailDouble=new BigDecimal(taxInMail.substring(1));
				if((taxDouble.subtract(taxInMailDouble).compareTo(new BigDecimal("0.01") )==0 ) || (taxDouble.subtract(taxInMailDouble).compareTo(new BigDecimal("-0.01") )==0 )) {
					Reporting.updateTestReport(taxInMail+" : shown as tax in Order Confirmation mail has just 0.01 difference with tax in Order Confirmation page: "+tax,
		                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				else {
					Reporting.updateTestReport(taxInMail+" : shown as tax in Order Confirmation mail was not same as tax in Order Confirmation page: "+tax,
		                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				
			}
			//Validation of shipping
			if(!shipping.contentEquals(" ")) {
				if(shipping.contentEquals(shippingChargeInMail))
					Reporting.updateTestReport(shippingChargeInMail+" : shown as shipping in Order Confirmation mail was same as Shipping charge in Order Confirmation page: "+shipping,
		                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(shippingChargeInMail+" : shown as shipping in Order Confirmation mail was not same as Shipping charge in Order Confirmation page: "+shipping,
		                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			
			//Validation of Order Total
			if(total.contentEquals(orderTotalInMail)) 
				Reporting.updateTestReport(orderTotalInMail+" : shown as tax in Order Confirmation mail was same as tax in Order Confirmation page: "+total,
	                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else{
				BigDecimal totalDouble=new BigDecimal(total.substring(1));
				BigDecimal totalInMailDouble=new BigDecimal(orderTotalInMail.substring(1));
				if((totalDouble.subtract(totalInMailDouble).compareTo(new BigDecimal("0.01") )==0 ) || (totalDouble.subtract(totalInMailDouble).compareTo(new BigDecimal("-0.01") )==0 )) {
					Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail has just 0.01 difference with total in Order Confirmation page: "+total,
		                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				else {
					Reporting.updateTestReport(orderTotalInMail+" : shown as Order total in Order Confirmation mail was not same as total in Order Confirmation page: "+total,
		                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				
			}
			
			driver.switchTo().defaultContent();
			
		}
		catch(Exception e){
			Reporting.updateTestReport("Order total and tax validation couldn't be done: "+e.getMessage(),
                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	}

