package com.testek.study.lesson13.exercise.pages;

import com.testek.driver.DriverManager;
import com.testek.projects.common.BasePage;
import com.testek.study.lesson13.exercise.objects.CreateOrderPageObjects;
import org.openqa.selenium.support.PageFactory;

public class CreateOrderPage extends BasePage {
    private final CreateOrderPageObjects createOrderPageObjects;

    public CreateOrderPage() {
        webDriver = DriverManager.getDriver();
        PageFactory.initElements(webDriver, this);

        createOrderPageObjects = CreateOrderPageObjects.getInstance();
    }
}
