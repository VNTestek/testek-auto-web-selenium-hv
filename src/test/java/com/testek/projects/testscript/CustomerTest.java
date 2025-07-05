package com.testek.projects.testscript;

import com.testek.annotations.FrameAnnotation;
import com.testek.consts.AuthorType;
import com.testek.consts.FrameConst;
import com.testek.projects.common.TestBase;
import com.testek.projects.pages.pages.CreateCustomerPage;
import com.testek.projects.pages.pages.CreateSupplierPage;
import com.testek.projects.pages.pages.HomePage;
import com.testek.projects.pages.pages.SupplierListPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerTest extends TestBase {
    CreateCustomerPage createCustomerPage;
    HomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() { //Truy cập web + thực hiện login + chuyển đến trang homepage
        super.beforeClass();
        homePage = new HomePage();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
//        Truy cập add button > Lựa chọn Nhà cung cấp
        createCustomerPage = homePage.accessCreateCustomerPage();
    }

    @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.Vincent}, reviewer = {AuthorType.Vincent})
    @Test(description = "Verify creating a new customer")
    public void TK_CreateOrder_001_Valid() {
        //Kiểm tra truy cập khach hang đúng không ?
        createCustomerPage.verifyAccessCreateCustomer();

        //Nhập thông tin cho Nhà cung cấp
        createCustomerPage.fillCusInfo()
                .clickAddCus();

        //Verify kết quả sau khi nhấn button [Thêm]
        createCustomerPage.verifyCusCreation();
    }
}
