package com.framework.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {

        this.driver = driver;
    }

    // Click Method with Wait and JS fallback
    public void click(By locator) {
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).click();
        } catch (Exception e) {
            try {
                org.openqa.selenium.WebElement element = driver.findElement(locator);
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } catch (Exception jsEx) {
                // Throw the original exception if JS fallback also fails
                throw e;
            }
        }
    }

    // Enter Text
    public void type(By locator, String text) {
        org.openqa.selenium.WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Get Text
    public String getText(By locator) {

        return driver.findElement(locator).getText();
    }

    // Is Displayed
    public boolean isDisplayed(By locator) {

        return driver.findElement(locator).isDisplayed();
    }
}