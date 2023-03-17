package org.example.firsttable;

public class Main {
    public static void main(String[] args) {
        //setup driver
//        WebDriverManager.chromedriver().setup();
//        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\lonbui\\Downloads\\chromedriver_win32\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        Actions actions = new Actions(driver);
//        driver.navigate().to("https://sqengineer.com/practice-sites/practice-tables-selenium/");

        FirstTableInfo automationToolInfo = new FirstTableInfo("UFT");
        FirstTable result = automationToolInfo.getData();
        System.out.println(result.getAutomationTool());
        System.out.println(result.getType());
        System.out.println(result.getLink());


    }
}
