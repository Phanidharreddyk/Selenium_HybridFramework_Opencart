package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

	public WebDriver driver;

	@FindBy(id = "input-firstname")
	WebElement firstnameBox;

	@FindBy(id = "input-lastname")
	WebElement lastnameBox;
	
	@FindBy(id = "input-email")
	WebElement emailBox;
	
	@FindBy(id = "input-telephone")
	WebElement telephoneBox;
	
	@FindBy(id = "input-password")
	WebElement passwordBox;
	
	@FindBy(id = "input-confirm")
	WebElement passconfirmBox;
	
	@FindBy(name = "agree")
	WebElement agreeCheckbox;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement createBtn;
	
	@FindBy(xpath = "//*[@id=\"content\"]/form/fieldset[3]//label[1]/input")
	WebElement radioBtn;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement ExistingEmailWarningMessage;
	
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public void clickOnAgree() {
		agreeCheckbox.click();
	}
	
	public void enterFirstname(String name) {
		firstnameBox.sendKeys(name);
	}
	
	public void enterLastname(String name) {
		lastnameBox.sendKeys(name);
	}
	
	public void enterEmail(String email) {
		emailBox.sendKeys(email);
	}
	
	public void eneterMobileNo(String num) {
		telephoneBox.sendKeys(num);
	}
	
	public void eneterPass(String name) {
		passwordBox.sendKeys(name);
	}
	
	public void confirmPass(String name) {
		passconfirmBox.sendKeys(name);
	}
	
	public void clickYes() {
		radioBtn.click();
	}
	
	public AccountSuccessPage clickOnSubmit() {
		createBtn.click();
		return new AccountSuccessPage(driver);
	}
	
	public String getWarningMessageText() {
		return ExistingEmailWarningMessage.getText();
	}

	
}
