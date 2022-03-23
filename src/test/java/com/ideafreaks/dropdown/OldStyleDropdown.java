package com.ideafreaks.dropdown;

import com.ideafreaks.resources.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.IOException;

public class OldStyleDropdown extends Base {

    String URL = "https://demoqa.com/select-menu";
    public WebDriver driver;
    public static Logger log = (Logger) LogManager.getLogger(Base.class.getName());

    @Test(description = "Old Style Dropdown", enabled = true)
    protected void oldStyleDropdownList() throws IOException {

        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(URL);

        //Declare the object of IU Select

        Select oldStyleMenu = new Select(driver.findElement(By.id("oldSelectMenu")));
        oldStyleMenu.selectByIndex(1);
        oldStyleMenu.selectByValue("8");
        oldStyleMenu.selectByVisibleText("Magenta");
    }

}
