package com.exception.ExpectedExceptionTest;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ScreenShot {
    private static WebDriver driver;

    private void setDriver() {
        System.setProperty("webdriver.gecko.driver", "c:\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void openBaidu() {
        driver.get("https://www.baidu.com/");
        System.out.println("当前页面为： " + driver.getTitle());
    }

    private void news() {
        driver.findElement(By.linkText("新闻")).click();
    }

    private void screenshots() {
//        ((TakesScreenshot)driver)：首先将driver 由WebDriver类 转换成 TakesScreenshot类
//        这样才可以调用getScreenshotAs()这个方法
        //调用截图方法 ,指定了OutputType.FILE做为参数传递给getScreenshotAs()方法，其含义是将截取的屏幕以文件形式返回
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File des = new File(".\\Screenshots\\screen.png");
        try {
            FileUtils.copyFile(src, des);//会自己创建必要，但不存在的路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("截图已完毕");
    }

    private void quit() {
        driver.quit();
    }

    public static void main(String[] args) {
        ScreenShot screenshot = new ScreenShot();
        screenshot.setDriver();
        screenshot.openBaidu();
        screenshot.news();
        screenshot.screenshots();
        screenshot.quit();
    }
}
