package pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private static Logger logger = LogManager.getLogger();
	private WebDriver driver;
	private Helper helper;
	
	public HomePage(WebDriver driver, Helper helper) {
		this.driver = driver;
		this.helper = helper;
	}
	
	public void HomePageGetStarted() {
		logger.info("Clicking on Get started button from home page");
		helper.homeGetStartedBtn();
	}
	
	public void selectModuleFromDropdown(String string) {
		logger.info("Selecting data structure modules from dropdown");
		helper.selectDropDownMenu(string);
	}
	
	public String GetErrorMessageLogIn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement alertMsg;
		String errorMsg = null;
		try {
			alertMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			errorMsg = alertMsg.getText();
			logger.info("Alert message displayed: " + errorMsg);
		}
		catch (Exception e) {
			logger.error("Did not get error message without login to home page");
		}
		return errorMsg;
	}
	
	public void clickModuleGetStarted(String string) {
		logger.info("Clicking data structures Get started button");
		helper.dataStructuresGetStarted(string);
	}
}
