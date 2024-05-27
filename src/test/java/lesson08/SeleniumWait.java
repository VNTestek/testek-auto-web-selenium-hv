package lesson08;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Class for Wait
 */
public class SeleniumWait {
    private WebDriver mWebDriver;
    private String baseURL = "https://demoqa.com/automation-practice-form";

    @BeforeMethod
    public void beforeTestMethod() {
        // Step 01: Setup file thực thi chrome driver cho system
        // Cách 01: Cài đặt driver cho chrome thông qua file thực thi
        //String chromeDriverPath = "src/test/resources/driver/chromedriver";
        //System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // Cách 02: Cài đặt tự đông
        // WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
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
     * Init Implicit Wait
     */
    public void initImplicitWait() {
        // Init implicit wait
        mWebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Tìm kiếm phần tử trong 10s
        // Sau 10s mà không tìm thấy thì sẽ thông báo No Such Element Exception
        mWebDriver.findElement(By.xpath("//input[@id='firstName"));
    }

    /**
     * Init Explicit Wait
     */
    public void initExplicitWait() {
        // Khoi tao WebDriverWait voi thoi gian 10s; Mac dinh interval time : 500ms
        WebDriverWait wait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));

        String firstNameXPath = "//input[@placeholder='First Name']";
        // Thuc hien cho cho toi khi phan tu First Name duoc hien thi tren man hinh
        WebElement firstNameEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstNameXPath)));
        System.out.println("ID :" + firstNameEle.getAttribute("id"));

        // Thuc hien tuong tac voi phan tu
        firstNameEle.sendKeys("Vincent");

        WebDriverWait seleniumWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        seleniumWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstNameXPath)));
    }


    /**
     * Init Fluent Wait
     */
    @Test
    public void initFluentWait(){
        mWebDriver.get(baseURL);

        // Waiting 30 seconds for an element to be present on the page, checking
        // for its presence once every 5 seconds.
        FluentWait<WebDriver> fluentWait = new FluentWait<>(mWebDriver)
                .withTimeout(Duration.ofSeconds(30))        // Timeout
                .pollingEvery(Duration.ofSeconds(5))        // Interval time; 5sec/times
                .ignoring(NoSuchElementException.class);    // Ignore NoSuchElementException

        // Cho toi khi phan tu First Name duoc hien
        String firstNameXPath = "//input[@placeholder='First Name']";
        // Ap dung cach nhu Explicit wait
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstNameXPath)));

        // Cach khac
        WebElement firstNameEle = fluentWait.until(driver -> driver.findElement(By.xpath(firstNameXPath)));
        System.out.println("ID :" + firstNameEle.getAttribute("id"));
    }

    @Test
    public void testWaitExample(){
        // Init Selenium Wait
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));

        String url ="https://demoqa.com/elements";
        mWebDriver.get(url);

        // Click [Text Box] at the left menu
        String textBoxXPath = "//ul[@class='menu-list']//span[text()='Text Box']";
        WebElement textBoxEle = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(textBoxXPath)));
        textBoxEle.click();

        String DYNAMIC_INPUT_PLACEHOLDER_FORMAT = "//input[@placeholder='%s']";
        String fullNameXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORMAT,"Full Name");
        WebElement firstNameEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fullNameXPath)));
        firstNameEle.sendKeys("Vincent");

        String emailXPath  = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORMAT, "name@example.com");
        WebElement emailEle = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(emailXPath)));
        emailEle.sendKeys("vincent@gmail.com");
    }

    @Test(description = "Thuc hanh 01: Wait trong Selenium")
    public void testPracticeWait(){
        // Init Selenium Wait
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));

        String url ="https://demoqa.com";
        mWebDriver.get(url);

        // Wait for the logo shown
        String logoXPath = "//img[@src='/images/Toolsqa.jpg']";
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(logoXPath)));

        // Click [Forms]. Lưu ý cần kiểm tra xem đối tượng đã ready để click chưa
        String formXPath  ="//h5[text()='Forms']";
        WebElement formEle = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(formXPath)));
        formEle.click();

        // Click [Practice Form]. Lưu ý cần kiểm tra xem đối tượng đã ready để click chưa
        String practiceFormXPath ="//span[text()='Practice Form']";
        WebElement practiceEle = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(practiceFormXPath)));
        practiceEle.click();
        System.out.println("");

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
