package com.example.hoanghiep.projectcakemaker.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.fragment.CakeFragment;
import com.example.hoanghiep.projectcakemaker.fragment.EventFragment;
import com.example.hoanghiep.projectcakemaker.fragment.HomeFragment;
import com.example.hoanghiep.projectcakemaker.fragment.ProfileFragment;

/**
 * Created by hoanghiep on 03/12/2015.
 */
public class SelectPagerAdapter extends FragmentStatePagerAdapter {

    public SelectPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CakeFragment();
            case 2:
                return new EventFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Cake";
            case 2:
                return "Event";
        }
        return null;
    }



}
