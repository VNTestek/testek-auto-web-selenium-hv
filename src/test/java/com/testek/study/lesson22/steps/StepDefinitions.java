package com.testek.study.lesson22.steps;

import com.testek.study.lesson22.common.DriverManager;
import com.testek.study.lesson22.steps.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class StepDefinitions {
    private LoginPage loginPage;
    private String baseURL = "https://rise.fairsketch.com";

    @Given("Truy cập website")
    public void truy_cập_website() {
        WebDriver mWebDriver = new ChromeDriver(configChromeOption());
        DriverManager.setWebDriver(mWebDriver);

        loginPage = new LoginPage(mWebDriver);
        loginPage.gotoWebsite(baseURL);
    }
    @When("Login with {string} and {string}")
    public void login_with_admin_demo_com_and_rise_demo(String userName, String password) {
       loginPage.login(userName, password);
    }
    @Then("Login successfully. The dashboard is shown")
    public void login_successfully_the_dashboard_is_shown() {
        System.out.println("Login successfully");
    }


    public ChromeOptions configChromeOption() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        return chromeOptions;
    }
}
