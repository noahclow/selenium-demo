package com.hexaware.da;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.landingPage;
import pageObject.loginPage;
import resources.Base;
import resources.ExcelUtil;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class TestCase02InvalidEmail extends Base {
    public WebDriver driver;
    public String sheetName = "TC02";
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void setupBrowser() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "tc02")
    public void userTypesAnInvalidEmailForCreateAnAccount(String baseUrl,
                                                          String invalidEmail) {
        landingPage homePage = new landingPage(driver);

        driver.get(baseUrl);
        log.info("1. Open this url " + baseUrl);
        assertTrue(homePage.verifySignInLink());
        loginPage AuthenticationPage = homePage.userClicksOnSignInLink();
        log.info("2. Click on sign in link");
        assertTrue(AuthenticationPage.verifyEmailCreateField());
        AuthenticationPage.userTypesAnEmail(invalidEmail);
        assertTrue(AuthenticationPage.verifyCreateAccountBtn());
        AuthenticationPage.userClicksOnCreateAccountBtn();
        log.info("3. Enter invalid email address in the email box and click enter");
        assertTrue(AuthenticationPage.createAccountErrorIsDisplayed());
        String expectedText = "Invalid email address.";
        Assert.assertEquals(expectedText, AuthenticationPage.createAccountErrorMsg());
        log.info("4. Validate that an error message is displaying saying \"Invalid email address.\"");

    }

    @DataProvider(name = "tc02")
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
