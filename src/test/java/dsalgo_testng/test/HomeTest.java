package dsalgo_testng.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.BaseTest;

public class HomeTest extends BaseTest {
	
	@BeforeMethod(onlyForGroups = "homePageBackground")
	public void homePageGetStarted() {
		getContext().getHomePage().homeGetStartedBtn();
		Reporter.log("Home Test: Get Started");
	}

	@Test
	public void verifyHomePageTitle() {
		getContext().getHomePage().homeGetStartedBtn();
		Assert.assertEquals(getContext().getHelper().getTitle(), "NumpyNinja");
		Reporter.log("Home Page Title verified");
	}
	
	@Test (dataProvider = "dropdownModules", groups = "homePageBackground")
	public void verifyErrorMessageDropdownSelect(String module) {
		getContext().getHomePage().selectDropDownMenu(module);
		Reporter.log("Verified Error message from dropdown menu click");
	}
	
	@DataProvider(name = "dropdownModules") 
	public Object[] getModulesDropdownData() {
		return new Object[] {"Arrays", "Linked List", "Stack", "Queue", "Tree", "Graph"};
	}
	
	@Test (dataProvider = "modules", groups = "homePageBackground")
	public void verifyErrorMessageLinkClick(String module) {
		getContext().getHomePage().dataStructuresGetStarted(module);
		Reporter.log("Verified Error Message from get started link");
	}
	
	@DataProvider(name = "modules") 
	public Object[] getModulesData() {
		return new Object[] {"data-structures-introduction", "array", 
				"linked-list", "stack", "queue", "tree", "graph"};
	}
}
