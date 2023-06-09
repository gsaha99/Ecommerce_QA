package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.DriverModule;

public class CommonMethods {
	
	public static BufferedWriter wr;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	public static WebDriver driver;


		public static String createURLFolder() {
			try {
				String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date());
				String path = System.getProperty("user.dir")+"\\URLs\\"+dateName;
				File folder = new File(path);

				if (!folder.exists()) {
					folder.mkdir();
				}
				return path;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return "";
	}

		}
		
		public static String createURLFile() {
			try {
				String path=createURLFolder();
				String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				FileWriter file=new FileWriter(path+"\\"+"URL"+dateName+".txt",true);
				wr=new BufferedWriter(file);
				wr.newLine();
				wr.flush();
				return path+"\\"+"URL"+dateName+".txt";
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return "";
			}
			
		}
		public static void AppendURLs(String URL) {
			try {			

				wr.write(URL);
				wr.newLine();
				wr.flush();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
			public static void closeURLFile() {
				try {
					wr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		public static void ChangeURL() throws IOException {	
			
			String filepath=CommonMethods.createURLFile();
			
			String URL_without_locale = excelOperation.getTestData("URL_without_locale", "StoreFront_CLP", "Data");
			String URL_with_locale = excelOperation.getTestData("URL_with_locale", "StoreFront_CLP", "Data");

			String clp_solr = "-c-";
			String clp_constructor = "-c2-";

			try {
				File file = new File(filepath);
				BufferedReader reader = new BufferedReader(new FileReader(file));
				StringBuffer stringBuffer = new StringBuffer();
				String line;

				while ((line = reader.readLine()) != null) {
					stringBuffer.append(line.replaceAll(URL_without_locale, URL_with_locale).replace(clp_solr, clp_constructor) + "\n");
				}
				reader.close();

				FileWriter writer = new FileWriter(file);
				BufferedWriter bufferedWriter = new BufferedWriter(writer);

				bufferedWriter.write(stringBuffer.toString());
				bufferedWriter.close();
				System.out.println("The URLs are updated successfully");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Reporting.updateTestReport("Error was thrown while appending the text file " + e.getMessage(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				e.printStackTrace();
			}

		
			
	}
		
	public static void hittingURL() throws Exception {
		
		String filepath=CommonMethods.createURLFile();
		FileReader read;
		try {
			read = new FileReader(filepath);
			BufferedReader br = new BufferedReader(read);
			List<String> urls = new ArrayList<String>();
			String line;
			while ((line = br.readLine()) != null) {
				urls.add(line);
			}
			br.close();
			read.close();

			for (String url : urls) {
				Thread.sleep(4000);
				driver.get(url);
				Reporting.updateTestReport("CLP got shifted from Solr to Constructor " + url,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

				Thread.sleep(2000);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Reporting.updateTestReport("Error while opening the URL" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			e.printStackTrace();
		}

	}
		
}	

