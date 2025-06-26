package com.testek.study.lesson11;

import com.testek.study.common.Menu;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

import static java.lang.Thread.sleep;

@Getter
@Setter
@Slf4j
public class SolutionTest extends TestBase {

    /**
     * URL: <a href="https://testek.vn/lab/auto/web-elements">...</a>
     * Access to @link{Menu#FORM} navigation
     */
    @Test(description = "Element Interaction")
    public void testStudentForm() {
        gotoTestWebsite(Menu.ELEMENTS, Menu.TEXT_BOX);

        String XPATH_TEST_ID_FORMAT = "//input[@test-id='%s']";

        String fullName = "Vincent";
        String email = "vincent@testek.edu.vn";
        String phoneNumber = "0832868822";
        String address = "Yen Hoa, Cau Giay, Ha Noi";

        sendKeysToElement(By.xpath(String.format(XPATH_TEST_ID_FORMAT, "textbox-form-input-name")), fullName, true);
        sendKeysToElement(By.xpath(String.format(XPATH_TEST_ID_FORMAT, "textbox-form-input-email")), email, true);
        sendKeysToElement(By.xpath(String.format(XPATH_TEST_ID_FORMAT, "textbox-form-input-phone")), phoneNumber, true);
        sendKeysToElement(By.xpath(String.format(XPATH_TEST_ID_FORMAT, "textbox-form-input-address")), address, true);

        // Click to Submit button
        clickElement(By.xpath("//button[@test-id='textbox-form-submit']"), "Submit");
        waitForDebug(5000);

        // Verify the result
        WebElement txtResultEle = waitForElement(By.id("outputText"));
        Assert.assertNotNull(txtResultEle, "Result element not found");

        String resultText = txtResultEle.getAttribute("value");
        String[] resultLines = resultText.split("\n");

        Assert.assertEquals(resultLines.length, 4, "Result lines not matched");
        Assert.assertEquals(resultLines[0].trim(), "Họ và tên: " + fullName, "Name not matched");
        Assert.assertEquals(resultLines[1].trim(), "Email: " + email, "Email not matched");
        Assert.assertEquals(resultLines[2].trim(), "Số điện thoại: " + phoneNumber, "Phone number not matched");
        Assert.assertEquals(resultLines[3].trim(), "Địa chỉ: " + address, "Address not matched");

        assertObject(resultLines[0].trim(), "Họ và tên: " + fullName, "Name not matched");
        assertObject(resultLines[1].trim(), "Email: " + email, "Email not matched");
        assertObject(resultLines[2].trim(), "Số điện thoại: " + phoneNumber, "Phone number not matched");
        assertObject(resultLines[3].trim(), "Địa chỉ: " + address, "Address not matched");
    }

    /**
     * URL: <a href="https://testek.vn/lab/auto/web-elements">...</a>
     * Access to @link{Menu#FORM} navigation
     */
    @Test(description = "Element Interaction")
    public void testStudentFormVerifyDisplay() {
        gotoTestWebsite(Menu.ELEMENTS, Menu.TEXT_BOX);

        String XPATH_TEST_ID_FORMAT = "//input[@test-id='%s']";

        String fullName = "Vincent";

        WebElement edtFullNameEle = waitForElement(By.xpath(String.format(XPATH_TEST_ID_FORMAT, "textbox-form-input-name")));
        Assert.assertTrue(edtFullNameEle.isDisplayed(), "Full Name element not displayed");

        edtFullNameEle.sendKeys(fullName);
        String actFullName = edtFullNameEle.getAttribute("value");
        log.info("Full Name: {}", actFullName);
        Assert.assertEquals(actFullName, fullName, "Full Name not matched");
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
     * Click to element
     */
    private void clickElement(By by, String label) {
        WebElement element = waitForElementClickable(by);
        if (element == null) {
            log.error("Element not found: {}", by);
            return;
        }
        element.click();
        log.info("Clicked to: {}", label);
    }


    /**
     * Verify the element
     */
    private void assertObject(Object actObject, Object expObject, String message) {
        log.info("Verify object: actual {} - expected {} => Result {} ", actObject, expObject, Objects.equals(actObject, expObject));
        Assert.assertEquals(actObject, expObject, message);
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
