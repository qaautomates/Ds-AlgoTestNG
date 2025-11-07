package dsalgo_testng.test;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import baseTest.BaseTest;
import pages.GraphPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class GraphTest extends BaseTest {

	private static ThreadLocal<GraphPage> graphPage = new ThreadLocal<>();

	@BeforeMethod
	public void initialization() {
		graphPage.set(new GraphPage(DriverFactory.getDriver(), getContext().getHelper()));
		Reporter.log("Graph Test initialization", true );

	}

	@BeforeMethod(onlyForGroups = "graphBackground")
	public void graphGetStarted() {
		getContext().getHomePage().dataStructuresGetStarted("graph");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Graph");
		Reporter.log("Graph page loaded", true);
	}

	@Test(groups = "loginRequired")
	public void navigate_Graph_Page_Link() {
		getContext().getHomePage().dataStructuresGetStarted("graph");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Graph");
		Reporter.log("Navigate Graph Page Link using Get started link", true);
	}

	@Test(groups = "loginRequired")
	public void navigate_Graph_Page_DropDown() {
		graphPage.get().selectGraphDropDownMenu("Graph");
		Assert.assertEquals(getContext().getHelper().getTitle(), "Graph");
		Reporter.log("Graph page navigated using dropdown", true);
	}

	@Test(dataProvider = "module_link", groups = {"loginRequired", "graphBackground"})
	public void navigate_SubModules_Graph(String link) {
		graphPage.get().graphClickLink(link);
		//	Assert.assertEquals(link);
		Reporter.log("Navigated to Graph page submodules", true);
	}

	@DataProvider(name = "module_link", parallel = true)
	public Object[] moduleLinkData() {
		return new Object[][] { { "graph" },
			{ "graph-representations" }, }; 
	}

	@Test(dataProvider = "subModule_TryEditor", groups = {"loginRequired", "graphBackground"})
	public void verify_TryEditor_Graph_SubModule(String link) {

		graphPage.get().graphClickLink(link);
		graphPage.get().clickGraphTryEditor();
		Assert.assertEquals(getContext().getHelper().getTitle(), "Assessment");
		Reporter.log("Navigated to Graph page Tryeditor from submodules", true);
	}

	@DataProvider(name = "subModule_TryEditor", parallel = true)
	public Object[] clickTryEditorData() {
		return new Object[] { "graph", "graph-representations" };

	}

	@Test(dataProvider = "PythonCode", groups = {"loginRequired", "graphBackground"})
	public void enter_PythonCode_TryEditor(String testId, String moduleLink) throws IOException {

		graphPage.get().graphClickLink(moduleLink);
		graphPage.get().clickGraphTryEditor();
		String pythonSheetName = ConfigReader.getProperty("pythonSheetName");
		graphPage.get().enterGraphPythonCode(pythonSheetName, testId);
		graphPage.get().clickGraphRunBtn();
		String expected = graphPage.get().readExpectedOutputForGraph(pythonSheetName, testId);
		String actual = graphPage.get().getActualOutputForGraph();
		Assert.assertEquals(expected, actual, "Expected and actual output for python code run is not same");
		Reporter.log("Run Python code for Graph page submodules", true);
	}

	@DataProvider(name = "PythonCode", parallel = true)
	public Object[][] enterGraphPythonCodeData() {
		return new Object[][] { { "TC001", "graph" }, { "TC002", "graph" },
			{ "TC003", "graph" }, { "TC001", "graph-representations" },
			{ "TC002", "graph-representations" }, { "TC003", "graph-representations" }, };

	}

	@Test(dataProvider = "module_link", groups = {"loginRequired", "graphBackground"})
	public void verify_Navigate_PracticeQns(String link) {

		graphPage.get().graphClickLink(link);
		graphPage.get().graphClickLinkpractice("Practice Questions");
		//Assert.assertEquals(helper.getTitle(), "Practice Questions");
		Reporter.log("Navigated to Practice question page for Graph submodules", true);
	}

}
