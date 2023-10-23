package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;
	
	@FindBy(id = "input-email")
	WebElement emailBox;
	
	@FindBy(id = "input-password")
	WebElement passwordBox;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginBtn;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmail(String emailValue) {
		emailBox.sendKeys(emailValue);
	}
	
	public void enterPassword(String passValue) {
		passwordBox.sendKeys(passValue);
	}
	
	public AccountPage clickOnLogin() {
		loginBtn.click();
		return new AccountPage(driver);
	}
}
