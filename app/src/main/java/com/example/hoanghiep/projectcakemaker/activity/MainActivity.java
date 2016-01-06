package com.example.hoanghiep.projectcakemaker.activity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.adapter.MenuAdapter;
import com.example.hoanghiep.projectcakemaker.fragment.ContactFragment;
import com.example.hoanghiep.projectcakemaker.fragment.EventFragment;
import com.example.hoanghiep.projectcakemaker.fragment.HomeFragment;
import com.example.hoanghiep.projectcakemaker.interfaces.ScreenChangeListener;
import com.example.hoanghiep.projectcakemaker.job.EventAsync;
import com.example.hoanghiep.projectcakemaker.model.Cart;
import com.example.hoanghiep.projectcakemaker.model.Screen;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ScreenChangeListener {


    ImageView ivCart;
    ImageView ivSearch;
    TextView tvTitle;
    TextView tvItemCart;
    Toolbar toolbar;
    ListView lsvMenu;
    RelativeLayout rlLeftDrawer;
    DrawerLayout dlLayout;
    ImageView vActionLeft;

    ActionBarDrawerToggle mDrawerToggle;

    MenuAdapter menuAdapter;
    ArrayList<Screen> screenArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventAsync eventAsync = new EventAsync(this);
        eventAsync.execute();

        initProject();

        initViews();

        initImageLoader();

        setUpMenu();
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
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        lsvMenu = (ListView) findViewById(R.id.lsvMenu);
        rlLeftDrawer = (RelativeLayout) findViewById(R.id.left_drawer);
        dlLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        vActionLeft = (ImageView) findViewById(R.id.actionLeft);

        ivCart.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        vActionLeft.setOnClickListener(this);
        lsvMenu.setDivider(null);


    }

    private void setUpMenu() {
        mDrawerToggle = new ActionBarDrawerToggle(this, dlLayout, 0, 0);

        screenArrayList.add(new Screen(R.mipmap.ic_home, "Home"));
        screenArrayList.add(new Screen(R.mipmap.ic_event_menu, "Event"));
        screenArrayList.add(new Screen(R.mipmap.ic_cart_menu, "Cart"));
        screenArrayList.add(new Screen(R.mipmap.icon_contact_us, "Contact"));
        screenArrayList.add(new Screen(R.mipmap.icon_about_us, "About"));

        lsvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //de-select all items
                for (Screen screen : screenArrayList) {
                    screen.is_selected = false;
                }

                //set clicked item is_selected = true
                screenArrayList.get(position).is_selected = true;

                //update menu UI
                menuAdapter.notifyDataSetChanged();

                //close menu after item clicked
                dlLayout.closeDrawer(rlLeftDrawer);

                //display fragment depend on menu item selected
                displayScreen(position);
            }
        });

        menuAdapter = new MenuAdapter(screenArrayList);
        lsvMenu.setAdapter(menuAdapter);

    }

    private void displayScreen(final int position) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                switch (position) {
                    case Screen.HOME:
                        transaction.replace(R.id.main_frame, new HomeFragment());
                        transaction.commit();
                        break;
                    case Screen.EVENT:
                        transaction.replace(R.id.main_frame, new EventFragment());
                        transaction.commit();
                        break;
                    case Screen.CONTACT_US:
                        transaction.replace(R.id.main_frame, new ContactFragment());
                        transaction.commit();
                        break;
                    case Screen.CART:
                        Intent intentCart = new Intent(getBaseContext(), CartActivity.class);
                        startActivity(intentCart);
                        break;
                    default:
                        break;
                }
            }
        }, 200);
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
            case R.id.ivSearch:
                Intent intentSearch = new Intent(getBaseContext(), SearchActivity.class);
                Bundle slideanim = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_up, R.anim.slide_down).toBundle();
                startActivity(intentSearch, slideanim);
                break;
            case R.id.actionLeft:
                dlLayout.openDrawer(rlLeftDrawer);
                break;

        }

    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        SharedPreferences sharedPreferences = getSharedPreferences("Card", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putInt("size", 0);
        editor.putBoolean("visible", false);
        Log.i("TEST", "Stop");
        editor.commit();
        super.onDestroy();
    }
}
