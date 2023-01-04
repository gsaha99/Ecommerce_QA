package PageObjectRepo;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Test_Suite.AGS_Test_Suite;
import Test_Suite.Vet_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.Reporting;
import utilities.StatusDetails;

public class app_Riskified_Repo {
	AGS_Test_Suite AGS_Test;
	public String SS_path=AGS_Test.SS_path;
	@FindBy(xpath = "//input[@placeholder='Email']")
	WebElement RiskifiedUserId;
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement RiskifiedPassword;
	@FindBy(xpath = "//button[@class='elm-button']")
	WebElement RiskifiedSignInButton;
	@FindBy(xpath = "(//div[@id='app-shell-merchant-shop-name'])/span[contains(text(),'Vet Consult - Wiley')]")
	WebElement Rvet;
	@FindBy(xpath = "//input[@id='app-shell-search-input']")
	WebElement RiskifiedOrderIdSearchField;
	@FindBy(xpath = "//div[@class='order-id']/span[1]")
	WebElement OrderIdInRiskified;
	@FindBy(id="app-shell-merchant-shop-name")
	WebElement SelectedDropdown;
	@FindBy(xpath="//div[contains(text(),'graphicstandards.com')]")
	WebElement AGSFromDropdown;
	@FindBy(xpath="//div[contains(text(),'Vet Consult - Wiley')]")
	WebElement VETFromDropdown;

	/*
	 * @Description: Enters Riskified User id in login page
	 * 
	 */
	public void enterRiskifiedUserId(String email) throws IOException {
		try {
			RiskifiedUserId.sendKeys(email);
			Reporting.updateTestReport("Riskified User id : " + email + " was entered successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter Riskified User id " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Description: Enters Riskified Password in login page
	 * 
	 */
	public void enterRiskifiedPassword(String password) throws IOException {
		try {

			RiskifiedPassword.sendKeys(password);
			Reporting.updateTestReport("Riskified Password : " + password + " was entered successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to enter Riskified Password " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Description: Clicks on the submit button in the Riskified login page in login page
	 * 
	 */
	public void clickOnRiskifiedSignInButton() throws IOException {
		try {
			RiskifiedSignInButton.click();
			Reporting.updateTestReport("Sign in button was clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Unable to Click on Sign in button " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Description: Searches the order id in Riskified
	 * 
	 */
	public void searchOrderIdInRiskified(String orderId) throws IOException {
		try {
			RiskifiedOrderIdSearchField.sendKeys(orderId);
			RiskifiedOrderIdSearchField.sendKeys(Keys.ENTER);
			Reporting.updateTestReport("Order Id: "+orderId+" was searched successfully ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Failed to search order Id", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);

		}
	}
	
	/*
	 * @Description: Selects the AGS Storefront from Riskified to search order id
	 * @Date: 20/12/22
	 */
	public void selectAGSFromDropdown() throws IOException{
		try {
			SelectedDropdown.click();
			AGSFromDropdown.click();
			Reporting.updateTestReport("AGS was successfully selected from the dropdown ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("AGS was couldn't be selected from the dropdown ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}
	
	
	/*
	 * @Description: Checks if the Order is present in riskified or not
	 * 
	 */
	public int checkIfOrderIdIsPresentInRiskified(WebDriver driver) throws IOException {
		try {
			int timeOut=30;
			int flag=0;
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			for(int i = 0; i<timeOut/5; i++) {
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='order-id']/span[1]")));
					flag=1;
					break;
				}
				catch(Exception e) {
					driver.navigate().refresh();
				}
			}
			if(flag==1)
				{
				String orderId = OrderIdInRiskified.getText();
				Reporting.updateTestReport(orderId + " was feteched successfully from Riskified",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
			
			return flag;
		} catch (Exception e) {
			Reporting.updateTestReport("Order id was not fetched with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			return 0;
		}
	}
	
	/*
	 * @Description: Checks if the Order is declined in riskified or not
	 * 
	 */
	public void checkIfOrderIdIsDeclinedInRiskified(WebDriver driver) throws IOException {
		try {
			int timeOut=30;
			int flag=0;
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			for(int i = 0; i<timeOut/5; i++) {
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Declined')]")));
					flag=1;
					break;
				}
				catch(Exception e) {
					driver.navigate().refresh();
				}
			}
			if(flag==1)
				{
				Reporting.updateTestReport("The order was declined from Riskified",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
			
			else
			{
			Reporting.updateTestReport("The order was not declined from Riskified",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Order was not declined with error message " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			
		}
	}
	
	/*
	 * @Description: Selects the AGS Storefront from Riskified to search order id
	 * @Date: 2/1/23
	 */
	public void selectVETFromDropdown() throws IOException{
		try {
			SelectedDropdown.click();
			VETFromDropdown.click();
			Reporting.updateTestReport("VET was successfully selected from the dropdown ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		}
		catch(Exception e){
			Reporting.updateTestReport("VET was couldn't be selected from the dropdown ", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}
	}
}