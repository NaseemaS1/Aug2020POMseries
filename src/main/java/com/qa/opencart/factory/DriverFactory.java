package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	 private static final Logger log = LogManager.getLogger(DriverFactory.class);

	 public static String highlight = null;

	public WebDriver initDriver(Properties prop) {
		String browsername = prop.getProperty("browser");
		//String browsername = System.getProperty("browser");
		//System.out.println("Browser name is:" + browsername);
		
		log.info("Browser name is:" + browsername);
		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);

		switch (browsername.toLowerCase().trim()) {
		case "chrome":
			log.info("Running it on chrome browser....");
		     if(Boolean.parseBoolean(prop.getProperty("remote"))) {
		    	 //run it on grid
		    	 log.info("Running it on remote machine");
		    	 initRemoteDriver(browsername);
		     }
		     else {
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
		    	 log.info("running it on local");
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		     }
			break;
		case "firefox":
			 if(Boolean.parseBoolean(prop.getProperty("remote"))) {
		    	 //run it on grid
		    	 initRemoteDriver(browsername);
		     }
		     else {
			//driver = new FirefoxDriver(optionsManager.getFierfoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFierfoxOptions()));
		     }
			break;
		case "edge":
			 if(Boolean.parseBoolean(prop.getProperty("remote"))) {
		    	 //run it on grid
		    	 initRemoteDriver(browsername);
		     }
		     else {
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		     }

			break;
		case "safari":
			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			//System.out.println("Please pass the right browser:" + browsername);
			log.warn("please pass the right browser:"+browsername);
			throw new FrameworkException("NO Browser Found");

		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}
	/**
	 * run tests on grid
	 * @param browsername
	 */
	private void initRemoteDriver(String browsername) {

		System.out.println("Running tests on GRID with browser:" +browsername);
		try {
		switch (browsername.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
			break;
		case "firefox":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFierfoxOptions()));
			break;
		case "edge":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
			break;

		default:
			System.out.println("Wrong browser info..can not run on grid remote machine");
			break;
		}
		
			
		}
		catch(MalformedURLException e) {
	}
	}
		

	public static  WebDriver getDriver() {
		return tlDriver.get();	
	}

	public Properties initprop() {
		
		//mvn clean install -Denv="qa"
		FileInputStream ip=null;
		prop = new Properties();
		
		String envName=System.getProperty("env");	
		//System.out.println("Environment name is: " +envName);
		log.info("Environment name is: " +envName);
		try {
		if(envName==null) {
			//System.out.println("Your environment is null, so by default running tests in qa..");
			log.info("Your environment is null, so by default running tests in qa..");
			ip=new FileInputStream("./src\\test\\resources\\config\\config.qa.properties");
			log.info(ip);
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
			//System.out.println("Please pass the right envinornment name.." +envName);
			log.error("Please pass the right envinornment name.." +envName);
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

	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {
		
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()+".png";
				
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
