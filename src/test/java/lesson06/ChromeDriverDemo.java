package lesson06;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Khoi tao ChromeDriver va basic actions
 */
public class ChromeDriverDemo {
    private WebDriver mWebDriver;
    private String baseURL = "https://demoqa.com/automation-practice-form";

    @BeforeMethod
    public void beforeTestMethod() {
        // Step 01: Setup file thực thi chrome driver cho system
        // Cách 01: Cài đặt driver cho chrome thông qua file thực thi
        String chromeDriverPath = "src/test/resources/driver/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // Cách 02: Cài đặt tự đông
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        // Lưu ý: Cần thêm thư viện webdrivermanager tại pom

        // Step 2: Khởi tạo Chrome Options : Chứa các tùy chỉnh cho Chrome
        ChromeOptions chromeOptions = new ChromeOptions();
        // Cài đặt Chrome mở full screen
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        // Step 3: Khởi tạo WebDriver -> Tương tác với các phần tử website
        mWebDriver = new ChromeDriver(chromeOptions);
    }

    /**
     * Truy cap website va tim kiem doi tuong web
     */
    @Test
    public void testInitElement() {

        // Truy cap website
        mWebDriver.get(baseURL);

        /*
         * Tim kiem phan tu tren website
         * Su dung locator da tim truoc do
         */
        String firstNameXPath = "//input[@id='firstName']";
        WebElement firstNameEle = mWebDriver.findElement(By.xpath(firstNameXPath));

        // Kiem tra xem lay dung phan tu khong
        String placeHolder = firstNameEle.getAttribute("placeholder");
        System.out.println("Placeholder: " + placeHolder);
    }

    /**
     * Action co ban cho cac WebElement
     */
    @Test
    public void testActionBasic() {
        // click
        mWebDriver.get(baseURL);
        String chkSportXPath = "//label[text()='Sports']";
        WebElement chkSportXElement = mWebDriver.findElement(By.xpath(chkSportXPath));
        chkSportXElement.click();

        // Send Key
        mWebDriver.get(baseURL);
        String firstNameXPath = "//input[@placeholder='First Name']";
        WebElement firstNameElement = mWebDriver.findElement(By.xpath(firstNameXPath));
        firstNameElement.sendKeys("Vincent First Name");

        // Clear
        mWebDriver.get(baseURL);
        String lastNameXPath = "//input[@placeholder='Last Name']";
        WebElement lastNameElement = mWebDriver.findElement(By.xpath(lastNameXPath));
        firstNameElement.sendKeys("Vincent Last Name");
    }
}
