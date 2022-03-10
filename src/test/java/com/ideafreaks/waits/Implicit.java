package com.ideafreaks.waits;

import java.io.IOException;
import java.time.Duration;

import com.ideafreaks.resources.Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Implicit extends Base{

    String URL = "https://demoqa.com/dynamic-properties";

    public WebDriver driver;
    public String status;

    public static Logger log = (Logger) LogManager.getLogger(Base.class.getName());

    @Test(description = "Implicit Wait", enabled = true)
    protected void implicitWait() throws InterruptedException, IOException {

        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(URL);
        log.info("Open " + URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        try{
            driver.findElement(By.id("visibleAfter")).click();
            System.out.println("Boton clickeado");
        } catch(Exception e)
        {
            System.out.println(e);
        }
        driver.quit();
    }

}
