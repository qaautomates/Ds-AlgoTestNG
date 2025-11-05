package pages;

import java.io.IOException;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ExcelSheetHandling;

public class GraphPage {

	private static Logger logger = LogManager.getLogger();
	private WebDriver driver;
	private Helper helper;

	public GraphPage(WebDriver driver, Helper helper) {
		this.driver = driver;
		this.helper = helper;
	}

	public void loginToPortalGraph() {

		helper.homeGetStartedBtn();
		helper.clickSignIn();
		helper.login();
		logger.info("Logging in portal to test Graph module");
	}

	public void selectGraphFromDropDown(String string) {
		helper.selectDropDownMenu(string);

	}

	public void clickGraphTryEditor() {
		helper.clickTryEditor();
	}

	public void graphGetStarted() {
		helper.dataStructuresGetStarted("graph");

	}

	public void graphClickLink(String string) {
		driver.findElement(By.xpath("//a[@href='" + string + "']")).click();
	}
	
	public void enterGraphPythonCode(String sheet, String testcase_id) throws IOException {

		ExcelSheetHandling excelReader = new ExcelSheetHandling();
		HashMap<String, String> code = excelReader.readExcelSheet(sheet, testcase_id);
		helper.enterPythonCode(code.get("pythonCode"));

	}

	public void clickGraphRunBtn() {
		helper.clickRunButton();
	}

	public String readExpectedOutputForGraph(String sheet, String testcase_id) throws IOException {
		return helper.readFromExcel(sheet, testcase_id, "Result");
	}

	public String getActualOutputForGraph() {
		return helper.readActualOutput();
	}

	public void graphClickLinkpractice(String string) {
		helper.clickLink(string);
	}
}

