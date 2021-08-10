package com.wp.reports;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class temp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Date d = new Date();
		System.out.println(d.toString().replace(":" ,"_"));
		new File("C:\\a\\b").mkdirs();
		ExtentReports rep = ExtentManager.getInstance("C:\\Users\\kotarv\\eclipse-workspace\\Amp project\\MyAmpProject\\reports\\");
		ExtentTest test  = rep.createTest("TestA");
		test.log(Status.INFO, "Starting test A");
		test.log(Status.INFO, "Executing");
		test.log(Status.FAIL, "Failed");
		rep.flush();
		
	}

}
