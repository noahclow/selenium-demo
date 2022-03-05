package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landingPage {
    public WebDriver driver;

    public landingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInLink;

    @FindBy(xpath = "//a[@title='Women']")
    private WebElement womenSection;

    @FindBy(css = "li[class='sfHover'] a[title='T-shirts']")
    private WebElement womenTshirts;

    @FindBy(css = "li[class='sfHover'] a[title='Summer Dresses']")
    private WebElement summerDresses;

    public boolean verifySignInLink() {
        return signInLink.isDisplayed();
    }

    public loginPage userClicksOnSignInLink() {
        signInLink.click();
        return new loginPage(driver);
    }

    public void moveToWomenSection() {
        Actions action = new Actions(driver);
        action.moveToElement(womenSection).perform();
    }

    public tshirtsPage moveToWomenTshirts() {
        Actions action = new Actions(driver);
        action.moveToElement(womenTshirts).perform();
        action.click().build().perform();
        return new tshirtsPage(driver);
    }

    public summerDressesPage moveSummerDresses() {
        Actions action = new Actions(driver);
        action.moveToElement(summerDresses).perform();
        action.click().build().perform();
        return new summerDressesPage(driver);
    }

}
