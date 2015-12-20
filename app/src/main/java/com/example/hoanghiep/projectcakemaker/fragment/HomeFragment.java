package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.interfaces.ScreenChangeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
    ScreenChangeListener screenChangeListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        screenChangeListener.setTitle("Home");
        screenChangeListener.setIconActionLeft(R.mipmap.ic_menu);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        screenChangeListener = (ScreenChangeListener) activity;
    }
}
