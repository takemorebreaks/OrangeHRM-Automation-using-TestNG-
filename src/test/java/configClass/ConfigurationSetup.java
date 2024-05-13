package configClass;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import ReportGeneration.ReportGenerationClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import sampleTestNG.TestSuit.OrangeHRMPIMElementLocators;

import java.util.List;

import javax.security.auth.login.CredentialException;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;


public class ConfigurationSetup implements ITestListener{
    public ConfigurationSetup() {
		super();
	}
	public WebDriver driver;

    public ConfigurationSetup(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                driver.manage().window().maximize();
                break;
            default:
                WebDriverManager.chromedriver().setup(); // Set default to Chrome
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
        }
    }
	public WebDriver getDriverConfiguration() throws Exception{
		return driver;
	}

	public void loadUrl() throws Exception {
		System.out.println("Message from config class......");
		// Navigate to the URL

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		System.out.println("Title of the page is " + driver.getTitle());

		Thread.sleep(5000);
		// Call AddConfiguration method after loading URL
		try {
			AddConfiguration("Admin", "admin123"); 

		} catch (CredentialException e) {
			e.printStackTrace();
		}
	}
	public void AddConfiguration(String username,String password)  throws Exception {
		System.out.println("Message from config class......");
		WebElement usernameField = driver.findElement(By.xpath(OrangeHRMPIMElementLocators.UserNameOfOrangeHRM));
		WebElement passwordField = driver.findElement(By.xpath(OrangeHRMPIMElementLocators.PasswordOfOrangeHRM));
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);

		driver.findElement(By.xpath(OrangeHRMPIMElementLocators.LoginButtonOrangeHRM)).click();

		Thread.sleep(5000);
		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat("OrangeHRM").isEqualTo(driver.getTitle(),"Not matching");


		// Find the button element using findElements (no exception thrown if not found)
		List<WebElement> buttonElementsLeft= driver.findElements(By.xpath(OrangeHRMPIMElementLocators.sideNavigationBarLeftSide));
		// Check if any button elements were found
		if (!buttonElementsLeft.isEmpty()) {
			// Get the first button element from the list
			WebElement buttonElement = buttonElementsLeft.get(0);

			// Check if the button is visible
			if (buttonElement.isDisplayed()) {
				// Button is visible, click it
				System.out.println("Button is visible,no need to click...");
			} else {
				// Button is not visible, handle the case appropriately
				System.out.println("Button is not visible...");
				// Perform alternative action or log a message
			}
		} else {
			// Button element is not found, handle the case appropriately
			System.out.println("Button element not found on the page.");
			List<WebElement> buttonElementRight = driver.findElements(By.xpath(OrangeHRMPIMElementLocators.sideNavigationBarRightSide));
			WebElement buttonElement = buttonElementRight.get(0);
			buttonElement.click();
			// Perform alternative action or log a message
		}
		softAssertions.assertAll(); // All soft assertions are evaluated here


	}
	public void ExitBrowser() {
		driver.close();
	}
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	

}
