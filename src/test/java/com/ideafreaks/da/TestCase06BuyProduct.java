package com.hexaware.da;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.*;
import resources.Base;
import resources.ExcelUtil;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class TestCase06BuyProduct extends Base {
    public WebDriver driver;
    public String sheetName = "TC06";
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void setup() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");

    }

    @Test(dataProvider = "TC06")
    public void buyProduct(String baseUrl,
                           String userEmail,
                           String userPassword,
                           String quantity,
                           String size,
                           String dressPosition) {

        landingPage home = new landingPage(driver);
        myAccountPage myAccount = new myAccountPage(driver);

        driver.get(baseUrl);
        log.info("1. Open this url " + baseUrl);
        loginPage loginPage = home.userClicksOnSignInLink();
        loginPage.userTypesLoginEmail(userEmail);
        loginPage.userTypesLoginPassword(userPassword);
        loginPage.userClicksSignInButton();
        log.info("2. Login to the website.");
        home.moveToWomenSection();
        log.info("3. Move your cursor over Women's link");
        summerDressesPage dressesPage = home.moveSummerDresses();
        log.info("4. Click on sub menu 'Summer Dresses'");
        dressesPage.userSelectsDress(dressPosition);
        log.info("5. Mouse hover on the second product displayed");
        dressesPage.userClicksMoreBtn(dressPosition);
        log.info("6. 'More' button will be displayed, click on 'More' button");
        dressesPage.userSetsQuantity(quantity);
        log.info("7. Increase quantity to 2");
        dressesPage.userSetsSize(size);
        log.info("8. Select size 'L'");
        dressesPage.userChooseWhiteColor();
        log.info("9. Select color");
        dressesPage.userAddToCart();
        log.info("10. Click 'Add to Cart' button");
        shoppingCartSumary cartSummary = dressesPage.userClicksCheckOutBtn();
        log.info("11. Click 'Proceed to checkout' button");
        shoppingAddress cartAddress = cartSummary.userClicksProceedToCheckout();
        shoppingShipping cartShipping = cartAddress.userClicksProceedToCheckout();
        cartShipping.userAcceptsTermsAndConditions();
        shoppingPayment cartPayment = cartShipping.userClicksProceedToCheckout();
        shoppingOrderSummary cartConfirmOrder = cartPayment.userSelectsPayByBankWire();
        cartConfirmOrder.userClicksIConfirmMyOrder();
        log.info("12. Complete the buy order process till payment");
        log.info("Order Reference in the summary = " + cartConfirmOrder.orderReference());
        String orderReference = cartConfirmOrder.orderReference();
        myAccount.userClicksOnTheirAccount();
        myAccount.userClicksOnOrderHistory();
        log.info("Order Reference in orders table = " + myAccount.userGetsLastOrder());
        assertEquals(orderReference, myAccount.userGetsLastOrder());
        log.info("13. Make sure that Product is ordered");

    }

    @DataProvider(name = "TC06")
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
