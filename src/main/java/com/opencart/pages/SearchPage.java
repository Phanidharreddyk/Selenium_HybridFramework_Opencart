package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	public WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement HP;
	
	@FindBy(xpath = "//*[@id='content']/p[2]")
	private WebElement noProductMessage;
	
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isProductDisplayed() {
		return HP.isDisplayed();
	}
	
	public String getNoProductText() {
		return noProductMessage.getText();
	}
}
