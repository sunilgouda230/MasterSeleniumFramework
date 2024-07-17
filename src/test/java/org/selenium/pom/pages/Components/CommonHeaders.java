package org.selenium.pom.pages.Components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.HomePages;
import org.selenium.pom.pages.StorePage;

public class CommonHeaders extends BasePage {

    private final By storeMenuLink = By.xpath("//li[@id='menu-item-1227']/a[contains(text(),'Store')]");

    public CommonHeaders(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
        return new StorePage(driver);
    }

    public WebElement logo(){
        return driver.findElement(By.xpath("//a[@class='header-brand-site-logo-container__link']"));
    }

    public CommonHeaders verifyTitle(){
        wait.until(ExpectedConditions.visibilityOf(logo()));
        return this;
    }
}
