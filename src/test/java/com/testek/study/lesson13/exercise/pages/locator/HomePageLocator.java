package com.testek.study.lesson13.exercise.pages.locator;

import lombok.Getter;

public class HomePageLocator extends BaseLocator{
        @Getter
        public static HomePageLocator instance = new HomePageLocator();

        private HomePageLocator() {
        }


        String lblHeader = "//div[@id='about-me']/h2";
}
