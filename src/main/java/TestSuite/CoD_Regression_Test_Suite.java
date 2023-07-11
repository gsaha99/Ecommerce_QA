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
	String emailAddress = "ATestsa" + utilities.Helper.generateRandomString() + "@mailinator.com";	
	
	String emailAddressFor3DCards = "Test" + utilities.Helper.generateRandomString() + "@mailinator.com";
	private static String common_Password = excelOperation.getTestData("Password", "CoD_Test_Data", "Test_Data");
	private static String cod_DEV_LoginURL = excelOperation.getTestData("Dev_URL", "CoD_Test_Data", "Test_Data");
	private static String cod_DEV_CreateAccount_URL = excelOperation.getTestData("Dev_CreateAccount_URL",
			"CoD_Test_Data", "Test_Data");
	private static String cod_QA_Login_URL = excelOperation.getTestData("QA_Login_URL", "CoD_Test_Data", "Test_Data");
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
	private static String cardNumber3D_Insufficientfunds = excelOperation.getTestData("3d_CardInsufficientBalance",
			"CoD_Test_Data", "Test_Data");

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
			// driver.get(cod_DEV_CreateAccount_URL);			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			driver.get(cod_QA_CreateAccount_URL);
			try {				
				accountLoginPageObject.verifyAccountLoginPage();			
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));			
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.name("firstName")));
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					try {
						createAccountPageObject.getSuccessMessageForAccountCreation();
						
						try {							
							createAccountPageObject.clickOnEditButton(); 
						} catch (Exception e) {
							Reporting.updateTestReport("Edit button is not there" + e.getClass().toString(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Success message is not correct" + e.getClass().toString(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
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
			// driver.get(cod_DEV_CreateAccount_URL);
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			try {		
				accountLoginPageObject.veifyLoginButtonIsDisabled();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
					accountLoginPageObject.enterEmailAddress(emailAddress);
					accountLoginPageObject.enterPassword(common_Password);
					accountLoginPageObject.clickOnLoginButton();
					try {
						accountLoginPageObject.verifySubscriptionAccountText();
						try {
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Edit button is not present" + e.getClass().toString(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Subscription account text is not correct" + e.getClass().toString(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Login failed" + e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Login button is not disabled " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC03_verifyLoginFromLoginPage() throws IOException, InterruptedException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_Login_from_Login_Page");
			LogTextFile.writeTestCaseStatus("TC03_Login_from_Login_Page", "Test case");
			// driver.get(cod_DEV_LoginURL);
			driver.get(cod_QA_Login_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='nav-menu']")));
				loginPageObject.clickOnNavigationMenu();
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//button[@class='user-dropdown__login-btn']")));
				loginPageObject.selectLoginButton();
				try {					
					loginPageObject.veifyLoginButtonIsDisabled();
					try {
						loginPageObject.enterEmailAddress(emailAddress);
						loginPageObject.enterPassword(common_Password);
						loginPageObject.clickOnLoginButton();
						try {
							loginPageObject.clickOnNavigationMenu();
							loginPageObject.clickOnLogoutButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Logout button is not clicked" + e.getClass().toString(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("User is not logged in" + e.getClass().toString(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Login button is enabled" + e.getClass().toString(),
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
		try {
			Reporting.test = Reporting.extent.createTest("TC04_verify_Registration_Field_Validation_On_Checkout");
			LogTextFile.writeTestCaseStatus("TC04_verify_Registration_Field_Validation_On_Checkout", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				createAccountPageObject.enterFirstName("");
				createAccountPageObject.enterLastName("");
				createAccountPageObject.enterEmailAddress("");
				createAccountPageObject.enterConfirmEmailAddress("");
				createAccountPageObject.enterPassword("");
				createAccountPageObject.enterConfirmPassword("");
				createAccountPageObject.clickOnOffersCheckBox();
				Assert.assertEquals(createAccountPageObject.verifyFirstNameFieldValidation(),
						"First Name is required.");
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
				Assert.assertEquals(createAccountPageObject.verifyEmailFieldValidation(),
						"Email address is not valid.");
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
			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	@Test
	public void TC05_loginFormFieldValidationInLoginPage() throws IOException, InterruptedException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_LoginForm_Field_Validation_In_LoginPage");
			LogTextFile.writeTestCaseStatus("TC05_LoginForm_Field_Validation_In_LoginPage", "Test case");
			// driver.get(cod_DEV_LoginURL);
			driver.get(cod_QA_Login_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='nav-menu']")));
				loginPageObject.clickOnNavigationMenu();
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//button[@class='user-dropdown__login-btn']")));
				loginPageObject.selectLoginButton();
				loginPageObject.verifyLoginPageText();
				try {
					loginPageObject.clickOnEmailInputField();
					loginPageObject.clickOnPasswordInputField();
					try {
						loginPageObject.verifyEmailAddressBlankMessageText();
						loginPageObject.clickOnEmailInputField();
						loginPageObject.verifyBlankPasswordFieldMessageText();
						try {
							/* Enter invalid details in Email address field */
							loginPageObject.enterSymbolsInEmailAddressField();
							loginPageObject.verifyInvalidEmailAddressMessageText();
							loginPageObject.enterAlphabetsInEmailAddressField();
							loginPageObject.verifyInvalidEmailAddressMessageText();
							loginPageObject.enterNumbersInEmailAddressField();
							loginPageObject.verifyInvalidEmailAddressMessageText();
							loginPageObject.enterSymbolsInPasswordField();
							try {
								/* Enter invalid details in password field */
								loginPageObject.verifyInvalidPasswordMessageText();
								loginPageObject.enterAlphabetsInPassswordField();
								loginPageObject.verifyInvalidPasswordMessageText();
								loginPageObject.enterNumbersInPasswordField();
								loginPageObject.verifyInvalidPasswordMessageText();
								try {
									/* Entering wrong email address and wrong password */
									loginPageObject.enterAlphabetsInEmailAddressField();
									loginPageObject.enterAlphabetsInPassswordField();
									loginPageObject.verifyInvalidEmailAddressMessageText();
									loginPageObject.verifyBlankPasswordFieldMessageText();
									loginPageObject.veifyLoginButtonIsDisabled();
									try {
										/* Entering correct email address but wrong password */
										loginPageObject.enterRandomGenerationEmailForEmailAddressField();
										loginPageObject.enterAlphabetsInPassswordField();
										loginPageObject.verifyBlankPasswordFieldMessageText();
										loginPageObject.veifyLoginButtonIsDisabled();
										try {
											/* Entering invalid email address but right password */
											loginPageObject.enterAlphabetsInEmailAddressField();
											loginPageObject.enterRandomStringForPasswordAddressField();
											loginPageObject.verifyInvalidEmailAddressMessageText();
											loginPageObject.veifyLoginButtonIsDisabled();
											try {
												/* Enter Invalid credentials */
												loginPageObject.enterRandomGenerationEmailForEmailAddressField();
												loginPageObject.enterRandomStringForPasswordAddressField();
												loginPageObject.clickOnLoginButton();
												// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='There
												// was an unexpected problem with your login. Please check your
												// credentials.']")));
												loginPageObject.verifyInvalidCredentialMessageText();
											} catch (Exception e) {
												Reporting.updateTestReport(
														"Invalid email credetials message is worng message is wrong "
																+ e.getClass().toString(),
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
											/* Enter Invalid credentials */
											loginPageObject.enterRandomGenerationEmailForEmailAddressField();
											loginPageObject.enterRandomStringForPasswordAddressField();
											loginPageObject.clickOnLoginButton();
											loginPageObject.verifyInvalidCredentialMessageText();
										} catch (Exception e) {
											Reporting.updateTestReport(
													"Invalid email address message is wrong " + e.getClass().toString(),
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Blank password field message is wrong " + e.getClass().toString(),
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Not able to enter wrong details in email and password field "
													+ e.getClass().toString(),
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Not able to enter invalid details in password field "
												+ e.getClass().toString(),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"Not able to enter symbols in email field " + e.getClass().toString(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Clicking on email input field and passowrd input field is not working "
										+ e.getClass().toString(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
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
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
				accountLoginPageObject.enterEmailAddress(emailAddress);
				accountLoginPageObject.enterPassword(common_Password);
				accountLoginPageObject.clickOnLoginButton();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.name("CCname")));
					subscriptionAccountPageObject.clickOnCreditCardInputBox();
					subscriptionAccountPageObject.clickOnAddressLine1InputBox();
					try {
						subscriptionAccountPageObject.verifyCreditCardErrorMessageForBlankField();
						subscriptionAccountPageObject.clickOnCityTownInputBox();
						subscriptionAccountPageObject.verifyAddressLine1RequiredErrorMessageForBlankField();
						subscriptionAccountPageObject.clickOnStateInputBox();
						try {
							subscriptionAccountPageObject.verifyCityTownErrorMessageForBlankField();
							subscriptionAccountPageObject.clickOnZipCodeInputBox();
							subscriptionAccountPageObject.clickOnPhoneNumberInputBox();
							try {
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
										"zip input box or phone number input field is not clicked "
												+ e.getClass().toString(),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"city or state input field is not clicked " + e.getClass().toString(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Credit card or address line1 input field is not clicked " + e.getClass().toString(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}

				} catch (Exception e) {
					Reporting.updateTestReport(
							"Billing address testing  for invalid data is failed " + e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Login button is not visible or user was not created " + e.getClass().toString(),
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
			String emailAddress = "Testsa" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC45_verifyGenericDecline");
			LogTextFile.writeTestCaseStatus("TC45_verifyGenericDecline", "Test case");			
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();

							Assert.assertEquals(subscriptionAccountPageObject.getVatValue().contains(vat), true,
									"Vat value is not visible");
							Assert.assertEquals(subscriptionAccountPageObject.getRecurringMonthlyBillValue().contains(
									recurringMonthlyBill), true, "Recurring Monthly Bill value is not visible");
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {							
							Reporting.updateTestReport("Vat value and Recurring Bill value message not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC11_orderPlacement() throws InterruptedException, IOException {
		try {
			String emailAddress = "Testsas" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC11_orderPlacement");
			LogTextFile.writeTestCaseStatus("TC11_orderPlacement", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {							
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();
							try {
								subscriptionAccountPageObject.enterWPSSecurePaymentDetails(visa, expiryDate, cvc,
										driver);
								subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
								try {
									orderDetailsPageObject.verifyThankYouMessage();
									loginPageObject.clickOnNavigationMenu();
									orderDetailsPageObject.selectMySubscriptionButton();
									orderDetailsPageObject.verifySubscriptionActivationDetailsMessage();
									subscriptionDetailsPageObject.verifySubscriptionInformationPage(driver);
									loginPageObject.clickOnNavigationMenu();
									loginPageObject.clickOnLogoutButton();
								} catch (Exception e) {
									Reporting.updateTestReport("Subscription Information Page is verified",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									loginPageObject.clickOnNavigationMenu();
									loginPageObject.clickOnLogoutButton();
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Unable to click on submit order button",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								createAccountPageObject.clickOnEditButton();
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Secure payment button is not enabled",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_QA_CreateAccount_URL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	@Test
	public void TC15_subscriptionDetailsPageVerification() throws InterruptedException, IOException {		
		try {
			String emailAddress = "Testsab" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC15_Subscription_Details_Page_Verification");
			LogTextFile.writeTestCaseStatus("TC15_Subscription_Details_Page_Verification", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();
							try {
								subscriptionAccountPageObject.enterWPSSecurePaymentDetails(visa, expiryDate, cvc,
										driver);
								subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
								try {
									orderDetailsPageObject.verifyThankYouMessage();
									loginPageObject.clickOnNavigationMenu();
									orderDetailsPageObject.selectMySubscriptionButton();
									orderDetailsPageObject.verifySubscriptionActivationDetailsMessage();
									subscriptionDetailsPageObject.verifySubscriptionInformationPage(driver);
									loginPageObject.clickOnNavigationMenu();
									loginPageObject.clickOnLogoutButton();
								} catch (Exception e) {
									Reporting.updateTestReport("Subscription Information Page is verified",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									loginPageObject.clickOnNavigationMenu();
									loginPageObject.clickOnLogoutButton();
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Unable to click on submit order button",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								createAccountPageObject.clickOnEditButton();
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Secure payment button is not enabled",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_QA_CreateAccount_URL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC27_cancelSubscriptionWithinGracePeriod() throws InterruptedException, IOException {
		try {
			String emailAddress = "Testsac" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC27_cancelSubscription_Within_GracePeriod");
			LogTextFile.writeTestCaseStatus("TC27_cancelSubscription_Within_GracePeriod", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();
							try {
								subscriptionAccountPageObject.enterWPSSecurePaymentDetails(visa, expiryDate, cvc,
										driver);
								subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
								try {
									orderDetailsPageObject.verifyThankYouMessage();
									loginPageObject.clickOnNavigationMenu();
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='user-dropdown__my-account-btn']")));
									orderDetailsPageObject.selectMySubscriptionButton();
									orderDetailsPageObject.verifySubscriptionActivationDetailsMessage();
									subscriptionDetailsPageObject.verifySubscriptionInformationPage(driver);
									try {
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
										Reporting.updateTestReport("Cancellation isnot working",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport("Subscription Information Page is verified",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Unable to click on submit order button",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								createAccountPageObject.clickOnEditButton();
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Secure payment button is not enabled",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_QA_CreateAccount_URL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC17_changePaymentMethod() throws IOException, InterruptedException {
		try {
			String emailAddress = "Testsad" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC17_changePaymentMethod");
			LogTextFile.writeTestCaseStatus("TC17_changePaymentMethod", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();
							try {
								subscriptionAccountPageObject.enterWPSSecurePaymentDetails(visa, expiryDate, cvc,
										driver);
								subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
								try {
									orderDetailsPageObject.verifyThankYouMessage();
									loginPageObject.clickOnNavigationMenu();
									orderDetailsPageObject.selectMySubscriptionButton();
									orderDetailsPageObject.verifySubscriptionActivationDetailsMessage();
									subscriptionDetailsPageObject.verifySubscriptionInformationPage(driver);
									try {
										wait.until(ExpectedConditions.visibilityOfElementLocated(
												By.xpath("//button[text()='Change Payment Method']")));
										subscriptionDetailsPageObject.clickOnChangePaymentMethod();
										subscriptionAccountPageObject.enterWPSSecurePaymentDetails(updateCardNumber,
												updateExpiryDate, updateCVV, driver);
										subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
										Assert.assertEquals(
												subscriptionDetailsPageObject.getCardNumber().contains("8210"), true);
										Assert.assertEquals(subscriptionDetailsPageObject.getExpirationDate()
												.contains(updateExpiryDate), true);
									} catch (Exception e) {
										Reporting.updateTestReport("Card number and expiration date message not found",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport("Subscription Information Page is verified",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Unable to click on submit order button",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								createAccountPageObject.clickOnEditButton();
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Secure payment button is not enabled",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_QA_CreateAccount_URL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	

	@Test
	public void TC59_3DSPopUpFail() throws InterruptedException, IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC59_3DS_PopUp_Fail");
			LogTextFile.writeTestCaseStatus("TC59_3DS_PopUp_Fail", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));			
			try {				
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				createAccountPageObject.creationOfAccount(firstName, lastName, emailAddressFor3DCards, password);
				createAccountPageObject.getSuccessMessageForAccountCreation();
				try {
					subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
					subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
					try {
						// subscriptionAccountPageObject.clickOnAddressLine2Field();
						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						subscriptionAccountPageObject.clickOnSecurePaymentButton();
						try {					
							subscriptionAccountPageObject.enterWPSSecurePaymentDetails(secure_3d, expiryDate, cvc, driver);
							subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
							subscriptionAccountPageObject.clickOnCancelButtonOn3DSecurePopUp(driver);
							subscriptionAccountPageObject.verify3DErrorMessage();
							try {												
								subscriptionAccountPageObject.enterWPSSecurePaymentDetails(cardNumber3D_Insufficientfunds, expiryDate, cvc,
										driver);
								subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
								subscriptionAccountPageObject.clickOnFailAuthentication(driver);
								subscriptionAccountPageObject.verify3DErrorMessage();
							} catch (Exception e) {
								Reporting.updateTestReport("Fail message for 3D is not correct " + e.getClass().toString(),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								createAccountPageObject.clickOnEditButton();
							}							
						} catch (Exception e) {
							Reporting.updateTestReport("Cancel for 3D did not work " + e.getClass().toString(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
							}						
					} catch (Exception e) {
						Reporting.updateTestReport("Not able to enter values in city, zipcode or phone numbe field " + e.getClass().toString(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}					
				} catch (Exception e) {
					Reporting.updateTestReport("Not able to enter values in credit cards or address line1 field " + e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					createAccountPageObject.clickOnEditButton();
				}				
			} catch (Exception e) {
				Reporting.updateTestReport("Account success message is not correct " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}			
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	@Test
	public void TC60_securePaymentValidation3DInsufficientFunds() throws InterruptedException, IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC60_securePayment_Validation_3DInsufficient_Funds");
			LogTextFile.writeTestCaseStatus("TC60_securePayment_Validation_3DInsufficient_Funds", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				createAccountPageObject.creationOfAccount(firstName, lastName, emailAddressFor3DCards, password);
				createAccountPageObject.getSuccessMessageForAccountCreation();
				try {
					subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
					subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
					try {
						// subscriptionAccountPageObject.clickOnAddressLine2Field();
						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						subscriptionAccountPageObject.clickOnSecurePaymentButton();
						try {
							subscriptionAccountPageObject.enterWPSSecurePaymentDetails(secure_3d, expiryDate, cvc,
									driver);
							subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
							subscriptionAccountPageObject.clickOnCancelButtonOn3DSecurePopUp(driver);
							subscriptionAccountPageObject.verify3DErrorMessage();
							try {								
								subscriptionAccountPageObject.enterWPSSecurePaymentDetails(
										cardNumber3D_Insufficientfunds, expiryDate, cvc, driver);
								subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
								subscriptionAccountPageObject.clickOnFailAuthentication(driver);
								subscriptionAccountPageObject.verify3DErrorMessage();
								try {
									subscriptionAccountPageObject.enterWPSSecurePaymentDetails(
											cardNumber3D_Insufficientfunds, expiryDate, cvc, driver);
									subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
									subscriptionAccountPageObject.clickOnCompleteAuthentication(driver);
									subscriptionAccountPageObject.verifyInsufficientPaymentErrorMessageFor3D();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Insufficient error message is not correct " + e.getClass().toString(),
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									createAccountPageObject.clickOnEditButton();
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Fail message for 3D is not correct " + e.getClass().toString(),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								createAccountPageObject.clickOnEditButton();
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Cancel for 3D did not work " + e.getClass().toString(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Not able to enter values in city, zipcode or phone numbe field "
										+ e.getClass().toString(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}

				} catch (Exception e) {
					Reporting.updateTestReport(
							"Not able to enter values in credit cards or address line1 field "
									+ e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					createAccountPageObject.clickOnEditButton();
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Account success message is not correct " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC71_3DCardSuccessfullPayment() throws InterruptedException, IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC71_3DCard_Successfull_Payment");
			LogTextFile.writeTestCaseStatus("TC71_3DCard_Successfull_Payment", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				createAccountPageObject.creationOfAccount(firstName, lastName, emailAddressFor3DCards, password);
				createAccountPageObject.getSuccessMessageForAccountCreation();
				try {
					subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
					subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();
					try {
						// subscriptionAccountPageObject.clickOnAddressLine2Field();
						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						subscriptionAccountPageObject.clickOnSecurePaymentButton();
						try {			
							subscriptionAccountPageObject.enterWPSSecurePaymentDetails(secure_3d, expiryDate, cvc,
									driver);
							subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
							subscriptionAccountPageObject.clickOnCompleteAuthentication(driver);
							try {
								orderDetailsPageObject.verifyThankYouMessage();
							} catch (Exception e) {
								Reporting.updateTestReport("Thank you message is not correct " + e.getClass().toString(),
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}							
						} catch (Exception e) {
							Reporting.updateTestReport("Complete Authentication button is not working " + e.getClass().toString(),
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Not able to enter values in city, zipcode or phone numbe field "
										+ e.getClass().toString(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"Not able to enter values in credit cards or address line1 field "
									+ e.getClass().toString(),
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					createAccountPageObject.clickOnEditButton();
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Account success message is not correct " + e.getClass().toString(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC45_verifyGenericDecline() throws InterruptedException, IOException {
		try {
			String emailAddress = "Testsae" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC45_verifyGenericDecline");
			LogTextFile.writeTestCaseStatus("TC45_verifyGenericDecline", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();

							subscriptionAccountPageObject.enterWPSSecurePaymentDetails(genericDecline, expiryDate, cvc,
									driver);
							subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
							Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(),
									"Your card has been declined.");
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Card decline message not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC46_verifyInsufficientFundDecline() throws InterruptedException, IOException {
		try {
			String emailAddress = "Testsaf" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC46_verifyInsufficientFundDecline");
			LogTextFile.writeTestCaseStatus("TC46_verifyInsufficientFundDecline", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();

							subscriptionAccountPageObject.enterWPSSecurePaymentDetails(insufficientFundDecline,
									expiryDate, cvc, driver);
							subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
							Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(),
									"Your card has insufficient funds.");
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Insufficient funds message not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC47_verifyLostCardDecline() throws InterruptedException, IOException {
		try {
			String emailAddress = "Testsag" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC47_verifyLostCardDecline");
			LogTextFile.writeTestCaseStatus("TC47_verifyLostCardDecline", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();

							subscriptionAccountPageObject.enterWPSSecurePaymentDetails(lostCardDecline, expiryDate, cvc,
									driver);
							subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
							Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(),
									"Your card has been declined.");
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Cards decline message not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC48_verifyStolenCardDecline() throws InterruptedException, IOException {
		try {
			String emailAddress = "Testsah" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC48_verifyStolenCardDecline");
			LogTextFile.writeTestCaseStatus("TC48_verifyStolenCardDecline", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();

							subscriptionAccountPageObject.enterWPSSecurePaymentDetails(stolenCardDecline, expiryDate,
									cvc, driver);
							subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
							Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(),
									"Your card has been declined.");
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Cards decline message not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC49_verifyExpiredCardDecline() throws InterruptedException, IOException {
		try {
			String emailAddress = "Testsai" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC49_verifyExpiredCardDecline");
			LogTextFile.writeTestCaseStatus("TC49_verifyExpiredCardDecline", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();

							subscriptionAccountPageObject.enterWPSSecurePaymentDetails(expiredCardDecline, expiryDate,
									cvc, driver);
							subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
							Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(),
									"Your card has expired.");
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Cards expired message not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC50_verifyIncorrectCVCCardDecline() throws InterruptedException, IOException {
		try {
			String emailAddress = "Testsaj" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC50_verifyIncorrectCVCCardDecline");
			LogTextFile.writeTestCaseStatus("TC50_verifyIncorrectCVCCardDecline", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();

							subscriptionAccountPageObject.enterWPSSecurePaymentDetails(incorrectCVCDecline, expiryDate,
									cvc, driver);
							subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
							Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(),
									"Your card's security code is incorrect.");
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Cards Security code message not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC51_verifyProcessingErrorCardDecline() throws InterruptedException, IOException {
		try {
			String emailAddress = "Testsak" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC51_verifyProcessingErrorCardDecline");
			LogTextFile.writeTestCaseStatus("TC51_verifyProcessingErrorCardDecline", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();

							subscriptionAccountPageObject.enterWPSSecurePaymentDetails(processingErrorDecline,
									expiryDate, cvc, driver);
							subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
							Assert.assertEquals(subscriptionAccountPageObject.verifyErrorMessageForCards(),
									"An error occurred while processing your card. Try again in a little bit.");
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Cards error processing message not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC62_verifyExpiryDateInPaymentFieldValidation() throws IOException, InterruptedException {
		try {
			String emailAddress = "Testsal" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC62_verifyExpiryDateInPaymentFieldValidation");
			LogTextFile.writeTestCaseStatus("TC62_verifyExpiryDateInPaymentFieldValidation", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();

							subscriptionAccountPageObject.enterCardNumber(visa, driver);
							subscriptionAccountPageObject.enterExpiryDate(inValidExpiryDate);
							subscriptionAccountPageObject.enterCVV(updateCVV);
							Assert.assertTrue(subscriptionAccountPageObject.verifyInValidExpiryDateFieldValidation()
									.contains("Your card's expiration year is in the past."));
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Card's expiration message not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC67_verifyErrorIfCVVIsLessDigitInPaymentField() throws IOException, InterruptedException {
		try {
			String emailAddress = "Testsam" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC67_verifyErrorIfCVVIsLessDigitInPaymentField");
			LogTextFile.writeTestCaseStatus("TC67_verifyErrorIfCVVIsLessDigitInPaymentField", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();

							subscriptionAccountPageObject.enterCardNumber(visa, driver);
							subscriptionAccountPageObject.enterExpiryDate(expiryDate);
							subscriptionAccountPageObject.enterCVV(inValidCVV);
							subscriptionAccountPageObject.enterExpiryDate(expiryDate);
							Assert.assertTrue(subscriptionAccountPageObject.verifyInCompleteCVVFieldValidation()
									.contains("security code is incomplete."));
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Security Coder Incomplete message not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC68_verifyErrorIfCardNumberIsLessDigitInPaymentField() throws IOException, InterruptedException {
		try {
			String emailAddress = "Testsan" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC68_verifyErrorIfCardNumberIsLessDigitInPaymentField");
			LogTextFile.writeTestCaseStatus("TC68_verifyErrorIfCardNumberIsLessDigitInPaymentField", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();

							subscriptionAccountPageObject.enterCardNumber(inCompleteCardNumber, driver);
							subscriptionAccountPageObject.enterExpiryDate(expiryDate);
							Assert.assertEquals(
									subscriptionAccountPageObject.verifyInCompleteCardNumberFieldValidation(),
									"Your card number is incomplete.");
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Card number Incomplete message not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC69_verifyInCorrectCardNumberInPaymentField() throws IOException, InterruptedException {
		try {
			String emailAddress = "Testsao" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC69_verifyInCorrectCardNumberInPaymentField");
			LogTextFile.writeTestCaseStatus("TC69_verifyInCorrectCardNumberInPaymentField", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();

							subscriptionAccountPageObject.enterCardNumber(inValidCardNumber, driver);
							subscriptionAccountPageObject.enterExpiryDate(expiryDate);
							Assert.assertEquals(subscriptionAccountPageObject.verifyInValidCardNumberFieldValidation(),
									"Your card number is invalid.");
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Invalid Card number message not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC70_verifyErrorIfExpiryDateIsLessDigitInPaymentField() throws IOException, InterruptedException {
		try {
			String emailAddress = "Testsap" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC70_verifyErrorIfExpiryDateIsLessDigitInPaymentField");
			LogTextFile.writeTestCaseStatus("TC70_verifyErrorIfExpiryDateIsLessDigitInPaymentField", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();

							subscriptionAccountPageObject.enterCardNumber(visa, driver);
							subscriptionAccountPageObject.enterExpiryDate(inCompleteExpiryDate);
							subscriptionAccountPageObject.enterCVV(updateCVV);
							Assert.assertTrue(subscriptionAccountPageObject.verifyInCompleteExpiryDateFieldValidation()
									.contains("expiration date is incomplete."));
							createAccountPageObject.clickOnEditButton();
						} catch (Exception e) {
							Reporting.updateTestReport("Expiration date incomplete message not found",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_DEV_LoginURL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	@Test
	public void TC20_verifyErrorIfCardNumberIsLessDigitInSubscriptionDetailsPage()
			throws IOException, InterruptedException {
		try {
			String emailAddress = "Testsaq" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent
					.createTest("TC20_verifyErrorIfCardNumberIsLessDigitInSubscriptionDetailsPage");
			LogTextFile.writeTestCaseStatus("TC20_verifyErrorIfCardNumberIsLessDigitInSubscriptionDetailsPage",
					"Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();
							try {
								subscriptionAccountPageObject.enterWPSSecurePaymentDetails(visa, expiryDate, cvc,
										driver);
								subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
								try {
									orderDetailsPageObject.verifyThankYouMessage();
									loginPageObject.clickOnNavigationMenu();
									orderDetailsPageObject.selectMySubscriptionButton();
									orderDetailsPageObject.verifySubscriptionActivationDetailsMessage();
									subscriptionDetailsPageObject.verifySubscriptionInformationPage(driver);
									try {
										wait.until(ExpectedConditions.visibilityOfElementLocated(
												By.xpath("//button[text()='Change Payment Method']")));
										subscriptionDetailsPageObject.clickOnChangePaymentMethod();
										subscriptionAccountPageObject.enterCardNumber(inCompleteCardNumber, driver);
										subscriptionAccountPageObject.enterExpiryDate(expiryDate);
										Assert.assertEquals(
												subscriptionAccountPageObject
														.verifyInCompleteCardNumberFieldValidation(),
												"Your card number is incomplete.");
										loginPageObject.clickOnNavigationMenu();
										loginPageObject.clickOnLogoutButton();
									} catch (Exception e) {
										Reporting.updateTestReport("Cards number incomplete error message not found",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										loginPageObject.clickOnNavigationMenu();
										loginPageObject.clickOnLogoutButton();
									}
								} catch (Exception e) {
									Reporting.updateTestReport("Subscription Information Page is verified",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									loginPageObject.clickOnNavigationMenu();
									loginPageObject.clickOnLogoutButton();
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Unable to click on submit order button",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								createAccountPageObject.clickOnEditButton();
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Secure payment button is not enabled",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_QA_CreateAccount_URL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	@Test
	public void TC21_verifyErrorIfExpiryDateIsInPastDigitInSubscriptionDetailsPage()
			throws IOException, InterruptedException {
		try {
			String emailAddress = "Testsar" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent
					.createTest("TC21_verifyErrorIfExpiryDateIsInPastDigitInSubscriptionDetailsPage");
			LogTextFile.writeTestCaseStatus("TC21_verifyErrorIfExpiryDateIsInPastDigitInSubscriptionDetailsPage",
					"Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();
							try {
								subscriptionAccountPageObject.enterWPSSecurePaymentDetails(visa, expiryDate, cvc,
										driver);
								subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
								try {
									orderDetailsPageObject.verifyThankYouMessage();
									loginPageObject.clickOnNavigationMenu();
									orderDetailsPageObject.selectMySubscriptionButton();
									orderDetailsPageObject.verifySubscriptionActivationDetailsMessage();
									subscriptionDetailsPageObject.verifySubscriptionInformationPage(driver);
									try {
										wait.until(ExpectedConditions.visibilityOfElementLocated(
												By.xpath("//button[text()='Change Payment Method']")));
										subscriptionDetailsPageObject.clickOnChangePaymentMethod();
										subscriptionAccountPageObject.enterCardNumber(visa, driver);
										subscriptionAccountPageObject.enterExpiryDate(inValidExpiryDate);
										subscriptionAccountPageObject.enterCVV(updateCVV);
										Assert.assertTrue(
												subscriptionAccountPageObject.verifyInValidExpiryDateFieldValidation()
														.contains("Your card's expiration year is in the past."));
										loginPageObject.clickOnNavigationMenu();
										loginPageObject.clickOnLogoutButton();
									} catch (Exception e) {
										Reporting.updateTestReport("Cards expiration year error message not found",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										loginPageObject.clickOnNavigationMenu();
										loginPageObject.clickOnLogoutButton();
									}
								} catch (Exception e) {
									Reporting.updateTestReport("Subscription Information Page is verified",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									loginPageObject.clickOnNavigationMenu();
									loginPageObject.clickOnLogoutButton();
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Unable to click on submit order button",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								createAccountPageObject.clickOnEditButton();
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Secure payment button is not enabled",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_QA_CreateAccount_URL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	@Test
	public void TC22_verifyErrorIfCardNumberIsIncorrectInSubscriptionDetailsPage()
			throws IOException, InterruptedException {
		try {
			String emailAddress = "Testsas" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent
					.createTest("TC22_verifyErrorIfCardNumberIsIncorrectInSubscriptionDetailsPage");
			LogTextFile.writeTestCaseStatus("TC22_verifyErrorIfCardNumberIsIncorrectInSubscriptionDetailsPage",
					"Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();
							try {
								subscriptionAccountPageObject.enterWPSSecurePaymentDetails(visa, expiryDate, cvc,
										driver);
								subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
								try {
									orderDetailsPageObject.verifyThankYouMessage();
									loginPageObject.clickOnNavigationMenu();
									orderDetailsPageObject.selectMySubscriptionButton();
									orderDetailsPageObject.verifySubscriptionActivationDetailsMessage();
									subscriptionDetailsPageObject.verifySubscriptionInformationPage(driver);
									try {
										wait.until(ExpectedConditions.visibilityOfElementLocated(
												By.xpath("//button[text()='Change Payment Method']")));
										subscriptionDetailsPageObject.clickOnChangePaymentMethod();
										subscriptionAccountPageObject.enterCardNumber(inValidCardNumber, driver);
										subscriptionAccountPageObject.enterExpiryDate(expiryDate);
										Assert.assertEquals(
												subscriptionAccountPageObject.verifyInValidCardNumberFieldValidation(),
												"Your card number is invalid.");
										loginPageObject.clickOnNavigationMenu();
										loginPageObject.clickOnLogoutButton();
									} catch (Exception e) {
										Reporting.updateTestReport("Invalid card number error message not found",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										loginPageObject.clickOnNavigationMenu();
										loginPageObject.clickOnLogoutButton();
									}
								} catch (Exception e) {
									Reporting.updateTestReport("Subscription Information Page is verified",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									loginPageObject.clickOnNavigationMenu();
									loginPageObject.clickOnLogoutButton();
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Unable to click on submit order button",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								createAccountPageObject.clickOnEditButton();
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Secure payment button is not enabled",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_QA_CreateAccount_URL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

	@Test
	public void TC23_verifyErrorIfCVVIsIncorrectInSubscriptionDetailsPage() throws IOException, InterruptedException {
		try {
			String emailAddress = "Testsat" + utilities.Helper.generateRandomString() + "@mailinator.com";
			Reporting.test = Reporting.extent.createTest("TC23_verifyErrorIfCVVIsIncorrectInSubscriptionDetailsPage");
			LogTextFile.writeTestCaseStatus("TC23_verifyErrorIfCVVIsIncorrectInSubscriptionDetailsPage", "Test case");
			driver.get(cod_QA_CreateAccount_URL);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try {
				accountLoginPageObject.verifyAccountLoginPage();
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".create-account-btn")));
				createAccountPageObject.clickOnCreateANewAccountLink();
				try {
					createAccountPageObject.creationOfAccount(firstName, lastName, emailAddress, password);
					createAccountPageObject.getSuccessMessageForAccountCreation();
					try {
						subscriptionAccountPageObject.enterAlphabetsInCreditCardField();
						subscriptionAccountPageObject.enterAlphaNumericInAddressLine1Field();

						subscriptionAccountPageObject.enterAlphaNumericInCityTownField();
						subscriptionAccountPageObject.enterAlphaNumericInZipCodeField();
						subscriptionAccountPageObject.enterValidNumbersInPhoneNumberField();
						subscriptionAccountPageObject.clickOnMonthlySubscriptionFeeCheckbox();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.cssSelector("#continue-to-payment-btn")));
							subscriptionAccountPageObject.isSecurePaymentButtonEnabled();
							subscriptionAccountPageObject.clickOnSecurePaymentButton();
							try {
								subscriptionAccountPageObject.enterWPSSecurePaymentDetails(visa, expiryDate, cvc,
										driver);
								subscriptionAccountPageObject.clickOnSubmitOrderButton(driver);
								try {
									orderDetailsPageObject.verifyThankYouMessage();
									loginPageObject.clickOnNavigationMenu();
									orderDetailsPageObject.selectMySubscriptionButton();
									orderDetailsPageObject.verifySubscriptionActivationDetailsMessage();
									subscriptionDetailsPageObject.verifySubscriptionInformationPage(driver);
									try {
										wait.until(ExpectedConditions.visibilityOfElementLocated(
												By.xpath("//button[text()='Change Payment Method']")));
										subscriptionDetailsPageObject.clickOnChangePaymentMethod();
										subscriptionAccountPageObject.enterCardNumber(visa, driver);
										subscriptionAccountPageObject.enterExpiryDate(expiryDate);
										subscriptionAccountPageObject.enterCVV(inValidCVV);
										subscriptionAccountPageObject.enterExpiryDate(expiryDate);
										Assert.assertTrue(
												subscriptionAccountPageObject.verifyInCompleteCVVFieldValidation()
														.contains("security code is incomplete."));
										loginPageObject.clickOnNavigationMenu();
										loginPageObject.clickOnLogoutButton();
									} catch (Exception e) {
										Reporting.updateTestReport("Security Code message has been verified",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										loginPageObject.clickOnNavigationMenu();
										loginPageObject.clickOnLogoutButton();
									}
								} catch (Exception e) {
									Reporting.updateTestReport("Subscription Information Page is verified",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									loginPageObject.clickOnNavigationMenu();
									loginPageObject.clickOnLogoutButton();
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Unable to click on submit order button",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								createAccountPageObject.clickOnEditButton();
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Secure payment button is not enabled",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							createAccountPageObject.clickOnEditButton();
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Unable to click on Monthly Subscription Checkbox button",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						createAccountPageObject.clickOnEditButton();
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Unable to get the success Message for Account Creation",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Unable to Click on Create a new account link",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		} catch (Exception e) {
			driver.get(cod_QA_CreateAccount_URL);
			Reporting.updateTestReport("Exception occured" + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}

	}

}
