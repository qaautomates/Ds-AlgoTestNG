package pages;

import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;



public class QueuePage {

	
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
	
	public QueuePage(WebDriver driver, Helper helper) {
		
		this.driver = driver;
		this.helper =helper;
		PageFactory.initElements(driver,this);
	}
	
	
	public void queueClickLink(String string) {
		Reporter.log("Clicking sub module link for Queue");
		WebElement subModule = driver.findElement(By.xpath("//a[text()='" + string + "']"));
		helper.clickElement(subModule);
	}
	
	public void ClickQueueTryEditor() {
		Reporter.log("Clicking Try here button for Queue module");
		helper.clickTryEditor(tryHereBtn);
	}
	
	public void enterQueuePythonCode(String code) throws IOException {
		//String code = helper.readFromExcel(sheet, testcase_id, "pythonCode");
		Reporter.log("Entering python code for Queue module: " + code);
		helper.enterPythonCode(code);
	}
	
	public void QueueRunBtn() {
		Reporter.log("Clicking Run button for assessment page");
		helper.clickElement(runBtn);
	}
	
	
	/*public String readExpectedOutputForQueue(String code) throws IOException {
		//String expected =  helper.readFromExcel(sheet, testcase_id, "Result");
		Reporter.log("Expected output from excel: " + expected);
		return expected;
	}*/
	
	public String getActualOutputForQueue() {
		String output_Message = helper.readTryEditorAlertMessage();
		if (output_Message == null) {
			return output.getText();
		}
		Reporter.log("Alert message captured in assessment page: " + output_Message);
		return output_Message;
	}
}
