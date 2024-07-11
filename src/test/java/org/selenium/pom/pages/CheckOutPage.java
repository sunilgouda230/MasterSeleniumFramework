package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

import java.util.List;

public class CheckOutPage extends BasePage {

    private final By enterFirstNameFld = By.name("billing_first_name");
    private final By enterLastNameFld = By.name("billing_last_name");
    private final By enterAddressFld = By.id("billing_address_1");
    private final By enterSecondAddressFld = By.id("billing_address_2");
    private final By enterBillingCity = By.id("billing_city");
    private final By enterPostalCode = By.id("billing_postcode");
    private final By enterEmail = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");

    private final By checkoutLoginBtn = By.xpath("//a[contains(text(),'Click here to login')]");

    private final By usernameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By loginBtn = By.name("login");

    private final By clickHereloginLink = By.xpath("//a[@class='showlogin']");

    private final By overlay = By.xpath("//div[@class='blockUI blockOverlay']");
    private final By selectStateOrCountryBydropDown = By.xpath("//span[@class='select2-results']//li");
    private final By dropDownArrayCountry = By.xpath("(//span[@class='select2-selection__arrow'])[1]");
    private final By dropDownArrayState = By.xpath("(//span[@class='select2-selection__arrow'])[2]");
    private final By selectDirectBankTransfer = By.id("payment_method_bacs");
    private final By productName = By.xpath("//td[@class='product-name']");

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public CheckOutPage load(){
        load("/checkout/");
        return this;
    }


    public CheckOutPage checkOutPageLoginBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutLoginBtn)).click();
        return this;
    }

    public CheckOutPage enterUserName(String username){
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFld)).sendKeys(username);
        return this;
    }

    public CheckOutPage clickLoginBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }

    public CheckOutPage clickHereToLoginLink(){
        wait.until(ExpectedConditions.elementToBeClickable(clickHereloginLink)).click();
        return this;
    }

    public CheckOutPage enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld)).sendKeys(password);
        return this;
    }

    public CheckOutPage login(User user){
        return enterUserName(user.getUsername()).
                enterPassword(user.getPassword()).
                clickLoginBtn();
    }

    public CheckOutPage enterFirstName(String firstName){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(enterFirstNameFld));
        element.clear();
        element.sendKeys(firstName);
        return this;
    }

    public CheckOutPage enterLastNameFld(String lastName){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(enterLastNameFld));
        element.clear();
        element.sendKeys(lastName);
        return this;
    }

    public CheckOutPage firstAddress(String firstAddress){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(enterAddressFld));
        element.clear();
        element.sendKeys(firstAddress);
        return this;
    }

    public CheckOutPage enterSecondAddressFld(String secondAddress){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(enterSecondAddressFld));
        element.clear();
        element.sendKeys(secondAddress);
        return this;
    }

    public CheckOutPage enterPostalCode(String postalcode){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(enterPostalCode));
        element.clear();
        element.sendKeys(postalcode);
        return this;
    }

    public CheckOutPage enterEmail(String email){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(enterEmail));
        element.clear();
        element.sendKeys(email);
        return this;
    }

    public CheckOutPage enterCity(String city){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(enterBillingCity));
        element.clear();
        element.sendKeys(city);
        return this;
    }

    public CheckOutPage placeOrder(){
        waitForTheOverlaysDisappear(overlay);
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
        return this;
    }

    public CheckOutPage selectStateorCountryByDropDown(String stateorcountry, String dropdownfor){
        if (dropdownfor.equals("Country")){
            wait.until(ExpectedConditions.elementToBeClickable(dropDownArrayCountry)).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(dropDownArrayState)).click();
        }

        List<WebElement> selectState = driver.findElements(selectStateOrCountryBydropDown);
        for (WebElement stateorcountryvalue: selectState
             ) {
            if (stateorcountryvalue.getText().equals(stateorcountry)){
                System.out.println("Country Or State Present in DropDown and selected");
                wait.until(ExpectedConditions.elementToBeClickable(stateorcountryvalue)).click();
                break;
            } else {
                System.out.println("Country Or State is not Present in DropDown ");
            }
        }
        return this;
    }


    public CheckOutPage setBillingAddress(BillingAddress billingAddress){
        return enterCity(billingAddress.getCity())
                .enterPostalCode(billingAddress.getPostalCode()).
                firstAddress(billingAddress.getAdressLineOne()).
                selectStateorCountryByDropDown(billingAddress.getCountry(), "Country").
                enterSecondAddressFld(billingAddress.getAdressLineTwo()).
                selectStateorCountryByDropDown(billingAddress.getState(), "State").
                enterEmail(billingAddress.getEmail())
                .enterFirstName(billingAddress.getFirstName()).
                enterLastNameFld(billingAddress.getLastName());
    }

    private By getOrderReceiveMessage(){
        return By.xpath("//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']");
    }

    public String viewOrderReceiveMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getOrderReceiveMessage())).getText();
    }

    public CheckOutPage selectDirectBank(){
        WebElement e = driver.findElement(selectDirectBankTransfer);
        if (!e.isSelected()){
            e.click();
        }
        return this;
    }

    public String getProductItem(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }


}
