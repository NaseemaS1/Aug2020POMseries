package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest{

	
	@Test
	public void LoginPageTitleTest() {
		String title=loginpage.getPageTitle();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}
	@Test
	public void LoginPageUrlTest() {
		String actUrl=loginpage.getPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginpage.isforgotPwdLinkExist());
	}
	@Test
	public void appLogoExistTest() {
		Assert.assertTrue(loginpage.isLogoExist());
	}
	@Test
	public void loginTest() {
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
        Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
}
