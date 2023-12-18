package com.qa.opencart.tests;


import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductResultsPageTest extends BaseTest{
	

	@BeforeTest
	public void accSetup() {
		accPage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getSearchData(){
		return new Object[][] {
			{"MacBook","MacBook Pro",4},
			{"MacBook","MacBook Air",4},
			{"iMac","iMac",3},
			{"Samsung","Samsung SyncMaster 941BW",1}
		};
	}
	@Test(dataProvider="getSearchData")
	public void ProductImagesTest(String searchKey,String productName,int imageCount) {
		searchResultsPage=accPage.doSearch(searchKey);
		ProductInfoPage=searchResultsPage.selectProduct(productName);
		Assert.assertEquals(ProductInfoPage.getproductImagesCount(), imageCount);
	}

	@Test
	public void productInfoTest() {
		searchResultsPage=accPage.doSearch("MacBook");
		ProductInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		Map<String , String> productDetailsMap=ProductInfoPage.getProductDetais();
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");

		softAssert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("extraxprice"), "$2,000.00");
		
		softAssert.assertAll();

	}
}
