package com.example.hoanghiep.projectcakemaker.job;

import android.os.AsyncTask;

import com.example.hoanghiep.projectcakemaker.model.Product;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by HoangHiep on 1/3/16.
 */
public class ShowProductAsync extends AsyncTask<Void, Void, List<Product>> {
    @Override
    protected List<Product> doInBackground(Void... params) {
        ParseQuery<Product> query = ParseQuery.getQuery(Product.class);
        query.fromLocalDatastore();
        try {

            return query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
