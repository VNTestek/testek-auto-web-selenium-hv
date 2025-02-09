package com.testek.projects.testscript;

import com.testek.annotations.FrameAnnotation;
import com.testek.consts.AuthorType;
import com.testek.consts.FrameConst;
import com.testek.consts.FrameConst.CategoryType;
import com.testek.driver.BrowserFactory;
import com.testek.driver.DriverManager;
import com.testek.projects.common.TestBase;
import com.testek.projects.dataprovider.testek.LoginProvider;
import com.testek.projects.model.LoginModel;
import com.testek.projects.page.PageManagement;
import com.testek.projects.page.pages.HomePage;
import com.testek.projects.page.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginTest extends TestBase {

    @BeforeMethod
    public void beforeMethod() {
        if (DriverManager.getDriver() == null){
            BrowserFactory.initWebDriver(FrameConst.ProjectConfig.BROWSER);
        }
    }


    @AfterMethod
    public void afterMethod() {
        DriverManager.quitDriver();
    }


    @FrameAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Vincent}, reviewer = {AuthorType.Vincent})
    @Test(description = "Verify the login function", dataProvider = "Testek_Login_001_Valid", dataProviderClass = LoginProvider.class)
    public void Testek_Login_001_Valid(LoginModel data) throws InterruptedException {
        LoginPage loginPage = PageManagement.accessWebPage();
        HomePage homePage = loginPage.verifyLoginPageDisplay()
                .enterUserName(data.getUserName().getValue())
                .enterPassword(data.getPassword().getValue())
                .clickLoginButton();

        // Verify the login successfully
        homePage.verifyHomePage();
    }
}
