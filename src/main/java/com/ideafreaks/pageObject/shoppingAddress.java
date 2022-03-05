package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shoppingAddress {
    public WebDriver driver;

    public shoppingAddress(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[name='processAddress'] span")
    private WebElement processAddress;

    public shoppingShipping userClicksProceedToCheckout() {
        processAddress.click();
        return new shoppingShipping(driver);
    }

}
