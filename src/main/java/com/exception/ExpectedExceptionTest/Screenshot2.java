package com.exception.ExpectedExceptionTest;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Screenshot2 {
    private static WebDriver driver;

    private void setDriver() {
        System.setProperty("webdriver.gecko.driver", "c:\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void openBaidu() {
        driver.get("https://www.baidu.com/");
    }

    public static void main(String[] args) throws IOException {
        Screenshot2 shot = new Screenshot2();
        shot.setDriver();
        shot.openBaidu();
        driver.findElement(By.id("kw")).sendKeys("selenium");//输入selenium
        try {
            driver.findElement(By.id("123")).click();//点击搜索；因id错误，点击搜索很可能失败，所以尝试这个行为：try
        } catch (Exception e) {//失败后，捕获异常，截图
            //
            shot.getScreenshot();
        }
    }

    private void getScreenshot() throws IOException {//截图的实现
//      首先将driver 由WebDriver类 转换成 TakesScreenshot类
        TakesScreenshot scrshot = (TakesScreenshot) driver;
//      调用getScreenshotAs（）实现截图，并将截图保存为scrFile
        File scrFile = scrshot.getScreenshotAs(OutputType.FILE);
        File desFile = new File(".\\Screenshot2\\screen.png");
//      use selenium.io.FileHandler to copy the screenshot to the new file
        FileHandler.copy(scrFile, desFile);// 必须确保Screenshot2已存在，否则报错
    }
}
