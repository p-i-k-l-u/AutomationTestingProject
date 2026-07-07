package com.framework.pages;

import com.framework.base.BasePage;

import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    WebDriver driver;

    public DashboardPage(WebDriver driver) {

        super(driver);

        this.driver = driver;
    }

    public boolean verifyDashboardDisplayed() {
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(30));
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("dashboard"));
            
            String currentURL = driver.getCurrentUrl();
            System.out.println("Current URL : " + currentURL);
            return true;
        } catch (Exception e) {
            System.out.println("Timeout waiting for dashboard URL. Current URL: " + driver.getCurrentUrl());
            return false;
        }
    }
}