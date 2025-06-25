package com.testek.study.lesson09;

import com.testek.study.common.Menu;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

/**
 * Class for Wait
 */
@Slf4j
public class SeleniumWaitTest {
    private WebDriver mWebDriver;

    @BeforeMethod
    public void beforeTestMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        mWebDriver = new ChromeDriver(chromeOptions);
    }

    @AfterMethod
    public void afterMethod() {
        if (mWebDriver != null) {
            mWebDriver.quit();
            mWebDriver = null;
        }
    }

    /**
     * Init Implicit Wait
     */
    @Test
    public void initImplicitWait() {
        gotoTestWebsite(Menu.FORM, Menu.STUDENT_FORM);
        // Init implicit wait
        mWebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Find the element within 10s
        // After 10s, if the element is not found, it will throw NoSuchElementException
        mWebDriver.findElement(By.xpath("//input[@id='firstName']"));
    }

    /**
     * Init Explicit Wait
     */
    @Test
    public void initExplicitWait() {
        gotoTestWebsite(Menu.FORM, Menu.STUDENT_FORM);

        // Init WebDriverWait with 10s; Default interval time: 500ms
        WebDriverWait wait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));

        String firstNameXPath = "//input[@placeholder='Enter your first name']";
        // Wait for the element to be visible
        WebElement firstNameEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstNameXPath)));
        log.info("ID :{}", firstNameEle.getAttribute("id"));

        // Send keys to the element
        firstNameEle.sendKeys("Vincent");

        WebDriverWait seleniumWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        seleniumWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstNameXPath)));
    }


    /**
     * Init Fluent Wait
     */
    @Test
    public void initFluentWait(){
        gotoTestWebsite(Menu.FORM, Menu.STUDENT_FORM);

        // Waiting 30 seconds for an element to be present on the page, checking
        // for its presence once every 5 seconds.
        FluentWait<WebDriver> fluentWait = new FluentWait<>(mWebDriver)
                .withTimeout(Duration.ofSeconds(30))        // Timeout
                .pollingEvery(Duration.ofSeconds(5))        // Interval time; 5sec/times
                .ignoring(NoSuchElementException.class);    // Ignore NoSuchElementException

        // Wait for the element to be visible
        String firstNameXPath = "//input[@placeholder='Enter your first name']";

        // Use FluentWait to wait for the element to be visible
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstNameXPath)));

        // Otherwise, you can use the FluentWait to wait for the element to be clickable
        WebElement firstNameEle = fluentWait.until(driver -> driver.findElement(By.xpath(firstNameXPath)));
        log.info("ID :{}", firstNameEle.getAttribute("id"));
    }


    /**
     * Go to Test Website
     */
    private void gotoTestWebsite(Menu parent, Menu subMenu) {
        String url = "https://testek.vn/lab/auto/web-elements/";
        mWebDriver.get(url);

        String XPATH_MENU_FORMAT = "//div[@test-id='%s']";

        // Access: Form > [Student Form]
        WebElement mnuElementEle = mWebDriver.findElement(By.xpath(String.format(XPATH_MENU_FORMAT, parent.getName())));
        mnuElementEle.click();

        WebElement mnuEle = mWebDriver.findElement(By.xpath(String.format(XPATH_MENU_FORMAT, subMenu.getName())));
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
