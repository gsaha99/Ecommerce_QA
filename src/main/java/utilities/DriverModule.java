

package utilities;

import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.naming.Context;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DriverModule {

	public  static WebDriver driver =null;
	
	public static final String username="gourabsaha_Nlruz2";
	public static final String AccessToken ="WUrEEqUxwLoVAJEKWbyh";
	
	public static final String URL = "https://"+username+":"+AccessToken+"@hub-cloud.browserstack.com/wd/hub";
	
	@BeforeTest
	@Parameters("browser")
	public void initiate(ITestContext context,@Optional("edge") String browser)
	{
		try {
			
			//String browser ="firefox"; // Currently Chrome is hardcoded 
			
			String date = new SimpleDateFormat("ddmmyyyyhhmmss").format(new Date());			
			String testSuiteName=context.getCurrentXmlTest().getClasses().stream()
		               .findFirst().get().getName().substring(11);
			
			DesiredCapabilities Caps= new DesiredCapabilities();
			Caps.setCapability("name", "PPE Regression Suite");			
			
			//create firefox instance
			if(browser.equalsIgnoreCase("firefox")){
				
				Caps.setCapability("os", "windows");
				Caps.setCapability("os_version", "11");
				Caps.setCapability("browser", browser);
				Caps.setCapability("browser_version", "109");
				
				driver= new RemoteWebDriver(new URL(URL), Caps);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
				
				Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
				
				String browserName = caps.getBrowserName();							
				String browserVersion = caps.getBrowserVersion();
				
				String OS_Name = System.getProperty("os.name").toLowerCase();
				
				Reporting.summaryReportdesign(testSuiteName+"_ReportSummary_In_"+browserName+"_"+date,
						browserName,browserVersion,OS_Name);
				
			}
				//Check if parameter passed as 'chrome'
			else if(browser.equalsIgnoreCase("chrome")){
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
			
				Caps.setCapability("os", "windows");
				Caps.setCapability("os_version", "10");
				Caps.setCapability("browser", browser);
				Caps.setCapability("browser_version", "110");
				Caps.setCapability(ChromeOptions.CAPABILITY,options);
				
				driver= new RemoteWebDriver(new URL(URL), Caps);

				
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
				
				Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
				
				String browserName = caps.getBrowserName();							
				String browserVersion = caps.getBrowserVersion();
				
				String OS_Name = System.getProperty("os.name").toLowerCase();
				
				Reporting.summaryReportdesign(testSuiteName+"_ReportSummary_In_"+browserName+"_"+date,
						browserName,browserVersion,OS_Name);
				
			}
			else if(browser.equalsIgnoreCase("safari")){

				Caps.setCapability("os", "OS X");
				Caps.setCapability("os_version", "Ventura");
				Caps.setCapability("browser", browser);
				Caps.setCapability("browser_version", "16.3");

				driver= new RemoteWebDriver(new URL(URL), Caps);

				
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
				
				Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
				
				String browserName = caps.getBrowserName();							

				String browserVersion = caps.getBrowserVersion();				

				String OS_Name = System.getProperty("os.name").toLowerCase();
				
				Reporting.summaryReportdesign(testSuiteName+"_ReportSummary_In_"+browserName+"_"+date,
						browserName,browserVersion,OS_Name);
				
			}
			else if(browser.equalsIgnoreCase("Edge")){

				EdgeOptions options = new EdgeOptions();
				options.addArguments("InPrivate");
				Caps.setCapability("os", "windows");
				Caps.setCapability("os_version", "11");
				Caps.setCapability("browser", browser);
				Caps.setCapability("browser_version", "110");

				//Caps.setCapability(EdgeOptions.CAPABILITY,options);
				//driver= new RemoteWebDriver(new URL(URL), Caps);
				driver=new EdgeDriver(options);

				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
				
				Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
				
				String browserName = caps.getBrowserName();							
				String browserVersion = caps.getBrowserVersion();				
				String OS_Name = System.getProperty("os.name").toLowerCase();
				
				Reporting.summaryReportdesign(testSuiteName+"_ReportSummary_In_"+browserName+"_"+date,
						browserName,browserVersion,OS_Name);
				LogTextFile.createTodayLog(testSuiteName+"_"+date);
			}
					
		}
		catch(Exception e){ System.out.println(e.getMessage());}

	}

	public static WebDriver getWebDriver() { return driver;}

	@AfterTest
	public void CloseBrowser(){	

		Reporting.summaryEndReport();
		LogTextFile.closeLogFile();
		driver.close();
		driver.quit();

	}
}
