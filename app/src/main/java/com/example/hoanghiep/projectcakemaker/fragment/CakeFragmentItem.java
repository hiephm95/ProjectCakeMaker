package com.example.hoanghiep.projectcakemaker.fragment;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.activity.DetailActivity;
import com.example.hoanghiep.projectcakemaker.adapter.RecyclerViewAdapter;
import com.example.hoanghiep.projectcakemaker.job.ProductByEventAsync;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CakeFragmentItem extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerViewAdapter viewAdapter;

    View root;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_cake_item, container, false);

            if (Build.VERSION.SDK_INT >= 21) {
                getActivity().getWindow().setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.share_element_transition_a));
            }
            final RelativeLayout relativeLayout = (RelativeLayout) root.findViewById(R.id.fragcake_one);
            recyclerView = (RecyclerView) root.findViewById(R.id.rvcake_one);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.setHasFixedSize(true);
            Bundle b = getArguments();


            ProductByEventAsync async = new ProductByEventAsync(getActivity());
            async.adapter = adapter;
            async.recyclerView = recyclerView;
            async.execute(b.getString("event"));
        }
        return root;
    }
}
