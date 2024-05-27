package lesson12.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class DemoQATextBoxPage {
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    public DemoQATextBoxPage(WebDriver mWebDriver) {
        this.mWebDriver = mWebDriver;
        // Lặp đi lặp lại việc khởi tạo WebWaitDriver -> Đưa ra BasePage
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Nhap thon tin cho student
     */
    public void inputStudentInfo(HashMap<String, String> data) {
        inputTextTo("Full Name", data.getOrDefault("fullName", null));
        inputTextTo("name@example.com", data.getOrDefault("email", null));
        inputTextTo("Current Address", data.getOrDefault("address", null));

        WebElement perAddEle = mWebDriver.findElement(By.id("permanentAddress"));
        perAddEle.clear();
        perAddEle.sendKeys(data.getOrDefault("permanentAddress", null));
    }


    /**
     * Tao method chung cho nhap du lieu
     */
    private void inputTextTo(String placeHolder, String value) {
        String DYNAMIC_INPUT_PLACEHOLDER_XPATH = "//*[@placeholder='%s']";

        String xpath = String.format(DYNAMIC_INPUT_PLACEHOLDER_XPATH, placeHolder);
        WebElement element = mWebDriver.findElement(By.xpath(xpath));
        element.clear();
        element.sendKeys(value);
    }
}
