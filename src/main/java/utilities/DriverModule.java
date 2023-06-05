

package utilities;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
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
	
	public static final String username="arakshit_jcH98V";
	public static final String AccessToken ="snS4qNFU3yjVeMNDfdSy";
	
	public static final String URL = "https://"+username+":"+AccessToken+"@hub-cloud.browserstack.com/wd/hub";
	
	@BeforeTest
	@Parameters("browser")
	public void initiate(ITestContext context,@Optional("Edge") String browser)
	{
		try {
			String date = new SimpleDateFormat("ddmmyyyyhhmmss").format(new Date());			
			String testSuiteName=context.getCurrentXmlTest().getClasses().stream()
		               .findFirst().get().getName().substring(11);			
			DesiredCapabilities Caps= new DesiredCapabilities();
			Caps.setCapability("name", "PPE Regression Suite");	
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("InPrivate");
			edgeOptions.addArguments("--remote-allow-origins=*");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			
			//create firefox instance
			if(browser.equalsIgnoreCase("firefox")){
				
				Caps.setCapability("os", "windows");
				Caps.setCapability("os_version", "11");
				Caps.setCapability("browser", browser);
				Caps.setCapability("browser_version", "109");
				
			}
				//Check if parameter passed as 'chrome'
			else if(browser.equalsIgnoreCase("chrome")){			
				Caps.setCapability("os", "windows");
				Caps.setCapability("os_version", "10");
				Caps.setCapability("browser", browser);
				Caps.setCapability("browser_version", "110");
				Caps.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
				
			}
			else if(browser.equalsIgnoreCase("safari")){

				Caps.setCapability("os", "OS X");
				Caps.setCapability("os_version", "Ventura");
				Caps.setCapability("browser", browser);
				Caps.setCapability("browser_version", "16.3");
				
			}
			else if(browser.equalsIgnoreCase("Edge")){				
				Caps.setCapability("os", "windows");
				Caps.setCapability("os_version", "10");
				Caps.setCapability("browser", browser);
				Caps.setCapability("browser_version", "110");
			}
			//driver= new RemoteWebDriver(new URL(URL), Caps);
			driver=new EdgeDriver(edgeOptions);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));			
			
			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
			
			String browserName = caps.getBrowserName();							
			String browserVersion = caps.getBrowserVersion();				
			String OS_Name = System.getProperty("os.name").toLowerCase();
			
			Reporting.summaryReportdesign(testSuiteName+"_ReportSummary_In_"+browserName+"_"+date,
					browserName,browserVersion,OS_Name);
			LogTextFile.createTodayLog(testSuiteName+"_"+date,browserName,browserVersion,OS_Name);
					
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
