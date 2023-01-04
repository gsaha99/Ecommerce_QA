package Test_Suite;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import PageObjectRepo.app_Hybris_BO_Repo;
import PageObjectRepo.app_WEL_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class WEL_Test_Suite extends DriverModule {
	app_WEL_Repo WEL;
	app_Hybris_BO_Repo HybrisBO;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);

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
	public void TC01_Standalone_UserRegistration() throws IOException {
		try {
			// JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC01_Standalone_UserRegistration");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			WEL.ClickingOnLoginButton();
			WEL.ClickOnCreateOne();
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
			WEL.EnterFirstName(excelOperation.getTestData("TC01", "WEL_Test_Data", "First_Name"));
			WEL.EnterLastName(excelOperation.getTestData("TC01", "WEL_Test_Data", "Last_Name"));
			String email = WEL.enterEmailIdInCreateAccountForm();
			WEL.confirmEmailIdInCreateAccountForm(email);
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			WEL.EnterPassword(excelOperation.getTestData("TC01", "WEL_Test_Data", "Password"));
			WEL.ClickonTermsCheckBox();
			WEL.ClickonAgreementCheckBox();
			WEL.ClickingOnCreateAccoutButton();
			if (WEL.CheckAccountTextAftreLogin())
				Reporting.updateTestReport("Account Link is appearing after successful Login",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed display the Account Link", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			excelOperation.updateTestData("TC01", "WEL_Test_Data", "Email_Address", email);
			Thread.sleep(1000);

			// Thread.sleep(5000);

			/*
			 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			 * wait.until(ExpectedConditions .visibilityOfElementLocated(By.
			 * xpath("//button[@class='btn-primary btn-privacy']")));
			 */
			try {
				WEL.ClickonAccountIcon();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//button[@class='btn-primary btn-privacy']")));

				WEL.ClickonIAcceptButton();
			} catch (Exception e) {
				WEL.ClickonSignOut();
			}
			WEL.ClickonSignOut();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validating User Registration During checkout for Physical
	 * Product
	 */
//	@Test
	/*
	 * public void TC02_UserRegistration_DuringCheckoutFor_PhysicalCart() throws
	 * IOException { try { JavascriptExecutor JS = (JavascriptExecutor) driver;
	 * Reporting.test =
	 * Reporting.extent.createTest("TC01_Standalone_UserRegistration");
	 * driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset",
	 * "Data")); driver.navigate().refresh(); Thread.sleep(1000);
	 * JS.executeScript("window.scrollBy(0,600)"); WEL.ClickonCIAProduct();
	 * Thread.sleep(100); WEL.ClickonShopCourse(); Thread.sleep(1000);
	 * WEL.ClickonAddToCart(); WEL.ClickonCouponRemove();
	 * WEL.ClickonCheckOutOnCartPage();
	 * 
	 * } catch (Exception e) { System.out.println(e.getMessage()); }
	 * 
	 * }
	 */
	/*
	 * @Author: Vishnu
	 * 
	 * @Description: This test cases is to Login with newly Registered user
	 */
	@Test
	public void TC04_LoginFor_NewlyRegisteredUser() throws IOException {
		try {
			//JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC04_LoginFor_NewlyRegisterUser");
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			WEL.ClickingOnLoginButton();
			WEL.EnterUserNameOnLoginPage(excelOperation.getTestData("TC01", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordOnLoginPage(excelOperation.getTestData("TC01", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginButton();
			WEL.ClickonAccountIcon();
			Thread.sleep(1000);
			// WEL.ClickonIAcceptButton();
			WEL.ClickonSignOut();
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			//JavascriptExecutor JS = (JavascriptExecutor) driver;
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
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
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
			// JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent
					.createTest("TC03_UserRegistration_DuringCheckoutForDigitalCartwith_NonUSBillingAddress");
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			// JS.executeScript("window.scrollBy(0,600)");
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			// JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageScrolldown(driver, 0, 300);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			// JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
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
			WEL.selectCountry(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Country"));
			WEL.AddressLineOne(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Address_line1"));
			WEL.EnterZipecode(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Zip_Code"));
			WEL.EnterCity(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_City/ Province"));
			WEL.EnterPhoneNumber(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Phone_Number"));
			Thread.sleep(2000);
			// JS.executeScript("window.scrollBy(0,-300)");
			ScrollingWebPage.PageScrolldown(driver, 0, -300);
			WEL.ClickOnBackTOCart();
			WEL.ClickOnRemoveOnCartPage();
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			// JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC05_UserLoginDuring_Checkout");
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			// JS.executeScript("window.scrollBy(0,600)");
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(1000);
			// JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageScrolldown(driver, 0, 300);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			// JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			WEL.ClickonCouponRemove();
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC05", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC05", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			// JS.executeScript("window.scrollBy(0,-300)");
			ScrollingWebPage.PageScrolldown(driver, 0, -300);
			WEL.ClickOnBackTOCart();
			WEL.ClickOnRemoveOnCartPage();
			WEL.ClickAccountCartPage();
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//button[@class='btn-primary btn-privacy']")));

			WEL.ClickonIAcceptButton();
			WEL.ClickonSignOut();
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			// JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC07_ForgotPasswordDuring_Checkout");
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			// JS.executeScript("window.scrollBy(0,600)");
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(1000);
			// JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageScrolldown(driver, 0, 300);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			// JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
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
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			WEL.ClickCartIconOnForgotPasswordPage();
			WEL.ClickOnRemoveOnCartPage();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/*
	 * 
	 * @Author: Vishnu
	 * 
	 * @Description: This test cases is To Verify Forgot Password page redirection
	 * after 3 failed attempts to user login
	 * 
	 * @Test public void
	 * TC08_UserRedirection_ForgotChangePasswordpage_After3Failed_AttemptsToLogin()
	 * throws IOException { try { JavascriptExecutor JS = (JavascriptExecutor)
	 * driver; Reporting.test = Reporting.extent .createTest(
	 * "TC08_UserRedirection_ForgotChangePasswordpage_After3Failed_AttemptsToLogin")
	 * ; driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset",
	 * "Data")); driver.navigate().refresh(); Thread.sleep(1000);
	 * JS.executeScript("window.scrollBy(0,600)"); WEL.ClickonCMAProduct();
	 * Thread.sleep(2000); WEL.ClickonExploreCourseCMAProduct(); Thread.sleep(1000);
	 * JS.executeScript("window.scrollBy(0,300)");
	 * WEL.ClickonViewCourseForCMAProduct(); Thread.sleep(2000); //
	 * WEL.ClickonCMAeBook(); WEL.ClickonCMAPrinteBook(); Thread.sleep(2000);
	 * JS.executeScript("window.scrollBy(0,400)"); Thread.sleep(1000);
	 * WEL.ClickonCMAAddToCart(); WEL.ClickonCouponRemove();
	 * WEL.ClickonCheckOutOnCartPage();
	 * WEL.EnterexistingUserName(excelOperation.getTestData("TC08", "WEL_Test_Data",
	 * "Email_Address"));
	 * WEL.EnterInvalidPasswordLoginPage(excelOperation.getTestData("TC08",
	 * "WEL_Test_Data", "Password"));
	 * WEL.EnterInvalidPasswordLoginPage(excelOperation.getTestData("TC08",
	 * "WEL_Test_Data", "Password"));
	 * WEL.EnterInvalidPasswordLoginPage(excelOperation.getTestData("TC08",
	 * "WEL_Test_Data", "Password")); WEL.ClickonLoginAndContinue(); String title =
	 * driver.getTitle(); if
	 * (title.equals("Request New Password | Wiley Efficient Learning"))
	 * Reporting.updateTestReport("Forgot / Change Password Page is be displayed",
	 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); else
	 * Reporting.
	 * updateTestReport("Failed to display the Forgot / Change Password Page",
	 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
	 * WEL.ClickCartIconOnForgotPasswordPage(); WEL.ClickOnRemoveOnCartPage(); }
	 * catch (Exception e) { System.out.println(e.getMessage()); }
	 * 
	 * }
	 * 
	 */ @Test

	public void TC09_AddToCart_WithStudentDiscount_InAnonymousCart() throws IOException {
		try {
			// JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC09_AddToCart_WithStudentDiscount_InAnonymousCart");
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			// JS.executeScript("window.scrollBy(0,600)");
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(1000);
			// JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageScrolldown(driver, 0, 300);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			// JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			WEL.ClickonCouponRemove();
			WEL.ClickOnAddaDiscountCode();
			WEL.EnterDiscountCode(excelOperation.getTestData("TC09", "WEL_Test_Data", "PromoCode"));
			WEL.ClickOnDiscountApplyButton();
			WEL.ClickOnRemoveOnCartPage();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void TC10_AddToCart_WithPromoCode_InAnonymousCart() throws IOException {
		try {
			// JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC10_AddToCart_WithPromoCode_InAnonymousCart");
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			// JS.executeScript("window.scrollBy(0,600)");
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(1000);
			// JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageScrolldown(driver, 0, 300);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			// JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			WEL.ClickonCouponRemove();
			WEL.ClickOnAddaDiscountCode();
			WEL.EnterDiscountCode(excelOperation.getTestData("TC10", "WEL_Test_Data", "PromoCode"));
			WEL.ClickOnDiscountApplyButton();
			WEL.ClickOnRemoveOnCartPage();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void TC11_AddToCartFor_LoggedInUser() throws IOException {
		try {
			// JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC11_AddToCartFor_LoggedInUser");
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			WEL.ClickingOnLoginButton();
			WEL.EnterUserNameOnLoginPage(excelOperation.getTestData("TC01", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordOnLoginPage(excelOperation.getTestData("TC01", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginButton();
			// WEL.ClickonIAcceptButton();
			WEL.ClickWELIconHomePage();
			Thread.sleep(1000);
			// JS.executeScript("window.scrollBy(0,600)");
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(1000);
			// JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageScrolldown(driver, 0, 300);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			// JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			WEL.ClickonCouponRemove();
			Thread.sleep(2000);
			WEL.ClickOnRemoveOnCartPage();
			Thread.sleep(1000);
			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(1000);
			WEL.ClickonAccountIcon();
			WEL.ClickonSignOut();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void TC12_QunatityRestriction_ForPhysicalProduct() throws IOException {
		try {
			// JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC02_UserRegistration_DuringCheckoutForPhysicalCart");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageScrolldown(driver, 0, 480);
			/*
			 * Actions builder = new Actions(driver); Action moveAction = (Action)
			 * builder.moveByOffset(550, 0).build().perform();; moveAction.perform();
			 */

			// JS.executeScript("window.scrollBy(550,550)");

			WEL.ClickOnCIAProduct();
			// Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
			WEL.ClickOnShopCourseForCIAProduct();
			// Thread.sleep(1000);
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
			// JS.executeScript("window.scrollBy(0,800)");
			ScrollingWebPage.PageScrolldown(driver, 0, 800);
			WEL.ClickOnViewCourseForCIAProduct(); // Thread.sleep(2000);
			WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
			// JS.executeScript("window.scrollBy(0,700)");
			ScrollingWebPage.PageScrolldown(driver, 0, 680);
			WEL.ClickOnCIAAddProduct();
			WEL.ClickOnSelectQuantity();
			WEL.ClickOnRemoveOnCartPage();
		} catch (Exception e) {

		}
	}

	@Test
	public void TC02_UserRegistration_DuringCheckoutForPhysicalCart() throws IOException {
		try {
			// JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC02_UserRegistration_DuringCheckoutForPhysicalCart");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageScrolldown(driver, 0, 480);

			WEL.ClickOnCIAProduct();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
			WEL.ClickOnShopCourseForCIAProduct();
			// Thread.sleep(1000);
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
			// JS.executeScript("window.scrollBy(0,800)");
			ScrollingWebPage.PageScrolldown(driver, 0, 800);
			WEL.ClickOnViewCourseForCIAProduct(); // Thread.sleep(2000);
			WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
			// JS.executeScript("window.scrollBy(0,700)");
			WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait7.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
			ScrollingWebPage.PageScrolldown(driver, 0, 680);
			WEL.ClickOnCIAAddProduct();
			WEL.ClickonCheckOutOnCartPage();
			String Gemail = WEL.EnterGuestUser();
			WEL.ClickingOnCreateAccoutButton();
			WEL.GuestConfirmEmailId(Gemail);
			WEL.EnterPassword(excelOperation.getTestData("TC02", "WEL_Test_Data", "Password"));
			WEL.ClickonAgreementCheckBox();
			WEL.ClickingOnSaveAndContinue();
			// JS.executeScript("window.scrollBy(0,-300)");
			ScrollingWebPage.PageScrollUp(driver, 0, -300);
			WEL.ClickOnBackTOCart();
			WEL.ClickOnRemoveOnCartPage();
		} catch (Exception e) {

		}
	}

	@Test
	public void TC14_ExtraDiscount_CouponCodeGOVT() throws IOException {
		try {
			//JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC14_ExtraDiscount_CouponCodeGOVT");
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			Thread.sleep(2000);
			driver.navigate().refresh();
			Thread.sleep(1000);
			//JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			WEL.ClickonCPAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			//JS.executeScript("window.scrollBy(0,800)");
			ScrollingWebPage.PageScrolldown(driver, 0, 800);
			Thread.sleep(1000);
			WEL.ClickonViewCourseForCPAProduct();
			//JS.executeScript("window.scrollBy(0,500)");
			ScrollingWebPage.PageScrolldown(driver, 0, 500);
			Thread.sleep(1000);
			WEL.ClickOnCIAAddProduct();
			WEL.ClickOnAddaDiscountCode();
			//JS.executeScript("window.scrollBy(0,200)");
			ScrollingWebPage.PageScrolldown(driver, 0, 200);
			WEL.EnterExtraDiscountCode(excelOperation.getTestData("TC14", "WEL_Test_Data", "PromoCode"));
			WEL.ClickOnDiscountApplyButton();
			WEL.ClickOnRemoveOnCartPage();
		} catch (Exception e) {

		}
	}

	
	@Test
	public void TC13_PlaceOrder_WithmultipleProducts_And_PromotionForExistingUser() throws IOException {
		try {
			JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent
					.createTest("TC13_PlaceOrder_WithmultipleProducts_And_PromotionForExistingUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			//JS.executeScript("window.scrollBy(0,600)");
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			//JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageScrolldown(driver, 0, 300);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			//JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			Thread.sleep(1000);
			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(2000);
			//JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			WEL.ClickonCPAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			//JS.executeScript("window.scrollBy(0,800)");
			ScrollingWebPage.PageScrolldown(driver, 0, 800);
			Thread.sleep(1000);
			WEL.ClickonViewCourseForCPAProduct();
			//JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickOnCIAAddProduct();
			WEL.ClickOnRemoveOnCartPage();
			WEL.ClickOnRemoveOnCartPage();
		} catch (Exception e) {

		}
	}
  
	@Test
	public void TC15_PlaceOrder_CMACPACFA_DigitalProducts_ForNewUser() throws IOException {
		try {
			//JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC15_PlaceOrder_CMACPACFA_DigitalProducts_ForNewUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			//JS.executeScript("window.scrollBy(0,600)");
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			//JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageScrolldown(driver, 0, 300);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			//JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(2000);
			//JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			WEL.ClickonCPAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			//JS.executeScript("window.scrollBy(0,800)");
			ScrollingWebPage.PageScrolldown(driver, 0, 800);
			Thread.sleep(2000);
			WEL.ClickonViewCourseForCPAProduct();
			//JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(2000);
			WEL.ClickOnCIAAddProduct();
			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(2000);
			//JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			WEL.ClickonCFAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			//JS.executeScript("window.scrollBy(0,800)");
			ScrollingWebPage.PageScrolldown(driver, 0, 800);
			Thread.sleep(2000);
			WEL.ClickonViewCourseForCFAProduct();
			//JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageScrolldown(driver, 0, 300);
			Thread.sleep(2000);
			WEL.ClickonViewCourseLinkForCFAProduct();
			Thread.sleep(2000);
			WEL.ClickeBookforCFAProduct();
			Thread.sleep(3000);
			//JS.executeScript("window.scrollBy(0,650)");
			ScrollingWebPage.PageScrolldown(driver, 0, 650);
			WEL.ClickOnCIAAddProduct();
			WEL.ClickonCheckOutOnCartPage();
			String Gemail = WEL.EnterGuestUser();
			WEL.ClickingOnCreateAccoutButton();
			WEL.GuestConfirmEmailId(Gemail);
			WEL.EnterPassword(excelOperation.getTestData("TC15", "WEL_Test_Data", "Password"));
			WEL.ClickonAgreementCheckBox();
			WEL.ClickingOnSaveAndContinue();
			WEL.GuestFirstName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Lname"));
			WEL.selectShipCountry(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_Country"));
			WEL.ShipAddressLineOne(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_Address_line1"));
			WEL.ShipPostCode(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_Zip_Code"));

			WEL.ShipTownCity(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_City/ Province"));
			WEL.ShipState(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_State"));

			WEL.ShipPhoneNumber(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_Phone_Number"));
			WEL.ShipSaveAndContinueButton();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
			WEL.enterCardHolderName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Fname"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
			WEL.enterCardNumber(excelOperation.getTestData("TC15", "WEL_Test_Data", "Card_Number"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
			WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC15", "WEL_Test_Data", "Expiry_Month"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
			WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC15", "WEL_Test_Data", "Expiry_Year"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
			WEL.enterCVV_Number(excelOperation.getTestData("TC15", "WEL_Test_Data", "CVV"));
			driver.switchTo().defaultContent();
			WEL.SaveAndContinueCheckOut();
			WEL.clickOnPlaceOrderButton();
			String orderID = WEL.fetchOrderId();
			excelOperation.updateTestData("TC15", "WEL_Test_Data", "Order_Id", orderID);
			//JS.executeScript("window.scrollBy(0,600)");
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			String tax = WEL.fetchTaxAmount();
			excelOperation.updateTestData("TC15", "WEL_Test_Data", "Tax", tax);
			String orderTotal = WEL.fetchOrderTotal();
			excelOperation.updateTestData("TC15", "WEL_Test_Data", "Order_Total", orderTotal);
			Thread.sleep(2000);
			//JS.executeScript("window.scrollBy(0,-300)");
			ScrollingWebPage.PageScrolldown(driver, 0, -300);
			Thread.sleep(2000);
			WEL.ClickOnWELIconOrderConfirmationPage();
			Thread.sleep(2000);
			WEL.ClickOnAccountIconFromOrderConfirmationPage();
			WEL.ClickonSignOut();
		} catch (Exception e) {

		}
	}

	@Test
	public void TC16_PlaceOrder_CMACPACFA_DigitalProducts_ForExistingUser() throws IOException {
		try {
			//JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC16_PlaceOrder_CMACPACFA_DigitalProducts_ForExistingUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			//JS.executeScript("window.scrollBy(0,600)");
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			//JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageScrolldown(driver, 0, 300);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			//JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(2000);
			//JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			WEL.ClickonCPAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			//JS.executeScript("window.scrollBy(0,800)");
			ScrollingWebPage.PageScrolldown(driver, 0, 800);
			Thread.sleep(1000);
			WEL.ClickonViewCourseForCPAProduct();
			//JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			Thread.sleep(1000);
			WEL.ClickOnCIAAddProduct();
			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(2000);
			//JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400);
			WEL.ClickonCFAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			//JS.executeScript("window.scrollBy(0,800)");
			ScrollingWebPage.PageScrolldown(driver, 0, 800);
			Thread.sleep(1000);
			WEL.ClickonViewCourseForCFAProduct();
			//JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageScrolldown(driver, 0, 300);
			Thread.sleep(1000);
			WEL.ClickonViewCourseLinkForCFAProduct();
			WEL.ClickeBookforCFAProduct();
			Thread.sleep(1000);
			///JS.executeScript("window.scrollBy(0,650)");
			ScrollingWebPage.PageScrolldown(driver, 0, 650);
			WEL.ClickOnCIAAddProduct();
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC16", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			WEL.SelectingUSEButton();
			Thread.sleep(1000);
			WEL.SaveContinueCheckOutPage();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
			WEL.enterCardHolderName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Fname"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
			WEL.enterCardNumber(excelOperation.getTestData("TC16", "WEL_Test_Data", "Card_Number"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
			WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC16", "WEL_Test_Data", "Expiry_Month"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
			WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC16", "WEL_Test_Data", "Expiry_Year"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
			WEL.enterCVV_Number(excelOperation.getTestData("TC16", "WEL_Test_Data", "CVV"));
			driver.switchTo().defaultContent();
			WEL.SaveAndContinueCheckOut();
			WEL.clickOnPlaceOrderButton();
			String orderID = WEL.fetchOrderId();
			excelOperation.updateTestData("TC16", "WEL_Test_Data", "Order_Id", orderID);
			//JS.executeScript("window.scrollBy(0,600)");
			ScrollingWebPage.PageScrolldown(driver, 0, 600);
			String tax = WEL.fetchTaxAmount();
			excelOperation.updateTestData("TC16", "WEL_Test_Data", "Tax", tax);
			String orderTotal = WEL.fetchOrderTotal();
			excelOperation.updateTestData("TC16", "WEL_Test_Data", "Order_Total", orderTotal);
			Thread.sleep(2000);
			//JS.executeScript("window.scrollBy(0,-300)");
			ScrollingWebPage.PageScrolldown(driver, 0, 300);
			Thread.sleep(2000);
			WEL.ClickOnWELIconOrderConfirmationPage();
			Thread.sleep(2000);
			WEL.ClickOnAccountIconFromOrderConfirmationPage();
			WEL.ClickonSignOut();
		} catch (Exception e) {

		}
	}
	  
	@Test
	public void TC20_PlaceOrder_OfOther_ProductsCIACAIAFor_NewUser() throws IOException {
		try {
			JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC20_PlaceOrder_OfOther_ProductsCIACAIAFor_NewUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			JS.executeScript("window.scrollBy(0,700)");
			WEL.ClickOnCIAProduct();
			WEL.ClickOnShopCourseForCIAProduct();
			Thread.sleep(1000);
			JS.executeScript("window.scrollBy(0,300)");
			WEL.ClickOnViewCourseForCIAProduct();
			Thread.sleep(2000);
			JS.executeScript("window.scrollBy(0,600)");
			Thread.sleep(1000);
			WEL.ClickOnCIAAddProduct();

			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(2000);
			JS.executeScript("window.scrollBy(0,500)");
			Thread.sleep(1000);
			WEL.ClickOnCAIAProduct();
			Thread.sleep(1000);
			WEL.ClickOnLevel1TestBankForCAIAProduct();
			Thread.sleep(1000);
			JS.executeScript("window.scrollBy(0,600)");
			WEL.ClickOnCAIAAddProduct();
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

			WEL.ShipTownCity(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_City/ Province"));
			WEL.ShipState(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_State"));

			WEL.ShipPhoneNumber(excelOperation.getTestData("TC20", "WEL_Test_Data", "Sh_Phone_Number"));
			WEL.ShipSaveAndContinueButton();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
			WEL.enterCardHolderName(excelOperation.getTestData("TC20", "WEL_Test_Data", "Guest_Fname"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
			WEL.enterCardNumber(excelOperation.getTestData("TC20", "WEL_Test_Data", "Card_Number"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
			WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC20", "WEL_Test_Data", "Expiry_Month"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
			WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC20", "WEL_Test_Data", "Expiry_Year"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
			WEL.enterCVV_Number(excelOperation.getTestData("TC20", "WEL_Test_Data", "CVV"));
			driver.switchTo().defaultContent();
			WEL.SaveAndContinueCheckOut();
			WEL.clickOnPlaceOrderButton();
			String orderID = WEL.fetchOrderId();
			excelOperation.updateTestData("TC20", "WEL_Test_Data", "Order_Id", orderID);
			JS.executeScript("window.scrollBy(0,600)");
			String tax = WEL.fetchTaxAmount();
			excelOperation.updateTestData("TC20", "WEL_Test_Data", "Tax", tax);
			String orderTotal = WEL.fetchOrderTotal();
			excelOperation.updateTestData("TC20", "WEL_Test_Data", "Order_Total", orderTotal);
			Thread.sleep(2000);
			JS.executeScript("window.scrollBy(0,-300)");
			Thread.sleep(2000);
			WEL.ClickOnWELIconOrderConfirmationPage();
			Thread.sleep(2000);
			WEL.ClickOnAccountIconFromOrderConfirmationPage();
			WEL.ClickonSignOut();
		} catch (Exception e) {

		}
	}

	@Test
	public void TC21_PlaceOrder_CPACMACFA_FreeTrailFor_NewUser() throws IOException {
		try {
			JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC21_PlaceOrder_CPACMACFA_FreeTrailFor_Newuser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			JS.executeScript("window.scrollBy(0,600)");
			WEL.ClickonCPAProduct();
			Thread.sleep(1000);
			WEL.ClickOnFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC21", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC21", "WEL_Test_Data", "Last_Name"));
			String cpauser = WEL.EnterFreeTrailNewtUser();
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailCountry(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_Country"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailState(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_State"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailPassword(excelOperation.getTestData("TC21", "WEL_Test_Data", "Password"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			String cpaftextmessage = WEL.FreeTrailCPAText();
			Thread.sleep(2000);
			if (cpaftextmessage.equals("You’re almost set!"))
				Reporting.updateTestReport(
						"CPA FreeTail is successfully completed and page having text You’re almost set!",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the CPA FreeTail Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			WEL.FreeTrailWELIcon();
			Thread.sleep(1000);
			JS.executeScript("window.scrollBy(0,600)");
			WEL.ClickonCMAProduct();
			WEL.ClickOnFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC21", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC21", "WEL_Test_Data", "Last_Name"));
			String cmauser = WEL.EnterFreeTrailNewtUser();
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailCountry(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_Country"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailState(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_State"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailPassword(excelOperation.getTestData("TC21", "WEL_Test_Data", "Password"));
			JS.executeScript("window.scrollBy(0,200)");
			Thread.sleep(1000);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			String cmaftextmessage = WEL.FreeTrailCPAText();
			Thread.sleep(2000);
			if (cpaftextmessage.equals("You’re almost set!"))
				Reporting.updateTestReport(
						"CMA FreeTail is successfully completed and page having text You’re almost set!",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the CMA FreeTail Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			WEL.FreeTrailWELIcon();
			Thread.sleep(1000);
			JS.executeScript("window.scrollBy(0,400)");
			WEL.ClickonCFAProduct();
			WEL.ClickOnFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC21", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC21", "WEL_Test_Data", "Last_Name"));
			String cfauser = WEL.EnterFreeTrailNewtUser();
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailCountry(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_Country"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailState(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_State"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailPassword(excelOperation.getTestData("TC21", "WEL_Test_Data", "Password"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			String cfaftextmessage = WEL.FreeTrailCPAText();
			Thread.sleep(2000);
			if (cpaftextmessage.equals("You’re almost set!"))
				Reporting.updateTestReport(
						"CFA FreeTail is successfully completed and page having text You’re almost set!",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the CFA FreeTail Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void TC22_PlaceOrder_CPACMACFA_FreeTrailFor_ExistingUser() throws IOException {
		try {
			JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC22_PlaceOrder_CPACMACFA_FreeTrailFor_ExistingUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			JS.executeScript("window.scrollBy(0,600)");
			WEL.ClickonCPAProduct();
			Thread.sleep(1000);
			WEL.ClickOnFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC22", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC22", "WEL_Test_Data", "Last_Name"));
			WEL.EnterFreeTrailEmail(excelOperation.getTestData("TC22", "WEL_Test_Data", "Email_Address"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailCountry(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_Country"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailState(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_State"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailPassword(excelOperation.getTestData("TC22", "WEL_Test_Data", "Password"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			WEL.FreeTraiModelPopUpLoginButton();
			WEL.EnterFreeTrailUserNameOnLoginPage(excelOperation.getTestData("TC22", "WEL_Test_Data", "Email_Address"));
			WEL.EnterFreeTrailPasswordOnLoginPage(excelOperation.getTestData("TC22", "WEL_Test_Data", "Password"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTraiModelPopUpSubmitButton();
			WEL.FreeTrailCountry(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_Country"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailState(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_State"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailPassword(excelOperation.getTestData("TC22", "WEL_Test_Data", "Password"));
			JS.executeScript("window.scrollBy(0,200)");
			Thread.sleep(1000);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailSignUpCheckBox();
			Thread.sleep(1000);
			WEL.FreeTrailSignInButton();
		} catch (Exception e) {

		}
	}

	@Test
	public void TC23_PlaceOrder_OfOther_ProductsCIA_FreeTrailFor_NewUser() throws IOException {
		try {
			JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC23_PlaceOrder_OfOther_ProductsCIA_FreeTrailFor_NewUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			JS.executeScript("window.scrollBy(0,700)");
			WEL.ClickOnCIAProduct();
			Thread.sleep(1000);
			WEL.ClickOnCIAFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC23", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC23", "WEL_Test_Data", "Last_Name"));
			String ciauser = WEL.EnterFreeTrailNewtUser();
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailCountry(excelOperation.getTestData("TC23", "WEL_Test_Data", "Sh_Country"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailState(excelOperation.getTestData("TC23", "WEL_Test_Data", "Sh_State"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailPassword(excelOperation.getTestData("TC23", "WEL_Test_Data", "Password"));
			JS.executeScript("window.scrollBy(0,200)");
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			JS.executeScript("window.scrollBy(0,200)");
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
		} catch (Exception e) {

		}
	}

	@Test
	public void TC24_PlaceOrder_UsingSavedShippingAddress_ForExistingUser() throws IOException {
		try {
			JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC24_PlaceOrder_UsingSavedShippingAddress_ForExistingUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			JS.executeScript("window.scrollBy(0,600)");
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			JS.executeScript("window.scrollBy(0,300)");
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			JS.executeScript("window.scrollBy(0,400)");
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC16", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			WEL.SelectingUSEButton();
			Thread.sleep(1000);
			WEL.SaveContinueCheckOutPage();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
			WEL.enterCardHolderName(excelOperation.getTestData("TC03", "WEL_Test_Data", "Guest_Fname"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
			WEL.enterCardNumber(excelOperation.getTestData("TC16", "WEL_Test_Data", "Card_Number"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
			WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC16", "WEL_Test_Data", "Expiry_Month"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
			WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC16", "WEL_Test_Data", "Expiry_Year"));
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
			WEL.enterCVV_Number(excelOperation.getTestData("TC16", "WEL_Test_Data", "CVV"));
			driver.switchTo().defaultContent();
			WEL.SaveAndContinueCheckOut();
			WEL.clickOnPlaceOrderButton();
			String orderID = WEL.fetchOrderId();
			excelOperation.updateTestData("TC16", "WEL_Test_Data", "Order_Id", orderID);
			JS.executeScript("window.scrollBy(0,600)");
			String tax = WEL.fetchTaxAmount();
			excelOperation.updateTestData("TC16", "WEL_Test_Data", "Tax", tax);
			String orderTotal = WEL.fetchOrderTotal();
			excelOperation.updateTestData("TC16", "WEL_Test_Data", "Order_Total", orderTotal);
			Thread.sleep(2000);
			JS.executeScript("window.scrollBy(0,-300)");
			Thread.sleep(2000);
			WEL.ClickOnWELIconOrderConfirmationPage();
			Thread.sleep(2000);
			WEL.ClickOnAccountIconFromOrderConfirmationPage();
		} catch (Exception e) {

		}
	}

	@Test
	public void TC25_ShippingMethodfor_USUKAustralia_CanadaIndiaSingapore() throws IOException {
		try {
			JavascriptExecutor JS = (JavascriptExecutor) driver;
			Reporting.test = Reporting.extent.createTest("TC25_ShippingMethodfor_USUKAustralia_CanadaIndiaSingapore");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			JS.executeScript("window.scrollBy(0,600)");
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			JS.executeScript("window.scrollBy(0,300)");
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAPrinteBook();
			Thread.sleep(2000);
			JS.executeScript("window.scrollBy(0,400)");
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC16", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			JS.executeScript("window.scrollBy(0,300)");
			WEL.ClickOnAddNewAddressButton();
			WEL.GuestFirstName(excelOperation.getTestData("TC25", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC25", "WEL_Test_Data", "Guest_Lname"));
			WEL.selectShipCountry(excelOperation.getTestData("TC25", "WEL_Test_Data", "Sh_Country1"));
			String UKShipmethod = WEL.ShippingMethodForUK();
			System.out.println(UKShipmethod);
			Thread.sleep(2000);
			if (UKShipmethod.equals("$40.00"))
				Reporting.updateTestReport("The Shipping method for UK is $40",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Shipping method", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			WEL.selectShipCountry(excelOperation.getTestData("TC25", "WEL_Test_Data", "Sh_Country2"));
			String AusShipmethod = WEL.ShippingMethodForUK();
			System.out.println(AusShipmethod);
			Thread.sleep(2000);
			if (AusShipmethod.equals("$40.00"))
				Reporting.updateTestReport("The Shipping method for Australia is $40",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Shipping method", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);

		} catch (Exception e) {

		}
	}

}