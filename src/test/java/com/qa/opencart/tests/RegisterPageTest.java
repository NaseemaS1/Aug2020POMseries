package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
    
	@BeforeClass
	public void regSetup() {
		registerPage=loginpage.navigateToRegisterPage();
	}
	
	public String getRandomEmailId() {
		return "testautomation"+System.currentTimeMillis()+"@opencart.com";
		//testautomation121212@opencart.com
		//return "testautomation"+UUID.randomUUID()+@gmail.com
	}

	/*
	 * @DataProvider public Object[][] getUserRegData() { 
	 * return new Object[][] {
	 * 
	 * {"Jim", "Cherry", "9876543216", "jim@123", "yes"}, {"Chole", "auto",
	 * "98765432456", "chole@123", "yes"}, {"jessica", "automation", "98765432175",
	 * "jessica@123", "yes"}, 
	 * }; }
	 */
	@DataProvider
	public Object[][] getUserRegTestExcelData() {
		Object regdata[][]=ExcelUtil.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
		return regdata;
	}
	@Test(dataProvider="getUserRegData")
	public void userRegTest(String username,String lastname,String telephone,String password,String subscribe) throws InterruptedException{
		boolean isRegDone=registerPage.userRegisteration(username, lastname, getRandomEmailId(), telephone,password,subscribe);
		Assert.assertTrue(isRegDone);
	}
	
}
