package resources;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class Base {
    public WebDriver driver;
    public Properties prop;

    public WebDriver initializeDriver() throws IOException {
        Logger log = LogManager.getLogger(Base.class.getName());

        //chrome
        //firefox
        prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/resources/data.properties");
        prop.load(fileInputStream);

        //mvn test -Dbrowser=<browserName>

        String browserName = System.getProperty("browser"); // to run from maven

        //String browserName = prop.getProperty("browser"); // for use from the data.properties

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/driver/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            driver = new ChromeDriver();
            log.info("Browser is initialized = " + browserName);

        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/main/driver/geckodriver");
            driver = new FirefoxDriver();
            log.info("Browser is initialized = " + browserName);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        return driver;
    }

    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String destinationFile = System.getProperty("user.dir") + "/reports/" + (formatter.format(date)) + " - " + testCaseName + ".png";

        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }
}
