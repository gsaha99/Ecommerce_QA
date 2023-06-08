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

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import utilities.CaptureScreenshot;

import utilities.LogTextFile;
import utilities.DriverModule;

import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.*;
import java.io.BufferedWriter;
import java.util.List;
import java.util.ArrayList;

public class StoreFront_Validate_CLP extends DriverModule {
	
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
	 * @Description: Changing the CLP from C to C2
	 * 
	 * @Date: 29/05/23
	 */
	@Test
	public void TC01_CategoryLandingPage() throws IOException, InterruptedException {

		/* Printing the output in text file */
		try {

			Reporting.test = Reporting.extent.createTest("TC01_CategoryLandingPage");
			LogTextFile.writeTestCaseStatus("TC01_CategoryLandingPage", "Test case");
			PrintStream text = new PrintStream(new FileOutputStream("C:\\URLs\\URL.txt", true));
			System.setOut(text);

			/* Invoking browser & fetching all the links */
			driver.get(excelOperation.getTestData("Subject_Page", "StoreFront_CLP", "Data"));
			driver.manage().window().maximize();
			WebElement subjects = driver.findElement(By.className("section-description"));
			List<WebElement> URLs = subjects.findElements(By.tagName("a"));
			for (WebElement link : URLs) {
				// System.out.println(link.getText()+"-->"+link.getAttribute("href"));
				System.out.println(link.getAttribute("href"));
			}
			System.out.println("No of links--> " + URLs.size());

			/* Adding locale & Changing c to C2 */
			String oldText1 = excelOperation.getTestData("URL_without_locale", "StoreFront_CLP", "Data");
			String newText1 = excelOperation.getTestData("URL_with_locale", "StoreFront_CLP", "Data");

			String oldText = "-c-";
			String newText = "-c2-";

			try {
				File file = new File("C:\\URLs\\URL.txt");
				BufferedReader reader = new BufferedReader(new FileReader(file));
				StringBuffer stringBuffer = new StringBuffer();
				String line;

				while ((line = reader.readLine()) != null) {
					stringBuffer.append(line.replaceAll(oldText1, newText1).replace(oldText, newText) + "\n");
				}
				reader.close();

				FileWriter writer = new FileWriter(file);
				BufferedWriter bufferedWriter = new BufferedWriter(writer);

				bufferedWriter.write(stringBuffer.toString());
				bufferedWriter.close();
				System.out.println("The URLs are updated successfully");

			} catch (IOException e) {
				e.printStackTrace();
			}

			/* Hitting URLs */
			FileReader read;
			try {
				read = new FileReader("C:\\URLs\\URL.txt");
				BufferedReader br = new BufferedReader(read);
				List<String> urls = new ArrayList<String>();
				String line;
				while ((line = br.readLine()) != null) {
					urls.add(line);
				}
				br.close();
				read.close();

				for (String url : urls) {
					driver.get(url);
					Reporting.updateTestReport("CLP got shifted from Solr to Constructor " + url,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

					Thread.sleep(4000);
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Reporting.updateTestReport("CLP did not get shifted from Solr to Constructor " + e.getMessage(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Reporting.updateTestReport("CLP did not get shifted from Solr to Constructor " + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			e.printStackTrace();
		}
	}

}