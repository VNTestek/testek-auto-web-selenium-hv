package com.testek.study.lesson13.pages.locator;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class LoginLocator {
    public static final String INPUT_USERNAME_XPATH = "//input[@id='normal_login_username']";
    public static final String INPUT_PASSWORD_XPATH = "//input[@id='normal_login_password']";
    public static final String BUTTON_LOGIN_XPATH = "//button[@type='submit']";

    public static final String LINK_FORGOT_PASSWORD_XPATH = "//div[normalize-space()='Quên mật khẩu?']";
    public static final String LINK_REGISTER_USER_XPATH = "//div[normalize-space()=' Đăng ký']";


}
