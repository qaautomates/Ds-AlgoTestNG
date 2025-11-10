package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

	WebDriver driver;
	public Helper helper;
	public HomePage homePage;
	public LoginPage loginPage;
	public ArrayPage arrayPage;
	public DataStructurePage dataStructurePage;
	public GraphPage graphPage;
	public LinkedListPage linkedListPage;
	public QueuePage queuePage;
	public RegisterPage registerPage;
	public StackPage stackPage;
	public TreePage treePage;

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
			if (helper == null)
				helper = getHelper();
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

	public ArrayPage getArrayPage() {
		if (arrayPage == null) {
			arrayPage = new ArrayPage(driver, helper);
		}
		return arrayPage;
	}

	public DataStructurePage getDataStructurePage() {
		if (dataStructurePage == null) {
			dataStructurePage = new DataStructurePage(driver, helper);
		}
		return dataStructurePage;
	}

	public GraphPage getGraphPage() {
		if (graphPage == null) {
			graphPage = new GraphPage(driver, helper);
		}
		return graphPage;
	}

	public LinkedListPage getLinkedListPage() {
		if (linkedListPage == null) {
			linkedListPage = new LinkedListPage(driver, helper);
		}
		return linkedListPage;
	}

	public QueuePage getQueuePage() {
		if (queuePage == null) {
			queuePage = new QueuePage(driver, helper);
		}
		return queuePage;
	}

	public RegisterPage getRegisterPage() {
		if (registerPage == null) {
			registerPage = new RegisterPage(driver, helper);
		}
		return registerPage;
	}

	public StackPage getStackPage() {
		if (stackPage == null) {
			stackPage = new StackPage(driver, helper);
		}
		return stackPage;
	}

	public TreePage getTreePage() {
		if (treePage == null) {
			treePage = new TreePage(driver, helper);
		}
		return treePage;
	}

}
