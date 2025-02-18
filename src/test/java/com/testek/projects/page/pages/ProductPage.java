package com.testek.projects.page.pages;

import com.testek.consts.FrameConst;
import com.testek.projects.common.BasePage;
import com.testek.projects.model.CreateProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.testek.consts.FrameConst.ProjectConfig;
import static com.testek.utils.DateTimeUtils.getCurrentTimestamp;
import static com.testek.utils.RandomNumberUtils.getRandomInt;
import static com.testek.utils.RandomNumberUtils.getRandomPrice;

/**
 * Implement the functions of the Product page
 * {@link ProjectConfig#APP_URL} is the URL of the login page
 */
public class ProductPage extends BasePage {
    public ProductPage() {
        super();
        PageFactory.initElements(webDriver, this);
    }

    //***************** Init WebElement Object *****************/
    //region Init WebElement Object

    @FindBy(xpath = "//button[@testek='btn-add']")
    WebElement btnCreateProductEle;

    @FindBy(id = "form_item_category")
    WebElement formItemCategoryEle;

    @FindBy(id = "form_item_supplier")
    WebElement formItemSupplerEle;

    @FindBy(id = "form_item_code")
    WebElement edtProductCodeEle;

    @FindBy(id = "form_item_name")
    WebElement edtProductNameEle;

    @FindBy(id = "form_item_unit")
    WebElement edtProductUnitEle;

    @FindBy(id = "form_item_description")
    WebElement edtProductDesEle;

    @FindBy(id = "form_item_price")
    WebElement edtProductPriceEle;

    @FindBy(id = "form_item_quantity")
    WebElement edtProductQuantityEle;

    @FindBy(id = "//span[text()='Thêm sản phẩm thành công']")
    WebElement msgCreateSuccessEle;

    @FindBy(xpath = "//textarea[@type='text']")
    WebElement txtAreaResult;

    public WebElement getTypeInputEle(String type) {
        return  waitForElementVisible(By.xpath(String.format("//td[text()='%s']", type)));
    }
    //endregion

    //***************** Init WebElement Object *****************/

    //region Basic Functions

    /* Click btn create product */
    public ProductPage clickToCreateProduct() {
        clickElementViaJs(btnCreateProductEle, "Create Product");
        return this;
    }

    /* Select category product */
    public void selectCategory(String category) {
        clickElementViaJs(formItemCategoryEle, "Select Category");
        formItemCategoryEle.sendKeys(category);
        //sleepMillisecond(1000);
        clickElement(getTypeInputEle(category), "Select Category Type");
    }

    /* Select supplier product */
    public void selectSupplier(String supplier) {
        clickElementViaJs(formItemSupplerEle, "Select Suppler");
        formItemSupplerEle.sendKeys(supplier);
        //sleepMillisecond(1000);
        clickElement(getTypeInputEle(supplier), "Select Suppler Type");
    }

    /* Input product code */
    public void enterPrdCode(String preCode) {
        inputTextTo(edtProductCodeEle, "ProductCode", preCode + getCurrentTimestamp());
    }

    /* Input product name */
    public void enterPrdName(String preName) {
        inputTextTo(edtProductNameEle, "ProductName", preName + getCurrentTimestamp());
    }

    /* Input product unit */
    public void enterPrdUnit(String unit) {
        inputTextTo(edtProductUnitEle, "Unit", unit);
    }

    /* Input product description */
    public void enterPrdDes(String des) {
        inputTextTo(edtProductDesEle, "Description", des);
    }

    /* Input product price */
    public void enterPrdPrice(String price) {
        inputTextTo(edtProductPriceEle, "Price", price);
    }

    /* Input product quantity */
    public void enterPrdQuantity(String quantity) {
        inputTextTo(edtProductQuantityEle, "Quantity", quantity);
    }

    //endregion

    //***************** Integration Functions *****************/
    //region Integration Functions
    public ProductPage fillProductForm(CreateProductModel createProductModel) {
        waitForElementClickable(btnCreateProductEle);
        selectCategory(createProductModel.getCategory().getValue());
        selectSupplier(createProductModel.getSupplier().getValue());
        enterPrdCode(createProductModel.getCode().getValue());
        enterPrdUnit(createProductModel.getUnit().getValue());
        enterPrdName(createProductModel.getCategory().getValue() + " " + createProductModel.getSupplier().getValue());
        enterPrdDes(createProductModel.getCategory().getValue());
        enterPrdPrice(String.valueOf(getRandomPrice(200, 3000)));
        enterPrdQuantity(String.valueOf(getRandomInt(200, 3000)));
        return this;
    }

    public void clickAddPrd() {
        clickElement(btnCreateProductEle, "Btn Add Product");
    }
    //endregion

    //***************** Verify *****************/
    //region Verify
    public void verifyPopupSuccessDisplay() {
        verifyElementDisplayed(msgCreateSuccessEle);
    }

    public ProductPage verifyFormPage() {

        return this;
    }

    /* Verify the product page display */
    public void verifyProductPageDisplay() {
        assertEqualCondition(null, "Testek admin", webDriver.getTitle(),
                FrameConst.FailureHandling.STOP_ON_FAILURE, "Verify the page title");
    }
    //endregion

}
