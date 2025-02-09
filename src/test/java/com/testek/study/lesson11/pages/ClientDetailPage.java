package com.testek.study.lesson11.pages;

import com.testek.study.lesson12.pages.ClientPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.logging.Logger;

public class ClientDetailPage {
    Logger logger = Logger.getLogger(ClientPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    public ClientDetailPage(WebDriver mWebDriver) {
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    public void verifyClientStatistic() {
        // Verify total record / page
        String totalRecordID = "client-table_length";
        WebElement totalRecordEle = mWebDriver.findElement(By.id(totalRecordID));
        Assert.assertTrue(totalRecordEle.isDisplayed(),"The total item displayed");
    }
}
