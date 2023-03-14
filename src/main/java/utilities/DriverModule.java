

package utilities;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.naming.Context;

import org.openqa.selenium.Capabilities;
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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DriverModule {

	public  static WebDriver driver =null;

	@BeforeTest
	//@Parameters("chrome")
	public void initiate(ITestContext context)
	{
		try {
			String browser ="chrome"; // Currently Chrome is hardcoded 
			
			String date = new SimpleDateFormat("hhmmss").format(new Date());			
			String testSuiteName=context.getCurrentXmlTest().getClasses().stream()
		               .findFirst().get().getName().substring(11);
			
			if(browser.equalsIgnoreCase("firefox")){
				//create firefox instance
		
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("-private");
				driver = new FirefoxDriver(options);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
				
				Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
				
				String browserName = caps.getBrowserName();							
				String browserVersion = caps.getBrowserVersion();
				
				String OS_Name = System.getProperty("os.name").toLowerCase();
				
				Reporting.summaryReportdesign(testSuiteName+"_ReportSummary_"+date,browserName,browserVersion,OS_Name);
				
			}
				//Check if parameter passed as 'chrome'
			else if(browser.equalsIgnoreCase("chrome")){
				
				//configure options parameter to Chrome driver
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--incognito");		      		      	
				driver = new ChromeDriver(options);
				
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
				
				Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
				
				String browserName = caps.getBrowserName();							
				String browserVersion = caps.getBrowserVersion();
				
				String OS_Name = System.getProperty("os.name").toLowerCase();
				
				Reporting.summaryReportdesign(testSuiteName+"_ReportSummary_"+date,browserName,browserVersion,OS_Name);
				
			}
			else if(browser.equalsIgnoreCase("Edge")){
				//set path to Edge.exe
				
				EdgeOptions options = new EdgeOptions();
				options.addArguments("InPrivate");
				driver = new EdgeDriver(options);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
				
				Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
				
				String browserName = caps.getBrowserName();							
				String browserVersion = caps.getBrowserVersion();
				
				String OS_Name = System.getProperty("os.name").toLowerCase();
				
				Reporting.summaryReportdesign(testSuiteName+"_ReportSummary_"+date,browserName,browserVersion,OS_Name);
			}
					
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
