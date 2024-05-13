package ReportGeneration;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import ModelclassForDataStoring.ExtentReportModel;
import ReusableMethods.TakeScreenConfigShotClass;



public class ReportGenerationClass {
	    private static ExtentReports extentReports;
	    public TakeScreenConfigShotClass screenConfig = new TakeScreenConfigShotClass();
	   // private static final String DEFAULT_FILE_PATH = "C:\\Users\\Palnar\\Downloads\\mailvalidation\\OrangeHRMTestNG\\Screenshots";
	    // Private constructor to prevent instantiation
	    public ReportGenerationClass() {}
	    // Initialize ExtentReports instance
	    public  void initializeReports(String CustomefilePath) {
	        if (CustomefilePath == null || CustomefilePath.isEmpty()) {
	        	File filePath = screenConfig.screenshotsFolder;
	        	 extentReports = new ExtentReports();
	 	        extentReports.attachReporter(new ExtentSparkReporter(filePath));
	        }
	        else {
	       	 extentReports = new ExtentReports();
	 	        extentReports.attachReporter(new ExtentSparkReporter(CustomefilePath));
	        }
	       
	    }

	    // Create a test and return the ExtentTest instance
	    public  ExtentTest createTest(String testName) {
	        return extentReports.createTest(testName);
	    }



	    // Flush the ExtentReports instance
	    public static void flushReports() {
	       
		  extentReports.flush();
	    }
	}