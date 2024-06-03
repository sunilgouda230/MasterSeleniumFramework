package org.selenium.pom.objects;

import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;

public class Products {
    private String name;
    private int id;

    public Products(){}

    public Products(int id) throws IOException {
        Products [] products = JacksonUtils.deserializeJson("products.json",Products[].class);
        for (Products product :products) {
            if (product.getId() == id){
                this.id = product.getId();
                this.name = product.getName();
            }
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
