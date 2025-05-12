package com.testek.study.lesson06;

import com.testek.study.common.LogUtils;
import com.testek.study.common.Menu;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

import static java.lang.Thread.sleep;

/**
 * Khoi tao ChromeDriver va basic actions
 */
@Slf4j
public class ChromeDriverDemoTest {
    private WebDriver mWebDriver;

    @BeforeMethod
    public void beforeTestMethod() {
        // Step 01: Setup Chrome Driver, 3 ways to do it
        // 01: Config the driver path
        //String chromeDriverPath = "src/test/resources/driver/chromedriver";
        //System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        // Note: Demo & Show the error - miss match the version of Chrome and ChromeDriver

        // 02: Using WebDriverManager to setup the driver automatically
        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        // Note: You need to add the webdrivermanager library in pom.xml

        // 03: From Selenium 4.6.0 you can use Selenium Manager to download the driver automatically

        // Step 02: Initialize Chrome Options: Contains the options for Chrome
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");

        // Step 3: Initialize WebDriver -> Interact with the elements on the website
        mWebDriver = new ChromeDriver(chromeOptions);
        mWebDriver.manage().window().maximize(); // Set the window size to maximum

        // mWebDriver: The driver for interacting with the webpage
    }

    /**
     * Method will be executed after each class, will close all running chrome sessions - Debug Mode
     **/
    @AfterClass
    public void afterClass() {
        if (Objects.nonNull(mWebDriver)) mWebDriver.quit();
    }


    /**
     * URL: <a href="https://demoqa.com/automation-practice-form">...</a>
     * Access to Elements navigation
     */
    @Test
    public void testInitElement() {
        // Access the website using WebDriver and get method
        String BASE_URL = "https://testek.vn/lab/auto/web-elements/";
        mWebDriver.get(BASE_URL);

        gotoTestWebsite();
        /*
         * Find the element on the website
         * Use the locator that has been found before
         */
        String txtFullNameXPath = "//input[@test-id='textbox-form-input-name']";
        WebElement txtFullNameEle = mWebDriver.findElement(By.xpath(txtFullNameXPath));

        // Check if the element is correct
        String placeHolder = txtFullNameEle.getAttribute("placeholder");
        LogUtils.info("Placeholder 1: {}", placeHolder);


        txtFullNameEle = mWebDriver.findElement(By.id("name"));

        // Check if the element is correct
        placeHolder = txtFullNameEle.getAttribute("placeholder");
        LogUtils.info("Placeholder 2: {}", placeHolder);
    }

    /**
     * Action co ban cho cac WebElement
     */
    @Test
    public void testActionBasic() {
        gotoTestWebsite();

        // Send Key
        String txtFullNameXPath = "//input[@test-id='textbox-form-input-name']";
        WebElement txtFullNameEle = mWebDriver.findElement(By.xpath(txtFullNameXPath));
        txtFullNameEle.sendKeys("Vincent");
        log.info("Send Key [Vincent] successfully...");
        waitForDebug(5000);

        // Click
        String btnClearXPath = "//button[@test-id='textbox-form-reset']";
        WebElement btnClearEle = mWebDriver.findElement(By.xpath(btnClearXPath));
        btnClearEle.click();
        log.info("Click [Clear] successfully...");
        waitForDebug(5000);

        // Send Key
        txtFullNameXPath = "//input[@test-id='textbox-form-input-name']";
        txtFullNameEle = mWebDriver.findElement(By.xpath(txtFullNameXPath));
        txtFullNameEle.sendKeys("Testek - KIEM THU THUC CHIEN");
        log.info("Send Key [Testek - KIEM THU THUC CHIEN] successfully...");
        waitForDebug(5000);

        // Clear
        txtFullNameEle.clear();
        log.info("Clear [Testek - KIEM THU THUC CHIEN] successfully...");
        waitForDebug(5000);
    }


    /**
     * Go to Test Website
     */
    private void gotoTestWebsite() {
        String url = "https://testek.vn/lab/auto/web-elements/";
        mWebDriver.get(url);

        String XPATH_MENU_FORMAT = "//div[@test-id='%s']";

        // Access: Form > [Student Form]
        WebElement mnuElementEle = mWebDriver.findElement(By.xpath(String.format(XPATH_MENU_FORMAT, Menu.ELEMENTS.getName())));
        mnuElementEle.click();

        WebElement mnuEle = mWebDriver.findElement(By.xpath(String.format(XPATH_MENU_FORMAT, Menu.TEXT_BOX.getName())));
        mnuEle.click();

        waitForDebug(5000);
    }

    /**
     * Sleep for debugging
     *
     * @param milliseconds : number of milliseconds
     */
    private void waitForDebug(long milliseconds) {
        try {
            sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
