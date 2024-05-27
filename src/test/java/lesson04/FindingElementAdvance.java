package lesson04;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FindingElementAdvance {

    // The driver for interacting with the webpage
    private WebDriver webDriver;


    @BeforeClass
    public void beforeClass() {
        //region Chrome Driver configuration
        /**
         * 1. Check your Chrome version
         * 2. Download chrome driver at link: https://chromedriver.chromium.org/downloads
         * 3. Create a folder and save chrome driver in there
         * 4. config chrome driver in your source code
         */
        //        String chromeDriverPath = "src/test/resources/driver/chromedriver";
        //        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--max-window-size");
        chromeOptions.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(chromeOptions);
    }


    /**
     * Create a xpath expression
     * @param format : Expression format
     * @param values : Values for the expression
     * @return : The xpath
     */
    private String getStringXPath(String format, String... values) {
        return String.format(format, values);
    }

    /**
     * URL: https://demoqa.com/automation-practice-form
     * Finding all locators in this URL.
     */
    @Test(description = "Dynamic Locator Test")
    public void testDynamicLocator() {
        // Bước 1: Thực hiện đánh giá các thành phần trên website, phán đoán các thành phần có điểm chung và có thể có chung các thông tin về locator
        // Nhận định: First Name, Last Name, Email, Mobile và Subjects có khả năng có điểm chung : Ô nhập text dạng textbox (thẻ input)

        // Bước 2: Thực hiện tìm kiếm lần lượt các phần tử trên website và tập hợp các đối tượng có chung thuộc tính
        String firstNameXPath = "//input[@placeholder='First Name']";
        String lastNameXPath = "//input[@placeholder='Last Name']";
        String emailXPath = "//input[@placeholder='name@example.com']";
        String mobilePhoneXPath = "//input[@placeholder='Mobile Number']";
        String subjectXPath = "//input[@id='subjectsInput']";

        // => Tất cả các phần tử đều có input và placeholder, ngoại trừ subject khác với các phần tử còn lại.

        // Bước 3: Thực hiện tối ưu các locator vừa tìm kiếm
        String DYNAMIC_LOCATOR_INPUT_TEXT_FORM = "//input[@placeholder='%s']";

        // Bước 4: Thử nghiệm và đánh giá
        String finalFirstNameXPath = String.format(DYNAMIC_LOCATOR_INPUT_TEXT_FORM, "First Name");
        String finalLastNameXPath = String.format(DYNAMIC_LOCATOR_INPUT_TEXT_FORM, "Last Name");
        String finalLastEmailXPath = String.format(DYNAMIC_LOCATOR_INPUT_TEXT_FORM, "name@example.com");
        String finalMobileXPath = String.format(DYNAMIC_LOCATOR_INPUT_TEXT_FORM, "Mobile Number");


        // Tương tự với các thành phần khác
        // Gender
        String maleXPath ="//input[@type='radio']//following-sibling::label[text()='Male']";
        String femaleXPath ="//input[@type='radio']//following-sibling::label[text()='Female']";
        String otherXPath ="//input[@type='radio']//following-sibling::label[text()='Other']";

        // Hobbies
        String sportXPath="//input[@type='checkbox']//following-sibling::label[text()='Sports']";
        String readingXPath="//input[@type='checkbox']//following-sibling::label[text()='Reading']";
        String musicXPath="//input[@type='checkbox']//following-sibling::label[text()='Music']";

        String DYNAMIC_LOCATOR_SELECT_LABEL_FORM = "//input[@type='%s']//following-sibling::label[text()='%s']";

        String finalMaleXPath = String.format(DYNAMIC_LOCATOR_SELECT_LABEL_FORM, "radio", "Male");
        String finalFemaleXPath = String.format(DYNAMIC_LOCATOR_SELECT_LABEL_FORM, "radio", "Female");
        String finalOtherXPath = String.format(DYNAMIC_LOCATOR_SELECT_LABEL_FORM, "radio", "Other");
        String finalSportXPath = String.format(DYNAMIC_LOCATOR_SELECT_LABEL_FORM, "checkbox", "Sports");
        String finalReadingXPath = String.format(DYNAMIC_LOCATOR_SELECT_LABEL_FORM, "checkbox", "Reading");
        String finalMusicXPath = String.format(DYNAMIC_LOCATOR_SELECT_LABEL_FORM, "checkbox", "Music");

        // Current Address
        String DYNAMIC_LOCATOR_TEXT_AREA_FORM = "//textarea[@placeholder='%s']";
        String currentAddressXPath = String.format(DYNAMIC_LOCATOR_TEXT_AREA_FORM,"Current Address");
    }


}
