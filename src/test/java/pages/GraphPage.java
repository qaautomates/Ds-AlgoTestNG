package pages;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GraphPage {

	private static Logger logger = LogManager.getLogger();
	private WebDriver driver;
	private Helper helper;

	@FindBy(xpath = "//a[contains(text(),'Data Structures')]")
	WebElement dropdownMenu;
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

	public GraphPage(WebDriver driver, Helper helper) {
		this.driver = driver;
		this.helper = helper;
		PageFactory.initElements(driver, this);
	}

	public void selectGraphDropDownMenu(String string) {
		helper.clickElement(dropdownMenu);

		WebElement dropDownElement = driver.findElement(By.linkText("" + string + ""));
		helper.clickElement(dropDownElement);
	}

	public void clickGraphTryEditor() {
		logger.info("Clicking Try Here button for Graph");
		helper.clickTryEditor(tryHereBtn);
	}

	public void graphClickLink(String string) {
		//driver.findElement(By.xpath("//a[@href='" + string + "']")).click();
		logger.info("Clicking submodule link for Graph " + string);
		WebElement subModule = driver.findElement(By.xpath("//a[@href='" + string + "']"));
		helper.clickElement(subModule);
	}

	public void enterGraphPythonCode(String code) throws IOException {
		logger.info("Entering python code for Graph modules: " + code);
		helper.enterPythonCode(code);
	}

	public void clickGraphRunBtn() {
		logger.info("Clicking Run button for Graph");
		helper.clickElement(runBtn);
	}

	/*public String readExpectedOutputForGraph(String sheet, String testid) throws IOException {

		String expected =  helper.readFromExcel(sheet, testid, "Result");
		logger.info("Expected output from excel: " + expected);
		return expected;
	}*/

	public String getActualOutputForGraph() {
		String output_Message = helper.readTryEditorAlertMessage();
		if (output_Message == null) {
			return output.getText();
		}
		logger.info("Alert message captured in assessment page: " + output_Message);
		return output_Message;
	}

	public void graphClickLinkpractice(String string) {
		WebElement subModule1 = driver.findElement(By.xpath("//a[text()='" + string + "']"));
		helper.clickElement(subModule1);

	}
}	
