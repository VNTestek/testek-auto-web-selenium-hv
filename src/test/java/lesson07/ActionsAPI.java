package lesson07;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Class for Window interaction
 */
public class ActionsAPI {
    private WebDriver mWebDriver;
    private String baseURL = "https://demoqa.com/tool-tips";

    @BeforeMethod
    public void beforeTestMethod() {
        // Step 01: Setup file thực thi chrome driver cho system

        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        // Lưu ý: Cần thêm thư viện webdrivermanager tại pom

        // Step 2: Khởi tạo Chrome Options : Chứa các tùy chỉnh cho Chrome
        ChromeOptions chromeOptions = new ChromeOptions();
        // Cài đặt Chrome mở full screen
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        // Step 3: Khởi tạo WebDriver -> Tương tác với các phần tử website
        mWebDriver = new ChromeDriver(chromeOptions);
    }

    @AfterMethod
    public void afterMethod() {
        if (mWebDriver != null) {
            mWebDriver.quit();
            mWebDriver = null;
        }
    }


    /**
     * Truy cap website and interact with frame
     */
    @Test
    public void testAction() {
        // Truy cap website
        mWebDriver.get(baseURL);

        // Create a new Actions
        Actions mActions = new Actions(mWebDriver);

        // Hover to elements
        String hoverId = "toolTipButton";
        WebElement hoverElement = mWebDriver.findElement(By.id(hoverId));
        mActions.moveToElement(hoverElement).click().build().perform();

        // Verify the hover. The elements had a new attribute "aria-describedby"
        String hoverStatus = mWebDriver.findElement(By.id(hoverId)).getAttribute("aria-describedby");
        System.out.println(hoverStatus);

        // Click via Actions
        //  mActions.click().build().perform();
    }


    /**
     * Wait for
     *
     * @param seconds : waiting time
     */
    public void sleep(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
