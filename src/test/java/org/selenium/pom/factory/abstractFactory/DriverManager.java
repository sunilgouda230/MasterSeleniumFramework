package org.selenium.pom.factory.abstractFactory;

import org.bouncycastle.asn1.cms.OtherRecipientInfo;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.utils.ConfigLoader;

public class DriverManager {

    private static final ConfigLoader config = ConfigLoader.getInstance();

    public static WebDriver createDriver(String browser){
        DriverType driverType = DriverType.valueOf(browser.toUpperCase());

        if (config.isGridEnabled()) {
            System.out.println("Grid enabled → Using RemoteDriver");
            return DriverFactory.getDriver(driverType).getDriver();
        } else {
            System.out.println("Grid disabled → Using Local Driver");
            return DriverFactory.getDriver(driverType).getDriver();
        }
    }
}
