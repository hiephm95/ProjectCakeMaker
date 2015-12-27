package com.example.hoanghiep.projectcakemaker.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.fragment.CakeFragmentItem;

/**
 * Created by HoangHiep on 12/18/15.
 */
public class CakeActivityOne extends AppCompatActivity implements View.OnClickListener{

    ImageView actionLeft2;
    TextView tvWeddingTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_one);
        actionLeft2 = (ImageView) findViewById(R.id.actionLeft2);
        tvWeddingTitle = (TextView) findViewById(R.id.tv_wedding_title);
        Bundle bundle = getIntent().getExtras();
        tvWeddingTitle.setText(bundle.getString("event"));
        actionLeft2.setOnClickListener(this);
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
        finish();
    }
}
