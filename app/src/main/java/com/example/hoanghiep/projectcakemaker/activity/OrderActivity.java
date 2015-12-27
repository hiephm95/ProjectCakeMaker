package com.example.hoanghiep.projectcakemaker.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.fragment.OrderFragment;

/**
 * Created by HoangHiep on 12/25/15.
 */
public class OrderActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView actionBack;
    TextView titleStepChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initOrder();

        initView();
    }

    public void initView() {
        actionBack = (ImageView) findViewById(R.id.actionBackOrder);
        actionBack.setOnClickListener(this);


    }

    public void initOrder() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.flOrder, new OrderFragment());
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionBackOrder:
                finish();
                break;

        }
    }


}
