package com.testek.study.lesson11;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;

@Slf4j
public class TestNGLifeCycle {

    @Test
    public void testNGLifeCycle() {
        log.info("TestNG Life Cycle : Test");
    }

    @BeforeSuite
    public void beforeSuite() {
        log.info("Before Suite method");
    }

    @AfterSuite
    public void afterSuite() {
        log.info("After Suite method");
    }

    @BeforeTest
    public void beforeTest() {
        log.info("Before Test method");
    }

    @AfterTest
    public void afterTest() {
        log.info("After Test method");
    }

    @BeforeClass
    public void beforeClass() {
        log.info("Before Class method");
    }


    @AfterClass
    public void afterClass() {
        log.info("After Class method");
    }

    @BeforeMethod
    public void beforeMethod() {
        log.info("Before Method");
        log.info("Testing method: method 1");
    }

    @AfterMethod
    public void afterMethod() {
        log.info("After Method");
        log.info("Testing method: method 1 - complete");
    }

}
