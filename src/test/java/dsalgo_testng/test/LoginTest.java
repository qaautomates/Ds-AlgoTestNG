package dsalgo_testng.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import baseTest.BaseTest;

public class LoginTest extends BaseTest{

	@BeforeMethod
	public void enter_signIn_Page() {
		getContext().getLoginPage().clickSignIn();
		Reporter.log("Login Test: Entered Sign in Page");
	}
	
	@Test(groups = "launchRequired")
	public void verify_login_valid_credentials() {
		getContext().getLoginPage().loginToPortal(getContext().getExcelReader());
		Assert.assertEquals(getContext().getLoginPage().verifyMessage(), "You are logged in");
		Reporter.log("Logged in using valid credentials");
	}
	
	@Test(dataProvider = "invalid_credentials", groups = "launchRequired")
	public void verify_login_invalid_credentials(String userName, String password, String message) {
		getContext().getLoginPage().invalidCredentials(userName, password);
		getContext().getLoginPage().clickLogin();
		String actualMessage = getContext().getLoginPage().geterrorMsg();
		Assert.assertEquals(actualMessage, message, "Login validation message mismatch!");
		Reporter.log("Login using invalid credentials verified");
	}
	
	@DataProvider(name = "invalid_credentials", parallel = true)
	public Object[][] getInvalidCredentialsData() {
		return new Object[][] {
			{"", "", "Please fill out this field."}, 
			{"Qaautomates4", "", "Please fill out this field."},
			{"", "September2025$", "Please fill out this field."}, 
			{"invalid", "invalid", "Invalid Username and Password"},
			{"invalid", "September2025$", "Invalid Username and Password"},
			{"Qaautomates4", "invalid", "Invalid Username and Password"}
		};
	}
	
	@Test(groups = "launchRequired")
	public void verify_signout() {
		getContext().getLoginPage().loginToPortal(getContext().getExcelReader());
		getContext().getLoginPage().clickSignout();
		String actualMessage = getContext().getLoginPage().signoutMessage();
		Assert.assertEquals(actualMessage, "Logged out successfully", "Logout message mismatch!");
		Reporter.log("Signed out successfully");
	}
}
