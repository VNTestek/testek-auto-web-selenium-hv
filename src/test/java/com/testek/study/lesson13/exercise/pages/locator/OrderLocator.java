package com.testek.study.lesson13.exercise.pages.locator;

import lombok.Getter;

@Getter
public class OrderLocator extends BaseLocator{
    @Getter
    public static OrderLocator instance = new OrderLocator();

    private OrderLocator(){
    }

    String formCustomer = "ID||form_item_customerId";
    String txtPhoneNumber = "ID||form_item_phoneNum";
    String txtShipperPhoneNumber = "ID||form_item_shippingPhoneNum";
    String txtEmail = "ID||form_item_email";
    String txtShipAddress = "ID||form_item_shipAddress";
    String chkBillAddress = "//input[@class='ant-checkbox-input']";
    String formEmployee = "ID||form_item_employeeId";
    String btnAddMore = "//button[@testek='btn-add-more']";
    String popUpAddOrderResult = "//div[contains(@class,'ant-message-success')]";
    String lblRowContent = "//td[text()='%s']";
}
