package com.ideafreaks.da;

import com.ideafreaks.pageObject.AwsHomePage;
import com.ideafreaks.resources.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class TestSwitchTabs extends Base {
    public WebDriver driver;
    public String status;
    String URL = "https://aws.amazon.com/es/";
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @Test(description = "Switch tabs", enabled = true)
    protected void switchTabs() throws InterruptedException, IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get("https://www.tutorialspoint.com/about/about_careers.htm");
        // Keys.Chord string
        String clicks = Keys.chord(Keys.COMMAND, Keys.ENTER);
        // open the link in new tab, Keys.Chord string passed to sendKeys
        driver.findElement(
                By.xpath("//*[text()='Terms of Use']")).sendKeys(clicks);
        Thread.sleep(1000);
        driver.findElement(
                By.xpath("//*[text()='Terms of Use']")).sendKeys(clicks);
        Thread.sleep(1000);
        driver.findElement(
                By.xpath("//*[text()='Terms of Use']")).sendKeys(clicks);

        // hold all window handles in array list
        ArrayList<String> newTb = new ArrayList<>(driver.getWindowHandles());
        //switch to new tab
        driver.switchTo().window(newTb.get(3));
        System.out.println("Page title of new tab: " + driver.getTitle());
        ;
        driver.switchTo().window(newTb.get(2));
        System.out.println("Page title of new tab: " + driver.getTitle());

        driver.switchTo().window(newTb.get(1));
        System.out.println("Page title of new tab: " + driver.getTitle());

        //driver.close();
        //switch to parent window
        driver.switchTo().window(newTb.get(0));
        System.out.println("Page title of parent window: " + driver.getTitle());

        driver.quit();

    }

    @Test(description = "Switch tabs with JS", enabled = true)
    protected void switchTabsWithJs() throws InterruptedException, IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(URL);
        AwsHomePage AwsHomePage = new AwsHomePage(driver);
        AwsHomePage.userClicksSearchIcon();
        AwsHomePage.userTypesSearch("Amazon Corretto");
/*        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();
        System.out.println(originalWindow);
        //Check we don't have other windows open already*/

        assert driver.getWindowHandles().size() == 1;
        AwsHomePage.openResultInOtherTab();
        ArrayList<String> newTb = new ArrayList<>(driver.getWindowHandles());
        AwsHomePage.changeTab(newTb.get(0));
        AwsHomePage.changeTab(newTb.get(1));


    }
}

