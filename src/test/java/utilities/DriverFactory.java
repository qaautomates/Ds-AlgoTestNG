package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

public class DriverFactory {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static ThreadLocal<String> browserName = new ThreadLocal<>();

	public static void inItBrowser(String browser) {
		if (browser == null) {
			browser = ConfigReader.getProperty("browser");
		}
		Reporter.log("Opening browser:" + browser, true);
		if (browser.equalsIgnoreCase("Edge")) {
			driver.set(new EdgeDriver());

		} else if (browser.equalsIgnoreCase("Chrome")) {

			driver.set(new ChromeDriver());

		} else if (browser.equalsIgnoreCase("Firefox")) {

			driver.set(new FirefoxDriver());

		} else {
			throw new IllegalArgumentException("Browser instance can not be initialized");
		}


	}

	public static String getBrowser() {
		String browser = browserName.get();

		if (browser == null) {
			browser = ConfigReader.getProperty("browser");
		}
		return browser;
	}

	public static WebDriver getDriver() {
		if (driver.get() == null) {
			DriverFactory.inItBrowser(null);
		}
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}

	public static void setupBrowser() {
		WebDriver localDriver = driver.get();
		localDriver.get(ConfigReader.getProperty("url"));
		localDriver.manage().window().maximize();
		localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public static String getBrowserName() {
		return browserName.get();
	}

	public static void setBrowserName(String browserName) {
		DriverFactory.browserName.set(browserName);
	}
}
