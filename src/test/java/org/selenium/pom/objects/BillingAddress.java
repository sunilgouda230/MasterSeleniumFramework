package org.selenium.pom.objects;

public class BillingAddress {
    private String firstName;
    private String lastName;
    private String adressLineOne;
    private String adressLineTwo;
    private String postalCode;
    private String email;
    private String city;
    private String country;

    private String state;

    public BillingAddress(){}

    public BillingAddress(String firstName, String lastName, String postalCode, String city, String adressLineOne,
                          String adressLineTwo, String email){
        this.firstName=firstName;
        this.lastName= lastName;
        this.city=city;
        this.adressLineOne=adressLineOne;
        this.adressLineTwo=adressLineTwo;
        this.email=email;
        this.postalCode=postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public BillingAddress setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAdressLineOne() {
        return adressLineOne;
    }

    public BillingAddress setAdressLineOne(String adressLineOne) {
        this.adressLineOne = adressLineOne;
        return this;
    }

    public String getAdressLineTwo() {
        return adressLineTwo;
    }

    public BillingAddress setAdressLineTwo(String adressLineTwo) {
        this.adressLineTwo = adressLineTwo;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public BillingAddress setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddress setEmail(String email) {
        this.email = email;
        return this;
    }
}
