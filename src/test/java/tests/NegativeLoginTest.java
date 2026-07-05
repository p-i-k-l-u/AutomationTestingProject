package tests;

import com.framework.base.BaseTest;

import com.framework.pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginTest
        extends BaseTest {

    @Test
    public void invalidLoginTest() {
    	
    	

        test = extent.createTest(
                "Invalid Login Test"
        );

        test.info(
                "Entering Invalid Credentials"
        );

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "WrongUser",
                "WrongPassword"
        );

        test.info(
                "Checking Error Message"
        );

        Assert.assertTrue(
                loginPage.isErrorDisplayed()
        );

        test.pass(
                "Error Message Verified"
        );
    }
    
 
}