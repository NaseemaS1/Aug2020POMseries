package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class productInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By ProductHeader = By.cssSelector("div#content h1");
	private By ProductImages = By.cssSelector("ul.thumbnails img");
	private By ProductMetadata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By ProductpriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	//private Map<String, String> productMap = new HashMap<String, String>();
	//private Map<String, String> productMap = new LinkedHashMap<String, String>();
	private Map<String, String> productMap = new TreeMap<String, String>();


	// page const..
	public productInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getProductHeaderName() {
		String ProductHeaderVal = eleUtil.doElementGetText(ProductHeader);
		System.out.println("Product Header Val is:" + ProductHeaderVal);
		return ProductHeaderVal;
	}

	public int getproductImagesCount() {

		int imagesCount = eleUtil.waitForVisibilityOfElements(ProductImages, AppConstants.MEDIUM_DEFAUTT_WAIT).size();
		System.out.println("Product" + getProductHeaderName() + "images Count" + imagesCount);
		return imagesCount;

	}
	
	private void getProductMetaData() {
		List<WebElement>metaDataList=eleUtil.waitForVisibilityOfElements(ProductMetadata, AppConstants.MEDIUM_DEFAUTT_WAIT);
		for(WebElement e:metaDataList) {
			String metaData=e.getText();
			String metaKey=metaData.split(":")[0].trim();
			String metaVal=metaData.split(":")[1].trim();
			productMap.put(metaKey, metaVal);
		}
		
		
	}
	
	private void getProductPriceData() {
		List<WebElement>metaPriceList=eleUtil.waitForVisibilityOfElements(ProductpriceData, AppConstants.MEDIUM_DEFAUTT_WAIT);
		
		String productPrice=metaPriceList.get(0).getText();
		String productExPrice=metaPriceList.get(1).getText().split(":")[1].trim();
		
		productMap.put("price",productPrice);
		productMap.put("extraxprice", productExPrice);
		
		
	}
	
	public Map<String, String> getProductDetais() {
		productMap.put("Product Name",getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productMap);
		return productMap;
	}
	
	
	
	
	
	
	
	
	
	

}
