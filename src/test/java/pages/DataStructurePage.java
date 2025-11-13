package pages;

import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;


public class DataStructurePage {
	
	
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
	
	public DataStructurePage(WebDriver driver, Helper helper) {
		this.driver = driver;
		this.helper =helper;
		PageFactory.initElements(driver,this);
	}
	
	public void datastructureClickLink(String string) {
		Reporter.log("Clicking sub module link for Data Structure-Introduction");
		WebElement subModule = driver.findElement(By.xpath("//a[text()='" + string + "']"));
		helper.clickElement(subModule);
	}
	
	public void ClickDataStructureTryEditor() {
		Reporter.log("Clicking Try here button for Data Structure-Introduction modules");
		helper.clickTryEditor(tryHereBtn);
	}
	
	public void enterDataStructurePythonCode(String code ) throws IOException {
		helper.enterPythonCode(code);
	}
	
	public void DataStructureRunBtn() {
		Reporter.log("Clicking Run button for assessment page");
		helper.clickElement(runBtn);
	}
	
	/*public String readExpectedOutputForDataStructure(String sheet, String testcase_id) throws IOException {
		Reporter.log("Reading expected output for Data Structure from Excel Sheet: " 
		        + sheet + ", TestCase ID: " + testcase_id);
		return helper.readFromExcel(sheet, testcase_id, "Result");
	}*/
	
	public String getActualOutputForDataStructure() {
		String output_Message = helper.readTryEditorAlertMessage();
		if (output_Message == null) {
			return output.getText();
		}
		return output_Message;
	}
	
	
	
}
