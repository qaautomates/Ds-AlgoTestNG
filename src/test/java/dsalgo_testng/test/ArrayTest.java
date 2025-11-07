package dsalgo_testng.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pages.ArrayPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class ArrayTest extends BaseTest {
	private static ThreadLocal<ArrayPage> arrayPage = new ThreadLocal<>();

	@BeforeMethod
	public void initialization() {
		arrayPage.set(new ArrayPage(DriverFactory.getDriver(), getContext().getHelper()));
		Reporter.log("Array Test initialization");
	}

	@Test(groups = "loginRequired")
	public void navigate_Array_Page_Link() {
		getContext().getHomePage().dataStructuresGetStarted("array");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Array");
		Reporter.log("Navigated to array page using Get Started Link");
	}

	@Test(groups = "loginRequired")
	public void navigate_Array_Page_DropDown() {
		getContext().getHomePage().selectDropDownMenu("Arrays");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Array");
		Reporter.log("Navigated to array page using dropdown menu");
	}

	@Test(dataProvider = "click_link", groups = "loginRequired")
	public void navigate_SubModules_Array(String link, String expected_Title) {
		getContext().getHomePage().dataStructuresGetStarted("array");
		arrayPage.get().arrayClickLink(link);
		Assert.assertEquals(link, expected_Title);
		Reporter.log("Navigated to array page submodules");
	}

	@DataProvider(name = "click_link", parallel = true)
	public Object[][] clickLinkData() {
		return new Object[][] { { "Arrays in Python", "Arrays in Python" },
				{ "Arrays Using List", "Arrays Using List" },
				{ "Basic Operations in Lists", "Basic Operations in Lists" },
				{ "Applications of Array", "Applications of Array" }, };
	}

	@Test(dataProvider = "subModule_TryEditor", groups = "loginRequired")
	public void verify_TryEditor_Array_SubModule(String link) {
		getContext().getHomePage().dataStructuresGetStarted("array");
		arrayPage.get().arrayClickLink(link);
		arrayPage.get().clickArrayTryEditor();
		Assert.assertEquals(getContext().getHelper().getTitle(), "Assessment");
		Reporter.log("Navigated to Try Editor page for array submodules");
	}

	@DataProvider(name = "subModule_TryEditor", parallel = true)
	public Object[] clickTryEditorData() {
		return new Object[] { "Arrays in Python", "Arrays Using List", "Basic Operations in Lists",
				"Applications of Array" };
	}

	@Test(dataProvider = "PythonCode", groups = "loginRequired")
	public void enter_PythonCode_TryEditor(String testId, String moduleLink) throws IOException {
		getContext().getHomePage().dataStructuresGetStarted("array");
		arrayPage.get().arrayClickLink(moduleLink);
		arrayPage.get().clickArrayTryEditor();
		String pythonSheetName = ConfigReader.getProperty("pythonSheetName");
		arrayPage.get().enterArrayPythonCode(pythonSheetName, testId);
		arrayPage.get().clickArrayRunBtn();
		String expected = arrayPage.get().readExpectedOutputForArray(pythonSheetName, testId);
		String actual = arrayPage.get().getActualOutputForArray();
		Assert.assertEquals(expected, actual, "Expected and actual output for python code run is not same");
		Reporter.log("Verified Python code run for array submodules");

	}

	@DataProvider(name = "PythonCode", parallel = true)
	public Object[][] enterPythonCodeData() {
		return new Object[][] { { "TC001", "Arrays in Python" }, { "TC002", "Arrays in Python" },
				{ "TC003", "Arrays in Python" }, { "TC001", "Arrays Using List" }, { "TC002", "Arrays Using List" },
				{ "TC003", "Arrays Using List" }, { "TC001", "Basic Operations in Lists" },
				{ "TC002", "Basic Operations in Lists" }, { "TC003", "Basic Operations in Lists" },
				{ "TC001", "Applications of Array" }, { "TC002", "Applications of Array" },
				{ "TC003", "Applications of Array" }, };

	}

	@Test(dataProvider = "click_link", groups = "loginRequired")
	public void verify_Navigate_PracticeQns(String link, String expected_Title) {
		getContext().getHomePage().dataStructuresGetStarted("array");
		arrayPage.get().arrayClickLink(link);
		arrayPage.get().arrayClickLink("Practice Questions");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Practice Questions");
		Reporter.log("Navigated to practice questions page for array submodules");
	}

	@Test(dataProvider = "PracticeQuestions", groups = "loginRequired")
	public void verify_PracticeQns_Links(String link, String expected_Question) {
		getContext().getHomePage().dataStructuresGetStarted("array");
		arrayPage.get().arrayClickLink("Arrays in Python");
		arrayPage.get().moveToPracticeQuestionsEditor(link);
		Assert.assertEquals(arrayPage.get().getAssessmentQuestion(), expected_Question);
		Reporter.log("Verified all practice questions link for array");
	}

	@DataProvider(name = "PracticeQuestions", parallel = true)
	public Object[][] verify_PracticeQuestionsLinks() {
		return new Object[][] { { "Search the array", "search" }, { "Max Consecutive Ones", "findMaxConsecutiveOnes" },
				{ "Find Numbers with Even Number of Digits", "findNumbers" },
				{ "Squares of  a Sorted Array", "sortedSquares" }

		};
	}

	@Test(dataProvider = "PracticeQuestionsPythonCode", groups = "loginRequired")
	public void enter_PythonCode_PracticeQns_RunBtn(String Testcase_ID, String question_link) throws IOException {
		getContext().getHomePage().dataStructuresGetStarted("array");
		arrayPage.get().arrayClickLink("Arrays in Python");
		arrayPage.get().moveToPracticeQuestionsEditor(question_link);
		String practiceqnsSheet = ConfigReader.getProperty("practiceQnsSheeetName");
		arrayPage.get().enterArrayPythonCode(practiceqnsSheet, Testcase_ID);
		arrayPage.get().clickArrayRunBtn();
		String expected = arrayPage.get().readExpectedOutputForArray(practiceqnsSheet, Testcase_ID);
		String actual = arrayPage.get().getActualOutputForArray();
		Assert.assertEquals(expected, actual, "Expected and actual output for python code run is not same");
		Reporter.log("Verified python code run for array practice questions");
	}

	@DataProvider(name = "PracticeQuestionsPythonCode", parallel = true)
	public Object[][] enterCodePracticeQuestionsData() {
		return new Object[][] { { "TC001", "Search the array" }, { "TC002", "Search the array" },
				{ "TC003", "Search the array" }, { "TC001", "Max Consecutive Ones" },
				{ "TC002", "Max Consecutive Ones" }, { "TC003", "Max Consecutive Ones" },
				{ "TC001", "Find Numbers with Even Number of Digits" },
				{ "TC002", "Find Numbers with Even Number of Digits" },
				{ "TC003", "Find Numbers with Even Number of Digits" }, { "TC001", "Squares of  a Sorted Array" },
				{ "TC002", "Squares of  a Sorted Array" }, { "TC003", "Squares of  a Sorted Array" }, };

	}

	@Test(dataProvider = "PracticeQuestionsPythonCode", groups = "loginRequired")
	public void enter_PythonCode_PracticeQns_SubmitBtn(String Testcase_ID, String question_link) throws IOException {
		getContext().getHomePage().dataStructuresGetStarted("array");
		arrayPage.get().arrayClickLink("Arrays in Python");
		arrayPage.get().moveToPracticeQuestionsEditor(question_link);
		String practiceqnsSheet = ConfigReader.getProperty("practiceQnsSheeetName");
		arrayPage.get().enterArrayPythonCode(practiceqnsSheet, Testcase_ID);
		arrayPage.get().clickArraySubmitBtn();
		Assert.assertEquals(arrayPage.get().getSubmitMesssage(), "Submitted Successfully",
				"Expected and actual output for python code run is not same");
		Reporter.log("Verified python code submit for array practice questions");
	}

}
