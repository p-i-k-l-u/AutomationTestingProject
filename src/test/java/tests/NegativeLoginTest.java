package tests;

import com.framework.base.BaseTest;
import com.framework.driver.DriverManager;
import com.framework.pages.LoginPage;
import com.framework.utilities.ExtentReportManager;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginTest extends BaseTest {

    @Test
    public void invalidLoginTest() {

        ExtentReportManager.setTest(extent.createTest(
                "Invalid Login Test"
        ));

        ExtentReportManager.getTest().info(
                "Entering Invalid Credentials"
        );

        LoginPage loginPage =
                new LoginPage(DriverManager.getDriver());

        loginPage.login(
                "WrongUser",
                "WrongPassword"
        );

        ExtentReportManager.getTest().info(
                "Checking Error Message"
        );

        Assert.assertTrue(
                loginPage.isErrorDisplayed()
        );

        ExtentReportManager.getTest().pass(
                "Error Message Verified"
        );
    }
}