package utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;

import Test_Suite.ClientPortal_RegressionSuite;

public class dbOperation {
	
	public static String SS_path=ClientPortal_RegressionSuite.SS_path;
	public static String DB_Select(String DBName,String Query,String Parameter) throws InstantiationException, IllegalAccessException
    
	{
        String connectionUrl = "jdbc:mysql://wps.c7hepfyiogmn.us-east-1.rds.amazonaws.com:3306/"+DBName;
        //String connectionUrl = "jdbc:mysql://wps-perf.c7hepfyiogmn.us-east-1.rds.amazonaws.com:3306/"+DBName;
        System.out.println(connectionUrl);
        String columnValue = "";
        try {
        	String userid = excelOperation.getTestData("DBQAUserID", "Generic_Dataset", "Data");
            String pwd = excelOperation.getTestData("DBQAPassword", "Generic_Dataset", "Data");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(connectionUrl,userid,pwd);
            Statement stmt= con.createStatement();
            //String SQL = "SELECT * FROM "+DBName+"."+TableName+" order by "+ColumnName2+" desc limit 1";
            //System.out.println(SQL);
            
            ResultSet rs = stmt.executeQuery(Query);
           
            // Iterate through the data in the result set and display it.
            if (Parameter.equalsIgnoreCase("Int"))
            {
            	while (rs.next())
            	{
                Integer Col=null;
        	    System.out.println(rs.getInt(1));
        	    Col = rs.getInt(1);
        	    columnValue=Col.toString();
            	}
              
            }
            
            else if (Parameter.equalsIgnoreCase("VarChar"))
            {
            	while (rs.next())
            	{
            	System.out.println(rs.getString(1));
        	    columnValue = rs.getString(1);
            	}
            }
            
		  con.close();
            }

        // Handle any errors that may have occurred.
          catch (SQLException | ClassNotFoundException e) 
                  {
        	         Reporting.updateTestReport("Exception occured: " + e.getMessage(),
						         "", StatusDetails.WARNING);
			         System.out.println(e.getMessage());
                  }
        return columnValue;
	}
	
	/* 
	 * Author : Jayanta
	 * Description :  Method to check Promote to prod status
	 */

	public static void appPromotionStatus(WebDriver driver,String DBName,String query,String datatype,String SS_Path) throws IOException {
		try {
			
			String expectedstatusid=null;
			int i=0;
			do 
			{
				expectedstatusid=dbOperation.DB_Select(DBName,query,datatype);
				System.out.println(expectedstatusid);
				if(i%300==0)
				{
					ScrollingWebPage.PageDown(driver, SS_Path);
					ScrollingWebPage.PageUp(driver, SS_Path);
				}
				i++;
			}
			while ((Integer.parseInt(expectedstatusid)!=4)&&(Integer.parseInt(expectedstatusid)!=5));
			
			if(expectedstatusid.equalsIgnoreCase(excelOperation.getTestData("Promoted", "Generic_Dataset", "Data")))
		       {
			
			      Reporting.updateTestReport("DB Validation is done, app is promoted to prod and Promotion Status ID: " + expectedstatusid,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		       }
		      else if(expectedstatusid.equalsIgnoreCase(excelOperation.getTestData("Promotion_Failed", "Generic_Dataset", "Data")))
		       {
			      Reporting.updateTestReport("Promotion is failed and Promotion Status ID: " + expectedstatusid,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		       }
		}
		catch(Exception e){
			Reporting.updateTestReport("Promote to prod not working : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	 }

	/* 
	 * Author : Jayanta
	 * Description :  Method to check Promotion rollback status
	 */

	public static void appPromotionRollbackStatus(WebDriver driver,String DBName,String query,String datatype,String SS_Path) throws IOException {
		try {
			String expectedstatusid="";
			do 
			{
			expectedstatusid=dbOperation.DB_Select(DBName,query,datatype);
			System.out.println(expectedstatusid);
			}
			while ((Integer.parseInt(expectedstatusid)!=6));
			if(expectedstatusid.equalsIgnoreCase(excelOperation.getTestData("Promotion_Rollback", "Generic_Dataset", "Data")))
		       {
			
			      Reporting.updateTestReport("DB Validation is done, Promotion is reverted and Promotion Status ID: " + expectedstatusid,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
		       }
		      else if(expectedstatusid.equalsIgnoreCase(excelOperation.getTestData("Promotion_Failed", "Generic_Dataset", "Data")))
		       {
			      Reporting.updateTestReport("Promotion is not reverted and Promotion Status ID: " + expectedstatusid,
					     CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		       }
		}
		catch(Exception e){
			Reporting.updateTestReport("Promotion rollback not working : "+e.getClass().toString(),CaptureScreenshot.getScreenshot(""), StatusDetails.FAIL);
		}
	 }

}
