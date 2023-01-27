package utilities;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.naming.Context;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DriverModule {

	public  static WebDriver driver =null;
	


	@BeforeTest
	public void initiate(ITestContext context)
	{
		
		try {
			String date = new SimpleDateFormat("hhmmss").format(new Date());
			
			String testSuiteName=context.getCurrentXmlTest().getClasses().stream()
		               .findFirst().get().getName().substring(11);
						
			//System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.ee");

			//configure options parameter to Chrome driver	
//			String cpath=SeleniumManager.getInstance().getDriverPath("chromedriver");
//			System.out.println(cpath);
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--incognito");		      		      	
//			driver = new ChromeDriver(options);
			
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-private");
			driver = new FirefoxDriver(options);
			String cpath=SeleniumManager.getInstance().getDriverPath("geckodriver");
			System.out.println(cpath);
			
//			driver=new EdgeDriver();

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
			
			Reporting.summaryReportdesign(testSuiteName+"_ReportSummary_"+date);

		}catch(Exception e){ System.out.println(e.getMessage());}

	}

	public static WebDriver getWebDriver() { return driver;}

	@AfterTest
	public void CloseBrowser(){	
		
		Reporting.summaryEndReport();
		driver.close();
		driver.quit();
		
	}
}
