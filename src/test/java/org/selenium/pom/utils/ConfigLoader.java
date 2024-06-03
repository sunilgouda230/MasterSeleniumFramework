package org.selenium.pom.utils;

import org.selenium.pom.constants.EnvType;

import java.util.Properties;
import static org.selenium.pom.constants.EnvType.STAGE;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        String env = System.getProperty("env", String.valueOf(STAGE));
        switch (EnvType.valueOf(env)){
            case STAGE:
                properties = PropertyUtils.propertiesLoader("src/test/resources/stage_config.properties");
                break;
            case PRODUCTION:
                properties = PropertyUtils.propertiesLoader("src/test/resources/prod_config.properties");
                break;
            default:
                throw new IllegalStateException("Env not found "+ env);
        }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }
    public String getBaseUrl(){
        String prop = properties.getProperty("baseurl");
        if (prop != null ){
            return prop;
        } else {
            throw new RuntimeException("property baseurl is not specified in properties file");
        }
    }

    public String getUsername(){
        String prop = properties.getProperty("username");
        if (prop != null ){
            return prop;
        } else {
            throw new RuntimeException("property username is not specified in properties file");
        }
    }

    public String getPassword(){
        String prop = properties.getProperty("password");
        if (prop != null ){
            return prop;
        } else {
            throw new RuntimeException("property password is not specified in properties file");
        }
    }

    public String getemail(){
        String prop = properties.getProperty("email");
        if (prop != null ){
            return prop;
        } else {
            throw new RuntimeException("property email is not specified in properties file");
        }
    }
}
