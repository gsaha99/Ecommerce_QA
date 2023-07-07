package PageObjectRepo;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestSuite.CoD_Regression_Test_Suite;
import utilities.CaptureScreenshot;
import utilities.Reporting;
import utilities.StatusDetails;

/**
 * 
 * @author shprasad
 *
 */

public class SubscriptionDetailsPageObject {
	
	public String SS_path=CoD_Regression_Test_Suite.SS_path;
	
	@FindBy(xpath = "(//span[@class='subscription__title'])[1]")
    private WebElement subscriptionInformationText;
	
	@FindBy(css = "button.subscription__cancel-btn")
	private WebElement cancelMySubscriptionButton;
	
	@FindBy(xpath = "//*[text()='When you cancel within the 2 day grace period, you will immediately lose access to this subscription and receive a refund.']")
	private WebElement cancelMySubscriptionMessageWithinGracePeriod;
	
	@FindBy(css = "button.modal__back-btn")
	private WebElement dontCancelMySubscriptionButton;
	
	@FindBy(xpath = "//button[@class='primary-cta modal__action-btn']")
	private WebElement cancelMySubscriptionButtonPopUp;	
	
	@FindBy(css = "button.subscription__change-billing-btnn")
	private WebElement disabledChangeBillingInformation;
	
	@FindBy(css = "button.sub-information__change-payment-btn")
	private WebElement disabledChangePaymentMethodButton;
	
	@FindBy(xpath = "//button[text()='Change Payment Method']")
    private WebElement changePaymentMethodLink;
	
	@FindBy(css = ".sub-information__payment-container>div>span:nth-of-type(2)")
    private WebElement getCardNumber;

    @FindBy(css = ".sub-information__payment-container>div>span:nth-of-type(3)")
    private WebElement getExpiryDate;
	
	public void verifyChangeBillingInformationIsDisabed() throws IOException {
		try {
			if (!disabledChangeBillingInformation.isEnabled()) {
				Reporting.updateTestReport("Change billing information is disabled", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Change billing information is not disabled", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}	
		} catch (Exception e) {
			Reporting.updateTestReport("Change billing information is not disabled"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}				
	}
	
	public void verifyChangePaymentMethodButtonIsDisabed() throws IOException {
		try {
			if (!disabledChangePaymentMethodButton.isEnabled()) {
				Reporting.updateTestReport("Change payment information is disabled", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Change payment information is not disabled", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}	
		} catch (Exception e) {
			Reporting.updateTestReport("Change payment information is not disabled"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}			
	}
	
	public void verifyCancelMySubscriptionButtonIsDisabed() throws IOException {	
		try {
			if (cancelMySubscriptionButton.isEnabled()) {
				Reporting.updateTestReport("Cancel my subscription button is enabled", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Cancel my subscription button is not enabled", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}	
		} catch (Exception e) {
			Reporting.updateTestReport("Cancel my subscription button is not enabled"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}				 		
	}
	
	public void clickOnCancelMySubscriptionButtonPopUp() throws IOException {
		try {
			utilities.Helper.click(cancelMySubscriptionButtonPopUp);		
			Reporting.updateTestReport("Cancel my subscription button pop up is clicked", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Cancel my subscription button pop up is not clicked"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}		
	}

	public void clickOnCancelMySubscriptionButton() throws IOException {
		try {
			utilities.Helper.click(cancelMySubscriptionButton);
			Reporting.updateTestReport("Cancel button is clicked", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Cancel button is not clicked"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
		
	}
	
	public void clickOnDontCancelMySubscriptionButton() {
		utilities.Helper.click(dontCancelMySubscriptionButton);
	}
	
	public void verifySubscriptionInformationPage(WebDriver driver) throws IOException {		
		try {
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(3));
			Thread.sleep(40000);
			utilities.Helper.refreshThePage();		
			String errormsg = subscriptionInformationText.getText();
			if (errormsg.equals("Subscription Information")) {
				Reporting.updateTestReport("Subscription information page is visible", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Subscription information page is not visible", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Subscription information page is not visible"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
		}		
	}
	
	public void verifyCancelMySubscriptionMessageWithinGracePeriod() throws IOException {	
		try {
			String msg = cancelMySubscriptionMessageWithinGracePeriod.getText();
			if (msg.equals("When you cancel within the 2 day grace period, you will immediately lose access to this subscription and receive a refund.")) {
				Reporting.updateTestReport("Cancel my subscription message within grace period is correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Cancel my subscription message within grace period is not correct",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Cancel my subscription message within grace period is not correct" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public void clickOnChangePaymentMethod() throws IOException {
		try {
			utilities.Helper.waitForElementVisibility(changePaymentMethodLink, 20);
			utilities.Helper.scrollWindow(changePaymentMethodLink);
			utilities.Helper.click(changePaymentMethodLink);
			Reporting.updateTestReport("Change Payment Link clicked successfully",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			Reporting.updateTestReport("Change Payment Link is not clicked" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	public String getCardNumber() throws IOException {
        try {
        utilities.Helper.scrollWindow(changePaymentMethodLink);
        String cardNumber = getCardNumber.getText();
        Reporting.updateTestReport("Card number text is present",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        return cardNumber;
        }catch(Exception e) {
            Reporting.updateTestReport("Card number text is not present"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
        }
        return "";
    }

	public String getExpirationDate() throws IOException {
        try {
        String cardNumber = getExpiryDate.getText();
        Reporting.updateTestReport("Expiry Date text is present",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
        return cardNumber; 
        }catch(Exception e) {
            Reporting.updateTestReport("Expiry Date text is not present"+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
        }
        return "";
    }
}
