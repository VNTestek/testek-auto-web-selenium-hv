package com.testek.study.demo.pages;

public class LoginPage {
    String userNameXPath="//input[@id='username']";
    String passwordXPath="//input[@id='password']";
    String loginButtonXPath="//button[@id='login']";

    public void login(String userName, String password) {
        // Input username
        // Input password
        // Click login button
    }

    private void inputTextTo(String xpath, String text) {
        // Input text to the element
    }

}
