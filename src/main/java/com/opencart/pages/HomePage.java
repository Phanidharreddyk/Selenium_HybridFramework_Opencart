package com.opencart.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	public WebDriver driver;
	
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText = "Login")
	private WebElement LoginOption;
	
	@FindBy(linkText = "Register")
	private WebElement RegisterOption;
	
	@FindBy(xpath = "//*[@id=\"search\"]/input")
	private WebElement SearchBox;
	
	@FindBy(xpath = "//*[@id=\"search\"]/span/button")
	private WebElement SearchSubmit;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement WarningText;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnMyAccount() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(myAccountDropMenu));
		myAccountDropMenu.click();
	}
	
	public LoginPage clickOnLogin() {
		LoginOption.click();
		return new LoginPage(driver);
	}
	
	public RegistrationPage clickOnRegister() {
		RegisterOption.click();
		return new RegistrationPage(driver);
	}
	
	public void enterProductName(String name) {
		SearchBox.sendKeys(name);
	}
	
	public SearchPage clickonSearch() {
		SearchSubmit.click();
		return new SearchPage(driver);
	}
	
	public String getWarningMessageText() {
		return WarningText.getText();
	}
	
	

}
