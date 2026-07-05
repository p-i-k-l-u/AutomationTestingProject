package com.framework.utilities;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

public class SelfHealingHelper {

	public static WebElement findElement(

			WebDriver driver,

			By primaryLocator,

			By fallbackLocator) {

		try {

			return driver.findElement(primaryLocator);
		}

		catch (Exception e) {

			System.out.println("Primary Locator Failed");

			return driver.findElement(fallbackLocator);
		}
	}
}