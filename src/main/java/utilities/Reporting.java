package utilities;

import java.io.File;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.JavascriptExecutor;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.observer.entity.MediaEntity;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting {

	public static ExtentSparkReporter report ;
	public static ExtentTest test;
	public static ExtentReports extent;

	private static int FlagBS=0;
	private static String wordDocPath;
	
	public static String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date());
	
	
	
	public static String CreateTodayReportFolder()
	{
		
		String path = System.getProperty("user.dir")+"\\Result\\"+dateName;
		File folder = new File(path);

		if (!folder.exists()) {
			folder.mkdir();
		}
		return path;
	}

	
	public static String CreateExecutionScreenshotFolder(String startTime)
	{ 
			 String path=CreateTodayReportFolder();
		
			 String SS_path =path+"\\"+startTime; 
	      	    
			 File screenshotfolder=new File(SS_path); 
			 screenshotfolder.mkdir(); 
	    	   
			 wordDocPath= WordScrrenshotLocation(startTime);
			
			return SS_path;
	}
	
	public static String WordScrrenshotLocation(String ss)
	{
		String path1=CreateTodayReportFolder();
		
		String Word_SS_Path =path1+"\\"+ss+"_WordSS";
	    
	    File Word_screenshotfolder=new File(Word_SS_Path); 
	    Word_screenshotfolder.mkdir();	
	    
	    return Word_SS_Path;
	}
	 
	public static void summaryReportdesign(String filename,String BrowserName,String BrowserVersion,String OS_Name,String OS_Version) 
	{
		
		try {
			String SummaryReportPath=CreateTodayReportFolder();
			//new added code ends on 21/06/22
			report = new ExtentSparkReporter(SummaryReportPath+"\\"+filename+".html"); //modified line of code on 21/06/22

			
			String Hostname = SystemUtils.getHostName();
			String Username = SystemUtils.getUserName();			
							
			//initialize ExtentReports and attach the HtmlReporter
			extent = new ExtentReports();
			extent.attachReporter(report);

			extent.setSystemInfo("OS", OS_Name);
			extent.setSystemInfo("OS Version", OS_Version);
			extent.setSystemInfo("HostName", Hostname);
			extent.setSystemInfo("IP Address", InetAddress.getByName(Hostname).getHostAddress());
			extent.setSystemInfo("UserName", Username);
			extent.setSystemInfo("Browser", BrowserName);
			extent.setSystemInfo("Browser Version", BrowserVersion);
			extent.setSystemInfo("Browser Mode","Normal");

			//configuration items to change the look and feel
			//add content, manage tests etc

			report.config().setDocumentTitle("Automation Report");
			report.config().setReportName("WPS Automation Report");
			report.config().setTheme(Theme.STANDARD);
			report.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
			
		} catch (Exception e) {
			System.out.println("Unexcepted exception in Summary Report design Method"+ " "+e.getMessage());
		}	

	}

	public static void updateTestReport(String ObjectName,String SS_path, StatusDetails st){

		try{
			String startTime1 = new SimpleDateFormat("MMDDhhmmss").format(new Date());
			String wordPath = wordDocPath +"\\"+startTime1+".png";
						
			Path src = Paths.get(SS_path);
			Path dest = Paths.get(wordPath); 
			Files.copy(src, dest);
			

			switch(st){

			case FAIL :			
				
				test.log(Status.FAIL, ObjectName,MediaEntityBuilder.createScreenCaptureFromPath(SS_path).build());
				LogTextFile.writeTestCaseStatus(ObjectName, st.toString());				
				WordDocumentReport.writeTestCaseStatus(ObjectName, st.toString(), wordPath);
				FlagBS=1;
				break;

			case PASS :
					
				test.log(Status.PASS,ObjectName,MediaEntityBuilder.createScreenCaptureFromPath(SS_path).build());
				LogTextFile.writeTestCaseStatus(ObjectName, st.toString());
				WordDocumentReport.writeTestCaseStatus(ObjectName, st.toString(), wordPath);
							
				break;
				
			case INFO :
				test.log(Status.INFO, ObjectName,MediaEntityBuilder.createScreenCaptureFromPath(SS_path).build());
				LogTextFile.writeTestCaseStatus(ObjectName, st.toString());
				WordDocumentReport.writeTestCaseStatus(ObjectName, st.toString(), wordPath);
				break;

			case WARNING :
				test.log(Status.WARNING,ObjectName,MediaEntityBuilder.createScreenCaptureFromPath(SS_path).build());
				LogTextFile.writeTestCaseStatus(ObjectName, st.toString());
				WordDocumentReport.writeTestCaseStatus(ObjectName, st.toString(), wordPath);
				break;

			case ERROR :
				test.log(Status.SKIP, MarkupHelper.createLabel(ObjectName,ExtentColor.GREY ));
				LogTextFile.writeTestCaseStatus(ObjectName, st.toString());
				WordDocumentReport.writeTestCaseStatus(ObjectName, st.toString(), wordPath);
				break;
			default :
				test.log(Status.INFO,MarkupHelper.createLabel(ObjectName,ExtentColor.BLUE ));
				LogTextFile.writeTestCaseStatus(ObjectName, st.toString());
				WordDocumentReport.writeTestCaseStatus(ObjectName, st.toString(), wordPath);
			}
		}catch(Exception e){ System.out.println(e.getMessage()); }

	}	
	
	/* Author : Gourab Saha
	 * Description : Javascript is used to update the status in Browser stack
	 */
	
	public static void summaryEndReport() {
	
		
		JavascriptExecutor jse = (JavascriptExecutor) DriverModule.getWebDriver();
		
		if(FlagBS==0)
			 jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"All TCs are Passed\"}}");
		else if (FlagBS==1)
			 jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Kindly check the Report for more info\"}}");
		
		
		extent.flush();
	}
}


