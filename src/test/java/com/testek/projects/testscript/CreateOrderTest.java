package com.testek.projects.testscript;

import com.testek.annotations.FrameAnnotation;
import com.testek.consts.AuthorType;
import com.testek.consts.FrameConst;
import com.testek.driver.BrowserFactory;
import com.testek.driver.DriverManager;
import com.testek.projects.common.TestBase;
import com.testek.projects.dataprovider.model.CreateProductModel;
import com.testek.projects.dataprovider.providers.CreateProductProvider;
import com.testek.projects.pages.pages.HomePage;
import com.testek.projects.pages.pages.LoginPage;
import com.testek.projects.pages.pages.OrderPage;
import com.testek.projects.pages.pages.ProductPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.testek.consts.FrameConst.ExecuteConfig.EXE_BROWSER;

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
        orderPage = homePage.gotoOrderPage(); // Access to Order page
    }

    @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.Vincent}, reviewer = {AuthorType.Vincent})
    @Test(description = "Verify creating a new order")
    public void TK_CreateOrder_001_Valid() {
        orderPage.clickIconAdd();
        orderPage.clickMnuOrder();

        orderPage.verifyOrderPageDisplay(); // Verify order page hien thi

        orderPage.clickToCreateOrder()      // Click button Create Product
                .fillOrderInfo()
                .clickToCreateOrder();

        // Verify the login successfully
        orderPage.verifyOrderCreation();        // Verify popup success hien thi
    }
}
