package ReusableMethods;

import java.sql.Driver;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class ITestListnerClass implements ITestListener {
	
	TakeScreenConfigShotClass takeScreenShotClass	=  new TakeScreenConfigShotClass();

	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart...");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess...");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure...");
		//takeScreenShotClass.captureScreenshot("Failed_Screenshot_"+result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		System.out.println("onStart...");
	}

	public void onFinish(ITestContext context) {
		System.out.println("onFinish...");
	}
	

}
