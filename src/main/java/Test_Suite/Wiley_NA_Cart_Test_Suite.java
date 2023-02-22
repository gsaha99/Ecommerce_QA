package Test_Suite;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import PageObjectRepo.app_Wiley_Repo;
import utilities.CaptureScreenshot;
import utilities.OrderConfirmationMail;
import utilities.DriverModule;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.Duration;

import java.util.Date;

import java.util.Set;

public class Wiley_NA_Cart_Test_Suite extends DriverModule {
	app_Wiley_Repo wiley;

	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);
	public static String EmailConfirmationText="//button/div[contains(text(),'Your Order with Wiley')]";

	@BeforeTest
	public void launchBrowser() {
		wiley = PageFactory.initElements(driver, app_Wiley_Repo.class);

	}



	/*
	 * @Author: Anindita
	 * @Description: This test case is about placing an order with Digital product with new user
	 * @Date: 06/09/22
	 */
	@Test
	public void TC01_Digital_Product_Purchase_New_User() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC01_Digital_Product_Purchase_New_User");

			driver.get(wiley.wileyURLConcatenation("TC01", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal price=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				BigDecimal subtotal=new BigDecimal(wiley.fetchOrderSubTotalInCartPage().substring(1));
				if(price.compareTo(subtotal)==0) 
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);


				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(email);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();			
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					wiley.enterCardHolderName(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "First_Name"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
					wiley.enterCardNumber(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Card_Number"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
					wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
					wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
					wiley.enterCVV_Number(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "CVV"));
					driver.switchTo().defaultContent();			
					wiley.enterFirstName(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "First_Name"));
					wiley.enterLastName(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Last_Name"));
					wiley.selectCountry(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_Country"));
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
						wiley.enterAddressLine1Billing(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_Address_line1"));
						wiley.enterZipBilling(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_Zip_Code"));
						//wiley.enterCityBilling(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_City"));
						//wiley.enterState(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_State"));
						//wiley.selectUState();
						wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_Phone_Number"));
						wiley.clickOnSaveAndContinueButton();
						try {
							if(wiley.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed()) 
							{wiley.clickOnUseSelectedBillingAddressButtonAddressDoctor();
							}}
						catch(Exception e) {
							Reporting.updateTestReport("Adress doctor pop up did not appear",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
						}
						wiley.clickOnPlaceOrderButton();
						if(wiley.checkIfUserIsInOrderConfirmation()) Reporting.updateTestReport("User is in Order Confirmation page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						else Reporting.updateTestReport("User was not in Order Confirmation page",CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
						String orderID=wiley.fetchOrderId();
						excelOperation.updateTestData("TC01", "WILEY_NA_Cart_Test_Data", "Order_Id", orderID);
						excelOperation.updateTestData("TC01", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
						excelOperation.updateTestData("TC02", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
						ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
						String tax=wiley.fetchTaxAmount();
						String orderTotal=wiley.fetchOrderTotal();

						excelOperation.updateTestData("TC01", "WILEY_NA_Cart_Test_Data", "Tax", tax);
						excelOperation.updateTestData("TC01", "WILEY_NA_Cart_Test_Data", "Order_Total", orderTotal);
						driver.get(excelOperation.getTestData("Yopmail_URL",
								"Generic_Dataset", "Data"));
						wiley.enterEmailIdInYopmail(email);
						wiley.clickOnCheckInboxButton();
						if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
							Reporting.updateTestReport("Order Confirmation mail was received",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
							OrderConfirmationMail.validateOrderConfirmationMailContent("Wiley",driver,SS_path,tax," ",orderTotal);
						}
						else {
							Reporting.updateTestReport("Order Confirmation mail was not received",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}


					}
					catch(Exception e) {
						Reporting.updateTestReport("Billing address line 1 was not clickable"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Cardholder name ield in Card information"
							+ " section was not clickable and caused timeout exception"
							, CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();

		}
		catch(Exception e){
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}


	/*
	 * @Author: Vishnu 
	 * @Description: This is verify to placing an order for digital product for the
	 * existing user
	 */
	@Test
	public void TC02_Digital_productpurchase_ForExistingUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC02_Digital_productpurchase_ForExistingUser");
			driver.get(wiley.wileyURLConcatenation("TC02", "WILEY_NA_Cart_Test_Data", "URL"));

			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();	
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "Email_Id");
				wiley.enterExistingWileyUserMailID(email);
				wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnLogInAndContinueButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				driver.switchTo().frame(0);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					wiley.enterCardHolderName(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "First_Name"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.className("gw-proxy-number")));		
					wiley.enterCardNumber(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "Card_Number"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
					wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
					wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.className("gw-proxy-securityCode")));
					wiley.enterCVV_Number(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "CVV"));
					driver.switchTo().defaultContent();
					wiley.clickOnEnterNewAddress();
					wiley.enterFirstName(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "First_Name"));
					wiley.enterLastName(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "Last_Name"));
					wiley.selectCountry(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "Bill_Country"));
					Thread.sleep(3000);
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
						wiley.enterAddressLine1Billing(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "Bill_Address_line1"));
						wiley.enterZipBilling(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "Bill_Zip_Code"));
						//wiley.enterCityBilling(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "Bill_City"));
						//wiley.enterState(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "Bill_State"));
						//						wiley.SelectState(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data","Bill_State"));
						wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC02", "WILEY_NA_Cart_Test_Data", "Bill_Phone_Number"));
						try {
							if(wiley.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed()) 
								wiley.clickOnUseSelectedBillingAddressButtonAddressDoctor();
						}
						catch(Exception e) {
							Reporting.updateTestReport("Adress doctor pop up did not appear",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
						}
						wiley.clickOnSaveAndContinueButton();
						String title = driver.getTitle();
						if (title.equalsIgnoreCase("Secure Checkout | Wiley")) {
							Reporting.updateTestReport("user was on Secure Checkout Page",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
							wiley.clickOnPlaceOrderButton();
							String orderconfirmation = driver.getTitle();
							if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
								ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
								String orderId = wiley.fetchOrderId();
								excelOperation.updateTestData("TC02", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
								excelOperation.updateTestData("TC02", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
								ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
								String ordertotal = wiley.fetchOrderTotal();
								String taxamount = wiley.fetchTaxAmount();
								System.out.println("The Order Id is is " + orderId);
								excelOperation.updateTestData("TC02", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
								excelOperation.updateTestData("TC02", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);

							}						
							else {
								Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}

						} else {
							Reporting.updateTestReport("user was not on Secure Checkout Page",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}          

					}
					catch(Exception e) {
						Reporting.updateTestReport("Billing address line 1 was not clickable"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Cardholder name ield in Card information"
							+ " section was not clickable and caused timeout exception"
							, CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch (Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);

		}
	}

	@Test
	public void TC03_Physical_productpurchase_ForNewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC03_Physical_productpurchase_ForNewUser");

			driver.get(wiley.wileyURLConcatenation("TC03", "WILEY_NA_Cart_Test_Data", "URL"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String emailID = wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(emailID);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC03", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.enterFirstName(excelOperation.getTestData("TC03", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC03", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC03", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC03", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC03", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC03", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC03", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC03", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));

					wiley.selectShippingMethod();
					wiley.clickOnSaveAndContinueButton();
					try {
						if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
							wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					driver.switchTo().frame(0);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						wiley.enterCardHolderName(excelOperation.getTestData("TC03", "WILEY_NA_Cart_Test_Data", "First_Name"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));			
						wiley.enterCardNumber(excelOperation.getTestData("TC03", "WILEY_NA_Cart_Test_Data", "Card_Number"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='expiryMonth']")));
						wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
						wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='securityCode']")));
						wiley.enterCVV_Number(excelOperation.getTestData("TC03", "WILEY_NA_Cart_Test_Data", "CVV"));
						driver.switchTo().defaultContent();
						wiley.clickOnSaveAndContinueButton();
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC03", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC03", "WILEY_NA_Cart_Test_Data", "Email_Id", emailID);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							String scharge=wiley.fetchShippingCharge();
							excelOperation.updateTestData("TC03", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC03", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
							excelOperation.updateTestData("TC03", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);
							driver.get(excelOperation.getTestData("Yopmail_URL",
									"Generic_Dataset", "Data"));
							wiley.enterEmailIdInYopmail(emailID);
							wiley.clickOnCheckInboxButton();
							if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
								Reporting.updateTestReport("Order Confirmation mail was received",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								OrderConfirmationMail.validateOrderConfirmationMailContent("Wiley",driver,SS_path,taxamount,scharge,ordertotal);
							}
							else {
								Reporting.updateTestReport("Order Confirmation mail was not received",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}				
						}			

						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Cardholder name ield in Card information"
								+ " section was not clickable and caused timeout exception"
								, CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Shipping Address line 1 was not clickable"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}

		catch (Exception e) {
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);

		}
	}

	/*
	 * @Author: Anindita
	 * @Description: This test case is about placing an order with Physical product with guest user
	 * @Date: 07/09/22
	 */
	@Test
	public void TC04_Physical_Product_Purchase_Guest_User() throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			Reporting.test = Reporting.extent.createTest("TC04_Physical_Product_Purchase_Guest_User");
			driver.get(wiley.wileyURLConcatenation("TC04", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnContinueAsGuestButton();
				wiley.enterFirstName(excelOperation.getTestData("TC04", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC04", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC04", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC04", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC04", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC04", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC04", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC04", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));
					wiley.clickOnSaveAndContinueButton();
					try {
						if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
							wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						wiley.enterCardHolderName(excelOperation.getTestData("TC04", "WILEY_NA_Cart_Test_Data", "First_Name"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
						wiley.enterCardNumber(excelOperation.getTestData("TC04", "WILEY_NA_Cart_Test_Data", "Card_Number"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
						wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC04", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
						wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC04", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
						wiley.enterCVV_Number(excelOperation.getTestData("TC04", "WILEY_NA_Cart_Test_Data", "CVV"));
						driver.switchTo().defaultContent();
						wiley.clickOnSaveAndContinueButton();
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC04", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC04", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							String scharge=wiley.fetchShippingCharge();
							excelOperation.updateTestData("TC04", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC04", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
							excelOperation.updateTestData("TC04", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);
							driver.get(excelOperation.getTestData("Yopmail_URL",
									"Generic_Dataset", "Data"));
							wiley.enterEmailIdInYopmail(email);
							wiley.clickOnCheckInboxButton();
							if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
								Reporting.updateTestReport("Order Confirmation mail was received",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								OrderConfirmationMail.validateOrderConfirmationMailContent("Wiley",driver,SS_path,taxamount,scharge,ordertotal);
							}
							else {
								Reporting.updateTestReport("Order Confirmation mail was not received",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}

						}				
						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Cardholder name ield in Card information"
								+ " section was not clickable and caused timeout exception"
								, CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}			
				}
				catch(Exception e) {
					Reporting.updateTestReport("Shipping Address line 1 was not clickable"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}

			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e){
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu 
	 * @Description: This is verify to placing an order for Physical product for the
	 * existing user
	 */
	@Test
	public void TC05_Physical_productpurchase_ForExistingUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC05_Physical_productpurchase_ForExistingUser");
			driver.get(wiley.wileyURLConcatenation("TC05", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				wiley.selectQuantityDropDown(excelOperation.getTestData("TC05", "WILEY_NA_Cart_Test_Data", "Quantity"));
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String emailID=excelOperation.getTestData("TC05", "WILEY_NA_Cart_Test_Data", "Email_Id");
				wiley.enterExistingWileyUserMailID(emailID);
				wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC05", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnLogInAndContinueButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				wiley.selectUSEOptionForExistingAddress();
				ScrollingWebPage.PageScrolldown(driver,0,600,SS_path);
				Thread.sleep(2000);
				wiley.clickOnSaveAndContinueButton();
				driver.switchTo().frame(driver.findElement(By.className("gw-proxy-nameOnCard"))); // Thread.sleep(2000);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					wiley.enterCardHolderName(excelOperation.getTestData("TC05", "WILEY_NA_Cart_Test_Data", "First_Name"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.className("gw-proxy-number")));
					wiley.enterCardNumber(excelOperation.getTestData("TC05", "WILEY_NA_Cart_Test_Data", "Card_Number"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
					wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC05", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
					wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC05", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.className("gw-proxy-securityCode")));
					wiley.enterCVV_Number(excelOperation.getTestData("TC05", "WILEY_NA_Cart_Test_Data", "CVV"));
					driver.switchTo().defaultContent();
					wiley.clickOnSaveAndContinueButton();
					wiley.clickOnPlaceOrderButton();
					String orderconfirmation = driver.getTitle();
					if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
						ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
						String orderId = wiley.fetchOrderId();
						excelOperation.updateTestData("TC05", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
						excelOperation.updateTestData("TC05", "WILEY_NA_Cart_Test_Data", "Email_Id", emailID);
						ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
						String ordertotal = wiley.fetchOrderTotal();
						String taxamount = wiley.fetchTaxAmount();
						String scharge=wiley.fetchShippingCharge();
						excelOperation.updateTestData("TC05", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
						excelOperation.updateTestData("TC05", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
						excelOperation.updateTestData("TC05", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);

					}				
					else {
						Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Cardholder name ield in Card information"
							+ " section was not clickable and caused timeout exception"
							, CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		} catch (Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}



	@Test
	public void TC06_Mixed_productpurchase_ForExistingUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC06_Mixed_productpurchase_ForExistingUser");
			driver.get(wiley.wileyURLConcatenation("TC06", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.searchDataInSearchBar(excelOperation.getTestData("TC06", "WILEY_NA_Cart_Test_Data", "ISBN"));
				wiley.clickOnSRP_WileyProduct();
				BigDecimal priceOfSecondProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
				wiley.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions.
							elementToBeClickable
							(By.xpath("//button[contains(text(),'View Cart')]")));
					wiley.clickOnViewCartButton();
					BigDecimal subtotal=new BigDecimal(wiley.fetchOrderSubTotalInCartPage().substring(1));
					if(priceOfFirstProduct.add(priceOfSecondProduct).compareTo(subtotal)==0) 
						Reporting.updateTestReport(
								"The addition of all the products' price is same as the subtotal in cart page",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport(
								"The addition of all the products' pricedidn't match with the subtotal in cart page",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
					wiley.clickOnProceedToCheckoutButton();
					String emailID=excelOperation.getTestData("TC06", "WILEY_NA_Cart_Test_Data", "Email_Id");
					wiley.enterExistingWileyUserMailID(emailID);
					wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC06", "WILEY_NA_Cart_Test_Data", "Password"));
					wiley.clickOnLogInAndContinueButton();
					wiley.selectQuantityDropDown(excelOperation.getTestData("TC06", "WILEY_NA_Cart_Test_Data", "Quantity"));
					ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
					wiley.clickOnProceedToCheckoutButton();
					wiley.selectUSEOptionForExistingAddress();
					Thread.sleep(1000);
					wiley.clickOnSaveAndContinueButton();
					driver.switchTo().frame(0);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						wiley.enterCardHolderName(excelOperation.getTestData("TC06", "WILEY_NA_Cart_Test_Data", "First_Name"));
						driver.switchTo().defaultContent();				
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));				
						wiley.enterCardNumber(excelOperation.getTestData("TC06", "WILEY_NA_Cart_Test_Data", "Card_Number"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='expiryMonth']")));
						wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
						wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='securityCode']")));
						wiley.enterCVV_Number(excelOperation.getTestData("TC06", "WILEY_NA_Cart_Test_Data", "CVV"));
						driver.switchTo().defaultContent();
						wiley.clickOnSaveAndContinueButton();
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC06", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC06", "WILEY_NA_Cart_Test_Data", "Email_Id", emailID);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							String scharge=wiley.fetchShippingCharge();
							excelOperation.updateTestData("TC06", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC06", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
							excelOperation.updateTestData("TC06", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);

						}			
						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Cardholder name ield in Card information"
								+ " section was not clickable and caused timeout exception"
								, CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}

		catch (Exception e) {
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);

		}
	}

	/*
	 * @Author: Anindita
	 * @Description: This test case is about placing an order with mixed cart with some stock available new user
	 * @Date: 08/09/22
	 */
	@Test
	public void TC07_Mixed_Product_Some_Stock_New_User() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC07_Mixed_Product_Some_Stock_New_User");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC07", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				wiley.searchDataInSearchBar(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "ISBN"));
				//Wiley.clickOnSearchIcon();
				wiley.clickOnSRP_WileyProduct();
				BigDecimal priceOfSecondProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
				wiley.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions.
							elementToBeClickable
							(By.xpath("//button[contains(text(),'View Cart')]")));
					wiley.clickOnViewCartButton();
					BigDecimal subtotal=new BigDecimal(wiley.fetchOrderSubTotalInCartPage().substring(1));
					if(priceOfFirstProduct.add(priceOfSecondProduct).compareTo(subtotal)==0) 
						Reporting.updateTestReport(
								"The addition of all the products' price is same as the subtotal in cart page",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport(
								"The addition of all the products' pricedidn't match with the subtotal in cart page",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
					wiley.clickOnProceedToCheckoutButton();
					//Create Account 
					String email=wiley.enterEmailIdInCreateAccountForm();
					wiley.clickOnCreateAccountButton();
					wiley.confirmEmailIdInCreateAccountForm(email);
					wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "Password"));
					wiley.clickOnSaveAndContinueButton();
					//Shipping section
					wiley.enterFirstName(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "First_Name"));
					wiley.enterLastName(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "Last_Name"));
					wiley.selectCountry(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
						wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
						wiley.enterShippingZIPCode(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
						wiley.enterShippingCity(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
						wiley.enterState(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
						wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));
						wiley.clickOnSaveAndContinueButton();
						try {
							if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
								wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
						catch(Exception e) {
							Reporting.updateTestReport("Adress doctor pop up did not appear",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
						}
						//Billing section
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
							wiley.enterCardHolderName(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "First_Name"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
							wiley.enterCardNumber(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "Card_Number"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
							wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
							wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
							wiley.enterCVV_Number(excelOperation.getTestData("TC07", "WILEY_NA_Cart_Test_Data", "CVV"));
							driver.switchTo().defaultContent();
							wiley.clickOnSaveAndContinueButton();
							wiley.clickOnPlaceOrderButton();
							String orderconfirmation = driver.getTitle();
							if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
								ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
								String orderId = wiley.fetchOrderId();
								excelOperation.updateTestData("TC07", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
								excelOperation.updateTestData("TC07", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
								ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
								String ordertotal = wiley.fetchOrderTotal();
								String taxamount = wiley.fetchTaxAmount();
								String scharge=wiley.fetchShippingCharge();
								excelOperation.updateTestData("TC07", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
								excelOperation.updateTestData("TC07", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
								excelOperation.updateTestData("TC07", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);
								driver.get(excelOperation.getTestData("Yopmail_URL",
										"Generic_Dataset", "Data"));
								wiley.enterEmailIdInYopmail(email);
								wiley.clickOnCheckInboxButton();
								if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
									Reporting.updateTestReport("Order Confirmation mail was received",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);

								}
								else {
									Reporting.updateTestReport("Order Confirmation mail was not received",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}

							}				
							else {
								Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}

						}
						catch(Exception e) {
							Reporting.updateTestReport("Cardholder name field in Card information"
									+ " section was not clickable and caused timeout exception"
									, CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Shipping Address line 1 was not clickable"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e){
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}


	}
	/*
	 * @Author: Anindita
	 * @Description: Placed an order with mixed product (stock available for none)
	 */
	@Test
	public void TC08_Mixed_Product_No_Stock_Guest_User() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC08_TC08_Mixed_Product_No_Stock_Guest_User");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC08", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				wiley.searchDataInSearchBar(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "ISBN"));
				//Wiley.clickOnSearchIcon();
				wiley.clickOnSRP_WileyProduct();
				wiley.clickOnPrintTab();
				BigDecimal priceOfSecondProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
				wiley.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions.
							elementToBeClickable
							(By.xpath("//button[contains(text(),'View Cart')]")));
					wiley.clickOnViewCartButton();
					BigDecimal subtotal=new BigDecimal(wiley.fetchOrderSubTotalInCartPage().substring(1));
					if(priceOfFirstProduct.add(priceOfSecondProduct).compareTo(subtotal)==0) 
						Reporting.updateTestReport(
								"The addition of all the products' price is same as the subtotal in cart page",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport(
								"The addition of all the products' pricedidn't match with the subtotal in cart page",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
					wiley.clickOnProceedToCheckoutButton();
					//Guest
					String email=wiley.enterEmailIdInCreateAccountForm();
					wiley.clickOnContinueAsGuestButton();
					wiley.enterFirstName(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "First_Name"));
					wiley.enterLastName(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "Last_Name"));
					wiley.selectCountry(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
						wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
						wiley.enterShippingZIPCode(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
						wiley.enterShippingCity(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
						wiley.enterState(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
						wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));
						wiley.clickOnSaveAndContinueButton();
						try {
							if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
								wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
						catch(Exception e) {
							Reporting.updateTestReport("Adress doctor pop up did not appear",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
						}
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
							wiley.enterCardHolderName(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "First_Name"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
							wiley.enterCardNumber(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "Card_Number"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
							wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
							wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
							wiley.enterCVV_Number(excelOperation.getTestData("TC08", "WILEY_NA_Cart_Test_Data", "CVV"));
							driver.switchTo().defaultContent();
							wiley.clickOnSaveAndContinueButton();
							wiley.clickOnPlaceOrderButton();
							String orderconfirmation = driver.getTitle();
							if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
								ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
								String orderId = wiley.fetchOrderId();
								excelOperation.updateTestData("TC08", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
								excelOperation.updateTestData("TC08", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
								ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
								String ordertotal = wiley.fetchOrderTotal();
								String taxamount = wiley.fetchTaxAmount();
								String scharge=wiley.fetchShippingCharge();
								excelOperation.updateTestData("TC08", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
								excelOperation.updateTestData("TC08", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
								excelOperation.updateTestData("TC08", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);
								driver.get(excelOperation.getTestData("Yopmail_URL",
										"Generic_Dataset", "Data"));
								wiley.enterEmailIdInYopmail(email);
								wiley.clickOnCheckInboxButton();
								if(OrderConfirmationMail.checkIfOrderConfirmationMailReceived(driver,SS_path,EmailConfirmationText)) {
									Reporting.updateTestReport("Order Confirmation mail was received",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
									//validateOrderConfirmationMailContent(taxamount,scharge,ordertotal);
								}
								else {
									Reporting.updateTestReport("Order Confirmation mail was not received",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}

							}
							else {
								Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}


						}
						catch(Exception e) {
							Reporting.updateTestReport("Cardholder name ield in Card information"
									+ " section was not clickable and caused timeout exception"
									, CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}	
					}
					catch(Exception e) {
						Reporting.updateTestReport("Shipping Address line 1 was not clickable"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}	
				}
				catch(Exception e) {
					Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();

		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}

	}

	@Test // Higher Education Digital product purchase (Arun)
	public void TC09_Higher_Education_Digitalproductpurchase_ForNewUser() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC09_Higher_Education_Digitalproductpurchase_ForNewUser");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC09", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String emailID = wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(emailID);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.selectSchoolInfo(excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "School_State"),excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "School_Name"));
				wiley.clickOnSaveAndContinueButton();
				driver.switchTo().frame(0);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					wiley.enterCardHolderName(excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "First_Name"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));

					wiley.enterCardNumber(excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "Card_Number"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='expiryMonth']")));
					wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
					wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='securityCode']")));
					wiley.enterCVV_Number(excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "CVV"));
					driver.switchTo().defaultContent();
					wiley.enterFirstName(excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "First_Name"));
					wiley.enterLastName(excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "Last_Name"));
					wiley.selectCountry(excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "Bill_Country"));
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
						wiley.enterAddressLine1Billing(excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "Bill_Address_line1"));
						wiley.enterZipBilling(excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "Bill_Zip_Code"));
						//wiley.enterCityBilling(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_City"));
						//wiley.enterState(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_State"));
						//wiley.selectUState();
						wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "Bill_Phone_Number"));
						wiley.clickOnSaveAndContinueButton();
						try {
							if(wiley.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed()) 
								wiley.clickOnUseSelectedBillingAddressButtonAddressDoctor();
						}
						catch(Exception e) {
							Reporting.updateTestReport("Adress doctor pop up did not appear",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
						}
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC09", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC09", "WILEY_NA_Cart_Test_Data", "Email_Id", emailID);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							excelOperation.updateTestData("TC09", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC09", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);

							/*driver.get(excelOperation.getTestData("Yopmail_URL",
							"Generic_Dataset", "Data"));

							wiley.enterEmailIdInYopmail(emailID);
							wiley.clickOnCheckInboxButton();
							if(checkIfOrderConfirmationMailReceived()) {
								Reporting.updateTestReport("Order Confirmation mail was received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								validateOrderConfirmationMailContent(taxamount,scharge,ordertotal);
							}
							else {
								Reporting.updateTestReport("Order Confirmation mail was not received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}*/

						}		

						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Billing address line 1 was not clickable"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Cardholder name ield in Card information"
							+ " section was not clickable and caused timeout exception"
							, CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();

		}

		catch (Exception e) {
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);

		}
	}
	/*
	 * @Author: Vishnu
	 * 
	 * @Description: This is verify to placing an order for digital product for the
	 * existing user
	 */
	@Test

	public void TC10_Shipping_And_BillingAddresses_Same() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC10_Shipping_And_BillingAddresses_Same");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC10", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(email);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.enterFirstName(excelOperation.getTestData("TC10", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC10", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC10", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC10", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC10", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC10", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC10", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC10", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));		
					wiley.selectShippingMethod();
					wiley.clickOnSaveAndContinueButton();
					try {
						if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
							wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					driver.switchTo().frame(0);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						wiley.enterCardHolderName(excelOperation.getTestData("TC10", "WILEY_NA_Cart_Test_Data", "First_Name"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));
						wiley.enterCardNumber(excelOperation.getTestData("TC10", "WILEY_NA_Cart_Test_Data", "Card_Number"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='expiryMonth']")));
						wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC10", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
						wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC10", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='securityCode']")));
						wiley.enterCVV_Number(excelOperation.getTestData("TC10", "WILEY_NA_Cart_Test_Data", "CVV"));
						driver.switchTo().defaultContent();
						wiley.clickOnSaveAndContinueButton();
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC10", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC10", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							String scharge=wiley.fetchShippingCharge();
							excelOperation.updateTestData("TC10", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC10", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
							excelOperation.updateTestData("TC10", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);
							/*driver.get("https://yopmail.com/en/");

							wiley.enterEmailIdInYopmail(email);
							wiley.clickOnCheckInboxButton();
							if(checkIfOrderConfirmationMailReceived()) {
								Reporting.updateTestReport("Order Confirmation mail was received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								validateOrderConfirmationMailContent(taxamount,scharge,ordertotal);
							}
							else {
								Reporting.updateTestReport("Order Confirmation mail was not received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}*/

						}		

						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Cardholder name ield in Card information"
								+ " section was not clickable and caused timeout exception"
								, CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Shipping Address line 1 was not clickable"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			wiley.wileyLogOutException();
		}

	}
	/*
	 * @Author: Vishnu
	 * 
	 * @Description: This is verify to placing an order for digital product for the
	 * existing user
	 */
	@Test
	public void TC11_Shipping_And_BillingAddresses_different() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC11_Shipping_And_BillingAddresses_different");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC11", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(email);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.enterFirstName(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(
							excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(
							excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));
					wiley.clickOnSaveAndContinueButton();
					try {
						if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
							wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					driver.switchTo().frame(0);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						wiley.enterCardHolderName(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "First_Name"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));
						wiley.enterCardNumber(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Card_Number"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='expiryMonth']")));
						wiley.selectExpirationMonthFromDropDown(
								excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
						wiley.selectExpirationYearFromDropDown(
								excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='securityCode']")));
						wiley.enterCVV_Number(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "CVV"));
						driver.switchTo().defaultContent();
						driver.findElement(By.xpath("//label[@id='sameAsBillingLabel']")).click();
						wiley.enterFirstName(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "First_Name"));
						wiley.enterLastName(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Last_Name"));
						wiley.selectCountry(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Bill_Country"));
						try{
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
							wiley.enterAddressLine1Billing(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Bill_Address_line1"));
							try{
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='postalCode']")));
								wiley.enterZipBilling(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Bill_Zip_Code"));
								try{
									wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='city']")));
									wiley.enterCityBilling(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Bill_City"));
									wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC11", "WILEY_NA_Cart_Test_Data", "Bill_Phone_Number"));
									wiley.clickOnSaveAndContinueButton();
									try {
										if(wiley.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed()) 
											wiley.clickOnUseSelectedBillingAddressButtonAddressDoctor();
									}
									catch(Exception e) {
										Reporting.updateTestReport("Adress doctor pop up did not appear",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
									}
									wiley.clickOnPlaceOrderButton();
									String orderconfirmation = driver.getTitle();
									if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
										ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
										String orderId = wiley.fetchOrderId();
										excelOperation.updateTestData("TC11", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
										excelOperation.updateTestData("TC11", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
										ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
										String ordertotal = wiley.fetchOrderTotal();
										String taxamount = wiley.fetchTaxAmount();
										String scharge=wiley.fetchShippingCharge();
										excelOperation.updateTestData("TC11", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
										excelOperation.updateTestData("TC11", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
										excelOperation.updateTestData("TC11", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);
										/*driver.get("https://yopmail.com/en/");

										wiley.enterEmailIdInYopmail(email);
										wiley.clickOnCheckInboxButton();
										if(checkIfOrderConfirmationMailReceived()) {
											Reporting.updateTestReport("Order Confirmation mail was received",
							                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
											validateOrderConfirmationMailContent(taxamount,scharge,ordertotal);
										}
										else {
											Reporting.updateTestReport("Order Confirmation mail was not received",
							                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}*/

									}				
									else {
										Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
												StatusDetails.FAIL);
									}

								}
								catch(Exception e) {
									Reporting.updateTestReport("Billing City was not clickable"
											+ " and caused timeout exception",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("Billing zip code was not clickable"
										+ " and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("Billing address line 1 was not clickable"
									+ " and caused timeout exception",
									CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Cardholder name ield in Card information"
								+ " section was not clickable and caused timeout exception"
								, CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Shipping Address line 1 was not clickable"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}

	}
	/*
	 * @Author: Vishnu
	 * 
	 * @Description: This is verify to placing an order for digital product for the
	 * existing user
	 */
	@Test

	public void TC12_Product_having_ADD_Promotion() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC12_Product_having_ADD_Promotion");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC12", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,400,SS_path);
				wiley.clickOnPromotionCodelink();
				wiley.enterPromoCode(excelOperation.getTestData("WILEY_PROMO_ADD", "Generic_Dataset", "Data"));
				wiley.ApplyPromo();
				BigDecimal subtotal=new BigDecimal(wiley.fetchOrderSubTotalInCartPage().substring(1));
				BigDecimal ordertotalInCart=new BigDecimal(wiley.fetchOrderTotalInCartPage().substring(1));
				BigDecimal discount=new BigDecimal(wiley.fetchDiscountAmountInCartPage().substring(2));
				System.out.println(discount);
				if(subtotal.multiply(new BigDecimal(0.25)).setScale(2, RoundingMode.CEILING).compareTo(discount)==0) 
					Reporting.updateTestReport(
							"The rounded value of :"+subtotal.multiply(new BigDecimal(0.25))+
							" is same as the discount value: "+discount,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The rounded value of :"+subtotal.multiply(new BigDecimal(0.25))+
							" is not same as the discount value: "+discount,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				if(priceOfFirstProduct.subtract(discount).compareTo(ordertotalInCart)==0) 
					Reporting.updateTestReport(
							"The addition of all the products' price and the subtraction of the discount"
									+ " is same as the subtotal in cart page",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' price and the subtraction of the discount "
									+ "didn't match with the subtotal in cart page",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

				ScrollingWebPage.PageScrolldown(driver,0,900,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(email);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.enterFirstName(excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(
							excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(
							excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));
					wiley.clickOnSaveAndContinueButton();
					try {
						if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
							wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					driver.switchTo().frame(0);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						wiley.enterCardHolderName(excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "First_Name"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));
						wiley.enterCardNumber(excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "Card_Number"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='expiryMonth']")));
						wiley.selectExpirationMonthFromDropDown(
								excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
						wiley.selectExpirationYearFromDropDown(
								excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='securityCode']")));
						wiley.enterCVV_Number(excelOperation.getTestData("TC12", "WILEY_NA_Cart_Test_Data", "CVV"));
						driver.switchTo().defaultContent();
						// Thread.sleep(3000);
						wiley.clickOnSaveAndContinueButton();

						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC12", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC12", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							String scharge=wiley.fetchShippingCharge();
							excelOperation.updateTestData("TC12", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC12", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
							excelOperation.updateTestData("TC12", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);
							/*driver.get("https://yopmail.com/en/");

							wiley.enterEmailIdInYopmail(email);
							wiley.clickOnCheckInboxButton();
							if(checkIfOrderConfirmationMailReceived()) {
								Reporting.updateTestReport("Order Confirmation mail was received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								validateOrderConfirmationMailContent(taxamount,scharge,ordertotal);
							}
							else {
								Reporting.updateTestReport("Order Confirmation mail was not received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}*/

						}				
						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Cardholder name ield in Card information"
								+ " section was not clickable and caused timeout exception"
								, CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Shipping Address line 1 was not clickable"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}

	}
	/*
	 * @Author: Vishnu
	 * 
	 * @Description: This is verify purchase the eBook Rental Product
	 */
	@Test

	public void TC13_eBook_Rental_product_Purchase() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC13_eBook_Rental_product_Purchase");
			driver.get(wiley.wileyURLConcatenation("TC13", "WILEY_NA_Cart_Test_Data", "URL"));
			//driver.navigate().refresh();
			//wiley.ebookRentalProduct();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(email);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.selectingSchoolInfo();
				wiley.clickOnSaveAndContinueButton();
				driver.switchTo().frame(0);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					wiley.enterCardHolderName(excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "First_Name"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));
					wiley.enterCardNumber(excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "Card_Number"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='expiryMonth']")));
					wiley.selectExpirationMonthFromDropDown(
							excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
					wiley.selectExpirationYearFromDropDown(
							excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='securityCode']")));
					wiley.enterCVV_Number(excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "CVV"));
					driver.switchTo().defaultContent();
					wiley.enterFirstName(excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "First_Name"));
					wiley.enterLastName(excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "Last_Name"));
					wiley.selectCountry(excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "Bill_Country"));
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
						wiley.enterAddressLine1Billing(excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "Bill_Address_line1"));
						wiley.enterZipBilling(excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "Bill_Zip_Code"));
						//wiley.enterCityBilling(excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "Bill_City"));
						//wiley.enterState(excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "Bill_State"));
						wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC13", "WILEY_NA_Cart_Test_Data", "Bill_Phone_Number"));

						wiley.clickOnSaveAndContinueButton();
						try {
							if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
								wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
						catch(Exception e) {
							Reporting.updateTestReport("Adress doctor pop up did not appear",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
						}
						//wiley.clickOnRentalTermsCheckbox();
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC13", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC13", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							excelOperation.updateTestData("TC13", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC13", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
							/*driver.get("https://yopmail.com/en/");

							wiley.enterEmailIdInYopmail(email);
							wiley.clickOnCheckInboxButton();
							if(checkIfOrderConfirmationMailReceived()) {
								Reporting.updateTestReport("Order Confirmation mail was received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								validateOrderConfirmationMailContent(taxamount," ",ordertotal);
							}
							else {
								Reporting.updateTestReport("Order Confirmation mail was not received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}*/


						}				
						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Billing address line 1 was not clickable"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Cardholder name ield in Card information"
							+ " section was not clickable and caused timeout exception"
							, CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();

		} catch (Exception e) {
			wiley.wileyLogOutException();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);

		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Placing an order with Textbook rental product
	 * @Date: 07/11/22
	 */
	@Test
	public void TC14_Textbook_Rental_product_purchase() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC14_Textbook_Rental_product_purchase");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC14", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String emailID = wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(emailID);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.enterFirstName(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));
					wiley.selectShippingMethod();
					wiley.clickOnSaveAndContinueButton();
					try {
						if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
							wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					wiley.selectSchoolInfo(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "School_State"),excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "School_Name"));
					Thread.sleep(1000);
					wiley.clickOnSaveAndContinueButton();
					driver.switchTo().frame(0);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						wiley.enterCardHolderName(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "First_Name"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));

						wiley.enterCardNumber(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "Card_Number"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='expiryMonth']")));
						wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
						wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='securityCode']")));
						wiley.enterCVV_Number(excelOperation.getTestData("TC14", "WILEY_NA_Cart_Test_Data", "CVV"));
						driver.switchTo().defaultContent();
						wiley.clickOnSaveAndContinueButton();
						wiley.clickOnRentalTermsCheckbox();
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC14", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC14", "WILEY_NA_Cart_Test_Data", "Email_Id", emailID);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							String scharge=wiley.fetchShippingCharge();
							excelOperation.updateTestData("TC14", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC14", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
							excelOperation.updateTestData("TC14", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);
							/*driver.get("https://yopmail.com/en/");

							wiley.enterEmailIdInYopmail(emailID);
							wiley.clickOnCheckInboxButton();
							if(checkIfOrderConfirmationMailReceived()) {
								Reporting.updateTestReport("Order Confirmation mail was received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								validateOrderConfirmationMailContent(taxamount,scharge,ordertotal);
							}
							else {
								Reporting.updateTestReport("Order Confirmation mail was not received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}*/

						}				
						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Cardholder name ield in Card information"
								+ " section was not clickable and caused timeout exception"
								, CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Shipping Address line 1 was not clickable"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();

		}
		catch(Exception e){
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Placing an order with eBundle product (Using RGuroo)
	 * @Date: 07/11/22
	 */
	@Test
	public void TC15_eBundle_product_purchase() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC15_eBundle_product_purchase");
			driver.get(wiley.wileyURLConcatenation("TC15", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(email);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.selectSchoolInfo(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "School_State"),excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "School_Name"));
				wiley.clickOnSaveAndContinueButton();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					wiley.enterCardHolderName(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "First_Name"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
					wiley.enterCardNumber(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "Card_Number"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
					wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
					wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
					wiley.enterCVV_Number(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "CVV"));
					driver.switchTo().defaultContent();
					wiley.enterFirstName(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "First_Name"));
					wiley.enterLastName(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "Last_Name"));
					wiley.selectCountry(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "Bill_Country"));
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
						wiley.enterAddressLine1Billing(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "Bill_Address_line1"));

						wiley.enterZipBilling(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "Bill_Zip_Code"));
						//wiley.enterCityBilling(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "Bill_City"));
						//wiley.enterState(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "Bill_State"));
						wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC15", "WILEY_NA_Cart_Test_Data", "Bill_Phone_Number"));
						wiley.clickOnSaveAndContinueButton();
						try {
							if(wiley.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed()) 
								wiley.clickOnUseSelectedBillingAddressButtonAddressDoctor();
						}
						catch(Exception e) {
							Reporting.updateTestReport("Adress doctor pop up did not appear",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
						}
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC15", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC15", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							excelOperation.updateTestData("TC15", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC15", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
							/*driver.get("https://yopmail.com/en/");

							wiley.enterEmailIdInYopmail(email);
							wiley.clickOnCheckInboxButton();
							if(checkIfOrderConfirmationMailReceived()) {
								Reporting.updateTestReport("Order Confirmation mail was received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								validateOrderConfirmationMailContent(taxamount," ",ordertotal);
							}
							else {
								Reporting.updateTestReport("Order Confirmation mail was not received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}*/


						}				
						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Billing address line 1 was not clickable"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Cardholder name ield in Card information"
							+ " section was not clickable and caused timeout exception"
							, CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e){
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Higher Education Physical product purchase
	 * @Date: 07/11/22
	 */
	@Test
	public void TC16_Higher_Education_Physical_product_purchase() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC16_Higher_Education_Physical_product_purchase");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC16", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String emailID = wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(emailID);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.enterFirstName(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));
					wiley.selectShippingMethod();
					wiley.clickOnSaveAndContinueButton();
					try {
						if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
							wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					wiley.selectSchoolInfo(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "School_State"),excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "School_Name"));
					wiley.clickOnSaveAndContinueButton();
					driver.switchTo().frame(0);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						wiley.enterCardHolderName(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "First_Name"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));
						wiley.enterCardNumber(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "Card_Number"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='expiryMonth']")));
						wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
						wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='securityCode']")));
						wiley.enterCVV_Number(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "CVV"));
						driver.switchTo().defaultContent();
						wiley.clickOnSaveAndContinueButton();
						wiley.clickOnRentalTermsCheckbox();
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC16", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC16", "WILEY_NA_Cart_Test_Data", "Email_Id", emailID);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							String scharge=wiley.fetchShippingCharge();
							excelOperation.updateTestData("TC16", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC16", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
							excelOperation.updateTestData("TC16", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);
							/*driver.get("https://yopmail.com/en/");

							wiley.enterEmailIdInYopmail(emailID);
							wiley.clickOnCheckInboxButton();
							if(checkIfOrderConfirmationMailReceived()) {
								Reporting.updateTestReport("Order Confirmation mail was received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								validateOrderConfirmationMailContent(taxamount,scharge,ordertotal);
							}
							else {
								Reporting.updateTestReport("Order Confirmation mail was not received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}*/

						}				
						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Shipping Address line 1 was not clickable"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Cardholder name ield in Card information"
							+ " section was not clickable and caused timeout exception"
							, CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();

		}
		catch(Exception e){
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Place an order with coupon code (SDP66)
	 * @Date: 7/11/22
	 */
	@Test
	public void TC17_Purchase_with_Coupon_Discount() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC17_Purchase_with_Coupon_Discount");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC17", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,400,SS_path);
				wiley.clickOnPromotionCodelink();
				wiley.enterPromoCode(excelOperation.getTestData("WILEY_PROMO_SDP66", "Generic_Dataset", "Data"));
				wiley.ApplyPromo();
				BigDecimal subtotal=new BigDecimal(wiley.fetchOrderSubTotalInCartPage().substring(1));
				BigDecimal ordertotalInCart=new BigDecimal(wiley.fetchOrderTotalInCartPage().substring(1));
				BigDecimal discount=new BigDecimal(wiley.fetchDiscountAmountInCartPage().substring(2));
				System.out.println(discount);
				if(subtotal.multiply(new BigDecimal(0.35)).setScale(2, RoundingMode.CEILING).compareTo(discount)==0) 
					Reporting.updateTestReport(
							"The rounded value of :"+subtotal.multiply(new BigDecimal(0.35))+
							" is same as the discount value: "+discount,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The rounded value of :"+subtotal.multiply(new BigDecimal(0.35))+
							" is not same as the discount value: "+discount,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				if(priceOfFirstProduct.subtract(discount).compareTo(ordertotalInCart)==0) 
					Reporting.updateTestReport(
							"The addition of all the products' price and the subtraction of the discount"
									+ " is same as the subtotal in cart page",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' price and the subtraction of the discount "
									+ "didn't match with the subtotal in cart page",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				ScrollingWebPage.PageScrolldown(driver,0,900,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String emailID = wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(emailID);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.enterFirstName(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));
					wiley.selectShippingMethod();
					wiley.clickOnSaveAndContinueButton();
					try {
						if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
							wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					driver.switchTo().frame(0);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						wiley.enterCardHolderName(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "First_Name"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));
						wiley.enterCardNumber(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Card_Number"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='expiryMonth']")));
						wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
						wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='securityCode']")));
						wiley.enterCVV_Number(excelOperation.getTestData("TC17", "WILEY_NA_Cart_Test_Data", "CVV"));
						driver.switchTo().defaultContent();
						wiley.clickOnSaveAndContinueButton();
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC17", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC17", "WILEY_NA_Cart_Test_Data", "Email_Id", emailID);
							excelOperation.updateTestData("TC33", "WILEY_NA_Cart_Test_Data", "Email_Id", emailID);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							String scharge=wiley.fetchShippingCharge();
							excelOperation.updateTestData("TC17", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC17", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
							excelOperation.updateTestData("TC17", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);
							/*driver.get("https://yopmail.com/en/");

							wiley.enterEmailIdInYopmail(emailID);
							wiley.clickOnCheckInboxButton();
							if(checkIfOrderConfirmationMailReceived()) {
								Reporting.updateTestReport("Order Confirmation mail was received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								validateOrderConfirmationMailContent(taxamount,scharge,ordertotal);
							}
							else {
								Reporting.updateTestReport("Order Confirmation mail was not received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}*/


						}				
						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Shipping Address line 1 was not clickable"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Cardholder name ield in Card information"
							+ " section was not clickable and caused timeout exception"
							, CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Recalculates the tax after changing the billing address
	 * @Date: 7/11/22
	 */
	@Test
	public void TC18_Tax_Recalculation() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest(" TC18_Tax_Recalculation");
			driver.get(wiley.wileyURLConcatenation("TC18", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(email);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();

				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					wiley.enterCardHolderName(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "First_Name"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
					wiley.enterCardNumber(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Card_Number"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
					wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
					wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
					wiley.enterCVV_Number(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "CVV"));
					driver.switchTo().defaultContent();			
					wiley.enterFirstName(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "First_Name"));
					wiley.enterLastName(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Last_Name"));
					wiley.selectCountry(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Bill_Country"));
					Thread.sleep(3000);
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
						wiley.enterAddressLine1Billing(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Bill_Address_line1"));
						wiley.enterZipBilling(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Bill_Zip_Code"));
						Thread.sleep(2000);
						wiley.enterCityBilling(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Bill_City"));
						//wiley.enterState(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Bill_State"));
						//wiley.selectUState();
						wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Bill_Phone_Number"));
						Thread.sleep(3000);
						wiley.clickOnSaveAndContinueButton();
						try {
							if(wiley.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed()) 
								wiley.clickOnUseSelectedBillingAddressButtonAddressDoctor();
						}
						catch(Exception e) {
							Reporting.updateTestReport("Adress doctor pop up did not appear",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
						}
						String tax1=wiley.fetchTaxInOrderReview();
						System.out.println(tax1);
						wiley.clickOnEditButtonInBilling();			
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));			
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
							wiley.enterCardHolderName(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "First_Name"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
							wiley.enterCardNumber(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Card_Number"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
							wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
							wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
							driver.switchTo().defaultContent();
							driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
							wiley.enterCVV_Number(excelOperation.getTestData("TC18", "WILEY_NA_Cart_Test_Data", "CVV"));
							driver.switchTo().defaultContent();			
							wiley.enterFirstName(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "First_Name"));
							wiley.enterLastName(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Last_Name"));
							wiley.selectCountry(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_Country"));
							Thread.sleep(3000);
							try{
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
								wiley.enterAddressLine1Billing(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_Address_line1"));
								wiley.enterZipBilling(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_Zip_Code"));
								//wiley.enterCityBilling(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_City"));
								//wiley.enterState(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_State"));

								wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_Phone_Number"));
								Thread.sleep(1000);
								wiley.clickOnSaveAndContinueButton();
								try {
									if(wiley.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed()) 
										wiley.clickOnUseSelectedBillingAddressButtonAddressDoctor();
								}
								catch(Exception e) {
									Reporting.updateTestReport("Adress doctor pop up did not appear",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
								}
								String tax2=wiley.fetchTaxInOrderReview();
								System.out.println(tax2);
								if(!tax1.contentEquals(tax2))
									Reporting.updateTestReport("Tax value for first address: "+tax1+" was recalculated for second address with new value: "+tax2,
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								else
									Reporting.updateTestReport("Both the tax values were equal for two different billing addresses",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

							}
							catch(Exception e) {
								Reporting.updateTestReport("Billing address line 1 was not clickable"
										+ " and caused timeout exception",
										CaptureScreenshot.getScreenshot(SS_path),
										StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("Cardholder name ield in Card information"
									+ " section was not clickable and caused timeout exception"
									, CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("Billing address line 1 was not clickable"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Cardholder name field in Card information"
							+ " section was not clickable and caused timeout exception"
							, CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Checks the country restriction functionality for physical products with restricted shipping address
	 * @Date: 7/11/22
	 */
	@Test
	public void TC19_Country_Restriction_Validation() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC19_Country_Restriction_Validation");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC19", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String emailID = wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(emailID);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.enterFirstName(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Shipping_City"));

					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC19", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));
					wiley.clickOnSaveAndContinueButton();
					try {
						if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
							wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					if(wiley.checkIfCountryRestrictrionModalIsDisplayed())
						Reporting.updateTestReport("Country Restriction modal functionality validated successfully", CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.PASS);
					else Reporting.updateTestReport("Country Restriction modal functionality was not validated ", CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
				catch(Exception e) {
					Reporting.updateTestReport("Shipping Address line 1 was not clickable"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Checks the tax for new user physical product
	 * @Date: 8/11/22
	 */
	@Test
	public void TC20_New_user_tax_for_physical_product() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC20_New_user_tax_for_physical_product");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC20", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String emailID = wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(emailID);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.enterFirstName(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));

					wiley.selectShippingMethod();
					wiley.clickOnSaveAndContinueButton();
					try {
						if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
							wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					driver.switchTo().frame(0);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						wiley.enterCardHolderName(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "First_Name"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));			
						wiley.enterCardNumber(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Card_Number"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='expiryMonth']")));
						wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
						wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='securityCode']")));
						wiley.enterCVV_Number(excelOperation.getTestData("TC20", "WILEY_NA_Cart_Test_Data", "CVV"));
						driver.switchTo().defaultContent();
						wiley.clickOnSaveAndContinueButton();
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC20", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC20", "WILEY_NA_Cart_Test_Data", "Email_Id", emailID);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							String scharge=wiley.fetchShippingCharge();
							excelOperation.updateTestData("TC20", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC20", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
							excelOperation.updateTestData("TC20", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);
							/*driver.get("https://yopmail.com/en/");

							wiley.enterEmailIdInYopmail(emailID);
							wiley.clickOnCheckInboxButton();
							if(checkIfOrderConfirmationMailReceived()) {
								Reporting.updateTestReport("Order Confirmation mail was received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								validateOrderConfirmationMailContent(taxamount,scharge,ordertotal);
							}
							else {
								Reporting.updateTestReport("Order Confirmation mail was not received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}*/


						}				
						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Cardholder name ield in Card information"
								+ " section was not clickable and caused timeout exception"
								, CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Shipping Address line 1 was not clickable"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Validates the tax for existing user physical product
	 * @Date: 8/11/22
	 */
	@Test
	public void TC21_Existing_user_tax_for_physical_product () throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC21_Existing_user_tax_for_physical_product");
			driver.get(wiley.wileyURLConcatenation("TC21", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				wiley.enterExistingWileyUserMailID(excelOperation.getTestData("TC21", "WILEY_NA_Cart_Test_Data", "Email_Id"));
				wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC21", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnLogInAndContinueButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				wiley.selectUSEOptionForExistingAddress();
				ScrollingWebPage.PageScrolldown(driver,0,600,SS_path);
				Thread.sleep(2000);
				wiley.clickOnSaveAndContinueButton();
				driver.switchTo().frame(0);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					wiley.enterCardHolderName(excelOperation.getTestData("TC21", "WILEY_NA_Cart_Test_Data", "First_Name"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));
					wiley.enterCardNumber(excelOperation.getTestData("TC21", "WILEY_NA_Cart_Test_Data", "Card_Number"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='expiryMonth']")));
					wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC21", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
					wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC21", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='securityCode']")));
					wiley.enterCVV_Number(excelOperation.getTestData("TC21", "WILEY_NA_Cart_Test_Data", "CVV"));
					driver.switchTo().defaultContent();
					wiley.clickOnSaveAndContinueButton();
					wiley.clickOnPlaceOrderButton();
					String orderconfirmation = driver.getTitle();
					if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
						ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
						String orderId = wiley.fetchOrderId();
						excelOperation.updateTestData("TC21", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
						ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
						String ordertotal = wiley.fetchOrderTotal();
						String taxamount = wiley.fetchTaxAmount();
						String scharge=wiley.fetchShippingCharge();
						excelOperation.updateTestData("TC21", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
						excelOperation.updateTestData("TC21", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
						excelOperation.updateTestData("TC21", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);

					}				
					else {
						Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}

				}
				catch(Exception e) {
					Reporting.updateTestReport("Cardholder name ield in Card information"
							+ " section was not clickable and caused timeout exception"
							, CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Validates the tax for new user digital product
	 * @Date: 8/11/22
	 */
	@Test
	public void TC22_New_User_Tax_for_Digital_product() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC22_New_User_Tax_for_Digital_product");
			driver.get(wiley.wileyURLConcatenation("TC22", "WILEY_NA_Cart_Test_Data", "URL"));

			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(email);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC22", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.selectSchoolInfo(excelOperation.getTestData("TC16", "WILEY_NA_Cart_Test_Data", "School_State"),excelOperation.getTestData("TC09", "WILEY_NA_Cart_Test_Data", "School_Name"));
				wiley.clickOnSaveAndContinueButton();
				driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='cardholder name']")));
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					wiley.enterCardHolderName(excelOperation.getTestData("TC22", "WILEY_NA_Cart_Test_Data", "First_Name"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='card number']")));
					wiley.enterCardNumber(excelOperation.getTestData("TC22", "WILEY_NA_Cart_Test_Data", "Card_Number"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
					wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC22", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
					wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC22", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
					wiley.enterCVV_Number(excelOperation.getTestData("TC22", "WILEY_NA_Cart_Test_Data", "CVV"));
					driver.switchTo().defaultContent();			
					wiley.enterFirstName(excelOperation.getTestData("TC22", "WILEY_NA_Cart_Test_Data", "First_Name"));
					wiley.enterLastName(excelOperation.getTestData("TC22", "WILEY_NA_Cart_Test_Data", "Last_Name"));
					wiley.selectCountry(excelOperation.getTestData("TC22", "WILEY_NA_Cart_Test_Data", "Bill_Country"));
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
						wiley.enterAddressLine1Billing(excelOperation.getTestData("TC22", "WILEY_NA_Cart_Test_Data", "Bill_Address_line1"));
						wiley.enterZipBilling(excelOperation.getTestData("TC22", "WILEY_NA_Cart_Test_Data", "Bill_Zip_Code"));
						wiley.enterCityBilling(excelOperation.getTestData("TC22", "WILEY_NA_Cart_Test_Data", "Bill_City"));
						//Commenting this line as this is not applicable for India
						//wiley.enterState(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_State"));

						wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC22", "WILEY_NA_Cart_Test_Data", "Bill_Phone_Number"));
						wiley.clickOnSaveAndContinueButton();
						try {
							if(wiley.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed()) 
								wiley.clickOnUseSelectedBillingAddressButtonAddressDoctor();
						}
						catch(Exception e) {
							Reporting.updateTestReport("Adress doctor pop up did not appear",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
						}
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC22", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC22", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							excelOperation.updateTestData("TC22", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC22", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
							/*driver.get("https://yopmail.com/en/");

							wiley.enterEmailIdInYopmail(email);
							wiley.clickOnCheckInboxButton();
							if(checkIfOrderConfirmationMailReceived()) {
								Reporting.updateTestReport("Order Confirmation mail was received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								validateOrderConfirmationMailContent(taxamount," ",ordertotal);
							}
							else {
								Reporting.updateTestReport("Order Confirmation mail was not received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}*/


						}				
						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Billing address line 1 was not clickable"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}

				}
				catch(Exception e) {
					Reporting.updateTestReport("Cardholder name ield in Card information"
							+ " section was not clickable and caused timeout exception"
							, CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Validates the tax for an existing user digital product
	 * @Date: 8/11/22
	 */
	@Test
	public void TC23_Existing_User_Tax_for_Digital_Product() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC23_Existing_User_Tax_for_Digital_Product");
			driver.get(wiley.wileyURLConcatenation("TC23", "WILEY_NA_Cart_Test_Data", "URL"));			
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "Email_Id");
				wiley.enterExistingWileyUserMailID(email);
				wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnLogInAndContinueButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				driver.switchTo().frame(0);
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
					wiley.enterCardHolderName(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "First_Name"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));
					wiley.enterCardNumber(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "Card_Number"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryMonth']")));
					wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
					wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='securityCode']")));
					wiley.enterCVV_Number(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "CVV"));
					driver.switchTo().defaultContent();
					//wiley.clickOnEnterNewAddress();
					wiley.enterFirstName(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "First_Name"));
					wiley.enterLastName(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "Last_Name"));
					wiley.selectCountry(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "Bill_Country"));
					try{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
						wiley.enterAddressLine1Billing(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "Bill_Address_line1"));
						wiley.enterZipBilling(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "Bill_Zip_Code"));
						wiley.enterCityBilling(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "Bill_City"));
						//wiley.enterState(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "Bill_State"));

						wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC23", "WILEY_NA_Cart_Test_Data", "Bill_Phone_Number"));
						wiley.clickOnSaveAndContinueButton();
						try {
							if(wiley.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed()) 
								wiley.clickOnUseSelectedBillingAddressButtonAddressDoctor();
						}
						catch(Exception e) {
							Reporting.updateTestReport("Adress doctor pop up did not appear",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
						}
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC23", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC23", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							excelOperation.updateTestData("TC23", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC23", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);


						}				
						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Billing address line 1 was not clickable"
								+ " and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("Cardholder name ield in Card information"
							+ " section was not clickable and caused timeout exception"
							, CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);


		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Verify the Shipping options for POD and Physical products on Cart Page
	 * @Date: 8/11/11
	 */
	@Test
	public void TC24_Verify_Shipping_Methods_for_POD_Products() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC24_Verify_Shipping_Methods_for_POD_Products");
			driver.get(wiley.wileyURLConcatenation("TC24", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String emailID = wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(emailID);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC24", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.validateShippingMethodMessageForPOD();
				wiley.WileyLogOut();
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}

		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Validates if products are coming in search result after searching with the word "rguroo"
	 * @Date: 8/11/22
	 */
	@Test
	public void TC25_Searching_by_Keyword_Rguroo() throws IOException{
		try {

			Reporting.test = Reporting.extent.createTest("TC25_Searching_by_Keyword_Rguroo");
			driver.get(wiley.wileyURLConcatenation("TC25", "WILEY_NA_Cart_Test_Data", "URL"));
			wiley.searchDataInSearchBar("rguroo");
			wiley.checkIfRGurooAppliedInFacet();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Turns a digital cart to phygital cart which results in difference in checkout steps
	 * @Date: 9/11/22
	 */
	@Test
	public void TC28_Cart_Update_from_Digital_to_Phygital() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC28_Cart_Update_from_Digital_to_Phygital");
			driver.get(wiley.wileyURLConcatenation("TC28", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(email);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC28", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.checkIfUserIsInPaymentMethod();
				wiley.searchDataInSearchBar(excelOperation.getTestData("TC28", "WILEY_NA_Cart_Test_Data", "ISBN"));
				wiley.clickOnSRP_WileyProduct();
				wiley.clickOnPrintTab();
				wiley.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions.
							elementToBeClickable
							(By.xpath("//button[contains(text(),'View Cart')]")));
					wiley.clickOnViewCartButton();
					ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
					wiley.clickOnProceedToCheckoutButton();
					String email1=wiley.enterEmailIdInCreateAccountForm();
					wiley.clickOnCreateAccountButton();
					wiley.confirmEmailIdInCreateAccountForm(email1);
					wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC28", "WILEY_NA_Cart_Test_Data", "Password"));
					wiley.clickOnSaveAndContinueButton();
					wiley.checkIfUserIsInShippingStep();

				}
				catch(Exception e) {
					Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Validates the UI of Cart page
	 * @Date: 9/11/22
	 */
	@Test
	public void TC29_Cart_Page_UI_Validation() throws IOException{
		try {

			Reporting.test = Reporting.extent.createTest("TC29_Cart_Page_UI_Validation");
			driver.get(wiley.wileyURLConcatenation("TC29", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				wiley.checkShopLinkInCartPageHeader();
				wiley.checkResearchLibrariesLinkInCartPageHeader();
				wiley.checkPublishingServicesLinkInCartPageHeader();
				wiley.checkProfessionalDevelopmentLinkInCartPageHeader();
				wiley.checkCartIcon();
				wiley.checkCartItemQuantity();
				wiley.checkBreadCrumbCartPage();
				wiley.checkTextInOrderSummaryTab();

			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();

		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Validating the cart merge functionality( old cart and new cart getting merged)
	 * @Date: 9/11/22
	 */
	@Test
	public void TC30_Cart_Merge_Functionality() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC30_Cart_Merge_Functionality");
			driver.get(wiley.wileyURLConcatenation("TC30", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				wiley.enterExistingWileyUserMailID(excelOperation.getTestData("TC30", "WILEY_NA_Cart_Test_Data", "Email_Id"));
				wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC30", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnLogInAndContinueButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				wiley.WileyLogOut();			
				driver.get(wiley.wileyURLConcatenation("TC30", "WILEY_NA_Cart_Test_Data", "ISBN"));		
				BigDecimal priceOfSecondProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
				wiley.clickOnAddToCartButton();
				try {
					wait.until(ExpectedConditions.
							elementToBeClickable
							(By.xpath("//button[contains(text(),'View Cart')]")));
					wiley.clickOnViewCartButton();
					
					ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
					wiley.clickOnProceedToCheckoutButton();
					wiley.enterExistingWileyUserMailID(excelOperation.getTestData("TC30", "WILEY_NA_Cart_Test_Data", "Email_Id"));
					wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC30", "WILEY_NA_Cart_Test_Data", "Password"));
					wiley.clickOnLogInAndContinueButton();
					String quantity=wiley.checkCartItemQuantity();
					BigDecimal ordertotal=new BigDecimal(wiley.fetchOrderTotalInCartPage().substring(1));
					if(priceOfFirstProduct.add(priceOfSecondProduct).compareTo(ordertotal)==0) 
						Reporting.updateTestReport(
								"The addition of all the products' price is same as the subtotal in cart page",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport(
								"The addition of all the products' pricedidn't match with the subtotal in cart page",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					if(quantity.equals("2")) Reporting.updateTestReport("New Cart was merged with old cart", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else Reporting.updateTestReport("New Cart was not merged with old cart", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

				}
				catch(Exception e) {
					Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Verifies the zero dollar flow
	 * @Date: 14/11/22
	 */
	@Test
	public void TC31_Zero_Dollar_Flow_For_Digital_Product() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC31_Zero_Dollar_Flow_For_Digital_Product");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC31", "WILEY_NA_Cart_Test_Data", "URL"));			
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				wiley.clickOnPromotionCodelink();
				wiley.enterPromoCode(excelOperation.getTestData("WILEY_100_PERCENT_PROMO", "Generic_Dataset", "Data"));
				wiley.ApplyPromo();
				BigDecimal subtotal=new BigDecimal(wiley.fetchOrderSubTotalInCartPage().substring(1));
				BigDecimal ordertotalInCart=new BigDecimal(wiley.fetchOrderTotalInCartPage().substring(1));
				BigDecimal discount=new BigDecimal(wiley.fetchDiscountAmountInCartPage().substring(2));
				System.out.println(discount);
				if(subtotal.multiply(new BigDecimal(1.00)).setScale(2, RoundingMode.CEILING).compareTo(discount)==0) 
					Reporting.updateTestReport(
							"The rounded value of :"+subtotal.multiply(new BigDecimal(1.00))+
							" is same as the discount value: "+discount,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The rounded value of :"+subtotal.multiply(new BigDecimal(1.00))+
							" is not same as the discount value: "+discount,
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				if(priceOfFirstProduct.subtract(discount).compareTo(ordertotalInCart)==0) 
					Reporting.updateTestReport(
							"The addition of all the products' price and the subtraction of the discount"
									+ " is same as the subtotal in cart page",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' price and the subtraction of the discount "
									+ "didn't match with the subtotal in cart page",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(email);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC31", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.checkTextInZeroDollarFlow();
				wiley.enterFirstName(excelOperation.getTestData("TC31", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC31", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC31", "WILEY_NA_Cart_Test_Data", "Bill_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='street1']")));
					wiley.enterAddressLine1Billing(excelOperation.getTestData("TC31", "WILEY_NA_Cart_Test_Data", "Bill_Address_line1"));
					wiley.enterZipBilling(excelOperation.getTestData("TC31", "WILEY_NA_Cart_Test_Data", "Bill_Zip_Code"));
					/*wiley.enterCityBilling(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_City"));
					wiley.enterState(excelOperation.getTestData("TC01", "WILEY_NA_Cart_Test_Data", "Bill_State"));*/
					//wiley.selectUState();
					wiley.enterPhoneNumberBilling(excelOperation.getTestData("TC31", "WILEY_NA_Cart_Test_Data", "Bill_Phone_Number"));
					wiley.clickOnSaveAndContinueButton();
					try {
						if(wiley.returnUseSelectedBillingAddressButtonAddressDoctorPopUp().isDisplayed()) 
							wiley.clickOnUseSelectedBillingAddressButtonAddressDoctor();
					}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					wiley.clickOnPlaceOrderButton();
					String orderconfirmation = driver.getTitle();
					if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
						ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
						String orderId = wiley.fetchOrderId();
						excelOperation.updateTestData("TC31", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
						excelOperation.updateTestData("TC31", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
						excelOperation.updateTestData("TC33", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
						ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
						String ordertotal = wiley.fetchOrderTotal();

						excelOperation.updateTestData("TC31", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);

						/*driver.get("https://yopmail.com/en/");

						wiley.enterEmailIdInYopmail(email);
						wiley.clickOnCheckInboxButton();
						if(checkIfOrderConfirmationMailReceived()) {
							Reporting.updateTestReport("Order Confirmation mail was received",
			                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
							validateOrderConfirmationMailContent("$0.00"," ",ordertotal);
						}
						else {
							Reporting.updateTestReport("Order Confirmation mail was not received",
			                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}*/



					}				
					else {
						Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}

				}
				catch(Exception e) {
					Reporting.updateTestReport("Billing address line 1 was not clickable"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path),
							StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();

		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}
	/*
	 * @Author: Anindita
	 * @Description: Validates if the prices and next availability date is present in all steps for Pre-order and back-order product
	 * @Date: 14/11/22
	 */
	@Test
	public void TC32_Preorder_Backorder_Flow_With_Print_Pdf_Page() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC32_Preorder_Backorder_Flow_With_Print_Pdf_Page");
			//This is the pdp page url for backorder product
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			driver.get(wiley.wileyURLConcatenation("TC32", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			BigDecimal priceOfFirstProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.clickOnViewCartButton();
			//This is the ISBN of pre-order product
			wiley.searchDataInSearchBar(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "ISBN"));
			//Wiley.clickOnSearchIcon();
			wiley.clickOnSRP_WileyProduct();
			wiley.clickOnPrintTab();
			BigDecimal priceOfSecondProduct=new BigDecimal(wiley.fetchPriceInPDP().substring(1));
			wiley.clickOnAddToCartButton();
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				BigDecimal ordertotalInCart=new BigDecimal(wiley.fetchOrderTotalInCartPage().substring(1));
				if(priceOfFirstProduct.add(priceOfSecondProduct).compareTo(ordertotalInCart)==0) 
					Reporting.updateTestReport(
							"The addition of all the products' price is same as the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport(
							"The addition of all the products' pricedidn't match with the subtotal in cart page",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				wiley.checkNextAvailabilityDatePreorderInCartPage();
				wiley.checkNextAvailabilityDateBackorderInCartPage();
				wiley.checkNotificationMessagePreorderInCartPage();
				wiley.checkNotificationMessageBackorderInCartPage();
				ScrollingWebPage.PageScrolldown(driver,0,900,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				String email=wiley.enterEmailIdInCreateAccountForm();
				wiley.clickOnCreateAccountButton();
				wiley.confirmEmailIdInCreateAccountForm(email);
				wiley.enterPasswordInCreateAccountForm(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnSaveAndContinueButton();
				wiley.enterFirstName(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "First_Name"));
				wiley.enterLastName(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "Last_Name"));
				wiley.selectCountry(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "Shipping_Country"));
				try{
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='line1']")));
					wiley.enterAddressLine1Shipping(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "Shipping_Address_line1"));
					wiley.enterShippingZIPCode(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "Shipping_Zip_Code"));
					wiley.enterShippingCity(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "Shipping_City"));
					wiley.enterState(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "Shipping_State"));
					wiley.enterPhoneNumberShipping(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "Shipping_Phone_Number"));

					wiley.selectShippingMethod();
					wiley.clickOnSaveAndContinueButton();
					try {
						if(wiley.returnUseSelectedShippingAddressButtonAddressDoctorPopUp().isDisplayed()) 
							wiley.clickOnUseSelectedShippingAddressButtonAddressDoctor();}
					catch(Exception e) {
						Reporting.updateTestReport("Adress doctor pop up did not appear",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.WARNING);
					}
					driver.switchTo().frame(0);
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.id("nameOnCard")));
						wiley.enterCardHolderName(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "First_Name"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='card number']")));
						wiley.enterCardNumber(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "Card_Number"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='expiryMonth']")));
						wiley.selectExpirationMonthFromDropDown(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "Expiry_Month"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='expiryYear']")));
						wiley.selectExpirationYearFromDropDown(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "Expiry_Year"));
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='securityCode']")));
						wiley.enterCVV_Number(excelOperation.getTestData("TC32", "WILEY_NA_Cart_Test_Data", "CVV"));
						driver.switchTo().defaultContent();
						wiley.clickOnSaveAndContinueButton();
						wiley.checkNextAvailabilityDatePreorderInOrderReviewPage();
						wiley.checkNextAvailabilityDateBackorderInOrderReviewPage();
						wiley.clickOnPlaceOrderButton();
						String orderconfirmation = driver.getTitle();
						if (orderconfirmation.equalsIgnoreCase("orderConfirmation Page | Wiley")) {
							wiley.checkPrintReciept();
							ScrollingWebPage.PageScrolldown(driver,0,300,SS_path);
							String orderId = wiley.fetchOrderId();
							excelOperation.updateTestData("TC32", "WILEY_NA_Cart_Test_Data", "Order_Id", orderId);
							excelOperation.updateTestData("TC32", "WILEY_NA_Cart_Test_Data", "Email_Id", email);
							ScrollingWebPage.PageScrolldown(driver,0,500,SS_path);
							String ordertotal = wiley.fetchOrderTotal();
							String taxamount = wiley.fetchTaxAmount();
							String scharge=wiley.fetchShippingCharge();
							excelOperation.updateTestData("TC32", "WILEY_NA_Cart_Test_Data", "Order_Total", ordertotal);
							excelOperation.updateTestData("TC32", "WILEY_NA_Cart_Test_Data", "Tax", taxamount);
							excelOperation.updateTestData("TC32", "WILEY_NA_Cart_Test_Data", "Shipping_Charge", scharge);
							/*driver.get("https://yopmail.com/en/");

							wiley.enterEmailIdInYopmail(email);
							wiley.clickOnCheckInboxButton();
							if(checkIfOrderConfirmationMailReceived()) {
								Reporting.updateTestReport("Order Confirmation mail was received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
								validateOrderConfirmationMailContent(taxamount,scharge,ordertotal);
							}
							else {
								Reporting.updateTestReport("Order Confirmation mail was not received",
				                        CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}*/



						}				
						else {
							Reporting.updateTestReport("Order was not placed", CaptureScreenshot.getScreenshot(SS_path),
									StatusDetails.FAIL);
						}

					}
					catch(Exception e) {
						Reporting.updateTestReport("Cardholder name ield in Card information"
								+ " section was not clickable and caused timeout exception"
								, CaptureScreenshot.getScreenshot(SS_path),
								StatusDetails.FAIL);
					}

				}
				catch(Exception e) {
					Reporting.updateTestReport("Shipping Address line 1 was not clickable"
							+ " and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e){
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Description: Validates the reset password functionality 
	 * @Date: 15/11/22
	 */

	@Test
	public void TC33_Forgot_Password_Functionality() throws IOException{
		try {

			Reporting.test = Reporting.extent.createTest("TC33_Forgot_Password_Functionality");
			driver.get(wiley.wileyURLConcatenation("TC33", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				wiley.clickOnForgotPasswordLink();
				wiley.entersEmailIdForRecievingResetPasswordMail(excelOperation.getTestData("TC33", "WILEY_NA_Cart_Test_Data", "Email_Id"));
				wiley.clickOnSubmitButtonForRecievingResetPasswordMail();
				if(wiley.checkIfResetPasswordInstructionsIsPresent()) {
					driver.get(excelOperation.getTestData("Yopmail_URL",
							"Generic_Dataset", "Data"));
					wiley.enterEmailIdInYopmail(excelOperation.getTestData("TC33", "WILEY_NA_Cart_Test_Data", "Email_Id"));
					wiley.clickOnCheckInboxButton();


					WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
					int timeOutSeconds=60;
					int flag=0;
					WebElement element1 = driver.findElement(By.xpath("//button[@id='refresh']"));
					WebElement element2 = null;

					/* The purpose of this loop is to wait for maximum of 60 seconds */
					for (int i = 0; i < timeOutSeconds / 5; i++) {

						try {
							driver.switchTo().frame("ifinbox");
							element2=wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Reset Password for Wiley.com')]")));

							if(element2.isDisplayed()==true)
							{
								flag=1;
								element2.click();
								driver.switchTo().defaultContent();
								break;
							}

						} catch (Exception e) {
							driver.switchTo().defaultContent();        
							element1.click();
						}
					}

					if(flag==1)
					{
						driver.switchTo().frame("ifmail");
						driver.findElement(By.xpath("//a[text()='Reset Password']")).click();
						driver.switchTo().defaultContent();


						Set<String> allWindowHandles = driver.getWindowHandles();
						java.util.Iterator<String> iterator = allWindowHandles.iterator();

						String yopmailHandle = iterator.next();
						String ChildWindow=iterator.next();
						driver.switchTo().window(ChildWindow);
						wiley.enterNewPasswordFieldInResetPasswordPage(excelOperation.getTestData("TC33", "WILEY_NA_Cart_Test_Data", "Password"));
						wiley.enterConfirmPasswordFieldInResetPasswordPage(excelOperation.getTestData("TC33", "WILEY_NA_Cart_Test_Data", "Password"));
						wiley.clickOnSubmitButtonInResetPasswordPage();
						wiley.enterExistingWileyUserMailID(excelOperation.getTestData("TC33", "WILEY_NA_Cart_Test_Data", "Email_Id"));
						wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC33", "WILEY_NA_Cart_Test_Data", "Password"));
						wiley.clickOnLogInAndContinueButton();
						if(wiley.checkBreadCrumbCartPage())
							Reporting.updateTestReport("User was on the cart apge and the login was successfull with new password", 
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						else 
							Reporting.updateTestReport("User was not on the cart apge and the login was not successfull with new password", 
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);

						driver.switchTo().window(yopmailHandle);
						driver.close();
						driver.switchTo().window(ChildWindow);
					}
					else {
						Reporting.updateTestReport("No reset password mail was recieved in yopmail inbox", 
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}


				}
				else {
					Reporting.updateTestReport("As reset password instructions was not displayed, yopmail was not opened", 
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}


			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}



	/*
	 * @Author: Anindita
	 * @Description: Validates the login page functionalities (Error message)
	 * @Date: 17/11/22
	 */
	@Test
	public void TC26_Login_Page_Validation() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC26_Login_Page_Validation");
			driver.get(wiley.wileyURLConcatenation("TC26", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.clickOnAddToCartButton();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			try {
				wait.until(ExpectedConditions.
						elementToBeClickable
						(By.xpath("//button[contains(text(),'View Cart')]")));
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				if(wiley.checkIfGuestCheckoutButtonIsPresent()) Reporting.updateTestReport("Guest checkout button is present in login page when only normal physical product is present in cart",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else Reporting.updateTestReport("Guest checkout button is not present in login page when only normal physical product is present in cart",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				wiley.clickOnCartIcon();
				wiley.searchDataInSearchBar(excelOperation.getTestData("TC26", "WILEY_NA_Cart_Test_Data", "ISBN"));
				wiley.clickOnSRP_WileyProduct();
				wiley.clickOnAddToCartButton();
				wiley.clickOnViewCartButton();
				ScrollingWebPage.PageScrolldown(driver,0,700,SS_path);
				wiley.clickOnProceedToCheckoutButton();
				if(!wiley.checkIfGuestCheckoutButtonIsPresent()) Reporting.updateTestReport("Guest checkout button was not present in login page when digital product is present in cart",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else Reporting.updateTestReport("Guest checkout button is present in login page when digital product is present in cart",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				wiley.enterEmailIdInCreateAccountFormNotAutoGenerated(excelOperation.getTestData("TC26", "WILEY_NA_Cart_Test_Data", "Email_Id"));
				wiley.clickOnCreateAccountButton();
				wiley.checkErrorMessageAfterEnteringExistingUserInCreateAccount();
				wiley.enterExistingWileyUserMailID(excelOperation.getTestData("TC26", "WILEY_NA_Cart_Test_Data", "Email_Id"));
				wiley.enterExistingWileyUserPassword(excelOperation.getTestData("TC26", "WILEY_NA_Cart_Test_Data", "Password"));
				wiley.clickOnLogInAndContinueButton();
				wiley.checkErrorMessageAfterEnteringWrongPassword();

			}
			catch(Exception e) {
				Reporting.updateTestReport("View Cart button was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
			driver.manage().deleteAllCookies();

		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * Author: Anindita
	 * @Description: Validates the AEM Header Footer
	 * @Date: 17/11/22
	 */
	@Test
	public void TC27_Validate_AEM_Header_Footer() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC27_Validate_AEM_Header_Footer");
			driver.get(wiley.wileyURLConcatenation("TC27", "WILEY_NA_Cart_Test_Data", "URL"));
			wiley.checkShopLinkInHomePageHeader();
			wiley.checkResearchLibrariesLinkInHomePageHeader();
			wiley.checkPublishingServicesLinkInHomePageHeader();
			wiley.checkProfessionalDevelopmentLinkInHomePageHeader();
			wiley.checkCartIconHomePage();
			wiley.checkSiteMaponfooter();
			wiley.checkPrivacypolicyOnFooter();
			wiley.checkTermsofuseOnFooter();
			wiley.checkRighrtAndPermissonsOnFooter();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Vishnu
	 * 
	 * @Description: Validates VETCONSULT_Purchase_Option_on_PDPpage
	 * 
	 * @Date: 23/11/22
	 */
	@Test
	public void TC34_Verify_VETCONSULT_Purchase_Option_on_PDPpage() throws IOException {
		try {
			Reporting.test = Reporting.extent.createTest("TC34_Verify_VETCONSULT_Purchase_Option_on_PDPpage");
			driver.get(wiley.wileyURLConcatenation("TC34", "WILEY_NA_Cart_Test_Data", "URL"));
			driver.navigate().refresh();
			wiley.Entertextonsearcbar(excelOperation.getTestData("TC34", "WILEY_NA_Cart_Test_Data", "SearchBox_Text"));
			wiley.clickOnFormatFacet();
			if(wiley.checkVetConsultInFormatFacet()) {
				wiley.clickOnVetConsultInFormatFacet();
				wiley.checkPriceInSRPForVetConsult();
				wiley.VetCosultPurchaseOption();
				wiley.checkPriceInPDPForVetConsult();
				wiley.ClikingOnVETSoultIconOnPDPPage();
				try {
					Set<String> allWindowHandles = driver.getWindowHandles();
					java.util.Iterator<String> iterator = allWindowHandles.iterator();

					String storefront = iterator.next();
					String ChildWindow=iterator.next();
					driver.switchTo().window(ChildWindow);
					String title=driver.getTitle();
					if(title.equalsIgnoreCase("VetConsult"))
						Reporting.updateTestReport("VET Consult Page is displayed", 
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport("Failed to Load Vet Consult page",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					driver.switchTo().window(storefront);
					driver.close();
					driver.switchTo().window(ChildWindow);
				}
				catch(Exception e) {
					Reporting.updateTestReport("One new window was not opened after clicking on vetconsult", 
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
			}
			else
				Reporting.updateTestReport("VET Consult was not present under format",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
			wiley.WileyLogOut();

		} catch (Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 6/1/23
	 * @Author: Anindita
	 * @Description: Validates if it is redirecting to secure.wiley.com link after clicking 
	 * on Request Digital Evaluation copy link for certain regions
	 */
	@Test
	public void TC35_EMEA_Catalog_Updates() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC35_EMEA_Catalog_Updates");
			String[] regions=excelOperation.getTestData("TC35", "WILEY_NA_Cart_Test_Data", "URL").split(",");
			for (String region:regions) {
				driver.get(wiley.wileyURLConcatenationwithRegions(region,
						excelOperation.getTestData("TC35", "WILEY_NA_Cart_Test_Data", "ISBN")));
				driver.navigate().refresh();
				wiley.clickOnRequestDigitalEvaluationCopyLink();
				Set<String> allWindowHandles = driver.getWindowHandles();
				java.util.Iterator<String> iterator = allWindowHandles.iterator();
				String compCopyWindow = iterator.next();
				String ChildWindow=iterator.next();
				driver.switchTo().window(compCopyWindow);
				driver.close();
				driver.switchTo().window(ChildWindow);
				if(driver.getCurrentUrl().contains(excelOperation.getTestData("TC35", "WILEY_NA_Cart_Test_Data", "Expected_Result")) && 
						driver.getCurrentUrl().contains(excelOperation.getTestData("TC35", "WILEY_NA_Cart_Test_Data", "ISBN")))
					Reporting.updateTestReport("User was redirected to : "+driver.getCurrentUrl()+" successfully, for "
							+region+" region.", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				else
					Reporting.updateTestReport("User was redirected to : "+driver.getCurrentUrl()+", for "
							+region+" region.", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			wiley.WileyLogOut();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Date: 6/1/23
	 * @Author: Anindita
	 * @Description: Validates if it is redirecting to new Onboarding link after clicking 
	 * on Request Digital Evaluation copy link for US and CA regions
	 */
	@Test
	public void TC36_Change_of_Hyperlink_for_comp_access_on_US_CA_wiley_PDP() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC36_Change_of_Hyperlink_for_comp_access_on_US_CA_wiley_PDP");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			String[] regions=excelOperation.getTestData("TC36", "WILEY_NA_Cart_Test_Data", "URL").split(",");
			String[] products=excelOperation.getTestData("TC36", "WILEY_NA_Cart_Test_Data", "ISBN").split(",");
			for (String region:regions) {
				for(String product:products) {
					driver.get(wiley.wileyURLConcatenationwithRegions(region,
							product));
					driver.navigate().refresh();
					wiley.clickOnPrintTab();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Request Digital Evaluation Copy')]")));
						if(wiley.fetchHyperlinkForRequestDigitalEvaluationCopy()
								.equalsIgnoreCase(excelOperation.getTestData("TC36", "WILEY_NA_Cart_Test_Data", "Expected_Result").split(",")[0]
										+product))
							Reporting.updateTestReport("The hyperlink was: "+
									excelOperation.getTestData("TC36", "WILEY_NA_Cart_Test_Data", "Expected_URL")+product+", for "
									+region+" region as expected.", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						else
							Reporting.updateTestReport("The hyperlink was not as expoected",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						wiley.clickOnRequestDigitalEvaluationCopyLink();
						Set<String> allWindowHandles = driver.getWindowHandles();
						java.util.Iterator<String> iterator = allWindowHandles.iterator();
						String compCopyWindow = iterator.next();
						String ChildWindow=iterator.next();
						driver.switchTo().window(compCopyWindow);
						driver.close();
						driver.switchTo().window(ChildWindow);
						if(driver.getCurrentUrl().equalsIgnoreCase(excelOperation.getTestData("TC36", "WILEY_NA_Cart_Test_Data", "Expected_Result")
								.split(",")[1]+product.substring(product.length()-13)))
							Reporting.updateTestReport("User was redirected to : "+driver.getCurrentUrl()+" successfully, for "
									+region+" region.", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
						else
							Reporting.updateTestReport("User was redirected to : "+driver.getCurrentUrl()+", for "
									+region+" region, so it is a non WileyReader product.", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.INFO);
					}
					catch(Exception e) {
						Reporting.updateTestReport("Request Digital Evaluation Copy link was not clickable"
								+ "and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
			}

			wiley.WileyLogOut();
		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

	/*
	 * @Author: Anindita
	 * @Date: 6/1/23
	 * @Description: Validates the updated hover text for E-Books in Product details page
	 */
	@Test
	public void TC37_Generic_info_hover_text_EBook_PDP() throws IOException{
		try {
			Reporting.test = Reporting.extent.createTest("TC37_Generic_info_hover_text_EBook_PDP");
			String[] regions=excelOperation.getTestData("TC37", "WILEY_NA_Cart_Test_Data", "URL").split(",");
			String[] products=excelOperation.getTestData("TC37", "WILEY_NA_Cart_Test_Data", "ISBN").split(",");
			for(String region:regions) {
				for(String product:products) {
					driver.get(wiley.wileyURLConcatenationwithRegions(region,product));
					driver.navigate().refresh();
					if(wiley.fetchGenericHoverInfo(driver).
							contains(excelOperation.getTestData("TC37", "WILEY_NA_Cart_Test_Data", "Expected_Result").trim()))
						Reporting.updateTestReport("Correct text was present in generic info for eBook",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
					else
						Reporting.updateTestReport("Correct text was not present in generic info for eBook"
								+wiley.fetchGenericHoverInfo(driver).length()+" "+
								excelOperation.getTestData("TC37", "WILEY_NA_Cart_Test_Data", "Expected_Result").trim().length(),
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);


				}
			}
			wiley.WileyLogOut();

		}
		catch(Exception e) {
			wiley.wileyLogOutException();
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getClass().toString(),
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}

}