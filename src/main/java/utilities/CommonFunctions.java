package utilities;

import java.io.IOException;
import java.util.List;

public class CommonFunctions {
	
	

	/*
	 * @Description: Compares the string values of a list if they are sorted or not
	 */
	public static int checkAscendingOrderStringList(List<String> stringList) throws IOException{
		try {
			int flag=0;
			for(int i=0;i<stringList.size()-1;i++) {
				if(stringList.get(i).compareToIgnoreCase(stringList.get(i+1))>0) {
					flag=1;
					System.out.println(stringList.get(i)+" wrong-> "+stringList.get(i+1));
					break;
				}
			}
			if(flag==0) {
				return 1;
			}
			else {
				return 0;
			}
		}
		catch(Exception e) {
			return 2;
		}
	}
	
	/*
	 * @Description: Checks if the number of pages calculated and number of pages calculated shown are correct
	 */
	public static void checkPaginationFunctionality(int flag,String totalProduct,int page,String SS_path) throws IOException{
		try {
			int numberOfPagesThroughCalculation;
			if(flag==1) {
				if(Integer.parseInt(totalProduct)%15==0) 
					numberOfPagesThroughCalculation=Integer.parseInt(totalProduct)/15;
				else
					numberOfPagesThroughCalculation=Integer.parseInt(totalProduct)/15+1;
				if(numberOfPagesThroughCalculation==page) {
					Reporting.updateTestReport("The pagination functionality is working fine",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				else
					Reporting.updateTestReport("The pagination functionality is not working fine",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
			else
			{
				if(Integer.parseInt(totalProduct)%10==0) 
					numberOfPagesThroughCalculation=Integer.parseInt(totalProduct)/10;
				else
					numberOfPagesThroughCalculation=Integer.parseInt(totalProduct)/10+1;
				if(numberOfPagesThroughCalculation==page) {
					Reporting.updateTestReport("The pagination functionality is working fine",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.PASS);
				}
				else
					Reporting.updateTestReport("The pagination functionality is not working fine",
							CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
			}
		}
		catch(Exception e) {
			Reporting.updateTestReport("The pagination functionality couldn't be validated",
					CaptureScreenshot.getScreenshot(SS_path), StatusDetails.FAIL);
		}
	}
	
	

}
