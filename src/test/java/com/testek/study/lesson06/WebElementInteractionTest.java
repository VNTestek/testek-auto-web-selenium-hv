package com.testek.study.lesson06;

import com.testek.study.common.Menu;
import lombok.extern.slf4j.Slf4j;
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

import static java.lang.Thread.sleep;

/**
 * Class tuong tac voi cac thanh phan co ban trong website
 */
@Slf4j
public class WebElementInteractionTest {
    private WebDriver mWebDriver;

    @BeforeMethod
    public void beforeTestMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        mWebDriver = new ChromeDriver(chromeOptions);
        mWebDriver.manage().window().maximize(); // Set the window size to maximum
    }

    @AfterMethod
    public void afterMethod() {
        if (mWebDriver != null) {
            mWebDriver.quit();
            mWebDriver = null;
        }
    }

    /**
     * Interact with Text Box
     */
    @Test
    public void testTextBoxInteraction() {
        gotoTestWebsite(Menu.ELEMENTS, Menu.TEXT_BOX);

        String txtEmailXPath = "//input[@test-id='textbox-form-input-email']";
        WebElement txtEmailEle = mWebDriver.findElement(By.xpath(txtEmailXPath));
        txtEmailEle.clear();
        txtEmailEle.sendKeys("info@testek.edu.vn");

        // Get the value of the text box
        String emailVal = txtEmailEle.getAttribute("value");
        log.info("Your email: {}", emailVal);

        // Get the id's value of the text box
        String idElement = txtEmailEle.getAttribute("id");
        log.info("Id of Email Element: {}", idElement);

        waitForDebug(10000);
        // Clear the text box; You should clear all the text box before entering new data
        txtEmailEle.clear();
    }

    /**
     * Tương tác với Button
     */
    @Test
    public void testButtonInteraction() {
        gotoTestWebsite(Menu.ELEMENTS, Menu.TEXT_BOX);

        String txtEmailXPath = "//input[@test-id='textbox-form-input-email']";
        WebElement txtEmailEle = mWebDriver.findElement(By.xpath(txtEmailXPath));
        txtEmailEle.clear();
        txtEmailEle.sendKeys("info@testek.edu.vn");
        log.info("Your email: {}", txtEmailEle.getAttribute("value"));

        //  Click button
        String btnSendXPath = "//button[@test-id='textbox-form-submit']";
        WebElement btnSendEle = mWebDriver.findElement(By.xpath(btnSendXPath));

        // Create a wait driver
        WebDriverWait webDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));

        // Wait for your element to ready click
        webDriverWait.until(ExpectedConditions.elementToBeClickable(btnSendEle));
        btnSendEle.click();
        log.info("Click [Send] successfully...");

        // Note: There are 3 click methods: Click via JavaScript, Click via Action, Click via WebElement
    }

    /**
     * Tuong tac voi Check Box
     */
    @Test
    public void testCheckBoxInteraction() {
        gotoTestWebsite(Menu.FORM, Menu.STUDENT_FORM);

        String DYNAMIC_CHECKBOX_FORMAT = "//input[@type='checkbox' and @test-id='%s']";
        String DYNAMIC_CHECKBOX_LABEL_FORMAT = "//label[@test-id='%s']";

        String chkSportXPath = String.format(DYNAMIC_CHECKBOX_FORMAT, "input-sports");
        WebElement chkSportEle = mWebDriver.findElement(By.xpath(chkSportXPath));
        chkSportEle.click();
        log.info("Sport Checked: {}", chkSportEle.isSelected());

        // Click checkbox via label
        String chkSportLabelXPath = String.format(DYNAMIC_CHECKBOX_LABEL_FORMAT, "label-sports");
        WebElement chkSportLabelEle = mWebDriver.findElement(By.xpath(chkSportLabelXPath));
        chkSportLabelEle.click();
        log.info("Sport Checked: {}", chkSportLabelEle.isSelected());

        boolean isChecked = chkSportLabelEle.isSelected();
        log.info("Sport Checked: {}", isChecked);

        chkSportLabelEle.click();
        isChecked = chkSportLabelEle.isSelected();
        log.info("Sport Checked: {}", isChecked);

        // FIXME - Khi tuong tac voi checkbox can chu y van de gi ? Hoi hoc vien
        // TODO - Thuc hanh: Hoc vien lam tuong tu voi Music
    }

    /**
     * Tuong tac voi Link
     */
    @Test
    public void testLinkInteraction() {
        gotoTestWebsite(Menu.ELEMENTS, Menu.LINKS);

        // Find with XPath
        String lnkCourseXPath = "//a[@test-id='testek-course']";
        WebElement lnkCourseEle = mWebDriver.findElement(By.xpath(lnkCourseXPath));
        String linkXPathHref = lnkCourseEle.getAttribute("href");
        log.info("Href - Directly: {}", linkXPathHref);

        // Find with Link Text
        WebElement lnkCourseTextEle = mWebDriver.findElement(By.linkText("Testek - Khoá học"));
        String linkStr = lnkCourseTextEle.getAttribute("href");
        log.info("Href - LinkText: {}", linkStr);
    }

    @Test
    public void testDropdownInteraction() {
        gotoTestWebsite(Menu.WIDGETS, Menu.SELECTED_MENU);

        // Find the dropdown element
        WebElement drdCityEle = mWebDriver.findElement(By.id("singleSelect"));
        Select select = new Select(drdCityEle);

        // Select the option with the text "Hải Phòng"
        log.info("Select : Hải Phòng");
        select.selectByVisibleText("Hải Phòng");
        waitForDebug(5000);

        WebElement valSelectedEle = mWebDriver.findElement(By.xpath("//select[@test-id='singleSelect']"));
        // Select the option with the value option4 ~ "Đà Nẵng"
        log.info("Select via Value: option4 ~ Đà Nẵng");
        select.selectByValue("option4");
        waitForDebug(5000);

        // Select the option at index 3
        log.info("Select via index 3 ~ Hải Phòng");
        select.selectByIndex(3);
        waitForDebug(5000);
    }


    /**
     * Go to Test Website
     */
    private void gotoTestWebsite(Menu parent, Menu subMenu) {
        String url = "https://testek.vn/lab/auto/web-elements/";
        mWebDriver.get(url);

        String XPATH_MENU_FORMAT = "//div[@test-id='%s']";

        // Access: Form > [Student Form]
        WebElement mnuElementEle = mWebDriver.findElement(By.xpath(String.format(XPATH_MENU_FORMAT, parent.getName())));
        mnuElementEle.click();

        WebElement mnuEle = mWebDriver.findElement(By.xpath(String.format(XPATH_MENU_FORMAT, subMenu.getName())));
        mnuEle.click();

        waitForDebug(5000);
    }

    /**
     * Sleep for debugging
     *
     * @param milliseconds : number of milliseconds
     */
    private void waitForDebug(long milliseconds) {
        try {
            sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
