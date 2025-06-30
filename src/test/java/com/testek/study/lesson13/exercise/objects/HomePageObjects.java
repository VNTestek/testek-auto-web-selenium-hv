package com.testek.study.lesson13.exercise.objects;

import com.testek.projects.pages.objects.BaseObjects;
import com.testek.study.lesson13.exercise.PageManagement;
import com.testek.study.lesson13.exercise.locator.HomePageLocator;
import com.testek.study.lesson13.exercise.pages.CreateOrderPage;
import lombok.Getter;
import org.openqa.selenium.WebElement;

public class HomePageObjects extends BaseObjects {
    private final HomePageLocator homePageLocator;

    @Getter
    public static HomePageObjects instance = new HomePageObjects();

    private HomePageObjects() {
        homePageLocator = HomePageLocator.getInstance();
    }

    public WebElement findHeader() {
        return findWebElement(homePageLocator.getLblHeader());
    }

    WebElement findBtnAddEle() {
        return findWebElement(homePageLocator.getBtnAdd());
    }

    WebElement findBtnOrderEle() {
        return findWebElement(homePageLocator.getBtnAdd());
    }

    public HomePageObjects clickAddButton() {
        clickTo(findBtnAddEle(), "Add Button");
        return this;
    }

    public CreateOrderPage clickOrderButton() {
        clickTo(findBtnOrderEle(), "Order Button");
        return PageManagement.gotoCreateOrderPage();
    }
}
