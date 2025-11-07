package pages;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedListPage {
	private static Logger logger = LogManager.getLogger();
	private Helper helper;
	private WebDriver driver;
	
	@FindBy(linkText = "Try here>>>")
	WebElement tryHereBtn;
	@FindBy(xpath = "//button[@type='button']")
	WebElement runBtn;
	@FindBy(xpath = "//a[text()='Practice Questions']")
	WebElement practiceQuestions;
	@FindBy(id = "output")
	WebElement output;
	
	public LinkedListPage(WebDriver driver, Helper helper) {
		this.driver = driver;
		this.helper = helper;
		PageFactory.initElements(driver, this);
	}
	
	public void linkedListClickLink(String string) {
		logger.info("Clicking sub module link for linked list");
		WebElement subModule = driver.findElement(By.xpath("//a[text()='" + string + "']"));
		helper.clickElement(subModule);
	}
	
	public void clickLinkedListTryEditor() {
		logger.info("Clicking Try here button for linked list modules");
		helper.clickTryEditor(tryHereBtn);
	}
	
	public void enterLinkedListPythonCode(String sheet, String testcase_id) throws IOException {	
		String code = helper.readFromExcel(sheet, testcase_id, "pythonCode");
		logger.info("Entering python code for Linked List modules: " + code);
		helper.enterPythonCode(code);
	}
	
	public void clickLinkedListRunBtn() {
		logger.info("Clicking Run button for assessment page");
		helper.clickElement(runBtn);
	}
	
	public String readExpectedOutputForLinkedList(String sheet, String testcase_id) throws IOException {
		String expected =  helper.readFromExcel(sheet, testcase_id, "Result");
		logger.info("Expected output from excel: " + expected);
		return expected;
	}
	
	public String getActualOutputForLinkedList() {
		String output_Message = helper.readTryEditorAlertMessage();
		if (output_Message == null) {
			return output.getText();
		}
		logger.info("Alert message captured in assessment page: " + output_Message);
		return output_Message;
	}
}
