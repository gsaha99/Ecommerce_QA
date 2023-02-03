package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Connect {
	
	
	public void DB_Select() throws InstantiationException, IllegalAccessException
    
	{
        String connectionUrl = "jdbc:mysql://wps.c7hepfyiogmn.us-east1.rds.amazonaws.com:3306/wps_client_app_qa";
        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            String userid = excelOperation.getTestData("DBQAUserID", "Generic_Dataset", "Data");
	            String pwd = excelOperation.getTestData("DBQAPassword", "Generic_Dataset", "Data");
	            
	            Connection con = DriverManager.getConnection(connectionUrl,userid,pwd);
	            Statement stmt= con.createStatement();
	            String SQL = "SELECT * FROM wps_client_app_qa.client_app_status";
	            ResultSet rs = stmt.executeQuery(SQL);
	    
	            // Iterate through the data in the result set and display it.
	         while (rs.next()) 
	              {
	           System.out.println(rs.getInt("status_id") + " " + rs.getString("status"));
	              }
          }

        // Handle any errors that may have occurred.
          catch (SQLException | ClassNotFoundException e) 
                  {
                     e.getMessage();
                  }
	}

}
