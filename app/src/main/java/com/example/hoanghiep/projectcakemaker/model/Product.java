package com.example.hoanghiep.projectcakemaker.model;


import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.ArrayList;
import java.util.List;

@ParseClassName("Product")
public class Product extends ParseObject{
    public List<Picture> picturesList;
    public int quantity;
    public boolean eggLess;

    public Product() {
        picturesList = new ArrayList<>();
    }

    //Id
    public String getId() {
        return getObjectId();
    }

    //description
    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String value) {
        put("description", value);
    }

    //name
    public String getName() {
        return getString("name");
    }

    public void setName(String value) {
        put("name", value);
    }

    //itemCode
    public String getItemCode() {
        return getString("itemCode");
    }

    public void setItemCode(String value) {
        put("itemCode", value);
    }

    //price
    public double getPrice() {
        return getDouble("price");
    }

    public void setPrice(double value) {
        put("price", value);
    }

    //egg
    public boolean getEgg() {
        return getBoolean("egg");
    }

    public void setEgg(Boolean value) {
        put("egg", value);
    }

    //discount
    public Double getDiscount() {
        return getDouble("discount");
    }

    public void setDiscount(double value) {
        put("discount", value);
    }

    //Picture
    public ParseRelation<Picture> getPictureRelation() {
        return getRelation("pictures");
    }

    public void setPictureRelation(Picture p) {
        getPictureRelation().add(p);
    }

    public void setPictureList(List<Picture> list) {
        picturesList = list;
    }

    public List<Picture> getPicturesList() {
        return picturesList;
    }


    //Get list all product
    public static void getListProduct(FindCallback<Product> callback) {
        ParseQuery<Product> query = ParseQuery.getQuery(Product.class);
        query.findInBackground(callback);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return this.getObjectId().equals(product.getObjectId());
    }

    @Override
    public int hashCode() {
        return this.getObjectId().hashCode();
    }
}
