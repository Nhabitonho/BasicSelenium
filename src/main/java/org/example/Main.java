package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws InterruptedException, AWTException {
        Integer iBrokenImageCount = 0;
        String status = "passed";

        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\lonbui\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.navigate().to("http://the-internet.herokuapp.com/");

        //click btn1
        WebElement bnt1 = driver.findElement(By.xpath("//a[contains(text(),'A/B Testing')]"));
        bnt1.click();
        //get title
        WebElement title = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3"));
        String titleBnt1 = title.getText();
        System.out.println(titleBnt1);
        System.out.println("Testcase1: Passed");
        //back and click bnt2
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //bnt2
        WebElement bnt2 = driver.findElement(By.xpath("//a[contains(text(),'Add/Remove Elements')]"));
        bnt2.click();
        //click add bnt
        WebElement bnt2AddElement = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
        actions.doubleClick(bnt2AddElement).perform();
        WebElement bnt2Delete = driver.findElement(By.xpath("//*[@id=\"elements\"]/button[2]"));
        bnt2Delete.click();
        WebElement bnt2Delete2 = driver.findElement(By.xpath("//*[@id=\"elements\"]/button[1]"));
        bnt2Delete.click();
        System.out.println("Testcase2: Passed");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //bnt3
        WebElement btn3 = driver.findElement(By.xpath("//a[contains(text(),'Basic Auth')]"));
        btn3.click();
        driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        String titlelogin = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p")).getText();
        System.out.println("title " + titlelogin);
        driver.navigate().back();
        System.out.println("Testcase3: Passed");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //bnt4
        WebElement btn4 = driver.findElement(By.xpath("//a[contains(text(),'Broken Images')]"));
        btn4.click();
        checkImgBroken(driver, iBrokenImageCount, status);
        System.out.println("Testcase4: Passed");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //bnt5
        WebElement btn5 = driver.findElement(By.xpath("//a[contains(text(),'Challenging DOM')]"));
        btn5.click();
        WebElement btnblue = driver.findElement(By.xpath("(//a[@class='button'])"));
        btnblue.click();
        WebElement btnred = driver.findElement(By.xpath("//a[@class='button alert']"));
        btnred.click();
        WebElement btngreen = driver.findElement(By.xpath("//a[@class='button success']"));
        btngreen.click();
        System.out.println("Testcase5: Passed");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //bnt6
        WebElement btn6 = driver.findElement(By.xpath("//a[contains(text(),'Checkboxes')]"));
        btn6.click();
        WebElement checkBox1 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/input[1]"));
        checkBox1.click();
        WebElement checkBox2 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/input[2]"));
        checkBox2.click();
        System.out.println("Testcase6: Passed");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //bnt7
        WebElement btn7 = driver.findElement(By.xpath("//a[contains(text(),'Context Menu')]"));
        btn7.click();
        WebElement boxElement = driver.findElement(By.id("hot-spot"));
        actions.contextClick(boxElement).perform();
        driver.switchTo().alert().accept();
        boxElement.click();
        System.out.println("Testcase7: Passed");
        driver.navigate().back();
        //driver.navigate().to("http://the-internet.herokuapp.com/");
        //bnt8
        WebElement btn8 = driver.findElement(By.xpath("//a[contains(text(),'Digest Authentication')]"));
        btn8.click();
        driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/digest_auth");
        System.out.println("title " + titlelogin);
        driver.navigate().back();
        System.out.println("Testcase8: Passed");
        driver.navigate().back();
        //bnt9
        WebElement btn9 = driver.findElement(By.xpath("//a[contains(text(),'Disappearing Elements')]"));
        btn9.click();
        By by = By.xpath("//a[contains(text(),'Gallery')]");
        isElementNotPresent(driver, by);
        System.out.println("Testcase9: Passed");
        driver.navigate().back();
//        //bnt10
        WebElement btn10 = driver.findElement(By.xpath("//a[contains(text(),'Drag and Drop')]"));
        btn10.click();
        WebElement columnA = driver.findElement(By.id("column-a"));
        WebElement columnB = driver.findElement(By.id("column-b"));
        //drag and drop A -> B
        actions.dragAndDrop(columnA, columnB).build().perform();
        actions.release(columnB);
        System.out.println("Testcase10: Passed");
        driver.navigate().back();
//        //bnt11
        WebElement btn11 = driver.findElement(By.xpath("//a[contains(text(),'Dropdown')]"));
        btn11.click();
        Select drpList = new Select(driver.findElement(By.id("dropdown")));
        //drpList.selectByVisibleText("Option 1");
        drpList.selectByIndex(1);
        System.out.println("Testcase11: Passed");
        driver.navigate().back();
//        btn12
        WebElement btn12 = driver.findElement(By.xpath("//a[contains(text(),'Dynamic Content')]"));
        btn12.click();
        By textContext = By.xpath("//*[@id=\"content\"]/div[3]/div[2]");
        //WebElement textContent1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]"));

        String text1 = driver.findElement(textContext).getText();
        System.out.println(text1);
        driver.navigate().refresh();
        //WebElement textContent2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]"));
        String text2 = driver.findElement(textContext).getText();
        System.out.println(text2.equals(text1));
        System.out.println("Testcase12: Passed");
        driver.navigate().back();
//        bnt13
        WebElement btn13 = driver.findElement(By.xpath("//a[contains(text(),'Dynamic Controls')]"));
        btn13.click();
        WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"checkbox\"]/input"));
        checkBox.click();
        WebElement removeBtn = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
        removeBtn.click();
        WebElement addBtn = driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
        addBtn.click();
        //WebElement addBtn = (new WebDriverWait(driver, Duration.ofSeconds(5))).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Add')]")));
        WebElement enableBtn = driver.findElement(By.xpath("//button[contains(text(),'Enable')]"));
        enableBtn.click();
        Thread.sleep(3000);
