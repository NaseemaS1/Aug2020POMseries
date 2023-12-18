package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPagetest extends BaseTest{

	@BeforeTest
	public void accSetup() {
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test (priority = 1)
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test (priority = 2)
	public void accPageUrlTest() {
		System.out.println("Accounts Page URL:"+accPage.getPageUrl());
		Assert.assertTrue(accPage.getPageUrl().contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION) );
	}
	
	@Test
	public void isLogOutLinkExist() {
		Assert.assertTrue(accPage.isLogoutLinkExist());

	}
	
	@Test (priority = 6)
	public void isSearchFeildLinkExist() {
		Assert.assertTrue(accPage.isSearchFeildExist());

	}
	@Test (priority = 3)
	public void AccountHeaderCountTest() {
		List<String>actAccHeadersList=accPage.getAccountsHeaders();
		System.out.println(actAccHeadersList);
		Assert.assertEquals(actAccHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	
	@Test (priority = 4)
	public void AccountHeaderTest() {
		List<String>actAccHeadersList=accPage.getAccountsHeaders();
		System.out.println(actAccHeadersList);
		Assert.assertEquals(actAccHeadersList,AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	@Test (priority = 5)
	public void SearchTest() {
		searchResultsPage=accPage.doSearch("Macbook");
		ProductInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		String ActHeader=ProductInfoPage.getProductHeaderName();
		Assert.assertEquals(ActHeader, "MacBook Pro");
	}
	
	
	
	
}
