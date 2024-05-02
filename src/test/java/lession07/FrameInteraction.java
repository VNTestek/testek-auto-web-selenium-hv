package lession07;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Class for Window interaction
 */
public class FrameInteraction {
    private WebDriver mWebDriver;
    private String baseURL = "https://demoqa.com/frames";

    @BeforeMethod
    public void beforeTestMethod() {
        // Step 01: Setup file thực thi chrome driver cho system

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
    public void testFrameNavigation() {
        // Truy cap website
        mWebDriver.get(baseURL);

        // Thuc hien tim kiem frame va verify
        String frameXPath = "//iframe[@id='frame1']";
        String frameID = "frame1";
        WebElement frameElement = mWebDriver.findElement(By.id(frameID));

        //region Frame verification
        String expFrameWidth= "500px";
        String actFrameWidth = frameElement.getAttribute("width");
        if(expFrameWidth.equals(actFrameWidth)){
            System.out.println(String.format("The width of frame: %s", actFrameWidth));
        } else {
            System.out.println(String.format("The width of frame is not same: actual: %s, expected: %s", actFrameWidth, expFrameWidth));
        }
        //endregion

        // Access to this frame
        mWebDriver.switchTo().frame(frameElement);
        String sampleHeadingID = "sampleHeading";
        WebElement sampleHeading = mWebDriver.findElement(By.id(sampleHeadingID));
        String actHeadingText = sampleHeading.getText();
        System.out.println("Current text: " + actHeadingText);

        //region Frame verification
        String expHeadingText = "This is a sample page";
        if(actHeadingText.equals(expHeadingText)){
            System.out.println(String.format("The frame text:  %s", actHeadingText));
        } else {
            System.out.println(String.format("The frame text is not same: actual: %s, expected: %s", actHeadingText, expHeadingText));
        }
        //endregion

        // Back to page contents
        mWebDriver.switchTo().defaultContent();
    }

    /**
     * Thuc hanh voi frame con lai
     */
    @Test
    public void testFramePractice_002() {
        // Truy cap website
        mWebDriver.get(baseURL);

        // Truy cap frame
        String frameXPath = "//iframe[@id='frame1']";
        String frameID = "frame1";
        WebElement frameElement = mWebDriver.findElement(By.id(frameID));

        // Kiem tra thong tin frame da lay chinh xac khong
        System.out.println("Debug");

        // Dieu huong qua frame va thuc hien hanh dong


        // Thuc hien chuyen lai tab chinh va thao tac
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
