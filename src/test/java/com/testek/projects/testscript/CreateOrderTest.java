package com.testek.projects.testscript;

import com.testek.annotations.FrameAnnotation;
import com.testek.consts.AuthorType;
import com.testek.consts.FrameConst;
import com.testek.projects.common.TestBase;
import com.testek.projects.dataprovider.model.CreateProductModel;
import com.testek.projects.dataprovider.providers.CreateProductProvider;
import com.testek.projects.pages.pages.HomePage;
import com.testek.projects.pages.pages.OrderPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CreateOrderTest extends TestBase {
    OrderPage orderPage;
    HomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        super.beforeClass();
        homePage = new HomePage();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        orderPage = homePage.gotoOrderPage();
    }

    @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.HuongPham}, reviewer = {AuthorType.HuongPham})
    @Test(description = "Verify creating a new order", dataProvider = "TK_CreateProduct_001_Valid", dataProviderClass = CreateProductProvider.class)
    public void TK_CreateProduct_001_Valid(CreateProductModel createProductModel) {
        orderPage.verifyOrderPageDisplay(); // Verify order page hien thi
        orderPage.clickToCreateOrder()      // Click button Create Order
                .fillOrderInfo()
                .clickToCreateOrder();

        orderPage.verifyOrderCreation();

    }
}
