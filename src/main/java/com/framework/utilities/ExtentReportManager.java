package com.framework.utilities;

import com.aventstack.extentreports.ExtentTest;

public class ExtentReportManager {

    private static final ThreadLocal<ExtentTest> testLocal = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return testLocal.get();
    }

    public static void setTest(ExtentTest testInstance) {
        testLocal.set(testInstance);
    }

    public static void unload() {
        testLocal.remove();
    }
}
