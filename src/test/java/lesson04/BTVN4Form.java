package lesson04;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

/**
 * Hoc vien hoan thanh bai tap trong class nay
 */
public class BTVN4Form {
    // The driver for interacting with the webpage
    private WebDriver webDriver;

    /**
     * Method thực thi trước mỗi class, tiến hành cấu hình Chrome Driver và khởi tạo
     */
    @BeforeClass
    public void beforeClass() {
        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--max-window-size");
        chromeOptions.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(chromeOptions);
    }

    /**
     * Method thực thi cuối cùng mỗi class, sẽ tiến hành đóng toàn bộ các chrome session đang chạy - Debug Mode
     **/
    @AfterClass
    public void afterClass() {
        if (Objects.nonNull(webDriver)) webDriver.quit();
    }


    /**
     * Description:
     */
    @Test(description = "Xác định phần tử với tìm kiếm nâng cao ")
    public void btvn_improve_findingLocator_01() {
        gotoDemOrangeHrmWebsite();

        System.out.println("Vincent-Debug: Access website");
        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản advance khác nhau cho cùng component
    }


    /**
     * Go to DemoQA Website
     */
    private void gotoDemOrangeHrmWebsite() {
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        webDriver.get(url);

        String LOGIN_FORM ="//input[@placeholder='%s']";

        // Wait for login shown
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LOGIN_FORM,"Username"))));

        // Input username
        WebElement loginEle = webDriver.findElement(By.xpath(String.format(LOGIN_FORM,"Username")));
        loginEle.clear();
        loginEle.sendKeys("Admin");

        // Input password
        WebElement passwordEle = webDriver.findElement(By.xpath(String.format(LOGIN_FORM,"Password")));
        passwordEle.clear();
        passwordEle.sendKeys("admin123");

        // Click login button
        WebElement loginButton = webDriver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        // Wait for user icon shown
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header//img[@alt='profile picture']")));

        // Access my profile
        WebElement myProfileEle = webDriver.findElement(By.xpath("//nav[@role='navigation']//span[text()='My Info']"));
        myProfileEle.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-layout-context']//img[@alt='profile picture']")));
    }


}
