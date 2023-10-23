package com.opencart.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static String filename = "TestReport_"+Utilities.getTimeStamp()+".html";
	 
	public static ExtentReports extentReportGen() {

		ExtentReports extentReporter = new ExtentReports();
		File reportFile = new File(System.getProperty("user.dir")+ "\\test-output\\ExtentReports\\"+filename);
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("Test Automation Report");
		sparkReporter.config().setReportName("My Test Report");
		
		extentReporter.attachReporter(sparkReporter);
		return extentReporter;
		
		
	}
	
	

}
