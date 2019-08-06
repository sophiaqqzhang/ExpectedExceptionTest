package com.exception.ExpectedExceptionTest;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestQQEmail {
    private QQMail qqMail = new QQMail();

    @BeforeMethod
    private void logOn() {
        qqMail.setDriver();
        qqMail.logOn();
    }

    @AfterMethod
    private void logOut() {
        qqMail.logOut();
        qqMail.quit();
    }

    @Test
    private void wr() {
        qqMail.writeANewLetter();
    }

    @Test(dependsOnMethods = {"wr"})
    private void check() {
        String expected = "木叶》for test1";
        Assert.assertTrue(qqMail.checkInbox().contains(expected));
    }
}
