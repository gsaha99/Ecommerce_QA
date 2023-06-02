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
import org.testng.annotations.Test;
import PageObjectRepo.app_Hybris_BO_Repo;
import PageObjectRepo.app_Wiley_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.LogTextFile;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class BackOfficeOrderValidation  extends DriverModule{

	app_Hybris_BO_Repo HybrisBO;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	
	@BeforeTest
	public void launchBrowser() {
		HybrisBO = PageFactory.initElements(driver, app_Hybris_BO_Repo.class);
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
	 * @Description: Checks the Order details of one test case in backoffice
	 */
	@Test
	public void TC01_Check_Order_Details_for_Wiley() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC01_Check_Order_Details_for_Wiley");
			LogTextFile.writeTestCaseStatus("TC01_Check_Order_Details_for_Wiley", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			driver.get(excelOperation.getTestData("Backoffice_URL", "Generic_Dataset", "Data"));
			HybrisBO.enterHybrisBOUserName(excelOperation.getTestData("Backoffice_Admin_User_ID", "Generic_Dataset", "Data"));
			HybrisBO.enterHybrisBOPassword(excelOperation.getTestData("Backoffice_Admin_Password", "Generic_Dataset", "Data"));
			HybrisBO.selectBOLanguage();
			HybrisBO.clickOnHybrisBOLoginButton();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Logout']/span/img")));
				HybrisBO.checkIfUserLoggedInHybrisBO();
				HybrisBO.searchFieldInHybrisBO("orders");
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@title='Orders']/td/div/div/span")));
					HybrisBO.clickOnOrders();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Switch search mode']")));
						HybrisBO.clickOnSwitchSearchMode();
						HybrisBO.enterValueInAdvancedSearch(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Order_Id"));
						HybrisBO.clickOnSearchButtonInAdvanncedSearch();
						ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
									"//span[@class='yw-listview-cell-label z-label' and contains(text(),'"+
											excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Order_Id")+"')]")));
							HybrisBO.clickOnFirstSearchResult();
							HybrisBO.clickTopArrowButtonForExpand();
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated
										(By.xpath("//div[@class='z-caption-content' and contains(text(),'Common')]")));
								HybrisBO.clickOnUserSectionInOrderDetails(driver);
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated
											(By.xpath("//div[@class='z-caption-content' and contains(text(),'Edit item')]")));
									HybrisBO.checkFirstNameInOrderDetails(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "First_Name"));
									HybrisBO.checkLastNameInOrderDetails(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Last_Name"));
									HybrisBO.checkUserIdInOrderDetails(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Email_Id"));
								}
								catch(Exception e) {
									Reporting.updateTestReport("User section couldn't be opened and caused timeout exception"
											, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
								
							}
							catch(Exception e) {
								Reporting.updateTestReport("Order detaisl was not expanded after clciking top arrow button"
										+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("No search result was found for the order id: "+
									excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Order_Id"), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("The Search mode switch button was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("The Orders option in left pane was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("The logout button in Hybris BO homepage after login was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		}
		catch(Exception e) {
			//HybrisBO.clickOnLogOutButton();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

}
