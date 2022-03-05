package com.hexaware.da;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.createAccountPage;
import pageObject.landingPage;
import pageObject.loginPage;
import pageObject.myAccountPage;
import resources.Base;
import resources.ExcelUtil;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestCase01RegisterUser extends Base {
    public WebDriver driver;
    public String sheetName = "TC01";
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void setupBrowser() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "tc01")
    public void registerUser(String baseUrl,
                             String email,
                             String firstname,
                             String lastname,
                             String password,
                             String address,
                             String city,
                             String state,
                             String postalCode,
                             String mobilePhone,
                             String addressAlias
    ) {

        landingPage homePage = new landingPage(driver);

        //driver.get(prop.getProperty("url"));
        driver.get(baseUrl);
        log.info("1. Open this url " + baseUrl);
        assertTrue(homePage.verifySignInLink());
        loginPage loginPage = homePage.userClicksOnSignInLink();
        log.info("2. Click on sign in link");
        assertTrue(loginPage.verifyEmailCreateField());
        loginPage.userTypesAnEmail(email);
        log.info("3. Enter your email address in 'Create and account' section");
        assertTrue(loginPage.verifyCreateAccountBtn());
        createAccountPage createAccountForm = loginPage.userClicksOnCreateAccountBtn();
        log.info("4. Click on Create an Account button");
        createAccountForm.userTypesFirstName(firstname);
        createAccountForm.userTypesLastName(lastname);
        createAccountForm.userTypesPassword(password);
        createAccountForm.userTypesAddress(address);
        createAccountForm.userTypesCity(city);
        createAccountForm.userSelectsState(state);
        createAccountForm.userTypesPostcode(postalCode);
        createAccountForm.userTypesMobilephone(mobilePhone);
        createAccountForm.userTypesAddressAlias(addressAlias);
        log.info("5. Enter your Personal Information, Address and Contact info");
        myAccountPage myAccount = createAccountForm.userCliksRegisterBtn();
        log.info("6. Click on Register button");
        String expectedText = (firstname + " " + lastname);
        assertEquals(expectedText, myAccount.userAccountName());
        log.info("7. Validate that user is created");

    }

    @DataProvider(name = "tc01")
    public Object[][] getData() throws IOException {
        String path = prop.getProperty("excelPath");
        ExcelUtil xlsx = new ExcelUtil(path);
        int totalRows = xlsx.getRowCount(sheetName);
        int totalColumns = xlsx.getCellCount(sheetName, 1);
        String[][] data = new String[totalRows][totalColumns];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                data[i - 1][j] = xlsx.getCellData(sheetName, i, j);
            }
        }
        return data;
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

}
