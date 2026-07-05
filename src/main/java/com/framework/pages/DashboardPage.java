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

        String currentURL =
                driver.getCurrentUrl();

        System.out.println(
                "Current URL : " + currentURL
        );

        return currentURL.contains(
                "dashboard"
        );
    }
}