package baseTest;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.Helper;
import pages.HomePage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class BaseTest {

	public Helper helper;
	public HomePage homePage;
	public LoginPage loginPage;

	@BeforeSuite
	public static void loadConfigProp() {
		if (ConfigReader.getProp() == null) {
			ConfigReader.loadProperties();
			System.out.println("Before Suite");
		}
	}

	@BeforeMethod
	public void setUp(Method method) {
		DriverFactory.inItBrowser();
		DriverFactory.setupBrowser();
		System.out.println("Base Test SetUp");
		helper = new Helper(DriverFactory.getDriver());
		homePage = new HomePage(DriverFactory.getDriver(), helper);

		boolean launchRequired = false;
		boolean loginRequired = false;
		if (method.getAnnotation(Test.class) != null) {

			for (String group : method.getAnnotation(Test.class).groups()) {
				if (group.equals("launchRequired")) {
					launchRequired = true;
				}
				if (group.equals("loginRequired")) {
					loginRequired = true;
				}
			}
			if (launchRequired || loginRequired) {
				homePage.homeGetStartedBtn();
				Assert.assertEquals(helper.getTitle(), "NumpyNinja");
			}
			// Perform login if needed
			if (loginRequired) {
				loginPage = new LoginPage(DriverFactory.getDriver(), helper);
				loginPage.clickSignIn();
				loginPage.loginToPortal();

			}
		}

	}

	@AfterMethod
	public void tearDown() {

		DriverFactory.quitDriver();

	}

}
