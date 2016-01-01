package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.adapter.RecyclerViewHomeAdapter;
import com.example.hoanghiep.projectcakemaker.adapter.SlideAdapter;
import com.example.hoanghiep.projectcakemaker.interfaces.ScreenChangeListener;
import com.example.hoanghiep.projectcakemaker.job.ProductAsync;

import java.util.Timer;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private LinearLayout indicator_slide;
    private int dotsCount;
    private ImageView[] dots;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerViewHomeAdapter viewAdapter;

    View root;
    SlideAdapter adapter;
    AutoScrollViewPager viewPagerHome;
    ScreenChangeListener screenChangeListener;
    int count = 0;
    Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_home, container, false);
            recyclerView = (RecyclerView) root.findViewById(R.id.rvCake);
//            final RelativeLayout relativeLayout = (RelativeLayout) root.findViewById(R.id.layoutHome);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.setHasFixedSize(true);
            initView();
            ProductAsync productAsync = new ProductAsync(getActivity());
            productAsync.recyclerView = recyclerView;
            productAsync.adapter = viewAdapter;
            productAsync.execute();


        }
        return root;

    }


    public void initView() {
        viewPagerHome = (AutoScrollViewPager) root.findViewById(R.id.viewPagerHome);
        indicator_slide = (LinearLayout) root.findViewById(R.id.pagerCountDots);
        adapter = new SlideAdapter(getActivity());
        screenChangeListener.setTitle("Home");
        viewPagerHome.setAdapter(adapter);
        viewPagerHome.setInterval(2000);
        viewPagerHome.startAutoScroll();


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

    @Override
    public void onPause() {
        super.onPause();
        viewPagerHome.stopAutoScroll();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPagerHome.startAutoScroll();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        screenChangeListener = (ScreenChangeListener) activity;
    }
}
