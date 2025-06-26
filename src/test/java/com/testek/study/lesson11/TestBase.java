package com.testek.study.lesson11;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Objects;


@Setter
@Getter
@Listeners({TestListener.class})
@Slf4j
public class TestBase {
    // The driver for interacting with the webpage
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    public TestBase() {
        // Constructor
    }

    @Parameters({"browser"})
    @BeforeSuite
    public void beforeSuite(@Optional("chrome") String browser) {
        // Before Suite
    }

    @BeforeMethod(alwaysRun = true)
    public void addInvocation(ITestResult tr) {
        // Action before test method
    }

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        // Khởi tạo Chrome Driver
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        // Quite Chrome Driver
    }

    /**
     * Method will be executed before each class, configure Chrome Driver and initialize
     */
    @BeforeClass
    public void beforeClass() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--max-window-size");
        chromeOptions.addArguments("--remote-allow-origins=*");
        mWebDriver = new ChromeDriver(chromeOptions);
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));

        mWebDriver.manage().window().maximize();
    }

    /**
     * Method will be executed after each class, will close all running chrome sessions - Debug Mode
     **/
    @AfterClass
    public void afterClass() {
        if (Objects.nonNull(mWebDriver)) mWebDriver.quit();
    }

}
