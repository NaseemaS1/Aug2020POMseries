package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class Login {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// by locators: OR

	private By UserName = By.id("input-email");
	private By Password = By.id("input-password");
	private By LoginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By Logo = By.xpath("//img[@title='naveenopencart']");
	private By registerLink=By.linkText("Register");

	// page const..
	public Login(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// page actions/methods
	public String getPageTitle() {

		String title = eleUtil.waitFortitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAUTT_WAIT);
		System.out.println("Login page Title" + title);
		return title;
	}

	public String getPageUrl() {
		String Url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAUTT_WAIT);

		System.out.println("Login page url" + Url);
		return Url;
	}

	public boolean isforgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(Logo, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	public AccoutsPage doLogin(String username, String password) {

		System.out.println("Credentials are:"+username+":"+password);
		eleUtil.waitForVisibilityOfElement(UserName, AppConstants.MEDIUM_DEFAUTT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(Password, password);
		eleUtil.doclick(LoginBtn);
		
		return new AccoutsPage(driver);
	}
    
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAUTT_WAIT).click();
		return new RegisterPage(driver);
	}

	
}
