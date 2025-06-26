package com.testek.study.lesson13.exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class CreateOrderTest extends TestBase {

    @Test
    public void createOrder() {
        // Login
        goToSpecificURL("https://testek.vn/lab/auto", "Testek Web Automation Lab");

        // Login
        WebElement edtLoginEle = waitForElementVisible(By.id("normal_login_username"));
        inputText(edtLoginEle, "testek.vn", "admin_com_role");
        WebElement edtPasswordEle = waitForElementVisible(By.id("normal_login_password"));
        inputText(edtPasswordEle, "testek.vn", "aA12345678@");
        WebElement btnLoginEle = waitForElementVisible(By.xpath("//button[@type='submit']"));
        clickTo(btnLoginEle, "Login Button");

        // Verify login success
        WebElement lblHeaderEle = waitForElementVisible(By.xpath("//div[@id='about-me']/h2"));
        assertTrueCondition(lblHeaderEle.isDisplayed(), "Verify login success");

        // Go to Order page
    }

}
