package com.example.hoanghiep.projectcakemaker.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseRelation;

import java.util.ArrayList;
import java.util.List;

@ParseClassName("Event")
public class Event extends ParseObject {
    public List<Product> productList;
    public Event() {
        productList = new ArrayList<>();
    }

    //name
    public String getName() {
        return getString("name");
    }

    public void setName(String value) {
        put("name", value);
    }

    //Products Relation
    public ParseRelation<Product> getProductRelation()
    {
        return getRelation("Products");
    }

    public void setProductList(List<Product> list) {
        productList = list;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProductRelation(Product p)
    {
        getProductRelation().add(p);
    }


}
