package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.SystemUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.support.Colors;


public class WordDocumentReport {
	
	private static XWPFDocument documentReport=new XWPFDocument();
	private static XWPFParagraph paragraph=documentReport.createParagraph();
	private static XWPFRun run=paragraph.createRun();
	private static FileOutputStream fos;
	
	/*
	 * @Description: Creates a word document report
	 */
	public static void createWordDocumentReport(String suiteName,String browserName,String browserVersion,String OS_Name) {
		try {
			String path=Reporting.CreateTodayReportFolder();
			String startTime = new SimpleDateFormat("MMDDhhmmss").format(new Date());
			
			fos=new FileOutputStream(path+"\\"+suiteName+"_"+startTime+"_SummaryReport.docx");
			
			String Hostname = SystemUtils.getHostName();
			String Username = SystemUtils.getUserName();
			String OS_Version = SystemUtils.OS_VERSION.toString();	
			
			//Adding lines
			run.setText("System Specifications:-");
			run.addCarriageReturn();
			run.addCarriageReturn();
			run.setText("OS: "+ OS_Name);
			run.addCarriageReturn();
			run.setText("OS Version: "+ OS_Version);
			run.addCarriageReturn();
			run.setText("HostName: "+ Hostname);
			run.addCarriageReturn();
			run.setText("IP Address: "+ InetAddress.getByName(Hostname).getHostAddress());
			run.addCarriageReturn();
			run.setText("UserName: "+ Username);
			run.addCarriageReturn();
			run.setText("Browser: "+ browserName);
			run.addCarriageReturn();
			run.setText("Browser Version: "+ browserVersion);
			run.addCarriageReturn();
			run.addCarriageReturn();
			run.setText(suiteName+" Regression Suite: -");
			run.addCarriageReturn();
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * @Description: It writes the pass/ fail status in the word document file
	 */
	public static void writeTestCaseStatus(String TCName,String status,String SS_path) {
		try {
			
				FileInputStream imageData=new FileInputStream(SS_path);
				int imageType=XWPFDocument.PICTURE_TYPE_PNG;
				//String imageFileName=SS_path.split("/")[-1];
				int imageWidth=500;
				int imageHeight = 200;
				run.addPicture(imageData, imageType, SS_path, Units.toEMU(imageWidth), Units.toEMU(imageHeight));
				run.addCarriageReturn();
				run.setText(TCName);
				run.setText("--> "+status);
				run.addCarriageReturn();
				run.setText("--------------------------------------------------------------------");
				run.addCarriageReturn();
				run.addCarriageReturn();
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/*
	 * @Description: Indicates the testcase starting and ending
	 */
	public static void writeTestcaseName(String testCaseName) {
		try {
			run.addCarriageReturn();
			run.addCarriageReturn();
			run.setText(testCaseName);
			run.setBold(true);
			run.setText(" --> Execution started");
			run.addCarriageReturn();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * @Description: Closes the word file
	 */
	public static void closeWordFile() {
		try {
			documentReport.write(fos);
			fos.close();
			documentReport.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	

}