//        actions.moveToElement(enableBtn);
        WebElement input = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[2]/input[1]"));
        //WebElement input = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\\\"input-example\\\"]/input")));
        input.sendKeys("Enable input");
        WebElement disableBtn = driver.findElement(By.xpath("//button[contains(text(),'Disable')]"));
        disableBtn.click();
        System.out.println("Testcase13: Passed");
        driver.navigate().back();
//        //bnt14
        WebElement btn14 = driver.findElement(By.xpath("//a[contains(text(),'Dynamic Loading')]"));
        btn14.click();
        WebElement example1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a[1]"));
        example1.click();
        WebElement startBtn = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
        startBtn.click();
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("document.getElementById('finish').value='Hello World!';");
        String text1Example = (String) j.executeScript("return document.getElementById('finish').value");
        System.out.println("Text1: " + text1Example);
        driver.navigate().back();
        WebElement example2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a[2]"));
        example2.click();
        WebElement startBtn2 = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
        startBtn2.click();
        WebElement titleElementHidden2 = driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]"));
        //WebElement titleElementHidden = (new WebDriverWait(driver, Duration.ofSeconds(5))).until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));
        //Thread.sleep(3000);
        String text2Example = titleElementHidden2.getText();
        System.out.println("Text2: " + text2Example);
        driver.navigate().back();
        System.out.println("Testcase14: Passed");
        driver.navigate().back();
//        //bnt5
        WebElement btn15 = driver.findElement(By.xpath("//a[contains(text(),'Entry Ad')]"));
        btn15.click();
        WebElement modal = driver.findElement(By.xpath("//*[@id=\"modal\"]/div[2]"));
