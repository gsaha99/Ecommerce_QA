package Test_Suite;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;


import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjectRepo.app_Wiley_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;

import java.util.Date;


public class Wiley_dot_com_Test_Suite extends DriverModule {
	app_Wiley_Repo wiley;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);

	@BeforeTest
	public void launchBrowser() {
		wiley = PageFactory.initElements(driver, app_Wiley_Repo.class);
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating microsites functionality
	 */
	@Test
	public void TC01_MicroSites() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC01_MicroStes");
			driver.get(wiley.wileyURLConcatenation("TC01", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			String title = driver.getTitle();
			if (title.equals("The Leadership Challenge"))
				Reporting.updateTestReport("Micro Sites Pages was loaded Successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the microsites page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			ScrollingWebPage.PageScrolldown(driver,0,19000);

		} catch (Exception e) {
			wiley.WileyLogOut();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating the Product Details Page functionality
	 */
	@Test
	public void TC02_ProductDetailsPage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC02_ProductDetailsPage");
			driver.get(wiley.wileyURLConcatenation("TC02", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.checkShopLinkInCartPageHeader();
			wiley.checkResearchLibrariesLinkInCartPageHeader();
			wiley.checkPublishingServicesLinkInCartPageHeader();
			wiley.checkProfessionalDevelopmentLinkInCartPageHeader();
			wiley.checkEducationResourcesoncartPage();
			ScrollingWebPage.PageScrolldown(driver,0,29000);
			wiley.checkSiteMapononpdppage();
			wiley.checkRighrtAndPermissonsononpdppage();
			wiley.checkTermsofuseonpdptpage();
			wiley.checkPrivacypolicyonpdppage();

		} catch (Exception e) {
			wiley.WileyLogOut();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating Site Footer functionality
	 */
	@Test
	public void TC03_SiteFooter() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_SiteFooter");
			driver.get(wiley.wileyURLConcatenation("TC03", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			ScrollingWebPage.PageScrolldown(driver,0,19000);
			wiley.checkSiteMaponfooter();
			wiley.checkPrivacypolicyOnFooter();
			wiley.checkTermsofuseOnFooter();
			wiley.checkRighrtAndPermissonsOnFooter();

		} catch (Exception e) {
			wiley.WileyLogOut();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating 404 error page functionality
	 */
	@Test
	public void TC04_404_Errorpage() throws IOException {
		try {
			
			Reporting.test = Reporting.extent.createTest("TC04_404 Error Page");
			driver.get(wiley.wileyURLConcatenation("TC04", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			String pagenotfound = driver.getTitle();
			if (pagenotfound.equals("404 Error Page | Wiley"))
				Reporting.updateTestReport("Page not found(404) is displayed ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load 404 Error page", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);

		} catch (Exception e) {
			wiley.WileyLogOut();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating About us page functionality
	 */
	@Test
	public void TC05_AboutUsPage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_About Us Page");
			driver.get(wiley.wileyURLConcatenation("TC05", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			ScrollingWebPage.PageScrolldown(driver,0,29000);
			wiley.AboutWileyPage();
			String aboutuspagetitle = driver.getTitle();
			if (aboutuspagetitle.equals("About Wiley | Over 200 Years of Unlocking Human Potential"))
				Reporting.updateTestReport("The title of the Page : " + aboutuspagetitle + " ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("New Cart was not merged with old cart",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		} catch (Exception e) {
			wiley.WileyLogOut();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating SiteMap functionality
	 */
	@Test
	public void TC06_SiteMap() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC06_SiteMap");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			driver.get(wiley.wileyURLConcatenation("TC06", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			ScrollingWebPage.PageScrolldown(driver,0,30000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/sitemap']")));
			//wiley.checkSiteMapononpdppage();
			wiley.clickonsitemap();
			String sitemaptitle = driver.getTitle();
			if (sitemaptitle.equals("Site Map"))
				Reporting.updateTestReport("The title of the Page is : " + sitemaptitle + " ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to load the SiteMap Page", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);

		} catch (Exception e) {
			wiley.WileyLogOut();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Verifying the ProductListingPage Functionality
	 */
	@Test
	public void TC07_ProductListPage() throws IOException {
		try {
			
			Reporting.test = Reporting.extent.createTest("TC07_ProductListPage");
			driver.get(wiley.wileyURLConcatenation("TC07", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.Entertextonsearcbar(excelOperation.getTestData("TC07", "WILEY_Dot_Com_Test_Data", "SearchBox_Text"));
			String plpproduct = wiley.PlpProductText();
			String pproduct = plpproduct.substring(0, 8);
			if (pproduct.equals("PRODUCTS"))
				Reporting.updateTestReport(
						"Product landing page was loaded Successfully and page having text PRODUCTS Headers Section",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the Product Landing Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			String plpcontent = wiley.PlpContentText();
			String pcontent = plpcontent.substring(0, 7);
			if (pcontent.equals("CONTENT"))
				Reporting.updateTestReport(
						"Product landing page was loaded Successfully and page having text CONTENT Headers Section",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the Product Landing Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		} catch (Exception e) {
			wiley.WileyLogOut();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating & Launching Home Page
	 */
	@Test
	public void TC08_HomePage() throws IOException {
		try {
			
			Reporting.test = Reporting.extent.createTest("TC08_HomePage");
			driver.get(wiley.wileyURLConcatenation("TC08", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			String titleofHomepgae = driver.getTitle();
			if (titleofHomepgae.equals("Wiley | Global Leader in Publishing, Education and Research"))
				Reporting.updateTestReport("Home Page was lauched Successfully", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the Home Page", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);

		} catch (Exception e) {
			wiley.WileyLogOut();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating Site Header
	 */
	@Test
	public void TC09_SiteHeader() throws IOException {
		try {
			
			Reporting.test = Reporting.extent.createTest("TC09_SiteHeader");
			driver.get(wiley.wileyURLConcatenation("TC09", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.checkShopLinkInHomePageHeader();
			wiley.checkResearchLibrariesLinkInHomePageHeader();
			wiley.checkPublishingServicesLinkInHomePageHeader();
			wiley.checkResearchLibrariesLinkInHomePageHeader();
			wiley.checkProfessionalDevelopmentLinkInHomePageHeader();
		} catch (Exception e) {
			wiley.WileyLogOut();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}


	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating Search Result Empty Page
	 */
	@Test
	public void TC10_SearchResultEmptyPage() throws IOException {
		try {
			
			Reporting.test = Reporting.extent.createTest("TC10_SearchResultEmptyPage");
			driver.get(wiley.wileyURLConcatenation("TC10", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.Entertextonsearcbar(excelOperation.getTestData("TC10", "WILEY_Dot_Com_Test_Data", "SearchBox_Text"));
			String titleofthePage=driver.getTitle();
			if(titleofthePage.equals("Wiley"))
				Reporting.updateTestReport("Your Search didn;t match any results messsage is displayed", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load Results message", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);

			
		} catch (Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating Search Result Empty Page
	 */
	@Test
	public void TC11_Add_To_PopUp() throws IOException {
		try {
			
			Reporting.test = Reporting.extent.createTest("TC11_Add_To_PopUp");
			driver.get(wiley.wileyURLConcatenation("TC11", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			wiley.clickOnViewCartButton();
		} catch (Exception e) {
			wiley.WileyLogOut();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating Search Result Empty Page
	 */
	@Test
	public void TC12_Content_Search_ResultPage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC12_Content_Search_ResultPage");
			driver.get(wiley.wileyURLConcatenation("TC12", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.Entertextonsearcbar(excelOperation.getTestData("TC12", "WILEY_Dot_Com_Test_Data", "SearchBox_Text"));
			wiley.SearchingFortheProduct();
			wiley.ClickOnContentSearchOnPDPPage();
			ScrollingWebPage.PageScrolldown(driver,0,1700);
		} catch (Exception e) {
			wiley.WileyLogOut();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating Category Landing Page
	 */
	@Test
	public void TC13_Category_LandingPage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC13_Category_LandingPage");
			driver.get(wiley.wileyURLConcatenation("TC13", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.ShopLinkHeaderCLPPage();
			ScrollingWebPage.PageScrolldown(driver,0,300);
			wiley.checkFeaturedProductsOnCLPPage();
			wiley.ViewAllOnCLPPage();
		} catch (Exception e) {
			wiley.WileyLogOut();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating Category Landing Page
	 */
	@Test
	public void TC14_SearchBox() throws IOException {
		try {
			
			Reporting.test = Reporting.extent.createTest("TC14_SearchBox");
			driver.get(wiley.wileyURLConcatenation("TC14", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.ClickingOnHomePage();
			wiley.HomePageSearchBar(excelOperation.getTestData("TC14", "WILEY_Dot_Com_Test_Data", "SearchBox_Text"));
			wiley.ClickingSearchAllResults();
			wiley.ClickSortByOptionPDPPage();
		} catch (Exception e) {
			wiley.WileyLogOut();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: Validates the Product Search Results Page
	 * @Date: 25/11/22
	 */
	@Test
	public void TC15_Product_Search_Results_Page() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC15_Product_Search_Results_Page");
			driver.get(wiley.wileyURLConcatenation("TC15", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.Entertextonsearcbar(excelOperation.getTestData("TC15", "WILEY_Dot_Com_Test_Data", "SearchBox_Text"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			String newXpath="(//span[@class='search-highlight' and contains(text(),'"+
					excelOperation.getTestData("TC15", "WILEY_Dot_Com_Test_Data", "SearchBox_Text")+"')])[1]";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXpath)));
			System.out.println(newXpath);
			wiley.checkProductsWithHighlightedSearchedTerm(newXpath);
			wiley.checkSubjectFacet();
			wiley.checkCourseFacet();
			wiley.checkAuthorFacet();
			wiley.checkFormatFacet();
			wiley.checkPublishedDateFacet();
			wiley.checkBrandsFacet();
			wiley.checkSeriesFacet();
			
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}


	
}
