package com.example.hoanghiep.projectcakemaker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.hoanghiep.projectcakemaker.R;

/**
 * Created by HoangHiep on 12/30/15.
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView ivActionSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ivActionSearch = (ImageView) findViewById(R.id.actionBackSearch);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
