package org.example.firsttable;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FirstTableInfo {
    private String keyword;

    public FirstTableInfo(String keyword) {
        this.keyword = keyword;
    }

    public FirstTable getData() {
        FirstTable firstTable = new FirstTable();

        //setup driver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions actions = new Actions(driver);
        driver.navigate().to("https://sqengineer.com/practice-sites/practice-tables-selenium/");

        //location of table
        WebElement table = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody"));
        //define row of table
        List<WebElement> rows_table = table.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();
        //System.out.println("rows: " + (rows_count - 1));
        for (int row = 1; row < rows_count; row++) {
            List<WebElement> Column_row = rows_table.get(row).findElements(By.tagName("td"));
            //define cell
            int columns_count = Column_row.size();
            //System.out.println("Cell" + columns_count);
            if (Column_row.get(0).getText().contains(keyword)) {
                firstTable.setAutomationTool(Column_row.get(0).getText());
                firstTable.setType(Column_row.get(1).getText());
                firstTable.setLink(Column_row.get(2));
            }
        }
        driver.close();
        return firstTable;
    }
}
