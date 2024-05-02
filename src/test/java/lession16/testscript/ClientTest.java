package lession16.testscript;


import lession16.common.DriverManager;
import lession16.common.TestBase;
import lession16.pages.ClientPage;
import lession16.pages.HomePage;
import lession16.pages.LoginPage;
import lession16.provider.ClientProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Locale;

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
    }


    private void gotoWebsite() {
        loginPage.gotoWebsite(baseURL);
    }
}
