package com.opencart.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BaseClass {
	public WebDriver driver;
	public Properties prop;
	
	public BaseClass() throws IOException {
		prop = new Properties();
		File file = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\opencart\\config\\config.properties");
		FileInputStream file1 = new FileInputStream(file);
		prop.load(file1);

	}

	public WebDriver initializeBrowser(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get(prop.getProperty("url"));

		return driver;
	}
}
