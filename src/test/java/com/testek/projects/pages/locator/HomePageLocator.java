package com.testek.projects.pages.locator;

import lombok.Getter;
@Getter
public class HomePageLocator extends BaseLocator{
    @Getter
    public static HomePageLocator instance = new HomePageLocator();

    private HomePageLocator() {
    }

    String lblHeader = "//div[@id='about-me']/h2";
    String iconUser = "//div[@testek='icon-user']";
    String iconAdd = "//div[@class='cursor-pointer icon-24 icon-insert']";
    String mnuOrder = "//span/div[text()='Đơn hàng']";
    String titleAddOrder = "//div[@class='font-bold text-xl mb-4']";

    //Bai 14:
    String optSupplier = "//span/div[text()='Nhà cung cấp']";
    String optCustomer = "//span/div[text()='Khách hàng']";
}
