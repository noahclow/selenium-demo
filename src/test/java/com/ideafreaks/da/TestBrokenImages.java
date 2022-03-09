package com.ideafreaks.da;

import com.ideafreaks.pageObject.BrokenImages;
import com.ideafreaks.resources.Base;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestBrokenImages extends Base {
    public WebDriver driver;
    public String status;
    String URL = "https://the-internet.herokuapp.com/broken_images";
    public static Logger log = LogManager.getLogger(Base.class.getName());


    @BeforeTest
    public void setupBrowser() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(URL);
        log.info("Open " + URL);

    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

    @Test(description = "Approach 1: Find broken images on a web page using Selenium WebDriver verify HTTP code", enabled = true)
    protected void verifyBrokenImagesHttpCode() {
          BrokenImages BrokenImages = new BrokenImages(driver);
        int iBrokenImageCount = 0;


        try {
            /* Print the total number of images on the page */
            System.out.println("The page under test has " + BrokenImages.allImages().size() + " images");
            log.info("The page under test has " + BrokenImages.allImages().size() + " images");
            for (WebElement img : BrokenImages.allImages()) {
                if (img != null) {
                    HttpClient client = HttpClientBuilder.create().build();
                    HttpGet request = new HttpGet(img.getAttribute("src"));
                    HttpResponse response = client.execute(request);
                    /* For valid images, the HttpStatus will be 200 */
                    if (response.getStatusLine().getStatusCode() != 200) {
                        System.out.println(img.getAttribute("outerHTML") + " is broken.");
                        log.info(img.getAttribute("outerHTML") + " is broken.");
                        //System.out.println(response.getStatusLine().getStatusCode());
                        iBrokenImageCount++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = "failed";
            System.out.println(e.getMessage());
        }
        status = "passed";
        System.out.println("The page " + URL + " has " + iBrokenImageCount + " broken images");
    }

    @Test(description="Approach 2: Find broken images on a web page using Selenium WebDriver", enabled = true)
    protected void verifyBrokenImages()
    {
      BrokenImages BrokenImages = new BrokenImages(driver);
        int iBrokenImageCount = 0;

        try
        {
            /* Print the total number of images on the page */
            System.out.println("The page under test has " + BrokenImages.allImages().size() + " images");
            for (WebElement img : BrokenImages.allImages())
            {
                if (img != null)
                {
                    if (img.getAttribute("naturalWidth").equals("0"))
                    {
                        System.out.println(img.getAttribute("outerHTML") + " is broken.");
                        iBrokenImageCount++;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status = "failed";
            System.out.println(e.getMessage());
        }
        status = "passed";
        System.out.println("The page " + URL + " has " + iBrokenImageCount + " broken images");
    }
}
