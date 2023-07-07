package utilities;
import com.codoid.products.fillo.Connection;

import com.codoid.products.fillo.Fillo;

import com.codoid.products.fillo.Recordset;

public class excelOperation {

	public static String getTestData(String TCName,String sheetName,String ReqData){

		  String property = "";
		  try{

		  Fillo fillo = new Fillo();
  		  Connection conn = fillo.getConnection(".\\Test_Data\\Automation_Datasheet.xlsx");
  		  
		  String Query = "Select * from "+sheetName+" where Test_Case='"+TCName+"'  ";
		  Recordset recordSet = null;
		  recordSet = conn.executeQuery(Query);
		  
		  while(recordSet.next())
		  	{
			  System.out.println(recordSet.getField(ReqData));
			  property = recordSet.getField(ReqData);
		  	}
		  recordSet.close();
		  conn.close();
		  }
		  catch(Exception e){ 
			  Reporting.updateTestReport("Exception occured: " + e.getMessage(),
						"", StatusDetails.WARNING);
			  System.out.println(e.getMessage()); 
		}

		 return property;

		 }
	public static void updateTestData(String TCName,String sheetName, String column,String NewData) {
		try{

			  Fillo fillo = new Fillo();
	  		  Connection conn = fillo.getConnection(".\\Test_Data\\Automation_Datasheet.xlsx");
	  		  String Query = "UPDATE "+sheetName+" Set "+column+"='"+NewData+"' where Test_Case='"+TCName+"'  ";
	  		  System.out.println(Query);
	  		  conn.executeUpdate (Query);
	  		  conn.close();
		  }
		catch(Exception e)
		{ 
			System.out.println(e.getMessage());
			Reporting.updateTestReport("Exception occured: " + e.getMessage(),
					"", StatusDetails.WARNING);
			}
	}
	
}
