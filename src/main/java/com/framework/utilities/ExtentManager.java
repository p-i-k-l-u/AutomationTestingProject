package com.framework.utilities;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	static ExtentReports extent;

	public static ExtentReports getInstance() {

		if (extent == null) {

			// Report Path
			ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport.html");

			// Report Name
			reporter.config().setReportName("Automation Test Report");

			// Document Title
			reporter.config().setDocumentTitle("Selenium Automation Report");

			extent = new ExtentReports();

			extent.attachReporter(reporter);

			// System Information
			extent.setSystemInfo("Tester", "Piklu");

			extent.setSystemInfo("Framework", "Selenium + TestNG");

			extent.setSystemInfo("OS", System.getProperty("os.name"));

			extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		}

		return extent;
	}
}