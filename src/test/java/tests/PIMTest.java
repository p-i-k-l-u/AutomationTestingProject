package tests;

import com.framework.base.BaseTest;
import com.framework.driver.DriverManager;
import com.framework.pages.LoginPage;
import com.framework.pages.PIMPage;
import com.framework.utilities.ConfigReader;
import com.framework.utilities.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Random;

public class PIMTest extends BaseTest {

    @Test(priority = 1)
    public void addNewEmployeeAndSearchTest() {
        ExtentReportManager.setTest(extent.createTest("PIM - Add New Employee and Verify Search"));

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        ExtentReportManager.getTest().info("Logging in with valid credentials");
        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // Ensure dashboard is fully loaded before navigating
        com.framework.pages.DashboardPage dashboard = new com.framework.pages.DashboardPage(DriverManager.getDriver());
        Assert.assertTrue(dashboard.verifyDashboardDisplayed(), "Dashboard should display successfully after login.");

        PIMPage pimPage = new PIMPage(DriverManager.getDriver());
        ExtentReportManager.getTest().info("Navigating to PIM Module");
        pimPage.navigateToPIM();

        ExtentReportManager.getTest().info("Navigating to Add Employee Screen");
        pimPage.navigateToAddEmployee();

        // Generate a unique 6-digit random ID to prevent collisions on the shared database
        String randomId = String.valueOf(new java.util.Random().nextInt(900000) + 100000);
        String firstName = "QA";
        String lastName = "Engineer" + randomId;
        
        ExtentReportManager.getTest().info("Entering employee details. Random ID: " + randomId);
        pimPage.enterEmployeeDetails(firstName, "Automation", lastName, randomId);

        ExtentReportManager.getTest().info("Saving the employee profile");
        pimPage.clickSave();

        // Explicitly wait for saving redirect to complete
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(DriverManager.getDriver(), java.time.Duration.ofSeconds(20));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("viewPersonalDetails"));

        ExtentReportManager.getTest().info("Navigating to Employee List search");
        pimPage.navigateToEmployeeList();

        ExtentReportManager.getTest().info("Searching for employee ID: " + randomId);
        pimPage.searchByEmployeeId(randomId);

        int resultsCount = pimPage.getSearchResultsCount();
        ExtentReportManager.getTest().info("Results found: " + resultsCount);

        Assert.assertEquals(resultsCount, 1, "Exactly one matching employee record should be found in search table.");
        ExtentReportManager.getTest().pass("Employee added and verified successfully in search table.");
    }

    @Test(priority = 2)
    public void uploadProfilePictureAndDetailsTest() {
        ExtentReportManager.setTest(extent.createTest("PIM - Upload Profile Picture and Save Details"));

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        ExtentReportManager.getTest().info("Logging in with valid credentials");
        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // Ensure dashboard is fully loaded before navigating
        com.framework.pages.DashboardPage dashboard = new com.framework.pages.DashboardPage(DriverManager.getDriver());
        Assert.assertTrue(dashboard.verifyDashboardDisplayed(), "Dashboard should display successfully after login.");

        PIMPage pimPage = new PIMPage(DriverManager.getDriver());
        ExtentReportManager.getTest().info("Navigating to PIM Module");
        pimPage.navigateToPIM();

        ExtentReportManager.getTest().info("Searching for existing employees");
        pimPage.navigateToEmployeeList();
        
        ExtentReportManager.getTest().info("Selecting first employee record from list");
        pimPage.clickFirstRowDetails();

        ExtentReportManager.getTest().info("Clicking on profile photo layout");
        pimPage.clickProfileImage();

        // Prepare absolute path for avatar file upload
        File avatarFile = new File("src/test/resources/testdata/avatar.jpg");
        String absolutePath = avatarFile.getAbsolutePath();
        ExtentReportManager.getTest().info("Uploading profile photo from: " + absolutePath);
        pimPage.uploadPhoto(absolutePath);

        ExtentReportManager.getTest().info("Saving photo profile edits");
        pimPage.clickSave();

        boolean isSuccess = pimPage.isSuccessToastDisplayed();
        ExtentReportManager.getTest().info("Success toast displayed: " + isSuccess);
        
        Assert.assertTrue(isSuccess, "Successfully updated notification should be displayed upon file upload.");
        ExtentReportManager.getTest().pass("Profile photograph uploaded and verified successfully.");
    }
}
