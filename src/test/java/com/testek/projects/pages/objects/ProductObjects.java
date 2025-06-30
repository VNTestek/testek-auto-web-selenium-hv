package com.testek.projects.pages.objects;

import com.testek.consts.FrameConst.FailureHandling;
import com.testek.projects.pages.locator.ProductLocator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class ProductObjects extends BaseObjects {

    @Getter
    public static ProductObjects instance = new ProductObjects();

    private final ProductLocator productLocator;

    private ProductObjects() {
        productLocator = ProductLocator.getInstance();
    }

    public WebElement findBtnCreateProduct() {
        return findWebElement(productLocator.getBtnCreateProduct());
    }

    public WebElement findCategoryForm() {
        return findWebElement(productLocator.getFormItemCategory());
    }

    public WebElement findSupplierForm() {
        return findWebElement(productLocator.getFormItemSuppler());
    }

    public WebElement findProductCode() {
        return findWebElement(productLocator.getEdtProductCode());
    }

    public WebElement findProductName() {
        return findWebElement(productLocator.getEdtProductName());
    }

    public WebElement findProductPrice() {
        return findWebElement(productLocator.getEdtProductPrice());
    }

    public WebElement findProductDescription() {
        return findWebElement(productLocator.getEdtProductDes());
    }

    public WebElement findProductUnit() {
        return findWebElement(productLocator.getEdtProductUnit());
    }

    public WebElement findProductQuantity() {
        return findWebElement(productLocator.getEdtProductQuantity());
    }

    public WebElement findBtnAddProduct() {
        return findWebElement(productLocator.getBtnAddProduct());
    }

    public WebElement findMsgCreateProduct() {
        return findWebElement(productLocator.getPopUpAddProductResult());
    }

    public WebElement findProductIdRes() {
        return findWebElement(productLocator.getTxtProductIdResult());
    }

    public WebElement findProductResult() {
        return findWebElement(productLocator.getTxtAreaResult());
    }

    public WebElement findIconAdd() {
        return findWebElement(productLocator.getIconAdd());
    }

    public WebElement findMnuProduct() {
        return findWebElement(productLocator.getMnuProduct());
    }


    public ProductObjects inputProductCode(String productCode) {
        this.inputText(findProductCode(), "Product Code", productCode);
        return this;
    }

    public ProductObjects inputProductName(String productName) {
        this.inputText(findProductName(), "Product Name", productName);
        return this;
    }

    public ProductObjects inputProductPrice(String productPrice) {
        this.inputText(findProductPrice(), "Product Price", productPrice);
        return this;
    }

    public ProductObjects inputProductDescription(String productDescription) {
        this.inputText(findProductDescription(), "Product Description", productDescription);
        return this;
    }

    public ProductObjects inputProductQuantity(String productQuantity) {
        this.inputText(findProductQuantity(), "Product Quantity", productQuantity);
        return this;
    }

    public ProductObjects clickCreateProductButton() {
        clickTo(findBtnCreateProduct(), "Create Product Button");
        return this;
    }

    public ProductObjects inputProductUnit(String productUnit) {
        this.inputText(findProductUnit(), "Product Unit", productUnit);
        return this;
    }

    public ProductObjects clickAddProductButton() {
        clickTo(findBtnAddProduct(), "Add Product Button");
        return this;
    }

    public ProductObjects clickIconAddProduct() {
        clickTo(findIconAdd(), "Icon Add");
        return this;
    }

    public ProductObjects clickMenuProduct() {
        clickTo(findMnuProduct(), "Menu Product");
        return this;
    }

    public void verifySuccessMessageDisplayed() {
        WebElement lblMessage = findMsgCreateProduct();
        assertTrueCondition(lblMessage, lblMessage.isDisplayed(), FailureHandling.CONTINUE_ON_FAILURE, "Verify the success message is displayed after adding a product");
        verifyElementTextEqual(lblMessage, getLanguageValue("ProductCreateSuccessMessage"));
    }


    /**
     * Get the row content element by type
     *
     * @param type : Type of product (e.g., "Category", "Supplier")
     * @return : WebElement representing the row content for the specified type
     */
    public WebElement findDropContent(String type) {
        return waitForElementVisible(By.xpath(String.format(productLocator.getLblRowContent(), type)));
    }

    /* Select category product */
    public ProductObjects selectCategory(String expRowContent) {
        return selectDropdownContent(findCategoryForm(), expRowContent, "Category");
    }

    public ProductObjects selectSupplier(String expRowContent) {
        return selectDropdownContent(findSupplierForm(), expRowContent, "Supplier");
    }

    /**
     * Select the dropdown content based on the provided element and expected row content.
     *
     * @param element       : WebElement representing the dropdown element
     * @param expRowContent : Expected content of the row to select
     * @param title         : Title for the dropdown selection action, used for logging
     * @return : ProductObjects instance for method chaining
     */
    private ProductObjects selectDropdownContent(WebElement element, String expRowContent, String title) {
        clickElementViaJs(element, "Select " + title);
        inputText(element, title, expRowContent);

        clickTo(findDropContent(expRowContent), "Select " + title + " Type");
        return this;
    }


}
