package com.example.hoanghiep.projectcakemaker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.adapter.RecyclerViewCartAdapter;
import com.example.hoanghiep.projectcakemaker.model.Product;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

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

        recyclerViewCart = (RecyclerView) findViewById(R.id.rvCart);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewCart.setHasFixedSize(true);

        for (int i = 0; i < 6; i++) {
            Product p = new Product();
            p.setName("Cake One");
            p.setPrice(1);
            products.add(p);
        }

        adapter = new RecyclerViewCartAdapter(products);
        setUpAnimRecycleView();
    }

    public void setUpAnimRecycleView() {
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(adapter);
        alphaInAnimationAdapter.setDuration(1000);
        alphaInAnimationAdapter.setInterpolator(new OvershootInterpolator());
        alphaInAnimationAdapter.setFirstOnly(false);
        recyclerViewCart.setAdapter(new ScaleInAnimationAdapter(alphaInAnimationAdapter));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((RecyclerViewCartAdapter) adapter).setOnClickItemCart(new RecyclerViewCartAdapter.OnClickItemCart() {
            @Override
            public void onItemClickCart(View view, int position) {

            }
        });
    }
}

