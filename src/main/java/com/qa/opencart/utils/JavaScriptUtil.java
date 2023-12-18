package com.qa.opencart.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) this.driver;
	}

	public String getTitleByJs() {

		return js.executeScript("return document.title").toString();

	}

	public String getURLByJs() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.URL").toString();

	}

	public void generateJSAlert(String mesg) {
		js.executeScript("alert('" + mesg + "')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}

	public void generateJSConfirm(String mesg) {
		js.executeScript("confirm('" + mesg + "')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}

	public void generateJSPrompt(String mesg, String value) {
		js.executeScript("prompt('" + mesg + "')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
		alert.accept();
	}

	public void goBackWithJS() {
		js.executeScript("history.go(-1)");
	}

	public void goForwardWithJS() {
		js.executeScript("history.go(1)");
	}

	public void PageRefreshWithJS() {
		js.executeScript("history.go(0)");
	}

	public String getInnerText() {

		return js.executeScript("return document.documentElement.innerText").toString();

	}
	
	public void ScrollMiddlePageDown() {
		js.executeScript("window.scrollTo(0,document.body,ScrollHeight/2)");
	}
	
	public void ScrollPageDown() {
		js.executeScript("window.scrollTo(0,document.body,ScrollHeight)");
	}
	public void ScrollPageDown(String height) {
		js.executeScript("window.scrollTo(0,'"+height+"')");
	}
	
	public void ScrollPageUp() {
		js.executeScript("window.scrollTo(document.body,ScrollHeight,0)");
	}
	public void ScrollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public void ZoomChromeEdgeSafri(String zoomPercentage) {
		String Zoom="document.body.style.zoom = '"+zoomPercentage+"%'";
		js.executeScript(Zoom);
		
	}
	
	public void ZoomFirefox(String zoomPercentage) {
		String Zoom="document.body.style.MozTransform = 'Scale("+zoomPercentage+")'";
		js.executeScript(Zoom);
		
	}
	
	public void drawBorder(WebElement element) {
		js.executeScript("arguments[0].style.border='3px solid red'",element);
	}
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) {
			changeColor("rgb(0,200,0)", element);// Green
			changeColor(bgcolor, element);// Purple
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		//G->P->G->P

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	
	public void clickElementByJS(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

	public void sendKeysUsingWithId(String id, String value) {
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
						  //document.getElementById('input-email').value ='tom@gmail.com'
	}
	
	
	
	

}
