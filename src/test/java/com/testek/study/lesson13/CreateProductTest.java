package com.testek.study.lesson13;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

@Slf4j
public class CreateProductTest extends TestBase {

    @Test
    public void createProduct() {
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

        // Go to Product page
        String navProductXPath = "//div[@testek='the-navbar']//div[text()='Sản phẩm']";
        WebElement navProductEle = waitForElementVisible(By.xpath(navProductXPath));
        clickTo(navProductEle, "Product Navigation");

        // Verify Product page
        WebElement btnCreateProductEle = waitForElementVisible(By.xpath("//button[@testek='btn-add']"));
        assertTrueCondition(btnCreateProductEle.isDisplayed(), "Verify Product page");

        // Click Create Product button
        clickTo(btnCreateProductEle, "Create Product Button");

        WebElement formItemCategoryEle = waitForElementVisible(By.id("form_item_category"));
        selectDropdownContent(formItemCategoryEle, "Điện thoại", "Category");

        WebElement formItemSupplierEle = waitForElementVisible(By.id("form_item_supplier"));
        selectDropdownContent(formItemSupplierEle, "LG VietNam", "Supplier");

        inputText(waitForElementVisible(By.id("form_item_code")), "Product Code", "AUTO_VINCENT_PRODUCT_"+ System.currentTimeMillis());
        inputText(waitForElementVisible(By.id("form_item_name")), "Product Name", "Test Product");
        inputText(waitForElementVisible(By.id("form_item_unit")), "Product Unit", "Cái");
        inputText(waitForElementVisible(By.id("form_item_description")), "Product Description", "This is a test product");
        inputText(waitForElementVisible(By.id("form_item_price")), "Product Price", "1000000");
        inputText(waitForElementVisible(By.id("form_item_quantity")), "Product Quantity", "10");

        clickTo(waitForElementVisible(By.xpath("//button[@testek='btn-add-more']")), "Add Product Button");

        // Verify the result
        String popUpAddProductResult = "//div[contains(@class,'ant-message-success')]";
        WebElement lblMessage = waitForElementVisible(By.xpath(popUpAddProductResult));
        assertTrueCondition(lblMessage.isDisplayed(), "Verify the success message is displayed after adding a product");

        verifyElementTextEqual(lblMessage, "Thêm sản phẩm thành công");

        // TODO: Verify other information: Product Details
    }


}
