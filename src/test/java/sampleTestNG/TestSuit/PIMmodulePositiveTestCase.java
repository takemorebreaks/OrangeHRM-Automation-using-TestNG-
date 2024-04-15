package sampleTestNG.TestSuit;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GlobalexceptionHandler.NoSuchElementException;
import GlobalexceptionHandler.TimeOutExceptionoccured;
import ModelclassForDataStoring.EmployeeList;
import ReusableMethods.DropDownListOptionSelection;
import ReusableMethods.SelectNavigationBarOption;
import java.time.Duration;
import java.util.ArrayList;
public class PIMmodulePositiveTestCase{

	boolean SearchResult=false;
	SoftAssert softAssert = new SoftAssert();
	public WebDriver driver;
	DropDownListOptionSelection dropDownSelection = 	new DropDownListOptionSelection();
	List<Object> TrailList = new ArrayList<Object>();
	SelectNavigationBarOption navigationBarOption= new SelectNavigationBarOption();
	WebDriverWait driverWait = new WebDriverWait(driver,Duration.ofSeconds(10));
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));

	@BeforeClass 
	public void LoadApplication() throws Exception  {
		configurationsetup configurationsetup =new configurationsetup();
		configurationsetup.loadUrl();
		driver = configurationsetup.driver;
		navigationBarOption.SelectSideNavOption("viewPimModule");
		Thread.sleep(5000);
	}
	//	@AfterClass
	//	public void AfterTestCompletion() throws Exception {
	//		configurationsetup.ExitBrowser();
	//	}

	@Test(enabled = true,priority = 1)
	public void AddNewEmployee() throws InterruptedException {		
		navigationBarOption.SelectTopNavOption("Add Employee");
		List<WebElement>UserNameielement=new ArrayList<WebElement>();
		UserNameielement= driver.findElements(By.xpath(OrangeHRMPIMElementLocators.UserNameTextBox));
		if(!UserNameielement.isEmpty()) {
			WebElement UserNameIElement = UserNameielement.get(0);
			if(UserNameIElement.isDisplayed()) {
				Fillalluserdetails();
			}
		}
		else {
			try {
				//driver.findElement(By.xpath(CheckBox)).click();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
				WebElement CheckBoxElement=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OrangeHRMPIMElementLocators.CheckBox)));
				CheckBoxElement.click();
				Thread.sleep(2000);
				Fillalluserdetails();
				Thread.sleep(8000);
			}
			catch(ElementNotInteractableException e){
				throw new ElementNotInteractableException("Element not interacting..."+e.getMessage());
			}
		}
	}



	private void Fillalluserdetails() throws InterruptedException, NoSuchElementException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			WebElement firstNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.FirstNameTextBox)));
			firstNameElement.sendKeys(OrangeHRMPIMTestData.FirstName);

			WebElement lastNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.LastNameTextBox)));
			lastNameElement.sendKeys(OrangeHRMPIMTestData.LastName);

			WebElement employeeIdElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OrangeHRMPIMElementLocators.EmployeeIdTextBox)));
			employeeIdElement.sendKeys(Keys.CONTROL + "a"); // Select all text in the input field
			employeeIdElement.sendKeys(Keys.DELETE); // Delete the selected text
			employeeIdElement.sendKeys(OrangeHRMPIMTestData.EmployeeId);

			WebElement userNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.UserNameTextBox)));
			userNameElement.sendKeys(OrangeHRMPIMTestData.UserName);

			WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.PasswordTextBox)));
			passwordElement.sendKeys(OrangeHRMPIMTestData.Password);

			WebElement confirmPasswordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.ConfirmPasswordTextBox)));
			confirmPasswordElement.sendKeys(OrangeHRMPIMTestData.Password);

			WebElement saveButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OrangeHRMPIMElementLocators.SaveButton)));
			saveButtonElement.click();
		} catch (TimeOutExceptionoccured e) {
			throw new NoSuchElementException("Timeout waiting for element: " + e.getMessage());
		}
	}
	@Test(enabled = false)
	private void EmployeeInfoSearch() throws InterruptedException,NoSuchElementException {
		try {
			navigationBarOption.SelectTopNavOption("Employee List");

			WebElement EmpInfoPageEmployeeNameText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.EmployeeName))); 
			EmpInfoPageEmployeeNameText.sendKeys(OrangeHRMPIMTestData.FirstName);
			Thread.sleep(5000);

			WebElement EmpInfoPageEmployee_IdText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.Employee_Id)));
			EmpInfoPageEmployee_IdText.sendKeys(OrangeHRMPIMTestData.EmployeeId);
			Thread.sleep(5000);

			WebElement EmpInfoPageSupervisorNameText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.SupervisorName)));
			EmpInfoPageSupervisorNameText.sendKeys(OrangeHRMPIMTestData.SuperViceName);
			Thread.sleep(5000);

			WebElement EmpInfoEmployementStatusDropDown=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.EmployementStatusDropDownTextBox)));
			EmpInfoEmployementStatusDropDown.click();
			Thread.sleep(5000);


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
					EmpInfoEmployementStatusDropDown.sendKeys(Keys.ENTER);
					break; // Exit the loop
				}
			}
			//	        WebElement EmpInfoEmpRole=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EmpJobRole)));
			//	        EmpInfoEmpRole.click();
			//	        Thread.sleep(2000);
			//	        WebElement EmpInfoSelectOptionforempRole=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SelectOptionforempRole)));
			//	        EmpInfoEmpRole.click();
			//	        Thread.sleep(8000);
			dropDownSelection.selectOptionFromDropdownClassSpan(driver,OrangeHRMPIMElementLocators.JobTitle,OrangeHRMPIMTestData.expectedResultforSubUnit);     
			dropDownSelection.selectOptionFromDropdownClassSpan(driver,OrangeHRMPIMElementLocators.EmpJobRoleSubRole,OrangeHRMPIMTestData.expectedResultForJobTitle); 
			WebElement SearchButtonEmployeeListOption=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OrangeHRMPIMElementLocators.SearchButton)));
			SearchButtonEmployeeListOption.click();
			Thread.sleep(5000);
		}
		catch(NoSuchElementException e) {
			throw new NoSuchElementException("Element not found..."+e.getMessage());
		}

	}
	@Test(enabled = true,priority = 2)
	private void EmployeeListTableDataStoring() throws InterruptedException,NoSuchElementException {
		try {
			navigationBarOption.SelectTopNavOption("Employee List");
			Thread.sleep(2000);
			// Locate the row containing the column headers
			WebElement columnHeaderRow = driver.findElement(By.xpath(OrangeHRMPIMElementLocators.TableEmployeeListHeadLine));
			Thread.sleep(5000);
			WebElement colunmDataRow = driver.findElement(By.xpath(OrangeHRMPIMElementLocators.TableEmployeeListData));
			// Find all child elements representing the column headers using XPath
			List<WebElement> columnHeaderElements = columnHeaderRow.findElements(By.xpath(".//div[@role='columnheader']"));
			List<WebElement> columnDataElements = colunmDataRow.findElements(By.xpath("//div[@class='oxd-table-card']"));

			// Create a list to store the column headers
			List<String> columnHeaders = new ArrayList<String>();
			List<String> columnRowData = new ArrayList<String>();

			// Iterate through each column header element and extract the text
			for (WebElement columnHeaderElement : columnHeaderElements) {
				String columnHeaderText = columnHeaderElement.getText();
				columnHeaders.add(columnHeaderText);
				System.out.println(columnHeaderText);
			} 

			System.out.println("size of columnDataElements :" + columnDataElements.size());
			// for (WebElement Trail : columnDataElements) {
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
				boolean SearchResult = SelectEmployeeListTableDataStoring(OrangeHRMPIMTestData.EmployeeId,OrangeHRMPIMTestData.FirstName, Trail, employeeListAsList);
				//boolean SearchResult = SelectEmployeeListTableDataStoring(EmployeeId, FirstName, Trail, Arrays.asList((Object) TrailList.get(J)));
				if(SearchResult) {
					break;
				}
			}
			softAssert.assertEquals(SearchResult, true);
			softAssert.assertAll(); // This line will mark the test as failed if any soft assert has failed	    		
		}
		catch(NoSuchElementException e) {
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
