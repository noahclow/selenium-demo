package pageObject;

import org.apache.maven.surefire.shared.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shoppingOrderSummary {
    public WebDriver driver;

    public shoppingOrderSummary(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[class='button btn btn-default button-medium'] span")
    private WebElement confirmOrder;

    @FindBy(xpath = "//div[@class='box']")
    private WebElement orderSummary;

    public void userClicksIConfirmMyOrder() {
        confirmOrder.click();
    }

    public String orderReference() {
        String summary = orderSummary.getText();
        summary = StringUtils.substringAfterLast(summary, "- Do not forget to insert your order reference ");
        summary = StringUtils.substringBeforeLast(summary, " in the subject of your bank wire.");
        return summary;
    }

}