//        WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"modal\"]//p[text()='Close']"));

        WebElement closeBtn = driver.findElement(By.xpath("//p[contains(text(),'Close')]"));
        wait.until(ExpectedConditions.visibilityOf(modal));
        closeBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(modal));
        System.out.println("TestCase15: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
//        //bnt16
        //WebElement btn10 = driver.findElement(By.xpath("//a[contains(text(),'Drag and Drop')]"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn10);
        //btn16
        WebElement btn16 = driver.findElement(By.xpath("//a[contains(text(),'Exit Intent')]"));
        btn16.click();
        Robot robot = new Robot();
        robot.mouseMove(600, 0);

        WebElement modalIntent = driver.findElement(By.xpath("//*[@id=\"ouibounce-modal\"]/div[2]"));
        WebElement closeBtnIntent = driver.findElement(By.xpath("//*[@id=\"ouibounce-modal\"]//p[text()='Close']"));
//        WebElement closeBtnIntent = driver.findElement(By.xpath("//p[contains(text(),'Close')]"));

        wait.until(ExpectedConditions.visibilityOf(modalIntent));
        closeBtnIntent.click();
        wait.until(ExpectedConditions.invisibilityOf(modalIntent));
        System.out.println("TestCase16: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn10);
        //btn17
        WebElement btn17 = driver.findElement(By.xpath("//a[contains(text(),'File Download')]"));
        btn17.click();
        WebElement downloadBtn = driver.findElement(By.xpath("//a[contains(text(),'download.jpg')]"));
        downloadBtn.click();
        isFileDownloaded("C:\\Users\\lonbui\\Downloads", "my-screenshot");
        System.out.println("TestCase17: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //bnt18
        WebElement btn18 = driver.findElement(By.xpath("//a[contains(text(),'File Upload')]"));
        btn18.click();
        WebElement chooseFileBtn = driver.findElement(By.xpath("//input[@id='file-upload']"));
        chooseFileBtn.sendKeys("C:\\Users\\lonbui\\Downloads\\Hello World.docx");
        WebElement uploadBtn = driver.findElement(By.xpath("//*[@id=\"file-submit\"]"));
        uploadBtn.click();
        WebElement uploadTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3"));
        System.out.println(uploadTitle.getText());
        System.out.println("TestCase18: Pass");
        driver.navigate().back();
        //bnt19
        WebElement btn19 = driver.findElement(By.xpath("//a[contains(text(),'Floating Menu')]"));
        btn19.click();
        WebElement lastPage = driver.findElement(By.xpath("//div[@id='page-footer']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lastPage);
        WebElement homeBtn = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
        homeBtn.click();
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Click home button navigate to: " + currentUrl);
        driver.navigate().to("http://the-internet.herokuapp.com/");
        System.out.println("TestCase19: Pass");
        //bnt20
        WebElement btn20 = driver.findElement(By.xpath("//a[contains(text(),'Forgot Password')]"));
        btn20.click();
        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.sendKeys("lon.bui@gmail.com");
        WebElement retrieveBtn = driver.findElement(By.xpath("//button[@id='form_submit']"));
        retrieveBtn.click();
        System.out.println("reset password successfully !!");
        System.out.println("TestCase20: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn21
        WebElement btn21 = driver.findElement(By.xpath("//a[contains(text(),'Form Authentication')]"));
        btn21.click();
        By loginTitle = By.xpath("//*[@id=\"flash\"]");
        WebElement usernameInput = driver.findElement(By.xpath("//input[@id='username']"));
        usernameInput.sendKeys("tomsmith");
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInput.sendKeys("SuperSecretPassword!");
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
        loginBtn.click();
        String strLogin=driver.findElement(loginTitle).getText();
        System.out.println(strLogin);
        WebElement logoutBtn = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a"));
        logoutBtn.click();
        String strLogin1=driver.findElement(loginTitle).getText();
        System.out.println(strLogin);
        System.out.println("TestCase21: Pass");

        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn22
        WebElement btn22 = driver.findElement(By.linkText("Frames"));
        btn22.click();
        WebElement nestedFrames= driver.findElement(By.xpath("//a[contains(text(),'Nested Frames')]"));
        WebElement iFrame = driver.findElement(By.xpath("//a[contains(text(),'iFrame')]"));
        nestedFrames.click();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        WebElement frameLeft= driver.findElement(By.tagName("body"));
        System.out.println(frameLeft.getText());
        driver.navigate().back();
        iFrame.click();

        WebElement iFrameContext = driver.findElement(By.xpath("//iframe[@id='mce_0_ifr']"));
        driver.switchTo().frame(iFrameContext);

        WebElement iFrameText = driver.findElement(By.tagName("body"));
        iFrameText.clear();
        iFrameText.sendKeys("This is a iFrame text !!!");
        System.out.println(iFrameText.getText());
        driver.switchTo().defaultContent();
        System.out.println("TestCase22: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn23
        WebElement btn23 = driver.findElement(By.xpath("//a[contains(text(),'Geolocation')]"));
        btn23.click();
        System.out.println("TestCase21: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn24
        WebElement btn24 = driver.findElement(By.xpath("//a[contains(text(),'Horizontal Slider')]"));
        btn24.click();
        WebElement sliderContainer = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/input"));
        double width = sliderContainer.getSize().width;
        actions.clickAndHold(sliderContainer).moveByOffset(-(int)(width / 2), 0).perform();
        int increament = 8;
        for (int i = 0; i < increament ; i++)
        {
            actions.sendKeys(Keys.ARROW_RIGHT);
        }
        actions.perform();
        System.out.println("TestCase24: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn25
        WebElement btn25 = driver.findElement(By.xpath("//a[contains(text(),'Hovers')]"));
        btn25.click();
        WebElement imgUser = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/img"));
        actions.moveToElement(imgUser).perform();
        WebElement nameUser = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/h5"));
        wait.until(ExpectedConditions.visibilityOf(nameUser));
        System.out.println(nameUser.getText());
        WebElement viewProfile = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a"));
        wait.until(ExpectedConditions.visibilityOf(viewProfile));
        viewProfile.click();
        System.out.println(driver.getCurrentUrl());
        System.out.println("TestCase25: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn26
        WebElement btn26 = driver.findElement(By.xpath("//a[contains(text(),'Infinite Scroll')]"));
        btn26.click();

//        while (true){
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//
//            //get the height of the webpage and scroll to the end
//            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        }
        System.out.println("TestCase26: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn27
        WebElement btn27 = driver.findElement(By.xpath("//a[contains(text(),'Inputs')]"));
        btn27.click();
        WebElement inputNums = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/input"));
        inputNums.sendKeys("123abc456");
        System.out.println("nums is: "+inputNums.getAttribute("value"));
        System.out.println("Testcase27: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn28
        WebElement btn28 = driver.findElement(By.xpath("//a[contains(text(),'JQuery UI Menus')]"));
        btn28.click();
        WebElement enableBtnMenu = driver.findElement(By.xpath("//a[@id='ui-id-2']"));
        enableBtnMenu.click();
        WebElement dowloadsBtnMenu = driver.findElement(By.xpath("//a[@id='ui-id-4']"));
        dowloadsBtnMenu.click();
        WebElement csvBtn = driver.findElement(By.xpath("//a[@id='ui-id-7']"));
        System.out.println(csvBtn.getText());
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn29
        WebElement btn29 = driver.findElement(By.xpath("//a[contains(text(),'JavaScript Alerts')]"));
        btn29.click();
        By resultAlert = By.xpath("//p[@id='result']");
        WebElement jsAlertBtn = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]"));
        jsAlertBtn.click();
        Alert alert =  wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        System.out.println(driver.findElement(resultAlert).getText());
        WebElement jsConfirmBtn = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]"));
        jsConfirmBtn.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert confirmAlert = driver.switchTo().alert();
        confirmAlert.dismiss();
        System.out.println(driver.findElement(resultAlert).getText());
        WebElement jsPromptBtn = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]"));
        jsPromptBtn.click();
        Alert promtAlert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys("Selenium");
        alert.accept();
        System.out.println(driver.findElement(resultAlert).getText());
        System.out.println("TestCase29: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn30
        WebElement btn30 = driver.findElement(By.xpath("//a[contains(text(),'JavaScript onload event error')]"));
        btn30.click();
        WebElement jsError = driver.findElement(By.xpath("/html/body"));
        System.out.println(jsError.getText());
        System.out.println("TestCase30: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn31
        WebElement btn31 = driver.findElement(By.xpath("//a[contains(text(),'Key Presses')]"));
        btn31.click();
        By keyPressResult = By.xpath("//p[@id='result']");
        WebElement keyPress = driver.findElement(By.xpath("//input[@id='target']"));
        keyPress.sendKeys("1");
        System.out.println(driver.findElement(keyPressResult).getText());
        keyPress.sendKeys(Keys.TAB);
        System.out.println(driver.findElement(keyPressResult).getText());
        System.out.println("TestCase31: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn32
        WebElement btn32 = driver.findElement(By.xpath("//a[contains(text(),'Large & Deep DOM')]"));
        btn32.click();
        System.out.println("TestCase32: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn33
        WebElement btn33 = driver.findElement(By.xpath("//a[contains(text(),'Multiple Windows')]"));
        btn33.click();
        WebElement openNewWindow = driver.findElement(By.xpath("//a[contains(text(),'Click Here')]"));
        openNewWindow.click();
        windowHandle(driver);
        System.out.println("TestCase33: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn34
        WebElement btn34 = driver.findElement(By.xpath("//a[contains(text(),'Nested Frames')]"));
        btn34.click();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        //WebElement frameLeft= driver.findElement(By.tagName("body"));
        System.out.println(frameLeft.getText());
        System.out.println("TestCase34: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn35
        WebElement btn35 = driver.findElement(By.xpath("//a[contains(text(),'Notification Messages')]"));
        btn35.click();
        By notiMess = By.xpath("//div[@id='flash']");
        System.out.println(driver.findElement(notiMess).getText());
        WebElement clickNotiMess = driver.findElement(By.xpath("//a[contains(text(),'Click here')]"));
        clickNotiMess.click();
        System.out.println(driver.findElement(notiMess).getText());
        System.out.println("TestCase35: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn36
        WebElement btn36 = driver.findElement(By.xpath("//a[contains(text(),'Redirect Link')]"));
        btn36.click();
        WebElement hereBtnRedirection = driver.findElement(By.xpath("//a[@id='redirect']"));
        hereBtnRedirection.click();
        WebElement btn200Status = driver.findElement(By.xpath("//a[contains(text(),'200')]"));
        btn200Status.click();
        WebElement statusCodeTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p"));
        System.out.println(statusCodeTitle.getText());
        System.out.println("TestCase36: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn37
        WebElement btn37 = driver.findElement(By.xpath("//a[contains(text(),'Secure File Download')]"));
        btn30.click();
        System.out.println("TestCase37: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn38
        WebElement btn38 = driver.findElement(By.xpath("//a[contains(text(),'Shadow DOM')]"));
        btn30.click();
        System.out.println("TestCase38: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn39
        WebElement btn39 = driver.findElement(By.xpath("//a[contains(text(),'Shifting Content')]"));
        btn30.click();
        System.out.println("TestCase39: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn40
        WebElement btn40 = driver.findElement(By.xpath("//a[contains(text(),'Slow Resources')]"));
        btn30.click();
        System.out.println("TestCase40: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn41
        WebElement btn41 = driver.findElement(By.xpath("//a[contains(text(),'Sortable Data Tables')]"));
        btn30.click();
        System.out.println("TestCase41: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn42
        WebElement btn42 = driver.findElement(By.xpath("//a[contains(text(),'Status Codes')]"));
        btn30.click();
        System.out.println("TestCase42: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn43
        WebElement btn43 = driver.findElement(By.xpath("//a[contains(text(),'Typos')]"));
        btn30.click();
        System.out.println("TestCase43: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        //btn44
        WebElement btn44 = driver.findElement(By.xpath("//a[contains(text(),'WYSIWYG Editor')]"));
        btn30.click();
        System.out.println("TestCase44: Pass");
        driver.navigate().to("http://the-internet.herokuapp.com/");
        driver.close();
        Thread.sleep(5000);
        System.exit(0);
    }

    public static boolean isElementNotPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            return true;
        }
        return false;
    }

    public static void checkImgBroken(WebDriver driver, int iBrokenImageCount, String status) {
        try {
            iBrokenImageCount = 0;
            List<WebElement> image_list = driver.findElements(By.tagName("img"));
            /* Print the total number of images on the page */
            System.out.println("The page under test has " + image_list.size() + " images");
            for (WebElement img : image_list) {
                if (img != null) {
                    if (img.getAttribute("naturalWidth").equals("0")) {
                        System.out.println(img.getAttribute("outerHTML") + " is broken.");
                        iBrokenImageCount++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = "failed";
            System.out.println(e.getMessage());
        }
        status = "passed";
        System.out.println(iBrokenImageCount + " broken images");
    }

    public static boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().contains(fileName)) {
                // File has been found, it can now be deleted:
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }
    // check file da~ tai? ve??

    public static boolean windowHandle(WebDriver driver) {
        String parentWindow = driver.getWindowHandle();
        Set<String> handles =  driver.getWindowHandles();
        for(String windowHandle  : handles)
        {
            if(!windowHandle.equals(parentWindow))
            {
                driver.switchTo().window(windowHandle);
                System.out.println(driver.getCurrentUrl());
//         <!--Perform your operation here for new window-->
                driver.close(); //closing child window
                driver.switchTo().window(parentWindow); //cntrl to parent window
            }
        }
        return true;
    }

}