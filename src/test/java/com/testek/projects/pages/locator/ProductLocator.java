package com.testek.projects.pages.locator;


import lombok.Getter;

@Getter
public class ProductLocator extends BaseLocator {
    @Getter
    public static ProductLocator instance = new ProductLocator();

    private ProductLocator() {
    }


    /* Create Product */
    String btnCreateProduct = "//button[@testek='btn-add']";
    String formItemCategory = "ID|form_item_category";
    String formItemSuppler = "ID|form_item_supplier";
    String edtProductCode = "ID|form_item_code";
    String edtProductName = "ID|form_item_name";
    String edtProductUnit = "ID|form_item_unit";
    String edtProductDes = "ID|form_item_description";
    String edtProductPrice = "ID|form_item_price";
    String edtProductQuantity = "ID|form_item_quantity";
    String txtAreaResult = "//textarea[@type='text']";
    String txtProductIdResult="//input[@placeholder='Mã sản phẩm']";
    String btnAddProduct = "//button[@testek='btn-add']";
    String btnAddMoreProduct = "//button[@testek='btn-add-more']";

    String popUpAddProductResult= "//div[contains(@class,'ant-message-success')]";

    /* Product List */
    String lblRowContent = "//td[text()='%s']";
    String btnAddProductPage = "//button[@testek='btn-add']";
}
