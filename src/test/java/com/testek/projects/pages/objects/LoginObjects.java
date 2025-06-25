package com.testek.projects.pages.objects;

import com.testek.projects.pages.PageManagement;
import com.testek.projects.pages.locator.LoginLocator;
import com.testek.projects.pages.pages.HomePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class LoginObjects extends BaseObjects {

    @Getter
    public static LoginObjects instance = new LoginObjects();

    private final LoginLocator loginLocator;

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
