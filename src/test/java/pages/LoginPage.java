package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class LoginPage {

	
	public WebDriver driver;
	private Helper helper;
	
	@FindBy(linkText = "Sign in") WebElement signIn;
	@FindBy(name = "username")  WebElement username;
	@FindBy(name = "password")	WebElement password;
	@FindBy(xpath = "//input[@type='submit']")  WebElement submitBtn;
	@FindBy(xpath = "//div[@class='alert alert-primary']") WebElement alertMessage;
	@FindBy(xpath = "//input[@type='submit']") WebElement loginbtn;
	@FindBy(xpath = "//a[@href='/logout']") WebElement signOut;
	@FindBy(xpath= "//div[contains(text(),'Logged out successfully')]") WebElement successMessage;
	public LoginPage(WebDriver driver, Helper helper) {
		this.driver = driver;
		this.helper = helper;
		PageFactory.initElements(driver,this);
	}
	
	public void clickSignIn() {
		signIn.click();
	}
	
	public void login() throws IOException {
		helper.login();
		
	}
	
	public String verifyMessage() {
		String message =alertMessage.getText();
		return message;
	}
	
	public void invalidCredentials(String userName, String passWord) {
		username.sendKeys(userName);
		password.sendKeys(passWord);
		
	}

	public void clickLogin() {
		loginbtn.click();
	}
	
	public void clickHomeGetStartedBtn() {
		helper.homeGetStartedBtn();
	}
	
	public String readLoginCredentials(String sheet, String testcase_id, String key) throws IOException {
		return helper.readFromExcel(sheet, testcase_id, key);
	}
	
	public void readActualMsg() {
		
		helper.readActualOutput();
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
	                message = username.getAttribute("validationMessage");
	                if (message == null || message.isEmpty()) {
	                    message = password.getAttribute("validationMessage");
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
