package pages;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ArrayPage {
	private static Logger logger = LogManager.getLogger();
	private WebDriver driver;
	private Helper helper;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement submitBtn;
	@FindBy(xpath = "//pre[contains(text(),'Error') or contains(text(), 'No tests')]")
	WebElement submitBtnMessage;
	@FindBy(linkText = "Try here>>>")
	WebElement tryHereBtn;
	@FindBy(xpath = "//button[@type='button']")
	WebElement runBtn;
	@FindBy(xpath = "//a[text()='Practice Questions']")
	WebElement practiceQuestions;
	@FindBy(className = "cm-def")
	WebElement question;
	@FindBy(id = "output")
	WebElement output;

	public ArrayPage(WebDriver driver, Helper helper) {
		this.driver = driver;
		this.helper = helper;
		PageFactory.initElements(driver, this);

	}

	public void clickArrayTryEditor() {
		logger.info("Clicking try editor for array");
		helper.clickTryEditor(tryHereBtn);
	}

	public void arrayClickLink(String string) {
		logger.info("Clicking submodule link " + string);
		WebElement subModule = driver.findElement(By.xpath("//a[text()='" + string + "']"));
		helper.clickElement(subModule);
	}

	public void enterArrayPythonCode(String sheet, String testId) throws IOException {
		helper.enterPythonCode(helper.readFromExcel(sheet, testId, "pythonCode"));
	}

	public void clickArrayRunBtn() {
		logger.info("Clicking Run button for Array");
		helper.clickElement(runBtn);
	}

	public String readExpectedOutputForArray(String sheet, String testcase_id) throws IOException {
		return helper.readFromExcel(sheet, testcase_id, "Result");
	}

	public String getActualOutputForArray() {

		String output_Message = helper.readTryEditorAlertMessage();
		if (output_Message == null) {
			return output.getText();
		}
		return output_Message;
	}

	public String getAssessmentQuestion() {
		return question.getText();
	}

	public void moveToPracticeQuestionsEditor(String questionLink) {
		logger.info("Moving to Practice questions editor for array");
		helper.clickElement(practiceQuestions);
		WebElement questionLinkElement = driver.findElement(By.xpath("//a[text()='" + questionLink + "']"));
		helper.clickElement(questionLinkElement);
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
