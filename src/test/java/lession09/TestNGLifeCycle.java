package lession09;

import org.testng.annotations.*;

public class TestNGLifeCycle {

    @Test
    public void testNGLifeCycle() {
        System.out.println("TestNG Life Cycle : Test");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite method");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite method");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test method");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test method");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class method");
    }


    @AfterClass
    public void afterClass() {
        System.out.println("After Class method");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
        System.out.println("Testing method: method 1");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method");
        System.out.println("Testing method: method 1 - complete");
    }

}
