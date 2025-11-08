package dsalgo_testng.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.BaseTest;

import pages.DataStructurePage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class DataStructureTest extends BaseTest {
	private static ThreadLocal<DataStructurePage> datstructurePage = new ThreadLocal<>();
	

	@BeforeMethod
	public void initialization() {
		datstructurePage.set(new DataStructurePage(DriverFactory.getDriver(), getContext().getHelper()));
		Reporter.log("Data Structure Test initialization");

	}
	
	@BeforeMethod(onlyForGroups = "DataStructureBackground")
	public void DataStructureGetStarted() {
		getContext().getHomePage().dataStructuresGetStarted("data-structures-introduction");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Data Structures-Introduction");
		Reporter.log("DataStructure Introduction page loaded");
	}
	
	@Test(groups = "loginRequired")
	public void navigate_DataStructureIntro_Page_Link() {
		getContext().getHomePage().dataStructuresGetStarted("data-structures-introduction");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Data Structures-Introduction");
		Reporter.log("Data Structures-Introduction page loaded");
	}
	
	@Test(dataProvider = "click_link", groups = {"loginRequired", "DataStructureBackground"})
	public void navigate_SubModules_DataStructureIntro(String link, String expected_Title) {
		datstructurePage.get().datastructureClickLink(link);
		Assert.assertEquals(link, expected_Title);
		Reporter.log("Data Structures-Introduction page submodules");
	}
	
	@DataProvider(name = "click_link", parallel = true)
	public Object[][] clickLinkData() {
		return new Object[][] { { "Time Complexity", "Time Complexity" },};
	}
	
	@Test(dataProvider = "subModule_TryEditor", groups = {"loginRequired", "DataStructureBackground"})
	public void verify_TryEditor_DataStructureIntro_SubModule(String link) {
		datstructurePage.get().datastructureClickLink(link);
		datstructurePage.get().ClickDataStructureTryEditor();
		Assert.assertEquals(getContext().getHelper().getTitle(), "Assessment");
		Reporter.log("Navigated to Data Structures-Introduction page Tryeditor from submodules");
	}
	
	@DataProvider(name = "subModule_TryEditor", parallel = true)
	public Object[] clickTryEditorData() {
		return new Object[] { "Time Complexity"};
	}
	
	@Test(dataProvider = "PythonCode", groups = {"loginRequired", "DataStructureBackground"})
	public void enter_PythonCode_TryEditor(String testId, String moduleLink) throws IOException {
		datstructurePage.get().datastructureClickLink(moduleLink);
		datstructurePage.get().ClickDataStructureTryEditor();
		String pythonSheetName = ConfigReader.getProperty("pythonSheetName");
		datstructurePage.get().enterDataStructurePythonCode(pythonSheetName, testId);
		datstructurePage.get().DataStructureRunBtn();
		String expected = datstructurePage.get().readExpectedOutputForDataStructure(pythonSheetName, testId);
		String actual = datstructurePage.get().getActualOutputForDataStructure();
		Assert.assertEquals(expected, actual, "Expected and actual output for python code run is not same");
		Reporter.log("Python code run for Data Structure Introduction page submodules");	
	}

	@DataProvider(name = "PythonCode", parallel = true)
	public Object[][] enterPythonCodeData() {
		return new Object[][] { { "TC001", "Time Complexity" }, { "TC002", "Time Complexity" },
				{ "TC003", "Time Complexity" },};
	}
	
		
	@Test(dataProvider = "click_link", groups = {"loginRequired", "DataStructureBackground"})
	public void verify_Navigate_PracticeQns(String link, String expected_Title) {
		datstructurePage.get().datastructureClickLink(link);
		datstructurePage.get().datastructureClickLink("Practice Questions");
		Assert.assertEquals(getContext().getHelper().getTitle(),"Practice Questions");
		Reporter.log("Navigated to Practice question page for Data Structure Introduction submodules");	
	}
	
}
