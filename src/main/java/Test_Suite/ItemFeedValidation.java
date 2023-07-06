package Test_Suite;

import static org.testng.Assert.assertEquals;

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
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import PageObjectRepo.Hybris_BO_Repo;
import utilities.CaptureScreenshot;
import utilities.DriverModule;
import utilities.LogTextFile;
import utilities.Reporting;
import utilities.ScrollingWebPage;
import utilities.StatusDetails;
import utilities.excelOperation;

public class ItemFeedValidation extends DriverModule {

	Hybris_BO_Repo HybrisBO;
	public static String startTime = new SimpleDateFormat("hhmmss").format(new Date());
	public static String SS_path = Reporting.CreateExecutionScreenshotFolder(startTime);

	@BeforeTest
	public void launchBrowser() {
		HybrisBO = PageFactory.initElements(driver, Hybris_BO_Repo.class);
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
	 * @Description: Validating the Item feed in Hybris BO in Perf Environment
	 * @Date: 27/06/23
	 */
	@Test
	//	public void TC01_ItemFeedValidation(String tcNo,String sheet) throws IOException {

	public void TC01_ItemFeedValidation(@Optional("TC02") String tcNo,@Optional("Item_Feed_Validation") String sheet) throws IOException {

		try {

			Reporting.test = Reporting.extent.createTest("TC01_ItemFeedValidation");
			LogTextFile.writeTestCaseStatus("TC01_ItemFeedValidation", "Test case");			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));

			driver.get(excelOperation.getTestData("Backoffice_URL_Perf", "StoreFront_CLP", "Data"));
			HybrisBO.enterHybrisBOUserName(excelOperation.getTestData("Backoffice_Admin_User_ID", "StoreFront_CLP", "Data"));
			HybrisBO.enterHybrisBOPassword(excelOperation.getTestData("Backoffice_Admin_Password", "StoreFront_CLP", "Data"));
			HybrisBO.selectBOLanguage();
			HybrisBO.clickOnHybrisBOLoginButton();

			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Logout']/span/img")));
				HybrisBO.checkIfUserLoggedInHybrisBO();
				HybrisBO.searchFieldInHybrisBO("products");
				try {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@title='Products']/td/div/div/span")));
					HybrisBO.clickOnProducts();
					try {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Switch search mode']")));
						HybrisBO.clickOnSwitchSearchMode();
						HybrisBO.enterValueInAdvancedSearch(excelOperation.getTestData(tcNo, sheet, "id"));
						HybrisBO.clickOnSearchButtonInAdvancedSearch();
						ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
						try {
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
									"(//span[@class='yw-listview-cell-label z-label' and contains(text(),'"+
											excelOperation.getTestData(tcNo, sheet, "id")+"')])[2]")));
							HybrisBO.clickOnSecondSearchResult();
							HybrisBO.clickTopArrowButtonForExpand();
							try {
								wait.until(ExpectedConditions.visibilityOfElementLocated
										(By.xpath("//div[@class='z-center-caption']")));
								System.out.println("Item Number is " + HybrisBO.getItemNumber());
								System.out.println("Article Number is " + HybrisBO.getArticleNumber());
								Assert.assertEquals(HybrisBO.getArticleNumber(), HybrisBO.getItemNumber());
								System.out.println("Identifier value is "+ HybrisBO.getIdentifier());
								System.out.println("Item value is "+ HybrisBO.getItemName());
								//								Assert.assertEquals(HybrisBO.getIdentifier(), HybrisBO.getItemName());
								try {
									wait.until(ExpectedConditions.visibilityOfElementLocated
											(By.xpath("//*[contains(text(),'Multimedia')]/../span[@class='yw-editorarea-tablabel z-label']")));
									HybrisBO.clickOnMultimediaTab();
									Thread.sleep(1000);
									//									wait.until(ExpectedConditions.visibilityOfElementLocated
									//											(By.xpath(("//*[@class='yw-expandCollapse z-button'])[8]"))));									
									HybrisBO.clickEssentialArrowButtonForExpand();
									System.out.println("Essential Arrow button clicked");

									try {
										Thread.sleep(3000);

										//									wait.until(ExpectedConditions.visibilityOfElementLocated
										//											(By.xpath("//*[contains(text(),'Additional Multimedia Objects')]")));
										//									ScrollingWebPage.PageDown(driver, SS_path);
										//									ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);
										//									ScrollingWebPage.PageScrollDownUptoBottom(driver, SS_path);

										HybrisBO.doubleClickOnExternalImage();
										System.out.println("double clicked");

										Thread.sleep(3000);
										System.out.println("waited for 3000ms");
										wait.until(ExpectedConditions.visibilityOfElementLocated
												(By.className("yw-editorarea-tabbox-tabs-tab z-tab z-tab-selected")));
										HybrisBO.getMediaURL();
										System.out.println("Media URL "+ HybrisBO.getMediaURL());
									}
									catch (Exception e) {
										Reporting.updateTestReport("Double click on External Image failed",
												CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
									}
								}
								catch (Exception e) {
									Reporting.updateTestReport("Navigation to Multimedia tab failed",
											CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
								}
							}
							catch(Exception e) {
								Reporting.updateTestReport("The Item values and Item names did not match",
										CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
							}
						}
						catch(Exception e) {
							Reporting.updateTestReport("The Item value searched was not clickable",
									CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
						}
					}
					catch(Exception e) {
						Reporting.updateTestReport("The Advanced Search was not accessible to enter the Item Value",
								CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
					}	
				}
				catch(Exception e) {
					Reporting.updateTestReport("Products was not entered in the search bar on the left",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
				}
			}

			catch (Exception e) {
				Reporting.updateTestReport("Login to the Hybris BO was not successful",
						CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		catch (Exception e) {
			Reporting.updateTestReport("Item feed validation failed",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
}