package dsalgo_testng.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import utilities.ConfigReader;

public class QueueTest extends BaseTest {
	

	@BeforeMethod(onlyForGroups = "queueBackground")
	public void linkedListGetStarted() {
		getContext().getHomePage().dataStructuresGetStarted("queue");
		Reporter.log("Queue page loaded");
	}
	
	@Test(groups = "loginRequired")
	public void navigate_Queue_Page_Link() {
		getContext().getHomePage().dataStructuresGetStarted("queue");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Queue");
		Reporter.log("Queue page loaded using Get started link");
	}
	
	@Test(groups = "loginRequired")
	public void navigate_Queue_Page_DropDown() {
		getContext().getHomePage().selectDropDownMenu("Queue");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Queue");
		Reporter.log("Queue page loaded using dropdown");
	}
	
	@Test(dataProvider = "module_link", groups = {"loginRequired", "queueBackground"})
	public void navigate_SubModules_Queue(String link, String expected_Title) {
		getContext().getQueuePage().queueClickLink(link);
		Assert.assertEquals(link, expected_Title);
		Reporter.log("Navigated to Queue page submodules");

	}
	
	@DataProvider(name = "module_link", parallel = true)
	public Object[][] moduleLinkData() {
		return new Object[][] { { "Implementation of Queue in Python", "Implementation of Queue in Python" },
				{ "Implementation using collections.deque", "Implementation using collections.deque" },
				{ "Implementation using array", "Implementation using array" },
				{"Queue Operations", "Queue Operations"},};
	}
	
	@Test(dataProvider = "subModule_TryEditor", groups = {"loginRequired", "queueBackground"})
	public void verify_TryEditor_Queue_SubModule(String link) {
		getContext().getQueuePage().queueClickLink(link);
		getContext().getQueuePage().ClickQueueTryEditor();
		Assert.assertEquals(getContext().getHelper().getTitle(), "Assessment");
		Reporter.log("Navigated to Queue page Tryeditor from submodules");
	}
	
	@DataProvider(name = "subModule_TryEditor", parallel = true)
	public Object[] TryEditorData() {
		return new Object[] { "Implementation of Queue in Python", "Implementation using collections.deque", "Implementation using array",
				"Queue Operations" };
	}
	
	@Test(dataProvider = "queuePythonCode", groups = {"loginRequired", "queueBackground"})
	public void enter_PythonCode_TryEditor(String testId, String moduleLink) throws IOException {
		getContext().getQueuePage().queueClickLink(moduleLink);
		getContext().getQueuePage().ClickQueueTryEditor();
		String pythonSheetName = ConfigReader.getProperty("pythonSheetName");
		String code = getContext().getExcelReader().readFromExcel(pythonSheetName, testId, "pythonCode");
		getContext().getQueuePage().enterQueuePythonCode(code);
		getContext().getQueuePage().QueueRunBtn();
		String expected = getContext().getExcelReader().readFromExcel(pythonSheetName, testId, "Result");
		String actual = getContext().getQueuePage().getActualOutputForQueue();
		Assert.assertEquals(expected, actual, "Expected and actual output for python code run is not same");
		Reporter.log("Python code run for Queue page submodules");

	}
	@DataProvider(name = "queuePythonCode", parallel = true)
	public Object[][] enterqueuePythonCodeData() {
		return new Object[][] { { "TC001", "Implementation of Queue in Python" }, { "TC002", "Implementation of Queue in Python" },
				{ "TC003", "Implementation of Queue in Python" }, { "TC001", "Implementation using collections.deque" }, 
				{ "TC002", "Implementation using collections.deque" }, { "TC003", "Implementation using collections.deque" }, 
				{ "TC001", "Implementation using array" }, { "TC002", "Implementation using array" }, 
				{ "TC003", "Implementation using array" }, { "TC001", "Queue Operations" }, 
				{ "TC002", "Queue Operations" }, { "TC003", "Queue Operations" },};
	}
	
	@Test(dataProvider = "module_link", groups = {"loginRequired", "queueBackground"})
	public void verify_Navigate_PracticeQnsPage(String link, String expected_Title) {
		getContext().getQueuePage().queueClickLink(link);
		getContext().getQueuePage().queueClickLink("Practice Questions");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Practice Questions");
		Reporter.log("Navigated to Practice question page for Queue submodules");

	}
}
