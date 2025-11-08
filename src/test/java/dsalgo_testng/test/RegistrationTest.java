package dsalgo_testng.test;

import org.testng.annotations.Test;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import baseTest.BaseTest;
import pages.RegisterPage;
import utilities.DriverFactory;

public class RegistrationTest extends BaseTest {

	ThreadLocal<RegisterPage> registerPage = new ThreadLocal<>();

	@BeforeMethod
	public void initialization() {
		registerPage.set(new RegisterPage(DriverFactory.getDriver(), getContext().getHelper()));
		Reporter.log("Register Page Initialized");

		registerPage.get().clickRegisterLink();
	}

	@Test(groups = "launchRequired")
	public void verify_UserName_ErrorMsg() {
		registerPage.get().clickRegisterBtn();
		registerPage.get().validateMessageUname();
		Reporter.log("UserName error message verified");

	}

	@Test(groups = "launchRequired")
	public void verify_Password_ErrorMsg() {
		registerPage.get().enterUname();
		registerPage.get().clickRegisterBtn();
		registerPage.get().validateMessagepassword();
		Reporter.log("Password error message verified");

	}

	@Test(groups = "launchRequired")
	public void verify_CPassword_ErrorMsg() {
		registerPage.get().enterUnamePwd();
		registerPage.get().clickRegisterBtn();
		registerPage.get().validateMessageCpassword();
		Reporter.log("Confirmation password error message verified");

	}

	@Test(groups = "launchRequired", dataProvider = "invalid Credentials")
	public void verify_InvalidCredentials_ErrorMsg(String userName, String passWord, String cPassword,
			String expectedMessage) {
		registerPage.get().enterCredentials(userName, passWord, cPassword, expectedMessage);
		Reporter.log("Invalid credentails entered and verified");

	}

	@DataProvider(name = "invalid Credentials")
	public Object[][] invalidData() {
		Reporter.log("Invalid credentials data provider executed");
		return new Object[][] {
				{ "2434342", "September2025$", "September2025$", "UserName cannot be entirely numberic" },
				{ "TestQA", "123$", "123$", "Your password must contain at least 8 characters" },
				{ "TestQA", "test", "test", "Your password can’t be a commonly used password" },
				{ "TestQA", "123", "123", "Your password can’t be entirely numeric." },
				{ "TestQA", "September2025$", "Oct2025", "password_mismatch:The two password fields didn’t match." },

		};

	}

	@Test(dataProvider = "valid Credentials", groups = "launchRequired")
	public void validCredentailsLogin(String passWord, String cPassword, String expectedMessage) {
		String userName = registerPage.get().generateUserName();
		registerPage.get().enterValidCredentials(userName, passWord, cPassword, expectedMessage);
		Reporter.log("User registered with valid credentails");

	}

	@DataProvider(name = "valid Credentials")
	public Object[][] validData() {
		Reporter.log("Valid credentails data provider executed");
		return new Object[][] { { "September2025$", "September2025$", "New Account Created. You are logged in as " },

		};

	}
}
