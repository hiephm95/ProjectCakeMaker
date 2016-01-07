package com.example.hoanghiep.projectcakemaker.model;

import android.view.View;

import com.example.hoanghiep.projectcakemaker.activity.DetailActivity;
import com.example.hoanghiep.projectcakemaker.activity.MainActivity;

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
        if (!list.contains(product)) {
            list.add(product);
            if (list.size() > 0) {
                DetailActivity.tvItemCart.setVisibility(View.VISIBLE);
                MainActivity.tvItemCartMain.setVisibility(View.VISIBLE);
            }else
            {
                DetailActivity.tvItemCart.setVisibility(View.INVISIBLE);
                MainActivity.tvItemCartMain.setVisibility(View.INVISIBLE);
            }
            DetailActivity.tvItemCart.setText(String.valueOf(list.size()));
            MainActivity.tvItemCartMain.setText(String.valueOf(list.size()));
        }
    }

}
