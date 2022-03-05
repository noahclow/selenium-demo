package com.hexaware.da;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.landingPage;
import pageObject.summerDressesPage;
import resources.Base;
import resources.ExcelUtil;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestCase07WishList extends Base {

    public WebDriver driver;
    public String sheetName = "TC07";
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void setup() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "TC07")
    public void wishList(String baseUrl,
                         String errorMsgTxt,
                         String dressPosition) {

        landingPage home = new landingPage(driver);

        driver.get(baseUrl);
        log.info("1. Open link " + baseUrl);
        home.moveToWomenSection();
        log.info("2. Move your cursor over Women's link");
        summerDressesPage dressesPage = home.moveSummerDresses();
        log.info("3. Click on sub menu 'Summer Dresses'");
        dressesPage.userSelectsDress(dressPosition);
        log.info("4. Mouse hover on the second product displayed");
        dressesPage.userClicksAddToWishlist(dressPosition);
        log.info("5. 'Add to Wishlist' will appear on the bottom of that product, click on it.");
        assertTrue(dressesPage.userGetsError());
        log.info("Error Msg " + dressesPage.userGetsErrorMsg());
        assertEquals(dressesPage.userGetsErrorMsg(), errorMsgTxt);
        log.info("6. Verify that error message is displayed 'You must be logged in to manage your wish list.'");

    }

    @DataProvider(name = "TC07")
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
