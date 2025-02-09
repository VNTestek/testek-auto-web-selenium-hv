package com.testek.projects.testscript;

import com.testek.annotations.FrameAnnotation;
import com.testek.consts.AuthorType;
import com.testek.consts.FrameConst;
import com.testek.projects.common.TestBase;
import com.testek.projects.model.LoginModel;
import com.testek.projects.page.pages.HomePage;
import com.testek.study.demo.pages.ProductPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class CreateProductTest extends TestBase {

    ProductPage productPage;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {

        HomePage homePage = new HomePage();
        productPage = homePage.gotoProductPage();
    }

    @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.Vincent}, reviewer = {AuthorType.Vincent})
    @Test(description = "Verify creating a new product")
    public void Testek_CreateProduct_001_Valid() throws InterruptedException {
        sleep(10000);
    }

}
