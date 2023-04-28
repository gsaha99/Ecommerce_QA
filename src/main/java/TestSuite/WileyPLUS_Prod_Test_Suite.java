package TestSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import utilities.EmailValidation;
import utilities.LogTextFile;
import utilities.PaymentGateway;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;
import org.openqa.selenium.JavascriptExecutor;

public class WileyPLUS_Prod_Test_Suite extends DriverModule{
	app_WileyPLUS_Repo WileyPLUS;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	public static String EmailConfirmationText="//button/div[contains(text(),'Your Order with Wiley')]";
	private static String WileyHomepage=excelOperation.getTestData("Wiley_Homepage_URL", "Generic_Dataset", "Data");

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
	 * @Description: Checks the Search result page for WileyPLUS products
	 */
	@Test
	public void TC01_SRP_For_WileyPLUS() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC01_SRP_For_WileyPLUS");
			LogTextFile.writeTestCaseStatus("TC01_SRP_For_WileyPLUS", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WileyHomepage);
			int flag=0;
			driver.navigate().refresh();
			String price;

			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC01", "WileyPLUS_Test_Data", "SearchBox_Text"));
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					flag=1;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}

				if(flag==1) {
					ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
					WileyPLUS.checkPublicationDateInSRP_PLPNewSearchPage();
					WileyPLUS.checkWileyPLUSFormatInSRP_PLPNewSearchPage(driver);
					price=WileyPLUS.checkPriceInSRP_PLPNewSearchPage(driver);
					WileyPLUS.clickOnSRP_WileyProductNewSearchPage();

				}
				else {
					WileyPLUS.checkPublicationDateInSRP_PLP();
					WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
					price=WileyPLUS.checkPriceInSRP_PLP(driver);
					WileyPLUS.clickOnSRP_WileyProduct();
				}
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-add-to-cart']")));
					if(price.equalsIgnoreCase(WileyPLUS.checkPriceInPDP()))
						Reporting.updateTestReport("The price in SRP was same as price in PDP",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport("The price in SRP: "+price+" was not same as price in PDP: "+WileyPLUS.checkPriceInPDP(),
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
	 * @Description: Checks the Product listing page for WileyPLUS products
	 */
	@Test
	public void TC02_PLP_For_WileyPLUS() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC02_PLP_For_WileyPLUS");
			LogTextFile.writeTestCaseStatus("TC02_PLP_For_WileyPLUS", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			int flag=0;
			driver.get(WileyHomepage);
			String price;
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC02", "WileyPLUS_Test_Data", "SearchBox_Text"));
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					flag=1;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
				if(flag==1) {
					WileyPLUS.clickOnFormatFacetNewSearchPage();
					ScrollingWebPage.PageDown(driver, SS_path);
					WebElement l=driver.findElement(By.xpath("//label[@for='format|WileyPlus']"));
					// Javascript executor
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", l);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='format|WileyPlus']")));
						WileyPLUS.clickOnWileyPLUSInFormatFacetNewSearchPage();
						WileyPLUS.checkWileyPLUSInAppliedFacetNewSearchPage();	
						ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
						WileyPLUS.checkPublicationDateInSRP_PLPNewSearchPage();
						WileyPLUS.checkWileyPLUSFormatInSRP_PLPNewSearchPage(driver);
						price=WileyPLUS.checkPriceInSRP_PLPNewSearchPage(driver);
						WileyPLUS.clickOnSRP_WileyProductNewSearchPage();
					}
					catch(Exception e) {
						Reporting.updateTestReport("WileyPLUS Format facet couldn't be clicked",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						price="";
					}
				}
				else {
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
						price=WileyPLUS.checkPriceInSRP_PLP(driver);
						WileyPLUS.clickOnSRP_WileyProduct();
					}
					catch(Exception e1) {
						Reporting.updateTestReport("Format modal couldn't be loaded and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						price="";
					}
				}
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
	 * @Description: Checks if we are able to search WileyPLUS EGRIP (Standalone) or not
	 */
	@Test
	public void TC03_EGRIP_Search_For_WileyPLUS() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_EGRIP_Search_For_WileyPLUS");
			LogTextFile.writeTestCaseStatus("TC03_EGRIP_Search_For_WileyPLUS", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			int flag=0;
			driver.get(WileyHomepage);
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC03", "WileyPLUS_Test_Data", "SearchBox_Text"));
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					flag=1;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
				if(flag==1) {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLPNewSearchPage(driver);
					WileyPLUS.clickOnSRP_WileyProductNewSearchPage();
				}
				else {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
					WileyPLUS.clickOnSRP_WileyProduct();
				}
				if(WileyPLUS.checkWileyPLUSTabInPDP()){
					WileyPLUS.clickOnWileyPLUSTabPDP();
				}
				else {
					Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				WileyPLUS.checkSingleTermWileyPLUSTab(driver);
				WileyPLUS.checkMultipleTermsWileyPLUSTab(driver);
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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			int flag=0;
			driver.get(WileyHomepage);
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC04", "WileyPLUS_Test_Data", "SearchBox_Text"));
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					flag=1;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
				if(flag==1) {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLPNewSearchPage(driver);
					WileyPLUS.clickOnSRP_WileyProductNewSearchPage();
				}
				else {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
					WileyPLUS.clickOnSRP_WileyProduct();
				}
				if(WileyPLUS.checkWileyPLUSTabInPDP()){
					WileyPLUS.clickOnWileyPLUSTabPDP();
				}
				else {
					Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				WileyPLUS.checkSingleTermWileyPLUSTab(driver);
				WileyPLUS.checkMultipleTermsWileyPLUSTab(driver);
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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			int flag=0;
			driver.get(WileyHomepage);
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC05", "WileyPLUS_Test_Data", "SearchBox_Text"));
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					flag=1;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
				if(flag==1) {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLPNewSearchPage(driver);
					WileyPLUS.clickOnSRP_WileyProductNewSearchPage();
				}
				else {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
					WileyPLUS.clickOnSRP_WileyProduct();
				}
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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			int flag=0;
			driver.get(WileyHomepage);
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC06", "WileyPLUS_Test_Data", "SearchBox_Text"));
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					flag=1;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
				if(flag==1) {
					WileyPLUS.clickOnFormatFacetNewSearchPage();
					ScrollingWebPage.PageDown(driver, SS_path);
					WebElement l=driver.findElement(By.xpath("//label[@for='format|WileyPlus']"));
					// Javascript executor
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", l);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='format|WileyPlus']")));
						WileyPLUS.clickOnWileyPLUSInFormatFacetNewSearchPage();
						WileyPLUS.checkWileyPLUSInAppliedFacetNewSearchPage();	
					}
					catch(Exception e) {
						Reporting.updateTestReport("WileyPLUS Format facet couldn't be clicked",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				else {
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
					catch(Exception e1) {
						Reporting.updateTestReport("Format modal couldn't be loaded and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			int flag=0;
			driver.get(WileyHomepage);
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, "
								+ "Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC07", "WileyPLUS_Test_Data", "SearchBox_Text"));
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					flag=1;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
				if(flag==1) {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLPNewSearchPage(driver);
					WileyPLUS.clickOnSRP_WileyProductNewSearchPage();
				}
				else {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
					WileyPLUS.clickOnSRP_WileyProduct();
				}
				if(WileyPLUS.checkWileyPLUSTabInPDP()) {
					WileyPLUS.clickOnWileyPLUSTabPDP();
					WileyPLUS.checkSingleTermWileyPLUSTab(driver);
					WileyPLUS.checkMultipleTermsWileyPLUSTab(driver);
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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			int flag=0;
			driver.get(WileyHomepage);
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC08", "WileyPLUS_Test_Data", "SearchBox_Text"));
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					flag=1;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
				if(flag==1) {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLPNewSearchPage(driver);
					WileyPLUS.clickOnSRP_WileyProductNewSearchPage();
				}
				else {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
					WileyPLUS.clickOnSRP_WileyProduct();
				}
				if(WileyPLUS.checkWileyPLUSTabInPDP()) {
					WileyPLUS.clickOnWileyPLUSTabPDP();
					WileyPLUS.checkSingleTermWileyPLUSTab(driver);
					WileyPLUS.checkMultipleTermsWileyPLUSTab(driver);
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
	 * @Description: Checks the Product details page UI for WileyPLUS
	 */
	@Test
	public void TC09_PDP_UI_For_WileyPLUS() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC09_PDP_UI_For_WileyPLUS");
			LogTextFile.writeTestCaseStatus("TC09_PDP_UI_For_WileyPLUS", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			int flag=0;
			driver.get(WileyHomepage);
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC09", "WileyPLUS_Test_Data", "SearchBox_Text"));
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					flag=1;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
				if(flag==1) {
					WileyPLUS.clickOnSRP_WileyProductNewSearchPage();
				}
				else {
					WileyPLUS.clickOnSRP_WileyProduct();
				}
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
				WileyPLUS.checkSingleTermWileyPLUSTab(driver);
				WileyPLUS.checkMultipleTermsWileyPLUSTab(driver);
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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			int flag=0;
			driver.get(WileyHomepage);
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC10", "WileyPLUS_Test_Data", "SearchBox_Text"));
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					flag=1;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
				if(flag==1) {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLPNewSearchPage(driver);
					WileyPLUS.clickOnSRP_WileyProductNewSearchPage();
				}
				else {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
					WileyPLUS.clickOnSRP_WileyProduct();
				}
				if(WileyPLUS.checkWileyPLUSTabInPDP()) {
					WileyPLUS.clickOnWileyPLUSTabPDP();
					WileyPLUS.checkSingleTermWileyPLUSTab(driver);
					WileyPLUS.checkMultipleTermsWileyPLUSTab(driver);
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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			int flag=0;
			driver.get(WileyHomepage);
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC11", "WileyPLUS_Test_Data", "SearchBox_Text"));
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					flag=1;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
				if(flag==1) {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLPNewSearchPage(driver);
					WileyPLUS.clickOnSRP_WileyProductNewSearchPage();
				}
				else {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
					WileyPLUS.clickOnSRP_WileyProduct();
				}
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
	 * @Description: Checks if Global country list is present in shipping step when WileyPLUS product is present in cart
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
	 * @Description: Checks if Global country list is present in Billing step when WileyPLUS product is present in cart
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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			int flag=0;
			driver.get(WileyHomepage);
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "SearchBox_Text"));
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					flag=1;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
				if(flag==1) {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLPNewSearchPage(driver);
					WileyPLUS.clickOnSRP_WileyProductNewSearchPage();
				}
				else {
					WileyPLUS.checkWileyPLUSFormatInSRP_PLP(driver);
					WileyPLUS.clickOnSRP_WileyProduct();
				}
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

	/*
	 * @Date: 19/04/23
	 * @Description: Adding multiple wileyplus products to cart with coupon
	 */
	@Test
	public void TC16_Adding_Multiple_Wileyplus_Products_With_Coupon_Validating_Guest_User_Option() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC16_Adding_Multiple_Wileyplus_Products_With_Coupon");
			LogTextFile.writeTestCaseStatus("TC16_Adding_Multiple_Wileyplus_Products_With_Coupon", "Test case");
			driver.get(WileyPLUS.wileyURLConcatenation("TC16", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.checkIfUserIsOnCartPage(driver);
			driver.get(WileyPLUS.wileyURLConcatenation("TC16", "WileyPLUS_Test_Data", "SearchBox_Text"));
			driver.navigate().refresh();
			ScrollingWebPage.PageScrolldown(driver,0,400,SS_path);
			BigDecimal subtotal=new BigDecimal(WileyPLUS.fetchOrderSubTotalInCartPage().substring(1));
			BigDecimal discount=new BigDecimal(WileyPLUS.fetchDiscountAmountInCartPage().substring(2));
			if(subtotal.multiply(new BigDecimal(0.35)).setScale(2, RoundingMode.HALF_EVEN).compareTo(discount)==0) 
				Reporting.updateTestReport(
						"The rounded value of :"+subtotal.multiply(new BigDecimal(0.35))+
						" is same as the discount value: "+discount,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport(
						"The rounded value of :"+subtotal.multiply(new BigDecimal(0.35))+
						" is not same as the discount value: "+discount,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
			WileyPLUS.clickOnProceedToCheckoutButton();
			if(!WileyPLUS.checkIfGuestCheckoutButtonIsPresent()) Reporting.updateTestReport("Guest checkout button was not present in login page when WileyPLUS product is present in cart",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else Reporting.updateTestReport("Guest checkout button is present in login page when WileyPLUS product is present in cart",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			String email=WileyPLUS.enterEmailIdInCreateAccountForm();
			WileyPLUS.clickOnCreateAccountButton();
			WileyPLUS.confirmEmailIdInCreateAccountForm(email);
			WileyPLUS.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC16", "WileyPLUS_Test_Data", "Password"));
			WileyPLUS.clickOnSaveAndContinueButton();
			WileyPLUS.checkIfUserInBillingStep();
			WileyPLUS.removeProductsFromCart(driver);
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 19/4/23
	 * @Description: Checks if the standard shipping is free if textbook rental bundle is present in cart
	 */
	@Test
	public void TC17_Standard_Shipping_Free_For_TextBook_Rental_Bundle() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC17_Standard_Shipping_Free_For_TextBook_Rental_Bundle");
			LogTextFile.writeTestCaseStatus("TC17_Standard_Shipping_Free_For_TextBook_Rental_Bundle", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			driver.get(WileyPLUS.wileyURLConcatenation("TC17", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.checkIfUserIsOnCartPage(driver);
			WileyPLUS.checkBrandNameWileyPLUS();
			ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
			WileyPLUS.clickOnProceedToCheckoutButton();
			String email=WileyPLUS.enterEmailIdInCreateAccountForm();
			WileyPLUS.clickOnCreateAccountButton();
			WileyPLUS.confirmEmailIdInCreateAccountForm(email);
			WileyPLUS.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC17", "WileyPLUS_Test_Data", "Password"));
			WileyPLUS.clickOnSaveAndContinueButton();
			WileyPLUS.checkIfUserInShippingStep();
			if(WileyPLUS.validateStandardShippingCharge())
				Reporting.updateTestReport("Standard shipping is free for US when Textbook rental bundle is present in cart",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Standard shipping is not free for US when Textbook rental bundle is present in cart",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			WileyPLUS.selectCountry(excelOperation.getTestData("TC17", "WileyPLUS_Test_Data", "Shipping_Country"));
			try{
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
				if(WileyPLUS.validateStandardShippingCharge())
					Reporting.updateTestReport("Standard shipping is free for Canada when Textbook rental bundle is present in cart",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				else
					Reporting.updateTestReport("Standard shipping is not free for Non US country when Textbook rental bundle is present in cart",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Shipping Address line 1 was not clickable"
						+ " and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.removeProductsFromCart(driver);
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
}
