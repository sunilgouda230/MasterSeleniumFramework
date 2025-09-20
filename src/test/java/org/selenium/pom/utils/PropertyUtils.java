package org.selenium.pom.utils;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {

    public static Properties propertiesLoader(String filepath){
        Properties properties = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load the properties file "+filepath);
            }
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
            throw  new RuntimeException("File Not Found at "+ filepath);
        }
        return properties;
    }
}
