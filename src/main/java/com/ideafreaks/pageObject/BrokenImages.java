package com.ideafreaks.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class BrokenImages {
  public WebDriver driver;

  public BrokenImages(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBys({ @FindBy(xpath = "//div[@class='example']//img") })
  private List<WebElement> allImages;

  public List<WebElement> allImages() {
    return  allImages;  //The images are placed in a list, which will be further iterated to find broken images on the page.
  }
}
