package com.testek.study.lesson13.exercise.pages.objects;

import com.testek.projects.pages.PageManagement;
import com.testek.projects.pages.pages.HomePage;
import com.testek.study.lesson13.exercise.pages.locator.LoginLocator;
import lombok.Getter;
import org.checkerframework.common.reflection.qual.GetClass;
import org.openqa.selenium.WebElement;

@Getter
public class LoginObjects extends BaseObjects{

    @Getter
    public static LoginObjects instance = new LoginObjects();

    private final LoginLocator loginLocator;

    private LoginObjects() {loginLocator = LoginLocator.getInstance();}


    WebElement findUserNameEle() {return findWebElement(loginLocator.getTxtUserName());}

    WebElement findPasswordEle() {
        return findWebElement(loginLocator.getTxtPassword());
    }

    WebElement findLoginButtonEle() {
        return findWebElement(loginLocator.getBtnLogin());
    }

    public LoginObjects inputUserName(String userName) {
        this.inputText(findUserNameEle(), getLanguageValue("UserName"), userName);
        return this;
    }

    public LoginObjects inputPassword(String password) {
        this.inputText(findPasswordEle(), "Password", password);
        return this;
    }

    public HomePage clickLoginButton() {
        clickTo(findLoginButtonEle(), "Login Button");
        return PageManagement.gotoHomePage();
    }
}
