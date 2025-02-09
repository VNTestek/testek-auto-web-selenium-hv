package com.testek.study.lesson11.demo.tests;

import com.testek.study.lesson11.demo.pages.DashboardPage;
import com.testek.study.lesson11.demo.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClientTest {
    private WebDriver mWebDriver;
    private String baseURL = "https://rise.fairsketch.com";
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeClass
    public void beforeClass() {
        // Init WebDriver
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--max-window-size");
        chromeOptions.addArguments("--remote-allow-origins=*");
        mWebDriver = new ChromeDriver(chromeOptions);

        loginPage = new LoginPage(mWebDriver);
        dashboardPage = loginPage.login("username", "password");
    }

    @BeforeMethod
    public void beforeMethod() {
        // Go to home Page
        mWebDriver.get("HOME PAGGE");
    }

    @AfterClass
    public void afterClass() {
        mWebDriver.quit();
    }

    @Test
    public void Clients_001_VerifyPage() {
        // Start point: HomePage
        dashboardPage.clickToClients();

        //ClientPage  clientPage = dashboardPage.clickToClients();
        //clientPage.verify();

        /**
         * 1. Tim kiem cac thong tin cho login page
         * 2. Tao moi Client Page va khai bao cac action nhu Dashboard page
         * 3. Tao lien ket giua Dashboard Page va Client Page
         * 4. Chay test case nay
         */
    }



}
