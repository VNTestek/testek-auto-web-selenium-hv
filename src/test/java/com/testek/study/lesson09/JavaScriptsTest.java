package com.testek.study.lesson09;

import com.testek.study.common.Menu;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

@Slf4j
public class JavaScriptsTest {
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

    @Test
    public void initJavaScript() {
        gotoTestWebsite(Menu.ELEMENTS, Menu.TEXT_BOX);
        // Init Java Script
        JavascriptExecutor javaScripts = (JavascriptExecutor) mWebDriver;

        // Get title of web page
        Object result = javaScripts.executeScript("return document.title;");
        log.info("Title: {}", result.toString());
    }

    @Test
    public void testJavaScriptExample() {
        gotoTestWebsite(Menu.ELEMENTS, Menu.TEXT_BOX);

        // Init Java Script
        JavascriptExecutor javaScripts = (JavascriptExecutor) mWebDriver;

        // Click to Practice Form
        WebElement lblTextBoxEle = mWebDriver.findElement(By.xpath("//div[@test-id='submenu-textbox']"));
        javaScripts.executeScript("arguments[0].click();", lblTextBoxEle);

        // Draw border for element
        javaScripts.executeScript("arguments[0].style.border='3px solid red'", lblTextBoxEle);
        waitForDebug(5000);

        // Show Alert
        //javaScripts.executeScript("alert('Vincent - You are an automation engineer ?');");

        File file = new File(System.getProperty("user.dir") + "/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        takeSnapShot(mWebDriver, file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".png");
    }

    @Test(description = "Thuc hanh 01: JavaScript")
    public void testPracticeJavaScript() {
        gotoTestWebsite(Menu.ELEMENTS, Menu.TEXT_BOX);

        // Init Java Script
        JavascriptExecutor javaScripts = (JavascriptExecutor) mWebDriver;

        String DYNAMIC_INPUT_PLACEHOLDER_FORM = "//input[@placeholder='%s']";
        // Send Key Full Name
        String fullNameXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "Nhập họ tên");
        WebElement fullNameEle = mWebDriver.findElement(By.xpath(fullNameXPath));
        javaScripts.executeScript("arguments[0].value = 'Vincent';", fullNameEle);
        String text = (String) javaScripts.executeScript("return arguments[0].value;", fullNameEle);
        log.info("Full Name: {}", text);


        // Send Key Full Name
        String emailXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "Nhập email");
        WebElement emailEle = mWebDriver.findElement(By.xpath(emailXPath));
        javaScripts.executeScript("arguments[0].value = 'vincent@gmail.com';", emailEle);
        String email = (String) javaScripts.executeScript("return arguments[0].value;", emailEle);
        log.info("Email : {}", email);


        // Input phone number
        String edtPhoneXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "Nhập số điện thoại");
        WebElement edtPhoneEle = mWebDriver.findElement(By.xpath(edtPhoneXPath));
        javaScripts.executeScript("arguments[0].value = '0832868822';", edtPhoneEle);
        String currentAdd = (String) javaScripts.executeScript("return arguments[0].value;", edtPhoneEle);
        log.info("Phone Number : {}", currentAdd);

        // Input address
        String edtAddressXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "Nhập địa chỉ");
        WebElement edtAddressEle = mWebDriver.findElement(By.xpath(edtAddressXPath));
        javaScripts.executeScript("arguments[0].value = 'Q. Cầu Giấy, TP Hà Nội';", edtAddressEle);
        String permanent = (String) javaScripts.executeScript("return arguments[0].value;", edtAddressEle);
        log.info("Address : {}", permanent);

        // Click to Submit
        String btnSubmitXPath = "//button[@test-id='textbox-form-submit']";
        WebElement btnSubmitEle = mWebDriver.findElement(By.xpath(btnSubmitXPath));
        javaScripts.executeScript("arguments[0].click()", btnSubmitEle);
        waitForDebug(5000);
    }

    /**
     * This function will take screenshot
     */
    public void takeSnapShot(WebDriver webdriver, String fileWithPath) {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);

        File destFile = new File(fileWithPath);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
