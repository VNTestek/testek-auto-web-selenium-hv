package com.testek.study.lesson07;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.util.Set;

/**
 * Class for Window interaction
 */
public class WindowsInteraction {
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
     * Truy cap website
     */
    @Test
    public void testBrowserNavigation() {
        // Truy cap website
        mWebDriver.get(baseURL);
        // Truy cap link khac
        mWebDriver.get("https://selenium.dev");
        // Truy cap link khac
        mWebDriver.navigate().to(baseURL);

        // Back laij trang selenium.dev
        mWebDriver.navigate().back();

        // Chuyen tiep toi trang demoQA
        mWebDriver.navigate().forward();
    }

    /**
     * Windows in Selenium
     */
    @Test
    public void testWindows() {
        // Truy cap website
        mWebDriver.get(baseURL);

        String curWinID = mWebDriver.getWindowHandle();

        // Create a new window tab
        mWebDriver.switchTo().newWindow(WindowType.TAB);
        mWebDriver.get("https://selenium.dev");

        // Get window handle list
        Set<String> windowList = mWebDriver.getWindowHandles();
        System.out.println("Windows List: " + windowList);

        // Switch to previous window
        mWebDriver.switchTo().window(curWinID);
    }

    /**
     * Windows in Selenium
     */
    @Test
    public void testAlerts() {
        // Truy cap website
        mWebDriver.get(" https://demoqa.com/alerts");

        // Tương tác với Alert
        String alertButtonXPath = "//button[@id='alertButton']";
        WebElement alertEle = mWebDriver.findElement(By.xpath(alertButtonXPath));
        alertEle.click();

        //Chuyen sang alert
        Alert alert = mWebDriver.switchTo().alert();
        String text = alert.getText();
        alert.accept();                     // Press to OK

        // alert.sendKeys("Selenium");      // Type your message
        // alert.dismiss();                 // Press the Cancel button
    }


    @Test(description = "Thuc hanh 01: Tuong tac windows")
    public void testPracticeWindows() {
        // Truy cập địa chỉ "https://demoqa.com/alertsWindows"
        mWebDriver.get("https://demoqa.com/alertsWindows");

        // Click "Browser Windows"
        String browserWinXPath = "//span[text()='Browser Windows']";
        WebElement browserWinEle = mWebDriver.findElement(By.xpath(browserWinXPath));
        browserWinEle.click();

        // Get current windows
        String currentWin = mWebDriver.getWindowHandle();
        System.out.println("Current window: " + currentWin);

        // Thực hiện click vào từng button (03 buttons)
        WebElement newTabEle = mWebDriver.findElement(By.id("tabButton"));
        newTabEle.click();

        Set<String> windowList = mWebDriver.getWindowHandles();
        System.out.println(MessageFormat.format("The total windows: {0}", windowList.size()));

        // Switch to a new window
        for (String win : windowList) {
            if (!win.equals(currentWin)) {
                mWebDriver.switchTo().window(win);
            }
        }
        System.out.println("New window: " + mWebDriver.getWindowHandle());
        WebElement textWindow2Ele = mWebDriver.findElement(By.id("sampleHeading"));
        String textValue = textWindow2Ele.getText();
        if (textValue.equals("This is a sample page"))
            System.out.println("Verify the window text : successfully - " + textValue);

        // Back về trang chủ tại bước 2 (Sử dụng phương thức get window đầu tiên và switchTo)
        mWebDriver.switchTo().window(currentWin);

        // Lặp lại bước 3,4 cho từng loại button khác nhau
    }


    @Test(description = "Thuc hanh 02: Tuong tac voi Alert")
    public void testPracticeAlert() {
        // Truy cập địa chỉ "https://demoqa.com/alertsWindows"
        mWebDriver.get("https://demoqa.com/alertsWindows");

        // Click "Alerts" tại thanh menu bar
        String alertXPath = "//span[text()='Alerts']";
        WebElement alertWinEle = mWebDriver.findElement(By.xpath(alertXPath));
        alertWinEle.click();

        // Thực hiện mở từng alert bằng cách click vào [Click me]
        WebElement alertEle = mWebDriver.findElement(By.id("promtButton"));
        alertEle.click();

        // Switch to Alert
        Alert alert = mWebDriver.switchTo().alert();
        String alertText  = alert.getText();
        System.out.println("Text : " + alertText);

        alert.sendKeys("Automation Test - 2023");
        alert.accept();

        WebElement alertTextRes = mWebDriver.findElement(By.id("promptResult"));
        System.out.println("Your text: " + alertTextRes.getText());

        // Lặp lại các bước như vậy tới khi hết thì dừng
    }

    @Test(description = "Thuc hanh 03: Tuong tac voi Frame")
    public void testPracticeFrame() {
        // Truy cập địa chỉ "https://demoqa.com/alertsWindows"
        mWebDriver.get("https://demoqa.com/alertsWindows");

        // Click "Frames" tại thanh menu bar
        String frameXPath = "//span[text()='Frames']";
        WebElement frameEle = mWebDriver.findElement(By.xpath(frameXPath));
        frameEle.click();

        // Thực hiện mở từng alert bằng cách click vào [Click me]
        WebElement alertEle = mWebDriver.findElement(By.id("promtButton"));
        alertEle.click();

        // Switch to Alert
        Alert alert = mWebDriver.switchTo().alert();
        String alertText  = alert.getText();
        System.out.println("Text : " + alertText);

        alert.sendKeys("Automation Test - 2023");
        alert.accept();

        WebElement alertTextRes = mWebDriver.findElement(By.id("promptResult"));
        System.out.println("Your text: " + alertTextRes.getText());

        // Lặp lại các bước như vậy tới khi hết thì dừng
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
