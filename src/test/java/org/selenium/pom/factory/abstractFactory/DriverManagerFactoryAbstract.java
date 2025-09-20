package org.selenium.pom.factory.abstractFactory;

import org.selenium.pom.constants.DriverType;
import org.selenium.pom.utils.ConfigLoader;

public class DriverManagerFactoryAbstract {

    public static DriverManagerAbstract getManager(DriverType driverType){
        ConfigLoader config = ConfigLoader.getInstance();
        boolean useGrid = config.isGridEnabled();

        if (useGrid) {
            // Automatically switch to remote driver if Grid is enabled
            switch (driverType) {
                case CHROME:
                    return new RemoteDriverManagerChrome();
                case FIREFOX:
                    return new RemoteDriverManagerFireFox();
                default:
                    throw new IllegalStateException("Driver type not supported for Grid: " + driverType);
            }
        }

        switch (driverType){
            case CHROME:
                return new ChromeDriverManager();
            case FIREFOX:
                return new FirefoxDriverManager();
            case REMOTE_CHROME:
                return new RemoteDriverManagerChrome();
            case REMOTE_FIREFOX:
                return new RemoteDriverManagerFireFox();
            default:
                throw new IllegalStateException("Driver type is not available "+ driverType);
        }

    }
}
