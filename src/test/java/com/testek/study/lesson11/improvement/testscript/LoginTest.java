package com.testek.study.lesson11.improvement.testscript;

import com.testek.study.lesson11.pages.LoginPage;
import com.testek.study.lesson11.improvement.common.DriverManager;
import com.testek.study.lesson11.improvement.common.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    private WebDriver mWebDriver;
    private String baseURL = "https://rise.fairsketch.com";
    private LoginPage loginPage;

    @BeforeMethod
    public void beforeTestMethod() {
        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        mWebDriver= new ChromeDriver(configChromeOption());
        DriverManager.setWebDriver(mWebDriver);

        loginPage = new LoginPage(DriverManager.getWebDriver());
        loginPage.gotoWebsite(baseURL);
    }

    @AfterMethod
    public void afterMethod() {
        DriverManager.quit();
    }

    @Test(description = "Verify the login function")
    public void RISE_Login_001_Correct() {
        loginPage.gotoWebsite(baseURL);
        loginPage.login("admin@demo.com", "riseDemo");
    }

}
