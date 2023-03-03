package Test_Suite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
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
import utilities.OrderConfirmationMail;
import utilities.PaymentGateway;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class WileyPLUS_Test_Suite extends DriverModule{
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

			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC01", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.checkPublicationDateInSRP_PLP();
				WileyPLUS.checkWileyPLUSFormatInSRP_PLP();
				String price=WileyPLUS.checkPriceInSRP_PLP();
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
					WileyPLUS.checkWileyPLUSFormatInSRP_PLP();
					String price=WileyPLUS.checkPriceInSRP_PLP();
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
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
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
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
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
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
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
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, "
								+ "Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC07", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.checkWileyPLUSFormatInSRP_PLP();
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
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC08", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.checkWileyPLUSFormatInSRP_PLP();
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
			ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
			WileyPLUS.clickOnProceedToCheckoutButton();
			String email=WileyPLUS.enterEmailIdInCreateAccountForm();
			WileyPLUS.clickOnCreateAccountButton();
			WileyPLUS.confirmEmailIdInCreateAccountForm(email);
			WileyPLUS.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC13", "WileyPLUS_Test_Data", "Password"));
			WileyPLUS.clickOnSaveAndContinueButton();
			WileyPLUS.checkIfUserInShippingStep();
			WileyPLUS.checkGlobalCountryList(driver,excelOperation.getTestData("TC13", "WileyPLUS_Test_Data", "Shipping_Country"));
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
			ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
			WileyPLUS.clickOnProceedToCheckoutButton();
			String email=WileyPLUS.enterEmailIdInCreateAccountForm();
			WileyPLUS.clickOnCreateAccountButton();
			WileyPLUS.confirmEmailIdInCreateAccountForm(email);
			WileyPLUS.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC14", "WileyPLUS_Test_Data", "Password"));
			WileyPLUS.clickOnSaveAndContinueButton();
			WileyPLUS.checkIfUserInBillingStep();
			WileyPLUS.checkGlobalCountryListBilling(driver,excelOperation.getTestData("TC14", "WileyPLUS_Test_Data", "Bill_Country"));
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
	 * @Description: Checks if Course name is getting displayed during checkout journey
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

			WileyPLUS.checkTextInOrderSummaryTab(excelOperation.getTestData
					("OrderSummaryTabTextBeforeShipping","Generic_Messages", "Data"),driver);
			ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
			WileyPLUS.clickOnProceedToCheckoutButton();
			if(!WileyPLUS.checkIfGuestCheckoutButtonIsPresent()) Reporting.updateTestReport("Guest checkout button was not present in login"
					+ " page when digital/rental product is present in cart",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else Reporting.updateTestReport("Guest checkout button is present in login page when digital product is present in cart",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			String email=WileyPLUS.enterEmailIdInCreateAccountForm();
			WileyPLUS.clickOnCreateAccountButton();
			WileyPLUS.checkTextInOrderSummaryTab(excelOperation.getTestData
					("OrderSummaryTabTextBeforeShipping","Generic_Messages", "Data"),driver);
			WileyPLUS.confirmEmailIdInCreateAccountForm(email);
			WileyPLUS.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Password"));
			WileyPLUS.clickOnSaveAndContinueButton();
			WileyPLUS.checkIfUserInBillingStep();
			WileyPLUS.checkTextInOrderSummaryTab(excelOperation.getTestData
					("OrderSummaryTabTextBeforeBilling","Generic_Messages", "Data"),driver);
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
				PaymentGateway.paymentWileyPLUS(driver, WileyPLUS, "TC15", SS_path)	;	
				WileyPLUS.enterFirstName(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "First_Name"));
				WileyPLUS.enterLastName(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Last_Name"));
				WileyPLUS.selectCountry(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Bill_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
					WileyPLUS.enterAddressLine1Billing(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Bill_Address_line1"));
					WileyPLUS.enterZipBilling(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Bill_Zip_Code"));
					//wiley.enterCityBilling(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_City"));
					//wiley.enterState(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_State"));
					//wiley.selectUState();
					WileyPLUS.enterPhoneNumberBilling(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Bill_Phone_Number"));
					WileyPLUS.clickOnSaveAndContinueButton();
					try {
						wait.until(ExpectedConditions.visibilityOf(WileyPLUS.returnUseSelectedBillingAddressButtonAddressDoctorPopUp())); 
						WileyPLUS.clickOnUseSelectedBillingAddressButtonAddressDoctor();
					}
					catch(Exception e) {
						Reporting.updateTestReport("Address doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					if(new BigDecimal(WileyPLUS.fetchFirstProductPriceInOrderReview().substring(1))
							.add(new BigDecimal(WileyPLUS.fetchTaxInOrderReview().substring(1)))
							.compareTo(new BigDecimal(WileyPLUS.fetchTotalInOrderReview().substring(1)))==0)
						Reporting.updateTestReport("First Product price + Tax "
								+ " = Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport("First Product price + Tax "
								+ " is not equal to Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					WileyPLUS.checkCourseNameInOrderReviewPage(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Course"));
					WileyPLUS.clickOnPlaceOrderButton();
					String orderconfirmation = driver.getTitle();
					if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
						WileyPLUS.checkPrintReciept();
						WileyPLUS.checkTextInOrderConfirmationPage(
								excelOperation.getTestData("RegisteredUserOrderConfirmationText", "Generic_Messages", "Data"), driver);
						ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
						WileyPLUS.checkCourseNameInOrderConfirmationPage(excelOperation.getTestData("TC15", "WileyPLUS_Test_Data", "Course"));
						String orderId = WileyPLUS.fetchOrderId();
						excelOperation.updateTestData("TC15", "WileyPLUS_Test_Data", "Order_Id", orderId);
						excelOperation.updateTestData("TC15", "WileyPLUS_Test_Data", "Email_Id", email);
						ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
						String ordertotal = WileyPLUS.fetchOrderTotal();
						String taxamount = WileyPLUS.fetchTaxAmount();
						excelOperation.updateTestData("TC15", "WileyPLUS_Test_Data", "Order_Total", ordertotal);
						excelOperation.updateTestData("TC15", "WileyPLUS_Test_Data", "Tax", taxamount);
						driver.get(excelOperation.getTestData("Yopmail_URL",
								"Generic_Dataset", "Data"));
						WileyPLUS.enterEmailIdInYopmail(email);
						WileyPLUS.clickOnCheckInboxButton();
						if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
							Reporting.updateTestReport("Order Confirmation mail was received",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
							OrderConfirmationMail.validateOrderConfirmationMailContent("Wiley",driver,SS_path,taxamount," ",ordertotal);
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


				}
				catch(Exception e) {
					Reporting.updateTestReport("Billing address line 1 was not clickable"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Cardholder name field in Card information"
						+ " section was not clickable and caused timeout exception"
						, CaptureScreenshot.getScreenshot(SS_path),
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
	 * @Date: 6/1/23
	 * @Description: Checks if global saved address is displayed when WileyPLUS product is present in cart
	 */
	@Test
	public void TC16_Check_Global_Saved_Billing_Address() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC16_Check_Global_Saved_Billing_Address");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC16", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.checkIfUserIsOnCartPage(driver);
			WileyPLUS.checkBrandNameWileyPLUS();
			ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
			WileyPLUS.clickOnProceedToCheckoutButton();
			String email=WileyPLUS.enterEmailIdInCreateAccountForm();
			WileyPLUS.clickOnCreateAccountButton();
			WileyPLUS.confirmEmailIdInCreateAccountForm(email);
			WileyPLUS.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC16", "WileyPLUS_Test_Data", "Password"));
			WileyPLUS.clickOnSaveAndContinueButton();
			WileyPLUS.checkIfUserInBillingStep();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
				PaymentGateway.paymentWileyPLUS(driver, WileyPLUS, "TC16", SS_path)	;		
				WileyPLUS.enterFirstName(excelOperation.getTestData("TC16", "WileyPLUS_Test_Data", "First_Name"));
				WileyPLUS.enterLastName(excelOperation.getTestData("TC16", "WileyPLUS_Test_Data", "Last_Name"));
				WileyPLUS.selectCountry(excelOperation.getTestData("TC16", "WileyPLUS_Test_Data", "Bill_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
					WileyPLUS.enterAddressLine1Billing(excelOperation.getTestData("TC16", "WileyPLUS_Test_Data", "Bill_Address_line1"));
					WileyPLUS.enterZipBilling(excelOperation.getTestData("TC16", "WileyPLUS_Test_Data", "Bill_Zip_Code"));
					WileyPLUS.enterPhoneNumberBilling(excelOperation.getTestData("TC16", "WileyPLUS_Test_Data", "Bill_Phone_Number"));
					WileyPLUS.clickOnSaveAndContinueButton();
					try {
						wait.until(ExpectedConditions.visibilityOf(WileyPLUS.returnUseSelectedBillingAddressButtonAddressDoctorPopUp()));
						WileyPLUS.clickOnUseSelectedBillingAddressButtonAddressDoctor();
					}

					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					if(new BigDecimal(WileyPLUS.fetchFirstProductPriceInOrderReview().substring(1))
							.add(new BigDecimal(WileyPLUS.fetchTaxInOrderReview().substring(1)))
							.compareTo(new BigDecimal(WileyPLUS.fetchTotalInOrderReview().substring(1)))==0)
						Reporting.updateTestReport("First Product price + Tax "
								+ " = Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport("First Product price + Tax "
								+ " is not equal to Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					WileyPLUS.clickOnPlaceOrderButton();
					String orderconfirmation = driver.getTitle();
					if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
						WileyPLUS.checkPrintReciept();
						WileyPLUS.checkTextInOrderConfirmationPage(
								excelOperation.getTestData("RegisteredUserOrderConfirmationText", "Generic_Messages", "Data"), driver);
						ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
						String orderId = WileyPLUS.fetchOrderId();
						excelOperation.updateTestData("TC16", "WileyPLUS_Test_Data", "Order_Id", orderId);
						excelOperation.updateTestData("TC16", "WileyPLUS_Test_Data", "Email_Id", email);
						ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
						String ordertotal = WileyPLUS.fetchOrderTotal();
						String taxamount = WileyPLUS.fetchTaxAmount();
						excelOperation.updateTestData("TC16", "WileyPLUS_Test_Data", "Order_Total", ordertotal);
						excelOperation.updateTestData("TC16", "WileyPLUS_Test_Data", "Tax", taxamount);					
						driver.get(WileyPLUS.wileyURLConcatenation("TC16", "WileyPLUS_Test_Data", "URL"));
						driver.navigate().refresh();
						WileyPLUS.checkIfUserIsOnCartPage(driver);
						WileyPLUS.checkBrandNameWileyPLUS();
						WileyPLUS.clickOnProceedToCheckoutButton();
						WileyPLUS.checkIfUserInBillingStep();
						ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
						String xpathOfGlobalSavedAddress="//*[contains(text(),' "
								+excelOperation.getTestData("TC16", "WileyPLUS_Test_Data", "Bill_Address_line1")+
								"')]/following-sibling::div[contains(text(),'"+excelOperation.getTestData("TC16", "WileyPLUS_Test_Data", "Bill_Country")+"')]";

						System.out.println(xpathOfGlobalSavedAddress);
						WileyPLUS.checkGlobalSavedAddress(driver, xpathOfGlobalSavedAddress);
					}			

					else {
						Reporting.updateTestReport("Order was not placed and saved global address couldn't be validated", CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}

				}
				catch(Exception e) {
					Reporting.updateTestReport("Billing address line 1 was not clickable"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Cardholder name ield in Card information"
						+ " section was not clickable and caused timeout exception"
						, CaptureScreenshot.getScreenshot(SS_path),
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
	 * @Date: 6/1/23
	 * @Description: Checks if global saved shipping address is displayed when WileyPLUS product is present in cart
	 */
	@Test
	public void TC17_Check_Global_Saved_Shipping_Address() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC17_Check_Global_Saved_Shipping_Address");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
			WileyPLUS.enterFirstName(excelOperation.getTestData("TC17", "WileyPLUS_Test_Data", "First_Name"));
			WileyPLUS.enterLastName(excelOperation.getTestData("TC17", "WileyPLUS_Test_Data", "Last_Name"));
			WileyPLUS.selectCountry(excelOperation.getTestData("TC17", "WileyPLUS_Test_Data", "Shipping_Country"));
			try{
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
				WileyPLUS.enterAddressLine1Shipping(excelOperation.getTestData("TC17", "WileyPLUS_Test_Data", "Shipping_Address_line1"));
				WileyPLUS.enterShippingZIPCode(excelOperation.getTestData("TC17", "WileyPLUS_Test_Data", "Shipping_Zip_Code"));
				WileyPLUS.enterShippingCity(excelOperation.getTestData("TC17", "WileyPLUS_Test_Data", "Shipping_City"));
				//WileyPLUS.enterState(excelOperation.getTestData("TC17", "WileyPLUS_Test_Data", "Shipping_State"));
				WileyPLUS.enterPhoneNumberShipping(excelOperation.getTestData("TC17", "WileyPLUS_Test_Data", "Shipping_Phone_Number"));
				WileyPLUS.clickOnSaveAndContinueButton();
				try {
					wait.until(ExpectedConditions.visibilityOf(WileyPLUS.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()));
					WileyPLUS.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
				catch(Exception e) {
					Reporting.updateTestReport("Adress doctor pop up did not appear",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
				}
				WileyPLUS.checkIfUserInBillingStep();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					PaymentGateway.paymentWileyPLUS(driver, WileyPLUS, "TC17", SS_path)	;		
					WileyPLUS.clickOnSaveAndContinueButton();
					if(new BigDecimal(WileyPLUS.fetchFirstProductPriceInOrderReview().substring(1))
							.add(new BigDecimal(WileyPLUS.fetchShippingChargeInOrderReview().substring(1)))
							.add(new BigDecimal(WileyPLUS.fetchTaxInOrderReview().substring(1)))
							.compareTo(new BigDecimal(WileyPLUS.fetchTotalInOrderReview().substring(1)))==0)
						Reporting.updateTestReport("First Product price + Tax + Shipping charge"
								+ " = Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport("First Product price+ Tax + Shipping charge"
								+ " is not equal to Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					WileyPLUS.clickOnPlaceOrderButton();
					String orderconfirmation = driver.getTitle();
					if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
						WileyPLUS.checkPrintReciept();
						WileyPLUS.checkTextInOrderConfirmationPage(
								excelOperation.getTestData("RegisteredUserOrderConfirmationText", "Generic_Messages", "Data"), driver);
						ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
						String orderId = WileyPLUS.fetchOrderId();
						excelOperation.updateTestData("TC17", "WileyPLUS_Test_Data", "Order_Id", orderId);
						excelOperation.updateTestData("TC17", "WileyPLUS_Test_Data", "Email_Id", email);
						ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
						String ordertotal = WileyPLUS.fetchOrderTotal();
						String taxamount = WileyPLUS.fetchTaxAmount();
						excelOperation.updateTestData("TC17", "WileyPLUS_Test_Data", "Order_Total", ordertotal);
						excelOperation.updateTestData("TC17", "WileyPLUS_Test_Data", "Tax", taxamount);					
						driver.get(WileyPLUS.wileyURLConcatenation("TC17", "WileyPLUS_Test_Data", "URL"));
						driver.navigate().refresh();
						WileyPLUS.checkIfUserIsOnCartPage(driver);
						WileyPLUS.checkBrandNameWileyPLUS();
						WileyPLUS.clickOnProceedToCheckoutButton();
						WileyPLUS.checkIfUserInShippingStep();
						String xpathOfGlobalSavedAddress="//*[contains(text(),' "
								+excelOperation.getTestData("TC17", "WileyPLUS_Test_Data", "Shipping_Address_line1")+
								"')]/following-sibling::div[contains(text(),'"+excelOperation.getTestData("TC16", "WileyPLUS_Test_Data", "Shipping_Country")+"')]";		
						System.out.println(xpathOfGlobalSavedAddress);
						WileyPLUS.checkGlobalSavedAddress(driver, xpathOfGlobalSavedAddress);
					}			

					else {
						Reporting.updateTestReport("Order was not placed and saved global address couldn't be validated", CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}

				}
				catch(Exception e) {
					Reporting.updateTestReport("Cardholder name ield in Card information"
							+ " section was not clickable and caused timeout exception"
							, CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Shipping Address line 1 was not clickable"
						+ " and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
	 * @Date: 6/1/23
	 * @Description: Validates that the ISBN is not present in the WileyPLUS PDP
	 */
	@Test
	public void TC18_Check_ISBN_Not_Preset_In_WileyPLUS_PDP() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC18_Check_ISBN_Not_Preset_In_WileyPLUS_PDP");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC18", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				WileyPLUS.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC18", "WileyPLUS_Test_Data", "SearchBox_Text"));
				WileyPLUS.checkWileyPLUSFormatInSRP_PLP();
				WileyPLUS.clickOnSRP_WileyProduct();
				if(WileyPLUS.checkWileyPLUSTabInPDP()){
					WileyPLUS.clickOnWileyPLUSTabPDP();
				}
				else {
					Reporting.updateTestReport("The WileyPLUS tab was not present in PDP",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				if(!WileyPLUS.checkISBN_InPDP(driver,excelOperation.getTestData("TC18", "WileyPLUS_Test_Data", "SearchBox_Text"))) {
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
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 6/1/23
	 * @Description: Validates the Continue shopping link functionality
	 */
	@Test
	public void TC19_Validate_Continue_Shopping_Link() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC19_Validate_Continue_Shopping_Link");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC01", "WileyPLUS_Test_Data", "URL"));
			driver.get(excelOperation.getTestData("TC19", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnCreateAccountLinkOnboarding();
			WileyPLUS.enterFirstNameInOnboardingCreateAccount(excelOperation.getTestData("TC19", "WileyPLUS_Test_Data", "First_Name"));
			Thread.sleep(3000);
			WileyPLUS.enterLastNameInOnboardingCreateAccount(excelOperation.getTestData("TC19", "WileyPLUS_Test_Data", "Last_Name"));
			String emailId=WileyPLUS.enterEmailIdInOnboardingCreateAccount();
			WileyPLUS.enterInstitutionNameInOnboardingCreateAccount(driver, excelOperation.getTestData("TC19", "WileyPLUS_Test_Data", "Institution"));
			Thread.sleep(2000);
			WileyPLUS.enterPasswordInOnboarding(excelOperation.getTestData("TC19", "WileyPLUS_Test_Data", "Password"));
			WileyPLUS.clickOnOnboardingCreateAccountCheckbox();
			WileyPLUS.clickOnOnboardingCreateAccountButton();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Check Your Email')]")));
				driver.get(excelOperation.getTestData("Yopmail_URL",
						"Generic_Dataset", "Data"));
				WileyPLUS.enterEmailIdInYopmail(emailId);
				WileyPLUS.clickOnCheckInboxButton();
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
				int timeOutSeconds=10;
				int flag=0;
				WebElement element1 = driver.findElement(By.xpath("//button[@id='refresh']"));
				WebElement element2 = null;
				/* The purpose of this loop is to wait for maximum of 60 seconds */
				for (int i = 0; i < timeOutSeconds / 5; i++) {

					try {
						driver.switchTo().frame("ifinbox");
						element2=wait1.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[contains(text(),'Welcome to Wiley')]")));

						if(element2.isDisplayed()==true)
						{
							flag=1;
							element2.click();
							driver.switchTo().defaultContent();
							break;
						}

					} catch (Exception e) {
						driver.switchTo().defaultContent();        
						element1.click();
					}
				}
				if(flag==1) {
					driver.switchTo().frame("ifmail");
					WileyPLUS.clickOnFinishRegistrationLinkInMail();
					driver.switchTo().defaultContent();
					Set<String> allWindowHandles = driver.getWindowHandles();
					java.util.Iterator<String> iterator = allWindowHandles.iterator();
					String yopmailHandle = iterator.next();
					String ChildWindow=iterator.next();
					driver.switchTo().window(yopmailHandle);
					driver.close();
					driver.switchTo().window(ChildWindow);
				}
				else {
					Reporting.updateTestReport("No finish registration mail  was recieved in yopmail inbox", 
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				WileyPLUS.enterEmailIdInOnboardingLogin(emailId);
				WileyPLUS.enterPasswordInOnboarding(excelOperation.getTestData("TC19", "WileyPLUS_Test_Data", "Password"));
				WileyPLUS.clickOnOnboardingLoginButton();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//span[contains(text(),'add course')]")));
					Thread.sleep(2000);
					WileyPLUS.clickOnOnboardingAddCourseButton();
					WileyPLUS.enterCourseSectionId(excelOperation.getTestData("TC19", "WileyPLUS_Test_Data", "Course"));
					WileyPLUS.clickOnContinueButtonInOnboarding();
					try {
						wait.until(ExpectedConditions.presenceOfElementLocated(
								By.xpath("//h2[contains(text(),'Join your course')]")));
						WileyPLUS.clickOnSingleTermRadioButtonInJoinCourse();
						WileyPLUS.clickOnContinueButtonInOnboarding();
						WileyPLUS.clickOnFirstPurchaseOption();
						WileyPLUS.clickOnContinueToCheckoutButton();
						try {
							wait.until(ExpectedConditions.
									presenceOfElementLocated
									(By.xpath("//p[contains(text(),' Your Cart')]")));
							WileyPLUS.checkIfUserIsOnCartPage(driver);
							ScrollingWebPage.PageScrolldown(driver,0,800,SS_path);
							wait.until(ExpectedConditions.
									elementToBeClickable(By.xpath("(//button[@id='continue-shopping-button'])[2]")));
							WileyPLUS.clickOnContinueShoppingButton();
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(
										By.xpath("//input[@id='courseID']")));
								Reporting.updateTestReport("After clicking on continue shopping button"
										+ " user was in Add course page when the last added product was WileyPLUS",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								driver.get(WileyPLUS.wileyURLConcatenation("TC01", "WileyPLUS_Test_Data", "URL"));
								driver.navigate().refresh();								
								try {
									WileyPLUS.clickOnHomePage();
									wait.until(ExpectedConditions.presenceOfElementLocated(
											By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
													+ " Education and Research')]")));
									WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC19",
											"WileyPLUS_Test_Data", "SearchBox_Text"));
									WileyPLUS.clickOnSRP_WileyProduct();
									WileyPLUS.clickOnAddToCartButton();
									try {
										wait.until(ExpectedConditions.
												elementToBeClickable
												(By.xpath("//button[contains(text(),'View Cart')]")));
										WileyPLUS.clickOnViewCartButton();
										ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
										WileyPLUS.clickOnContinueShoppingButton();
										try {
											wait.until(ExpectedConditions.presenceOfElementLocated(
													By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing, Education and Research')]")));
											wait.until(ExpectedConditions.visibilityOfElementLocated(
													By.xpath("//img[@alt='Wiley Consumer Logo']")));
											Reporting.updateTestReport("Wiley Storefront homepage was opened after clicking on continue shopping button"
													+ " when the last added product was wiley.com",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
										}
										catch(Exception e) {
											Reporting.updateTestReport("Wiley Storefront homepage was not opened after clicking on continue shopping button"
													+ " when the last added product was wiley.com and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									}
									catch(Exception e) {
										Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								catch(Exception e) {
									Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e){
								Reporting.updateTestReport("After clicking on continue shopping button"
										+ " user was not in Add course page when the last added product was WileyPLUS "
										+ "and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e){
							Reporting.updateTestReport("User was not on cart page and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e){
						Reporting.updateTestReport("User couldn't add course and was not on Join Course page"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("User couldn't login and was not on my account page"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Create account form could not be submitted and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.WileyLogOut();

		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 27/01/23
	 * @Description: Places one order in CAD Currency from WileyPLUS Frontend
	 */
	@Test
	public void TC20_Place_WileyPLUS_CAD_Order_From_Frontend() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC20_Place_WileyPLUS_CAD_Order_From_Frontend");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC01", "WileyPLUS_Test_Data", "URL"));
			driver.get(excelOperation.getTestData("TC20", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnCreateAccountLinkOnboarding();
			WileyPLUS.enterFirstNameInOnboardingCreateAccount(excelOperation.getTestData("TC20", "WileyPLUS_Test_Data", "First_Name"));
			Thread.sleep(3000);
			WileyPLUS.enterLastNameInOnboardingCreateAccount(excelOperation.getTestData("TC20", "WileyPLUS_Test_Data", "Last_Name"));
			String emailId=WileyPLUS.enterEmailIdInOnboardingCreateAccount();
			WileyPLUS.enterInstitutionNameInOnboardingCreateAccount(driver, excelOperation.getTestData("TC20", "WileyPLUS_Test_Data", "Institution"));
			Thread.sleep(2000);
			WileyPLUS.enterPasswordInOnboarding(excelOperation.getTestData("TC20", "WileyPLUS_Test_Data", "Password"));
			WileyPLUS.clickOnOnboardingCreateAccountCheckbox();
			WileyPLUS.clickOnOnboardingCreateAccountButton();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Check Your Email')]")));
				driver.get(excelOperation.getTestData("Yopmail_URL",
						"Generic_Dataset", "Data"));
				WileyPLUS.enterEmailIdInYopmail(emailId);
				WileyPLUS.clickOnCheckInboxButton();
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
				int timeOutSeconds=10;
				int flag=0;
				WebElement element1 = driver.findElement(By.xpath("//button[@id='refresh']"));
				WebElement element2 = null;
				/* The purpose of this loop is to wait for maximum of 60 seconds */
				for (int i = 0; i < timeOutSeconds / 5; i++) {

					try {
						driver.switchTo().frame("ifinbox");
						element2=wait1.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[contains(text(),'Welcome to Wiley')]")));

						if(element2.isDisplayed()==true)
						{
							flag=1;
							element2.click();
							driver.switchTo().defaultContent();
							break;
						}

					} catch (Exception e) {
						driver.switchTo().defaultContent();        
						element1.click();
					}
				}
				if(flag==1) {
					driver.switchTo().frame("ifmail");
					WileyPLUS.clickOnFinishRegistrationLinkInMail();
					driver.switchTo().defaultContent();
					Set<String> allWindowHandles = driver.getWindowHandles();
					java.util.Iterator<String> iterator = allWindowHandles.iterator();
					String yopmailHandle = iterator.next();
					String ChildWindow=iterator.next();
					driver.switchTo().window(yopmailHandle);
					driver.close();
					driver.switchTo().window(ChildWindow);
				}
				else {
					Reporting.updateTestReport("No finish registration mail  was recieved in yopmail inbox", 
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				WileyPLUS.enterEmailIdInOnboardingLogin(emailId);
				WileyPLUS.enterPasswordInOnboarding(excelOperation.getTestData("TC20", "WileyPLUS_Test_Data", "Password"));
				WileyPLUS.clickOnOnboardingLoginButton();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//span[contains(text(),'add course')]")));
					Thread.sleep(2000);
					WileyPLUS.clickOnOnboardingAddCourseButton();
					WileyPLUS.enterCourseSectionId(excelOperation.getTestData("TC20", "WileyPLUS_Test_Data", "Course"));
					WileyPLUS.clickOnContinueButtonInOnboarding();
					try {
						wait.until(ExpectedConditions.presenceOfElementLocated(
								By.xpath("//h2[contains(text(),'Join your course')]")));
						WileyPLUS.clickOnSingleTermRadioButtonInJoinCourse();
						WileyPLUS.clickOnContinueButtonInOnboarding();
						WileyPLUS.clickOnFirstPurchaseOption();
						WileyPLUS.clickOnContinueToCheckoutButton();
						try {
							wait.until(ExpectedConditions.
									presenceOfElementLocated
									(By.xpath("//p[contains(text(),' Your Cart')]")));
							WileyPLUS.checkIfUserIsOnCartPage(driver);
							ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
							WileyPLUS.clickOnProceedToCheckoutButton();
							WileyPLUS.checkIfUserInBillingStep();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
								PaymentGateway.paymentWileyPLUS(driver, WileyPLUS, "TC20", SS_path)	;		
								WileyPLUS.enterFirstName(excelOperation.getTestData("TC20", "WileyPLUS_Test_Data", "First_Name"));
								WileyPLUS.enterLastName(excelOperation.getTestData("TC20", "WileyPLUS_Test_Data", "Last_Name"));
								WileyPLUS.selectCountry(excelOperation.getTestData("TC20", "WileyPLUS_Test_Data", "Bill_Country"));
								try{
									wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
									WileyPLUS.enterAddressLine1Billing(excelOperation.getTestData("TC20", "WileyPLUS_Test_Data", "Bill_Address_line1"));
									WileyPLUS.enterZipBilling(excelOperation.getTestData("TC20", "WileyPLUS_Test_Data", "Bill_Zip_Code"));
									//wiley.enterCityBilling(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_City"));
									//wiley.enterState(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_State"));
									//wiley.selectUState();
									WileyPLUS.enterPhoneNumberBilling(excelOperation.getTestData("TC20", "WileyPLUS_Test_Data", "Bill_Phone_Number"));
									WileyPLUS.clickOnSaveAndContinueButton();
									try {
										if(WileyPLUS.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed()) 
											WileyPLUS.clickOnUseSelectedBillingAddressButtonAddressDoctor();
									}
									catch(Exception e) {
										Reporting.updateTestReport("Adress doctor pop up did not appear",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
									}
									if(new BigDecimal(WileyPLUS.fetchFirstProductPriceInOrderReview().substring(5))
											.add(new BigDecimal(WileyPLUS.fetchTaxInOrderReview().substring(5)))
											.compareTo(new BigDecimal(WileyPLUS.fetchTotalInOrderReview().substring(5)))==0)
										Reporting.updateTestReport("First Product price + Tax "
												+ " = Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
									else
										Reporting.updateTestReport("First Product price + Tax "
												+ " is not equal to Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									WileyPLUS.clickOnPlaceOrderButton();
									String orderconfirmation = driver.getTitle();
									if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
										WileyPLUS.checkPrintReciept();
										WileyPLUS.checkTextInOrderConfirmationPage(
												excelOperation.getTestData("RegisteredUserOrderConfirmationText", "Generic_Messages", "Data"), driver);
										ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
										String orderId = WileyPLUS.fetchOrderId();
										excelOperation.updateTestData("TC20", "WileyPLUS_Test_Data", "Order_Id", orderId);
										excelOperation.updateTestData("TC20", "WileyPLUS_Test_Data", "Email_Id", emailId);
										ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
										String ordertotal = WileyPLUS.fetchOrderTotal();
										String taxamount = WileyPLUS.fetchTaxAmount();
										excelOperation.updateTestData("TC20", "WileyPLUS_Test_Data", "Order_Total", ordertotal);
										excelOperation.updateTestData("TC20", "WileyPLUS_Test_Data", "Tax", taxamount);
										driver.get(excelOperation.getTestData("Yopmail_URL",
												"Generic_Dataset", "Data"));
										WileyPLUS.enterEmailIdInYopmail(emailId);
										WileyPLUS.clickOnCheckInboxButton();
										if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
											ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
											Reporting.updateTestReport("Order Confirmation mail was received",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
											//OrderConfirmationMail.validateOrderConfirmationMailContent("Wiley",driver,SS_path,taxamount," ",ordertotal);
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


								}
								catch(Exception e) {
									Reporting.updateTestReport("Billing address line 1 was not clickable"
											+ " and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("Cardholder name field in Card information"
										+ " section was not clickable and caused timeout exception"
										, CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
						}
						catch(Exception e){
							Reporting.updateTestReport("User was not on cart page and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e){
						Reporting.updateTestReport("User couldn't add course and was not on Join Course page"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("User couldn't login and was not on my account page"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Create account form could not be submitted and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 27/01/23
	 * @Description: Places one order in USD Currency from WileyPLUS Frontend
	 */
	@Test
	public void TC21_Place_WileyPLUS_USD_Order_From_Frontend() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC21_Place_WileyPLUS_USD_Order_From_Frontend");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC01", "WileyPLUS_Test_Data", "URL"));
			driver.get(excelOperation.getTestData("TC21", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnCreateAccountLinkOnboarding();
			WileyPLUS.enterFirstNameInOnboardingCreateAccount(excelOperation.getTestData("TC21", "WileyPLUS_Test_Data", "First_Name"));
			Thread.sleep(3000);
			WileyPLUS.enterLastNameInOnboardingCreateAccount(excelOperation.getTestData("TC21", "WileyPLUS_Test_Data", "Last_Name"));
			String emailId=WileyPLUS.enterEmailIdInOnboardingCreateAccount();
			WileyPLUS.enterInstitutionNameInOnboardingCreateAccount(driver, excelOperation.getTestData("TC21", "WileyPLUS_Test_Data", "Institution"));
			Thread.sleep(2000);
			WileyPLUS.enterPasswordInOnboarding(excelOperation.getTestData("TC21", "WileyPLUS_Test_Data", "Password"));
			WileyPLUS.clickOnOnboardingCreateAccountCheckbox();
			WileyPLUS.clickOnOnboardingCreateAccountButton();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Check Your Email')]")));
				driver.get(excelOperation.getTestData("Yopmail_URL",
						"Generic_Dataset", "Data"));
				WileyPLUS.enterEmailIdInYopmail(emailId);
				WileyPLUS.clickOnCheckInboxButton();
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
				int timeOutSeconds=10;
				int flag=0;
				WebElement element1 = driver.findElement(By.xpath("//button[@id='refresh']"));
				WebElement element2 = null;
				/* The purpose of this loop is to wait for maximum of 60 seconds */
				for (int i = 0; i < timeOutSeconds / 5; i++) {

					try {
						driver.switchTo().frame("ifinbox");
						element2=wait1.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[contains(text(),'Welcome to Wiley')]")));

						if(element2.isDisplayed()==true)
						{
							flag=1;
							element2.click();
							driver.switchTo().defaultContent();
							break;
						}

					} catch (Exception e) {
						driver.switchTo().defaultContent();        
						element1.click();
					}
				}
				if(flag==1) {
					driver.switchTo().frame("ifmail");
					WileyPLUS.clickOnFinishRegistrationLinkInMail();
					driver.switchTo().defaultContent();
					Set<String> allWindowHandles = driver.getWindowHandles();
					java.util.Iterator<String> iterator = allWindowHandles.iterator();
					String yopmailHandle = iterator.next();
					String ChildWindow=iterator.next();
					driver.switchTo().window(yopmailHandle);
					driver.close();
					driver.switchTo().window(ChildWindow);
				}
				else {
					Reporting.updateTestReport("No finish registration mail  was recieved in yopmail inbox", 
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				WileyPLUS.enterEmailIdInOnboardingLogin(emailId);
				WileyPLUS.enterPasswordInOnboarding(excelOperation.getTestData("TC21", "WileyPLUS_Test_Data", "Password"));
				WileyPLUS.clickOnOnboardingLoginButton();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//span[contains(text(),'add course')]")));
					Thread.sleep(2000);
					WileyPLUS.clickOnOnboardingAddCourseButton();
					WileyPLUS.enterCourseSectionId(excelOperation.getTestData("TC21", "WileyPLUS_Test_Data", "Course"));
					WileyPLUS.clickOnContinueButtonInOnboarding();
					try {
						wait.until(ExpectedConditions.presenceOfElementLocated(
								By.xpath("//h2[contains(text(),'Join your course')]")));
						WileyPLUS.clickOnMultiTermRadioButtonInJoinCourse();
						WileyPLUS.clickOnContinueButtonInOnboarding();
						WileyPLUS.clickOnLooseLeafPurchaseOption();
						WileyPLUS.clickOnContinueToCheckoutButton();
						try {
							wait.until(ExpectedConditions.
									presenceOfElementLocated
									(By.xpath("//p[contains(text(),' Your Cart')]")));
							WileyPLUS.checkIfUserIsOnCartPage(driver);
							ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
							WileyPLUS.clickOnProceedToCheckoutButton();
							WileyPLUS.enterFirstName(excelOperation.getTestData("TC21", "WileyPLUS_Test_Data", "First_Name"));
							WileyPLUS.enterLastName(excelOperation.getTestData("TC21", "WileyPLUS_Test_Data", "Last_Name"));
							WileyPLUS.selectCountry(excelOperation.getTestData("TC21", "WileyPLUS_Test_Data", "Shipping_Country"));
							try{
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
								WileyPLUS.enterAddressLine1Shipping(excelOperation.getTestData("TC21", "WileyPLUS_Test_Data", "Shipping_Address_line1"));
								WileyPLUS.enterShippingZIPCode(excelOperation.getTestData("TC21", "WileyPLUS_Test_Data", "Shipping_Zip_Code"));
								//WileyPLUS.enterShippingCity(excelOperation.getTestData("TC17", "WileyPLUS_Test_Data", "Shipping_City"));
								//WileyPLUS.enterState(excelOperation.getTestData("TC17", "WileyPLUS_Test_Data", "Shipping_State"));
								WileyPLUS.enterPhoneNumberShipping(excelOperation.getTestData("TC21", "WileyPLUS_Test_Data", "Shipping_Phone_Number"));
								WileyPLUS.clickOnSaveAndContinueButton();
								try {
									if(WileyPLUS.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
										WileyPLUS.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
								catch(Exception e) {
									Reporting.updateTestReport("Adress doctor pop up did not appear",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
								}
								WileyPLUS.checkIfUserInBillingStep();
								driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
								try {
									wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
									PaymentGateway.paymentWileyPLUS(driver, WileyPLUS, "TC21", SS_path)	;
									WileyPLUS.clickOnSaveAndContinueButton();
									if(new BigDecimal(WileyPLUS.fetchFirstProductPriceInOrderReview().substring(1))
											.add(new BigDecimal(WileyPLUS.fetchShippingChargeInOrderReview().substring(1)))
											.add(new BigDecimal(WileyPLUS.fetchTaxInOrderReview().substring(1)))
											.compareTo(new BigDecimal(WileyPLUS.fetchTotalInOrderReview().substring(1)))==0)
										Reporting.updateTestReport("First Product price + Tax + Shipping charge"
												+ " = Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
									else
										Reporting.updateTestReport("First Product price+ Tax + Shipping charge"
												+ " is not equal to Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									WileyPLUS.clickOnPlaceOrderButton();
									String orderconfirmation = driver.getTitle();
									if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
										WileyPLUS.checkPrintReciept();
										WileyPLUS.checkTextInOrderConfirmationPage(
												excelOperation.getTestData("RegisteredUserOrderConfirmationText", "Generic_Messages", "Data"), driver);
										ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
										String orderId = WileyPLUS.fetchOrderId();
										excelOperation.updateTestData("TC21", "WileyPLUS_Test_Data", "Order_Id", orderId);
										excelOperation.updateTestData("TC21", "WileyPLUS_Test_Data", "Email_Id", emailId);
										ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
										String ordertotal = WileyPLUS.fetchOrderTotal();
										String taxamount = WileyPLUS.fetchTaxAmount();
										excelOperation.updateTestData("TC21", "WileyPLUS_Test_Data", "Order_Total", ordertotal);
										excelOperation.updateTestData("TC21", "WileyPLUS_Test_Data", "Tax", taxamount);
										driver.get(excelOperation.getTestData("Yopmail_URL",
												"Generic_Dataset", "Data"));
										WileyPLUS.enterEmailIdInYopmail(emailId);
										WileyPLUS.clickOnCheckInboxButton();
										if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
											ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
											Reporting.updateTestReport("Order Confirmation mail was received",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
											OrderConfirmationMail.validateOrderConfirmationMailContent("Wiley",driver,SS_path,taxamount," ",ordertotal);
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



								}
								catch(Exception e) {
									Reporting.updateTestReport("Cardholder name field in Card information"
											+ " section was not clickable and caused timeout exception"
											, CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
								}

							}
							catch(Exception e) {
								Reporting.updateTestReport("Shipping Address line 1 was not clickable"
										+ " and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e){
							Reporting.updateTestReport("User was not on cart page and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e){
						Reporting.updateTestReport("User couldn't add course and was not on Join Course page"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("User couldn't login and was not on my account page"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Create account form could not be submitted and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 30/01/23
	 * @Description: Validates the merge cart scenario
	 */
	@Test
	public void TC22_Merge_Cart_Functionality() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC22_Merge_Cart_Functionality");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WileyPLUS.wileyURLConcatenation("TC01", "WileyPLUS_Test_Data", "URL"));
			driver.get(excelOperation.getTestData("TC22", "WileyPLUS_Test_Data", "URL"));
			driver.navigate().refresh();
			WileyPLUS.clickOnCreateAccountLinkOnboarding();
			WileyPLUS.enterFirstNameInOnboardingCreateAccount(excelOperation.getTestData("TC22", "WileyPLUS_Test_Data", "First_Name"));
			Thread.sleep(3000);
			WileyPLUS.enterLastNameInOnboardingCreateAccount(excelOperation.getTestData("TC22", "WileyPLUS_Test_Data", "Last_Name"));
			String emailId=WileyPLUS.enterEmailIdInOnboardingCreateAccount();
			WileyPLUS.enterInstitutionNameInOnboardingCreateAccount(driver, excelOperation.getTestData("TC22", "WileyPLUS_Test_Data", "Institution"));
			Thread.sleep(2000);
			WileyPLUS.enterPasswordInOnboarding(excelOperation.getTestData("TC22", "WileyPLUS_Test_Data", "Password"));
			WileyPLUS.clickOnOnboardingCreateAccountCheckbox();
			WileyPLUS.clickOnOnboardingCreateAccountButton();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Check Your Email')]")));
				driver.get(excelOperation.getTestData("Yopmail_URL",
						"Generic_Dataset", "Data"));
				WileyPLUS.enterEmailIdInYopmail(emailId);
				WileyPLUS.clickOnCheckInboxButton();
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
				int timeOutSeconds=10;
				int flag=0;
				WebElement element1 = driver.findElement(By.xpath("//button[@id='refresh']"));
				WebElement element2 = null;
				/* The purpose of this loop is to wait for maximum of 60 seconds */
				for (int i = 0; i < timeOutSeconds / 5; i++) {

					try {
						driver.switchTo().frame("ifinbox");
						element2=wait1.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[contains(text(),'Welcome to Wiley')]")));

						if(element2.isDisplayed()==true)
						{
							flag=1;
							element2.click();
							driver.switchTo().defaultContent();
							break;
						}

					} catch (Exception e) {
						driver.switchTo().defaultContent();        
						element1.click();
					}
				}
				if(flag==1) {
					driver.switchTo().frame("ifmail");
					WileyPLUS.clickOnFinishRegistrationLinkInMail();
					driver.switchTo().defaultContent();
					Set<String> allWindowHandles = driver.getWindowHandles();
					java.util.Iterator<String> iterator = allWindowHandles.iterator();
					String yopmailHandle = iterator.next();
					String ChildWindow=iterator.next();
					driver.switchTo().window(yopmailHandle);
					driver.close();
					driver.switchTo().window(ChildWindow);
				}
				else {
					Reporting.updateTestReport("No finish registration mail  was recieved in yopmail inbox", 
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				WileyPLUS.enterEmailIdInOnboardingLogin(emailId);
				WileyPLUS.enterPasswordInOnboarding(excelOperation.getTestData("TC22", "WileyPLUS_Test_Data", "Password"));
				WileyPLUS.clickOnOnboardingLoginButton();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//span[contains(text(),'add course')]")));
					Thread.sleep(2000);
					WileyPLUS.clickOnOnboardingAddCourseButton();
					WileyPLUS.enterCourseSectionId(excelOperation.getTestData("TC22", "WileyPLUS_Test_Data", "Course"));
					WileyPLUS.clickOnContinueButtonInOnboarding();
					try {
						wait.until(ExpectedConditions.presenceOfElementLocated(
								By.xpath("//h2[contains(text(),'Join your course')]")));
						WileyPLUS.clickOnSingleTermRadioButtonInJoinCourse();
						WileyPLUS.clickOnContinueButtonInOnboarding();
						WileyPLUS.clickOnFirstPurchaseOption();
						WileyPLUS.clickOnContinueToCheckoutButton();
						try {
							wait.until(ExpectedConditions.
									presenceOfElementLocated
									(By.xpath("//p[contains(text(),' Your Cart')]")));
							WileyPLUS.checkIfUserIsOnCartPage(driver);
							try {
								WileyPLUS.clickOnHomePage();
								wait.until(ExpectedConditions.presenceOfElementLocated(
										By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
												+ " Education and Research')]")));
								WileyPLUS.searchProductInHomePageSearchBar(excelOperation.getTestData("TC19",
										"WileyPLUS_Test_Data", "SearchBox_Text"));
								WileyPLUS.clickOnSRP_WileyProduct();
								WileyPLUS.clickOnAddToCartButton();
								try {
									wait.until(ExpectedConditions.
											elementToBeClickable
											(By.xpath("//button[contains(text(),'View Cart')]")));
									WileyPLUS.clickOnViewCartButton();
									try {
										wait.until(ExpectedConditions.visibilityOf(WileyPLUS.getModalPopUp()));
										Reporting.updateTestReport("Currency change modal pop up was displayed successfully", 
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
										Thread.sleep(1000);
										WileyPLUS.clickOnContinueButtonOnModalPopUp();
										try {
											wait.until(ExpectedConditions.
													presenceOfElementLocated
													(By.xpath("//p[contains(text(),' Your Cart')]")));
											try {
												wait.until(ExpectedConditions.presenceOfElementLocated(
														By.xpath("//div[contains(text(),'USD')]")));
												Reporting.updateTestReport("Cart was merged successfully in USD", 
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
												WileyPLUS.getFirstProductsPrice();
												WileyPLUS.getSecondProductsPrice();
												driver.get(excelOperation.getTestData("TC22", "WileyPLUS_Test_Data", "URL"));
												try {
													wait.until(ExpectedConditions.elementToBeClickable(
															By.xpath("//span[contains(text(),'add course')]")));
													Thread.sleep(2000);
													WileyPLUS.clickOnOnboardingAddCourseButton();
													WileyPLUS.enterCourseSectionId(excelOperation.getTestData("TC22", "WileyPLUS_Test_Data", "Course"));
													WileyPLUS.clickOnContinueButtonInOnboarding();
													try {
														wait.until(ExpectedConditions.presenceOfElementLocated(
																By.xpath("//h2[contains(text(),'Join your course')]")));
														WileyPLUS.clickOnSingleTermRadioButtonInJoinCourse();
														WileyPLUS.clickOnContinueButtonInOnboarding();
														WileyPLUS.clickOnFirstPurchaseOption();
														WileyPLUS.clickOnContinueToCheckoutButton();
														try {
															wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
																	(By.id("add-to-cart-error-dialog")));
															driver.findElement(By.xpath("//span[contains(text(),'View Cart')]")).click();
															try {
																wait.until(ExpectedConditions.visibilityOf(WileyPLUS.getModalPopUp()));
																Reporting.updateTestReport("Currency change modal pop up was displayed successfully", 
																		CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
																Thread.sleep(1000);
																WileyPLUS.clickOnContinueButtonOnModalPopUp();
																try {
																	wait.until(ExpectedConditions.
																			presenceOfElementLocated
																			(By.xpath("//p[contains(text(),' Your Cart')]")));
																	try {
																		wait.until(ExpectedConditions.presenceOfElementLocated(
																				By.xpath("//div[contains(text(),'CAD')]")));
																		Reporting.updateTestReport("Cart was merged successfully in CAD", 
																				CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
																		WileyPLUS.getFirstProductsPrice();
																		WileyPLUS.getSecondProductsPrice();
																	}
																	catch(Exception e){
																		Reporting.updateTestReport("Cart was not merged in CAD", 
																				CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
																	}
																}
																catch(Exception e){
																	Reporting.updateTestReport("User was not on cart page and caused timeout exception",
																			CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
																}
															}
															catch(Exception e) {
																Reporting.updateTestReport("Currency change modal pop up was not displayed ", 
																		CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
															}

														}
														catch(Exception e) {
															Reporting.updateTestReport("The cart error dialog box didn't appear"
																	+ " and caused timeout exception",
																	CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
														}
													}
													catch(Exception e){
														Reporting.updateTestReport("User couldn't add course and was not on Join Course page"
																+ " and caused timeout exception",
																CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
													}
												}
												catch(Exception e) {
													Reporting.updateTestReport("User was not on my account page"
															+ " and caused timeout exception",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
												}

											}
											catch(Exception e){
												Reporting.updateTestReport("Cart was not merged in USD", 
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}

										}
										catch(Exception e){
											Reporting.updateTestReport("User was not on cart page and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}

									}
									catch(Exception e) {
										Reporting.updateTestReport("Currency change modal pop up was not displayed ", 
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								catch(Exception e) {
									Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e){
							Reporting.updateTestReport("User was not on cart page and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e){
						Reporting.updateTestReport("User couldn't add course and was not on Join Course page"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("User couldn't login and was not on my account page"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Create account form could not be submitted and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WileyPLUS.WileyLogOut();
		}
		catch(Exception e) {
			WileyPLUS.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
}


