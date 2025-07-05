package com.testek.projects.pages.locator;

import lombok.Getter;

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
    String iconAdd = "//div[@class='cursor-pointer icon-24 icon-insert']";
    String optionOrder = "//span[starts-with(@class,'ant-dropdown')]/div[normalize-space()='Đơn hàng']";

    String mnuOrder = "//div[@class='icon-24 icon-order-active mr-6']";
    String keywordXpath = "//input[@class='ant-input css-16pw25h h-8']";
    String btnSearchXpath = "//button[@testek='btn-search']";

    /* Order List */
    String lblRowContent = "//td[text()='%s']";

    /* Order Search Result */
    String searchResultAddress = "//tr[starts-with(@class,'ant-table-row')]/td[1]";
    String searchResultPhoneNum = "//tr[starts-with(@class,'ant-table-row')]/td[2]";
    String searchResultAddressOrder = "//tr[starts-with(@class,'ant-table-row')]/td[3]";
    String searchResultQuantity = "//tr[starts-with(@class,'ant-table-row')]/td[4]";
    String searchResultTotal = "//tr[starts-with(@class,'ant-table-row')]/td[5]";
}
