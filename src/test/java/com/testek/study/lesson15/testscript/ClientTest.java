package com.testek.study.lesson15.testscript;


import com.testek.study.lesson15.pages.HomePage;
import com.testek.study.lesson15.provider.ClientProvider;
import com.testek.study.lesson15.common.DriverManager;
import com.testek.study.lesson15.common.TestBase;
import com.testek.study.lesson15.pages.ClientPage;
import com.testek.study.lesson15.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ClientTest extends TestBase {
    private WebDriver mWebDriver;
    private String baseURL = "https://rise.fairsketch.com";
    private LoginPage loginPage;
    private HomePage homePage;
    private ClientPage clientPage;

    @BeforeClass
    public void beforeClass() {
        mWebDriver = DriverManager.getWebDriver();
        loginPage = new LoginPage(mWebDriver);

    }

    @AfterClass
    public void afterClass() {
        DriverManager.quit();
    }

    @Test(description = "Verify the login function")
    public void RISE_Client_001_VerifyDashboard() {
        gotoWebsite();
        // Login website với tài khoản hợp lệ -> Thành công truy cập vào HomePage
        homePage = loginPage.login("admin@demo.com", "riseDemo");

        // Click Clients] -> Thành công truy cập vào ClientPage
        clientPage = homePage.gotoClientsPage();

        // Thực hiện các hành động trong Client Page
        clientPage.verifyClientDashboard();
    }

    @Test(description = "Add Client with type Organization",
            dataProvider = "RISE_Client_Organization_Data",
            dataProviderClass = ClientProvider.class
    )
    public void RISE_Client_001_AddNewClient(HashMap<String, String> data) {
        gotoWebsite();
        // Login website với tài khoản hợp lệ -> Thành công truy cập vào HomePage
        homePage = loginPage.login("admin@demo.com", "riseDemo");

        // Click Clients] -> Thành công truy cập vào ClientPage
        clientPage = homePage.gotoClientsPage();

        // Create a mew client
        clientPage.createNewClient(data);


        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private void gotoWebsite() {
        loginPage.gotoWebsite(baseURL);
    }
}
