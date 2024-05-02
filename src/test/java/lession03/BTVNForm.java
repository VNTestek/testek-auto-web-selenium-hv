package lession03;

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
    @Test(description = "Xác định phần tử với tìm kiếm nâng cao ")
    public void btvn_findLocator_Advance_02() {
        gotoDemoQAWebsite();

        // Full Name Component
        String fullNameLabelXPath = "//label[text()='Full Name']";
        WebElement fullNameEle = webDriver.findElement(By.xpath(fullNameLabelXPath));


        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản advance khác nhau cho cùng component


        // TODO: 10/05/2023 Làm tương tự với các đối tượng khác
    }

    /**
     * Description:
     */
    @Test(description = "Xác định phần tử kết hợp vị trí để thực hiện tìm kiếm nâng cao")
    public void btvn_findLocator_Position_03() {
        gotoDemoQAWebsite();

        // Full Name Component
        /**
         * Xác định phần tử label (Full Name):
         * 1. Xác định phần tử input có placeholder='Full Name'
         * 2. Tìm kiếm tổ tiên của phần tử số (1) thỏa mãn điều kiện id='userName-wrapper'
         * 3. Thực hiện tìm kiếm trong toàn bộ phần tử (2) có tag là label
         * -> Đây chính là phần tử mà chúng ta cần tìm
         */

        String fullNameLabelXPath = "//input[@placeholder='Full Name']/ancestor::div[@id='userName-wrapper']//label";
        WebElement fullNameEls = webDriver.findElement(By.xpath(fullNameLabelXPath));

        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản position khác nhau cho cùng component


        // TODO: 10/05/2023 Làm tương tự với các đối tượng khác
    }


    /**
     * Go to DemoQA Website
     */
    private void gotoDemoQAWebsite() {
        String url = "https://demoqa.com/text-box";
        webDriver.get(url);
    }


}
