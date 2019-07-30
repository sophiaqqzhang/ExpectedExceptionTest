package com.exception.ExpectedExceptionTest;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class XieCheng {
    private static WebDriver driver;

    private void setDriver() {
        System.setProperty("webdriver.gecko.driver", "C:\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void openOfficalWebsite() {
        driver.get("https://www.baidu.com/");
        driver.findElement(By.id("kw")).sendKeys("携程");
        driver.findElement(By.id("su")).click();
        driver.findElement(By.cssSelector("div#content_left h2>a[title2=主标题]")).click();
    }

    private void searchhotels() {
        driver.findElement(By.cssSelector("div#searchBox.searchbox.searchbox_animate")).click();
        driver.findElement(By.xpath("//div[@id='searchBox']/ul[@id='searchBoxUl']/li[1]/b")).click();//酒店
        driver.findElement(By.xpath("//div[@class='s_content']/p[@id='hotelSwitch']/a[1]")).click();//国内酒店
        //destination
        driver.findElement(By.cssSelector("form#chinaHotelForm input#HD_CityName")).sendKeys("厦门");
        //入住及退房日期
        WebElement checkInDate = driver.findElement(By.cssSelector("form#chinaHotelForm>div.s_item_cont>div.s_item>input#HD_CheckIn"));
        checkInDate.sendKeys("2019-08-16");
        WebElement checkOutDate = driver.findElement(By.cssSelector("form#chinaHotelForm>div.s_item_cont>div.s_item2>input#HD_CheckOut"));
        checkOutDate.sendKeys("2019-08-20");
        //房间数
        Select roomCountList = new Select(driver.findElement(By.id("J_roomCountList")));//new a select 类型的 select 对象
        roomCountList.selectByVisibleText("2间");
        //住客数
        driver.findElement(By.id("J_RoomGuestInfoTxt")).click();//customer info
        driver.findElement(By.cssSelector("span#J_AdultCount i.icon_numplus")).click();
        driver.findElement(By.cssSelector("span#J_AdultCount i.icon_numplus")).click();//3adults
        driver.findElement(By.cssSelector("span#J_ChildCount i.icon_numplus")).click();//1children
        Select childageVal = new Select(driver.findElement(By.id("J_childageVal0")));//children age
        childageVal.selectByIndex(2);
        driver.findElement(By.id("J_RoomGuestInfoBtnOK")).click();//click confirm button
        //hotel lever
        Select hotelLevelSelect = new Select(driver.findElement(By.id("searchHotelLevelSelect")));
        hotelLevelSelect.deselectByValue("3");
        //hotel address
        driver.findElement(By.cssSelector("input#HD_TxtKeyword.w170.inputSel")).click();
        driver.findElement(By.linkText("曾厝垵")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    private void quit() {
        driver.quit();
    }

    public static void main(String[] args) {
        XieCheng xc = new XieCheng();
        xc.setDriver();
        xc.openOfficalWebsite();
        xc.searchhotels();
        xc.quit();
    }
}
