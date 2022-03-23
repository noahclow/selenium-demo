package com.ideafreaks.waits;

import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.NoSuchElementException; // este paquete no el de java

import com.ideafreaks.resources.Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.surefire.api.booter.DumpErrorSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Fluent extends Base {
    String URL = "https://demoqa.com/dynamic-properties";

    public WebDriver driver;
    public String status;

    public static Logger log = (Logger) LogManager.getLogger(Base.class.getName());

    @Test(description = "Explicit Wait", enabled = true)
    protected void explicitWait() throws InterruptedException, IOException {


        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(URL);
        log.info("Open " + URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);

        try {
            WebElement button = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.id("visibleAfter"));
                }
            });
            button.click();
            System.out.println("Boton clickeado");

        } catch (Exception e) {
            System.out.println(e);
        }
        driver.quit();
    }

}