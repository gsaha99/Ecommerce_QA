package TestSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjectRepo.AccountLoginPageObject;
import PageObjectRepo.CreateAccountPageObject;
import PageObjectRepo.LoginPageObject;
import PageObjectRepo.OrderDetailsPageObject;
import PageObjectRepo.SubscriptionAccountPageObject;
import PageObjectRepo.SubscriptionDetailsPageObject;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.LogTextFile;
import utilities.Reporting;
import utilities.StatusDetails;
import utilities.excelOperation;

/**
 * @author shprasad.
 * 
 *
 */

public class CoD_Regression_Test_Suite extends DriverModule {

	LoginPageObject loginPageObject;
	CreateAccountPageObject createAccountPageObject;
	SubscriptionAccountPageObject subscriptionAccountPageObject;
	AccountLoginPageObject accountLoginPageObject;
	OrderDetailsPageObject orderDetailsPageObject;
	SubscriptionDetailsPageObject subscriptionDetailsPageObject;

	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	String firstName = utilities.Helper.generateRandomAlphabets();
	String lastName = utilities.Helper.generateRandomAlphabets();
	String emailAddress = "Test" + utilities.Helper.generateRandomString() + "@mailinator.com";
	private static String common_Password = excelOperation.getTestData("Password", "CoD_Test_Data", "Test_Data");
	private static String cod_DEV_LoginURL = excelOperation.getTestData("Dev_URL", "CoD_Test_Data", "Test_Data");
	private static String cod_DEV_CreateAccount_URL = excelOperation.getTestData("Dev_CreateAccount_URL",
			"CoD_Test_Data", "Test_Data");
	private static String cod_QA_CreateAccount_URL = excelOperation.getTestData("QA_URL", "CoD_Test_Data", "Test_Data");
	private static String visa = excelOperation.getTestData("Visa", "Valid_Cards", "Test_Data");
	private static String secure_3d = excelOperation.getTestData("3d_card", "Valid_Cards", "Test_Data");
	private static String expiryDate = excelOperation.getTestData("expiryDate", "CoD_Test_Data", "Test_Data");
	private static String cvc = excelOperation.getTestData("completeCVV", "CoD_Test_Data", "Test_Data");
	private static String genericDecline = excelOperation.getTestData("genericDecline", "CoD_Test_Data", "Test_Data");
	private static String mastercard2series = excelOperation.getTestData("Mastercard_2series", "Valid_Cards",
			"Test_Data");
	private static String insufficientFundDecline = excelOperation.getTestData("insufficientFundDecline",
			"CoD_Test_Data", "Test_Data");
	private static String lostCardDecline = excelOperation.getTestData("lostCardDecline", "CoD_Test_Data", "Test_Data");
	private static String stolenCardDecline = excelOperation.getTestData("stolenCardDecline", "CoD_Test_Data",
			"Test_Data");
	private static String incorrectCVCDecline = excelOperation.getTestData("incorrectCVCDecline", "CoD_Test_Data",
			"Test_Data");
	private static String expiredCardDecline = excelOperation.getTestData("expiredCardDecline", "CoD_Test_Data",
			"Test_Data");
	private static String processingErrorDecline = excelOperation.getTestData("processingErrorDecline", "CoD_Test_Data",
			"Test_Data");
	private static String inValidFirstName = excelOperation.getTestData("inValidFirstName", "CoD_Test_Data",
			"Test_Data");
	private static String inValidLastName = excelOperation.getTestData("inValidLastName", "CoD_Test_Data", "Test_Data");
	private static String inValidEmail = excelOperation.getTestData("inValidEmail", "CoD_Test_Data", "Test_Data");
	private static String differentEmail = excelOperation.getTestData("differentEmail", "CoD_Test_Data", "Test_Data");
	private static String differentConfirmEmail = excelOperation.getTestData("differentConfirmEmail", "CoD_Test_Data",
			"Test_Data");
	private static String password = excelOperation.getTestData("password", "CoD_Test_Data", "Test_Data");
	private static String inValidPassword = excelOperation.getTestData("inValidPassword", "CoD_Test_Data", "Test_Data");
	private static String inValidConfirmPassword = excelOperation.getTestData("inValidConfirmPassword", "CoD_Test_Data",
			"Test_Data");
	private static String updateCardNumber = excelOperation.getTestData("updateCardNumber", "CoD_Test_Data",
			"Test_Data");
	private static String updateExpiryDate = excelOperation.getTestData("updateExpiryDate", "CoD_Test_Data",
			"Test_Data");
	private static String updateCVV = excelOperation.getTestData("updateCVV", "CoD_Test_Data", "Test_Data");
	private static String inValidCVV = excelOperation.getTestData("inValidCVV", "CoD_Test_Data", "Test_Data");
	private static String inValidExpiryDate = excelOperation.getTestData("inValidExpiryDate", "CoD_Test_Data",
			"Test_Data");
	private static String inCompleteCardNumber = excelOperation.getTestData("inCompleteCardNumber", "CoD_Test_Data",
			"Test_Data");
	private static String inValidCardNumber = excelOperation.getTestData("inValidCardNumber", "CoD_Test_Data",
			"Test_Data");
	private static String inCompleteExpiryDate = excelOperation.getTestData("inCompleteExpiryDate", "CoD_Test_Data",
			"Test_Data");
	private static String vat = excelOperation.getTestData("vat", "CoD_Test_Data", "Test_Data");
	private static String recurringMonthlyBill = excelOperation.getTestData("recurringMonthlyBill", "CoD_Test_Data",
			"Test_Data");
	private static String cardNumber3D_Insufficientfunds = excelOperation.getTestData("3d_CardInsufficientBalance", "CoD_Test_Data",
			"Test_Data");

