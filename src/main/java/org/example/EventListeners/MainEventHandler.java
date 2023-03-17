package org.example.EventListeners;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.Duration;

public class MainEventHandler {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("http://the-internet.herokuapp.com");
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);

        WebDriverEventHandler webDriverEventHandler = new WebDriverEventHandler();
        eventFiringWebDriver.register(webDriverEventHandler);

        eventFiringWebDriver.navigate().to("http://the-internet.herokuapp.com/dynamic_controls");
        eventFiringWebDriver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/h4[1]")).getText();
        eventFiringWebDriver.findElement(By.xpath("//*[@id=\"checkbox\"]/input")).click();
        eventFiringWebDriver.findElement(By.xpath("//button[contains(text(),'Remove')]")).click();
        eventFiringWebDriver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
        eventFiringWebDriver.findElement(By.xpath("//button[contains(text(),'Enable')]")).click();
        Thread.sleep(3000);
        eventFiringWebDriver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[2]/input[1]")).sendKeys("Enable input");
        eventFiringWebDriver.findElement(By.xpath("//button[contains(text(),'Disable')]")).click();

        eventFiringWebDriver.unregister(webDriverEventHandler);

        driver.close();
    }
}
