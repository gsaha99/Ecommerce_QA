package TestSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjectRepo.app_Wiley_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.EmailValidation;
import utilities.LogTextFile;
import utilities.PaymentGateway;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class Wiley_Prod_Test_Suite extends DriverModule{
	app_Wiley_Repo wiley;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);

	@BeforeTest
	public void launchBrowser() {
		wiley = PageFactory.initElements(driver, app_Wiley_Repo.class);
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
	 * @Date: 03/04/23
	 * @Description: Validating microsites functionality
	 */
	@Test
	public void TC01_MicroSites() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC01_MicroSites");
			LogTextFile.writeTestCaseStatus("TC01_MicroStes", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC01", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			String title = driver.getTitle();
			if (title.equals("The Leadership Challenge"))
				Reporting.updateTestReport("Micro Sites Pages was loaded Successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the microsites page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			

		} catch (Exception e) {
			wiley.WileyLogOut();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 03/04/23
	 * @Description: Validating the Product Details Page functionality
	 */
	@Test
	public void TC02_ProductDetailsPage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC02_ProductDetailsPage");
			LogTextFile.writeTestCaseStatus("TC02_ProductDetailsPage", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC02", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.checkShopLinkInCartPageHeader();
			wiley.checkResearchLibrariesLinkInCartPageHeader();
			wiley.checkPublishingServicesLinkInCartPageHeader();
			wiley.checkProfessionalDevelopmentLinkInCartPageHeader();
			wiley.checkEducationResourcesoncartPage();
			ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
			wiley.checkSiteMaponPDP();
			wiley.checkRighrtAndPermissonsonPDP();
			wiley.checkTermsofUseonPDP();
			wiley.checkPrivacyPolicyonPDP();

		} catch (Exception e) {
			wiley.WileyLogOut();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 03/04/23
	 * 
	 * @Description: Validating Site Footer functionality
	 */
	@Test
	public void TC03_SiteFooter() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_SiteFooter");
			LogTextFile.writeTestCaseStatus("TC03_SiteFooter", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC03", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
			wiley.checkSiteMaponFooter();
			wiley.checkPrivacypolicyOnFooter();
			wiley.checkTermsofuseOnFooter();
			wiley.checkRighrtAndPermissonsOnFooter();

		} catch (Exception e) {
			wiley.WileyLogOut();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 03/04/23
	 * 
	 * @Description: Validating 404 error page functionality
	 */
	@Test
	public void TC04_404_Errorpage() throws IOException {
		try {

			Reporting.test = Reporting.extent.createTest("TC04_404_Error_Page");
			LogTextFile.writeTestCaseStatus("TC04_404_Error_Page", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC04", "WILEY_Test_Data", "URL"));
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
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 03/04/23
	 * 
	 * @Description: Validating About us page functionality
	 */
	@Test
	public void TC05_AboutUsPage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_About_Us_Page");
			LogTextFile.writeTestCaseStatus("TC05_About_Us_Page", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC05", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
			wiley.AboutWileyPage();
			String aboutUsPageTitle = driver.getTitle();
			if (aboutUsPageTitle.equals("About Wiley | Over 200 Years of Unlocking Human Potential"))
				Reporting.updateTestReport("The title of the Page : " + aboutUsPageTitle + " ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("New Cart was not merged with old cart",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		} catch (Exception e) {
			wiley.WileyLogOut();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 03/04/23
	 * @Description: Validating SiteMap functionality
	 */
	@Test
	public void TC06_SiteMap() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC06_SiteMap");
			LogTextFile.writeTestCaseStatus("TC06_SiteMap", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			driver.get(wiley.wileyURLConcatenation("TC06", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/sitemap']")));
				wiley.clickonsitemap();
				String sitemaptitle = driver.getTitle();
				if (sitemaptitle.equals("Site Map"))
					Reporting.updateTestReport("The title of the Page is : " + sitemaptitle + " ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Failed to load the SiteMap Page", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Sitemap link on footer was not clickable"
						+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}

		} catch (Exception e) {
			wiley.WileyLogOut();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 03/04/23
	 * 
	 * @Description: Verifying the ProductListingPage Functionality
	 */
	@Test
	public void TC07_ProductListPage() throws IOException {
		try {

			Reporting.test = Reporting.extent.createTest("TC07_ProductListPage");
			LogTextFile.writeTestCaseStatus("TC07_ProductListPage", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC07", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.searchTextInSearchBar(excelOperation.getTestData("TC07", "WILEY_Test_Data", "SearchBox_Text"));
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
	 * @Date: 03/04/23 
	 * @Description: Validating & Launching Home Page
	 */
	@Test
	public void TC08_HomePage() throws IOException {
		try {

			Reporting.test = Reporting.extent.createTest("TC08_HomePage");
			LogTextFile.writeTestCaseStatus("TC08_HomePage", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC08", "WILEY_Test_Data", "URL"));
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
	 * @Date: 03/04/23 
	 * @Description: Validating Site Header
	 */
	@Test
	public void TC09_SiteHeader() throws IOException {
		try {

			Reporting.test = Reporting.extent.createTest("TC09_SiteHeader");
			LogTextFile.writeTestCaseStatus("TC09_SiteHeader", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC09", "WILEY_Test_Data", "URL"));
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
	 * @Date: 03/04/23
	 * @Description: Validating Search Result Empty Page
	 */
	@Test
	public void TC10_SearchResultEmptyPage() throws IOException {
		try {

			Reporting.test = Reporting.extent.createTest("TC10_SearchResultEmptyPage");
			LogTextFile.writeTestCaseStatus("TC10_SearchResultEmptyPage", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC10", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.searchTextInSearchBar(excelOperation.getTestData("TC10", "WILEY_Test_Data", "SearchBox_Text"));
			String titleofthePage=driver.getTitle();
			if(titleofthePage.equals("Wiley"))
				Reporting.updateTestReport("Your Search didn;t match any results messsage is displayed", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load Results message", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);


		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 03/04/23
	 * @Description: Validating Search Result Empty Page
	 */
	@Test
	public void TC11_Add_To_Cart_PopUp() throws IOException {
		try {

			Reporting.test = Reporting.extent.createTest("TC11_Add_To_Cart_PopUp");
			LogTextFile.writeTestCaseStatus("TC11_Add_To_Cart_PopUp", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC11", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			wiley.clickOnViewCartButton();
		} catch (Exception e) {
			wiley.WileyLogOut();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 03/04/23
	 * 
	 * @Description: Validating Search Result Empty Page
	 */
	@Test
	public void TC12_Content_Search_ResultPage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC12_Content_Search_ResultPage");
			LogTextFile.writeTestCaseStatus("TC12_Content_Search_ResultPage", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC12", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.searchTextInSearchBar(excelOperation.getTestData("TC12", "WILEY_Test_Data", "SearchBox_Text"));
			wiley.ClickOnContentSearchOnPDPPage();
			ScrollingWebPage.PageScrolldown(driver,0,1700,SS_path);
		} catch (Exception e) {
			wiley.WileyLogOut();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 03/04/23 
	 * @Description: Validating Category Landing Page
	 */
	@Test
	public void TC13_Category_LandingPage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC13_Category_LandingPage");
			LogTextFile.writeTestCaseStatus("TC13_Category_LandingPage", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC13", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.ShopLinkHeaderCLPPage();
			ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
			wiley.checkFeaturedProductsOnCLPPage();
			wiley.ViewAllOnCLPPage();
		} catch (Exception e) {
			wiley.WileyLogOut();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 03/04/23
	 * @Description: Validating Category Landing Page
	 */
	@Test
	public void TC14_SearchBox() throws IOException {
		try {

			Reporting.test = Reporting.extent.createTest("TC14_SearchBox");
			LogTextFile.writeTestCaseStatus("TC14_SearchBox", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC14", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.ClickingOnHomePage();
			wiley.HomePageSearchBar(excelOperation.getTestData("TC14", "WILEY_Test_Data", "SearchBox_Text"));
			wiley.ClickingSearchAllResults();
		} catch (Exception e) {
			wiley.WileyLogOut();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 03/04/23
	 * @Description: Validates the Product Search Results Page
	 */
	@Test
	public void TC15_Product_Search_Results_Page_Facet_Validation() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC15_Product_Search_Results_Page");
			LogTextFile.writeTestCaseStatus("TC15_Product_Search_Results_Page", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC15", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.searchTextInSearchBar(excelOperation.getTestData("TC15", "WILEY_Test_Data", "SearchBox_Text"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			String newXpath="(//span[@class='search-highlight' and contains(text(),'"+
					excelOperation.getTestData("TC15", "WILEY_Test_Data", "SearchBox_Text")+"')])[1]";
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXpath)));
				wiley.checkProductsWithHighlightedSearchedTerm(newXpath);
				wiley.checkSubjectFacet();
				wiley.checkCourseFacet();
				wiley.checkAuthorFacet();
				wiley.checkFormatFacet();
				wiley.checkPublishedDateFacet();
				wiley.checkBrandsFacet();
				wiley.checkSeriesFacet();
				//Validation of Author Facet
				wiley.clickOnAuthorFacet();
				String facetTextAndQuantity=wiley.clickOnFirstFacetValue();
				String authorName=facetTextAndQuantity.split("#")[0];
				String quantity=facetTextAndQuantity.split("#")[1];
				wiley.checkNumberOfProductsAfterFiltering(quantity);
				int numberOfPages;
				if(Integer.parseInt(quantity)%10==0) 
					numberOfPages=Integer.parseInt(quantity)/10;
				else
					numberOfPages=Integer.parseInt(quantity)/10+1;
				if(wiley.fetchNumberOfPagesAfterFiltering()==numberOfPages)
					Reporting.updateTestReport("Pagination funationility is working fine after filtering with Author field",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Pagination funationility is not working",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				List<WebElement> filteredProducts=driver.findElements(By.className("product-title"));
				System.out.println(filteredProducts.size());
				int flag=0;
				for(int i=1;i<filteredProducts.size()+1;i++) {
					try {
						WebElement author=driver.findElement(By.xpath("(//div[@class='product-author'])"+"["+i+"]"));
						System.out.println(author.getText());
						if (author.getText().contains(authorName))
							System.out.println(i+"th iteration");
						else
						{flag=1;
						Reporting.updateTestReport("This product with title : "+
								driver.findElement(By.xpath("(//h3[@class='product-title']/a)"+"["+i+"]")).getText()
								+" has different author", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Author name field for each product was not found",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				if(flag==0) {
					Reporting.updateTestReport("All the filtered products has same Author as: "+authorName,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				wiley.clickOnResetFilter();
				//Validation of Format Facet
				wiley.clickOnFormatFacet();
				String facetTextAndQuantityForFormat=wiley.clickOnEBookFormatFacetValue();
				String format=facetTextAndQuantityForFormat.split("#")[0];
				String quantity1=facetTextAndQuantityForFormat.split("#")[1];
				wiley.checkNumberOfProductsAfterFiltering(quantity1);
				int numberOfPages1;
				if(Integer.parseInt(quantity)%10==0) 
					numberOfPages1=Integer.parseInt(quantity1)/10;
				else
					numberOfPages1=Integer.parseInt(quantity1)/10+1;
				if(wiley.fetchNumberOfPagesAfterFiltering()==numberOfPages1)
					Reporting.updateTestReport("Pagination funationility is working fine after filtering with Format field",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Pagination funationility is not working",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				List<WebElement> filteredProductsForFormat=driver.findElements(By.className("product-title"));
				int flag1=0;
				for(int i=1;i<filteredProductsForFormat.size()+1;i++) {
					try {
						WebElement formatFacet=driver.findElement(By.xpath("(//div[@class='product-content']/span[1])"+"["+i+"]"));
						if (formatFacet.getText().compareTo(format)==1)
						{flag1=1;
						Reporting.updateTestReport("This product with title : "+
								driver.findElement(By.xpath("(//h3[@class='product-title']/a)"+"["+i+"]")).getText()
								+" has different format", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Format  field for each product was not found",
								CaptureScreenshot.getScreenshot(quantity), StatusDetails.FAIL);
					}
				}
				if(flag1==0) {
					Reporting.updateTestReport("All the filtered products has same format as: "+format,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}

			}
			catch(Exception e) {
				Reporting.updateTestReport("Searched highlighted term was not displayed"
						+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}

		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 03/04/23
	 * @Description: Validates the UI of Cart page
	 */
	@Test
	public void TC16_Cart_Page_UI_Validation() throws IOException{
		try {

			Reporting.test = Reporting.extent.createTest("TC16_Cart_Page_UI_Validation");
			LogTextFile.writeTestCaseStatus("TC16_Cart_Page_UI_Validation", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC16", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				wiley.checkShopLinkInCartPageHeader();
				wiley.checkResearchLibrariesLinkInCartPageHeader();
				wiley.checkPublishingServicesLinkInCartPageHeader();
				wiley.checkProfessionalDevelopmentLinkInCartPageHeader();
				wiley.checkCartIcon();
				wiley.checkCartItemQuantity();
				wiley.checkBreadCrumbCartPage();
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeShipping","Generic_Messages", "Data"),driver);

			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.removeProductsFromCart(driver);
			wiley.WileyLogOut();

		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 3/4/23
	 * @Description: Validates the login page functionalities (Error message)
	 */
	@Test
	public void TC17_Login_Page_Validation() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC17_Login_Page_Validation");
			LogTextFile.writeTestCaseStatus("TC17_Login_Page_Validation", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC17", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();

				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				if(wiley.checkIfGuestCheckoutButtonIsPresent()) Reporting.updateTestReport("Guest checkout button is present in login page when only normal physical product is present in cart",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else Reporting.updateTestReport("Guest checkout button is not present in login page when only normal physical product is present in cart",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				wiley.clickOnCartIcon();
				wiley.searchDataInSearchBar(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "ISBN"));
				wiley.clickOnSRP_WileyProduct();
				BigDecimal priceOfSecondProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
				wiley.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions.
							elementToBeClickable
							(By.xpath("//button[contains(text(),'View Cart')]")));
					wiley.clickOnViewCartButton();
					BigDecimal ordertotalInCartPage=new BigDecimal(wiley.fetchOrderTotalInCartPage().substring(1));
					if(priceOfFirstProduct.add(priceOfSecondProduct).compareTo(ordertotalInCartPage)==0) 
						Reporting.updateTestReport(
								"The addition of all the products' price is same as the subtotal in cart page",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport(
								"The addition of all the products' pricedidn't match with the subtotal in cart page",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
					wiley.clickOnProceedToCheckoutButton();
					if(!wiley.checkIfGuestCheckoutButtonIsPresent()) Reporting.updateTestReport("Guest checkout button was not present in login page when digital product is present in cart",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else Reporting.updateTestReport("Guest checkout button is present in login page when digital product is present in cart",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					wiley.enterEmailIdInCreateAccountFormNotAutoGenerated(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Email_Id"));
					wiley.clickOnCreateAccountButton();
					wiley.checkErrorMessageAfterEnteringExistingUserInCreateAccount();
					wiley.enterExistingWileyUserMailID(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Email_Id"));
					wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Password"));
					wiley.clickOnLogInAndContinueButton();
					wiley.checkErrorMessageAfterEnteringWrongPassword();
				}
				catch(Exception e) {
					Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.removeProductsFromCart(driver);
			wiley.WileyLogOut();
			

		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 03/04/23
	 * @Description: This test case is about placing an order with Digital product with new user
	 */
	@Test
	public void TC18_Add_Digital_Product_to_cart_for_New_User() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC18_Add_Digital_Product_to_cart_for_New_User");
			LogTextFile.writeTestCaseStatus("TC18_Add_Digital_Product_to_cart_for_New_User", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC18", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				BigDecimal ordertotalInCartPage=new BigDecimal(wiley.fetchOrderTotalInCartPage().substring(1));
				if(priceOfFirstProduct.compareTo(ordertotalInCartPage)==0) 
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeShipping","Generic_Messages", "Data"),driver);
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				if(!wiley.checkIfGuestCheckoutButtonIsPresent()) Reporting.updateTestReport("Guest checkout button was not present in login"
						+ " page when digital/rental product is present in cart",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else Reporting.updateTestReport("Guest checkout button is present in login page when digital product is present in cart",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeShipping","Generic_Messages", "Data"),driver);
				wiley.confirmEmailIdInCreateAccountForm(email);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();	
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeBilling","Generic_Messages", "Data"),driver);
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					PaymentGateway.paymentWiley(driver, wiley, "TC18", SS_path);
					wiley.enterFirstName(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "First_Name"));
					wiley.enterLastName(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Last_Name"));
					wiley.selectCountry(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Bill_Country"));
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
						wiley.enterAddressLine1Billing(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Bill_Address_line1"));
						wiley.enterZipBilling(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Bill_Zip_Code"));
						wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Bill_Phone_Number"));
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
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.removeProductsFromCart(driver);
			wiley.WileyLogOut();
		}
		catch(Exception e){
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Author: Anindita
	 * @Description: This test case is about placing an order with Physical product with guest user
	 * @Date: 07/09/22
	 */
	@Test
	public void TC19_Add_Physical_Product_With_Guest_User() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC19_Add_Physical_Product_With_Guest_User");
			LogTextFile.writeTestCaseStatus("TC19_Add_Physical_Product_With_Guest_User", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(wiley.wileyURLConcatenation("TC19", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				BigDecimal ordertotalInCartPage=new BigDecimal(wiley.fetchOrderTotalInCartPage().substring(1));
				if(priceOfFirstProduct.compareTo(ordertotalInCartPage)==0) 
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeShipping","Generic_Messages", "Data"),driver);
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnContinueAsGuestButton();
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeBilling","Generic_Messages", "Data"),driver);
				wiley.enterFirstName(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));
					wiley.clickOnSaveAndContinueButton();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='wel_use_suggested_address_button']/span[text()='Use Selected Address'])[2]")));
						wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					}
					wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
							("OrderSummaryTabTextBeforeBilling","Generic_Messages", "Data"),driver);
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						PaymentGateway.paymentWiley(driver, wiley, "TC19", SS_path);

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

			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.removeProductsFromCart(driver);
			wiley.WileyLogOut();
		}
		catch(Exception e){
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	
	/*
	 * @Date: 3/4/23
	 * @Description: Adding one Physical product to cart and proceeding to checkout for the existing user
	 */
	@Test
	public void TC20_Add_Physical_product_ForExistingUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC20_Add_Physical_product_ForExistingUser");
			LogTextFile.writeTestCaseStatus("TC20_Add_Physical_product_ForExistingUser", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC20", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				wiley.selectQuantityDropDown(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Quantity"));
				BigDecimal quantity=new BigDecimal(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Quantity"));
				BigDecimal ordertotalInCartPage=new BigDecimal(wiley.fetchOrderTotalInCartPage().substring(1));
				if(priceOfFirstProduct.multiply(quantity).compareTo(ordertotalInCartPage)==0) 
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeShipping","Generic_Messages", "Data"),driver);
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String emailID=excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Email_Id");
				wiley.enterExistingWileyUserMailID(emailID);
				wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnLogInAndContinueButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeBilling","Generic_Messages", "Data"),driver);
				wiley.enterFirstName(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));
					wiley.clickOnSaveAndContinueButton();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='wel_use_suggested_address_button']/span[text()='Use Selected Address'])[2]")));
						wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					}
					wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
							("OrderSummaryTabTextBeforeBilling","Generic_Messages", "Data"),driver);
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						PaymentGateway.paymentWiley(driver, wiley, "TC20", SS_path);

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
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.removeProductsFromCart(driver);
			wiley.WileyLogOut();
		} catch (Exception e) {
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
}
