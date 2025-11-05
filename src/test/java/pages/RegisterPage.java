package pages;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	private static Logger logger = LogManager.getLogger();

	public WebDriver driver;

	private Helper helper;

	public RegisterPage(WebDriver driver, Helper helper) {
		this.driver = driver;
		this.helper = helper;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = ("//input[@type='submit']"))
	WebElement registerBtn;
	@FindBy(name = ("username"))
	WebElement uName;
	@FindBy(name = ("password1"))
	WebElement passWord;
	@FindBy(name = ("password2"))
	WebElement cPassWord;
	@FindBy(css = ".alert.alert-primary")
	WebElement alert;

	public void clickHomeGetStartedBtn() {
		helper.homeGetStartedBtn();
		logger.info("User clicked on Home Get started button");
	}

	public void clickRegisterLink(String link) {
		helper.clickLink(link);
		logger.info("User clicked on Register Link");

	}

	public void clickRegisterBtn() {
		registerBtn.click();
		logger.info("User clicked on Register Button");

	}

	public String validateMessageUname() {

		String validationMessage = uName.getAttribute("validationMessage");
		System.out.println(validationMessage);
		logger.info("User verified message" + validationMessage + " for user Name ");

		return validationMessage;

	}

	public void enterUname() {
		uName.sendKeys("qaautomates4");
		logger.info("User enters the user name");

	}

	public void enterUnamePwd() {
		uName.sendKeys("TestQA");
		passWord.sendKeys("September2025$");
		logger.info("User enters the password");

	}

	public String validateMessagepassword() {

		String validationMessagePwd = passWord.getAttribute("validationMessage");
		System.out.println(validationMessagePwd);
		logger.info("User verified message" + validationMessagePwd + " for password ");

		return validationMessagePwd;

	}

	public String validateMessageCpassword() {

		String validationMessage = cPassWord.getAttribute("validationMessage");
		System.out.println(validationMessage);
		logger.info("User verified message " + validationMessage + " for password ");

		return validationMessage;

	}

	public String generateUserName() {

		String name = "QAAUTOMATES";
		Random randomValues = new Random();
		String username = name + randomValues.nextInt(1000);
		System.out.println("GeneratedRandom String : " + username);
		return username;
	}

	public void enterCredentials(String userName, String password, String passwordConfirmation) {
		uName.sendKeys(userName);
		passWord.sendKeys(password);
		cPassWord.sendKeys(passwordConfirmation);
		logger.info("User entered the credentials ");

	}

	public String actualMessage() {

		String expMessage = alert.getText();
		logger.info("User logged in to application with message as "+expMessage);
		return expMessage;

	}
}
