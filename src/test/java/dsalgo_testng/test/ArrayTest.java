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
		loginPage = new LoginPage(DriverFactory.getDriver(), helper);
		arrayPage = new ArrayPage(DriverFactory.getDriver(), helper);
		System.out.println("Array Test initialization");
		loginPage.clickSignIn();
		loginPage.loginToPortal();
	}

	// @Test
	public void login() {
		System.out.println("Array Test login");
		Assert.assertEquals(helper.getTitle(), "NumpyNinja");
	}

	@Test
	public void navigate_Array_Page_Link() {
		homePage.dataStructuresGetStarted("array");
		Assert.assertEquals(helper.getTitle(), "Array");
		System.out.println("navigate_Array_Page_Link");
	}

	@Test
	public void navigate_Array_Page_DropDown() {
		homePage.selectDropDownMenu("Arrays");
		Assert.assertEquals(helper.getTitle(), "Array");

	}

	@Test(dataProvider = "click_link")
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

	@Test(dataProvider = "subModule_TryEditor")
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

	@Test(dataProvider = "PythonCode")
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

}
