package utilities;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ScrollingWebPage {

	public static void PageScrolldown(WebDriver driver, Integer xcord, Integer ycord) throws IOException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy("+xcord+","+ycord+")");
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public static void PageScrollUp(WebDriver driver, Integer xcord, Integer ycord) throws IOException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy("+xcord+","+ycord+")");
		} catch (Exception e) {
			e.getMessage();
		}
	}
}