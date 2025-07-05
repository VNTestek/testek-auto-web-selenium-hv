package com.testek.projects.pages.locator;

import lombok.Getter;
@Getter
public class SupplierLocator extends BaseLocator{
    @Getter
    public static SupplierLocator instance = new SupplierLocator();

    private SupplierLocator() {
    }

    String title = "//div/div[text()='Thêm nhà cung cấp']";

    String txtSupName = "ID|form_item_supName";
    String txtSupPhone = "ID|form_item_supPhone";
    String txtSupContact = "ID|form_item_supContactName";
    String txtSupCountry = "ID|form_item_supCountry";
    String txtSupCity = "ID|form_item_supCity";
    String txtSupAddress = "ID|form_item_supAddress";
    String txtSupPostalCode = "ID|form_item_supPostalCode";
    String btnAddMore = "//span[normalize-space()='Thêm nhiều hơn']";

    String txtSupId = "//input[@placeholder='Mã nhà cung cấp']";
    String textAreaError = "//textarea[@data-v-0367fd5e='']";

    String popUpAddSupSuccess = "//span[normalize-space()='Thêm nhà cung cấp thành công']";

    String mnuSupplier = "//div[@data-v-1ac808eb='' and text()='Nhà cung cấp']";
    String keyword = "//input[@class='ant-input css-16pw25h h-8']";
    String btnSearch = "//button[@testek='btn-search']";

    String searchName = "//tr[starts-with(@class,'ant-table-row')]/td[1]";
    String searchAddress = "//tr[starts-with(@class,'ant-table-row')]/td[2]";
    String searchPhone = "//tr[starts-with(@class,'ant-table-row')]/td[3]";
    String searchContact = "//tr[starts-with(@class,'ant-table-row')]/td[4]";
    String searchCity = "//tr[starts-with(@class,'ant-table-row')]/td[5]";
    String searchCountry = "//tr[starts-with(@class,'ant-table-row')]/td[6]";
    String searchPostalCode = "//tr[starts-with(@class,'ant-table-row')]/td[7]";

}
