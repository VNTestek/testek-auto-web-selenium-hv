package lession13.testscript;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lession13.common.DriverManager;
import lession13.common.TestBase;
import lession13.pages.LoginPage;
import lession13.provider.LoginProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class LoginTest extends TestBase {
    private WebDriver mWebDriver;
    private String baseURL = "https://rise.fairsketch.com";
    private LoginPage loginPage;

    @BeforeMethod
    public void beforeTestMethod() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        mWebDriver = new ChromeDriver(configChromeOption());
        DriverManager.setWebDriver(mWebDriver);

        loginPage = new LoginPage(DriverManager.getWebDriver());
        loginPage.gotoWebsite(baseURL);
    }

    @AfterMethod
    public void afterMethod() {
        DriverManager.quit();
    }

    @Test(description ="Verify the login function")
    public void RISE_Login_001_Correct() {
        loginPage.login("admin@demo.com", "riseDemo");

        // Neu phai login voi nhieu user thi ban se thuc hien nhu the nao
    }


    @Test(
            priority = 1,
            description = "Verify the login function",
            dataProvider = "RISE_Login",
            dataProviderClass = LoginProvider.class)
    public void RISE_Login_002_Correct(HashMap<String, String> data) {
        loginPage.login(data.get("username"), data.get("password"));
    }

}
