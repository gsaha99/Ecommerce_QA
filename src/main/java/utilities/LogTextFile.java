package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	public static void createTodayLog(String suiteName) {
		try {
			String path=createTodayLogFolder();
			FileWriter file=new FileWriter(path+"\\"+suiteName+"_Log.txt",true);
			wr=new BufferedWriter(file);
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
			if(status.equalsIgnoreCase("fail")) {
				wr.write(TCName);
				wr.write(" --> FAIL");
			}
			else if(status.equalsIgnoreCase("pass")) {
				wr.write(TCName);
				wr.write(" --> PASS");
			}
			else {
				wr.newLine();
				wr.newLine();
				wr.write(TCName);
				wr.write(" --> Execution started");
				wr.newLine();
				
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
