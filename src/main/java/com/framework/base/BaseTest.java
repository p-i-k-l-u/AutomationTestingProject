package com.framework.base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.driver.DriverFactory;
import com.framework.driver.DriverManager;
import com.framework.utilities.ConfigReader;
import com.framework.utilities.ExtentManager;
import com.framework.utilities.ExtentReportManager;
import com.framework.utilities.ScreenshotUtil;

public class BaseTest {

    public static ExtentReports extent;

    @BeforeSuite
    public void startReport() {
        extent = ExtentManager.getInstance();
    }
    
    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) throws Exception {
        try {
            System.out.println("Loading Config File");
            ConfigReader.loadProperties();

            System.out.println("Initializing Driver for browser: " + browser);
            WebDriver driver = DriverFactory.initDriver(browser);
            DriverManager.setDriver(driver);

            System.out.println("Opening URL");
            DriverManager.getDriver().get(
                    ConfigReader.getProperty("url")
            );

            System.out.println("Setup Completed");
        }
        catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        ExtentTest test = ExtentReportManager.getTest();

        if (test != null) {
            // PASS
            if (result.getStatus() == ITestResult.SUCCESS) {
                test.log(
                        Status.PASS,
                        "Test Passed Successfully"
                );
            }
            // FAIL
            else if (result.getStatus() == ITestResult.FAILURE) {
                test.log(
                        Status.FAIL,
                        "Test Failed"
                );

                // Error Message
                test.fail(result.getThrowable());

                // Capture Screenshot
                WebDriver driver = DriverManager.getDriver();
                if (driver != null) {
                    String screenshotPath = ScreenshotUtil.captureScreenshot(
                            driver,
                            result.getName()
                    );

                    try {
                        // Add Screenshot in Report
                        test.addScreenCaptureFromPath(screenshotPath);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            // SKIP
            else {
                test.log(
                        Status.SKIP,
                        "Test Skipped"
                );
            }
        }

        // Cleanup driver and thread locals
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                driver.quit();
            }
        } finally {
            DriverManager.unload();
            ExtentReportManager.unload();
        }
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}