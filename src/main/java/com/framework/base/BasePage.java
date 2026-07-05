package com.framework.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {

        this.driver = driver;
    }

    // Click Method
    public void click(By locator) {

        driver.findElement(locator).click();
    }

    // Enter Text
    public void type(By locator, String text) {

        driver.findElement(locator).sendKeys(text);
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