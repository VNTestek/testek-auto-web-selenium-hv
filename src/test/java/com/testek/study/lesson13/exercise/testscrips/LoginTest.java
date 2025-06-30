package com.testek.study.lesson13.exercise.testscrips;

import com.testek.driver.BrowserFactory;
import com.testek.driver.DriverManager;
import com.testek.study.lesson13.TestBase;
import com.testek.study.lesson13.exercise.PageManagement;
import com.testek.study.lesson13.exercise.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.testek.consts.FrameConst.ExecuteConfig.EXE_BROWSER;

public class LoginTest extends TestBase {

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
    public void AnhNN_Access_Login_Page() {
        // Access to the login page
        PageManagement.accessWebPage();
    }

    @Test
    public void AnhNN_Login_Function_Valid() {
        LoginPage loginPage = PageManagement.accessWebPage();
        loginPage.login("admin_com_role","aA12345678@");
    }

    @Test
    public void AnhNN_Access_Products_Page() {
        AnhNN_Login_Function_Valid();

    }


}
