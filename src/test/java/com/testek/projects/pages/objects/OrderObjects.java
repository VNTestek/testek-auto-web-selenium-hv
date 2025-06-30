package com.testek.projects.pages.objects;

import com.testek.projects.pages.locator.OrderLocator;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class OrderObjects extends BaseObjects {
    @Getter
    public static OrderObjects instance = new OrderObjects();

    private final OrderLocator orderLocator;

    private OrderObjects() {
        orderLocator = OrderLocator.getInstance();
    }

    public WebElement findCustomerEle() {
        return findWebElement(orderLocator.getTxtCustomerId());
    }

    public WebElement findPhoneEle() {
        return findWebElement(orderLocator.getTxtPhoneId());
    }

    public WebElement findShipperPhoneEle() {
        return findWebElement(orderLocator.getTxtShipperPhoneId());
    }

    public WebElement findEmailEle() {
        return findWebElement(orderLocator.getTxtEmailId());
    }

    public WebElement findAddressEle() {
        return findWebElement(orderLocator.getTxtAddressId());
    }

    public WebElement findInvoiceAddressEle() {
        return findWebElement(orderLocator.getChkInvoiceAddressXPath());
    }

    public WebElement findEmployeeEle() {
        return findWebElement(orderLocator.getTxtEmployeeId());
    }

    public WebElement findAddEle() {
        return findWebElement(orderLocator.getBtnAddXPath());
    }

    public WebElement findOrderCodeEle() {
        return findWebElement(orderLocator.getTxtOrderCodeXPath());
    }

    public WebElement findErrorMessageEle() {
        return findWebElement(orderLocator.getTxtErrorMessageXPath());
    }

    // Select Khách hàng
    public OrderObjects selectCustomer() {
        clickTo(findCustomerEle(), "Customer");
        String lblCustomerValueXPath = "(//div[@class = 'ant-select-item-option-content'])[2]";
        clickTo(findWebElement(lblCustomerValueXPath), "the first Customer");
        return this;
    }

    // Input phone number
    public OrderObjects inputPhone(String phone) {
        this.inputText(findPhoneEle(), "Phone", phone);
        return this;
    }

    // Input phone number of Shipper
    public OrderObjects inputShipperPhone(String phone) {
        this.inputText(findShipperPhoneEle(), "Phone", phone);
        return this;
    }

    // Input email
    public OrderObjects inputEmail(String email) {
        this.inputText(findEmailEle(), "Email", email);
        return this;
    }

    // Input address
    public OrderObjects inputAddress(String address) {
        this.inputText(findAddressEle(), "Address", address);
        return this;
    }

    // Select checkbox
    public OrderObjects selectCheckboxAddress() {
        clickTo(findInvoiceAddressEle(), "Checkbox Giống địa chỉ giao hàng");
        return this;
    }

    // Select Nhân viên
    public OrderObjects selectEmployee() {
        clickTo(findEmployeeEle(), "Employee");
        String lblEmployeeValueXPath = "(//div[contains(@class, 'dropdown-menu-employee')]//div[@class = 'ant-select-item-option-content'])[2]";
        clickTo(findWebElement(lblEmployeeValueXPath), "the first Employee");
        return this;
    }

    // Click Thêm nhiều hơn
    public OrderObjects clickAddButton() {
        clickTo(findAddEle(), "Add Order Button");
        return this;
    }

}
