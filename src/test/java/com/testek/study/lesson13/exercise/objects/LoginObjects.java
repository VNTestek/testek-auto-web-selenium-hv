package com.testek.study.lesson13.exercise.objects;

import com.testek.projects.pages.objects.BaseObjects;
import com.testek.study.lesson13.exercise.PageManagement;
import com.testek.study.lesson13.exercise.locator.LoginLocator;
import com.testek.study.lesson13.exercise.pages.HomePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;

public class LoginObjects extends BaseObjects {
    private final LoginLocator loginLocator;

    @Getter
    public static LoginObjects instance = new LoginObjects();

    private LoginObjects() {
        loginLocator = LoginLocator.getInstance();
    }

    WebElement findUserNameEle() {
        return findWebElement(loginLocator.getTxtUserName());
    }

    WebElement findPasswordEle() {
        return findWebElement(loginLocator.getTxtPassword());
    }


    WebElement findLoginButtonEle() {
        return findWebElement(loginLocator.getBtnLogin());
    }

    /**
     * Input the username to the login page
     */
    public LoginObjects inputUserName(String userName) {
        this.inputText(findUserNameEle(), getLanguageValue("UserName"), userName);
        return this;
    }
    /**
     * Input the password to the login page
     */
    public LoginObjects inputPassword(String password) {
        this.inputText(findPasswordEle(), "Password", password);
        return this;
    }

    /**
     * Click the login button on the login page
     */
    public HomePage clickLoginButton() {
        clickTo(findLoginButtonEle(), "Login Button");
        return PageManagement.gotoHomePage();
    }
}
