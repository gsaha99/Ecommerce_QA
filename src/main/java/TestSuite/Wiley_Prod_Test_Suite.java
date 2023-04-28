package TestSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
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
import utilities.CommonFunctions;

public class Wiley_Prod_Test_Suite extends DriverModule{
	app_Wiley_Repo wiley;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	private static String Homepage = excelOperation.getTestData("Wiley_Homepage_URL", "Generic_Dataset", "Data");

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
	 * @Description: Validating microsites page if it is opening or not
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
	 * @Description: Validating Site Footer by checking all the elements of footer
	 */
	@Test
	public void TC03_SiteFooter() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_SiteFooter");
			LogTextFile.writeTestCaseStatus("TC03_SiteFooter", "Test case");
			driver.get(Homepage);
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
	 * @Description: Validating 404 error page after hitting some invalid URLS
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
	 * @Description: Validating the About us page if it is opening or not
	 */
	@Test
	public void TC05_AboutUsPage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_About_Us_Page");
			LogTextFile.writeTestCaseStatus("TC05_About_Us_Page", "Test case");
			driver.get(Homepage);
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
	 * @Description: Checks the Sitemap link in footer section
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
	 * @Description: Validates the Product l;isting page functionality
	 */
	@Test
	public void TC07_ProductListPage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC07_ProductListPage");
			LogTextFile.writeTestCaseStatus("TC07_ProductListPage", "Test case");
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
			int flag=0;
			driver.get(wiley.wileyURLConcatenation("TC07", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.searchTextInSearchBar(excelOperation.getTestData("TC07", "WILEY_Test_Data", "SearchBox_Text"));
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
				if(wiley.checkPlpProductTabNewSearch().trim().equals("Products"))
					Reporting.updateTestReport(
							"Product landing page was loaded Successfully and page having text Products Headers Section",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Failed to Load the Product Landing Page "+wiley.checkPlpProductTabNewSearch(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

				if (wiley.checkPlpContentTabNewSearch().equals("Content"))
					Reporting.updateTestReport(
							"Product landing page was loaded Successfully and page having text Content Headers Section",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Failed to Load the Product Landing Page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			}
			else {
				String plpProductText = wiley.PlpProductText().substring(0, 8);
				if (plpProductText.equals("PRODUCTS"))
					Reporting.updateTestReport(
							"Product landing page was loaded Successfully and page having text PRODUCTS Headers Section",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Failed to Load the Product Landing Page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				String plpContentText = wiley.PlpContentText().substring(0, 7);
				if (plpContentText.equals("CONTENT"))
					Reporting.updateTestReport(
							"Product landing page was loaded Successfully and page having text CONTENT Headers Section",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Failed to Load the Product Landing Page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}


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
			driver.get(Homepage);
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
	 * @Description: Validating Site Header in home page
	 */
	@Test
	public void TC09_SiteHeader() throws IOException {
		try {

			Reporting.test = Reporting.extent.createTest("TC09_SiteHeader");
			LogTextFile.writeTestCaseStatus("TC09_SiteHeader", "Test case");
			driver.get(Homepage);
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
	 * @Description: Validating Search Result Empty Page after searching something invalid text
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
	 * @Description: Validating the pop up after clicking on Add to cart button
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
			wiley.removeProductsFromCart(driver);
			wiley.WileyLogOut();
		} catch (Exception e) {
			wiley.WileyLogOut();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 03/04/23
	 * 
	 * @Description: Validating the Content Search Result page
	 */
	@Test
	public void TC12_Content_Search_ResultPage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC12_Content_Search_ResultPage");
			LogTextFile.writeTestCaseStatus("TC12_Content_Search_ResultPage", "Test case");
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
			int flag=0;
			driver.get(wiley.wileyURLConcatenation("TC12", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.searchTextInSearchBar(excelOperation.getTestData("TC12", "WILEY_Test_Data", "SearchBox_Text"));
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
				if (wiley.checkPlpContentTabNewSearch().equals("Content")) {
					Reporting.updateTestReport(
							"Product landing page was loaded Successfully and page having text Content Headers Section",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					wiley.clickOnContentTabNewSearch();
					ScrollingWebPage.PageScrolldown(driver,0,600,SS_path);
				}
				else
					Reporting.updateTestReport("Failed to Load the Product Landing Page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			}
			else {
				wiley.ClickOnContentSearchOnPDPPage();
				ScrollingWebPage.PageScrolldown(driver,0,1700,SS_path);
			}
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
	 * @Description: Validating the Searchbox in homepage
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
			int flag=0;
			driver.get(wiley.wileyURLConcatenation("TC15", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.searchTextInSearchBar(excelOperation.getTestData("TC15", "WILEY_Test_Data", "SearchBox_Text"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			String newXpath="(//span[@class='search-highlight' and contains(text(),'"+
					excelOperation.getTestData("TC15", "WILEY_Test_Data", "SearchBox_Text")+"')])[1]";

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
				wiley.checkProductsWithSearchedTermNewSearchPage(driver,
						excelOperation.getTestData("TC15", "WILEY_Test_Data", "SearchBox_Text"));
				wiley.checkSubjectFacetNewSearchPage();
				wiley.checkAuthorFacetNewSearchPage();
				wiley.checkFormatFacetNewSearchPage();
				wiley.checkPublishedDateFacetNewSearchPage();
				wiley.checkFeaturedFacetNewSearchPage();
				//Validation of Author Facet
				wiley.clickOnAuthorFacetNewSearchPage();
				ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
				String facetTextAndQuantity=wiley.clickOnMaxFacetValueNewSearchPage(driver);
				String authorName=facetTextAndQuantity.split("#")[0];
				String quantity=facetTextAndQuantity.split("#")[1];
				ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
				wiley.checkNumberOfProductsAfterFilteringNewSearchPage(quantity);
				int numberOfPages;
				if(Integer.parseInt(quantity)%15==0) 
					numberOfPages=Integer.parseInt(quantity)/15;
				else
					numberOfPages=Integer.parseInt(quantity)/15+1;
				if(wiley.fetchTotalNumberOfPagesInNewSearchPage(driver)==numberOfPages)
					Reporting.updateTestReport("Pagination funationility is working fine after filtering with Author field",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Pagination funationility is not working",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

				List<WebElement> filteredProducts=driver.findElements(By.className("product-card"));
				int flagForAuthor=0;
				for(int i=1;i<filteredProducts.size()+1;i++) {
					try {
						WebElement author=driver.findElement(By.xpath("(//div[@class='product-authors'])"+"["+i+"]"));
						if (author.getText().contains(authorName))
							System.out.println(i+"th iteration");
						else
						{flagForAuthor=1;
						Reporting.updateTestReport("This product with title : "+
								driver.findElement(By.xpath("(//h3[@class='product-title']/a)"+"["+i+"]")).getText()
								+" has different author", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);}
					}
					catch(Exception e1) {
						Reporting.updateTestReport("Author name ffield for each product was not found",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				if(flagForAuthor==0) {
					Reporting.updateTestReport("All the filtered products has same Author as: "+authorName,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
			}
			else {
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
					int flagForAuthor=0;
					for(int i=1;i<filteredProducts.size()+1;i++) {
						try {
							WebElement author=driver.findElement(By.xpath("(//div[@class='product-author'])"+"["+i+"]"));
							System.out.println(author.getText());
							if (author.getText().contains(authorName))
								System.out.println(i+"th iteration");
							else
							{flagForAuthor=1;
							Reporting.updateTestReport("This product with title : "+
									driver.findElement(By.xpath("(//h3[@class='product-title']/a)"+"["+i+"]")).getText()
									+" has different author", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);}
						}
						catch(Exception e1) {
							Reporting.updateTestReport("Author name ffield for each product was not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					if(flagForAuthor==0) {
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
						Reporting.updateTestReport("Pagination funationility is working fine after filtering with Format ffield",
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
						catch(Exception e2) {
							Reporting.updateTestReport("Format  ffield for each product was not found",
									CaptureScreenshot.getScreenshot(quantity), StatusDetails.FAIL);
						}
					}
					if(flag1==0) {
						Reporting.updateTestReport("All the filtered products has same format as: "+format,
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					}

				}
				catch(Exception e3) {
					Reporting.updateTestReport("Searched highlighted term was not displayed"
							+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
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
			driver.get(wiley.wileyURLConcatenation("TC16", "WILEY_Test_Data", "URL"));
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
			int flag=0;
			driver.get(wiley.wileyURLConcatenation("TC17", "WILEY_Test_Data", "URL"));
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
				wiley.searchDataInSearchBar(excelOperation.getTestData("TC17", "WILEY_Test_Data", "ISBN"));
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					flag=1;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				if(flag==1) {
					wiley.clickOnSRP_WileyProductNewSearchPage();
				}
				else {
					wiley.clickOnSRP_WileyProduct();
				}
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
					wiley.enterEmailIdInCreateAccountFormNotAutoGenerated(excelOperation.getTestData("TC17", "WILEY_Test_Data", "Email_Id"));
					wiley.clickOnCreateAccountButton();
					wiley.checkErrorMessageAfterEnteringExistingUserInCreateAccount();
					wiley.enterExistingWileyUserMailID(excelOperation.getTestData("TC17", "WILEY_Test_Data", "Email_Id"));
					wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC17", "WILEY_Test_Data", "Password"));
					ScrollingWebPage.PageScrolldown(driver, 0, 400, SS_path);
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
	 * @Description: Adds a Digital product to cart and going upto billing step with new user
	 */
	@Test
	public void TC18_Add_Digital_Product_to_cart_for_New_User() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC18_Add_Digital_Product_to_cart_for_New_User");
			LogTextFile.writeTestCaseStatus("TC18_Add_Digital_Product_to_cart_for_New_User", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC18", "WILEY_Test_Data", "URL"));
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
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC18", "WILEY_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();	
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeBilling","Generic_Messages", "Data"),driver);
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					PaymentGateway.paymentWiley(driver, wiley, "TC18", SS_path);
					wiley.enterFirstName(excelOperation.getTestData("TC18", "WILEY_Test_Data", "First_Name"));
					wiley.enterLastName(excelOperation.getTestData("TC18", "WILEY_Test_Data", "Last_Name"));
					wiley.selectCountry(excelOperation.getTestData("TC18", "WILEY_Test_Data", "Bill_Country"));
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
						wiley.enterAddressLine1Billing(excelOperation.getTestData("TC18", "WILEY_Test_Data", "Bill_Address_line1"));
						wiley.enterZipBilling(excelOperation.getTestData("TC18", "WILEY_Test_Data", "Bill_Zip_Code"));
						wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC18", "WILEY_Test_Data", "Bill_Phone_Number"));
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
	 * @Date: 04/04/23
	 * @Description: Adds a Physical product to cart and going upto billing step with guest user
	 */
	@Test
	public void TC19_Add_Physical_Product_With_Guest_User() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC19_Add_Physical_Product_With_Guest_User");
			LogTextFile.writeTestCaseStatus("TC19_Add_Physical_Product_With_Guest_User", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(wiley.wileyURLConcatenation("TC19", "WILEY_Test_Data", "URL"));
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
				wiley.enterFirstName(excelOperation.getTestData("TC19", "WILEY_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC19", "WILEY_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC19", "WILEY_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC19", "WILEY_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC19", "WILEY_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC19", "WILEY_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC19", "WILEY_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC19", "WILEY_Test_Data", "Shipping_Phone_Number"));
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
						Reporting.updateTestReport("Cardholder name ffield in Card information"
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
			driver.get(wiley.wileyURLConcatenation("TC20", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				wiley.selectQuantityDropDown(excelOperation.getTestData("TC20", "WILEY_Test_Data", "Quantity"));
				BigDecimal quantity=new BigDecimal(excelOperation.getTestData("TC20", "WILEY_Test_Data", "Quantity"));
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
				String emailID=excelOperation.getTestData("TC20", "WILEY_Test_Data", "Email_Id");
				wiley.enterExistingWileyUserMailID(emailID);
				wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC20", "WILEY_Test_Data", "Password"));
				wiley.clickOnLogInAndContinueButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeBilling","Generic_Messages", "Data"),driver);
				wiley.enterFirstName(excelOperation.getTestData("TC20", "WILEY_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC20", "WILEY_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC20", "WILEY_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC20", "WILEY_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC20", "WILEY_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC20", "WILEY_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC20", "WILEY_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC20", "WILEY_Test_Data", "Shipping_Phone_Number"));
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

	/*
	 * @Date: 4/4/23
	 * @Description: Verifies if the Shipping charge for multiple products are getting calculated properly
	 */
	@Test
	public void TC21_Shipping_Charge_For_Multiple_Products() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC21_Shipping_Charge_For_Multiple_Products");
			LogTextFile.writeTestCaseStatus("TC21_Shipping_Charge_For_Multiple_Products", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC21", "WILEY_Test_Data", "URL"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
				String emailID=excelOperation.getTestData("TC21", "WILEY_Test_Data", "Email_Id");
				wiley.enterExistingWileyUserMailID(emailID);
				wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC21", "WILEY_Test_Data", "Password"));
				wiley.clickOnLogInAndContinueButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeBilling","Generic_Messages", "Data"),driver);
				//By default the country is selected as US
				BigDecimal standardShippingChargeForOneUnit=wiley.fetchShippingCharge(driver, "Standard Shipping");
				BigDecimal expressShippingChargeForOneUnit=wiley.fetchShippingCharge(driver, "Express Shipping");
				BigDecimal nextDayShippingChargeForOneUnit=wiley.fetchShippingCharge(driver, "Next Day Shipping");
				//wiley.clickOnEnterNewAddresButtonInShipping();
				String country1=excelOperation.getTestData("TC21", "WILEY_Test_Data", "Shipping_Country").split(",")[0];
				String country2=excelOperation.getTestData("TC21", "WILEY_Test_Data", "Shipping_Country").split(",")[1];
				wiley.selectCountry(country1);
				//validation for Brzil/ columbia
				BigDecimal airMailChargeForOneUnit=wiley.fetchShippingCharge(driver, "Air Mail");
				BigDecimal courierChargeForOneUnit=wiley.fetchShippingCharge(driver, "Courier");
				wiley.selectCountry(country2);
				BigDecimal twoDayChargeForOneUnit=wiley.fetchShippingCharge(driver, "2-Day");
				wiley.clickOnCartIcon();
				String quantity=excelOperation.getTestData("TC21", "WILEY_Test_Data", "Quantity");
				wiley.selectQuantityDropDown(quantity);
				BigDecimal ordertotalInCartPage1=new BigDecimal(wiley.fetchOrderTotalInCartPage().substring(1));
				if(priceOfFirstProduct.multiply(new BigDecimal(quantity)).compareTo(ordertotalInCartPage1)==0) 
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				//By default the country is selected as US
				BigDecimal standardShippingChargeForMultiUnit=wiley.fetchShippingCharge(driver, "Standard Shipping");
				BigDecimal expressShippingChargeForMultiUnit=wiley.fetchShippingCharge(driver, "Express Shipping");
				BigDecimal nextDayShippingChargeForMultiUnit=wiley.fetchShippingCharge(driver, "Next Day Shipping");
				if((standardShippingChargeForOneUnit.add((new BigDecimal(quantity).subtract(new BigDecimal("1")))
						.multiply(new BigDecimal("0.01")))).setScale(2, RoundingMode.CEILING).compareTo(standardShippingChargeForMultiUnit)==0)
					Reporting.updateTestReport("Shipping charge has been correctly calculated for Standard shipping",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Shipping charge has been wrongly calculated for Standard shipping",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				if((expressShippingChargeForOneUnit.add((new BigDecimal(quantity).subtract(new BigDecimal("1")))
						.multiply(new BigDecimal("4")))).setScale(2, RoundingMode.CEILING).compareTo(expressShippingChargeForMultiUnit)==0)
					Reporting.updateTestReport("Shipping charge has been correctly calculated for Express shipping",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Shipping charge has been wrongly calculated for Express shipping",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				if((nextDayShippingChargeForOneUnit.add((new BigDecimal(quantity).subtract(new BigDecimal("1")))
						.multiply(new BigDecimal("4")))).setScale(2, RoundingMode.CEILING).compareTo(nextDayShippingChargeForMultiUnit)==0)
					Reporting.updateTestReport("Shipping charge has been correctly calculated for Next day shipping",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Shipping charge has been wrongly calculated for Next day shipping",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				//wiley.clickOnEnterNewAddresButtonInShipping();
				wiley.selectCountry(country1);
				//validation for Brzil/ columbia
				BigDecimal airMailChargeForMultiUnit=wiley.fetchShippingCharge(driver, "Air Mail");
				BigDecimal courierChargeForMultiUnit=wiley.fetchShippingCharge(driver, "Courier");
				if((airMailChargeForOneUnit.add((new BigDecimal(quantity).subtract(new BigDecimal("1")))
						.multiply(new BigDecimal("5")))).setScale(2, RoundingMode.CEILING).compareTo(airMailChargeForMultiUnit)==0)
					Reporting.updateTestReport("Air mail charge has been correctly calculated for Standard shipping",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Air mail charge has been wrongly calculated for Standard shipping",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				if((courierChargeForOneUnit.add((new BigDecimal(quantity).subtract(new BigDecimal("1")))
						.multiply(new BigDecimal("10")))).setScale(2, RoundingMode.CEILING).compareTo(courierChargeForMultiUnit)==0)
					Reporting.updateTestReport("Courier charge has been correctly calculated for Standard shipping",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Courier charge has been wrongly calculated for Standard shipping",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				wiley.selectCountry(country2);
				BigDecimal twoDayChargeForMultiUnit=wiley.fetchShippingCharge(driver, "2-Day");
				if((twoDayChargeForOneUnit.add((new BigDecimal(quantity).subtract(new BigDecimal("1")))
						.multiply(new BigDecimal("4")))).setScale(2, RoundingMode.CEILING).compareTo(twoDayChargeForMultiUnit)==0)
					Reporting.updateTestReport("2 day charge has been correctly calculated for Standard shipping",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("2 day charge has been wrongly calculated for Standard shipping",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);


			}catch(Exception e) {
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
	 * @Date: 4/4/23
	 * @Description: Entering different shipping and billing address
	 */
	@Test
	public void TC22_Shipping_And_BillingAddresses_different() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC22_Shipping_And_BillingAddresses_different");
			LogTextFile.writeTestCaseStatus("TC22_Shipping_And_BillingAddresses_different", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(wiley.wileyURLConcatenation("TC22", "WILEY_Test_Data", "URL"));
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
				wiley.clickOnCreateAccountButton();
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeShipping","Generic_Messages", "Data"),driver);
				wiley.confirmEmailIdInCreateAccountForm(email);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC22", "WILEY_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeBilling","Generic_Messages", "Data"),driver);
				wiley.enterFirstName(excelOperation.getTestData("TC22", "WILEY_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC22", "WILEY_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC22", "WILEY_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(
							excelOperation.getTestData("TC22", "WILEY_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC22", "WILEY_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC22", "WILEY_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC22", "WILEY_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(
							excelOperation.getTestData("TC22", "WILEY_Test_Data", "Shipping_Phone_Number"));
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
					driver.switchTo().frame(0);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						PaymentGateway.paymentWiley(driver, wiley, "TC22", SS_path);
						driver.findElement(By.xpath("//label[@id='sameAsBillingLabel']")).click();
						wiley.enterFirstName(excelOperation.getTestData("TC22", "WILEY_Test_Data", "First_Name"));
						wiley.enterLastName(excelOperation.getTestData("TC22", "WILEY_Test_Data", "Last_Name"));
						wiley.selectCountry(excelOperation.getTestData("TC22", "WILEY_Test_Data", "Bill_Country"));
						try{
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
							wiley.enterAddressLine1Billing(excelOperation.getTestData("TC22", "WILEY_Test_Data", "Bill_Address_line1"));
							try{
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='postalCode']")));
								wiley.enterZipBilling(excelOperation.getTestData("TC22", "WILEY_Test_Data", "Bill_Zip_Code"));
								try{
									wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='city']")));
									wiley.enterCityBilling(excelOperation.getTestData("TC22", "WILEY_Test_Data", "Bill_City"));
									wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC22", "WILEY_Test_Data", "Bill_Phone_Number"));
								}
								catch(Exception e) {
									Reporting.updateTestReport("Billing City was not clickable"
											+ " and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("Billing zip code was not clickable"
										+ " and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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

	/*
	 * @Date: 4/4/23
	 * @Description: Adds a product to cart with a promo and proceeds to checkout upto billing step
	 */
	@Test
	public void TC23_Add_Coupon_To_Cart() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC23_Add_Coupon_To_Cart");
			LogTextFile.writeTestCaseStatus("TC23_Add_Coupon_To_Cart", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(wiley.wileyURLConcatenation("TC23", "WILEY_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,400,SS_path);
				wiley.clickOnPromotionCodelink();
				wiley.enterPromoCode(excelOperation.getTestData("WILEY_PROMO_SDP66", "Generic_Dataset", "Data"));
				wiley.ApplyPromo();
				BigDecimal subtotal=new BigDecimal(wiley.fetchOrderSubTotalInCartPage().substring(1));
				BigDecimal ordertotalInCart=new BigDecimal(wiley.fetchOrderTotalInCartPage().substring(1));
				BigDecimal discount=new BigDecimal(wiley.fetchDiscountAmountInCartPage().substring(2));
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
				if(priceOfFirstProduct.subtract(discount).compareTo(ordertotalInCart)==0) 
					Reporting.updateTestReport(
							"The addition of all the products' price and the subtraction of the discount"
									+ " is same as the subtotal in cart page",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' price and the subtraction of the discount "
									+ "didn't match with the subtotal in cart page",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeShipping","Generic_Messages", "Data"),driver);
				ScrollingWebPage.PageScrolldown(driver,0,900,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String emailID = wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeShipping","Generic_Messages", "Data"),driver);
				wiley.confirmEmailIdInCreateAccountForm(emailID);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC23", "WILEY_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.checkTextInOrderSummaryTab(excelOperation.getTestData
						("OrderSummaryTabTextBeforeBilling","Generic_Messages", "Data"),driver);
				wiley.enterFirstName(excelOperation.getTestData("TC23", "WILEY_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC23", "WILEY_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC23", "WILEY_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC23", "WILEY_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC23", "WILEY_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC23", "WILEY_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC23", "WILEY_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC23", "WILEY_Test_Data", "Shipping_Phone_Number"));
					wiley.selectShippingMethod();
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
					driver.switchTo().frame(0);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						PaymentGateway.paymentWiley(driver, wiley, "TC23", SS_path);
					}
					catch(Exception e) {
						Reporting.updateTestReport("Shipping Address line 1 was not clickable"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.removeProductsFromCart(driver);
			wiley.WileyLogOut();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Validates the VAT Tooltip for Asian Countries
	 */
	@Test
	public void TC24_VAT_Tooltip_For_Asian_Countries() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC24_VAT_Tooltip_For_Asian_Countries");
			//VAT Tooltip should be present for China only
			String[] regions=excelOperation.getTestData("TC24", "WILEY_Test_Data", "URL").split(",");
			String region1=regions[0];
			String region2=regions[1];//Any Asian country other than china (Korea)
			driver.get(wiley.wileyURLConcatenationwithRegions(region1,
					excelOperation.getTestData("TC24", "WILEY_Test_Data", "ISBN")));
			driver.navigate().refresh();
			if(wiley.checkVAT_Tooltip(driver).
					contains(excelOperation.getTestData("VAT_Tooltip_Text", "Generic_Dataset", "Data").trim()))
				Reporting.updateTestReport("Correct text was present in VAT Tooltip for China",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Correct text was not present in VAT Tooltip for China"
						+wiley.checkVAT_Tooltip(driver).length()+" "+
						excelOperation.getTestData("VAT_Tooltip_Text", "Generic_Dataset", "Data").trim().length(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			driver.get(wiley.wileyURLConcatenationwithRegions(region2,
					excelOperation.getTestData("TC24", "WILEY_Test_Data", "ISBN")));
			driver.navigate().refresh();
			if(wiley.checkVAT_Tooltip(driver).
					contains(excelOperation.getTestData("VAT_Tooltip_Text", "Generic_Dataset", "Data").trim()))
				Reporting.updateTestReport("Correct text was present in VAT Tooltip for "+region2+" (Asian country other than china)",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			else
				Reporting.updateTestReport("Correct text was not present in VAT Tooltip for "+region2+" (Asian country other than china)"
						,CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 4/4/23
	 * @Description: Validates the updated hover text for E-Books in Product details page
	 */
	@Test
	public void TC25_Generic_info_hover_text_EBook_PDP() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC25_Generic_info_hover_text_EBook_PDP");
			LogTextFile.writeTestCaseStatus("TC25_Generic_info_hover_text_EBook_PDP", "Test case");
			String[] regions=excelOperation.getTestData("TC25", "WILEY_Test_Data", "URL").split(",");
			String[] products=excelOperation.getTestData("TC25", "WILEY_Test_Data", "ISBN").split(",");
			for(String region:regions) {
				for(String product:products) {
					driver.get(wiley.wileyURLConcatenationwithRegions(region,product));
					driver.navigate().refresh();
					if(wiley.fetchGenericHoverInfo(driver).
							contains(excelOperation.getTestData("TC25", "WILEY_Test_Data", "Expected_Result").trim()))
						Reporting.updateTestReport("Correct text was present in generic info for eBook",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport("Correct text was not present in generic info for eBook"
								+wiley.fetchGenericHoverInfo(driver).length()+" "+
								excelOperation.getTestData("TC25", "WILEY_Test_Data", "Expected_Result").trim().length(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			wiley.WileyLogOut();

		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Validate the sorting functionality in search result page
	 * @Date: 21/04/23
	 */
	@Test
	public void TC26_Sort_Functionality_In_SRP_With_Pagination() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC26_Sort_Functionality_In_SRP");
			LogTextFile.writeTestCaseStatus("TC26_Sort_Functionality_In_SRP", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
			driver.get(Homepage);
			boolean flag=false;
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				wiley.searchProductInHomePageSearchBar(excelOperation.getTestData("TC26", "WILEY_Test_Data", "SearchBox_Text"));				
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-card'])[1]")));
					Reporting.updateTestReport("New Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					flag=true;
				}
				catch(Exception e) {
					Reporting.updateTestReport("Old Search page came with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
				if(flag) {
					wiley.checkSortDropDownInSearchResultPageNewSearch(driver);
					int page=wiley.fetchTotalNumberOfPagesInNewSearchPage(driver);

					//Sort dropdown takes 3 different options: 1)"Relevance", 2)"Author's Name (A-Z)" 3) "Product's Name (A-Z)"

					//Sorting by products' name
					wiley.selectSortOptionNewSearch("Product's Name (A-Z)");
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//select[@id='sortSelect']/option[@value='item_name_ascending']")));
						List<String> productNameListAfterSorting=wiley.getProductNameListFromNewSearch(driver);
						if(CommonFunctions.checkAscendingOrderStringList(productNameListAfterSorting)==1)
							Reporting.updateTestReport("All the elements are correctly sorted in Ascending order",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						else if(CommonFunctions.checkAscendingOrderStringList(productNameListAfterSorting)==0)
							Reporting.updateTestReport("All the elements are not sorted in Ascending order",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						else
							Reporting.updateTestReport("The string list sorting couldn't be validated",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

						//Sorting by authors' name
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//select[@id='sortSelect']/option[@value='author_ascending']")));
							wiley.selectSortOptionNewSearch("Author's Name (A-Z)");
							List<String> authorsNameListAfterSorting=wiley.getAuthorNameListFromNewSearch(driver);
							if(CommonFunctions.checkAscendingOrderStringList(authorsNameListAfterSorting)==1)
								Reporting.updateTestReport("All the elements are correctly sorted in Ascending order",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
							else if(CommonFunctions.checkAscendingOrderStringList(authorsNameListAfterSorting)==0)
								Reporting.updateTestReport("All the elements are not sorted in Ascending order",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							else
								Reporting.updateTestReport("The string list sorting couldn't be validated",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

							//Checking the number of pages through calculation
							String totalSearchedProducts=wiley.checkNumberOfProductsInNewSearchPage();
							CommonFunctions.checkPaginationFunctionality(flag, totalSearchedProducts, page, SS_path);
							for(int i=0;i<page;i++) {

								//for last page
								if(i==page-1) {
									wiley.checkIfNextButtonDisabledInNewSearchPage();
									try {
										wait.until(ExpectedConditions.visibilityOfElementLocated
												(By.xpath("//div[@class='search-result-page-header']/h1")));
										List<WebElement> products=driver.findElements(By.xpath("//div[@class='product-card']"));
										int numberOfProductsInLastPage=products.size();
										if(Integer.parseInt(totalSearchedProducts)%15==numberOfProductsInLastPage)
											Reporting.updateTestReport("Correct number of products: "+numberOfProductsInLastPage+" is present in last page",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
										else
											Reporting.updateTestReport("Incorrect number of products: "+numberOfProductsInLastPage+" is present in last page",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}

									catch(Exception e) {
										Reporting.updateTestReport("The page didn't scrolled up to top",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}

								//for other pages except last pages
								else {									
									//Extra step for first page
									if(i==0) 
										wiley.checkIfPreviousButtonDisabledInNewSearchPage();
									try {
										wait.until(ExpectedConditions.visibilityOfElementLocated
												(By.xpath("//div[@class='search-result-page-header']/h1")));
										ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
										try {
											wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/terms-of-use']")));
											ScrollingWebPage.PageScrollUp(driver, 0, -200, SS_path);
											try {
												wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='Next page']")));
												wiley.clickOnNextButtonInNewSearchPage();
												Thread.sleep(1000);
											}
											catch(Exception e) {
												Reporting.updateTestReport("Next button was not clickable and caused timeout exception",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										catch(Exception e) {
											Reporting.updateTestReport("The page didn't scrolled down to bottom",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									}
									catch(Exception e) {
										Reporting.updateTestReport("The page didn't scrolled up to top",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
							}
						}

						catch(Exception e) {
							Reporting.updateTestReport("After sorting the page, the products were not clickable",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("After sorting the page, the products were not clickable",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}

				}
				
				//This is for old search page
				else {
					int page=wiley.fetchNumberOfPagesAfterFiltering();
					String totalSearchedProducts=wiley.getNumberOfProductsInSearchResult();
					CommonFunctions.checkPaginationFunctionality(flag, totalSearchedProducts, page, SS_path);
					for(int i=0;i<page;i++) {

						//for last page
						if(i==page-1) {
							wiley.checkIfNextButtonDisabled();
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated
										(By.xpath("//a[contains(text(),'PRODUCTS')]")));
								List<WebElement> products=driver.findElements(By.xpath("//section[@class='product-item']"));
								int numberOfProductsInLastPage=products.size();
								if(Integer.parseInt(totalSearchedProducts)%10==numberOfProductsInLastPage)
									Reporting.updateTestReport("Correct number of products: "+numberOfProductsInLastPage+" is present in last page",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								else
									Reporting.updateTestReport("Incorrect number of products: "+numberOfProductsInLastPage+" is present in last page",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

							catch(Exception e) {
								Reporting.updateTestReport("The page didn't scrolled up to top",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}

						//for other pages except last pages
						else {									
							//Extra step for first page
							if(i==0) 
								wiley.checkIfPreviousButtonDisabled();
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated
										(By.xpath("//a[contains(text(),'PRODUCTS')]")));
								
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@title='Next page'])[1]")));
										wiley.clickOnNextButton();
										Thread.sleep(1000);
									}
									catch(Exception e) {
										Reporting.updateTestReport("Next button was not clickable and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								
							}
							catch(Exception e) {
								Reporting.updateTestReport("The page didn't scrolled up to top",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
					}
					
				}


			}
			catch(Exception e) {
				Reporting.updateTestReport("Homepage couldn't be loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}


		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
}
