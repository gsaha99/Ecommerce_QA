package TestSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import PageObjectRepo.app_WEL_Repo;
import utilities.*;

public class WEL_Node_Test_Suite extends DriverModule {

	app_WEL_Repo WEL;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	public static String WEL_Homepage_URL = excelOperation.getTestData("WEL_Homepage_URL", "Generic_Dataset", "Data");

	@BeforeTest
	public void initializeRepo() {
		WEL = PageFactory.initElements(driver, app_WEL_Repo.class);

	}

	@BeforeMethod
	public void nameBefore(Method method) {
		System.out.println("Test case: " + method.getName() + " execution started");
	}

	@AfterMethod
	public void nameAfter(Method method) {
		System.out.println("Test case: " + method.getName() + " execution completed");
	}

	/*
	 * @Date: 5/4/23
	 *
	 * @Description: Registers one user through standalone registration
	 */
	@Test
	public void TC01_Standalone_User_Registration() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC01_Standalone_User_Registration");
			LogTextFile.writeTestCaseStatus("TC01_Standalone_User_Registration", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC01", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//a[@href='/welstorefront/register']")));
				WEL.clickOnCreateOneLinkInLoginPage();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("register.studentFirstName")));
					WEL.enterFirstNameInRegistrationForm(
							excelOperation.getTestData("TC01", "WEL_Test_Data", "First_Name"));
					WEL.enterLastNameInRegistrationForm(
							excelOperation.getTestData("TC01", "WEL_Test_Data", "Last_Name"));
					String emailId = WEL.enterEmailIdInRegistrationForm();
					WEL.enterPasswordInRegistrationForm(
							excelOperation.getTestData("TC01", "WEL_Test_Data", "Password"));
					WEL.clickOnCheckboxInRegistrationForm();
					WEL.clickOnCreateAccountButtonInRegistrationForm();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@href='/welstorefront/my-account'])[1]")));
						Reporting.updateTestReport("Account was created successfully",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

						excelOperation.updateTestData("TC01", "WEL_Test_Data", "Email_Address", emailId);
						excelOperation.updateTestData("TC02", "WEL_Test_Data", "Email_Address", emailId);
						excelOperation.updateTestData("TC04", "WEL_Test_Data", "Email_Address", emailId);
						excelOperation.updateTestData("TC05", "WEL_Test_Data", "Email_Address", emailId);
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Account was not created caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"The registration first name field was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("User was not on the login page and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));


		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}


	/*
	 * @Date: 5/4/23
	 *
	 * @Description: This test cases is to Login with newly Registered user
	 */
	@Test
	public void TC02_Login_With_Newly_Registered_User() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC02_Login_With_Newly_Registered_User");
			LogTextFile.writeTestCaseStatus("TC02_Login_With_Newly_Registered_User", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC01", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.id("j_username")));
				WEL.enterUserNameInLoginPage(excelOperation.getTestData("TC02", "WEL_Test_Data", "Email_Address"));
				WEL.enterPasswordInLoginPage(excelOperation.getTestData("TC02", "WEL_Test_Data", "Password"));
				WEL.clickOnStandaloneLoginButton();

			} catch (Exception e) {
				Reporting.updateTestReport(
						"Username field in login form was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}

	}

	/*
	 * @Date: 5/4/23
	 *
	 * @Description: Adds one physical product to cart and registers one new user
	 */

	@Test
	public void TC03_User_Registration_During_Checkout_for_Physical_Cart() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_User_Registration_During_Checkout_for_Physical_Cart");
			LogTextFile.writeTestCaseStatus("TC03_User_Registration_During_Checkout_for_Physical_Cart", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC03", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(By.xpath(
										"//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.id("checkoutLogRegPageTitle")));
						String emailId = WEL.enterNewUserIdInCheckoutLoginRegistration();
						WEL.clickOnCreateAccountButtonInCheckoutLoginRegistration();
						try {
							wait.until(ExpectedConditions.presenceOfElementLocated(
									By.xpath("//div[@class='welCheckoutJourneyMainDiv']")));
							WEL.enterConfirmEmailIdInCheckout(emailId);
							WEL.enterPasswordInCheckout(excelOperation.getTestData("TC02",
									"WEL_Test_Data", "Password"));
							WEL.clickOnSaveAndContinue();
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//h5[@id='shippingAddressTitle']/span")));
								Reporting.updateTestReport(
										"User was in shipping step after successful registration",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.PASS);
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath(
													"//div[@id='orderSummaryProductTotalValue']")));

									String orderprice = WEL
											.fetchFirstProductPriceInOrderSummary();
									if (orderprice.contains(","))
										orderprice = orderprice.replace(",", "");
									BigDecimal orderproductprice = new BigDecimal(
											orderprice.substring(1));

									String discount = WEL.fetchDiscountInOrderReview();
									if (discount.contains(","))
										discount = discount.replace(",", "");
									BigDecimal orderpriceafterdiscount = new BigDecimal(
											discount.substring(1));

									BigDecimal productpriceafterdiscount = orderproductprice
											.subtract(orderpriceafterdiscount);

									String totalorderReview = WEL.fetchTotalInOrderReview();
									if (totalorderReview.contains(","))
										totalorderReview = totalorderReview.replace(",",
												"");
									BigDecimal orderTotalPrice = new BigDecimal(
											totalorderReview.substring(1));

									if (productpriceafterdiscount
											.compareTo(orderTotalPrice) == 0)
										Reporting.updateTestReport(
												"First Product price - Discount "
														+ " = Order total in Order Review step",
												CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.PASS);
									else
										Reporting.updateTestReport(
												"First Product price + Tax "
														+ " is not equal to Order total in Order Review step",
												CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.FAIL);

								} catch (Exception e) {
									Reporting.updateTestReport(
											"Order Summary tab was not visible"
													+ e.getMessage(),
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"User was not in shipping step and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not redirected to checkout page and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not redirected to checkout login/ registration page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}

	/*
	 * @Date: 5/4/23
	 *
	 * @Description: Adds one physical product to cart and logs in during chekcout
	 */

	@Test
	public void TC04_User_Login_during_Checkout() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC04_User_Login_during_Checkout");
			LogTextFile.writeTestCaseStatus("TC04_User_Login_during_Checkout", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC04", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				try {
					wait.until(
							ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
						WEL.enterExistingUserNameInCheckoutLoginPage(excelOperation
								.getTestData("TC04", "WEL_Test_Data", "Email_Address"));
						WEL.enterExistingUserPasswordInCheckoutLoginPage(excelOperation
								.getTestData("TC04", "WEL_Test_Data", "Password"));
						WEL.clickOnLoginButtonInCheckoutLoginPage();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h5[@id='shippingAddressTitle']/span")));
							Reporting.updateTestReport(
									"User was in shipping step after successful registration",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.PASS);
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By
										.xpath("//div[@id='orderSummaryProductTotalValue']")));

								String orderprice = WEL.fetchFirstProductPriceInOrderSummary();
								if (orderprice.contains(","))
									orderprice = orderprice.replace(",", "");
								BigDecimal orderproductprice = new BigDecimal(
										orderprice.substring(1));

								String discount = WEL.fetchDiscountInOrderReview();
								if (discount.contains(","))
									discount = discount.replace(",", "");
								BigDecimal orderpriceafterdiscount = new BigDecimal(
										discount.substring(1));

								BigDecimal productpriceafterdiscount = orderproductprice
										.subtract(orderpriceafterdiscount);

								String totalorderReview = WEL.fetchTotalInOrderReview();
								if (totalorderReview.contains(","))
									totalorderReview = totalorderReview.replace(",", "");
								BigDecimal orderTotalPrice = new BigDecimal(
										totalorderReview.substring(1));

								if (productpriceafterdiscount.compareTo(orderTotalPrice) == 0)
									Reporting.updateTestReport(
											"First Product price - Discount "
													+ " = Order total in Order Review step",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.PASS);
								else
									Reporting.updateTestReport("First Product price + Tax "
													+ " is not equal to Order total in Order Review step",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);

							} catch (Exception e) {
								Reporting.updateTestReport(
										"Order Summary tab was not visible" + e.getMessage(),
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not in shipping step and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not redirected to checkout login/ registration page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}


			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}

	/*
	 * @Date: 6/4/23
	 *
	 * @Description: Does the reset password during login
	 */
	@Test
	public void TC05_Forget_Password_during_Checkout() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_Forget_Password_during_Checkout");
			LogTextFile.writeTestCaseStatus("TC05_Forget_Password_during_Checkout", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC05", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				try {
					wait.until(
							ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
						WEL.clickOnForgotPasswordLinkInCheckOutLoginPage();
						try {
							wait.until(ExpectedConditions.presenceOfElementLocated(
									By.xpath("//div[@class='forgotPasswordTitleDiv']")));
							WEL.enterEmailAddressOnForgotPassword(excelOperation
									.getTestData("TC05", "WEL_Test_Data", "Email_Address"));
							WEL.clickOnSubmitButtonnForgotPasswordPage();
							try {
								wait.until(
										ExpectedConditions.visibilityOfElementLocated(
												By.xpath("//p[contains(text(),'"
														+ excelOperation.getTestData(
														"ResetPasswordMessageWEL",
														"Generic_Messages", "Data")
														+ "')]")));
								Reporting.updateTestReport(excelOperation.getTestData(
												"ResetPasswordMessageWEL", "Generic_Messages", "Data")
												+ " : this message was shown after submitting the email",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.PASS);
								/*driver.get(excelOperation.getTestData("Yopmail_URL",
										"Generic_Dataset", "Data"));
								WEL.enterEmailIdInYopmail(excelOperation.getTestData("TC05",
										"WEL_Test_Data", "Email_Address"));
								WEL.clickOnCheckInboxButton();
								int flag = EmailValidation.forgotPasswordEmailForWEL(driver,
										SS_path, WEL);
								if (flag == 1) {
									driver.navigate().refresh();
									WEL.enterNewPasswordInResetPassword(excelOperation
											.getTestData("TC05", "WEL_Test_Data", "Password"));
									WEL.enterConfirmPasswordInResetPassword(excelOperation
											.getTestData("TC05", "WEL_Test_Data", "Password"));
									WEL.clickOnResetPasswordSubmit();
								} else {
									Reporting.updateTestReport(
											"No reset password mail was recieved in yopmail inbox",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
								}*/

							} catch (Exception e) {
								Reporting.updateTestReport(excelOperation.getTestData(
												"ResetPasswordMessageWEL", "Generic_Messages", "Data")
												+ " : this message was not shown after submitting the email",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
							driver.get(CommonFunctions.concatenateURLWithNodeIP("TC01", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
							WEL.enterExistingUserNameInCheckoutLoginPage(excelOperation
									.getTestData("TC05", "WEL_Test_Data", "Email_Address"));
							WEL.enterExistingUserPasswordInCheckoutLoginPage(excelOperation
									.getTestData("TC05", "WEL_Test_Data", "Password"));
							WEL.clickOnLoginButtonInCheckoutLoginPage();
						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not redirected to Forgot password form page and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not redirected to checkout login/ registration page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}
	/*
	 * @Date: 6/4/23
	 *
	 * @Description: Add some product to cart in WEL and add some promo
	 */

	@Test
	public void TC06_Add_Promo_In_Anonymous_Cart() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC06_Add_Promo_In_Anonymous_Cart");
			LogTextFile.writeTestCaseStatus("TC06_Add_Promo_In_Anonymous_Cart", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC06", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
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
				if (driver.findElement(
								By.xpath("//div[@class='row no-margin noPadding marginTop10 width100"
										+ " subscriptions-subtotal-value cartPageDiscountPrice']"))
						.isDisplayed())
					WEL.clickOnRemoveCouponInCartPage();
				ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
				WEL.clickOnAddDiscountLink();
				WEL.enterDiscountValueInCartPage(
						excelOperation.getTestData("WEL_Coupon", "Generic_Dataset", "Data"));
				WEL.clickOnDiscountApplyButtonInCartPage();
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderTotalOnCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of the price is not match with the subtotal in cart page due to GOVT discount is aplied in Cart Page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				try {
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
							By.id("cartPageSuccessCouponDiv")));
					Reporting.updateTestReport("The coupon code was successfuly applied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				} catch (Exception e) {
					Reporting.updateTestReport("The coupon code  couldn't be applied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}

	/*
	 * @Date: 6/4/23
	 *
	 * @Description: Add some product to cart in WEL and add Student discount promo
	 */

	@Test
	public void TC07_Add_Student_Discount_In_Anonymous_Cart() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC07_Add_Student_Discount_In_Anonymous_Cart");
			LogTextFile.writeTestCaseStatus("TC07_Add_Student_Discount_In_Anonymous_Cart", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC07", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//label[@for='student-discount-0']")));
				WEL.clickOnApplyStudentDiscountLink();

				BigDecimal price = new BigDecimal(
						WEL.fetchProductPriceInPDP().substring(1));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//div[@class='pdp-addtocart-div']")));
					WEL.clickOnAddToCartButton();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("cartPageMainTitle")));
					} catch (Exception e) {
						try {
							if (driver
									.findElement(By.xpath(
											"//h1[contains(text(),'SERVER ERROR (500)')]"))
									.isDisplayed()) {
								Reporting.updateTestReport(
										"Server error came in cart page and the page was refreshed",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.INFO);
								driver.navigate().refresh();
							}
						} catch (Exception e1) {
							Reporting.updateTestReport(
									"User was not in the cart page"
											+ " and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					}
					ScrollingWebPage.PageScrolldown(driver, 0, 400, SS_path);
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//div[@class='col-6 noPadding navyBlueLabel' and contains(text(),'STUDENT')]")));
						Reporting.updateTestReport(
								"Student discount was applied in cart page",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.PASS);

					} catch (Exception e) {
						Reporting.updateTestReport(
								"Student discount was not applied in cart page",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}

					BigDecimal subtotal = new BigDecimal(
							WEL.fetchOrderSubTotalInCartPage().substring(1));
					if (price.compareTo(subtotal) == 0)
						Reporting.updateTestReport(
								"The addition of all the products' price is same as the subtotal in cart page",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.PASS);
					else
						Reporting.updateTestReport(
								"The addition of the price is not match with the subtotal in cart page due to STUDENT discount is aplied in Cart Page",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
				} catch (Exception e) {
					Reporting.updateTestReport(
							"Add To Cart button on product page was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport(
						"Unable to click on Student Radio button timeout exception "+e.getMessage(),
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}

	/*
	 * @Date: 6/4/23
	 *
	 * @Description: Add some product to cart in WEL and add some promo
	 */

	@Test
	public void TC08_Add_to_cart_multiple_products_and_promotion_for_existing_user() throws IOException {
		try {
			Reporting.test = Reporting.extent
					.createTest("TC08_Add_to_cart_multiple_products_and_promotion_for_existing_user");
			LogTextFile.writeTestCaseStatus("TC08_Add_to_cart_multiple_products_and_promotion_for_existing_user",
					"Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			String urls[] = excelOperation.getTestData("TC08", "WEL_Test_Data", "URL").split(",");
			driver.get(WEL.AddingMultipleProducts(urls[0]));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='student-discount-0']")));
				WEL.clickOnApplyStudentDiscountLink();
				BigDecimal firstproductprice = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='pdp-addtocart-div']")));
					driver.navigate().refresh();
					WEL.clickOnAddToCartButton();
					driver.get(WEL.AddingMultipleProducts(urls[1]));
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='student-discount-0']")));
						WEL.clickOnApplyStudentDiscountLink();
						BigDecimal secondproductprice = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='pdp-addtocart-div']")));
							WEL.clickOnAddToCartButton();
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='col-6 noPadding navyBlueLabel' and contains(text(),'STUDENT')])[1]")));
								Reporting.updateTestReport("Student discount was applied in cart page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								BigDecimal subtotal = new BigDecimal(WEL.fetchOrderTotalOnCartPage().substring(1));
								if (firstproductprice.add(secondproductprice).compareTo(subtotal) == 0)
									Reporting.updateTestReport("The addition of all the products' price is same as the subtotal in cart page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								else
									Reporting.updateTestReport("The addition of the price is not match with the subtotal in cart page due to STUDENT discount is aplied in Cart Page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								try {
									wait.until(ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
									WEL.clickonCheckOutButtonOnCartPage();
									try {
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
										WEL.enterExistingUserNameInCheckoutLoginPage(excelOperation.getTestData("TC08", "WEL_Test_Data", "Email_Address"));
										WEL.enterExistingUserPasswordInCheckoutLoginPage(excelOperation.getTestData("TC08", "WEL_Test_Data", "Password"));
										WEL.clickOnLoginButtonInCheckoutLoginPage();
										try {
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[@id='shippingAddressTitle']/span")));
											Reporting.updateTestReport("User was in shipping step after successful registration", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
										} catch (Exception e) {
											Reporting.updateTestReport("User was not in shipping step and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
										try {
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='orderSummaryProductTotalValue']")));
											BigDecimal firstproductprice2 = new BigDecimal(WEL.fetchFirstProductPriceInOrderReview().substring(1));
											String discount = WEL.fetchDiscountInOrderReview();
											if (discount.contains(",")) discount = discount.replace(",", "");
											BigDecimal discountinorderreview = new BigDecimal(discount.substring(1));
											String totalorderReview = WEL.fetchTotalInOrderReview();
											if (totalorderReview.contains(",")) totalorderReview = totalorderReview.replace(",", "");
											BigDecimal orderTotalPrice1 = new BigDecimal(totalorderReview.substring(1));
											if (firstproductprice2.subtract(discountinorderreview).compareTo(orderTotalPrice1) == 0)
												Reporting.updateTestReport("First Product price + Second Product price -discount " + " = Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
											else
												Reporting.updateTestReport("First Product price +second product price- discount is not equal to Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										} catch (Exception e) {
											Reporting.updateTestReport("Order summary tab was not visible" + e.getMessage(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}

									} catch (Exception e) {
										Reporting.updateTestReport("User was not redirected to checkout login/ registration page and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport("Checkout button was not clickable in cart page and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("Student discount was not applied in cart page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}

				} catch (Exception e) {
					Reporting.updateTestReport("Student discount was not applied in cart page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Add To Cart button on product page was not clickable and caused timeout exception ", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}

	/*
	 * @Date: 6/4/23
	 *
	 * @Description: Add some product to cart in WEL and add some GOVT Promo
	 */

	@Test
	public void TC09_Extra_discount_coupon_code_GOVT() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC09_Extra_discount_coupon_code_GOVT");
			LogTextFile.writeTestCaseStatus("TC09_Extra_discount_coupon_code_GOVT",
					"Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC09", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
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
				if (driver.findElement(
								By.xpath("//div[@class='row no-margin noPadding marginTop10 width100"
										+ " subscriptions-subtotal-value cartPageDiscountPrice']"))
						.isDisplayed())
					WEL.clickOnRemoveCouponInCartPage();
				ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
				WEL.clickOnAddDiscountLink();
				WEL.enterDiscountValueInCartPage(
						excelOperation.getTestData("WEL_Coupon", "Generic_Dataset", "Data"));
				WEL.clickOnDiscountApplyButtonInCartPage();
				try {
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
							By.id("cartPageSuccessCouponDiv")));
					Reporting.updateTestReport("The coupon code was successfuly applied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				} catch (Exception e) {
					Reporting.updateTestReport("The coupon code  couldn't be applied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of the price is not match with the subtotal in cart page due to STUDENT discount is aplied in Cart Page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}

	/*
	 * @Date: 14/4/23
	 *
	 * @Description: Places free trial order for CMA CFA CPA
	 */
	@Test
	public void TC10_PlaceOrder_CPA_CMA_CFA_Free_Trail_For_NewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC10_PlaceOrder_CPA_CMA_CFA_Free_Trail_For_NewUser");
			LogTextFile.writeTestCaseStatus("TC10_PlaceOrder_CPA_CMA_CFA_Free_Trail_For_NewUser", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			String urls[] = excelOperation.getTestData("TC10", "WEL_Test_Data", "URL").split(",");
			driver.get(WEL.AddingMultipleProducts(urls[0]));
			driver.navigate().refresh();
			WEL.enterFreeTrialFirstName(excelOperation.getTestData("TC10", "WEL_Test_Data", "First_Name"));
			WEL.enterFreeTrialLastName(excelOperation.getTestData("TC10", "WEL_Test_Data", "Last_Name"));
			WEL.enterFreeTrialNewUser();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.selectFreeTrialCountry(excelOperation.getTestData("TC10", "WEL_Test_Data", "Shipping_Country"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.selectFreeTrialState(excelOperation.getTestData("TC10", "WEL_Test_Data", "Shipping_State"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.enterFreeTrialPassword(excelOperation.getTestData("TC10", "WEL_Test_Data", "Password"));
			WEL.enterFreeTrialConfirmPassword(excelOperation.getTestData("TC10", "WEL_Test_Data", "Confirm_Password"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			//ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.clickFreeTrialSignUpCheckBox();
			WEL.clickOnFreeTrialSignInButton();
			String freeTrialConfirmationMessage = WEL.checkFreeTrialCPAText();
			if (freeTrialConfirmationMessage.contains("You're Almost Set!"))
				Reporting.updateTestReport(
						"CPA FreeTail is successfully completed and page having text You’re almost set!",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

			else
				Reporting.updateTestReport("Failed to Load the CPA FreeTail Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
			driver.get(WEL.AddingMultipleProducts(urls[1]));

			WEL.enterFreeTrialFirstName(
					excelOperation.getTestData("TC10", "WEL_Test_Data", "First_Name"));
			WEL.enterFreeTrialLastName(
					excelOperation.getTestData("TC10", "WEL_Test_Data", "Last_Name"));
			WEL.enterFreeTrialNewUser();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.selectFreeTrialCountry(
					excelOperation.getTestData("TC10", "WEL_Test_Data", "Shipping_Country"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);

			WEL.selectFreeTrialState(
					excelOperation.getTestData("TC10", "WEL_Test_Data", "Shipping_State"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);

			WEL.enterFreeTrialPassword(excelOperation.getTestData("TC10", "WEL_Test_Data", "Password"));
			WEL.enterFreeTrialConfirmPassword(excelOperation.getTestData("TC10", "WEL_Test_Data", "Confirm_Password"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.clickFreeTrialSignUpCheckBox();
			WEL.clickOnFreeTrialSignInButton();
			WEL.checkFreeTrialCPAText();
			if (freeTrialConfirmationMessage.contains("You're Almost Set!"))
				Reporting.updateTestReport(
						"CMA FreeTail is successfully completed and page having text You’re almost set!",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

			else
				Reporting.updateTestReport("Failed to Load the CMA FreeTail Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
			driver.get(WEL.AddingMultipleProducts(urls[2]));
			WEL.clickOnFreeTrialButton();
			WEL.enterFreeTrialFirstName(
					excelOperation.getTestData("TC10", "WEL_Test_Data", "First_Name"));
			WEL.enterFreeTrialLastName(
					excelOperation.getTestData("TC10", "WEL_Test_Data", "Last_Name"));
			WEL.enterFreeTrialNewUser();
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.selectFreeTrialCountry(
					excelOperation.getTestData("TC10", "WEL_Test_Data", "Shipping_Country"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.selectFreeTrialState(
					excelOperation.getTestData("TC10", "WEL_Test_Data", "Shipping_State"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.selectShipCourse(excelOperation.getTestData("TC10", "WEL_Test_Data", "Course"));
			WEL.enterFreeTrialPassword(
					excelOperation.getTestData("TC10", "WEL_Test_Data", "Password"));
			WEL.enterFreeTrialConfirmPassword(excelOperation.getTestData("TC10", "WEL_Test_Data", "Confirm_Password"));
			ScrollingWebPage.PageScrolldown(driver, 0, 200, SS_path);
			WEL.clickFreeTrialSignUpCheckBox();
			WEL.clickOnFreeTrialSignInButton();
			if (WEL.checkFreeTrialCPAText().contains("You're Almost Set!"))
				Reporting.updateTestReport(
						"CFA FreeTail is successfully completed and page having text You’re almost set!",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			else
				Reporting.updateTestReport("Failed to Load the CFA FreeTail Page",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}

	/*
	 * @Date: 17/4/23
	 *
	 * @Description: Address SUggestion by Address Doctor in Shipping Page
	 */
	@Test
	public void TC11_Address_suggestionbyAddressDoctorinShippingpage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC11_Address_suggestionbyAddressDoctorinShippingpage");
			LogTextFile.writeTestCaseStatus("TC11_Address_suggestionbyAddressDoctorinShippingpage", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC11", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

				try {
					wait.until(
							ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
						WEL.enterExistingUserNameInCheckoutLoginPage(excelOperation
								.getTestData("TC11", "WEL_Test_Data", "Email_Address"));
						WEL.enterExistingUserPasswordInCheckoutLoginPage(excelOperation
								.getTestData("TC11", "WEL_Test_Data", "Password"));
						WEL.clickOnLoginButtonInCheckoutLoginPage();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h5[@id='shippingAddressTitle']/span")));
							WEL.ClickOnEnterNewAddressButtonOnShippingPage();
							WEL.firstName(excelOperation.getTestData("TC11", "WEL_Test_Data",
									"First_Name"));
							WEL.lastName(excelOperation.getTestData("TC11", "WEL_Test_Data",
									"Last_Name"));
							WEL.selectShipCountry(excelOperation.getTestData("TC11",
									"WEL_Test_Data", "Shipping_Country"));
							WEL.shipAddressLineOne(excelOperation.getTestData("TC11",
									"WEL_Test_Data", "Shipping_Address_line1"));
							WEL.shipTownCity(excelOperation.getTestData("TC11", "WEL_Test_Data",
									"Shipping_City/ Province"));
							WEL.enterState(excelOperation.getTestData("TC11", "WEL_Test_Data",
									"Shipping_State"));
							WEL.shipPostCode(excelOperation.getTestData("TC11", "WEL_Test_Data",
									"Shipping_Zip_Code"));
							WEL.shipPhonenumber(excelOperation.getTestData("TC11",
									"WEL_Test_Data", "Shipping_Phone_Number"));
							WEL.clickingOnSaveAndContinue();
							try {
								if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
										.isDisplayed())
									WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Failed to click Address on Address SUggestion due to timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.INFO);
							}

						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not in shipping step and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//div[@id='orderSummaryProductTotalValue']")));

							BigDecimal firstproductprice2 = new BigDecimal(
									WEL.fetchFirstProductPriceInOrderReview().substring(1));

							String discount = WEL.fetchDiscountInOrderReview();
							if (discount.contains(","))
								discount = discount.replace(",", "");
							BigDecimal discountinorderreview = new BigDecimal(
									discount.substring(1));
							String totalorderReview = WEL.fetchTotalInOrderReview();
							if (totalorderReview.contains(","))
								totalorderReview = totalorderReview.replace(",", "");
							BigDecimal orderTotalPrice1 = new BigDecimal(
									totalorderReview.substring(1));
							if (firstproductprice2.subtract(discountinorderreview)
									.compareTo(orderTotalPrice1) == 0)
								Reporting.updateTestReport(
										"First Product price + Second Product price -discount "
												+ " = Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.PASS);
							else
								Reporting.updateTestReport(
										"First Product price +second product price- discount is not equal to Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);

						} catch (Exception e) {
							Reporting.updateTestReport(
									"Order summary tab was not visible" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not redirected to checkout login/ registration page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					// WEL.clickOnEnterNewAddressButtonOnShippingPage();

				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}


			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}

	/*
	 * @Date: 17/4/23
	 *
	 * @Description: Address SUggestion by Address Doctor in Shipping Page
	 */
	@Test
	public void TC12_StudentVerification_ForphysicalCartof_USANDNonUS() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC12_StudentVerification_ForphysicalCartof_USANDNonUS");
			LogTextFile.writeTestCaseStatus("TC12_StudentVerification_ForphysicalCartof_USANDNonUS", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC12", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();

			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//label[@for='student-discount-0']")));
				WEL.clickOnApplyStudentDiscountLink();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//div[@class='pdp-addtocart-div']")));
					WEL.clickOnAddToCartButton();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("cartPageMainTitle")));
					} catch (Exception e) {
						try {
							if (driver
									.findElement(By.xpath(
											"//h1[contains(text(),'SERVER ERROR (500)')]"))
									.isDisplayed()) {
								Reporting.updateTestReport(
										"Server error came in cart page and the page was refreshed",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.INFO);
								driver.navigate().refresh();
							}
						} catch (Exception e1) {
							Reporting.updateTestReport(
									"User was not in the cart page"
											+ " and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					}
					ScrollingWebPage.PageScrolldown(driver, 0, 400, SS_path);
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//div[@class='col-6 noPadding navyBlueLabel' and contains(text(),'STUDENT')]")));
						Reporting.updateTestReport(
								"Student discount was applied in cart page",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.PASS);
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Student discount was not applied in cart page",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.id("cartCheckoutBtn")));
						WEL.clickonCheckOutButtonOnCartPage();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.id("checkoutLogRegPageTitle")));

							WEL.enterExistingUserNameInCheckoutLoginPage(excelOperation
									.getTestData("TC12", "WEL_Test_Data", "Email_Address"));
							WEL.enterExistingUserPasswordInCheckoutLoginPage(excelOperation
									.getTestData("TC12", "WEL_Test_Data", "Password"));
							WEL.clickOnLoginButtonInCheckoutLoginPage();
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//h5[@id='shippingAddressTitle']/span")));
								WEL.ClickOnEnterNewAddressButtonOnShippingPage();
								WEL.firstName(excelOperation.getTestData("TC12",
										"WEL_Test_Data", "First_Name"));
								WEL.lastName(excelOperation.getTestData("TC12",
										"WEL_Test_Data", "Last_Name"));
								WEL.selectShipCountry(excelOperation.getTestData("TC12",
										"WEL_Test_Data", "Shipping_Country"));
								WEL.shipAddressLineOne(excelOperation.getTestData("TC12",
										"WEL_Test_Data", "Shipping_Address_line1"));
								WEL.shipTownCity(excelOperation.getTestData("TC12",
										"WEL_Test_Data", "Shipping_City/ Province"));
								WEL.enterState(excelOperation.getTestData("TC12",
										"WEL_Test_Data", "Shipping_State"));
								WEL.shipPostCode(excelOperation.getTestData("TC12",
										"WEL_Test_Data", "Shipping_Zip_Code"));
								WEL.shipPhonenumber(excelOperation.getTestData("TC12",
										"WEL_Test_Data", "Shipping_Phone_Number"));
								WEL.clickingOnSaveAndContinue();
								try {
									if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
											.isDisplayed())
										WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Failed to click Address on Address SUggestion due to timeout exception",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.INFO);
								}
								Reporting.updateTestReport(
										"User was in shipping step after successful registration",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.PASS);
							} catch (Exception e) {
								Reporting.updateTestReport(
										"User was not in shipping step and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not redirected to checkout login/ registration page and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
						// WEL.clickOnEnterNewAddressButtonOnShippingPage();

						WEL.verificationOfStudentForUS();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By
									.xpath("//div[@id='orderSummaryProductTotalValue']")));

							String orderprice = WEL.fetchFirstProductPriceInOrderSummary();
							if (orderprice.contains(","))
								orderprice = orderprice.replace(",", "");
							BigDecimal orderproductprice = new BigDecimal(
									orderprice.substring(1));
							BigDecimal shippingChargeInOrderReview = new BigDecimal(
									WEL.fetchShippingChargeInOrderReview().substring(1));
							BigDecimal discount = new BigDecimal(
									WEL.fetchDiscountInOrderReview().substring(1));
							BigDecimal orderTotalpriceafterDiscount = orderproductprice
									.subtract(discount).add(shippingChargeInOrderReview);

							String totalorderReview = WEL.fetchTotalInOrderReview();
							if (totalorderReview.contains(","))
								totalorderReview = totalorderReview.replace(",", "");
							BigDecimal orderTotalPrice = new BigDecimal(
									totalorderReview.substring(1));

							if (orderTotalpriceafterDiscount
									.compareTo(orderTotalPrice) == 0)
								Reporting.updateTestReport(
										"First Product price-Discount+Shipping charge = Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.PASS);
							else
								Reporting.updateTestReport(
										"First Product price Not Equal to Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);

						} catch (Exception e) {
							Reporting.updateTestReport(
									"Order summary tab was not visible" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
						WEL.ClickOnEditIcononShippingPage();
						WEL.firstName(excelOperation.getTestData("TC20", "WEL_Test_Data",
								"First_Name"));
						WEL.lastName(excelOperation.getTestData("TC20", "WEL_Test_Data",
								"Last_Name"));
						Thread.sleep(500);
						WEL.selectShipCountry(excelOperation.getTestData("TC20",
								"WEL_Test_Data", "Shipping_Country"));
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//input[@id='line1']")));
							WEL.shipAddressLineOne(excelOperation.getTestData("TC20",
									"WEL_Test_Data", "Shipping_Address_line1"));
							WEL.shipPostCode(excelOperation.getTestData("TC20",
									"WEL_Test_Data", "Shipping_Zip_Code"));
							WEL.shipTownCity(excelOperation.getTestData("TC20",
									"WEL_Test_Data", "Shipping_City/ Province"));

							try {
								wait1.until(ExpectedConditions.elementToBeClickable(
										By.xpath("//input[@id='address.region']")));
								WEL.enterState(excelOperation.getTestData("TC20",
										"WEL_Test_Data", "Shipping_State"));
							} catch (Exception e) {
								try {
									wait1.until(ExpectedConditions.elementToBeClickable(
											By.xpath("//select[@id='address.region']")));
									WEL.selectStateFromDropsown(excelOperation.getTestData(
											"TC20", "WEL_Test_Data", "Shipping_State"));
								} catch (Exception e1) {

									Reporting.updateTestReport(
											"State field was not clickable and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);

								}

							}
							WEL.shipPhonenumber(excelOperation.getTestData("TC20",
									"WEL_Test_Data", "Shipping_Phone_Number"));
						} catch (Exception e) {
							Reporting.updateTestReport(
									"AddressLine1 field not appeared caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
						WEL.clickingOnSaveAndContinue();
						try {
							if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
									.isDisplayed())
								WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
						} catch (Exception e) {
							Reporting.updateTestReport(
									"Failed to click Address on Address SUggestion due to timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
						}
						WEL.VerificationOfStudentForNonUS();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By
									.xpath("//div[@id='orderSummaryProductTotalValue']")));

							String orderprice = WEL.fetchFirstProductPriceInOrderSummary();
							if (orderprice.contains(","))
								orderprice = orderprice.replace(",", "");
							BigDecimal orderproductprice = new BigDecimal(
									orderprice.substring(1));
							BigDecimal shippingChargeInOrderReview = new BigDecimal(
									WEL.fetchShippingChargeInOrderReview().substring(1));
							BigDecimal discount = new BigDecimal(
									WEL.fetchDiscountInOrderReview().substring(1));
							BigDecimal orderTotalpriceafterDiscount = orderproductprice
									.subtract(discount).add(shippingChargeInOrderReview);

							String totalorderReview = WEL.fetchTotalInOrderReview();
							if (totalorderReview.contains(","))
								totalorderReview = totalorderReview.replace(",", "");
							BigDecimal orderTotalPrice = new BigDecimal(
									totalorderReview.substring(1));

							if (orderTotalpriceafterDiscount
									.compareTo(orderTotalPrice) == 0)
								Reporting.updateTestReport(
										"First Product price-Discount+Shipping charge = Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.PASS);
							else
								Reporting.updateTestReport(
										"First Product price Not Equal to Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);

						} catch (Exception e) {
							Reporting.updateTestReport(
									"Order summary tab was not visible" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"Checkout button was not clickable in cart page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}

				} catch (Exception e) {
					Reporting.updateTestReport(
							"Add To Cart button on product page was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}

	/*
	 * @Date: 17/4/23
	 *
	 * @Description: Address SUggestion by Address Doctor in Shipping Page
	 */
	@Test
	public void TC13_ShippingAndBillingAddress_DifferentForNewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC13_ShippingAndBillingAddress_DifferentForNewUser");
			LogTextFile.writeTestCaseStatus("TC13_ShippingAndBillingAddress_DifferentForNewUser", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC13", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

				try {
					wait.until(
							ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
						String GuestEmail = WEL.enterGuestuser();
						WEL.clickingOnCreateAccoutButton();
						WEL.guestConfirmEmailId(GuestEmail);
						WEL.enterPassword(excelOperation.getTestData("TC13", "WEL_Test_Data",
								"Password"));
						WEL.clickonAgreementCheckBox();
						WEL.clickingOnSaveAndContinue();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h5[@id='shippingAddressTitle']/span")));
							WEL.firstName(excelOperation.getTestData("TC13", "WEL_Test_Data",
									"First_Name"));
							WEL.lastName(excelOperation.getTestData("TC13", "WEL_Test_Data",
									"Last_Name"));
							WEL.selectShipCountry(excelOperation.getTestData("TC13",
									"WEL_Test_Data", "Shipping_Country"));
							WEL.shipAddressLineOne(excelOperation.getTestData("TC13",
									"WEL_Test_Data", "Shipping_Address_line1"));
							WEL.shipTownCity(excelOperation.getTestData("TC13", "WEL_Test_Data",
									"Shipping_City/ Province"));
							WEL.enterState(excelOperation.getTestData("TC13", "WEL_Test_Data",
									"Shipping_State"));
							WEL.shipPostCode(excelOperation.getTestData("TC13", "WEL_Test_Data",
									"Shipping_Zip_Code"));
							WEL.shipPhonenumber(excelOperation.getTestData("TC13",
									"WEL_Test_Data", "Shipping_Phone_Number"));
							WEL.shipSaveAndContinueButton();
							try {
								if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
										.isDisplayed())
									WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Failed to click Address on Address SUggestion due to timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.INFO);
							}
							Reporting.updateTestReport(
									"User was in shipping step after successful registration",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.PASS);
						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not in shipping step and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//div[@id='orderSummaryProductTotalValue']")));

							BigDecimal firstproductprice2 = new BigDecimal(
									WEL.fetchFirstProductPriceInOrderReview().substring(1));

							String discount = WEL.fetchDiscountInOrderReview();
							if (discount.contains(","))
								discount = discount.replace(",", "");
							BigDecimal discountinorderreview = new BigDecimal(
									discount.substring(1));
							String totalorderReview = WEL.fetchTotalInOrderReview();
							if (totalorderReview.contains(","))
								totalorderReview = totalorderReview.replace(",", "");
							BigDecimal orderTotalPrice1 = new BigDecimal(
									totalorderReview.substring(1));
							if (firstproductprice2.subtract(discountinorderreview)
									.compareTo(orderTotalPrice1) == 0)
								Reporting.updateTestReport(
										"First Product price + Second Product price -discount "
												+ " = Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.PASS);
							else
								Reporting.updateTestReport(
										"First Product price +second product price- discount is not equal to Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);

						} catch (Exception e) {
							Reporting.updateTestReport(
									"Order summary tab was not visible" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not redirected to checkout login/ registration page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					// WEL.clickOnEnterNewAddressButtonOnShippingPage();

					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[@class='helpButton']")));
						ScrollingWebPage.PageScrolldown(driver, 0, 500, SS_path);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(
									By.xpath("//label[@id='sameAsBillingLabel']")));
							WEL.shipAndBillAddressSection();
							WEL.selectBillCountry(excelOperation.getTestData("TC13",
									"WEL_Test_Data", "Bill_Country"));
							WEL.addressLineOne(excelOperation.getTestData("TC13",
									"WEL_Test_Data", "Bill_Address_line1"));
							WEL.enterZipcode(excelOperation.getTestData("TC13", "WEL_Test_Data",
									"Bill_Zip_Code"));
							WEL.enterCity(excelOperation.getTestData("TC13", "WEL_Test_Data",
									"Bill_City/ Province"));
							WEL.enterPhoneNumber(excelOperation.getTestData("TC13",
									"WEL_Test_Data", "Bill_Phone_Number"));
						} catch (Exception e) {
							Reporting.updateTestReport(
									"Shipping same as billing was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"The help button was not visible and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}

				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}

	}

	/*
	 * @Date: 17/4/23
	 *
	 * @Description: Address SUggestion by Address Doctor in Billing Page
	 */
	@Test
	public void TC14_Address_suggestionbyAddressDoctorinBillingpage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC14_Address_suggestionbyAddressDoctorinBillingpage");
			LogTextFile.writeTestCaseStatus("TC14_Address_suggestionbyAddressDoctorinBillingpage", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC14", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//label[@for='product-variant-WEL_CMA_TYPE_EBOOK-0']")));
				WEL.clickonCMAeBook();
				BigDecimal price = new BigDecimal(
						WEL.fetchProductPriceInPDP().substring(1));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//div[@class='pdp-addtocart-div']")));
					WEL.clickOnAddToCartButton();
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.id("cartPageMainTitle")));
					} catch (Exception e) {
						try {
							if (driver.findElement(By.xpath(
											"//h1[contains(text(),'SERVER ERROR (500)')]"))
									.isDisplayed()) {
								Reporting.updateTestReport(
										"Server error came in cart page and the page was refreshed",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.INFO);
								driver.navigate().refresh();
							}
						} catch (Exception e1) {
							Reporting.updateTestReport(
									"User was not in the cart page"
											+ " and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					}
					BigDecimal subtotal = new BigDecimal(
							WEL.fetchOrderSubTotalInCartPage().substring(1));
					if (price.compareTo(subtotal) == 0)
						Reporting.updateTestReport(
								"The addition of all the products' price is same as the subtotal in cart page",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.PASS);
					else
						Reporting.updateTestReport(
								"The addition of all the products' pricedidn't match with the subtotal in cart page",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);

					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.id("cartCheckoutBtn")));
						WEL.clickonCheckOutButtonOnCartPage();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.id("checkoutLogRegPageTitle")));
							WEL.enterExistingUserNameInCheckoutLoginPage(
									excelOperation.getTestData("TC14", "WEL_Test_Data",
											"Email_Address"));
							WEL.enterExistingUserPasswordInCheckoutLoginPage(
									excelOperation.getTestData("TC14", "WEL_Test_Data",
											"Password"));
							WEL.clickOnLoginButtonInCheckoutLoginPage();
							try {
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.xpath(
												"//h5[@class='welCommonHeadingTitle']")));
								WEL.firstName(excelOperation.getTestData("TC14",
										"WEL_Test_Data", "First_Name"));
								WEL.lastName(excelOperation.getTestData("TC14",
										"WEL_Test_Data", "Last_Name"));

								WEL.selectBillCountry(excelOperation.getTestData("TC14",
										"WEL_Test_Data", "Bill_Country"));
								WEL.addressLineOne(excelOperation.getTestData("TC14",
										"WEL_Test_Data", "Bill_Address_line1"));
								WEL.enterZipcode(excelOperation.getTestData("TC14",
										"WEL_Test_Data", "Bill_Zip_Code"));
								WEL.enterState(excelOperation.getTestData("TC14",
										"WEL_Test_Data", "Bill_State"));
								WEL.enterCity(excelOperation.getTestData("TC14",
										"WEL_Test_Data", "Bill_City/ Province"));
								WEL.enterPhoneNumber(excelOperation.getTestData("TC14",
										"WEL_Test_Data", "Bill_Phone_Number"));

								// WEL.clickingOnSaveAndContinue();

								Reporting.updateTestReport(
										"User was in Billing step after successful registration",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.PASS);
							} catch (Exception e) {
								Reporting.updateTestReport(
										"User was not in shipping step and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
							try {
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.xpath(
												"//div[@id='orderSummaryProductTotalValue']")));

								BigDecimal firstproductprice2 = new BigDecimal(
										WEL.fetchFirstProductPriceInOrderReview()
												.substring(1));

								String discount = WEL.fetchDiscountInOrderReview();
								if (discount.contains(","))
									discount = discount.replace(",", "");
								BigDecimal discountinorderreview = new BigDecimal(
										discount.substring(1));
								String totalorderReview = WEL.fetchTotalInOrderReview();
								if (totalorderReview.contains(","))
									totalorderReview = totalorderReview.replace(",",
											"");
								BigDecimal orderTotalPrice1 = new BigDecimal(
										totalorderReview.substring(1));
								if (firstproductprice2.subtract(discountinorderreview)
										.compareTo(orderTotalPrice1) == 0)
									Reporting.updateTestReport(
											"First Product price + Second Product price -discount "
													+ " = Order total in Order Review step",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.PASS);
								else
									Reporting.updateTestReport(
											"First Product price +second product price- discount is not equal to Order total in Order Review step",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);

							} catch (Exception e) {
								Reporting.updateTestReport(
										"Order summary tab was not visible"
												+ e.getMessage(),
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not redirected to checkout login/ registration page and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

						driver.switchTo().frame(driver.findElement(
								By.xpath(".//iframe[@title='cardholder name']")));
						try {
							wait.until(ExpectedConditions
									.elementToBeClickable(By.id("nameOnCard")));
							WEL.enterCardHolderName(excelOperation.getTestData("TC14",
									"WEL_Test_Data", "First_Name"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(
									By.xpath(".//iframe[@title='card number']")));
							WEL.enterCardNumber(excelOperation.getTestData("TC14",
									"WEL_Test_Data", "Card_Number"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(
									By.xpath(".//iframe[@title='expiryMonth']")));
							WEL.selectExpirationMonthFromDropDown(
									excelOperation.getTestData("TC14", "WEL_Test_Data",
											"Expiry_Month"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(
									By.xpath(".//iframe[@title='expiryYear']")));
							WEL.selectExpirationYearFromDropDown(
									excelOperation.getTestData("TC14", "WEL_Test_Data",
											"Expiry_Year"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(
									By.xpath(".//iframe[@title='securityCode']")));
							WEL.enterCVV_Number(excelOperation.getTestData("TC14",
									"WEL_Test_Data", "CVV"));
							driver.switchTo().defaultContent();
							WEL.saveAndContinueCheckOut();
							try {
								wait.until(
										ExpectedConditions.visibilityOfElementLocated(By
												.xpath("//div[@class='helpButton']")));
								try {
									if (WEL.returnUseSelectedBillingAddressButtonAddressDoctorPopUp()
											.isDisplayed())
										WEL.clickOnUseSelectedBillingAddressButtonAddressDoctor();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Failed to click Address on Address SUggestion due to timeout exception",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.INFO);
								}

							} catch (Exception e) {
								Reporting.updateTestReport(
										"Help button was not visible and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"The name field in card information step was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"Checkout button was not clickable in cart page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}

				} catch (Exception e) {
					Reporting.updateTestReport(
							"Failed to click on CMA eBook caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}

	}
	/*
	 * @Date: 18/4/23
	 *
	 * @Description: Check Paypal payment Option for Existing User
	 */

	@Test
	public void TC15_Check_PaypalPayment_OptionForExistingUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC15_Check_PaypalPayment_OptionForExistingUser");
			LogTextFile.writeTestCaseStatus("TC15_Check_PaypalPayment_OptionForExistingUser", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC15", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();

			BigDecimal price = new BigDecimal(
					WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver.findElement(By.xpath(
										"//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.id("checkoutLogRegPageTitle")));
						WEL.enterExistingUserNameInCheckoutLoginPage(
								excelOperation.getTestData("TC15", "WEL_Test_Data",
										"Email_Address"));
						WEL.enterExistingUserPasswordInCheckoutLoginPage(
								excelOperation.getTestData("TC15", "WEL_Test_Data",
										"Password"));
						WEL.clickOnLoginButtonInCheckoutLoginPage();
						WEL.clickOnEnterNewAddressButtonOnShippingPage();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h5[@id='shippingAddressTitle']/span")));
							WEL.firstName(excelOperation.getTestData("TC16", "WEL_Test_Data",
									"First_Name"));
							WEL.lastName(excelOperation.getTestData("TC16", "WEL_Test_Data",
									"Last_Name"));
							WEL.selectShipCountry(excelOperation.getTestData("TC16",
									"WEL_Test_Data", "Shipping_Country"));
							WEL.shipAddressLineOne(excelOperation.getTestData("TC16",
									"WEL_Test_Data", "Shipping_Address_line1"));
							WEL.shipTownCity(excelOperation.getTestData("TC16", "WEL_Test_Data",
									"Shipping_City/ Province"));
							WEL.enterState(excelOperation.getTestData("TC16", "WEL_Test_Data",
									"Shipping_State"));
							WEL.shipPostCode(excelOperation.getTestData("TC16", "WEL_Test_Data",
									"Shipping_Zip_Code"));
							WEL.shipPhonenumber(excelOperation.getTestData("TC16",
									"WEL_Test_Data", "Shipping_Phone_Number"));
							WEL.shipSaveAndContinueButton();
							try {
								if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
										.isDisplayed())
									WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Failed to click Address on Address SUggestion due to timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.INFO);
							}
							WEL.clickingOnSaveAndContinue();
							WEL.verifyPaypaltext();
							Reporting.updateTestReport(
									"User was in shipping step after successful registration",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.PASS);
						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not in shipping step and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath(
											"//div[@id='orderSummaryProductTotalValue']")));

							BigDecimal firstproductprice2 = new BigDecimal(
									WEL.fetchFirstProductPriceInOrderReview()
											.substring(1));

							String discount = WEL.fetchDiscountInOrderReview();
							if (discount.contains(","))
								discount = discount.replace(",", "");
							BigDecimal discountinorderreview = new BigDecimal(
									discount.substring(1));
							String totalorderReview = WEL.fetchTotalInOrderReview();
							if (totalorderReview.contains(","))
								totalorderReview = totalorderReview.replace(",",
										"");
							BigDecimal orderTotalPrice1 = new BigDecimal(
									totalorderReview.substring(1));
							if (firstproductprice2.subtract(discountinorderreview)
									.compareTo(orderTotalPrice1) == 0)
								Reporting.updateTestReport(
										"First Product price + Second Product price -discount "
												+ " = Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.PASS);
							else
								Reporting.updateTestReport(
										"First Product price +second product price- discount is not equal to Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);

						} catch (Exception e) {
							Reporting.updateTestReport(
									"Order summary tab was not visible"
											+ e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not redirected to checkout login/ registration page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
					//

				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}


			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}

	}
	/*
	 * @Date: 18/4/23
	 *
	 * @Description: Check PaypalCredit Option for new User
	 */

	@Test
	public void TC16_Check_PaypalCredit_OptionForNewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC16_Check_PaypalCredit_OptionForNewUser");
			LogTextFile.writeTestCaseStatus("TC16_Check_PaypalCredit_OptionForNewUser", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC16", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				try {
					wait.until(
							ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
						String GuestEmail = WEL.enterGuestuser();
						WEL.clickingOnCreateAccoutButton();
						WEL.guestConfirmEmailId(GuestEmail);
						WEL.enterPassword(excelOperation.getTestData("TC16", "WEL_Test_Data",
								"Password"));
						WEL.clickonAgreementCheckBox();
						WEL.clickingOnSaveAndContinue();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h5[@id='shippingAddressTitle']/span")));
							WEL.firstName(excelOperation.getTestData("TC16", "WEL_Test_Data",
									"First_Name"));
							WEL.lastName(excelOperation.getTestData("TC16", "WEL_Test_Data",
									"Last_Name"));
							WEL.selectShipCountry(excelOperation.getTestData("TC16",
									"WEL_Test_Data", "Shipping_Country"));
							WEL.shipAddressLineOne(excelOperation.getTestData("TC16",
									"WEL_Test_Data", "Shipping_Address_line1"));
							WEL.shipTownCity(excelOperation.getTestData("TC16", "WEL_Test_Data",
									"Shipping_City/ Province"));
							WEL.enterState(excelOperation.getTestData("TC16", "WEL_Test_Data",
									"Shipping_State"));
							WEL.shipPostCode(excelOperation.getTestData("TC16", "WEL_Test_Data",
									"Shipping_Zip_Code"));
							WEL.shipPhonenumber(excelOperation.getTestData("TC16",
									"WEL_Test_Data", "Shipping_Phone_Number"));
							WEL.shipSaveAndContinueButton();
							try {
								if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
										.isDisplayed())
									WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Failed to click Address on Address SUggestion due to timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.INFO);
							}
							WEL.clickingOnSaveAndContinue();
							WEL.verifyPaypalCredittext();
							Reporting.updateTestReport(
									"User was in shipping step after successful registration",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.PASS);
						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not in shipping step and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//div[@id='orderSummaryProductTotalValue']")));

							BigDecimal firstproductprice2 = new BigDecimal(
									WEL.fetchFirstProductPriceInOrderReview().substring(1));

							String discount = WEL.fetchDiscountInOrderReview();
							if (discount.contains(","))
								discount = discount.replace(",", "");
							BigDecimal discountinorderreview = new BigDecimal(
									discount.substring(1));
							String totalorderReview = WEL.fetchTotalInOrderReview();
							if (totalorderReview.contains(","))
								totalorderReview = totalorderReview.replace(",", "");
							BigDecimal orderTotalPrice1 = new BigDecimal(
									totalorderReview.substring(1));
							if (firstproductprice2.subtract(discountinorderreview)
									.compareTo(orderTotalPrice1) == 0)
								Reporting.updateTestReport(
										"First Product price + Second Product price -discount "
												+ " = Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.PASS);
							else
								Reporting.updateTestReport(
										"First Product price +second product price- discount is not equal to Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);

						} catch (Exception e) {
							Reporting.updateTestReport(
									"Order summary tab was not visible" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not redirected to checkout login/ registration page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					// WEL.clickOnEnterNewAddressButtonOnShippingPage();

				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}

	}

	/*
	 * @Date: 18/4/23
	 *
	 * @Description: Add Productt some Parts of new user
	 */

	@Test
	public void TC19_AddProducts_Some_Parts_New_User() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC19_AddProducts_Some_Parts_New_User");
			LogTextFile.writeTestCaseStatus("TC19_AddProducts_Some_Parts_New_User", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC19", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			WEL.clickOnPart1InCIAPDP();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceonPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='cartCheckoutBtn']/span")));
				} catch (Exception e) {
					try {
						if (driver.findElement(By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]")).isDisplayed()) {
							Reporting.updateTestReport("Server error came in cart page and the page was refreshed", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"Checkout button was not clickable in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(WEL.fetchOrderTotalOnCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
						String GuestEmail = WEL.enterGuestuser();
						WEL.clickingOnCreateAccoutButton();
						WEL.guestConfirmEmailId(GuestEmail);
						WEL.enterPassword(
								excelOperation.getTestData("TC02", "WEL_Test_Data", "Password"));
						WEL.clickonAgreementCheckBox();
						WEL.clickingOnSaveAndContinue();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h5[@id='shippingAddressTitle']/span")));
							Reporting.updateTestReport(
									"User was in shipping step after successful registration",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not in shipping step and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not redirected to checkout login/ registration page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"Failed to click checkout button on Cart page due to timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Failed to click on Add To Cart button on PDP page due to timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}
	/*
	 * @Date: 18/4/23
	 *
	 * @Description: Shipping and Billing Address for India Address
	 */

	@Test
	public void TC20_Shipping_Billing_ForIndiaAddress() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC20_Shipping_Billing_ForIndiaAddress");
			LogTextFile.writeTestCaseStatus("TC20_Shipping_Billing_ForIndiaAddress", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC20", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();

			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				try {
					wait.until(
							ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
						WEL.enterExistingUserNameInCheckoutLoginPage(excelOperation
								.getTestData("TC20", "WEL_Test_Data", "Email_Address"));
						WEL.enterExistingUserPasswordInCheckoutLoginPage(excelOperation
								.getTestData("TC20", "WEL_Test_Data", "Password"));
						WEL.clickOnLoginButtonInCheckoutLoginPage();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h5[@id='shippingAddressTitle']/span")));
							WEL.ClickOnEnterNewAddressButtonOnShippingPage();
							WEL.firstName(excelOperation.getTestData("TC20", "WEL_Test_Data",
									"First_Name"));
							WEL.lastName(excelOperation.getTestData("TC20", "WEL_Test_Data",
									"Last_Name"));
							Thread.sleep(500);
							WEL.selectShipCountry(excelOperation.getTestData("TC20",
									"WEL_Test_Data", "Shipping_Country"));
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//input[@id='line1']")));
								WEL.shipAddressLineOne(excelOperation.getTestData("TC20",
										"WEL_Test_Data", "Shipping_Address_line1"));
								WEL.shipPostCode(excelOperation.getTestData("TC20",
										"WEL_Test_Data", "Shipping_Zip_Code"));
								WEL.shipTownCity(excelOperation.getTestData("TC20",
										"WEL_Test_Data", "Shipping_City/ Province"));

								try {
									wait1.until(ExpectedConditions.elementToBeClickable(
											By.xpath("//input[@id='address.region']")));
									WEL.enterState(excelOperation.getTestData("TC20",
											"WEL_Test_Data", "Shipping_State"));
								} catch (Exception e) {
									try {
										wait1.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//select[@id='address.region']")));
										WEL.selectStateFromDropsown(excelOperation.getTestData(
												"TC20", "WEL_Test_Data", "Shipping_State"));
									} catch (Exception e1) {

										Reporting.updateTestReport(
												"State field was not clickable and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.FAIL);

									}

								}

								WEL.shipPhonenumber(excelOperation.getTestData("TC20",
										"WEL_Test_Data", "Shipping_Phone_Number"));
								WEL.clickingOnSaveAndContinue();
								try {
									if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
											.isDisplayed())
										WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Failed to click Address on Address SUggestion due to timeout exception",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.INFO);
								}

								Reporting.updateTestReport(
										"User was in shipping step after successful registration",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.PASS);
							} catch (Exception e) {
								Reporting.updateTestReport(
										"User was not in shipping step and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By
										.xpath("//div[@id='orderSummaryProductTotalValue']")));

								BigDecimal firstproductprice2 = new BigDecimal(
										WEL.fetchFirstProductPriceInOrderReview().substring(1));

								String discount = WEL.fetchDiscountInOrderReview();
								if (discount.contains(","))
									discount = discount.replace(",", "");
								BigDecimal discountinorderreview = new BigDecimal(
										discount.substring(1));
								String totalorderReview = WEL.fetchTotalInOrderReview();
								if (totalorderReview.contains(","))
									totalorderReview = totalorderReview.replace(",", "");
								String shippingcharge = WEL.fetchShippingChargeInOrderReview();

								if (shippingcharge.contains(","))
									shippingcharge = shippingcharge.replace(",", "");
								BigDecimal ShipchargoneReviewpage = new BigDecimal(
										shippingcharge.substring(1));

								BigDecimal orderTotalPrice1 = new BigDecimal(
										totalorderReview.substring(1));
								if (firstproductprice2.subtract(discountinorderreview)
										.add(ShipchargoneReviewpage)
										.compareTo(orderTotalPrice1) == 0)
									Reporting.updateTestReport(
											"First Product price + shippingcharge -discount "
													+ " = Order total in Order Review step",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.PASS);
								else
									Reporting.updateTestReport(
											"First Product price +shipping charge- discount is not equal to Order total in Order Review step",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);

							} catch (Exception e) {
								Reporting.updateTestReport(
										"Order summary tab was not visible" + e.getMessage(),
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}

						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not redirected to checkout login/ registration page and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
						// WEL.clickOnEnterNewAddressButtonOnShippingPage();
						ScrollingWebPage.PageScrolldown(driver, 0, 350, SS_path);

					} catch (Exception e) {
						Reporting.updateTestReport(
								"Address line 1 was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}

	}
	/*
	 * @Date: 18/4/23
	 *
	 * @Description: Shipping and Billing Address for India Address
	 */

	@Test
	public void TC21_Shipping_Billing_ForChinaAddress() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC21_Shipping_Billing_ForChinaAddress");
			LogTextFile.writeTestCaseStatus("TC21_Shipping_Billing_ForChinaAddress", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC21", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

				try {
					wait.until(
							ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
						WEL.enterExistingUserNameInCheckoutLoginPage(excelOperation
								.getTestData("TC21", "WEL_Test_Data", "Email_Address"));
						WEL.enterExistingUserPasswordInCheckoutLoginPage(excelOperation
								.getTestData("TC21", "WEL_Test_Data", "Password"));
						WEL.clickOnLoginButtonInCheckoutLoginPage();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h5[@id='shippingAddressTitle']/span")));
							WEL.ClickOnEnterNewAddressButtonOnShippingPage();
							WEL.firstName(excelOperation.getTestData("TC21", "WEL_Test_Data",
									"First_Name"));
							WEL.lastName(excelOperation.getTestData("TC21", "WEL_Test_Data",
									"Last_Name"));
							Thread.sleep(500);
							WEL.selectShipCountry(excelOperation.getTestData("TC21",
									"WEL_Test_Data", "Shipping_Country"));
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//input[@id='line1']")));
								WEL.shipAddressLineOne(excelOperation.getTestData("TC21",
										"WEL_Test_Data", "Shipping_Address_line1"));
								WEL.shipPostCode(excelOperation.getTestData("TC21",
										"WEL_Test_Data", "Shipping_Zip_Code"));

								WEL.shipTownCity(excelOperation.getTestData("TC21",
										"WEL_Test_Data", "Shipping_City/ Province"));

								try {
									wait1.until(ExpectedConditions.elementToBeClickable(
											By.xpath("//input[@id='address.region']")));
									WEL.enterState(excelOperation.getTestData("TC21",
											"WEL_Test_Data", "Shipping_State"));
								} catch (Exception e) {
									try {
										wait1.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//select[@id='address.region']")));
										WEL.selectStateFromDropsown(excelOperation.getTestData(
												"TC21", "WEL_Test_Data", "Shipping_State"));
									} catch (Exception e1) {

										Reporting.updateTestReport(
												"State field was not clickable and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.FAIL);

									}

								}

								WEL.shipPhonenumber(excelOperation.getTestData("TC21",
										"WEL_Test_Data", "Shipping_Phone_Number"));
								WEL.clickingOnSaveAndContinue();
								try {
									if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
											.isDisplayed())
										WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Failed to click Address on Address SUggestion due to timeout exception",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.INFO);
								}
								ScrollingWebPage.PageScrolldown(driver, 0, 350, SS_path);

							} catch (Exception e) {
								Reporting.updateTestReport(
										"Address line 1 was not clickable and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
							Reporting.updateTestReport(
									"User was in shipping step after successful registration",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.PASS);
						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not in shipping step and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//div[@id='orderSummaryProductTotalValue']")));

							BigDecimal firstproductprice2 = new BigDecimal(
									WEL.fetchFirstProductPriceInOrderReview().substring(1));

							String discount = WEL.fetchDiscountInOrderReview();
							if (discount.contains(","))
								discount = discount.replace(",", "");
							BigDecimal discountinorderreview = new BigDecimal(
									discount.substring(1));
							String totalorderReview = WEL.fetchTotalInOrderReview();
							if (totalorderReview.contains(","))
								totalorderReview = totalorderReview.replace(",", "");
							String shippingcharge = WEL.fetchShippingChargeInOrderReview();

							if (shippingcharge.contains(","))
								shippingcharge = shippingcharge.replace(",", "");
							BigDecimal ShipchargoneReviewpage = new BigDecimal(
									shippingcharge.substring(1));

							BigDecimal orderTotalPrice1 = new BigDecimal(
									totalorderReview.substring(1));
							if (firstproductprice2.subtract(discountinorderreview)
									.add(ShipchargoneReviewpage)
									.compareTo(orderTotalPrice1) == 0)
								Reporting.updateTestReport(
										"First Product price + shippingcharge -discount "
												+ " = Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.PASS);
							else
								Reporting.updateTestReport(
										"First Product price +shipping charge- discount is not equal to Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);

						} catch (Exception e) {
							Reporting.updateTestReport(
									"Order summary tab was not visible" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not redirected to checkout login/ registration page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					// WEL.clickOnEnterNewAddressButtonOnShippingPage();

				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}
	/*
	 * @Date: 18/4/23
	 *
	 * @Description: Shipping and Billing Address for India Address
	 */

	@Test
	public void TC22_Shipping_Billing_ForJapanAddress() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC22_Shipping_Billing_ForJapanAddress");
			LogTextFile.writeTestCaseStatus("TC22_Shipping_Billing_ForJapanAddress", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC22", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				try {
					wait.until(
							ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
						WEL.enterExistingUserNameInCheckoutLoginPage(excelOperation
								.getTestData("TC22", "WEL_Test_Data", "Email_Address"));
						WEL.enterExistingUserPasswordInCheckoutLoginPage(excelOperation
								.getTestData("TC22", "WEL_Test_Data", "Password"));
						WEL.clickOnLoginButtonInCheckoutLoginPage();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h5[@id='shippingAddressTitle']/span")));
							WEL.ClickOnEnterNewAddressButtonOnShippingPage();
							WEL.firstName(excelOperation.getTestData("TC22", "WEL_Test_Data",
									"First_Name"));
							WEL.lastName(excelOperation.getTestData("TC22", "WEL_Test_Data",
									"Last_Name"));
							Thread.sleep(500);
							WEL.selectShipCountry(excelOperation.getTestData("TC22",
									"WEL_Test_Data", "Shipping_Country"));
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//input[@id='line1']")));
								WEL.shipAddressLineOne(excelOperation.getTestData("TC22",
										"WEL_Test_Data", "Shipping_Address_line1"));
								WEL.shipPostCode(excelOperation.getTestData("TC22",
										"WEL_Test_Data", "Shipping_Zip_Code"));
								WEL.shipTownCity(excelOperation.getTestData("TC22",
										"WEL_Test_Data", "Shipping_City/ Province"));

								try {
									wait1.until(ExpectedConditions.elementToBeClickable(
											By.xpath("//input[@id='address.region']")));
									WEL.enterState(excelOperation.getTestData("TC22",
											"WEL_Test_Data", "Shipping_State"));
								} catch (Exception e) {
									try {
										wait1.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//select[@id='address.region']")));
										WEL.selectStateFromDropsown(excelOperation.getTestData(
												"TC22", "WEL_Test_Data", "Shipping_State"));
									} catch (Exception e1) {

										Reporting.updateTestReport(
												"State field was not clickable and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.FAIL);

									}

								}

								WEL.shipPhonenumber(excelOperation.getTestData("TC22",
										"WEL_Test_Data", "Shipping_Phone_Number"));

								WEL.clickingOnSaveAndContinue();
								try {
									if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
											.isDisplayed())
										WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Failed to click Address on Address SUggestion due to timeout exception",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.INFO);
								}

								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By
											.xpath("//div[@id='orderSummaryProductTotalValue']")));

									BigDecimal firstproductprice2 = new BigDecimal(
											WEL.fetchFirstProductPriceInOrderReview()
													.substring(1));

									String discount = WEL.fetchDiscountInOrderReview();
									if (discount.contains(","))
										discount = discount.replace(",", "");
									BigDecimal discountinorderreview = new BigDecimal(
											discount.substring(1));
									String totalorderReview = WEL.fetchTotalInOrderReview();
									if (totalorderReview.contains(","))
										totalorderReview = totalorderReview.replace(",", "");
									String shippingcharge = WEL
											.fetchShippingChargeInOrderReview();

									if (shippingcharge.contains(","))
										shippingcharge = shippingcharge.replace(",", "");
									BigDecimal ShipchargoneReviewpage = new BigDecimal(
											shippingcharge.substring(1));

									BigDecimal orderTotalPrice1 = new BigDecimal(
											totalorderReview.substring(1));
									if (firstproductprice2.subtract(discountinorderreview)
											.add(ShipchargoneReviewpage)
											.compareTo(orderTotalPrice1) == 0)
										Reporting.updateTestReport(
												"First Product price + shippingcharge -discount "
														+ " = Order total in Order Review step",
												CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.PASS);
									else
										Reporting.updateTestReport(
												"First Product price +shipping charge- discount is not equal to Order total in Order Review step",
												CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.FAIL);

								} catch (Exception e) {
									Reporting.updateTestReport(
											"Order summary tab was not visible"
													+ e.getMessage(),
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
								}

								ScrollingWebPage.PageScrolldown(driver, 0, 350, SS_path);

							} catch (Exception e) {
								Reporting.updateTestReport(
										"Address line 1 was not clickable and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
							Reporting.updateTestReport(
									"User was in shipping step after successful registration",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.PASS);
						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not in shipping step and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not redirected to checkout login/ registration page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}

				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}
	//
	/*
	 * @Date: 18/4/23
	 *
	 * @Description: Message that we don't ship to PO boxes on shipping address
	 * Pricing Implementation
	 */

	@Test
	public void TC23_Messagethat_WedontShipToPOboxesOnShippingaddres_PricingImplementation() throws IOException {
		try {
			Reporting.test = Reporting.extent
					.createTest("TC23_Messagethat_WedontShipToPOboxesOnShippingaddres_PricingImplementation");
			LogTextFile.writeTestCaseStatus(
					"TC23_Messagethat_WedontShipToPOboxesOnShippingaddres_PricingImplementation", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC23", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				try {
					wait.until(
							ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
						WEL.enterExistingUserNameInCheckoutLoginPage(excelOperation
								.getTestData("TC23", "WEL_Test_Data", "Email_Address"));
						WEL.enterExistingUserPasswordInCheckoutLoginPage(excelOperation
								.getTestData("TC23", "WEL_Test_Data", "Password"));
						WEL.clickOnLoginButtonInCheckoutLoginPage();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h5[@id='shippingAddressTitle']/span")));
							WEL.ClickOnEnterNewAddressButtonOnShippingPage();
							WEL.firstName(excelOperation.getTestData("TC23", "WEL_Test_Data",
									"First_Name"));
							WEL.lastName(excelOperation.getTestData("TC23", "WEL_Test_Data",
									"Last_Name"));

							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//input[@id='line1']")));
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(
											By.xpath("//div[@class='helpButton']")));
									WEL.shipAddressLineOne(excelOperation.getTestData("TC22",
											"WEL_Test_Data", "Shipping_Address_line1"));

									WEL.checkDontShipToPOBoxMessage();
									try {
										wait.until(ExpectedConditions
												.visibilityOfElementLocated(By.xpath(
														"//div[@id='orderSummaryProductTotalValue']")));

										BigDecimal firstproductprice2 = new BigDecimal(
												WEL.fetchFirstProductPriceInOrderReview()
														.substring(1));

										String discount = WEL.fetchDiscountInOrderReview();
										if (discount.contains(","))
											discount = discount.replace(",", "");
										BigDecimal discountinorderreview = new BigDecimal(
												discount.substring(1));
										String totalorderReview = WEL.fetchTotalInOrderReview();
										if (totalorderReview.contains(","))
											totalorderReview = totalorderReview.replace(",",
													"");
										BigDecimal orderTotalPrice1 = new BigDecimal(
												totalorderReview.substring(1));
										if (firstproductprice2.subtract(discountinorderreview)
												.compareTo(orderTotalPrice1) == 0)
											Reporting.updateTestReport(
													"First Product price + Second Product price -discount "
															+ " = Order total in Order Review step",
													CaptureScreenshot.getScreenshot(SS_path),
													StatusDetails.PASS);
										else
											Reporting.updateTestReport(
													"First Product price +second product price- discount is not equal to Order total in Order Review step",
													CaptureScreenshot.getScreenshot(SS_path),
													StatusDetails.FAIL);

									} catch (Exception e) {
										Reporting.updateTestReport(
												"Order summary tab was not visible"
														+ e.getMessage(),
												CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.FAIL);
									}

								} catch (Exception e) {
									Reporting.updateTestReport(
											"Help button was not visible and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Address line 1 was not clickable and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}

							Reporting.updateTestReport(
									"User was in shipping step after successful registration",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.PASS);
						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not in shipping step and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not redirected to checkout login/ registration page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					// WEL.clickOnEnterNewAddressButtonOnShippingPage();
				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}
	/*
	 * @Date: 18/4/23
	 *
	 * @Description: Message that we don't ship to PO boxes on shipping address
	 * Pricing Implementation
	 */

	@Test
	public void TC24_AddProducts_CPACMACFA_ReviewCoursPhysicalproduct_FornewUserToCart_AndProceedTill_Paymentpage()
			throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest(
					"TC24_AddProducts_CPACMACFA_ReviewCoursPhysicalproduct_FornewUserToCart_AndProceedTill_Paymentpage");
			LogTextFile.writeTestCaseStatus(
					"TC24_AddProducts_CPACMACFA_ReviewCoursPhysicalproduct_FornewUserToCart_AndProceedTill_Paymentpage",
					"Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			String urls[] = excelOperation.getTestData("TC24", "WEL_Test_Data", "URL").split(",");
			driver.get(WEL.AddingMultipleProducts(urls[0]));
			BigDecimal firstproductprice = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				driver.get(WEL.AddingMultipleProducts(urls[1]));
				BigDecimal secondproductprice = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='pdp-addtocart-div']")));
					WEL.clickOnAddToCartButton();
					driver.get(WEL.AddingMultipleProducts(urls[2]));
					driver.navigate().refresh();
					BigDecimal thirdproductprice = new BigDecimal(WEL.fetchCFAPriceInPDP().substring(1));
					try {

						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='container']/div[@class='box']//div[@class='cart-box-buttons']/button)[1]")));
						ScrollingWebPage.PageScrolldown(driver, 0, 300, SS_path);
						WEL.clickOnCFAAddToCartButton();
						BigDecimal subtotal = new BigDecimal(WEL.fetchOrderSubTotalInCartPage().substring(1));
						if (firstproductprice.add(secondproductprice).add(thirdproductprice).compareTo(subtotal) == 0)
							Reporting.updateTestReport("The addition of all the products' price is same as the subtotal in cart page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						else
							Reporting.updateTestReport("The addition of the price is not match with the subtotal in cart page due to multiple coupons was aplied in Cart Page", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
							WEL.clickonCheckOutButtonOnCartPage();
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
								String GuestEmail = WEL.enterGuestuser();
								WEL.clickingOnCreateAccoutButton();
								WEL.guestConfirmEmailId(GuestEmail);
								WEL.enterPassword(excelOperation.getTestData("TC24", "WEL_Test_Data", "Password"));
								WEL.clickonAgreementCheckBox();
								WEL.clickingOnSaveAndContinue();
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[@id='shippingAddressTitle']/span")));
									WEL.firstName(excelOperation.getTestData("TC24", "WEL_Test_Data", "First_Name"));
									WEL.lastName(excelOperation.getTestData("TC24", "WEL_Test_Data", "Last_Name"));
									WEL.selectShipCountry(excelOperation.getTestData("TC24", "WEL_Test_Data", "Shipping_Country"));
									WEL.shipAddressLineOne(excelOperation.getTestData("TC24", "WEL_Test_Data", "Shipping_Address_line1"));
									WEL.shipTownCity(excelOperation.getTestData("TC24", "WEL_Test_Data", "Shipping_City/ Province"));
									WEL.enterState(excelOperation.getTestData("TC11", "WEL_Test_Data", "Shipping_State"));
									WEL.shipPostCode(excelOperation.getTestData("TC24", "WEL_Test_Data", "Shipping_Zip_Code"));
									WEL.shipPhonenumber(excelOperation.getTestData("TC24", "WEL_Test_Data", "Shipping_Phone_Number"));
									WEL.shipSaveAndContinueButton();
									try {
										if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed())
											WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
									} catch (Exception e) {
										Reporting.updateTestReport("Failed to click Address on Address SUggestion due to timeout exception", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
									}
									Reporting.updateTestReport("User was in shipping step after successful registration", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								} catch (Exception e) {
									Reporting.updateTestReport("User was not in shipping step and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='orderSummaryProductTotalValue']")));
									BigDecimal firstproductprice2 = new BigDecimal(WEL.fetchFirstProductPriceInOrderReview().substring(1));
									String discount = WEL.fetchDiscountInOrderReview();
									if (discount.contains(","))
										discount = discount.replace(",", "");
									BigDecimal discountinorderreview = new BigDecimal(discount.substring(1));
									String totalorderReview = WEL.fetchTotalInOrderReview();
									if (totalorderReview.contains(","))
										totalorderReview = totalorderReview.replace(",", "");
									BigDecimal orderTotalPrice1 = new BigDecimal(totalorderReview.substring(1));
									if (firstproductprice2.subtract(discountinorderreview).compareTo(orderTotalPrice1) == 0)
										Reporting.updateTestReport("First Product price + Second Product price+ third product price -discount " + " = Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
									else
										Reporting.updateTestReport("First Product price +second product price + third product price- discount is not equal to Order total in Order Review step", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

								} catch (Exception e) {
									Reporting.updateTestReport("Order summary tab was not visible" + e.getMessage(), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("User was not redirected to checkout login/ registration page and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
							// WEL.clickOnEnterNewAddressButtonOnShippingPage();
							WEL.clickingOnSaveAndContinue();
						} catch (Exception e) {
							Reporting.updateTestReport("Checkout button was not clickable in cart page and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Failed to click CFA Add To Cart button caused timeout exception ", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Failed to click on CMA Add To Cart button caused timeout exception ", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Failed to click on CPA Add To Cart button caused timeout exception ", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}/*
	 * @Date: 18/4/23
	 *
	 * @Description: Shipping and Billing Address for India Address
	 */

	@Test
	public void TC25_CartPage_UIValidation() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC25_CartPage_UIValidation");
			LogTextFile.writeTestCaseStatus("TC25_CartPage_UIValidation", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC25", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
				WEL.clickOnAddDiscountLink();
				WEL.enterDiscountValue(
						excelOperation.getTestData("Cart_Coupon", "Generic_Dataset", "Data"));

				WEL.clickOnDiscountApplyButtonInCartPage();
				WEL.verifyDiscountText();
				try {
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
							By.id("cartPageSuccessCouponDiv")));
					Reporting.updateTestReport("The coupon code was successfuly applied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				} catch (Exception e) {
					Reporting.updateTestReport("The coupon code  couldn't be applied",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport(
						"Failed to click on Add TO Cart and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}

	}
	/*
	 * @Author: Anindita
	 *
	 * @Date: 19/04/23
	 *
	 * @Description: Shipping Charge for Multiple Quantity
	 */

	@Test
	public void TC27_Shipping_Charge_For_Multiple_Quantity() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC27_Shipping_Charge_For_Multiple_Quantity");
			LogTextFile.writeTestCaseStatus("TC27_Shipping_Charge_For_Multiple_Quantity", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC27", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				try {
					wait.until(
							ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					String emailId = WEL.enterNewUserIdInCheckoutLoginRegistration();
					WEL.clickOnCreateAccountButtonInCheckoutLoginRegistration();
					try {
						wait.until(ExpectedConditions.presenceOfElementLocated(
								By.xpath("//div[@class='welCheckoutJourneyMainDiv']")));
						WEL.enterConfirmEmailIdInCheckout(emailId);
						WEL.enterPasswordInCheckout(excelOperation.getTestData("TC27",
								"WEL_Test_Data", "Password"));
						WEL.clickOnSaveAndContinue();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h5[@id='shippingAddressTitle']/span")));
							String country1 = excelOperation
									.getTestData("TC27", "WEL_Test_Data", "Shipping_Country")
									.split(",")[0];
							String country2 = excelOperation
									.getTestData("TC27", "WEL_Test_Data", "Shipping_Country")
									.split(",")[1];

							// Validation for Brazil
							WEL.selectShipCountry(country1);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(
										By.xpath("//input[@id='line1']")));
							} catch (Exception e) {
								Reporting.updateTestReport(
										"AddressLine1 is not appeared caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
							BigDecimal courierChargeForOneUnit = WEL
									.fetchShippingChargeNonUS(driver, "Courier");

							// Validation for Ameriocan samoa
							WEL.selectShipCountry(country2);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(
										By.xpath("//input[@id='line1']")));
							} catch (Exception e) {
								Reporting.updateTestReport(
										"AddressLine1 is not appeared caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
							BigDecimal twoDayChargeForOneUnit = WEL
									.fetchShippingChargeNonUS(driver, "Standard Shipping");
							// ScrollingWebPage.PageScrollUp(driver, 0, -100, SS_path);
							driver.navigate().refresh();
							try {
								wait.until(ExpectedConditions.elementToBeClickable(
										By.xpath("//span[@id='backTocartNavbarMainId']/a")));
								WEL.clickOnBackToCartButton();

								try {
									wait.until(ExpectedConditions.elementToBeClickable(
											By.xpath("//select[@id='quantity_0']")));
									String quantity = excelOperation.getTestData("TC27",
											"WEL_Test_Data", "Quantity");
									WEL.selectQuantity(quantity);
									WEL.clickonCheckOutButtonOnCartPage();
									String eamilId1 = WEL
											.enterNewUserIdInCheckoutLoginRegistration();
									WEL.clickOnCreateAccountButtonInCheckoutLoginRegistration();
									try {
										wait.until(ExpectedConditions
												.presenceOfElementLocated(By.xpath(
														"//div[@class='welCheckoutJourneyMainDiv']")));
										WEL.enterConfirmEmailIdInCheckout(eamilId1);
										WEL.enterPasswordInCheckout(excelOperation.getTestData(
												"TC27", "WEL_Test_Data", "Password"));
										WEL.clickOnSaveAndContinue();
										try {
											wait.until(ExpectedConditions
													.visibilityOfElementLocated(By.xpath(
															"//h5[@id='shippingAddressTitle']/span")));

											WEL.selectShipCountry(country1);
											try {
												wait.until(ExpectedConditions
														.elementToBeClickable(By.xpath(
																"//input[@id='line1']")));
											} catch (Exception e) {
												Reporting.updateTestReport(
														"AddressLine1 is not appeared caused timeout exception",
														CaptureScreenshot.getScreenshot(
																SS_path),
														StatusDetails.FAIL);
											}
											// validation for Brzil/ columbia
											BigDecimal courierChargeForMultiUnit = WEL
													.fetchShippingChargeNonUS(driver,
															"Courier");

											if ((courierChargeForOneUnit.add((new BigDecimal(
													quantity).subtract(new BigDecimal("1")))
													.multiply(new BigDecimal("10"))))
													.setScale(2,
															RoundingMode.CEILING)
													.compareTo(
															courierChargeForMultiUnit) == 0)
												Reporting.updateTestReport(
														"Courier charge has been correctly calculated ",
														CaptureScreenshot.getScreenshot(
																SS_path),
														StatusDetails.PASS);
											else
												Reporting.updateTestReport(
														"Courier charge has been wrongly calculated as: "
																+ (courierChargeForOneUnit
																.add((new BigDecimal(
																		quantity)
																		.subtract(
																				new BigDecimal(
																						"1"))).multiply(
																		new BigDecimal(
																				"10")))).setScale(
																2,
																RoundingMode.CEILING),
														CaptureScreenshot.getScreenshot(
																SS_path),
														StatusDetails.FAIL);
											WEL.selectShipCountry(country2);
											try {
												wait.until(ExpectedConditions
														.elementToBeClickable(By.xpath(
																"//input[@id='line1']")));
											} catch (Exception e) {
												Reporting.updateTestReport(
														"AddressLine1 is not appeared caused timeout exception",
														CaptureScreenshot.getScreenshot(
																SS_path),
														StatusDetails.FAIL);
											}
											BigDecimal twoDayChargeForMultiUnit = WEL
													.fetchShippingChargeNonUS(driver,
															"Standard Shipping");
											if ((twoDayChargeForOneUnit.add((new BigDecimal(
													quantity).subtract(new BigDecimal("1")))
													.multiply(new BigDecimal("4"))))
													.setScale(2,
															RoundingMode.CEILING)
													.compareTo(
															twoDayChargeForMultiUnit) == 0)
												Reporting.updateTestReport(
														"Standard Shipping charge has been correctly calculated ",
														CaptureScreenshot.getScreenshot(
																SS_path),
														StatusDetails.PASS);
											else
												Reporting.updateTestReport(
														"Standard Shipping charge has been wrongly calculated ",
														CaptureScreenshot.getScreenshot(
																SS_path),
														StatusDetails.FAIL);
										} catch (Exception e) {
											Reporting.updateTestReport(
													"User was not in shipping step and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path),
													StatusDetails.FAIL);
										}
									} catch (Exception e) {
										Reporting.updateTestReport(
												"User was not in Checkout login page and caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.FAIL);
									}

								} catch (Exception e) {
									Reporting.updateTestReport(
											"Quantity dropdown was not clcikable in cart page",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Back To Cart button not clickbale and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);

							}

						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not in shipping step and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not in Checkout login page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}

				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}/*
	 * @Date: 19/04/23
	 *
	 * @Description: Adds one product to cart and navigates to an fro between
	 * shipping and billing
	 */

	@Test
	public void TC26_Multiple_To_And_Fro_Navigation_Between_Shipping_And_Billing() throws IOException {
		try {
			Reporting.test = Reporting.extent
					.createTest("TC26_Multiple_To_And_Fro_Navigation_Between_DShipping_And_Billing");
			LogTextFile.writeTestCaseStatus("TC26_Multiple_To_And_Fro_Navigation_Between_DShipping_And_Billing",
					"Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC26", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
							driver.navigate().refresh();
						}
					} catch (Exception e1) {
						Reporting.updateTestReport(
								"User was not in the cart page"
										+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				BigDecimal subtotal = new BigDecimal(
						WEL.fetchOrderSubTotalInCartPage().substring(1));
				if (price.compareTo(subtotal) == 0)
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				try {
					wait.until(
							ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
						String GuestEmail = WEL.enterGuestuser();
						WEL.clickingOnCreateAccoutButton();
						WEL.guestConfirmEmailId(GuestEmail);
						WEL.enterPassword(excelOperation.getTestData("TC26", "WEL_Test_Data",
								"Password"));
						WEL.clickonAgreementCheckBox();
						WEL.clickingOnSaveAndContinue();
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//h5[@id='shippingAddressTitle']/span")));
							Reporting.updateTestReport(
									"User was in shipping step after successful registration",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.PASS);
						} catch (Exception e) {
							Reporting.updateTestReport(
									"User was not in shipping step and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.PASS);
						}
						try {
							wait.until(ExpectedConditions.visibilityOfElementLocated(
									By.xpath("//div[@id='orderSummaryProductTotalValue']")));

							BigDecimal firstproductprice2 = new BigDecimal(
									WEL.fetchFirstProductPriceInOrderReview().substring(1));

							String discount = WEL.fetchDiscountInOrderReview();
							if (discount.contains(","))
								discount = discount.replace(",", "");
							BigDecimal discountinorderreview = new BigDecimal(
									discount.substring(1));
							String totalorderReview = WEL.fetchTotalInOrderReview();
							if (totalorderReview.contains(","))
								totalorderReview = totalorderReview.replace(",", "");
							BigDecimal orderTotalPrice1 = new BigDecimal(
									totalorderReview.substring(1));
							if (firstproductprice2.subtract(discountinorderreview)
									.compareTo(orderTotalPrice1) == 0)
								Reporting.updateTestReport(
										"First Product price + Second Product price -discount "
												+ " = Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.PASS);
							else
								Reporting.updateTestReport(
										"First Product price +second product price- discount is not equal to Order total in Order Review step",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);

						} catch (Exception e) {
							Reporting.updateTestReport(
									"Order summary tab was not visible" + e.getMessage(),
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not redirected to checkout login/ registration page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
					// WEL.clickOnEnterNewAddressButtonOnShippingPage();
					WEL.firstName(
							excelOperation.getTestData("TC26", "WEL_Test_Data", "First_Name"));
					WEL.lastName(
							excelOperation.getTestData("TC26", "WEL_Test_Data", "Last_Name"));
					WEL.selectShipCountry(excelOperation.getTestData("TC26", "WEL_Test_Data",
							"Shipping_Country"));
					WEL.shipAddressLineOne(excelOperation.getTestData("TC26", "WEL_Test_Data",
							"Shipping_Address_line1"));
					WEL.shipTownCity(excelOperation.getTestData("TC26", "WEL_Test_Data",
							"Shipping_City/ Province"));
					WEL.enterState(excelOperation.getTestData("TC26", "WEL_Test_Data",
							"Shipping_State"));
					WEL.shipPostCode(excelOperation.getTestData("TC26", "WEL_Test_Data",
							"Shipping_Zip_Code"));
					WEL.shipPhonenumber(excelOperation.getTestData("TC26", "WEL_Test_Data",
							"Shipping_Phone_Number"));
					WEL.shipSaveAndContinueButton();
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
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[@class='helpButton']")));
						ScrollingWebPage.PageScrolldown(driver, 0, 500, SS_path);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(
									By.xpath("//label[@id='sameAsBillingLabel']")));
							WEL.shipAndBillAddressSection();
							WEL.selectBillCountry(excelOperation.getTestData("TC26",
									"WEL_Test_Data", "Bill_Country"));
							WEL.addressLineOne(excelOperation.getTestData("TC26",
									"WEL_Test_Data", "Bill_Address_line1"));
							WEL.enterZipcode(excelOperation.getTestData("TC26", "WEL_Test_Data",
									"Bill_Zip_Code"));
							WEL.enterCity(excelOperation.getTestData("TC26", "WEL_Test_Data",
									"Bill_City/ Province"));
							WEL.enterPhoneNumber(excelOperation.getTestData("TC26",
									"WEL_Test_Data", "Bill_Phone_Number"));
							try {
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
										"//a[@href='/welstorefront/checkout/multi/delivery-address/add/']/span[@class='stepEdit']")));
								WEL.clickOnShippingDetailsEditIcon();
								WEL.firstName(excelOperation.getTestData("TC26",
										"WEL_Test_Data", "First_Name"));
								WEL.lastName(excelOperation.getTestData("TC26", "WEL_Test_Data",
										"Last_Name"));
								WEL.selectShipCountry(excelOperation.getTestData("TC26",
										"WEL_Test_Data", "Shipping_Country"));
								WEL.shipAddressLineOne(excelOperation.getTestData("TC26",
										"WEL_Test_Data", "Shipping_Address_line1"));
								WEL.shipTownCity(excelOperation.getTestData("TC26",
										"WEL_Test_Data", "Shipping_City/ Province"));
								WEL.enterState(excelOperation.getTestData("TC26",
										"WEL_Test_Data", "Shipping_State"));
								WEL.shipPostCode(excelOperation.getTestData("TC26",
										"WEL_Test_Data", "Shipping_Zip_Code"));
								WEL.shipPhonenumber(excelOperation.getTestData("TC26",
										"WEL_Test_Data", "Shipping_Phone_Number"));
								WEL.shipSaveAndContinueButton();
								try {
									if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
											.isDisplayed())
										WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Failed to click Address on Address SUggestion due to timeout exception",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.INFO);
								}
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(
											By.xpath("//div[@class='helpButton']")));
									ScrollingWebPage.PageScrolldown(driver, 0, 500, SS_path);
									try {
										wait.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//label[@id='sameAsBillingLabel']")));
										WEL.shipAndBillAddressSection();
										WEL.selectBillCountry(excelOperation.getTestData("TC26",
												"WEL_Test_Data", "Bill_Country"));
										WEL.addressLineOne(excelOperation.getTestData("TC26",
												"WEL_Test_Data", "Bill_Address_line1"));
										WEL.enterZipcode(excelOperation.getTestData("TC26",
												"WEL_Test_Data", "Bill_Zip_Code"));
										WEL.enterCity(excelOperation.getTestData("TC26",
												"WEL_Test_Data", "Bill_City/ Province"));
										WEL.enterPhoneNumber(excelOperation.getTestData("TC26",
												"WEL_Test_Data", "Bill_Phone_Number"));
										try {
											wait.until(ExpectedConditions
													.elementToBeClickable(By.xpath(
															"//div[@class='step-head checkoutCompletedStep']//div[@class='edit']/a")));
											WEL.clickOnShippingDetailsEditIcon();
											WEL.firstName(excelOperation.getTestData("TC26",
													"WEL_Test_Data", "First_Name"));
											WEL.lastName(excelOperation.getTestData("TC26",
													"WEL_Test_Data", "Last_Name"));
											WEL.selectShipCountry(excelOperation.getTestData(
													"TC26", "WEL_Test_Data",
													"Shipping_Country"));
											WEL.shipAddressLineOne(excelOperation.getTestData(
													"TC26", "WEL_Test_Data",
													"Shipping_Address_line1"));
											WEL.shipTownCity(excelOperation.getTestData("TC26",
													"WEL_Test_Data",
													"Shipping_City/ Province"));
											WEL.enterState(excelOperation.getTestData("TC26",
													"WEL_Test_Data", "Shipping_State"));
											WEL.shipPostCode(excelOperation.getTestData("TC26",
													"WEL_Test_Data", "Shipping_Zip_Code"));
											WEL.shipPhonenumber(excelOperation.getTestData(
													"TC26", "WEL_Test_Data",
													"Shipping_Phone_Number"));
											WEL.shipSaveAndContinueButton();
											try {
												if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
														.isDisplayed())
													WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
											} catch (Exception e) {
												Reporting.updateTestReport(
														"Failed to click Address on Address SUggestion due to timeout exception",
														CaptureScreenshot.getScreenshot(
																SS_path),
														StatusDetails.INFO);
											}
											try {
												wait.until(ExpectedConditions
														.visibilityOfElementLocated(By.xpath(
																"//div[@class='helpButton']")));
												ScrollingWebPage.PageScrolldown(driver, 0, 500,
														SS_path);
												try {
													wait.until(ExpectedConditions
															.elementToBeClickable(By.xpath(
																	"//label[@id='sameAsBillingLabel']")));
													WEL.shipAndBillAddressSection();
													WEL.selectBillCountry(
															excelOperation.getTestData("TC26",
																	"WEL_Test_Data",
																	"Bill_Country"));
													WEL.addressLineOne(
															excelOperation.getTestData("TC26",
																	"WEL_Test_Data",
																	"Bill_Address_line1"));
													WEL.enterZipcode(excelOperation.getTestData(
															"TC26", "WEL_Test_Data",
															"Bill_Zip_Code"));
													WEL.enterCity(excelOperation.getTestData(
															"TC26", "WEL_Test_Data",
															"Bill_City/ Province"));
													WEL.enterPhoneNumber(
															excelOperation.getTestData("TC26",
																	"WEL_Test_Data",
																	"Bill_Phone_Number"));
												} catch (Exception e) {
													Reporting.updateTestReport(
															"Billing address line 1 was not clickable",
															CaptureScreenshot
																	.getScreenshot(SS_path),
															StatusDetails.FAIL);
												}
											} catch (Exception e) {
												Reporting.updateTestReport(
														"The help button was not visible and caused timeout exception",
														CaptureScreenshot.getScreenshot(
																SS_path),
														StatusDetails.FAIL);
											}

										} catch (Exception e) {
											Reporting.updateTestReport(
													"The shipping address edit icon was not clickable and caused timeout",
													CaptureScreenshot.getScreenshot(SS_path),
													StatusDetails.FAIL);
										}
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Billing address line 1 was not clickable",
												CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"The help button was not visible and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path),
											StatusDetails.FAIL);
								}

							} catch (Exception e) {
								Reporting.updateTestReport(
										"The shipping address edit icon was not clickable and caused timeout",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"Shipping same as billing was not clickable and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"The help button was not visible and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}

				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}
	}

	/*
	 * @Date: 19/04/23
	 *
	 * @Description: Checks the email format in checkout login / registration page
	 */
	@Test
	public void TC28_Validate_the_Email_Id_Format_In_Checkout_Login_And_Registration_Page() throws IOException {
		try {
			Reporting.test = Reporting.extent
					.createTest("TC28_Validate_the_Email_Id_Format_In_Checkout_Login_And_Registration_Page");
			LogTextFile.writeTestCaseStatus("TC28_Validate_the_Email_Id_Format_In_Checkout_Login_And_Registration_Page",
					"Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(CommonFunctions.concatenateURLWithNodeIP("TC28", "WEL_Test_Data", "URL","WEL_Homepage_URL"));
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//div[@class='pdp-addtocart-div']")));
				WEL.clickOnAddToCartButton();
				String[] emailIds = excelOperation.getTestData("TC28", "WEL_Test_Data", "Email_Address").split(",");



				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.id("cartPageMainTitle")));
				} catch (Exception e) {
					try {
						if (driver
								.findElement(
										By.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
								.isDisplayed()) {
							Reporting.updateTestReport(
									"Server error came in cart page and the page was refreshed",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.INFO);
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
					wait.until(
							ExpectedConditions.elementToBeClickable(By.id("cartCheckoutBtn")));
					WEL.clickonCheckOutButtonOnCartPage();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.id("checkoutLogRegPageTitle")));
						// Checking for various invalid email ids in create account email id
						// field
						for (String email : emailIds) {
							WEL.enterNewUserIdInCheckoutLoginRegistrationNonAutoGenerated(
									email);
							WEL.clickOutsideTheEmailIdField();
							WEL.checkOnClickErrorMessageInCheckoutLoginRegistrationPage();
						}

						driver.navigate().refresh();

						// Checking for various invalid email ids in existing user email id
						// field
						for (String email : emailIds) {
							WEL.enterExistingUserNameInCheckoutLoginPage(email);
							WEL.enterExistingUserPasswordInCheckoutLoginPage(excelOperation
									.getTestData("TC28", "WEL_Test_Data", "Password"));
							WEL.clickOutsideTheEmailIdField();
							WEL.checkOnClickErrorMessageInCheckoutLoginRegistrationPage();
						}

					} catch (Exception e) {
						Reporting.updateTestReport(
								"User was not in checkout login / registration page and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"Checkout button was not clickable in cart page and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport(
						"Add To Cart button on product page was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver,CommonFunctions.concatenateURLWithNodeIP("WILEY_LogOut_URL", "Generic_Dataset", "Data","WEL_Homepage_URL"));
		}

	}

}