package com.testek.study.lesson13.exercise.pages;

import com.testek.driver.DriverManager;
import com.testek.projects.common.BasePage;
import com.testek.study.lesson13.exercise.objects.HomePageObjects;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    private final HomePageObjects homePageObjects;

    public HomePage() {
        webDriver = DriverManager.getDriver();
        PageFactory.initElements(webDriver, this);

        homePageObjects = HomePageObjects.getInstance();
    }

    public void verifyHomePageLoaded() {
        waitForElementVisible(homePageObjects.findHeader());
    }

    public CreateOrderPage clickToCreateOrderButton() {
        CreateOrderPage createOrderPage = homePageObjects.clickAddButton()


                .clickOrderButton();
        return createOrderPage;
    }
}
