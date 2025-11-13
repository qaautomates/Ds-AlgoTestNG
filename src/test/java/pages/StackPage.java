package pages;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StackPage {

	private static Logger logger = LogManager.getLogger();
	private WebDriver driver;
	private Helper helper;

	@FindBy(linkText = "Try here>>>")
	WebElement tryHereBtn;
	@FindBy(xpath = "//button[@type='button']")
	WebElement runBtn;
	@FindBy(xpath = "//a[text()='Practice Questions']")
	WebElement practiceQuestions;
	@FindBy(id = "output")
	WebElement output;

	public StackPage(WebDriver driver, Helper helper) {
		this.driver = driver;
		this.helper = helper;
		PageFactory.initElements(driver, this);
	}

	public void stackClickLink(String string) {
		logger.info("Clicking submodule link " + string);
		WebElement subModule = driver.findElement(By.xpath("//a[text()='" + string + "']"));
		helper.clickElement(subModule);
	}

	public void clickStackTryEditor() {
		logger.info("Clicking Try Here button for Stack");
		helper.clickTryEditor(tryHereBtn);
	}

	public void enterStackPythonCode(String code) throws IOException {
		//String code = helper.readFromExcel(sheet, testId, "pythonCode");
		logger.info("Entering python code for Stack modules: " + code);
		helper.enterPythonCode(code);
	}

	public void clickStackRunBtn() {
		logger.info("Clicking Run button for Stack");
		helper.clickElement(runBtn);
	}

	/*public String readExpectedOutputForStack(String sheet, String testid) throws IOException {

		String expected =  helper.readFromExcel(sheet, testid, "Result");
		logger.info("Expected output from excel: " + expected);
		return expected;
	} */

	public String getActualOutputForStack() {
		String output_Message = helper.readTryEditorAlertMessage();
		if (output_Message == null) {
			return output.getText();
		}
		logger.info("Alert message captured in assessment page: " + output_Message);
		return output_Message;
	}
}		

