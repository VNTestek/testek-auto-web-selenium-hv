package com.testek.projects.pages.pages;


import com.testek.projects.common.BasePage;
import com.testek.projects.pages.objects.LoginObjects;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LoginPage2 extends BasePage {

    private LoginObjects loginObjects;

    public LoginPage2() {
        // Init WebDriver
        this.loginObjects = new LoginObjects();
    }


    public HomePage login(String username, String password) {
        loginObjects.inputUserName(username);
        loginObjects.inputPassword(password);
        return loginObjects.clickLoginButton(); // => move to HomePage
//        return loginObjects.inputUserName(username)
//         this       .inputPassword(password)
//           this     .clickLoginButton();

    }

}
