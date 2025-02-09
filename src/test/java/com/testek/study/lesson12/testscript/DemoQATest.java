package com.testek.study.lesson12.testscript;

import com.testek.study.lesson12.common.DriverManager;
import com.testek.study.lesson12.common.TestBase;
import com.testek.study.lesson12.pages.DemoQATextBoxPage;
import com.testek.study.lesson12.provider.StudentRegistrationProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashMap;

public class DemoQATest extends TestBase {
    WebDriver mWebDriver;
    WebDriverWait webDriverWait;
    DemoQATextBoxPage demoQATextBoxPage;

    @BeforeClass
    public void beforeClass() {
        mWebDriver = DriverManager.getWebDriver();
        webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        demoQATextBoxPage = new DemoQATextBoxPage(mWebDriver);
    }

    @AfterClass
    public void afterClass() {
        DriverManager.quit();
    }

    @Test(description = "Verify the login function", dataProvider = "DemoQA_Registration_Data", dataProviderClass = StudentRegistrationProvider.class)
    public void DemoQA_Registration_001_DataDriven(HashMap<String, String> data) {
        String url = "https://demoqa.com";
        mWebDriver.get(url);

        // Wait for the logo shown
        String logoXPath = "//img[@src='/images/Toolsqa.jpg']";
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(logoXPath)));

        // Click [Elements]. Lưu ý cần kiểm tra xem đối tượng đã ready để click chưa
        String elementXPath = "//h5[text()='Elements']";
        WebElement elementEle = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXPath)));
        elementEle.click();

        // Click [Text Box]. Lưu ý cần kiểm tra xem đối tượng đã ready để click chưa
        String textBoxXPath = "//span[text()='Text Box']";
        WebElement textBoxEle = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(textBoxXPath)));
        textBoxEle.click();

        demoQATextBoxPage.inputStudentInfo(data);
    }
}
