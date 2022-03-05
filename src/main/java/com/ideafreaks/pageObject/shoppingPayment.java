package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shoppingPayment {
    public WebDriver driver;

    public shoppingPayment(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[title='Pay by bank wire']")
    private WebElement payByBankWire;

    public shoppingOrderSummary userSelectsPayByBankWire() {
        payByBankWire.click();
        return new shoppingOrderSummary(driver);
    }

}
