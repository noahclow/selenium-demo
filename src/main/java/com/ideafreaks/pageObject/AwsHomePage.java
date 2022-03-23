package com.ideafreaks.pageObject;

import com.ideafreaks.resources.Base;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AwsHomePage extends Base {

    public WebDriver driver;

    public AwsHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//i[@class='m-nav-search-icon']")
    private WebElement searchIcon;

    @FindBy(xpath = "//div[@data-directory-id='typeahead-suggestions']//input[@placeholder='Buscar']")
    private WebElement searchTextBox;

    @FindBy(xpath = "//a[contains(text(),'Amazon Corretto Distribución lista para producción')]")
    private WebElement Corretto;

    public void userClicksSearchIcon() {
        searchIcon.click();
    }

    public void userTypesSearch(String txt) {
        searchTextBox.sendKeys(txt, Keys.ENTER);
    }

    public String getResultURL() {
        return Corretto.getAttribute("href");
    }

    public void openResultInOtherTab() {

        String Url = getResultURL();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(Url);

    }

    public void openResultInOtherTabJs() {

/*        String Url = getResultURL();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(Url);*/

        StringBuilder UrlResult;
        UrlResult = new StringBuilder("window.open('" + getResultURL() + "')");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(String.valueOf(UrlResult));
    }

    public void changeTab(String window) {
        driver.switchTo().window(window);

    }
}
