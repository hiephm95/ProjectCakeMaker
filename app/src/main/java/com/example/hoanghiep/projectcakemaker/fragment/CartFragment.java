package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.adapter.RecyclerViewCartAdapter;
import com.example.hoanghiep.projectcakemaker.model.Cart;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    public CartFragment() {
    }

    private RecyclerView recyclerViewCart;
    private RecyclerView.Adapter adapter;
    private RecyclerViewCartAdapter recyclerViewCartAdapter;


    View root;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_cart, container, false);
            recyclerViewCart = (RecyclerView) root.findViewById(R.id.rvCart);
            recyclerViewCart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recyclerViewCart.setHasFixedSize(true);

            Toast.makeText(getActivity(), "Count:" + Cart.productList().size(), Toast.LENGTH_SHORT).show();

            adapter = new RecyclerViewCartAdapter(Cart.list);
            setUpAnimRecycleView();
        }
        return root;
    }

    public void setUpAnimRecycleView() {
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(adapter);
        alphaInAnimationAdapter.setDuration(1000);
        alphaInAnimationAdapter.setInterpolator(new OvershootInterpolator());
        alphaInAnimationAdapter.setFirstOnly(false);
        recyclerViewCart.setAdapter(new ScaleInAnimationAdapter(alphaInAnimationAdapter));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((RecyclerViewCartAdapter) adapter).setOnClickItemCart(new RecyclerViewCartAdapter.OnClickItemCart() {
            @Override
            public void onItemClickCart(View view, int position) {

            }
        });
    }
}
