package sampleTestNG.TestSuit;

public class OrangeHRMPIMElementLocators {
	
	//configurationsetup class Element Locators...
	 public static final String UserNameOfOrangeHRM="//input[@name='username']";
	 public static final String PasswordOfOrangeHRM="//div[@class='oxd-form-row']//following::input[@name='password']";
	 public static final String LoginButtonOrangeHRM="//button[@type='submit']";
	 public static final String sideNavigationBarRightSide="//*[@class='oxd-icon bi-chevron-right']";
	 public static final String sideNavigationBarLeftSide="//*[@class='oxd-icon bi-chevron-left']";
	 
	    //PIMmodule class Element Locators...
	 public static final String PimNavBar="//nav[@role='navigation' and @aria-label='Topbar Menu']";
	 public static final String SideNaviationAllOptions="//*[@class='oxd-main-menu']";
	 public static final String FirstNameTextBox="//input[@name=\"firstName\"]";
	 public static final String LastNameTextBox="//input[@name=\"lastName\"]";
	 public static final String EmployeeIdTextBox="//div[@class='oxd-input-group__label-wrapper']//following::input[@class=\"oxd-input oxd-input--active\"]";
	 public static final String UserNameTextBox="//label[text()='Username']//following::input[1]";
	 public static final String PasswordTextBox="//label[text()='Username']//following::input[4]";
	 public static final String ConfirmPasswordTextBox="//label[text()='Username']//following::input[5]";
	 public static final String CheckBox="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/label/span";
	 public static final String SaveButton="//button[@type='submit']";
	 public static final String EmployementStatusDropDownTextBox="//label[text()='Employment Status']/following::div[4][@class='oxd-select-text-input']";
	 public static final String EmployeeName="//label[text()='Employee Name']//following::div[3]//input";
	 public static final String Employee_Id="//label[text()='Employee Id']//following::div[1]/input";
	 public static final String SupervisorName="//label[text()='Supervisor Name']//following::div[1]//child::div//input";
	 public static final String JobTitle="//label[text()='Job Title']//following::div[@class='oxd-select-text--after'][1]";
	 public static final String EmpJobRoleSubRole="//label[text()='Sub Unit']//following::div[4]";
	 public static final String JobTitledropdownList="//label[text()='Job Title']//following::div[4]";
	 public static final String SearchButton="//button[@type='submit' and text()=' Search ']";
	 public static final String ResetButton="//button[@type='reset' and text()=' Reset ']";
	 public static final String SelectOptionforEmpStatus="//span[text()='Freelance']";
	 public static final String SelectOptionforempRole="//span[text()='Quality Assurance']";
	 public static final String TableInEmployeeSearchResult="//div[@class='orangehrm-container']//child::div[@role='table']";
	 public static final String TableEmployeeListHeadLine="//div[@role='table']//following::div[1][@role='row']";
	 public static final String TableEmployeeListData="//div[@class='oxd-table-body' and @role='rowgroup']";

}
