package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class createAccountPage {
    public WebDriver driver;

    public createAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "customer_firstname")
    private WebElement firstNameField;

    @FindBy(id = "customer_lastname")
    private WebElement lastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "address1")
    private WebElement addressField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "id_state")
    private WebElement stateDropdown;

    @FindBy(id = "postcode")
    private WebElement postcodeField;

    @FindBy(id = "phone_mobile")
    private WebElement phoneMobileField;

    @FindBy(id = "alias")
    private WebElement addressAliasField;

    @FindBy(id = "submitAccount")
    private WebElement submitAccountBtn;

    @FindBy(xpath = "//div[@class='alert alert-danger']")
    private WebElement getErrorAlert;

    @FindBy(xpath = "//li[normalize-space()='You must register at least one phone number.']")
    private WebElement errorAlertPhone;

    @FindBy(xpath = "//b[normalize-space()='lastname']")
    private WebElement errorAlertLastname;

    @FindBy(xpath = "//b[normalize-space()='firstname']")
    private WebElement errorAlertFirstname;

    @FindBy(xpath = "//b[normalize-space()='passwd']")
    private WebElement errorAlertPassword;

    @FindBy(xpath = "//b[normalize-space()='address1']")
    private WebElement errorAlertAddress;

    @FindBy(xpath = "//b[normalize-space()='city']")
    private WebElement errorAlertCity;

    @FindBy(xpath = "//li[contains(text(),'Zip/Postal')]")
    private WebElement errorAlertPostalcode;

    @FindBy(xpath = "//li[contains(text(),'country requires')]")
    private WebElement errorAlertState;

    @FindBy(xpath = "//b[normalize-space()='email']")
    private WebElement errorAlertEmail;

    @FindBy(xpath = "//b[normalize-space()='phone_mobile']")
    private WebElement errorAlertMobilePhoneInvalid;

    public void userTypesFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void userTypesLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void userTypesEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void userTypesPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void userTypesAddress(String address) {
        addressField.sendKeys(address);
    }

    public void userTypesCity(String city) {
        cityField.sendKeys(city);
    }

    public Select stateDropdown() {
        return new Select(stateDropdown);
    }

    public void userSelectsState(String state) {
        stateDropdown().selectByVisibleText(state);
    }

    public void userTypesPostcode(String postcode) {
        postcodeField.sendKeys(postcode);
    }

    public void userTypesMobilephone(String mobile) {
        phoneMobileField.sendKeys(mobile);
    }

    public void userTypesAddressAlias(String alias) {
        addressAliasField.clear();
        addressAliasField.sendKeys(alias);
    }

    public myAccountPage userCliksRegisterBtn() {
        submitAccountBtn.click();
        return new myAccountPage(driver);
    }

    public boolean errorAlert() {
        return getErrorAlert.isDisplayed();
    }

    public boolean errorPhone() {
        return errorAlertPhone.isDisplayed();
    }

    public String errorPhoneMsg() {
        return errorAlertPhone.getText();
    }

    public boolean errorLastname() {
        return errorAlertLastname.isDisplayed();
    }

    public boolean errorFirstname() {
        return errorAlertFirstname.isDisplayed();
    }

    public boolean errorPassword() {
        return errorAlertPassword.isDisplayed();
    }

    public boolean errorAddress() {
        return errorAlertAddress.isDisplayed();
    }

    public boolean errorCity() {
        return errorAlertCity.isDisplayed();
    }

    public boolean errorPostalcode() {
        return errorAlertPostalcode.isDisplayed();
    }

    public boolean errorState() {
        return errorAlertState.isDisplayed();
    }

    public boolean errorEmail() {
        return errorAlertEmail.isDisplayed();
    }

    public boolean errorMobilePhoneInvalid() {
        return errorAlertMobilePhoneInvalid.isDisplayed();
    }

}
