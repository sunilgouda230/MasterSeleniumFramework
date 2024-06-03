package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.selenium.pom.constants.DriverType;

public class DriverManager {

    WebDriver driver;
    public  WebDriver initializeDriver(String browser) {
        switch (DriverType.valueOf(browser)){
            case CHROME:
                WebDriverManager.chromedriver().cachePath("Driver").setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().cachePath("Driver").setup();
                driver = new FirefoxDriver();
                break;
            case SAFARI:
                WebDriverManager.safaridriver().cachePath("Driver").setup();
                driver = new SafariDriver();
                break;
            default :
                System.out.println(browser +" Driver Not Found Staring the Chrome Driver");
                WebDriverManager.chromedriver().cachePath("Driver").setup();
                driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}
