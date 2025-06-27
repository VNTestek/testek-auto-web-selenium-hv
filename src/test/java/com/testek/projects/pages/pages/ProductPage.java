package com.testek.projects.pages.pages;

import com.testek.consts.FrameConst.FailureHandling;
import com.testek.projects.common.BasePage;
import com.testek.projects.dataprovider.model.CreateProductModel;
import com.testek.projects.pages.objects.ProductObjects;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

import static com.testek.utils.FakerUtils.getRandomPrice;
import static com.testek.utils.FakerUtils.getRandomQuantity;

/**
 * Implement the functions of the Product page
 */
public class ProductPage extends BasePage {
    private final ProductObjects productObjects;

    public ProductPage() {
        super();
        PageFactory.initElements(webDriver, this);
        productObjects = ProductObjects.getInstance();
    }

    public ProductPage clickToCreateProduct() {
        productObjects.clickAddProductButton();
        return this;
    }

    //region Integration Functions
    public ProductPage fillProductInfo(CreateProductModel model) {

        /* Update the random Price & Quantity */
        long currentTimeMillis = System.nanoTime();
        model.getPrice().setValue(String.valueOf(getRandomPrice()));
        model.getQuantity().setValue(String.valueOf(getRandomQuantity()));
        model.getCode().setValue(model.getCode().getValue() + currentTimeMillis);
        model.getName().setValue(model.getName().getValue() + currentTimeMillis);
        model.getDescription().setValue(model.getDescription().getValue() + currentTimeMillis);

        productObjects.selectCategory(model.getCategory().getValue())
                .selectSupplier(model.getSupplier().getValue())
                .inputProductCode(model.getCode().getValue())
                .inputProductUnit(model.getUnit().getValue())
                .inputProductName(model.getName().getValue())
                .inputProductDescription(model.getDescription().getValue())
                .inputProductPrice(model.getPrice().getValue())
                .inputProductQuantity(model.getPrice().getValue());
        return this;
    }

    /**
     * Fill the product information with random data
     */
    public ProductPage fillProductInfo() {
        /* Update the random Price & Quantity */
        long currentTimeMillis = System.nanoTime();
        String category = "Điện thoại";
        String supplier = "Nikon VietNam";
        String code = "AUTO_PRODUCT_VINCENT_" + currentTimeMillis;
        String name = "Samsung Galaxy S25 - " + currentTimeMillis;
        String description = "Smartphone Samsung Galaxy S25 - 128GB, 8GB RAM, 5G, Camera 108MP, Pin 5000mAh - " + currentTimeMillis;
        String unit = "Cái";
        String quantity = "10";
        String price = "15000000.0";

        productObjects.selectCategory(category)
                .selectSupplier(supplier)
                .inputProductCode(code)
                .inputProductUnit(unit)
                .inputProductName(name)
                .inputProductDescription(description)
                .inputProductPrice(price)
                .inputProductQuantity(quantity);
        return this;
    }


    /* Verify the product page display */
    public void verifyProductPageDisplay() {
        WebElement element = waitForElementVisible(productObjects.findBtnAddProductPage());
        assertTrueCondition(element, Objects.nonNull(element), FailureHandling.CONTINUE_ON_FAILURE, "Verify the Product page is displayed");
    }

    /**
     * Verify the product creation
     */
    public void verifyProductCreation() {
        productObjects.verifySuccessMessageDisplayed();

        WebElement productIdEle = productObjects.findProductIdRes();
        assertTrueCondition(productIdEle, Objects.nonNull(productIdEle), FailureHandling.CONTINUE_ON_FAILURE, "Verify the product ID is displayed after creating a product");

        WebElement productResultEle = productObjects.findProductResult();
        String resultText = getValueOfElement(productResultEle);
        JSONObject productJson = new JSONObject(resultText);
        assertEqualCondition(productResultEle, productJson.getJSONObject("data").get("id"), getValueOfElement(productIdEle),
                FailureHandling.CONTINUE_ON_FAILURE, "Verify the product code matches the input value");

        // TODO: Verify the product info
    }

    //endregion
}
