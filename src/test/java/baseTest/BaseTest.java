package baseTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import pages.Helper;
import pages.HomePage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class BaseTest {
	
	public Helper helper;
	public HomePage homePage;

	@BeforeSuite
	public static void loadConfigProp() {
		if (ConfigReader.getProp() == null) {
			ConfigReader.loadProperties();
			System.out.println("Before Suite");
		}
	}

	@BeforeMethod
	public void setUp() {
		DriverFactory.inItBrowser();
		DriverFactory.setupBrowser();
		System.out.println("Base Test SetUp");
		helper = new Helper(DriverFactory.getDriver());
		homePage = new HomePage(DriverFactory.getDriver());
		homePage.homeGetStartedBtn();
		

	}

	@AfterMethod
	public void tearDown() {

		DriverFactory.quitDriver();

	}

}
