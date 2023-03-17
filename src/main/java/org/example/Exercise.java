package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Exercise {
    public static void main(String[] args) throws InterruptedException, AWTException {

//        FirefoxOptions options = new FirefoxOptions();
//        options.setBinary("C:\\Users\\lonbui\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
//        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
//        WebDriver driver = new FirefoxDriver(options);
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        Actions actions = new Actions(driver);
//        driver.get("https://quotes.toscrape.com/");
//        driver.navigate().to("http://the-internet.herokuapp.com/");
//

//        List<WebElement> listOfElements = driver.findElements(By.xpath("/html/body/div/div[2]/div[1]/div/span[1]"));
//
//        for (WebElement listOfElement : listOfElements) {
//            System.out.println(listOfElement.getText());
//        }

//        System.out.println("TestCase37: Pass");
//        Thread.sleep(3000);


//        driver = new ChromeDriver();
        //Go to guru99 site
//        driver.get("http://demo.guru99.com/V4/");
        driver.get("https://hemtruyenma.info/nghe-truyen/truyen-audio-hac-am-tay-du-ky.html");
        driver.findElement(By.xpath("//*[@id=\"jp_container_1\"]/div/div[3]/ul/li[189]/div/a[2]")).click();
        Thread.sleep(3000);
//        driver.findElement(By.xpath("//*[@id=\"jp_container_1\"]/div/div[2]/div[2]/div[4]/div[1]/button")).click();

        //Second session of WebDriver
//        WebDriver driver2 = new ChromeDriver();
        //Goto guru99 site
//        driver2.get("http://demo.guru99.com/V4/");
        //driver.close();
//        driver.quit();
//        Thread.sleep(3000);
    }

}