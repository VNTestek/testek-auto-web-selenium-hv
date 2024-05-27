package lesson16.testscript;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lesson16.common.DriverManager;
import lesson16.common.TestBase;
import lesson16.pages.LoginPage;
import lesson16.provider.LoginProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
       // WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        mWebDriver = new ChromeDriver(configChromeOption());
        DriverManager.setWebDriver(mWebDriver);
//
//        loginPage = new LoginPage(DriverManager.getWebDriver());
//        loginPage.gotoWebsite(baseURL);
    }

    @AfterMethod
    public void afterMethod() {
        DriverManager.quit();
    }

    @Test(description = "Verify the login function")
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


    @Test
    public void testThai() {
        String url="https://www.mercadolibre.com/jms/mla/lgz/msl/login/H4sIAAAAAAAEAzWNQQ7DIBAD_-IzSu4c-xG0JUuCCgEtm5Kqyt8r1PZo2TN-I5U17k5flWHBZ03RR4VBTaShSHZxgUWuMGhR-RcTjQkJZVaWBvseopWXG4ciQxUoNYYBHbq5kEqH_X7BIDbHp7LslFzn-zPyaP_EWmCxqdZm57n3PmUWT0uptJbJlzyRzLgMAjV1KuQfsCoHXx-t-OJLzQAAAA/user";
        mWebDriver.get(url);

        WebElement element = mWebDriver.findElement(By.id("user_id"));
        element.clear();
        element.sendKeys("Tesststs");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
       // element.sendKeys("Te");
        System.out.println("Tesst");
        WebElement button = mWebDriver.findElement(By.xpath("//button[@type=\"submit\"]"));
        System.out.println(button.getAttribute("class"));
    }
}
