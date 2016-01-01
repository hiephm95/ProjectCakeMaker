package com.example.hoanghiep.projectcakemaker.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.fragment.EventFragment;
import com.example.hoanghiep.projectcakemaker.interfaces.ScreenChangeListener;

/**
 * Created by HoangHiep on 1/2/16.
 */
public class EventActivity extends AppCompatActivity implements View.OnClickListener, ScreenChangeListener {
    ImageView ivBack;
    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        ivBack.setOnClickListener(this);
        initProject();
    }

    public void initProject() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.flEvent, new EventFragment()).commit();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setIconActionLeft(int image_res) {

    }
}
