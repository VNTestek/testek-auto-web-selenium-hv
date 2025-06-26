package com.testek.study.lesson13.exercise;

import static com.testek.controller.WebUI.pressENTER;
import static com.testek.controller.WebUI.pressKeyEvent;
import static com.testek.controller.WebUI.switchToWindowByHandle;
import static com.testek.controller.WebUI.waitFor;

import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateOrderTest extends TestBase {
    private static final String YOUR_NAME = "CreateOrderTest";

    @Test
    public void createOrder() {
        List<String> shipperPhoneNumbers = new ArrayList<>();
        // Login
        goToSpecificURL("https://testek.vn/lab/auto", "Testek Web Automation Lab");

        // Login
        WebElement edtLoginEle = waitForElementVisible(By.id("normal_login_username"));
        inputText(edtLoginEle, "testek.vn", "admin_com_role");
        WebElement edtPasswordEle = waitForElementVisible(By.id("normal_login_password"));
        inputText(edtPasswordEle, "testek.vn", "aA12345678@");
        WebElement btnLoginEle = waitForElementVisible(By.xpath("//button[@type='submit']"));
        clickTo(btnLoginEle, "Login Button");

        // Verify login success
        WebElement lblHeaderEle = waitForElementVisible(By.xpath("//div[@id='about-me']/h2"));
        assertTrueCondition(lblHeaderEle.isDisplayed(), "Verify login success");

        // Go to Order page
        String navOrderXPath = "//div[@testek='the-navbar']//div[text()='Đơn hàng']";
        WebElement navOrderEle = waitForElementVisible(By.xpath(navOrderXPath));
        clickTo(navOrderEle, "Order Navigation");

        // Verify Order page
        WebElement btnCreateProductEle = waitForElementVisible(By.xpath("//button[@testek='btn-add']"));
        assertTrueCondition(btnCreateProductEle.isDisplayed(), "Verify Product page");
        assertEqualCondition(btnCreateProductEle.getText(),"Thêm đơn hàng", "Verify Create Order button text");

        // Click Create Order button
        clickElementViaJs(btnCreateProductEle, "Create Order Button");

        // Fill in Order form
        WebElement formItemCustomerIdEle = waitForElementVisible(By.id("form_item_customerId"));
        selectDropdownContent(formItemCustomerIdEle, "LINH CHI", "Khách hàng");

        String shipperPhoneNumberFirst = "01"+System.currentTimeMillis();
        inputText(waitForElementVisible(By.id("form_item_phoneNum")), "Phone Number", shipperPhoneNumberFirst);
        inputText(waitForElementVisible(By.id("form_item_shippingPhoneNum")), "Shipper Phone Number", "0901234567");
        shipperPhoneNumbers.add(shipperPhoneNumberFirst);
        inputText(waitForElementVisible(By.id("form_item_email")), "Email", String.format("%s_%s@gmail.com", YOUR_NAME, System.currentTimeMillis()));
        inputText(waitForElementVisible(By.id("form_item_shipAddress")), "Ship Address", String.format("Auto_%s_%s", YOUR_NAME, System.currentTimeMillis()));

        // click Checkbox for billing address
        String chkBillAddressXpath ="//input[@class='ant-checkbox-input']";
        WebElement chkBillingAddressEle = mWebDriver.findElement(By.xpath(chkBillAddressXpath));
        clickElementViaJs(chkBillingAddressEle, "Billing Address Checkbox");

        WebElement formItemEmployeeIdEle = waitForElementVisible(By.id("form_item_employeeId"));
        selectDropdownContent(formItemEmployeeIdEle, "Nguyễn Nhân Viên", "Nhân viên");

        WebElement btnAddMoreEle = waitForElementVisible(By.xpath("//button[@testek='btn-add-more']"));
        clickTo(waitForElementClickable(btnAddMoreEle), "Add Order More Button");

        // Verify the result
        String popUpAddProductResult = "//div[contains(@class,'ant-message-success')]";
        WebElement lblMessage = waitForElementVisible(By.xpath(popUpAddProductResult));
        assertTrueCondition(lblMessage.isDisplayed(), "Verify the success message is displayed after adding a Order");
        verifyElementTextEqual(lblMessage, "Thêm đơn hàng thành công");
        mWebDriverWait.until(ExpectedConditions.invisibilityOf(lblMessage));

        // Fill in Order form
        selectDropdownContent(formItemCustomerIdEle, "QUANG ANH", "Khách hàng");
        String shipperPhoneNumberSecond = "02"+System.currentTimeMillis();
        inputText(waitForElementVisible(By.id("form_item_phoneNum")), "Phone Number", shipperPhoneNumberSecond);
        inputText(waitForElementVisible(By.id("form_item_shippingPhoneNum")), "Shipper Phone Number","0907654321" );
        shipperPhoneNumbers.add(shipperPhoneNumberSecond);
        inputText(waitForElementVisible(By.id("form_item_email")), "Email", String.format("%s_%s@gmail.com", YOUR_NAME, System.currentTimeMillis()));
        selectDropdownContent(formItemEmployeeIdEle, "Mai Quỳnh Chi", "Nhân viên");

        // Click Add Order More button
        clickTo(btnAddMoreEle, "Add Order More Button");
        waitForSpinnerInvisible();

        // Verify the result
        mWebDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(popUpAddProductResult)));

        // Go to Order page
        clickTo(navOrderEle, "Order Navigation");
        for (String shipperPhoneNumber:shipperPhoneNumbers){
            // Verify the Order is added to the list
            String chbSeachXpath = "//div[contains(@class,'search-text')]/child::input[@testek='search-input']";
            WebElement chbSearchEle = waitForElementVisible(By.xpath(chbSeachXpath));
            inputText(chbSearchEle, "Search Order", shipperPhoneNumber);

            //click on button Search
            String btnSearchXpath = "//button[@testek='btn-search']";
            WebElement btnSearchEle = waitForElementVisible(By.xpath(btnSearchXpath));
            clickTo(btnSearchEle, "Button Search");

            // Wait for loading icon to disappear
            waitForSpinnerInvisible();
            String searchResultXPath = "//tr[starts-with(@class, 'ant-table-row')]";
            mWebDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(searchResultXPath)));

            // Verify search results
            List<WebElement> searchResultEles = mWebDriver.findElements(By.xpath(searchResultXPath));
            System.out.println("Number of search results: " + searchResultEles.size());

            // Check each row contains the shipper phone number
            for (int i = 0; i < searchResultEles.size(); i++) {
                // Get the shipper phone number columns
                System.out.println("[ROUND]= "+i);
                WebElement searchResultEle = searchResultEles.get(i);
                WebElement tdMobileColEle = searchResultEle.findElement(By.xpath(".//td[2]"));
                String tdMobileColEleText = tdMobileColEle.getText();

                boolean isMatch = tdMobileColEleText.equals(shipperPhoneNumber);

                System.out.println("Row " + (i + 1) + " SDT shipper: " + tdMobileColEleText +" => Match: with"+shipperPhoneNumber+" is " + isMatch);
                assertTrueCondition(isMatch, "Result return in row " + (i + 1) + "does not equal "+shipperPhoneNumber+" in SDT");
            }
        }
    }
}
