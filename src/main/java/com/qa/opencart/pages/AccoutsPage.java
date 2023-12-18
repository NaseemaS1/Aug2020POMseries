package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccoutsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By Search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accHeaders = By.cssSelector("div#content >h2");

	// page const..
	public AccoutsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// page actions
	public String getPageTitle() {

		String title = eleUtil.waitFortitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_DEFAUTT_WAIT);
		System.out.println("Account page Title" + title);
		return title;
	}

	public String getPageUrl() {
		// String Url=this.getPageUrl();

		String Url = eleUtil.waitForURLContains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION,
				AppConstants.SHORT_DEFAUTT_WAIT);

		System.out.println("Account page url" + Url);
		return Url;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	public void logout() {
		if (isLogoutLinkExist()) {
			eleUtil.doclick(logoutLink);
		}
	}

	public boolean isSearchFeildExist() {
		return eleUtil.waitForVisibilityOfElement(Search, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	public List<String> getAccountsHeaders() {

		List<WebElement> headersList = eleUtil.waitForVisibilityOfElements(accHeaders,
				AppConstants.MEDIUM_DEFAUTT_WAIT);
		List<String> headersvalList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			headersvalList.add(text);

		}
		return headersvalList;

	}

	public SearchResultsPage doSearch(String searchkey) {
		eleUtil.waitForVisibilityOfElement(Search, AppConstants.MEDIUM_DEFAUTT_WAIT).clear();

		eleUtil.waitForVisibilityOfElement(Search, AppConstants.MEDIUM_DEFAUTT_WAIT).sendKeys(searchkey);
		eleUtil.doclick(searchIcon);
		return new SearchResultsPage(driver);// tdd
	}

}
