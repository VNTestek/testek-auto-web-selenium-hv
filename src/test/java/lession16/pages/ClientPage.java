package lession16.pages;


import lession16.common.BasePage;
import lession16.common.LogType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

public class ClientPage extends BasePage {
    Logger logger = Logger.getLogger(ClientPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    public ClientPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        // Lặp đi lặp lại việc khởi tạo WebWaitDriver -> Đưa ra BasePage
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Verify the client dashboard
     */
    public void verifyClientDashboard() {
        logger.info("verifyClientDashboard");
        String nameEleXPath = "//div[contains(@class ,'card-body')]//span[normalize-space()='%s']";
        String titleELeXPath = "//div[contains(@class ,'card-body')]//strong[normalize-space()='%s']";

        // Verify total clients
        String totalClientXPath = String.format(nameEleXPath, "Total clients");
        WebElement totalClient = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(totalClientXPath)));
        Assert.assertTrue(Objects.nonNull(totalClient), "Verify Total Client");
        addReportInfo(LogType.VERIFY, "Verify Total Client");


        // Verify total contacts
        String totalContactsXPath = String.format(nameEleXPath, "Total contacts");
        WebElement totalContacts = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(totalContactsXPath)));
        Assert.assertTrue(Objects.nonNull(totalContacts), "Verify Total contacts");
        addReportInfo(LogType.VERIFY, "Verify Total contacts");

        // Verify Clients has unpaid invoices
        String clientUnPaidXPath = String.format(titleELeXPath, "Clients has unpaid invoices");
        WebElement clientUnPaid = mWebDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(clientUnPaidXPath)));
        Assert.assertTrue(Objects.nonNull(clientUnPaid), "Verify Clients has unpaid invoices");
        addReportInfo(LogType.VERIFY, "Verify Clients has unpaid invoices");
    }

    /**
     * @param data
     */
    public void createNewClient(HashMap<String, String> data) {
        // Click to [Add Client]
        clickAddClientButton();

        // Verify the popup
        String popupXPath = "//div[@id='ajaxModal']//h4";
        WebElement element = waitForElementVisible(By.xpath(popupXPath));
        Assert.assertTrue(element.isDisplayed(), "The popup shown");

        // Input info
        // Check radio
        String radFormXPath = "//label[text()='%s']//preceding-sibling::input";
        WebElement rad = findElement(By.xpath(String.format(radFormXPath, data.get("type"))));
        if (!rad.isSelected()) rad.click();

        inputTextToTextBoxWithPlaceHolder("Company name", data.get("companyName"));
        inputTextToTextBoxWithPlaceHolder("Address", data.get("address"));
        inputTextToTextBoxWithPlaceHolder("City", data.get("city"));
        inputTextToTextBoxWithPlaceHolder("State", data.get("state"));
        inputTextToTextBoxWithPlaceHolder("Zip", data.get("zip"));
        inputTextToTextBoxWithPlaceHolder("Country", data.get("country"));
        inputTextToTextBoxWithPlaceHolder("Phone", data.get("phone"));
        inputTextToTextBoxWithPlaceHolder("Website", data.get("website"));
        scrollToEndOfPage(findElement(By.xpath("//form/div[contains(@class,'modal-body')]")));

        inputTextToTextBoxWithPlaceHolder("VAT Number", data.get("vatNumber"));
        inputTextToTextBoxWithPlaceHolder("GST Number", data.get("gstNumber"));

        inputTextTo(By.id("currency_symbol"), data.get("currencySymbol"));

        String chkPayment = "//label[normalize-space()='Disable online payment']//following-sibling::div/input";
        WebElement chkEle = findElement(By.xpath(chkPayment));
        if (!chkEle.isSelected() && data.get("disableOnlinePayment").equals("true")) {
            clickElement(chkEle);       // Thuc hien tick
        } else if (chkEle.isSelected() && data.get("disableOnlinePayment").equals("false")) {
            clickElement(chkEle);       // Thuc hien untick
        }
        // Verify Data
    }

    /**
     * Click to [Add Client button]
     */
    private void clickAddClientButton() {
        String addXPath = "//a[@title='Add client']";
        clickElement(By.xpath(addXPath));
    }

    /**
     * Input text to textbox with placeholder params
     */
    private void inputTextToTextBoxWithPlaceHolder(String placeHolder, String value, String... xpaths) {
        String DYNAMIC_PLACEHOLDER_FORM = "//div[@id='ajaxModal']//*[@placeholder='%s']";
        if (xpaths.length > 0)
            DYNAMIC_PLACEHOLDER_FORM = xpaths[0];

        By txtByObj = By.xpath(String.format(DYNAMIC_PLACEHOLDER_FORM, placeHolder));
        inputTextTo(txtByObj, value);

    }
}
