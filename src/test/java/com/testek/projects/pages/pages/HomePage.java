package com.testek.projects.pages.pages;

import com.testek.consts.FrameConst;
import com.testek.driver.DriverManager;
import com.testek.projects.common.BasePage;
import com.testek.projects.pages.PageManagement;
import com.testek.projects.pages.objects.HomePageObjects;
import com.testek.projects.pages.objects.LoginObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class HomePage extends BasePage {

    private final HomePageObjects homePageObjects;
    private final LoginObjects loginObjects;

    public HomePage() {
        webDriver = DriverManager.getDriver();
        PageFactory.initElements(webDriver, this);

        homePageObjects = HomePageObjects.getInstance();
        loginObjects = LoginObjects.getInstance();
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

    public HomePage accessAddOrder() {
        HomePageObjects homePageObj = homePageObjects.clickIconAdd()
                        .clickMnuOrder();
        return PageManagement.gotoHomePage();
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

}
