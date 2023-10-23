package com.opencart.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencart.base.BaseClass;
import com.opencart.pages.HomePage;
import com.opencart.pages.SearchPage;

public class SearchTest extends BaseClass {
	
	SearchPage searchpage;
	HomePage homepage;
	public WebDriver driver;

	public SearchTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowser(prop.getProperty("browser"));
		homepage = new HomePage(driver);
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchresultWithValidProduct() {

		homepage.enterProductName("HP LP3065");
		searchpage = homepage.clickonSearch();
		
		Assert.assertTrue(searchpage.isProductDisplayed());
	}

	@Test(priority = 2)
	public void verifySearchresultWithInValidProduct() {

		homepage.enterProductName("Dell");
		searchpage = homepage.clickonSearch();
		
		String actualText = searchpage.getNoProductText();
		Assert.assertEquals(actualText, "There is no product that matches the search criteria.");
	}

	@Test(priority = 3,dependsOnMethods = {"verifySearchresultWithInValidProduct"})
	public void verifySearchresultWithoutData() {

		searchpage = homepage.clickonSearch();
		
		String actualText = searchpage.getNoProductText();
		Assert.assertEquals(actualText, "There is no product that matches the search criteria.");
	}
}
