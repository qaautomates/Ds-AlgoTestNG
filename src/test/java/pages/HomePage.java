package pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private static Logger logger = LogManager.getLogger();
	private WebDriver driver;

	@FindBy(xpath = "//button[contains(text(),'Get Started')]")
	WebElement getStartedBtn;
	@FindBy(xpath = "//a[contains(text(),'Data Structures')]")
	WebElement dropdownMenu;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void homeGetStartedBtn() {

		getStartedBtn.click();

	}

	public void selectDropDownMenu(String string) {
		dropdownMenu.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("" + string + ""))).click();

	}

	public void dataStructuresGetStarted(String module) {
		driver.findElement(By.xpath("//a[@href='" + module + "']")).click();
	}

	public String GetErrorMessageLogIn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement alertMsg;
		String errorMsg = null;
		try {
			alertMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
			errorMsg = alertMsg.getText();
			logger.info("Alert message displayed: " + errorMsg);
		} catch (Exception e) {
			logger.error("Did not get error message without login to home page");
		}
		return errorMsg;
	}

}
