package Test_Suite;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.By;
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
					Reporting.updateTestReport("Failed display the Account Link", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				excelOperation.updateTestData("TC01", "WEL_Test_Data", "Email_Address", email);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath( "//a[@aria-label='account']")));
					WEL.ClickonAccountIcon();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@class='btn-primary btn-privacy']")));
						WEL.ClickonIAcceptButton();
						WEL.ClickonSignOut();
					}
					catch(Exception e) {
						Reporting.updateTestReport("Privacy agreement accept button was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Account button was not clickable on homepage and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Help button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		} catch (Exception e) {
			WEL.ClickonSignOut();
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			WEL.ClickingOnLoginButton();
			WEL.EnterUserNameOnLoginPage(excelOperation.getTestData("TC01", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordOnLoginPage(excelOperation.getTestData("TC01", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginButton();
			// WEL.ClickonAccountIcon();
			Thread.sleep(1000);
			// WEL.ClickonIAcceptButton();
			WEL.ClickonSignOut();
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
		}

		catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
			ScrollingWebPage.PageScrolldown(driver, 0, -300,SS_path);
			WEL.ClickOnBackTOCart();
			WEL.ClickOnRemoveOnCartPage();
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
			WEL.ClickonCouponRemove();
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC05", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC05", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			ScrollingWebPage.PageScrolldown(driver, 0, -300,SS_path);
			WEL.ClickOnBackTOCart();
			WEL.ClickOnRemoveOnCartPage();
			WEL.ClickAccountCartPage();
			Thread.sleep(1000);
			WEL.ClickonSignOut();
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
			WEL.ClickonCouponRemove();
			WEL.ClickOnAddaDiscountCode();
			WEL.EnterDiscountCode(excelOperation.getTestData("TC09", "WEL_Test_Data", "PromoCode"));
			WEL.ClickOnDiscountApplyButton();
			WEL.ClickOnRemoveOnCartPage();
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	@Test
	public void TC10_AddToCart_WithPromoCode_InAnonymousCart() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC10_AddToCart_WithPromoCode_InAnonymousCart");
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
			WEL.ClickonCouponRemove();
			WEL.ClickOnAddaDiscountCode();
			WEL.EnterDiscountCode(excelOperation.getTestData("TC10", "WEL_Test_Data", "PromoCode"));
			WEL.ClickOnDiscountApplyButton();
			WEL.ClickOnRemoveOnCartPage();
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	@Test
	public void TC11_AddToCartFor_LoggedInUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC11_AddToCartFor_LoggedInUser");
			Thread.sleep(1000);
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			WEL.ClickingOnLoginButton();
			WEL.EnterUserNameOnLoginPage(excelOperation.getTestData("TC01", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordOnLoginPage(excelOperation.getTestData("TC01", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginButton();
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
			WEL.ClickonCouponRemove();
			Thread.sleep(2000);
			WEL.ClickOnRemoveOnCartPage();
			Thread.sleep(1000);
			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(1000);
			WEL.ClickonAccountIcon();
			WEL.ClickonSignOut();
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
						By.xpath("//div[@class='fe_flex grid_1']/a[text()='CIA']"))));
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
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							WEL.ClickOnCIAAddProduct();
							WEL.ClickOnSelectQuantity();
							WEL.ClickOnRemoveOnCartPage();
						}
						catch(Exception e) {
							Reporting.updateTestReport("Add to cart button on CIA PDP was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("The View Course link on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("CIA Product link was not visible on homepage and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC02_UserRegistration_DuringCheckoutForPhysicalCart() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC02_UserRegistration_DuringCheckoutForPhysicalCart");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver,SS_path);
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
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						WEL.ClickonCheckOutOnCartPage();
						String Gemail = WEL.EnterGuestUser();
						WEL.ClickingOnCreateAccoutButton();
						WEL.GuestConfirmEmailId(Gemail);
						WEL.EnterPassword(excelOperation.getTestData("TC02", "WEL_Test_Data", "Password"));
						WEL.ClickonAgreementCheckBox();
						WEL.ClickingOnSaveAndContinue();
						ScrollingWebPage.PageScrollUp(driver, 0, -300,SS_path);
						WEL.ClickOnBackTOCart();
						WEL.ClickOnRemoveOnCartPage();
					}
					catch(Exception e) {
						Reporting.updateTestReport("Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
			ScrollingWebPage.PageScrolldown(driver, 0, 800,SS_path);
			// ScrollingWebPage.PageDown(driver);
			Thread.sleep(1000);
			WEL.ClickonViewCourseForCPAProduct();
			ScrollingWebPage.PageScrolldown(driver, 0, 400,SS_path);
			//			ScrollingWebPage.PageDown(driver);
			Thread.sleep(1000);
			WEL.ClickOnCPAAddProduct();
			WEL.ClickOnAddaDiscountCode();
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.EnterExtraDiscountCode(excelOperation.getTestData("TC14", "WEL_Test_Data", "PromoCode"));
			WEL.ClickOnDiscountApplyButton();
			WEL.ClickOnRemoveOnCartPage();
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	@Test
	public void TC13_PlaceOrder_WithmultipleProducts_And_PromotionForExistingUser() throws IOException {
		try {
			Reporting.test = Reporting.extent
					.createTest("TC13_PlaceOrder_WithmultipleProducts_And_PromotionForExistingUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAProduct();
			WEL.ClickonExploreCourseCMAProduct();
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonViewCourseForCMAProduct();
			WEL.ClickonCMAeBook();
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
			ScrollingWebPage.PageDown(driver, SS_path);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
			WEL.ClickonCMAAddToCart();
			Thread.sleep(1000);
			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(2000); //
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCPAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct(); //

			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(2000);
			WEL.ClickonViewCourseForCPAProduct(); //

			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(2000);
			WEL.ClickOnCPAAddProduct();

			WEL.ClickOnRemoveOnCartPage();
			WEL.ClickOnRemoveOnCartPage();
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC15_PlaceOrder_CMACPACFA_DigitalProducts_ForNewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC15_PlaceOrder_CMACPACFA_DigitalProducts_ForNewUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
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
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(2000); //
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCPAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			// JS.executeScript("window.scrollBy(0,800)");
			ScrollingWebPage.PageScrolldown(driver, 0, 800,SS_path);
			// ScrollingWebPage.PageDown(driver);
			Thread.sleep(1000);
			WEL.ClickonViewCourseForCPAProduct();
			// JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageScrolldown(driver, 0, 400,SS_path);
			//			ScrollingWebPage.PageDown(driver);
			Thread.sleep(1000);
			WEL.ClickOnCPAAddProduct();
			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(2000); 
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCFAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct(); 
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(2000);
			WEL.ClickonViewCourseForCFAProduct(); 
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(2000);
			WEL.ClickonViewCourseLinkForCFAProduct();
			Thread.sleep(2000);
			WEL.ClickeBookforCFAProduct();
			Thread.sleep(3000); //
			ScrollingWebPage.PageDown(driver, SS_path);
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
			try {
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
				// JS.executeScript("window.scrollBy(0,600)");
				ScrollingWebPage.PageDown(driver, SS_path);
				String tax = WEL.fetchTaxAmount();
				excelOperation.updateTestData("TC15", "WEL_Test_Data", "Tax", tax);
				String orderTotal = WEL.fetchOrderTotal();
				excelOperation.updateTestData("TC15", "WEL_Test_Data", "Order_Total", orderTotal);
				Thread.sleep(2000);
				ScrollingWebPage.PageScrollUp(driver, 0, -300,SS_path);
				Thread.sleep(2000);
				WEL.ClickOnWELIconOrderConfirmationPage();
				Thread.sleep(2000);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'ACCOUNT')]"))));
					WEL.ClickOnAccountIconFromOrderConfirmationPage();
					WEL.ClickonSignOut();
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
					}
					catch(Exception e) {
						Reporting.updateTestReport("Help button was not visible and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("The Account button in order confirmation page was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("The name field in card information step was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC16_PlaceOrder_CMACPACFA_DigitalProducts_ForExistingUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC16_PlaceOrder_CMACPACFA_DigitalProducts_ForExistingUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
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
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(2000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCPAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			ScrollingWebPage.PageScrolldown(driver, 0, 800,SS_path);
			Thread.sleep(1000);
			WEL.ClickonViewCourseForCPAProduct();
			ScrollingWebPage.PageScrolldown(driver, 0, 400,SS_path);
			Thread.sleep(1000);
			WEL.ClickOnCPAAddProduct();
			WEL.ClickWELIconCheckoutPage();
			Thread.sleep(2000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCFAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickonViewCourseForCFAProduct();
			ScrollingWebPage.PageDown(driver, SS_path);
			Thread.sleep(1000);
			WEL.ClickonViewCourseLinkForCFAProduct();
			WEL.ClickeBookforCFAProduct();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
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
			try {
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
				ScrollingWebPage.PageDown(driver, SS_path);
				String tax = WEL.fetchTaxAmount();
				excelOperation.updateTestData("TC16", "WEL_Test_Data", "Tax", tax);
				String orderTotal = WEL.fetchOrderTotal();
				excelOperation.updateTestData("TC16", "WEL_Test_Data", "Order_Total", orderTotal);
				Thread.sleep(2000);
				ScrollingWebPage.PageScrollUp(driver, 0, -300,SS_path);
				Thread.sleep(2000);
				WEL.ClickOnWELIconOrderConfirmationPage();
				Thread.sleep(2000);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
							By.xpath("//a[contains(text(),'ACCOUNT')]"))));
					WEL.ClickOnAccountIconFromOrderConfirmationPage();
					WEL.ClickonSignOut();
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
					}
					catch(Exception e) {
						Reporting.updateTestReport("Help button was not visible and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("The Account button in order confirmation page was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("The name field in card information step was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC20_PlaceOrder_OfOther_ProductsCIACAIAFor_NewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC20_PlaceOrder_OfOther_ProductsCIACAIAFor_NewUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
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
				ScrollingWebPage.PageDown(driver, SS_path);
				String tax = WEL.fetchTaxAmount();
				excelOperation.updateTestData("TC20", "WEL_Test_Data", "Tax", tax);
				String orderTotal = WEL.fetchOrderTotal();
				excelOperation.updateTestData("TC20", "WEL_Test_Data", "Order_Total", orderTotal);
				Thread.sleep(2000);
				ScrollingWebPage.PageScrollUp(driver, 0, -300,SS_path);
				Thread.sleep(2000);
				WEL.ClickOnWELIconOrderConfirmationPage();
				Thread.sleep(2000);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'ACCOUNT')]"))));
					WEL.ClickOnAccountIconFromOrderConfirmationPage();
					WEL.ClickonSignOut();
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
					}
					catch(Exception e) {
						Reporting.updateTestReport("Help button was not visible and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);}
				}
				catch(Exception e) {
					Reporting.updateTestReport("The Account button in order confirmation page was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("The name field in card information step was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC21_PlaceOrder_CPACMACFA_FreeTrailFor_NewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC21_PlaceOrder_CPACMACFA_FreeTrailFor_Newuser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageScrolldown(driver,0,600,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.ClickonCPAProduct();
			Thread.sleep(1000);
			WEL.ClickOnFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC21", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC21", "WEL_Test_Data", "Last_Name"));
			WEL.EnterFreeTrailNewtUser();
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailCountry(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_Country"));
			// ScrollingWebPage.PageDown(driver);
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			WEL.FreeTrailState(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_State"));
			// ScrollingWebPage.PageDown(driver);
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			WEL.FreeTrailPassword(excelOperation.getTestData("TC21", "WEL_Test_Data", "Password"));
			// ScrollingWebPage.PageDown(driver);
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			// ScrollingWebPage.PageDown(driver);
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
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
			ScrollingWebPage.PageScrolldown(driver,0,600,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.ClickonCMAProduct();
			WEL.ClickOnFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC21", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC21", "WEL_Test_Data", "Last_Name"));
			WEL.EnterFreeTrailNewtUser();
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailCountry(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_Country"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailState(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_State"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailPassword(excelOperation.getTestData("TC21", "WEL_Test_Data", "Password"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			Thread.sleep(1000);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			WEL.FreeTrailCPAText();
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
			ScrollingWebPage.PageScrolldown(driver,0,400,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.ClickonCFAProduct();
			WEL.ClickOnFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC21", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC21", "WEL_Test_Data", "Last_Name"));
			WEL.EnterFreeTrailNewtUser();
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailCountry(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_Country"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailState(excelOperation.getTestData("TC21", "WEL_Test_Data", "Sh_State"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailPassword(excelOperation.getTestData("TC21", "WEL_Test_Data", "Password"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			WEL.FreeTrailCPAText();
			Thread.sleep(2000);
			if (cpaftextmessage.equals("You’re almost set!"))
				Reporting.updateTestReport(
						"CFA FreeTail is successfully completed and page having text You’re almost set!",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the CFA FreeTail Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
			ScrollingWebPage.PageScrolldown(driver,0,600,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.ClickonCPAProduct();
			Thread.sleep(1000);
			WEL.ClickOnFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC22", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC22", "WEL_Test_Data", "Last_Name"));
			WEL.EnterFreeTrailEmail(excelOperation.getTestData("TC22", "WEL_Test_Data", "Email_Address"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailCountry(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_Country"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailState(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_State"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailPassword(excelOperation.getTestData("TC22", "WEL_Test_Data", "Password"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailSignUpCheckBox();
			WEL.FreeTrailSignInButton();
			WEL.FreeTraiModelPopUpLoginButton();
			WEL.EnterFreeTrailUserNameOnLoginPage(excelOperation.getTestData("TC22", "WEL_Test_Data", "Email_Address"));
			WEL.EnterFreeTrailPasswordOnLoginPage(excelOperation.getTestData("TC22", "WEL_Test_Data", "Password"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTraiModelPopUpSubmitButton();
			WEL.FreeTrailCountry(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_Country"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailState(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_State"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailSignUpCheckBox();
			Thread.sleep(1000);
			WEL.FreeTrailSignInButton();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
				ScrollingWebPage.PageScrollUp(driver, 0, -300,SS_path);	
				WEL.FreeTrailWELIcon();
				Thread.sleep(1000);
				ScrollingWebPage.PageScrolldown(driver,0,600,SS_path);
				WEL.ClickonCMAProduct();
				WEL.ClickOnFreeTrail();
				WEL.FreeTrailCountry(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_Country"));
				ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
				// ScrollingWebPage.PageDown(driver);
				WEL.FreeTrailState(excelOperation.getTestData("TC22", "WEL_Test_Data", "Sh_State"));
				ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
				// ScrollingWebPage.PageDown(driver);
				WEL.FreeTrailTermsAndCOndtionsCheckBox();
				ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
				// ScrollingWebPage.PageDown(driver);
				WEL.FreeTrailSignUpCheckBox();
				Thread.sleep(1000);
				WEL.FreeTrailSignInButton();
			}catch(Exception e) {
				Reporting.updateTestReport("The help button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC23_PlaceOrder_OfOther_ProductsCIA_FreeTrailFor_NewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC23_PlaceOrder_OfOther_ProductsCIA_FreeTrailFor_NewUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.ClickOnCIAProduct();
			Thread.sleep(1000);
			WEL.ClickOnCIAFreeTrail();
			WEL.FreeTrailFirstName(excelOperation.getTestData("TC23", "WEL_Test_Data", "First_Name"));
			WEL.FreeTrailLastName(excelOperation.getTestData("TC23", "WEL_Test_Data", "Last_Name"));
			WEL.EnterFreeTrailNewtUser();
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailCountry(excelOperation.getTestData("TC23", "WEL_Test_Data", "Sh_Country"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailState(excelOperation.getTestData("TC23", "WEL_Test_Data", "Sh_State"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailPassword(excelOperation.getTestData("TC23", "WEL_Test_Data", "Password"));
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
			// ScrollingWebPage.PageDown(driver);
			WEL.FreeTrailTermsAndCOndtionsCheckBox();
			ScrollingWebPage.PageScrolldown(driver,0,200,SS_path);
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
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC24_PlaceOrder_UsingSavedShippingAddress_ForExistingUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC24_PlaceOrder_UsingSavedShippingAddress_ForExistingUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageScrolldown(driver,0,600,SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			ScrollingWebPage.PageScrolldown(driver,0,400,SS_path);
			ScrollingWebPage.PageDown(driver, SS_path);
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
			try {
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
				// JS.executeScript("window.scrollBy(0,600)");
				ScrollingWebPage.PageDown(driver, SS_path);
				ScrollingWebPage.PageDown(driver, SS_path);
				String tax = WEL.fetchTaxAmount();
				excelOperation.updateTestData("TC16", "WEL_Test_Data", "Tax", tax);
				String orderTotal = WEL.fetchOrderTotal();
				excelOperation.updateTestData("TC16", "WEL_Test_Data", "Order_Total", orderTotal);
				Thread.sleep(2000);
				ScrollingWebPage.PageScrollUp(driver, 0, -300,SS_path);	
				Thread.sleep(2000);
				WEL.ClickOnWELIconOrderConfirmationPage();
				Thread.sleep(2000);
				WEL.ClickOnAccountIconFromOrderConfirmationPage();
			}
			catch(Exception e) {
				Reporting.updateTestReport("The name field in card information step was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC25_ShippingMethodfor_USUKAustralia_CanadaIndiaSingapore() throws IOException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

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
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnAddNewAddressButton();
			String countries = excelOperation.getTestData("TC25", "WEL_Test_Data", "Shipping_Countries");
			//String country1 = countries.substring(0, 13);
			String words[] = countries.split(",");
			
			for (int i = 0; i < words.length; i++) {
				
				WEL.selectShipCountry(words[i]);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
				//ScrollingWebPage.PageScrolldown(driver, 0,140, SS_path);
				ScrollingWebPage.PageDown(driver, SS_path);
				WEL.ShippingMethodForUK();
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
			WEL.selectShipCountry(excelOperation.getTestData("TC25", "WEL_Test_Data", "Sh_Country3"));
			String canadaone = WEL.ShippingMethodOneForCanada();
			if (canadaone.equals("$5.00"))
				Reporting.updateTestReport("The Shipping method for Canada is $5.00",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Shipping method", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			String canadatwo = WEL.ShippingMethodTwoForCanada();
			if (canadatwo.equals("$12.00"))
				Reporting.updateTestReport("The Shipping method for Canada is $12.00",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Shipping method", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			WEL.selectShipCountry(excelOperation.getTestData("TC25", "WEL_Test_Data", "Sh_Country4"));
			String SingaporeShipmethod = WEL.ShippingMethodForSingapore();
			Thread.sleep(2000);
			if (SingaporeShipmethod.equals("$40.00"))
				Reporting.updateTestReport("The Shipping method for Singapore is $40",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Shipping method", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);

			WEL.selectShipCountry(excelOperation.getTestData("TC25", "WEL_Test_Data", "Sh_Country5"));
			String IndiaShipmethod = WEL.ShippingMethodForIndia();
			Thread.sleep(2000);
			if (IndiaShipmethod.equals("$40.00"))
				Reporting.updateTestReport("The Shipping method for India is $40",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Shipping method", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			ScrollingWebPage.PageScrollUp(driver, 0, -300,SS_path);
			WEL.ClickOnBackTOCart();
			WEL.ClickOnRemoveOnCartPage();
			WEL.ClickAccountCartPage();
			WEL.ClickonSignOut();
		} 
			
		}	catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
		
	}		

	@Test
	public void TC31_ShippingAndBilling_AddressSamefor_NewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC31_ShippingAndBilling_AddressSamefor_NewUser");
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
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
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
							WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC15", "WEL_Test_Data", "Expiry_Month"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
							WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC31", "WEL_Test_Data", "Expiry_Year"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
							WEL.enterCVV_Number(excelOperation.getTestData("TC15", "WEL_Test_Data", "CVV"));
							driver.switchTo().defaultContent();
							WEL.SaveAndContinueCheckOut();
							ScrollingWebPage.PageScrollUp(driver, 0, -300,SS_path);
							WEL.ClickOnBackTOCart();
							WEL.ClickOnRemoveOnCartPage();
						}
						catch(Exception e) {
							Reporting.updateTestReport("The name field in card information step was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}


			// WEL.selectShipCountry();
			/*
			 * String reversedString = ""; for (int i = 0; i < words.length; i++) { if (i ==
			 * words.length) reversedString = words[i] + reversedString; else reversedString
			 * = " " + words[i] + reversedString; System.out.println(reversedString);
			 * WEL.selectShipCountries(reversedString); }
			 * System.out.print("Reversed string : " + reversedString);
			 * 
			 * WEL.selectShipCountry(excelOperation.getTestData("TC25", "WEL_Test_Data",
			 * "Sh_Country1")); String UKShipmethod = WEL.ShippingMethodForUK();
			 * System.out.println(UKShipmethod); Thread.sleep(2000); if
			 * (UKShipmethod.equals("$40.00"))
			 * Reporting.updateTestReport("The Shipping method for UK is $40",
			 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); else
			 * Reporting.updateTestReport("Failed to Shipping method",
			 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			 * WEL.selectShipCountry(excelOperation.getTestData("TC25", "WEL_Test_Data",
			 * "Sh_Country2")); String AusShipmethod = WEL.ShippingMethodForUK();
			 * System.out.println(AusShipmethod); Thread.sleep(2000); if
			 * (AusShipmethod.equals("$40.00"))
			 * Reporting.updateTestReport("The Shipping method for Australia is $40",
			 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); else
			 * Reporting.updateTestReport("Failed to Shipping method",
			 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			 * WEL.selectShipCountry(excelOperation.getTestData("TC25", "WEL_Test_Data",
			 * "Sh_Country3")); String canadaone = WEL.ShippingMethodOneForCanada(); if
			 * (canadaone.equals("$5.00"))
			 * Reporting.updateTestReport("The Shipping method for Canada is $5.00",
			 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); else
			 * Reporting.updateTestReport("Failed to Shipping method",
			 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL); String
			 * canadatwo = WEL.ShippingMethodTwoForCanada(); if (canadatwo.equals("$12.00"))
			 * Reporting.updateTestReport("The Shipping method for Canada is $12.00",
			 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); else
			 * Reporting.updateTestReport("Failed to Shipping method",
			 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			 * WEL.selectShipCountry(excelOperation.getTestData("TC25", "WEL_Test_Data",
			 * "Sh_Country4")); String SingaporeShipmethod =
			 * WEL.ShippingMethodForSingapore(); Thread.sleep(2000); if
			 * (SingaporeShipmethod.equals("$40.00"))
			 * Reporting.updateTestReport("The Shipping method for Singapore is $40",
			 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); else
			 * Reporting.updateTestReport("Failed to Shipping method",
			 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			 * 
			 * WEL.selectShipCountry(excelOperation.getTestData("TC25", "WEL_Test_Data",
			 * "Sh_Country5")); String IndiaShipmethod = WEL.ShippingMethodForIndia();
			 * Thread.sleep(2000); if (IndiaShipmethod.equals("$40.00"))
			 * Reporting.updateTestReport("The Shipping method for India is $40",
			 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS); else
			 * Reporting.updateTestReport("Failed to Shipping method",
			 * CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			 * ScrollingWebPage.PageScrollUp(driver, 0, -300, SS_path);
			 * 
			 */ /*
				 * WEL.ClickOnBackTOCart(); WEL.ClickOnRemoveOnCartPage();
				 * WEL.ClickAccountCartPage(); WEL.ClickonSignOut();
				 */
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	@Test
	public void TC32_ShippingAndBilling_AddressDifferentfor_NewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC32_ShippingAndBilling_AddressDifferentfor_NewUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
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
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
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
						WEL.ShipState(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_State"));

						WEL.ShipPhoneNumber(excelOperation.getTestData("TC15", "WEL_Test_Data", "Sh_Phone_Number"));
						WEL.ShipSaveAndContinueButton();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
							ScrollingWebPage.PageScrolldown(driver, 0, 500,SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@id='sameAsBillingLabel']")));				
								WEL.ShipAndBillAddressSection();
								WEL.selectCountry(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Country"));
								WEL.AddressLineOne(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Address_line1"));
								WEL.EnterZipecode(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Zip_Code"));
								WEL.EnterCity(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_City/ Province"));
								WEL.EnterPhoneNumber(excelOperation.getTestData("TC03", "WEL_Test_Data", "Bill_Phone_Number"));
							} catch (Exception e) {
								Reporting.updateTestReport("Shipping same as billing was not clickable and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport("The help button was not visible and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			} 

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}
	@Test
	public void TC27_AddressSuggestion_ByAddressDoctorInShippingpage() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			Reporting.test = Reporting.extent.createTest("TC27_AddressSuggestion_ByAddressDoctorInShippingpage");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver,SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver,SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver,SS_path);
						WEL.ClickOnCIAAddProduct();
						WEL.ClickonCheckOutOnCartPage();
						WEL.EnterexistingUserName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Email_Address"));
						WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC16", "WEL_Test_Data", "Password"));
						WEL.ClickonLoginAndContinue();
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
						}
						WEL.ClickOnBackTOCart();
						WEL.ClickOnRemoveOnCartPage();
						WEL.ClickAccountCartPage();
						WEL.ClickonSignOut();
					}
					catch(Exception e) {
						Reporting.updateTestReport("Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);


		}
	}

	@Test
	public void TC33_AddressSuggestion_ByAddressDoctorInBillingpage() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			Reporting.test = Reporting.extent.createTest("TC33_AddressSuggestion_ByAddressDoctorInBillingpage");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver,SS_path);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			// JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageDown(driver,SS_path);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			// JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageDown(driver,SS_path);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
			WEL.ClickonCheckOutOnCartPage();
			WEL.EnterexistingUserName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Email_Address"));
			WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC16", "WEL_Test_Data", "Password"));
			WEL.ClickonLoginAndContinue();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickonCheckOutOnCartPage();
			WEL.ClickOnEnterNewAddressButtonOnBillingPage();
			WEL.GuestFirstName(excelOperation.getTestData("TC33", "WEL_Test_Data", "Guest_Fname"));
			WEL.GuestLastName(excelOperation.getTestData("TC33", "WEL_Test_Data", "Guest_Lname"));
			WEL.selectCountry(excelOperation.getTestData("TC33", "WEL_Test_Data", "Bill_Country"));
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
				WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC33", "WEL_Test_Data", "Expiry_Month"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
				WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC33", "WEL_Test_Data", "Expiry_Year"));
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
					}
					WEL.ClickOnBackTOCart();
					WEL.ClickOnRemoveOnCartPage();
					WEL.ClickAccountCartPage();
					WEL.ClickonSignOut();
				}
				catch(Exception e) {
					Reporting.updateTestReport("Help button was not visible and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("The name field in card information step was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	@Test
	public void TC30_PlaceOrder_UsingSavedBillingAddress_ForExistingUser() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			Reporting.test = Reporting.extent.createTest("TC30_PlaceOrder_UsingSavedBillingAddress_ForExistingUser");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver,SS_path);
			WEL.ClickonCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonExploreCourseCMAProduct();
			Thread.sleep(3000);
			// JS.executeScript("window.scrollBy(0,300)");
			ScrollingWebPage.PageDown(driver,SS_path);
			WEL.ClickonViewCourseForCMAProduct();
			Thread.sleep(2000);
			WEL.ClickonCMAeBook();
			Thread.sleep(2000);
			// JS.executeScript("window.scrollBy(0,400)");
			ScrollingWebPage.PageDown(driver,SS_path);
			Thread.sleep(1000);
			WEL.ClickonCMAAddToCart();
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
				WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC30", "WEL_Test_Data", "Expiry_Month"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
				WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC30", "WEL_Test_Data", "Expiry_Year"));
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
				WEL.enterCVV_Number(excelOperation.getTestData("TC30", "WEL_Test_Data", "CVV"));
				driver.switchTo().defaultContent();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
					WEL.SaveAndContinueCheckOut();
					WEL.clickOnPlaceOrderButton();
					String orderID = WEL.fetchOrderId();
					ScrollingWebPage.PageScrolldown(driver, 0, 800,SS_path);
					excelOperation.updateTestData("TC30", "WEL_Test_Data", "Order_Id", orderID);
					String tax = WEL.fetchTaxValue();
					excelOperation.updateTestData("TC30", "WEL_Test_Data", "Tax", tax);
					String orderTotal = WEL.fetchOrderTotal();
					excelOperation.updateTestData("TC30", "WEL_Test_Data", "Order_Total", orderTotal);
					Thread.sleep(2000);
					ScrollingWebPage.PageScrollUp(driver, 0, -300,SS_path);
					Thread.sleep(2000);
					WEL.ClickOnWELIconOrderConfirmationPage();
					Thread.sleep(2000);
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
						WEL.ClickOnAccountIconFromOrderConfirmationPage();
						WEL.ClickonSignOut();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
						}
						catch(Exception e) {
							Reporting.updateTestReport("Help button was not visible and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Help button was not visible and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Help button was not visible and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("The name field in card information step was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}


	@Test
	public void TC50_PlaceOrder_CanadaAddress() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			Reporting.test = Reporting.extent.createTest("TC50_PlaceOrder_CanadaAddress");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						WEL.ClickonCheckOutOnCartPage();
						WEL.EnterexistingUserName(excelOperation.getTestData("TC16", "WEL_Test_Data", "Email_Address"));
						WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC16", "WEL_Test_Data", "Password"));
						WEL.ClickonLoginAndContinue();
						WEL.ClickonCheckOutOnCartPage();
						WEL.ClickonCheckOutOnCartPage();
						WEL.ClickOnEnterNewAddressButtonOnShippingPage();
						WEL.GuestFirstName(excelOperation.getTestData("TC50", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC50", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC50", "WEL_Test_Data", "Sh_Country"));
						Thread.sleep(2000);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
								WEL.ShipAddressLineOne(excelOperation.getTestData("TC50", "WEL_Test_Data", "Sh_Address_line1"));
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
									WEL.ShipPostCode(excelOperation.getTestData("TC50", "WEL_Test_Data", "Sh_Zip_Code"));
									/*
									 * WEL.ShipTownIndiaCity(excelOperation.getTestData("TC50", "WEL_Test_Data",
									 * "Sh_City/ Province"));
									 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
									 * "//input[@id='address.region']")));
									 * WEL.ShipState(excelOperation.getTestData("TC50", "WEL_Test_Data",
									 * "Sh_State"));
									 */WEL.ShipPhoneNumber(excelOperation.getTestData("TC50", "WEL_Test_Data", "Sh_Phone_Number"));
									 WEL.ClickingOnSaveAndContinue();
									 try {
										 if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
											 WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
									 } catch (Exception e) {

									 }
									 driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
									 try {
										 wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
										 WEL.enterCardHolderName(excelOperation.getTestData("TC30", "WEL_Test_Data", "Guest_Fname"));
										 driver.switchTo().defaultContent();
										 driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
										 WEL.enterCardNumber(excelOperation.getTestData("TC30", "WEL_Test_Data", "Card_Number"));
										 driver.switchTo().defaultContent();
										 driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
										 WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC30", "WEL_Test_Data", "Expiry_Month"));
										 driver.switchTo().defaultContent();
										 driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
										 WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC30", "WEL_Test_Data", "Expiry_Year"));
										 driver.switchTo().defaultContent();
										 driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
										 WEL.enterCVV_Number(excelOperation.getTestData("TC30", "WEL_Test_Data", "CVV"));
										 driver.switchTo().defaultContent();
										 try {
											 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
											 WEL.SaveAndContinueCheckOut();
											 WEL.clickOnPlaceOrderButton();
											 String orderID = WEL.fetchOrderId();
											 ScrollingWebPage.PageScrolldown(driver, 0, 800,SS_path);
											 excelOperation.updateTestData("TC50", "WEL_Test_Data", "Order_Id", orderID);
											 String tax = WEL.fetchTaxValue();
											 excelOperation.updateTestData("TC50", "WEL_Test_Data", "Tax", tax);
											 String orderTotal = WEL.fetchOrderTotal();
											 excelOperation.updateTestData("TC50", "WEL_Test_Data", "Order_Total", orderTotal);
											 Thread.sleep(2000);
											 ScrollingWebPage.PageScrollUp(driver, 0, -300,SS_path);
											 Thread.sleep(2000);
											 WEL.ClickOnWELIconOrderConfirmationPage();
											 Thread.sleep(2000);
											 try {
												 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
												 WEL.ClickOnAccountIconFromOrderConfirmationPage();
												 WEL.ClickonSignOut();
												 try {
													 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
												 }
												 catch(Exception e) {
													 Reporting.updateTestReport("Help button was not visible and caused timeout exception",
															 CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
												 }
											 }
											 catch(Exception e) {
												 Reporting.updateTestReport("Help button was not visible and caused timeout exception",
														 CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											 }
										 }
										 catch(Exception e) {
											 Reporting.updateTestReport("Help button was not visible and caused timeout exception",
													 CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										 }
									 }
									 catch(Exception e) {
										 Reporting.updateTestReport("The name field in card information step was not clickable and caused timeout exception",
												 CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									 }
								}
								catch(Exception e) {
									Reporting.updateTestReport("Help button was not visible and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("Help button was not visible and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			}
			catch(Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	@Test
	public void TC51_PlaceOrder_IndiaAddress() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			Reporting.test = Reporting.extent.createTest("TC51_PlaceOrder_IndiaAddress");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						WEL.ClickonCheckOutOnCartPage();
						WEL.EnterexistingUserName(excelOperation.getTestData("TC51", "WEL_Test_Data", "Email_Address"));
						WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC51", "WEL_Test_Data", "Password"));
						WEL.ClickonLoginAndContinue();
						WEL.ClickonCheckOutOnCartPage();
						WEL.ClickonCheckOutOnCartPage();
						WEL.ClickOnEnterNewAddressButtonOnShippingPage();
						WEL.GuestFirstName(excelOperation.getTestData("TC51", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC51", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC51", "WEL_Test_Data", "Sh_Country"));
						Thread.sleep(3000);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
								WEL.ShipAddressLineOne(excelOperation.getTestData("TC51", "WEL_Test_Data", "Sh_Address_line1"));
								WEL.ShipPostCode(excelOperation.getTestData("TC51", "WEL_Test_Data", "Sh_Zip_Code"));
								// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='city']")));
								WEL.ShipTownIndiaCity(excelOperation.getTestData("TC51", "WEL_Test_Data", "Sh_City/ Province"));
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='address.region']")));
									WEL.enterState(excelOperation.getTestData("TC51", "WEL_Test_Data", "Sh_State"));
									WEL.ShipPhoneNumber(excelOperation.getTestData("TC51", "WEL_Test_Data", "Sh_Phone_Number"));
									WEL.ClickingOnSaveAndContinue();
									try {
										if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
											WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
									} catch (Exception e) {
									}
									driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
										WEL.enterCardHolderName(excelOperation.getTestData("TC51", "WEL_Test_Data", "Guest_Fname"));
										driver.switchTo().defaultContent();
										driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
										WEL.enterCardNumber(excelOperation.getTestData("TC51", "WEL_Test_Data", "Card_Number"));
										driver.switchTo().defaultContent();
										driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
										WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC51", "WEL_Test_Data", "Expiry_Month"));
										driver.switchTo().defaultContent();
										driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
										WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC51", "WEL_Test_Data", "Expiry_Year"));
										driver.switchTo().defaultContent();
										driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
										WEL.enterCVV_Number(excelOperation.getTestData("TC51", "WEL_Test_Data", "CVV"));
										driver.switchTo().defaultContent();
										try {
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
											WEL.SaveAndContinueCheckOut();
											WEL.clickOnPlaceOrderButton();
											String orderID = WEL.fetchOrderId();
											ScrollingWebPage.PageScrolldown(driver, 0, 800,SS_path);
											excelOperation.updateTestData("TC51", "WEL_Test_Data", "Order_Id", orderID);
											String tax = WEL.fetchTaxValue();
											excelOperation.updateTestData("TC51", "WEL_Test_Data", "Tax", tax);
											String orderTotal = WEL.fetchOrderTotal();
											excelOperation.updateTestData("TC51", "WEL_Test_Data", "Order_Total", orderTotal);
											Thread.sleep(2000);
											ScrollingWebPage.PageScrollUp(driver, 0, -300,SS_path);
											Thread.sleep(2000);
											WEL.ClickOnWELIconOrderConfirmationPage();
											Thread.sleep(2000);
											try {
												wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
												WEL.ClickOnAccountIconFromOrderConfirmationPage();
												WEL.ClickonSignOut();
												try {
													wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
												}
												catch(Exception e) {
													Reporting.updateTestReport("Help button was not visible and caused timeout exception",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
												}
											}
											catch(Exception e) {
												Reporting.updateTestReport("Help button was not visible and caused timeout exception",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										catch(Exception e) {
											Reporting.updateTestReport("Help button was not visible and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									}
									catch(Exception e) {
										Reporting.updateTestReport("The name field in card information step was not clickable and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								catch(Exception e) {
									Reporting.updateTestReport("State field was not visible and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("Help button was not visible and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}

	}

	@Test
	public void TC52_PlaceOrder_ChinaAddress() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			Reporting.test = Reporting.extent.createTest("TC52_PlaceOrder_ChinaAddress");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						WEL.ClickonCheckOutOnCartPage();
						WEL.EnterexistingUserName(excelOperation.getTestData("TC52", "WEL_Test_Data", "Email_Address"));
						WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC52", "WEL_Test_Data", "Password"));
						WEL.ClickonLoginAndContinue();
						WEL.ClickonCheckOutOnCartPage();
						WEL.ClickonCheckOutOnCartPage();
						WEL.ClickOnEnterNewAddressButtonOnShippingPage();
						WEL.GuestFirstName(excelOperation.getTestData("TC52", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC52", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC52", "WEL_Test_Data", "Sh_Country"));
						Thread.sleep(3000);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							WEL.ShipAddressLineOne(excelOperation.getTestData("TC52", "WEL_Test_Data", "Sh_Address_line1"));
							WEL.ShipPostCode(excelOperation.getTestData("TC52", "WEL_Test_Data", "Sh_Zip_Code"));
							// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='city']")));
							WEL.ShipTownIndiaCity(excelOperation.getTestData("TC52", "WEL_Test_Data", "Sh_City/ Province"));
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='address.region']")));
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
									WEL.enterState(excelOperation.getTestData("TC52", "WEL_Test_Data", "Sh_State"));
									WEL.ShipPhoneNumber(excelOperation.getTestData("TC52", "WEL_Test_Data", "Sh_Phone_Number"));
									WEL.ClickingOnSaveAndContinue();
									try {
										if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
											WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
									} catch (Exception e) {
										Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
									driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
										WEL.enterCardHolderName(excelOperation.getTestData("TC52", "WEL_Test_Data", "Guest_Fname"));
										driver.switchTo().defaultContent();
										driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
										WEL.enterCardNumber(excelOperation.getTestData("TC52", "WEL_Test_Data", "Card_Number"));
										driver.switchTo().defaultContent();
										driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
										WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC52", "WEL_Test_Data", "Expiry_Month"));
										driver.switchTo().defaultContent();
										driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
										WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC52", "WEL_Test_Data", "Expiry_Year"));
										driver.switchTo().defaultContent();
										driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
										WEL.enterCVV_Number(excelOperation.getTestData("TC52", "WEL_Test_Data", "CVV"));
										driver.switchTo().defaultContent();
										try {
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
											WEL.SaveAndContinueCheckOut();
											WEL.clickOnPlaceOrderButton();
											String orderID = WEL.fetchOrderId();
											ScrollingWebPage.PageScrolldown(driver, 0, 800,SS_path);
											excelOperation.updateTestData("TC52", "WEL_Test_Data", "Order_Id", orderID);
											String tax = WEL.fetchTaxValue();
											excelOperation.updateTestData("TC52", "WEL_Test_Data", "Tax", tax);
											String orderTotal = WEL.fetchOrderTotal();
											excelOperation.updateTestData("TC52", "WEL_Test_Data", "Order_Total", orderTotal);
											Thread.sleep(2000);
											ScrollingWebPage.PageScrollUp(driver, 0, -300,SS_path);
											Thread.sleep(2000);
											WEL.ClickOnWELIconOrderConfirmationPage();
											Thread.sleep(2000);
											try {
												wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
												WEL.ClickOnAccountIconFromOrderConfirmationPage();
												WEL.ClickonSignOut();
												try {
													wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
												}
												catch(Exception e) {
													Reporting.updateTestReport("Help button was not visible and caused timeout exception",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
												}
											}
											catch(Exception e) {
												Reporting.updateTestReport("Help button was not visible and caused timeout exception",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										catch(Exception e) {
											Reporting.updateTestReport("Help button was not visible and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									}
									catch(Exception e) {
										Reporting.updateTestReport("The name field in card information step was not clickable and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								catch(Exception e) {
									Reporting.updateTestReport("Help button was not visible and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("State field was not visible and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	@Test
	public void TC53_PlaceOrder_JapanAddress() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			Reporting.test = Reporting.extent.createTest("TC53_PlaceOrder_JapanAddress");
			driver.get(excelOperation.getTestData("WEL_Env_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			Thread.sleep(1000);
			ScrollingWebPage.PageDown(driver, SS_path);
			WEL.ClickOnCIAProduct();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'SHOP')]")));
				WEL.ClickOnShopCourseForCIAProduct();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@class='comparison-table-title-container header-sticky']//div[2]/a")));
					ScrollingWebPage.PageDown(driver, SS_path);
					WEL.ClickOnViewCourseForCIAProduct();
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						WEL.ClickOnCIAAddProduct();
						WEL.ClickonCheckOutOnCartPage();
						WEL.EnterexistingUserName(excelOperation.getTestData("TC53", "WEL_Test_Data", "Email_Address"));
						WEL.EnterPasswordLoginPage(excelOperation.getTestData("TC53", "WEL_Test_Data", "Password"));
						WEL.ClickonLoginAndContinue();
						WEL.ClickonCheckOutOnCartPage();
						WEL.ClickonCheckOutOnCartPage();
						WEL.ClickOnEnterNewAddressButtonOnShippingPage();
						WEL.GuestFirstName(excelOperation.getTestData("TC53", "WEL_Test_Data", "Guest_Fname"));
						WEL.GuestLastName(excelOperation.getTestData("TC53", "WEL_Test_Data", "Guest_Lname"));
						WEL.selectShipCountry(excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_Country"));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
							WEL.ShipAddressLineOne(excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_Address_line1"));
							WEL.ShipPostCode(excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_Zip_Code"));
							// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='city']")));
							WEL.ShipTownIndiaCity(excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_City/ Province"));
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='address.region']")));
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
									WEL.enterState(excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_State"));
									WEL.ShipPhoneNumber(excelOperation.getTestData("TC53", "WEL_Test_Data", "Sh_Phone_Number"));
									WEL.ClickingOnSaveAndContinue();
									try {
										if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
											WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
									} catch (Exception e) {
										Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
									driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
										WEL.enterCardHolderName(excelOperation.getTestData("TC53", "WEL_Test_Data", "Guest_Fname"));
										driver.switchTo().defaultContent();
										driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
										WEL.enterCardNumber(excelOperation.getTestData("TC53", "WEL_Test_Data", "Card_Number"));
										driver.switchTo().defaultContent();
										driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
										WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC53", "WEL_Test_Data", "Expiry_Month"));
										driver.switchTo().defaultContent();
										driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
										WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC53", "WEL_Test_Data", "Expiry_Year"));
										driver.switchTo().defaultContent();
										driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
										WEL.enterCVV_Number(excelOperation.getTestData("TC53", "WEL_Test_Data", "CVV"));
										driver.switchTo().defaultContent();
										try {
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
											WEL.SaveAndContinueCheckOut();
											WEL.clickOnPlaceOrderButton();
											String orderID = WEL.fetchOrderId();
											ScrollingWebPage.PageScrolldown(driver, 0, 800, SS_path);
											excelOperation.updateTestData("TC53", "WEL_Test_Data", "Order_Id", orderID);
											String tax = WEL.fetchTaxValue();
											excelOperation.updateTestData("TC53", "WEL_Test_Data", "Tax", tax);
											String orderTotal = WEL.fetchOrderTotal();
											excelOperation.updateTestData("TC53", "WEL_Test_Data", "Order_Total", orderTotal);
											Thread.sleep(2000);
											ScrollingWebPage.PageScrollUp(driver, 0, -300, SS_path);
											Thread.sleep(2000);
											WEL.ClickOnWELIconOrderConfirmationPage();
											Thread.sleep(2000);
											try {
												wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
												WEL.ClickOnAccountIconFromOrderConfirmationPage();
												WEL.ClickonSignOut();
												try {
													wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='helpButton']")));
												}
												catch(Exception e) {
													Reporting.updateTestReport("Help button was not visible and caused timeout exception",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
												}
											}
											catch(Exception e) {
												Reporting.updateTestReport("Help button was not visible and caused timeout exception",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										catch(Exception e) {
											Reporting.updateTestReport("Help button was not visible and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									}
									catch(Exception e) {
										Reporting.updateTestReport("The name field in card information step was not clickable and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								catch(Exception e) {
									Reporting.updateTestReport("Help button was not visible and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("State field was not visible and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("Address line 1 was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Add to cart button on CIA PDP was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("The View Course link on CIA PDP was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Shop button was not visible and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}
}