package com.opencart.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static String generateEmail() {

		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "kotala" + timestamp + "@gmail.com";
	}

	public static Object[][] getExcellData(String sheetName) throws IOException {
		
		File file = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\opencart\\testdata\\TestData_Opencart.xlsx");
		
			FileInputStream fis = new FileInputStream(file); 
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
		

			
		
		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {

			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < cols; j++) {

				XSSFCell cell = row.getCell(j);
				CellType celltype = cell.getCellType();

				switch (celltype) {

				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
				//System.out.println(data[i][j]);
			}

		}
		return data;

	}
	
	public static String takeScreenshot(WebDriver driver, String testname) {
		
		File screenshotfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String Destination = System.getProperty("user.dir") + "\\Screenshots\\"+ testname+"_"+ Utilities.getTimeStamp() + ".png";
		try {
			FileHandler.copy(screenshotfile, new File(Destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Destination;
			
	}
	
	public static String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy_hh-mm-ss");
		String timestamp = formatter.format(date);
		return timestamp;
	}
}
