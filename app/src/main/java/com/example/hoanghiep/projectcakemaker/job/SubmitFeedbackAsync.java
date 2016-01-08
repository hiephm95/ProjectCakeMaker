package com.example.hoanghiep.projectcakemaker.job;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.model.Feedback;
import com.parse.ParseException;

public class SubmitFeedbackAsync extends AsyncTask<Void,Void,Boolean>{
    ProgressDialog progressDialog;
    Feedback feedback;
    Context context;
    TextView tvToast;

    public SubmitFeedbackAsync(Feedback feedback,Context context) {
        this.feedback = feedback;
        this.context = context;
        progressDialog = new ProgressDialog(context);
    }
    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Please wait...!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);

        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            feedback.save();
            return true;

        } catch (ParseException e) {
            return false;
        }

    }

    @Override
    protected void onPostExecute(Boolean s) {
        if (s) {
            showToastCart(context);
            tvToast.setText("Contact Successfully!");
        } else {
            showToastCart(context);
            tvToast.setText("Network have problem, Try again!");
        }

        if (progressDialog.isShowing() == true) {
            progressDialog.dismiss();
        }
    }

    public void showToastCart(Context context) {
        getView(context);
    }

    public View getView(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        tvToast = (TextView) v.findViewById(R.id.tvToast);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(v);
        toast.show();
        return v;
    }
}
