package sampleTestNG.TestSuit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ReusableMethods.SelectNavigationBarOption;
public class PIMmoduleNegativeTestCase {
	public WebDriver driver; 
@BeforeClass
private void SetUp() throws Exception {
	PIMmodulePositiveTestCase piMmodulePositiveTestCase = new PIMmodulePositiveTestCase();
	configurationsetup configurationsetup =new configurationsetup();
	SelectNavigationBarOption navigationBarOption= new SelectNavigationBarOption();
	configurationsetup.loadUrl();
	driver = configurationsetup.driver;
	navigationBarOption.SelectSideNavOption("viewPimModule");
	Thread.sleep(5000);
}
@Test()
private void AddNewEmployeeWithInvalidData() {
	
}
}
