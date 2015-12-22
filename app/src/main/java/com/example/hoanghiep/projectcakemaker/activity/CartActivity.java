package com.example.hoanghiep.projectcakemaker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.adapter.RecyclerViewCartAdapter;
import com.example.hoanghiep.projectcakemaker.model.Product;

import java.util.ArrayList;

/**
 * Created by HoangHiep on 12/22/15.
 */
public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCart;
    private RecyclerView.Adapter adapter;
    private RecyclerViewCartAdapter recyclerViewCartAdapter;
    private ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
    }
}
