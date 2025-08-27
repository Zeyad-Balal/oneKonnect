package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Base_Tests {

    protected WebDriver driver;
    protected Properties prop;

    public Base_Tests() {
        try {
            prop = new Properties();
            String configPath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
            FileInputStream fis = new FileInputStream(configPath);
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUp() {
        String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get(prop.getProperty("baseUrl") + "Account/Login");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}