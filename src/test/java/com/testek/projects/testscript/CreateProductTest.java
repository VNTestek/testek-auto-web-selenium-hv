package com.testek.projects.testscript;

import com.testek.annotations.FrameAnnotation;
import com.testek.consts.AuthorType;
import com.testek.consts.FrameConst;
import com.testek.projects.common.TestBase;
import com.testek.projects.dataprovider.testek.CreateProductProvider;
import com.testek.projects.model.CreateProductModel;
import com.testek.projects.page.pages.HomePage;
import com.testek.projects.page.pages.ProductPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateProductTest extends TestBase {
    ProductPage productPage;
    HomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        homePage = new HomePage();
        homePage.verifyHomePage();
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
                .fillProductForm(createProductModel)
                .clickAddPrd();

        // Verify the login successfully
        productPage.verifyPopupSuccessDisplay();        // Verify popup success hien thi
    }

}
