package com.example.hoanghiep.projectcakemaker.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    public static List<Product> list = new ArrayList<>();

    public Cart() {

    }

    public static List<Product> productList() {
        return list;
    }


}
