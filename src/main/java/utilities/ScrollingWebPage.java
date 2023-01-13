package utilities;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;



public class ScrollingWebPage {
	

	public static void PageScrolldown(WebDriver driver, Integer xcord, Integer ycord,String SS_path) throws IOException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(" + xcord + "," + ycord + ")");
			Reporting.updateTestReport("The page was scrolled down", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.INFO);
		} catch (Exception e) {
			Reporting.updateTestReport("Unable to Scroll Down", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.WARNING);
		}
	}

	public static void PageScrollUp(WebDriver driver, Integer xcord, Integer ycord,String SS_path) throws IOException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(" + xcord + "," + ycord + ")");
			Reporting.updateTestReport("The page was scrolled up", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.INFO);
		} catch (Exception e) {
			Reporting.updateTestReport("Unable to Scroll Up", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.WARNING);
		}
	}

	public static void PageDown(WebDriver driver,String SS_path) throws IOException {
		try {
			Actions at = new Actions(driver);
			at.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(2000);
			Reporting.updateTestReport("Perform Page Down", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.INFO);
		} catch (Exception e) {
			Reporting.updateTestReport("Unable to Perform Page Down", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.WARNING);
		}
	}
}