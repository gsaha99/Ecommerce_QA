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

import PageObjectRepo.app_VET_Repo;
import PageObjectRepo.app_WEL_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.EmailValidation;
import utilities.LogTextFile;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class WEL_Prod_Test_Suite extends DriverModule{

	app_WEL_Repo WEL;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	public static String WEL_Homepage_URL=excelOperation.getTestData("WEL_Homepage_URL", "Generic_Dataset", "Data");
	@BeforeTest
	public void initializeRepo() {
		WEL = PageFactory.initElements(driver, app_WEL_Repo.class);

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
	 * @Date: 5/4/23
	 * @Description: Registers one user through standalone registration
	 */
	@Test
	public void TC01_Standalone_User_Registration() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC01_Standalone_User_Registration");
			LogTextFile.writeTestCaseStatus("TC01_Standalone_User_Registration", "Test case");
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				WEL.clickOnLoginButtonHomepage();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.efficientlearning.com/register']")));
					WEL.clickOnCreateOneLinkInLoginPage();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("register.studentFirstName")));
						WEL.enterFirstNameInRegistrationForm(excelOperation.getTestData("TC01", "WEL_Test_Data", "First_Name"));
						WEL.enterLastNameInRegistrationForm(excelOperation.getTestData("TC01", "WEL_Test_Data", "Last_Name"));
						String emailId=WEL.enterEmailIdInRegistrationForm();
						WEL.enterPasswordInRegistrationForm(excelOperation.getTestData("TC01", "WEL_Test_Data", "Password"));
						WEL.clickOnCheckboxInRegistrationForm();
						WEL.clickOnCreateAccountButtonInRegistrationForm();
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='account']")));
							Reporting.updateTestReport("Account was created successfully",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
							WEL.clickOnAccountButtonInHomepage();
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'I Accept')]")));
								Reporting.updateTestReport("Wiley & WEL Privacy Agreement banner was displayed",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								WEL.clickonAcceptButtonOnWileyWELPrivacyAgreement();
								WEL.clickOnSignOutButtonInMyAccountPage();
							} catch (Exception e) {
								Reporting.updateTestReport("Wiley & WEL Privacy Agreement banner was not displayed",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							}
							excelOperation.updateTestData("TC01", "WEL_Test_Data", "Email_Address", emailId);
							excelOperation.updateTestData("TC02", "WEL_Test_Data", "Email_Address", emailId);
							excelOperation.updateTestData("TC04", "WEL_Test_Data", "Email_Address", emailId);
							excelOperation.updateTestData("TC05", "WEL_Test_Data", "Email_Address", emailId);
						}
						catch(Exception e) {
							Reporting.updateTestReport("Account button in homepage was not visisble and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("The registration first name field was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("User was not on the login page and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
		catch(Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 5/4/23 
	 * @Description: This test cases is to Login with newly Registered user
	 */
	@Test
	public void TC02_Login_With_Newly_Registered_User() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC02_Login_With_Newly_Registered_User");
			LogTextFile.writeTestCaseStatus("TC02_Login_With_Newly_Registered_User", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				WEL.clickOnLoginButtonHomepage();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
					WEL.enterUserNameInLoginPage(excelOperation.getTestData("TC02", "WEL_Test_Data", "Email_Address"));
					WEL.enterPasswordInLoginPage(excelOperation.getTestData("TC02", "WEL_Test_Data", "Password"));
					WEL.clickOnStandaloneLoginButton();
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
						WEL.clickOnSignOutButtonInMyAccountPage();
					} catch (Exception e) {
						Reporting.updateTestReport("Failed display the Account Link", CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Username field in login form was not clickable and caused timeout exception" ,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
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
	 * @Date: 5/4/23
	 * @Description: Adds one physical product to cart and registers one new user
	 */

	@Test
	public void TC03_User_Registration_During_Checkout_for_Physical_Cart() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC03_User_Registration_During_Checkout_for_Physical_Cart");
			LogTextFile.writeTestCaseStatus("TC03_User_Registration_During_Checkout_for_Physical_Cart", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
					WEL.clickOnCMALinkOnHomepage();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='shop-courses-btn  ']")));
						driver.navigate().refresh();
						WEL.clickOnExploreCourseButton();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='price-block'])[1]")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/cma/products/platinum-cma-review-course/']"
										+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
								WEL.clickOnCMAViewCourseButton();
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(
											By.xpath("(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")));
									ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
								}
								catch(Exception e) {
									Reporting.updateTestReport("GET PLATINUM WILEY CMA COURSE -> button didn't appear",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								try {
									wait.until(ExpectedConditions.
											visibilityOfElementLocated(By.xpath("//label[@for='Print + eBook0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									try {
										wait.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
										WEL.clickOnAddToCartButton();
										try {
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
										}
										catch(Exception e) {
											try {
												if (driver.findElement(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
														.isDisplayed()) {
													Reporting.updateTestReport(
															"Server error came in cart page and the page was refreshed",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
													driver.navigate().refresh();
												}
											} catch (Exception e1) {
												Reporting.updateTestReport(
														"User was not in the cart page"
																+ " and caused timeout exception",
																CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										try {
											wait.until(ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
											WEL.clickonCheckOutButtonOnCartPage();
											try {
												wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
												String emailId=WEL.enterNewUserIdInCheckoutLoginRegistration();
												WEL.clickOnCreateAccountButtonInCheckoutLoginRegistration();
												try {
													wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='welCheckoutJourneyMainDiv']")));
													WEL.enterConfirmEmailIdInCheckout(emailId);
													WEL.enterPasswordInCheckout(excelOperation.getTestData("TC02", "WEL_Test_Data", "Password"));
													WEL.clickOnSaveAndContinue();
													try {
														wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[@id='shippingAddressTitle']/span")));
														Reporting.updateTestReport("User was in shipping step after successful registration",
																CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
													}
													catch(Exception e) {
														Reporting.updateTestReport("User was not in shipping step and caused timeout exception",
																CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
													}
												}
												catch(Exception e) {
													Reporting.updateTestReport(
															"User was not redirected to checkout page and caused timeout exception",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
												}
											}
											catch(Exception e) {
												Reporting.updateTestReport(
														"User was not redirected to checkout login/ registration page and caused timeout exception",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										catch(Exception e) {
											Reporting.updateTestReport(
													"Checkout button was not clickable in cart page and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}

									}
									catch(Exception e) {
										Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								catch(Exception e) {
									Reporting.updateTestReport("CMA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Explore course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("CMA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
		catch(Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 5/4/23
	 * @Description: Adds one physical product to cart and logs in during chekcout
	 */

	@Test
	public void TC04_User_Login_during_Checkout() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC04_User_Login_during_Checkout");
			LogTextFile.writeTestCaseStatus("TC04_User_Login_during_Checkout", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
					WEL.clickOnCMALinkOnHomepage();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='shop-courses-btn  ']")));
						driver.navigate().refresh();
						WEL.clickOnExploreCourseButton();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='price-block'])[1]")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/cma/products/platinum-cma-review-course/']"
										+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
								WEL.clickOnCMAViewCourseButton();
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(
											By.xpath("(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")));
									ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
								}
								catch(Exception e) {
									Reporting.updateTestReport("GET PLATINUM WILEY CMA COURSE -> button didn't appear",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								try {
									wait.until(ExpectedConditions.
											visibilityOfElementLocated(By.xpath("//label[@for='Print + eBook0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									try {
										wait.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
										WEL.clickOnAddToCartButton();
										try {
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
										}
										catch(Exception e) {
											try {
												if (driver.findElement(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
														.isDisplayed()) {
													Reporting.updateTestReport(
															"Server error came in cart page and the page was refreshed",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
													driver.navigate().refresh();
												}
											} catch (Exception e1) {
												Reporting.updateTestReport(
														"User was not in the cart page"
																+ " and caused timeout exception",
																CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										try {
											wait.until(ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
											WEL.clickonCheckOutButtonOnCartPage();
											try {
												wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
												WEL.enterExistingUserNameInCheckoutLoginPage(
														excelOperation.getTestData("TC04", "WEL_Test_Data", "Email_Address"));
												WEL.enterExistingUserPasswordInCheckoutLoginPage
												(excelOperation.getTestData("TC04", "WEL_Test_Data", "Password"));
												WEL.clickOnLoginButtonInCheckoutLoginPage();
												try {
													wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[@id='shippingAddressTitle']/span")));
													Reporting.updateTestReport("User was in shipping step after successful registration",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
												}
												catch(Exception e) {
													Reporting.updateTestReport("User was not in shipping step and caused timeout exception",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
												}

											}
											catch(Exception e) {
												Reporting.updateTestReport(
														"User was not redirected to checkout login/ registration page and caused timeout exception",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										catch(Exception e) {
											Reporting.updateTestReport(
													"Checkout button was not clickable in cart page and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}

									}
									catch(Exception e) {
										Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								catch(Exception e) {
									Reporting.updateTestReport("CMA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Explore course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("CMA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
		catch(Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 6/4/23
	 * @Description: Does the reset password during login
	 */
	@Test
	public void TC05_Forget_Password_during_Checkout() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC05_Forget_Password_during_Checkout");
			LogTextFile.writeTestCaseStatus("TC05_Forget_Password_during_Checkout", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
					WEL.clickOnCMALinkOnHomepage();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='shop-courses-btn  ']")));
						driver.navigate().refresh();
						WEL.clickOnExploreCourseButton();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='price-block'])[1]")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/cma/products/platinum-cma-review-course/']"
										+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
								WEL.clickOnCMAViewCourseButton();
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(
											By.xpath("(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")));
									ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
								}
								catch(Exception e) {
									Reporting.updateTestReport("GET PLATINUM WILEY CMA COURSE -> button didn't appear",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								try {
									wait.until(ExpectedConditions.
											visibilityOfElementLocated(By.xpath("//label[@for='Print + eBook0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									try {
										wait.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
										WEL.clickOnAddToCartButton();
										try {
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
										}
										catch(Exception e) {
											try {
												if (driver.findElement(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
														.isDisplayed()) {
													Reporting.updateTestReport(
															"Server error came in cart page and the page was refreshed",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
													driver.navigate().refresh();
												}
											} catch (Exception e1) {
												Reporting.updateTestReport(
														"User was not in the cart page"
																+ " and caused timeout exception",
																CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										try {
											wait.until(ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
											WEL.clickonCheckOutButtonOnCartPage();
											try {
												wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
												WEL.clickOnForgotPasswordLinkInCheckOutLoginPage();
												try {
													wait.until(ExpectedConditions.presenceOfElementLocated
															(By.xpath("//div[@class='forgotPasswordTitleDiv']")));
													WEL.enterEmailAddressOnForgotPassword(excelOperation.getTestData("TC05", "WEL_Test_Data", "Email_Address"));
													WEL.clickOnSubmitButtonnForgotPasswordPage();
													try {
														wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'"+
																excelOperation.getTestData("ResetPasswordMessageWEL", "Generic_Messages", "Data")+"')]")));
														Reporting.updateTestReport(excelOperation.getTestData("ResetPasswordMessageWEL", "Generic_Messages", "Data")
																+" : this message was shown after submitting the email", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
														driver.get(excelOperation.getTestData("Yopmail_URL", "Generic_Dataset", "Data"));
														WEL.enterEmailIdInYopmail(excelOperation.getTestData("TC05", "WEL_Test_Data", "Email_Address"));
														WEL.clickOnCheckInboxButton();
														int flag=EmailValidation.forgotPasswordEmailForWEL(driver, SS_path, WEL);
														if(flag==1) {
															WEL.enterNewPasswordInResetPassword(
																	excelOperation.getTestData("TC05", "WEL_Test_Data", "Password"));
															WEL.enterConfirmPasswordInResetPassword(
																	excelOperation.getTestData("TC05", "WEL_Test_Data", "Password"));
															WEL.clickOnResetPasswordSubmit();
															try {
																wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
																WEL.enterUserNameInLoginPage(excelOperation.getTestData("TC05", "WEL_Test_Data", "Email_Address"));
																WEL.enterPasswordInLoginPage(excelOperation.getTestData("TC05", "WEL_Test_Data", "Password"));
																WEL.clickOnStandaloneLoginButton();
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
																	WEL.clickOnSignOutButtonInMyAccountPage();
																} catch (Exception e) {
																	Reporting.updateTestReport("Failed display the Account Link", CaptureScreenshot.getScreenshot(SS_path),
																			StatusDetails.FAIL);
																}
															}
															catch(Exception e) {
																Reporting.updateTestReport("Username field in login form was not clickable and caused timeout exception" ,
																		CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
															}
														}
														else {
															Reporting.updateTestReport("No reset password mail was recieved in yopmail inbox", 
																	CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
														}

													}
													catch(Exception e) {
														Reporting.updateTestReport(excelOperation.getTestData("ResetPasswordMessageWEL", "Generic_Messages", "Data")
																+" : this message was not shown after submitting the email", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
													}
												}
												catch(Exception e) {
													Reporting.updateTestReport(
															"User was not redirected to Forgot password form page and caused timeout exception",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
												}

											}
											catch(Exception e) {
												Reporting.updateTestReport(
														"User was not redirected to checkout login/ registration page and caused timeout exception",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										catch(Exception e) {
											Reporting.updateTestReport(
													"Checkout button was not clickable in cart page and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}

									}
									catch(Exception e) {
										Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								catch(Exception e) {
									Reporting.updateTestReport("CMA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Explore course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("CMA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
		catch(Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 6/4/23
	 * @Description: Add some product to cart in WEL and add some promo
	 */

	@Test
	public void TC06_Add_Promo_In_Anonymous_Cart() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC06_Add_Promo_In_Anonymous_Cart");
			LogTextFile.writeTestCaseStatus("TC06_Add_Promo_In_Anonymous_Cart", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
					WEL.clickOnCMALinkOnHomepage();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='shop-courses-btn  ']")));
						driver.navigate().refresh();
						WEL.clickOnExploreCourseButton();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='price-block'])[1]")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/cma/products/platinum-cma-review-course/']"
										+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
								WEL.clickOnCMAViewCourseButton();
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(
											By.xpath("(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")));
									ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
								}
								catch(Exception e) {
									Reporting.updateTestReport("GET PLATINUM WILEY CMA COURSE -> button didn't appear",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								try {
									wait.until(ExpectedConditions.
											visibilityOfElementLocated(By.xpath("//label[@for='Print + eBook0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									try {
										wait.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
										WEL.clickOnAddToCartButton();
										try {
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
										}
										catch(Exception e) {
											try {
												if (driver.findElement(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
														.isDisplayed()) {
													Reporting.updateTestReport(
															"Server error came in cart page and the page was refreshed",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
													driver.navigate().refresh();
												}
											} catch (Exception e1) {
												Reporting.updateTestReport(
														"User was not in the cart page"
																+ " and caused timeout exception",
																CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
										if(driver.findElement(By.xpath("//div[@class='row no-margin noPadding marginTop10 width100"
												+ " subscriptions-subtotal-value cartPageDiscountPrice']")).isDisplayed())
											WEL.clickOnRemoveCouponInCartPage();
										ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
										WEL.clickOnAddDiscountLink();
										WEL.enterDiscountValueInCartPage(excelOperation.getTestData("WEL_Coupon",
												"Generic_Dataset", "Data"));
										WEL.clickOnDiscountApplyButtonInCartPage();
										try {
											wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
													(By.id("cartPageSuccessCouponDiv")));
											Reporting.updateTestReport("The coupon code was successfuly applied",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
										}
										catch(Exception e) {
											Reporting.updateTestReport("The coupon code  couldn't be applied",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									}
									catch(Exception e) {
										Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								catch(Exception e) {
									Reporting.updateTestReport("CMA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Explore course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("CMA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
		catch(Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 6/4/23
	 * @Description: Add some product to cart in WEL and add Student discount promo
	 */

	@Test
	public void TC07_Add_Student_Discount_In_Anonymous_Cart() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC07_Add_Student_Discount_In_Anonymous_Cart");
			LogTextFile.writeTestCaseStatus("TC07_Add_Student_Discount_In_Anonymous_Cart", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
								+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
										+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions.
											visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									try {
										wait.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//span[@class='apply-discount-link']")));
										WEL.clickOnApplyStudentDiscountLink();
										try {
											wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
													"//p[@class='current-price-link']/a[contains(text(),'Switch to student discount price')]")));
											WEL.clickOnSwitchToStudentDiscountLink();
											try {
												wait.until(ExpectedConditions.elementToBeClickable(
														By.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
												WEL.clickOnAddToCartButton();
												try {
													wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
												}
												catch(Exception e) {
													try {
														if (driver.findElement(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
																.isDisplayed()) {
															Reporting.updateTestReport(
																	"Server error came in cart page and the page was refreshed",
																	CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
															driver.navigate().refresh();
														}
													} catch (Exception e1) {
														Reporting.updateTestReport(
																"User was not in the cart page"
																		+ " and caused timeout exception",
																		CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
													}
												}
												ScrollingWebPage.PageScrolldown(driver, 0, 400, SS_path);
												try {
													wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
															("//div[@class='col-6 noPadding navyBlueLabel' and contains(text(),'STUDENT')]")));
													Reporting.updateTestReport("Student discount was applied in cart page", 
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
												}
												catch(Exception e) {
													Reporting.updateTestReport("Student discount was not applied in cart page", 
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
												}

											}
											catch(Exception e) {
												Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										catch(Exception e){
											Reporting.updateTestReport(
													"Switch to student discount link was not clickable and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									}
									catch(Exception e) {
										Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								catch(Exception e) {
									Reporting.updateTestReport("CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		}
		catch(Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 6/4/23
	 * @Description: Add some product to cart in WEL and add some promo
	 */

	@Test
	public void TC08_Add_to_cart_multiple_products_and_promotion_for_existing_user() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC08_Add_to_cart_multiple_products_and_promotion_for_existing_user");
			LogTextFile.writeTestCaseStatus("TC08_Add_to_cart_multiple_products_and_promotion_for_existing_user", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
								+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
										+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions.
											visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									try {
										wait.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//span[@class='apply-discount-link']")));
										WEL.clickOnApplyStudentDiscountLink();
										try {
											wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
													"//p[@class='current-price-link']/a[contains(text(),'Switch to student discount price')]")));
											WEL.clickOnSwitchToStudentDiscountLink();
											try {
												wait.until(ExpectedConditions.elementToBeClickable(
														By.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
												WEL.clickOnAddToCartButton();
												try {
													wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
												}
												catch(Exception e) {
													try {
														if (driver.findElement(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
																.isDisplayed()) {
															Reporting.updateTestReport(
																	"Server error came in cart page and the page was refreshed",
																	CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
															driver.navigate().refresh();
														}
													} catch (Exception e1) {
														Reporting.updateTestReport(
																"User was not in the cart page"
																		+ " and caused timeout exception",
																		CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
													}
												}
												ScrollingWebPage.PageScrolldown(driver, 0, 400, SS_path);
												try {
													wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
															("(//div[@class='col-6 noPadding navyBlueLabel' and contains(text(),'STUDENT')])[1]")));
													Reporting.updateTestReport("Student discount was applied in cart page", 
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
													driver.get(WEL_Homepage_URL);
													try {
														wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
														ScrollingWebPage.PageDown(driver, SS_path);
														try {
															wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCFA' and @data-key='0']")));
															WEL.clickOnCFALinkOnHomepage();
															try {
																wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='shop-courses-btn  ']")));
																driver.navigate().refresh();
																WEL.clickOnExploreCourseButton();
																try {
																	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
																			"(//div[@class='card-title course-pkg-cards-heading other-block'])[1]")));
																	ScrollingWebPage.PageDown(driver, SS_path);
																	try {
																		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/cfa/products/']"
																				+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
																		WEL.clickOnCFAViewCourseButton();
																		try {
																			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
																			ScrollingWebPage.PageDown(driver, SS_path);
																			try {
																				wait.until(ExpectedConditions.elementToBeClickable(
																						By.xpath("//a[@href='/cfa/products/level-1/platinum-cfa-course/' and contains(text(),'View Course')]")));
																				WEL.clickOnCFAViewCourseLink();
																				try {
																					wait.until(ExpectedConditions.
																							visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
																					ScrollingWebPage.PageDown(driver, SS_path);
																					try {
																						wait.until(ExpectedConditions.elementToBeClickable(
																								By.xpath("//span[@class='apply-discount-link']")));
																						WEL.clickOnApplyStudentDiscountLink();
																						try {
																							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
																									"//p[@class='current-price-link']/a[contains(text(),'Switch to student discount price')]")));
																							WEL.clickOnSwitchToStudentDiscountLink();
																							try {
																								wait.until(ExpectedConditions.elementToBeClickable(
																										By.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
																								WEL.clickOnAddToCartButton();
																								try {
																									wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
																								}
																								catch(Exception e) {
																									try {
																										if (driver.findElement(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
																												.isDisplayed()) {
																											Reporting.updateTestReport(
																													"Server error came in cart page and the page was refreshed",
																													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
																											driver.navigate().refresh();
																										}
																									} catch (Exception e1) {
																										Reporting.updateTestReport(
																												"User was not in the cart page"
																														+ " and caused timeout exception",
																														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
																									}
																								}
																								ScrollingWebPage.PageScrolldown(driver, 0, 400, SS_path);
																								try {
																									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
																											("(//div[@class='col-6 noPadding navyBlueLabel' and contains(text(),'STUDENT')])[1]")));
																									Reporting.updateTestReport("Student discount was applied in cart page", 
																											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
																									try {
																										wait.until(ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
																										WEL.clickonCheckOutButtonOnCartPage();
																										try {
																											wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
																											WEL.enterExistingUserNameInCheckoutLoginPage(
																													excelOperation.getTestData("TC08", "WEL_Test_Data", "Email_Address"));
																											WEL.enterExistingUserPasswordInCheckoutLoginPage
																											(excelOperation.getTestData("TC08", "WEL_Test_Data", "Password"));
																											WEL.clickOnLoginButtonInCheckoutLoginPage();
																											try {
																												wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[@id='shippingAddressTitle']/span")));
																												Reporting.updateTestReport("User was in shipping step after successful registration",
																														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
																											}
																											catch(Exception e) {
																												Reporting.updateTestReport("User was not in shipping step and caused timeout exception",
																														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
																											}

																										}
																										catch(Exception e) {
																											Reporting.updateTestReport(
																													"User was not redirected to checkout login/ registration page and caused timeout exception",
																													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
																										}
																									}
																									catch(Exception e) {
																										Reporting.updateTestReport(
																												"Checkout button was not clickable in cart page and caused timeout exception",
																												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
																									}
																								}
																								catch(Exception e) {
																									Reporting.updateTestReport("Student discount was not applied in cart page", 
																											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
																								}


																							}
																							catch(Exception e) {
																								Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ",
																										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
																							}
																						}
																						catch(Exception e){
																							Reporting.updateTestReport(
																									"Switch to student discount link was not clickable and caused timeout exception",
																									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
																						}
																					}
																					catch(Exception e) {
																						Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ",
																								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
																					}
																				}
																				catch(Exception e) {
																					Reporting.updateTestReport("CIA Product details page didn't appear and caused timeout exception",
																							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
																				}


																			}
																			catch(Exception e) {
																				Reporting.updateTestReport("The View Course link in CFA PDP was not clickable and caused timeout exception ",
																						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
																			}
																		}
																		catch(Exception e) {
																			Reporting.updateTestReport("After clicking on View course button, the page didn't show the cfa product title and caused timeout exception ",
																					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
																		}
																	}
																	catch(Exception e) {
																		Reporting.updateTestReport("View Course button on product page was not clickable and caused timeout exception ",
																				CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
																	}
																}
																catch(Exception e) {
																	Reporting.updateTestReport("After clicking on explore course button, the page didn't show the product title and caused timeout exception ",
																			CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
																}
															}
															catch(Exception e) {
																Reporting.updateTestReport("Explore course button on product page was not clickable and caused timeout exception ",
																		CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
															}
														}
														catch(Exception e) {
															Reporting.updateTestReport("CFA Link on homepage was not clickable and caused timeout exception ",
																	CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
														}
													}
													catch(Exception e) {
														Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
																CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
													}

												}
												catch(Exception e) {
													Reporting.updateTestReport("Student discount was not applied in cart page", 
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
												}

											}
											catch(Exception e) {
												Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										catch(Exception e){
											Reporting.updateTestReport(
													"Switch to student discount link was not clickable and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									}
									catch(Exception e) {
										Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								catch(Exception e) {
									Reporting.updateTestReport("CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Explore course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
		catch(Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 6/4/23
	 * @Description: Add some product to cart in WEL and add some GOVT Promo
	 */

	@Test
	public void TC09_Extra_discount_coupon_code_GOVT() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC09_Extra_discount_coupon_code_GOVT");
			LogTextFile.writeTestCaseStatus("TC08_Add_to_cart_multiple_products_and_promotion_for_existing_user", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
								+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
										+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions.
											visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);

									try {
										wait.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
										WEL.clickOnAddToCartButton();
										try {
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPageMainTitle")));
										}
										catch(Exception e) {
											try {
												if (driver.findElement(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
														.isDisplayed()) {
													Reporting.updateTestReport(
															"Server error came in cart page and the page was refreshed",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
													driver.navigate().refresh();
												}
											} catch (Exception e1) {
												Reporting.updateTestReport(
														"User was not in the cart page"
																+ " and caused timeout exception",
																CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
										if(driver.findElement(By.xpath("//div[@class='row no-margin noPadding marginTop10 width100"
												+ " subscriptions-subtotal-value cartPageDiscountPrice']")).isDisplayed())
											WEL.clickOnRemoveCouponInCartPage();
										ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
										WEL.clickOnAddDiscountLink();
										WEL.enterDiscountValueInCartPage(excelOperation.getTestData("WEL_Coupon",
												"Generic_Dataset", "Data"));
										WEL.clickOnDiscountApplyButtonInCartPage();
										try {
											wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
													(By.id("cartPageSuccessCouponDiv")));
											Reporting.updateTestReport("The coupon code was successfuly applied",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
										}
										catch(Exception e) {
											Reporting.updateTestReport("The coupon code  couldn't be applied",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}

									}
									catch(Exception e) {
										Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								catch(Exception e) {
									Reporting.updateTestReport("CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Explore course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
		catch(Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

	/*
	 * @Date: 14/4/23
	 * @Description: Places free trial order for CMA CFA CPA
	 */
	@Test
	public void TC10_PlaceOrder_CPA_CMA_CFA_Free_Trail_For_NewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC10_PlaceOrder_CPA_CMA_CFA_Free_Trail_For_NewUser");
			LogTextFile.writeTestCaseStatus("TC10_PlaceOrder_CPA_CMA_CFA_Free_Trail_For_NewUser", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WEL_Homepage_URL);
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCPA' and @data-key='0']")));
					WEL.clickOnCFALinkOnHomepage();
					WEL.clickOnFreeTrialButton();
					WEL.enterFreeTrialFirstName(excelOperation.getTestData("TC10", "WEL_Test_Data", "First_Name"));
					WEL.enterFreeTrialLastName(excelOperation.getTestData("TC10", "WEL_Test_Data", "Last_Name"));
					WEL.enterFreeTrialNewUser();
					ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
					WEL.selectFreeTrialCountry(excelOperation.getTestData("TC10", "WEL_Test_Data", "Shipping_Country"));
					ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
					WEL.selectFreeTrialState(excelOperation.getTestData("TC10", "WEL_Test_Data", "Shipping_State"));
					ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
					WEL.enterFreeTrialPassword(excelOperation.getTestData("TC10", "WEL_Test_Data", "Password"));
					ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
					WEL.clickOnFreeTrialTermsAndCOndtionsCheckBox();
					ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
					WEL.clickFreeTrialSignUpCheckBox();
					WEL.clickOnFreeTrialSignInButton();
					String freeTrialConfirmationMessage = WEL.checkFreeTrialCPAText();
					if (freeTrialConfirmationMessage.equals("You’re almost set!"))
						Reporting.updateTestReport(
								"CPA FreeTail is successfully completed and page having text You’re almost set!",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport("Failed to Load the CPA FreeTail Page",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

					WEL.clickOnFreeTrialWELIcon();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
						ScrollingWebPage.PageDown(driver, SS_path);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
							WEL.clickOnCMALinkOnHomepage();
							WEL.clickOnFreeTrialButton();
							WEL.enterFreeTrialFirstName(excelOperation.getTestData("TC10", "WEL_Test_Data", "First_Name"));
							WEL.enterFreeTrialLastName(excelOperation.getTestData("TC10", "WEL_Test_Data", "Last_Name"));
							WEL.enterFreeTrialNewUser();
							ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
							WEL.selectFreeTrialCountry(excelOperation.getTestData("TC10", "WEL_Test_Data", "Shipping_Country"));
							ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);

							WEL.selectFreeTrialState(excelOperation.getTestData("TC10", "WEL_Test_Data", "Shipping_State"));
							ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);

							WEL.enterFreeTrialPassword(excelOperation.getTestData("TC10", "WEL_Test_Data", "Password"));
							ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);

							WEL.clickOnFreeTrialTermsAndCOndtionsCheckBox();
							ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);

							WEL.clickFreeTrialSignUpCheckBox();
							WEL.clickOnFreeTrialSignInButton();
							WEL.checkFreeTrialCPAText();
							if (freeTrialConfirmationMessage.equals("You’re almost set!"))
								Reporting.updateTestReport(
										"CMA FreeTail is successfully completed and page having text You’re almost set!",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
							else
								Reporting.updateTestReport("Failed to Load the CMA FreeTail Page",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							WEL.clickOnFreeTrialWELIcon();
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
								ScrollingWebPage.PageDown(driver, SS_path);
								try {
									wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCFA' and @data-key='0']")));
									WEL.clickOnCFALinkOnHomepage();
									WEL.clickOnFreeTrialButton();
									WEL.enterFreeTrialFirstName(excelOperation.getTestData("TC10", "WEL_Test_Data", "First_Name"));
									WEL.enterFreeTrialLastName(excelOperation.getTestData("TC10", "WEL_Test_Data", "Last_Name"));
									WEL.enterFreeTrialNewUser();
									ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
									WEL.selectFreeTrialCountry(excelOperation.getTestData("TC10", "WEL_Test_Data", "Shipping_Country"));
									ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
									WEL.selectFreeTrialState(excelOperation.getTestData("TC10", "WEL_Test_Data", "Shipping_State"));
									ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
									WEL.enterFreeTrialPassword(excelOperation.getTestData("TC10", "WEL_Test_Data", "Password"));
									ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
									WEL.clickOnFreeTrialTermsAndCOndtionsCheckBox();
									ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
									WEL.clickFreeTrialSignUpCheckBox();
									WEL.clickOnFreeTrialSignInButton();
									if (WEL.checkFreeTrialCPAText().equals("You’re almost set!"))
										Reporting.updateTestReport(
												"CFA FreeTail is successfully completed and page having text You’re almost set!",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
									else
										Reporting.updateTestReport("Failed to Load the CFA FreeTail Page",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
								}

								catch (Exception e) {
									Reporting.updateTestReport("Failed to click on CFA Product due to  timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("The homepage was not loaded and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Failed to click on CMA Product due to  timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("The homepage was not loaded and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Failed to click on CPA Product due to  timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("The homepage was not loaded and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}

}
