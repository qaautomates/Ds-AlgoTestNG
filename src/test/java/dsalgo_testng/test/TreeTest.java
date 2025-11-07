package dsalgo_testng.test;

import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import baseTest.BaseTest;
import pages.TreePage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class TreeTest extends BaseTest {

	private static ThreadLocal<TreePage> treePage = new ThreadLocal<>();

	@BeforeMethod
	public void initialization() {
		treePage.set(new TreePage(DriverFactory.getDriver(), getContext().getHelper()));
		Reporter.log("Tree Module Initialization");
	}

	@Test(groups = "loginRequired")
	public void naviagte_Tree_DataStructure_getStdLink() {
		getContext().getHomePage().dataStructuresGetStarted("tree");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Tree");
		Reporter.log("User selected Tree using get Started link");
	}

	@Test(groups = "loginRequired")
	public void navigate_Tree_Page_DropDown() {
		getContext().getHomePage().selectDropDownMenu("Tree");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Tree");
		Reporter.log("User selected Tree using Dropdown");

	}

	@DataProvider(name = "subModuleLinks")
	public Object[][] subModuleLinks() {
		Reporter.log("SubModuleLinks DataProvider for Tree is executed");

		return new Object[][] { { "Overview of Trees", "Overview of Trees" }, { "Terminologies", "Terminologies" },
				{ "Types of Trees", "Types of Trees" }, { "Tree Traversals", "Tree Traversals" },
				{ "Traversals-Illustration", "Traversals-Illustration" }, { "Binary Trees", "Binary Trees" },
				{ "Types of Binary Trees", "Types of Binary Trees" },
				{ "Implementation in Python", "Implementation in Python" },
				{ "Binary Tree Traversals", "Binary Tree Traversals" },
				{ "Implementation of Binary Trees", "Implementation of Binary Trees" },
				{ "Applications of Binary trees", "Applications of Binary trees" },
				{ "Binary Search Trees", "Binary Search Trees" }, { "Implementation Of BST", "Implementation Of BST" },

		};
	}

	@Test(dataProvider = "subModuleLinks", groups = "loginRequired")
	public void navigate_SubModules_Tree(String link, String expected_Title) {
		getContext().getHomePage().dataStructuresGetStarted("tree");
		treePage.get().treeClickLink(link);
		Assert.assertEquals(link, expected_Title);
		Reporter.log("Verified the Tree sub module link page title");

	}

	@DataProvider(name = "subModule_TryEditor")
	public Object[] clickTryEditorData() {
		Reporter.log("subModule_TryEditor data provider is executed");
		return new Object[] { "Overview of Trees", "Terminologies", "Types of Trees", "Tree Traversals",
				"Traversals-Illustration", "Binary Trees", "Types of Binary Trees", "Implementation in Python",
				"Binary Tree Traversals", "Implementation of Binary Trees", "Applications of Binary trees",
				"Binary Search Trees", "Implementation Of BST" };

	}

	@Test(dataProvider = "subModule_TryEditor", groups = "loginRequired")
	public void navigate_TryEditor_TreePage(String link) {
		getContext().getHomePage().dataStructuresGetStarted("tree");
		treePage.get().treeClickLink(link);
		treePage.get().clickTreeTryEditor();
		Assert.assertEquals(getContext().getHelper().getTitle(), "Assessment");
		Reporter.log("User navigated to tree sub module try editor page and verified the title ");

	}

	@DataProvider(name = "PythonCode")
	public Object[][] enterPythonCodeData() {
		Reporter.log("PythonCode data provider is executed");
		return new Object[][] { { "TC001", "Overview of Trees" }, { "TC002", "Overview of Trees" },
				{ "TC003", "Overview of Trees" }, { "TC001", "Terminologies" }, { "TC002", "Terminologies" },
				{ "TC003", "Terminologies" }, { "TC001", "Types of Trees" }, { "TC002", "Types of Trees" },
				{ "TC003", "Types of Trees" }, { "TC001", "Tree Traversals" }, { "TC002", "Tree Traversals" },
				{ "TC003", "Tree Traversals" }, { "TC001", "Traversals-Illustration" },
				{ "TC002", "Traversals-Illustration" }, { "TC003", "Traversals-Illustration" },
				{ "TC001", "Binary Trees" }, { "TC002", "Binary Trees" }, { "TC003", "Binary Trees" },
				{ "TC001", "Types of Binary Trees" }, { "TC002", "Types of Binary Trees" },
				{ "TC003", "Types of Binary Trees" }, { "TC001", "Implementation in Python" },
				{ "TC002", "Implementation in Python" }, { "TC003", "Implementation in Python" },
				{ "TC001", "Binary Tree Traversals" }, { "TC002", "Binary Tree Traversals" },
				{ "TC003", "Binary Tree Traversals" }, { "TC001", "Implementation of Binary Trees" },
				{ "TC002", "Implementation of Binary Trees" }, { "TC003", "Implementation of Binary Trees" },
				{ "TC001", "Applications of Binary trees" }, { "TC002", "Applications of Binary trees" },
				{ "TC003", "Applications of Binary trees" }, { "TC001", "Binary Search Trees" },
				{ "TC002", "Binary Search Trees" }, { "TC003", "Binary Search Trees" },
				{ "TC001", "Implementation Of BST" }, { "TC002", "Implementation Of BST" },
				{ "TC003", "Implementation Of BST" }, };

	}

	@Test(dataProvider = "PythonCode", groups = "loginRequired")
	public void enter_PythonCode_TryEditor(String testId, String moduleLink) throws IOException {
		getContext().getHomePage().dataStructuresGetStarted("tree");
		treePage.get().treeClickLink(moduleLink);
		treePage.get().clickTreeTryEditor();
		String pythonSheetName = ConfigReader.getProperty("pythonSheetName");
		treePage.get().enterTreePythonCode(pythonSheetName, testId);
		treePage.get().clickTreeRunBtn();
		String expectResult = treePage.get().readExpectedOutputForTree(pythonSheetName, testId);
		String ActualResult = treePage.get().getActualOutputForTree();
		Assert.assertEquals(ActualResult, expectResult);
		Reporter.log(
				"Entered the python code and validated the expected and actual output for all the tree module sub links");

	}
}
