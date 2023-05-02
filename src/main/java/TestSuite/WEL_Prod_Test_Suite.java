package TestSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

public class WEL_Prod_Test_Suite extends DriverModule {

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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				WEL.clickOnLoginButtonHomepage();
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@href='https://www.efficientlearning.com/register']")));
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
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='account']")));
							Reporting.updateTestReport("Account was created successfully",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
							WEL.clickOnAccountButtonInHomepage();
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("//button[contains(text(),'I Accept')]")));
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
						} catch (Exception e) {
							Reporting.updateTestReport(
									"Account button in homepage was not visisble and caused timeout exception",
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
			} catch (Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
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
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[contains(text(),'I Accept')]")));
						Reporting.updateTestReport("Wiley & WEL Privacy Agreement banner was displayed",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
						WEL.clickonAcceptButtonOnWileyWELPrivacyAgreement();
					} catch (Exception e) {
						Reporting.updateTestReport("Wiley & WEL Privacy Agreement banner was not displayed",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					}
					try {
						wait.until(
								ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'ACCOUNT')]")));
						Reporting.updateTestReport("Account Link is appearing after successful Login",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						WEL.clickOnSignOutButtonInMyAccountPage();
					} catch (Exception e) {
						Reporting.updateTestReport("Failed display the Account Link",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport(
							"Username field in login form was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
	 * 
	 * @Description: Adds one physical product to cart and registers one new user
	 */

	@Test
	public void TC03_User_Registration_During_Checkout_for_Physical_Cart() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_User_Registration_During_Checkout_for_Physical_Cart");
			LogTextFile.writeTestCaseStatus("TC03_User_Registration_During_Checkout_for_Physical_Cart", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
					WEL.clickOnCMALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@class='shop-courses-btn  ']")));
						driver.navigate().refresh();
						WEL.clickOnExploreCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("(//div[@class='price-block'])[1]")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(
										By.xpath("//a[@href='/cma/products/platinum-cma-review-course/']"
												+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
								WEL.clickOnCMAViewCourseButton();
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By
											.xpath("(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")));
									ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
								} catch (Exception e) {
									Reporting.updateTestReport("GET PLATINUM WILEY CMA COURSE -> button didn't appear",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print + eBook0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
															wait.until(ExpectedConditions.visibilityOfElementLocated(By
																	.xpath("//div[@id='orderSummaryProductTotalValue']")));

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
																totalorderReview = totalorderReview.replace(",", "");
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
																Reporting.updateTestReport("First Product price + Tax "
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CMA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Explore course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CMA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
	 * 
	 * @Description: Adds one physical product to cart and logs in during chekcout
	 */

	@Test
	public void TC04_User_Login_during_Checkout() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC04_User_Login_during_Checkout");
			LogTextFile.writeTestCaseStatus("TC04_User_Login_during_Checkout", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
					WEL.clickOnCMALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@class='shop-courses-btn  ']")));
						driver.navigate().refresh();
						WEL.clickOnExploreCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("(//div[@class='price-block'])[1]")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(
										By.xpath("//a[@href='/cma/products/platinum-cma-review-course/']"
												+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
								WEL.clickOnCMAViewCourseButton();
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By
											.xpath("(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")));
									ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
								} catch (Exception e) {
									Reporting.updateTestReport("GET PLATINUM WILEY CMA COURSE -> button didn't appear",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print + eBook0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CMA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Explore course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CMA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
					WEL.clickOnCMALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@class='shop-courses-btn  ']")));
						driver.navigate().refresh();
						WEL.clickOnExploreCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("(//div[@class='price-block'])[1]")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(
										By.xpath("//a[@href='/cma/products/platinum-cma-review-course/']"
												+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
								WEL.clickOnCMAViewCourseButton();
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By
											.xpath("(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")));
									ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
								} catch (Exception e) {
									Reporting.updateTestReport("GET PLATINUM WILEY CMA COURSE -> button didn't appear",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print + eBook0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
														driver.get(excelOperation.getTestData("Yopmail_URL",
																"Generic_Dataset", "Data"));
														WEL.enterEmailIdInYopmail(excelOperation.getTestData("TC05",
																"WEL_Test_Data", "Email_Address"));
														WEL.clickOnCheckInboxButton();
														int flag = EmailValidation.forgotPasswordEmailForWEL(driver,
																SS_path, WEL);
														if (flag == 1) {
															WEL.enterNewPasswordInResetPassword(excelOperation
																	.getTestData("TC05", "WEL_Test_Data", "Password"));
															WEL.enterConfirmPasswordInResetPassword(excelOperation
																	.getTestData("TC05", "WEL_Test_Data", "Password"));
															WEL.clickOnResetPasswordSubmit();
															try {
																wait.until(ExpectedConditions
																		.elementToBeClickable(By.id("username")));
																WEL.enterUserNameInLoginPage(excelOperation.getTestData(
																		"TC05", "WEL_Test_Data", "Email_Address"));
																WEL.enterPasswordInLoginPage(excelOperation.getTestData(
																		"TC05", "WEL_Test_Data", "Password"));
																WEL.clickOnStandaloneLoginButton();
																try {
																	wait.until(ExpectedConditions
																			.elementToBeClickable(By.xpath(
																					"//button[contains(text(),'I Accept')]")));
																	Reporting.updateTestReport(
																			"Wiley & WEL Privacy Agreement banner was displayed",
																			CaptureScreenshot.getScreenshot(SS_path),
																			StatusDetails.INFO);
																	WEL.clickonAcceptButtonOnWileyWELPrivacyAgreement();
																} catch (Exception e) {
																	Reporting.updateTestReport(
																			"Wiley & WEL Privacy Agreement banner was not displayed",
																			CaptureScreenshot.getScreenshot(SS_path),
																			StatusDetails.INFO);
																}
																try {
																	wait.until(ExpectedConditions
																			.elementToBeClickable(By.xpath(
																					"//div[contains(text(),'ACCOUNT')]")));
																	Reporting.updateTestReport(
																			"Account Link is appearing after successful Login",
																			CaptureScreenshot.getScreenshot(SS_path),
																			StatusDetails.PASS);
																	WEL.clickOnSignOutButtonInMyAccountPage();
																} catch (Exception e) {
																	Reporting.updateTestReport(
																			"Failed display the Account Link",
																			CaptureScreenshot.getScreenshot(SS_path),
																			StatusDetails.FAIL);
																}
															} catch (Exception e) {
																Reporting.updateTestReport(
																		"Username field in login form was not clickable and caused timeout exception",
																		CaptureScreenshot.getScreenshot(SS_path),
																		StatusDetails.FAIL);
															}
														} else {
															Reporting.updateTestReport(
																	"No reset password mail was recieved in yopmail inbox",
																	CaptureScreenshot.getScreenshot(SS_path),
																	StatusDetails.FAIL);
														}

													} catch (Exception e) {
														Reporting.updateTestReport(excelOperation.getTestData(
																"ResetPasswordMessageWEL", "Generic_Messages", "Data")
																+ " : this message was not shown after submitting the email",
																CaptureScreenshot.getScreenshot(SS_path),
																StatusDetails.FAIL);
													}
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CMA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Explore course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CMA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
					WEL.clickOnCMALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@class='shop-courses-btn  ']")));
						driver.navigate().refresh();
						WEL.clickOnExploreCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("(//div[@class='price-block'])[1]")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(
										By.xpath("//a[@href='/cma/products/platinum-cma-review-course/']"
												+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
								WEL.clickOnCMAViewCourseButton();
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By
											.xpath("(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")));
									ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
								} catch (Exception e) {
									Reporting.updateTestReport("GET PLATINUM WILEY CMA COURSE -> button didn't appear",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
								}
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print + eBook0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CMA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Explore course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CMA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
	 * @Date: 6/4/23
	 * 
	 * @Description: Add some product to cart in WEL and add Student discount promo
	 */

	@Test
	public void TC07_Add_Student_Discount_In_Anonymous_Cart() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC07_Add_Student_Discount_In_Anonymous_Cart");
			LogTextFile.writeTestCaseStatus("TC07_Add_Student_Discount_In_Anonymous_Cart", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									try {
										wait.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//span[@class='apply-discount-link']")));
										WEL.clickOnApplyStudentDiscountLink();
										try {
											wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
													"//p[@class='current-price-link']/a[contains(text(),'Switch to student discount price')]")));
											WEL.clickOnSwitchToStudentDiscountLink();
											BigDecimal price = new BigDecimal(
													WEL.fetchProductPriceInPDP().substring(1));
											try {
												wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
														"//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
													"Switch to student discount link was not clickable and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Add To Cart button on product page was not clickable and caused timeout exception ",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									try {
										wait.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//span[@class='apply-discount-link']")));
										WEL.clickOnApplyStudentDiscountLink();
										try {
											wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
													"//p[@class='current-price-link']/a[contains(text(),'Switch to student discount price')]")));
											WEL.clickOnSwitchToStudentDiscountLink();
											BigDecimal firstproductprice = new BigDecimal(
													WEL.fetchProductPriceInPDP().substring(1));
											try {
												wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
														"//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
															"(//div[@class='col-6 noPadding navyBlueLabel' and contains(text(),'STUDENT')])[1]")));
													Reporting.updateTestReport(
															"Student discount was applied in cart page",
															CaptureScreenshot.getScreenshot(SS_path),
															StatusDetails.PASS);
													driver.get(WEL_Homepage_URL);
													try {
														wait.until(ExpectedConditions.elementToBeClickable(
																By.xpath("//a[@aria-label='login']")));
														ScrollingWebPage.PageDown(driver, SS_path);
														try {
															wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
																	"//a[@data-for='productTooltipCFA' and @data-key='0']")));
															WEL.clickOnCFALinkOnHomepage();
															try {
																wait.until(ExpectedConditions.elementToBeClickable(By
																		.xpath("//button[@class='shop-courses-btn  ']")));
																driver.navigate().refresh();
																WEL.clickOnExploreCourseButton();
																try {
																	wait.until(ExpectedConditions
																			.visibilityOfElementLocated(By.xpath(
																					"(//div[@class='card-title course-pkg-cards-heading other-block'])[1]")));
																	ScrollingWebPage.PageDown(driver, SS_path);
																	try {
																		wait.until(ExpectedConditions
																				.elementToBeClickable(By.xpath(
																						"//a[@href='/cfa/products/']"
																								+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
																		WEL.clickOnCFAViewCourseButton();
																		try {
																			wait.until(ExpectedConditions
																					.visibilityOfElementLocated(By
																							.xpath("//div[@class='col compare-wiley-heading']")));
																			ScrollingWebPage.PageDown(driver, SS_path);
																			try {
																				wait.until(ExpectedConditions
																						.elementToBeClickable(By.xpath(
																								"//a[@href='/cfa/products/level-1/platinum-cfa-course/' and contains(text(),'View Course')]")));
																				WEL.clickOnCFAViewCourseLink();
																				try {
																					wait.until(ExpectedConditions
																							.visibilityOfElementLocated(
																									By.xpath(
																											"//label[@for='Print0']")));
																					ScrollingWebPage.PageDown(driver,
																							SS_path);
																					try {
																						wait.until(ExpectedConditions
																								.elementToBeClickable(By
																										.xpath("//span[@class='apply-discount-link']")));
																						WEL.clickOnApplyStudentDiscountLink();
																						try {
																							wait.until(
																									ExpectedConditions
																											.elementToBeClickable(
																													By.xpath(
																															"//p[@class='current-price-link']/a[contains(text(),'Switch to student discount price')]")));
																							WEL.clickOnSwitchToStudentDiscountLink();
																							BigDecimal secondproductprice = new BigDecimal(
																									WEL.fetchProductPriceInPDP()
																											.substring(
																													1));
																							try {
																								wait.until(
																										ExpectedConditions
																												.elementToBeClickable(
																														By.xpath(
																																"//button[@type='submit' and contains(text(),'ADD TO CART')]")));
																								WEL.clickOnAddToCartButton();
																								try {
																									wait.until(
																											ExpectedConditions
																													.visibilityOfElementLocated(
																															By.id("cartPageMainTitle")));
																								} catch (Exception e) {
																									try {
																										if (driver
																												.findElement(
																														By.xpath(
																																"//h1[contains(text(),'SERVER ERROR (500)')]"))
																												.isDisplayed()) {
																											Reporting
																													.updateTestReport(
																															"Server error came in cart page and the page was refreshed",
																															CaptureScreenshot
																																	.getScreenshot(
																																			SS_path),
																															StatusDetails.INFO);
																											driver.navigate()
																													.refresh();
																										}
																									} catch (Exception e1) {
																										Reporting
																												.updateTestReport(
																														"User was not in the cart page"
																																+ " and caused timeout exception",
																														CaptureScreenshot
																																.getScreenshot(
																																		SS_path),
																														StatusDetails.FAIL);
																									}
																								}
																								ScrollingWebPage
																										.PageScrolldown(
																												driver,
																												0, 400,
																												SS_path);
																								try {
																									wait.until(
																											ExpectedConditions
																													.visibilityOfElementLocated(
																															By.xpath(
																																	"(//div[@class='col-6 noPadding navyBlueLabel' and contains(text(),'STUDENT')])[1]")));
																									Reporting
																											.updateTestReport(
																													"Student discount was applied in cart page",
																													CaptureScreenshot
																															.getScreenshot(
																																	SS_path),
																													StatusDetails.PASS);
																									BigDecimal subtotal = new BigDecimal(
																											WEL.fetchOrderTotalOnCartPage()
																													.substring(
																															1));
																									if (firstproductprice
																											.add(secondproductprice)
																											.compareTo(
																													subtotal) == 0)
																										Reporting
																												.updateTestReport(
																														"The addition of all the products' price is same as the subtotal in cart page",
																														CaptureScreenshot
																																.getScreenshot(
																																		SS_path),
																														StatusDetails.PASS);
																									else
																										Reporting
																												.updateTestReport(
																														"The addition of the price is not match with the subtotal in cart page due to STUDENT discount is aplied in Cart Page",
																														CaptureScreenshot
																																.getScreenshot(
																																		SS_path),
																														StatusDetails.FAIL);
																									try {
																										wait.until(
																												ExpectedConditions
																														.elementToBeClickable(
																																By.id("cartCheckoutBtn")));
																										WEL.clickonCheckOutButtonOnCartPage();
																										try {
																											wait.until(
																													ExpectedConditions
																															.visibilityOfElementLocated(
																																	By.id("checkoutLogRegPageTitle")));
																											WEL.enterExistingUserNameInCheckoutLoginPage(
																													excelOperation
																															.getTestData(
																																	"TC08",
																																	"WEL_Test_Data",
																																	"Email_Address"));
																											WEL.enterExistingUserPasswordInCheckoutLoginPage(
																													excelOperation
																															.getTestData(
																																	"TC08",
																																	"WEL_Test_Data",
																																	"Password"));
																											WEL.clickOnLoginButtonInCheckoutLoginPage();
																											try {
																												wait.until(
																														ExpectedConditions
																																.visibilityOfElementLocated(
																																		By.xpath(
																																				"//h5[@id='shippingAddressTitle']/span")));
																												Reporting
																														.updateTestReport(
																																"User was in shipping step after successful registration",
																																CaptureScreenshot
																																		.getScreenshot(
																																				SS_path),
																																StatusDetails.PASS);
																											} catch (Exception e) {
																												Reporting
																														.updateTestReport(
																																"User was not in shipping step and caused timeout exception",
																																CaptureScreenshot
																																		.getScreenshot(
																																				SS_path),
																																StatusDetails.FAIL);
																											}
																											try {
																												wait.until(
																														ExpectedConditions
																																.visibilityOfElementLocated(
																																		By.xpath(
																																				"//div[@id='orderSummaryProductTotalValue']")));
																												// This
																												// statement
																												// is we
																												// are
																												// getting
																												// the
																												// firstProduct
																												// price
																												// in
																												// Order
																												// Review
																												// page
																												// and
																												// Storing
																												// in
																												// String
																												// Object

																												BigDecimal firstproductprice2 = new BigDecimal(
																														WEL.fetchFirstProductPriceInOrderReview()
																																.substring(
																																		1));

																												String discount = WEL
																														.fetchDiscountInOrderReview();
																												if (discount
																														.contains(
																																","))
																													discount = discount
																															.replace(
																																	",",
																																	"");
																												BigDecimal discountinorderreview = new BigDecimal(
																														discount.substring(
																																1));
																												String totalorderReview = WEL
																														.fetchTotalInOrderReview();
																												if (totalorderReview
																														.contains(
																																","))
																													totalorderReview = totalorderReview
																															.replace(
																																	",",
																																	"");
																												BigDecimal orderTotalPrice1 = new BigDecimal(
																														totalorderReview
																																.substring(
																																		1));
																												if (firstproductprice2
																														.subtract(
																																discountinorderreview)
																														.compareTo(
																																orderTotalPrice1) == 0)
																													Reporting
																															.updateTestReport(
																																	"First Product price + Second Product price -discount "
																																			+ " = Order total in Order Review step",
																																	CaptureScreenshot
																																			.getScreenshot(
																																					SS_path),
																																	StatusDetails.PASS);
																												else
																													Reporting
																															.updateTestReport(
																																	"First Product price +second product price- discount is not equal to Order total in Order Review step",
																																	CaptureScreenshot
																																			.getScreenshot(
																																					SS_path),
																																	StatusDetails.FAIL);

																											} catch (Exception e) {
																												Reporting
																														.updateTestReport(
																																"Order summary tab was not visible"
																																		+ e.getMessage(),
																																CaptureScreenshot
																																		.getScreenshot(
																																				SS_path),
																																StatusDetails.FAIL);
																											}

																										} catch (Exception e) {
																											Reporting
																													.updateTestReport(
																															"User was not redirected to checkout login/ registration page and caused timeout exception",
																															CaptureScreenshot
																																	.getScreenshot(
																																			SS_path),
																															StatusDetails.FAIL);
																										}
																									} catch (Exception e) {
																										Reporting
																												.updateTestReport(
																														"Checkout button was not clickable in cart page and caused timeout exception",
																														CaptureScreenshot
																																.getScreenshot(
																																		SS_path),
																														StatusDetails.FAIL);
																									}
																								} catch (Exception e) {
																									Reporting
																											.updateTestReport(
																													"Student discount was not applied in cart page",
																													CaptureScreenshot
																															.getScreenshot(
																																	SS_path),
																													StatusDetails.PASS);
																								}

																							} catch (Exception e) {
																								Reporting
																										.updateTestReport(
																												"Add To Cart button on product page was not clickable and caused timeout exception ",
																												CaptureScreenshot
																														.getScreenshot(
																																SS_path),
																												StatusDetails.FAIL);
																							}
																						} catch (Exception e) {
																							Reporting.updateTestReport(
																									"Switch to student discount link was not clickable and caused timeout exception",
																									CaptureScreenshot
																											.getScreenshot(
																													SS_path),
																									StatusDetails.FAIL);
																						}
																					} catch (Exception e) {
																						Reporting.updateTestReport(
																								"Add To Cart button on product page was not clickable and caused timeout exception ",
																								CaptureScreenshot
																										.getScreenshot(
																												SS_path),
																								StatusDetails.FAIL);
																					}
																				} catch (Exception e) {
																					Reporting.updateTestReport(
																							"CIA Product details page didn't appear and caused timeout exception",
																							CaptureScreenshot
																									.getScreenshot(
																											SS_path),
																							StatusDetails.FAIL);
																				}

																			} catch (Exception e) {
																				Reporting.updateTestReport(
																						"The View Course link in CFA PDP was not clickable and caused timeout exception ",
																						CaptureScreenshot
																								.getScreenshot(SS_path),
																						StatusDetails.FAIL);
																			}
																		} catch (Exception e) {
																			Reporting.updateTestReport(
																					"After clicking on View course button, the page didn't show the cfa product title and caused timeout exception ",
																					CaptureScreenshot
																							.getScreenshot(SS_path),
																					StatusDetails.FAIL);
																		}
																	} catch (Exception e) {
																		Reporting.updateTestReport(
																				"View Course button on product page was not clickable and caused timeout exception ",
																				CaptureScreenshot.getScreenshot(
																						SS_path),
																				StatusDetails.FAIL);
																	}
																} catch (Exception e) {
																	Reporting.updateTestReport(
																			"After clicking on explore course button, the page didn't show the product title and caused timeout exception ",
																			CaptureScreenshot.getScreenshot(SS_path),
																			StatusDetails.FAIL);
																}
															} catch (Exception e) {
																Reporting.updateTestReport(
																		"Explore course button on product page was not clickable and caused timeout exception ",
																		CaptureScreenshot.getScreenshot(SS_path),
																		StatusDetails.FAIL);
															}
														} catch (Exception e) {
															Reporting.updateTestReport(
																	"CFA Link on homepage was not clickable and caused timeout exception ",
																	CaptureScreenshot.getScreenshot(SS_path),
																	StatusDetails.FAIL);
														}
													} catch (Exception e) {
														Reporting.updateTestReport(
																"Login button on homepage was not clickable and caused timeout exception ",
																CaptureScreenshot.getScreenshot(SS_path),
																StatusDetails.FAIL);
													}

												} catch (Exception e) {
													Reporting.updateTestReport(
															"Student discount was not applied in cart page",
															CaptureScreenshot.getScreenshot(SS_path),
															StatusDetails.PASS);
												}

											} catch (Exception e) {
												Reporting.updateTestReport(
														"Add To Cart button on product page was not clickable and caused timeout exception ",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										} catch (Exception e) {
											Reporting.updateTestReport(
													"Switch to student discount link was not clickable and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Add To Cart button on product page was not clickable and caused timeout exception ",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Explore course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
	 * @Date: 6/4/23
	 * 
	 * @Description: Add some product to cart in WEL and add some GOVT Promo
	 */

	@Test
	public void TC09_Extra_discount_coupon_code_GOVT() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC09_Extra_discount_coupon_code_GOVT");
			LogTextFile.writeTestCaseStatus("TC08_Add_to_cart_multiple_products_and_promotion_for_existing_user",
					"Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Explore course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
			driver.get(WEL_Homepage_URL);
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCPA' and @data-key='0']")));
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
							wait.until(ExpectedConditions.elementToBeClickable(
									By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
							WEL.clickOnCMALinkOnHomepage();
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
								wait.until(
										ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
								ScrollingWebPage.PageDown(driver, SS_path);
								try {
									wait.until(ExpectedConditions.elementToBeClickable(
											By.xpath("//a[@data-for='productTooltipCFA' and @data-key='0']")));
									WEL.clickOnCFALinkOnHomepage();
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
									WEL.enterFreeTrialPassword(
											excelOperation.getTestData("TC10", "WEL_Test_Data", "Password"));
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
								}

								catch (Exception e) {
									Reporting.updateTestReport(
											"Failed to click on CFA Product due to  timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport("The homepage was not loaded and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport("Failed to click on CMA Product due to  timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("The homepage was not loaded and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Failed to click on CPA Product due to  timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("The homepage was not loaded and caused timeout exception",
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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
	 * @Date: 17/4/23
	 * 
	 * @Description: Address SUggestion by Address Doctor in Shipping Page
	 */
	@Test
	public void TC12_StudentVerification_ForphysicalCartof_USANDNonUS() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC12_StudentVerification_ForphysicalCartof_USANDNonUS");
			LogTextFile.writeTestCaseStatus("TC12_StudentVerification_ForphysicalCartof_USANDNonUS", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
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
												wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
														"//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
														wait.until(ExpectedConditions.elementToBeClickable(
																By.xpath("//div[@class='edit']")));
													} catch (Exception e) {
														Reporting.updateTestReport(
																"Failed to Click on Edit Icon on Shipping page",
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
													"Switch to student discount link was not clickable and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Add To Cart button on product page was not clickable and caused timeout exception ",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
					WEL.clickOnCMALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@class='shop-courses-btn  ']")));
						driver.navigate().refresh();
						WEL.clickOnExploreCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("(//div[@class='price-block'])[1]")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(
										By.xpath("//a[@href='/cma/products/platinum-cma-review-course/']"
												+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
								WEL.clickOnCMAViewCourseButton();
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By
											.xpath("(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")));
									ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
									try {
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
												"(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")));
										ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
									} catch (Exception e) {
										Reporting.updateTestReport(
												"GET PLATINUM WILEY CMA COURSE -> button didn't appear",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
									}
									try {
										wait.until(ExpectedConditions.visibilityOfElementLocated(
												By.xpath("//label[@for='Print + eBook0']")));
										WEL.clickonCMAeBook();
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Failed to click on CMA eBook caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
									}
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
														.getTestData("TC14", "WEL_Test_Data", "Email_Address"));
												WEL.enterExistingUserPasswordInCheckoutLoginPage(excelOperation
														.getTestData("TC14", "WEL_Test_Data", "Password"));
												WEL.clickOnLoginButtonInCheckoutLoginPage();
												try {
													wait.until(ExpectedConditions.visibilityOfElementLocated(
															By.xpath("//h5[@class='welCommonHeadingTitle']")));
													WEL.firstName(excelOperation.getTestData("TC14", "WEL_Test_Data",
															"First_Name"));
													WEL.lastName(excelOperation.getTestData("TC14", "WEL_Test_Data",
															"Last_Name"));

													WEL.selectBillCountry(excelOperation.getTestData("TC14",
															"WEL_Test_Data", "Bill_Country"));
													WEL.addressLineOne(excelOperation.getTestData("TC14",
															"WEL_Test_Data", "Bill_Address_line1"));
													WEL.enterZipcode(excelOperation.getTestData("TC14", "WEL_Test_Data",
															"Bill_Zip_Code"));
													WEL.enterState(excelOperation.getTestData("TC14", "WEL_Test_Data",
															"Bill_State"));
													WEL.enterCity(excelOperation.getTestData("TC14", "WEL_Test_Data",
															"Bill_City/ Province"));
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

											driver.switchTo().frame(driver
													.findElement(By.xpath(".//iframe[@title='cardholder name']")));
											try {
												wait.until(
														ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
												WEL.enterCardHolderName(excelOperation.getTestData("TC14",
														"WEL_Test_Data", "First_Name"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='card number']")));
												WEL.enterCardNumber(excelOperation.getTestData("TC14", "WEL_Test_Data",
														"Card_Number"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
												WEL.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC14",
														"WEL_Test_Data", "Expiry_Month"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(
														driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
												WEL.selectExpirationYearFromDropDown(excelOperation.getTestData("TC14",
														"WEL_Test_Data", "Expiry_Year"));
												driver.switchTo().defaultContent();
												driver.switchTo().frame(driver
														.findElement(By.xpath(".//iframe[@title='securityCode']")));
												WEL.enterCVV_Number(
														excelOperation.getTestData("TC14", "WEL_Test_Data", "CVV"));
												driver.switchTo().defaultContent();
												WEL.saveAndContinueCheckOut();
												try {
													wait.until(ExpectedConditions.visibilityOfElementLocated(
															By.xpath("//div[@class='helpButton']")));
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
					WEL.clickOnCMALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@class='shop-courses-btn  ']")));
						driver.navigate().refresh();
						WEL.clickOnExploreCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("(//div[@class='price-block'])[1]")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(
										By.xpath("//a[@href='/cma/products/platinum-cma-review-course/']"
												+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
								WEL.clickOnCMAViewCourseButton();
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By
											.xpath("(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")));
									ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
									try {
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
												"(//button[contains(text(),'GET PLATINUM WILEY CMA COURSE')])[2]")));
										ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
									} catch (Exception e) {
										Reporting.updateTestReport(
												"GET PLATINUM WILEY CMA COURSE -> button didn't appear",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
									}
									try {
										wait.until(ExpectedConditions.visibilityOfElementLocated(
												By.xpath("//label[@for='Print + eBook0']")));
										WEL.clickonCMAeBook();
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Failed to click on CMA eBook caused timeout exception",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
														.getTestData("TC15", "WEL_Test_Data", "Email_Address"));
												WEL.enterExistingUserPasswordInCheckoutLoginPage(excelOperation
														.getTestData("TC15", "WEL_Test_Data", "Password"));
												WEL.clickOnLoginButtonInCheckoutLoginPage();
												try {
													wait.until(ExpectedConditions.visibilityOfElementLocated(
															By.xpath("//h5[@class='welCommonHeadingTitle']")));
													WEL.firstName(excelOperation.getTestData("TC15", "WEL_Test_Data",
															"First_Name"));
													WEL.lastName(excelOperation.getTestData("TC15", "WEL_Test_Data",
															"Last_Name"));
													WEL.selectBillCountry(excelOperation.getTestData("TC15",
															"WEL_Test_Data", "Bill_Country"));
													WEL.addressLineOne(excelOperation.getTestData("TC15",
															"WEL_Test_Data", "Bill_Address_line1"));
													WEL.enterZipcode(excelOperation.getTestData("TC15", "WEL_Test_Data",
															"Bill_Zip_Code"));
													WEL.enterState(excelOperation.getTestData("TC15", "WEL_Test_Data",
															"Bill_State"));
													WEL.enterCity(excelOperation.getTestData("TC15", "WEL_Test_Data",
															"Bill_City/ Province"));
													WEL.enterPhoneNumber(excelOperation.getTestData("TC15",
															"WEL_Test_Data", "Bill_Phone_Number"));
													WEL.verifyPaypaltext();
													Reporting.updateTestReport(
															"User was in Billing step after successful registration",
															CaptureScreenshot.getScreenshot(SS_path),
															StatusDetails.PASS);
												} catch (Exception e) {
													Reporting.updateTestReport(
															"User was not in billing step and caused timeout exception",
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}

	}/*
		 * @Date: 18/4/23
		 * 
		 * @Description: Add partner product having 100% discount to cart
		 */

	@Test
	public void TC17_AddPartnerproduct_Having100PrcentDiscountIntoCart() throws IOException {
		try {
			Reporting.test = Reporting.extent
					.createTest("TC17_AddPartnerproduct_Having100PrcentDiscountIntoCart%_Discount");
			LogTextFile.writeTestCaseStatus("TC17_AddPartnerproduct_Having100PrcentDiscountIntoCart", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(WEL_Homepage_URL);
			driver.get(excelOperation.getTestData("100%Discount_Product", "Generic_Dataset", "Data"));
			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='form-group']//input[@id='inputPartnerSearch']")));
				WEL.enterUniversityName(excelOperation.getTestData("TC17", "WEL_Test_Data", "Partner_Name"));
				ScrollingWebPage.PageScrolldown(driver, 0, 230, SS_path);
				Thread.sleep(100);
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//div[@class='container product-categories-container']//div[2]//dd/a[contains(text(),'CPA')]")));
					WEL.clickOnDeanDortonCMAProduct();
					ScrollingWebPage.PageDown(driver, SS_path);
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//button[@type='submit' and @class='add-to-cart-btn  ']")));
						String price = WEL.fetchPartnerProductPriceInPDP();
						try {
							wait.until(ExpectedConditions.elementToBeClickable(
									By.xpath("//button[@type='submit' and @class='add-to-cart-btn  ']")));
							WEL.clickOnAddToCartButtonOnPDP();
							
								try {
									wait.until(ExpectedConditions
											.elementToBeClickable(By.xpath("//button[@id='cartCheckoutBtn']/span")));

								} catch (Exception e) {
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
												"Checkout button was not clickable in the cart page"
														+ " and caused timeout exception",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}

								}
								String subtotal = WEL.fetchOrderSubTotalInCartPage();
								if (subtotal.equals(price))
									Reporting.updateTestReport("The Subtotal is FREE for DeanDorton Products",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

								else
									Reporting.updateTestReport(
											"The addition of all the products' pricedidn't match with the subtotal in cart page",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Failed to click on Add To Cart button due to timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
							
						} catch (Exception e) {
							Reporting.updateTestReport(
									"Add To Cart button was not visible and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
							
				} catch (Exception e) {
					Reporting.updateTestReport("Failed to click on DeanDorton CMA Product caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			} catch (Exception e) {
				Reporting.updateTestReport("Failed to Enter the University Name caused timeout exception",
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
	 * @Date: 18/4/23
	 * 
	 * @Description: Add University partner Product
	 */

	@Test
	public void TC18_AddUniversiry_PartnerProduct() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC18_AddUniversiry_PartnerProduct");
			LogTextFile.writeTestCaseStatus("TC18_AddUniversiry_PartnerProduct", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(excelOperation.getTestData("University_PartnetProdut_URL", "Generic_Dataset", "Data"));
			driver.navigate().refresh();
			try {
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class='form-group']//input[@id='inputPartnerSearch']")));
				WEL.enteruniversityName(excelOperation.getTestData("TC18", "WEL_Test_Data", "Partner_Name"));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//div[@class='partner-detail partner-list-container']//div[@class='col-xs-12 col-sm-6 package-selection-col']//button")));
					BigDecimal price = new BigDecimal(WEL.fetchPartnerProductPriceInPDP().substring(1));
					try {
						wait.until(ExpectedConditions.elementToBeClickable(
								By.xpath("//button[@type='submit' and @class='add-to-cart-btn  ']")));

						WEL.clickOnAddToCartButtonOnPDP();
						try {
							wait.until(ExpectedConditions
									.elementToBeClickable(By.xpath("//button[@id='cartCheckoutBtn']/span")));

						} catch (Exception e) {
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
										"Checkout button was not clickable in the cart page"
												+ " and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

						}
						
						BigDecimal subtotal = new BigDecimal(WEL.fetchOrderSubTotalInCartPage().substring(1));
						if (price.compareTo(subtotal) == 0)
							Reporting.updateTestReport(
									"The addition of all the products' price is same as the subtotal in cart page",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						else
							Reporting.updateTestReport(
									"The addition of all the products' pricedidn't match with the subtotal in cart page",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					} catch (Exception e) {
						Reporting.updateTestReport("Failed to click on Add To Cart button due to timeout exception",
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
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
											+ " and contains(text(),'View Course')])[1]")));
							ScrollingWebPage.PageDown(driver, SS_path);
							WEL.clickOnCIAViewCourseButton();
							try {
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.xpath("//button[@class='add-to-cart-btn  ']")));
								Thread.sleep(1000);
								// ScrollingWebPage.PageDown(driver, SS_path);

								WEL.clickOnPart1InCIAPDP();
								BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
								Thread.sleep(1000);
								ScrollingWebPage.PageDown(driver, SS_path);

								WEL.clickOnAddToCartButtonOnPDP();
								try {
									wait.until(ExpectedConditions
											.elementToBeClickable(By.xpath("//button[@id='cartCheckoutBtn']/span")));

								} catch (Exception e) {
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

						} catch (Exception e) {
							Reporting.updateTestReport(
									"Failed to click on CIA view course button due to timeout exception",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Failed to click on shop course button due to timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Failed to click on CIA link on Home Page due to timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
	 * @Date: 18/4/23
	 * 
	 * @Description: Shipping and Billing Address for India Address
	 */

	@Test
	public void TC20_Shipping_Billing_ForIndiaAddress() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC20_Shipping_Billing_ForIndiaAddress");
			LogTextFile.writeTestCaseStatus("TC20_Shipping_Billing_ForIndiaAddress", "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
														try {
															wait.until(ExpectedConditions.visibilityOfElementLocated(
																	By.xpath("//div[@class='helpButton']")));
															WEL.shipAddressLineOne(excelOperation.getTestData("TC20",
																	"WEL_Test_Data", "Shipping_Address_line1"));
															WEL.shipTownCity(excelOperation.getTestData("TC20",
																	"WEL_Test_Data", "Shipping_City/ Province"));
															WEL.enterState(excelOperation.getTestData("TC20",
																	"WEL_Test_Data", "Shipping_State"));
															WEL.shipPostCode(excelOperation.getTestData("TC20",
																	"WEL_Test_Data", "Shipping_Zip_Code"));
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
															"Help button was not visible and caused timeout exception",
															CaptureScreenshot.getScreenshot(SS_path),
															StatusDetails.FAIL);
												}
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {

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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
														try {
															wait.until(ExpectedConditions.visibilityOfElementLocated(
																	By.xpath("//div[@class='helpButton']")));
															WEL.shipAddressLineOne(excelOperation.getTestData("TC21",
																	"WEL_Test_Data", "Shipping_Address_line1"));
															WEL.shipTownCity(excelOperation.getTestData("TC21",
																	"WEL_Test_Data", "Shipping_City/ Province"));
															WEL.enterState(excelOperation.getTestData("TC21",
																	"WEL_Test_Data", "Shipping_State"));
															WEL.shipPostCode(excelOperation.getTestData("TC21",
																	"WEL_Test_Data", "Shipping_Zip_Code"));
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {

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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
														try {
															wait.until(ExpectedConditions.visibilityOfElementLocated(
																	By.xpath("//div[@class='helpButton']")));
															WEL.shipAddressLineOne(excelOperation.getTestData("TC22",
																	"WEL_Test_Data", "Shipping_Address_line1"));
															WEL.shipTownCity(excelOperation.getTestData("TC22",
																	"WEL_Test_Data", "Shipping_City/ Province"));
															WEL.enterState(excelOperation.getTestData("TC22",
																	"WEL_Test_Data", "Shipping_State"));
															WEL.shipPostCode(excelOperation.getTestData("TC22",
																	"WEL_Test_Data", "Shipping_Zip_Code"));
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//div[@class='fe_flex grid_1']/a[1]")));
					WEL.clickOnCPALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//button[@class='shop-courses-btn  ']")));
						WEL.clickOnExploreCourseButton();
						ScrollingWebPage.PageScrolldown(driver, 0, 900, SS_path);
						try {

							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
									"//a[@href='/cpa/products/platinum-cpa-review-course/']/button[contains(text(),'VIEW COURSE')]")));
							WEL.clickOnCPAViewCourseButton();
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
										"//div[@class='btn-group btn-group-toggle']/label[@class='btn btn-secondary print-ebook active']")));
								WEL.clickonCPAPrinteBook();
								driver.navigate().refresh();
								ScrollingWebPage.PageDown(driver, SS_path);
								BigDecimal firstproductprice = new BigDecimal(
										WEL.fetchProductPriceInPDP().substring(1));
								try {
									wait.until(ExpectedConditions.elementToBeClickable(
											By.xpath("//button[@type='submit' and @class='add-to-cart-btn  ']")));
									WEL.clickOnAddToCartButtonOnPDP();
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
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
												driver.navigate().refresh();
											}
										} catch (Exception e1) {
											Reporting.updateTestReport(
													"User was not in the cart page" + " and caused timeout exception",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									}
									driver.get(WEL_Homepage_URL);
									try {
										wait.until(ExpectedConditions
												.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
										ScrollingWebPage.PageDown(driver, SS_path);
										try {
											wait.until(ExpectedConditions.elementToBeClickable(
													By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
											WEL.clickOnCMALinkOnHomepage();
											try {
												wait.until(ExpectedConditions.elementToBeClickable(
														By.xpath("//button[@class='shop-courses-btn  ']")));
												driver.navigate().refresh();
												WEL.clickOnExploreCourseButton();
												try {
													wait.until(ExpectedConditions.visibilityOfElementLocated(
															By.xpath("(//div[@class='price-block'])[1]")));
													ScrollingWebPage.PageDown(driver, SS_path);
													try {
														wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
																"//a[@href='/cma/products/platinum-cma-review-course/']"
																		+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
														WEL.clickOnCMAViewCourseButton();
														try {
															wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
																	"//div[@class='btn-group btn-group-toggle']/label[1]")));
															WEL.clickonCMAPrinteBook();
															driver.navigate().refresh();
															ScrollingWebPage.PageDown(driver, SS_path);
															BigDecimal secondproductprice = new BigDecimal(
																	WEL.fetchProductPriceInPDP().substring(1));
															try {
																wait.until(ExpectedConditions.elementToBeClickable(By
																		.xpath("//button[@type='submit' and @class='add-to-cart-btn  ']")));
																WEL.clickOnAddToCartButtonOnPDP();
																try {
																	wait.until(ExpectedConditions
																			.visibilityOfElementLocated(
																					By.id("cartPageMainTitle")));
																} catch (Exception e) {
																	try {
																		if (driver.findElement(By.xpath(
																				"//h1[contains(text(),'SERVER ERROR (500)')]"))
																				.isDisplayed()) {
																			Reporting.updateTestReport(
																					"Server error came in cart page and the page was refreshed",
																					CaptureScreenshot
																							.getScreenshot(SS_path),
																					StatusDetails.INFO);
																			driver.navigate().refresh();
																		}
																	} catch (Exception e1) {
																		Reporting.updateTestReport(
																				"User was not in the cart page"
																						+ " and caused timeout exception",
																				CaptureScreenshot.getScreenshot(
																						SS_path),
																				StatusDetails.FAIL);
																	}
																}
																driver.get(WEL_Homepage_URL);
																try {
																	wait.until(ExpectedConditions.elementToBeClickable(
																			By.xpath("//a[@aria-label='login']")));
																	ScrollingWebPage.PageDown(driver, SS_path);
																} catch (Exception e) {

																}
																try {
																	wait.until(ExpectedConditions
																			.visibilityOfElementLocated(By.xpath(
																					"//div[@class='fe-product_content']/div[2]/div[@class='fe_flex grid_2']/a[1]")));
																	WEL.clickonCFAlinkOnHomePage();
																	try {
																		wait.until(ExpectedConditions
																				.visibilityOfElementLocated(By.xpath(
																						"//button[@class='shop-courses-btn  ']")));
																		WEL.clickOnExploreCourseButton();
																	} catch (Exception e) {

																	}
																	ScrollingWebPage.PageScrolldown(driver, 0, 1250,
																			SS_path);
																	try {
																		wait.until(ExpectedConditions
																				.elementToBeClickable(By.xpath(
																						"//a[@href='/cfa/products/']"
																								+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
																		WEL.clickOnCFAViewCourseButton();
																		try {
																			wait.until(ExpectedConditions
																					.visibilityOfElementLocated(By
																							.xpath("//div[@class='col compare-wiley-heading']")));
																			ScrollingWebPage.PageDown(driver, SS_path);
																			try {
																				wait.until(ExpectedConditions
																						.elementToBeClickable(By.xpath(
																								"//a[@href='/cfa/products/level-1/platinum-cfa-course/' and contains(text(),'View Course')]")));
																				WEL.clickOnCFAViewCourseLink();
																				driver.navigate().refresh();
																				ScrollingWebPage.PageDown(driver,
																						SS_path);
																				BigDecimal thirdproductprice = new BigDecimal(
																						WEL.fetchProductPriceInPDP()
																								.substring(1));
																				try {
																					wait.until(ExpectedConditions
																							.elementToBeClickable(By
																									.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
																					WEL.clickOnAddToCartButton();
																					try {
																						wait.until(ExpectedConditions
																								.visibilityOfElementLocated(
																										By.id("cartPageMainTitle")));
																					} catch (Exception e) {
																						try {
																							if (driver.findElement(By
																									.xpath("//h1[contains(text(),'SERVER ERROR (500)')]"))
																									.isDisplayed()) {
																								Reporting
																										.updateTestReport(
																												"Server error came in cart page and the page was refreshed",
																												CaptureScreenshot
																														.getScreenshot(
																																SS_path),
																												StatusDetails.INFO);
																								driver.navigate()
																										.refresh();
																							}
																						} catch (Exception e1) {
																							Reporting.updateTestReport(
																									"User was not in the cart page"
																											+ " and caused timeout exception",
																									CaptureScreenshot
																											.getScreenshot(
																													SS_path),
																									StatusDetails.FAIL);
																						}
																					}
																					BigDecimal subtotal = new BigDecimal(
																							WEL.fetchOrderSubTotalInCartPage()
																									.substring(1));
																					if (firstproductprice
																							.add(secondproductprice)
																							.add(thirdproductprice)
																							.compareTo(subtotal) == 0)
																						Reporting.updateTestReport(
																								"The addition of all the products' price is same as the subtotal in cart page",
																								CaptureScreenshot
																										.getScreenshot(
																												SS_path),
																								StatusDetails.PASS);
																					else
																						Reporting.updateTestReport(
																								"The addition of the price is not match with the subtotal in cart page due to multiple coupons was aplied in Cart Page",
																								CaptureScreenshot
																										.getScreenshot(
																												SS_path),
																								StatusDetails.INFO);
																					try {
																						wait.until(ExpectedConditions
																								.elementToBeClickable(By
																										.id("cartCheckoutBtn")));
																						WEL.clickonCheckOutButtonOnCartPage();
																						try {
																							wait.until(
																									ExpectedConditions
																											.visibilityOfElementLocated(
																													By.id("checkoutLogRegPageTitle")));
																							String GuestEmail = WEL
																									.enterGuestuser();
																							WEL.clickingOnCreateAccoutButton();
																							WEL.guestConfirmEmailId(
																									GuestEmail);
																							WEL.enterPassword(
																									excelOperation
																											.getTestData(
																													"TC24",
																													"WEL_Test_Data",
																													"Password"));
																							WEL.clickonAgreementCheckBox();
																							WEL.clickingOnSaveAndContinue();

																							try {
																								wait.until(
																										ExpectedConditions
																												.visibilityOfElementLocated(
																														By.xpath(
																																"//h5[@id='shippingAddressTitle']/span")));
																								WEL.firstName(
																										excelOperation
																												.getTestData(
																														"TC24",
																														"WEL_Test_Data",
																														"First_Name"));
																								WEL.lastName(
																										excelOperation
																												.getTestData(
																														"TC24",
																														"WEL_Test_Data",
																														"Last_Name"));
																								WEL.selectShipCountry(
																										excelOperation
																												.getTestData(
																														"TC24",
																														"WEL_Test_Data",
																														"Shipping_Country"));
																								WEL.shipAddressLineOne(
																										excelOperation
																												.getTestData(
																														"TC24",
																														"WEL_Test_Data",
																														"Shipping_Address_line1"));
																								WEL.shipTownCity(
																										excelOperation
																												.getTestData(
																														"TC24",
																														"WEL_Test_Data",
																														"Shipping_City/ Province"));
																								WEL.enterState(
																										excelOperation
																												.getTestData(
																														"TC11",
																														"WEL_Test_Data",
																														"Shipping_State"));
																								WEL.shipPostCode(
																										excelOperation
																												.getTestData(
																														"TC24",
																														"WEL_Test_Data",
																														"Shipping_Zip_Code"));
																								WEL.shipPhonenumber(
																										excelOperation
																												.getTestData(
																														"TC24",
																														"WEL_Test_Data",
																														"Shipping_Phone_Number"));
																								WEL.shipSaveAndContinueButton();
																								try {
																									if (WEL.returnUseSelectedShippingAddressButtonAddressDoctorPopUp()
																											.isDisplayed())
																										WEL.clickOnUseSelectedShippingAddressButtonAddressDoctor();
																								} catch (Exception e) {
																									Reporting
																											.updateTestReport(
																													"Failed to click Address on Address SUggestion due to timeout exception",
																													CaptureScreenshot
																															.getScreenshot(
																																	SS_path),
																													StatusDetails.INFO);
																								}

																								Reporting
																										.updateTestReport(
																												"User was in shipping step after successful registration",
																												CaptureScreenshot
																														.getScreenshot(
																																SS_path),
																												StatusDetails.PASS);
																							} catch (Exception e) {
																								Reporting
																										.updateTestReport(
																												"User was not in shipping step and caused timeout exception",
																												CaptureScreenshot
																														.getScreenshot(
																																SS_path),
																												StatusDetails.FAIL);
																							}
																							try {
																								wait.until(
																										ExpectedConditions
																												.visibilityOfElementLocated(
																														By.xpath(
																																"//div[@id='orderSummaryProductTotalValue']")));

																								BigDecimal firstproductprice2 = new BigDecimal(
																										WEL.fetchFirstProductPriceInOrderReview()
																												.substring(
																														1));

																								String discount = WEL
																										.fetchDiscountInOrderReview();
																								if (discount
																										.contains(","))
																									discount = discount
																											.replace(
																													",",
																													"");
																								BigDecimal discountinorderreview = new BigDecimal(
																										discount.substring(
																												1));
																								String totalorderReview = WEL
																										.fetchTotalInOrderReview();
																								if (totalorderReview
																										.contains(","))
																									totalorderReview = totalorderReview
																											.replace(
																													",",
																													"");
																								BigDecimal orderTotalPrice1 = new BigDecimal(
																										totalorderReview
																												.substring(
																														1));
																								if (firstproductprice2
																										.subtract(
																												discountinorderreview)
																										.compareTo(
																												orderTotalPrice1) == 0)
																									Reporting
																											.updateTestReport(
																													"First Product price + Second Product price -discount "
																															+ " = Order total in Order Review step",
																													CaptureScreenshot
																															.getScreenshot(
																																	SS_path),
																													StatusDetails.PASS);
																								else
																									Reporting
																											.updateTestReport(
																													"First Product price +second product price- discount is not equal to Order total in Order Review step",
																													CaptureScreenshot
																															.getScreenshot(
																																	SS_path),
																													StatusDetails.FAIL);

																							} catch (Exception e) {
																								Reporting
																										.updateTestReport(
																												"Order summary tab was not visible"
																														+ e.getMessage(),
																												CaptureScreenshot
																														.getScreenshot(
																																SS_path),
																												StatusDetails.FAIL);
																							}

																						} catch (Exception e) {
																							Reporting.updateTestReport(
																									"User was not redirected to checkout login/ registration page and caused timeout exception",
																									CaptureScreenshot
																											.getScreenshot(
																													SS_path),
																									StatusDetails.FAIL);
																						}
																						// WEL.clickOnEnterNewAddressButtonOnShippingPage();
																						WEL.clickingOnSaveAndContinue();

																					} catch (Exception e) {
																						Reporting.updateTestReport(
																								"Checkout button was not clickable in cart page and caused timeout exception",
																								CaptureScreenshot
																										.getScreenshot(
																												SS_path),
																								StatusDetails.FAIL);
																					}
																				} catch (Exception e) {
																					Reporting.updateTestReport(
																							"Failed to click on Add To Cart button caused timeout exception ",
																							CaptureScreenshot
																									.getScreenshot(
																											SS_path),
																							StatusDetails.FAIL);
																				}

																			} catch (Exception e) {
																				Reporting.updateTestReport(
																						"Failed to click on CFAView Coouse link button caused timeout exception ",
																						CaptureScreenshot
																								.getScreenshot(SS_path),
																						StatusDetails.FAIL);
																			}
																		} catch (Exception e) {
																			Reporting.updateTestReport(
																					"Failed to click on CFAView Coouse button caused timeout exception ",
																					CaptureScreenshot
																							.getScreenshot(SS_path),
																					StatusDetails.FAIL);
																		}
																	} catch (Exception e) {
																		Reporting.updateTestReport(
																				"Failed to click on CFA explore Coouse button caused timeout exception ",
																				CaptureScreenshot.getScreenshot(
																						SS_path),
																				StatusDetails.FAIL);
																	}

																} catch (Exception e) {
																	Reporting.updateTestReport(
																			"Failed to click on CFA link from Home Page caused timeout exception ",
																			CaptureScreenshot.getScreenshot(SS_path),
																			StatusDetails.FAIL);
																}
															} catch (Exception e) {
																Reporting.updateTestReport(
																		"Failed to click on Add To Cart button caused timeout exception ",
																		CaptureScreenshot.getScreenshot(SS_path),
																		StatusDetails.FAIL);
															}

														} catch (Exception e) {
															Reporting.updateTestReport(
																	"Failed to click on CMAPrinteBook caused timeout exception ",
																	CaptureScreenshot.getScreenshot(SS_path),
																	StatusDetails.FAIL);
														}
													} catch (Exception e) {
														Reporting.updateTestReport(
																"Failed to click on Add To Cart button caused timeout exception ",
																CaptureScreenshot.getScreenshot(SS_path),
																StatusDetails.FAIL);
													}
												} catch (Exception e) {
													Reporting.updateTestReport(
															"Failed to click on CMAView Coouse button caused timeout exception ",
															CaptureScreenshot.getScreenshot(SS_path),
															StatusDetails.FAIL);
												}
											} catch (Exception e) {
												Reporting.updateTestReport(
														"Failed to click on Explore course button caused timeout exception ",
														CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										} catch (Exception e) {
											Reporting.updateTestReport(
													"Failed to click on CMALinkHomePage caused timeout exception ",
													CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									} catch (Exception e) {
										Reporting.updateTestReport(
												"Failed to click on Add To Cart button caused timeout exception ",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								} catch (Exception e) {
									Reporting.updateTestReport(
											"Failed to click on Add To Cart button caused timeout exception ",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Failed to click on CPA Physital product caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"Failed to click on CPA View course button caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport("Failed to click on explore course button caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("Failed to click on CPA link on home page caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCMA' and @data-key='1']")));
					WEL.clickOnCMALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//button[@class='shop-courses-btn  ']")));
						driver.navigate().refresh();
						WEL.clickOnExploreCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("(//div[@class='price-block'])[1]")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions.elementToBeClickable(
										By.xpath("//a[@href='/cma/products/platinum-cma-review-course/']"
												+ "/button[contains(text(),'VIEW COURSE OPTIONS')]")));
								WEL.clickOnCMAViewCourseButton();
								try {
									wait.until(ExpectedConditions.elementToBeClickable(
											By.xpath("//div[@class='btn-group btn-group-toggle']/label[1]")));
									WEL.clickonCMAPrinteBook();
									driver.navigate().refresh();
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(
												By.xpath("//button[@type='submit' and @class='add-to-cart-btn  ']")));
										WEL.clickOnAddToCartButtonOnPDP();
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CMA PrinteBook was selected and caused timeout exception ",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"Failed to click on CMA View Course and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"Failed to click on Explore Course and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"After clicking on explore course button, the page didn't show the product title and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CMA Icon was not clickable  and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		} catch (Exception e) {
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		}
	}/*
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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
													BigDecimal courierChargeForOneUnit = WEL
															.fetchShippingChargeNonUS(driver, "Courier");

													// Validation for Ameriocan samoa
													WEL.selectShipCountry(country2);
													BigDecimal twoDayChargeForOneUnit = WEL
															.fetchShippingChargeNonUS(driver, "Standard Shipping");
													ScrollingWebPage.PageScrollDownUptoTop(driver, SS_path);
													try {
														wait.until(ExpectedConditions
																.elementToBeClickable(By.id("backTocartNavbarMainId")));
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
																				"Courier charge has been wrongly calculated as: "+(courierChargeForOneUnit.add((new BigDecimal(
																						quantity).subtract(new BigDecimal("1")))
																						.multiply(new BigDecimal("10"))))
																								.setScale(2,RoundingMode.CEILING),CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
																	WEL.selectShipCountry(country2);
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
				Reporting.updateTestReport("Login button on homepage was not clickable and caused timeout exception ",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			WEL.logOutWEL(driver, excelOperation.getTestData("WEL_Logout_URL", "Generic_Dataset", "Data"));
		} catch (Exception e) {

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
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									BigDecimal price = new BigDecimal(WEL.fetchProductPriceInPDP().substring(1));
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
																"//div[@class='step-head checkoutCompletedStep']//div[@class='edit']/a")));
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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
			String[] emailIds = excelOperation.getTestData("TC28", "WEL_Test_Data", "Email_Address").split(",");
			driver.get(WEL_Homepage_URL);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='login']")));
				ScrollingWebPage.PageDown(driver, SS_path);
				try {
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//a[@data-for='productTooltipCIA' and @data-key='2']")));
					WEL.clickOnCIALinkOnHomepage();
					try {
						wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath("//div[@class='col-xs-12 col-sm-6 col-md-5 col-lg-5 "
										+ "col-xl-4 shop-courses-btn-container']/a/button")));
						driver.navigate().refresh();
						WEL.clickOnShopCourseButton();
						try {
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//div[@class='col compare-wiley-heading']")));
							ScrollingWebPage.PageDown(driver, SS_path);
							try {
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("(//a[@href='/cia/products/cia-review-course/'"
												+ " and contains(text(),'View Course')])[1]")));
								WEL.clickOnCIAViewCourseButton();
								try {
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath("//label[@for='Print0']")));
									ScrollingWebPage.PageDown(driver, SS_path);
									try {
										wait.until(ExpectedConditions.elementToBeClickable(By
												.xpath("//button[@type='submit' and contains(text(),'ADD TO CART')]")));
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
								} catch (Exception e) {
									Reporting.updateTestReport(
											"CIA Product details page didn't appear and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							} catch (Exception e) {
								Reporting.updateTestReport(
										"View Course button on product page was not clickable and caused timeout exception ",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						} catch (Exception e) {
							Reporting.updateTestReport(
									"After clicking on explore course button, the page didn't show the prices and caused timeout exception ",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					} catch (Exception e) {
						Reporting.updateTestReport(
								"Shop course button on product page was not clickable and caused timeout exception ",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				} catch (Exception e) {
					Reporting.updateTestReport("CIA Link on homepage was not clickable and caused timeout exception ",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			} catch (Exception e) {
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

}
