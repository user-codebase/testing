package com.kodilla.testing2.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverConfig {
    public final static String FIREFOX = "FIREFOX_DRIVER";
    public final static String CHROME = "CHROME_DRIVER";

    public static WebDriver getDriver(final String driver) {

        if (driver.equals(FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();

        } else if (driver.equals(CHROME)) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();

        } else {
            throw new IllegalArgumentException("Unknown driver: " + driver);
        }
    }
}