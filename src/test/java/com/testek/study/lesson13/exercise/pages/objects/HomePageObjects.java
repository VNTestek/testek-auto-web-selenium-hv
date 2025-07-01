package com.testek.study.lesson13.exercise.pages.objects;

import com.testek.projects.pages.locator.HomePageLocator;
import lombok.Getter;
import org.openqa.selenium.WebElement;

public class HomePageObjects extends BaseObjects{
    @Getter
    public static HomePageObjects instance = new HomePageObjects();

    private final HomePageLocator homePageLocator;

    private HomePageObjects() {
        homePageLocator = HomePageLocator.getInstance();
    }

    public WebElement findHeader() {
        return findWebElement(homePageLocator.getLblHeader());
    }
}
