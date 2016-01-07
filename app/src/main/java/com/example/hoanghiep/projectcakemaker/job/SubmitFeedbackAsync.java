package com.example.hoanghiep.projectcakemaker.job;

import android.os.AsyncTask;
import android.util.Log;

import com.example.hoanghiep.projectcakemaker.model.Feedback;
import com.parse.ParseException;

public class SubmitFeedbackAsync extends AsyncTask<Void,Void,String>{

    Feedback feedback;

    public SubmitFeedbackAsync(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            feedback.save();
            return "Send Feedback Success";

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "Send Feedback Error!";
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("Status:", s);
    }
}
