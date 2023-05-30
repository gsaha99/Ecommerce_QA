package Test_Suite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.LogTextFile;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;

public class Manual_TestCase extends DriverModule{
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);

	@Test
	public void checkSeriesCategory() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("checkSeriesCategory");
			LogTextFile.writeTestCaseStatus("checkSeriesCategory", "Test case");
			File file =    new File(".\\Test Data\\Series.xlsx");
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb=new XSSFWorkbook(inputStream);
			XSSFSheet sheet=wb.getSheet("Series");
			ArrayList<String> myList = new ArrayList<String>();
			int rowsNumber=sheet.getLastRowNum()+1;
			System.out.println(rowsNumber);
			for(int i=1;i<rowsNumber;i++) {
				String value =new BigDecimal(sheet.getRow(i).getCell(0).toString()).setScale(0, RoundingMode.CEILING).toString();
				myList.add(value);
				//System.out.println(myList);
			}
			System.out.println(myList.size());
			driver.get("https://uat.hybris.wiley.com/backoffice/login.zul");
			Thread.sleep(1000);
			driver.findElement(By.name("j_username")).sendKeys("admin");
			driver.findElement(By.name("j_password")).sendKeys("Admin!23Pass");
			driver.findElement(By.xpath("//select[@class='z-select']")).click();
			driver.findElement(By.xpath("//select/option[text()='English']")).click();
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(45));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Logout']/span/img")));
				Reporting.updateTestReport("User was logged in in Hybris backoffice",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				driver.findElement(By.xpath("//input[@placeholder='Filter Tree entries']")).sendKeys("categories");
				Thread.sleep(1000);
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@title='Categories']/td/div/div/span")));
					driver.findElement(By.xpath("//tr[@title='Categories']/td/div/div/span")).click();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Switch search mode']")));
						driver.findElement(By.xpath("//button[@title='Switch search mode']")).click();
						driver.findElement(By.xpath("(//input[@value='Contains'])[1]")).click();
						driver.findElement(By.xpath("(//span[@class='z-comboitem-text'])[1]")).click();
						for(String each:myList) {
							driver.findElement(By.xpath
									("(//input[@class='ye-input-text ye-com_hybris_cockpitng_editor_defaulttext z-textbox'])[1]")).sendKeys(Keys.chord(Keys.CONTROL, "a"), each);
							/*Thread.sleep(2000);
						driver.findElement(By.xpath
								("(//input[@class='ye-input-text ye-com_hybris_cockpitng_editor_defaulttext z-textbox'])[1]")).sendKeys(each);*/
							driver.findElement(By.xpath("//button[@type='button' and text()='Search']")).click();
							Thread.sleep(2000);
							ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);

							try {
								//String title=driver.findElement(By.xpath("(//div[@class='z-listcell-content']/span)[3]")).getText();
								driver.findElement(By.xpath("(//div[@class='z-listcell-content']/span)[3]")).click();
								Thread.sleep(2000);
								String titleFromInputBox=driver.findElement
										(By.xpath("(//input[@class='ye-input-text ye-com_hybris_cockpitng_editor_defaulttext"
												+ " z-textbox'])[4]")).getAttribute("value");
								if(titleFromInputBox.contains("\"\""))
									Reporting.updateTestReport("The name of the series: "+titleFromInputBox+" for Category Id: "+each+" is containing \"\"",
											CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);

								else
									Reporting.updateTestReport("The name of the series: "+titleFromInputBox+" for Category Id: "+each+" didn't contain \"\"",
											CaptureScreenshot.getScreenshot(SS_path),StatusDetails.PASS);
								Row row = sheet.getRow(myList.indexOf(each)+1);
								Cell cell = row.createCell(1);
								cell.setCellType(cell.CELL_TYPE_STRING);
								cell.setCellValue(titleFromInputBox);
								System.out.println(titleFromInputBox+" "+myList.indexOf(each));


							}
							catch(Exception e) {
								Reporting.updateTestReport("No search result was present in Hybris backoffice for Category Id: "+ each,
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
							}
						}
						inputStream.close();
						FileOutputStream outPutStream = new FileOutputStream(file);
						wb.write(outPutStream);
						outPutStream.close();

					}
					catch(Exception e) {

					}
				}
				catch(Exception e) {

				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("User was not  logged in in Hybris backoffice", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		}
		catch(Exception e) {

		}
	}

	@Test
	public void checkCourseCategory() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("checkCourseCategory");
			LogTextFile.writeTestCaseStatus("checkCourseCategory", "Test case");
			File file =    new File(".\\Test Data\\Courses.xlsx");
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb=new XSSFWorkbook(inputStream);
			XSSFSheet sheet=wb.getSheet("Courses");
			ArrayList<String> myList = new ArrayList<String>();
			int rowsNumber=sheet.getLastRowNum()+1;
			System.out.println(rowsNumber);
			for(int i=1;i<rowsNumber;i++) {
				String value =sheet.getRow(i).getCell(0).toString();
				myList.add(value);
				//System.out.println(myList);
			}
			System.out.println(myList.size());
			driver.get("https://uat.hybris.wiley.com/backoffice/login.zul");
			Thread.sleep(1000);
			driver.findElement(By.name("j_username")).sendKeys("admin");
			driver.findElement(By.name("j_password")).sendKeys("Admin!23Pass");
			driver.findElement(By.xpath("//select[@class='z-select']")).click();
			driver.findElement(By.xpath("//select/option[text()='English']")).click();
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(45));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Logout']/span/img")));
				Reporting.updateTestReport("User was logged in in Hybris backoffice",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				driver.findElement(By.xpath("//input[@placeholder='Filter Tree entries']")).sendKeys("categories");
				Thread.sleep(1000);
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@title='Categories']/td/div/div/span")));
					driver.findElement(By.xpath("//tr[@title='Categories']/td/div/div/span")).click();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Switch search mode']")));
						driver.findElement(By.xpath("//button[@title='Switch search mode']")).click();
						driver.findElement(By.xpath("(//input[@value='Contains'])[1]")).click();
						driver.findElement(By.xpath("(//span[@class='z-comboitem-text'])[1]")).click();
						for(String each:myList) {
							driver.findElement(By.xpath
									("(//input[@class='ye-input-text ye-com_hybris_cockpitng_editor_defaulttext z-textbox'])[1]")).sendKeys(Keys.chord(Keys.CONTROL, "a"), each);
							/*Thread.sleep(2000);
						driver.findElement(By.xpath
								("(//input[@class='ye-input-text ye-com_hybris_cockpitng_editor_defaulttext z-textbox'])[1]")).sendKeys(each);*/
							driver.findElement(By.xpath("//button[@type='button' and text()='Search']")).click();
							Thread.sleep(2000);
							ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);

							try {
								//String title=driver.findElement(By.xpath("(//div[@class='z-listcell-content']/span)[3]")).getText();
								driver.findElement(By.xpath("(//div[@class='z-listcell-content']/span)[3]")).click();
								Thread.sleep(2000);
								String titleFromInputBox=driver.findElement
										(By.xpath("(//input[@class='ye-input-text ye-com_hybris_cockpitng_editor_defaulttext"
												+ " z-textbox'])[4]")).getAttribute("value");
								if(titleFromInputBox.contains("\"\""))
									Reporting.updateTestReport("The name of the series: "+titleFromInputBox+" for Category Id: "+each+" is containing \"\"",
											CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);

								else
									Reporting.updateTestReport("The name of the Course: "+titleFromInputBox+" for Category Id: "+each+" didn't contain \"\"",
											CaptureScreenshot.getScreenshot(SS_path),StatusDetails.PASS);
								Row row = sheet.getRow(myList.indexOf(each)+1);
								Cell cell = row.createCell(1);
								cell.setCellType(cell.CELL_TYPE_STRING);
								cell.setCellValue(titleFromInputBox);
								System.out.println(titleFromInputBox+" "+myList.indexOf(each));


							}
							catch(Exception e) {
								Reporting.updateTestReport("No search result was present in Hybris backoffice for Category Id: "+ each,
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
							}
						}
						inputStream.close();
						FileOutputStream outPutStream = new FileOutputStream(file);
						wb.write(outPutStream);
						outPutStream.close();

					}
					catch(Exception e) {

					}
				}
				catch(Exception e) {

				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("User was not  logged in in Hybris backoffice", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		}
		catch(Exception e) {

		}
	}

	@Test
	public void TC03_Check_Series_PDP() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC03_Check_Series_PDP");
			LogTextFile.writeTestCaseStatus("TC03_Check_Series_PDP", "Test case");
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
			driver.get("https://devmonkey:d3Vm0nK3y@uat.store.wiley.com/en-us/Perspectives+in+Nephrology+and+Hypertension-c-1209");
			File file =    new File(".\\Test Data\\Series_Category_Id_with_Name.xlsx");
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb=new XSSFWorkbook(inputStream);
			XSSFSheet sheet=wb.getSheet("Series");
			ArrayList<String> myList = new ArrayList<String>();
			ArrayList<String> myNames = new ArrayList<String>();
			int rowsNumber=sheet.getLastRowNum()+1;
			System.out.println(rowsNumber);
			for(int i=1;i<rowsNumber;i++) {
				String value =new BigDecimal(sheet.getRow(i).getCell(0).toString()).setScale(0, RoundingMode.CEILING).toString();
				myList.add(value);
				//System.out.println(myList);
			}
			for(int i=1;i<rowsNumber;i++) {
				String value =sheet.getRow(i).getCell(1).toString();
				myNames.add(value);
				//System.out.println(myList);
			}
			for(int j=0;j<myList.size();j++) {System.out.println(j);
				String url="https://uat.store.wiley.com/en-us/Perspectives+in+Nephrology+and+Hypertension-c-1209";
				String urlFirstPart=url.split("c-")[0];
				String newURL=urlFirstPart+"c-"+myList.get(j);
				driver.get(newURL);
				Thread.sleep(2000);
				try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//li[@class='active breadcrumbActiveLink' and text()='"+myNames.get(j)+"']")));
				Reporting.updateTestReport("THe category landing page had the same series name: "+myNames.get(j)+" as backoffice with URL: "+driver.getCurrentUrl(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("THe category landing page didn't have the same series name: "+myNames.get(j)+" as backoffice with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
			}

		}
		catch(Exception e) {

		}

	}
	
	@Test
	public void TC04_Check_Course_Category_Landing() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC04_Check_Course_Category_Landing");
			LogTextFile.writeTestCaseStatus("TC04_Check_Course_Category_Landing", "Test case");
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
			driver.get("https://devmonkey:d3Vm0nK3y@uat.store.wiley.com/en-us/Perspectives+in+Nephrology+and+Hypertension-c-1209");
			File file =    new File(".\\Test Data\\Courses_Category_Id_with_Name.xlsx");
			FileInputStream inputStream = new FileInputStream(file);
			XSSFWorkbook wb=new XSSFWorkbook(inputStream);
			XSSFSheet sheet=wb.getSheet("Courses");
			ArrayList<String> myList = new ArrayList<String>();
			ArrayList<String> myNames = new ArrayList<String>();
			int rowsNumber=sheet.getLastRowNum()+1;
			System.out.println(rowsNumber);
			for(int i=1;i<rowsNumber;i++) {
				String value =sheet.getRow(i).getCell(0).toString();
				myList.add(value);
				//System.out.println(myList);
			}
			for(int i=1;i<rowsNumber;i++) {
				String value =sheet.getRow(i).getCell(1).toString();
				myNames.add(value);
				//System.out.println(myList);
			}
			for(int j=0;j<myList.size();j++) {System.out.println(j);
				String url="https://uat.store.wiley.com/en-us/Perspectives+in+Nephrology+and+Hypertension-c-1209";
				String urlFirstPart=url.split("c-")[0];
				String newURL=urlFirstPart+"c-"+myList.get(j);
				driver.get(newURL);
				Thread.sleep(2000);
				try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
						"//li[@class='active breadcrumbActiveLink' and text()='"+myNames.get(j)+"']")));
				Reporting.updateTestReport("THe category landing page had the same series name: "+myNames.get(j)+" as backoffice with URL: "+driver.getCurrentUrl(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				catch(Exception e) {
					Reporting.updateTestReport("THe category landing page didn't have the same series name: "+myNames.get(j)+" as backoffice with URL: "+driver.getCurrentUrl(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
			}

		}
		catch(Exception e) {

		}

	}
}
