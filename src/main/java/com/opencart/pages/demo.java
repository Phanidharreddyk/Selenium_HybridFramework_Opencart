package com.opencart.pages;

import java.text.SimpleDateFormat;
import java.util.Date;

public class demo {

	public static void main(String args[]) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy_hh_mm_ss");
		String timestamp = formatter.format(date);
		System.out.println(timestamp);
	}
}
