package com.example.hoanghiep.projectcakemaker.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.fragment.CartFragment;

/**
 * Created by HoangHiep on 12/22/15.
 */
public class CartActivity extends AppCompatActivity implements View.OnClickListener{


    ImageView acitonBack;
    TextView tvOrder;
    public static TextView cartTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        acitonBack = (ImageView) findViewById(R.id.actionBack);
        tvOrder = (TextView) findViewById(R.id.tvOrder);
        cartTotal = (TextView) findViewById(R.id.cartTotal);

        tvOrder.setOnClickListener(this);
        acitonBack.setOnClickListener(this);
        initCart();
    }

    public void initCart() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        CartFragment cf = new CartFragment();
        transaction.replace(R.id.flCart, cf).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvOrder:
                Intent i = new Intent(getBaseContext(), OrderActivity.class);
                startActivity(i);
                break;
            case R.id.actionBack:
                finish();
                break;
        }

    }

}

