package com.testek.projects.pages.locator;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class HomePageLocator extends BaseLocator{
    @Getter
    public static HomePageLocator instance = new HomePageLocator();

    private HomePageLocator() {
    }

    String FORM_CLASS_XPATH = "//div[@class='%s']";

    String lblHeader = "//div[@id='about-me']/h2";
    String iconUser = "//div[@testek='icon-user']";
    String iconAdd = String.format(FORM_CLASS_XPATH, "cursor-pointer icon-24 icon-insert");
    String mnuOrder = "//span[@class='ant-dropdown-menu-title-content']/div[normalize-space()='Đơn hàng']";
    String titleAddOrder = String.format(FORM_CLASS_XPATH, "font-bold text-xl mb-4");
}
