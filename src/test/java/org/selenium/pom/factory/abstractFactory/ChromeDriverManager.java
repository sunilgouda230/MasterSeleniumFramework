package org.selenium.pom.factory.abstractFactory;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManagerAbstract{
    @Override
    protected void startDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
}
