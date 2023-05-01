package Test_Suite;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjectRepo.app_Wiley_Repo;
import utilities.CaptureScreenshot;
import utilities.CommonFunctions;
import utilities.DriverModule;
import utilities.LogTextFile;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.lang.reflect.Method;


public class Wiley_dot_com_Test_Suite extends DriverModule {
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
	 * @Author: Vishnu
	 * 
	 * @Description: Validating microsites functionality
	 */
	@Test
	public void TC01_MicroSites() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC01_MicroSites");
			LogTextFile.writeTestCaseStatus("TC01_MicroStes", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC01", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			String title = driver.getTitle();
			if (title.equals("The Leadership Challenge"))
				Reporting.updateTestReport("Micro Sites Pages was loaded Successfully",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the microsites page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			ScrollingWebPage.PageScrolldown(driver,0,19000,SS_path);

		} catch (Exception e) {
			wiley.WileyLogOut(driver);
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
			LogTextFile.writeTestCaseStatus("TC02_ProductDetailsPage", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC02", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.checkShopLinkInCartPageHeader();
			wiley.checkResearchLibrariesLinkInCartPageHeader();
			wiley.checkPublishingServicesLinkInCartPageHeader();
			wiley.checkProfessionalDevelopmentLinkInCartPageHeader();
			wiley.checkEducationResourcesoncartPage();
			ScrollingWebPage.PageScrolldown(driver,0,29000,SS_path);
			wiley.checkSiteMapononpdppage();
			wiley.checkRighrtAndPermissonsononpdppage();
			wiley.checkTermsofuseonpdptpage();
			wiley.checkPrivacypolicyonpdppage();

		} catch (Exception e) {
			wiley.WileyLogOut(driver);
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
			LogTextFile.writeTestCaseStatus("TC03_SiteFooter", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC03", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			ScrollingWebPage.PageScrolldown(driver,0,19000,SS_path);
			wiley.checkSiteMaponfooter();
			wiley.checkPrivacypolicyOnFooter();
			wiley.checkTermsofuseOnFooter();
			wiley.checkRighrtAndPermissonsOnFooter();

		} catch (Exception e) {
			wiley.WileyLogOut(driver);
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

			Reporting.test = Reporting.extent.createTest("TC04_404_Error_Page");
			LogTextFile.writeTestCaseStatus("TC04_404_Error_Page", "Test case");
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
			wiley.WileyLogOut(driver);
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
			Reporting.test = Reporting.extent.createTest("TC05_About_Us_Page");
			LogTextFile.writeTestCaseStatus("TC05_About_Us_Page", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC05", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			ScrollingWebPage.PageScrolldown(driver,0,29000,SS_path);
			wiley.AboutWileyPage();
			String aboutuspagetitle = driver.getTitle();
			if (aboutuspagetitle.equals("About Wiley | Over 200 Years of Unlocking Human Potential"))
				Reporting.updateTestReport("The title of the Page : " + aboutuspagetitle + " ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("New Cart was not merged with old cart",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		} catch (Exception e) {
			wiley.WileyLogOut(driver);
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
			LogTextFile.writeTestCaseStatus("TC06_SiteMap", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			driver.get(wiley.wileyURLConcatenation("TC06", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			ScrollingWebPage.PageScrolldown(driver,0,30000,SS_path);
			try {
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
			}
			catch(Exception e) {
				Reporting.updateTestReport("Sitemap link on footer was not clickable"
						+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}

		} catch (Exception e) {
			wiley.WileyLogOut(driver);
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
			LogTextFile.writeTestCaseStatus("TC07_ProductListPage", "Test case");
			WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
			driver.get(wiley.wileyURLConcatenation("TC07", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("search-bar")));
				wiley.searchDataInSearchBar(excelOperation.getTestData("TC07", "WILEY_Dot_Com_Test_Data", "SearchBox_Text"));
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
			}catch(Exception e) {
				Reporting.updateTestReport("Search bar was not present in cart page and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		} catch (Exception e) {
			wiley.WileyLogOut(driver);
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
			LogTextFile.writeTestCaseStatus("TC08_HomePage", "Test case");
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
			wiley.WileyLogOut(driver);
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
			LogTextFile.writeTestCaseStatus("TC09_SiteHeader", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC09", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.checkShopLinkInHomePageHeader();
			wiley.checkResearchLibrariesLinkInHomePageHeader();
			wiley.checkPublishingServicesLinkInHomePageHeader();
			wiley.checkResearchLibrariesLinkInHomePageHeader();
			wiley.checkProfessionalDevelopmentLinkInHomePageHeader();
		} catch (Exception e) {
			wiley.WileyLogOut(driver);
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
			LogTextFile.writeTestCaseStatus("TC10_SearchResultEmptyPage", "Test case");
			WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
			driver.get(wiley.wileyURLConcatenation("TC10", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("search-bar")));
				wiley.searchDataInSearchBar(excelOperation.getTestData("TC10", "WILEY_Dot_Com_Test_Data", "SearchBox_Text"));
				String titleofthePage=driver.getTitle();
				if(titleofthePage.equals("Wiley"))
					Reporting.updateTestReport("Your Search didn;t match any results messsage is displayed", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.PASS);
				else
					Reporting.updateTestReport("Failed to Load Results message", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
			}catch(Exception e) {
				Reporting.updateTestReport("Search bar was not present in cart page and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}


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
	public void TC11_Add_To_Cart_PopUp() throws IOException {
		try {

			Reporting.test = Reporting.extent.createTest("TC11_Add_To_PopUp");
			LogTextFile.writeTestCaseStatus("TC11_Add_To_PopUp", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC11", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton(driver);
			wiley.clickOnViewCartButton();
		} catch (Exception e) {
			wiley.WileyLogOut(driver);
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
			LogTextFile.writeTestCaseStatus("TC12_Content_Search_ResultPage", "Test case");
			WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
			driver.get(wiley.wileyURLConcatenation("TC12", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("search-bar")));
				wiley.searchDataInSearchBar(excelOperation.getTestData("TC12", "WILEY_Dot_Com_Test_Data", "SearchBox_Text"));
				wiley.SearchingFortheProduct();
				wiley.ClickOnContentSearchOnPDPPage();
				ScrollingWebPage.PageScrolldown(driver,0,1700,SS_path);
			}catch(Exception e) {
				Reporting.updateTestReport("Search bar was not present in cart page and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			wiley.WileyLogOut(driver);
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
			LogTextFile.writeTestCaseStatus("TC13_Category_LandingPage", "Test case");
			driver.get(wiley.wileyURLConcatenation("TC13", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.ShopLinkHeaderCLPPage();
			ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
			wiley.checkFeaturedProductsOnCLPPage();
			wiley.ViewAllOnCLPPage();
		} catch (Exception e) {
			wiley.WileyLogOut(driver);
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
			LogTextFile.writeTestCaseStatus("TC14_SearchBox", "Test case");
			WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
			driver.get(wiley.wileyURLConcatenation("TC14", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.ClickingOnHomePage();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("searchbar")));
				wiley.HomePageSearchBar(excelOperation.getTestData("TC14", "WILEY_Dot_Com_Test_Data", "SearchBox_Text"));
				wiley.ClickingSearchAllResults();
				wiley.ClickSortByOptionPDPPage();
			}catch(Exception e) {
				Reporting.updateTestReport("Search bar was not present in cart page and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			wiley.WileyLogOut(driver);
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
	public void TC15_Product_Search_Results_Page_Facet_Validation() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC15_Product_Search_Results_Page");
			LogTextFile.writeTestCaseStatus("TC15_Product_Search_Results_Page", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			driver.get(wiley.wileyURLConcatenation("TC15", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("search-bar")));
				wiley.searchDataInSearchBar(excelOperation.getTestData("TC15", "WILEY_Dot_Com_Test_Data", "SearchBox_Text"));
				String newXpath="(//span[@class='search-highlight' and contains(text(),'"+
						excelOperation.getTestData("TC15", "WILEY_Dot_Com_Test_Data", "SearchBox_Text")+"')])[1]";
				try {
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
			}catch(Exception e) {
				Reporting.updateTestReport("Search bar was not present in cart page and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Validate the sorting functionality in search result page
	 * @Date: 3/3/23 
	 */
	@Test
	public void TC16_Sort_Functionality_In_SRP() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC16_Sort_Functionality_In_SRP");
			LogTextFile.writeTestCaseStatus("TC16_Sort_Functionality_In_SRP", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			driver.get(wiley.wileyURLConcatenation("TC16", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("search-bar")));
				wiley.searchDataInSearchBar(excelOperation.getTestData("TC16", "WILEY_Dot_Com_Test_Data", "SearchBox_Text"));
				String newXpath="(//span[@class='search-highlight' and contains(text(),'"+
						excelOperation.getTestData("TC16", "WILEY_Dot_Com_Test_Data", "SearchBox_Text")+"')])[1]";
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXpath)));
					wiley.checkProductsWithHighlightedSearchedTerm(newXpath);
					wiley.clickOnSortDropDown();
					WebElement l1=driver.findElement(By.id("sortOptions-button"));
					WebElement l=driver.findElement(By.xpath("//div[contains(text(),'Publication Date (newest-oldest)')]"));
					wiley.clickOnPublicationDateFromSortDropDown();
					/*Actions action = new Actions(driver);
				try {
					action.moveToElement(l1).click().perform();
					action.moveToElement(l).click().perform();
					}
				catch(Exception e) {
					Reporting.updateTestReport(e.getMessage(),CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
				}*/
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='ui-selectmenu-text' and contains(text(),"
							+ "'Publication Date (newest-oldest)')]")));
					//wait.until(ExpectedConditions.urlContains("publicationDate"));
					String date="Dec 2023";
					String previousMonth="Dec";
					String previousYear="2023";
					List<WebElement> filteredProducts=driver.findElements(By.className("product-title"));
					System.out.println(filteredProducts.size());
					String[] months=new String[] {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
					for(int i=1;i<filteredProducts.size()+1;i++) {
						try {
							WebElement publicationDate=driver.findElement(By.xpath(
									"(//div[@class='product-date wileyProductDateGroup wileyProductPubDate'])"+"["+i+"]"));
							if(i==1) {
								date=publicationDate.getText();
								previousMonth=date.split(" ")[0];
								previousYear=date.split(" ")[1];

							}
							else {
								String currentdate=publicationDate.getText();
								String currentMonth=currentdate.split(" ")[0];
								String currentYear=currentdate.split(" ")[1];
								if(Integer.parseInt(currentYear)<Integer.parseInt(previousYear))
									Reporting.updateTestReport("Current date: "+currentdate+" is older than previous date: "+date,
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								else {
									if(Integer.parseInt(currentYear)==Integer.parseInt(previousYear)) {
										if(Arrays.asList(months).indexOf(currentMonth)<Arrays.asList(months).indexOf(previousMonth))
											Reporting.updateTestReport("Current date: "+currentdate+" is older than previous date: "+date,
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
										else
											Reporting.updateTestReport("Current date: "+currentdate+" is not older than previous date: "+date,
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
									else
										Reporting.updateTestReport("Current date: "+currentdate+" is not older than previous date: "+date,
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
								date=currentdate;
								previousMonth=currentMonth;
								previousYear=currentYear;

							}


						}
						catch(Exception e) {
							Reporting.updateTestReport(e.getMessage()+" Publication date field for each product was not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}

				}
				catch(Exception e) {
					Reporting.updateTestReport(e.getMessage()+" Searched highlighted term was not displayed"
							+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}catch(Exception e) {
				Reporting.updateTestReport("Search bar was not present in cart page and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 27/04/23
	 * @Description: Checks the pagination functionality
	 */
	@Test
	public void TC17_Pagination_Functionality_In_Search_Result_Page() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC17_Pagination_Functionality_In_Search_Result_Page");
			LogTextFile.writeTestCaseStatus("TC17_Pagination_Functionality_In_Search_Result_Page", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC17", "WILEY_Dot_Com_Test_Data", "URL"));
			driver.navigate().refresh();
			try {
				wiley.clickOnHomePage();
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//title[contains(text(),'Wiley | Global Leader in Publishing,"
								+ " Education and Research')]")));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("searchbar")));
					wiley.searchProductInHomePageSearchBar(excelOperation.getTestData("TC17", "WILEY_Dot_Com_Test_Data", "SearchBox_Text"));
					int page=wiley.fetchNumberOfPagesAfterFiltering();
					String totalSearchedProducts=wiley.getNumberOfProductsInSearchResult();
					CommonFunctions.checkPaginationFunctionality(0, totalSearchedProducts, page, SS_path);
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
				}catch(Exception e) {
					Reporting.updateTestReport("Search bar was not present in home page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("User was not on homepage and caused timeout exception ",
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
