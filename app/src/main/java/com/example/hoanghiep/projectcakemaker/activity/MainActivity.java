package com.example.hoanghiep.projectcakemaker.activity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.fragment.EventFragment;
import com.example.hoanghiep.projectcakemaker.fragment.HomeFragment;
import com.example.hoanghiep.projectcakemaker.interfaces.ScreenChangeListener;
import com.example.hoanghiep.projectcakemaker.job.EventAsync;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ScreenChangeListener {


    ImageView ivCart;
    ImageView ivEvent;
    ImageView ivSearch;
    TextView tvTitle;
    Toolbar toolbar;
    FloatingActionMenu menu;
    FloatingActionButton fabHome,fabAboutUs,fabContactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventAsync eventAsync = new EventAsync(this);
        eventAsync.execute();

        initProject();

        initViews();

        initImageLoader();
    }

    public void initProject() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new HomeFragment());
        transaction.commit();
    }


    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config);
    }

    private void initViews() {
        ivCart = (ImageView) findViewById(R.id.ivCart);
        ivEvent = (ImageView) findViewById(R.id.ivEvent);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        fabHome = (FloatingActionButton) findViewById(R.id.fabHome);
        menu = (FloatingActionMenu) findViewById(R.id.menu);
        menu.setClosedOnTouchOutside(true);
        ivEvent.setOnClickListener(this);
        ivCart.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        fabHome.setOnClickListener(this);


    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setIconActionLeft(int image_res) {

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.ivCart:
                Intent intentCart = new Intent(getBaseContext(), CartActivity.class);
                startActivity(intentCart);
                break;
            case R.id.ivEvent:
                transaction.replace(R.id.main_frame, new EventFragment());
                transaction.commit();
                break;
            case R.id.ivSearch:
                Intent intentSearch = new Intent(getBaseContext(), SearchActivity.class);
                Bundle slideanim = ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.slide_up, R.anim.slide_down).toBundle();
                startActivity(intentSearch,slideanim);
            case R.id.fabHome:
                transaction.replace(R.id.main_frame, new HomeFragment());
                transaction.commit();
                break;

        }

    }
}
