package com.testek.projects.pages.pages;

import com.testek.driver.ChromeBrowserDriver;
import com.testek.projects.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage2 extends BasePage {

    public HomePage2() {
        // Constructor logic if needed
    }

    public CreatePage chooseCreateNewOrder() {
        // Logic to navigate to CreatePage

        return new CreatePage();
    }

}
