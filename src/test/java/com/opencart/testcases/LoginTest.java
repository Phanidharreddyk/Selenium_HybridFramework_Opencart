package com.opencart.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;


import com.opencart.base.BaseClass;
import com.opencart.pages.AccountPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.LoginPage;
import com.opencart.utils.Utilities;

public class LoginTest extends BaseClass {
	
	LoginPage loginpage;
	AccountPage aPage;
	public WebDriver driver;

	public LoginTest() throws IOException {
		super();

	}

	@BeforeMethod
	public void beforeLogin() throws InterruptedException {

		driver = initializeBrowser(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		loginpage = homepage.clickOnLogin();
	}

	@AfterMethod
	public void afterLogin() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "logindata")
	public void verifyLoginWithValidCredentials(String email, String password) {

		
		loginpage.enterEmail(email);
		loginpage.enterPassword(password);
		aPage = loginpage.clickOnLogin();
		
		Assert.assertTrue(aPage.verifyEditAccountInfo());
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {
		
		loginpage.enterEmail(Utilities.generateEmail());
		loginpage.enterPassword("adin");
		loginpage.clickOnLogin();
		String actualText = (new HomePage(driver)).getWarningMessageText();		
		String expectedText = "Warning: No match for E-Mail Address and/or Password.";
		AssertJUnit.assertEquals(actualText, expectedText);
	}

	@Test(priority = 3)
	public void verifyLoginWithInValidEmail() {
		
		loginpage.enterEmail(Utilities.generateEmail());
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLogin();

		String actualText = (new HomePage(driver)).getWarningMessageText();	
		String expectedText = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualText, expectedText);
	}

	@Test(priority = 4)
	public void verifyLoginWithInValidPassword() {
		
		loginpage.enterEmail(prop.getProperty("validUser"));
		loginpage.enterPassword("udfvbeiwhf");
		loginpage.clickOnLogin();
		
		String actualText = (new HomePage(driver)).getWarningMessageText();	
		String expectedText = "Warning: No match for E-Mail Address and/or Password.";
		AssertJUnit.assertEquals(actualText, expectedText);
	}

	@Test(priority = 5)
	public void verifyLoginWithoutData() {

		loginpage.clickOnLogin();
		String actualText = (new HomePage(driver)).getWarningMessageText();	
		String expectedText = "Warning: No match for E-Mail Address and/or Password.";
		AssertJUnit.assertEquals(actualText, expectedText);
	}

	@DataProvider
	public Object[][] logindata() throws IOException {

		Object[][] data = Utilities.getExcellData("Login");
		return data;
	}
}
