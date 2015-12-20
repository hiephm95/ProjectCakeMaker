package com.example.hoanghiep.projectcakemaker.activity;

import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.fragment.DetailFragment;

/**
 * Created by HoangHiep on 12/18/15.
 */
public class DetailActivity extends AppCompatActivity {
    ImageView actionLeft3;
    TransitionInflater transitionInflater;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        getWindow().setSharedElementExitTransition(TransitionInflater.from(DetailActivity.this).inflateTransition(R.transition.share_element_transition_a));
        actionLeft3 = (ImageView) findViewById(R.id.actionLeft3);
        actionLeft3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DetailActivity.this.finishAfterTransition();
            }
        });
        initProject();
    }

    public void initProject() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.flDetail, new DetailFragment());
        transaction.commit();
    }

}
