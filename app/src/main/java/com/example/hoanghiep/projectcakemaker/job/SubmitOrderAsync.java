package com.example.hoanghiep.projectcakemaker.job;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.hoanghiep.projectcakemaker.model.Cart;
import com.example.hoanghiep.projectcakemaker.model.Order;
import com.example.hoanghiep.projectcakemaker.model.Product;

public class SubmitOrderAsync extends AsyncTask<Void, Void, String> {
    ProgressDialog progressDialog;
    public Order order;

    public SubmitOrderAsync(Context context) {
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Submitting Order...!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    @Override
    protected String doInBackground(Void... params) {
        order.saveInBackground();
        for (Product p : Cart.list) {
            order.getProductRelation().add(p);
        }
        order.saveInBackground();
        return "Your Order Are Submited.Please wait our reply, thank you ^^";
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("Completed", s);
    }
}
