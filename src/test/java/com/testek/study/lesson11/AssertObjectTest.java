package com.testek.study.lesson11;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AssertObjectTest {

    @Test
    public void testAssertObject() {
        String actResult = "Hello";
        String expResult = "Hello";

        // Assert that obj1 and obj2 are not the same object
        Assert.assertTrue(actResult.equals(expResult), "actResult and expResult: assertTrue");

        // Assert that obj1 and obj2 are not the same object
        Assert.assertNotSame(actResult, expResult, "actResult and expResult: assertNotSame");

        // Assert that obj1 and obj2 are the same object
        Assert.assertSame(actResult, expResult, "actResult and expResult: assertSame");

        Assert.assertNotEquals(actResult, expResult, "actResult and expResult: assertNotEquals");

        Assert.assertFalse(actResult.equals(expResult), "actResult and expResult: assertFalse");

    }
}
