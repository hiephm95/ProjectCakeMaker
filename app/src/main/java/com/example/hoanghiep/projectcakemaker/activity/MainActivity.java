package com.example.hoanghiep.projectcakemaker.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.fragment.CategoryFragment;
import com.example.hoanghiep.projectcakemaker.interfaces.ScreenChangeListener;
import com.example.hoanghiep.projectcakemaker.model.Event;
import com.example.hoanghiep.projectcakemaker.model.Picture;
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.github.clans.fab.FloatingActionMenu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.parse.Parse;
import com.parse.ParseObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ScreenChangeListener {


    ImageView ivCart;
    Toolbar toolbar;
    FloatingActionMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initParse();

        initProject();

        initViews();

        initImageLoader();

    }

    public void initProject() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new CategoryFragment());
        transaction.commit();
    }


    private void initImageLoader()
    {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config);
    }

    private void initParse() {
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Product.class);
        ParseObject.registerSubclass(Event.class);
        ParseObject.registerSubclass(Picture.class);
        Parse.initialize(this, "l5OJy4F4rw3COKG6Jgc0VKNi7rFQzarUVLcjw4jA", "HCRpx0LQxTlvaBXDQ6BxeFsLnJqkGscA9xf1aq8Q");
    }



    private void initViews() {
        ivCart = (ImageView) findViewById(R.id.ivCart);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        menu = (FloatingActionMenu) findViewById(R.id.menu);
        menu.setClosedOnTouchOutside(true);
        ivCart.setOnClickListener(this);

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setIconActionLeft(int image_res) {

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getBaseContext(), CartActivity.class);
        startActivity(i);
    }
}
