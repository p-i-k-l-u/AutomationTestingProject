package com.framework.pages;

import com.framework.base.BasePage;
import com.framework.utilities.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PIMPage extends BasePage {

    private WebDriver driver;

    // Sidebar Locators
    private By pimMenu = By.xpath("//a[contains(@href, 'viewPimModule')]");

    // Top Bar Locators inside PIM
    private By addEmployeeTab = By.xpath("//a[text()='Add Employee']");
    private By employeeListTab = By.xpath("//a[text()='Employee List']");

    // Add Employee Locators
    private By firstNameField = By.name("firstName");
    private By middleNameField = By.name("middleName");
    private By lastNameField = By.name("lastName");
    private By employeeIdField = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");
    private By saveButton = By.xpath("//button[@type='submit']");

    // Employee List / Search Locators
    private By searchEmpIdField = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By tableRows = By.xpath("//div[@class='oxd-table-body']/div[@class='oxd-table-card']");

    // Profile & Photo Locators
    private By employeeImage = By.className("employee-image");
    private By fileInput = By.cssSelector("input[type='file']");
    
    // Toast Notification Locator
    private By toastMessage = By.cssSelector(".oxd-toast");

    public PIMPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void navigateToPIM() {
        WaitHelper.waitForElement(driver, pimMenu);
        click(pimMenu);
    }

    private void waitForFormLoaderToDisappear() {
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        } catch (Exception e) {
            // Ignore if loader was not present
        }
    }

    private void waitForSpinnerToDisappear() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-loading-spinner")));
        } catch (Exception e) {
            // Ignore if spinner was not present
        }
    }

    public void navigateToAddEmployee() {
        waitForFormLoaderToDisappear();
        waitForSpinnerToDisappear();
        WaitHelper.waitForElement(driver, addEmployeeTab);
        click(addEmployeeTab);
    }

    public String getEmployeeId() {
        waitForFormLoaderToDisappear();
        waitForSpinnerToDisappear();
        WaitHelper.waitForElement(driver, employeeIdField);
        
        // Wait until the value attribute is not empty
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            wait.until(d -> {
                String val = d.findElement(employeeIdField).getAttribute("value");
                return val != null && !val.trim().isEmpty();
            });
        } catch (Exception e) {
            System.out.println("Warning: Employee ID field was empty or could not be retrieved.");
        }
        
        String empId = driver.findElement(employeeIdField).getAttribute("value");
        System.out.println("DEBUG: Extracted pre-filled Employee ID = [" + empId + "]");
        return empId;
    }

    public void enterEmployeeDetails(String firstName, String middleName, String lastName) {
        waitForFormLoaderToDisappear();
        WaitHelper.waitForElement(driver, firstNameField);
        type(firstNameField, firstName);
        type(middleNameField, middleName);
        type(lastNameField, lastName);
    }

    public void enterEmployeeDetails(String firstName, String middleName, String lastName, String empId) {
        waitForFormLoaderToDisappear();
        WaitHelper.waitForElement(driver, firstNameField);
        type(firstNameField, firstName);
        type(middleNameField, middleName);
        type(lastNameField, lastName);
        
        // Set value directly via Javascript to guarantee Vue.js model state update
        waitForFormLoaderToDisappear();
        WebElement idInput = driver.findElement(employeeIdField);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
            "arguments[0].click();" +
            "arguments[0].focus();" +
            "arguments[0].value = arguments[1];" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            idInput, empId
        );
    }

    public void clickSave() {
        waitForFormLoaderToDisappear();
        WaitHelper.waitForElement(driver, saveButton);
        click(saveButton);
    }

    public void navigateToEmployeeList() {
        waitForFormLoaderToDisappear();
        waitForSpinnerToDisappear();
        WaitHelper.waitForElement(driver, employeeListTab);
        click(employeeListTab);
    }

    public void searchByEmployeeId(String empId) {
        waitForFormLoaderToDisappear();
        waitForSpinnerToDisappear();
        WaitHelper.waitForElement(driver, searchEmpIdField);
        WebElement searchField = driver.findElement(searchEmpIdField);
        
        // Set value directly via Javascript to guarantee Vue.js model state update
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
            "arguments[0].click();" +
            "arguments[0].focus();" +
            "arguments[0].value = arguments[1];" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            searchField, empId
        );
        
        click(searchButton);
        waitForSpinnerToDisappear();
    }

    public int getSearchResultsCount() {
        try {
            waitForSpinnerToDisappear();
            List<WebElement> rows = driver.findElements(tableRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickFirstRowDetails() {
        waitForFormLoaderToDisappear();
        waitForSpinnerToDisappear();
        WaitHelper.waitForElement(driver, tableRows);
        List<WebElement> rows = driver.findElements(tableRows);
        if (!rows.isEmpty()) {
            rows.get(0).click();
            waitForFormLoaderToDisappear();
            waitForSpinnerToDisappear();
        }
    }

    public void clickProfileImage() {
        waitForFormLoaderToDisappear();
        waitForSpinnerToDisappear();
        WaitHelper.waitForElement(driver, employeeImage);
        click(employeeImage);
        waitForFormLoaderToDisappear();
        waitForSpinnerToDisappear();
    }

    public void uploadPhoto(String absoluteFilePath) {
        // Wait for hidden input to be present in DOM (cannot wait for visibility as input is hidden by styling)
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(fileInput));
        driver.findElement(fileInput).sendKeys(absoluteFilePath);
    }

    public boolean isSuccessToastDisplayed() {
        try {
            WaitHelper.waitForElement(driver, toastMessage);
            return isDisplayed(toastMessage);
        } catch (Exception e) {
            return false;
        }
    }
}
