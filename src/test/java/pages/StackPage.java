package pages;


import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//import utilities.LoggerLoad;

public class StackPage {

	private static Logger logger = LogManager.getLogger();
		public WebDriver driver;
		public Helper helper;
		
		@FindBy(linkText ="Sign in" ) WebElement signinBtn;
		
		public StackPage(WebDriver driver, Helper helper) {
			this.driver = driver;
			this.helper = helper;
			PageFactory.initElements(driver,this);
		}

		public void loginToPortalStack() {
			
			helper.homeGetStartedBtn();
			signinBtn.click();
			helper.login();
			logger.info("Logging in portal to test Stack module");
		}
			
		public void selectStackFromDropDown(String string) {
			helper.selectDropDownMenu(string);
			
		}
		
		public void clickStackTryEditor() {
			logger.info("Clicking Try Here button");
			helper.clickTryEditor();
		}

		public void stackGetStarted() {
			logger.info("Clicking Get Started for Stack");
			helper.dataStructuresGetStarted("stack");
			
		}
		
		public void stackClickLink(String string) {
			helper.clickLink(string);
		}
		
		public void enterStackPythonCode(String sheet, String testcase_id) throws IOException {
		
			helper.enterPythonCode(helper.readFromExcel(sheet, testcase_id, "pythonCode"));
			}
							
		public void clickStackRunBtn() {
			helper.clickRunButton();
		}

		public String readExpectedOutputForStack(String sheet, String testcase_id) throws IOException {
			return helper.readFromExcel(sheet, testcase_id, "Result");
		}

		public String getActualOutputForStack() {
			return helper.readActualOutput();
		}
		
		public void moveToPracticeQuestionsPage(String string) {
		
			helper.dataStructuresGetStarted("stack");
			helper.clickLink(string);
			helper.clickLink("Practice Questions");
		}
}