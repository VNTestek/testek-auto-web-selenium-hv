package com.testek.projects.pages.locator;


import lombok.Getter;

@Getter
public class LoginLocator {
    @Getter
    public static LoginLocator instance = new LoginLocator();

    private LoginLocator() {}

    String txtUserName = "ID|normal_login_username";
    String txtPassword = "ID|normal_login_password";
    String btnLogin = "//button[@type='submit']";
    String btnForgot = "//div[normalize-space() = 'Quên mật khẩu?']";
    String btnRegister = "//span[normalize-space() = 'Đăng ký']";
}
