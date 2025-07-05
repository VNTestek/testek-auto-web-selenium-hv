package com.testek.projects.pages.locator;

import lombok.Getter;

@Getter
public class CustomerLocator extends BaseLocator{
    @Getter
    public static CustomerLocator instance = new CustomerLocator();

    private CustomerLocator() {
    }

    String title = "//div/div[text()='Thêm khách hàng']";

    String txtName = "ID|form_item_name";
    String txtPhone = "ID|form_item_phoneNum";
    String txtEmail = "ID|form_item_email";
    String txtContact = "ID|form_item_contact";
    String txtCountry = "ID|form_item_country";
    String txtCity = "ID|form_item_city";
    String txtAddress = "ID|form_item_address";
    String txtPostalCode = "ID|form_item_postalCode";
    String btnAdd = "//span[normalize-space()='Thêm']";

    String txtCusId = "//input[@placeholder='Mã khách hàng']";
    String textAreaError = "//textarea[@data-v-0367fd5e='']";

    String popUpAddSupSuccess = "//span[normalize-space()='Thêm khách hàng thành công']";
}
