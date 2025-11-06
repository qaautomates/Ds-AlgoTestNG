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

	

	public String getTitle() {

		return driver.getTitle();

	}
	
	public void clickElement(WebElement webElement) {
		webElement.click();
	}

	
	public void clickLink(WebElement link) {
		driver.findElement(By.xpath("//a[text()='" + link + "']")).click();
	}

	public String getUrl() {

		return driver.getCurrentUrl();

	}

	public void clickTryEditor(WebElement element) {

		Actions act = new Actions(driver);
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
