package com.testek.projects.page.pages;

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
 * Implement the functions of the login page
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
    WebElement btnCreateProduct;

    @FindBy(id = "form_item_category")
    WebElement formItemCategory;

    @FindBy(id = "form_item_supplier")
    WebElement formItemSuppler;

    @FindBy(id = "form_item_code")
    WebElement prdCode;

    @FindBy(id = "form_item_name")
    WebElement prdName;

    @FindBy(id = "form_item_unit")
    WebElement prdUnit;

    @FindBy(id = "form_item_description")
    WebElement prdDescription;

    @FindBy(id = "form_item_price")
    WebElement prdPrice;

    @FindBy(id = "form_item_quantity")
    WebElement prdQuantity;

    @FindBy(id = "//span[text()='Thêm sản phẩm thành công']")
    WebElement messageCreateSuccess;

    public WebElement getTypeInputEle(String type) {
        return webDriver.findElement(By.xpath(String.format("//td[text()='%s']", type)));
    }
    //endregion

    //***************** Init WebElement Object *****************/

    //region Basic Functions

    /* Click btn create product */
    public void clickToCreateProduct() {
        clickElementViaJs(btnCreateProduct, "Create Product");
    }

    /* Select category product */
    public void selectCategory(String category) {
        clickElementViaJs(formItemCategory, "Select Category");
        formItemCategory.sendKeys(category);
        sleepMillisecond(500);
        clickElement(getTypeInputEle(category), "Select Category Type");
    }

    /* Select supplier product */
    public void selectSupplier(String supplier) {
        clickElementViaJs(formItemSuppler, "Select Suppler");
        formItemSuppler.sendKeys(supplier);
        sleepMillisecond(500);
        clickElement(getTypeInputEle(supplier), "Select Suppler Type");
    }

    /* Input product code */
    public void enterPrdCode(String preCode) {
        inputTextTo(prdCode, "ProductCode", preCode + getCurrentTimestamp());
    }

    /* Input product name */
    public void enterPrdName(String preName) {
        inputTextTo(prdName, "ProductName", preName + getCurrentTimestamp());
    }

    /* Input product unit */
    public void enterPrdUnit(String unit) {
        inputTextTo(prdUnit, "Unit", unit);
    }

    /* Input product description */
    public void enterPrdDes(String des) {
        inputTextTo(prdDescription, "Description", des);
    }

    /* Input product price */
    public void enterPrdPrice(String price) {
        inputTextTo(prdPrice, "Price", price);
    }

    /* Input product quantity */
    public void enterPrdQuantity(String quantity) {
        inputTextTo(prdQuantity, "Quantity", quantity);
    }

    //endregion

    //***************** Integration Functions *****************/
    //region Integration Functions
    public ProductPage fillForm(CreateProductModel createProductModel) {
        waitForElementClickable(btnCreateProduct);
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

    public ProductPage clickCreatePrd() {
        clickElement(btnCreateProduct, "Btn Add Product");
        return this;
    }
    //endregion

    //***************** Verify *****************/
    //region Verify
    public void verifyPopupSuccessDisplay() {
        verifyElementDisplayed(messageCreateSuccess);
    }

    public ProductPage verifyFormPage() {

        return this;
    }

    //endregion

}
