package org.selenium.pom.factory.abstractFactory;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.selenium.pom.constants.Constants;
import org.selenium.pom.utils.ConfigLoader;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverManagerChrome extends DriverManagerAbstract{
    @Override
    protected void startDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless"); // Add options as needed
        ConfigLoader config = ConfigLoader.getInstance(); // read singleton
        boolean useGrid = config.isGridEnabled();

        try {
            if (useGrid){
                System.out.println("Starting Chrome on Selenium Grid...");
                driver = new RemoteWebDriver(new URL(ConfigLoader.getInstance().getValue(Constants.GRID_URL)), options);
            } else {
                System.out.println("Starting Chrome locally...");
                driver = new ChromeDriver(options);
            }

        } catch (MalformedURLException e) {
            System.err.println("The Selenium Grid URL is malformed.");
            throw new RuntimeException(e);
        } catch (SessionNotCreatedException e) {
            System.err.println("Session not created: Check Selenium Grid configuration.");
            throw new RuntimeException(e);
        }
    }
}
