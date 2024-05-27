package lesson06;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Class tuong tac voi cac thanh phan co ban trong website
 */
public class WebElementInteraction {
    private WebDriver mWebDriver;
    private String baseURL = "https://demoqa.com/automation-practice-form";

    @BeforeMethod
    public void beforeTestMethod() {
        // Step 01: Setup file thực thi chrome driver cho system
        // Cách 01: Cài đặt driver cho chrome thông qua file thực thi
        //String chromeDriverPath = "src/test/resources/driver/chromedriver";
        //System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // Cách 02: Cài đặt tự đông
        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
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
     * Tuong tac voi Text Box
     */
    @Test
    public void testTextBoxInteraction() {
        mWebDriver.get(baseURL);

        String firstNameXPath = "//input[@placeholder='First Name']";
        WebElement firstNameElement = mWebDriver.findElement(By.xpath(firstNameXPath));
        firstNameElement.clear();
        firstNameElement.sendKeys("Vincent First Name");

        // Lay gia tri text cua doi tuong
        String text = firstNameElement.getAttribute("value");
        System.out.println("First name Value: " + text);

        // Lay gia tri cua moi thuoc tinh trong doi tuong
        String idElement = firstNameElement.getAttribute("id");
        System.out.println("First name Id: " + idElement);

        sleep(2);
        // Xoa du lieu tai phan tu
        firstNameElement.clear();
    }

    /**
     * Tương tác với Button
     */
    @Test
    public void testButtonInteraction() {
        mWebDriver.get("https://demoqa.com/text-box");
        sleep(2);

        String buttonXPath = "//button[@id='submit']";
        WebElement buttonElement = mWebDriver.findElement(By.xpath(buttonXPath));
        // Create a wait driver
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
        // Wait for your element to ready click
        webDriverWait.until(ExpectedConditions.elementToBeClickable(buttonElement));

        buttonElement.click();
    }

    /**
     * Tuong tac voi Check Box
     */
    @Test
    public void testCheckBoxInteraction() {
        mWebDriver.get(baseURL);
        sleep(2);

        String sportAttr = "hobbies-checkbox-1";
        String DYNAMIC_CHECKBOX_FORMAT = "//input[@type='checkbox' and @id='%s']";
        String DYNAMIC_CHECKBOX_LABEL_FORMAT = "//label[@for='%s']";
        String sportXPath = String.format(DYNAMIC_CHECKBOX_FORMAT, sportAttr);
        WebElement sportElement = mWebDriver.findElement(By.xpath(sportXPath));
        // sportElement.click();

        // Click checkbox qua label
        String sportLabel = String.format(DYNAMIC_CHECKBOX_LABEL_FORMAT, sportAttr);
        WebElement sportLabelElement = mWebDriver.findElement(By.xpath(sportLabel));
        sportLabelElement.click();

        boolean isChecked = sportElement.isSelected();
        System.out.println("Sport Checked: " + isChecked);

        sleep(2);
        sportLabelElement.click();
        isChecked = sportElement.isSelected();
        System.out.println("Sport Checked: " + isChecked);

        // FIXME - Khi tuong tac voi checkbox can chu y van de gi ? Hoi hoc vien
        // TODO - Thuc hanh: Hoc vien lam tuong tu voi Music
    }

    /**
     * Tuong tac voi Radio Button
     */
    @Test
    public void testRadioButtonInteraction() {
        mWebDriver.get(baseURL);
        sleep(2);

        String maleAttr = "gender-radio-1";
        String DYNAMIC_RADIO_FORMAT = "//input[@type='radio' and @id='%s']";
        String DYNAMIC_RADIO_LABEL_FORMAT = "//label[@for='%s']";
        String maleXPath = String.format(DYNAMIC_RADIO_FORMAT, maleAttr);
        WebElement maleElement = mWebDriver.findElement(By.xpath(maleXPath));
        // sportElement.click();

        // Click checkbox qua label
        String maleLabel = String.format(DYNAMIC_RADIO_LABEL_FORMAT, maleAttr);
        WebElement maleLabelElement = mWebDriver.findElement(By.xpath(maleLabel));
        maleLabelElement.click();

        boolean isChecked = maleElement.isSelected();
        System.out.println("Male Checked: " + isChecked);

        sleep(2);
        maleLabelElement.click();
        isChecked = maleElement.isSelected();
        System.out.println("Male Checked: " + isChecked);

        // TODO - Thuc hanh: Hoc vien lam tuong tu voi Female
    }

    /**
     * Tuong tac voi Link
     */
    @Test
    public void testLinkInteraction() {
        String url = "https://demoqa.com/links";
        mWebDriver.get(url);
        sleep(2);

        // Tim kiem theo XPath
        String linkXPath = "//a[@id='simpleLink']";
        WebElement linkXPathElement = mWebDriver.findElement(By.xpath(linkXPath));
        String linkXPathHref = linkXPathElement.getAttribute("href");
        System.out.println("Href: " + linkXPathHref);

        // Tim kiem theo link
        WebElement linkElement = mWebDriver.findElement(By.linkText("Home"));
        String linkStr = linkXPathElement.getAttribute("href");
        System.out.println("Href: " + linkStr);
    }

    @Test
    public void testDropdownInteraction(){
        String url="http://demo.seleniumeasy.com/basic-select-dropdown-demo.html";
        mWebDriver.get(url);
        sleep(2);

        // Tim kiem doi tuong dropdown: La Select Tag
        WebElement dropdownElement = mWebDriver.findElement(By.xpath("//select[@id='select-demo']"));
        Select select = new Select(dropdownElement);

        // Kiem tra ket qua sau khi lua chon
        WebElement  result = mWebDriver.findElement(By.xpath("//p[@class='selected-value']"));

        // Lua chon gia tri text
        select.selectByVisibleText("Tuesday");
        System.out.println("Result : " + result.getText());

        // Lua chon gia tri text
        select.selectByValue("Tuesday");
        System.out.println("Result : " + result.getText());

        // Lua chon vi tri
        select.selectByIndex(3);
        System.out.println("Result : " +result.getText());
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
