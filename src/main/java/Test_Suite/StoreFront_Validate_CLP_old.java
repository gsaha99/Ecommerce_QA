package Test_Suite;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import utilities.CaptureScreenshot;
import utilities.CommonMethods;
import utilities.LogTextFile;
import utilities.DriverModule;

import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class StoreFront_Validate_CLP_old extends DriverModule {

	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);

	@BeforeMethod
	public void nameBefore(Method method) {
		System.out.println("Test case: " + method.getName() + " execution started");
	}

	@AfterMethod
	public void nameAfter(Method method) {
		System.out.println("Test case: " + method.getName() + " execution completed");
	}

	/*
	 * @Description: Validating the CLP URL from c to c2
	 * @Date: 29/05/23
	 */
	@Test(enabled=false)
	public void TC01_CategoryLandingPage() throws Exception {


		try {

			Reporting.test = Reporting.extent.createTest("TC01_CategoryLandingPage");
			LogTextFile.writeTestCaseStatus("TC01_CategoryLandingPage", "Test case");

			CommonMethods.createURLFile();

			/*Invoking browser & fetching all the links*/
			driver.get(excelOperation.getTestData("Subject_Page", "StoreFront_CLP", "Data"));

			WebElement subjects = driver.findElement(By.className("section-description"));
			List<WebElement> URLs = subjects.findElements(By.tagName("a"));

			for (WebElement link : URLs) {

				CommonMethods.AppendURLs(link.getAttribute("href"));
			}

			CommonMethods.ChangeURL();
			CommonMethods.hittingURL();
			CommonMethods.closeURLFile();

		} catch (Exception e) {
			Reporting.updateTestReport("CLP did not get shifted from Solr to Constructor " + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			e.printStackTrace();
		}
	}


	/*
	 * @Description: Validating the bread crumbs in PDP URL
	 * @Date: 13/06/23
	 */

	@Test
	public void TC02_ProductDisplayPage() throws Exception {
		try {
//			excelOperation.getTestDataPOI();
			/* String URL=null;
            for (int i=1;i<=160731;i++) {
	            System.out.println(Integer.toString(i));
	            URL= excelOperation.getTestData(Integer.toString(i),"StoreFront_PDP","workingURL");
	            driver.get(URL);
	            Thread.sleep(500);
	            Reporting.updateTestReport(driver.getTitle().toString(),
	                    CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
            }*/
		} catch (Exception e) {
			Reporting.updateTestReport("CLP did not get shifted from Solr to Constructor " + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			e.printStackTrace();
		}
	}
}