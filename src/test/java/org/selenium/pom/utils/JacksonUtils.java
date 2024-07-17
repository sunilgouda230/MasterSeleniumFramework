package org.selenium.pom.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.StoreProducts;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JacksonUtils {
    public static <T> T deserializeJson(String filepath, Class<T> T) throws IOException {
        InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(filepath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is,T);
    }
}
