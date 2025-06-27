package com.testek.projects.testscript;

import com.testek.annotations.FrameAnnotation;
import com.testek.consts.AuthorType;
import com.testek.consts.FrameConst;
import com.testek.projects.common.TestBase;
import com.testek.projects.dataprovider.model.CreateProductModel;
import com.testek.projects.dataprovider.providers.CreateProductProvider;
import com.testek.projects.pages.pages.HomePage;
import com.testek.projects.pages.pages.ProductPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateProductTest extends TestBase {
    ProductPage productPage;
    HomePage homePage;

    @BeforeClass (alwaysRun = true)
    public void beforeClass() {
        super.beforeClass();
        homePage = new HomePage();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        productPage = homePage.gotoProductPage();   // Access to Product page
    }

    @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.Vincent}, reviewer = {AuthorType.Vincent})
    @Test(description = "Verify creating a new product", dataProvider = "TK_CreateProduct_001_Valid", dataProviderClass = CreateProductProvider.class)
    public void TK_CreateProduct_001_Valid(CreateProductModel createProductModel) {
        productPage.verifyProductPageDisplay(); // Verify product page hien thi

        productPage.clickToCreateProduct()      // Click button Create Product
                .fillProductInfo(createProductModel)
                .clickToCreateProduct();

        // Verify the login successfully
        productPage.verifyProductCreation();        // Verify popup success hien thi
    }

    @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.Vincent}, reviewer = {AuthorType.Vincent})
    @Test(description = "Verify creating a new product")
    public void TK_CreateProduct_002_Invalid() {
        productPage.verifyProductPageDisplay(); // Verify product page hien thi

        productPage.clickToCreateProduct()      // Click button Create Product
                .fillProductInfo()
                .clickToCreateProduct();

        // Verify the login successfully
        productPage.verifyProductCreation();        // Verify popup success hien thi
    }


}
