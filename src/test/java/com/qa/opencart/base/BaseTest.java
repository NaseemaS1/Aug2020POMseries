package com.qa.opencart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccoutsPage;
import com.qa.opencart.pages.Login;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.productInfoPage;

public class BaseTest {

	protected WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	protected Login loginpage;
    protected AccoutsPage accPage;
    protected SearchResultsPage searchResultsPage;
    protected productInfoPage ProductInfoPage;
    protected RegisterPage registerPage;
    
    private static final Logger log = LogManager.getLogger(BaseTest.class);
    protected SoftAssert softAssert;
    @Parameters({"browser","browserversion","testname"})
	@BeforeTest
	public void setup(String browserName,String browserversion,String testName) {
    	log.info(browserName+" : "+browserversion+" "+testName);
		df = new DriverFactory();
		prop=df.initprop();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserversion);
			prop.setProperty("testName", testName);
		}
		driver = df.initDriver(prop);
		loginpage=new Login(driver);
		softAssert=new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		log.info("browser is closed");
	}
}
