package com.exception.ExpectedExceptionTest;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

public class XieCheng {
    private static WebDriver driver;

    private void setDriver() {
        System.setProperty("webdriver.gecko.driver", "C:\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
//      System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
//      driver = new ChromeDriver();
//      System.setProperty("webdriver.ie.driver", "C:\\driver\\IEDriverServer.exe");
//      driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void openOfficalWebsite() {
        driver.get("https://www.baidu.com/");
        driver.findElement(By.id("kw")).sendKeys("携程");
        driver.findElement(By.id("su")).click();
        driver.findElement(By.cssSelector("h2>a[title2=主标题]")).click();
    }

    private void searchhotels() throws InterruptedException {
        Thread.sleep(5000);
        String currentWindow = driver.getWindowHandle();//获取当前窗口句柄
        Set<String> handles = driver.getWindowHandles();//获取所有窗口句柄
        for (String h : handles) {
            if (currentWindow.equals(h))//判断it.next()是否是当前窗口
                continue;//是，跳出，继续迭代
            driver.switchTo().window(h);//否，切换至新窗口
        }
        System.out.println("New page title is:" + driver.getTitle());
        driver.findElement(By.cssSelector("ul#searchBoxUl>li:first-of-type")).click();//酒店
        driver.findElement(By.cssSelector("div.s_content>p#hotelSwitch>a:first-of-type")).click();//国内酒店
        //destination
        driver.findElement(By.cssSelector("form#chinaHotelForm input#HD_CityName")).sendKeys("厦门");
        //入住及退房日期
        WebElement checkInDate = driver.findElement(By.cssSelector("form#chinaHotelForm>div.s_item_cont>div.s_item>input#HD_CheckIn"));
        checkInDate.clear();
        checkInDate.sendKeys("2019-08-16");
        WebElement checkOutDate = driver.findElement(By.cssSelector("form#chinaHotelForm>div.s_item_cont>div.s_item2>input#HD_CheckOut"));
        checkOutDate.clear();
        checkOutDate.sendKeys("2019-08-20");
        //房间数
        Select roomCountList = new Select(driver.findElement(By.id("J_roomCountList")));//new a select 类型的 select 对象
        roomCountList.selectByVisibleText("2间");
        //住客数
        driver.findElement(By.id("J_RoomGuestInfoTxt")).click();//customer info
        WebElement adultplus = driver.findElement(By.cssSelector("span#J_AdultCount i.icon_numplus"));
        adultplus.click();
        adultplus.click();

        WebElement childplus = driver.findElement(By.cssSelector("span#J_ChildCount i.icon_numplus"));//1children
        Thread.sleep(1000);
        childplus.click();
        Thread.sleep(2000);
        Select childageVal = new Select(driver.findElement(By.id("J_childageVal0")));//children age

        childageVal.selectByIndex(2);
        driver.findElement(By.id("J_RoomGuestInfoBtnOK")).click();//click confirm button
        //hotel lever
        Select hotelLevelSelect = new Select(driver.findElement(By.id("searchHotelLevelSelect")));
        hotelLevelSelect.selectByValue("3");
        //hotel address
        driver.findElement(By.cssSelector("input#HD_TxtKeyword.w170.inputSel")).click();
        driver.findElement(By.linkText("曾厝垵")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    private void printHotelList() {
        List<WebElement> hotelList = driver.findElements(By.cssSelector("h2.hotel_name>a"));
        for (WebElement hotelname : hotelList) {
            System.out.println(hotelname.getText());
        }
    }

    //选取"精选" 酒店中，价格最便宜的作为best choice
    private void bestChoice() {
        List<WebElement> choiceness = driver.findElements(By.xpath("//span[@class='label_selection']/parent::a"));
        List<WebElement> hotelPrices = driver.findElements(By.xpath("//span[@class='label_selection']/ancestor::ul/li[3]//span[@class='J_price_lowList']"));
        int a = 0;
        int[] price = new int[hotelPrices.size()];
        int pr = Integer.parseInt(hotelPrices.get(0).getText());//pr=price[0]
        for (int i = 1; i < hotelPrices.size(); i++) {
            price[i] = Integer.parseInt(hotelPrices.get(i).getText());
            if (price[i] < pr) {
                pr = price[i];
                a = i;
            }
        }
        System.out.println("The best choice is:  " + choiceness.get(a).getText() + ":  " + hotelPrices.get(a).getText());
    }
    private void quit() {
        driver.quit();
    }
    public static void main(String[] args) throws InterruptedException {
        XieCheng xc = new XieCheng();
        xc.setDriver();
        xc.openOfficalWebsite();
        xc.searchhotels();
        xc.printHotelList();
        xc.bestChoice();
        xc.quit();
    }
}
