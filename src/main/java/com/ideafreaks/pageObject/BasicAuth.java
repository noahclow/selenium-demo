package com.ideafreaks.pageObject;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Queue;

public class BasicAuth {

    public WebDriver driver;

    public BasicAuth(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "p")
    private WebElement basicAuthText;

    public String successLoginText() {
        return basicAuthText.getText();
    }
}
