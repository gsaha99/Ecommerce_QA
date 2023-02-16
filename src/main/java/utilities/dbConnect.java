package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnect {
	
	
	public static String DB_Select(String DBName,String Query,String Parameter) throws InstantiationException, IllegalAccessException
    
	{
        String connectionUrl = "jdbc:mysql://wps.c7hepfyiogmn.us-east-1.rds.amazonaws.com:3306/"+DBName;
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
            System.out.println(rs);
            
            
    
            // Iterate through the data in the result set and display it.
            if (Parameter=="Int")
            {
                Integer Col=null;
        	    System.out.println(rs.getInt(0));
        	    Col = rs.getInt(0);
        	    columnValue=Col.toString();
              
            }
            
            else if (Parameter == "VarChar")
            {
            	System.out.println(rs.getString(0));
        	    columnValue = rs.getString(0);
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

}
