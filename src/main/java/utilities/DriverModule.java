package utilities;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
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




public class DriverModule {

	public  static WebDriver driver =null;
	private static final String username		= excelOperation.getTestData("BS_Username", "BrowserStack_Config", "Data");
	private static final String AccessToken 	= excelOperation.getTestData("BS_Password", "BrowserStack_Config", "Data");
	
	public static final String URL = "https://"+username+":"+AccessToken+"@hub-cloud.browserstack.com/wd/hub";
	
	
	@BeforeTest
	@Parameters({"browser","DeviceType"})
	public void initiate(ITestContext context,@Optional("chrome") String browser,@Optional("Desktop")String DeviceType)
	{
		try {
			
			
			String date = new SimpleDateFormat("hhmmss").format(new Date());			
			String testSuiteName=context.getCurrentXmlTest().getClasses().stream()
		               .findFirst().get().getName().substring(11);
			
			DesiredCapabilities Caps= new DesiredCapabilities();
			
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			
			browserstackOptions.put("seleniumVersion", "4.8.0");
			
			if (DeviceType.equalsIgnoreCase("Desktop")) 
			{
				browserstackOptions.put("os", excelOperation.getTestData("BS_OS_Desktop", "BrowserStack_Config", "Data"));
				browserstackOptions.put("osVersion", excelOperation.getTestData("BS_OSVersion_Desktop", "BrowserStack_Config", "Data"));
			}
			else if (DeviceType.equalsIgnoreCase("Mobile")) 
			{
				browserstackOptions.put("os", excelOperation.getTestData("BS_OS_Mobile", "BrowserStack_Config", "Data"));
				browserstackOptions.put("osVersion", excelOperation.getTestData("BS_OSVersion_Mobile", "BrowserStack_Config", "Data"));
				browserstackOptions.put("deviceName",excelOperation.getTestData("BS_DeviceName_Mobile", "BrowserStack_Config", "Data"));
			}
			
			browserstackOptions.put("browserVersion",excelOperation.getTestData("BS_BrowserVersion", "BrowserStack_Config", "Data"));
			
			browserstackOptions.put("projectName", "WPS RegressionSuite");
			browserstackOptions.put("buildName", testSuiteName);
			browserstackOptions.put("sessionName", excelOperation.getTestData("BS_BuildNo", "BrowserStack_Config", "Data"));
			//browserstackOptions.put("name", testSuiteName);
			
			browserstackOptions.put("interactiveDebugging", "true"); // Enable the Interactive session in runtime
			browserstackOptions.put("debug", "true"); 		// to enable visual logs
			browserstackOptions.put("consoleLogs", "info");  // Enable console logs
			browserstackOptions.put("networkLogs", "true"); // to enable network logs to be logged
			
			browserstackOptions.put("resolution", "1024x768");	 // To set Resolution
			
			browserstackOptions.put("idleTimeout", "300"); // Default 90 sec and Max 300 Sec
			
			browserstackOptions.put("browser", browser); // Set the Browser
							
			Caps.setCapability("bstack:options", browserstackOptions);	
			
			driver= new RemoteWebDriver(new URL(URL), Caps);
		
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));			
			
			
			String browserName 		= browserstackOptions.get("browser").toString();							
			String browserVersion 	= browserstackOptions.get("browserVersion").toString();
			String OS_Name			= browserstackOptions.get("os").toString();
			String OS_Version 		= browserstackOptions.get("osVersion").toString();
	
		
			Reporting.summaryReportdesign(testSuiteName+"_ReportSummary_In_"+browserName+"_"+date,
					browserName,browserVersion,OS_Name,OS_Version);
			
			LogTextFile.createTodayLog(testSuiteName+"_"+date,browserName,browserVersion,OS_Name);
						
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
