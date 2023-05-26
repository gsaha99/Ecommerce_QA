package TestSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjectRepo.app_AGS_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.EmailValidation;
import utilities.LogTextFile;
import utilities.PaymentGateway;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class AGS_Node_Test_Suite extends DriverModule {
	app_AGS_Repo AGS;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	private static String AGS_Homepage_URL=excelOperation.getTestData("AGS_Homepage_URL", "Generic_Dataset", "Data");
	private static String AGS_Login_URL=excelOperation.getTestData("AGS_Login_URL", "Generic_Dataset", "Data");
	private static String AGS_Subscription_URL=excelOperation.getTestData("AGS_Subscription_URL", "Generic_Dataset", "Data");

	@BeforeTest
	public void initializeRepo() {
		AGS = PageFactory.initElements(driver, app_AGS_Repo.class);

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
			driver.get(AGS_Homepage_URL);
			AGS.checkHomePageTitle();
			AGS.checkSubScribeNowTabInHomePage();
			AGS.checkLoginTabInHomeopage();
			ScrollingWebPage.PageDown(driver, SS_path);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated
						(By.xpath("//img[@src='https://www.graphicstandards.com/wp-content/uploads/2016/03/logo.png']")));
				AGS.checkWileyLogoInHomepageFooter();
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
	 * @Description: Creates one account in AGS and logs in with that user
	 */
	@Test
	public void TC02_Register_a_New_User_And_Login() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC02_Register_a_New_User_And_Login");
			LogTextFile.writeTestCaseStatus("TC02_Register_a_New_User_And_Login", "Test case");
			driver.get(AGS_Login_URL);
			AGS.enterFirstName(excelOperation.getTestData("TC02", "AGS_Test_Data", "First_Name"));
			AGS.enterLastName(excelOperation.getTestData("TC02", "AGS_Test_Data", "Last_Name"));
			String emailId = AGS.enterEmailId();
			AGS.enterPassword(excelOperation.getTestData("TC02", "AGS_Test_Data", "Password"));
			AGS.clickCreateAccountButton();
			String title = driver.getTitle().trim();
			if (title.equalsIgnoreCase("Edit Profile | AGS Site")) {
				Reporting.updateTestReport("Account was created successfully", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
				excelOperation.updateTestData("TC02", "AGS_Test_Data", "Email_Id", emailId);
				excelOperation.updateTestData("TC03", "AGS_Test_Data", "Email_Id", emailId);
				excelOperation.updateTestData("TC04", "AGS_Test_Data", "Email_Id", emailId);
				excelOperation.updateTestData("TC05", "AGS_Test_Data", "Email_Id", emailId);
				AGS.logOut(driver);
				driver.get(AGS_Login_URL);
				AGS.enterExistingUserId(excelOperation.getTestData("TC02", "AGS_Test_Data", "Email_Id"));
				AGS.enterExistingUserPassword(excelOperation.getTestData("TC02", "AGS_Test_Data", "Password"));
				AGS.clickOnLoginButton();
				String newTitle = driver.getTitle().trim();
				if (newTitle.equalsIgnoreCase("Edit Profile | AGS Site")) {
					Reporting.updateTestReport("User was able to login successfully with the newly created account",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				} else {
					Reporting.updateTestReport("User was not able to login with the newly created account",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} else {
				Reporting.updateTestReport("Account couldn't be  created", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.FAIL);
			}
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		}
		catch(Exception e) {
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
			Reporting.updateTestReport("Password reset was not successful with error message " + e.getMessage(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Description: Validates the My Account page for AGS (Reset password from my account)
	 */
	@Test
	public void TC03_Reset_Password_My_Account() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_Reset_Password_My_Account");
			LogTextFile.writeTestCaseStatus("TC03_Reset_Password_My_Account", "Test case");
			driver.get(AGS_Login_URL);
			AGS.enterExistingUserId(excelOperation.getTestData("TC03", "AGS_Test_Data", "Email_Id"));
			AGS.enterExistingUserPassword(excelOperation.getTestData("TC03", "AGS_Test_Data", "Previous_Password"));
			AGS.clickOnLoginButton();
			AGS.isMyAccountPage();
			AGS.clickEditPasswordPage();
			AGS.isChangePasswordPage();
			AGS.enterPreviousPassword(excelOperation.getTestData("TC03", "AGS_Test_Data", "Previous_Password"));
			AGS.enterNewPassword(excelOperation.getTestData("TC03", "AGS_Test_Data", "Password"));
			AGS.clickPasswordSaveButton();
			if (!AGS.isPasswordResetSuccessMessagePresentInMyAccountPage().equalsIgnoreCase(""))
				Reporting.updateTestReport("Password reset was successful", CaptureScreenshot.getScreenshot(SS_path),
						StatusDetails.PASS);
			else
				Reporting.updateTestReport("Password reset was not successful",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
			driver.get(AGS_Login_URL);
			AGS.enterExistingUserId(excelOperation.getTestData("TC03", "AGS_Test_Data", "Email_Id"));
			AGS.enterExistingUserPassword(excelOperation.getTestData("TC03", "AGS_Test_Data", "Password"));
			AGS.clickOnLoginButton();
			AGS.isMyAccountPage();
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
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
			Reporting.test = Reporting.extent.createTest("TC04_ResetPasswordFromLoginPage");
			LogTextFile.writeTestCaseStatus("TC04_ResetPasswordFromLoginPage", "Test case");
			driver.get(AGS_Login_URL);
			AGS.clickOnForgotPassword();
			AGS.enterEmailIdToGetResetPasswordMail(excelOperation.getTestData("TC04", "AGS_Test_Data", "Email_Id"));
			AGS.clickOnSubmit();
			AGS.checkPasswordResetInstructionMessage();
			driver.get(excelOperation.getTestData("Yopmail_URL",
					"Generic_Dataset", "Data"));
			AGS.enterEmailIdInYopmail(excelOperation.getTestData("TC04", "AGS_Test_Data","Email_Id"));
			AGS.clickOnArrowButton();
			EmailValidation.forgotPasswordMailForAGS(driver, SS_path, AGS);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
			driver.get(AGS_Login_URL);
			AGS.enterExistingUserId(excelOperation.getTestData("TC04", "AGS_Test_Data", "Email_Id"));
			AGS.enterExistingUserPassword(excelOperation.getTestData("TC04", "AGS_Test_Data", "Password"));
			AGS.clickOnLoginButton();
			AGS.isMyAccountPage();
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}


	/*
	 * @Description: Edits the name in edit profile, in my account section
	 */
	@Test
	public void TC05_Edit_Profile() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_Edit_Profile");
			LogTextFile.writeTestCaseStatus("TC05_Edit_Profile", "Test case");
			driver.get(AGS_Login_URL);
			AGS.enterExistingUserId(excelOperation.getTestData("TC05", "AGS_Test_Data", "Email_Id"));
			AGS.enterExistingUserPassword(excelOperation.getTestData("TC05", "AGS_Test_Data", "Password"));
			AGS.clickOnLoginButton();
			AGS.isMyAccountPage();
			String editProfilePage=driver.getTitle().trim();
			if (editProfilePage.equalsIgnoreCase("Edit Profile | AGS Site")) {
				AGS.editProfileLastName(excelOperation.getTestData("TC05", "AGS_Test_Data", "Last_Name"));
				AGS.clickOnMyAcountSaveButton();
				Thread.sleep(1000);
				AGS.checkAlertMessageAfterUserDataUpdation();
			}
			else
				Reporting.updateTestReport("User was not on edit profile page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));

		}
	}
	
	/*
	 * @Description: User adds one subscription to cart and proceeds upto the billing information page
	 */
	@Test
	public void TC06_Provide_Billing_Information() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC06_Provide_File_Information");
			LogTextFile.writeTestCaseStatus("TC06_Provide_File_Information", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(AGS_Subscription_URL);
			AGS.clickOnYearlySubscriptionButton();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'My Cart')]")));
				System.out.println("Cart page came");
				AGS.clickOnContinueButtonCartPage();
				try {
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//h1[contains(text(),"
									+ "'Create an Account or Login')]")));
					AGS.enterFirstName(excelOperation.getTestData("TC06", "AGS_Test_Data", "First_Name"));
					AGS.enterLastName(excelOperation.getTestData("TC06", "AGS_Test_Data", "Last_Name"));
					AGS.enterEmailId();
					AGS.enterPassword(excelOperation.getTestData("TC06", "AGS_Test_Data", "Password"));
					AGS.clickCreateAccountButton();
					try {
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//div[contains(text(),'Billing Address')]")));
						AGS.enterBillingFirstName(excelOperation.getTestData("TC06", "AGS_Test_Data", "First_Name"));
						AGS.enterBillingLastName(excelOperation.getTestData("TC06", "AGS_Test_Data", "Last_Name"));
						AGS.selectCountry(excelOperation.getTestData("TC06", "AGS_Test_Data", "Country"));
						try{
							wait.until(ExpectedConditions.elementToBeClickable(By.id("addressLine1")));
							AGS.enterAddressLine1(excelOperation.getTestData("TC06", "AGS_Test_Data", "Address_line1"));
							AGS.enterCity(excelOperation.getTestData("TC06", "AGS_Test_Data", "City"));
							try {
								wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'State')]")));
								AGS.enterState(excelOperation.getTestData("TC06", "AGS_Test_Data", "State"));
							}
							catch(Exception e){
								Reporting.updateTestReport("State field was not present for this country: "
										+excelOperation.getTestData("TC06", "AGS_Test_Data", "Country"),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}
							AGS.enterZip(excelOperation.getTestData("TC06", "AGS_Test_Data", "Zip_Code"));
							AGS.enterPhoneNumber(excelOperation.getTestData("TC06", "AGS_Test_Data", "Phone_Number"));
							AGS.checkBoxBilling();
							AGS.continueToCardDetailsPage();
							PaymentGateway.paymentAGS("WPS", driver, AGS, "TC06", SS_path);
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
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		}
		catch(Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			AGS.logOutWithURL(driver, excelOperation.getTestData("AGS_Logout_URL", "Generic_Dataset", "Data"));
		}
	}


}
