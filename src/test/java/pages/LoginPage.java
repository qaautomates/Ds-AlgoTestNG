package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ConfigReader;

import java.io.IOException;
import java.time.Duration;

public class LoginPage {	
	private WebDriver driver;
	private Helper helper;
		
	@FindBy(linkText = "Sign in")
	WebElement signInLink;
	@FindBy(name = "username")
	WebElement uName;
	@FindBy(name = "password")
	WebElement pwd;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement logInBtn;
	@FindBy(xpath = "//div[@class='alert alert-primary']") WebElement alertMessage;
	@FindBy(xpath = "//a[@href='/logout']") WebElement signOut;
	@FindBy(xpath= "//div[contains(text(),'Logged out successfully')]") WebElement successMessage;
	
	public LoginPage(WebDriver driver, Helper helper) {
		
		this.driver = driver;
		this.helper = helper;
		PageFactory.initElements(driver,this);
	}
	
	public void clickSignIn() {
		
		helper.clickElement(signInLink);
	}
	
	public void loginToPortal() {

		String userNameValue;
		try {
			userNameValue = helper.readFromExcel(ConfigReader.getProperty("sheetName"), "TC001", "UserName");
			String passWordValue = helper.readFromExcel(ConfigReader.getProperty("sheetName"), "TC001", "Password");
			uName.sendKeys(userNameValue);
			pwd.sendKeys(passWordValue);
			logInBtn.click();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public String verifyMessage() {
		String message =alertMessage.getText();
		return message;
	}
	
	public void invalidCredentials(String userName, String passWord) {
		uName.sendKeys(userName);
		pwd.sendKeys(passWord);
		
	}

	public void clickLogin() {
		logInBtn.click();
	}
	
	
	public String readLoginCredentials(String sheet, String testcase_id, String key) throws IOException {
		return helper.readFromExcel(sheet, testcase_id, key);
	}
	
	public String geterrorMsg() {
	    String message = "";

	    try {
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	        message = alert.getText().trim();
	        alert.accept();
	        return message;

	    } catch (Exception e1) {
	        try {
	            
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	            WebElement alert1 = wait.until(ExpectedConditions.visibilityOf(alertMessage));
	            message = alert1.getText().trim();
	            if (!message.isEmpty()) return message;
	        } catch (Exception e2) {
	            
	            try {
	                message = uName.getAttribute("validationMessage");
	                if (message == null || message.isEmpty()) {
	                    message = pwd.getAttribute("validationMessage");
	                }
	            } catch (Exception e3) {
	                message = "No message found";
	            }
	        }
	    }

	    return message.trim();
	}
	
	
	public void clickSignout() {
		signOut.click();
	}
	
	public String signoutMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOf(successMessage));
	    
	    return successMessage.getText().trim();
	}
}
