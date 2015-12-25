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
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class ProductAsync extends AsyncTask<Void, Void, List<Event>> {

    public ProgressDialog progressDialog;
    public ItemAdapter adapter;
    public ListView lsvItem;

    public ProductAsync(Context context)
    {
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Please Wait...!");
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        Log.d("Status:", "Loading......");
    }


    @Override
    protected List<Event> doInBackground(Void... params) {
        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        try {
            for (Event e : query.find()) {
                e.setProductList(e.getProductRelation().getQuery().find());
                for (Product p : e.getProductList()) {
                    p.setPictureList(p.getPictureRelation().getQuery().find());
                }

            }
            Product.pinAll(query.find());
            return query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Event> events) {
        adapter = new ItemAdapter(events);
        lsvItem.setAdapter(adapter);
        lsvItem.setDivider(null);
        if(progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        Log.d("Status:", "Completed");
    }
}
