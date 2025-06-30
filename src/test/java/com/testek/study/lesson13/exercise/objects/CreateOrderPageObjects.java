package com.testek.study.lesson13.exercise.objects;

import com.testek.projects.pages.objects.BaseObjects;
import com.testek.study.lesson13.exercise.PageManagement;
import com.testek.study.lesson13.exercise.locator.HomePageLocator;
import com.testek.study.lesson13.exercise.pages.HomePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;

public class CreateOrderPageObjects extends BaseObjects {
    private final HomePageLocator homePageLocator;

    @Getter
    public static CreateOrderPageObjects instance = new CreateOrderPageObjects();

    private CreateOrderPageObjects() {
        homePageLocator = HomePageLocator.getInstance();
    }

    WebElement findBtnAddEle() {
        return findWebElement(homePageLocator.getBtnAdd());
    }

    WebElement findBtnOrderEle() {
        return findWebElement(homePageLocator.getBtnAdd());
    }

    public HomePage clickAddButton() {
        clickTo(findBtnAddEle());
        return PageManagement.gotoHomePage();

    }

    public HomePage clickOrderButton() {
        clickTo(findBtnOrderEle());
        return PageManagement.gotoHomePage();
    }
}
