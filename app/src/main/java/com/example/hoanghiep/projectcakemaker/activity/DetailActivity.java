package com.example.hoanghiep.projectcakemaker.activity;

import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.fragment.DetailFragment;
import com.nostra13.universalimageloader.core.ImageLoader;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView actionLeft3;
    ImageView ivProductDetails;
    FloatingActionButton fabCart;
    TransitionInflater transitionInflater;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
//        getWindow().setSharedElementExitTransition(TransitionInflater.from(DetailActivity.this).inflateTransition(R.transition.share_element_transition_a));
        initView();
        actionLeft3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initProject();
    }

    private void initProject() {
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(getIntent().getExtras());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.flDetail, detailFragment);
        transaction.commit();
    }

    private void initView() {
        actionLeft3 = (ImageView) findViewById(R.id.actionLeft3);
        ivProductDetails = (ImageView) findViewById(R.id.ivProductDetails);
        fabCart = (FloatingActionButton) findViewById(R.id.fabCart);
        fabCart.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        ImageLoader.getInstance().displayImage(b.getString("p_Avatar"), ivProductDetails);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getBaseContext(), CartActivity.class);
        startActivity(i);
    }
}
