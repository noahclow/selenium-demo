package com.ideafreaks.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DragAndDrop {

    public WebDriver driver;

    public DragAndDrop(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "draggable")
    private WebElement draggable;

//    public void draggable() {
//          draggable;
//    }
}
