package com.example.hoanghiep.projectcakemaker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Cart {
    public static List<Product> list = new ArrayList<>();

    public static double total = 0.0;
    public Cart() {

    }

    public static List<Product> productList() {
        return list;
    }

    public static ListIterator<Product> listIterator() {
        return list.listIterator();
    }

    public static void addProduct(Product product) {
        if(!list.contains(product))
        {
            list.add(product);
        }
    }

}
