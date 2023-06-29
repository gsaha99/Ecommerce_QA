package utilities;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author shprasad.
 * @description This is a common method which contains all the methods which can
 *              be used in any of the classes.
 *
 */


public class Helper extends DriverModule {

	// private static final Logger LOG =
	// LogManager.getLogger(WebUtil.class.getName());
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	/**
	 * @author shprasad. @description. This method gives the current date and time.
	 */
	public static String getCurrentDateAndTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	/**
	 * @author shprasad. @description. This method selects the option user wants to
	 *         select from the dropdown.
	 */
	public static void selectDropDownByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * @author shprasad. @description. This method takes care of the wait time.
	 */
	public static void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @author shprasad.
	 * @description. This method takes care of the wait time.
	 */
	public static void jsClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
	}

	/**
	 * @author shprasad. 
	 * @description. This method waits for the element to be visible.
	 * 
	 */	
	public static void waitForElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * @author shprasad. 
	 * @description. This method waits for the element to be visible according to the time required for that particular scenario
	 * 
	 */	
	public static void waitForElementVisibility(WebElement element, int timeInSec) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	/**
	 * @author shprasad. 
	 * @description. This method highlights the elements
	 *         
	 */
	public static void highlightWebElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style',background:#ff; border='3px solid red;'", element);
	}
	
	/**
	 * @author shprasad.
	 * @description. This method highlights clears the text box and enters the value
	 *         
	 */
	public static void enterText(WebElement element, String text) {
		waitForElementVisibility(element);		
		element.clear();
		element.sendKeys(text);
	}
	
	/**
	 * @author shprasad.
	 * @description. This method clicks the element
	 *         
	 */
	public static void click(WebElement element) {
		try {
			waitForElementVisibility(element);	
			element.click();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception occured while clicking"+e);
		}
		
			
		
		
	}
	
	/**
	 * @author shprasad.
	 * @description. This method takes screenshot
	 *         
	 */
	public static String takeScreenShot(String screenshot) throws IOException {
		String dateName = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss").format(new Date());		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + screenshot + dateName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}
	
	/**
	 * @author shprasad.
	 * @description. This method is used for scrolling
	 *         
	 */
	public static void scrollWindow(WebElement element) {
		JavascriptExecutor jsexe = (JavascriptExecutor) driver;
		jsexe.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	/**
	 * @author shprasad.
	 * @description. This method accepts the alert
	 * @throws InterruptedException 
	 *         
	 */
	public static void acceptAlert() throws InterruptedException {
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
	}
	
	/**
     * @author mkaushik.
     * @description. This method double click the element
     */
    public static void doubleClick(WebElement ele) {
        Actions act = new Actions(driver);        
        act.doubleClick(ele).perform();    
    }
    
    /**
     * @author mkaushik.
     * @description. This method takes care of the wait time.
     */
    public static String randomNumber() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        String formatted = String.format("%05d", num); 
        return formatted;
    }
    
    /**
	 * @author shprasad.
	 * @description. This method generates random symbol
	 *          
	 */
    public static String generateRandomSymbols() {
    	String randomSymbol = "@#$%^&*(?/}{)";
		StringBuilder s = new StringBuilder();
		Random rnd = new Random();
		while (s.length() < 5) {
			int index = (int) (rnd.nextFloat() * randomSymbol.length());
			s.append(randomSymbol.charAt(index));
		}
		String symbol = s.toString(); 
		return symbol;
    }
    
    /**
	 * @author shprasad.
	 * @description. This method generates random alphabets
	 *          
	 */
    public static String generateRandomAlphabets() {
    	String randomAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyx";
		StringBuilder s = new StringBuilder();
		Random rnd = new Random();
		while (s.length() < 8) {
			int index = (int) (rnd.nextFloat() * randomAlphabets.length());
			s.append(randomAlphabets.charAt(index));
		}
		String alpha = s.toString(); 
		return alpha;
    }
    
    /**
   	 * @author shprasad.
   	 * @description. This method generates random numbers less than 5
   	 *          
   	 */
       public static String generateRandomNumbersLessThanFive() {
       	String randomAlphabets = "1234567890";
   		StringBuilder s = new StringBuilder();
   		Random rnd = new Random();
   		while (s.length() < 5) {
   			int index = (int) (rnd.nextFloat() * randomAlphabets.length());
   			s.append(randomAlphabets.charAt(index));
   		}
   		String number = s.toString(); 
   		return number;
       }
       
       /**
      	 * @author shprasad.
      	 * @description. This method generates random numbers
      	 *          
      	 */
          public static String generateRandomNumbers() {
          	String randomAlphabets = "09123456789035";
      		StringBuilder s = new StringBuilder();
      		Random rnd = new Random();
      		while (s.length() < 13) {
      			int index = (int) (rnd.nextFloat() * randomAlphabets.length());
      			s.append(randomAlphabets.charAt(index));
      		}
      		String number = s.toString(); 
      		return number;
          }
       
     /**
   	 * @author shprasad.
   	 * @description. This method generates random string which contains alphabets, numbers
   	 *          
   	 */
       public static String generateRandomString() {
       	String randomAlphabets = "abcdefghijklmnopqrstuvwxyx1234567890";
   		StringBuilder s = new StringBuilder();
   		Random rnd = new Random();
   		while (s.length() < 10) {
   			int index = (int) (rnd.nextFloat() * randomAlphabets.length());
   			s.append(randomAlphabets.charAt(index));
   		}
   		String alpha = s.toString(); 
   		return alpha;
       }
       
       /**
      	 * @author shprasad.
      	 * @description. This method generates alphanumeric characters
      	 *          
      	 */
          public static String generateRandomAlphaNumericChar() {
          	String randomAlphabets = "aghkl12569";
      		StringBuilder s = new StringBuilder();
      		Random rnd = new Random();
      		while (s.length() < 10) {
      			int index = (int) (rnd.nextFloat() * randomAlphabets.length());
      			s.append(randomAlphabets.charAt(index));
      		}
      		String alpha = s.toString(); 
      		return alpha;
          }
          
          /**
        	 * @author shprasad.
        	 * @description. This method generates alphanumeric characters
        	 *          
        	 */
            public static String generateRandomAlphaNumericCharOfSixChar() {
            	String randomAlphabets = "aghkl12569";
        		StringBuilder s = new StringBuilder();
        		Random rnd = new Random();
        		while (s.length() < 6) {
        			int index = (int) (rnd.nextFloat() * randomAlphabets.length());
        			s.append(randomAlphabets.charAt(index));
        		}
        		String alpha = s.toString(); 
        		return alpha;
            }
       
            /**
          	 * @author shprasad.
          	 * @description. This method refreshes the page
          	 *          
          	 */
            public static void refreshThePage() {
            	driver.navigate().refresh();
            }
    
	
}
