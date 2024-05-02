package lession11.pages;

import lession12.pages.ClientPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.logging.Logger;

public class ClientContactPage {
    Logger logger = Logger.getLogger(ClientPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    public ClientContactPage(WebDriver mWebDriver) {
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    public void verifyContactStatistic() {
        // Verify total record / page
        String totalRecordID = "contact-table_length";
        WebElement totalRecordEle = mWebDriver.findElement(By.id(totalRecordID));
        Assert.assertTrue(totalRecordEle.isDisplayed(),"The total item displayed");
    }
}
