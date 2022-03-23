package com.ideafreaks.dropdown;

import com.ideafreaks.resources.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.IOException;

public class MultipleSelect extends Base{

    String URL = "https://demoqa.com/select-menu";
    public WebDriver driver;
    public static Logger log = (Logger) LogManager.getLogger(Base.class.getName());

    @Test(description = "Old Style Dropdown", enabled = true)
    protected void MultipleSelectList() throws IOException {

        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(URL);

        //Declare the object of IU Select

        Select cars = new Select(driver.findElement(By.id("cars")));
        cars.selectByIndex(2);
        cars.selectByValue("audi");
        cars.selectByVisibleText("Volvo");
        cars.deselectByValue("audi");
        cars.deselectAll();
    }
}
