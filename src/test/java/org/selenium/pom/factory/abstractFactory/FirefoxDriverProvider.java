package org.selenium.pom.factory.abstractFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverProvider extends AbstractDriverFactory {

    @Override
    public WebDriver getDriver() {
        return new FirefoxDriver();
    }
}
