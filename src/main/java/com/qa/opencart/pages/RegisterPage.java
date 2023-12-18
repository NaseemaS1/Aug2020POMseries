package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	// page const..
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.xpath("//input[@name='agree']");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.xpath("//*[@id=\"content\"]/h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	

	public boolean userRegisteration(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) throws InterruptedException {

		eleUtil.waitForVisibilityOfElement(this.firstName, AppConstants.MEDIUM_DEFAUTT_WAIT).sendKeys(firstName);

		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doclick(subscribeYes);
		} else {
			eleUtil.doclick(subscribeNo);
		}
		
		eleUtil.doclick(agreeCheckBox);
		
		eleUtil.doclick(continueButton);
		
		
		
		String successMesg = eleUtil.waitForVisibilityOfElement(successMessg, AppConstants.MEDIUM_DEFAUTT_WAIT).getText();
		System.out.println("Account Created Confirmation Message: "+successMesg);

		if (successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG)) {
				eleUtil.doclick(logoutLink);
				eleUtil.doclick(registerLink);
			return true;
		} else {
			return false;
		}

	}
	
	
	
	


}
