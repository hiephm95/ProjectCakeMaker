package com.example.hoanghiep.projectcakemaker.job;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.example.hoanghiep.projectcakemaker.adapter.ItemAdapter;
import com.example.hoanghiep.projectcakemaker.adapter.RecyclerViewAdapter;
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class ProductAsync extends AsyncTask<Void, Void, List<Product>> {

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
    protected List<Product> doInBackground(Void... params) {
        ParseQuery<Product> query = ParseQuery.getQuery(Product.class);
        try {
            for (Product p : query.find()) {
                p.setPictureList(p.getPictureRelation().getQuery().find());
            }
            Product.pinAll(query.find());
            return query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Product> products) {
        adapter = new ItemAdapter(products);
        lsvItem.setAdapter(adapter);
        lsvItem.setDivider(null);
        if(progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        Log.d("Status:", "Completed");
    }
}
