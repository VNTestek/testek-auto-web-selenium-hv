package com.testek.projects.testscript;

import com.testek.annotations.FrameAnnotation;
import com.testek.consts.AuthorType;
import com.testek.consts.FrameConst;
import com.testek.projects.common.TestBase;
import com.testek.projects.pages.pages.CreateSupplierPage;
import com.testek.projects.pages.pages.HomePage;
import com.testek.projects.pages.pages.SupplierListPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SupplierTest extends TestBase {
    CreateSupplierPage createSupplierPage;
    HomePage homePage;
    SupplierListPage supplierListPage;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() { //Truy cập web + thực hiện login + chuyển đến trang homepage
        super.beforeClass();
        homePage = new HomePage();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
//        Truy cập add button > Lựa chọn Nhà cung cấp
        createSupplierPage = homePage.accessCreateSupplierPage();
//        createSupplierPage = homePage.gotoCreateSupplierPage(); // Access to Create Supplier page
    }

    @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.Vincent}, reviewer = {AuthorType.Vincent})
    @Test(description = "Verify creating a new supplier")
    public void TK_CreateOrder_001_Valid() {
        //Kiểm tra truy cập Nhà cung cấp đúng không ?
        createSupplierPage.verifyAccessCreateSupplier();

        for (int i = 0; i < 2; i++) {
            //Nhập thông tin cho Nhà cung cấp
            createSupplierPage.fillSupInfo()
                    .clickAddMoreSup();

            //Verify kết quả sau khi nhấn button [Thêm nhiều hơn]
            createSupplierPage.verifySupCreation();

            //Clear data
            createSupplierPage.clearData();

//            waitForDebug(2000);
        }

        // Truy cập menu “Nhà cung cấp” > Tìm kiếm trong danh sách
        supplierListPage = homePage.accessSupplierListPage();
        supplierListPage.searchInfoSup();

        // Verify search result
    }
}
