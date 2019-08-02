package com.exception.ExpectedExceptionTest;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
            shot.getScreenshot();
            shot.elementScreenshot();
        }
        shot.quit();
    }

    private void getScreenshot() throws IOException {
        //首先将driver 由WebDriver类 转换成 TakesScreenshot类
        TakesScreenshot scrshot = (TakesScreenshot) driver;
        // 调用getScreenshotAs（）实现截图，并将截图保存为scrFile
        File scrFile = scrshot.getScreenshotAs(OutputType.FILE);
//      截图成功，save
//      (创建一个.png 文件， 将截图copy进去)
        File desFile = new File(".\\Screenshot2\\screen.png");
//      use selenium.io.FileHandler to copy the screenshot to the new file
        FileHandler.copy(scrFile, desFile);// 必须确保Screenshot2已存在，否则报错
    }

    private void elementScreenshot() throws IOException {
        //首先将driver 由WebDriver类 转换成 TakesScreenshot类
        TakesScreenshot scrshot = (TakesScreenshot) driver;
        // 调用getScreenshotAs（）实现截图，并将截图保存为scrFile
        File srcFile = scrshot.getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(srcFile);//File file
        WebElement ele = driver.findElement(By.id("result_logo"));
        Point point = ele.getLocation();
        int width = ele.getSize().getWidth();
        int height = ele.getSize().getHeight();
        BufferedImage eleImg = fullImg.getSubimage(point.getX(), point.getY(), width, height);
        ImageIO.write(eleImg, "png", new File(".\\Screenshot2\\eleScreenshot.png"));
        //imageIo.write(original-image,string-save-type,new-a-file)
    }

    private void quit() {
        driver.quit();
    }
}
