package lession11.improvement.testscript;

import lession11.improvement.common.DriverManager;
import lession11.improvement.common.TestBase;
import lession11.pages.ClientPage;
import lession11.pages.HomePage;
import lession11.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClientTest extends TestBase {
    private WebDriver mWebDriver;
    private String baseURL = "https://rise.fairsketch.com";
    private LoginPage loginPage;
    private HomePage homePage;
    private ClientPage clientPage;

    @BeforeClass
    public void beforeTestMethod() {
        mWebDriver = DriverManager.getWebDriver();
        loginPage = new LoginPage(mWebDriver);
        loginPage.gotoWebsite(baseURL);
    }

    @AfterClass
    public void afterMethod() {
       DriverManager.quit();
    }

    @Test(description = "Verify the login function")
    public void RISE_Client_001_VerifyDashboard() {
        // Login website với tài khoản hợp lệ -> Thành công truy cập vào HomePage
        homePage = loginPage.login("admin@demo.com", "riseDemo");

        // Click Clients] -> Thành công truy cập vào ClientPage
        clientPage = homePage.gotoClientsPage();

        // Thực hiện các hành động trong Client Page
        clientPage.verifyClientDashboard();
    }
}
