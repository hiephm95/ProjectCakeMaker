package com.example.hoanghiep.projectcakemaker.job;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.adapter.ItemAdapter;
import com.example.hoanghiep.projectcakemaker.adapter.RecyclerViewAdapter;
import com.example.hoanghiep.projectcakemaker.model.Event;
import com.example.hoanghiep.projectcakemaker.model.Picture;
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class EventAsync extends AsyncTask<Void, Void, List<Event>> {

    public ProgressDialog progressDialog;
    public ItemAdapter adapter;

    public EventAsync(Context context) {
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Please Wait...!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Log.d("Status:", "Loading......");
    }


    @Override
    protected List<Event> doInBackground(Void... params) {
        ParseQuery<Event> queryFromLocal = ParseQuery.getQuery(Event.class);
        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);

        queryFromLocal.fromLocalDatastore();
        try {
            if (queryFromLocal.find().size() != 0 && queryFromLocal.find().size() == query.find().size()) {
                for (Event e : queryFromLocal.find()) {
                    e.setProductList(e.getProductRelation().getQuery().find());
                    for (Product p : e.getProductList()) {
                        p.setPictureList(p.getPictureRelation().getQuery().find());
                    }
                }
                return queryFromLocal.find();
            } else {
                for (Event e : query.find()) {
                    e.setProductList(e.getProductRelation().getQuery().find());
                    for (Product p : e.getProductList()) {
                        p.setPictureList(p.getPictureRelation().getQuery().find());
                        Picture.pinAll(p.getPictureRelation().getQuery().find());
                    }
                    Product.pinAll(e.getProductRelation().getQuery().find());
                }
                Event.pinAll(query.find());
                return query.find();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Event> events) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        Log.d("Status:", "Completed");
    }
}
