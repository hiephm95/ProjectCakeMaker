package com.example.hoanghiep.projectcakemaker.model;

/**
 * Created by HoangHiep on 12/18/15.
 */
public class Product {


    public String name;
    public String eggs;
    public float price;
    public int categoryId;

    public Product() {
    }

    public Product(String name, String eggs, float price, int categoryId) {
        this.name = name;
        this.eggs = eggs;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Product(int categoryId, String name, float price) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Product(String name, String eggs, float price) {
        this.name = name;
        this.eggs = eggs;
        this.price = price;
    }

    public String getEggs() {
        return eggs;
    }

    public void setEggs(String eggs) {
        this.eggs = eggs;
    }

    public float getPrice() {

        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
