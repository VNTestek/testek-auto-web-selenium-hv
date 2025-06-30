package com.testek.projects.pages.objects;

import com.testek.consts.FrameConst;
import com.testek.projects.pages.PageManagement;
import com.testek.projects.pages.locator.HomePageLocator;
import com.testek.projects.pages.pages.HomePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class HomePageObjects extends BaseObjects {
    @Getter
    public static HomePageObjects instance = new HomePageObjects();

    private final HomePageLocator homePageLocator;

    private HomePageObjects() {
        homePageLocator = HomePageLocator.getInstance();
    }

    public WebElement findHeader() {
        return findWebElement(homePageLocator.getLblHeader());
    }

    public WebElement findIconUser() {
        return findWebElement(homePageLocator.getIconUser());
    }

    public WebElement findIconAdd() {
        return findWebElement(homePageLocator.getIconAdd());
    }

    public WebElement findMnuOrder() {
        return findWebElement(homePageLocator.getMnuOrder());
    }
    public WebElement findTitleAddOrder() {
        return findWebElement(homePageLocator.getTitleAddOrder());
    }

    /**
     * Click the login button on the login page
     */
    public HomePageObjects clickIconAdd() {
        clickTo(findIconAdd(), "Icon add");
        return this;
    }

    public HomePageObjects clickMnuOrder() {
        clickTo(findMnuOrder(), "Menu Order");
        return this;
    }

    public void verifyAddOrderPage() {
        WebElement titleAddOrderEle = findTitleAddOrder();
        assertEqualCondition(titleAddOrderEle, titleAddOrderEle.getText().trim(), "Thêm đơn hàng", FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "Add New Order Page not match");
        verifyElementTextEqual(titleAddOrderEle, getLanguageValue("OrderAddNewTitle"));
    }


}
