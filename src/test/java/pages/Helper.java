package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ConfigReader;
import utilities.ExcelSheetHandling;

public class Helper {

	WebDriver driver;

	public Helper(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Sign in")
	WebElement signInLink;
	@FindBy(name = "username")
	WebElement uName;
	@FindBy(name = "password")
	WebElement pwd;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement logInBtn;
	@FindBy(xpath = "//button[contains(text(),'Get Started')]")
	WebElement getStartedBtn;
	@FindBy(xpath = "//a[contains(text(),'Data Structures')]")
	WebElement dropdownMenu;
	@FindBy(linkText = "Try here>>>")
	WebElement tryHereBtn;
	@FindBy(xpath = "//button[@type='button']")
	WebElement runBtn;

	public void clickSignIn() {
		signInLink.click();
	}

	public void login() {

		String userNameValue;
		try {
			userNameValue = readFromExcel(ConfigReader.getProperty("sheetName"), "TC001", "UserName");
			String passWordValue = readFromExcel(ConfigReader.getProperty("sheetName"), "TC001", "Password");
			uName.sendKeys(userNameValue);
			pwd.sendKeys(passWordValue);
			logInBtn.click();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

	}

	public String getTitle() {

		return driver.getTitle();

	}

	public void homeGetStartedBtn() {

		getStartedBtn.click();

	}

	public void selectDropDownMenu(String string) {
		dropdownMenu.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("" + string + ""))).click();
		
		//driver.findElement(By.linkText("" + string + "")).click();
	}

	public void dataStructuresGetStarted(String module) {
		driver.findElement(By.xpath("//a[@href='" + module + "']")).click();
	}

	public void clickLink(String link) {
		driver.findElement(By.xpath("//a[text()='" + link + "']")).click();
	}

	public String getUrl() {

		return driver.getCurrentUrl();

	}

	public void clickTryEditor() {

		Actions act = new Actions(driver);
		WebElement element = tryHereBtn;
		act.moveToElement(element).click(element).build().perform();
	}

	public void enterPythonCode(String code) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('.CodeMirror').CodeMirror.setValue('');");
		js.executeScript("document.querySelector('.CodeMirror').CodeMirror.setValue(arguments[0]);", code);

		/*
		 * try { Thread.sleep(500); // Small pause (not mandatory but helpful sometimes)
		 * } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
		 */
	}

	public void clickRunButton() {
		runBtn.click();
	}

	public String readFromExcel(String sheet, String testcase_id, String key) throws IOException {
		ExcelSheetHandling excelReader = new ExcelSheetHandling();
		HashMap<String, String> code = excelReader.readExcelSheet(sheet, testcase_id);
		return code.get(key);

	}

	public String readActualOutput() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			return alert.getText();
		} catch (Exception e) {
			WebElement output = driver.findElement(By.id("output"));
			return output.getText();
		}
	}
}
