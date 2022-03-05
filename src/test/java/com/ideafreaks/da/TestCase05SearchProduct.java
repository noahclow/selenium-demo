package com.hexaware.da;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.landingPage;
import pageObject.searchPage;
import pageObject.tshirtsPage;
import resources.Base;
import resources.ExcelUtil;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class TestCase05SearchProduct extends Base {
    public WebDriver driver;
    public String sheetName = "TC05";
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void setup() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "TC05")
    public void searchProduct(String baseUrl){

        landingPage home = new landingPage(driver);

        driver.get(baseUrl);
        log.info("1. Open this url " + baseUrl);
        home.moveToWomenSection();
        log.info("2. Move your cursor over Women's link");
        tshirtsPage tshirts = home.moveToWomenTshirts();
        log.info("3. Click on sub menu 'T-shirts'");
        String productName = tshirts.productName();
        String price = tshirts.price();
        log.info("4. Get Name/Text of the first product displayed on the page");
        tshirts.userTypesProductToSearch(productName);
        searchPage search = tshirts.userClicksSearchBtn();
        log.info("5. Now enter the same product name in the search bar present on top of page and click search button");
        String searchedProduct = search.searchResults();
        String searchedPrice = search.price();
        StringBuilder sb = new StringBuilder("\"");
        productName = (sb + productName + sb);
        assertEquals(productName, searchedProduct);
        assertEquals(price, searchedPrice);
        log.info("6. Validate that same product is displayed on searched page with same details which were displayed on\n" +
                "T-Shirt's page");

    }

    @DataProvider(name = "TC05")
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
