package com.testek.study.lesson22.common;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHooks {
    @Before
    public  static void beforeTest(){

    }

    @After
    public static void afterTest(){
        if(DriverManager.getWebDriver() != null){
            DriverManager.quit();
        }
    }
}
