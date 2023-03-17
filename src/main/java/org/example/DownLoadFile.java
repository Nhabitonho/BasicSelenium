package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DownLoadFile {
    public static void main(String[] args) throws Exception {
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\lonbui\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions actions = new Actions(driver);
        driver.navigate().to("http://the-internet.herokuapp.com/");
//        WebElement btn15 = driver.findElement(By.xpath("//a[contains(text(),'Entry Ad')]"));
//        btn15.click();
//        WebElement btn16 = driver.findElement(By.xpath("//a[contains(text(),'Exit Intent')]"));
//        btn16.click();
        WebElement btn17 = driver.findElement(By.xpath("//a[contains(text(),'File Download')]"));
        btn17.click();
        WebElement downloadBtn = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a[1]"));
        downloadBtn.click();
        isFileDownloaded("C:\\Wget", "Hello World");
    }
    public static boolean isFileDownloaded(String filePath, String fileName) throws Exception {
        final int SLEEP_TIME_MILLIS = 1000;
        File file = new File(filePath);
        final int timeout = 60* SLEEP_TIME_MILLIS;
        int timeElapsed = 0;
        while (timeElapsed<timeout){
            if (file.exists()) {
                System.out.println(fileName + " is present");
                return true;
            } else {
                timeElapsed +=SLEEP_TIME_MILLIS;
                Thread.sleep(SLEEP_TIME_MILLIS);
            }
        }
        return false;
    }
}
