package com.example.hoanghiep.projectcakemaker.activity;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.adapter.MenuAdapter;
import com.example.hoanghiep.projectcakemaker.adapter.SelectPagerAdapter;
import com.example.hoanghiep.projectcakemaker.fragment.AboutUsFragment;
import com.example.hoanghiep.projectcakemaker.fragment.CategoryFragment;
import com.example.hoanghiep.projectcakemaker.fragment.HomeFragment;
import com.example.hoanghiep.projectcakemaker.interfaces.ScreenChangeListener;
import com.example.hoanghiep.projectcakemaker.model.Event;
import com.example.hoanghiep.projectcakemaker.model.Picture;
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.example.hoanghiep.projectcakemaker.model.Screen;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ScreenChangeListener {

    TextView tvTitle;
    ListView lsvMenu;
    RelativeLayout rlLeftDrawer;
    DrawerLayout dlLayout;
    ImageView vActionLeft;
    Toolbar toolbar;


    ActionBarDrawerToggle mDrawerToggle;

    MenuAdapter menuAdapter;
    ArrayList<Screen> screenArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initParse();

        initProject();

        initViews();

        initImageLoader();

        setUpMenu();

        setActions();


        //setUpViewPager();
    }

    public void initProject() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new HomeFragment());
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

    public void setUpViewPager() {


    }

    private void initViews() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        lsvMenu = (ListView) findViewById(R.id.lsvMenu);
        rlLeftDrawer = (RelativeLayout) findViewById(R.id.left_drawer);
        dlLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        vActionLeft = (ImageView) findViewById(R.id.actionLeft);
        toolbar = (Toolbar) findViewById(R.id.toolBar);

    }


    private void setUpMenu() {
        mDrawerToggle = new ActionBarDrawerToggle(this, dlLayout, 0, 0);

        screenArrayList.add(new Screen(R.mipmap.icon_home, "Home"));
        screenArrayList.add(new Screen(R.mipmap.icon_cake, "Category"));
        screenArrayList.add(new Screen(R.mipmap.icon_contact_us, "Contact Us"));
        screenArrayList.add(new Screen(R.mipmap.icon_about_us, "About Us"));

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
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                switch (position) {
                    case Screen.HOME:
                        transaction.replace(R.id.main_frame, new HomeFragment());
                        transaction.commit();
                        break;
                    case Screen.CATEGORY:
                        transaction.replace(R.id.main_frame, new CategoryFragment());
                        transaction.commit();
                        break;
                    case Screen.ABOUT_US:
                        transaction.replace(R.id.main_frame, new AboutUsFragment());
                        transaction.commit();
                        break;
                    default:
                        break;
                }
            }
        }, 200);
    }

    private void setActions() {
        vActionLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Fragment currentFragment = getFragmentManager().findFragmentById(R.id.main_frame);

        switch (v.getId()) {
            case R.id.actionLeft:
//                if (currentFragment instanceof DetailsFragment) {
//                    getFragmentManager().popBackStack();
//                } else {
//                    dlLayout.openDrawer(rlLeftDrawer);
//                }
//                break;
                dlLayout.openDrawer(rlLeftDrawer);
        }
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setIconActionLeft(int image_res) {
        vActionLeft.setImageResource(image_res);
    }
}
