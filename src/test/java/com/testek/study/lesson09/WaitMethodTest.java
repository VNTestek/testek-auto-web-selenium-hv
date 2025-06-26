package com.testek.study.lesson09;

import com.testek.study.common.Menu;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

@Slf4j
public class WaitMethodTest {
    private WebDriver mWebDriver;
    private WebDriverWait mWebDriverWait;

    @BeforeMethod
    public void beforeTestMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        mWebDriver = new ChromeDriver(chromeOptions);

        // Init Explicit Wait: Wait for 10s with interval 500ms
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void afterMethod() {
        if (mWebDriver != null) {
            mWebDriver.quit();
            mWebDriver = null;
        }
    }


    @Test(description = "TextBox > Text Box")
    public void testTextBox() {
        gotoTestWebsite(Menu.ELEMENTS, Menu.TEXT_BOX);

        // Init Explicit Wait
        WebElement txtFirstNameEle = waitForElement(By.xpath("//input[@id='firstName']"));
        txtFirstNameEle.isDisplayed();

        // Send keys to element
        sendKeysToElement(By.xpath("//input[@id='firstName']"), "Nguyen Van A", true);

        // Sleep for debugging
        waitForDebug(5000);
    }


    @Test(description = "Visible & Present")
    public void testDisplayed() {
        gotoTestWebsite(Menu.WIDGETS, Menu.MENU);

        // Find the element
        WebElement btnWebDesignEle = waitForElementPresent(By.xpath("//a[@test-id='submenu-link-web-design']"));
        WebElement btnServiceEle = waitForElement(By.xpath("//a[@test-id='menu-link-services']"));

        log.info("btnWebDesignEle - display: {}", btnWebDesignEle.isDisplayed());
        log.info("btnServiceEle - display: {}", btnServiceEle.isDisplayed());
        // Sleep for debugging
        waitForDebug(2000);
    }

    /**
     * Wait for element visible
     *
     * @param by : The locator of the element (By object)
     * @return : The WebElement
     */
    private WebElement waitForElement(By by) {
        log.info("Waiting for element visibility: {}", by);
        return mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Wait for element present
     *
     * @param by : The locator of the element (By object)
     * @return : The WebElement
     */
    private WebElement waitForElementPresent(By by) {
        log.info("Waiting for element present: {}", by);
        return mWebDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Wait for element clickable
     */
    private WebElement waitForElementClickable(By by) {
        log.info("Waiting for element clickable: {}", by);
        return mWebDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    }


    /**
     * Wait for element invisible
     */
    private Boolean waitForElementInvisible(By by) {
        log.info("Waiting for element invisible: {}", by);
        return mWebDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * Send keys to element
     */
    private void sendKeysToElement(By by, String text, boolean isClear) {
        log.info("Send keys to element: {}", by);
        WebElement element = waitForElement(by);
        if (element == null) {
            log.error("Element not found: {}", by);
            return;
        }

        // Clear the text if isClear = true
        if (isClear) {
            element.clear();
        }
        element.sendKeys(text);
        log.info("Send keys to element: {}", text);
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
