package ReusableMethods;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
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

import sampleTestNG.TestSuit.configurationsetup;
import com.aventstack.extentreports.MediaEntityBuilder;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenShotClass {
    private WebDriver driver;
    private static String screenshotsSubFolderName;

    public TakeScreenShotClass(WebDriver driver) {
        this.driver = driver;
    }

    public Media captureScreenshot(String fileName) {
        if (screenshotsSubFolderName == null) {
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            screenshotsSubFolderName = myDateObj.format(myFormatObj);
        }
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("./Screenshots/" + fileName + "_" + screenshotsSubFolderName + ".png");
        try {
            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot saved successfully");
        return MediaEntityBuilder.createScreenCaptureFromPath(destinationFile.getAbsolutePath()).build();
    }
}