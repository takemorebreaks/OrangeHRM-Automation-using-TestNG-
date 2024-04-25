package GlobalexceptionHandler;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class GlobalExceptionHandler implements ITestListener {
    public void onTestFailure(ITestResult result) {
        // Check if the test result has an exception
        if (result.getThrowable() != null) {
            System.out.println("Exception occurred in test: " + result.getMethod().getMethodName());
            result.getThrowable().printStackTrace();
        }
    }

    // This method handles the UserAlreadyExistException
    public void HandleUserAlreadyExistException(CredantialError exception){
        // Handle the exception (e.g., log it, report it, etc.)
        System.out.println("User already exists: " + exception.getMessage());
        // Additional handling code as needed
    }

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
}
