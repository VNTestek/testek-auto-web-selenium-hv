package com.testek.projects.pages.pages;

import com.testek.driver.DriverManager;
import com.testek.projects.common.BasePage;
import com.testek.projects.pages.objects.HomePageObjects;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage {

    private final HomePageObjects homePageObjects;

    public HomePage() {
        webDriver = DriverManager.getDriver();
        PageFactory.initElements(webDriver, this);

        homePageObjects = HomePageObjects.getInstance();
    }

    //***************** Init WebElement Object *****************/

    public void verifyHomePage() {
        waitForElementVisible(homePageObjects.findHeader());
        verifyElementTextEqual(homePageObjects.findHeader(), "TESTEK - KIỂM THỬ THỰC CHIẾN");
    }

}
