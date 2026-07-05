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
import com.framework.utilities.ConfigReader;
import com.framework.utilities.ExtentManager;
import com.framework.utilities.ScreenshotUtil;

public class BaseTest {

    public WebDriver driver;

    public static ExtentReports extent;

    public static ExtentTest test;

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

            System.out.println("Initializing Driver");

            driver = DriverFactory.initDriver(browser);

            System.out.println("Opening URL");

            driver.get(
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
    public void tearDown(
            ITestResult result
    ) {

        // PASS
        if(result.getStatus()
                == ITestResult.SUCCESS) {

            test.log(
                    Status.PASS,
                    "Test Passed Successfully"
            );
        }

        // FAIL
        else if(result.getStatus()
                == ITestResult.FAILURE) {

            test.log(
                    Status.FAIL,
                    "Test Failed"
            );

            // Error Message
            test.fail(
                    result.getThrowable()
            );

            // Capture Screenshot
            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(
                            driver,
                            result.getName()
                    );

            try {

                // Add Screenshot in Report
                test.addScreenCaptureFromPath(
                        screenshotPath
                );
            }

            catch(Exception e) {

                e.printStackTrace();
            }
        }

        // SKIP
        else {

            test.log(
                    Status.SKIP,
                    "Test Skipped"
            );
        }

        if(driver != null) {

            driver.quit();
        }
    }

    @AfterSuite
    public void flushReport() {

        extent.flush();
    }
}