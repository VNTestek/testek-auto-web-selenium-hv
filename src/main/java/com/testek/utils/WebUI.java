package com.testek.utils;

import com.testek.driver.DriverManager;
import com.testek.report.ExtentReportManager;
import com.testek.report.ExtentTestManager;
import com.testek.utils.configloader.AbsPropertyUtils;
import com.testek.utils.configloader.CaptureUtils;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import javax.annotation.Nullable;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.*;

import static com.testek.consts.FrameConst.DataConfig.*;
import static com.testek.consts.FrameConst.*;
import static com.testek.consts.FrameConst.ProjectConfig.WAIT_EXPLICIT;
import static com.testek.consts.FrameConst.ProjectConfig.WAIT_IMPLICIT;
import static com.testek.consts.FrameConst.ReportConst.DRAW_BORDER_ERR_ELEMENT;
import static com.testek.consts.FrameConst.ReportConst.SCREENSHOT_ALL_STEPS;
import static java.lang.Thread.sleep;

/**
 * WebUI provides the interaction method, based on the Selenium Automation Framework
 */
public class WebUI {
    /**
     * Create a web drive wait
     *
     * @param duration : The interval in milliseconds to scan the element
     */
    public static WebDriverWait getWaitDriver(long... duration) {
        long interval = duration.length > 0 && duration[0] != 0 ? duration[0] : WAIT_EXPLICIT;
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(interval), Duration.ofMillis(500));
    }

    /**
     * Get Selenium Action Manager
     */
    public static Actions getActions() {
        return new Actions(DriverManager.getDriver());
    }

    /**
     * Initialize the JavaScript Executor
     */
    public static JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) DriverManager.getDriver();
    }


    // region Navigation

    /**
     * Open website with get URL
     */
    @Step("Go to URL : {URL}")
    public static void goToURL(String URL) {
        var currentURL = DriverManager.getDriver().getCurrentUrl();
        if (URL.equalsIgnoreCase(currentURL)) return;

        DriverManager.getDriver().get(URL);
        String msg = ReportConst.BOLD_START + Icon.ICON_NAVIGATE_RIGHT + " Go to URL : " + ReportConst.BOLD_END + URL;
        addReportInfo(LogType.INFO, msg, "goToUrl_", null);
    }

    /**
     * Refresh the browser
     */
    @Step("Refresh URL : {URL}")
    public void refreshPage() {
        String URL = DriverManager.getDriver().getCurrentUrl();
        DriverManager.getDriver().navigate().refresh();

        String msg = ReportConst.BOLD_START + Icon.ICON_NAVIGATE_RIGHT + " Refresh URL : " + ReportConst.BOLD_END + URL;
        addReportInfo(LogType.INFO, msg, "refreshPage", null);
    }


    /**
     * Back to previous page
     */
    @Step("Back to URL : {URL}")
    public void backPreviousPage() {
        DriverManager.getDriver().navigate().back();

        String URL = DriverManager.getDriver().getCurrentUrl();
        String msg = ReportConst.BOLD_START + Icon.ICON_NAVIGATE_RIGHT + " Back to URL : " + ReportConst.BOLD_END + URL;
        addReportInfo(LogType.INFO, msg, "backToPage", null);
    }

    /**
     * Verify the URL Page
     */
    @Step("Verify the Page URL {pageUrl}")
    public static boolean verifyPageUrl(String pageUrl) {
        LogUtils.info("Actual URL: " + DriverManager.getDriver().getCurrentUrl());
        return DriverManager.getDriver().getCurrentUrl().contains(pageUrl.trim());
    }

    /**
     * Open the new tab in the browser
     */
    public static void openNewTab() {
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
    }

    /**
     * Open the new browser window
     */
    public static void openNewWindow() {
        DriverManager.getDriver().switchTo().newWindow(WindowType.WINDOW);
    }

    /**
     * Get the current window
     *
     * @return The id of the current window
     */
    public String getCurrentWindowHandle() {
        return DriverManager.getDriver().getWindowHandle();
    }

    /**
     * Close the all windows at the browser
     */
    public void closeAllWindowExceptCurrent() {
        String currentWindow = DriverManager.getDriver().getWindowHandle();

        Set<String> listWindows = DriverManager.getDriver().getWindowHandles();
        for (String window : listWindows) {
            if (!window.equals(currentWindow)) {
                switchToWindowByHandle(window);
                try {
                    DriverManager.getDriver().close();
                } catch (NoSuchWindowException exception) {
                    LogUtils.info("Need research!");
                }
                sleepMillisecond(500);
            }
        }
        switchToWindowByHandle(currentWindow);
    }

    /**
     * Switch to the last window
     */
    public static void switchToLastWindow() {
        Set<String> windowHandles = DriverManager.getDriver().getWindowHandles();
        DriverManager.getDriver().switchTo().window(DriverManager.getDriver().getWindowHandles().toArray()[windowHandles.size() - 1].toString());
    }

    // Windows

    /**
     * Switch to the specified window by a position
     *
     * @param position : The position of window
     */
    public static void switchToWindowOrTab(int position) {
        DriverManager.getDriver().switchTo().window(DriverManager.getDriver().getWindowHandles().toArray()[position].toString());
    }

    /**
     * Verify the number of windows or tabs
     *
     * @param number : The expected number of windows
     * @return true if the same, false otherwise
     */
    public static boolean verifyNumberOfWindowsOrTab(int number) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_EXPLICIT)).until(ExpectedConditions.numberOfWindowsToBe(number));
    }

    /**
     * Switch to the main window
     */
    public static void switchToMainWindow() {
        DriverManager.getDriver().switchTo().window(DriverManager.getDriver().getWindowHandles().toArray()[0].toString());
    }

    /**
     * Switch to the window by a window id
     *
     * @param windowHandle : The window id
     */
    public static void switchToWindowByHandle(String windowHandle) {
        DriverManager.getDriver().switchTo().window(windowHandle);
    }
    // endregion

    // region Find Element

    /**
     * Get Website Elements
     */
    public static WebElement getWebElement(Object object) {
        WebElement element;
        if (object instanceof By) {
            element = waitForElementVisibleWithBy((By) object);
            //element = DriverManager.getDriver().findElement((By) object);
        } else element = (WebElement) object;
        return element;
    }

    /**
     * Get all elements with a by object
     *
     * @param by : The by object of elements
     * @return : The elements
     */
    public static List<WebElement> getListWebElement(By by) {
        overwriteImplicitTimeout(Duration.ofSeconds(5));
        List<WebElement> elements = DriverManager.getDriver().findElements(by);
        overwriteImplicitTimeout(Duration.ofSeconds(0));
        return elements;
    }

    /**
     * Get all child elements into the list
     */
    public static List<WebElement> getElementListWithULHTML(Object object) {
        WebElement element = getWebElement(object);
        if (!verifyElementDisplayed(element)) {
            throw new NoSuchElementException(String.format(ELEMENT_NOT_FOUND, element));
        }
        List<WebElement> links = element.findElements(By.tagName("li"));
        for (WebElement link : links) {
            System.out.println(link.getText());
        }
        return links;
    }

    /**
     * Get a by object of elements
     *
     * @param locatorForm : The locator format
     * @param keyValues   : The key value for this format
     * @return : a by object of elements
     */
    public static By getByXpathDynamic(String locatorForm, String... keyValues) {
        return By.xpath(getStringXPathDynamic(locatorForm, (Object) keyValues));
    }


    /**
     * Receives a wildcard string, replace the wildcard with the value and return to the caller
     *
     * @param xpath Xpath with wildcard string
     * @param value multi value to be replaced in place of wildcard
     *              VD: ObjectUtils.getXpathDynamic("//button[normalize-space()='%s']//div[%d]//span[%d]", "Login", 2, 10);
     * @return dynamic xpath string
     */
    @SneakyThrows
    public static String getStringXPathDynamic(String xpath, Object... value) {
        if (Objects.isNull(xpath) || xpath.isEmpty()) {
            LogUtils.info("getXpathDynamic: Parameter passing error. The 'XPath' parameter is null.");
            throw new Exception("Warning !! The XPath is null.");
        } else {
            if (value.length == 0) return xpath;
            return String.format(xpath, value);
        }
    }

    /**
     * Get element or default
     */
    public static WebElement getFirstElementOrDefault(WebElement scope, By by) {
        var webElement = scope.findElements(by);
        if (Objects.nonNull(webElement) && !webElement.isEmpty()) return webElement.get(0);
        return null;
    }
    // endregion


    // region Base Action

    /**
     * Click the object
     */
    @Step("Click on the element {element}")
    public void clickElement(Object object, String... titles) {
        var element = getWebElement(object);
        waitForElementClickable(element);
        String locator = getLocatorFromWebElement(element);
        String value = getDomPropertyOfElement(element, ELEMENT_PROPERTY_TEXT_CONTENT);
        if (titles.length > 0) value = titles[0];
        System.out.println("Clicking on element: " + locator);
        element.click();
        System.out.println("Clicked: " + locator);
        String msg = String.format("Clicked [%s]  <br/> <span style='font-size: 0.75em'>(Element's locator:  %s)</span>", value, locator);
        addReportInfo(LogType.INFO, msg, "clickElement_", locator);
    }

    @Step("Insert text {value} to {title}     (Element's locator: : {element})")
    public WebElement inputTextTo(Object object, String title, String value, boolean... isClear) {
        // Handle logic for input text
        WebElement element = null;
        try {
            element = getWebElement(object);
            if (isSameValueOfElement(element, value)) return null;

            /*if (isClear.length == 0 || isClear[0]) {
                if ((Objects.nonNull(element.getText()) && !element.getText().isEmpty()) || !getDomPropertyOfElement(element, "value").isEmpty())
                    clearElementText(element);
                else getActions().click(element).perform();
            } else getActions().click(element).perform();*/
            element.sendKeys(value);
        } finally {
            String locator = getLocatorFromWebElement(element);
            String msg = String.format("Insert text [%s] to [%s] <br/> <span style='font-size: 0.75em'>(Element's locator:  %s)</span>", ReportConst.BOLD_START + value + ReportConst.BOLD_END, title, locator);
            addReportInfo(LogType.INFO, msg, "setText_" + value, locator);
        }
        return element;
    }

    @Step("Insert text {value} to {title}     (Element's locator: : {element})")
    public WebElement inputTextToWithSelectAllText(Object object, String title, String value) {
        WebElement element = null;
        try {
            element = getWebElement(object);
            if (isSameValueOfElement(element, value)) return null;

            getActions().click(element).perform();
            getActions().click(element).keyDown(element, Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).build().perform();
            element.sendKeys(value);
        } finally {
            String locator = getLocatorFromWebElement(element);
            String msg = String.format("Insert text [%s] to [%s] <br/> <span style='font-size: 0.75em'>(Element's locator:  %s)</span>", ReportConst.BOLD_START + value + ReportConst.BOLD_END, title, locator);
            addReportInfo(LogType.INFO, msg, "setText_" + value, locator);
        }
        return element;
    }

    @Step("Insert text {value} to {title}     (Element's locator: : {element})")
    public void inputTextByJSTo(WebElement element, String title, String value) {
        getJsExecutor().executeScript("arguments[0].value = '';", element);
        getJsExecutor().executeScript(String.format("arguments[0].innerText = '%s'", value), element);
        String locator = getLocatorFromWebElement(element);
        String msg = String.format("Insert text [%s] to [%s] <br/> <span style='font-size: 0.75em'>(Element's locator:  %s)</span>", ReportConst.BOLD_START + value + ReportConst.BOLD_END, title, locator);
        addReportInfo(LogType.INFO, msg, "setText_" + value, locator);
    }


    /**
     * Clear text of element (especial case)
     */
    public void clearElementText(Object object) {
        WebElement element = getWebElement(object);
        getActions().click(element).keyDown(element, Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build().perform();
        if (!Strings.isEmpty(element.getText()))
            getActions().keyDown(element, Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build().perform();
        waitFor(ProjectConfig.WAIT_SLEEP_STEP);
    }

    /**
     * Select item from checkbox
     */
    public void selectItemFromCheckBox(Object object, boolean isChecked, String... titles) {
        WebElement element = getWebElement(object);
        String msg = null;
        if (isChecked && !element.isSelected()) {
            try {
                getActions().click(element).perform();
            } catch (ElementNotInteractableException | MoveTargetOutOfBoundsException exception) {
                clickElementViaJs(element);
            }
            msg = "Checked";
        } else if (!isChecked && element.isSelected()) {
            try {
                getActions().click(element).perform();
            } catch (ElementNotInteractableException | MoveTargetOutOfBoundsException exception) {
                clickElementViaJs(element);
            }
            msg = "UnChecked";
        }

        String value = getDomPropertyOfElement(element, ELEMENT_PROPERTY_TEXT_CONTENT);
        if (titles.length > 0) value = titles[0];
        msg = String.format("%s to [%s]  <br/> <span style='font-size: 0.75em'>(Element's locator:  %s)</span>", msg, value, getLocatorFromWebElement(element));
        addReportInfo(LogType.INFO, msg, "clickElement_", getLocatorFromWebElement(element));
    }

    /**
     * Get text of an element
     */
    @Step("Get text element")
    public static String getTextElement(Object object) {
        WebElement webElement = getWebElement(object);
        String textValue = webElement.getText();
        return textValue.trim();
    }


    /**
     * Get the property of Element
     */
    public String getDomPropertyOfElement(Object object, String propertyName) {
        var webElement = getWebElement(object);
        try {
            return webElement.getDomProperty(propertyName);
        } catch (Exception e) {
            return Strings.EMPTY;
        }
    }

    /**
     * Get value of element with DOM
     */
    public String getValueOfElement(Object object) {
        var element = getWebElement(object);
        return getDomPropertyOfElement(element, ELEMENT_PROPERTY_VALUE);
    }

    /**
     * Get the property of Element
     */
    public String getAttributeOfElement(WebElement element, String attName) {
        return element.getAttribute(attName);
    }

    /**
     * scroll to element
     */
    public static void scrollToElement(WebElement element) {
        getJsExecutor().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * scroll to element
     */
    public static void scrollToElementWithBy(By by) {
        if (Objects.nonNull(by)) {
            scrollToElement(getWebElement(by));
        }
    }

    public void clickElementViaJs(Object object, String... titles) {
        WebElement element = getWebElement(object);
        getJsExecutor().executeScript("arguments[0].click()", element);

        String locator = getLocatorFromWebElement(element);
        String value = getDomPropertyOfElement(element, ELEMENT_PROPERTY_TEXT_CONTENT);
        if (titles.length > 0) value = titles[0];

        String msg = String.format("Clicked [%s]  <br/> <span style='font-size: 0.75em'>(Element's locator:  %s)</span>", value, locator);
        addReportInfo(LogType.INFO, msg, "clickElement_", locator);
    }

    /**
     * Upload file using sendKeys
     *
     * @param filePaths List of file Path
     */
    @Step("Upload a file to the system; File path: {filePath}")
    public static void uploadFileSendKeys(Object object, String... filePaths) {
        if (Objects.isNull(filePaths) || filePaths.length == 0) return;

        WebElement webElement = getWebElement(object);
        Arrays.stream(filePaths).forEach(webElement::sendKeys);
        addReportInfo(LogType.INFO, "Upload file ..", "Upload File", getLocatorFromWebElement(webElement));
    }

    /**
     * Hover to element with Action
     */
    public void hoverElement(Object object, boolean... isJavaScripts) {
        try {
            WebElement element = getWebElement(object);
            if (isJavaScripts.length == 0) getActions().moveToElement(element).perform();
            else hoverElementByJS(element);
        } catch (Exception e) {
            LogUtils.error("VException: hoverElement: " + e.getMessage());
        }
    }

    /**
     * Hover to element with JavaScript
     */
    public void hoverElementByJS(Object object) {
        try {
            WebElement element = getWebElement(object);
            String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', " + " true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            getJsExecutor().executeScript(mouseOverScript, element);
        } catch (Exception e) {
            LogUtils.error("VException: hoverElementByJS: " + e.getMessage());
        }
    }

    /**
     * Click the Enter Keyboard
     */
    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Press key {keyEvent} from the keyboard")
    public static boolean pressKeyEvent(int keyEvent) {
        try {
            Robot robot = new Robot();
            robot.keyPress(keyEvent);
            robot.keyRelease(keyEvent);
            addReportInfo(LogType.INFO, String.format("Press key %s from the keyboard", keyEvent), null, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendKeyF9(Object object) {
        WebElement element = getWebElement(object);
        clickElement(element);
        element.sendKeys(Keys.F9);
    }
    // endregion

    // region Assert Condition

    /**
     * Assert Element Objects.
     * Support 3 type of Failure Handling
     */
    public static void assertTrueCondition(Object object, boolean isResult, FailureHandling failureHandling, @Nullable String errMsg) {
        WebElement element = getWebElement(object);
        drawBorderForErrorElement(element, isResult);

        String locator = Strings.EMPTY;
        if (Objects.nonNull(element)) {
            locator = getLocatorFromWebElement(element);
        }

        if (Objects.isNull(errMsg) || errMsg.isEmpty()) {
            errMsg = String.format("Verify TRUE object: " + locator);
        }

        try {
            if (!isResult) {
                LogUtils.info(String.format("%s -> VERIFY : %s", errMsg, false));
            }
            switch (failureHandling) {
                case STOP_ON_FAILURE:
                    if (!isResult) {
                        ExtentReportManager.fail(String.format("%s -> VERIFY : %s", errMsg, ReportConst.FAIL));
                    }
                    Assert.assertTrue(isResult);
                    ExtentReportManager.pass(String.format("%s -> VERIFY : %s", errMsg, ReportConst.PASS));
                    break;
                case CONTINUE_ON_FAILURE:
                    if (!isResult) {
                        String softMsg = "SOFT ASSERT: Assert TRUE object: FAILED";

                        Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                        ExtentReportManager.fail(String.format("%s -> VERIFY : %s", errMsg, ReportConst.FAIL));
                        ExtentReportManager.addScreenShot(softMsg + " " + locator);
                    } else {
                        ExtentReportManager.pass(String.format("%s -> VERIFY : %s", errMsg, ReportConst.PASS));
                    }
                    break;
                default:
                    break;
            }
        } finally {
            if (SCREENSHOT_ALL_STEPS) {
                CaptureUtils.captureScreenshot(DriverManager.getDriver(), "image_" + isResult);
            }
            clearBorderForErrorElement(element, isResult);
        }
    }

    /**
     * Assert Fail
     */
    public static void assertFalseCondition(Object object, boolean isResult, FailureHandling failureHandling, String errMsg) {
        WebElement element = getWebElement(object);
        drawBorderForErrorElement(element, isResult);

        String locator = Strings.EMPTY;
        if (Objects.nonNull(element)) {
            locator = getLocatorFromWebElement(element);
        }

        String apiLog = "";
        if (Objects.isNull(errMsg) || errMsg.isEmpty()) {
            errMsg = String.format("Verify FALSE object: " + locator);
        } else if (errMsg.contains(SEPARATE_KEY)) {
            String[] errs = errMsg.split(SEPARATE_KEY);
            errMsg = errs[0];
            apiLog = errs[1];
        }
        try {
            if (isResult) {
                LogUtils.info(String.format("%s -> VERIFY : %s", errMsg, !isResult));
                ExtentReportManager.logMessage(errMsg);
            }
            switch (failureHandling) {
                case STOP_ON_FAILURE:
//                    if (isResult) {
//                        ExtentReportManager.fail(String.format("%s -> VERIFY : %s", errMsg, ReportConfig.FAIL));
//                        ExtentReportManager.addNode(apiLog, "API Response");
//                        ExtentReportManager.logMessage("API Log: " + apiLog);
//                    }
                    Assert.assertFalse(isResult);
                    ExtentReportManager.pass(String.format("%s -> VERIFY : %s", errMsg, ReportConst.PASS));
                    break;
                case CONTINUE_ON_FAILURE:
                    if (isResult) {
                        String softMsg = "SOFT ASSERT: Verify FALSE object: FAILED";

                        Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                        ExtentReportManager.fail(String.format("%s -> VERIFY : %s", errMsg, ReportConst.FAIL));
                        ExtentReportManager.addScreenShot(softMsg + " " + locator);
                    } else {
                        ExtentReportManager.pass(String.format("%s -> VERIFY : %s", errMsg, ReportConst.PASS));
                    }
                    break;
            }
        } finally {
            //  Attached the report info
            if (SCREENSHOT_ALL_STEPS) {
                CaptureUtils.captureScreenshot(DriverManager.getDriver(), "verifyElement_" + isResult);
            }
            clearBorderForErrorElement(element, isResult);
        }
    }

    /**
     * Assert Equal
     */
    public static void assertEqualCondition(Object object, Object actual, Object expected, FailureHandling failureHandling, String errMsg) {
        WebElement element = getWebElement(object);
        boolean isResult = Objects.equals(actual, expected);
        drawBorderForErrorElement(element, isResult);

        String locator = Strings.EMPTY;
        if (Objects.nonNull(element)) {
            locator = getLocatorFromWebElement(element);
        }

        if (Objects.isNull(errMsg) || errMsg.isEmpty()) {
            errMsg = String.format("Verify equal object " + locator);
        }

        errMsg = String.format("%s - Actual: %s ; Expected: %s", errMsg, actual.toString(), expected.toString());

        try {
            if (!isResult) {
                LogUtils.info(String.format("%s -> VERIFY : %s", errMsg, false));
            }

            switch (failureHandling) {
                case STOP_ON_FAILURE:
                    if (!isResult) {
                        ExtentReportManager.fail(String.format("%s -> VERIFY : %s", errMsg, ReportConst.FAIL));
                    }
                    Assert.assertEquals(actual, expected);
                    ExtentReportManager.pass(String.format("%s -> VERIFY : %s", errMsg, ReportConst.PASS));
                    break;
                case CONTINUE_ON_FAILURE:
                    if (!isResult) {
                        String softMsg = "SOFT ASSERT: Verify the result: FAILED";
                        Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                        ExtentReportManager.fail(String.format("%s -> VERIFY : %s", errMsg, ReportConst.FAIL));
                        ExtentReportManager.addScreenShot(softMsg + " " + locator);
                    } else {
                        ExtentReportManager.pass(String.format("%s -> VERIFY : %s", errMsg, ReportConst.PASS));
                    }
                    break;
            }
        } finally {
            //  Attached the report info
            if (SCREENSHOT_ALL_STEPS) {
                CaptureUtils.captureScreenshot(DriverManager.getDriver(), "verifyElement_" + isResult);
            }
            clearBorderForErrorElement(element, isResult);
        }
    }

    /**
     * Draw a border for the error element
     */
    private static void drawBorderForErrorElement(Object object, boolean isResult) {
        WebElement element = getWebElement(object);
        if (DRAW_BORDER_ERR_ELEMENT && !isResult && Objects.nonNull(element)) {
            // scrollToElement(element);
            try {
                scrollElementToViewCenter(element);
            } catch (Exception e) {
                LogUtils.error("VException: " + e.getMessage());
            }

            JavascriptExecutor js = getJsExecutor();
            js.executeScript("arguments[0].style.border='3px solid red'", element);
        }
    }


    /**
     * Draw a border for the error element
     */
    private static void drawBorderForErrorElement(By by, boolean isResult) {
        WebElement element = null;
        if (Objects.nonNull(by)) {
            element = getWebElement(by);
        }
        drawBorderForErrorElement(element, isResult);
    }


    /**
     * Clear a border for the error element
     */
    private static void clearBorderForErrorElement(Object object, boolean isResult) {
        WebElement element = getWebElement(object);
        if (DRAW_BORDER_ERR_ELEMENT && !isResult && Objects.nonNull(element)) {
            JavascriptExecutor js = getJsExecutor();
            js.executeScript("arguments[0].style.border='0px solid red'", element);
        }
    }

    /**
     * Clear a border for the error element
     */
    private static void clearBorderForErrorElement(By by, boolean isResult) {
        WebElement element = null;
        if (Objects.nonNull(by)) {
            element = getWebElement(by);
        }
        clearBorderForErrorElement(element, isResult);
    }

    // endregion

    public static WebElement waitForElementVisibleWithBy(By by) {
        try {
            WebElement obj = getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(by));
            if (Objects.nonNull(obj))
                LogUtils.info(String.format("waitForElementVisibleWithBy: Element %s : visible", by.toString()));
            return obj;
        } catch (Exception e) {
            var elementList = DriverManager.getDriver().findElements(by);
            LogUtils.error(String.format("waitForElementVisibleWithBy: Element %s : invisible %s", by.toString(), elementList.isEmpty() ? "" : " Had more element with this XPATH. Please re-check!"));
        }
        return null;
    }


    /**
     * Verify whether the element is visible or not
     */
    public static WebElement waitForElementVisible(Object object) {
        if (object instanceof By) return waitForElementVisibleWithBy((By) object);
        WebElement element = getWebElement(object);
        String locator = getLocatorFromWebElement(element);
        String msg = String.format("waitForElementVisible: Element %s : invisible", locator);
        try {
            element = getWaitDriver().until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            LogUtils.info(msg);
            element = null;
        }
        if (Objects.nonNull(element)) msg = String.format("waitForElementVisible: Element %s : visible", locator);
        LogUtils.info(msg);
        return element;
    }

    public static WebElement waitForElementClickableBy(By by) {
        WebElement element;
        String msg = String.format("waitForElementClickableBy: Element %s : un-clickable", by.toString());
        try {
            element = getWaitDriver().until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            LogUtils.info(msg);
            element = null;
        }
        if (Objects.nonNull(element))
            msg = String.format("waitForElementClickableBy: Element %s : clickable", by);
        LogUtils.info(msg);
        return element;
    }


    public static WebElement waitForElementClickable(Object object) {
        if (object instanceof By) return waitForElementClickableBy((By) object);
        WebElement element = getWebElement(object);
        String locator = getLocatorFromWebElement(element);
        String msg = String.format("waitForElementClickable: Element %s : un-clickable", locator);
        try {
            element = getWaitDriver().until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            LogUtils.info(msg);
            element = null;
        }
        if (Objects.nonNull(element)) msg = String.format("waitForElementClickable: Element %s : clickable", locator);
        LogUtils.info(msg);
        return element;
    }

    /**
     *
     */
    public WebElement waitForElementPresent(By by) {
        try {
            return getWaitDriver().until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.error("Element not exist. " + by.toString());
        }
        return null;
    }

    /**
     * wait for element invisible
     */
    public static void waitForElementInvisible(Object object, long... waitDuration) {
        if (object instanceof By)
            getWaitDriver(waitDuration).until(ExpectedConditions.invisibilityOfElementLocated((By) object));
        else if (object instanceof WebElement)
            getWaitDriver(waitDuration).until(ExpectedConditions.invisibilityOf((WebElement) object));
    }

    public static boolean verifyElementDisplayed(Object object, int... wait) {
        try {
            WebElement element;
            if (object instanceof By) {
                overwriteImplicitTimeout(Duration.ofSeconds(wait.length > 0 ? wait[0] : WAIT_IMPLICIT));
                element = DriverManager.getDriver().findElement((By) object);
                overwriteImplicitTimeout(Duration.ofSeconds(0));
            } else element = (WebElement) object;
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step
    public static void waitToVerifyElementVisible(Object object, boolean expDisplay, FailureHandling failureHandling) {
        if (object instanceof By) waitToVerifyElementVisibleWithBy((By) object, expDisplay, failureHandling);
        WebElement element = getWebElement(object);
        String locator = getLocatorFromWebElement(element);
        String msg;

        boolean isResult;
        if (expDisplay) {
            msg = String.format("Verify the object %s : visible", locator);
            isResult = Objects.nonNull(waitForElementVisible(element)) && element.isDisplayed();
        } else {
            msg = String.format("Verify the object %s : invisible", locator);
            isResult = Objects.isNull(waitForElementVisible(element)) || !element.isDisplayed();
        }
        assertTrueCondition(element, isResult, failureHandling, msg);
    }

    /**
     * Verify whether the element is visible or not
     */
    private static void waitToVerifyElementVisibleWithBy(By by, boolean expDisplay, FailureHandling failureHandling) {
        String msg;
        WebElement element;
        boolean isResult;
        if (expDisplay) {
            msg = String.format("Verify the object %s : visible", by);
            element = waitForElementVisibleWithBy(by);
            isResult = Objects.nonNull(element) && element.isDisplayed();
        } else {
            msg = String.format("Verify the object %s : invisible", by);
            element = waitForElementVisibleWithBy(by);
            isResult = Objects.isNull(element) || !element.isDisplayed();
        }
        assertTrueCondition(element, isResult, failureHandling, msg);
    }

    // endregion

    // region Alert
    public static void alertAccept() {
        DriverManager.getDriver().switchTo().alert().accept();
    }

    public static void alertDismiss() {
        DriverManager.getDriver().switchTo().alert().dismiss();
    }

    public static void alertGetText() {
        DriverManager.getDriver().switchTo().alert().getText();
    }

    public static void alertSetText(String text) {
        DriverManager.getDriver().switchTo().alert().sendKeys(text);
    }

    public static boolean verifyAlertPresent() {
        try {
            getWaitDriver().until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Throwable error) {
            Assert.fail("Not found Alert.");
            return false;
        }
    }

    // endregion

    // region Utils

    public static void scrollElementToViewCenter(Object elementObj) {
        //Log.info("Vincent  =: scrollElementToViewCenter ");
        WebElement element = getWebElement(elementObj);
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);" + "var elementTop = arguments[0].getBoundingClientRect().top;" + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(scrollElementIntoMiddle, element);
    }

    public boolean isSameValueOfElement(WebElement element, String expValue) {
        return expValue.equals(element.getText()) || expValue.equals(getValueOfElement(element));
    }

    public static String getLanguageValue(String key) {
        return AbsPropertyUtils.getLanguageValue(key);
    }

    /**
     * Force wait
     */
    public static void waitFor(double second) {
        try {
            sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            LogUtils.error("VException: " + e.getMessage());
        }
    }

    public static void sleepMillisecond(double millisecond) {
        try {
            sleep((long) (millisecond));
        } catch (InterruptedException e) {
            LogUtils.error("VException: " + e.getMessage());
        }
    }

    public static void overwriteImplicitTimeout(Duration duration) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(duration);
    }

    /**
     * Add more information for Report: Including Extent and Allure.
     * You can add more report at this function.
     */
    public static void addReportInfo(LogType logType, String extMsg, String capText, String locator) {
        // Add for Extent Report
        if (ExtentTestManager.getExtentTest() != null) {
            if (logType.equals(LogType.INFO)) ExtentReportManager.info(extMsg);
            else ExtentReportManager.pass(extMsg);
        }
    }

    /**
     * Get the element locator from WebElement
     */
    public static String getLocatorFromWebElement(WebElement element) {
        var list = element.toString().split("->");
        if (list.length > 1)
            return element.toString().split("->")[1].replaceFirst("xpath:(?s)(.*)]", "$1").trim();
        else return element.toString();
    }
    // endregion

    // region Haven't Use
    public static boolean verifyElementListExist(List<WebElement> elements) {
        return Objects.nonNull(elements) && !elements.isEmpty();
    }

    /**
     * Upload files using EventKey
     */
    @Step("Upload a file to the system; File path: {filePath}")
    public static void uploadFileForm(Object object, String filePath) {
        WebElement webElement = getWebElement(object);
        //Click để mở form upload
        getActions().moveToElement(webElement).click().perform();
        waitFor(WAIT_IMPLICIT);

        // Khởi tạo Robot class
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            LogUtils.error("VException: " + e.getMessage());
        }

        // Copy File path vào Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        // Nhấn Control+V để dán
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // Xác nhận Control V
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        waitFor(WAIT_IMPLICIT);
        // Nhấn Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        addReportInfo(LogType.INFO, "Upload file .." + filePath, "Upload File", getLocatorFromWebElement(webElement));
    }

    public boolean verifyPageTitle(String expectedTitle) {
        return getPageTitle().equals(expectedTitle);
    }

    public static String getPageTitle() {
        String title = DriverManager.getDriver().getTitle();
        LogUtils.info("getPageTitle: Page Title: " + title);
        return title;
    }

    public static boolean verifyElementTextEqual(By by, String expectedValue) {
        var obj = waitForElementVisibleWithBy(by);
        return getTextElement(obj).trim().equals(expectedValue.trim());
    }

    public static void verifyElementTextEqual(WebElement webElement, String expectedValue) {
        String elementText = getTextElement(webElement);
        assertEqualCondition(webElement, elementText.trim(), expectedValue.trim(),
                FailureHandling.CONTINUE_ON_FAILURE, "Verify the text of element");
    }

}
