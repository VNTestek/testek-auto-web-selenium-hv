package com.testek.study.lesson08;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class JavaScripts {
    private WebDriver mWebDriver;
    private String baseURL = "https://demoqa.com/automation-practice-form";

    @BeforeMethod
    public void beforeTestMethod() {
        // Step 01: Setup file thực thi chrome driver cho system
        // Cách 01: Cài đặt driver cho chrome thông qua file thực thi
        //String chromeDriverPath = "src/test/resources/driver/chromedriver";
        //System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // Cách 02: Cài đặt tự đông
        // WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        // Lưu ý: Cần thêm thư viện webdrivermanager tại pom

        // Step 2: Khởi tạo Chrome Options : Chứa các tùy chỉnh cho Chrome
        ChromeOptions chromeOptions = new ChromeOptions();
        // Cài đặt Chrome mở full screen
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        // Step 3: Khởi tạo WebDriver -> Tương tác với các phần tử website
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
        mWebDriver.get(baseURL);
        // Init Java Script
        JavascriptExecutor javaScripts = (JavascriptExecutor) mWebDriver;

        // Get title of web page
        Object result = javaScripts.executeScript("return document.title;");
        System.out.println("Title: " + result.toString());
    }

    @Test
    public void testJavaScriptExample() {
        String url = "https://demoqa.com/forms";
        mWebDriver.get(url);

        // Init Java Script
        JavascriptExecutor javaScripts = (JavascriptExecutor) mWebDriver;

        // Click to Practice Form
        WebElement practiceFormEle = mWebDriver.findElement(By.xpath("//span[text()='Practice Form']"));
        javaScripts.executeScript("arguments[0].click();", practiceFormEle);

        // Draw border for element
        javaScripts.executeScript("arguments[0].style.border='3px solid red'", practiceFormEle);

        // Send Key
        WebElement firstNameEle = mWebDriver.findElement(By.xpath("//input[@placeholder='First Name']"));
        javaScripts.executeScript("arguments[0].value = 'Vincent';", firstNameEle);
        String text = (String) javaScripts.executeScript("return arguments[0].value;", firstNameEle);
        System.out.println("Text: " + text);

        // Show Alert
        //javaScripts.executeScript("alert('Vincent - You are an automation engineer ?');");

        File file = new File(System.getProperty("user.dir") + "/Images");
        if (!file.exists()) file.mkdirs();
        takeSnapShot(mWebDriver, file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".png");
    }

    @Test(description = "Thuc hanh 01: JavaScript")
    public void testPracticeJavaScript(){
        baseURL="https://demoqa.com/text-box";
        mWebDriver.get(baseURL);

        // Init Java Script
        JavascriptExecutor javaScripts = (JavascriptExecutor) mWebDriver;

        String DYNAMIC_INPUT_PLACEHOLDER_FORM = "//input[@placeholder='%s']";
        // Send Key Full Name
        String fullNameXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "Full Name");
        WebElement fullNameEle = mWebDriver.findElement(By.xpath(fullNameXPath));
        javaScripts.executeScript("arguments[0].value = 'Vincent';", fullNameEle);
        String text = (String) javaScripts.executeScript("return arguments[0].value;", fullNameEle);
        System.out.println("Full Name: " + text);


        // Send Key Full Name
        String emailXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "name@example.com");
        WebElement emailEle = mWebDriver.findElement(By.xpath(emailXPath));
        javaScripts.executeScript("arguments[0].value = 'vincent@gmail.com';", emailEle);
        String email = (String) javaScripts.executeScript("return arguments[0].value;", emailEle);
        System.out.println("Email : " + email);


        String DYNAMIC_TEXTAREA_ID_FORM = "//textarea[@id='%s']";
        // Input current address
        String currentXPath = String.format(DYNAMIC_TEXTAREA_ID_FORM, "currentAddress");
        WebElement currentEle = mWebDriver.findElement(By.xpath(currentXPath));
        javaScripts.executeScript("arguments[0].value = 'KĐT Nam Trung Yên, P. Yên Hòa, Q. Cầu Giấy, TP Hà Nội';", currentEle);
        String currentAdd = (String) javaScripts.executeScript("return arguments[0].value;", currentEle);
        System.out.println("Current Address : " + currentAdd);

        // Input Permanent address
        String permanentXPath = String.format(DYNAMIC_TEXTAREA_ID_FORM, "permanentAddress");
        WebElement permanentEle = mWebDriver.findElement(By.xpath(permanentXPath));
        javaScripts.executeScript("arguments[0].value = 'Q. Cầu Giấy, TP Hà Nội';", permanentEle);
        String permanent = (String) javaScripts.executeScript("return arguments[0].value;", permanentEle);
        System.out.println("Permanent Address : " + permanent);

        // Click to Submit
        String buttonXPath = "//button[@id='submit']";
        WebElement submitEle = mWebDriver.findElement(By.xpath(buttonXPath));
        javaScripts.executeScript("arguments[0].click()", submitEle);
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
}
