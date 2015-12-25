package com.example.hoanghiep.projectcakemaker.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.fragment.CartFragment;
import com.example.hoanghiep.projectcakemaker.fragment.OrderFragment;

/**
 * Created by HoangHiep on 12/25/15.
 */
public class OrderActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView actionBack;
    TextView actionStep;
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
        actionStep = (TextView) findViewById(R.id.actionStep);
        titleStepChange = (TextView) findViewById(R.id.tvStep);
        actionBack.setOnClickListener(this);
        actionStep.setOnClickListener(this);

    }

    public void initOrder() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.flOrder, new CartFragment());
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.flOrder);
        switch (v.getId()) {
            case R.id.actionStep:
                transaction.replace(R.id.flOrder, new OrderFragment());
                transaction.commit();
                actionStep.setVisibility(View.GONE);
                titleStepChange.setText("Step 2");
                break;
            case R.id.actionBackOrder:
                if (currentFragment instanceof OrderFragment) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.flOrder, new CartFragment()).commit();
                    actionStep.setVisibility(View.VISIBLE);
                    titleStepChange.setText("Step 1");
                } else {
                    finish();
                }
                break;

        }
    }


}
