package org.selenium.pom.factory.abstractFactory;

import org.selenium.pom.constants.DriverType;
import org.selenium.pom.utils.ConfigLoader;

public class DriverFactory {

    public static AbstractDriverFactory getDriver(DriverType driverType){
        ConfigLoader config = ConfigLoader.getInstance();
        boolean useGrid = config.isGridEnabled();

        if (useGrid) {
            // Automatically switch to remote driver if Grid is enabled
            switch (driverType) {
                case CHROME:
                    return new RemoteDriverChromeProvider();
                case FIREFOX:
                    return new RemoteDriverFireFoxProvider();
                default:
                    throw new IllegalStateException("Driver type not supported for Grid: " + driverType);
            }
        }

        switch (driverType){
            case CHROME:
                return new ChromeDriverProvider();
            case FIREFOX:
                return new FirefoxDriverProvider();
            case REMOTE_CHROME:
                return new RemoteDriverChromeProvider();
            case REMOTE_FIREFOX:
                return new RemoteDriverFireFoxProvider();
            default:
                throw new IllegalStateException("Driver type is not available "+ driverType);
        }

    }
}
