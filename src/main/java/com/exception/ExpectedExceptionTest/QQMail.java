package com.exception.ExpectedExceptionTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QQMail {
    private WebDriver driver;

    void setDriver() {
        System.setProperty("webdriver.gecko.driver", "c:\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    void logOn() {
        driver.get("https://mail.qq.com/cgi-bin/loginpage");
        driver.findElement(By.id("qqLoginTab")).click();
        WebElement frame = driver.findElement(By.id("login_frame"));
        driver.switchTo().frame(frame);
        driver.findElement(By.cssSelector("input#u.inputstyle")).sendKeys("1035681086");
        driver.findElement(By.cssSelector("input#p.inputstyle.password")).sendKeys("Zqq261717");
        driver.findElement(By.cssSelector("input#login_button.btn")).click();
        driver.switchTo().defaultContent();
    }

    void writeANewLetter() {
        driver.findElement(By.cssSelector("div#leftPanel.newskinbody>div#navBarDiv>ul>li#composebtn_td")).click();
        driver.switchTo().frame(driver.findElement(By.id("mainFrame")));
        driver.findElement(By.xpath("//div[@id='toAreaCtrl']/div[2]/input")).sendKeys("1035681086@qq.com");
        driver.findElement(By.cssSelector("input#subject")).sendKeys("for test1");
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        driver.findElement(By.tagName("body")).sendKeys("an email from auto program.");
        driver.switchTo().parentFrame();
        driver.findElement(By.linkText("发送")).click();
        driver.switchTo().defaultContent();
    }

    void logOut() {
        driver.findElement(By.linkText("退出")).click();
    }

    void quit() {
        driver.quit();
    }

    List<String> checkInbox() {
        List<String> emailhead = new ArrayList<>();
        WebElement mainframe = driver.findElement(By.id("mainFrame"));
        driver.switchTo().frame(mainframe);
        driver.findElement(By.linkText("未读邮件")).click();
        List<WebElement> unread = driver.findElements(By.cssSelector("div.ur_l_mailhead"));
        for (WebElement un : unread) {
            emailhead.add(un.getText());
        }
        driver.switchTo().defaultContent();
        return emailhead;
    }
    public static void main(String[] args) {
        QQMail qq = new QQMail();
        qq.setDriver();
        qq.logOn();
//        qq.writeANewLetter();
        qq.checkInbox();
        qq.logOut();
        qq.quit();
    }
}
