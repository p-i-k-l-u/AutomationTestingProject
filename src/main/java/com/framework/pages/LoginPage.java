//package com.framework.pages;
//
//import com.framework.base.BasePage;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class LoginPage extends BasePage {
//
//    WebDriver driver;
//
//    WebDriverWait wait;
//
//    // Locators
//    By username =
//            By.name("username");
//
//    By password =
//            By.name("password");
//
//    By loginBtn =
//            By.xpath("//button[@type='submit']");
//    By errorMessage =
//            By.xpath("//p[contains(text(),'Invalid')]");
//    
//    // Constructor
//    public LoginPage(WebDriver driver) {
//
//        super(driver);
//
//        this.driver = driver;
//
//        wait = new WebDriverWait(
//                driver,
//                Duration.ofSeconds(10)
//        );
//    }
//
//    // Actions
//
//    public void enterUsername(String user) {
//
//        wait.until(
//                ExpectedConditions
//                        .visibilityOfElementLocated(username)
//        );
//
//        type(username, user);
//    }
//
//    public void enterPassword(String pass) {
//
//        wait.until(
//                ExpectedConditions
//                        .visibilityOfElementLocated(password)
//        );
//
//        type(password, pass);
//    }
//
//    public void clickLogin() {
//
//        wait.until(
//                ExpectedConditions
//                        .elementToBeClickable(loginBtn)
//        );
//
//        click(loginBtn);
//    }
//
//    public void login(String user, String pass) {
//
//        enterUsername(user);
//
//        enterPassword(pass);
//
//        clickLogin();
//
//        // Wait after login
//        try {
//            Thread.sleep(5000);
//        }
//        catch(Exception e) {
//
//            e.printStackTrace();
//        }
//    }
//}



// --------------------------- NEW Login Code with Helper Function -----------------
package com.framework.pages;

import com.framework.base.BasePage;

import com.framework.utilities.WaitHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    WebDriver driver;

    By username =
            By.name("username");

    By password =
            By.name("password");

    By loginBtn =
            By.xpath("//button[@type='submit']");

    By errorMessage =
            By.cssSelector(".oxd-alert-content-text");

    public LoginPage(WebDriver driver) {

        super(driver);

        this.driver = driver;
    }

    public void login(String user, String pass) {

        WaitHelper.waitForElement(
                driver,
                username
        );

        type(username, user);

        type(password, pass);

        click(loginBtn);
    }

    public boolean isErrorDisplayed() {

        WaitHelper.waitForElement(
                driver,
                errorMessage
        );

        return isDisplayed(errorMessage);
    }
}
