package lesson11.demo.pages;

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
import java.util.Objects;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginPage {
    WebDriver webDriver;
    WebDriverWait mWebDriverWait;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        mWebDriverWait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
    }

    //region Locator
    /* User Name */ String txtUserNameXPath = "";
    /* Password */ String txtPasswordXPath = "";

    /* Login Button */ String btnLoginXPath = "";

    //endregion


    //region Element
    WebElement txtUserNameEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(txtUserNameXPath)));
    WebElement txtPasswordEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(txtPasswordXPath)));
    WebElement btnLoginEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(btnLoginXPath)));

    //endregion


    //region Element's Action
    public void gotoWeb() {
        String baseURL = "https://rise.fairsketch.com";
        webDriver.get(baseURL);

        verifyLoginPageDisplay();
    }

    /**
     * Input text to the user name field
     *
     * @param value : The text value which you want
     */
    public void inputTextToUserName(String value) {
        inputTextTo(txtUserNameEle, value);
    }

    /**
     * @param value
     */
    public void inputTextToPassword(String value) {
        inputTextTo(txtPasswordEle, value);
    }

    /**
     *
     */
    public DashboardPage clickToLogin() {
        btnLoginEle.click();
        // Sau khi click login -> Dashboard Page
        return new DashboardPage(webDriver);
    }

    /**
     * @param element
     * @param value
     */
    private void inputTextTo(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
        System.out.printf("Input text %s to %s%n", value, element);
    }

    public DashboardPage login(String userName, String password) {
        inputTextToUserName(userName);
        inputTextToPassword(password);

        clickToLogin();
        return new DashboardPage(webDriver);
    }
    //endregion


    //region Verify

    public void verifyLoginPageDisplay() {
        Assert.assertTrue(txtUserNameEle.isDisplayed(), "Verify display");
        Assert.assertTrue(Objects.nonNull(txtPasswordEle), "Verify display");
    }
    //endregion
}
