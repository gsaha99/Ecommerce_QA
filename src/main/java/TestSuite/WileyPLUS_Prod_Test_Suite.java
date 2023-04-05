package TestSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjectRepo.app_WileyPLUS_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.LogTextFile;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class WileyPLUS_Prod_Test_Suite extends DriverModule{
	app_WileyPLUS_Repo WileyPLUS;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	public static String EmailConfirmationText="//button/div[contains(text(),'Your Order with Wiley')]";

	@BeforeTest
	public void launchBrowser() {
		WileyPLUS = PageFactory.initElements(driver, app_WileyPLUS_Repo.class);

	}
	@BeforeMethod
	public void nameBefore(Method method)
	{
		System.out.println("Test case: " + method.getName()+" execution started");       
	}

	@AfterMethod
	public void nameAfter(Method method)
	{
		System.out.println("Test case: " + method.getName()+" execution completed");       
	}
	
	/*
	 * @Date: 4/4/23
	 * @Description: Checks the SRP page for WileyPLUS products
	 */
	@Test
	public void TC01_SRP_For_WileyPLUS() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC01_SRP_For_WileyPLUS");
			LogTextFile.writeTestCaseStatus("TC01_SRP_For_WileyPLUS", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC01", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();

			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC01", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.checkPublicationDateInSRP_PLP();
				WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
				String price=WileyPLUS.checkPriceInSRP_PLP(driver);
				WileyPLUS.clickOnSRP_WileyProduct();
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-add-to-cart']")));
					if(price.equalsIgnoreCase(WileyPLUS.checkPriceInPDP()))
						Reporting.updateTestReport("The price in SRP was same as price in PDP",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport("The price in SRP was not same as price in PDP",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				catch(Exception e) {
					Reporting.updateTestReport("Product details page couldn't be loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}


		}
		catch(Exception e){
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Checks the PLP page for WileyPLUS products
	 */
	@Test
	public void TC02_PLP_For_WileyPLUS() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC02_PLP_For_WileyPLUS");
			LogTextFile.writeTestCaseStatus("TC02_PLP_For_WileyPLUS", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC02", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC02", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.clickOnFormatFacet();
				WileyPLUS.clickOnSeeMoreLinkUnderFormat();
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//div[@class='modal-title']")));
					if(WileyPLUS.checkWileyPLUSInFormatFacet())
						WileyPLUS.clickOnWileyPLUSInFormatFacet();
					else
						Reporting.updateTestReport("WileyPLUS was not present under format",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					WileyPLUS.checkWileyPLUSInAppliedFacet();			
					WileyPLUS.checkPublicationDateInSRP_PLP();
					WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
					String price=WileyPLUS.checkPriceInSRP_PLP(driver);
					WileyPLUS.clickOnSRP_WileyProduct();
					try {
						wait.until(ExpectedConditions.presenceOfElementLocated(
								By.xpath("//div[@class='product-add-to-cart']")));
						if(price.equalsIgnoreCase(WileyPLUS.checkPriceInPDP()))
							Reporting.updateTestReport("The price in PLP was same as price in PDP",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						else
							Reporting.updateTestReport("The price in PLP was not same as price in PDP",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					catch(Exception e) {
						Reporting.updateTestReport("Product details page couldn't be loaded and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Format modal couldn't be loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Checks if we are able to search WileyPLUS EGRIP or not
	 */
	@Test
	public void TC03_EGRIP_Search_For_WileyPLUS() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_EGRIP_Search_For_WileyPLUS");
			LogTextFile.writeTestCaseStatus("TC03_EGRIP_Search_For_WileyPLUS", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC03", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC03", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
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
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Checks if we are able to search WileyPLUS bundle or not
	 */
	@Test
	public void TC04_Bundle_Search_For_WileyPLUS() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC04_Bundle_Search_For_WileyPLUS");
			LogTextFile.writeTestCaseStatus("TC04_Bundle_Search_For_WileyPLUS", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC04", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC04", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
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
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Verify that When we open a wileyplus product, URL should not getting truncated
	 */
	@Test
	public void TC05_Check_If_URL_Not_Truncated_For_WileyPLUS() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_Check_If_URL_Not_Truncated_For_WileyPLUS");
			LogTextFile.writeTestCaseStatus("TC05_Check_If_URL_Not_Truncated_For_WileyPLUS", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC05", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC05", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
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
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Verify that WileyPlus option is present in the Facet Format
	 */
	@Test
	public void TC06_Format_Facet_WileyPLUS() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC06_Format_Facet_WileyPLUS");
			LogTextFile.writeTestCaseStatus("TC06_Format_Facet_WileyPLUS", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC06", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC06", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.clickOnFormatFacet();
				WileyPLUS.clickOnSeeMoreLinkUnderFormat();
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//div[@class='modal-title']")));
					if(WileyPLUS.checkWileyPLUSInFormatFacet())
						WileyPLUS.clickOnWileyPLUSInFormatFacet();
					else
						Reporting.updateTestReport("WileyPLUS was not present under format",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					WileyPLUS.checkWileyPLUSInAppliedFacet();
				}
				catch(Exception e) {
					Reporting.updateTestReport("Format modal couldn't be loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}			
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Checks the additional UI Adjustments as per ticket 37115
	 */
	@Test
	public void TC07_Verify_Additional_UI_Adjustments_WileyPLUS_PDP() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC07_Verify_Additional_UI_Adjustments_WileyPLUS_PDP");
			LogTextFile.writeTestCaseStatus("TC07_Verify_Additional_UI_Adjustments_WileyPLUS_PDP", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC07", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, "
								+ "Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC07", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
				WileyPLUS.clickOnSRP_WileyProduct();
				if(WileyPLUS.checkWileyPLUSTabInPDP()) {
					WileyPLUS.clickOnWileyPLUSTabPDP();
					WileyPLUS.checkSingleTermWileyPLUSTab();
					WileyPLUS.checkMultipleTermsWileyPLUSTab();
					WileyPLUS.checkStandardPricicngTextWileyPLUSTab();
					WileyPLUS.checkMultipleTermWileyPLUSText(driver,excelOperation.getTestData("MultipleTermWileyPLUSText", "Generic_Messages", "Data"));
					WileyPLUS.clickOnSingleTermWileyPLUSButton();
					WileyPLUS.checkSingleTermWileyPLUSText(driver,excelOperation.getTestData("SingleTermWileyPLUSText", "Generic_Messages", "Data"));
					WileyPLUS.checkStandardPricicngTextWileyPLUSTab();
					ScrollingWebPage.PageScrolldown(driver, 0, 500, SS_path);
					WileyPLUS.checkGreyBoxWileyPLUSTab(driver,excelOperation.getTestData("WileyPLUS_Grey_Box_Text", "Generic_Messages", "Data"));
					WileyPLUS.checkLoginToWileyPLUSButton();
				}

				else 
					Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Validate the calculated percentage is displayed correctly in the multiple term text
	 */
	@Test
	public void TC08_Validate_Percentage_In_Multiple_Term() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC08_Validate_Percentage_In_Multiple_Term");
			LogTextFile.writeTestCaseStatus("TC08_Validate_Percentage_In_Multiple_Term", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC08", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC08", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
				WileyPLUS.clickOnSRP_WileyProduct();
				if(WileyPLUS.checkWileyPLUSTabInPDP()) {
					WileyPLUS.clickOnWileyPLUSTabPDP();
					WileyPLUS.checkSingleTermWileyPLUSTab();
					WileyPLUS.checkMultipleTermsWileyPLUSTab();
					String multiTermPercentage=WileyPLUS.fetchPercentageMultiTerm(driver,excelOperation.getTestData("MultipleTermWileyPLUSText", "Generic_Messages", "Data"));
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
				else 
					Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.WileyLogOut();

		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Checks the PDP UI for WileyPLUS
	 */
	@Test
	public void TC09_PDP_UI_For_WileyPLUS() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC09_PDP_UI_For_WileyPLUS");
			LogTextFile.writeTestCaseStatus("TC09_PDP_UI_For_WileyPLUS", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC09", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
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
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Checks if the multiterm is set as default after opening the PDP
	 */
	@Test
	public void TC10_WileyPLUS_Set_To_MultiTerm_Default() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC10_WileyPLUS_Set_To_MultiTerm_Default");
			LogTextFile.writeTestCaseStatus("TC10_WileyPLUS_Set_To_MultiTerm_Default", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC10", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC10", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.clickOnSRP_WileyProduct();
				if(WileyPLUS.checkWileyPLUSTabInPDP()) {
					WileyPLUS.clickOnWileyPLUSTabPDP();
					WileyPLUS.checkSingleTermWileyPLUSTab();
					WileyPLUS.checkMultipleTermsWileyPLUSTab();
					if(WileyPLUS.checkMultiTermDefault())
						Reporting.updateTestReport("The WileyPLUS Multiple Term was selected by deafult",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport("The WileyPLUS Multiple Term was not selected by deafult",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}		
				else 
					Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	/*
	 * @Date: 4/4/23
	 * @Description: Checks if it opens a new tab after clicking on the Log in to WileyPLUS button
	 */
	@Test
	public void TC11_New_Tab_Opened_After_Clicking_Login_To_WileyPLUS_Button() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC11_New_Tab_Opened_After_Clicking_Login_To_WileyPLUS_Button");
			LogTextFile.writeTestCaseStatus("TC11_New_Tab_Opened_After_Clicking_Login_To_WileyPLUS_Button", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC11", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC11", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.clickOnSRP_WileyProduct();
				if(WileyPLUS.checkWileyPLUSTabInPDP()) {
					WileyPLUS.clickOnWileyPLUSTabPDP();
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
				else 
					Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.WileyLogOut();

		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Adding WileyPLUS product to cart through link
	 */
	@Test
	public void TC12_Add_WileyPLUS_Product_To_Cart() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC12_Add_WileyPLUS_Product_To_Cart");
			LogTextFile.writeTestCaseStatus("TC12_Add_WileyPLUS_Product_To_Cart", "Test case");
			driver.get(WileyPLUS.wileyURLConcatenation("TC12", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.checkIfUserIsOnCartPage(driver);
			WileyPLUS.checkBrandNameWileyPLUS();
			WileyPLUS.removeProductsFromCart(driver);
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e){
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Checks if Global country list is present in shipping step
	 */
	@Test
	public void TC13_Global_Country_List_In_Shipping() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC13_Global_Country_List_In_Shipping");
			LogTextFile.writeTestCaseStatus("TC13_Global_Country_List_In_Shipping", "Test case");
			driver.get(WileyPLUS.wileyURLConcatenation("TC13", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.checkIfUserIsOnCartPage(driver);
			WileyPLUS.checkBrandNameWileyPLUS();
			ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
			WileyPLUS.clickOnProceedToCheckoutButton();
			String email=WileyPLUS.enterEmailIdInCreateAccountForm();
			WileyPLUS.clickOnCreateAccountButton();
			WileyPLUS.confirmEmailIdInCreateAccountForm(email);
			WileyPLUS.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC13", "WileyPLUS_Test_Data", "Password"));
			WileyPLUS.clickOnSaveAndContinueButton();
			WileyPLUS.checkIfUserInShippingStep();
			WileyPLUS.checkGlobalCountryList(driver,excelOperation.getTestData("TC13", "WileyPLUS_Test_Data", "Shipping_Country"));
			WileyPLUS.removeProductsFromCart(driver);
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Checks if Global country list is present in shipping step
	 */
	@Test
	public void TC14_Global_Country_List_In_Billing() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC14_Global_Country_List_In_Billing");
			LogTextFile.writeTestCaseStatus("TC14_Global_Country_List_In_Billing", "Test case");
			driver.get(WileyPLUS.wileyURLConcatenation("TC14", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.checkIfUserIsOnCartPage(driver);
			WileyPLUS.checkBrandNameWileyPLUS();
			ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
			WileyPLUS.clickOnProceedToCheckoutButton();
			String email=WileyPLUS.enterEmailIdInCreateAccountForm();
			WileyPLUS.clickOnCreateAccountButton();
			WileyPLUS.confirmEmailIdInCreateAccountForm(email);
			WileyPLUS.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC14", "WileyPLUS_Test_Data", "Password"));
			WileyPLUS.clickOnSaveAndContinueButton();
			WileyPLUS.checkIfUserInBillingStep();
			WileyPLUS.checkGlobalCountryListBilling(driver,excelOperation.getTestData("TC14", "WileyPLUS_Test_Data", "Bill_Country"));
			WileyPLUS.removeProductsFromCart(driver);
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 4/4/23
	 * @Description: Validates that the ISBN is not present in the WileyPLUS PDP
	 */
	@Test
	public void TC15_Check_ISBN_Not_Preset_In_WileyPLUS_PDP() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC15_Check_ISBN_Not_Preset_In_WileyPLUS_PDP");
			LogTextFile.writeTestCaseStatus("TC15_Check_ISBN_Not_Preset_In_WileyPLUS_PDP", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC15", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
				WileyPLUS.clickOnSRP_WileyProduct();
				if(WileyPLUS.checkWileyPLUSTabInPDP()){
					WileyPLUS.clickOnWileyPLUSTabPDP();
				}
				else {
					Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				if(!WileyPLUS.checkISBN_InPDP(driver,excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "SearchBox_Text"))) {
					Reporting.updateTestReport("Searched WileyPLUS ISBN was not present in the WileyPLUS PDP",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				else
					Reporting.updateTestReport("Searched WileyPLUS ISBN was present in the WileyPLUS PDP",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
}
