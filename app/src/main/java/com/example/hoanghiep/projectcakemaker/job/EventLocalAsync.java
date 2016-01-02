package com.example.hoanghiep.projectcakemaker.job;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.hoanghiep.projectcakemaker.adapter.ItemAdapter;
import com.example.hoanghiep.projectcakemaker.model.Event;
import com.example.hoanghiep.projectcakemaker.model.Picture;
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class EventLocalAsync extends AsyncTask<Void, Void, List<Event>> {

    public ProgressDialog progressDialog;
    public ItemAdapter adapter;
    public ListView lsvItem;

    public EventLocalAsync(Context context) {
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Please Wait...!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        //progressDialog.show();
        Log.d("Status:", "Loading......");
    }


    @Override
    protected List<Event> doInBackground(Void... params) {
        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        query.fromLocalDatastore();
        try {
//            for (:) {
//
//            }
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
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        Log.d("Status:", "Completed");
    }
}
