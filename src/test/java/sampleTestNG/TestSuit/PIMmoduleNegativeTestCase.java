package sampleTestNG.TestSuit;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.ScreenCapture;
import sampleTestNG.TestSuit.OrangeHRMPIMTestData;

import GlobalexceptionHandler.NoSuchElementException;
import ReportGeneration.ReportGenerationClass;
import ReusableMethods.DropDownListOptionSelection;
import ReusableMethods.SelectNavigationBarOption;
import ReusableMethods.TakeScreenConfigShotClass;
import configClass.ConfigurationSetup;
public class PIMmoduleNegativeTestCase {
	public WebDriver driver;
	private static ExtentTest test;
	SelectNavigationBarOption navigationBarOption;
	ConfigurationSetup configurationsetup;
	WebDriverWait driverWait;
	TakeScreenConfigShotClass screenShotClass; 
	OrangeHRMPIMTestData hrmpimTestData;
	public ReportGenerationClass reportGeneration = new ReportGenerationClass();

	@BeforeClass 
	public void LoadApplication() throws Exception  {
		configurationsetup =new ConfigurationSetup("chrome");
		configurationsetup.loadUrl();
		this.driver = configurationsetup.driver;
		driverWait=new WebDriverWait(driver, Duration.ofSeconds(10));
		navigationBarOption =  new SelectNavigationBarOption(driver);
		navigationBarOption.SelectSideNavOption("viewPimModule");
		// Initialize WebDriver and other setup
		screenShotClass=new TakeScreenConfigShotClass(driver);
		screenShotClass.createFolder("PIMmoduleNegativeTestCase");
		reportGeneration.initializeReports(null); // Provide a file path if necessary, otherwise use default
		Thread.sleep(5000);

	}
	@AfterClass
	public void tearDown() {
		// Clean up WebDriver and other teardown
		reportGeneration.flushReports();
	}


	@Test(dataProvider = "CheckFirstNameandLastName", dataProviderClass = OrangeHRMPIMTestData.class,priority = 1)
	public void AddNewEmployeeWithInvalidData(String textBoxName, Object[][] testData) throws Exception {
		//check whether Admin can add new employee on add employee page...and fill all data ->move to next tab ->return back and check data is still there or textBox is empty

		try {
			navigationBarOption.SelectTopNavOption("Add Employee");
			Thread.sleep(1000);
			if (test == null) {
				test = reportGeneration.createTest("AddNewEmployeeWithInvalidData");
			}
			test.log(Status.PASS, "User Navigated to add employeeScreen",screenShotClass.captureScreenshot("employeeScreen")); 
			// Call the testTextBox method for testing different text boxes
			testTextBox(textBoxName, testData,test);
		}
		catch (NoSuchElementException e) {
			test.log(Status.FAIL, "User cant click Add button",screenShotClass.captureScreenshot("")); 
			throw new NoSuchElementException("There is no Add button in Employee Screeen...");
		}
	}

	public Boolean testTextBox(String textBoxName, Object[][] testData,ExtentTest test) throws Exception  {
		switch (textBoxName) {
		case "FirstName":
			WebElement FirstName=driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(OrangeHRMPIMElementLocators.FirstNameTextBox)));
			FirstName.click();
			for (Object[] data : testData) {
				// Assuming the test data is stored in the first index of each inner array
				String value = String.valueOf(data[0]);
				System.out.println("FirstName value..."+value);
				// Send the test data to the FirstName text box
				FirstName.sendKeys(value);
				driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(OrangeHRMPIMElementLocators.SaveButton))).click();;
				Thread.sleep(2000);
				test.log(Status.FAIL,"This Field Accepting whitespace/Special char/Numbers",screenShotClass.captureScreenshot(""));
				FirstName.sendKeys(Keys.CONTROL+"a");
				FirstName.sendKeys(Keys.DELETE);
			}
			break;
		case "MiddleName":
			WebElement MiddleirstName=driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(OrangeHRMPIMElementLocators.MiddleNameTextBox)));
			for (Object[] data : testData) {
				// Assuming the test data is stored in the first index of each inner array
				String value = String.valueOf(data[0]);
				System.out.println("MiddleName value..."+value);
				MiddleirstName.sendKeys(value);
				driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(OrangeHRMPIMElementLocators.SaveButton))).click();
				Thread.sleep(2000);
				test.log(Status.FAIL,"This Field Accepting whitespace/Special char/Numbers",screenShotClass.captureScreenshot(""));
				MiddleirstName.sendKeys(Keys.CONTROL+"a");
				MiddleirstName.sendKeys(Keys.DELETE);
			}
			break;
		case "LastName":
			WebElement LastName=driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(OrangeHRMPIMElementLocators.LastNameTextBox)));
			for (Object[] data : testData) {
				// Assuming the test data is stored in the first index of each inner array
				String value = String.valueOf(data[0]);
				System.out.println("MiddleName value..."+value);
				LastName.sendKeys(value);
				driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(OrangeHRMPIMElementLocators.SaveButton))).click();
				Thread.sleep(2000);
				test.log(Status.FAIL,"This Field Accepting whitespace/Special char/Numbers",screenShotClass.captureScreenshot(""));
				LastName.sendKeys(Keys.CONTROL+"a");
				LastName.sendKeys(Keys.DELETE);
			}
			break;
		}

		return null;
	}



}
