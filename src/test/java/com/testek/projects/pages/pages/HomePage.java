package com.testek.projects.pages.pages;

import com.testek.consts.FrameConst;
import com.testek.driver.DriverManager;
import com.testek.projects.common.BasePage;
import com.testek.projects.pages.PageManagement;
import com.testek.projects.pages.objects.HomePageObjects;
import com.testek.projects.pages.objects.LoginObjects;
import com.testek.projects.pages.objects.SupplierListObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage {

    private final HomePageObjects homePageObjects;
//    private final LoginObjects loginObjects;
    private final SupplierListObjects supplierListObjects;

    public HomePage() {
        webDriver = DriverManager.getDriver();
        PageFactory.initElements(webDriver, this);

        homePageObjects = HomePageObjects.getInstance();
//        loginObjects = LoginObjects.getInstance();
        supplierListObjects = SupplierListObjects.getInstance();
    }

    //***************** Init WebElement Object *****************/

    public void verifyHomePage() {
        waitForElementVisible(homePageObjects.findHeader());
        verifyElementTextEqual(homePageObjects.findHeader(), "TESTEK - KIỂM THỬ THỰC CHIẾN");
    }

    public void verifyLoginSuccess() {
        WebElement iconUserEle = homePageObjects.findIconUser();
        assertTrueCondition(iconUserEle, iconUserEle.isDisplayed(), FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "Login Failed");
    }

    public OrderPage accessAddOrder() {
        return homePageObjects.clickIconAdd()
                        .clickMnuOrder();
    }

    public void verifyAddOrder() {
        homePageObjects.verifyAddOrderPage();
    }

//    public HomePage login(String userName, String password) {
//        LoginObjects loginObjects1 = loginObjects.inputUserName(userName);
//        LoginObjects loginObjects2 = loginObjects1.inputPassword(password);
//        HomePage homePage = loginObjects2.clickLoginButton();


//        HomePage homePage = loginObjects.inputUserName(userName)
//                .inputPassword(password)
//                .clickLoginButton();

        // Verify the home page after login
//        homePage.verifyHomePage();
//        return homePage;
//    }

    //BÀI 14:
    //Truy cập add button > Lựa chọn Nhà cung cấp
    public CreateSupplierPage accessCreateSupplierPage() {
        return homePageObjects.clickIconAdd()
                .clickOptSupplier();
    }

    //Truy cập add button > Lựa chọn khach hang
    public CreateCustomerPage accessCreateCustomerPage() {
        return homePageObjects.clickIconAdd()
               .clickOptCus();
    }

    // Truy cập menu “Nhà cung cấp” > Tìm kiếm trong danh sách và verify
    public SupplierListPage accessSupplierListPage() {
        supplierListObjects.clickMnuSup();
        return PageManagement.gotoSupplierListPage();
    }
}
