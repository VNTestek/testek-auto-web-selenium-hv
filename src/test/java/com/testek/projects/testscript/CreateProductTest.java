package com.testek.projects.testscript;

import com.testek.annotations.FrameAnnotation;
import com.testek.consts.AuthorType;
import com.testek.consts.FrameConst;
import com.testek.driver.DriverManager;
import com.testek.projects.common.TestBase;
import com.testek.projects.dataprovider.testek.CreateProductProvider;
import com.testek.projects.model.CreateProductModel;
import com.testek.projects.page.pages.HomePage;
import com.testek.projects.page.pages.ProductPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateProductTest extends TestBase {

    ProductPage productPage;
    HomePage homePage;
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        homePage = new HomePage();
        homePage.verifyHomePage();
        productPage = homePage.gotoProductPage();
    }

    @AfterMethod
    public void afterMethod() {
        DriverManager.quitDriver();
    }

    @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.Testek}, reviewer = {AuthorType.Vincent})
    @Test(description = "Verify creating a new product", dataProvider = "Testek_CreateProduct_001_Valid", dataProviderClass = CreateProductProvider.class)
    public void Testek_CreateProduct_001_Valid(CreateProductModel createProductModel) throws InterruptedException {
        productPage.verifyProductPageDisplay();
        productPage.clickToCreateProduct();
        productPage.fillForm(createProductModel);
        productPage.clickAddPrd();
        // Verify the login successfully
        productPage.verifyPopupSuccessDisplay();
    }

}
