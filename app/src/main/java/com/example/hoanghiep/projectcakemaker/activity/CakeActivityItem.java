package com.example.hoanghiep.projectcakemaker.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.fragment.CakeFragmentItem;
import com.example.hoanghiep.projectcakemaker.model.Cart;

/**
 * Created by HoangHiep on 12/18/15.
 */
public class CakeActivityItem extends AppCompatActivity implements View.OnClickListener{

    ImageView actionLeft2;
    TextView tvWeddingTitle;
    FloatingActionButton fabEvent;
    public static TextView tvItemCart2;
    ImageView ivCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_item);
        actionLeft2 = (ImageView) findViewById(R.id.actionLeft2);
        fabEvent = (FloatingActionButton) findViewById(R.id.fabEvent);
        tvWeddingTitle = (TextView) findViewById(R.id.tv_wedding_title);
        tvItemCart2 = (TextView) findViewById(R.id.tvItemCart2);
        ivCart = (ImageView) findViewById(R.id.ivCart);
        Bundle bundle = getIntent().getExtras();
        tvWeddingTitle.setText(bundle.getString("event"));
        actionLeft2.setOnClickListener(this);
        fabEvent.setOnClickListener(this);
        ivCart.setOnClickListener(this);
        initProject();
    }

    public void initProject() {
        CakeFragmentItem cakeFragmentItem = new CakeFragmentItem();
        cakeFragmentItem.setArguments(getIntent().getExtras());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.flCakeWedding, cakeFragmentItem);
        transaction.commit();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionLeft2:
                finish();
                break;
            case R.id.fabEvent:
                Intent intentEvent = new Intent(getBaseContext(), EventActivity.class);
                startActivity(intentEvent);
                break;
            case R.id.ivCart:
                Intent intent = new Intent(getBaseContext(), CartActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    protected void onResume() {
        tvItemCart2.setText(String.valueOf(Cart.list.size()));
        if(Cart.list.size() > 0) {
        tvItemCart2.setVisibility(View.VISIBLE);
        }else
        {
            tvItemCart2.setVisibility(View.INVISIBLE);
        }
        super.onResume();
    }

}
