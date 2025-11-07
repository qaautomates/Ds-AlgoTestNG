package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

	WebDriver driver;
	public Helper helper;
	public HomePage homePage;
	public LoginPage loginPage;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public Helper getHelper() {
		if (helper == null) {
			helper = new Helper(driver);
		}
		return helper;
	}

	public HomePage getHomePage() {
		if (homePage == null) {
			if (helper == null) helper = new Helper(driver);
			homePage = new HomePage(driver, helper);
		}
		return homePage;
	}

	public LoginPage getLoginPage() {
		if (loginPage == null) {
			loginPage = new LoginPage(driver, helper);
		}
		return loginPage;
	}
	
}
