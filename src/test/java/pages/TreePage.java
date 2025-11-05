package pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TreePage {
	private static Logger logger = LogManager.getLogger();

	public WebDriver driver;
	public Helper helper;

	@FindBy(linkText = "Sign in")
	WebElement signInBtn;

	public TreePage(WebDriver driver, Helper helper) {
		this.driver = driver;
		this.helper = helper;
		PageFactory.initElements(driver, this);

	}

	public void loginToPortal() throws IOException  {
		helper.homeGetStartedBtn();
		signInBtn.click();
		helper.login();
		logger.info("User Logged in to DS algo portal for testing Tree module");

		// helper.login();
	}

	public void treeGetStarted() {
		helper.dataStructuresGetStarted("tree");
		logger.info("Opening Tree module page using button");
	}

	public void selectTreeFromDropDown(String string) {
		helper.selectDropDownMenu(string);
		logger.info("Opening Tree module page using dropdown");
	}

	public void treeClickLink(String string) {
		helper.clickLink(string);
		logger.info("Clicking sub module" + string + " link for Tree");
	}

	public void clickTreeTryEditor() {
		helper.clickTryEditor();
		logger.info("Clicking Try here button for Tree modules");
	}

	public void enterTreePythonCode(String sheet, String testcase_id) throws IOException {
		helper.enterPythonCode(helper.readFromExcel(sheet, testcase_id, "pythonCode"));
		logger.info("Entering python code for Tree modules");
	}

	public void clickTreeRunBtn() {
		helper.clickRunButton();
		logger.info("Clicking Run button for assessment page");
	}

	public String getActualOutputForTree() {
		return helper.readActualOutput();

	}

	public String readExpectedOutputForTree(String sheet, String testcase_id) throws IOException {
		return helper.readFromExcel(sheet, testcase_id, "Result");

	}

}
