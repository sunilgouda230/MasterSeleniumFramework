package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.utils.CookieUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.List;

public class BaseTest {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    protected WebDriver getDriver(){
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser){
              // browser = System.getProperty("browser", browser);
        if (browser == null) {
            browser = "CHROME";
        }
        setDriver(new DriverManager().initializeDriver(browser));
        System.out.println("CURRENT THREAD "+ Thread.currentThread().getId() + " ,"+ getDriver());
    }

    @AfterMethod
    public void quitDriver(){
        System.out.println("CURRENT THREAD "+ Thread.currentThread().getId() + " , "+ getDriver());
        getDriver().quit();
    }

    public void injectCookieToTheBrowser(Cookies cookies){
        List<Cookie> seleniumCookie = new CookieUtils().convertRestAssuredCookieToSeleniumCookie(cookies);
        for (Cookie c: seleniumCookie
             ) {
            getDriver().manage().addCookie(c);
        }
    }
}
