package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shoppingShipping {
    public WebDriver driver;

    public shoppingShipping(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "cgv")
    private WebElement termsAndConditions;

    @FindBy(css = "button[name='processCarrier'] span")
    private WebElement shippingCheckout;

    public void userAcceptsTermsAndConditions() {
        termsAndConditions.click();
    }

    public shoppingPayment userClicksProceedToCheckout() {
        shippingCheckout.click();
        return new shoppingPayment(driver);
    }

}
