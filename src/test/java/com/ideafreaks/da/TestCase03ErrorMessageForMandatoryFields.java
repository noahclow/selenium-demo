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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestCase03ErrorMessageForMandatoryFields extends Base {
    public WebDriver driver;
    public String sheetName = "TC03";
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void setupBrowser() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "tc03")
    public void errorMessageForMandatoryFields(String baseUrl,
                             String email) {

        landingPage homePage = new landingPage(driver);

        //driver.get(prop.getProperty("url"));
        driver.get(baseUrl);
        log.info("1. Open this url" + baseUrl);
        assertTrue(homePage.verifySignInLink());
        loginPage login = homePage.userClicksOnSignInLink();
        log.info("2. Click on sign in link");
        login.userTypesAnEmail(email);
        createAccountPage createAccountForm = login.userClicksOnCreateAccountBtn();
        log.info("3. Enter email address and click Register button");
        createAccountForm.userCliksRegisterBtn();
        log.info("4. Leave the mandatory fields (marked with *) blank and click Register button");
        assertTrue(createAccountForm.errorAlert());
        assertTrue(createAccountForm.errorPhone());
        String expectedText = "You must register at least one phone number.";
        assertEquals(expectedText, createAccountForm.errorPhoneMsg());
        assertTrue(createAccountForm.errorLastname());
        assertTrue(createAccountForm.errorFirstname());
        assertTrue(createAccountForm.errorPassword());
        assertTrue(createAccountForm.errorAddress());
        assertTrue(createAccountForm.errorCity());
        assertTrue(createAccountForm.errorPostalcode());
        assertTrue(createAccountForm.errorState());
        log.info("5. Verify that error has been displayed for the mandatory fields");

    }

    @DataProvider(name = "tc03")
    public Object[][] getData() throws IOException{
        String path = prop.getProperty("excelPath");
        ExcelUtil xlsx = new ExcelUtil(path);
        int totalRows = xlsx.getRowCount(sheetName);
        int totalColumns = xlsx.getCellCount(sheetName, 1);
        String[][] data = new String[totalRows][totalColumns];

        for (int i = 1; i <= totalRows; i++){
            for (int j = 0; j < totalColumns; j++){
                data[i-1][j] = xlsx.getCellData(sheetName, i , j);
            }
        }
        return data;
    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }

}
