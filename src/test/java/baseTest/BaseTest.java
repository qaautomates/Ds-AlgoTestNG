package baseTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import utilities.ConfigReader;
import utilities.DriverFactory;

public class BaseTest {

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

	}

	@AfterMethod
	public void tearDown() {

		DriverFactory.quitDriver();

	}

}
