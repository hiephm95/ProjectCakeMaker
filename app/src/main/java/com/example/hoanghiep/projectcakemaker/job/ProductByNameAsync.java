package com.example.hoanghiep.projectcakemaker.job;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.example.hoanghiep.projectcakemaker.activity.DetailActivity;
import com.example.hoanghiep.projectcakemaker.adapter.RecyclerViewAdapter;
import com.example.hoanghiep.projectcakemaker.adapter.RecyclerViewSearchAdapter;
import com.example.hoanghiep.projectcakemaker.model.Event;
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class ProductByNameAsync extends AsyncTask<String, Void, List<Product>> {
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    Context context;
    List<Product> listByName;

    public ProductByNameAsync(Context context) {
        this.context = context;
        listByName = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Product> doInBackground(String... params) {
        ParseQuery<Product> query = ParseQuery.getQuery(Product.class);
        query.fromLocalDatastore();
        try {
            for (Product p : query.find()) {
                if (p.getName().toLowerCase().contains(params[0].toLowerCase())) {
                    listByName.add(p);
                }
            }
            return listByName;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(final List<Product> productList) {
        adapter = new RecyclerViewSearchAdapter(productList);

        //Setup Animation
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(adapter);
        alphaInAnimationAdapter.setDuration(1000);
        alphaInAnimationAdapter.setInterpolator(new OvershootInterpolator());
        alphaInAnimationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaInAnimationAdapter));

        ((RecyclerViewSearchAdapter) adapter).setOnItemClickListener(new RecyclerViewSearchAdapter.MyClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(View view, int position) {
//                view.setTransitionName("transitionImage");
//                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, view, view.getTransitionName());
                Intent i = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("p_id", productList.get(position).getObjectId());
                bundle.putString("p_Avatar", productList.get(position).getPicturesList().get(0).getFile().getUrl());
                bundle.putString("p_Name", productList.get(position).getName());
                bundle.putDouble("p_Price", productList.get(position).getPrice());
                bundle.putString("p_Description", productList.get(position).getDescription());
                i.putExtras(bundle);
                context.startActivity(i);

            }
        });
        this.cancel(true);
    }


}
