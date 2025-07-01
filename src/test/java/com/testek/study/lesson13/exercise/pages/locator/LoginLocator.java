package com.testek.study.lesson13.exercise.pages.locator;

import lombok.Getter;

@Getter
public class LoginLocator {
    @Getter
    public static LoginLocator instance = new LoginLocator();

    private LoginLocator() {}

    String txtUserName = "ID|normal_login_username";
    String txtPassword = "ID|normal_login_password";
    String btnLogin = "//button[@type='submit']";
}
