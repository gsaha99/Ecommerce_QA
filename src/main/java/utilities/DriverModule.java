package utilities;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.browserstack.local.Local;


public class DriverModule {

	public  static WebDriver driver =null;
	public static final String username="gourabsaha_Nlruz2";
	public static final String AccessToken ="WUrEEqUxwLoVAJEKWbyh";
	
	public static final String URL = "https://"+username+":"+AccessToken+"@hub-cloud.browserstack.com/wd/hub";
	
	
	@BeforeTest
	@Parameters("browser")
	public void initiate(ITestContext context,@Optional("chrome") String browser)
	{
		try {
			
			
			String date = new SimpleDateFormat("hhmmss").format(new Date());			
			String testSuiteName=context.getCurrentXmlTest().getClasses().stream()
		               .findFirst().get().getName().substring(11);
			
		/*	DesiredCapabilities Caps= new DesiredCapabilities();
			
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			
			//browserstackOptions.put("local", "true");
			browserstackOptions.put("os", "Windows");
			browserstackOptions.put("osVersion", "11");
			browserstackOptions.put("browserVersion", "latest");
			
			browserstackOptions.put("projectName", "WPS RegressionSuite");
			browserstackOptions.put("buildName", testSuiteName);
			browserstackOptions.put("sessionName", "Sprint 35.4");
			//browserstackOptions.put("name", testSuiteName);
			
			browserstackOptions.put("debug", "true"); // to enable visual logs
			browserstackOptions.put("consoleLogs", "info"); 
			browserstackOptions.put("networkLogs", "true"); // to enable network logs to be logged
			
			browserstackOptions.put("resolution", "1024x768");	
			
			browserstackOptions.put("idleTimeout", "900");
			
			*/
					
			//Set Firefox			
			if(browser.equalsIgnoreCase("firefox")) 
				//browserstackOptions.put("browser", browser);
				driver= new FirefoxDriver();
			
			//Set Chrome		
			else if(browser.equalsIgnoreCase("chrome"))
				//browserstackOptions.put("browser", browser);
				driver= new ChromeDriver();
							
			// Set Edge 			
			else if(browser.equalsIgnoreCase("Edge")) //browserstackOptions.put("browser", browser);
							
			//Caps.setCapability("bstack:options", browserstackOptions);	
			
			//driver= new RemoteWebDriver(new URL(URL), Caps);
		
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));			
			
			//Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
			
		/*	String browserName 		= browserstackOptions.get("browser").toString();							
			String browserVersion 	= browserstackOptions.get("browserVersion").toString();
			String OS_Name			= browserstackOptions.get("os").toString();
			String OS_Version 		= browserstackOptions.get("osVersion").toString();
		*/
			String browserName 		= "Chrome";							
			String browserVersion 	= "111.0.5563.147";
			String OS_Name			= "Windows";
			String OS_Version 		= "10";
			
			
			Reporting.summaryReportdesign(testSuiteName+"_ReportSummary_In_"+browserName+"_"+date,
					browserName,browserVersion,OS_Name,OS_Version);
			
			LogTextFile.createTodayLog(testSuiteName+"_"+date);
			
			
		}
		catch(Exception e){ System.out.println(e.getMessage());}

	}

	public static WebDriver getWebDriver() { return driver;}

	@AfterTest
	public void CloseBrowser(){	
		
		Reporting.summaryEndReport();
		driver.close();
		driver.quit();
		
	}
}
