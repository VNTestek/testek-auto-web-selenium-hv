package com.testek.projects.pages.locator;

import lombok.Getter;

@Getter
public class OrderLocator {
    @Getter
    public static OrderLocator instance = new OrderLocator();

    private OrderLocator() {
    }

    ;

    /* Create Order */
    String txtCustomerId = "ID|form_item_customerId";
    String txtPhoneId = "ID|form_item_phoneNum";
    String txtShipperPhoneId = "ID|form_item_shippingPhoneNum";
    String txtEmailId = "ID|form_item_email";
    String txtAddressId = "ID|form_item_shipAddress";
    String chkInvoiceAddressXPath = "//input[@type = 'checkbox']/ancestor::label";
    String txtEmployeeId = "ID|form_item_employeeId";

    String btnAddXPath = "//button[@testek = 'btn-add']";
    String txtOrderCodeXPath = "//input[@placeholder = 'Mã đơn hàng']";
    String txtErrorMessageXPath = "//div[normalize-space() = 'Error message' and contains(@class, 'w-full')]//textarea";


}
