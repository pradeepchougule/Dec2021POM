package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void productHeaderTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}
	
	@Test(priority = 2)
	public void productImageCountTest() {
		searchResultsPage = accountsPage.doSearch("iMac");
		productInfoPage = searchResultsPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(),Constants.IMAC_IMAGE_COUNT);
	}
	
	@Test(priority = 3)
	public void productInfoTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String> actproductInfoMap = productInfoPage.getProductInfo();
		actproductInfoMap.forEach((k,v) -> System.out.println(k + ":"+ v));
		softAssert.assertEquals(actproductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actproductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actproductInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();
		
	}
}
