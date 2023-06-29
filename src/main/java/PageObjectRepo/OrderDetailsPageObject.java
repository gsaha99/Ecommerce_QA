package PageObjectRepo;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import TestSuite.CoD_Regression_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.Reporting;
import utilities.StatusDetails;

/**
 * 
 * @author shprasad
 *
 */

public class OrderDetailsPageObject {
	
	public String SS_path=CoD_Regression_Test_Suite.SS_path;	
	
	@FindBy(xpath = "//span[text()='Thanks for your order!']")
    private WebElement thankForYourOrderMessage;
	
	@FindBy(xpath = "//button[@class='user-dropdown__my-account-btn']")
	private WebElement mySubscriptionButton;
	
	@FindBy(xpath = "//span[@class='no-sub__message']")
	private WebElement subscriptionActivationDetailsMessage;
	
	
	public void verifyThankYouMessage() throws IOException {
		try {
			utilities.Helper.waitForElementVisibility(thankForYourOrderMessage);
			String errormsg = thankForYourOrderMessage.getText();
			if (errormsg.equals("Thanks for your order!")) {
				Reporting.updateTestReport("Thankyou message is correct",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} else {
			Reporting.updateTestReport("Thankyou message is not correct",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	} catch (Exception e) {
		Reporting.updateTestReport("Thankyou message is not correct" + e.getClass().toString(),
				CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
	}		
}
	
	public void verifySubscriptionActivationDetailsMessage() throws IOException {	
		try {
			utilities.Helper.waitForElementVisibility(subscriptionActivationDetailsMessage);
			String errormsg = subscriptionActivationDetailsMessage.getText();
			if (errormsg.equals("We are currently activating your new subscription. Your subscription details and VitalSource access will be available once you receive your Order Confirmation email.")) {
				Reporting.updateTestReport("Subscription activation message is correct",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Subscription activation message is not correct",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}			
		} catch (Exception e) {
			Reporting.updateTestReport("Subscription activation message is not correct"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public void selectMySubscriptionButton() throws IOException {
		try {
			utilities.Helper.click(mySubscriptionButton);
			Reporting.updateTestReport("Subscription button is selected",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Subscription button is not selected",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		}
	}
}
