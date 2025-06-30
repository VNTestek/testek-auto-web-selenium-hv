package com.testek.study.lesson13.exercise.testscrips;

import com.testek.driver.BrowserFactory;
import com.testek.driver.DriverManager;
import com.testek.study.lesson13.TestBase;
import com.testek.study.lesson13.exercise.PageManagement;
import com.testek.study.lesson13.exercise.pages.HomePage;
import com.testek.study.lesson13.exercise.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.testek.consts.FrameConst.ExecuteConfig.EXE_BROWSER;

public class CreateOrderPageTest extends TestBase {

    @BeforeMethod
    public void beforeMethod() {
        if (DriverManager.getDriver() == null) {
            BrowserFactory.initWebDriver(EXE_BROWSER);
        }
    }

    @AfterMethod
    public void afterMethod() {
        DriverManager.quitDriver();
    }

    @Test
    public void AnhNN_Access_Create_Order_Page() {
        // Access to the login page
        LoginPage loginPage = PageManagement.accessWebPage();
        HomePage homePage = loginPage.login("admin_com_role","aA12345678@");
        homePage.verifyHomePageLoaded();
        homePage.clickToCreateOrderButton();
    }

}
