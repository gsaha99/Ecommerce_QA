package Test_Suite;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

	public static void main(String[] args) throws Exception {
		try {

		String excel = "C:\\Users\\shafali\\CategoryLanding_1\\Ecommerce_QA\\Test Data\\Automation_Datasheet.xlsx";
		FileInputStream file = new FileInputStream (excel);

		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet("StoreFront_PDP");

		int columnNumber=3;
		for (Row row : sheet) {
			Cell cell = row.getCell(columnNumber);
			String cellValue=cell.getStringCellValue();
			System.out.println(cellValue);			
		}
		wb.close();
		file.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		/*int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(1).getLastCellNum();

		for (int i=0;i<=rows;i++) {

			XSSFRow row = sheet.getRow(i);
			for (int j=0;j<=columns;j++) {
				XSSFCell cell =	row.getCell(j);
				System.out.print(cell);
				System.out.print("|");
			}
		}
		 */

	}

}
