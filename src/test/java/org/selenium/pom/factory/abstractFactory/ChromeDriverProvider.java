package org.selenium.pom.factory.abstractFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverProvider extends AbstractDriverFactory{

    @Override
    public WebDriver getDriver() {
        return new ChromeDriver();
    }
}
