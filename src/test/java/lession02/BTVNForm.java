package lession02;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Objects;

/**
 * Hoc vien hoan thanh bai tap trong class nay
 */
public class BTVNForm {
    private String baseURL = "https://demoqa.com/elements";

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
    @Test(description = "Xác định phần tử với các phương pháp tìm kiếm cơ bản")
    public void btvn_findLocator_Basic_02() {
        gotoDemoQAWebsite();

        // Full Name Component
        String firstNameID = "firstName";
        WebElement firstNameEle = webDriver.findElement(By.id(firstNameID));

        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản khác nhau cho cùng component


        // TODO: 10/05/2023 Làm tương tự với các đối tượng khác

    }

    /**
     * Description:
     */
    @Test(description = "Xác định phần tử với XPath")
    public void btvn_findLocator_XPath_02() {
        gotoDemoQAWebsite();

        // Full Name Component
        String firstNamePlaceHolderXPath = "//input[@placeholder='First Name']";
        WebElement firstNameEle = webDriver.findElement(By.xpath(firstNamePlaceHolderXPath));

        String firstNameIDXPath = "//input[@id ='firstName']";
        firstNameEle = webDriver.findElement(By.xpath(firstNameIDXPath));


        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản xpath khác nhau cho cùng component


        // TODO: 10/05/2023 Làm tương tự với các đối tượng khác
    }

    /**
     * Description:
     */
    @Test(description = "Xác định phần tử với CSS")
    public void btvn_findLocator_CSS_03() {
        gotoDemoQAWebsite();

        // Full Name Component
        String firstNamePlaceHolderCss = "input[placeholder='First Name']";
        WebElement firstNameEle = webDriver.findElement(By.cssSelector(firstNamePlaceHolderCss));

        String firstNameIDCss = "input[id ='firstName']";
        firstNameEle = webDriver.findElement(By.cssSelector(firstNameIDCss));


        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản css khác nhau cho cùng component


        // TODO: 10/05/2023 Làm tương tự với các đối tượng khác
    }


    /**
     * Go to DemoQA Website
     */
    private void gotoDemoQAWebsite() {
        String url = "https://demoqa.com/automation-practice-form";
        webDriver.get(url);
    }



}
