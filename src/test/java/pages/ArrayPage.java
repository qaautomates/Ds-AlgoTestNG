package pages;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ArrayPage  {
	private static Logger logger = LogManager.getLogger();
	private WebDriver driver;
	private Helper helper;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement submitBtn;
	@FindBy(xpath = "//pre[contains(text(),'Error') or contains(text(), 'No tests')]") 
	WebElement submitBtnMessage;
	

	public ArrayPage(WebDriver driver, Helper helper) {
		this.driver = driver;
		this.helper = helper;
		PageFactory.initElements(driver, this);

	}

	public void loginToPortal() {
		helper.homeGetStartedBtn();
		helper.clickSignIn();
		helper.login();
		logger.info("Logged in to DS Algo portal for testing Arrays");
	}

	public void selectArrayFromDropDown(String string) {
		logger.info("Selecting Arrays from dropdown menu");
		helper.selectDropDownMenu(string);
	}

	public void clickArrayTryEditor() {
		logger.info("Clicking try editor for array");
		helper.clickTryEditor();
	}

	public void arrayGetStarted() {
		helper.dataStructuresGetStarted("array");

	}

	public void arrayClickLink(String string) {
		logger.info("Clicking submodule link " + string);
		helper.clickLink(string);
	}

	public void enterArrayPythonCode(String sheet, String testcase_id) throws IOException {
		helper.enterPythonCode(helper.readFromExcel(sheet, testcase_id, "pythonCode"));
	}

	public void clickArrayRunBtn() {
		logger.info("Clicking Run button for Array");
		helper.clickRunButton();
	}

	public String readExpectedOutputForArray(String sheet, String testcase_id) throws IOException {
		return helper.readFromExcel(sheet, testcase_id, "Result");
	}

	public String getActualOutputForArray() {
		return helper.readActualOutput();
	}

	public void moveToPracticeQuestionsPage() {
		logger.info("Moving to array practice questions page");
		helper.dataStructuresGetStarted("array");
		helper.clickLink("Arrays in Python");
		helper.clickLink("Practice Questions");
	}

	public String getAssessmentQuestion() {
		WebElement question = driver.findElement(By.className("cm-def"));
		return question.getText();
	}

	public void moveToPracticeQuestionsEditor(String questionLink) {
		logger.info("Moving to Practice questions editor for array");
		moveToPracticeQuestionsPage();
		helper.clickLink(questionLink);
	}

	public void clickArraySubmitBtn() {
		submitBtn.click();
	}
	
	public String getSubmitMesssage() {		
		String message = submitBtnMessage.getText();
		logger.info("submit message:" + message);
		return message;
	}
}
