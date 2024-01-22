package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class Login {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// by locators: OR

	private By UserName = By.id("input-email");
	private By Password = By.id("input-password");
	private By LoginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By Logo = By.xpath("//img[@title='naveenopencart']");
	private By registerLink = By.linkText("Register");

	private static final Logger log = LogManager.getLogger(Login.class);

	
	// page const..
	public Login(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// page actions/methods
	@Step("getting login page title")
	public String getPageTitle() {

		String title = eleUtil.waitFortitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAUTT_WAIT);
		//System.out.println("Login page Title" + title);
		log.info("Login page Title" +title);
		return title;
	}

	@Step("getting login page url")
	public String getPageUrl() {

		String Url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAUTT_WAIT);

		//System.out.println("Login page url" + Url);
		log.info("Login page url" +Url);
		return Url;
	}
	@Step("checking forgot pwd link exist")
	public boolean isforgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}
	@Step("checking logo exist")
	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(Logo, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	@Step("username is : {0} and password {1}")
	public AccoutsPage doLogin(String username, String password) {

		//System.out.println("Credentials are:" + username + ":" + password);
		log.info("Credentials are:" + username + ":" + password);
		eleUtil.waitForVisibilityOfElement(UserName, AppConstants.MEDIUM_DEFAUTT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(Password, password);
		eleUtil.doclick(LoginBtn);

		return new AccoutsPage(driver);
	}
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAUTT_WAIT).click();
		return new RegisterPage(driver);
	}

}
