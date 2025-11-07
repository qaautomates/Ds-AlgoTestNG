package baseTest;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.BasePage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class BaseTest {
	private static ThreadLocal<BasePage> context = new ThreadLocal<>();

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
		Reporter.log("Base Test SetUp");
		context.set(new BasePage(DriverFactory.getDriver()));

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
				getContext().getHomePage().homeGetStartedBtn();
				Assert.assertEquals(getContext().getHelper().getTitle(), "NumpyNinja");
				Reporter.log("Data strucutres home page");
			}
			// Perform login if needed
			if (loginRequired) {
				getContext().getLoginPage().clickSignIn();
				getContext().getLoginPage().loginToPortal();
				Reporter.log("Logged in to data structures home page");
			}
		}

	}
	
	public BasePage getContext() {
	        return context.get();
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
		if(context != null) {
			context.remove();
		}
	}

}
