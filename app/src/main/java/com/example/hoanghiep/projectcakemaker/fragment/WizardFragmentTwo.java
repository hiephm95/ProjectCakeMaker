package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.interfaces.WizardChangeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class WizardFragmentTwo extends Fragment {


    public WizardFragmentTwo() {
        // Required empty public constructor
    }

    View root;
    WizardChangeListener wizardChangeListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.wizard_two_fragment, container, false);

        }
        return root;
    }



}
