package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codoid.products.fillo.Connection;

import com.codoid.products.fillo.Fillo;

import com.codoid.products.fillo.Recordset;


public class excelOperation {

	public static String getTestData(String TCName,String sheetName,String ReqData){

		  String property = "";
		  try{

		  Fillo fillo = new Fillo();
  		  Connection conn = fillo.getConnection(".\\Test Data\\Automation_Datasheet_SF_TC01.xlsx");
  		  
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
	  		  Connection conn = fillo.getConnection(".\\Test Data\\Automation_Datasheet_SF_TC01.xlsx");
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
	
	/*@Author : Shafati Ali 
	 * @Description : It will fetch data from Excel using POI
	 * @Parameter
	 */
	
	public static String getTestDataPOI(int rowNumber)
	{
		 //Create an object of File class to open xlsx file
		try {
			File file =    new File(".\\Test Data\\Automation_Datasheet_SF_TC02.xlsx");

			//Create an object of FileInputStream class to read excel file
			FileInputStream inputStream = new FileInputStream(file);

			XSSFWorkbook wb=new XSSFWorkbook(inputStream);

			//creating a Sheet object
			XSSFSheet sheet= wb.getSheet("StoreFront_PDP");

			        String url =sheet.getRow(rowNumber).getCell(4).getStringCellValue();	    

			wb.close();
			inputStream.close();
			return url;
		} 
        catch (Exception e) {
			e.printStackTrace();	
			return "";
		}
	}

	
	public static int getRowCount()
	{
		 //Create an object of File class to open xlsx file
		try {
			File file =    new File(".\\Test Data\\Automation_Datasheet_SF_TC02.xlsx");

			//Create an object of FileInputStream class to read excel file
			FileInputStream inputStream = new FileInputStream(file);

			XSSFWorkbook wb=new XSSFWorkbook(inputStream);

			//creating a Sheet object
			XSSFSheet sheet= wb.getSheet("StoreFront_PDP");

			//get row count from the sheet
			int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
			wb.close();
			inputStream.close();
			return rowCount;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}