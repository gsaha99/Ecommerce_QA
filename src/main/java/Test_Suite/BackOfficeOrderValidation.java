package Test_Suite;

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
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import PageObjectRepo.app_Hybris_BO_Repo;
import PageObjectRepo.app_Wiley_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.LogTextFile;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class BackOfficeOrderValidation  extends DriverModule{

	app_Hybris_BO_Repo HybrisBO;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);

	@BeforeTest
	public void launchBrowser() {
		HybrisBO = PageFactory.initElements(driver, app_Hybris_BO_Repo.class);
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
	 * @Description: Checks the Order details of one test case in backoffice
	 */
	
	public void Check_Order_Details_In_Backoffice(@Optional("TC01") String tcNo,@Optional("WILEY_NA_Cart_Test_Data") String sheet) throws IOException {
		try {
			String suite=sheet.split("_")[0];
			Reporting.test = Reporting.extent.createTest("Check_Order_Details_In_Backoffice_For_"+tcNo+"_"+suite);
			LogTextFile.writeTestCaseStatus("Check_Order_Details_In_Backoffice_For_"+tcNo+"_"+suite, "Test case");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
			driver.get(excelOperation.getTestData("Backoffice_URL", "Generic_Dataset", "Data"));
			HybrisBO.enterHybrisBOUserName(excelOperation.getTestData("Backoffice_Admin_User_ID", "Generic_Dataset", "Data"));
			HybrisBO.enterHybrisBOPassword(excelOperation.getTestData("Backoffice_Admin_Password", "Generic_Dataset", "Data"));
			HybrisBO.selectBOLanguage();
			HybrisBO.clickOnHybrisBOLoginButton();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Logout']/span/img")));
				HybrisBO.checkIfUserLoggedInHybrisBO();
				HybrisBO.searchFieldInHybrisBO("orders");
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@title='Orders']/td/div/div/span")));
					HybrisBO.clickOnOrders();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Switch search mode']")));
						HybrisBO.clickOnSwitchSearchMode();
						HybrisBO.enterValueInAdvancedSearch(excelOperation.getTestData(tcNo, sheet, "Order_Id"));
						HybrisBO.clickOnSearchButtonInAdvanncedSearch();
						ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
									"//span[@class='yw-listview-cell-label z-label' and contains(text(),'"+
											excelOperation.getTestData(tcNo, sheet, "Order_Id")+"')]")));
							HybrisBO.clickOnFirstSearchResult();
							HybrisBO.clickTopArrowButtonForExpand();
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated
										(By.xpath("//div[@class='z-caption-content' and contains(text(),'Common')]")));
								HybrisBO.clickOnUserSectionInOrderDetails(driver);
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated
											(By.xpath("//div[@class='z-caption-content' and contains(text(),'Edit item')]")));
									HybrisBO.checkFirstNameInOrderDetails(excelOperation.getTestData(tcNo, sheet, "First_Name"));
									HybrisBO.checkLastNameInOrderDetails(excelOperation.getTestData(tcNo, sheet, "Last_Name"));
									HybrisBO.checkUserIdInOrderDetails(excelOperation.getTestData(tcNo, sheet, "Email_Id"));
									HybrisBO.clickOnCloseBackofficePopUp();
									HybrisBO.clickOnPositionAndPricesTab();
									try {
										wait.until(ExpectedConditions.visibilityOfElementLocated
												(By.xpath("(//div[@class='z-caption-content' and contains(text(),'Common')])[2]")));
										HybrisBO.clickOnSideScrollBarAndScrollDown(driver);
										HybrisBO.checkTotalTaxInOrderDetails(excelOperation.getTestData(tcNo, sheet, "Tax"));
										HybrisBO.checkShippingCostInOrderDetails(excelOperation.getTestData(tcNo, sheet, "Shipping_Charge"));										
										HybrisBO.clickOnPaymentAndDeliveryTab();
										try{
											wait.until(ExpectedConditions.visibilityOfElementLocated
													(By.xpath("//div[@class='z-caption-content' and contains(text(),'Status')]")));
											HybrisBO.clickOnSideScrollBarAndScrollDown(driver);
											HybrisBO.clickOnPaymentAddressSectionInOrderDetails(driver);
											try {
												wait.until(ExpectedConditions.visibilityOfElementLocated
														(By.xpath("//div[@class='z-caption-content' and contains(text(),'Edit item')]")));
												ScrollingWebPage.PageScrolldown(driver, 0, 900, SS_path);
												HybrisBO.checkStreetNameInOrderDetails(excelOperation.getTestData(tcNo, sheet, "Bill_Address_line1"));
												HybrisBO.checkPostalCodeInOrderDetails(excelOperation.getTestData(tcNo, sheet, "Bill_Zip_Code"));
												HybrisBO.checkTownInOrderDetails(excelOperation.getTestData(tcNo, sheet, "Bill_City"));
												HybrisBO.checkCountryInOrderDetails(excelOperation.getTestData(tcNo, sheet, "Bill_Country"));
												HybrisBO.clickOnCloseBackofficePopUp();
												HybrisBO.clickOnRightScrollIcon();
												try {
													wait.until(ExpectedConditions.elementToBeClickable
															(By.xpath("//li[@title='Administration']/span/div/div/span")));
													HybrisBO.clickOnAdministrationTab();
													try {
														wait.until(ExpectedConditions.visibilityOfElementLocated
																(By.xpath("//div[@class='z-caption-content' and contains(text(),'Metadata')]")));
														HybrisBO.clickOnWileyOrderProcessSectionInOrderDetails(driver);
														try {
															wait.until(ExpectedConditions.visibilityOfElementLocated
																	(By.xpath("//div[@class='z-caption-content' and contains(text(),'Edit item')]")));
															HybrisBO.clickOnFirstOrderProcessStep(driver);
															try {
																wait.until(ExpectedConditions.visibilityOfElementLocated
																		(By.xpath("(//div[@class='z-caption-content' and contains(text(),'Edit item')])[2]")));
																HybrisBO.checkWileyOrderProcess(driver);

															}
															catch(Exception e) {
																Reporting.updateTestReport("Wiley order process steps couldn't be opened and caused timeout exception"
																		, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
															}
														}
														catch(Exception e) {
															Reporting.updateTestReport("Wileyorder process section couldn't be opened and caused timeout exception"
																	, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
														}
													}
													catch(Exception e) {
														Reporting.updateTestReport("The Administration Tab was not opened and caused timeout exception",
																CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
													}
												}
												catch(Exception e) {
													Reporting.updateTestReport("The Administration Tab was was not clickable and caused timeout exception",
															CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
												}
											}
											catch(Exception e){
												Reporting.updateTestReport("Address section couldn't be opened and caused timeout exception"
														, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
											}
										}
										catch(Exception e) {
											Reporting.updateTestReport("The Payment And Delivery tab was not loaded and caused timeout exception"
													+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
										}
									}
									catch(Exception e) {
										Reporting.updateTestReport("The positions and prices tab was not loaded and caused timeout exception"
												+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}	

								}
								catch(Exception e) {
									Reporting.updateTestReport("User section couldn't be opened and caused timeout exception"
											, CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}

							}
							catch(Exception e) {
								Reporting.updateTestReport("Order detaisl was not expanded after clciking top arrow button"
										+ " and caused timeout exception", CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("No search result was found for the order id: "+
									excelOperation.getTestData(tcNo, sheet, "Order_Id"), CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("The Search mode switch button was not clickable and caused timeout exception",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}
				}
				catch(Exception e) {
					Reporting.updateTestReport("The Orders option in left pane was not clickable and caused timeout exception",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}
			catch(Exception e) {
				Reporting.updateTestReport("The logout button in Hybris BO homepage after login was not clickable and caused timeout exception",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			//HybrisBO.clickOnLogOutButton();
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
		}
		catch(Exception e) {
			//HybrisBO.clickOnLogOutButton();
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), 
					CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

	/*
	 * @Description: Checks the order process for all the Wiley Orders
	 */
	@Test
	public void checkOrderForWiley() throws IOException{
		try {
			String j;
			String orderId;
			for(int i=1;i<=10;i++) {
				j=Integer.toString(i);
				if(i<10) { 
					j="0"+Integer.toString(i);
					System.out.println(j);
				}
				orderId=excelOperation.getTestData("TC"+j, "WILEY_NA_Cart_Test_Data", "Order_Id");
				if(!orderId.equalsIgnoreCase(""))
					Check_Order_Details_In_Backoffice("TC"+j,"WILEY_NA_Cart_Test_Data");
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("Exception occured: "+e.getClass().toString(), 
					CaptureScreenshot.getScreenshot(SS_path),StatusDetails.FAIL);
		}
	}

}
