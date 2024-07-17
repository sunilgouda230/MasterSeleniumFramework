package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.constants.EndPoint;

import java.util.List;

public class CartPage extends BasePage {
    private final By checkOutBtn = By.xpath("//a[normalize-space()='Proceed to checkout']");
    private final By itemsInCart = By.xpath("//table[@class='shop_table shop_table_responsive cart woocommerce-cart-form__contents']//following-sibling::tr[contains(@class,'cart_item')]/td[@class='product-name']/a");

    public CartPage(WebDriver driver){
        super(driver);
    }

    public CheckOutPage checkout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkOutBtn)).click();
        return new CheckOutPage(driver);
    }

    private By getProductTitle(){
        return By.xpath("//td[@class='product-name']/a");
    }

    public String viewProductTitle(){
        By productItem = getProductTitle();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productItem)).getText();
    }

    public CartPage verifyProductInCart(){
        List<WebElement> items = driver.findElements(itemsInCart);
        wait.until(ExpectedConditions.visibilityOfAllElements(items));
        return this;
    }
}
