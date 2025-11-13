package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class TreePage {

	private WebDriver driver;
	private Helper helper;

	@FindBy(linkText = "Try here>>>")
	WebElement tryHereBtn;
	@FindBy(xpath = "//button[@type='button']")
	WebElement runBtn;
	@FindBy(id ="output") WebElement output;

	public TreePage(WebDriver driver, Helper helper) {
		this.driver = driver;
		this.helper = helper;
		PageFactory.initElements(driver, this);

	}


	public void treeClickLink(String link) {
		WebElement subModElementname = driver.findElement(By.xpath("//a[text()='" + link + "']"));
		helper.clickElement(subModElementname);
		Reporter.log("Tree Sub Module : "+link+ "is clicked");
	}

	public void clickTreeTryEditor() {
		helper.clickElement(tryHereBtn);
		Reporter.log("try here button is clicked");
	}

	public void enterTreePythonCode(String code) throws IOException {
		//helper.enterPythonCode(helper.readFromExcel(sheet, testId, "pythonCode"));
		helper.enterPythonCode(code);
		Reporter.log("Python code is entered");
	}

	public void clickTreeRunBtn() {
		helper.clickElement(runBtn);
		Reporter.log("Run button is clicked");
	}

	public String getActualOutputForTree() {
		String output_Message = helper.readTryEditorAlertMessage();
		if(output_Message == null) {
			Reporter.log("reading Actual output for tree message in Dsalgo application");

			return output.getText();
		}return output_Message;
	}
	

	/*public String readExpectedOutputForTree(String sheet, String testcase_id) throws IOException {
		Reporter.log("Reading Expected message from the excel sheet or tree");

		return helper.readFromExcel(sheet, testcase_id, "Result");

	}*/

}
