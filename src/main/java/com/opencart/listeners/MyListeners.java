 package com.opencart.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.opencart.utils.ExtentReporter;
import com.opencart.utils.Utilities;

public class MyListeners implements ITestListener {
	
	ExtentReports extentReporter;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		//System.out.println(" --> Testing Started");
		extentReporter = ExtentReporter.extentReportGen();
		
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		//System.out.println(result.getName() + " --> started Executing");
		extentTest = extentReporter.createTest(result.getName());
		extentTest.log(Status.INFO, "Test Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//System.out.println(result.getName() + " --> Executed Successfully");
		extentTest.log(Status.PASS, "Test Success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//System.out.println(result.getName() + " --> Failed");
		extentTest.log(Status.FAIL, "Test failed,refer Snap");
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String snapPath = Utilities.takeScreenshot(driver, result.getName());
		extentTest.addScreenCaptureFromPath(snapPath);
		extentTest.log(Status.INFO, result.getThrowable());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//System.out.println(result.getName() + " --> skipped");
		extentTest.log(Status.SKIP, "Test Skipped");
		extentTest.log(Status.INFO, result.getThrowable());
		
	}

	@Override
	public void onFinish(ITestContext context) {
		//System.out.println(" --> Testing Completed");
		extentReporter.flush();
		
		String path = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\"+ ExtentReporter.filename;
		File file = new File(path);
		try {
			Desktop.getDesktop().browse(file.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
