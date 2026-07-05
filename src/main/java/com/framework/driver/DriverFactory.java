package com.framework.driver;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.framework.utilities.ConfigReader;

public class DriverFactory {

    public static WebDriver initDriver(String browser) {
        WebDriver driver = null;
        try {
            if(browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new RemoteWebDriver(
                        new URL("http://localhost:4444"),
                        options
                );
            }
            // FIREFOX
            else if(browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                driver = new RemoteWebDriver(
                        new URL("http://localhost:4444"),
                        options
                );
            }
            // EDGE
            else if(browser.equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();
                driver = new RemoteWebDriver(
                        new URL("http://localhost:4444"),
                        options
                );
            }
            
            if (driver != null) {
                driver.manage().window().maximize();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return driver;
    }
}



// --------------------------------------- Normal Code ---------------------

//package com.framework.driver;
//
//import com.framework.utilities.ConfigReader;
//
//import org.openqa.selenium.WebDriver;
//
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class DriverFactory {
//
//    public static WebDriver driver;
//
//    public static WebDriver initDriver() {
//
//        String browser =
//                ConfigReader.getProperty(
//                        "browser"
//                );
//
//        if(browser.equalsIgnoreCase("chrome")) {
//
//            WebDriverManager.chromedriver().setup();
//
//            driver = new ChromeDriver();
//
//            driver.manage().window().maximize();
//        }
//
//        return driver;
//    }
//}