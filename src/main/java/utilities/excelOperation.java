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
  		  Connection conn = fillo.getConnection(".\\Test Data\\Automation_Datasheet.xlsx");
  		  
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
	  		  Connection conn = fillo.getConnection(".\\Test Data\\Automation_Datasheet.xlsx");
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
	
	public static void getTestDatPOI()
	{
		 //Create an object of File class to open xlsx file
        try {
			File file =    new File(".\\Test Data\\Automation_Datasheet.xlsx");

			//Create an object of FileInputStream class to read excel file
			FileInputStream inputStream = new FileInputStream(file);

			
			//Workbook wrkbk= StreamingReader.builder().rowCacheSize(200).bufferSize(4096).open(inputStream);
					
			XSSFWorkbook wb=new XSSFWorkbook(inputStream);

			//creating a Sheet object
			XSSFSheet sheet= wb.getSheet("StoreFront_PDP");
			
			//get all rows in the sheet
			int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
			
			System.out.println("Total number of rows" + rowCount);
			//iterate over all the row to print the data present in each cell.
			for(int i=1;i<=rowCount;i++){
			    
			        System.out.print("Row"+i+ "-->" + sheet.getRow(i).getCell(4).getStringCellValue());
			        System.out.println();
			    }
			   		
			wb.close();
			inputStream.close();
		} 
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
 
		
		
	}
}

