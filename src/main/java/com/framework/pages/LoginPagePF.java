package com.framework.pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

public class LoginPagePF {

	WebDriver driver;

	// Locators

	@FindBy(name = "username")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginBtn;

	// Constructor
	public LoginPagePF(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	// Actions

	public void login(String user, String pass) {

		username.sendKeys(user);

		password.sendKeys(pass);

		loginBtn.click();
	}
}