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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Select;

import PageObjectRepo.app_Eloqua_Repo;
import PageObjectRepo.app_Hybris_BO_Repo;
import PageObjectRepo.app_Riskified_Repo;
import PageObjectRepo.app_VET_Repo;
import utilities.CaptureScreenshot;
import utilities.EmailValidation;
import utilities.LogTextFile;
import utilities.DriverModule;
import utilities.PaymentGateway;
import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

import java.io.IOException;
import java.lang.reflect.Method;
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
	@BeforeMethod
	public void nameBefore(Method method)
	{
	    System.out.println("Test case: " + method.getName()+" execution started");       
	}
	
	@AfterMethod
	public void nameAfter(Method method)
	{
	    System.out.println("Test case: " + method.getName()+" execution completed");       
	}

	/*
	 * Author : Arun 
	 * Description : New User Registration with placing Order
	 */
	@Test
	public void TC01_Anonymous_User_Registration() throws IOException {
		try {

			Reporting.test = Reporting.extent.createTest("TC01_Anonymous_User_Registration");
			LogTextFile.writeTestCaseStatus("TC01_Anonymous_User_Registration", "Test case");
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("TC01", "VET_Test_Data", "URL"));
			VET.addSubscriptionToCart();
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
					PaymentGateway.paymentVET("WPG",driver, VET, "TC01", SS_path);
				} else {
					/** WPS Code **/
					PaymentGateway.paymentVET("WPS",driver, VET, "TC01", SS_path);
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
					if(EmailValidation.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
						Reporting.updateTestReport("Order Confirmation mail was received",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						EmailValidation.validateOrderConfirmationMailContent("VET",driver,SS_path,tax,"",total);
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
			LogTextFile.writeTestCaseStatus("TC02_Create_Account", "Test case");
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
			LogTextFile.writeTestCaseStatus("TC03_StandaloneLogin", "Test case");
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
			LogTextFile.writeTestCaseStatus("TC04_Login_During_Checkout", "Test case");
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("TC04", "VET_Test_Data", "URL"));
			VET.addSubscriptionToCart();
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
				PaymentGateway.paymentVET("WPG",driver, VET, "TC04", SS_path);
			} else {
				/** WPS Code **/
				PaymentGateway.paymentVET("WPS",driver, VET, "TC04", SS_path);
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
	 * @Description: Resets the password in My Account page
	 */
	@Test
	public void TC05_Reset_Password_My_Account() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_Reset_Password_My_Account");
			LogTextFile.writeTestCaseStatus("TC05_Reset_Password_My_Account", "Test case");
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
			LogTextFile.writeTestCaseStatus("TC06_ResetPasswordFromLoginPage", "Test case");
			driver.get(excelOperation.getTestData("VET_Login_URL", "Generic_Dataset", "Data"));
			VET.ForgotchangePassword();
			VET.RetriveLoginInfo(excelOperation.getTestData("TC06", "VET_Test_Data", "Email_Id"));
			VET.clickOnSubmit();
			VET.AlertMessage();
			driver.get(excelOperation.getTestData("Yopmail_URL",
					"Generic_Dataset", "Data"));
			VET.enteryopmail(excelOperation.getTestData("TC06", "VET_Test_Data","Email_Id"));
			VET.clickonbutton();
			EmailValidation.forgotPasswordMailForVet(driver, SS_path, VET);
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
			LogTextFile.writeTestCaseStatus("TC07_Verify_Tax", "Test case");
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("TC07", "VET_Test_Data", "URL"));
			VET.addSubscriptionToCart();
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
				PaymentGateway.paymentVET("WPG",driver, VET, "TC07", SS_path);
			} else {
				/** WPS Code **/
				PaymentGateway.paymentVET("WPS",driver, VET, "TC07", SS_path);
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
				if(EmailValidation.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
					Reporting.updateTestReport("Order Confirmation mail was received",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					EmailValidation.validateOrderConfirmationMailContent("VET",driver,SS_path,tax,"",total);
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
	 * @Author: Anindita
	 * @Description: Places an order in VET with promo code
	 */
	@Test
	public void TC11_Place_Order_With_Promo_Code() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC11_Place_Order_With_Promo_Code");
			LogTextFile.writeTestCaseStatus("TC11_Place_Order_With_Promo_Code", "Test case");
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
					PaymentGateway.paymentVET("WPG",driver, VET, "TC11", SS_path);
				} else {
					/** WPS Code **/
					PaymentGateway.paymentVET("WPS",driver, VET, "TC11", SS_path);
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
					if(EmailValidation.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
						Reporting.updateTestReport("Order Confirmation mail was received",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						EmailValidation.validateOrderConfirmationMailContent("VET",driver,SS_path,tax,"",total);
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
			LogTextFile.writeTestCaseStatus("TC12_PlaceOrderwithNonUSAddress", "Test case");
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
				PaymentGateway.paymentVET("WPG",driver, VET, "TC12", SS_path);
			} else {
				/** WPS Code **/
				PaymentGateway.paymentVET("WPS",driver, VET, "TC12", SS_path);
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



	/*
	 * Author : Arun 
	 * Description : This flow is to verify if the Auto-toggle button is on or off in the Manage subscription.
	 */
	@Test
	public void TC16_Manage_Subscription() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC16_Manage_Subscription");
			LogTextFile.writeTestCaseStatus("TC16_Manage_Subscription", "Test case");
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
			LogTextFile.writeTestCaseStatus("TC18_ZeroOrder", "Test case");
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
			LogTextFile.writeTestCaseStatus("TC19_Edit_Profile_from_my_account_page", "Test case");
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

	/*
	 * @Description: This method updates the billing address in my account page
	 */
	@Test
	public void TC20_Edit_Billing_address_in_my_account() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC20_Edit_Billing_address_in_my_account");
			LogTextFile.writeTestCaseStatus("TC20_Edit_Billing_address_in_my_account", "Test case");
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

	/*
	 *  Anindita on 13/07/22
	 *  @Description: Validates if the orders are reaching Eloqua
	 */
	@Test
	public void TC08_Eloqua_validation() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC08_Eloqua_validation");
			LogTextFile.writeTestCaseStatus("TC08_Eloqua_validation", "Test case");
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

	/*
	 * @Author: Anindita
	 * @Description: Validates the auto renew toggle functionality
	 */
	@Test
	public void TC17_Auto_Renew_Toggle_validation() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC17_Auto_Renew_Toggle_validation");
			LogTextFile.writeTestCaseStatus("TC17_Auto_Renew_Toggle_validation", "Test case");
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
	
	//This test case was designed to prepare the data for the data preparation of other test cases
	@Test
	public void Data_Preparation() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("Data_Preparation");
			LogTextFile.writeTestCaseStatus("Data_Preparation", "Test case");
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
					PaymentGateway.paymentVET("WPG",driver, VET, "TC01", SS_path);
				} else {
					/** WPS Code **/
					PaymentGateway.paymentVET("WPS",driver, VET, "TC01", SS_path);
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
	 * @Author: Anindita
	 * @Description: Places an order with last name "RiskifiedDenied" which results in riskified declined  order
	 */
	@Test
	public void TC21_Riskified_Declined_Order() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC21_Riskified_Declined_Order");
			LogTextFile.writeTestCaseStatus("TC21_Riskified_Declined_Order", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			driver.get(excelOperation.getTestData("TC21", "VET_Test_Data", "URL"));
			VET.addSubscriptionToCart();
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

								PaymentGateway.paymentVET("WPG",driver, VET, "TC21", SS_path);
							} else {

								PaymentGateway.paymentVET("WPS",driver, VET, "TC21", SS_path);
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
