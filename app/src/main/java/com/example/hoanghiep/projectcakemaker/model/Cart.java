package com.example.hoanghiep.projectcakemaker.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    public static List<Product> list = new ArrayList<>();
    public static double total = 0.0;

    public Cart() {

    }

    public static List<Product> productList() {
        return list;
    }


}
