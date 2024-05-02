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

import java.util.List;
import java.util.Objects;

import static java.lang.Thread.sleep;

/**
 * Class to find the Web Element and interact with
 *
 * @author: Vincent
 */
public class FindingElement {
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
     * Liệt kê các phương thức tìm kiếm cơ bản
     * 1. Thực hành tìm kiếm thủ công trên website
     * 2. Phát triển các method để kiểm tra xem có xác định được phần tử không ?
     **/
    @Test(enabled = false, description = "Các phương thức tìm kiếm element cơ bản")
    public void findingElements() {
        // Truy cập website
        gotoDemoQAWebsite();

        List<WebElement> elementList = webDriver.findElements(By.cssSelector("[class='form-label']"));
        List<WebElement> xpathElements = webDriver.findElements(By.xpath("//label[@class='form-label']"));
        // Tìm kiếm danh sách phần tử -> Kiểm tra ID mỗi phần tử trong list
        xpathElements.stream().forEach(e -> {
            System.out.println(e.getAttribute("id"));
        });

        // region Find by id
        // Xác định locator
        String idXpath = "//input[@id='userName']";
        String idCSS = "input[id='userName']";
        String idShortedCSS = "userName";
        // Áp dụng tìm kiếm đối tượng
        WebElement userNameElement = webDriver.findElement(By.xpath(idXpath));
        userNameElement = webDriver.findElement(By.cssSelector(idCSS));
        userNameElement = webDriver.findElement(By.id(idShortedCSS));
        //endregion

        // region Find by name
        // Xác định locator
        String viewPortXpath = " //meta[@name='viewport']";     // Locator theo XPath
        String viewPortCSS = "meta[name='viewport']";           // Locator theo CSS
        // Áp dụng tìm kiếm đối tượng
        WebElement viewPortElement = webDriver.findElement(By.xpath(viewPortXpath));
        viewPortElement = webDriver.findElement(By.cssSelector(viewPortCSS));
        //endregion

        // region Find by class name
        // Xác định locator
        String fullNameXPath = "//label[@class='form-label']";  // Locator theo XPath
        String fullNameCSS = "label[class='form-label']";       // Locator theo CSS
        String fullNameShortedCSS = "form-label";               // Locator theo CSS (short)
        // Áp dụng tìm kiếm đối tượng
        WebElement fullNameElement = webDriver.findElement(By.xpath(fullNameXPath));
        fullNameElement = webDriver.findElement(By.cssSelector(fullNameCSS));
        fullNameElement = webDriver.findElement(By.className(fullNameShortedCSS));
        //endregion

        // region Find by tag name
        // Xác định locator
        String tagXPath = "//label";        // Locator theo XPath
        String tagNameCSS = "label";        // Locator theo CSS
        // Áp dụng tìm kiếm đối tượng
        WebElement tagNameElement = webDriver.findElement(By.xpath(tagXPath));
        tagNameElement = webDriver.findElement(By.cssSelector(tagNameCSS));
        //endregion

        // region Switch to link tab
        String linksXPath = "//span[text()='Links']";
        webDriver.findElement(By.xpath(linksXPath)).click();
        //endregion

        // region Find by link attribute
        String linkXPath = "//a[@href='https://demoqa.com']";
        WebElement linkElement = webDriver.findElement(By.xpath(linkXPath));

        String linkCSS = "a[href='https://demoqa.com']";
        linkElement = webDriver.findElement(By.cssSelector(linkCSS));

        String linkShortedCSS = "Home";
        linkElement = webDriver.findElement(By.linkText(linkShortedCSS));

        String partialLinkShortedCSS = "me";
        linkElement = webDriver.findElement(By.partialLinkText(partialLinkShortedCSS));
        // endregion
    }

    /**
     * Sleep for debugging
     *
     * @param milliseconds : waiting time (unit: milliseconds)
     */
    private void waitForDebug(long milliseconds) {
        try {
            sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Go to DemoQA Website
     */
    private void gotoDemoQAWebsite() {
        String url = "https://demoqa.com/text-box";
        webDriver.get(url);
    }
}
