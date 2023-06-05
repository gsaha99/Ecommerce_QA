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
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class Manual_TestCase extends DriverModule{
	app_Wiley_Repo wiley;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);

	@BeforeTest
	public void launchBrowser() {
		wiley = PageFactory.initElements(driver, app_Wiley_Repo.class);
	}

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
			driver.get("https://qa.hybris.wiley.com/backoffice/login.zul");
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
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='z-listcell-content']/span)[3]")));
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
			driver.get("https://qa.hybris.wiley.com/backoffice/login.zul");
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
									Reporting.updateTestReport("The name of the Course: "+titleFromInputBox+" for Category Id: "+each+" is containing \"\"",
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
			String newURL=urlFirstPart+"c2-"+myList.get(j);
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
			driver.get("https://devmonkey:d3Vm0nK3y@qa.store.wiley.com/en-us/Perspectives+in+Nephrology+and+Hypertension-c-1209");
			File file =    new File(".\\Test Data\\Courses.xlsx");
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
			String url="https://qa.store.wiley.com/en-us/Perspectives+in+Nephrology+and+Hypertension-c-1209";
			String urlFirstPart=url.split("c-")[0];
			String newURL=urlFirstPart+"c2-"+myList.get(j);
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

	/*
	 * @Author: Anindita
	 * @Description: Validates if it is redirecting to secure.wiley.com link after clicking 
	 * on Request Digital Evaluation copy link for certain regions
	 */
	@Test
	public void DEC_Link_Updates() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("DEC_Link_Updates");
			LogTextFile.writeTestCaseStatus("DEC_Link_Updates", "Test case");
			String[] regions=excelOperation.getTestData("TC35", "WILEY_NA_Cart_Test_Data", "URL").split(",");
			String[] products=excelOperation.getTestData("TC35", "WILEY_NA_Cart_Test_Data", "ISBN").split(",");
			for (String region:regions) {
				for(String product:products) {
					driver.get(wiley.wileyURLConcatenationwithRegions(region,
							product));
					driver.navigate().refresh();
					try {
						wiley.clickOnRequestDigitalEvaluationCopyLink();
						Set<String> allWindowHandles = driver.getWindowHandles();
						java.util.Iterator<String> iterator = allWindowHandles.iterator();
						String compCopyWindow = iterator.next();
						String ChildWindow=iterator.next();
						driver.switchTo().window(compCopyWindow);
						driver.close();
						driver.switchTo().window(ChildWindow);
						if(driver.getCurrentUrl().equalsIgnoreCase(excelOperation.getTestData("TC35", "WILEY_NA_Cart_Test_Data", "Expected_Result")+"?"+product))
							Reporting.updateTestReport("User was redirected to : "+driver.getCurrentUrl()+" successfully, for "
									+region+" region.", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						else
							Reporting.updateTestReport("User was redirected to : "+driver.getCurrentUrl()+", for "
									+region+" region.", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					catch(Exception e) {
						Reporting.updateTestReport("New tab was not opened as the link was not present",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					}

					//Clicking on DEC Link from the print tab
					driver.get(wiley.wileyURLConcatenationwithRegions(region,
							product));
					driver.navigate().refresh();
					wiley.clickOnPrintTab();
					try {
						wiley.clickOnRequestDigitalEvaluationCopyLink();
						Set<String> allWindowHandles1 = driver.getWindowHandles();
						java.util.Iterator<String> iterator1 = allWindowHandles1.iterator();
						String compCopyWindow1 = iterator1.next();
						String ChildWindow1=iterator1.next();
						driver.switchTo().window(compCopyWindow1);
						driver.close();
						driver.switchTo().window(ChildWindow1);
						if(driver.getCurrentUrl().equalsIgnoreCase(excelOperation.getTestData("TC35", "WILEY_NA_Cart_Test_Data", "Expected_Result")+"?"+product))
							Reporting.updateTestReport("User was redirected to : "+driver.getCurrentUrl()+" successfully, for "
									+region+" region even when the DEC Link was clicked from the Print tab", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						else
							Reporting.updateTestReport("User was redirected to : "+driver.getCurrentUrl()+", for "
									+region+" region when the DEC Link was clicked from the Print tab.", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					catch(Exception e) {
						Reporting.updateTestReport("New tab was not opened as the link was not present",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					}
				}
			}
			wiley.WileyLogOut(driver);
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
}
