package TestSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjectRepo.app_VET_Repo;
import PageObjectRepo.app_VET_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.EmailValidation;
import utilities.LogTextFile;
import utilities.PaymentGateway;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class VET_Prod_Test_Suite extends DriverModule{
	app_VET_Repo VET;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	private static String VET_Login_URL=excelOperation.getTestData("VET_Login_URL", "Generic_Dataset", "Data");

	@BeforeTest
	public void initializeRepo() {
		VET = PageFactory.initElements(driver, app_VET_Repo.class);

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
	 * @Description: Opens the Home page and checks the content if everything is present or not
	 */
	@Test
	public void TC01_Open_Homepage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC01_Open_Homepage");
			LogTextFile.writeTestCaseStatus("TC01_Open_Homepage", "Test case");
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
			driver.get(excelOperation.getTestData("VET_Homepage_URL", "Generic_Dataset", "Data"));
			VET.checkHomePageTitle();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			VET.checkExploreMoreButtonInHomePage();
			VET.checkLoginButtonInHomeopage();
			ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated
						(By.xpath("//img[@src='/static/media/whitewiley.c5a4478a.png']")));
				VET.checkWileyLogoInHomepageFooter();
			}
			catch(Exception e) {
				Reporting.updateTestReport("Wiley Logo was not present in homepage footer and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Description: Creates one account in VET and logs in with that user
	 */
	@Test
	public void TC02_Register_a_New_User_And_Login() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC02_Register_a_New_User_And_Login");
			LogTextFile.writeTestCaseStatus("TC02_Register_a_New_User_And_Login", "Test case");
			driver.get(VET_Login_URL);
			VET.enterFirstName(excelOperation.getTestData("TC02", "VET_Test_Data", "First_Name"));
			VET.enterLastName(excelOperation.getTestData("TC02", "VET_Test_Data", "Last_Name"));
			String emailId = VET.enterEmailId();
			VET.enterPassword(excelOperation.getTestData("TC02", "VET_Test_Data", "Password"));
			VET.clickCreateAccountButton();
			String title = driver.getTitle().trim();
			if (title.equalsIgnoreCase("Edit Profile | VET online Site")) {
				Reporting.updateTestReport("Account was created successfully", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
				excelOperation.updateTestData("TC02", "VET_Test_Data", "Email_Id", emailId);
				excelOperation.updateTestData("TC03", "VET_Test_Data", "Email_Id", emailId);
				excelOperation.updateTestData("TC04", "VET_Test_Data", "Email_Id", emailId);
				excelOperation.updateTestData("TC05", "VET_Test_Data", "Email_Id", emailId);
				VET.logOut(driver);
				driver.get(VET_Login_URL);
				VET.enterExistingUserId(excelOperation.getTestData("TC02", "VET_Test_Data", "Email_Id"));
				VET.enterExistingUserPassword(excelOperation.getTestData("TC02", "VET_Test_Data", "Password"));
				VET.clickOnLoginButton();
				String newTitle = driver.getTitle().trim();
				if (newTitle.equalsIgnoreCase("Edit Profile | VET online Site")) {
					Reporting.updateTestReport("User was able to login successfully with the newly created account",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				} else {
					Reporting.updateTestReport("User was not able to login with the newly created account",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				VET.logOut(driver);
			} else {
				Reporting.updateTestReport("Account couldn't be  created", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
			

		}
		catch(Exception e) {
			Reporting.updateTestReport("Password reset was not successful with error message " + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Description: Validates the My Account page for VET (Reset password from my account)
	 */
	@Test
	public void TC03_Reset_Password_My_Account() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_Reset_Password_My_Account");
			LogTextFile.writeTestCaseStatus("TC03_Reset_Password_My_Account", "Test case");
			driver.get(VET_Login_URL);
			VET.enterExistingUserId(excelOperation.getTestData("TC03", "VET_Test_Data", "Email_Id"));
			VET.enterExistingUserPassword(excelOperation.getTestData("TC03", "VET_Test_Data", "Previous_Password"));
			VET.clickOnLoginButton();
			VET.isMyAccountPage();
			VET.clickEditPasswordPage();
			VET.isChangePasswordPage();
			VET.enterPreviousPassword(excelOperation.getTestData("TC03", "VET_Test_Data", "Previous_Password"));
			VET.enterNewPassword(excelOperation.getTestData("TC03", "VET_Test_Data", "Password"));
			VET.clickPasswordSaveButton();
			if (!VET.isPasswordResetSuccessMessagePresentInMyAccount().equalsIgnoreCase(""))
				Reporting.updateTestReport("Password reset was successful", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			else
				Reporting.updateTestReport("Password reset was not successful",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		    VET.logOut(driver);
			driver.get(VET_Login_URL);
			VET.enterExistingUserId(excelOperation.getTestData("TC03", "VET_Test_Data", "Email_Id"));
			VET.enterExistingUserPassword(excelOperation.getTestData("TC03", "VET_Test_Data", "Password"));
			VET.clickOnLoginButton();
			VET.isMyAccountPage();
			VET.logOut(driver);
		} catch (Exception e) {
			Reporting.updateTestReport("Password reset was not successful with error message " + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 31/03/23
	 * @Description: Reset the Password From Login Page through the forgot password link
	 */
	@Test
	public void TC04_Reset_Password_From_Login_Page() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC04_Reset_Password_From_Login_Page");
			LogTextFile.writeTestCaseStatus("TC04_Reset_Password_From_Login_Page", "Test case");
			driver.get(VET_Login_URL);
			VET.ForgotchangePassword();
			VET.RetriveLoginInfo(excelOperation.getTestData("TC04", "VET_Test_Data", "Email_Id"));
			VET.clickOnSubmit();
			VET.checkAlertMessageAfterSubmittingEmailId();
			driver.get(excelOperation.getTestData("Yopmail_URL",
					"Generic_Dataset", "Data"));
			VET.enterEmailIdInYopmail(excelOperation.getTestData("TC04", "VET_Test_Data","Email_Id"));
			VET.clickOnArrowButton();
			EmailValidation.forgotPasswordMailForVet(driver, SS_path, VET);
			driver.get(VET_Login_URL);
			VET.enterExistingUserId(excelOperation.getTestData("TC04", "VET_Test_Data", "Email_Id"));
			VET.enterExistingUserPassword(excelOperation.getTestData("TC04", "VET_Test_Data", "Password"));
			VET.clickOnLoginButton();
			VET.isMyAccountPage();
			VET.logOut(driver);

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}


	/*
	 * @Description: Edits the name in edit profile, my account section
	 */
	@Test
	public void TC05_Edit_Profile() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_Edit_Profile");
			LogTextFile.writeTestCaseStatus("TC05_Edit_Profile", "Test case");
			driver.get(VET_Login_URL);
			VET.enterExistingUserId(excelOperation.getTestData("TC05", "VET_Test_Data", "Email_Id"));
			VET.enterExistingUserPassword(excelOperation.getTestData("TC05", "VET_Test_Data", "Password"));
			VET.clickOnLoginButton();
			VET.isMyAccountPage();
			String editProfilePage=driver.getTitle().trim();
			if (editProfilePage.equalsIgnoreCase("Edit Profile | VET online Site")) {
				VET.editProfileLastName(excelOperation.getTestData("TC05", "VET_Test_Data", "Last_Name"));
				VET.clickOnMyAcountSaveButton();
				Thread.sleep(1000);
				VET.checkAlertMessageAfterUserDataUpdation();
			}
			else
				Reporting.updateTestReport("User was not on edit profile page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			VET.logOut(driver);

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

		}
	}

	/*
	 * @Description: User adds one VET Subscription to cart and proceeds upto the billing information page
	 */
	@Test
	public void TC06_Provide_Billing_Information() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC06_Provide_Billing_Information");
			LogTextFile.writeTestCaseStatus("TC06_Provide_Billing_Information", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("VET_Subscription_URL", "Generic_Dataset", "Data"));
			VET.clickOnYearlySubscriptionButton();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'My Cart')]")));
				System.out.println("Cart page came");
				VET.clickOnContinueButtonCartPage();
				try {
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//h1[contains(text(),"
									+ "'Create an Account or Login')]")));
					VET.enterFirstName(excelOperation.getTestData("TC06", "VET_Test_Data", "First_Name"));
					VET.enterLastName(excelOperation.getTestData("TC06", "VET_Test_Data", "Last_Name"));
					VET.enterEmailId();
					VET.enterPassword(excelOperation.getTestData("TC06", "VET_Test_Data", "Password"));
					VET.clickCreateAccountButton();
					try {
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//div[contains(text(),'Billing Address')]")));
						VET.enterBillingFirstName(excelOperation.getTestData("TC06", "VET_Test_Data", "First_Name"));
						VET.enterBillingLastName(excelOperation.getTestData("TC06", "VET_Test_Data", "Last_Name"));
						VET.selectCountry(excelOperation.getTestData("TC06", "VET_Test_Data", "Country"));
						try{
							wait.until(ExpectedConditions.elementToBeClickable(By.id("addressLine1")));
							VET.enterAddressLine1(excelOperation.getTestData("TC06", "VET_Test_Data", "Address_line1"));
							VET.enterCity(excelOperation.getTestData("TC06", "VET_Test_Data", "City"));
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'State')]")));
								VET.enterState(excelOperation.getTestData("TC06", "VET_Test_Data", "State"));
							}
							catch(Exception e){
								Reporting.updateTestReport("State field was not present for this country: "
										+excelOperation.getTestData("TC06", "VET_Test_Data", "Country"),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}
							VET.enterZip(excelOperation.getTestData("TC06", "VET_Test_Data", "Zip_Code"));
							VET.enterPhoneNumber(excelOperation.getTestData("TC06", "VET_Test_Data", "Phone_Number"));
							VET.checkBoxBilling();
							VET.continueToCardDetailsPage();
							PaymentGateway.paymentVET("WPS", driver, VET, "TC06", SS_path);
						}
						catch(Exception e) {
							Reporting.updateTestReport("Address line 1 was not loaded and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

						}
					} catch (Exception e) {
						Reporting.updateTestReport("Billing address page was not loaded and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

					}
				} catch (Exception e) {
					Reporting.updateTestReport("Create Account / Login page was not loaded and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Cart page was not loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			
		}
		catch(Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			
		}
	}
}
