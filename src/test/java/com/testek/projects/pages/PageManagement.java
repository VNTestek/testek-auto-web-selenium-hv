package com.testek.projects.pages;

import com.testek.driver.DriverManager;
import com.testek.projects.pages.pages.*;

import static com.testek.consts.FrameConst.AppConfig.APP_DOMAIN;


/**
 * Page management
 */
public class PageManagement {

    /* Access to the web page */
    public static LoginPage accessWebPage() {
        DriverManager.getDriver().get(APP_DOMAIN);
        return new LoginPage();
    }


    /* Go to the home page */
    public static HomePage gotoHomePage() {
        return new HomePage();
    }

    /* Go to the order page */
    public static OrderPage gotoOrderPage() {
        return new OrderPage();
    }

    /* Go to the create supplier page */
    public static CreateSupplierPage gotoCreateSupplierPage() {
        return new CreateSupplierPage();
    }

    /* Go to the supplier list page */
    public static SupplierListPage gotoSupplierListPage() {
        return new SupplierListPage();
    }

    /* Go to the create supplier page */
    public static CreateCustomerPage gotoCreateCustomerPage() {
        return new CreateCustomerPage();
    }
}
