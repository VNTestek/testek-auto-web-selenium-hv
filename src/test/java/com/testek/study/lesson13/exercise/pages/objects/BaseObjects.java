package com.testek.study.lesson13.exercise.pages.objects;

import com.testek.driver.DriverManager;
import com.testek.projects.common.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class BaseObjects extends BasePage {

    protected WebDriver webDriver;

    public BaseObjects() {
        this.webDriver = DriverManager.getDriver();
    }
}
