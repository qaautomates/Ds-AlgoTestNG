package dsalgo_testng.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pages.ArrayPage;
import pages.Helper;
import utilities.DriverFactory;

public class ArrayTest extends BaseTest {

	Helper helper;
	ArrayPage arrayPage;

	@BeforeMethod
	public void initialization() {
		 helper = new Helper(DriverFactory.getDriver());
		 arrayPage = new ArrayPage(DriverFactory.getDriver(), helper);
		System.out.println("Array Test initialization");

	}

	@Test
	public void login() {
		arrayPage.loginToPortal();
		System.out.println("Array Test login");

	}

	@Test
	public void print() {
		System.out.println("Test Array Module");
	}

}
