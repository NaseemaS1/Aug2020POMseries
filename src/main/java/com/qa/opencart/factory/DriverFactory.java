package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	

	public WebDriver initDriver(Properties prop) {
		String browsername = prop.getProperty("browser");
		//String browsername = System.getProperty("browser");
		System.out.println("Browser name is:" + browsername);

		optionsManager = new OptionsManager(prop);

		switch (browsername.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver(optionsManager.getFierfoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFierfoxOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));

			break;
		case "safari":
			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Please pass the right browser:" + browsername);
			throw new FrameworkException("NO Browser Found");

		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}
	
	public static  WebDriver getDriver() {
		return tlDriver.get();	
	}

	public Properties initprop() {
		
		//mvn clean install -Denv="qa"
		FileInputStream ip=null;
		prop = new Properties();
		
		String envName=System.getProperty("env");	
		System.out.println("Environment name is: " +envName);
		try {
		if(envName==null) {
			System.out.println("Your environment is null, so by default running tests in qa..");
			ip=new FileInputStream("./src\\test\\resources\\config\\config.qa.properties");
		}
		
		else {
			
		switch (envName.toLowerCase().trim()) {
		case "qa":
			//ip=new FileInputStream("./src\\test\\resources\\config\\config.qa.properties");
			ip=new FileInputStream(".src\\test\\resources\\config\\config.qa.properties");
			break;
		case "dev":
			ip=new FileInputStream(".src\\test\\resources\\config\\config.dev.properties");
			break;
		case "uat":
			ip=new FileInputStream(".src\\test\\resources\\config\\config.uat.properties");
			break;
		case "stage":
			ip=new FileInputStream(".src\\test\\resources\\config\\config.stage.properties");
			break;
		case "prod":
			ip=new FileInputStream(".src\\test\\resources\\config\\config.properties");
			break;

		default:
			System.out.println("Please pass the right envinornment name.." +envName);
			throw new FrameworkException("Wrong env name: " +envName);
			
		}	
		}
		}
		catch(FileNotFoundException e) {
				
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
		return prop;
	}

	public static String getScreenshot(String methodName) {
		// TODO Auto-generated method stub
		return null;
	}
}
