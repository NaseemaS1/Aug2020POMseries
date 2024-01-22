package com.qa.opencart.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic 100:Design open cart login value")
@Story("Epic 101:login page features")
@Feature("F50:Feature login page ")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest{

	private static final Logger log = LogManager.getLogger(LoginPageTest.class);
	@Description("login page test....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void LoginPageTitleTest() {
		String title=loginpage.getPageTitle();
		log.info("Actual login page title:"+title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	} 
	@Description("login url test....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void LoginPageUrlTest() {
		String actUrl=loginpage.getPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	@Description("verifying forgot pwd link test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginpage.isforgotPwdLinkExist());
	}
	@Description("verifying app logo exist test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void appLogoExistTest() {
		Assert.assertTrue(loginpage.isLogoExist());
	}
	@Description("verifying user is able to login with correct credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=5)
	public void loginTest() {
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
        Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
}
