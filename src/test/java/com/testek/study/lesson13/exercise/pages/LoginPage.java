package com.testek.study.lesson13.exercise.pages;

import com.testek.driver.DriverManager;
import com.testek.projects.common.BasePage;
import com.testek.study.lesson13.exercise.objects.LoginObjects;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    private final LoginObjects loginObjects;

    public LoginPage() {
        webDriver = DriverManager.getDriver();
        PageFactory.initElements(webDriver, this);

        loginObjects = LoginObjects.getInstance();
    }

    public HomePage login(String userName, String password) {
        HomePage homePage = loginObjects.inputUserName(userName)
                .inputPassword(password)
                .clickLoginButton();
        return homePage;
    }
}
