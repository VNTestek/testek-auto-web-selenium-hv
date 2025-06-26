package com.testek.projects.testscript;

import com.testek.annotations.FrameAnnotation;
import com.testek.consts.AuthorType;
import com.testek.consts.FrameConst.CategoryType;
import com.testek.driver.BrowserFactory;
import com.testek.driver.DriverManager;
import com.testek.projects.common.TestBase;
import com.testek.projects.dataprovider.model.LoginModel;
import com.testek.projects.dataprovider.providers.LoginProvider;
import com.testek.projects.pages.PageManagement;
import com.testek.projects.pages.pages.HomePage;
import com.testek.projects.pages.pages.LoginPage;
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

    @FrameAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Vincent}, reviewer = {AuthorType.Vincent})
    @Test(description = "Verify the login function", dataProvider = "Testek_Login_001_Valid", dataProviderClass = LoginProvider.class)
    public void Testek_Login_001_Valid(LoginModel data) {
        // Access to the login page
        LoginPage loginPage = PageManagement.accessWebPage();

        // Verify the login page is displayed
        loginPage.verifyLoginPageDisplay();

        // Perform login with valid credentials
        HomePage homePage = loginPage.login(data);

        // Verify the home page is displayed after login
        homePage.verifyHomePage();
    }
}
