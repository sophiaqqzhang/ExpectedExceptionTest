package com.exception.ExpectedExceptionTest;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JQueryTest {
    private WebDriver driver;

    private void setDriver() {
        System.setProperty("webdriver.gecko.driver", "c:\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    private void quit() {
        driver.quit();
    }

    private void jqueryTest() {
        driver.get("file:///C:\\codebase\\ExpectedExceptionTest\\jquery demo.html");
        String jq1 = "$('#dd').val('')";
        ((JavascriptExecutor) driver).executeScript(jq1);
    }

    public static void main(String[] args) {
        JQueryTest jq = new JQueryTest();
        jq.setDriver();
        jq.jqueryTest();
    }
}
