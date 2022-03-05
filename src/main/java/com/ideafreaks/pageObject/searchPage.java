package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class searchPage {
    public WebDriver driver;

    public searchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='lighter']")
    private WebElement searchResult;

    @FindBy(css = "div[class='right-block'] span[class='price product-price']")
    private WebElement priceResult;

    public String searchResults(){
        return searchResult.getText();
    }

    public String price(){
        return priceResult.getText();
    }
}
