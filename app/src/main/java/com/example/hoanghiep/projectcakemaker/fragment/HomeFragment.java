package com.example.hoanghiep.projectcakemaker.fragment;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.adapter.RecyclerViewAdapter;
import com.example.hoanghiep.projectcakemaker.adapter.SlideAdapter;
import com.example.hoanghiep.projectcakemaker.job.ProductAsync;
import com.example.hoanghiep.projectcakemaker.model.Product;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private LinearLayout indicator_slide;
    private int dotsCount;
    private ImageView[] dots;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerViewAdapter viewAdapter;

    View root;
    SlideAdapter adapter;
    ViewPager viewPagerHome;
    int count = 0;
    Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_home, container, false);
            recyclerView = (RecyclerView) root.findViewById(R.id.rvCake);
            final RelativeLayout relativeLayout = (RelativeLayout) root.findViewById(R.id.layoutHome);
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, 1));
            recyclerView.setHasFixedSize(true);
            initView();
            ProductAsync productAsync = new ProductAsync(getActivity());
            productAsync.recyclerView = recyclerView;
            productAsync.adapter = viewAdapter;
            productAsync.execute();

//            setUpAnimRecycleView();
        }
        return root;

    }

    public void setUpAnimRecycleView() {
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(recyclerViewAdapter);
        alphaInAnimationAdapter.setDuration(1000);
        alphaInAnimationAdapter.setInterpolator(new OvershootInterpolator());
        alphaInAnimationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaInAnimationAdapter));
    }

    public void initView() {
        viewPagerHome = (ViewPager) root.findViewById(R.id.viewPagerHome);
        indicator_slide = (LinearLayout) root.findViewById(R.id.pagerCountDots);
        adapter = new SlideAdapter(getActivity());

        viewPagerHome.setAdapter(adapter);
        viewPagerHome.setCurrentItem(0);


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (count <= 2) {
                    viewPagerHome.setCurrentItem(count);
                    count++;
                } else {
                    count = 0;
                    viewPagerHome.setCurrentItem(count);
                }
            }
        }, 500, 3000);

        setUiPageViewController();
        viewPagerHome.setOnPageChangeListener(this);
    }

    private void setUiPageViewController() {
        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );


            params.setMargins(4, 0, 4, 0);

            indicator_slide.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }
        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
