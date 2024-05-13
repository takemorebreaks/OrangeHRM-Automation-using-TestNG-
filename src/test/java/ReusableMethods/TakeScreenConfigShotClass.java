package ReusableMethods;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.observer.entity.MediaEntity;

import com.aventstack.extentreports.MediaEntityBuilder;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenConfigShotClass {
    public WebDriver driver;
    private static String screenshotsSubFolderName=null;
    private static String ScreenshotFolderName="./Screenshots/";
    public  static File screenshotsFolder;
    public TakeScreenConfigShotClass() {}

    public TakeScreenConfigShotClass(WebDriver driver) {
		this.driver = driver;
	}
    public File createFolder(String folderName) {
         screenshotsFolder = new File(ScreenshotFolderName + folderName + mkTimeFormate());
        // Check if the folder already exists
        if (!screenshotsFolder.exists()) {
            // Attempt to create the folder and its parent directories if they don't exist
            boolean success = screenshotsFolder.mkdirs();
            if (success) {
                return screenshotsFolder; // Folder creation was successful
            } else {
                return null; // Folder creation failed
            }
        } else {
            return screenshotsFolder; // Folder already exists, so creation is not attempted
        }
    }

    private String mkTimeFormate() {
    	LocalDateTime myDate = LocalDateTime.now();
    	DateTimeFormatter myFormate =DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
    	screenshotsSubFolderName = myDate.format(myFormate);
		return screenshotsSubFolderName;
    }
	public Media captureScreenshot(String fileName) {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        //screenshotsFolder = new File("./Screenshots/" + fileName + "_" + screenshotsSubFolderName + ".png");
        if(fileName.isEmpty()||fileName==null) {
        	fileName=mkTimeFormate();
        }
        File screenshotFile = new File(screenshotsFolder, fileName + ".png");
        try {
            FileUtils.copyFile(sourceFile, screenshotFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot saved successfully");
        return MediaEntityBuilder.createScreenCaptureFromPath(screenshotFile.getAbsolutePath()).build();
    }
}