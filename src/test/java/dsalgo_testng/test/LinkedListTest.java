package dsalgo_testng.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import baseTest.BaseTest;
import pages.LinkedListPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class LinkedListTest extends BaseTest {
	private static ThreadLocal<LinkedListPage> linkedListPage = new ThreadLocal<>();
	
	@BeforeMethod
	public void initialization() {
		linkedListPage.set(new LinkedListPage(DriverFactory.getDriver(), getContext().getHelper()));
		Reporter.log("LinkedList Test initialization");

	}
	
	@BeforeMethod(onlyForGroups = "linkedListBackground")
	public void linkedListGetStarted() {
		getContext().getHomePage().dataStructuresGetStarted("linked-list");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Linked List");
		Reporter.log("Linked List page loaded");
	}
	
	@Test(groups = "loginRequired")
	public void navigate_LinkedList_Page_Link() {
		getContext().getHomePage().dataStructuresGetStarted("linked-list");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Linked List");
		Reporter.log("Linked List page loaded using Get started link");
	}
	
	@Test(groups = "loginRequired")
	public void navigate_LinkedList_Page_DropDown() {
		getContext().getHomePage().selectDropDownMenu("Linked List");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Linked List");
		Reporter.log("Linked List page loaded using dropdown");
	}
	
	@Test(dataProvider = "module_link", groups = {"loginRequired", "linkedListBackground"})
	public void navigate_SubModules_LinkedList(String link, String expected_Title) {
		linkedListPage.get().linkedListClickLink(link);
		Assert.assertEquals(link, expected_Title);
		Reporter.log("Navigated to Linked List page submodules");

	}

	@DataProvider(name = "module_link", parallel = true)
	public Object[][] moduleLinkData() {
		return new Object[][] { { "Introduction", "Introduction" },
				{ "Creating Linked LIst", "Creating Linked LIst" },
				{ "Types of Linked List", "Types of Linked List" },
				{ "Implement Linked List in Python", "Implement Linked List in Python" },
				{"Traversal", "Traversal"}, {"Insertion", "Insertion"},
				{"Deletion", "Deletion"}};
	}
	
	@Test(dataProvider = "subModule_TryEditor", groups = {"loginRequired", "linkedListBackground"})
	public void verify_TryEditor_LinkedList_SubModule(String link) {
		linkedListPage.get().linkedListClickLink(link);
		linkedListPage.get().clickLinkedListTryEditor();
		Assert.assertEquals(getContext().getHelper().getTitle(), "Assessment");
		Reporter.log("Navigated to Linked List page Tryeditor from submodules");
	}

	@DataProvider(name = "subModule_TryEditor", parallel = true)
	public Object[] TryEditorData() {
		return new Object[] { "Introduction", "Creating Linked LIst", "Types of Linked List",
				"Implement Linked List in Python", "Traversal", "Insertion", "Deletion" };
	}
	
	@Test(dataProvider = "linkedListPythonCode", groups = {"loginRequired", "linkedListBackground"})
	public void enter_PythonCode_TryEditor(String testId, String moduleLink) throws IOException {
		linkedListPage.get().linkedListClickLink(moduleLink);
		linkedListPage.get().clickLinkedListTryEditor();
		String pythonSheetName = ConfigReader.getProperty("pythonSheetName");
		linkedListPage.get().enterLinkedListPythonCode(pythonSheetName, testId);
		linkedListPage.get().clickLinkedListRunBtn();
		String expected = linkedListPage.get().readExpectedOutputForLinkedList(pythonSheetName, testId);
		String actual = linkedListPage.get().getActualOutputForLinkedList();
		Assert.assertEquals(expected, actual, "Expected and actual output for python code run is not same");
		Reporter.log("Python code run for Linked List page submodules");

	}

	@DataProvider(name = "linkedListPythonCode", parallel = true)
	public Object[][] enterLinkedListPythonCodeData() {
		return new Object[][] { { "TC001", "Introduction" }, { "TC002", "Introduction" },
				{ "TC003", "Introduction" }, { "TC001", "Creating Linked LIst" }, 
				{ "TC002", "Creating Linked LIst" }, { "TC003", "Creating Linked LIst" }, 
				{ "TC001", "Types of Linked List" }, { "TC002", "Types of Linked List" }, 
				{ "TC003", "Types of Linked List" }, { "TC001", "Implement Linked List in Python" }, 
				{ "TC002", "Implement Linked List in Python" }, { "TC003", "Implement Linked List in Python" }, 
				{"TC001", "Traversal"}, {"TC002", "Traversal"}, {"TC003", "Traversal"}, 
				{"TC001", "Insertion"}, {"TC002", "Insertion"}, {"TC003", "Insertion"},
				{"TC001", "Deletion"}, {"TC002", "Deletion"}, {"TC003", "Deletion"}};

	}
	
	@Test(dataProvider = "module_link", groups = {"loginRequired", "linkedListBackground"})
	public void verify_Navigate_PracticeQnsPage(String link, String expected_Title) {
		linkedListPage.get().linkedListClickLink(link);
		linkedListPage.get().linkedListClickLink("Practice Questions");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Practice Questions");
		Reporter.log("Navigated to Practice question page for Linked List submodules");

	}
}
