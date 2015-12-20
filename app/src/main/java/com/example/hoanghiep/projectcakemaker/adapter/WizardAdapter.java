package com.example.hoanghiep.projectcakemaker.adapter;



import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.example.hoanghiep.projectcakemaker.fragment.WizardFragmentOne;
import com.example.hoanghiep.projectcakemaker.fragment.WizardFragmentThree;
import com.example.hoanghiep.projectcakemaker.fragment.WizardFragmentTwo;

/**
 * Created by HoangHiep on 12/12/15.
 */
public class WizardAdapter extends FragmentPagerAdapter {

    final int NUM_WIZARD = 3;

    public WizardAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new WizardFragmentOne();
            case 1:
                return new WizardFragmentTwo();
            case 2:
                return new WizardFragmentThree();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_WIZARD;
    }
}
