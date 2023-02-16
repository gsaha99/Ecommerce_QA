package utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CaptureScreenshot {

	public static String getScreenshot(String ReportFileLocation) throws IOException {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		//Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)DriverModule.getWebDriver());

        //Call getScreenshotAs method to create image file

        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
		

        String Destination_Location=ReportFileLocation +"\\" +dateName+".png";
        File DestFile=new File(Destination_Location);
        /*
         * Testing the Ashot
         
        //Screenshot screenshot = new AShot().takeScreenshot(DriverModule.getWebDriver());
        
        Screenshot screenshot =new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).
        		takeScreenshot(DriverModule.getWebDriver());
        ImageIO.write(screenshot.getImage(), "png", new File(Destination_Location));*/

        //Copy file at destination
                
        FileUtils.copyFile(SrcFile, DestFile);        
        return Destination_Location;

		 }
}
