package Test_Suite;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import PageObjectRepo.Hybris_BO_Repo;
import PageObjectRepo.Store_Front_CLP_Repo;

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

public class StoreFront_Validate_CLP extends DriverModule {

	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	Store_Front_CLP_Repo StoreFrontRepo;

	@BeforeTest
	public void launchBrowser() {
		StoreFrontRepo = PageFactory.initElements(driver, Store_Front_CLP_Repo.class);
	}

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
			Reporting.test = Reporting.extent.createTest("TC02_ProductDisplayPage");
			LogTextFile.writeTestCaseStatus("TC02_ProductDisplayPage", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			String excel = ".\\Test Data\\Automation_Test(10).xlsx";
			FileInputStream file = new FileInputStream (excel);
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet sheet1 = wb.getSheet("StoreFront_PDP");
			
			String clp_solr = "-c-";
			String clp_constructor = "-c2-";
			int columnNumber=4;
			//CommonMethods.ReadExcel_TC02();
			
			for (Row row : sheet1) {
				Cell cell = row.getCell(columnNumber);
				String cellValue=cell.getStringCellValue();
				DriverModule.driver.get(cellValue);
				/*Hitting the last breadcrumb and changing it from Solr to Constructor Page*/
				try { 
					StoreFrontRepo.lastvalueClick();
					String Solr_URL=DriverModule.driver.getCurrentUrl();
					String Constructor_URL= Solr_URL.replace(clp_solr, clp_constructor);
					driver.get(Constructor_URL);
					WebElement breadcrumb_parent= DriverModule.driver.findElement(By.xpath("//*[@class='breadcrumb']"));
					List <WebElement> breadcrumb_child = breadcrumb_parent.findElements(By.tagName("li"));
					ArrayList<WebElement> breadcrumbli= new ArrayList<WebElement>(breadcrumb_child);
					for(int i=breadcrumbli.size()-1;i>1;i--) {
						WebElement breadcrumbItem=breadcrumbli.get(i);
						breadcrumbItem.click();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Find Wiley products based on your area of interest')]")));
							//Thread.sleep(3000);
						}catch (Exception e) {
							Reporting.updateTestReport("404 Error while clicking the breadcrumb and the correct Items are not dispayed" + cellValue + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}

				}catch (Exception e) {
					Reporting.updateTestReport("404 Error while hitting the PDP URL " + cellValue,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					e.printStackTrace();
				}
			}
		//	wb.close();
		//	file.close();
		}catch (Exception e) {
			Reporting.updateTestReport("CLP did not get shifted from Solr to Constructor " + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			e.printStackTrace();
		}
	}
}