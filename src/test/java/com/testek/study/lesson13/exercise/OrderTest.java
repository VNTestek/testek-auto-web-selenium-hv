package com.testek.study.lesson13.exercise;

import com.testek.annotations.FrameAnnotation;
import com.testek.consts.AuthorType;
import com.testek.consts.FrameConst;
import com.testek.projects.common.TestBase;
import com.testek.projects.dataprovider.model.CreateOrderModel;
import com.testek.projects.pages.pages.HomePage;
import com.testek.study.lesson13.exercise.pages.pages.OrderPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderTest extends TestBase {
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

    @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.Vincent}, reviewer = {AuthorType.Vincent})
    @Test(description = "Verify the login function")
    public void Testek_CreateOrder_001_Valid(CreateOrderModel createOrderModel) {
        orderPage.fillOrderInfo(createOrderModel);

    }
}
