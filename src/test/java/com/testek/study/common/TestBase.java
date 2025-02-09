package com.testek.study.common;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

@Getter
public class TestBase {

    /**
     * Sleep for debugging
     *
     * @param milliseconds : number of milliseconds
     */
    protected void waitForDebug(long milliseconds) {
        try {
            sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Go to DemoQA Website
     */
    public void gotoDemoQAWebsite(WebDriver webDriver) {
        String url = "https://demoqa.com/elements";
        webDriver.get(url);
        webDriver.manage().window().maximize();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@src='/images/Toolsqa.jpg']")));
    }

    /**
     * Go to DemoQA Website
     */
    public void gotoDemoQAWebsite(WebDriver webDriver, String url) {
        webDriver.get(url);
        webDriver.manage().window().maximize();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@src='/images/Toolsqa.jpg']")));
    }


    /**
     * Go to navigation tab by the name
     *
     * @param tabName : The tab name
     */
    public void goToNavTab(String tabName, WebDriver webDriver) {
        String tabXPathForm = "//li[contains(@class,'btn btn-light')]//span[normalize-space()='%s']";
        String tabXPath = String.format(tabXPathForm, tabName.trim());
        webDriver.findElement(By.xpath(tabXPath)).click();
        waitForDebug(1000);
    }


    /**
     * Get dynamic locator
     *
     * @param locator       : The locator
     * @param dynamicValues : The dynamic values
     * @return : The dynamic locator
     */
    protected String getDynamicLocator(String locator, String... dynamicValues) {
        return String.format(locator, (Object[]) dynamicValues);
    }

    /**
     * Get WebElement
     */
    protected WebElement getWebElement(WebDriver webDriver, String locatorType) {
        return webDriver.findElement(getByLocator(locatorType));
    }

    /**
     * Get List WebElement
     */
    public List<WebElement> getListWebElement(WebDriver webDriver, String locatorType) {
        return webDriver.findElements(getByLocator(locatorType));
    }

    /**
     * SendKey to Element
     */
    protected void sendKeyToElement(WebDriver webDriver, String locatorType, String textValue) {
        try {
            WebElement element = getWebElement(webDriver, locatorType);
            element.clear();
            element.sendKeys(textValue);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Click to Element
     */
    public void clickToElement(WebDriver webDriver, String locatorType) {
        try {
            getWebElement(webDriver, locatorType).click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get by locator
     */
    protected By getByLocator(String locatorType) {
        By by = null;
        if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            by = By.xpath(locatorType);
        }
        return by;
    }

    /**
     * Scroll to Element
     */
    protected void scrollToElement(WebDriver webDriver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(webDriver, locatorType));
    }
}
