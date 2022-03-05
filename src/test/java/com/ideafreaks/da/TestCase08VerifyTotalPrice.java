package com.hexaware.da;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.surefire.shared.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.landingPage;
import pageObject.loginPage;
import pageObject.shoppingCartSumary;
import pageObject.summerDressesPage;
import resources.Base;
import resources.ExcelUtil;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class TestCase08VerifyTotalPrice extends Base {

    public WebDriver driver;
    public String sheetName = "TC08";
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void setup() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "TC08")
    public void verifyTotalPrice(String baseUrl,
                                 String userEmail,
                                 String userPassword,
                                 String dressPosition,
                                 String size,
                                 String quantity,
                                 String incressQuantity) {

        landingPage home = new landingPage(driver);

        driver.get(baseUrl);
        log.info("1. Open link" + baseUrl);
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
        log.info("7. Make sure quantity is set to 1.");
        dressesPage.userSetsSize(size);
        log.info("8. Select size" + size);
        dressesPage.userChooseWhiteColor();
        log.info("9. Select color of your choice.");
        String unitPrice = dressesPage.userGetsUnitPrice();
        log.info("Unit price = " + unitPrice);
        dressesPage.userAddToCart();
        log.info("10. Click 'Add to Cart' button");
        shoppingCartSumary cartSumary = dressesPage.userClicksCheckOutBtn();
        log.info("11. Click 'Proceed to checkout' button");
        cartSumary.userChangeQuantity(incressQuantity);
        log.info("12. Change the quantity to 2.");
        int num = Integer.parseInt(incressQuantity);
        log.info("Quantity = " + num);
        float f = Float.parseFloat(unitPrice);
        log.info("Unit Price = " + f);
        float result = num * f;
        log.info("Total calculated = " + result);
        String productAmount = cartSumary.userGetsProductAmountTotal(result);
        productAmount = StringUtils.substringAfterLast(productAmount, "$");
        log.info("Total visible= " + productAmount);
        float ff = Float.parseFloat(productAmount);
        assertEquals(ff, result);
        log.info("13. Verify that Total price is changing and reflecting correct price.");

    }

    @DataProvider(name = "TC08")
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
