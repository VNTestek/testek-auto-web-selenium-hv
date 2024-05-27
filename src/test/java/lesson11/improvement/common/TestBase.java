package lesson11.improvement.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.Objects;

@Listeners(TestListener.class)
public class TestBase {
    private WebDriver mWebDriver;

    @BeforeSuite
    public void beforeSuite() {
        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        mWebDriver = getWebDriverInstance();
        DriverManager.setWebDriver(mWebDriver);
    }


    @AfterSuite
    public void afterSuite() {
        DriverManager.quit();
    }

    /**
     * Init WebDriver
     */
    public WebDriver getWebDriverInstance() {
        return Objects.nonNull(mWebDriver)? mWebDriver: new ChromeDriver(configChromeOption());
    }

    /**
     * Config Chrome Option
     */
    public ChromeOptions configChromeOption() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        return chromeOptions;
    }
}
