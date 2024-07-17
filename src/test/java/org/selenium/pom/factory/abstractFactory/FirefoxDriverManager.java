package org.selenium.pom.factory.abstractFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManagerAbstract {

    @Override
    protected void startDriver() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
}
