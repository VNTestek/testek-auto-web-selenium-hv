package com.testek.study.lesson07;

import com.testek.study.common.Menu;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.util.Set;

import static java.lang.Thread.sleep;

/**
 * Class for Window interaction
 */
@Slf4j
public class WindowsInteractionTest {
    private WebDriver mWebDriver;
    private final String baseURL = "https://testek.vn/lab/auto/web-elements/";

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
     * Access to the website and use navigation
     */
    @Test
    public void testBrowserNavigation() {
        // Access to the website
        mWebDriver.get(baseURL);
        waitForDebug(5000);

        // Access to another link
        mWebDriver.get("https://selenium.dev");
        waitForDebug(5000);

        // Access to another link
        mWebDriver.navigate().to(baseURL);
        waitForDebug(5000);

        // Back to the previous page
        mWebDriver.navigate().back();
        waitForDebug(5000);

        // Move to the next page
        mWebDriver.navigate().forward();
        waitForDebug(5000);
    }

    /**
     * Windows in Selenium
     */
    @Test
    public void testWindows() {
        // Access to the website
        mWebDriver.get(baseURL);
        waitForDebug(5000);

        String curWinID = mWebDriver.getWindowHandle();

        // Create a new window tab
        mWebDriver.switchTo().newWindow(WindowType.TAB);
        mWebDriver.get("https://selenium.dev");
        waitForDebug(5000);

        // Get window handle list
        Set<String> windowList = mWebDriver.getWindowHandles();
        log.info("Windows List: {}", windowList);
        waitForDebug(5000);

        // Switch to previous window
        mWebDriver.switchTo().window(curWinID);
        waitForDebug(5000);
    }

    /**
     * Windows in Selenium
     */
    @Test
    public void testAlerts() {
        // Access to the website
        gotoTestWebsite(Menu.ALERT_FRAME_AND_WINDOWS, Menu.ALERT);

        // Alert interaction
        String altSimpleXPath = "//button[@test-id='simpleAlert']";
        WebElement altSimpleEle = mWebDriver.findElement(By.xpath(altSimpleXPath));
        altSimpleEle.click();

        // Switch to Alert
        Alert alert = mWebDriver.switchTo().alert();
        String expValue = alert.getText();
        log.info("Alert text: {}", expValue);
        waitForDebug(5000);

        alert.accept();                     // Press to OK
        log.info("Accept alert successfully...");

        // alert.sendKeys("Selenium");      // Type your message
        // alert.dismiss();                 // Press the Cancel button
    }


    @Test(description = "Windows interaction")
    public void testPracticeWindows() {
        gotoTestWebsite(Menu.ALERT_FRAME_AND_WINDOWS, Menu.WINDOWS);

        // Click "Browser Windows"
        String browserWinXPath = "//button[@test-id='newTab']";
        WebElement browserWinEle = mWebDriver.findElement(By.xpath(browserWinXPath));
        browserWinEle.click();
        waitForDebug(5000);

        // Get current windows
        String currentWin = mWebDriver.getWindowHandle();
        log.info("Current window: {}", currentWin);

        // Click to each button
        WebElement newTabEle = mWebDriver.findElement(By.id("newTab"));
        newTabEle.click();
        waitForDebug(5000);

        Set<String> windowList = mWebDriver.getWindowHandles();
        log.info("Window list: {}", windowList);

        // Switch to a new window
        for (String win : windowList) {
            if (!win.equals(currentWin)) {
                mWebDriver.switchTo().window(win);
                waitForDebug(3000);
            }
        }
        log.info("Switch to new window: {}", mWebDriver.getWindowHandle());

        // Back to the first window at step 2 (Use the first window method and switchTo)
        mWebDriver.switchTo().window(currentWin);
        waitForDebug(3000);
    }


    @Test(description = "Thuc hanh 02: Tuong tac voi Alert")
    public void testPracticeAlert() {
        gotoTestWebsite(Menu.ALERT_FRAME_AND_WINDOWS, Menu.ALERT);

        // Click "Alerts"
        String alertXPath = "//button[test-id='simpleAlert']";
        WebElement alertWinEle = mWebDriver.findElement(By.xpath(alertXPath));
        alertWinEle.click();
        waitForDebug(3000);

        // Open prompt alert
        WebElement alertEle = mWebDriver.findElement(By.id("promptAlert"));
        alertEle.click();
        waitForDebug(3000);

        // Switch to Alert
        Alert alert = mWebDriver.switchTo().alert();
        String alertText = alert.getText();
        log.info("Text : {}", alertText);
        waitForDebug(3000);

        alert.sendKeys("Automation Test - 2025");
        waitForDebug(3000);
        alert.accept();
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
