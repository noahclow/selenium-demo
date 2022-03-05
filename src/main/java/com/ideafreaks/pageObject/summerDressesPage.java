package pageObject;

import org.apache.maven.surefire.shared.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class summerDressesPage {
    public WebDriver driver;

    public summerDressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='product-image-container']")
    private List<WebElement> dresses;

    @FindBy(css = "a[title='View'] span")
    private List<WebElement> moreButton;

    @FindBy(id = "quantity_wanted")
    private WebElement quantity_wanted;

    @FindBy(id = "group_1")
    private WebElement setSize;

    @FindBy(name = "White")
    private WebElement whiteColor;

    @FindBy(id = "add_to_cart")
    private WebElement add_to_cart;

    @FindBy(xpath = "//span[normalize-space()='Proceed to checkout']")
    private WebElement checkOut;

    @FindBy(xpath = "//div[@class='wishlist']")
    private List<WebElement> addWishList;

    @FindBy(xpath = "//div[@class='fancybox-inner']")
    private WebElement fancyError;

    @FindBy(id = "our_price_display")
    private WebElement price;


    public void userSelectsDress(String num) {
        List<WebElement> dressesList = dresses;
        WebElement position = dressesList.get(Integer.parseInt(num));
        Actions action = new Actions(driver);
        action.moveToElement(position).perform();

    }

    public void userClicksMoreBtn(String n) {
        List<WebElement> moreList = moreButton;
        WebElement position = moreList.get(Integer.parseInt(n));
        Actions action = new Actions(driver);
        action.moveToElement(position).perform();
        action.click().build().perform();

    }

    public void userSetsQuantity(String quantity) {
        quantity_wanted.clear();
        quantity_wanted.sendKeys(quantity);
    }

    public void userSetsSize(String size) {
        Select drpSize = new Select(setSize);
        drpSize.selectByVisibleText(size);

    }

    public void userChooseWhiteColor() {
        whiteColor.click();
    }

    public void userAddToCart() {
        add_to_cart.click();
    }

    public shoppingCartSumary userClicksCheckOutBtn() {
        checkOut.click();
        return new shoppingCartSumary(driver);
    }

    public void userClicksAddToWishlist(String n) {
        List<WebElement> addWishListList = addWishList;
        WebElement position = addWishListList.get(Integer.parseInt(n));
        Actions action = new Actions(driver);
        action.moveToElement(position).perform();
        action.click().build().perform();
    }

    public boolean userGetsError() {
        return fancyError.isDisplayed();
    }

    public String userGetsErrorMsg() {
        return fancyError.getText();
    }

    public String userGetsUnitPrice() {
        return StringUtils.substringAfterLast(price.getText(), "$");
    }

}
