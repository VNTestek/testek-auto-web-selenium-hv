package com.testek.projects.page;

import com.testek.driver.DriverManager;
import com.testek.projects.page.pages.HomePage;
import com.testek.projects.page.pages.LoginPage;

import static com.testek.consts.FrameConst.ProjectConfig;

/**
 * Page management
 */
public class PageManagement {

    /* Access to the web page */
    public static LoginPage accessWebPage() {
        DriverManager.getDriver().get(ProjectConfig.APP_URL);
        return new LoginPage();
    }


    /* Go to the home page */
    public static HomePage gotoHomePage() {
        return new HomePage();
    }

}
