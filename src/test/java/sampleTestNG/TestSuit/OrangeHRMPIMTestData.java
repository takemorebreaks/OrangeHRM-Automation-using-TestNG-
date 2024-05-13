package sampleTestNG.TestSuit;

import org.testng.annotations.DataProvider;

public class OrangeHRMPIMTestData {
	
	//PIMmodulePositiveTestCase Variable and values...
	public static final String FirstName="Shiva";
	public static final String LastName="Prasad";
	public static final String UserName="ShivaPrasad";
	public static final String Password="ShivaPrasad@123";
	public static final String EmployeeId="1111";
	public static final String SuperViceName="Odis Adalwin";
	public static final String expectedResultForEmpStatus = "Part-Time Internship";
	public static final String expectedResultForJobTitle = "Quality Assurance";
	public static final String expectedResultforSubUnit= "Automaton Tester";
	
	
	@DataProvider
	public static Object[][] CheckFirstNameandLastName() {
		return new Object[][] {
			{"FirstName", new Object[][] {{"133!@@F"},{"   fabc"},{"!@f#abc"},{"5464788"}}},
			{"MiddleName", new Object[][] {{"133!@@"},{"  s abc"},{"!s@#abc"},{"4548485"}}},
			{"LastName", new Object[][] {{"133!@@"},{"  t abc"},{"!@t#abc"},{"0000000"}}}
		};
	}
}
