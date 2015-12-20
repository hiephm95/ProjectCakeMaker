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
public class AboutUsFragment extends Fragment {


    public AboutUsFragment() {
        // Required empty public constructor
    }

    ScreenChangeListener screenChangeListener;

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_about_us, container, false);

        }
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        screenChangeListener.setTitle("About Us");
        screenChangeListener.setIconActionLeft(R.mipmap.ic_menu);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        screenChangeListener = (ScreenChangeListener) activity;
    }
}
