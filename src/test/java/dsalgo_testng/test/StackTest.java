package dsalgo_testng.test;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import baseTest.BaseTest;
import pages.StackPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class StackTest extends BaseTest {

	private static ThreadLocal<StackPage> stackPage = new ThreadLocal<>();

	@BeforeMethod
	public void initialization() {
		stackPage.set(new StackPage(DriverFactory.getDriver(), getContext().getHelper()));
		Reporter.log("Stack Test initialization", true);

	}

	@BeforeMethod(onlyForGroups = "stackBackground")
	public void stackGetStarted() {
		getContext().getHomePage().dataStructuresGetStarted("stack");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Stack");
		Reporter.log("Stack page loaded", true);
	}

	@Test(groups = "loginRequired")
	public void navigate_Stack_Page_Link() {
		getContext().getHomePage().dataStructuresGetStarted("stack");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Stack");
		Reporter.log("Navigate Stack Page Link using Get started link", true);
	}

	@Test(groups = "loginRequired")
	public void navigate_Stack_Page_DropDown() {
		getContext().getHomePage().selectDropDownMenu("Stack");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Stack");
		Reporter.log("Stack page navigated using dropdown", true);
	}

	@Test(dataProvider = "module_link", groups = {"loginRequired", "stackBackground"})
	public void navigate_SubModules_Stack(String link, String expected_Title) {
		stackPage.get().stackClickLink(link);
		Assert.assertEquals(link, expected_Title);
		Reporter.log("Navigated to Stack page submodules", true);
	}

	@DataProvider(name = "module_link", parallel = true)
	public Object[][] moduleLinkData() {
		return new Object[][] { { "Operations in Stack", "Operations in Stack" },
			{ "Implementation", "Implementation" },
			{ "Applications", "Applications" }, };
	}

	@Test(dataProvider = "subModule_TryEditor", groups = {"loginRequired", "stackBackgroud"})
	public void verify_TryEditor_Stack_SubModule(String link) {

		stackPage.get().stackClickLink(link);
		stackPage.get().clickStackTryEditor();
		Assert.assertEquals(getContext().getHelper().getTitle(), "Assessment");
		Reporter.log("Navigated to Stack page Tryeditor from submodules", true);
	}

	@DataProvider(name = "subModule_TryEditor", parallel = true)
	public Object[] clickTryEditorData() {
		return new Object[] { "Operations in Stack", "Implementation", "Applications" };

	}

	@Test(dataProvider = "stackPythonCode", groups = {"loginRequired", "stackBackground"})
	public void enter_PythonCode_TryEditor(String testId, String moduleLink) throws IOException {

		stackPage.get().stackClickLink(moduleLink);
		stackPage.get().clickStackTryEditor();
		String pythonSheetName = ConfigReader.getProperty("pythonSheetName");
		stackPage.get().enterStackPythonCode(pythonSheetName, testId);
		stackPage.get().clickStackRunBtn();
		String expected = stackPage.get().readExpectedOutputForStack(pythonSheetName, testId);
		String actual = stackPage.get().getActualOutputForStack();
		Assert.assertEquals(expected, actual, "Expected and actual output for python code run is not same");
		Reporter.log("Run Python code for Stack page submodules", true);
	}

	@DataProvider(name = "stackPythonCode", parallel = true)
	public Object[][] enterStackPythonCodeData() {
		return new Object[][] { { "TC001", "Operations in Stack" }, { "TC002", "Operations in Stack" },
			{ "TC003", "Operations in Stack" }, { "TC001", "Implementation" }, { "TC002", "Implementation" },
			{ "TC003", "Implementation" }, { "TC001", "Applications" },
			{ "TC002", "Applications" }, { "TC003", "Applications" }, };

	}

	@Test(dataProvider = "module_link", groups = {"loginRequired", "stackBackground"})
	public void verify_Navigate_PracticeQns(String link, String expected_Title) {

		stackPage.get().stackClickLink(link);
		stackPage.get().stackClickLink("Practice Questions");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Practice Questions");
		Reporter.log("Navigated to Practice question page for Stack submodules", true);
	}

}
