package Test_Suite;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.LogTextFile;
import utilities.Reporting;
import utilities.StatusDetails;


public class ExcelRead extends DriverModule {

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

	@Test
	public void TC02_ProductDisplayPage() throws Exception {
		try {
			Reporting.test = Reporting.extent.createTest("TC02_ProductDisplayPage");
			LogTextFile.writeTestCaseStatus("TC02_ProductDisplayPage", "Test case");

			String excel = "C:\\Ecommerce_QA\\Storefront\\Ecommerce_QA\\Test Data\\Automation_Test(10).xlsx";
			FileInputStream file = new FileInputStream (excel);
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet sheet = wb.getSheet("StoreFront_PDP");
			String clp_solr = "-c-";
			String clp_constructor = "-c2-";
			int columnNumber=4;
			for (Row row : sheet) {
				Cell cell = row.getCell(columnNumber);
				String cellValue=cell.getStringCellValue();
				//System.out.println(cellValue);
				DriverModule.driver.get(cellValue);
				/*Hitting the last breadcrumb and changing it from Solr to Constructor Page*/
				try { 
					WebElement lastvalue= DriverModule.driver.findElement(By.xpath("//*[@id='breadcrumbStyle']/li[last()]"));
					lastvalue.click();
					Thread.sleep(5000);
					String Solr_URL=DriverModule.driver.getCurrentUrl();
					String Constructor_URL= Solr_URL.replace(clp_solr, clp_constructor);
					
					driver.get(Constructor_URL);
					/*WebElement breadcrumb= DriverModule.driver.findElement(By.xpath("//*[@id='breadcrumbStyle']"));
					ArrayList<String> nav= new ArrayList<String>();
					nav.add(breadcrumb.toString());
					System.out.println(nav);*/
					
					}catch (Exception e) {
					Reporting.updateTestReport("404 Error while clicking the breadcrumb " + e.getMessage(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					e.printStackTrace();
					}
			}
			wb.close();
			file.close();
		}catch (Exception e) {
			Reporting.updateTestReport("CLP did not get shifted from Solr to Constructor " + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			e.printStackTrace();
		}
	}
}