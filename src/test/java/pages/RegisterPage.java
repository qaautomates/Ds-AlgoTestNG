package pages;

import java.util.Random;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;


public class RegisterPage {

	public WebDriver driver;

	private Helper helper;

	public RegisterPage(WebDriver driver, Helper helper) {
		this.driver = driver;
		this.helper = helper;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath = "//a[text()=' Register ']") WebElement registerLink;
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

	
	public void clickRegisterLink() {
		helper.clickElement(registerLink);
		Reporter.log("User clicked on Register Link");

	}

	public void clickRegisterBtn() {
		registerBtn.click();
		Reporter.log("User clicked on Register Button");

	}

	public String validateMessageUname() {

		String validationMessage = uName.getAttribute("validationMessage");
		System.out.println(validationMessage);
		Reporter.log("User verified message" + validationMessage + " for user Name ");
		return validationMessage;
	}

	public void enterUname() {
		uName.sendKeys("qaautomates4");
		Reporter.log("User enters the user name");
	}

	public void enterUnamePwd() {
		uName.sendKeys("TestQA");
		passWord.sendKeys("September2025$");
		Reporter.log("User enters the password");
	}

	public String validateMessagepassword() {

		String validationMessagePwd = passWord.getAttribute("validationMessage");
		System.out.println(validationMessagePwd);
		Reporter.log("User verified message" + validationMessagePwd + " for password ");
		return validationMessagePwd;
	}

	public String validateMessageCpassword() {

		String validationMessage = cPassWord.getAttribute("validationMessage");
		System.out.println(validationMessage);
		Reporter.log("User verified message " + validationMessage + " for password ");
		return validationMessage;

	}

	public String generateUserName() {

		String name = "QAAUTOMATES";
		Random randomValues = new Random();
		String username = name + randomValues.nextInt(1000);
		System.out.println("GeneratedRandom String : " + username);
		Reporter.log("Random username is generated");
		return username;
	}

	public void enterCredentials(String userName, String password, String passwordConfirmation, String expectedMessage) {
		uName.sendKeys(userName);
		passWord.sendKeys(password);
		cPassWord.sendKeys(passwordConfirmation);
		Reporter.log("User entered the invalid credentials ");
		clickRegisterBtn();
		String actualMessage = alert.getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Expected and actual message are not matching");


	}
	
	public void enterValidCredentials(String userName, String password, String passwordConfirmation, String expectedMessage) {
		uName.sendKeys(userName);
		passWord.sendKeys("September2025$");
		cPassWord.sendKeys("September2025$");
		Reporter.log("User entered the valid credentials ");
		clickRegisterBtn();
		String actualMessage = alert.getText();
		String expectedAlertMessage =expectedMessage+userName;
		Assert.assertEquals(actualMessage, expectedAlertMessage, "Expected and actual message are not matching");


	}

	public String actualMessage() {

		String expMessage = alert.getText();
		Reporter.log("User logged in to application with message as "+expMessage);
		return expMessage;

	}
}