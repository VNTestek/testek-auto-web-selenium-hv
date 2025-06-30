package com.testek.projects.pages.locator;


import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class OrderLocator extends BaseLocator {
    @Getter
    public static OrderLocator instance = new OrderLocator();

    private OrderLocator() {
    }

    /* Create Order */
    String formItemCustomer = "ID|form_item_customerId";
    String formItemEmployee = "ID|form_item_employeeId";
    String edtShippingPhone = "ID|form_item_shippingPhoneNum";
    String edtPhoneNum = "ID|form_item_phoneNum";
    String edtEmail = "ID|form_item_email";
    String edtAddress = "ID|form_item_shipAddress";
    String edtAddressOrder = "ID|form_item_invoiceAddress";
    String txtAreaResult = "//textarea[@data-v-0367fd5e='']";
    String txtOrderIdResult="//input[@placeholder='Mã đơn hàng']";
    String btnAdd = "//button[@testek='btn-add']";
    String btnAddMore = "//button[@testek='btn-add-more']";
    String popUpAddOrderSuccess = "//span[normalize-space()='Thêm đơn hàng thành công']";
//    String popUpAddProductResult= "//div[contains(@class,'ant-message-success')]";

    /* Order List */
    String lblRowContent = "//td[text()='%s']";
}
