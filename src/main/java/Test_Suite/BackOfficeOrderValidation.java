package Test_Suite;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import PageObjectRepo.app_Hybris_BO_Repo;
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
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.name("j_username")));
				HybrisBO.enterHybrisBOUserName(excelOperation.getTestData("Backoffice_Admin_User_ID", "Generic_Dataset", "Data"));
				HybrisBO.enterHybrisBOUserName(excelOperation.getTestData("Backoffice_Admin_Password", "Generic_Dataset", "Data"));
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
				Reporting.updateTestReport("The username field in Hybris BO login page was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		catch(Exception e) {
			HybrisBO.clickOnLogOutButton();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

}
