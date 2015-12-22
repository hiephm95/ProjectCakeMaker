package com.example.hoanghiep.projectcakemaker.fragment;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.activity.MainActivity;
import com.example.hoanghiep.projectcakemaker.adapter.SelectPagerAdapter;
import com.example.hoanghiep.projectcakemaker.interfaces.ScreenChangeListener;
import com.example.hoanghiep.projectcakemaker.tranformer.ViewPagerTranformer;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements MaterialTabListener{


    public CategoryFragment() {
        // Required empty public constructor
    }
    View root;
    ScreenChangeListener screenChangeListener;
    MaterialTabHost tabLayout;
    ViewPager viewPager;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_category, container, false);
            tabLayout = (MaterialTabHost) root.findViewById(R.id.tabs_layout);
            viewPager = (ViewPager) root.findViewById(R.id.viewPager);
            viewPager.setPageTransformer(true, new ViewPagerTranformer(ViewPagerTranformer.TransformType.DEPTH));
            SelectPagerAdapter adapter = new SelectPagerAdapter(getFragmentManager());
            adapter.context=CategoryFragment.this.getActivity();
            viewPager.setAdapter(adapter);
            viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

                @Override
                public void onPageSelected(int position) {
                    tabLayout.setSelectedNavigationItem(position);
                }
            });
            for (int i = 0; i < adapter.getCount(); i++) {
                tabLayout.addTab(
                        tabLayout.newTab()
                                .setIcon(adapter.getIcon(i))
                                .setTabListener(this)

                );
            }

        }
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        screenChangeListener.setTitle("Category");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        screenChangeListener = (ScreenChangeListener) activity;
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }
}
