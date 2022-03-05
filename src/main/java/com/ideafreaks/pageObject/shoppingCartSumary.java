package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class shoppingCartSumary {
    public WebDriver driver;

    public shoppingCartSumary(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[class='button btn btn-default standard-checkout button-medium'] span")
    private WebElement summaryCheckout;

    @FindBy(xpath = "//input[@class='cart_quantity_input form-control grey']")
    private WebElement orderQuantity;

    @FindBy(xpath = "//span[contains(@id, 'total_product_price')][1]")
    private WebElement productAmountTotal;

    @FindBy(xpath = "//td[@class='cart_total']")
    private List<WebElement> productAmountTotals;

    public shoppingAddress userClicksProceedToCheckout() {
        summaryCheckout.click();
        return new shoppingAddress(driver);
    }

    public void userChangeQuantity(String n) {
        orderQuantity.clear();
        orderQuantity.sendKeys(n);
    }

    public String userGetsProductAmountTotal(float result) {

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@id, 'total_product_price')][contains(text()," + result + ")]")));
        return productAmountTotal.getText();
    }


}
