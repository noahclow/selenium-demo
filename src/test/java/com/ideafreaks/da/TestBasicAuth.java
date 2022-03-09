package com.ideafreaks.da;

import com.ideafreaks.pageObject.BasicAuth;
import com.ideafreaks.resources.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class TestBasicAuth extends Base {

    public WebDriver driver;
    String URL = "https://the-internet.herokuapp.com/basic_auth";
    public static Logger log = LogManager.getLogger(Base.class.getName());


    @BeforeTest
    public void setupBrowser() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(URL);
        log.info("Open " + URL);

    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

    @Test(description = "Verify basic authentication", enabled = true)
    protected void verifyBasicAuth() {

/*      The syntax for handling this login pop up is:

        https://username:password@URL*/

        //Set the user
        String username = "admin";
        //Set the password
        String password = "admin";
        String URL = "https://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
        driver.get(URL);
        String title = driver.getTitle();
        System.out.println("The page title is " + title);
        log.info("The page title is " + title);
        BasicAuth BasicAuth = new BasicAuth(driver);
        String text = BasicAuth.successLoginText();
        assertEquals(text,"Congratulations! You must have the proper credentials.");
        System.out.println("The test present in page is ==> " + text);
        log.info("The test present in page is ==> " + text);

    }
}
