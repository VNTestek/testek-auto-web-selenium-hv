package com.testek.study.lesson13.exercise;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.Objects;

import static com.testek.consts.FrameConst.WaitConfig.WAIT_EXPLICIT;


@Setter
@Getter
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
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--max-window-size");
        chromeOptions.addArguments("--remote-allow-origins=*");
        mWebDriver = new ChromeDriver(chromeOptions);
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(20));

        mWebDriver.manage().window().maximize();
    }

    /**
     * Method will be executed after each class, will close all running chrome sessions - Debug Mode
     **/
    @AfterClass
    public void afterClass() {
        if (Objects.nonNull(mWebDriver)) mWebDriver.quit();
    }


    // Define tạm

    public WebDriverWait getWaitDriver(long... duration) {
        long interval = duration.length > 0 && duration[0] != 0 ? duration[0] : WAIT_EXPLICIT;
        return new WebDriverWait(mWebDriver, Duration.ofSeconds(interval), Duration.ofMillis(500));
    }

    /**
     * Wait for element visible with By object
     *
     * @param by : The By object of element
     * @return : The WebElement if it is visible, null otherwise
     */
    public WebElement waitForElementVisible(By by) {
        WebElement element = null;
        try {
            element = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            if (Objects.nonNull(element))
                log.info("Element with locator {} : visible", by.toString());
        } catch (Exception e) {
            var elementList = mWebDriver.findElements(by);
            log.error("Element {} : invisible {}", by.toString(), elementList.isEmpty() ? "" : " Had more element with this XPATH. Please re-check!");
        }
        return element;
    }

    /**
     * Assert Element Objects.
     * Support 3 type of Failure Handling
     */
    public void assertTrueCondition(boolean isResult, @Nullable String errMsg) {
        Assert.assertTrue(isResult, errMsg);
    }

    /**
     * Assert Equal
     */
    public void assertEqualCondition(Object actual, Object expected, String errMsg) {
        Assert.assertEquals(actual, expected, errMsg);
    }

    /**
     * Get the element locator from WebElement
     */
    public String getLocatorFromWebElement(WebElement element) {
        var list = element.toString().split("->");
        if (list.length > 1)
            return element.toString().split("->")[1].replaceFirst("xpath:(?s)(.*)]", "$1").trim();
        else return element.toString();
    }

    public void verifyElementTextEqual(WebElement webElement, String expectedValue) {
        String elementText = getTextElement(webElement);
        assertEqualCondition(elementText.trim(), expectedValue.trim(), "Verify the text of element");
    }

    public String getTextElement(WebElement element) {
        return element.getText().trim();
    }


    /**
     * Go to specific URL
     *
     * @param URL       : URL Page
     * @param pageTitle : Page title
     */
    protected void goToSpecificURL(String URL, String pageTitle) {
        mWebDriver.get(URL);
        assertTrueCondition(verifyPageUrl(URL), String.format("Verify the '%s' page", pageTitle));
    }

    /**
     * Verify the URL Page
     */
    public boolean verifyPageUrl(String pageUrl) {
        log.info("Actual URL: {}", mWebDriver.getCurrentUrl());
        return mWebDriver.getCurrentUrl().contains(pageUrl.trim());
    }

    public void inputText(WebElement element, String title, String value) {
        try {
            element.clear();
            element.sendKeys(value);
        } finally {
            String locator = getLocatorFromWebElement(element);
            String msg = String.format("Insert text <b>[%s]</b> to <b>[%s]</b> <br/> <span style='font-size: 0.75em'>(Element's locator:  %s)</span>", value, title, locator);
        }
    }

    /**
     * Click the object
     */
    public void clickTo(WebElement element, String title) {
        element = waitForElementClickable(element);
        element.click();
        log.info("Click to element: {}", title);
    }

    public void selectDropdownContent(WebElement element, String expRowContent, String name) {
        clickElementViaJs(element, "Select " + name);
        inputText(element, name, expRowContent);

        clickTo(findDropContent(expRowContent), "Select " + name + " Type");
    }

    public void clickElementViaJs(WebElement element, String title) {
        getJsExecutor().executeScript("arguments[0].click()", element);
        log.info("Click to element by JS: {}", title);

    }

    /**
     * Initialize the JavaScript Executor
     */
    public JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) mWebDriver;
    }

    public WebElement findDropContent(String name) {
        return waitForElementVisible(By.xpath(String.format("//td[text()='%s']", name)));
    }

    /**
     * Wait for element clickable
     *
     * @param element: The WebElement to wait for
     * @return : The WebElement if it is clickable, null otherwise
     */
    public WebElement waitForElementClickable(WebElement element) {
        String locator = Strings.EMPTY;
        String msg = "clickable";
        try {
            locator = getLocatorFromWebElement(element);
            element = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            msg = "un-clickable";
        }
        log.info("Element {} : {}", locator, msg);
        return element;
    }
}
