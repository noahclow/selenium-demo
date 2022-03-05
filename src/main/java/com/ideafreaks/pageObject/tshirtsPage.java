package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class tshirtsPage {
    public WebDriver driver;

    public tshirtsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Faded Short Sleeve T-shirts']")
    private WebElement product;

    @FindBy(css = "div[class='right-block'] span[class='price product-price']")
    private WebElement price;

    @FindBy(id = "search_query_top")
    private WebElement searchProduct;

    @FindBy(name = "submit_search")
    private WebElement searchButton;

    public String productName() {
        return product.getText().toUpperCase();
    }

    public String price() {
        return price.getText();
    }

    public void userTypesProductToSearch(String productName) {
        searchProduct.sendKeys(productName);
    }

    public searchPage userClicksSearchBtn() {
        searchButton.click();
        return new searchPage(driver);
    }

}
