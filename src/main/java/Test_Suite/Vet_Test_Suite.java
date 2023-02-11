package Test_Suite;

import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Select;

import PageObjectRepo.app_Eloqua_Repo;
import PageObjectRepo.app_Hybris_BO_Repo;
import PageObjectRepo.app_Riskified_Repo;
import PageObjectRepo.app_VET_Repo;
import utilities.CaptureScreenshot;
import utilities.OrderConfirmationMail;
import utilities.DriverModule;
import utilities.PaymentGateway;
import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Vet_Test_Suite extends DriverModule {
	app_VET_Repo VET;
	app_Hybris_BO_Repo HybrisBO;
	app_Eloqua_Repo Eloqua;
	app_Riskified_Repo RiskifiedRepo;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	public static String EmailConfirmationText="//button/div[contains(text(),'Order Confirmation')]";


	@BeforeTest
	public void initializeRepo() {
		VET = PageFactory.initElements(driver, app_VET_Repo.class);
		HybrisBO = PageFactory.initElements(driver, app_Hybris_BO_Repo.class);
		Eloqua = PageFactory.initElements(driver, app_Eloqua_Repo.class);
		RiskifiedRepo = PageFactory.initElements(driver, app_Riskified_Repo.class);

	}

	/*
	 * Author : Arun 
	 * Description : New User Registration with placing Order
	 */
	@Test
	public void TC01_Anonymous_User_Registration() throws IOException {
		try {

			Reporting.test = Reporting.extent.createTest("TC01_Anonymous_User_Registration");
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("TC01", "VET_Test_Data", "URL"));
			VET.clickOnGetStarted();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Continue')])[2]")));
				VET.clickOnContinueButton();
				VET.enterFirstName(excelOperation.getTestData("TC01", "VET_Test_Data", "First_Name"));
				VET.enterLastName(excelOperation.getTestData("TC01", "VET_Test_Data", "Last_Name"));
				String email=VET.enterEmailId();
				VET.enterPassword(excelOperation.getTestData("TC01", "VET_Test_Data", "Password"));
				VET.clickCreateAccountButton();
				VET.enterBillingFirstName(excelOperation.getTestData("TC01", "VET_Test_Data", "First_Name"));
				VET.enterBillingLastName(excelOperation.getTestData("TC01", "VET_Test_Data", "Last_Name"));
				VET.selectCountry(excelOperation.getTestData("TC01", "VET_Test_Data", "Country"));
				VET.enterAddressLine1(excelOperation.getTestData("TC01", "VET_Test_Data", "Address_line1"));
				VET.enterCity(excelOperation.getTestData("TC01", "VET_Test_Data", "City"));
				VET.enterState(excelOperation.getTestData("TC01", "VET_Test_Data", "State"));
				VET.enterZip(excelOperation.getTestData("TC01", "VET_Test_Data", "Zip_Code"));
				VET.enterPhoneNumber(excelOperation.getTestData("TC01", "VET_Test_Data", "Phone_Number"));
				VET.checkBoxBilling();
				VET.continueToCardDetailsPage();		
				String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
				if (payment_gateway.contains("WPG")) {
					/* WPG Code */
					PaymentGateway.paymentWPG_VET(driver, VET, "TC01", SS_path);
				} else {
					/** WPS Code **/
					PaymentGateway.paymentWPS_VET(driver, VET, "TC01", SS_path);
				}
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed."
							+ " A confirmation email')]")));
					String orderID = VET.fetchOrderId();
					String tax = VET.fetchTax();
					String total = VET.fetchTotal();
					excelOperation.updateTestData("TC01", "VET_Test_Data", "Order_Id", orderID);
					excelOperation.updateTestData("TC01", "VET_Test_Data", "Tax", tax);
					excelOperation.updateTestData("TC01", "VET_Test_Data", "Total", total);
					excelOperation.updateTestData("TC01", "VET_Test_Data", "Email_Id", email);
					VET.logOut(driver);
					driver.get(excelOperation.getTestData("Yopmail_URL",
							"Generic_Dataset", "Data"));
					VET.enterEmailIdInYopmail(email);
					VET.clickOnArrowButton();
					if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
						Reporting.updateTestReport("Order Confirmation mail was received",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						OrderConfirmationMail.validateOrderConfirmationMailContent("VET",driver,SS_path,tax,"",total);
					}
					else {
						Reporting.updateTestReport("Order Confirmation mail was not received",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

					}
				}

				catch(Exception e)
				{Reporting.updateTestReport("Order was not placed and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
				;}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Continue button on cart page was not clickable"
						+ " and caused timeout excepion", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			VET.logOut(driver);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}

	}

	/*
	 * @Author: Anindita
	 * @Description: Creates a new account (Standalone registration)
	 */
	@Test
	public void TC02_Create_Account() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC02_Create_Account");
			driver.get(excelOperation.getTestData("VET_Login_URL", "Generic_Dataset", "Data"));
			VET.enterFirstName(excelOperation.getTestData("TC02", "VET_Test_Data", "First_Name"));
			VET.enterLastName(excelOperation.getTestData("TC02", "VET_Test_Data", "Last_Name"));
			String emailId = VET.enterEmailId();
			VET.enterPassword(excelOperation.getTestData("TC02", "VET_Test_Data", "Password"));
			VET.clickCreateAccountButton();
			excelOperation.updateTestData("TC05", "VET_Test_Data", "Email_Id", emailId);
			String expectedUrl = "https://vetqa.wiley.com/my-account/update-profile/";
			String actualUrl = driver.getCurrentUrl();
			if (actualUrl.equalsIgnoreCase(expectedUrl)) {
				Reporting.updateTestReport("Account was created successfully ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				Reporting.updateTestReport("Account was not created", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
			VET.logOut(driver);
		} catch (Exception e) {
			System.out.println("Create Account method couldn't be executed" + e.getMessage());
			Reporting.updateTestReport("Create Account method couldn't be executed" + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: This is to user StandaloneLogin in vet Application
	 */
	@Test
	public void TC03_StandaloneLogin() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_StandaloneLogin");
			driver.get(excelOperation.getTestData("VET_Login_URL", "Generic_Dataset", "Data"));
			VET.enterExistingUserId(excelOperation.getTestData("TC03", "VET_Test_Data", "Email_Id"));
			VET.enterExistingUserPassword(excelOperation.getTestData("TC03", "VET_Test_Data", "Password"));
			VET.clickOnLoginButton();
			VET.logOut(driver);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * Author : Arun 
	 * Description : Placing an order with Existing User
	 */
	@Test
	public void TC04_Login_During_Checkout() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC04_Login_During_Checkout");
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("TC04", "VET_Test_Data", "URL"));
			VET.clickOnGetStarted();
			VET.clickOnContinueButton();
			VET.enterExistingUserId(excelOperation.getTestData("TC04", "VET_Test_Data", "Email_Id"));
			VET.enterExistingUserPassword(excelOperation.getTestData("TC04", "VET_Test_Data", "Password"));
			VET.clickOnLoginButton();
			VET.clickOnContinueButton();
			VET.enterBillingFirstName(excelOperation.getTestData("TC04", "VET_Test_Data", "First_Name"));
			VET.enterBillingLastName(excelOperation.getTestData("TC04", "VET_Test_Data", "Last_Name"));
			VET.selectCountry(excelOperation.getTestData("TC04", "VET_Test_Data", "Country"));
			VET.enterAddressLine1(excelOperation.getTestData("TC04", "VET_Test_Data", "Address_line1"));
			VET.enterCity(excelOperation.getTestData("TC04", "VET_Test_Data", "City"));
			VET.enterState(excelOperation.getTestData("TC04", "VET_Test_Data", "State"));
			VET.enterZip(excelOperation.getTestData("TC04", "VET_Test_Data", "Zip_Code"));
			VET.enterPhoneNumber(excelOperation.getTestData("TC04", "VET_Test_Data", "Phone_Number"));
			VET.checkBoxBilling();
			VET.continueToCardDetailsPage();

			String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
			System.out.println(payment_gateway);
			if (payment_gateway.contains("WPG")) {
				/* WPG Code */
				PaymentGateway.paymentWPG_VET(driver, VET, "TC04", SS_path);
			} else {
				/** WPS Code **/
				PaymentGateway.paymentWPS_VET(driver, VET, "TC04", SS_path);
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed."
						+ " A confirmation email')]")));
				String orderID = VET.fetchOrderId();
				String tax = VET.fetchTax();
				String total = VET.fetchTotal();
				excelOperation.updateTestData("TC04", "VET_Test_Data", "Order_Id", orderID);
				excelOperation.updateTestData("TC04", "VET_Test_Data", "Tax", tax);
				excelOperation.updateTestData("TC04", "VET_Test_Data", "Total", total);
				VET.logOut(driver);
			}

			catch(Exception e)
			{Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			VET.logOut(driver);}
		}

		catch (Exception e) {
			// TODO: handle exception
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: 
	 */
	@Test
	public void TC05_Reset_Password_My_Account() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_Reset_Password_My_Account");
			driver.get(excelOperation.getTestData("VET_Login_URL", "Generic_Dataset", "Data"));
			VET.enterExistingUserId(excelOperation.getTestData("TC05", "VET_Test_Data", "Email_Id"));
			VET.enterExistingUserPassword(excelOperation.getTestData("TC05", "VET_Test_Data", "Previous_Password"));
			VET.clickOnLoginButton();
			VET.isMyAccountPage();
			VET.clickEditPasswordPage();
			VET.isChangePasswordPage();
			VET.enterPreviousPassword(excelOperation.getTestData("TC05", "VET_Test_Data", "Previous_Password"));
			VET.enterNewPassword(excelOperation.getTestData("TC05", "VET_Test_Data", "Password"));
			VET.clickPasswordSaveButton();
			if (VET.isPasswordResetAlertPresent())
				Reporting.updateTestReport("Password reset was successful", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			else
				Reporting.updateTestReport("Password reset was not successful",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			VET.logOut(driver);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Password reset was not successful with error message " + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Ability to Rest the Password From Login Page
	 */
	@Test
	public void TC06_ResetPasswordFromLoginPage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC06_ResetPasswordFromLoginPage");
			driver.get(excelOperation.getTestData("VET_Login_URL", "Generic_Dataset", "Data"));
			VET.ForgotchangePassword();
			VET.RetriveLoginInfo(excelOperation.getTestData("TC06", "VET_Test_Data", "Email_Id"));
			VET.clickOnSubmit();
			VET.AlertMessage();
			driver.get(excelOperation.getTestData("Yopmail_URL",
					"Generic_Dataset", "Data"));
			VET.enteryopmail(excelOperation.getTestData("TC06", "VET_Test_Data","Email_Id"));
			VET.clickonbutton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			int timeOutSeconds=60;
			int flag=0;
			WebElement element1 = driver.findElement(By.xpath("//button[@id='refresh']"));
			WebElement element2 = null;

			/* The purpose of this loop is to wait for maximum of 60 seconds */
			for (int i = 0; i < timeOutSeconds / 5; i++) {

				try {
					driver.switchTo().frame("ifinbox");
					element2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Password Reset Request')]")));

					if(element2.isDisplayed()==true)
					{
						flag=1;
						element2.click();
						driver.switchTo().defaultContent();
						break;
					}

				} catch (Exception e) {
					driver.switchTo().defaultContent();        
					element1.click();
				}
			}

			if(flag==1)
			{driver.switchTo().frame("ifmail");
			try {
				wait.until(ExpectedConditions.elementToBeClickable(
						driver.findElement(By.xpath("(//main[@class='yscrollbar']"
								+ "/div/div/div/table/tbody/tr/td/center/table/tbody/tr/td)[2]/p[3]/"
								+ "a[contains(text(),'Click here to change your password')]"))));
				VET.clickOnResetPasswordLink();
				Set<String> allWindowHandles = driver.getWindowHandles();
				java.util.Iterator<String> iterator = allWindowHandles.iterator();
				String yopmailHandle = iterator.next();
				String ChildWindow=iterator.next();
				driver.switchTo().window(ChildWindow);
				VET.ResetPwd(excelOperation.getTestData("TC06", "VET_Test_Data", "New_Password"));
				VET.ResetConfirmPassword(excelOperation.getTestData("TC06", "VET_Test_Data", "Confirm_Password"));
				VET.ResetPassSubmit();
				VET.PasswordResetSuccess();
				driver.switchTo().window(yopmailHandle);
				driver.close();
				driver.switchTo().window(ChildWindow);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Rest password link was not cliackable in the mail", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			}
			else {
				Reporting.updateTestReport("No reset password mail was recieved in yopmail inbox", 
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}


	}

	/*
	 * Author : Arun 
	 * Description : This flow is verify the tax on the order
	 * confirmation page.
	 */
	@Test
	public void TC07_Verify_Tax() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC07_Verify_Tax");
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("TC07", "VET_Test_Data", "URL"));
			VET.clickOnGetStarted();
			VET.clickOnContinueButton();
			VET.enterFirstName(excelOperation.getTestData("TC07", "VET_Test_Data", "First_Name"));
			VET.enterLastName(excelOperation.getTestData("TC07", "VET_Test_Data", "Last_Name"));
			String email=VET.enterEmailId();
			VET.enterPassword(excelOperation.getTestData("TC07", "VET_Test_Data", "Password"));
			VET.clickCreateAccountButton();
			VET.enterBillingFirstName(excelOperation.getTestData("TC07", "VET_Test_Data", "First_Name"));
			VET.enterBillingLastName(excelOperation.getTestData("TC07", "VET_Test_Data", "Last_Name"));
			VET.selectCountry(excelOperation.getTestData("TC07", "VET_Test_Data", "Country"));
			VET.enterAddressLine1(excelOperation.getTestData("TC07", "VET_Test_Data", "Address_line1"));
			VET.enterCity(excelOperation.getTestData("TC07", "VET_Test_Data", "City"));
			VET.enterState(excelOperation.getTestData("TC07", "VET_Test_Data", "State"));
			VET.enterZip(excelOperation.getTestData("TC07", "VET_Test_Data", "Zip_Code"));
			VET.enterPhoneNumber(excelOperation.getTestData("TC07", "VET_Test_Data", "Phone_Number"));
			VET.checkBoxBilling();
			VET.continueToCardDetailsPage();
			String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
			System.out.println(payment_gateway);
			if (payment_gateway.contains("WPG")) {
				/* WPG Code */
				PaymentGateway.paymentWPG_VET(driver, VET, "TC07", SS_path);
			} else {
				/** WPS Code **/
				PaymentGateway.paymentWPS_VET(driver, VET, "TC07", SS_path);
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed."
						+ " A confirmation email')]")));
				String orderID = VET.fetchOrderId();
				String tax = VET.fetchTax();
				String total = VET.fetchTotal();
				excelOperation.updateTestData("TC07", "VET_Test_Data", "Order_Id", orderID);
				excelOperation.updateTestData("TC07", "VET_Test_Data", "Tax", tax);
				excelOperation.updateTestData("TC07", "VET_Test_Data", "Total", total);
				excelOperation.updateTestData("TC07", "VET_Test_Data", "Email_Id", email);
				VET.logOut(driver);
				driver.get(excelOperation.getTestData("Yopmail_URL",
						"Generic_Dataset", "Data"));
				VET.enterEmailIdInYopmail(email);
				VET.clickOnArrowButton();
				if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
					Reporting.updateTestReport("Order Confirmation mail was received",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					OrderConfirmationMail.validateOrderConfirmationMailContent("VET",driver,SS_path,tax,"",total);
				}
				else {
					Reporting.updateTestReport("Order Confirmation mail was not received",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

				}
			}

			catch(Exception e)
			{Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			VET.logOut(driver);}
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}

	}


	/*
	 * @Author: Vishnu
	 * 
	 * @Desciption: This test cases is verifying the Order in Riskified Check.
	 * 
	 */
	/*
	@Test
	public void TC09_Riskifiedcheck() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC09_Riskifiedcheck");
			driver.get(excelOperation.getTestData("Riskified_URL", "Generic_Dataset", "Data"));
			RiskRepo.riskifiedemail(excelOperation.getTestData("Riskified_User_ID", "Generic_Dataset", "Data"));
			RiskRepo.riskifiedpassword(excelOperation.getTestData("Riskified_Password", "Generic_Dataset", "Data"));
			RiskRepo.riskifiedsubmit();
			RiskRepo.rorderenter(excelOperation.getTestData("TC09", "VET_Test_Data", "Order_Id"));
			RiskRepo.enter();
			RiskRepo.FecthROrderId();
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);

		}
	}
	 */

	//Backoofice related testcases have been made out of scope and will be tested manually
	/*
	 * Author : Arun 
	 * Description : This flow is using  for refund the amount to customer.
	 */
	/*@Test
	public void TC10_VET_Refund() throws IOException {
		try {

			Reporting.test = Reporting.extent.createTest("TC10_VET_Refund");
			driver.get(excelOperation.getTestData("Backoffice_URL", "Generic_Dataset", "Data"));
			HybrisBO.enterBOUserName(excelOperation.getTestData("Backoffice_CSR_User_ID", "Generic_Dataset", "Data"));
			HybrisBO.enterBOPassword(excelOperation.getTestData("Backoffice_CSR_Password", "Generic_Dataset", "Data"));
			HybrisBO.selectBOLanguage();
			HybrisBO.clickOnBOLoginButton();
			Thread.sleep(2000);
			HybrisBO.clickOnProceedButton();
			Thread.sleep(4000);
			HybrisBO.clickOnOrdersOption();
			Thread.sleep(2000);
			HybrisBO.EnterOrderID(excelOperation.getTestData("TC10", "VET_Test_Data", "Order_Id"));
			Thread.sleep(1000);
			HybrisBO.clickOnSearchBtn();
			Thread.sleep(1000);
			HybrisBO.clickOnOrderID();
			HybrisBO.clickOnRefundOrderTab();
			HybrisBO.SelectRefundReason();
			Thread.sleep(1000);
		//	HybrisBO.clickOnRecalculateTaxTab();
			Thread.sleep(2000);
			HybrisBO.clickOnConfirmSelectedTab();
			Thread.sleep(2000);
			HybrisBO.clickOnYesConfirmationButton();
			Thread.sleep(3000);
			HybrisBO.clickOnLogOutButton();
			driver.get(excelOperation.getTestData("Yopmail_URL", "Generic_Dataset", "Data"));
			VET.enterYOPUserMailID(excelOperation.getTestData("TC10", "VET_Test_Data", "Email_Id"));
			VET.clickOnRefreshButton();
			Thread.sleep(2000);	

		}

		catch (Exception e) {
			// TODO: handle exception
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
			HybrisBO.clickOnLogOutButton();
		}

	}*/


	@Test
	public void TC11_Place_Order_With_Promo_Code() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC11_Place_Order_With_Promo_Code");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30, 1));
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("TC11", "VET_Test_Data", "URL"));
			VET.addSubscriptionToCart();
			try {
				wait.until(ExpectedConditions.visibilityOf(VET.returnDiscountCodeField()));
				VET.addPromoToCart(excelOperation.getTestData("Partial _Coupon_Code", "Generic_Dataset", "Data"));
				VET.continueToCheckout();
				VET.enterFirstName(excelOperation.getTestData("TC11", "VET_Test_Data", "First_Name"));
				VET.enterLastName(excelOperation.getTestData("TC11", "VET_Test_Data", "Last_Name"));
				String email = VET.enterEmailId();
				VET.enterPassword(excelOperation.getTestData("TC02", "VET_Test_Data", "Password"));
				VET.clickCreateAccountButton();
				VET.enterBillingFirstName(excelOperation.getTestData("TC11", "VET_Test_Data", "First_Name"));
				VET.enterBillingLastName(excelOperation.getTestData("TC11", "VET_Test_Data", "Last_Name"));
				VET.selectCountry(excelOperation.getTestData("TC11", "VET_Test_Data", "Country"));
				VET.enterAddressLine1(excelOperation.getTestData("TC11", "VET_Test_Data", "Address_line1"));
				VET.enterCity(excelOperation.getTestData("TC11", "VET_Test_Data", "City"));
				VET.enterState(excelOperation.getTestData("TC11", "VET_Test_Data", "State"));
				VET.enterZip(excelOperation.getTestData("TC11", "VET_Test_Data", "Zip_Code"));
				VET.enterPhoneNumber(excelOperation.getTestData("TC11", "VET_Test_Data", "Phone_Number"));
				VET.checkBoxBilling();
				VET.continueToCardDetailsPage();

				String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
				System.out.println(payment_gateway);
				if (payment_gateway.contains("WPG")) {
					/* WPG Code */
					PaymentGateway.paymentWPG_VET(driver, VET, "TC11", SS_path);
				} else {
					/** WPS Code **/
					PaymentGateway.paymentWPS_VET(driver, VET, "TC11", SS_path);
				}
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed."
							+ " A confirmation email')]")));
					String orderID = VET.fetchOrderId();
					String tax = VET.fetchTax();
					String total = VET.fetchTotal();
					excelOperation.updateTestData("TC11", "VET_Test_Data", "Order_Id", orderID);
					excelOperation.updateTestData("TC11", "VET_Test_Data", "Tax", tax);
					excelOperation.updateTestData("TC11", "VET_Test_Data", "Total", total);
					excelOperation.updateTestData("TC11", "VET_Test_Data", "Email_Id", email);
					VET.logOut(driver);
					driver.get(excelOperation.getTestData("Yopmail_URL",
							"Generic_Dataset", "Data"));
					VET.enterEmailIdInYopmail(email);
					VET.clickOnArrowButton();
					if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
						Reporting.updateTestReport("Order Confirmation mail was received",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						OrderConfirmationMail.validateOrderConfirmationMailContent("VET",driver,SS_path,tax,"",total);
					}
					else {
						Reporting.updateTestReport("Order Confirmation mail was not received",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

					}
				}

				catch(Exception e)
				{Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Discount code field was"
						+ " not displayed and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
			VET.logOut(driver);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @description: This is to verify placing an Order with Non Us Address
	 */
	@Test
	public void TC12_PlaceOrderwithNonUSAddress() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC12_PlaceOrderwithNonUSAddress");
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("TC12", "VET_Test_Data", "URL"));
			VET.addSubscriptionToCart();
			VET.continueToCheckout();
			VET.enterExistingUserId(excelOperation.getTestData("TC12", "VET_Test_Data", "Email_Id"));
			VET.enterExistingUserPassword(excelOperation.getTestData("TC12", "VET_Test_Data", "Password"));
			VET.clickOnLoginButton();
			VET.continueToCheckout();
			VET.enterBillingFirstName(excelOperation.getTestData("TC12", "VET_Test_Data", "First_Name"));
			VET.enterBillingLastName(excelOperation.getTestData("TC12", "VET_Test_Data", "Last_Name"));
			VET.selectCountry(excelOperation.getTestData("TC12", "VET_Test_Data", "Country"));
			VET.enterAddressLine1(excelOperation.getTestData("TC12", "VET_Test_Data", "Address_line1"));
			VET.enterCity(excelOperation.getTestData("TC12", "VET_Test_Data", "City"));
			VET.enterState(excelOperation.getTestData("TC12", "VET_Test_Data", "State"));
			VET.enterZip(excelOperation.getTestData("TC12", "VET_Test_Data", "Zip_Code"));
			VET.enterPhoneNumber(excelOperation.getTestData("TC12", "VET_Test_Data", "Phone_Number"));
			VET.checkBoxBilling();
			VET.continueToCardDetailsPage();
			String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
			System.out.println(payment_gateway);
			if (payment_gateway.contains("WPG")) {
				/* WPG Code */
				PaymentGateway.paymentWPG_VET(driver, VET, "TC12", SS_path);
			} else {
				/** WPS Code **/
				PaymentGateway.paymentWPS_VET(driver, VET, "TC12", SS_path);
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed."
						+ " A confirmation email')]"))); 
				String orderID = VET.fetchOrderId();
				String tax = VET.fetchTax();
				String total = VET.fetchTotal();
				excelOperation.updateTestData("TC12", "VET_Test_Data", "Order_Id", orderID);
				excelOperation.updateTestData("TC12", "VET_Test_Data", "Tax", tax);
				excelOperation.updateTestData("TC12", "VET_Test_Data", "Total", total);
			
			}

			catch(Exception e)
			{Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			}
			VET.logOut(driver);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}

	}

	//Backoofice related testcases have been made out of scope and will be tested manually

	/*@Test
	public void TC13_Renew_Subscription() throws IOException {
		try {

			Reporting.test = Reporting.extent.createTest("TC13_Renew_Subscription");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30, 1));
			driver.get(excelOperation.getTestData("Backoffice_URL", "Generic_Dataset", "Data"));
			HybrisBO.enterHybrisBOUserName(excelOperation.getTestData("Backoffice_Admin_User_ID", "Generic_Dataset", "Data"));
			HybrisBO.enterHybrisBOPassword(excelOperation.getTestData("Backoffice_Admin_Password", "Generic_Dataset", "Data"));
			HybrisBO.clickOnBOLoginButton();
			HybrisBO.enterSearchText("Wiley");
			HybrisBO.clickOnWileySubscription();
			Thread.sleep(3000);
			HybrisBO.clickOnSwitchSearchMode();
			HybrisBO.enterCustomerEmailID(excelOperation.getTestData("TC13", "VET_Test_Data", "Email_Id"));
			Thread.sleep(3000);
			HybrisBO.clickOnSuggestedmailID();
			Thread.sleep(4000);
			HybrisBO.clickOnSearchButton1();
			Thread.sleep(3000);
			HybrisBO.clickOnOrderDetails();
			Thread.sleep(3000);
			HybrisBO.clearExpirationDateField();
			Calendar cal = Calendar.getInstance(); // creates calendar
			cal.setTime(new Date()); // sets calendar time/date
			cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
			Date date = cal.getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy hh:mm:ss aa");
			String formattedDate = dateFormat.format(date).toString();
			System.out.println(formattedDate);
			HybrisBO.enterExpirationDateField(formattedDate);
			HybrisBO.clickOnSaveButton();
            Thread.sleep(2000); 
            HybrisBO.clearFieldInHybrisBO();
			HybrisBO.searchFieldInHybrisBO("cronjob");            
            Thread.sleep(2000);
            HybrisBO.clickOnCronJobs();
            Thread.sleep(3000);
            wait.until(ExpectedConditions.elementToBeClickable(HybrisBO.returnSearchButton()));
			HybrisBO.clickOnSearchButton();
			wait.until(ExpectedConditions.elementToBeClickable(HybrisBO.returnSearchBar()));
			HybrisBO.enterInSearchBar("renewal");
			Thread.sleep(2000);
			HybrisBO.clickOnSearchButtonBO();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(HybrisBO.returnRenewalCronjob()));
			HybrisBO.clickOnRenewalCronjob();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(HybrisBO.returnRunButton()));
			HybrisBO.runCronJob();
			Thread.sleep(2000);
			HybrisBO.clickOnLogOutButton();	

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}*/

	/*@Test
	public void TC14_Update_card_details_and_renew_subscription() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC14_Update_card_details_and_renew_subscription");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30, 1));
			driver.get(excelOperation.getTestData("VET_Login_URL", "Generic_Dataset", "Data"));
			VET.enterExistingUserId(excelOperation.getTestData("TC14", "VET_Test_Data", "Email_Id"));
			VET.enterExistingUserPassword(excelOperation.getTestData("TC14", "VET_Test_Data", "Password"));
			VET.clickOnLoginButton();
			VET.isMyAccountPage();
			VET.clickOnEditPayment();
			VET.isEditPaymentPage();
			VET.clickOnUpdateCreditCardButton();

			String str = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
            System.out.println(str);
            if (str.contains("WPG")) {
                // WPG Code 
                driver.switchTo().frame(0);
                driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
                VET.enterCardNumberWPG(excelOperation.getTestData("TC14", "VET_Test_Data", "Card_Number"));
                driver.switchTo().parentFrame();
                driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
                VET.selectExpiryMonthWPG();
                driver.switchTo().parentFrame();
                driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
                VET.selectExpiryYearWPG();
                driver.switchTo().parentFrame();
                driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='security code']")));
                VET.enterSecurityCodeWPG(excelOperation.getTestData("TC14", "VET_Test_Data", "CVV"));
                driver.switchTo().parentFrame();
                VET.clickOnMakePaymentButtonWPG();
                driver.switchTo().defaultContent();
            } else {
                // WPS Code

               driver.switchTo().frame("tokenFrame");
                driver.switchTo().frame(0);
                VET.enterCardNumber(excelOperation.getTestData("TC14", "VET_Test_Data", "Card_Number"));
                VET.enterExpiryDate(excelOperation.getTestData("TC14", "VET_Test_Data", "Expiry_Date"));
                VET.enterCVC(excelOperation.getTestData("TC14", "VET_Test_Data", "CVV"));
                driver.switchTo().parentFrame();
                VET.paymentDetailsSubmit();
                driver.switchTo().defaultContent();

            }
			VET.isUpdatedCardLogoDisplayed();
			VET.logOut();
			driver.get(excelOperation.getTestData("Backoffice_URL", "Generic_Dataset", "Data"));
			HybrisBO.enterHybrisBOUserName(excelOperation.getTestData("Backoffice_Admin_User_ID", "Generic_Dataset", "Data"));
			HybrisBO.enterHybrisBOPassword(excelOperation.getTestData("Backoffice_Admin_Password", "Generic_Dataset", "Data"));
			HybrisBO.clickOnHybrisBOLoginButton();
			HybrisBO.checkIfUserLoggedInHybrisBO();
			HybrisBO.searchFieldInHybrisBO("wiley");
			HybrisBO.clickOnWileySubscriptionField();
			HybrisBO.clickOnSearchButton();
			HybrisBO.enterCustomerIdInHybrisBO(excelOperation.getTestData("TC14", "VET_Test_Data", "Email_Id"));
			wait.until(ExpectedConditions.elementToBeClickable(HybrisBO.returnSuggestedCustomerId()));
			HybrisBO.clickOnSuggestedCustomerId();
			//HybrisBO.clickOnSuggestedmailID();
			HybrisBO.clickOnSearchButtonBO();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(HybrisBO.returnXpathOfSubscription()));
			HybrisBO.clickOnSubscription();
			wait.until(ExpectedConditions.elementToBeClickable(HybrisBO.returnExpiryDateField()));
			HybrisBO.clearExpirationDateField();
			Calendar cal = Calendar.getInstance(); // creates calendar
			cal.setTime(new Date()); // sets calendar time/date
			cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
			Date date = cal.getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy hh:mm:ss aa");
			String formattedDate = dateFormat.format(date).toString();
			System.out.println(formattedDate);
			HybrisBO.enterExpirationDateField(formattedDate);
			HybrisBO.clickOnSaveButton();
			HybrisBO.clearFieldInHybrisBO();
			HybrisBO.searchFieldInHybrisBO("cronjob");

			wait.until(ExpectedConditions.elementToBeClickable(HybrisBO.returnCronJobs()));
			HybrisBO.clickOnCronjob();
			wait.until(ExpectedConditions.elementToBeClickable(HybrisBO.returnSearchButton()));
			HybrisBO.clickOnSearchButton();
			wait.until(ExpectedConditions.elementToBeClickable(HybrisBO.returnSearchBar()));
			HybrisBO.enterInSearchBar("renewal");
			Thread.sleep(2000);
			HybrisBO.clickOnSearchButtonBO();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(HybrisBO.returnRenewalCronjob()));
			HybrisBO.clickOnRenewalCronjob();
			wait.until(ExpectedConditions.elementToBeClickable(HybrisBO.returnRunButton()));
			HybrisBO.runCronJob();
			Thread.sleep(2000);
			HybrisBO.clickOnLogOutButton();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
			HybrisBO.clickOnLogOutButton();
		}
	}*/

	//Backoofice related testcases have been made out of scope and will be tested manually
	/*
	 * @Author: Vishnu Description: This is to verify the Order in Backoffice and
	 * Validating the Order
	 */
	/*@Test
	public void TC15_ValidationOrdersinBO() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC15_ValidationOrdersinBO");
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("TC11", "VET_Test_Data", "URL"));
			VET.addSubscriptionToCart();
			VET.addPromoToCart(excelOperation.getTestData("100_Percent_Coupon", "Generic_Dataset", "Data"));
			VET.continueToCheckout();
			VET.enterFirstName(excelOperation.getTestData("TC15", "VET_Test_Data", "First_Name"));
			VET.enterLastName(excelOperation.getTestData("TC15", "VET_Test_Data", "Last_Name"));
			VET.enterEmailId();
			VET.enterPassword(excelOperation.getTestData("TC15", "VET_Test_Data", "Password"));
			VET.clickCreateAccountButton();
			if (VET.checkIfOrderPlaced()) {
				String orderId = VET.fetchOrderId();
				excelOperation.updateTestData("TC15", "VET_Test_Data", "Order_Id", orderId);
				Reporting.updateTestReport("Order was placed successfully with Order Id: " + orderId,
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				VET.logOut(driver);
				driver.get(excelOperation.getTestData("Backoffice_URL", "Generic_Dataset", "Data"));
				HybrisBO.enterHybrisBOUserName(excelOperation.getTestData("Backoffice_Admin_User_ID", "Generic_Dataset", "Data"));
				HybrisBO.enterHybrisBOPassword(excelOperation.getTestData("Backoffice_Admin_Password", "Generic_Dataset", "Data"));
				HybrisBO.clickOnHybrisBOLoginButton();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='yw-explorerTree-filterTextbox yw-filter-textbox y-general-textinput z-textbox']")));
					HybrisBO.OrderSearchInBo();
					HybrisBO.ClickOnOrders();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='z-div']/span[contains(text(),'No items')]")));
						HybrisBO.EnterorderId(excelOperation.getTestData("TC15", "VET_Test_Data", "Order_Id"));
						HybrisBO.OrderSearch();
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='yw-listview-pagingcontainer z-div']/span[contains(text(),'1 items')]")));
							HybrisBO.ClickonOrderId();
							WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(60));
							try {
								wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='z-div']/span[contains(text(), 'Auto Vet')]")));
								excelOperation.updateTestData("TC15", "VET_Test_Data", "Order_Status", HybrisBO.OrderStatus());
								Reporting.updateTestReport("Order Id and Status of the Order is " + HybrisBO.OrderStatus(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								HybrisBO.clickOnLogOutButton();
							}
							catch(Exception e) {
								Reporting.updateTestReport("Order details was not displayed"
										+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("Searched Order Id was not clickable"
									+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Order Id search field was not clickable"
								+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Orders could not be searched in the left pane"
							+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			} else {
				Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
			HybrisBO.clickOnLogOutButton();	
		}
	}*/

	/*
	 * Author : Arun 
	 * Description : This flow is to verify if the Auto-toggle button is on or off in the Manage subscription.
	 */
	@Test
	public void TC16_Manage_Subscription() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC16_Manage_Subscription");
			driver.get(excelOperation.getTestData("VET_Login_URL", "Generic_Dataset", "Data"));
			VET.enterExistingUserId(excelOperation.getTestData("TC16", "VET_Test_Data", "Email_Id"));
			VET.enterExistingUserPassword(excelOperation.getTestData("TC16", "VET_Test_Data", "Previous_Password"));
			VET.clickOnLoginButton();
			VET.clickOnManageSubscription();

			if (driver.getPageSource().contains("on"))

				Reporting.updateTestReport("Auto Renew Toggle Button is on ", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			else
				Reporting.updateTestReport("Auto Renew Toggle Button is off ", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			VET.logOut(driver);
		}

		catch (Exception e) {
			// TODO: handle exception
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}

	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: This is to placing Zero Order for VEt Application.
	 */
	@Test
	public void TC18_ZeroOrder() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC18_ZeroOrder");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("TC11", "VET_Test_Data", "URL"));
			VET.addSubscriptionToCart();
			VET.addPromoToCart(excelOperation.getTestData("100_Percent_Coupon", "Generic_Dataset", "Data"));
			VET.continueToCheckout();
			VET.enterFirstName(excelOperation.getTestData("TC18", "VET_Test_Data", "First_Name"));
			VET.enterLastName(excelOperation.getTestData("TC18", "VET_Test_Data", "Last_Name"));
			String email=VET.enterEmailId();
			VET.enterPassword(excelOperation.getTestData("TC18", "VET_Test_Data", "Password"));
			VET.clickCreateAccountButton();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed."
						+ " A confirmation email')]")));
				String orderID = VET.fetchOrderId();
				String total = VET.fetchTotal();
				excelOperation.updateTestData("TC18", "VET_Test_Data", "Order_Id", orderID);
				excelOperation.updateTestData("TC18", "VET_Test_Data", "Total", total);
				excelOperation.updateTestData("TC18", "VET_Test_Data", "Email_Id", email);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
			VET.logOut(driver);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}

	}


	/*
	 * Author : Arun 
	 * Description : This flow using for editing the profile from my account page.
	 */
	@Test
	public void TC19_Edit_Profile_from_my_account_page() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC19_Edit_Profile_from_my_account_page");
			driver.get(excelOperation.getTestData("VET_Login_URL", "Generic_Dataset", "Data"));
			VET.enterExistingUserId(excelOperation.getTestData("TC19", "VET_Test_Data", "Email_Id"));
			VET.enterExistingUserPassword(excelOperation.getTestData("TC19", "VET_Test_Data", "Previous_Password"));
			VET.clickOnLoginButton();
			VET.editProfileLastName(excelOperation.getTestData("TC19", "VET_Test_Data", "Last_Name"));
			VET.clickOnProfileSaveButton();
			Thread.sleep(1000);
			String EditProfilePage = driver.getTitle();
			if (EditProfilePage.equalsIgnoreCase("Edit Profile | VET online Site")) {
				Reporting.updateTestReport("EditProfile LastName has been Updated sucessfully",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}

			else
				Reporting.updateTestReport("EditProfile LastName was not updated properly",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			VET.logOut(driver);

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}

	}

	@Test
	public void TC20_Edit_Billing_address_in_my_account() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC20_Edit_Billing_address_in_my_account");
			driver.get(excelOperation.getTestData("VET_Login_URL", "Generic_Dataset", "Data"));
			VET.enterExistingUserId(excelOperation.getTestData("TC20", "VET_Test_Data", "Email_Id"));
			VET.enterExistingUserPassword(excelOperation.getTestData("TC20", "VET_Test_Data", "Password"));
			VET.clickOnLoginButton();
			VET.isMyAccountPage();
			VET.clickOnEditPayment();
			VET.isEditPaymentPage();
			VET.selectCountry(excelOperation.getTestData("TC20", "VET_Test_Data", "Country"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='addressLine1']")));
				VET.enterAddressLine1(excelOperation.getTestData("TC20", "VET_Test_Data", "Address_line1"));
				VET.enterCity(excelOperation.getTestData("TC20", "VET_Test_Data", "City"));
				VET.enterState(excelOperation.getTestData("TC20", "VET_Test_Data", "State"));
				VET.enterZip(excelOperation.getTestData("TC20", "VET_Test_Data", "Zip_Code"));
				VET.enterPhoneNumber(excelOperation.getTestData("TC20", "VET_Test_Data", "Phone_Number"));
				VET.clickOnBillingSaveButton();
				VET.checkIfAlertBoxDisplayedOnBillingAddressPage();
				
			}
			catch(Exception e) {
				Reporting.updateTestReport("Address line 1 field was not clickable"
						+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
			VET.logOut(driver);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	// Anindita on 13/07/22
	@Test
	public void TC08_Eloqua_validation() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC08_Eloqua_validation");
			driver.get(excelOperation.getTestData("Eloqua_URL", "Generic_Dataset", "Data"));
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
			Eloqua.enterEloquaSiteName(excelOperation.getTestData("Eloqua_Site_Name", "Generic_Dataset", "Data"));
			Eloqua.enterEloquaUserName(excelOperation.getTestData("Eloqua_User_ID", "Generic_Dataset", "Data"));
			Eloqua.enterEloquaPassword(excelOperation.getTestData("Eloqua_Password", "Generic_Dataset", "Data"));
			Eloqua.clickOnEloquaLoginButton();
			Eloqua.checkIfUserLoggedInEloqua();
			Eloqua.checkIfUserLoggedInEloqua();
			Eloqua.clickOnEloquaCustomObject();
			Eloqua.clickOnCustomObjectOnHovering();
			driver.switchTo().frame("fullPageFrame");
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@name='QuickSearchForm']/input[@id='FormName948']")));
				Eloqua.searchEloquaOnlinePurchase();
				driver.switchTo().frame("EditFrame");
				driver.findElement(By.xpath("//td[@class='ResultCell ListTableCell']/span[@title='Global Hybris Online Purchases']")).click();
				Thread.sleep(3000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("fullPageFrame");
				driver.switchTo().frame("EditFrame");
				Eloqua.clickOnCustomObjectOnlinePurchase();
				Eloqua.clickOnSearchOnlinePurchase();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("dialogiframe");
				driver.switchTo().frame(0);
				Eloqua.selectHybrisOrderIdInEloqua();
				Eloqua.enterHybrisOrderIdInEloqua(excelOperation.getTestData("TC08", "VET_Test_Data", "Order_Id"));
				Eloqua.clickOnSearchOrderInEloqua();
				if(driver.findElement(By.xpath("//iframe[@id='frameAction']")).isDisplayed()){
					driver.switchTo().frame("frameAction");
					WebElement record = driver.findElement(By.xpath("//span[text()='Total Records: 1']"));
					if (record.isDisplayed()) {
						Reporting.updateTestReport("Order is present in Eloqua ", CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.PASS);
					} else {
						Reporting.updateTestReport("Order is not present in Eloqua ", CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}}
				else {
					Reporting.updateTestReport("Order is not present in Eloqua ", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
				Eloqua.EloquaOrderEditObject();
				driver.get("https://www.google.com/");
				String windowHandle = driver.getWindowHandle();
				driver.switchTo().alert().accept();
				driver.switchTo().window(windowHandle);
			}
			catch(Exception e) {
				Reporting.updateTestReport("Quick search box in eloqua was not clickable"
						+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),
					StatusDetails.FAIL);
			driver.get("https://www.google.com/");
			String windowHandle = driver.getWindowHandle();
			driver.switchTo().alert().accept();
			driver.switchTo().window(windowHandle);
		}
	}

	@Test
	public void TC17_Auto_Renew_Toggle_validation() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC17_Auto_Renew_Toggle_validation");
			driver.get(excelOperation.getTestData("VET_Login_URL", "Generic_Dataset", "Data"));
			VET.enterExistingUserId(excelOperation.getTestData("TC17", "VET_Test_Data", "Email_Id"));
			VET.enterExistingUserPassword(excelOperation.getTestData("TC17", "VET_Test_Data", "Password"));
			VET.clickOnLoginButton();
			VET.isMyAccountPage();
			VET.clickOnManageSubscription();
			Boolean toggle = VET.checkIfAutoRenewToggleOn();
			if (!toggle) {
				VET.clickOnAutoRenewToggle();
				Thread.sleep(1000);
				Reporting.updateTestReport("Auto Renew toggle was successfully switched on",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} else {
				VET.clickOnAutoRenewToggle();
				Thread.sleep(1000);
				Reporting.updateTestReport("Auto Renew toggle was successfully switched off",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			}
			VET.logOut(driver);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	//Backoffice related scenarios have been made out of scope
	// This test case was created to check if subscription was created or not
	/*@Test
	public void TC21_Check_If_Subscription_Is_Created() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC21_Check_If_Subscription_Is_Created");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30, 1));
			driver.get(excelOperation.getTestData("Backoffice_URL", "Generic_Dataset", "Data"));
			HybrisBO.enterHybrisBOUserName(excelOperation.getTestData("Backoffice_Admin_User_ID", "Generic_Dataset", "Data"));
			HybrisBO.enterHybrisBOPassword(excelOperation.getTestData("Backoffice_Admin_Password", "Generic_Dataset", "Data"));
			HybrisBO.clickOnHybrisBOLoginButton();
			HybrisBO.checkIfUserLoggedInHybrisBO();
			HybrisBO.clearFieldInHybrisBO();
			HybrisBO.searchFieldInHybrisBO("wiley");
			HybrisBO.clickOnWileySubscriptionField();
			HybrisBO.clickOnSearchButton();
			HybrisBO.enterCustomerIdInHybrisBO(excelOperation.getTestData("TC14", "VET_Test_Data", "Email_Id"));
			Thread.sleep(3000);
			HybrisBO.clickOnSuggestedmailID();
			HybrisBO.clickOnSearchButtonBO();
			Thread.sleep(3000);
			HybrisBO.checkIfTwoItemsDisplayed();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Expiration date']")));
			driver.findElement(By.xpath("//div[text()='Expiration date']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Expiration date']")));
			driver.findElement(By.xpath("//div[text()='Expiration date']")).click();
			wait.until(ExpectedConditions.visibilityOf(HybrisBO.returnXpathOfSubscription()));
			HybrisBO.clickOnSubscription();
			wait.until(ExpectedConditions.visibilityOf(HybrisBO.returnXpathOfNewRenewedOrderId()));
			String newOrder = HybrisBO.getNewSubscriptionOrderId();
			Reporting.updateTestReport("New Subscription OrderID: " + newOrder + " was fetched ",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}*/
	//This test case was designed to prepare the data for the data preparation of other test cases
	@Test
	public void Data_Preparation() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("Data_Preparation");
			//Data for TC03_standalone login and TC05_Resetet password from login page
			driver.get(excelOperation.getTestData("VET_Login_URL", "Generic_Dataset", "Data"));
			String first = excelOperation.getTestData("TC01", "VET_Test_Data", "First_Name");
			String last = excelOperation.getTestData("TC01", "VET_Test_Data", "Last_Name");
			String password = excelOperation.getTestData("TC01", "VET_Test_Data", "Password");
			VET.enterFirstName(first);
			VET.enterLastName(last);
			String emailId = VET.enterEmailId();
			VET.enterPassword(password);
			VET.clickCreateAccountButton();
			String expectedUrl = "https://vetqa.wiley.com/my-account/update-profile/";
			String actualUrl = driver.getCurrentUrl();
			VET.logOut(driver);
			if (actualUrl.equalsIgnoreCase(expectedUrl)) {
				Reporting.updateTestReport("Data was entered successfully ",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				excelOperation.updateTestData("TC03", "VET_Test_Data", "Email_Id", emailId);
				excelOperation.updateTestData("TC04", "VET_Test_Data", "Email_Id", emailId);
				excelOperation.updateTestData("TC06", "VET_Test_Data", "Email_Id", emailId);
				excelOperation.updateTestData("TC12", "VET_Test_Data", "Email_Id", emailId);


			} else {
				Reporting.updateTestReport("Data couldn't be entered", CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
			}
			//Data for Eloqua, Riskified, Auto-renew toggle, edit profile and Update Billing address
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30, 1));
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("TC01", "VET_Test_Data", "URL"));
			VET.addSubscriptionToCart();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'My Cart')]")));
				VET.continueToCheckout();
				VET.enterFirstName(excelOperation.getTestData("TC01", "VET_Test_Data", "First_Name"));
				VET.enterLastName(excelOperation.getTestData("TC01", "VET_Test_Data", "Last_Name"));
				String emailIdVet = VET.enterEmailId();
				VET.enterPassword(excelOperation.getTestData("TC01", "VET_Test_Data", "Password"));
				VET.clickCreateAccountButton();
				VET.enterBillingFirstName(excelOperation.getTestData("TC01", "VET_Test_Data", "First_Name"));
				VET.enterBillingLastName(excelOperation.getTestData("TC01", "VET_Test_Data", "Last_Name"));
				VET.selectCountry(excelOperation.getTestData("TC01", "VET_Test_Data", "Country"));
				VET.enterAddressLine1(excelOperation.getTestData("TC01", "VET_Test_Data", "Address_line1"));
				VET.enterCity(excelOperation.getTestData("TC01", "VET_Test_Data", "City"));
				VET.enterState(excelOperation.getTestData("TC01", "VET_Test_Data", "State"));
				VET.enterZip(excelOperation.getTestData("TC01", "VET_Test_Data", "Zip_Code"));
				VET.enterPhoneNumber(excelOperation.getTestData("TC01", "VET_Test_Data", "Phone_Number"));
				VET.checkBoxBilling();
				VET.continueToCardDetailsPage();
				String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
				System.out.println(payment_gateway);
				if (payment_gateway.contains("WPG")) {
					/* WPG Code */
					PaymentGateway.paymentWPG_VET(driver, VET, "TC01", SS_path);
				} else {
					/** WPS Code **/
					PaymentGateway.paymentWPS_VET(driver, VET, "TC01", SS_path);
				}
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed."
							+ " A confirmation email')]")));
					VET.fetchOrderId();
					VET.fetchTax();
					VET.fetchTotal();
					excelOperation.updateTestData("TC09", "VET_Test_Data", "Email_Id", emailIdVet);
					excelOperation.updateTestData("TC17", "VET_Test_Data", "Email_Id", emailIdVet);
					excelOperation.updateTestData("TC19", "VET_Test_Data", "Email_Id", emailIdVet);
					excelOperation.updateTestData("TC20", "VET_Test_Data", "Email_Id", emailIdVet);
					VET.logOut(driver);
				}

				catch(Exception e)
				{Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
				VET.logOut(driver);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("User was not on cart page and caused timeout exception"
						+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}




	/*
	 * @Date: 2/1/23
	 * @Description: Places an order with last name "RiskifiedDenie" which results in riskified declined  order
	 */
	@Test
	public void TC21_Riskified_Declined_Order() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC21_Riskified_Declined_Order");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("TC21", "VET_Test_Data", "URL"));
			VET.clickOnGetStarted();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'My Cart')]")));
				System.out.println("Cart page came");
				VET.clickOnContinueButton();
				try {
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Create an Account or Login')]")));
					VET.enterFirstName(excelOperation.getTestData("TC21", "VET_Test_Data", "First_Name"));
					VET.enterLastName(excelOperation.getTestData("TC21", "VET_Test_Data", "Last_Name"));
					String email = VET.enterEmailId();
					VET.enterPassword(excelOperation.getTestData("TC21", "VET_Test_Data", "Password"));
					VET.clickCreateAccountButton();
					try {
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//div[contains(text(),'Billing Address')]")));
						VET.enterBillingFirstName(excelOperation.getTestData("TC21", "VET_Test_Data", "First_Name"));
						VET.enterBillingLastName(excelOperation.getTestData("TC21", "VET_Test_Data", "Last_Name"));
						VET.selectCountry(excelOperation.getTestData("TC21", "VET_Test_Data", "Country"));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("addressLine1")));
							VET.enterAddressLine1(excelOperation.getTestData("TC21", "VET_Test_Data", "Address_line1"));
							VET.enterCity(excelOperation.getTestData("TC21", "VET_Test_Data", "City"));	
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'State')]")));
								VET.enterState(excelOperation.getTestData("TC21", "VET_Test_Data", "State"));
							}
							catch(Exception e){
								Reporting.updateTestReport(
										"State field was not present for this country: "+
												excelOperation.getTestData("TC15", "AGS_Test_Data", "Country"),
												CaptureScreenshot.getScreenshot(SS_path),StatusDetails.INFO);
							}

							VET.enterZip(excelOperation.getTestData("TC21", "VET_Test_Data", "Zip_Code"));
							VET.enterPhoneNumber(excelOperation.getTestData("TC21", "VET_Test_Data", "Phone_Number"));
							VET.checkBoxBilling();
							VET.continueToCardDetailsPage();
							String payment_gateway = excelOperation.getTestData("Payment_Gateway", "Generic_Dataset", "Data");
							System.out.println(payment_gateway);
							if (payment_gateway.contains("WPG")) {

								PaymentGateway.paymentWPG_VET(driver, VET, "TC21", SS_path);
							} else {

								PaymentGateway.paymentWPS_VET(driver, VET, "TC21", SS_path);
							}

							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Your order is being processed."
										+ " A confirmation email')]")));
								String orderID = VET.fetchOrderId();
								String tax = VET.fetchTax();
								String total = VET.fetchTotal();
								excelOperation.updateTestData("TC21", "VET_Test_Data", "Order_Id", orderID);
								excelOperation.updateTestData("TC21", "VET_Test_Data", "Tax", tax);
								excelOperation.updateTestData("TC21", "VET_Test_Data", "Total", total);
								excelOperation.updateTestData("TC21", "VET_Test_Data", "Email_Id", email);
								VET.logOut(driver);
								driver.get(excelOperation.getTestData("Riskified_URL", "Generic_Dataset", "Data"));
								RiskifiedRepo.enterRiskifiedUserId(excelOperation.getTestData("Riskified_User_ID", "Generic_Dataset", "Data"),SS_path);
								RiskifiedRepo.enterRiskifiedPassword(excelOperation.getTestData("Riskified_Password", "Generic_Dataset", "Data"),SS_path);
								RiskifiedRepo.clickOnRiskifiedSignInButton(SS_path);
								try {
									wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Account Settings')]")));
									RiskifiedRepo.selectVETFromDropdown(SS_path);
									try {
										wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Vet Consult - Wiley')]")));
										RiskifiedRepo.searchOrderIdInRiskified(excelOperation.getTestData("TC21", "VET_Test_Data", "Order_Id"),SS_path);
										RiskifiedRepo.checkIfOrderIdIsPresentInRiskified(driver,SS_path);
										RiskifiedRepo.checkIfOrderIdIsDeclinedInRiskified(driver,SS_path);
									}
									catch(Exception e){
										Reporting.updateTestReport("VET order search page of Riskified couldn't be loaded and caused timeout exception ", CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.FAIL);
									}
								}
								catch(Exception e) {
									Reporting.updateTestReport("User was not in Riskified homepage"
											+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
								}
							}

							catch(Exception e) {
								Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
								VET.logOut(driver);}	
							
						}
						catch(Exception e) {
							Reporting.updateTestReport("Address line 1 field was not clickable"
									+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
							VET.logOut(driver);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Billing address page was not loaded and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						VET.logOut(driver);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Create Account / Login page was not loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					VET.logOut(driver);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Cart page was not loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				VET.logOut(driver);
			}	
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			VET.logOut(driver);

		}
	}




}
