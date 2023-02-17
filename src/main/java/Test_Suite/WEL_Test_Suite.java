package Test_Suite;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import PageObjectRepo.app_Hybris_BO_Repo;
import PageObjectRepo.app_WEL_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.OrderConfirmationMail;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class WEL_Test_Suite extends DriverModule {
	app_WEL_Repo WEL;
	app_Hybris_BO_Repo HybrisBO;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	public static String EmailConfirmationText = "//button/div[contains(text(),'Order Confirmation')]";

	@BeforeTest
	public void initializeRepo() {
		WEL = PageFactory.initElements(driver, app_WEL_Repo.class);

	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: validating the standalone user registraion
	 */
	@Test
	/* (invocationCount = 10) */
	public void TC01_Standalone_UserRegistration() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC01_Standalone_UserRegistration");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			WEL.ClickingOnLoginButton();
			WEL.ClickOnCreateOne();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
				WEL.EnterFirstName(excelOperation.getTestData("TC01", "WEL_Test_Data", "First_Name"));
				WEL.EnterLastName(excelOperation.getTestData("TC01", "WEL_Test_Data", "Last_Name"));
				String email = WEL.enterEmailIdInCreateAccountForm();
				WEL.confirmEmailIdInCreateAccountForm(email);
				// ScrollingWebPage.PageScrolldown(driver, 0, 400);
				ScrollingWebPage.PageDown(driver, SS_path);
				WEL.EnterPassword(excelOperation.getTestData("TC01", "WEL_Test_Data", "Password"));
				WEL.ClickonTermsCheckBox();
				WEL.ClickonAgreementCheckBox();
				WEL.ClickingOnCreateAccoutButton();
				if (WEL.CheckAccountTextAftreLogin())
					Reporting.updateTestReport("Account Link is appearing after successful Login",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("Failed display the Account Link",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				excelOperation.updateTestData("TC01", "WEL_Test_Data", "Email_Address", email);
				excelOperation.updateTestData("TC08", "WEL_Test_Data", "Email_Address", email);

			} catch (Exception e) {
				Reporting.updateTestReport("Help button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {

			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: This test cases is to Login with newly Registered user
	 */
	@Test
	public void TC04_LoginFor_NewlyRegisteredUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC04_LoginFor_NewlyRegisterUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			WEL.ClickingOnLoginButton();
			WEL.EnterUserNameOnLoginPage(excelOperation.getTestData("TC01", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordOnLoginPage(excelOperation.getTestData("TC01", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginButton();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'I Accept')]")));
				Reporting.updateTestReport("Wiley & WEL Privacy Agreement banner was displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				WEL.clickonAcceptButtonOnWileyWELPrivacyAgreement();
			} catch (Exception e) {
				Reporting.updateTestReport("Wiley & WEL Privacy Agreement banner was not displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
			}
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'ACCOUNT')]")));
				Reporting.updateTestReport("Account Link is appearing after successful Login",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport("Failed display the Account Link", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: This test cases is to Standalone ForgotPassword
	 */
	@Test
	public void TC06_Standalone_ForgotPassword() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC06_Standalone_ForgotPassword");
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			WEL.ClickingOnLoginButton();
			WEL.EnterUserNameOnLoginPage(excelOperation.getTestData("TC01", "WEL_Test_Data", "Email_Address"));
			WEL.ClickonForgotPassword();
			WEL.EnterEmailAddressOnForgotPassword(excelOperation.getTestData("TC01", "WEL_Test_Data", "Email_Address"));
			WEL.ClickSubmitButtonnForgotPasswordPage();
			String title = driver.getTitle();
			if (title.equals("Reset Password | Wiley Efficient Learning"))
				Reporting.updateTestReport(
						"Password reset instructions have been sent to your e-mail address text should be displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to display the Forgot Password instructions",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

		catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: This test cases is UserRegistraion During checkout for Digital
	 * cart with Non Us Billing Address
	 */
	@Test
	public void TC03_UserRegistration_DuringCheckoutForDigitalCartwith_NonUSBillingAddress() throws IOException {
		try {
			Reporting.test = Reporting.extent
					.createTest("TC03_UserRegistration_DuringCheckoutForDigitalCartwith_NonUSBillingAddress");
			Thread.sleep(1000);
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			// ScrollingWebPage.PageScrolldown(driver, 0, 600);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			// ScrollingWebPage.PageScrolldown(driver, 0, 300);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			// ScrollingWebPage.PageScrolldown(driver, 0, 400);
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}

			WEL.ClickonCouponRemove();
			WEL.ClickonCheckOutOnCartPage();
			String Gemail = WEL.EnterGuestUser();
			WEL.ClickingOnCreateAccoutButton();
			WEL.GuestConfirmEmailId(Gemail);
			WEL.EnterPassword(excelOperation.getTestData("TC03", "WEL_Test_Data", "Password"));
			WEL.ClickonAgreementCheckBox();
			WEL.ClickingOnSaveAndContinue();
			WEL.GuestFirstName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Lname"));
			WEL.selectBillCountry(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Country"));
			WEL.AddressLineOne(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Address_line1"));
			WEL.EnterZipecode(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Zip_Code"));
			WEL.EnterCity(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_City/ Province"));
			WEL.EnterPhoneNumber(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Phone_Number"));
			Thread.sleep(2000);
			ScrollingWebPage.PageScrolldown(driver, 0, -300, SS_path);
			WEL.ClickOnBackTOCart();
			WEL.ClickOnRemoveOnCartPage();
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: This test cases is To Verify the UserLogin During Checkout
	 */
	@Test
	public void TC05_UserLoginDuring_Checkout() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_UserLoginDuring_Checkout");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			// ScrollingWebPage.PageScrolldown(driver, 0, 600);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			// ScrollingWebPage.PageScrolldown(driver, 0, 300);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			ScrollingWebPage.PageDown(driver, SS_path);
			// ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}

			WEL.ClickonCouponRemove();
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC05", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC05", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			ScrollingWebPage.PageScrolldown(driver, 0, -300, SS_path);
			WEL.ClickOnBackTOCart();
			WEL.ClickOnRemoveOnCartPage();
			WEL.ClickAccountCartPage();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'I Accept')]")));
				Reporting.updateTestReport("Wiley & WEL Privacy Agreement banner was displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				WEL.clickonAcceptButtonOnWileyWELPrivacyAgreement();
			} catch (Exception e) {
				Reporting.updateTestReport("Wiley & WEL Privacy Agreement banner was not displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
			}
			WEL.ClickonSignOut();
			Thread.sleep(1000);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: This test cases is To Verify the UserLogin During Checkout
	 */
	@Test
	public void TC07_ForgotPasswordDuring_Checkout() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC07_ForgotPasswordDuring_Checkout");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			// ScrollingWebPage.PageScrolldown(driver, 0, 600);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			// ScrollingWebPage.PageScrolldown(driver, 0, 300);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			ScrollingWebPage.PageDown(driver, SS_path);
			// ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}

			WEL.ClickonCouponRemove();

			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC07", "WEL_Test_Data", "Email_Address"));
			WEL.ClickonForgotPasswordOnCheckoutPage();
			WEL.EnterEmailAddressOnForgotPassword(excelOperation.getTestData("TC07", "WEL_Test_Data", "Email_Address"));
			WEL.ClickSubmitButtonnForgotPasswordPage();
			String title = driver.getTitle();
			if (title.equals("Reset Password | Wiley Efficient Learning"))
				Reporting.updateTestReport(
						"Password reset instructions have been sent to your e-mail address text should be displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to display the Forgot Password instructions",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.ClickCartIconOnForgotPasswordPage();
			WEL.ClickOnRemoveOnCartPage();
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	/*
	 * @Author: Vishnu
	 *
	 * @Description: This test case is verify the Apply the Student Discount In
	 * Anonymous Cart
	 */
	@Test

	public void TC09_AddToCart_WithStudentDiscount_InAnonymousCart() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC09_AddToCart_WithStudentDiscount_InAnonymousCart");
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			// ScrollingWebPage.PageScrolldown(driver, 0, 600);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(1000);
			// ScrollingWebPage.PageScrolldown(driver, 0, 300);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			ScrollingWebPage.PageDown(driver, SS_path);
			// ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}

			WEL.ClickonCouponRemove();
			WEL.ClickOnAddaDiscountCode();
			WEL.EnterDiscountCode(excelOperation.getTestData("TC09", "WEL_Test_Data", "PromoCode"));
			WEL.ClickOnDiscountApplyButton();
			WEL.ClickOnRemoveOnCartPage();
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	@Test
	public void TC10_AddToCart_WithPromoCode_InAnonymousCart() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC10_AddToCart_WithPromoCode_InAnonymousCart");
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			// ScrollingWebPage.PageScrolldown(driver, 0, 600);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			// ScrollingWebPage.PageScrolldown(driver, 0, 300);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			// ScrollingWebPage.PageScrolldown(driver, 0, 400);
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}

			WEL.ClickonCouponRemove();
			WEL.ClickOnAddaDiscountCode();
			WEL.EnterDiscountCode(excelOperation.getTestData("TC10", "WEL_Test_Data", "PromoCode"));
			WEL.ClickOnDiscountApplyButton();
			WEL.ClickOnRemoveOnCartPage();
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	@Test
	public void TC11_AddToCartFor_LoggedInUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC11_AddToCartFor_LoggedInUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			WEL.ClickingOnLoginButton();
			WEL.EnterUserNameOnLoginPage(excelOperation.getTestData("TC01", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordOnLoginPage(excelOperation.getTestData("TC01", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginButton();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'I Accept')]")));
				Reporting.updateTestReport("Wiley & WEL Privacy Agreement banner was displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				WEL.clickonAcceptButtonOnWileyWELPrivacyAgreement();
			} catch (Exception e) {
				Reporting.updateTestReport("Wiley & WEL Privacy Agreement banner was not displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
			}
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'ACCOUNT')]")));
				Reporting.updateTestReport("Account Link is appearing after successful Login",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			} catch (Exception e) {
				Reporting.updateTestReport("Failed display the Account Link", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
			WEL.ClickWELIconHomePage();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			// ScrollingWebPage.PageScrolldown(driver, 0, 600);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			// ScrollingWebPage.PageScrolldown(driver, 0, 300);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			// ScrollingWebPage.PageScrolldown(driver, 0, 400);
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}

			WEL.ClickonCouponRemove();
			Thread.sleep(2000);
			WEL.ClickOnRemoveOnCartPage();
			Thread.sleep(1000);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	@Test
	public void TC12_QunatityRestriction_ForPhysicalProduct() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC02_UserRegistration_DuringCheckoutForPhysicalCart");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			// ScrollingWebPage.PageScrolldown(driver, 0, 510);
			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(
						driver.findElement(By.xpath("//div[@class='fe_flex grid_1']/a[text()='CIA']"))));
				WEL.ClickOnCIAProduct();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SHOP')]")));
					WEL.ClickOnShopCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(
								By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnViewCourseForCIAProduct();
						try {
							wait.until(ExpectedConditions
									.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							WEL.ClickOnCIAAddProduct();
							WEL.ClickOnSelectQuantity();
							WEL.ClickOnRemoveOnCartPage();
						} catch (Exception e) {
							Reporting.updateTestReport(
									"Add to cart button on CIA PDP was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"The View Course link on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("CIA Product link was not visible on homepage and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC02_UserRegistration_DuringCheckoutForPhysicalCart() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC02_UserRegistration_DuringCheckoutForPhysicalCart");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();

					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}
						WEL.ClickonCheckOutOnCartPage();
						String Gemail = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(Gemail);
						WEL.EnterPassword(excelOperation.getTestData("TC02", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						ScrollingWebPage.PageScrollUp(driver, 0, -300, SS_path);
						WEL.ClickOnBackTOCart();
						WEL.ClickOnRemoveOnCartPage();
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC14_ExtraDiscount_CouponCodeGOVT() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC14_ExtraDiscount_CouponCodeGOVT");
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCPAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
			// ScrollingWebPage.PageDown(driver);
			Thread.sleep(1000);
			WEL.ClickonViewCourseForCPAProduct();
			ScrollingWebPage.PageScrolldown(driver, 0, 400, SS_path);
			// ScrollingWebPage.PageDown(driver);
			Thread.sleep(1000);
			WEL.ClickOnCPAAddProduct();
			WEL.ClickOnAddaDiscountCode();
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.EnterExtraDiscountCode(excelOperation.getTestData("TC14", "WEL_Test_Data", "PromoCode"));
			WEL.ClickOnDiscountApplyButton();
			WEL.ClickOnRemoveOnCartPage();
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	@Test
	public void TC13_PlaceOrder_WithmultipleProducts_And_PromotionForExistingUser() throws IOException {
		try {
			Reporting.test = Reporting.extent
					.createTest("TC13_PlaceOrder_WithmultipleProducts_And_PromotionForExistingUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			String urls[] = excelOperation.getTestData("TC13", "WEL_Test_Data", "URL").split(",");

			driver.get(urls[0]);
			try {
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			}
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));

			} catch (Exception e) {

			}
			driver.get(urls[1]);
			// ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			Thread.sleep(2000);
			WEL.ClickOnCPAAddProduct();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}

			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC13", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC13", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickOnAddaDiscountCode();
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.EnterCouponExtraDiscountCode(excelOperation.getTestData("TC13", "WEL_Test_Data", "PromoCode"));
			WEL.ClickOnDiscountApplyButton();
			WEL.ClickonCheckOutOnCartPage();
			WEL.SelectingUSEButton();
			Thread.sleep(1000);
			WEL.SaveContinueCheckOutPage();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
				WEL.enterCardHolderName(excelOperation.getTestData("TC13", "WEL_Test_Data", "Guest_Fname"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
				WEL.enterCardNumber(excelOperation.getTestData("TC13", "WEL_Test_Data", "Card_Number"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
				WEL.selectExpirationMonthFromDropDown(
						excelOperation.getTestData("TC13", "WEL_Test_Data", "Expiry_Month"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
				WEL.selectExpirationYearFromDropDown(
						excelOperation.getTestData("TC13", "WEL_Test_Data", "Expiry_Year"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
				WEL.enterCVV_Number(excelOperation.getTestData("TC13", "WEL_Test_Data", "CVV"));
				driver.switchTo().defaultContent();
				WEL.SaveAndContinueCheckOut();
				WEL.clickOnPlaceOrderButton();
				String orderID = WEL.fetchOrderId();
				excelOperation.updateTestData("TC13", "WEL_Test_Data", "Order_Id", orderID);
				ScrollingWebPage.PageDown(driver, SS_path);
				String tax = WEL.fetchTaxAmount();
				excelOperation.updateTestData("TC13", "WEL_Test_Data", "Tax", tax);
				String orderTotal = WEL.fetchOrderTotal();
				excelOperation.updateTestData("TC13", "WEL_Test_Data", "Order_Total", orderTotal);

				WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Card Details was not enetred due to Time Out Exception: " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC15_PlaceOrder_CMACPACFA_DigitalProducts_ForNewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC15_PlaceOrder_CMACPACFA_DigitalProducts_ForNewUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			String urls[] = excelOperation.getTestData("TC15", "WEL_Test_Data", "URL").split(",");

			driver.get(urls[0]);
			try {
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			}
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));

			} catch (Exception e) {

			}
			driver.get(urls[1]);
			try {
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			}

			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			driver.get(urls[2]);
			try {
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			}
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCFAAddProduct();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			WEL.ClickonCheckOutOnCartPage();

			String Gemail = WEL.EnterGuestUser();
			WEL.ClickingOnCreateAccoutButton();
			WEL.GuestConfirmEmailId(Gemail);
			WEL.EnterPassword(excelOperation.getTestData("TC15", "WEL_Test_Data", "Password"));
			WEL.ClickonAgreementCheckBox();
			WEL.ClickingOnSaveAndContinue();
			WEL.GuestFirstName(excelOperation.getTestData("TC15", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC15", "WEL_Test_Data", "Guest_Lname"));
			WEL.selectBillCountry(excelOperation.getTestData("TC15", "WEL_Test_Data", "Bill_Country"));
			WEL.AddressLineOne(excelOperation.getTestData("TC15", "WEL_Test_Data", "Bill_Address_line1"));
			WEL.EnterZipecode(excelOperation.getTestData("TC15", "WEL_Test_Data", "Bill_Zip_Code"));
			WEL.EnterCity(excelOperation.getTestData("TC15", "WEL_Test_Data", "Bill_City/ Province"));
			WEL.EnterPhoneNumber(excelOperation.getTestData("TC15", "WEL_Test_Data", "Bill_Phone_Number"));

			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
				WEL.enterCardHolderName(excelOperation.getTestData("TC15", "WEL_Test_Data", "Guest_Fname"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
				WEL.enterCardNumber(excelOperation.getTestData("TC15", "WEL_Test_Data", "Card_Number"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
				WEL.selectExpirationMonthFromDropDown(
						excelOperation.getTestData("TC15", "WEL_Test_Data", "Expiry_Month"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
				WEL.selectExpirationYearFromDropDown(
						excelOperation.getTestData("TC15", "WEL_Test_Data", "Expiry_Year"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
				WEL.enterCVV_Number(excelOperation.getTestData("TC15", "WEL_Test_Data", "CVV"));
				driver.switchTo().defaultContent();
				WEL.SaveAndContinueCheckOut();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
					try {
						if (WEL.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed())
							WEL.clickOnUseSelectedBillingAddressButtonAddressDoctor();
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Failed to click Address on Address SUggestion due to timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					}
					WEL.clickOnPlaceOrderButton();
					String orderID = WEL.fetchOrderId();
					excelOperation.updateTestData("TC15", "WEL_Test_Data", "Order_Id", orderID);
					// JS.executeScript("window.scrollBy(0,600)");
					ScrollingWebPage.PageDown(driver, SS_path);
					String tax = WEL.fetchTaxValue();
					excelOperation.updateTestData("TC15", "WEL_Test_Data", "Tax", tax);
					String orderTotal = WEL.fetchOrderTotal();
					excelOperation.updateTestData("TC15", "WEL_Test_Data", "Order_Total", orderTotal);
					WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
					Thread.sleep(2000);
				} catch (Exception e) {
					Reporting.updateTestReport("Help button was not appeared due to timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"The name field in card information step was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC16_PlaceOrder_CMACPACFA_DigitalProducts_ForExistingUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC16_PlaceOrder_CMACPACFA_DigitalProducts_ForExistingUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			String urls[] = excelOperation.getTestData("TC16", "WEL_Test_Data", "URL").split(",");

			driver.get(urls[0]);
			try {
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			}
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));

			} catch (Exception e) {

			}
			driver.get(urls[1]);
			try {
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			}

			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			driver.get(urls[2]);
			try {
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			}
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCFAAddProduct();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			WEL.ClickonCheckOutOnCartPage();

			WEL.EnterexistingUserName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC16", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickOnEnterNewAddressButtonOnBillingPage();
			WEL.GuestFirstName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Guest_Lname"));
			WEL.selectBillCountry(excelOperation.getTestData("TC16", "WEL_Test_Data", "Bill_Country"));
			WEL.AddressLineOne(excelOperation.getTestData("TC16", "WEL_Test_Data", "Bill_Address_line1"));
			WEL.EnterZipecode(excelOperation.getTestData("TC16", "WEL_Test_Data", "Bill_Zip_Code"));
			WEL.EnterCity(excelOperation.getTestData("TC16", "WEL_Test_Data", "Bill_City/ Province"));
			WEL.EnterPhoneNumber(excelOperation.getTestData("TC16", "WEL_Test_Data", "Bill_Phone_Number"));

			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
				WEL.enterCardHolderName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Guest_Fname"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
				WEL.enterCardNumber(excelOperation.getTestData("TC16", "WEL_Test_Data", "Card_Number"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
				WEL.selectExpirationMonthFromDropDown(
						excelOperation.getTestData("TC16", "WEL_Test_Data", "Expiry_Month"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
				WEL.selectExpirationYearFromDropDown(
						excelOperation.getTestData("TC16", "WEL_Test_Data", "Expiry_Year"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
				WEL.enterCVV_Number(excelOperation.getTestData("TC16", "WEL_Test_Data", "CVV"));
				driver.switchTo().defaultContent();
				WEL.SaveAndContinueCheckOut();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
					try {
						if (WEL.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed())
							WEL.clickOnUseSelectedBillingAddressButtonAddressDoctor();
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Failed to click Address on Address SUggestion due to timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					}
					WEL.clickOnPlaceOrderButton();
					String orderID = WEL.fetchOrderId();
					excelOperation.updateTestData("TC16", "WEL_Test_Data", "Order_Id", orderID);
					// JS.executeScript("window.scrollBy(0,600)");
					ScrollingWebPage.PageDown(driver, SS_path);
					String tax = WEL.fetchTaxValue();
					excelOperation.updateTestData("TC16", "WEL_Test_Data", "Tax", tax);
					String orderTotal = WEL.fetchOrderTotal();
					excelOperation.updateTestData("TC16", "WEL_Test_Data", "Order_Total", orderTotal);
					WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
					Thread.sleep(2000);
				} catch (Exception e) {
					Reporting.updateTestReport("Help button was not appeared due to timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"The name field in card information step was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC20_PlaceOrder_OfOther_ProductsCIACAIAFor_NewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC20_PlaceOrder_OfOther_ProductsCIACAIAFor_NewUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			WEL.ClickOnShopCourseForCIAProduct();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnViewCourseForCIAProduct();
			Thread.sleep(2000);
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickOnCIAAddProduct();

			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(2000);
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickOnCAIAProduct();
			Thread.sleep(1000);
			WEL.ClickOnLevel1TestBankForCAIAProduct();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCAIAAddProduct();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}

			WEL.ClickonCheckOutOnCartPage();
			String Gemail = WEL.EnterGuestUser();
			WEL.ClickingOnCreateAccoutButton();
			WEL.GuestConfirmEmailId(Gemail);
			WEL.EnterPassword(excelOperation.getTestData("TC15", "WEL_Test_Data", "Password"));
			WEL.ClickonAgreementCheckBox();
			WEL.ClickingOnSaveAndContinue();
			WEL.GuestFirstName(excelOperation.getTestData("TC20", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC20", "WEL_Test_Data", "Guest_Lname"));
			WEL.selectShipCountry(excelOperation.getTestData("TC20", "WEL_Test_Data", "Sh_Country"));
			WEL.ShipAddressLineOne(excelOperation.getTestData("TC20", "WEL_Test_Data", "Sh_Address_line1"));
			WEL.ShipPostCode(excelOperation.getTestData("TC20", "WEL_Test_Data", "Sh_Zip_Code"));

			/*
			 * WEL.ShipTownCity(excelOperation.getTestData("TC15", "WEL_Test_Data",
			 * "Sh_City/ Province")); WEL.ShipState(excelOperation.getTestData("TC15",
			 * "WEL_Test_Data", "Sh_State"));
			 */

			WEL.ShipPhoneNumber(excelOperation.getTestData("TC20", "WEL_Test_Data", "Sh_Phone_Number"));
			WEL.ShipSaveAndContinueButton();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
				WEL.enterCardHolderName(excelOperation.getTestData("TC20", "WEL_Test_Data", "Guest_Fname"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
				WEL.enterCardNumber(excelOperation.getTestData("TC20", "WEL_Test_Data", "Card_Number"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
				WEL.selectExpirationMonthFromDropDown(
						excelOperation.getTestData("TC20", "WEL_Test_Data", "Expiry_Month"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
				WEL.selectExpirationYearFromDropDown(
						excelOperation.getTestData("TC20", "WEL_Test_Data", "Expiry_Year"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
				WEL.enterCVV_Number(excelOperation.getTestData("TC20", "WEL_Test_Data", "CVV"));
				driver.switchTo().defaultContent();
				WEL.SaveAndContinueCheckOut();
				WEL.clickOnPlaceOrderButton();
				String orderID = WEL.fetchOrderId();
				excelOperation.updateTestData("TC20", "WEL_Test_Data", "Order_Id", orderID);
				ScrollingWebPage.PageDown(driver, SS_path);
				String tax = WEL.fetchTaxAmount();
				excelOperation.updateTestData("TC20", "WEL_Test_Data", "Tax", tax);
				String orderTotal = WEL.fetchOrderTotal();
				excelOperation.updateTestData("TC20", "WEL_Test_Data", "Order_Total", orderTotal);

			} catch (Exception e) {
				Reporting.updateTestReport(
						"The name field in card information step was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC21_PlaceOrder_CPACMACFA_FreeTrailFor_NewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC21_PlaceOrder_CPACMACFA_FreeTrailFor_Newuser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageScrolldown(driver, 0, 600, SS_path);

			WEL.ClickonCPAProduct();
			// Thread.sleep(1000);
			WEL.ClickOnFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC21", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC21", "WEL_Test_Data", "Last_Name"));
			WEL.EnterFreeTrailNewtUser();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailCountry(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_Country"));
			// ScrollingWebPage.PageDown(driver);
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailState(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_State"));
			// ScrollingWebPage.PageDown(driver);
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailPassword(excelOperation.getTestData("TC21", "WEL_Test_Data", "Password"));
			// ScrollingWebPage.PageDown(driver);
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			// ScrollingWebPage.PageDown(driver);
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			String cpaftextmessage = WEL.FreeTrailCPAText();
			// Thread.sleep(2000);
			if (cpaftextmessage.equals("You’re almost set!"))
				Reporting.updateTestReport(
						"CPA FreeTail is successfully completed and page having text You’re almost set!",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the CPA FreeTail Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			WEL.FreeTrailWELIcon();
			// Thread.sleep(1000);
			ScrollingWebPage.PageScrolldown(driver, 0, 600, SS_path);
			WEL.ClickonCMAProduct();
			WEL.ClickOnFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC21", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC21", "WEL_Test_Data", "Last_Name"));
			WEL.EnterFreeTrailNewtUser();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailCountry(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_Country"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailState(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_State"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailPassword(excelOperation.getTestData("TC21", "WEL_Test_Data", "Password"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			// Thread.sleep(1000);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			WEL.FreeTrailCPAText();
			// Thread.sleep(2000);
			if (cpaftextmessage.equals("You’re almost set!"))
				Reporting.updateTestReport(
						"CMA FreeTail is successfully completed and page having text You’re almost set!",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the CMA FreeTail Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			WEL.FreeTrailWELIcon();
			// Thread.sleep(1000);
			ScrollingWebPage.PageScrolldown(driver, 0, 400, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.ClickonCFAProduct();
			WEL.ClickOnFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC21", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC21", "WEL_Test_Data", "Last_Name"));
			WEL.EnterFreeTrailNewtUser();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailCountry(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_Country"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailState(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_State"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailPassword(excelOperation.getTestData("TC21", "WEL_Test_Data", "Password"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			WEL.FreeTrailCPAText();
			// Thread.sleep(2000);
			if (cpaftextmessage.equals("You’re almost set!"))
				Reporting.updateTestReport(
						"CFA FreeTail is successfully completed and page having text You’re almost set!",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the CFA FreeTail Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC22_PlaceOrder_CPACMACFA_FreeTrailFor_ExistingUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC22_PlaceOrder_CPACMACFA_FreeTrailFor_ExistingUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageScrolldown(driver, 0, 600, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.ClickonCPAProduct();
			// Thread.sleep(1000);
			WEL.ClickOnFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC22", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC22", "WEL_Test_Data", "Last_Name"));
			WEL.EnterFreeTrailEmail(excelOperation.getTestData("TC22", "WEL_Test_Data", "Email_Address"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailCountry(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_Country"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailState(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_State"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailPassword(excelOperation.getTestData("TC22", "WEL_Test_Data", "Password"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			WEL.FreeTraiModelPopUpLoginButton();
			WEL.EnterFreeTrailUserNameOnLoginPage(excelOperation.getTestData("TC22", "WEL_Test_Data", "Email_Address"));
			WEL.EnterFreeTrailPasswordOnLoginPage(excelOperation.getTestData("TC22", "WEL_Test_Data", "Password"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTraiModelPopUpSubmitButton();
			WEL.FreeTrailCountry(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_Country"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailState(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_State"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			Thread.sleep(1000);
			ScrollingWebPage.PageScrollUp(driver, 0, -1500, SS_path);
			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//a[@class='navbar-brand brand-logo-top-desktop']")));
				WEL.FreeTrailWELIcon();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click on WEL Icon due to timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			ScrollingWebPage.PageScrolldown(driver, 0, 600, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_1']/a[2]")));
				WEL.ClickonCMAProduct();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click CMAProduct due to timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='free-trial-btn  ']")));
				WEL.ClickOnFreeTrail();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click FreeTrial Icon due to timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.FreeTrailCountry(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_Country"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailState(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_State"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			Thread.sleep(500);
			ScrollingWebPage.PageScrollUp(driver, 0, -1500, SS_path);
			WEL.FreeTrailWELIcon();
			Thread.sleep(1000);
			ScrollingWebPage.PageScrolldown(driver, 0, 400, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.ClickonCFAProduct();
			WEL.ClickOnFreeTrail();

			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailCountry(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_Country"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailState(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_State"));
			ScrollingWebPage.PageScrolldown(driver, 0, 100, SS_path);

			// ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='form-box']//input[@name='termsAndConditions']")));
				WEL.FreeTrailTermsAndCOndtionsCheckBox();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click Terms and Conditions due to timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC23_PlaceOrder_OfOther_ProductsCIA_FreeTrailFor_NewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC23_PlaceOrder_OfOther_ProductsCIA_FreeTrailFor_NewUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageScrolldown(driver, 0, 700, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.ClickOnCIAProduct();
			Thread.sleep(1000);
			WEL.ClickOnCIAFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC23", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC23", "WEL_Test_Data", "Last_Name"));
			WEL.EnterFreeTrailNewtUser();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailCountry(excelOperation.getTestData("TC23", "WEL_Test_Data", "Sh_Country"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailState(excelOperation.getTestData("TC23", "WEL_Test_Data", "Sh_State"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailPassword(excelOperation.getTestData("TC23", "WEL_Test_Data", "Password"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			String ciaftextmessage = WEL.FreeTrailCIAText();
			Thread.sleep(2000);
			if (ciaftextmessage.equals("You’re almost set!"))
				Reporting.updateTestReport(
						"CPA FreeTail is successfully completed and page having text You’re almost set!",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the CPA FreeTail Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC24_PlaceOrder_UsingSavedShippingAddress_ForExistingUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC24_PlaceOrder_UsingSavedShippingAddress_ForExistingUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			// ScrollingWebPage.PageScrolldown(driver, 0, 600, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			// ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAPrinteBook();
			Thread.sleep(2000);
			// ScrollingWebPage.PageScrolldown(driver, 0, 400, SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC16", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}

			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			WEL.SelectingUSEButton();
			Thread.sleep(1000);
			WEL.SaveContinueCheckOutPage();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
				WEL.enterCardHolderName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Fname"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
				WEL.enterCardNumber(excelOperation.getTestData("TC16", "WEL_Test_Data", "Card_Number"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
				WEL.selectExpirationMonthFromDropDown(
						excelOperation.getTestData("TC16", "WEL_Test_Data", "Expiry_Month"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
				WEL.selectExpirationYearFromDropDown(
						excelOperation.getTestData("TC16", "WEL_Test_Data", "Expiry_Year"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
				WEL.enterCVV_Number(excelOperation.getTestData("TC16", "WEL_Test_Data", "CVV"));
				driver.switchTo().defaultContent();
				WEL.SaveAndContinueCheckOut();
				WEL.clickOnPlaceOrderButton();
				String orderID = WEL.fetchOrderId();
				excelOperation.updateTestData("TC16", "WEL_Test_Data", "Order_Id", orderID);
				// JS.executeScript("window.scrollBy(0,600)");
				// ScrollingWebPage.PageDown(driver, SS_path);
				ScrollingWebPage.PageDown(driver, SS_path);
				String tax = WEL.fetchTaxValue();
				excelOperation.updateTestData("TC16", "WEL_Test_Data", "Tax", tax);
				String orderTotal = WEL.fetchOrderTotal();
				excelOperation.updateTestData("TC16", "WEL_Test_Data", "Order_Total", orderTotal);

			} catch (Exception e) {
				Reporting.updateTestReport(
						"The name field in card information step was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC25_ShippingMethodfor_USUKAustralia_CanadaIndiaSingapore() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC25_ShippingMethodfor_USUKAustralia_CanadaIndiaSingapore");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAPrinteBook();
			Thread.sleep(2000);
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC16", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnAddNewAddressButton();
			WEL.GuestFirstName(excelOperation.getTestData("TC50", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC50", "WEL_Test_Data", "Guest_Lname"));
			String countries = excelOperation.getTestData("TC25", "WEL_Test_Data", "Shipping_Countries");
			String words[] = countries.split(",");
			for (String word : words) {
				String xpathOfCountry = "//option[contains(text(),'" + word + "')]";
				if (driver.findElement(By.xpath(xpathOfCountry)).isDisplayed()) {
					WEL.selectShipCountry(word);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
						WEL.ShippingMethodValidation();
						Thread.sleep(2000);
					} catch (Exception e) {
						Reporting.updateTestReport("Addressline1 is not appearing and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} else
					Reporting.updateTestReport(word + " was not present in the country dropdown",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}

		catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC31_ShippingAndBilling_AddressSamefor_NewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC31_ShippingAndBilling_AddressSamefor_NewUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}

						WEL.ClickonCheckOutOnCartPage();
						String Gemail = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(Gemail);
						WEL.EnterPassword(excelOperation.getTestData("TC02", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						WEL.GuestFirstName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_Country"));
						WEL.ShipAddressLineOne(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_Address_line1"));
						WEL.ShipPostCode(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_Zip_Code"));

						WEL.ShipPhoneNumber(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_Phone_Number"));
						WEL.ShipSaveAndContinueButton();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
							WEL.enterCardHolderName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Fname"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
							WEL.enterCardNumber(excelOperation.getTestData("TC15", "WEL_Test_Data", "Card_Number"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
							WEL.selectExpirationMonthFromDropDown(
									excelOperation.getTestData("TC15", "WEL_Test_Data", "Expiry_Month"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
							WEL.selectExpirationYearFromDropDown(
									excelOperation.getTestData("TC31", "WEL_Test_Data", "Expiry_Year"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
							WEL.enterCVV_Number(excelOperation.getTestData("TC15", "WEL_Test_Data", "CVV"));
							driver.switchTo().defaultContent();
							WEL.SaveAndContinueCheckOut();
							ScrollingWebPage.PageScrollUp(driver, 0, -300, SS_path);
							WEL.ClickOnBackTOCart();
							WEL.ClickOnRemoveOnCartPage();
						} catch (Exception e) {
							Reporting.updateTestReport(
									"The name field in card information step was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC32_ShippingAndBilling_AddressDifferentfor_NewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC32_ShippingAndBilling_AddressDifferentfor_NewUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				// Thread.sleep(1000);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct(); //
					Thread.sleep(2000);
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}

						WEL.ClickonCheckOutOnCartPage();
						String Gemail = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(Gemail);
						WEL.EnterPassword(excelOperation.getTestData("TC02", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						WEL.GuestFirstName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_Country"));
						WEL.ShipAddressLineOne(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_Address_line1"));
						WEL.ShipPostCode(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_Zip_Code"));

						WEL.ShipTownCity(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_City/ Province"));
						WEL.enterState(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_State"));

						WEL.ShipPhoneNumber(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_Phone_Number"));
						WEL.ShipSaveAndContinueButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
							ScrollingWebPage.PageScrolldown(driver, 0, 500, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("//label[@id='sameAsBillingLabel']")));
								WEL.ShipAndBillAddressSection();
								WEL.selectBillCountry(
										excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Country"));
								WEL.AddressLineOne(
										excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Address_line1"));
								WEL.EnterZipecode(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Zip_Code"));
								WEL.EnterCity(
										excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_City/ Province"));
								WEL.EnterPhoneNumber(
										excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Phone_Number"));
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Shipping same as billing was not clickable and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport("The help button was not visible and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	@Test
	public void TC27_AddressSuggestion_ByAddressDoctorInShippingpage() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC27_AddressSuggestion_ByAddressDoctorInShippingpage");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						WEL.ClickonCheckOutOnCartPage();
						WEL.EnterexistingUserName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Email_Address"));
						WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC16", "WEL_Test_Data", "Password"));
						WEL.ClickonLoginAndContinue();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}

						WEL.ClickonCheckOutOnCartPage();
						WEL.ClickonCheckOutOnCartPage();
						WEL.ClickOnEnterNewAddressButtonOnShippingPage();
						WEL.GuestFirstName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC27", "WEL_Test_Data", "Sh_Country"));
						WEL.ShipAddressLineOne(excelOperation.getTestData("TC27", "WEL_Test_Data", "Sh_Address_line1"));
						WEL.ShipPostCode(excelOperation.getTestData("TC27", "WEL_Test_Data", "Sh_Zip_Code"));

						/*
						 * WEL.ShipTownCity(excelOperation.getTestData("TC15", "WEL_Test_Data",
						 * "Sh_City/ Province")); WEL.ShipState(excelOperation.getTestData("TC15",
						 * "WEL_Test_Data", "Sh_State"));
						 */

						WEL.ShipPhoneNumber(excelOperation.getTestData("TC27", "WEL_Test_Data", "Sh_Phone_Number"));
						WEL.ClickingOnSaveAndContinue();
						try {
							if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
								WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
						} catch (Exception e) {
							Reporting.updateTestReport(
									"Failed to click Address on Address SUggestion due to timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
						}
						WEL.ClickOnBackTOCart();
						WEL.ClickOnRemoveOnCartPage();
						WEL.ClickAccountCartPage();
						WEL.ClickonSignOut();
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	@Test
	public void TC33_AddressSuggestion_ByAddressDoctorInBillingpage() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC33_AddressSuggestion_ByAddressDoctorInBillingpage");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			// JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			// JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}

			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC16", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickOnEnterNewAddressButtonOnBillingPage();
			WEL.GuestFirstName(excelOperation.getTestData("TC33", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC33", "WEL_Test_Data", "Guest_Lname"));
			WEL.selectBillCountry(excelOperation.getTestData("TC33", "WEL_Test_Data", "Bill_Country"));
			WEL.AddressLineOne(excelOperation.getTestData("TC33", "WEL_Test_Data", "Bill_Address_line1"));
			WEL.EnterZipecode(excelOperation.getTestData("TC33", "WEL_Test_Data", "Bill_Zip_Code"));
			WEL.EnterPhoneNumber(excelOperation.getTestData("TC33", "WEL_Test_Data", "Bill_Phone_Number"));
			// WEL.ClickingOnSaveAndContinue();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
				WEL.enterCardHolderName(excelOperation.getTestData("TC33", "WEL_Test_Data", "Guest_Fname"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
				WEL.enterCardNumber(excelOperation.getTestData("TC33", "WEL_Test_Data", "Card_Number"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
				WEL.selectExpirationMonthFromDropDown(
						excelOperation.getTestData("TC33", "WEL_Test_Data", "Expiry_Month"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
				WEL.selectExpirationYearFromDropDown(
						excelOperation.getTestData("TC33", "WEL_Test_Data", "Expiry_Year"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
				WEL.enterCVV_Number(excelOperation.getTestData("TC33", "WEL_Test_Data", "CVV"));
				driver.switchTo().defaultContent();
				WEL.SaveAndContinueCheckOut();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
					try {
						if (WEL.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed())
							WEL.clickOnUseSelectedBillingAddressButtonAddressDoctor();
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Failed to click Address on Address SUggestion due to timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					}
					WEL.ClickOnBackTOCart();
					WEL.ClickOnRemoveOnCartPage();

				} catch (Exception e) {
					Reporting.updateTestReport("Help button was not visible and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"The name field in card information step was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	@Test
	public void TC30_PlaceOrder_UsingSavedBillingAddress_ForExistingUser() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC30_PlaceOrder_UsingSavedBillingAddress_ForExistingUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			// JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			// JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC30", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC30", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();

			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			WEL.SelectingUSEButton();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
				WEL.enterCardHolderName(excelOperation.getTestData("TC30", "WEL_Test_Data", "Guest_Fname"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
				WEL.enterCardNumber(excelOperation.getTestData("TC30", "WEL_Test_Data", "Card_Number"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
				WEL.selectExpirationMonthFromDropDown(
						excelOperation.getTestData("TC30", "WEL_Test_Data", "Expiry_Month"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
				WEL.selectExpirationYearFromDropDown(
						excelOperation.getTestData("TC30", "WEL_Test_Data", "Expiry_Year"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
				WEL.enterCVV_Number(excelOperation.getTestData("TC30", "WEL_Test_Data", "CVV"));
				driver.switchTo().defaultContent();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
					WEL.SaveAndContinueCheckOut();
					WEL.clickOnPlaceOrderButton();
					String orderID = WEL.fetchOrderId();
					ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
					excelOperation.updateTestData("TC30", "WEL_Test_Data", "Order_Id", orderID);
					String tax = WEL.fetchTaxValue();
					excelOperation.updateTestData("TC30", "WEL_Test_Data", "Tax", tax);
					String orderTotal = WEL.fetchOrderTotal();
					excelOperation.updateTestData("TC30", "WEL_Test_Data", "Order_Total", orderTotal);

				} catch (Exception e) {
					Reporting.updateTestReport("Help button was not visible and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"The name field in card information step was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	@Test
	public void TC51_PlaceOrder_IndiaAddress() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC51_PlaceOrder_IndiaAddress");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}
						WEL.ClickonCheckOutOnCartPage();
						String emailId = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(emailId);
						WEL.EnterPassword(excelOperation.getTestData("TC51", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						WEL.GuestFirstName(excelOperation.getTestData("TC51", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC51", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC51", "WEL_Test_Data", "Sh_Country"));
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							WEL.ShipAddressLineOne(
									excelOperation.getTestData("TC51", "WEL_Test_Data", "Sh_Address_line1"));
							WEL.ShipPostCode(excelOperation.getTestData("TC51", "WEL_Test_Data", "Sh_Zip_Code"));
							// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='city']")));
							WEL.ShipTownIndiaCity(
									excelOperation.getTestData("TC51", "WEL_Test_Data", "Sh_City/ Province"));

							try {
								wait1.until(ExpectedConditions
										.elementToBeClickable(By.xpath("//input[@id='address.region']")));
								WEL.enterState(excelOperation.getTestData("TC51", "WEL_Test_Data", "Sh_State"));
							} catch (Exception e) {
								try {
									wait1.until(ExpectedConditions
											.elementToBeClickable(By.xpath("//select[@id='address.region']")));
									WEL.selectStateFromDropsown(
											excelOperation.getTestData("TC51", "WEL_Test_Data", "Sh_State"));
								} catch (Exception e1) {

									Reporting.updateTestReport(
											"State field was not clickable and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

								}

							}
							WEL.ShipPhoneNumber(excelOperation.getTestData("TC51", "WEL_Test_Data", "Sh_Phone_Number"));
							WEL.ShipSaveAndContinueButton();
							try {
								if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
									WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Failed to click Address on Address SUggestion due to timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}
							driver.switchTo()
									.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
								WEL.enterCardHolderName(
										excelOperation.getTestData("TC51", "WEL_Test_Data", "Guest_Fname"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
								WEL.enterCardNumber(excelOperation.getTestData("TC51", "WEL_Test_Data", "Card_Number"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
								WEL.selectExpirationMonthFromDropDown(
										excelOperation.getTestData("TC51", "WEL_Test_Data", "Expiry_Month"));
								driver.switchTo().defaultContent();
								driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
								WEL.selectExpirationYearFromDropDown(
										excelOperation.getTestData("TC51", "WEL_Test_Data", "Expiry_Year"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
								WEL.enterCVV_Number(excelOperation.getTestData("TC51", "WEL_Test_Data", "CVV"));
								driver.switchTo().defaultContent();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
									WEL.SaveAndContinueCheckOut();
									ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
									WEL.clickOnPlaceOrderButton();
									String orderID = WEL.fetchOrderId();
									ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
									excelOperation.updateTestData("TC51", "WEL_Test_Data", "Order_Id", orderID);
									String tax = WEL.fetchTaxValue();
									excelOperation.updateTestData("TC51", "WEL_Test_Data", "Tax", tax);
									String orderTotal = WEL.fetchOrderTotal();
									excelOperation.updateTestData("TC51", "WEL_Test_Data", "Order_Total", orderTotal);
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Help button was not visible and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"The name field in card information step was not clickable and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	@Test
	public void TC50_PlaceOrder_CanadaAddress() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC50_PlaceOrder_CanadaAddress");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							waitt.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}
						WEL.ClickonCheckOutOnCartPage();
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='guest.email']")));
							String emailId = WEL.EnterGuestUser();
							WEL.ClickingOnCreateAccoutButton();
							WEL.GuestConfirmEmailId(emailId);
							WEL.EnterPassword(excelOperation.getTestData("TC50", "WEL_Test_Data", "Password"));
							WEL.ClickonAgreementCheckBox();
							WEL.ClickingOnSaveAndContinue();
							WEL.GuestFirstName(excelOperation.getTestData("TC50", "WEL_Test_Data", "Guest_Fname"));
							WEL.GuestLastName(excelOperation.getTestData("TC50", "WEL_Test_Data", "Guest_Lname"));
							WEL.selectShipCountry(excelOperation.getTestData("TC50", "WEL_Test_Data", "Sh_Country"));
							// Thread.sleep(2000);
							try {
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.xpath("//input[@id='line1']")));
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
									WEL.ShipAddressLineOne(
											excelOperation.getTestData("TC50", "WEL_Test_Data", "Sh_Address_line1"));
									try {
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
										WEL.ShipPostCode(
												excelOperation.getTestData("TC50", "WEL_Test_Data", "Sh_Zip_Code"));
										WEL.ShipPhoneNumber(
												excelOperation.getTestData("TC50", "WEL_Test_Data", "Sh_Phone_Number"));
										/*
										 * try { wait.until(ExpectedConditions
										 * .elementToBeClickable(By.xpath("//input[@id='city']")));
										 * WEL.EnterCity(excelOperation.getTestData("TC50", "WEL_Test_Data",
										 * "Sh_City/ Province")); } catch (Exception e) {
										 * 
										 * }
										 */
										WEL.ShipSaveAndContinueButton();
										try {
											if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
													.isDisplayed())
												WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
										} catch (Exception e) {
											Reporting.updateTestReport(
													"Failed to click Address on Address SUggestion due to timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
										}
										driver.switchTo().frame(
												driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
										try {
											wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
											WEL.enterCardHolderName(
													excelOperation.getTestData("TC30", "WEL_Test_Data", "Guest_Fname"));
											driver.switchTo().defaultContent();
											driver.switchTo().frame(
													driver.findElement(By.xpath(".//iframe[@title='card number']")));
											WEL.enterCardNumber(
													excelOperation.getTestData("TC30", "WEL_Test_Data", "Card_Number"));
											driver.switchTo().defaultContent();
											driver.switchTo().frame(
													driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
											WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC30",
													"WEL_Test_Data", "Expiry_Month"));
											driver.switchTo().defaultContent();
											driver.switchTo().frame(
													driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
											WEL.selectExpirationYearFromDropDown(
													excelOperation.getTestData("TC30", "WEL_Test_Data", "Expiry_Year"));
											driver.switchTo().defaultContent();
											driver.switchTo().frame(
													driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
											WEL.enterCVV_Number(
													excelOperation.getTestData("TC30", "WEL_Test_Data", "CVV"));
											driver.switchTo().defaultContent();
											try {
												wait.until(ExpectedConditions.visibilityOfElementLocated(
														By.xpath("//div[@class='helpButton']")));
												WEL.SaveAndContinueCheckOut();
												ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
												WEL.clickOnPlaceOrderButton();
												String orderID = WEL.fetchOrderId();
												ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
												excelOperation.updateTestData("TC50", "WEL_Test_Data", "Order_Id",
														orderID);
												String tax = WEL.fetchTaxValue();
												excelOperation.updateTestData("TC50", "WEL_Test_Data", "Tax", tax);
												String orderTotal = WEL.fetchOrderTotal();
												excelOperation.updateTestData("TC50", "WEL_Test_Data", "Order_Total",
														orderTotal);

											} catch (Exception e) {
												Reporting.updateTestReport(
														"Help button was not visible and caused timeout exception",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										} catch (Exception e) {
											Reporting.updateTestReport(
													"The name field in card information step was not clickable and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Help button was not visible and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Help button was not visible and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Failed to enter the username caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	@Test
	public void TC53_PlaceOrder_JapanAddress() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC53_PlaceOrder_JapanAddress");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}
						WEL.ClickonCheckOutOnCartPage();
						String emailId = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(emailId);
						WEL.EnterPassword(excelOperation.getTestData("TC53", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						WEL.GuestFirstName(excelOperation.getTestData("TC53", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC53", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_Country"));
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							WEL.ShipAddressLineOne(
									excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_Address_line1"));
							WEL.ShipPostCode(excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_Zip_Code"));
							// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='city']")));
							WEL.ShipTownIndiaCity(
									excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_City/ Province"));

							try {
								wait1.until(ExpectedConditions
										.elementToBeClickable(By.xpath("//input[@id='address.region']")));
								WEL.enterState(excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_State"));
							} catch (Exception e) {
								try {
									wait1.until(ExpectedConditions
											.elementToBeClickable(By.xpath("//select[@id='address.region']")));
									WEL.selectStateFromDropsown(
											excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_State"));
								} catch (Exception e1) {

									Reporting.updateTestReport(
											"State field was not clickable and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

								}

							}
							WEL.ShipPhoneNumber(excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_Phone_Number"));
							WEL.ShipSaveAndContinueButton();
							try {
								if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
									WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Failed to click Address on Address SUggestion due to timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}
							driver.switchTo()
									.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
								WEL.enterCardHolderName(
										excelOperation.getTestData("TC53", "WEL_Test_Data", "Guest_Fname"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
								WEL.enterCardNumber(excelOperation.getTestData("TC53", "WEL_Test_Data", "Card_Number"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
								WEL.selectExpirationMonthFromDropDown(
										excelOperation.getTestData("TC53", "WEL_Test_Data", "Expiry_Month"));
								driver.switchTo().defaultContent();
								driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
								WEL.selectExpirationYearFromDropDown(
										excelOperation.getTestData("TC53", "WEL_Test_Data", "Expiry_Year"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
								WEL.enterCVV_Number(excelOperation.getTestData("TC53", "WEL_Test_Data", "CVV"));
								driver.switchTo().defaultContent();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
									WEL.SaveAndContinueCheckOut();
									ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
									WEL.clickOnPlaceOrderButton();
									String orderID = WEL.fetchOrderId();
									ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
									excelOperation.updateTestData("TC53", "WEL_Test_Data", "Order_Id", orderID);
									String tax = WEL.fetchTaxValue();
									excelOperation.updateTestData("TC53", "WEL_Test_Data", "Tax", tax);
									String orderTotal = WEL.fetchOrderTotal();
									excelOperation.updateTestData("TC53", "WEL_Test_Data", "Order_Total", orderTotal);
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Help button was not visible and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"The name field in card information step was not clickable and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	@Test
	public void TC52_PlaceOrder_ChinaAddress() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC52_PlaceOrder_ChinaAddress");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}
						WEL.ClickonCheckOutOnCartPage();
						String emailId = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(emailId);
						WEL.EnterPassword(excelOperation.getTestData("TC52", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						WEL.GuestFirstName(excelOperation.getTestData("TC52", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC52", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC52", "WEL_Test_Data", "Sh_Country"));
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							WEL.ShipAddressLineOne(
									excelOperation.getTestData("TC52", "WEL_Test_Data", "Sh_Address_line1"));
							WEL.ShipPostCode(excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_Zip_Code"));
							// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='city']")));
							WEL.ShipTownIndiaCity(
									excelOperation.getTestData("TC52", "WEL_Test_Data", "Sh_City/ Province"));

							try {
								wait1.until(ExpectedConditions
										.elementToBeClickable(By.xpath("//input[@id='address.region']")));
								WEL.enterState(excelOperation.getTestData("TC52", "WEL_Test_Data", "Sh_State"));
							} catch (Exception e) {
								try {
									wait1.until(ExpectedConditions
											.elementToBeClickable(By.xpath("//select[@id='address.region']")));
									WEL.selectStateFromDropsown(
											excelOperation.getTestData("TC52", "WEL_Test_Data", "Sh_State"));
								} catch (Exception e1) {

									Reporting.updateTestReport(
											"State field was not clickable and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

								}

							}
							WEL.ShipPhoneNumber(excelOperation.getTestData("TC52", "WEL_Test_Data", "Sh_Phone_Number"));
							WEL.ShipSaveAndContinueButton();
							try {
								if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
									WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Failed to click Address on Address SUggestion due to timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}
							driver.switchTo()
									.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
								WEL.enterCardHolderName(
										excelOperation.getTestData("TC52", "WEL_Test_Data", "Guest_Fname"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
								WEL.enterCardNumber(excelOperation.getTestData("TC52", "WEL_Test_Data", "Card_Number"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
								WEL.selectExpirationMonthFromDropDown(
										excelOperation.getTestData("TC52", "WEL_Test_Data", "Expiry_Month"));
								driver.switchTo().defaultContent();
								driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
								WEL.selectExpirationYearFromDropDown(
										excelOperation.getTestData("TC52", "WEL_Test_Data", "Expiry_Year"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
								WEL.enterCVV_Number(excelOperation.getTestData("TC52", "WEL_Test_Data", "CVV"));
								driver.switchTo().defaultContent();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
									WEL.SaveAndContinueCheckOut();
									ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
									WEL.clickOnPlaceOrderButton();
									String orderID = WEL.fetchOrderId();
									ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
									excelOperation.updateTestData("TC52", "WEL_Test_Data", "Order_Id", orderID);
									String tax = WEL.fetchTaxValue();
									excelOperation.updateTestData("TC52", "WEL_Test_Data", "Tax", tax);
									String orderTotal = WEL.fetchOrderTotal();
									excelOperation.updateTestData("TC52", "WEL_Test_Data", "Order_Total", orderTotal);
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Help button was not visible and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"The name field in card information step was not clickable and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	@Test
	public void TC34_PlaceOrder_withPaypalPayment_OptionForNewUser() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC34_PlaceOrder_withPaypalPayment_OptionForNewUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_1']/a[2]")));
				WEL.ClickonCMAProduct();
			} catch (Exception e) {
				Reporting.updateTestReport("CMA Product was not appeared in Home caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("(//button[contains(text(),'EXPLORE COURSES')])[1]")));
				WEL.ClickonExploreCourseCMAProduct();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to Clickon Explore Courses caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"(//div[@class='container-fluid course-pkg-background']//div[@class='card-footer bg-transparent course-pkg-cards-footer'])[1]//button[contains(text(),'VIEW COURSE OPTIONS')]")));
				WEL.ClickonViewCourseForCMAProduct();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to Clickon View Courses caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to Click on CMA eBook caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='container-fluid banner-container product-detail-banner mt-4']//div[7]//button")));
				WEL.ClickonCMAAddToCart();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to Click on Add To Cart caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			WEL.ClickonCheckOutOnCartPage();
			String Guestemail = WEL.EnterGuestUser();
			WEL.ClickingOnCreateAccoutButton();
			WEL.GuestConfirmEmailId(Guestemail);
			WEL.EnterPassword(excelOperation.getTestData("TC34", "WEL_Test_Data", "Password"));
			WEL.ClickonAgreementCheckBox();
			WEL.ClickingOnSaveAndContinue();
			WEL.GuestFirstName(excelOperation.getTestData("TC34", "WEL_Test_Data", "First_Name"));
			WEL.GuestLastName(excelOperation.getTestData("TC34", "WEL_Test_Data", "Last_Name"));
			WEL.selectBillCountry(excelOperation.getTestData("TC34", "WEL_Test_Data", "Bill_Country"));
			WEL.AddressLineOne(excelOperation.getTestData("TC34", "WEL_Test_Data", "Bill_Address_line1"));
			WEL.EnterZipecode(excelOperation.getTestData("TC34", "WEL_Test_Data", "Bill_Zip_Code"));
			WEL.enterState(excelOperation.getTestData("TC34", "WEL_Test_Data", "Bill_State"));
			WEL.EnterCity(excelOperation.getTestData("TC34", "WEL_Test_Data", "Bill_City/ Province"));
			WEL.EnterPhoneNumber(excelOperation.getTestData("TC34", "WEL_Test_Data", "Bill_Phone_Number"));
			WEL.SelectPayPalOption();
			WEL.ClickOnProceedToPayPal();
			WEL.EnterPayPalUserName(excelOperation.getTestData("PayPal_UserName", "Generic_Dataset", "Data"));
			WEL.ClickOnNextButtonPayPalLoginPage();
			WEL.EnterPayPalPassword(excelOperation.getTestData("Paypal_Password", "Generic_Dataset", "Data"));
			WEL.ClickOnPaypalLogin();
			WEL.ClickOnPaypalReviewOrder();
			WEL.clickOnPlaceOrderButton();
			String orderID = WEL.fetchOrderId();
			excelOperation.updateTestData("TC34", "WEL_Test_Data", "Order_Id", orderID);
			ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
			String tax = WEL.fetchTaxValue();
			excelOperation.updateTestData("TC34", "WEL_Test_Data", "Tax", tax);
			String orderTotal = WEL.fetchOrderTotal();
			excelOperation.updateTestData("TC34", "WEL_Test_Data", "Order_Total", orderTotal);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC35_PlaceOrder_withPaypalPayment_OptionForExistingUser() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC35_PlaceOrder_withPaypalPayment_OptionForExistingUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_1']/a[2]")));
				WEL.ClickonCMAProduct();
			} catch (Exception e) {
				Reporting.updateTestReport("CMA Product was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("(//button[contains(text(),'EXPLORE COURSES')])[1]")));
				WEL.ClickonExploreCourseCMAProduct();
			} catch (Exception e) {
				Reporting.updateTestReport("Explore CMA Product was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"(//div[@class='container-fluid course-pkg-background']//div[@class='card-footer bg-transparent course-pkg-cards-footer'])[1]//button[contains(text(),'VIEW COURSE OPTIONS')]")));
				WEL.ClickonViewCourseForCMAProduct();
			} catch (Exception e) {
				Reporting.updateTestReport("View Course was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			// Thread.sleep(2000);
			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			} catch (Exception e) {
				Reporting.updateTestReport("CMAeBook was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='container-fluid banner-container product-detail-banner mt-4']//div[7]//button")));
				WEL.ClickonCMAAddToCart();
			} catch (Exception e) {
				Reporting.updateTestReport("Add to Cart is failed and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC35", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC35", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();

			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickOnEnterNewAddressButtonOnBillingPage();
			WEL.GuestFirstName(excelOperation.getTestData("TC35", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC35", "WEL_Test_Data", "Guest_Lname"));
			WEL.selectBillCountry(excelOperation.getTestData("TC35", "WEL_Test_Data", "Bill_Country"));
			WEL.AddressLineOne(excelOperation.getTestData("TC35", "WEL_Test_Data", "Bill_Address_line1"));
			WEL.EnterZipecode(excelOperation.getTestData("TC35", "WEL_Test_Data", "Bill_Zip_Code"));
			WEL.EnterCity(excelOperation.getTestData("TC35", "WEL_Test_Data", "Bill_City/ Province"));
			WEL.EnterPhoneNumber(excelOperation.getTestData("TC35", "WEL_Test_Data", "Bill_Phone_Number"));
			WEL.enterState(excelOperation.getTestData("TC35", "WEL_Test_Data", "Bill_State"));
			WEL.SelectPayPalOption();
			WEL.ClickOnProceedToPayPal();
			driver.manage().deleteAllCookies();

			WEL.ClickOnPaypalReviewOrder();
			WEL.clickOnPlaceOrderButton();
			String orderID = WEL.fetchOrderId();
			excelOperation.updateTestData("TC35", "WEL_Test_Data", "Order_Id", orderID);
			ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
			String tax = WEL.fetchTaxValue();
			excelOperation.updateTestData("TC35", "WEL_Test_Data", "Tax", tax);
			String orderTotal = WEL.fetchOrderTotal();
			excelOperation.updateTestData("TC35", "WEL_Test_Data", "Order_Total", orderTotal);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC36_PlaceOrder_withPaypalCredit_OptionForExistingUser() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			Reporting.test = Reporting.extent.createTest("TC36_PlaceOrder_withPaypalCredit_OptionForExistingUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			// JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAPrinteBook();
			Thread.sleep(2000);
			// JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC36", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC36", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();

			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickOnEnterNewAddressButtonOnShippingPage();
			WEL.GuestFirstName(excelOperation.getTestData("TC36", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC36", "WEL_Test_Data", "Guest_Lname"));
			WEL.selectShipCountry(excelOperation.getTestData("TC36", "WEL_Test_Data", "Sh_Country"));
			WEL.ShipAddressLineOne(excelOperation.getTestData("TC36", "WEL_Test_Data", "Sh_Address_line1"));
			WEL.ShipPostCode(excelOperation.getTestData("TC36", "WEL_Test_Data", "Sh_Zip_Code"));
			WEL.ShipTownCity(excelOperation.getTestData("TC36", "WEL_Test_Data", "Sh_City/ Province"));
			WEL.enterState(excelOperation.getTestData("TC36", "WEL_Test_Data", "Sh_State"));
			WEL.ShipPhoneNumber(excelOperation.getTestData("TC36", "WEL_Test_Data", "Sh_Phone_Number"));
			WEL.ShipSaveAndContinueButton();

			try {
				if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
					WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
			} catch (Exception e) {
				Reporting.updateTestReport("Address Suggestiond page is not appering on Shipping page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
			}

			WEL.SelectPayPalCreditOption();
			WEL.ClickOnProceedToPayPalCredit();
			WEL.EnterPayPalUserName(excelOperation.getTestData("PayPal_UserName", "Generic_Dataset", "Data"));
			WEL.ClickOnNextButtonPayPalLoginPage();
			WEL.EnterPayPalPassword(excelOperation.getTestData("Paypal_Password", "Generic_Dataset", "Data"));
			WEL.ClickOnPaypalLogin();

			WEL.ClickOnPaypalReviewOrder();
			WEL.clickOnPlaceOrderButton();
			String orderID = WEL.fetchOrderId();
			excelOperation.updateTestData("TC34", "WEL_Test_Data", "Order_Id", orderID);
			ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
			String tax = WEL.fetchTaxAmount();
			excelOperation.updateTestData("TC34", "WEL_Test_Data", "Tax", tax);
			String orderTotal = WEL.fetchOrderTotal();
			excelOperation.updateTestData("TC34", "WEL_Test_Data", "Order_Total", orderTotal);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Description: Validates if "we don't ship to PO boxes" message on shipping
	 * section
	 */
	@Test
	public void TC64_Validate_Dont_Ship_To_PO_Box_Message() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC64_Validate_Dont_Ship_To_PO_Box_Message");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();

					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}

						WEL.ClickonCheckOutOnCartPage();
						String emailId = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(emailId);
						WEL.EnterPassword(excelOperation.getTestData("TC64", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						WEL.GuestFirstName(excelOperation.getTestData("TC64", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC64", "WEL_Test_Data", "Guest_Lname"));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							WEL.checkDontShipToPOBoxMessage();
						} catch (Exception e) {
							Reporting.updateTestReport("Addressline1 is not appearing and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Description: Validates if the cookie management is working fine
	 */
	@Test
	public void TC61_Validate_Cookie_Management() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC61_Validate_Cookie_Management");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			WEL.ClickingOnLoginButton();
			WEL.EnterUserNameOnLoginPage(excelOperation.getTestData("TC61", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordOnLoginPage(excelOperation.getTestData("TC61", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginButton();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'I Accept')]")));
				Reporting.updateTestReport("Wiley & WEL Privacy Agreement banner was displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				WEL.clickonAcceptButtonOnWileyWELPrivacyAgreement();
			} catch (Exception e) {
				Reporting.updateTestReport("Wiley & WEL Privacy Agreement banner was not displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
			}
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'ACCOUNT')]")));
				WEL.clickOnCartIconOnMyAccountPage();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
					try {
						Reporting.updateTestReport(
								"wel-cart cookie was present with value: "
										+ driver.manage().getCookieNamed("wel-cart").getValue(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					} catch (Exception e) {
						Reporting.updateTestReport("wel-cart cookie was not present",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					try {
						Reporting.updateTestReport(
								"wel-cart-total-items cookie was present with value: "
										+ driver.manage().getCookieNamed("wel-cart-total-items").getValue(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					} catch (Exception e) {
						Reporting.updateTestReport("wel-cart-total-items cookie was not present",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					try {
						Reporting.updateTestReport(
								"AccessToken cookie was present with value: "
										+ driver.manage().getCookieNamed("AccessToken").getValue(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					} catch (Exception e) {
						Reporting.updateTestReport("AccessToken cookie was not present",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					WEL.clickOnWELHomePageLogoOnCartPage();
					Thread.sleep(1000);
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnCIAProduct();
					try {
						wait.until(
								ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SHOP')]")));
						WEL.ClickOnShopCourseForCIAProduct();
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
									"//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
							ScrollingWebPage.PageDown(driver, SS_path);
							WEL.ClickOnViewCourseForCIAProduct();

							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
								ScrollingWebPage.PageDown(driver, SS_path);
								WEL.ClickOnCIAAddProduct();
								try {
									wait.until(
											ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
									try {
										if (driver.manage().getCookieNamed("wel-cart-total-items").getValue()
												.equalsIgnoreCase("1"))
											Reporting.updateTestReport(
													"wel-cart-total-items cookie was present with value: " + driver
															.manage().getCookieNamed("wel-cart-total-items").getValue()
															+ " as expected",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
										else
											Reporting.updateTestReport(
													"wel-cart-total-items cookie was present with value: " + driver
															.manage().getCookieNamed("wel-cart-total-items").getValue()
															+ " iinstead of 1",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									} catch (Exception e) {
										Reporting.updateTestReport("wel-cart-total-items cookie was not present",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"User was not on cart page and" + "it caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Add to cart button on CIA PDP was not clickable and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"The View Course link on CIA PDP was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("User was not on cart page and" + "it caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("User was not on my account page and" + "it caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Description: Validates WEL Cart Upsells Summary Line
	 */
	@Test
	public void TC74_WEL_Cart_Upsells_Add_Summary_Line() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC74_WEL_Cart_Upsells_Add_Summary_Line");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			driver.get(excelOperation.getTestData("TC74", "WEL_Test_Data", "URL"));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				WEL.clickOnAddToCartButtonOnPDP();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
					ScrollingWebPage.PageScrolldown(driver, 0, 500, SS_path);
					WEL.checkRecommendationTitle();
					WEL.getCartUpsellsSummaryLine();
					WEL.checkAddToCartButtonOnRecommendationTitle();
					WEL.checkViewProductLinkOnRecommendationTitle();
					WEL.clickOnViewProductLinkOnRecommendationTitle();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("(//a[contains(text(),'Product Tour')])[1]")));
						Reporting.updateTestReport("User was on the PDP after clicking on View Product link and",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						WEL.clickOnCartIconOnPDP();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
							ScrollingWebPage.PageScrolldown(driver, 0, 500, SS_path);
							WEL.clickOnAddToCartButtonOnRecommendationTitle();
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
								Reporting.updateTestReport(
										"User was on cart page "
												+ "after clicking on Add to cart in recommendation title",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								driver.get(excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
							} catch (Exception e) {
								Reporting.updateTestReport(
										"User was not on cart page and" + "it caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport("User was not on cart page and " + "it caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not on the PDP after clicking on View Product link and"
										+ "it caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}

				} catch (Exception e) {
					Reporting.updateTestReport("User was not on cart page and" + "it caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Add to cart button on PDP was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 31/01/23
	 * 
	 * @Description: Validates that the heads up validation is not present for the
	 * related products and the pop up should only come in case of trying to add
	 * exact same digital product twice or to add multiple digital products
	 */
	@Test
	public void TC75_Check_Add_To_Cart_Functionality_for_Heads_Up_Validation() throws IOException {
		try {
			Reporting.test = Reporting.extent
					.createTest("TC75_Check_Add_To_Cart_Functionality_for_Heads_Up_Validation");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();

					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
						Thread.sleep(1000);
						WEL.clickOnEBookButtonPDP();
						Thread.sleep(1000);
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
							WEL.clickOnProductLinkOnCartPage();
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
								Thread.sleep(1000);
								WEL.clickOnEBookButtonPDP();
								Thread.sleep(1000);
								ScrollingWebPage.PageDown(driver, SS_path);
								WEL.ClickOnCIAAddProduct();
								try {
									wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
											By.xpath("(//p[@class='modal-notice'])[2]")));
									WEL.checkErrorModal();
									WEL.clickOnKeepShoppingButtonOnErrorModal();
									ScrollingWebPage.PageScrollUp(driver, 0, -500, SS_path);
									WEL.clickOnPackageButtonOnCIAPDP();
									Thread.sleep(1000);
									ScrollingWebPage.PageDown(driver, SS_path);
									WEL.ClickOnCIAAddProduct();
									try {
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.id("cartPageMainTitle")));
										Reporting.updateTestReport(
												"The other variant of CIA was added to cart without any exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
									} catch (Exception e) {
										Reporting.updateTestReport(
												"User was not on cart page and" + "it caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"The error modal didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Add to cart button on CIA PDP was not clickable and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport("User was not on cart page and" + "it caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC28_StudentVerification_ForDigitalCartofUSANDNonUS() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC28_StudentVerification_ForDigitalCartofUSANDNonUS");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
						By.xpath("//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_1']/a[2]"))));
				WEL.ClickonCMAProduct();
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("(//button[contains(text(),'EXPLORE COURSES')])[1]")));
					WEL.ClickonExploreCourseCMAProduct();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"(//div[@class='container-fluid course-pkg-background']//div[@class='card-footer bg-transparent course-pkg-cards-footer'])[1]//button[contains(text(),'VIEW COURSE OPTIONS')]")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickonViewCourseForCMAProduct();
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
									"//div[@class='container-fluid banner-container product-detail-banner mt-4']//div[7]//button")));
							ScrollingWebPage.PageDown(driver, SS_path);
							WEL.ClickonCMAAddToCart();
							WEL.ClickonCheckOutOnCartPage();
							WEL.EnterexistingUserName(
									excelOperation.getTestData("TC28", "WEL_Test_Data", "Email_Address"));
							WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC29", "WEL_Test_Data", "Password"));
							WEL.ClickonLoginAndContinue();
							try {
								wait1.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
								driver.navigate().refresh();
							} catch (Exception e) {

							}
							WEL.ClickonCheckOutOnCartPage();
							WEL.ClickOnAddaDiscountCode();
							WEL.EnterDiscountCode(excelOperation.getTestData("TC28", "WEL_Test_Data", "PromoCode"));
							WEL.ClickOnDiscountApplyButton();
							WEL.ClickonCheckOutOnCartPage();
							WEL.ClickOnEnterNewAddressButtonOnShippingPage();
							WEL.GuestFirstName(excelOperation.getTestData("TC28", "WEL_Test_Data", "First_Name"));
							WEL.GuestLastName(excelOperation.getTestData("TC28", "WEL_Test_Data", "Last_Name"));
							WEL.selectShipCountry(excelOperation.getTestData("TC28", "WEL_Test_Data", "Sh_Country"));
							WEL.ShipAddressLineOne(
									excelOperation.getTestData("TC28", "WEL_Test_Data", "Sh_Address_line1"));
							WEL.ShipPostCode(excelOperation.getTestData("TC28", "WEL_Test_Data", "Sh_Zip_Code"));
							WEL.ShipTownCity(excelOperation.getTestData("TC28", "WEL_Test_Data", "Sh_City/ Province"));
							WEL.enterState(excelOperation.getTestData("TC28", "WEL_Test_Data", "Sh_State"));
							WEL.ShipPhoneNumber(excelOperation.getTestData("TC28", "WEL_Test_Data", "Sh_Phone_Number"));
							WEL.ShipSaveAndContinueButton();

							try {
								if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
									WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
							} catch (Exception e) {
								Reporting.updateTestReport("Address Suggestiond page is not appering on Shipping page",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}

							WEL.VerificationOfStudentForUS();
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='edit']")));
							} catch (Exception e) {
								Reporting.updateTestReport("Failed to Click on Edit Icon on Shipping page",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

							}
							WEL.ClickOnEditIcononShippingPage();
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
										"(//div[@class='savedAddressDivFirstChild']/div[@id='savedAddressButtonsDiv']//button)[1]")));
							} catch (Exception e) {
								Reporting.updateTestReport("Failed to select User Button on Shipping page",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
							WEL.ClickOnShowMoreShippingAddress();
							WEL.ClickOnUseButtonForExistingShippingAddress();
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("//button[@id='addressSubmit']")));
							} catch (Exception e) {
								Reporting.updateTestReport("Failed to click on Save and COntinue button",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
							WEL.ShipSaveAndContinueButton();
							WEL.VerificationOfStudentForNonUS();

						} catch (Exception e) {
							Reporting.updateTestReport("CIA producy is not displayed and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Shop course button not clickabe caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("View Course is clickabe caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click on Add To Cart caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	@Test
	public void TC29_StudentVerification_ForPhysicalCartofUSANDNonUS() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC29_StudentVerification_ForPhysicalCartofUSANDNonUS");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(
						driver.findElement(By.xpath("//div[@class='fe_flex grid_1']/a[text()='CIA']"))));
				WEL.ClickOnCIAProduct();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SHOP')]")));
					WEL.ClickOnShopCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(
								By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnViewCourseForCIAProduct();
						try {
							wait.until(ExpectedConditions
									.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							WEL.ClickOnCIAAddProduct();
							try {
								wait1.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
								driver.navigate().refresh();
							} catch (Exception e) {

							}
							WEL.ClickonCheckOutOnCartPage();
							WEL.EnterexistingUserName(
									excelOperation.getTestData("TC29", "WEL_Test_Data", "Email_Address"));
							WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC29", "WEL_Test_Data", "Password"));
							WEL.ClickonLoginAndContinue();
							try {
								wait1.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
								driver.navigate().refresh();
							} catch (Exception e) {

							}
							WEL.ClickonCheckOutOnCartPage();
							WEL.ClickOnAddaDiscountCode();
							WEL.EnterDiscountCode(excelOperation.getTestData("TC29", "WEL_Test_Data", "PromoCode"));
							WEL.ClickOnDiscountApplyButton();
							WEL.ClickonCheckOutOnCartPage();
							WEL.ClickOnEnterNewAddressButtonOnShippingPage();
							WEL.GuestFirstName(excelOperation.getTestData("TC28", "WEL_Test_Data", "First_Name"));
							WEL.GuestLastName(excelOperation.getTestData("TC28", "WEL_Test_Data", "Last_Name"));
							WEL.selectShipCountry(excelOperation.getTestData("TC29", "WEL_Test_Data", "Sh_Country"));
							WEL.ShipAddressLineOne(
									excelOperation.getTestData("TC29", "WEL_Test_Data", "Sh_Address_line1"));
							WEL.ShipPostCode(excelOperation.getTestData("TC29", "WEL_Test_Data", "Sh_Zip_Code"));
							WEL.ShipTownCity(excelOperation.getTestData("TC29", "WEL_Test_Data", "Sh_City/ Province"));
							WEL.enterState(excelOperation.getTestData("TC29", "WEL_Test_Data", "Sh_State"));
							WEL.ShipPhoneNumber(excelOperation.getTestData("TC29", "WEL_Test_Data", "Sh_Phone_Number"));
							WEL.ShipSaveAndContinueButton();

							try {
								if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
									WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
							} catch (Exception e) {
								Reporting.updateTestReport("Address Suggestiond page is not appering on Shipping page",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}

							WEL.VerificationOfStudentForUS();
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='edit']")));
							} catch (Exception e) {
								Reporting.updateTestReport("Failed to Click on Edit Icon on Shipping page",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

							}
							WEL.ClickOnEditIcononShippingPage();
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
										"(//div[@class='savedAddressDivFirstChild']/div[@id='savedAddressButtonsDiv']//button)[1]")));
							} catch (Exception e) {
								Reporting.updateTestReport("Failed to select User Button on Shipping page",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
							WEL.ClickOnShowMoreShippingAddress();
							WEL.ClickOnUseButtonForExistingShippingAddress();
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("//button[@id='addressSubmit']")));
							} catch (Exception e) {
								Reporting.updateTestReport("Failed to click on Save and COntinue button",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
							WEL.ShipSaveAndContinueButton();
							WEL.VerificationOfStudentForNonUS();

						} catch (Exception e) {
							Reporting.updateTestReport("CIA producy is not displayed and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Shop course button not clickabe caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("View Course is clickabe caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click on Add To Cart caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	@Test
	public void TC72_PlaceExistingUser_OrderForDigitalProduct_CPATestBank_AndValidatThetax() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent
					.createTest("TC72_PlaceExistingUser_OrderForDigitalProduct_CPATestBank_AndValidatThetax");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_1']/a[1]")));
				WEL.ClickonCPAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("(//div[@class='row navitem-div-medium navitem-div'])[2]//a")));
					WEL.ClickOnProductdropdown();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//div[@class='col dropdown-item product-dropdown-desktop-item item1']//li[2]/a")));
						WEL.ClickOnCPATestBank();
						ScrollingWebPage.PageDown(driver, SS_path);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
									"//div[@class='package-selection-container platinum-package']//div[2]//button")));
							WEL.ClickOnAddToCartCPATestBank();
							try {
								wait1.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
								driver.navigate().refresh();
							} catch (Exception e) {

							}

							WEL.ClickonCheckOutOnCartPage();
							WEL.EnterexistingUserName(
									excelOperation.getTestData("TC72", "WEL_Test_Data", "Email_Address"));
							WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC72", "WEL_Test_Data", "Password"));
							WEL.ClickonLoginAndContinue();
							WEL.ClickOnEnterNewAddressButtonOnBillingPage();
							WEL.GuestFirstName(excelOperation.getTestData("TC72", "WEL_Test_Data", "First_Name"));
							WEL.GuestLastName(excelOperation.getTestData("TC72", "WEL_Test_Data", "Last_Name"));
							WEL.selectBillCountry(excelOperation.getTestData("TC72", "WEL_Test_Data", "Bill_Country"));
							WEL.AddressLineOne(
									excelOperation.getTestData("TC72", "WEL_Test_Data", "Bill_Address_line1"));
							WEL.EnterZipecode(excelOperation.getTestData("TC72", "WEL_Test_Data", "Bill_Zip_Code"));
							WEL.EnterCity(excelOperation.getTestData("TC72", "WEL_Test_Data", "Bill_City/ Province"));
							WEL.EnterPhoneNumber(
									excelOperation.getTestData("TC72", "WEL_Test_Data", "Bill_Phone_Number"));
							WEL.enterState(excelOperation.getTestData("TC72", "WEL_Test_Data", "Bill_State"));
							driver.switchTo()
									.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
								WEL.enterCardHolderName(
										excelOperation.getTestData("TC72", "WEL_Test_Data", "Guest_Fname"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
								WEL.enterCardNumber(excelOperation.getTestData("TC72", "WEL_Test_Data", "Card_Number"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
								WEL.selectExpirationMonthFromDropDown(
										excelOperation.getTestData("TC72", "WEL_Test_Data", "Expiry_Month"));
								driver.switchTo().defaultContent();
								driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
								WEL.selectExpirationYearFromDropDown(
										excelOperation.getTestData("TC72", "WEL_Test_Data", "Expiry_Year"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
								WEL.enterCVV_Number(excelOperation.getTestData("TC72", "WEL_Test_Data", "CVV"));
								driver.switchTo().defaultContent();
								WEL.SaveAndContinueCheckOut();
								try {
									if (WEL.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed())
										WEL.clickOnUseSelectedBillingAddressButtonAddressDoctor();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Address Suggestiond page is not appering on Billing page",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}

							} catch (Exception e) {
								Reporting.updateTestReport("Card Details was not enetred" + e.getClass().toString(),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

							WEL.clickOnPlaceOrderButton();
							String orderID = WEL.fetchOrderId();
							excelOperation.updateTestData("TC72", "WEL_Test_Data", "Order_Id", orderID);
							ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
							String tax = WEL.fetchTaxValue();
							excelOperation.updateTestData("TC72", "WEL_Test_Data", "Tax", tax);
							String orderTotal = WEL.fetchOrderTotal();
							excelOperation.updateTestData("TC72", "WEL_Test_Data", "Order_Total", orderTotal);
						} catch (Exception e) {
							Reporting.updateTestReport("CPA Product was not visible and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("CPA Product was select and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

					}
				} catch (Exception e) {
					Reporting.updateTestReport("Failed to click on CPA Product caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click on Add To Cart caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC71_PlaceNewUser_OrderForDigitalProduct_CPATestBank_AndValidatThetax() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent
					.createTest("TC71_PlaceNewUser_OrderForDigitalProduct_CPATestBank_AndValidatThetax");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_1']/a[1]")));
				WEL.ClickonCPAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("(//div[@class='row navitem-div-medium navitem-div'])[2]//a")));
					WEL.ClickOnProductdropdown();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//div[@class='col dropdown-item product-dropdown-desktop-item item1']//li[2]/a")));
						WEL.ClickOnCPATestBank();
						ScrollingWebPage.PageDown(driver, SS_path);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
									"//div[@class='package-selection-container platinum-package']//div[2]//button")));
							WEL.ClickOnAddToCartCPATestBank();
							try {
								wait1.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
								driver.navigate().refresh();
							} catch (Exception e) {

							}

							WEL.ClickonCheckOutOnCartPage();
							String Guestemail = WEL.EnterGuestUser();
							WEL.ClickingOnCreateAccoutButton();
							WEL.GuestConfirmEmailId(Guestemail);
							WEL.EnterPassword(excelOperation.getTestData("TC71", "WEL_Test_Data", "Password"));
							WEL.ClickonAgreementCheckBox();
							WEL.ClickingOnSaveAndContinue();
							WEL.GuestFirstName(excelOperation.getTestData("TC71", "WEL_Test_Data", "Guest_Fname"));
							WEL.GuestLastName(excelOperation.getTestData("TC71", "WEL_Test_Data", "Guest_Lname"));
							WEL.selectBillCountry(excelOperation.getTestData("TC71", "WEL_Test_Data", "Bill_Country"));
							WEL.AddressLineOne(
									excelOperation.getTestData("TC71", "WEL_Test_Data", "Bill_Address_line1"));
							WEL.EnterZipecode(excelOperation.getTestData("TC71", "WEL_Test_Data", "Bill_Zip_Code"));
							WEL.EnterCity(excelOperation.getTestData("TC71", "WEL_Test_Data", "Bill_City/ Province"));
							WEL.EnterPhoneNumber(
									excelOperation.getTestData("TC71", "WEL_Test_Data", "Bill_Phone_Number"));
							WEL.enterState(excelOperation.getTestData("TC72", "WEL_Test_Data", "Bill_State"));
							driver.switchTo()
									.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
								WEL.enterCardHolderName(
										excelOperation.getTestData("TC71", "WEL_Test_Data", "Guest_Fname"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
								WEL.enterCardNumber(excelOperation.getTestData("TC71", "WEL_Test_Data", "Card_Number"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
								WEL.selectExpirationMonthFromDropDown(
										excelOperation.getTestData("TC71", "WEL_Test_Data", "Expiry_Month"));
								driver.switchTo().defaultContent();
								driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
								WEL.selectExpirationYearFromDropDown(
										excelOperation.getTestData("TC71", "WEL_Test_Data", "Expiry_Year"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
								WEL.enterCVV_Number(excelOperation.getTestData("TC71", "WEL_Test_Data", "CVV"));
								driver.switchTo().defaultContent();
								WEL.SaveAndContinueCheckOut();
								try {
									if (WEL.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed())
										WEL.clickOnUseSelectedBillingAddressButtonAddressDoctor();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Address Suggestiond page is not appering on Billing page",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}

							} catch (Exception e) {
								Reporting.updateTestReport("Card Details was not enetred" + e.getClass().toString(),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

							WEL.clickOnPlaceOrderButton();
							String orderID = WEL.fetchOrderId();
							excelOperation.updateTestData("TC71", "WEL_Test_Data", "Order_Id", orderID);
							ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
							String tax = WEL.fetchTaxValue();
							excelOperation.updateTestData("TC71", "WEL_Test_Data", "Tax", tax);
							String orderTotal = WEL.fetchOrderTotal();
							excelOperation.updateTestData("TC71", "WEL_Test_Data", "Order_Total", orderTotal);

						} catch (Exception e) {
							Reporting.updateTestReport("CPA Product was not visible and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("CPA Product was select and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

					}
				} catch (Exception e) {
					Reporting.updateTestReport("Failed to click on CPA Product caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click on Add To Cart caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC44_PlaceOrderFor_UniversityPartner_Product() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC44_PlaceOrderFor_UniversityPartner_Product");
			driver.get(excelOperation.getTestData("University_PartnetProdut_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='form-group']//input[@id='inputPartnerSearch']")));
				WEL.EnterUniversityName(excelOperation.getTestData("TC44", "WEL_Test_Data", "Partner_Name"));

				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//div[@class='partner-detail partner-list-container']//div[@class='col-xs-12 col-sm-6 package-selection-col']//button")));
					WEL.ClickOnAddToCartUniversityProduct();

					try {
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}

						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("(//span[@class='welCheckoutContBtnText'])[2]")));

						WEL.ClickonCheckOutOnCartPage();
						WEL.EnterexistingUserName(excelOperation.getTestData("TC44", "WEL_Test_Data", "Email_Address"));
						WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC44", "WEL_Test_Data", "Password"));
						WEL.ClickonLoginAndContinue();
						try {
							wait.until(ExpectedConditions.elementToBeClickable(
									By.xpath("(//div[@id='newAddressBtnDiv']/button[@id='addNewAddressButton'])")));

							WEL.ClickOnEnterNewAddressButtonOnShippingPage();
							WEL.GuestFirstName(excelOperation.getTestData("TC44", "WEL_Test_Data", "First_Name"));
							WEL.GuestLastName(excelOperation.getTestData("TC44", "WEL_Test_Data", "Last_Name"));
							WEL.selectShipCountry(excelOperation.getTestData("TC44", "WEL_Test_Data", "Sh_Country"));
							WEL.ShipAddressLineOne(
									excelOperation.getTestData("TC44", "WEL_Test_Data", "Sh_Address_line1"));
							WEL.ShipPostCode(excelOperation.getTestData("TC44", "WEL_Test_Data", "Sh_Zip_Code"));
							WEL.ShipTownCity(excelOperation.getTestData("TC44", "WEL_Test_Data", "Sh_City/ Province"));
							WEL.enterState(excelOperation.getTestData("TC44", "WEL_Test_Data", "Sh_State"));
							WEL.ShipPhoneNumber(excelOperation.getTestData("TC44", "WEL_Test_Data", "Sh_Phone_Number"));
							WEL.ShipSaveAndContinueButton();

							try {
								if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
									WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
							} catch (Exception e) {
								Reporting.updateTestReport("Address Suggestiond page is not appering on Shipping page",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}

							driver.switchTo()
									.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
								WEL.enterCardHolderName(
										excelOperation.getTestData("TC44", "WEL_Test_Data", "Guest_Fname"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
								WEL.enterCardNumber(excelOperation.getTestData("TC44", "WEL_Test_Data", "Card_Number"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
								WEL.selectExpirationMonthFromDropDown(
										excelOperation.getTestData("TC44", "WEL_Test_Data", "Expiry_Month"));
								driver.switchTo().defaultContent();
								driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
								WEL.selectExpirationYearFromDropDown(
										excelOperation.getTestData("TC44", "WEL_Test_Data", "Expiry_Year"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
								WEL.enterCVV_Number(excelOperation.getTestData("TC44", "WEL_Test_Data", "CVV"));
								driver.switchTo().defaultContent();
								WEL.SaveAndContinueCheckOut();
								try {
									wait.until(ExpectedConditions
											.elementToBeClickable(By.xpath("(//button[@id='placeOrder'])[1]")));
									WEL.clickOnPlaceOrderButton();
									String orderID = WEL.fetchOrderId();
									excelOperation.updateTestData("TC44", "WEL_Test_Data", "Order_Id", orderID);
									ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
									String tax = WEL.fetchTaxAmount();
									excelOperation.updateTestData("TC44", "WEL_Test_Data", "Tax", tax);
									String orderTotal = WEL.fetchOrderTotal();
									excelOperation.updateTestData("TC44", "WEL_Test_Data", "Order_Total", orderTotal);

								} catch (Exception e) {
									Reporting.updateTestReport(
											"Place Order button was not clicked due to timeout exception"
													+ e.getClass().toString(),
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Card Details was not enetred" + e.getClass().toString(),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Address is not appering on Shipping page",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Failed to Click on Add To cart timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Failed to enter university name caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click on Edit New Address button due to timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	/*
	 * @Date: 01/02/23
	 * 
	 * @Description: Validates that if user comes back and changes the shipping
	 * address, if the tax is getting recalculated in that case or not
	 */
	@Test
	public void TC47_Tax_Recalculation_After_Editing_Shipping_Address() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC47_Tax_Recalculation_After_Editing_Shipping_Address");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}

						WEL.ClickonCheckOutOnCartPage();
						String email = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(email);
						WEL.EnterPassword(excelOperation.getTestData("TC47", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						WEL.GuestFirstName(excelOperation.getTestData("TC47", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC47", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(
								excelOperation.getTestData("TC47", "WEL_Test_Data", "Sh_Country").split(",")[0]);
						Thread.sleep(3000);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							WEL.ShipAddressLineOne(excelOperation
									.getTestData("TC47", "WEL_Test_Data", "Sh_Address_line1").split(",")[0]);
							WEL.ShipPostCode(
									excelOperation.getTestData("TC47", "WEL_Test_Data", "Sh_Zip_Code").split(",")[0]);
							// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='city']")));
							// WEL.ShipTownIndiaCity(
							// excelOperation.getTestData("TC47", "WEL_Test_Data", "Sh_City/
							// Province").split(",")[0]);
							try {
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.xpath("//input[@id='address.region']")));
								WEL.ShipPhoneNumber(
										excelOperation.getTestData("TC47", "WEL_Test_Data", "Sh_Phone_Number"));
								WEL.ClickSaveAndContinueOnShippinInfoPage();
								try {
									if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
										WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
								} catch (Exception e) {
								}
								try {
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
									wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
									WEL.enterCardHolderName(
											excelOperation.getTestData("TC47", "WEL_Test_Data", "Guest_Fname"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
									WEL.enterCardNumber(
											excelOperation.getTestData("TC47", "WEL_Test_Data", "Card_Number"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
									WEL.selectExpirationMonthFromDropDown(
											excelOperation.getTestData("TC47", "WEL_Test_Data", "Expiry_Month"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
									WEL.selectExpirationYearFromDropDown(
											excelOperation.getTestData("TC47", "WEL_Test_Data", "Expiry_Year"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
									WEL.enterCVV_Number(excelOperation.getTestData("TC47", "WEL_Test_Data", "CVV"));
									driver.switchTo().defaultContent();
									WEL.SaveAndContinueCheckOut();
									String tax1 = WEL.fetchTaxInOrderSummaryTab();
									WEL.clickOnShippingDetailsEditIcon();
									WEL.GuestFirstName(
											excelOperation.getTestData("TC47", "WEL_Test_Data", "Guest_Fname"));
									WEL.GuestLastName(
											excelOperation.getTestData("TC47", "WEL_Test_Data", "Guest_Lname"));
									WEL.selectShipCountry(excelOperation
											.getTestData("TC47", "WEL_Test_Data", "Sh_Country").split(",")[1]);
									Thread.sleep(3000);
									try {
										wait.until(ExpectedConditions
												.elementToBeClickable(By.xpath("//input[@id='line1']")));
										WEL.ShipAddressLineOne(
												excelOperation.getTestData("TC47", "WEL_Test_Data", "Sh_Address_line1")
														.split(",")[1]);
										WEL.ShipPostCode(excelOperation
												.getTestData("TC47", "WEL_Test_Data", "Sh_Zip_Code").split(",")[1]);
										// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='city']")));
										// WEL.ShipTownIndiaCity(
										// excelOperation.getTestData("TC47", "WEL_Test_Data", "Sh_City/
										// Province").split(",")[1]);
										try {
											wait.until(ExpectedConditions
													.elementToBeClickable(By.xpath("//input[@id='townCity']")));
											WEL.ShipTownCity(excelOperation
													.getTestData("TC47", "WEL_Test_Data", "Sh_City/ Province")
													.split(",")[1]);
											WEL.ShipPhoneNumber(excelOperation.getTestData("TC47", "WEL_Test_Data",
													"Sh_Phone_Number"));
											WEL.ClickSaveAndContinueOnShippinInfoPage();
											try {
												if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
														.isDisplayed())
													WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
											} catch (Exception e) {
												Reporting.updateTestReport(
														"Address Suggestiond page is not appering on Shipping page",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
											}
											try {
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='cardholder name']")));
												wait.until(
														ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
												WEL.enterCardHolderName(excelOperation.getTestData("TC47",
														"WEL_Test_Data", "Guest_Fname"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='card number']")));
												WEL.enterCardNumber(excelOperation.getTestData("TC47", "WEL_Test_Data",
														"Card_Number"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
												WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC47",
														"WEL_Test_Data", "Expiry_Month"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(
														driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
												WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC47",
														"WEL_Test_Data", "Expiry_Year"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='securityCode']")));
												WEL.enterCVV_Number(
														excelOperation.getTestData("TC47", "WEL_Test_Data", "CVV"));
												driver.switchTo().defaultContent();
												WEL.SaveAndContinueCheckOut();
												String tax2 = WEL.fetchTaxInOrderSummaryTab();
												if (tax1.equalsIgnoreCase(tax2))
													Reporting.updateTestReport(
															"The tax was not recalculated after editing the shipping address and "
																	+ "the tax value was : " + tax1 + " in both cases",
															CaptureScreenshot.getScreenshot(SS_path),
															StatusDetails.FAIL);
												else
													Reporting.updateTestReport(
															"The tax was recalculated from " + tax1 + " to " + tax2
																	+ " after editing the shipping address",
															CaptureScreenshot.getScreenshot(SS_path),
															StatusDetails.PASS);

											} catch (Exception e) {
												Reporting.updateTestReport(
														"The name field in card information step was not clickable and caused timeout exception",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										} catch (Exception e) {
											Reporting.updateTestReport(
													"City field was not visible and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Address line 1 was not clickable and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"The name field in card information step was not clickable and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("State field was not visible and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 01/02/23
	 * 
	 * @Description: Validates that if user comes back and changes the shipping
	 * address, if the tax is getting recalculated in that case or not
	 */
	@Test
	public void TC48_Tax_Recalculation_After_Editing_Billing_Address() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC48_Tax_Recalculation_After_Editing_Billing_Address");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						Thread.sleep(1000);
						WEL.clickOnEBookButtonPDP();
						Thread.sleep(1000);
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}

						WEL.ClickonCheckOutOnCartPage();
						String email = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(email);
						WEL.EnterPassword(excelOperation.getTestData("TC48", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						WEL.GuestFirstName(excelOperation.getTestData("TC48", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC48", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectBillCountry(
								excelOperation.getTestData("TC48", "WEL_Test_Data", "Bill_Country").split(",")[0]);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("street1")));
							WEL.AddressLineOne(excelOperation.getTestData("TC48", "WEL_Test_Data", "Bill_Address_line1")
									.split(",")[0]);
							WEL.EnterZipecode(
									excelOperation.getTestData("TC48", "WEL_Test_Data", "Bill_Zip_Code").split(",")[0]);
							WEL.EnterCity(excelOperation.getTestData("TC48", "WEL_Test_Data", "Bill_City/ Province")
									.split(",")[0]);
							WEL.enterState(
									excelOperation.getTestData("TC48", "WEL_Test_Data", "Bill_State").split(",")[0]);
							WEL.EnterPhoneNumber(
									excelOperation.getTestData("TC48", "WEL_Test_Data", "Bill_Phone_Number"));
							driver.switchTo()
									.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
								WEL.enterCardHolderName(
										excelOperation.getTestData("TC48", "WEL_Test_Data", "Guest_Fname"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
								WEL.enterCardNumber(excelOperation.getTestData("TC48", "WEL_Test_Data", "Card_Number"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
								WEL.selectExpirationMonthFromDropDown(
										excelOperation.getTestData("TC48", "WEL_Test_Data", "Expiry_Month"));
								driver.switchTo().defaultContent();
								driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
								WEL.selectExpirationYearFromDropDown(
										excelOperation.getTestData("TC48", "WEL_Test_Data", "Expiry_Year"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
								WEL.enterCVV_Number(excelOperation.getTestData("TC48", "WEL_Test_Data", "CVV"));
								driver.switchTo().defaultContent();
								WEL.SaveAndContinueCheckOut();
								try {
									if (WEL.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed())
										WEL.clickOnUseSelectedBillingAddressButtonAddressDoctor();
								} catch (Exception e) {
								}
								String tax1 = WEL.fetchTaxInOrderSummaryTab();
								WEL.clickOnPaymentDetailsEditIcon();
								WEL.GuestFirstName(excelOperation.getTestData("TC48", "WEL_Test_Data", "Guest_Fname"));
								WEL.GuestLastName(excelOperation.getTestData("TC48", "WEL_Test_Data", "Guest_Lname"));
								WEL.selectBillCountry(excelOperation
										.getTestData("TC48", "WEL_Test_Data", "Bill_Country").split(",")[1]);
								try {
									wait.until(ExpectedConditions.elementToBeClickable(By.id("street1")));
									WEL.AddressLineOne(excelOperation
											.getTestData("TC48", "WEL_Test_Data", "Bill_Address_line1").split(",")[1]);
									WEL.EnterZipecode(excelOperation
											.getTestData("TC48", "WEL_Test_Data", "Bill_Zip_Code").split(",")[1]);
									// WEL.EnterCity(
									// excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_City/
									// Province").split(",")[0]);
									// WEL.enterState(excelOperation.getTestData("TC03", "WEL_Test_Data",
									// "Bill_State").split(",")[0]);
									WEL.EnterPhoneNumber(
											excelOperation.getTestData("TC48", "WEL_Test_Data", "Bill_Phone_Number"));
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
										WEL.enterCardHolderName(
												excelOperation.getTestData("TC48", "WEL_Test_Data", "Guest_Fname"));
										driver.switchTo().defaultContent();
										driver.switchTo()
												.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
										WEL.enterCardNumber(
												excelOperation.getTestData("TC48", "WEL_Test_Data", "Card_Number"));
										driver.switchTo().defaultContent();
										driver.switchTo()
												.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
										WEL.selectExpirationMonthFromDropDown(
												excelOperation.getTestData("TC48", "WEL_Test_Data", "Expiry_Month"));
										driver.switchTo().defaultContent();
										driver.switchTo()
												.frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
										WEL.selectExpirationYearFromDropDown(
												excelOperation.getTestData("TC48", "WEL_Test_Data", "Expiry_Year"));
										driver.switchTo().defaultContent();
										driver.switchTo().frame(
												driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
										WEL.enterCVV_Number(excelOperation.getTestData("TC48", "WEL_Test_Data", "CVV"));
										driver.switchTo().defaultContent();
										WEL.SaveAndContinueCheckOut();
										try {
											if (WEL.returnUseSelectedBillingAddressButtonAddressDoctorPopUp()
													.isDisplayed())
												WEL.clickOnUseSelectedBillingAddressButtonAddressDoctor();
										} catch (Exception e) {
											Reporting.updateTestReport(
													"Address Suggestiond page is not appering on Shipping page",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
										}
										String tax2 = WEL.fetchTaxInOrderSummaryTab();
										if (tax1.equalsIgnoreCase(tax2))
											Reporting.updateTestReport(
													"The tax was not recalculated after editing the Billing address and "
															+ "the tax value was : " + tax1 + " in both cases",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										else
											Reporting.updateTestReport(
													"The tax was recalculated from " + tax1 + " to " + tax2
															+ " after editing the Billing address",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

									} catch (Exception e) {
										Reporting.updateTestReport(
												"The name field in card information step was not clickable and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}

								} catch (Exception e) {
									Reporting.updateTestReport(
											"Billing address line 1 was not clickable and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"The name field in card information step was not clickable and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"Billing address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 01/02/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Places order with some parts of set as new user
	 */
	@Test
	public void TC49_Place_Order_With_Some_Parts_New_User() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC49_Place_Order_With_Some_Parts_New_User");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						Thread.sleep(1000);
						WEL.clickOnPart1InCIAPDP();
						Thread.sleep(1000);
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}

						WEL.ClickonCheckOutOnCartPage();
						String emailId = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(emailId);
						WEL.EnterPassword(excelOperation.getTestData("TC49", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						WEL.GuestFirstName(excelOperation.getTestData("TC49", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC49", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC49", "WEL_Test_Data", "Sh_Country"));
						Thread.sleep(2000);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));

							WEL.ShipAddressLineOne(
									excelOperation.getTestData("TC49", "WEL_Test_Data", "Sh_Address_line1"));
							try {
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
								WEL.ShipPostCode(excelOperation.getTestData("TC49", "WEL_Test_Data", "Sh_Zip_Code"));
								WEL.ShipPhoneNumber(
										excelOperation.getTestData("TC49", "WEL_Test_Data", "Sh_Phone_Number"));
								WEL.ClickSaveAndContinueOnShippinInfoPage();
								try {
									if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
										WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Address Suggestiond page is not appering on Shipping page",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
								try {
									wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
									WEL.enterCardHolderName(
											excelOperation.getTestData("TC49", "WEL_Test_Data", "Guest_Fname"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
									WEL.enterCardNumber(
											excelOperation.getTestData("TC49", "WEL_Test_Data", "Card_Number"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
									WEL.selectExpirationMonthFromDropDown(
											excelOperation.getTestData("TC49", "WEL_Test_Data", "Expiry_Month"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
									WEL.selectExpirationYearFromDropDown(
											excelOperation.getTestData("TC49", "WEL_Test_Data", "Expiry_Year"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
									WEL.enterCVV_Number(excelOperation.getTestData("TC49", "WEL_Test_Data", "CVV"));
									driver.switchTo().defaultContent();
									try {
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
										WEL.SaveAndContinueCheckOut();
										WEL.clickOnPlaceOrderButton();
										String orderID = WEL.fetchOrderId();
										ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
										excelOperation.updateTestData("TC49", "WEL_Test_Data", "Order_Id", orderID);
										excelOperation.updateTestData("TC49", "WEL_Test_Data", "Email_Address",
												emailId);
										String tax = WEL.fetchTaxValue();
										excelOperation.updateTestData("TC49", "WEL_Test_Data", "Tax", tax);
										String orderTotal = WEL.fetchOrderTotal();
										excelOperation.updateTestData("TC49", "WEL_Test_Data", "Order_Total",
												orderTotal);

									} catch (Exception e) {
										Reporting.updateTestReport(
												"Help button was not visible and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"The name field in card information step was not clickable and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Help button was not visible and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	/*
	 * @Date: 01/02/23
	 * 
	 * @Description: Navigating to and fro between the shipping and billing section
	 * multiple times
	 */
	@Test
	public void TC38_Multiple_To_And_Fro_Navigation_Between_Shipping_And_Billing() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent
					.createTest("TC38_Multiple_To_And_Fro_Navigation_Between_Shipping_And_Billing");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}

						WEL.ClickonCheckOutOnCartPage();
						String email = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(email);
						WEL.EnterPassword(excelOperation.getTestData("TC38", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						WEL.GuestFirstName(excelOperation.getTestData("TC38", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC38", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC38", "WEL_Test_Data", "Sh_Country"));
						Thread.sleep(3000);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							WEL.ShipAddressLineOne(
									excelOperation.getTestData("TC38", "WEL_Test_Data", "Sh_Address_line1"));
							WEL.ShipPostCode(excelOperation.getTestData("TC38", "WEL_Test_Data", "Sh_Zip_Code"));
							// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='city']")));
							// WEL.ShipTownIndiaCity(
							// excelOperation.getTestData("TC47", "WEL_Test_Data", "Sh_City/ Province"));
							try {
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.xpath("//input[@id='address.region']")));
								WEL.ShipPhoneNumber(
										excelOperation.getTestData("TC38", "WEL_Test_Data", "Sh_Phone_Number"));
								WEL.ClickSaveAndContinueOnShippinInfoPage();
								try {
									if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
										WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
								} catch (Exception e) {
								}
								try {
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
									wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
									WEL.enterCardHolderName(
											excelOperation.getTestData("TC38", "WEL_Test_Data", "Guest_Fname"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
									WEL.enterCardNumber(
											excelOperation.getTestData("TC38", "WEL_Test_Data", "Card_Number"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
									WEL.selectExpirationMonthFromDropDown(
											excelOperation.getTestData("TC38", "WEL_Test_Data", "Expiry_Month"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
									WEL.selectExpirationYearFromDropDown(
											excelOperation.getTestData("TC38", "WEL_Test_Data", "Expiry_Year"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
									WEL.enterCVV_Number(excelOperation.getTestData("TC38", "WEL_Test_Data", "CVV"));
									driver.switchTo().defaultContent();
									WEL.SaveAndContinueCheckOut();
									wait.until(ExpectedConditions.visibilityOfElementLocated(
											By.xpath("//div[@class='row taxCalculationDetails orderReviewRightPanel']/"
													+ "div[@class='col-6 noPadding price orderDetailCommonVal']")));
									wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
											"//div[@class='step-head checkoutCompletedStep']//div[@class='edit']/a")));
									WEL.clickOnShippingDetailsEditIcon();
									// WEL.ClickOnEditIcononShippingPage();
									WEL.GuestFirstName(
											excelOperation.getTestData("TC38", "WEL_Test_Data", "Guest_Fname"));
									WEL.GuestLastName(
											excelOperation.getTestData("TC38", "WEL_Test_Data", "Guest_Lname"));
									WEL.selectShipCountry(
											excelOperation.getTestData("TC38", "WEL_Test_Data", "Sh_Country"));
									Thread.sleep(3000);
									try {
										wait.until(ExpectedConditions
												.elementToBeClickable(By.xpath("//input[@id='line1']")));
										WEL.ShipAddressLineOne(excelOperation.getTestData("TC38", "WEL_Test_Data",
												"Sh_Address_line1"));
										WEL.ShipPostCode(
												excelOperation.getTestData("TC38", "WEL_Test_Data", "Sh_Zip_Code"));
										try {
											wait.until(ExpectedConditions
													.elementToBeClickable(By.xpath("//input[@id='townCity']")));
											WEL.ShipPhoneNumber(excelOperation.getTestData("TC38", "WEL_Test_Data",
													"Sh_Phone_Number"));
											WEL.ClickSaveAndContinueOnShippinInfoPage();
											try {
												if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
														.isDisplayed())
													WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
											} catch (Exception e) {
												Reporting.updateTestReport(
														"Failed to click Address on Address SUggestion due to timeout exception",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
											}
											try {
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='cardholder name']")));
												wait.until(
														ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
												WEL.enterCardHolderName(excelOperation.getTestData("TC38",
														"WEL_Test_Data", "Guest_Fname"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='card number']")));
												WEL.enterCardNumber(excelOperation.getTestData("TC38", "WEL_Test_Data",
														"Card_Number"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
												WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC38",
														"WEL_Test_Data", "Expiry_Month"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(
														driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
												WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC38",
														"WEL_Test_Data", "Expiry_Year"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='securityCode']")));
												WEL.enterCVV_Number(
														excelOperation.getTestData("TC38", "WEL_Test_Data", "CVV"));
												driver.switchTo().defaultContent();
												WEL.SaveAndContinueCheckOut();
												wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
														"//div[@class='row taxCalculationDetails orderReviewRightPanel']/"
																+ "div[@class='col-6 noPadding price orderDetailCommonVal']")));
												wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
														"//div[@class='step-head checkoutCompletedStep']//div[@class='edit']/a")));
												// WEL.ClickOnEditIcononShippingPage();
												WEL.clickOnShippingDetailsEditIcon();
												WEL.GuestFirstName(excelOperation.getTestData("TC38", "WEL_Test_Data",
														"Guest_Fname"));
												WEL.GuestLastName(excelOperation.getTestData("TC38", "WEL_Test_Data",
														"Guest_Lname"));
												WEL.selectShipCountry(excelOperation.getTestData("TC38",
														"WEL_Test_Data", "Sh_Country"));
												Thread.sleep(3000);
												try {
													wait.until(ExpectedConditions
															.elementToBeClickable(By.xpath("//input[@id='line1']")));
													WEL.ShipAddressLineOne(excelOperation.getTestData("TC38",
															"WEL_Test_Data", "Sh_Address_line1"));
													WEL.ShipPostCode(excelOperation.getTestData("TC38", "WEL_Test_Data",
															"Sh_Zip_Code"));
													try {
														wait.until(ExpectedConditions.elementToBeClickable(
																By.xpath("//input[@id='townCity']")));
														WEL.ShipPhoneNumber(excelOperation.getTestData("TC38",
																"WEL_Test_Data", "Sh_Phone_Number"));
														WEL.ClickSaveAndContinueOnShippinInfoPage();
														try {
															if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
																	.isDisplayed())
																WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
														} catch (Exception e) {
															Reporting.updateTestReport(
																	"Address Suggestiond page is not appering on Shipping page",
																	CaptureScreenshot.getScreenshot(SS_path),
																	StatusDetails.INFO);
														}
														try {
															driver.switchTo().frame(driver.findElement(
																	By.xpath(".//iframe[@title='cardholder name']")));
															wait.until(ExpectedConditions
																	.elementToBeClickable(By.id("nameOnCard")));
															WEL.enterCardHolderName(excelOperation.getTestData("TC38",
																	"WEL_Test_Data", "Guest_Fname"));
															driver.switchTo().defaultContent();
															driver.switchTo().frame(driver.findElement(
																	By.xpath(".//iframe[@title='card number']")));
															WEL.enterCardNumber(excelOperation.getTestData("TC38",
																	"WEL_Test_Data", "Card_Number"));
															driver.switchTo().defaultContent();
															driver.switchTo().frame(driver.findElement(
																	By.xpath(".//iframe[@title='expiryMonth']")));
															WEL.selectExpirationMonthFromDropDown(
																	excelOperation.getTestData("TC38", "WEL_Test_Data",
																			"Expiry_Month"));
															driver.switchTo().defaultContent();
															driver.switchTo().frame(driver.findElement(
																	By.xpath(".//iframe[@title='expiryYear']")));
															WEL.selectExpirationYearFromDropDown(
																	excelOperation.getTestData("TC38", "WEL_Test_Data",
																			"Expiry_Year"));
															driver.switchTo().defaultContent();
															driver.switchTo().frame(driver.findElement(
																	By.xpath(".//iframe[@title='securityCode']")));
															WEL.enterCVV_Number(excelOperation.getTestData("TC38",
																	"WEL_Test_Data", "CVV"));
															driver.switchTo().defaultContent();
															WEL.SaveAndContinueCheckOut();
															Reporting.updateTestReport(
																	"Multiple to and fro naviagtion between billing and shipping"
																			+ " was successful",
																	CaptureScreenshot.getScreenshot(SS_path),
																	StatusDetails.PASS);
															driver.get(excelOperation.getTestData("WEL_Logout_URL",
																	"Generic_Dataset", "Data"));
														} catch (Exception e) {
															Reporting.updateTestReport(
																	"The name field in card information step was not clickable and caused timeout exception",
																	CaptureScreenshot.getScreenshot(SS_path),
																	StatusDetails.FAIL);
														}
													} catch (Exception e) {
														Reporting.updateTestReport(
																"City field was not visible and caused timeout exception",
																CaptureScreenshot.getScreenshot(SS_path),
																StatusDetails.FAIL);
													}
												} catch (Exception e) {
													Reporting.updateTestReport(
															"Address line 1 was not clickable and caused timeout exception",
															CaptureScreenshot.getScreenshot(SS_path),
															StatusDetails.FAIL);
												}
											} catch (Exception e) {
												Reporting.updateTestReport(
														"The name field in card information step was not clickable and caused timeout exception",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										} catch (Exception e) {
											Reporting.updateTestReport(
													"City field was not visible and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Address line 1 was not clickable and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"The name field in card information step was not clickable and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("State field was not visible and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC37_PlaceOrder_withPaypalCredit_OptionForNewUser() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			Reporting.test = Reporting.extent.createTest("TC37_PlaceOrder_withPaypalCredit_OptionForNewUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_1']/a[2]")));
				WEL.ClickonCMAProduct();
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("(//button[contains(text(),'EXPLORE COURSES')])[1]")));
					WEL.ClickonExploreCourseCMAProduct();
					ScrollingWebPage.PageDown(driver, SS_path);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"(//div[@class='container-fluid course-pkg-background']//div[@class='card-footer bg-transparent course-pkg-cards-footer'])[1]//button[contains(text(),'VIEW COURSE OPTIONS')]")));
						WEL.ClickonViewCourseForCMAProduct();
						try {
							wait.until(ExpectedConditions.elementToBeClickable(
									By.xpath("//div[@class='btn-group btn-group-toggle']/label[1]")));
							WEL.ClickonCMAPrinteBook();
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
										"//div[@class='container-fluid banner-container product-detail-banner mt-4']//div[7]//button")));
								WEL.ClickonCMAAddToCart();
								try {
									wait.until(ExpectedConditions.elementToBeClickable(
											By.xpath("(//span[@class='welCheckoutContBtnText'])[2]")));

									WEL.ClickonCheckOutOnCartPage();
									String Guestemail = WEL.EnterGuestUser();
									WEL.ClickingOnCreateAccoutButton();
									WEL.GuestConfirmEmailId(Guestemail);
									WEL.EnterPassword(excelOperation.getTestData("TC37", "WEL_Test_Data", "Password"));
									WEL.ClickonAgreementCheckBox();
									WEL.ClickingOnSaveAndContinue();
									WEL.GuestFirstName(
											excelOperation.getTestData("TC37", "WEL_Test_Data", "Guest_Fname"));
									WEL.GuestLastName(
											excelOperation.getTestData("TC37", "WEL_Test_Data", "Guest_Lname"));
									WEL.selectShipCountry(
											excelOperation.getTestData("TC37", "WEL_Test_Data", "Sh_Country"));
									WEL.ShipAddressLineOne(
											excelOperation.getTestData("TC37", "WEL_Test_Data", "Sh_Address_line1"));
									WEL.ShipPostCode(
											excelOperation.getTestData("TC37", "WEL_Test_Data", "Sh_Zip_Code"));
									WEL.ShipTownCity(
											excelOperation.getTestData("TC37", "WEL_Test_Data", "Sh_City/ Province"));
									WEL.enterState(excelOperation.getTestData("TC37", "WEL_Test_Data", "Sh_State"));
									WEL.ShipPhoneNumber(
											excelOperation.getTestData("TC37", "WEL_Test_Data", "Sh_Phone_Number"));
									WEL.ShipSaveAndContinueButton();

									try {
										if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
												.isDisplayed())
											WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Address Suggestiond page is not appering on Shipping page",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
									}

									try {
										wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
												"//div[@id='billingMultiPaymentOptionValues']/ul/li[4]/a/span")));
										WEL.SelectPayPalCreditOption();
										WEL.ClickOnProceedToPayPalCredit();

										ScrollingWebPage.PageDown(driver, SS_path);
										try {
											wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
													"((//div[@class='FundingInstrument_container_16IeJ'])[2]//span)[1]")));
											WEL.ClickOnPaypalCreditRadioButton();
											WEL.ClickOnPaypalReviewOrder();
											ScrollingWebPage.PageDown(driver, SS_path);
											WEL.clickOnPlaceOrderButton();
											String orderID = WEL.fetchOrderId();
											excelOperation.updateTestData("TC37", "WEL_Test_Data", "Order_Id", orderID);
											ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
											String tax = WEL.fetchTaxAmount();
											excelOperation.updateTestData("TC37", "WEL_Test_Data", "Tax", tax);
											String orderTotal = WEL.fetchOrderTotal();
											excelOperation.updateTestData("TC37", "WEL_Test_Data", "Order_Total",
													orderTotal);

										} catch (Exception e) {
											Reporting.updateTestReport(
													"Failed to click on CMA Product due to timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Failed to click on CMAExplore product due to timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Failed to click on CMAViewCoure product due to timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Failed to click on CMAPrintBook product due to timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"Failed to click on CMAAddTOCart product due to timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

						}
					} catch (Exception e) {
						Reporting.updateTestReport("Failed to click on CheckoutOnCartPage due to timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

					}
				} catch (Exception e) {
					Reporting.updateTestReport("Failed to select PaypalCredit Option due to timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

				}
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click on PayPalCredit Radio button due to timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC73_ValidateTaxin_IndiaForPhysicalOrder() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC73_ValidateTaxin_IndiaForPhysicalOrder");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}

						WEL.ClickonCheckOutOnCartPage();
						WEL.EnterexistingUserName(excelOperation.getTestData("TC73", "WEL_Test_Data", "Email_Address"));
						WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC73", "WEL_Test_Data", "Password"));
						WEL.ClickonLoginAndContinue();
						WEL.ClickonCheckOutOnCartPage();
						WEL.ClickonCheckOutOnCartPage();
						WEL.ClickOnEnterNewAddressButtonOnShippingPage();
						WEL.GuestFirstName(excelOperation.getTestData("TC73", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC73", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC73", "WEL_Test_Data", "Sh_Country"));
						Thread.sleep(3000);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							try {
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
								WEL.ShipAddressLineOne(
										excelOperation.getTestData("TC73", "WEL_Test_Data", "Sh_Address_line1"));
								WEL.ShipPostCode(excelOperation.getTestData("TC73", "WEL_Test_Data", "Sh_Zip_Code"));
								// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='city']")));
								WEL.ShipTownIndiaCity(
										excelOperation.getTestData("TC73", "WEL_Test_Data", "Sh_City/ Province"));
								try {
									wait1.until(ExpectedConditions
											.elementToBeClickable(By.xpath("//input[@id='address.region']")));
									WEL.enterState(excelOperation.getTestData("TC73", "WEL_Test_Data", "Sh_State"));
								} catch (Exception e) {
									try {
										wait1.until(ExpectedConditions
												.elementToBeClickable(By.xpath("//select[@id='address.region']")));
										WEL.selectStateFromDropsown(
												excelOperation.getTestData("TC73", "WEL_Test_Data", "Sh_State"));
									} catch (Exception e1) {

										Reporting.updateTestReport(
												"State field was not clickable and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

									}

								}
								WEL.ShipPhoneNumber(
										excelOperation.getTestData("TC73", "WEL_Test_Data", "Sh_Phone_Number"));
								WEL.ClickingOnSaveAndContinue();
								try {
									if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
										WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Failed to click Address on Address SUggestion due to timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
								try {
									wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
									WEL.enterCardHolderName(
											excelOperation.getTestData("TC73", "WEL_Test_Data", "Guest_Fname"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
									WEL.enterCardNumber(
											excelOperation.getTestData("TC73", "WEL_Test_Data", "Card_Number"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
									WEL.selectExpirationMonthFromDropDown(
											excelOperation.getTestData("TC73", "WEL_Test_Data", "Expiry_Month"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
									WEL.selectExpirationYearFromDropDown(
											excelOperation.getTestData("TC73", "WEL_Test_Data", "Expiry_Year"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
									WEL.enterCVV_Number(excelOperation.getTestData("TC73", "WEL_Test_Data", "CVV"));
									driver.switchTo().defaultContent();
									try {
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
										WEL.SaveAndContinueCheckOut();
										WEL.clickOnPlaceOrderButton();
										String orderID = WEL.fetchOrderId();
										ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
										excelOperation.updateTestData("TC73", "WEL_Test_Data", "Order_Id", orderID);
										String tax = WEL.fetchTaxValue();
										excelOperation.updateTestData("TC73", "WEL_Test_Data", "Tax", tax);
										String orderTotal = WEL.fetchOrderTotal();
										excelOperation.updateTestData("TC73", "WEL_Test_Data", "Order_Total",
												orderTotal);
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Help button was not visible and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"The name field in card information step was not clickable and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}

							} catch (Exception e) {
								Reporting.updateTestReport("Help button was not visible and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}

	}

	@Test
	public void TC43_PlaceOrderwith_PartnerProduct_Having100Percent_Discount() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC43_PlaceOrderwith_PartnerProduct_Having100%_Discount");
			driver.get(excelOperation.getTestData("100%Discount_Product", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='form-group']//input[@id='inputPartnerSearch']")));
				WEL.EnterUniversityName(excelOperation.getTestData("TC43", "WEL_Test_Data", "Partner_Name"));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//div[@class='container product-categories-container']//div[2]//dd/a[contains(text(),'CPA')]")));
					WEL.ClickOnDeanDortonCPAProduct();
					ScrollingWebPage.PageDown(driver, SS_path);
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("(//div[@class='col']//button)[2]")));
						WEL.ClickOnAddToCartForDeanDortonProduct();
						try {
							try {
								wait1.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
								driver.navigate().refresh();
							} catch (Exception e) {

							}

							wait.until(ExpectedConditions
									.elementToBeClickable(By.xpath("(//span[@class='welCheckoutContBtnText'])[2]")));

							WEL.ClickonCheckOutOnCartPage();
							WEL.EnterexistingUserName(
									excelOperation.getTestData("TC44", "WEL_Test_Data", "Email_Address"));
							WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC44", "WEL_Test_Data", "Password"));
							WEL.ClickonLoginAndContinue();
							WEL.ClickOnEnterNewAddressButtonOnShippingPage();
							WEL.GuestFirstName(excelOperation.getTestData("TC43", "WEL_Test_Data", "First_Name"));
							WEL.GuestLastName(excelOperation.getTestData("TC43", "WEL_Test_Data", "Last_Name"));
							WEL.selectShipCountry(excelOperation.getTestData("TC43", "WEL_Test_Data", "Sh_Country"));
							WEL.ShipAddressLineOne(
									excelOperation.getTestData("TC43", "WEL_Test_Data", "Sh_Address_line1"));
							WEL.ShipPostCode(excelOperation.getTestData("TC43", "WEL_Test_Data", "Sh_Zip_Code"));
							WEL.ShipTownCity(excelOperation.getTestData("TC43", "WEL_Test_Data", "Sh_City/ Province"));
							WEL.enterState(excelOperation.getTestData("TC43", "WEL_Test_Data", "Sh_State"));
							WEL.ShipPhoneNumber(excelOperation.getTestData("TC43", "WEL_Test_Data", "Sh_Phone_Number"));
							WEL.ShipSaveAndContinueButton();

							try {
								if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
									WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
							} catch (Exception e) {
								Reporting.updateTestReport("Address Suggestiond page is not appering on Shipping page",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}

							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("//button[@id='paymentBilling']")));
								WEL.SaveAndContinueCheckOut();
								WEL.clickOnPlaceOrderButton();
								String orderID = WEL.fetchOrderId();
								excelOperation.updateTestData("TC43", "WEL_Test_Data", "Order_Id", orderID);
								ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
								String orderTotal = WEL.fetchOrderTotal();
								excelOperation.updateTestData("TC43", "WEL_Test_Data", "Order_Total", orderTotal);
								ScrollingWebPage.PageScrollUp(driver, 0, -800, SS_path);

							} catch (Exception e) {
								Reporting.updateTestReport(
										"Failed to Enter the University Name caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"Failed to click on DeanDorton CPA Product caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Failed to click on Add To cartProduct caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Failed to click on CheckOutOn cart Page caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click on SaveAndContinue caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			driver.get(excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Date: 02/02/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Validates Order Confirmation mail for new user
	 */
	@Test
	public void TC62_Email_Verification_For__New_User() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC62_Email_Verification_For__New_User");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}

						WEL.ClickonCheckOutOnCartPage();
						String emailId = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(emailId);
						WEL.EnterPassword(excelOperation.getTestData("TC62", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						WEL.GuestFirstName(excelOperation.getTestData("TC62", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC62", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC62", "WEL_Test_Data", "Sh_Country"));
						Thread.sleep(2000);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));

							WEL.ShipAddressLineOne(
									excelOperation.getTestData("TC62", "WEL_Test_Data", "Sh_Address_line1"));
							try {
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
								WEL.ShipPostCode(excelOperation.getTestData("TC62", "WEL_Test_Data", "Sh_Zip_Code"));
								WEL.ShipPhoneNumber(
										excelOperation.getTestData("TC62", "WEL_Test_Data", "Sh_Phone_Number"));
								WEL.ClickSaveAndContinueOnShippinInfoPage();
								try {
									if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
										WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Failed to click Address on Address SUggestion due to timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
								try {
									wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
									WEL.enterCardHolderName(
											excelOperation.getTestData("TC62", "WEL_Test_Data", "Guest_Fname"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
									WEL.enterCardNumber(
											excelOperation.getTestData("TC62", "WEL_Test_Data", "Card_Number"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
									WEL.selectExpirationMonthFromDropDown(
											excelOperation.getTestData("TC62", "WEL_Test_Data", "Expiry_Month"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
									WEL.selectExpirationYearFromDropDown(
											excelOperation.getTestData("TC62", "WEL_Test_Data", "Expiry_Year"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
									WEL.enterCVV_Number(excelOperation.getTestData("TC62", "WEL_Test_Data", "CVV"));
									driver.switchTo().defaultContent();
									try {
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
										WEL.SaveAndContinueCheckOut();
										WEL.clickOnPlaceOrderButton();
										String orderID = WEL.fetchOrderId();
										ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
										excelOperation.updateTestData("TC62", "WEL_Test_Data", "Order_Id", orderID);
										excelOperation.updateTestData("TC62", "WEL_Test_Data", "Email_Address",
												emailId);
										String tax = WEL.fetchTaxValue();
										String shippingCharge = WEL.fetchShippingChargeInOrderConfirmation();
										excelOperation.updateTestData("TC62", "WEL_Test_Data", "Tax", tax);
										String orderTotal = WEL.fetchOrderTotal();
										excelOperation.updateTestData("TC62", "WEL_Test_Data", "Order_Total",
												orderTotal);
										WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL",
												"Generic_Dataset", "Data"));
										driver.get(
												excelOperation.getTestData("Yopmail_URL", "Generic_Dataset", "Data"));
										WEL.enterEmailIdInYopmail(emailId);
										WEL.clickOnCheckInboxButton();
										if (OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver, SS_path,
												EmailConfirmationText)) {
											ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
											Reporting.updateTestReport("Order Confirmation mail was received",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
											OrderConfirmationMail.validateOrderConfirmationMailContent("WEL", driver,
													SS_path, tax, shippingCharge, orderTotal);
										} else {
											Reporting.updateTestReport("Order Confirmation mail was not received",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}

									} catch (Exception e) {
										Reporting.updateTestReport(
												"Help button was not visible and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"The name field in card information step was not clickable and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Help button was not visible and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	/*
	 * @Date: 02/02/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Validates Order Confirmation mail content
	 */
	@Test
	public void TC79_Email_Format_Validation() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC79_Email_Format_Validation");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}

						WEL.ClickonCheckOutOnCartPage();
						String emailId = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(emailId);
						WEL.EnterPassword(excelOperation.getTestData("TC79", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						WEL.GuestFirstName(excelOperation.getTestData("TC79", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC79", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC79", "WEL_Test_Data", "Sh_Country"));
						Thread.sleep(2000);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							WEL.ShipAddressLineOne(
									excelOperation.getTestData("TC79", "WEL_Test_Data", "Sh_Address_line1"));
							WEL.ShipPostCode(excelOperation.getTestData("TC79", "WEL_Test_Data", "Sh_Zip_Code"));
							// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='city']")));
							// WEL.ShipTownIndiaCity(
							// excelOperation.getTestData("TC47", "WEL_Test_Data", "Sh_City/ Province");
							try {
								wait.until(
										ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='townCity']")));
								WEL.ShipTownCity(
										excelOperation.getTestData("TC79", "WEL_Test_Data", "Sh_City/ Province"));
								WEL.ShipPhoneNumber(
										excelOperation.getTestData("TC79", "WEL_Test_Data", "Sh_Phone_Number"));
								WEL.ClickSaveAndContinueOnShippinInfoPage();
								try {
									if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
										WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Address Suggestiond page is not appering on Shipping page",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
								try {
									wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
									WEL.enterCardHolderName(
											excelOperation.getTestData("TC79", "WEL_Test_Data", "Guest_Fname"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
									WEL.enterCardNumber(
											excelOperation.getTestData("TC79", "WEL_Test_Data", "Card_Number"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
									WEL.selectExpirationMonthFromDropDown(
											excelOperation.getTestData("TC79", "WEL_Test_Data", "Expiry_Month"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
									WEL.selectExpirationYearFromDropDown(
											excelOperation.getTestData("TC79", "WEL_Test_Data", "Expiry_Year"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
									WEL.enterCVV_Number(excelOperation.getTestData("TC79", "WEL_Test_Data", "CVV"));
									driver.switchTo().defaultContent();
									try {
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
										WEL.SaveAndContinueCheckOut();
										WEL.clickOnPlaceOrderButton();
										String orderID = WEL.fetchOrderId();
										ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
										excelOperation.updateTestData("TC79", "WEL_Test_Data", "Order_Id", orderID);
										excelOperation.updateTestData("TC79", "WEL_Test_Data", "Email_Address",
												emailId);
										String tax = WEL.fetchTaxValue();
										String shippingCharge = WEL.fetchShippingChargeInOrderConfirmation();
										excelOperation.updateTestData("TC79", "WEL_Test_Data", "Tax", tax);
										String orderTotal = WEL.fetchOrderTotal();
										excelOperation.updateTestData("TC79", "WEL_Test_Data", "Order_Total",
												orderTotal);
										WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL",
												"Generic_Dataset", "Data"));
										driver.get(
												excelOperation.getTestData("Yopmail_URL", "Generic_Dataset", "Data"));
										WEL.enterEmailIdInYopmail(emailId);
										WEL.clickOnCheckInboxButton();
										if (OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver, SS_path,
												EmailConfirmationText)) {
											ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
											Reporting.updateTestReport("Order Confirmation mail was received",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
											OrderConfirmationMail.validateOrderConfirmationMailContent("WEL", driver,
													SS_path, tax, shippingCharge, orderTotal);
											driver.switchTo().frame("ifmail");
											WEL.checkMailHeaderElements();
											WEL.validateOrderIdInMail(orderID);
											WEL.validateShippingAddressLine1InMail(excelOperation.getTestData("TC79",
													"WEL_Test_Data", "Sh_Address_line1"));
											WEL.validateShippingCityZipCodeInMail(
													excelOperation.getTestData("TC79", "WEL_Test_Data",
															"Sh_City/ Province"),
													excelOperation.getTestData("TC79", "WEL_Test_Data", "Sh_Zip_Code"));
											WEL.validateCardNumberInMail(
													excelOperation.getTestData("TC79", "WEL_Test_Data", "Card_Number"));
											driver.switchTo().defaultContent();

										} else {
											Reporting.updateTestReport("Order Confirmation mail was not received",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}

									} catch (Exception e) {
										Reporting.updateTestReport(
												"Help button was not visible and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"The name field in card information step was not clickable and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Help button was not visible and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
	}

	/*
	 * @Date: 2/2/23
	 * 
	 * @Author: Anindita
	 * 
	 * @Description: Places an order with a university partner product with price
	 */
	@Test
	public void TC42_Place_order_with_partner_product_having_price() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			Reporting.test = Reporting.extent.createTest("TC42_Place_order_with_partner_product_having_price");
			driver.get(excelOperation.getTestData("TC42", "WEL_Test_Data", "URL"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.clickOnAddToCartButtonOnPDP();
			try {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			WEL.ClickonCheckOutOnCartPage();
			String email = WEL.EnterGuestUser();
			WEL.ClickingOnCreateAccoutButton();
			WEL.GuestConfirmEmailId(email);
			WEL.EnterPassword(excelOperation.getTestData("TC42", "WEL_Test_Data", "Password"));
			WEL.ClickonAgreementCheckBox();
			WEL.ClickingOnSaveAndContinue();
			WEL.GuestFirstName(excelOperation.getTestData("TC42", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC42", "WEL_Test_Data", "Guest_Lname"));
			WEL.selectBillCountry(excelOperation.getTestData("TC42", "WEL_Test_Data", "Bill_Country"));
			Thread.sleep(3000);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("street1")));
				WEL.AddressLineOne(excelOperation.getTestData("TC42", "WEL_Test_Data", "Bill_Address_line1"));
				WEL.EnterZipecode(excelOperation.getTestData("TC42", "WEL_Test_Data", "Bill_Zip_Code"));
				WEL.EnterPhoneNumber(excelOperation.getTestData("TC42", "WEL_Test_Data", "Bill_Phone_Number"));
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					WEL.enterCardHolderName(excelOperation.getTestData("TC42", "WEL_Test_Data", "Guest_Fname"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
					WEL.enterCardNumber(excelOperation.getTestData("TC42", "WEL_Test_Data", "Card_Number"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
					WEL.selectExpirationMonthFromDropDown(
							excelOperation.getTestData("TC42", "WEL_Test_Data", "Expiry_Month"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
					WEL.selectExpirationYearFromDropDown(
							excelOperation.getTestData("TC42", "WEL_Test_Data", "Expiry_Year"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
					WEL.enterCVV_Number(excelOperation.getTestData("TC42", "WEL_Test_Data", "CVV"));
					driver.switchTo().defaultContent();
					WEL.SaveAndContinueCheckOut();
					try {
						if (WEL.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed())
							WEL.clickOnUseSelectedBillingAddressButtonAddressDoctor();
					} catch (Exception e) {
						Reporting.updateTestReport("Address Suggestiond page is not appering on Shipping page",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					}
					try {
						wait.until(
								ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
						WEL.clickOnPlaceOrderButton();
						String orderID = WEL.fetchOrderId();
						ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
						excelOperation.updateTestData("TC42", "WEL_Test_Data", "Order_Id", orderID);
						excelOperation.updateTestData("TC42", "WEL_Test_Data", "Email_Address", email);
						String tax = WEL.fetchTaxValue();
						excelOperation.updateTestData("TC42", "WEL_Test_Data", "Tax", tax);
						String orderTotal = WEL.fetchOrderTotal();
						excelOperation.updateTestData("TC42", "WEL_Test_Data", "Order_Total", orderTotal);

						excelOperation.updateTestData("TC61", "WEL_Test_Data", "Email_Address", email);

					} catch (Exception e) {
						Reporting.updateTestReport("Help button was not visible and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}

				} catch (Exception e) {
					Reporting.updateTestReport(
							"The name field in card information step was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Billing address line 1 was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 02/02/23
	 * 
	 * @Description: Validates that if user comes back and changes the shipping
	 * method, if the tax is getting recalculated in that case or not
	 */
	@Test
	public void TC46_Tax_Recalculation_After_Editing_Shipping_Method() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent.createTest("TC46_Tax_Recalculation_After_Editing_Shipping_Method");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						try {
							wait1.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
							driver.navigate().refresh();
						} catch (Exception e) {

						}

						WEL.ClickonCheckOutOnCartPage();
						String email = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(email);
						WEL.EnterPassword(excelOperation.getTestData("TC46", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						WEL.GuestFirstName(excelOperation.getTestData("TC46", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC46", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC46", "WEL_Test_Data", "Sh_Country"));
						Thread.sleep(3000);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							WEL.ShipAddressLineOne(
									excelOperation.getTestData("TC46", "WEL_Test_Data", "Sh_Address_line1"));
							WEL.ShipPostCode(excelOperation.getTestData("TC46", "WEL_Test_Data", "Sh_Zip_Code"));
							// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='city']")));
							// WEL.ShipTownIndiaCity(
							// excelOperation.getTestData("TC47", "WEL_Test_Data", "Sh_City/ Province");
							try {
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.xpath("//input[@id='address.region']")));
								WEL.ShipPhoneNumber(
										excelOperation.getTestData("TC46", "WEL_Test_Data", "Sh_Phone_Number"));

								WEL.ClickSaveAndContinueOnShippinInfoPage();
								try {
									if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
										WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Address Suggestiond page is not appering on Shipping page",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								try {
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
									wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
									WEL.enterCardHolderName(
											excelOperation.getTestData("TC46", "WEL_Test_Data", "Guest_Fname"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
									WEL.enterCardNumber(
											excelOperation.getTestData("TC46", "WEL_Test_Data", "Card_Number"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
									WEL.selectExpirationMonthFromDropDown(
											excelOperation.getTestData("TC46", "WEL_Test_Data", "Expiry_Month"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
									WEL.selectExpirationYearFromDropDown(
											excelOperation.getTestData("TC46", "WEL_Test_Data", "Expiry_Year"));
									driver.switchTo().defaultContent();
									driver.switchTo()
											.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
									WEL.enterCVV_Number(excelOperation.getTestData("TC46", "WEL_Test_Data", "CVV"));
									driver.switchTo().defaultContent();
									WEL.SaveAndContinueCheckOut();
									String tax1 = WEL.fetchTaxInOrderSummaryTab();
									WEL.clickOnShippingDetailsEditIcon();
									WEL.GuestFirstName(
											excelOperation.getTestData("TC46", "WEL_Test_Data", "Guest_Fname"));
									WEL.GuestLastName(
											excelOperation.getTestData("TC46", "WEL_Test_Data", "Guest_Lname"));
									WEL.selectShipCountry(
											excelOperation.getTestData("TC46", "WEL_Test_Data", "Sh_Country"));
									Thread.sleep(3000);
									try {
										wait.until(ExpectedConditions
												.elementToBeClickable(By.xpath("//input[@id='line1']")));
										WEL.ShipAddressLineOne(excelOperation.getTestData("TC46", "WEL_Test_Data",
												"Sh_Address_line1"));
										WEL.ShipPostCode(
												excelOperation.getTestData("TC46", "WEL_Test_Data", "Sh_Zip_Code"));
										try {
											wait.until(ExpectedConditions
													.elementToBeClickable(By.xpath("//input[@id='townCity']")));
											WEL.ShipPhoneNumber(excelOperation.getTestData("TC46", "WEL_Test_Data",
													"Sh_Phone_Number"));
											WEL.selectNextDayShipping();
											WEL.ClickSaveAndContinueOnShippinInfoPage();
											try {
												if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
														.isDisplayed())
													WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
											} catch (Exception e) {
											}
											try {
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='cardholder name']")));
												wait.until(
														ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
												WEL.enterCardHolderName(excelOperation.getTestData("TC46",
														"WEL_Test_Data", "Guest_Fname"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='card number']")));
												WEL.enterCardNumber(excelOperation.getTestData("TC46", "WEL_Test_Data",
														"Card_Number"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
												WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC46",
														"WEL_Test_Data", "Expiry_Month"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(
														driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
												WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC46",
														"WEL_Test_Data", "Expiry_Year"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='securityCode']")));
												WEL.enterCVV_Number(
														excelOperation.getTestData("TC46", "WEL_Test_Data", "CVV"));
												driver.switchTo().defaultContent();
												WEL.SaveAndContinueCheckOut();
												String tax2 = WEL.fetchTaxInOrderSummaryTab();
												if (tax1.equalsIgnoreCase(tax2))
													Reporting.updateTestReport(
															"The tax was not recalculated after editing the shipping address and "
																	+ "the tax value was : " + tax1 + " in both cases",
															CaptureScreenshot.getScreenshot(SS_path),
															StatusDetails.INFO);
												else
													Reporting.updateTestReport(
															"The tax was recalculated from " + tax1 + " to " + tax2
																	+ " after editing the shipping address",
															CaptureScreenshot.getScreenshot(SS_path),
															StatusDetails.PASS);

											} catch (Exception e) {
												Reporting.updateTestReport(
														"The name field in card information step was not clickable and caused timeout exception",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										} catch (Exception e) {
											Reporting.updateTestReport(
													"City field was not visible and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Address line 1 was not clickable and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"The name field in card information step was not clickable and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("State field was not visible and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC17_PlaceOrderof_CPACMA_PhysitalProducts_And_CFAPhysicalReviewCourse_ProductForNewUser()
			throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest(
					"TC17_PlaceOrderof_CPACMA_PhysitalProducts_And_CFAPhysicalReviewCourse_ProductForNewUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			String urls[] = excelOperation.getTestData("TC17", "WEL_Test_Data", "URL").split(",");

			driver.get(urls[0]);
			try {
				wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@id='Ultimate_CPA Review Course 2022 (Mentorship & Tutoring Bundle)']//form//button")));
				ScrollingWebPage.PageDown(driver, SS_path);
				WEL.ClickOnCPAAddProduct();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@id='Ultimate_CPA Review Course 2022 (Mentorship & Tutoring Bundle)']//form//button")));
				WEL.ClickOnCPAAddProduct();
			}
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));

			} catch (Exception e) {

			}
			driver.get(urls[1]);
			try {
				wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='container-fluid banner-container product-detail-banner mt-4']//div[7]//button")));
				ScrollingWebPage.PageDown(driver, SS_path);
				WEL.ClickonCMAAddToCart();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='container-fluid banner-container product-detail-banner mt-4']//div[7]//button")));
				WEL.ClickonCMAAddToCart();
			}
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			driver.get(urls[2]);
			try {
				wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='product-detail-page']//div[@class='container-fluid banner-container product-detail-banner mt-4']//button")));
				ScrollingWebPage.PageDown(driver, SS_path);
				WEL.ClickOnCFAAddProduct();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='product-detail-page']//div[@class='container-fluid banner-container product-detail-banner mt-4']//button")));
				WEL.ClickOnCFAAddProduct();
			}

			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			WEL.ClickonCheckOutOnCartPage();

			String Gemail = WEL.EnterGuestUser();
			WEL.ClickingOnCreateAccoutButton();
			WEL.GuestConfirmEmailId(Gemail);
			WEL.EnterPassword(excelOperation.getTestData("TC17", "WEL_Test_Data", "Password"));
			WEL.ClickonAgreementCheckBox();
			WEL.ClickingOnSaveAndContinue();
			WEL.GuestFirstName(excelOperation.getTestData("TC17", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC17", "WEL_Test_Data", "Guest_Lname"));
			WEL.selectShipCountry(excelOperation.getTestData("TC17", "WEL_Test_Data", "Sh_Country"));
			WEL.ShipAddressLineOne(excelOperation.getTestData("TC17", "WEL_Test_Data", "Sh_Address_line1"));
			WEL.ShipPostCode(excelOperation.getTestData("TC17", "WEL_Test_Data", "Sh_Zip_Code"));
			WEL.ShipTownCity(excelOperation.getTestData("TC17", "WEL_Test_Data", "Sh_City/ Province"));
			WEL.enterState(excelOperation.getTestData("TC17", "WEL_Test_Data", "Sh_State"));
			WEL.ShipPhoneNumber(excelOperation.getTestData("TC17", "WEL_Test_Data", "Sh_Phone_Number"));
			WEL.ShipSaveAndContinueButton();
			try {
				if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
					WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click Address on Address SUggestion due to timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
			}
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='nameOnCard']")));
				WEL.enterCardHolderName(excelOperation.getTestData("TC17", "WEL_Test_Data", "Guest_Fname"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
				WEL.enterCardNumber(excelOperation.getTestData("TC17", "WEL_Test_Data", "Card_Number"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
				WEL.selectExpirationMonthFromDropDown(
						excelOperation.getTestData("TC17", "WEL_Test_Data", "Expiry_Month"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
				WEL.selectExpirationYearFromDropDown(
						excelOperation.getTestData("TC17", "WEL_Test_Data", "Expiry_Year"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
				WEL.enterCVV_Number(excelOperation.getTestData("TC17", "WEL_Test_Data", "CVV"));
				driver.switchTo().defaultContent();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='paymentBilling']")));
					WEL.SaveAndContinueCheckOut();
					WEL.clickOnPlaceOrderButton();
					String orderID = WEL.fetchOrderId();
					ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
					excelOperation.updateTestData("TC17", "WEL_Test_Data", "Order_Id", orderID);
					String tax = WEL.fetchTaxValue();
					excelOperation.updateTestData("TC17", "WEL_Test_Data", "Tax", tax);
					String orderTotal = WEL.fetchOrderTotal();
					excelOperation.updateTestData("TC17", "WEL_Test_Data", "Order_Total", orderTotal);
					WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
				} catch (Exception e) {
					Reporting.updateTestReport("Card details were not entered due to timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click Save and Continue button due to timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}

	}

	@Test
	public void TC18_PlaceOrderof_CPACMA_PhysitalProducts_And_CFAPhysicalReviewCourse_ProductForExistingUser()
			throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest(
					"TC18_PlaceOrderof_CPACMA_PhysitalProducts_And_CFAPhysicalReviewCourse_ProductForExistingUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			String urls[] = excelOperation.getTestData("TC17", "WEL_Test_Data", "URL").split(",");

			driver.get(urls[0]);
			try {
				wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@id='Ultimate_CPA Review Course 2022 (Mentorship & Tutoring Bundle)']//form//button")));
				ScrollingWebPage.PageDown(driver, SS_path);
				WEL.ClickOnCPAAddProduct();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@id='Ultimate_CPA Review Course 2022 (Mentorship & Tutoring Bundle)']//form//button")));
				WEL.ClickOnCPAAddProduct();
			}
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));

			} catch (Exception e) {

			}
			driver.get(urls[1]);
			try {
				wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='container-fluid banner-container product-detail-banner mt-4']//div[7]//button")));
				ScrollingWebPage.PageDown(driver, SS_path);
				WEL.ClickonCMAAddToCart();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='container-fluid banner-container product-detail-banner mt-4']//div[7]//button")));
				WEL.ClickonCMAAddToCart();
			}
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			driver.get(urls[2]);
			try {
				wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='product-detail-page']//div[@class='container-fluid banner-container product-detail-banner mt-4']//button")));
				ScrollingWebPage.PageDown(driver, SS_path);
				WEL.ClickOnCFAAddProduct();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='product-detail-page']//div[@class='container-fluid banner-container product-detail-banner mt-4']//button")));
				WEL.ClickOnCFAAddProduct();
			}

			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC18", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC18", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickOnEnterNewAddressButtonOnShippingPage();
			WEL.GuestFirstName(excelOperation.getTestData("TC18", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC18", "WEL_Test_Data", "Guest_Lname"));
			WEL.selectShipCountry(excelOperation.getTestData("TC18", "WEL_Test_Data", "Sh_Country"));
			WEL.ShipAddressLineOne(excelOperation.getTestData("TC18", "WEL_Test_Data", "Sh_Address_line1"));
			WEL.ShipPostCode(excelOperation.getTestData("TC18", "WEL_Test_Data", "Sh_Zip_Code"));
			WEL.ShipTownCity(excelOperation.getTestData("TC18", "WEL_Test_Data", "Sh_City/ Province"));
			WEL.enterState(excelOperation.getTestData("TC18", "WEL_Test_Data", "Sh_State"));
			WEL.ShipPhoneNumber(excelOperation.getTestData("TC18", "WEL_Test_Data", "Sh_Phone_Number"));
			WEL.ShipSaveAndContinueButton();
			try {
				if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
					WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click Address on Address SUggestion due to timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
			}
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='nameOnCard']")));
				WEL.enterCardHolderName(excelOperation.getTestData("TC18", "WEL_Test_Data", "Guest_Fname"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
				WEL.enterCardNumber(excelOperation.getTestData("TC18", "WEL_Test_Data", "Card_Number"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
				WEL.selectExpirationMonthFromDropDown(
						excelOperation.getTestData("TC18", "WEL_Test_Data", "Expiry_Month"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
				WEL.selectExpirationYearFromDropDown(
						excelOperation.getTestData("TC18", "WEL_Test_Data", "Expiry_Year"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
				WEL.enterCVV_Number(excelOperation.getTestData("TC18", "WEL_Test_Data", "CVV"));
				driver.switchTo().defaultContent();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='paymentBilling']")));
					WEL.SaveAndContinueCheckOut();
					WEL.clickOnPlaceOrderButton();
					String orderID = WEL.fetchOrderId();
					ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
					excelOperation.updateTestData("TC18", "WEL_Test_Data", "Order_Id", orderID);
					String tax = WEL.fetchTaxValue();
					excelOperation.updateTestData("TC18", "WEL_Test_Data", "Tax", tax);
					String orderTotal = WEL.fetchOrderTotal();
					excelOperation.updateTestData("TC18", "WEL_Test_Data", "Order_Total", orderTotal);
					WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
				} catch (Exception e) {
					Reporting.updateTestReport("Card details were not entered due to timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click Save and Continue button due to timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}

	}

	@Test
	public void TC69_PlaceNewUser_OrderForPhysicalProduct_CFALevelI_PlatinumAndValidateThetax() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent
					.createTest("TC69_PlaceNewUser_OrderForPhysicalProduct_CFALevelI_PlatinumAndValidateThetax");
			driver.get(excelOperation.getTestData("TC69", "WEL_Test_Data", "URL"));
			driver.navigate().refresh();
			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='product-detail-page']//div[@class='container-fluid banner-container product-detail-banner mt-4']//button")));
				WEL.ClickOnCFAAddProduct();

				try {
					try {
						wait1.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
						driver.navigate().refresh();
					} catch (Exception e) {

					}
					WEL.ClickonCheckOutOnCartPage();
					String Guestemail = WEL.EnterGuestUser();
					WEL.ClickingOnCreateAccoutButton();
					WEL.GuestConfirmEmailId(Guestemail);
					WEL.EnterPassword(excelOperation.getTestData("TC69", "WEL_Test_Data", "Password"));
					WEL.ClickonAgreementCheckBox();
					WEL.ClickingOnSaveAndContinue();
					WEL.GuestFirstName(excelOperation.getTestData("TC69", "WEL_Test_Data", "Guest_Fname"));
					WEL.GuestLastName(excelOperation.getTestData("TC69", "WEL_Test_Data", "Guest_Lname"));
					WEL.selectShipCountry(excelOperation.getTestData("TC69", "WEL_Test_Data", "Sh_Country"));
					WEL.ShipAddressLineOne(excelOperation.getTestData("TC69", "WEL_Test_Data", "Sh_Address_line1"));
					WEL.ShipPostCode(excelOperation.getTestData("TC69", "WEL_Test_Data", "Sh_Zip_Code"));
					WEL.ShipTownCity(excelOperation.getTestData("TC69", "WEL_Test_Data", "Sh_City/ Province"));
					WEL.enterState(excelOperation.getTestData("TC69", "WEL_Test_Data", "Sh_State"));
					WEL.ShipPhoneNumber(excelOperation.getTestData("TC69", "WEL_Test_Data", "Sh_Phone_Number"));
					WEL.ShipSaveAndContinueButton();

					try {
						if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
							WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Failed to click Address on Address SUggestion due to timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					}

					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='nameOnCard']")));
						WEL.enterCardHolderName(excelOperation.getTestData("TC69", "WEL_Test_Data", "Guest_Fname"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
						WEL.enterCardNumber(excelOperation.getTestData("TC69", "WEL_Test_Data", "Card_Number"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
						WEL.selectExpirationMonthFromDropDown(
								excelOperation.getTestData("TC69", "WEL_Test_Data", "Expiry_Month"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
						WEL.selectExpirationYearFromDropDown(
								excelOperation.getTestData("TC69", "WEL_Test_Data", "Expiry_Year"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
						WEL.enterCVV_Number(excelOperation.getTestData("TC69", "WEL_Test_Data", "CVV"));
						driver.switchTo().defaultContent();

						try {
							wait.until(ExpectedConditions
									.elementToBeClickable(By.xpath("//button[@id='paymentBilling']")));
							WEL.SaveAndContinueCheckOut();
							WEL.clickOnPlaceOrderButton();
							String orderID = WEL.fetchOrderId();
							ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
							excelOperation.updateTestData("TC69", "WEL_Test_Data", "Order_Id", orderID);
							String tax = WEL.fetchTaxAmount();
							excelOperation.updateTestData("TC69", "WEL_Test_Data", "Tax", tax);
							String orderTotal = WEL.fetchOrderTotal();
							excelOperation.updateTestData("T69", "WEL_Test_Data", "Order_Total", orderTotal);

						} catch (Exception e) {
							Reporting.updateTestReport("Failed to click on SaveAndContinue due to timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Failed to Enter the card details due to timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

					}
				} catch (Exception e) {
					Reporting.updateTestReport("Failed to click on CheckOutOnCartPage due to timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click on CFAAddToCart due to timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (

		Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC70_PlaceExistingUser_OrderForPhysicalProduct_CFALevelI_PlatinumAndValidateThetax()
			throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			Reporting.test = Reporting.extent
					.createTest("TC70_PlaceExistingUser_OrderForPhysicalProduct_CFALevelI_PlatinumAndValidateThetax");
			driver.get(excelOperation.getTestData("TC70", "WEL_Test_Data", "URL"));
			driver.navigate().refresh();
			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='product-detail-page']//div[@class='container-fluid banner-container product-detail-banner mt-4']//button")));
				WEL.ClickOnCFAAddProduct();

				try {
					try {
						wait1.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
						driver.navigate().refresh();
					} catch (Exception e) {

					}
						WEL.ClickonCheckOutOnCartPage();
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='j_username']")));
							WEL.EnterexistingUserName(
									excelOperation.getTestData("TC70", "WEL_Test_Data", "Email_Address"));
							WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC70", "WEL_Test_Data", "Password"));
							WEL.ClickonLoginAndContinue();
							WEL.ClickOnEnterNewAddressButtonOnShippingPage();
							WEL.GuestFirstName(excelOperation.getTestData("TC70", "WEL_Test_Data", "Guest_Fname"));
							WEL.GuestLastName(excelOperation.getTestData("TC70", "WEL_Test_Data", "Guest_Lname"));
							WEL.selectShipCountry(excelOperation.getTestData("TC70", "WEL_Test_Data", "Sh_Country"));
							WEL.ShipAddressLineOne(
									excelOperation.getTestData("TC70", "WEL_Test_Data", "Sh_Address_line1"));
							WEL.ShipPostCode(excelOperation.getTestData("TC70", "WEL_Test_Data", "Sh_Zip_Code"));
							WEL.ShipTownCity(excelOperation.getTestData("TC70", "WEL_Test_Data", "Sh_City/ Province"));
							WEL.enterState(excelOperation.getTestData("TC70", "WEL_Test_Data", "Sh_State"));
							WEL.ShipPhoneNumber(excelOperation.getTestData("TC70", "WEL_Test_Data", "Sh_Phone_Number"));
							WEL.ShipSaveAndContinueButton();
							try {
								if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
									WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Failed to click Address on Address SUggestion due to timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}
							driver.switchTo()
									.frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
							try {
								wait.until(
										ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='nameOnCard']")));
								WEL.enterCardHolderName(
										excelOperation.getTestData("TC70", "WEL_Test_Data", "Guest_Fname"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
								WEL.enterCardNumber(excelOperation.getTestData("TC70", "WEL_Test_Data", "Card_Number"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
								WEL.selectExpirationMonthFromDropDown(
										excelOperation.getTestData("TC70", "WEL_Test_Data", "Expiry_Month"));
								driver.switchTo().defaultContent();
								driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
								WEL.selectExpirationYearFromDropDown(
										excelOperation.getTestData("TC69", "WEL_Test_Data", "Expiry_Year"));
								driver.switchTo().defaultContent();
								driver.switchTo()
										.frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
								WEL.enterCVV_Number(excelOperation.getTestData("TC70", "WEL_Test_Data", "CVV"));
								driver.switchTo().defaultContent();

								try {
									wait.until(ExpectedConditions
											.elementToBeClickable(By.xpath("//button[@id='paymentBilling']")));
									WEL.SaveAndContinueCheckOut();
									WEL.clickOnPlaceOrderButton();
									String orderID = WEL.fetchOrderId();
									ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
									excelOperation.updateTestData("TC70", "WEL_Test_Data", "Order_Id", orderID);
									String tax = WEL.fetchTaxAmount();
									excelOperation.updateTestData("TC70", "WEL_Test_Data", "Tax", tax);
									String orderTotal = WEL.fetchOrderTotal();
									excelOperation.updateTestData("T70", "WEL_Test_Data", "Order_Total", orderTotal);
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Failed to click on SaveAndContinue due to timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Failed to enter the Card details due to timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Failed to enter the emails address due to timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Failed to click on CheckoutOnCartPage due to timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Failed to click on CFAAddToCart due to timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC08_UserRedirection_ToForgotORChangePassword_PageAfter3Failed_AttemptsToLogin() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			Reporting.test = Reporting.extent
					.createTest("TC08_UserRedirection_ToForgotORChangePassword_PageAfter3Failed_AttemptsToLogin");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			driver.get("https://uat.efficientlearning.com/login/checkout");
			driver.navigate().refresh();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC08", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC08", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC08", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC08", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			String pageurl = driver.getCurrentUrl();
			if (pageurl.equals("https://uat.efficientlearning.com/login/pw/request/"))
				Reporting.updateTestReport("Forgot /Change Password page should be displayed",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to display the Forgot Password instructions",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	@Test
	public void TC19_PlaceOrderOf_CPACMACFA_SupplementCourse_ForNewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC19_PlaceOrderOf_CPACMACFA_SupplementCourse_ForNewUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			String urls[] = excelOperation.getTestData("TC19", "WEL_Test_Data", "URL").split(",");

			driver.get(urls[0]);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnAddToCartForSupplement();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));

			} catch (Exception e) {

			}
			driver.get(urls[1]);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnAddToCartForSupplement();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			driver.get(urls[2]);
			WEL.ClickOnAddToCartForSupplement();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			WEL.ClickonCheckOutOnCartPage();
			String Gemail = WEL.EnterGuestUser();
			WEL.ClickingOnCreateAccoutButton();
			WEL.GuestConfirmEmailId(Gemail);
			WEL.EnterPassword(excelOperation.getTestData("TC19", "WEL_Test_Data", "Password"));
			WEL.ClickonAgreementCheckBox();
			WEL.ClickingOnSaveAndContinue();
			WEL.GuestFirstName(excelOperation.getTestData("TC19", "WEL_Test_Data", "First_Name"));
			WEL.GuestLastName(excelOperation.getTestData("TC19", "WEL_Test_Data", "Last_Name"));
			WEL.selectBillCountry(excelOperation.getTestData("TC19", "WEL_Test_Data", "Bill_Country"));
			WEL.AddressLineOne(excelOperation.getTestData("TC19", "WEL_Test_Data", "Bill_Address_line1"));
			WEL.EnterZipecode(excelOperation.getTestData("TC19", "WEL_Test_Data", "Bill_Zip_Code"));
			WEL.enterState(excelOperation.getTestData("TC19", "WEL_Test_Data", "Bill_State"));
			WEL.EnterCity(excelOperation.getTestData("TC19", "WEL_Test_Data", "Bill_City/ Province"));
			WEL.EnterPhoneNumber(excelOperation.getTestData("TC19", "WEL_Test_Data", "Bill_Phone_Number"));
			try {
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
				wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
				WEL.enterCardHolderName(excelOperation.getTestData("TC19", "WEL_Test_Data", "Guest_Fname"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
				WEL.enterCardNumber(excelOperation.getTestData("TC19", "WEL_Test_Data", "Card_Number"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
				WEL.selectExpirationMonthFromDropDown(
						excelOperation.getTestData("TC19", "WEL_Test_Data", "Expiry_Month"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
				WEL.selectExpirationYearFromDropDown(
						excelOperation.getTestData("TC19", "WEL_Test_Data", "Expiry_Year"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
				WEL.enterCVV_Number(excelOperation.getTestData("TC19", "WEL_Test_Data", "CVV"));
				driver.switchTo().defaultContent();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to Enter the Carrd detais due to  timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.SaveAndContinueCheckOut();

			try {
				if (WEL.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed())
					WEL.clickOnUseSelectedBillingAddressButtonAddressDoctor();
			} catch (Exception e) {
				Reporting.updateTestReport("Address Suggestiond page is not appering on Billing page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
			}

			WEL.clickOnPlaceOrderButton();
			String orderID = WEL.fetchOrderId();
			excelOperation.updateTestData("TC19", "WEL_Test_Data", "Order_Id", orderID);
			ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
			String tax = WEL.fetchTaxValue();
			excelOperation.updateTestData("TC19", "WEL_Test_Data", "Tax", tax);
			String orderTotal = WEL.fetchOrderTotal();
			excelOperation.updateTestData("TC19", "WEL_Test_Data", "Order_Total", orderTotal);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}

	@Test
	public void TC77_Verify_TheFreeTrail_FormForCMACPACFAProducts() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			Reporting.test = Reporting.extent.createTest("TC77_Verify_TheFreeTrail_FormForCMACPACFAProducts");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCPAProduct();
			WEL.ClickOnFreeTrail();
			WEL.FreeTrialFirstName();
			WEL.FreeTrailLastName();
			WEL.FreeTrailEmail();
			WEL.FreeTrailPassword();

			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//a[@class='navbar-brand brand-logo-top-desktop']")));
				WEL.FreeTrailWELIcon();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click WEL Icon due to  timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_1']/a[2]")));
				WEL.ClickonCMAProduct();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click on CMAProduct due to  timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.ClickOnCMAFreeTrail();
			WEL.FreeTrialFirstName();
			WEL.FreeTrailLastName();
			WEL.FreeTrailEmail();
			WEL.FreeTrailPassword();
			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//a[@class='navbar-brand brand-logo-top-desktop']")));
				WEL.FreeTrailWELIcon();
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click on WEL Icon due to  timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			ScrollingWebPage.PageScrolldown(driver, 0, 400, SS_path);
			WEL.ClickonCFAProduct();
			WEL.ClickOnCFAFreeTrail();
			WEL.FreeTrialFirstName();
			WEL.FreeTrailLastName();
			WEL.FreeTrailEmail();
			WEL.FreeTrailPassword();

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC26_ShippingChargeFor_MultipleProducts() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC26_ShippingChargeFor_MultipleProducts");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			String urls[] = excelOperation.getTestData("TC26", "WEL_Test_Data", "URL").split(",");

			driver.get(urls[0]);
			try {
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			} catch (Exception e) {
				driver.navigate().refresh();
				wait1.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='btn-group btn-group-toggle']/label[2]")));
				WEL.ClickonCMAeBook();
			}
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAAddToCart();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));

			} catch (Exception e) {

			}
			driver.get(urls[1]);
			// ScrollingWebPage.PageDown(driver, SS_path);
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			Thread.sleep(2000);
			WEL.ClickOnCPAAddProduct();
			try {
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")));
				driver.navigate().refresh();
			} catch (Exception e) {

			}
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC26", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC26", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ShippingChargeForMultipleProducts();
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

}