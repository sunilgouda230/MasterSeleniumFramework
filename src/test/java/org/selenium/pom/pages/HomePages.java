package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class HomePages extends BasePage {

    private final By storeMenuLink = By.xpath("//li[@id='menu-item-1227']/a[contains(text(),'Store')]");

    public HomePages(WebDriver driver) {
        super(driver);
    }


    public HomePages load(){
        load("");
        return this;
    }

    public StorePage navigateToStoreUsingMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
        return new StorePage(driver);
    }

    public WebElement logo(){
        return driver.findElement(By.xpath("//a[@class='header-brand-site-logo-container__link']"));
    }

    public HomePages verifyTitle(){
        wait.until(ExpectedConditions.visibilityOf(logo()));
        return this;
    }
}
