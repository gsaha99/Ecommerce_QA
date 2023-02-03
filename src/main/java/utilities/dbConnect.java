package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnect {
	
	
	public static String DB_Select(String DBName,String TableName,String ColumnName1,String ColumnName2) throws InstantiationException, IllegalAccessException
    
	{
        String connectionUrl = "jdbc:mysql://wps.c7hepfyiogmn.us-east-1.rds.amazonaws.com:3306/"+DBName;
        System.out.println(connectionUrl);
        String property = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String userid = excelOperation.getTestData("DBQAUserID", "Generic_Dataset", "Data");
            String pwd = excelOperation.getTestData("DBQAPassword", "Generic_Dataset", "Data");
            Connection con = DriverManager.getConnection(connectionUrl,userid,pwd);
            Statement stmt= con.createStatement();
            String SQL = "SELECT * FROM "+DBName+"."+TableName+" order by "+ColumnName2+" desc limit 1";
            System.out.println(SQL);
            ResultSet rs = stmt.executeQuery(SQL);
            
    
            // Iterate through the data in the result set and display it.
         while (rs.next()) 
              {
        	    System.out.println(rs.getString(ColumnName1));
			    //property = rs.getString(ColumnName);
           //System.out.println(rs.getInt("status_id") + " " + rs.getString("status"));
              }
          //rs.close();
		  con.close();
            }

        // Handle any errors that may have occurred.
          catch (SQLException | ClassNotFoundException e) 
                  {
                     e.getMessage();
                  }
        return property;
	}

}
