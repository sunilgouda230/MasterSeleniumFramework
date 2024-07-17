package org.selenium.pom.dataprovider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.selenium.pom.objects.Products;
import org.selenium.pom.objects.StoreProducts;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomDataProvider {

    @DataProvider(name = "getFeaturedProducts", parallel = true)
    public Object[] getFeaturedProducts() throws IOException {
        Products[] products = JacksonUtils.deserializeJson("products.json", Products[].class);
        List<Object> featuredProductsList = new ArrayList<>();

        for (Products p : products) {
            if (p.getFeature()) {
                featuredProductsList.add(p);
            }
        }

        // Convert the list to an array and return it
        return featuredProductsList.toArray();
    }

    @DataProvider(name = "getProducts", parallel = true)
    public Object[][] getProducts() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StoreProducts[] products = objectMapper.readValue(new File("src/test/resources/StoreProducts.json"), StoreProducts[].class);
        List<StoreProducts> productList = Arrays.asList(products);

        return new Object[][]{{productList}};
    }
}
