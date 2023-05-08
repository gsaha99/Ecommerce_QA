package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.SystemUtils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class LogTextFile {
	public static BufferedWriter wr;
	/*
	 * @Description: Creates the log file
	 */
	public static String createTodayLogFolder() {
		try {
			String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date());
			String path = System.getProperty("user.dir")+"\\Log\\"+dateName;
			File folder = new File(path);

			if (!folder.exists()) {
				folder.mkdir();
			}
			return path;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	
	/*
	 * @Description: Creates the log file
	 */
	public static void createTodayLog(String suiteName,String browserName,String browserVersion,String OS_Name) {
		try {
			String path=createTodayLogFolder();
			FileWriter file=new FileWriter(path+"\\"+suiteName+"_Log.txt",true);
			wr=new BufferedWriter(file);
			String Hostname = SystemUtils.getHostName();
			String Username = SystemUtils.getUserName();
			String OS_Version = SystemUtils.OS_VERSION.toString();			
			wr.write("System Specifications:-");
			wr.newLine();
			wr.newLine();
			wr.write("OS: "+ OS_Name);
			wr.newLine();
			wr.write("OS Version: "+ OS_Version);
			wr.newLine();
			wr.write("HostName: "+ Hostname);
			wr.newLine();
			wr.write("IP Address: "+ InetAddress.getByName(Hostname).getHostAddress());
			wr.newLine();
			wr.write("UserName: "+ Username);
			wr.newLine();
			wr.write("Browser: "+ browserName);
			wr.newLine();
			wr.write("Browser Version: "+ browserVersion);
			wr.newLine();
			wr.newLine();
			wr.write(suiteName+" Regression Suite: -");
			wr.newLine();
			wr.flush();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	/*
	 * @Description: It writes the pass/ fail status in the log text file
	 */
	public static void writeTestCaseStatus(String TCName,String status) {
		try {
			
			if(status.equalsIgnoreCase("Test case")) {
				wr.newLine();
				wr.newLine();
				wr.write(TCName);
				wr.write(" --> Execution started");
				wr.newLine();
			}
			else  {
				wr.write(TCName);
				wr.write(" --> "+status);
			}
				
			wr.newLine();
			wr.flush();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/*
	 * @Description: Closes the log file
	 */
	public static void closeLogFile() {
		try {
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
