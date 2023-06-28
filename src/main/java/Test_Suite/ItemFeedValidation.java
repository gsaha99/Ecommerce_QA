package Test_Suite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import PageObjectRepo.Hybris_BO_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.LogTextFile;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class ItemFeedValidation extends DriverModule {

	Hybris_BO_Repo HybrisBO;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);

	@BeforeTest
	public void launchBrowser() {
		HybrisBO = PageFactory.initElements(driver, Hybris_BO_Repo.class);
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
	 * @Description: Validating the Item feed in Hybris BO in Perf Environment
	 * @Date: 27/06/23
	 */
	@Test
//	public void TC01_ItemFeedValidation(String tcNo,String sheet) throws IOException {

		public void TC01_ItemFeedValidation(@Optional("TC01") String tcNo,@Optional("Item_Feed_Validation") String sheet) throws IOException {

			try {

			Reporting.test = Reporting.extent.createTest("TC01_ItemFeedValidation");
			LogTextFile.writeTestCaseStatus("TC01_ItemFeedValidation", "Test case");			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			
			driver.get(excelOperation.getTestData("Backoffice_URL_Perf", "StoreFront_CLP", "Data"));
			HybrisBO.enterHybrisBOUserName(excelOperation.getTestData("Backoffice_Admin_User_ID", "StoreFront_CLP", "Data"));
			HybrisBO.enterHybrisBOPassword(excelOperation.getTestData("Backoffice_Admin_Password", "StoreFront_CLP", "Data"));
			HybrisBO.selectBOLanguage();
			HybrisBO.clickOnHybrisBOLoginButton();
			
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Logout']/span/img")));
				HybrisBO.checkIfUserLoggedInHybrisBO();
				HybrisBO.searchFieldInHybrisBO("products");
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@title='Products']/td/div/div/span")));
					HybrisBO.clickOnProducts();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Switch search mode']")));
						HybrisBO.clickOnSwitchSearchMode();
						HybrisBO.enterValueInAdvancedSearch(excelOperation.getTestData(tcNo, sheet, "00028025"));
						HybrisBO.clickOnSearchButtonInAdvancedSearch();
						ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
									"(//span[@class='yw-listview-cell-label z-label' and contains(text(),'"+
											excelOperation.getTestData(tcNo, sheet, "00028025")+"')])[2]")));
							HybrisBO.clickOnSecondSearchResult();
							HybrisBO.clickTopArrowButtonForExpand();
						}
						catch (Exception e) {
							// TODO: handle exception
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}	
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			}
		
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}