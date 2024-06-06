package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
    public WebDriver driver;

    public WebDriver getDriverBrowser(String browserName) {
//        if (browserName.equals("firefox")) {
//            driver = new FirefoxDriver();
//        } else if (browserName.equals("chrome")) {
//            driver = new ChromeDriver();
//        } else if (browserName.equals("edge")) {
//            driver = new EdgeDriver();
//        } else {
//            throw new RuntimeException("Browser name invalid");
//        }
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                      break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser name invalid");
        }
        driver.get("https://demo.nopcommerce.com/");
//        driver.get("https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/");


        return driver;

    }
}
