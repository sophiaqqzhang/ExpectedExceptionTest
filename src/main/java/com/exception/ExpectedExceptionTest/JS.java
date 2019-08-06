package com.exception.ExpectedExceptionTest;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class JS {
    private static WebDriver driver;

    private void setDriver() {
        System.setProperty("webdriver.gecko.driver", "c:\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    private void quit() {
        driver.quit();
    }

    private void scrollTo() {
        setDriver();
        driver.get("https://www.baidu.com/");
        Dimension targetSize = new Dimension(800, 800);
        driver.manage().window().setSize(targetSize);
        driver.findElement(By.id("kw")).sendKeys("selenium");
        driver.findElement(By.id("su")).click();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String j_s = "window.scrollTo(800,1600);";
        jsExecutor.executeScript(j_s);
        quit();
    }

    private void textAreaInput() {//use js to input text in html document
        setDriver();
        driver.get("file:///C:\\codebase\\ExpectedExceptionTest\\new 1.html");//打开本地Html文件
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String j_s = "document.getElementById('particular').value='the third one';";
        System.out.println(j_s);
        js.executeScript(j_s);
        quit();
    }

    public static void main(String[] args) {
        JS js = new JS();
        js.scrollTo();
        js.textAreaInput();
    }
}
