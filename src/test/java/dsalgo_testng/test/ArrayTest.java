package dsalgo_testng.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pages.ArrayPage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class ArrayTest extends BaseTest {

	LoginPage loginPage;
	ArrayPage arrayPage;

	public ArrayTest() {

	}

	@BeforeMethod
	public void initialization() {
		arrayPage = new ArrayPage(DriverFactory.getDriver(), helper);
		System.out.println("Array Test initialization");

	}

	@Test(groups = "loginRequired")
	public void navigate_Array_Page_Link() {
		homePage.dataStructuresGetStarted("array");
		Assert.assertEquals(helper.getTitle(), "Array");
		System.out.println("navigate_Array_Page_Link");
	}

	@Test(groups = "loginRequired")
	public void navigate_Array_Page_DropDown() {
		homePage.selectDropDownMenu("Arrays");
		Assert.assertEquals(helper.getTitle(), "Array");

	}

	@Test(dataProvider = "click_link", groups = "loginRequired")
	public void navigate_SubModules_Array(String link, String expected_Title) {
		homePage.dataStructuresGetStarted("array");
		arrayPage.arrayClickLink(link);
		Assert.assertEquals(link, expected_Title);

	}

	@DataProvider(name = "click_link")
	public Object[][] clickLinkData() {
		return new Object[][] { { "Arrays in Python", "Arrays in Python" },
				{ "Arrays Using List", "Arrays Using List" },
				{ "Basic Operations in Lists", "Basic Operations in Lists" },
				{ "Applications of Array", "Applications of Array" }, };
	}

	@Test(dataProvider = "subModule_TryEditor", groups = "loginRequired")
	public void verify_TryEditor_Array_SubModule(String link) {
		homePage.dataStructuresGetStarted("array");
		arrayPage.arrayClickLink(link);
		arrayPage.clickArrayTryEditor();
		Assert.assertEquals(helper.getTitle(), "Assessment");
	}

	@DataProvider(name = "subModule_TryEditor")
	public Object[] clickTryEditorData() {
		return new Object[] { "Arrays in Python", "Arrays Using List", "Basic Operations in Lists",
				"Applications of Array" };
	}

	@Test(dataProvider = "PythonCode", groups = "loginRequired")
	public void enter_PythonCode_TryEditor(String testId, String moduleLink) throws IOException {
		homePage.dataStructuresGetStarted("array");
		arrayPage.arrayClickLink(moduleLink);
		arrayPage.clickArrayTryEditor();
		String pythonSheetName = ConfigReader.getProperty("pythonSheetName");
		arrayPage.enterArrayPythonCode(pythonSheetName, testId);
		arrayPage.clickArrayRunBtn();
		String expected = arrayPage.readExpectedOutputForArray(pythonSheetName, testId);
		String actual = arrayPage.getActualOutputForArray();
		Assert.assertEquals(expected, actual, "Expected and actual output for python code run is not same");

	}

	@DataProvider(name = "PythonCode")
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
		homePage.dataStructuresGetStarted("array");
		arrayPage.arrayClickLink(link);
		arrayPage.arrayClickLink("Practice Questions");
		Assert.assertEquals(helper.getTitle(), "Practice Questions");

	}

	@Test(dataProvider = "PracticeQuestions", groups = "loginRequired")
	public void verify_PracticeQns_Links(String link, String expected_Question) {
		homePage.dataStructuresGetStarted("array");
		arrayPage.arrayClickLink("Arrays in Python");
		arrayPage.moveToPracticeQuestionsEditor(link);
		Assert.assertEquals(arrayPage.getAssessmentQuestion(), expected_Question);

	}

	@DataProvider(name = "PracticeQuestions")
	public Object[][] verify_PracticeQuestionsLinks() {
		return new Object[][] { { "Search the array", "search" }, { "Max Consecutive Ones", "findMaxConsecutiveOnes" },
				{ "Find Numbers with Even Number of Digits", "findNumbers" },
				{ "Squares of  a Sorted Array", "sortedSquares" }

		};

	}

	@Test(dataProvider = "PracticeQuestionsPythonCode", groups = "loginRequired")
	public void enter_PythonCode_PracticeQns_RunBtn(String Testcase_ID, String question_link) throws IOException {
		homePage.dataStructuresGetStarted("array");
		arrayPage.arrayClickLink("Arrays in Python");
		arrayPage.moveToPracticeQuestionsEditor(question_link);
		String practiceqnsSheet = ConfigReader.getProperty("practiceQnsSheeetName");
		arrayPage.enterArrayPythonCode(practiceqnsSheet, Testcase_ID);
		arrayPage.clickArrayRunBtn();
		String expected = arrayPage.readExpectedOutputForArray(practiceqnsSheet, Testcase_ID);
		String actual = arrayPage.getActualOutputForArray();
		Assert.assertEquals(expected, actual, "Expected and actual output for python code run is not same");

	}

	@DataProvider(name = "PracticeQuestionsPythonCode")
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
		homePage.dataStructuresGetStarted("array");
		arrayPage.arrayClickLink("Arrays in Python");
		arrayPage.moveToPracticeQuestionsEditor(question_link);
		String practiceqnsSheet = ConfigReader.getProperty("practiceQnsSheeetName");
		arrayPage.enterArrayPythonCode(practiceqnsSheet, Testcase_ID);
		arrayPage.clickArraySubmitBtn();
		Assert.assertEquals(arrayPage.getSubmitMesssage(), "Submitted Successfully",
				"Expected and actual output for python code run is not same");

	}

}
