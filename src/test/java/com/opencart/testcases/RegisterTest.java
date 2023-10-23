package com.opencart.testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import com.opencart.base.BaseClass;
import com.opencart.pages.AccountSuccessPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.RegistrationPage;
import com.opencart.utils.Utilities;

public class RegisterTest extends BaseClass {

	RegistrationPage regpage;
	AccountSuccessPage accountSuccessPage;
	public WebDriver driver;
	
	public RegisterTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void beforeLogin() {

		driver = initializeBrowser(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		regpage = homepage.clickOnRegister();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegistrationWithMandatoryFields() {

		regpage.enterFirstname("phani");
		regpage.enterLastname("kotala");
		regpage.enterEmail(Utilities.generateEmail());
		regpage.eneterMobileNo("4872348384");
		regpage.eneterPass("admin");
		regpage.confirmPass("admin");
		regpage.clickOnAgree();
		accountSuccessPage = regpage.clickOnSubmit();

		String actualSuccessHeading = accountSuccessPage.getAccountSuccessText();
		AssertJUnit.assertEquals(actualSuccessHeading, "Your Account Has Been Created!");

	}

	@Test(priority = 2)
	public void verifyRegistrationWithAllFields() {

		regpage.enterFirstname("phani");
		regpage.enterLastname("kotala");
		regpage.enterEmail(Utilities.generateEmail());
		regpage.eneterMobileNo("4872348384");
		regpage.eneterPass("admin");
		regpage.confirmPass("admin");
		regpage.clickYes();
		regpage.clickOnAgree();
		accountSuccessPage = regpage.clickOnSubmit();

		String actualSuccessHeading = accountSuccessPage.getAccountSuccessText();
		AssertJUnit.assertEquals(actualSuccessHeading, "Your Account Has Been Created!");

	}

	@Test(priority = 3)
	public void verifyRegistrationWithExistingEmail() {

		regpage.enterFirstname("phani");
		regpage.enterLastname("kotala");
		regpage.enterEmail("phanidhar1234@gmail.com");
		regpage.eneterMobileNo("4872348384");
		regpage.eneterPass("admin");
		regpage.confirmPass("admin");
		regpage.clickOnAgree();
		accountSuccessPage = regpage.clickOnSubmit();

		String actualSuccessHeading = regpage.getWarningMessageText();
		Assert.assertTrue(actualSuccessHeading.contains("Warning: E-Mail Address is already registered!"));

	}

	@Test(priority = 4)
	public void verifyRegistrationWithoutFillingAnyDetails() {
		
		regpage.clickOnSubmit();
		
		String actualSuccessHeading = driver
				.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		AssertJUnit.assertTrue(actualSuccessHeading.contains("Warning: You must agree to the Privacy Policy!"));

		String actualWarning1 = driver.findElement(By.xpath("//*[@id=\"account\"]/div[2]/div/div")).getText();
		AssertJUnit.assertTrue(actualWarning1.contains("First Name must be between 1 and 32 characters!"));

		String actualWarning2 = driver.findElement(By.xpath("//*[@id=\"account\"]/div[3]/div/div")).getText();
		AssertJUnit.assertTrue(actualWarning2.contains("Last Name must be between 1 and 32 characters!"));

		String actualWarning3 = driver.findElement(By.xpath("//*[@id=\"account\"]/div[4]/div/div")).getText();
		AssertJUnit.assertTrue(actualWarning3.contains("E-Mail Address does not appear to be valid!"));

		String actualWarning4 = driver.findElement(By.xpath("//*[@id=\"account\"]/div[5]/div/div")).getText();
		AssertJUnit.assertTrue(actualWarning4.contains("Telephone must be between 3 and 32 characters!"));

		String actualWarning5 = driver.findElement(By.xpath("//*[@id=\"content\"]/form/fieldset[2]/div[1]/div/div"))
				.getText();
		AssertJUnit.assertTrue(actualWarning5.contains("Password must be between 4 and 20 characters!"));

	}

}
