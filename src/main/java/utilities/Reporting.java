package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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


	public static String CreateTodayReportFolder()
	{
		String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String path = System.getProperty("user.dir")+"\\Result\\"+dateName;
		File folder = new File(path);

		if (!folder.exists()) {
			folder.mkdir();
		}
		return path;
	}

	
	public static String CreateExecutionScreenshotFolder(String startTime)
	{ 
		//String startTime =new SimpleDateFormat("hhmmss").format(new Date()); 
	   // String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date()); 
		String path=CreateTodayReportFolder();
	    String SS_path =path+"\\"+startTime; 
	    File screenshotfolder=new File(SS_path); 
	    screenshotfolder.mkdir(); 
	    return SS_path;

	}
	 
	public static void summaryReportdesign(String filename)
	{
		
		String SummaryReportPath=CreateTodayReportFolder();
		//new added code ends on 21/06/22
		report = new ExtentSparkReporter(SummaryReportPath+"\\"+filename+".html"); //modified line of code on 21/06/22

		//initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(report);


		extent.setSystemInfo("OS", "Windows10");	 
		extent.setSystemInfo("username", "ECommerce QA");
		extent.setSystemInfo("Browser", "Chrome");

		//configuration items to change the look and feel
		//add content, manage tests etc

		report.config().setDocumentTitle("Automation Report");
		report.config().setReportName("WPS Regression Automation Report");
		report.config().setTheme(Theme.STANDARD);
		report.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");	

	}


	public static void updateTestReport(String ObjectName,String SS_path, StatusDetails st){

		try{
			
			switch(st){

			case FAIL :			
				//test.log(Status.FAIL, MarkupHelper.createLabel(ObjectName,ExtentColor.RED ));
				test.log(Status.FAIL, ObjectName,MediaEntityBuilder.createScreenCaptureFromPath(SS_path).build());
				break;

			case PASS :
				//test.log(Status.PASS, MarkupHelper.createLabel(ObjectName,ExtentColor.GREEN ));	
				test.log(Status.PASS,ObjectName,MediaEntityBuilder.createScreenCaptureFromPath(SS_path).build());
							
				break;

			case WARNING :
				test.log(Status.WARNING, ObjectName,MediaEntityBuilder.createScreenCaptureFromPath(SS_path).build());
				break;
				
			case INFO :
				test.log(Status.INFO, ObjectName,MediaEntityBuilder.createScreenCaptureFromPath(SS_path).build());
				break;	

			case ERROR :
				test.log(Status.SKIP, MarkupHelper.createLabel(ObjectName,ExtentColor.GREY ));
				break;
			default :
				test.log(Status.INFO,MarkupHelper.createLabel(ObjectName,ExtentColor.BLUE ));
			}
		}catch(Exception e){ System.out.println(e.getMessage()); }

	}	
	public static void summaryEndReport() {
		extent.flush();
	}

}


