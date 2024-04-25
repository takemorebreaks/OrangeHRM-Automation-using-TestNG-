package ReportGeneration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import ModelclassForDataStoring.ExtentReportModel;
import sampleTestNG.TestSuit.configurationsetup;


public class ReportGenerationClass {
	  private static ExtentReports extentReports;
	    private static final String DEFAULT_FILE_PATH = "C:\\Users\\Palnar\\Downloads\\mailvalidation\\OrangeHRMTestNG\\Screenshots";

	    // Private constructor to prevent instantiation
	    public ReportGenerationClass() {}

	    // Initialize ExtentReports instance
	    public static void initializeReports(String filePath) {
	        if (filePath == null || filePath.isEmpty()) {
	            filePath = DEFAULT_FILE_PATH;
	        }
	        extentReports = new ExtentReports();
	        extentReports.attachReporter(new ExtentSparkReporter(filePath));
	    }

	    // Create a test and return the ExtentTest instance
	    public static ExtentTest createTest(String testName) {
	        return extentReports.createTest(testName);
	    }

//	    // Log information to the test
//	    public static void logInfo(ExtentTest test, String logMessage) {
//	        test.log(Status.INFO, logMessage);
//	    }
//
//	    // Mark the test as passed
//	    public static void passTest(ExtentTest test) {
//	        test.pass("Test Passed");
//	    }

	    // Flush the ExtentReports instance
	    public static void flushReports() {
	        extentReports.flush();
	    }
	}