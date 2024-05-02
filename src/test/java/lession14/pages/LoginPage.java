package lession14.pages;

import lession14.common.BasePage;
import lession14.common.LogType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Objects;
import java.util.logging.Logger;

public class LoginPage extends BasePage {
    Logger logger = Logger.getLogger(LoginPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    /**
     * Constructor for Login
     */
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        this.mWebDriver = webDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Goto the website
     *
     * @param url : The website link
     */
    public void gotoWebsite(String url) {
        //String url = "https://rise.fairsketch.com/signin";
        logger.info(MessageFormat.format("Go to the website {0}", url));
        mWebDriver.get(url);
        addReportInfo(LogType.INFO,MessageFormat.format("Go to the website {0}", url));
    }

    /**
     * Login the website
     *
     * @param username : The username
     * @param password : The password
     * @return @HomePage
     */
    public HomePage login(String username, String password) {
        String LOGIN_FORM = "//input[@placeholder='%s']";

        // Wait for login shown
        WebElement loginEle = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LOGIN_FORM, "Email"))));
        Assert.assertTrue(Objects.nonNull(loginEle), "Verify that accessing the website");
        inputTextTo(loginEle, username);

        // Input password
        WebElement passwordEle = mWebDriver.findElement(By.xpath(String.format(LOGIN_FORM, "Password")));
        inputTextTo(passwordEle, password);

        // Click login button
        WebElement loginButton = mWebDriver.findElement(By.xpath("//button[@type='submit']"));
        clickElement(loginButton);

        // Wait for user icon shown
        mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='user-dropdown']//img")));

        // TODO: Verify the title of the page
        return new HomePage(mWebDriver);
    }
}
