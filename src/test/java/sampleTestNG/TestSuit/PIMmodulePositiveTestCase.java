package sampleTestNG.TestSuit;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import GlobalexceptionHandler.NoSuchElementException;
import GlobalexceptionHandler.TimeOutExceptionoccured;
import ModelclassForDataStoring.EmployeeList;
import ReportGeneration.ReportGenerationClass;
import ReusableMethods.DropDownListOptionSelection;
import ReusableMethods.ITestListnerClass;
import ReusableMethods.SelectNavigationBarOption;
import ReusableMethods.TakeScreenConfigShotClass;
import configClass.ConfigurationSetup;

import java.time.Duration;
import java.util.ArrayList;
@Listeners(ITestListnerClass.class)
public class PIMmodulePositiveTestCase{

	boolean SearchResult=false;
	ConfigurationSetup configurationsetup;
	SoftAssert softAssert = new SoftAssert();
	public static WebDriver driver; 
	WebDriverWait driverWait;
	List<Object> TrailList = new ArrayList<Object>();
	DropDownListOptionSelection dropDownSelection = 	new DropDownListOptionSelection();
	public ReportGenerationClass reportGeneration = new ReportGenerationClass();
	SelectNavigationBarOption navigationBarOption; 
	TakeScreenConfigShotClass screenShotClass; 

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
		screenShotClass.createFolder("PIMmodulePositiveTestCase");
		reportGeneration.initializeReports(null); // Provide a file path if necessary, otherwise use default
		Thread.sleep(5000);
		
	}
	  @AfterClass
	    public void tearDown() {
	        // Clean up WebDriver and other teardown
		  reportGeneration.flushReports();
	    }


	@Test(testName = "AddNewEmployee",enabled = true,alwaysRun = true,priority = 1)
	public void AddNewEmployee() throws InterruptedException {	
		System.out.println("priority 1");
		ExtentTest  test1 = reportGeneration.createTest("AddNewEmployee");
		navigationBarOption.SelectTopNavOption("Add Employee");
		Thread.sleep(1000);
        //test1.log(Status.PASS, "Navigated to add employee Screen");
        test1.log(Status.PASS, "User Navigated to add employeeScreen",screenShotClass.captureScreenshot("employeeScreen")); 
		List<WebElement>UserNameielement=new ArrayList<WebElement>();
		UserNameielement= driver.findElements(By.xpath(OrangeHRMPIMElementLocators.UserNameTextBox));

		if(!UserNameielement.isEmpty()) {
			WebElement UserNameIElement = UserNameielement.get(0);
			if(UserNameIElement.isDisplayed()) {
				Fillalluserdetails(test1);
			}
		}
		else {
			try {
				//driver.findElement(By.xpath(CheckBox)).click();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
				WebElement CheckBoxElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OrangeHRMPIMElementLocators.CheckBox)));
				CheckBoxElement.click();  
				 test1.log(Status.PASS, "Clicked CheckBox");
				Thread.sleep(2000);
				Fillalluserdetails(test1);
				Thread.sleep(5000);
			}
			catch(ElementNotInteractableException e){
				throw new ElementNotInteractableException("Element not interacting..."+e.getMessage());
			}
		}

	}


	private void Fillalluserdetails(ExtentTest test) throws InterruptedException, NoSuchElementException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			WebElement firstNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.FirstNameTextBox)));
			firstNameElement.sendKeys(OrangeHRMPIMTestData.FirstName);
			test.log(Status.PASS," User can Enter FirstName inside FirstName textbox",screenShotClass.captureScreenshot(" FirstName textbox "));
            
			WebElement lastNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.LastNameTextBox)));
			lastNameElement.sendKeys(OrangeHRMPIMTestData.LastName); 
			test.log(Status.PASS," User can Enter LastName inside LastName textbox ",screenShotClass.captureScreenshot(" LastName textbox "));

			WebElement employeeIdElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OrangeHRMPIMElementLocators.EmployeeIdTextBox)));
			employeeIdElement.sendKeys(Keys.CONTROL + "a"); // Select all text in the input field
			employeeIdElement.sendKeys(Keys.DELETE); // Delete the selected text
			employeeIdElement.sendKeys(OrangeHRMPIMTestData.EmployeeId); 
			test.log(Status.PASS," User can Enter EmployeeId inside EmployeeId textbox ",screenShotClass.captureScreenshot(" EmployeeId textbox "));

			WebElement userNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.UserNameTextBox)));
			userNameElement.sendKeys(OrangeHRMPIMTestData.UserName);  
			test.log(Status.PASS," User can Enter UserName inside UserName textbox ",screenShotClass.captureScreenshot(" UserName textbox "));
			WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.PasswordTextBox)));
			passwordElement.sendKeys(OrangeHRMPIMTestData.Password);
		    String ActualResult = passwordElement.getAttribute("value");
			String ExpectedResult = OrangeHRMPIMTestData.Password;
			if (ActualResult.equals(ExpectedResult)) {
			    test.log(Status.PASS," Password entered successfully in Confirm Password textbox ",screenShotClass.captureScreenshot(" Password textbox "));

			} else {
			    test.log(Status.FAIL," Password entered in Confirm Password textbox does not match the expected value ",screenShotClass.captureScreenshot(" Password textbox "));
			}

			WebElement confirmPasswordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.ConfirmPasswordTextBox)));
			confirmPasswordElement.sendKeys(OrangeHRMPIMTestData.Password);
			test.log(Status.PASS, "User can ReEnter Password inside confirmPassword textbox");    

			WebElement saveButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OrangeHRMPIMElementLocators.SaveButton)));
			if (saveButtonElement.isEnabled()) {
			    saveButtonElement.click();
			    test.log(Status.PASS,"  User can click save button ",screenShotClass.captureScreenshot(" Save Button "));
			} else {
			    test.log(Status.FAIL,"  Save button is not clickable ",screenShotClass.captureScreenshot(" Save Button "));
			} 

		} catch (TimeOutExceptionoccured e) {
			throw new NoSuchElementException("Timeout waiting for element: " + e.getMessage());
		}
	}
	@Test(enabled = true,priority = 2)
	private void EmployeeInfoSearch() throws InterruptedException,NoSuchElementException {
		System.out.println("priority 2");
		ExtentTest  test2 = reportGeneration.createTest("EmployeeInfoSearch");
		try {
			navigationBarOption.SelectTopNavOption("Employee List");
			WebElement EmpInfoPageEmployeeNameText = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.EmployeeName))); 
			EmpInfoPageEmployeeNameText.sendKeys(OrangeHRMPIMTestData.FirstName);
			test2.log(Status.PASS,"User Entered FirstName",screenShotClass.captureScreenshot("FirstName"));
			Thread.sleep(1000);

			WebElement EmpInfoPageEmployee_IdText=driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.Employee_Id)));
			EmpInfoPageEmployee_IdText.sendKeys(OrangeHRMPIMTestData.EmployeeId);
			test2.log(Status.PASS,"User Entered EmployeeId",screenShotClass.captureScreenshot("EmployeeId"));
			Thread.sleep(1000);

			WebElement EmpInfoPageSupervisorNameText=driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.SupervisorName)));
			EmpInfoPageSupervisorNameText.sendKeys(OrangeHRMPIMTestData.SuperViceName);
			test2.log(Status.PASS,"User Entered SuperViceName",screenShotClass.captureScreenshot("SuperViceName"));
			Thread.sleep(1000);

			WebElement EmpInfoEmployementStatusDropDown=driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(OrangeHRMPIMElementLocators.EmployementStatusDropDownTextBox)));
			EmpInfoEmployementStatusDropDown.click();
			Thread.sleep(1000);
			test2.log(Status.PASS,"User Clicked EmployementStatusDropDownTextBox",screenShotClass.captureScreenshot("EmployementStatusDropDownTextBox"));

			// Iterate through each option
			for (int i = 0; i < 10; i++) {
				// Move down to the desired option
				EmpInfoEmployementStatusDropDown.sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(1000); // Add a small delay to allow the dropdown to update
				// Get the currently selected option
				String selectedOption = EmpInfoEmployementStatusDropDown.getText();
				System.out.println(selectedOption);

				// Check if the selected option matches the expected result
				if (selectedOption.equals(OrangeHRMPIMTestData.expectedResultForEmpStatus)) {
					// If it matches, select the option
					test2.log(Status.PASS,"User Clicked expectedResultForEmpStatus",screenShotClass.captureScreenshot("expectedResultForEmpStatus"));
					EmpInfoEmployementStatusDropDown.sendKeys(Keys.ENTER);
					break; // Exit the loop
				}
			}
			
			dropDownSelection.selectOptionFromDropdownOptionalValues(driver,OrangeHRMPIMElementLocators.JobTitle,OrangeHRMPIMTestData.expectedResultforSubUnit);     
			dropDownSelection.selectOptionFromDropdownOptionalValues(driver,OrangeHRMPIMElementLocators.EmpJobRoleSubRole,OrangeHRMPIMTestData.expectedResultForJobTitle); 
			WebElement SearchButtonEmployeeListOption=driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.SearchButton)));
			SearchButtonEmployeeListOption.click();
			test2.log(Status.PASS,"User Clicked SearchButton",screenShotClass.captureScreenshot("SearchButton"));
			Thread.sleep(5000);
		}
		catch(NoSuchElementException e) { 
			test2.log(Status.FAIL,e.getMessage()+" From EmployeeInfoSearch Method ",screenShotClass.captureScreenshot("Error from EmployeeInfoSearch Method"));
			throw new NoSuchElementException("Element not found..."+e.getMessage());
		}

	}
	@Test(enabled = true,priority = 3)
	private void EmployeeListTableDataStoring() throws InterruptedException,NoSuchElementException {
		System.out.println("priority 3");
		ExtentTest  test3 = reportGeneration.createTest("EmployeeListTableDataStoring");
		try {	
			navigationBarOption.SelectTopNavOption("Employee List");
			test3.log(Status.PASS,"User navigated to Employee List",screenShotClass.captureScreenshot("Employee List"));
			Thread.sleep(2000);
			// Locate the row containing the column headers
			WebElement columnHeaderRow = driver.findElement(By.xpath(OrangeHRMPIMElementLocators.TableEmployeeListHeadLine));
			Thread.sleep(2000);
			WebElement colunmDataRow = driver.findElement(By.xpath(OrangeHRMPIMElementLocators.TableEmployeeListData));
			// Find all child elements representing the column headers using XPath
			List<WebElement> columnHeaderElements = columnHeaderRow.findElements(By.xpath(".//div[@role='columnheader']"));
			List<WebElement> columnDataElements = colunmDataRow.findElements(By.xpath("//div[@class='oxd-table-card']"));

			// Create a list to store the column headers
			List<String> columnHeaders = new ArrayList<String>();
		
			// Iterate through each column header element and extract the text
			for (WebElement columnHeaderElement : columnHeaderElements) {
				String columnHeaderText = columnHeaderElement.getText();
				columnHeaders.add(columnHeaderText);
				System.out.println(columnHeaderText);
			} 

			System.out.println("size of columnDataElements :" + columnDataElements.size());
			
			for(int J = 0;J<columnDataElements.size();J++) {
				WebElement Trail = columnDataElements.get(J);
				EmployeeList employeeList = new EmployeeList(); 
				List<WebElement> children = Trail.findElements(By.xpath("./div/child::div")); // Use ./ to select child elements 9 values
				for (int i = 0; i < children.size(); i++) {
					WebElement child = children.get(i);
					String text = child.getText().trim();
					// System.out.println("column text value :"+text);
					switch(i) {
					case 0:
						employeeList.setCheckBox((Boolean) (text.isEmpty()?false:text));
						break;
					case 1:
						employeeList.setId(text.isEmpty() ? "empty field" : text);
						break;
					case 2:
						employeeList.setFirstName(text.isEmpty() ? "empty field" : text);
						break;
					case 3:
						employeeList.setLastName(text.isEmpty() ? "empty field" : text);
						break;
					case 4:
						employeeList.setJonTitle(text.isEmpty() ? "empty field" : text);
						break;
					case 5:
						employeeList.setEmploymentStatus(text.isEmpty() ? "empty field" : text);
						break;
					case 6:
						employeeList.setSubUnit(text.isEmpty() ? "empty field" : text);
						break;
					case 7:
						employeeList.setSuperVisor(text.isEmpty() ? "empty field" : text);
						break;
					case 8:
						employeeList.setAction(text.isEmpty() ? "empty field" : text);
						break;
					case 9:
						employeeList.setAction(text.isEmpty() ? "empty field" : text);
						break;
					default:
						// Handle any additional columns here
						break;
					}
				}
				TrailList.add(employeeList);
				List<Object> employeeListAsList = new ArrayList<Object>();
				employeeListAsList.add(employeeList);
				 SearchResult = SelectEmployeeListTableDataStoring(OrangeHRMPIMTestData.EmployeeId,OrangeHRMPIMTestData.FirstName, Trail, employeeListAsList);
	        		}
				//boolean SearchResult = SelectEmployeeListTableDataStoring(EmployeeId, FirstName, Trail, Arrays.asList((Object) TrailList.get(J)));
				if(SearchResult) {
					test3.log(Status.PASS, "Matching option found",screenShotClass.captureScreenshot("Emp Details Matching"));
					softAssert.assertEquals(SearchResult, true);
					softAssert.assertAll(); // This line will mark the test as failed if any soft assert has failed	    	
					
				}
				else {
					test3.log(Status.FAIL, "Matching option Not found",screenShotClass.captureScreenshot("Emp Details Matching"));
				}
			
				
		}
		catch(NoSuchElementException e) {
			test3.log(Status.FAIL, "Matching option not found",screenShotClass.captureScreenshot("Emp Details not  Matching"));
			throw new NoSuchElementException("Element not found..."+e.getMessage());
		}

	}

	public Boolean SelectEmployeeListTableDataStoring(String Id,String FirstName,WebElement columnData ,List<Object> columnDATA)throws InterruptedException,NoSuchElementException {
		try {
			for (Object obj : columnDATA) {
				EmployeeList employeeList = (EmployeeList) obj;
				System.out.println("Id : " + employeeList.getId());
				System.out.println("First Name : " + employeeList.getFirstName());
				System.out.println("Last Name : " + employeeList.getLastName());
				System.out.println("Job Title : " + employeeList.getJonTitle());
				System.out.println("Employment Status : " + employeeList.getEmploymentStatus());
				System.out.println("Sub Unit : " + employeeList.getSubUnit());
				System.out.println("Supervisor : " + employeeList.getSuperVisor());
				System.out.println("Action : " + employeeList.getAction());
				// Print additional attributes if needed
				if(employeeList.getId().equals(Id) && employeeList.getFirstName().equals(FirstName)) {
					System.out.println("This line is Matching...");
					columnData.click();
					return true;
				}
				else {
					System.out.println("This line of row Not Matching...");
				}
			}
			return false;
		}
		catch(NoSuchElementException e) {
			throw new NoSuchElementException("Element not found..."+e.getMessage());
		}

	}

}
