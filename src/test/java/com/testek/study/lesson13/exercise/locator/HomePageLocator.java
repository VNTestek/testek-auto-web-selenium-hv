package com.testek.study.lesson13.exercise.locator;

import lombok.Getter;

@Getter
public class HomePageLocator {
    @Getter
    public static HomePageLocator instance = new HomePageLocator();

    private HomePageLocator() {
    }

    String lblHeader = "//div[@id='about-me']/h2";
    String btnAdd = "//div[@testek='add']";
    String btnOrder = "//li[@role='menuitem' and .//div[text()='Đơn hàng']]";
}