	@BeforeTest
	public void initializeRepo() {
		loginPageObject = PageFactory.initElements(driver, LoginPageObject.class);
		createAccountPageObject = PageFactory.initElements(driver, CreateAccountPageObject.class);
		subscriptionAccountPageObject = PageFactory.initElements(driver, SubscriptionAccountPageObject.class);
		accountLoginPageObject = PageFactory.initElements(driver, AccountLoginPageObject.class);
		orderDetailsPageObject = PageFactory.initElements(driver, OrderDetailsPageObject.class);
		subscriptionDetailsPageObject = PageFactory.initElements(driver, SubscriptionDetailsPageObject.class);
	}

	@BeforeMethod
	public void nameBefore(Method method) {
		System.out.println("Test case: " + method.getName() + " execution started");
	}

	@AfterMethod
	public void nameAfter(Method method) {
		System.out.println("Test case: " + method.getName() + " execution completed");
	}

	@Test
	public void TC01_verifyRegistrationFromCheckoutPage() throws IOException, InterruptedException {
		try {
			Reporting.test = Reporting.extent.createTest("TC01_Registration_from_Checkout_Page");
			LogTextFile.writeTestCaseStatus("TC01_Registration_from_Checkout_Page", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				accountLoginPageObject.verifyAccountLoginPage();
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					createAccountPageObject.clickOnEditButton();
				} catch (Exception e) {
					Reporting.updateTestReport("User is not logged in" + e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Create new account link is not working" + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC02_verifyLoginFromCheckoutPage() throws IOException, InterruptedException {
		try {
			Reporting.test = Reporting.extent.createTest("TC02_Login_from_Checkout_Page");
			LogTextFile.writeTestCaseStatus("TC02_Login_from_Checkout_Page", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				accountLoginPageObject.veifyLoginButtonIsDisabled();
				accountLoginPageObject.enterEmailAddress(emailAddress);
				accountLoginPageObject.enterPassword(common_Password);
				accountLoginPageObject.clickOnLoginButton();
				accountLoginPageObject.verifySubscriptionAccountText();
				createAccountPageObject.clickOnEditButton();
			} catch (Exception e) {
				Reporting.updateTestReport("User is getting issue" + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test()
	public void TC03_verifyLoginFromLoginPage() throws IOException, InterruptedException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_Login_from_Login_Page");
			LogTextFile.writeTestCaseStatus("TC03_Login_from_Login_Page", "Test case");
			driver.get(cod_DEV_LoginURL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				loginPageObject.clickOnNavigationMenu();
				loginPageObject.selectLoginButton();
				try {
					loginPageObject.veifyLoginButtonIsDisabled();
					loginPageObject.enterEmailAddress();
					loginPageObject.enterPassword();
					loginPageObject.clickOnLoginButton();
					loginPageObject.clickOnNavigationMenu();
					loginPageObject.clickOnLogoutButton();
				} catch (Exception e) {
					Reporting.updateTestReport("Login button is not clicked" + e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Login button is not visible" + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC04_verifyRegistrationFieldValidationOnCheckout() throws IOException, InterruptedException {
		Reporting.test = Reporting.extent.createTest("TC04_verify_Registration_Field_Validation_On_Checkout");
		LogTextFile.writeTestCaseStatus("TC04_verify_Registration_Field_Validation_On_Checkout", "Test case");
		driver.get(cod_DEV_CreateAccount_URL);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		createAccountPageObject.clickOnCreateANewAccountLink();
		Assert.assertEquals(createAccountPageObject.getPageTextCreateAccount(), "Create Account");
		createAccountPageObject.enterFirstName("");
		createAccountPageObject.enterLastName("");
		createAccountPageObject.enterEmailAddress("");
		createAccountPageObject.enterConfirmEmailAddress("");
		createAccountPageObject.enterPassword("");
		createAccountPageObject.enterConfirmPassword("");
		Assert.assertEquals(createAccountPageObject.clickOnOffersCheckBox(), true, "Offers Checkbox is not visible");
		Assert.assertEquals(createAccountPageObject.verifyFirstNameFieldValidation(), "First Name is required.");
		Assert.assertEquals(createAccountPageObject.verifyLastNameFieldValidation(), "Last Name is required.");
		Assert.assertEquals(createAccountPageObject.verifyEmailFieldValidation(), "Email is required.");
		Assert.assertEquals(createAccountPageObject.verifyConfirmEmailFieldValidation(),
				"Confirmed email is required.");
		Assert.assertEquals(createAccountPageObject.verifyPasswordFieldValidation(), "Password is required.");
		Assert.assertEquals(createAccountPageObject.verifyConfirmPasswordFieldValidation(),
				"Confirmed password is required.");
		createAccountPageObject.enterFirstName(inValidFirstName);
		createAccountPageObject.enterLastName(inValidLastName);
		Assert.assertEquals(createAccountPageObject.verifyFirstNameFieldValidation(),
				"First Name must contain only letters, apostrophes or dashes.");
		Assert.assertEquals(createAccountPageObject.verifyLastNameFieldValidation(),
				"Last Name must contain only letters, apostrophes or dashes.");

		createAccountPageObject.enterEmailAddress(inValidEmail);
		createAccountPageObject.enterConfirmEmailAddress(inValidEmail);
		Assert.assertEquals(createAccountPageObject.verifyEmailFieldValidation(), "Email address is not valid.");
		Assert.assertEquals(createAccountPageObject.verifyConfirmEmailFieldValidation(),
				"Confirmed email address is not valid.");
		createAccountPageObject.enterEmailAddress(differentEmail);
		createAccountPageObject.enterConfirmEmailAddress(differentConfirmEmail);
		Assert.assertEquals(createAccountPageObject.verifyConfirmEmailFieldValidation(),
				"Your email addresses must match.");

		createAccountPageObject.enterPassword(inValidPassword);
		createAccountPageObject.enterConfirmPassword(inValidConfirmPassword);
		Assert.assertEquals(createAccountPageObject.verifyPasswordFieldValidation(),
				"Password does not meet all the requirements.");
		createAccountPageObject.enterPassword(password);
		createAccountPageObject.enterConfirmPassword(inValidConfirmPassword);
		Assert.assertEquals(createAccountPageObject.verifyConfirmPasswordFieldValidation(),
				"Your passwords do not match.");
	}

	@Test
	public void TC05_loginFormFieldValidationInLoginPage() throws IOException, InterruptedException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_LoginForm_Field_Validation_In_LoginPage");
			LogTextFile.writeTestCaseStatus("TC05_LoginForm_Field_Validation_In_LoginPage", "Test case");
			// driver.get(cod_DEV_LoginURL);
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				loginPageObject.clickOnNavigationMenu();
				loginPageObject.selectLoginButton();
				loginPageObject.verifyLoginPageText();
				try {
					loginPageObject.clickOnEmailInputField();
					loginPageObject.clickOnPasswordInputField();
					loginPageObject.verifyEmailAddressBlankMessageText();
					loginPageObject.clickOnEmailInputField();
					loginPageObject.verifyBlankPasswordFieldMessageText();
					/* Enter invalid details in Email address field */
					loginPageObject.enterSymbolsInEmailAddressField();
					loginPageObject.verifyInvalidEmailAddressMessageText();
					loginPageObject.enterAlphabetsInEmailAddressField();
					loginPageObject.verifyInvalidEmailAddressMessageText();
					loginPageObject.enterNumbersInEmailAddressField();
					loginPageObject.verifyInvalidEmailAddressMessageText();
					loginPageObject.enterSymbolsInPasswordField();
					/* Enter invalid details in password field */
					loginPageObject.verifyInvalidPasswordMessageText();
					loginPageObject.enterAlphabetsInPassswordField();
					loginPageObject.verifyInvalidPasswordMessageText();
					loginPageObject.enterNumbersInPasswordField();
					loginPageObject.verifyInvalidPasswordMessageText();
					/* Entering wrong email address and wrong password */
					loginPageObject.enterAlphabetsInEmailAddressField();
					loginPageObject.enterAlphabetsInPassswordField();
					loginPageObject.verifyInvalidEmailAddressMessageText();
					loginPageObject.verifyBlankPasswordFieldMessageText();
					loginPageObject.veifyLoginButtonIsDisabled();
					/* Entering correct email address but wrong password */
					loginPageObject.enterRandomGenerationEmailForEmailAddressField();
					loginPageObject.enterAlphabetsInPassswordField();
					loginPageObject.verifyBlankPasswordFieldMessageText();
					loginPageObject.veifyLoginButtonIsDisabled();
					/* Entering invalid email address but right password */
					loginPageObject.enterAlphabetsInEmailAddressField();
					loginPageObject.enterRandomStringForPasswordAddressField();
					loginPageObject.verifyInvalidEmailAddressMessageText();
					loginPageObject.veifyLoginButtonIsDisabled();
					/* Enter Invalid credentials */
					loginPageObject.enterRandomGenerationEmailForEmailAddressField();
					loginPageObject.enterRandomStringForPasswordAddressField();
					loginPageObject.clickOnLoginButton();
					loginPageObject.verifyInvalidCredentialMessageText();
				} catch (Exception e) {
					Reporting.updateTestReport("Invalid data message is not correct " + e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Login button is not visible " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test()
	public void TC07_billingInformationFieldsValidation() throws InterruptedException, IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC07_Billing_Information_Fields_Validation");
			LogTextFile.writeTestCaseStatus("TC07_Billing_Information_Fields_Validation", "Test case");
			driver.get(cod_DEV_LoginURL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				loginPageObject.clickOnNavigationMenu();
				loginPageObject.selectLoginButton();
				try {
					loginPageObject.enterEmailAddress();
					loginPageObject.enterPassword();
					loginPageObject.clickOnLoginButton();
					loginPageObject.clickOnTestSubscriptionDetailsSubscribeButon();
					subscriptionAccountPageObject.clickOnCreditCardInputBox();
					subscriptionAccountPageObject.clickOnAddressLine1InputBox();
					subscriptionAccountPageObject.verifyCreditCardErrorMessageForBlankField();
					subscriptionAccountPageObject.clickOnCityTownInputBox();
					subscriptionAccountPageObject.verifyAddressLine1RequiredErrorMessageForBlankField();
					subscriptionAccountPageObject.clickOnStateInputBox();
					subscriptionAccountPageObject.verifyCityTownErrorMessageForBlankField();
					subscriptionAccountPageObject.clickOnZipCodeInputBox();
					subscriptionAccountPageObject.clickOnPhoneNumberInputBox();
					subscriptionAccountPageObject.verifyZipCodeErrorMessageForBlankField();
					subscriptionAccountPageObject.clickOnZipCodeInputBox();
					subscriptionAccountPageObject.verifyPhoneNumberErrorMessageForBlankField();
					subscriptionAccountPageObject.enterSymbolsInCreditCardField();
					subscriptionAccountPageObject.verifyCreditCardErrorMessage();
					subscriptionAccountPageObject.enterNumbersInCreditCardField();
					// Assert.assertEquals(subscriptionAccountPageObject.verifyCreditCardErrorMessage(),"First
					// Name must contain only letters, apostrophes or dashes.");
					subscriptionAccountPageObject.enterAlphaNumericInCreditCardField();
					// Assert.assertEquals(subscriptionAccountPageObject.verifyCreditCardErrorMessage(),"First
					// Name must contain only letters, apostrophes or dashes.");
					subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
					subscriptionAccountPageObject.enterThreeCharInAddressLine1Field();
					subscriptionAccountPageObject.verifyAddressLine1ErrorMessage();
					subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
					subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
					subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
					subscriptionAccountPageObject.enterInvalidNumbersInPhoneNumberField();
					subscriptionAccountPageObject.verifyPhoneNumberErrorMessage();
					createAccountPageObject.clickOnEditButton();
				} catch (Exception e) {
					Reporting.updateTestReport(
							"Billing address testing  for invalid data is failed " + e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Login button is not visible " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC09_verifyPriceOnOrderSummary() throws IOException, InterruptedException, Exception {
		try {
			Reporting.test = Reporting.extent.createTest("TC09_verifyPriceOnOrderSummary");
			LogTextFile.writeTestCaseStatus("TC09_verifyPriceOnOrderSummary", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				accountLoginPageObject.verifyAccountLoginPage();
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
				} catch (Exception e) {
					Reporting.updateTestReport("User is not logged in" + e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Create new account link is not working" + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
			subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
			subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
			subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
			subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
			subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
			subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
			subscriptionAccountPageObject.clickOnSecurePaymentButton();
			Assert.assertEquals(subscriptionAccountPageObject.getVatValue().contains(vat), true,
					"Vat value is not visible");
			Assert.assertEquals(
					subscriptionAccountPageObject.getRecurringMonthlyBillValue().contains(recurringMonthlyBill), true,
					"Recurring Monthly Bill value is not visible");
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC11_orderPlacement() throws InterruptedException, IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC11_Order_Placement");
			LogTextFile.writeTestCaseStatus("TC11_Order_Placement", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, common_Password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
						// subscriptionAccountPageObject.clickOnAddressLine2Field();
						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						Assert.assertEquals(subscriptionAccountPageObject.isSecurePaymentButtonEnabled(), true,
								"Secure Payment button is not disabled");
						subscriptionAccountPageObject.clickOnSecurePaymentButton();
						subscriptionAccountPageObject.enterWPSSecurePaymentDetails(visa, expiryDate, cvc, driver);
						subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
						orderDetailsPageObject.verifyThankYouMessage();
						loginPageObject.clickOnNavigationMenu();
						orderDetailsPageObject.selectMySubscriptionButton();
						orderDetailsPageObject.verifySubscriptionActivationDetailsMessage();
						subscriptionDetailsPageObject.verifySubscriptionInformationPage(driver);
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Subscription details page has not come up and order is not placed"
										+ e.getClass().toString(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Account is not created" + e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Create account link is not clicked" + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC15_subscriptionDetailsPageVerification() throws InterruptedException, IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC15_Subscription_Details_Page_Verification");
			LogTextFile.writeTestCaseStatus("TC11_Subscription_Details_Page_Verification", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			createAccountPageObject.clickOnCreateANewAccountLink();
			createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, common_Password);
			createAccountPageObject.getSuccessMessageForAccountCreation();
			subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
			subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
			// subscriptionAccountPageObject.clickOnAddressLine2Field();
			subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
			subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
			subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
			subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
			Assert.assertEquals(subscriptionAccountPageObject.isSecurePaymentButtonEnabled(), true,
					"Secure Payment button is not disabled");
			subscriptionAccountPageObject.clickOnSecurePaymentButton();
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(visa, expiryDate, cvc, driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			orderDetailsPageObject.verifyThankYouMessage();
			loginPageObject.clickOnNavigationMenu();
			orderDetailsPageObject.selectMySubscriptionButton();
			orderDetailsPageObject.verifySubscriptionActivationDetailsMessage();
			subscriptionDetailsPageObject.verifySubscriptionInformationPage(driver);
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC17_changePaymentMethod() throws IOException, InterruptedException {
		try {

			Reporting.test = Reporting.extent.createTest("TC17_ChangePaymentMethod");
			LogTextFile.writeTestCaseStatus("TC17_ChangePaymentMethod", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			accountLoginPageObject.verifyAccountLoginPage();
			createAccountPageObject.clickOnCreateANewAccountLink();
			createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
			createAccountPageObject.getSuccessMessageForAccountCreation();
			subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
			subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
			subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
			subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
			subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
			subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
			subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
			subscriptionAccountPageObject.clickOnSecurePaymentButton();
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(visa, expiryDate, cvc, driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			orderDetailsPageObject.verifyThankYouMessage();
			loginPageObject.clickOnNavigationMenu();
			orderDetailsPageObject.selectMySubscriptionButton();
			orderDetailsPageObject.verifySubscriptionActivationDetailsMessage();
			subscriptionDetailsPageObject.verifySubscriptionInformationPage(driver);
			subscriptionDetailsPageObject.clickOnChangePaymentMethod();
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(updateCardNumber, updateExpiryDate, updateCVV,
					driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			Assert.assertEquals(subscriptionDetailsPageObject.getCardNumber().contains("8210"), true);
			Assert.assertEquals(subscriptionDetailsPageObject.getExpirationDate().contains(updateExpiryDate), true);
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC27_cancelSubscriptionWithinGracePeriod() throws InterruptedException, IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC27_cancel_Subscription_Within_Grace_Period");
			LogTextFile.writeTestCaseStatus("TC27_cancel_Subscription_Within_Grace_Period", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			createAccountPageObject.clickOnCreateANewAccountLink();
			createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
			createAccountPageObject.getSuccessMessageForAccountCreation();
			subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
			subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
			// subscriptionAccountPageObject.clickOnAddressLine2Field();
			subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
			subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
			subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
			subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
			Assert.assertEquals(subscriptionAccountPageObject.isSecurePaymentButtonEnabled(), true,
					"Secure Payment button is not disabled");
			subscriptionAccountPageObject.clickOnSecurePaymentButton();
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(mastercard2series, expiryDate, cvc, driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			orderDetailsPageObject.verifyThankYouMessage();
			loginPageObject.clickOnNavigationMenu();
			orderDetailsPageObject.selectMySubscriptionButton();
			orderDetailsPageObject.verifySubscriptionActivationDetailsMessage();
			subscriptionDetailsPageObject.verifySubscriptionInformationPage(driver);
			subscriptionDetailsPageObject.clickOnCancelMySubscriptionButton();
			subscriptionDetailsPageObject.verifyCancelMySubscriptionMessageWithinGracePeriod();
			subscriptionDetailsPageObject.clickOnDontCancelMySubscriptionButton();
			subscriptionDetailsPageObject.clickOnCancelMySubscriptionButton();
			subscriptionDetailsPageObject.verifyCancelMySubscriptionMessageWithinGracePeriod();
			subscriptionDetailsPageObject.clickOnCancelMySubscriptionButtonPopUp();
			subscriptionDetailsPageObject.verifyCancelMySubscriptionButtonIsDisabed();
			subscriptionDetailsPageObject.verifyChangeBillingInformationIsDisabed();
			subscriptionDetailsPageObject.verifyChangePaymentMethodButtonIsDisabed();
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	@Test
	public void TC71_securePaymentValidation3DInsufficientFunds() throws InterruptedException, IOException {
		try {
		Reporting.test = Reporting.extent.createTest("TC71_securePayment_Validation_3DInsufficient_Funds");
		LogTextFile.writeTestCaseStatus("TC71_securePayment_Validation_3DInsufficient_Funds", "Test case");
		driver.get(cod_DEV_CreateAccount_URL);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		createAccountPageObject.clickOnCreateANewAccountLink();
		loginPageObject.clickOnSubscribeButton();
		createAccountPageObject.clickOnCreateANewAccountLink();
		createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
		createAccountPageObject.getSuccessMessageForAccountCreation();
		subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
		subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
		// subscriptionAccountPageObject.clickOnAddressLine2Field();
		subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
		subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
		subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
		subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
		Assert.assertEquals(subscriptionAccountPageObject.isSecurePaymentButtonEnabled(), true,
				"Secure Payment button is not disabled");
		subscriptionAccountPageObject.clickOnSecurePaymentButton();
		subscriptionAccountPageObject.enterWPSSecurePaymentDetails(cardNumber3D_Insufficientfunds, expiryDate, cvc, driver);
		subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);	
		subscriptionAccountPageObject.clickOnCancelButtonOn3DSecurePopUp(driver);
		subscriptionAccountPageObject.verify3DErrorMessage();		
		subscriptionAccountPageObject.enterWPSSecurePaymentDetails(cardNumber3D_Insufficientfunds, expiryDate, cvc, driver);
		subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);		
		subscriptionAccountPageObject.clickOnFailAuthentication(driver);		
		subscriptionAccountPageObject.verify3DErrorMessage();		
		subscriptionAccountPageObject.enterWPSSecurePaymentDetails(cardNumber3D_Insufficientfunds, expiryDate, cvc, driver);
		subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
		subscriptionAccountPageObject.clickOnCompleteAuthentication(driver);		
		subscriptionAccountPageObject.verifyInsufficientPaymentErrorMessageFor3D();		
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC59_3DSPopUpFail() throws InterruptedException, IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC59_3DS_PopUp_Fail");
			LogTextFile.writeTestCaseStatus("TC59_3DS_PopUp_Fail", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			createAccountPageObject.clickOnCreateANewAccountLink();
			loginPageObject.clickOnSubscribeButton();
			createAccountPageObject.clickOnCreateANewAccountLink();
			createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
			createAccountPageObject.getSuccessMessageForAccountCreation();
			subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
			subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
			// subscriptionAccountPageObject.clickOnAddressLine2Field();
			subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
			subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
			subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
			subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
			Assert.assertEquals(subscriptionAccountPageObject.isSecurePaymentButtonEnabled(), true,
					"Secure Payment button is not disabled");
			subscriptionAccountPageObject.clickOnSecurePaymentButton();
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(secure_3d, expiryDate, cvc, driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			subscriptionAccountPageObject.clickOnCancelButtonOn3DSecurePopUp(driver);
			subscriptionAccountPageObject.verify3DErrorMessage();
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC60_3DCardSuccessfullPayment() throws InterruptedException, IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC60_3D_Card_Successfull_Payment");
			LogTextFile.writeTestCaseStatus("TC60_3D_Card_Successfull_Payment", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			createAccountPageObject.clickOnCreateANewAccountLink();
			loginPageObject.clickOnSubscribeButton();
			createAccountPageObject.clickOnCreateANewAccountLink();
			createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
			createAccountPageObject.getSuccessMessageForAccountCreation();
			subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
			subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
			// subscriptionAccountPageObject.clickOnAddressLine2Field();
			subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
			subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
			subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
			subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
			Assert.assertEquals(subscriptionAccountPageObject.isSecurePaymentButtonEnabled(), true,
					"Secure Payment button is not disabled");
			subscriptionAccountPageObject.clickOnSecurePaymentButton();
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(secure_3d, expiryDate, cvc, driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			subscriptionAccountPageObject.clickOnCompleteAuthentication(driver);
			orderDetailsPageObject.verifyThankYouMessage();
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC45_verifyGenericDecline() throws InterruptedException, IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC01_Open_Homepage");
			LogTextFile.writeTestCaseStatus("TC01_Open_Homepage", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			createAccountPageObject.clickOnCreateANewAccountLink();
			createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, common_Password);
			subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
			subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
			subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
			subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
			subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
			subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
			Assert.assertEquals(subscriptionAccountPageObject.isSecurePaymentButtonEnabled(), true,
					"Secure Payment button is not disabled");
			subscriptionAccountPageObject.clickOnSecurePaymentButton();
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(genericDecline, expiryDate, cvc, driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(),
					"Your card has been declined.");

		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC46_verifyInsufficientFundDecline() throws InterruptedException, IOException {
		try {
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(insufficientFundDecline, expiryDate, cvc,
					driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(),
					"Your card has insufficient funds.");
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC47_verifyLostCardDecline() throws InterruptedException, IOException {
		try {
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(lostCardDecline, expiryDate, cvc, driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(),
					"Your card has been declined.");
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC48_verifyStolenCardDecline() throws InterruptedException, IOException {
		try {
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(stolenCardDecline, expiryDate, cvc, driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(),
					"Your card has been declined.");
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC49_verifyExpiredCardDecline() throws InterruptedException, IOException {
		try {
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(expiredCardDecline, expiryDate, cvc, driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(), "Your card has expired.");
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC50_verifyIncorrectCVCCardDecline() throws InterruptedException, IOException {
		try {
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(incorrectCVCDecline, expiryDate, cvc, driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(),
					"Your card's security code is incorrect.");
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC51_verifyProcessingErrorCardDecline() throws InterruptedException, IOException {
		try {
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(processingErrorDecline, expiryDate, cvc, driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(),
					"An error occurred while processing your card. Try again in a little bit.");
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC017_changePaymentMethod() throws IOException, InterruptedException {
		try {
			Reporting.test = Reporting.extent.createTest("TC17_ChangePaymentMethod");
			LogTextFile.writeTestCaseStatus("TC17_ChangePaymentMethod", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			accountLoginPageObject.verifyAccountLoginPage();
			createAccountPageObject.clickOnCreateANewAccountLink();
			createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
			createAccountPageObject.getSuccessMessageForAccountCreation();
			subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
			subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

			subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
			subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
			subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
			subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
			subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
			subscriptionAccountPageObject.clickOnSecurePaymentButton();
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(visa, expiryDate, cvc, driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			orderDetailsPageObject.verifyThankYouMessage();
			loginPageObject.clickOnNavigationMenu();
			orderDetailsPageObject.selectMySubscriptionButton();
			orderDetailsPageObject.verifySubscriptionActivationDetailsMessage();
			subscriptionDetailsPageObject.verifySubscriptionInformationPage(driver);

			subscriptionDetailsPageObject.clickOnChangePaymentMethod();
			subscriptionAccountPageObject.enterWPSSecurePaymentDetails(updateCardNumber, updateExpiryDate, updateCVV,
					driver);
			subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
			Assert.assertEquals(subscriptionDetailsPageObject.getCardNumber().contains("8210"), true);
			Assert.assertEquals(subscriptionDetailsPageObject.getExpirationDate().contains(updateExpiryDate), true);
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC062_verifyExpiryDateInPaymentFieldValidation() throws IOException, InterruptedException {
		try {
			Reporting.test = Reporting.extent.createTest("TC62_verifyExpiryDateInPaymentField");
			LogTextFile.writeTestCaseStatus("TC62_verifyExpiryDateInPaymentField", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			accountLoginPageObject.verifyAccountLoginPage();
			createAccountPageObject.clickOnCreateANewAccountLink();
			createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
			createAccountPageObject.getSuccessMessageForAccountCreation();
			subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
			subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

			subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
			subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
			subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
			subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
			subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
			subscriptionAccountPageObject.clickOnSecurePaymentButton();

			subscriptionAccountPageObject.enterCardNumber(visa, driver);
			subscriptionAccountPageObject.enterExpiryDate(inValidExpiryDate);
			subscriptionAccountPageObject.enterCVV(updateCVV);
			Assert.assertTrue(subscriptionAccountPageObject.verifyInValidExpiryDateFieldValidation()
					.contains("Your card's expiration year is in the past."));
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC067_verifyErrorIfCVVIsLessDigitInPaymentField() throws IOException, InterruptedException {
		try {
			Reporting.test = Reporting.extent.createTest("TC67_verifyCVVLessDataError");
			LogTextFile.writeTestCaseStatus("TC67_verifyCVVLessDataError", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			accountLoginPageObject.verifyAccountLoginPage();
			createAccountPageObject.clickOnCreateANewAccountLink();
			createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
			createAccountPageObject.getSuccessMessageForAccountCreation();
			subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
			subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
			subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
			subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
			subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
			subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
			subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
			subscriptionAccountPageObject.clickOnSecurePaymentButton();
			subscriptionAccountPageObject.enterCardNumber(visa, driver);
			subscriptionAccountPageObject.enterExpiryDate(expiryDate);
			subscriptionAccountPageObject.enterCVV(inValidCVV);
			subscriptionAccountPageObject.enterExpiryDate(expiryDate);
			Assert.assertTrue(subscriptionAccountPageObject.verifyInCompleteCVVFieldValidation()
					.contains("security code is incomplete."));
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC068_verifyErrorIfCardNumberIsLessDigitInPaymentField() throws IOException, InterruptedException {
		try {
			Reporting.test = Reporting.extent.createTest("TC68_verifyErrorIfCardNumberIsLessDigit");
			LogTextFile.writeTestCaseStatus("TC68_verifyErrorIfCardNumberIsLessDigit", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			accountLoginPageObject.verifyAccountLoginPage();
			createAccountPageObject.clickOnCreateANewAccountLink();
			createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
			createAccountPageObject.getSuccessMessageForAccountCreation();
			subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
			subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

			subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
			subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
			subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
			subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
			subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
			subscriptionAccountPageObject.clickOnSecurePaymentButton();

			subscriptionAccountPageObject.enterCardNumber(inCompleteCardNumber, driver);
			subscriptionAccountPageObject.enterExpiryDate(expiryDate);
			Assert.assertEquals(subscriptionAccountPageObject.verifyInCompleteCardNumberFieldValidation(),
					"Your card number is incomplete.");
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC069_verifyInCorrectCardNumberInPaymentField() throws IOException, InterruptedException {
		try {
			Reporting.test = Reporting.extent.createTest("TC069_verifyInCorrectCardNumberInPaymentField");
			LogTextFile.writeTestCaseStatus("TC069_verifyInCorrectCardNumberInPaymentField", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			accountLoginPageObject.verifyAccountLoginPage();
			createAccountPageObject.clickOnCreateANewAccountLink();
			createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
			createAccountPageObject.getSuccessMessageForAccountCreation();
			subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
			subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

			subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
			subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
			subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
			subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
			subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
			subscriptionAccountPageObject.clickOnSecurePaymentButton();

			subscriptionAccountPageObject.enterCardNumber(inValidCardNumber, driver);
			subscriptionAccountPageObject.enterExpiryDate(expiryDate);
			Assert.assertEquals(subscriptionAccountPageObject.verifyInValidCardNumberFieldValidation(),
					"Your card number is invalid.");
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC070_verifyErrorIfExpiryDateIsLessDigitInPaymentField() throws IOException, InterruptedException {
		try {
			Reporting.test = Reporting.extent.createTest("TC070_verifyErrorIfExpiryDateIsLessDigitInPaymentField");
			LogTextFile.writeTestCaseStatus("TC070_verifyErrorIfExpiryDateIsLessDigitInPaymentField", "Test case");
			driver.get(cod_DEV_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			accountLoginPageObject.verifyAccountLoginPage();
			createAccountPageObject.clickOnCreateANewAccountLink();
			createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
			createAccountPageObject.getSuccessMessageForAccountCreation();
			subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
			subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

			subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
			subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
			subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
			subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
			subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
			subscriptionAccountPageObject.clickOnSecurePaymentButton();

			subscriptionAccountPageObject.enterCardNumber(visa, driver);
			subscriptionAccountPageObject.enterExpiryDate(inCompleteExpiryDate);
			subscriptionAccountPageObject.enterCVV(updateCVV);
			Assert.assertTrue(subscriptionAccountPageObject.verifyInCompleteExpiryDateFieldValidation()
					.contains("expiration date is incomplete."));
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

}
