package com.testek.study.lesson13.demo.pages;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DashboardPage {
    WebDriver webDriver;
    WebDriverWait mWebDriverWait;

    public DashboardPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        mWebDriverWait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
    }

    //region Locator
    String imgAvatarXPath = "";
    String navClientXPath = "";
    //endregion

    //region Element
    WebElement imgAvatarEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(imgAvatarXPath)));

    WebElement navClientEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(navClientXPath)));
    //endregion


    //region Element's Action
    public void clickToClients() {
        navClientEle.click();
        // Sau khi click Client -> Chuyeen huwong toi Client Page
    }
    //endregion


    //region Verify

    public void verifyAvatar() {
        Assert.assertTrue(imgAvatarEle.isDisplayed(), "Verify avatar");
    }
    //endregion
}
