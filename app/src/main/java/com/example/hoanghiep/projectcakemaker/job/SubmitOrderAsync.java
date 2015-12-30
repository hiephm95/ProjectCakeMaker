package com.example.hoanghiep.projectcakemaker.job;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.hoanghiep.projectcakemaker.model.Cart;
import com.example.hoanghiep.projectcakemaker.model.Order;
import com.example.hoanghiep.projectcakemaker.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubmitOrderAsync extends AsyncTask<Void, Void, String> {
    ProgressDialog progressDialog;
    public Order order;
    JSONArray productJsonArray = new JSONArray();

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
        for (Product p : Cart.list) {
            try {
                JSONObject productJson = new JSONObject();
                productJson.put("id", p.getObjectId());
                productJson.put("quantity", p.quantity + 1);
                productJsonArray.put(productJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        order.put("products", productJsonArray);
        order.saveInBackground();
        return "Your Order Are Submited.Please wait our reply, thank you ^^";
    }

    @Override
    protected void onPostExecute(String s) {
        Cart.list.clear();
        if (progressDialog.isShowing() == true) {
            progressDialog.dismiss();
        }
        Log.d("Completed", s);
    }
}
