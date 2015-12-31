package com.example.hoanghiep.projectcakemaker.job;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.example.hoanghiep.projectcakemaker.activity.DetailActivity;
import com.example.hoanghiep.projectcakemaker.adapter.RecyclerViewAdapter;
import com.example.hoanghiep.projectcakemaker.model.Event;
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class ProductByEventAsync extends AsyncTask<String, Void, List<Product>> {
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    Context context;

    public ProductByEventAsync(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Product> doInBackground(String... params) {
        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        query.fromLocalDatastore();
        try {
            for (Event e : query.find()) {
                if (e.getName().equals(params[0])) {
//                    e.setProductList(e.getProductRelation().getQuery().fromLocalDatastore().find());
//                    for (Product p : e.getProductList()) {
//                        p.setPictureList(p.getPictureRelation().getQuery().fromLocalDatastore().find());
//                    }
                    return e.getProductRelation().getQuery().find();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(final List<Product> productList) {
        adapter = new RecyclerViewAdapter(productList);

        //Setup Animation
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(adapter);
        alphaInAnimationAdapter.setDuration(1000);
        alphaInAnimationAdapter.setInterpolator(new OvershootInterpolator());
        alphaInAnimationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaInAnimationAdapter));

        ((RecyclerViewAdapter) adapter).setOnItemClickListener(new RecyclerViewAdapter.MyClickListener() {
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
    }
}
