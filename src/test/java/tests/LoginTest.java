//package tests;
//
//import com.framework.base.BaseTest;
//import com.framework.pages.DashboardPage;
//import com.framework.pages.LoginPage;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class LoginTest extends BaseTest {
//
//    @Test
//    public void validLoginTest()
//            throws InterruptedException {
//
//        // Create Login Page Object
//        LoginPage loginPage =
//                new LoginPage(driver);
//
//        // Perform Login
//        loginPage.login(
//                "Admin",
//                "admin123"
//        );
//        
//        System.out.println(driver.getCurrentUrl());
//
//      
//
//        // Dashboard Verification
//        DashboardPage dashboard =
//                new DashboardPage(driver);
//
//        Assert.assertTrue(
//                dashboard.verifyDashboardDisplayed()
//        );
//
//        System.out.println(
//                "Login Successful"
//        );
//    }
//}


// -------------------------- nEW Code ---------------

//package tests;
//
//import com.framework.base.BaseTest;
//
//import com.framework.pages.DashboardPage;
//import com.framework.pages.LoginPage;
//
//import com.framework.utilities.ConfigReader;
//
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//public class LoginTest extends BaseTest {
//
//    @Test
//    public void validLoginTest() {
//
//        LoginPage loginPage =
//                new LoginPage(driver);
//
//        loginPage.login(
//
//                ConfigReader.getProperty(
//                        "username"
//                ),
//
//                ConfigReader.getProperty(
//                        "password"
//                )
//        );
//
//        DashboardPage dashboard =
//                new DashboardPage(driver);
//
//        Assert.assertTrue(
//                dashboard.verifyDashboardDisplayed()
//        );
//
//        System.out.println(
//                "Login Successful"
//        );
//    }
//    
//    @DataProvider(name = "loginData")
//
//    public Object[][] loginData() {
//
//        return new Object[][] {
//
//                {"Admin", "admin123"},
//                {"Wrong", "Wrong123"}
//        };
//    }
//}


//--------------------------------------- NEW Code 2026-05-25 ----
package tests;

import com.framework.base.BaseTest;
import com.framework.driver.DriverManager;
import com.framework.pages.DashboardPage;
import com.framework.pages.LoginPage;
import com.framework.utilities.ConfigReader;
import com.framework.utilities.ExtentReportManager;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        // Create Test
        ExtentReportManager.setTest(extent.createTest(
                "Valid Login Test"
        ));

        ExtentReportManager.getTest().info(
                "Browser Launched"
        );

        LoginPage loginPage =
                new LoginPage(DriverManager.getDriver());

        ExtentReportManager.getTest().info(
                "Entering Username and Password"
        );

        loginPage.login(

                ConfigReader.getProperty(
                        "username"
                ),

                ConfigReader.getProperty(
                        "password"
                )
        );

        ExtentReportManager.getTest().info(
                "Login Button Clicked"
        );

        DashboardPage dashboard =
                new DashboardPage(DriverManager.getDriver());

        ExtentReportManager.getTest().info(
                "Verifying Dashboard"
        );

        Assert.assertTrue(
                dashboard.verifyDashboardDisplayed()
        );

        ExtentReportManager.getTest().pass(
                "Dashboard Verified Successfully"
        );

        System.out.println(
                "Login Successful"
        );
    }
}