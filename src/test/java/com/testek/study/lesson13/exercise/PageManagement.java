package com.testek.study.lesson13.exercise;

import com.testek.driver.DriverManager;
import com.testek.study.lesson13.exercise.pages.CreateOrderPage;
import com.testek.study.lesson13.exercise.pages.HomePage;
import com.testek.study.lesson13.exercise.pages.LoginPage;

public class PageManagement {
    /* Access to the web page */
    public static LoginPage accessWebPage() {
        DriverManager.getDriver().get("https://testek.vn/lab/auto/login");
        return new LoginPage();
    }


    /* Go to the home page */
    public static HomePage gotoHomePage() {
        return new HomePage();
    }

    public static CreateOrderPage gotoCreateOrderPage() {
        return new CreateOrderPage();
    }

}
