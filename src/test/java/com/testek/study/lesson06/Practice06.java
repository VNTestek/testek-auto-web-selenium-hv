package com.testek.study.lesson06;

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
 * Dap an cho BTVN
 */
public class Practice06 {
    private WebDriver mWebDriver;
    private String baseURL = "https://demoqa.com/automation-practice-form";
    private String DYNAMIC_INPUT_PLACEHOLDER_FORM = "//input[@placeholder='%s']";

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

    @Test(description = "Thuc hanh 01: Tuong tac Text box va Button")
    public void insertUserForm() {
        // Truy cap dia chi chinh
        baseURL = "https://demoqa.com/text-box";
        mWebDriver.get(baseURL);
        sleep(2);

        String DYNAMIC_INPUT_PLACEHOLDER_FORM = "//input[@placeholder='%s']";
        // Input Full Name
        String fullNameXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "Full Name");
        WebElement fullNameEle = mWebDriver.findElement(By.xpath(fullNameXPath));
        fullNameEle.sendKeys("Vincent");

        // Input email
        String emailXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "name@example.com");
        WebElement emailEle = mWebDriver.findElement(By.xpath(emailXPath));
        emailEle.sendKeys("vincent@gmail.com");


        String DYNAMIC_TEXTAREA_ID_FORM = "//textarea[@id='%s']";
        // Input current address
        String currentXPath = String.format(DYNAMIC_TEXTAREA_ID_FORM, "currentAddress");
        WebElement currentEle = mWebDriver.findElement(By.xpath(currentXPath));
        currentEle.sendKeys("KĐT Nam Trung Yên, P. Yên Hòa, Q. Cầu Giấy, TP Hà Nội");

        // Input Permanent address
        String permanentXPath = String.format(DYNAMIC_TEXTAREA_ID_FORM, "permanentAddress");
        WebElement permanentEle = mWebDriver.findElement(By.xpath(permanentXPath));
        permanentEle.sendKeys("Q. Cầu Giấy, TP Hà Nội");

        // Click to Submit
        String buttonXPath = "//button[@id='submit']";
        WebElement submitEle = mWebDriver.findElement(By.xpath(buttonXPath));
        submitEle.click();
    }

    /**
     * Action co ban cho cac WebElement
     */
    @Test(description = "Thuc hanh 02: Tuong tac Radio button")
    public void testRadioButtonChecked() {
        // click
        baseURL = "https://demoqa.com/radio-button";
        mWebDriver.get(baseURL);

        // 1. Tuong tac vs phan tu
        String radYesXPath = "//label[text()='Yes']";
        WebElement radYesLabelEle = mWebDriver.findElement(By.xpath(radYesXPath));
        WebElement radYesButtonEle = mWebDriver.findElement(By.id("yesRadio"));
        WebElement radImpButtonEle = mWebDriver.findElement(By.id("impressiveRadio"));
        WebElement radNoButtonEle = mWebDriver.findElement(By.id("noRadio"));

        // Kiem tra Yes check chua ? chua thi check va kiem tra trang thai
        if (!radYesButtonEle.isSelected()) {
            radYesLabelEle.click();
        }

        // 2. Kiem tra xem da duoc chon chua
        boolean isYesChecked = radYesButtonEle.isSelected();
        boolean isImpChecked = radImpButtonEle.isSelected();
        boolean isNoChecked = radNoButtonEle.isSelected();
        System.out.println("Yes button - Checked: "+ isYesChecked);
        System.out.println("Impressive button - Checked: "+ isImpChecked);
        System.out.println("No button - Checked: "+ isNoChecked);
    }

    @Test
    public void insertFormInformation() {
        mWebDriver.get(baseURL);
        sleep(2);

        // Input First Name
        String firstNameXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "First Name");
        WebElement firstNameEle = mWebDriver.findElement(By.xpath(firstNameXPath));
        firstNameEle.sendKeys("Vincent");

        // Input Last Name
        String lastNameXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "Last Name");
        WebElement lastNameEle = mWebDriver.findElement(By.xpath(lastNameXPath));
        lastNameEle.sendKeys("T");

        // Input Email
        String emailXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "name@example.com");
        WebElement emailEle = mWebDriver.findElement(By.xpath(emailXPath));
        emailEle.sendKeys("vincent@gmail.com");

        // Input Genders
        String maleAttr = "gender-radio-1";
        String DYNAMIC_RADIO_LABEL_FORMAT = "//label[@for='%s']";
        String maleLabel = String.format(DYNAMIC_RADIO_LABEL_FORMAT, maleAttr);
        WebElement maleLabelElement = mWebDriver.findElement(By.xpath(maleLabel));
        maleLabelElement.click();

        // Input Mobile Number
        String mobileXPath = String.format(DYNAMIC_INPUT_PLACEHOLDER_FORM, "Mobile Number");
        WebElement mobileEle = mWebDriver.findElement(By.xpath(mobileXPath));
        mobileEle.sendKeys("84946210891");

        // Input Date of Birthday
        String dateXPath = "//input[@id='dateOfBirthInput']";
        WebElement dateOfBirthEle = mWebDriver.findElement(By.xpath(dateXPath));
        dateOfBirthEle.click();
        String dateXP = "//div[@role='listbox']//div[@tabindex='0']";
        mWebDriver.findElement(By.xpath(dateXP)).click();


        // Input subject
        String subjectXPath = "//input[@id='subjectsInput']";
        WebElement subjectEle = mWebDriver.findElement(By.xpath(subjectXPath));
        subjectEle.sendKeys("Computer Science");
        String dropDownValueXPath = "//div[contains(@class,'subjects-auto-complete__menu')]//div[normalize-space()='Computer Science']";
        mWebDriver.findElement(By.xpath(dropDownValueXPath)).click();

        // Input Hobbies
        String sportAttr = "hobbies-checkbox-1";
        String DYNAMIC_CHECKBOX_LABEL_FORMAT = "//label[@for='%s']";
        String sportLabel = String.format(DYNAMIC_CHECKBOX_LABEL_FORMAT, sportAttr);
        WebElement sportLabelElement = mWebDriver.findElement(By.xpath(sportLabel));
        sportLabelElement.click();

        // Input Current Address
        String currentAddressXPath = "//textarea[@id='currentAddress']";
        WebElement currentAddressEle = mWebDriver.findElement(By.xpath(currentAddressXPath));
        currentAddressEle.sendKeys("No 1 Pham Van Bach, Yen Hoa, Cau Giay, HN");

        sleep(10);      // For Debug
    }

    /**
     * Wait for loading elements
     *
     * @param seconds : The waiting time in seconds
     */
    private void sleep(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
