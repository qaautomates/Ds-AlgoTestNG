package pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DataStructurePage {
	
	private static Logger logger = LogManager.getLogger();
	
	private Helper helper;
	
	@FindBy(linkText ="Sign in" ) WebElement signinBtn;
	
	public DataStructurePage(WebDriver driver, Helper helper) {
		
		this.helper =helper;
		PageFactory.initElements(driver,this);
	}
	public void loginToPortal() {
		helper.homeGetStartedBtn();
		signinBtn.click();
		helper.login();
		logger.info("logged in to DS algo Portal for testing Data Structure-Introduction module");
	}
	
	public void datastructureGetStarted() {
		logger.info("Opening Data Structure module page using button");
		helper.dataStructuresGetStarted("data-structures-introduction");
	}
	
	public void datastructureClickLink(String string) {
		logger.info("Clicking sub module link for Data Structure-Introduction");
		helper.clickLink(string);
	}
	
	public void ClickDataStructureTryEditor() {
		logger.info("Clicking Try here button for Data Structure-Introduction modules");
		helper.clickTryEditor();
	}
	
	public void enterDataStructurePythonCode(String sheet,String testcase_id ) throws IOException {
		logger.info("Entering Python code for Data Structure-Introduction from Excel sheet: {}, TestCase ID: {}", sheet, testcase_id);
		helper.enterPythonCode(helper.readFromExcel(sheet, testcase_id, "pythonCode"));
	}
	
	public void DataStructureRunBtn() {
		logger.info("Clicking Run button for assessment page");
		helper.clickRunButton();
	}
	
	public String readExpectedOutputForDataStructure(String sheet, String testcase_id) throws IOException {
		logger.info("Read expected output for Queue from Excel ({} - {}): {}", sheet, testcase_id);
		return helper.readFromExcel(sheet, testcase_id, "Result");
	}
	
	public String getActualOutputForDataStructure() {
		return helper.readActualOutput();
	}
}
