package lession05;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class PracticeForm {
    // The driver for interacting with the webpage
    private WebDriver webDriver;

    /**
     * Method thực thi trước mỗi class, tiến hành cấu hình Chrome Driver và khởi tạo
     */
    @BeforeClass
    public void beforeClass() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
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
    @Test(description = "Luyen tap tim kiem phan tu")
    public void practices_findingLocator_01() {
        gotoRiseWebsite();

        System.out.println("Vincent-Debug: Access website");
        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản advance khác nhau cho cùng component
    }


    /**
     * Go to DemoQA Website
     */
    private void gotoRiseWebsite() {
        String url = "https://rise.fairsketch.com/signin";
        webDriver.get(url);

        String LOGIN_FORM ="//input[@placeholder='%s']";

        // Wait for login shown
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LOGIN_FORM,"Email"))));

        // Input username
        WebElement loginEle = webDriver.findElement(By.xpath(String.format(LOGIN_FORM,"Email")));
        loginEle.clear();
        loginEle.sendKeys("client@demo.com");

        // Input password
        WebElement passwordEle = webDriver.findElement(By.xpath(String.format(LOGIN_FORM,"Password")));
        passwordEle.clear();
        passwordEle.sendKeys("riseDemo");

        // Click login button
        WebElement loginButton = webDriver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        // Wait for user icon shown
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='user-dropdown']//img")));

        // Access my profile
        WebElement myProfileEle = webDriver.findElement(By.xpath("//ul[@id='sidebar-menu']//span[text()='My Profile']"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", myProfileEle);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='profile-image-preview']")));
    }


}
