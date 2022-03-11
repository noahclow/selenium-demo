package com.ideafreaks.draganddrop;


import com.ideafreaks.resources.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.IOException;

public class DragAndDrop extends Base {
    String URL = "https://demoqa.com/droppable";
    public WebDriver driver;
    public static Logger log = (Logger) LogManager.getLogger(Base.class.getName());

    @Test(description = "Drag and Drop ", enabled = true)
    protected void dragAndDrop() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(URL);

        //#2 - Instantiate the actions class
        Actions action = new Actions(driver);

        //#3 - Define the from and to
        WebElement from = driver.findElement(By.id("draggable"));
        WebElement to = driver.findElement(By.id("droppable"));

        //#4 - Use the method dragAndDrop
        action.dragAndDrop(from, to).build().perform();
        driver.quit();

    }

    @Test(description = "Drag and Drop By", enabled = true)
    protected void dragAndDropBy() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(URL);

        //#2 - Instantiate the actions class
        Actions action = new Actions(driver);

        //#3 - Define the from and to
        WebElement from = driver.findElement(By.id("draggable"));
        WebElement to = driver.findElement(By.id("droppable"));

        //#4 - Use the method dragAndDropBy X and Y properties
        action.dragAndDropBy(from, 270, 36).build().perform();
        driver.quit();

    }

}
