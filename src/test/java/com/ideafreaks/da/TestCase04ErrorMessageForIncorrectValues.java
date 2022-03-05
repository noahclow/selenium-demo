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
import resources.Base;
import resources.ExcelUtil;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class TestCase04ErrorMessageForIncorrectValues extends Base {
    public WebDriver driver;
    public String sheetName = "TC04";
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void setupBrowser() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "TC04")
    public void errorMsgForIncorrectValues(String baseUrl,
                                           String email,
                                           String firstname,
                                           String lastname,
                                           String password,
                                           String address,
                                           String postalCode,
                                           String mobilePhone,
                                           String invalidEmail) {

        landingPage homePage = new landingPage(driver);

        //driver.get(prop.getProperty("url"));
        driver.get(baseUrl);
        log.info("1. Open this url" + baseUrl);
        assertTrue(homePage.verifySignInLink());
        loginPage login = homePage.userClicksOnSignInLink();
        log.info("2. Click on sign in link");
        assertTrue(login.verifyEmailCreateField());
        login.userTypesAnEmail(email);
        assertTrue(login.verifyCreateAccountBtn());
        createAccountPage createAccount = login.userClicksOnCreateAccountBtn();
        log.info("3. Enter email address and click Register button");
        createAccount.userTypesFirstName(firstname);
        createAccount.userTypesLastName(lastname);
        createAccount.userTypesEmail(invalidEmail);
        createAccount.userTypesAddress(address);
        createAccount.userTypesPostcode(postalCode);
        createAccount.userTypesMobilephone(mobilePhone);
        createAccount.userCliksRegisterBtn();
        log.info("4. Enter incorrect values in fields like., enter numbers in first and last name, city field etc., and enter\n" +
                "alphabets in Mobile no, Zip postal code etc., and click on 'Register' button");
        assertTrue(createAccount.errorAlert());
        assertTrue(createAccount.errorFirstname());
        assertTrue(createAccount.errorLastname());
        assertTrue(createAccount.errorEmail());
        assertTrue(createAccount.errorPassword());
        assertTrue(createAccount.errorAddress());
        assertTrue(createAccount.errorPostalcode());
        assertTrue(createAccount.errorMobilePhoneInvalid());
        log.info("5. Verify that error messages for respective fields are displaying");
    }

    @DataProvider(name = "TC04")
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
