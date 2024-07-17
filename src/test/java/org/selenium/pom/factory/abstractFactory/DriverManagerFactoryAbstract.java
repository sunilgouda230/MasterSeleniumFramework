package org.selenium.pom.factory.abstractFactory;

import org.selenium.pom.constants.DriverType;

public class DriverManagerFactoryAbstract {

    public static DriverManagerAbstract getManager(DriverType driverType){
        switch (driverType){
            case CHROME:
                return new ChromeDriverManager();
            case FIREFOX:
                return new FirefoxDriverManager();
            default:
                throw new IllegalStateException("Driver type is not available "+ driverType);
        }

    }
}
