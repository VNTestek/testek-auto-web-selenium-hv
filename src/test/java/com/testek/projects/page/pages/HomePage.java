package com.testek.projects.page.pages;

import com.testek.driver.DriverManager;
import com.testek.projects.common.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage {

    public HomePage() {
        webDriver = DriverManager.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    //***************** Init WebElement Object *****************/
    //region Init WebElement Object

    @FindBy(xpath = "//div[@id='about-me']/h2")
    WebElement aboutMeEle;


    //endregion

    //***************** Init WebElement Object *****************/

    //region Basic Functions

    /* Enter the username */


    //endregion

    //***************** Integration Functions *****************/
    //region Integration Functions

    //endregion

    //***************** Verify *****************/
    //region Verify

    public void verifyHomePage() {
        waitForElementVisible(aboutMeEle);
        verifyElementTextEqual(aboutMeEle, "TESTEK - KIỂM THỬ THỰC CHIẾN");
    }

    //endregion
}
