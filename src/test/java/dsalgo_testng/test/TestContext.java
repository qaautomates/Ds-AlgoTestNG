package dsalgo_testng.test;

import pages.Helper;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverFactory;

public class TestContext {

	Helper helper;
	HomePage homePage;
	LoginPage loginPage;

	public Helper getHelper() {
		if (helper == null) {
			helper = new Helper(DriverFactory.getDriver());
		}
		return helper;
	}

	public HomePage getHomePage() {
		if (homePage == null) {
			homePage = new HomePage(DriverFactory.getDriver());
		}
		return homePage;
	}

	public LoginPage getLoginPage() {
		if(loginPage == null) {
			loginPage = new LoginPage(DriverFactory.getDriver(), helper);
		}
		return loginPage;
	}

}
