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

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SubmitOrderAsync extends AsyncTask<Void, Void, String> {
    ProgressDialog progressDialog;
    public Order order;
    JSONArray productJsonArray = new JSONArray();
    String subject;
    String content;
    Context context;
    List<Product> products;

    public SubmitOrderAsync(Context context, List<Product> products) {
        this.products = products;
        this.context = context;
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

        String productOfOrder = "";

        for (Product p : Cart.list) {
            try {

                JSONObject productJson = new JSONObject();
                productJson.put("id", p.getObjectId());
                productJson.put("quantity", p.quantity + 1);
                productJson.put("eggLess", p.eggLess);
                productJsonArray.put(productJson);

                for (Product product : products) {
                    if (p.getObjectId() == product.getObjectId()) {
                        productOfOrder = productOfOrder + "\n" + product.getName() + " " + (product.quantity + 1) + " Eggless:" + product.eggLess;
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        order.put("products", productJsonArray);
        order.saveInBackground();

        subject = "Bill Cakes";
        content = "You Have Successfully Ordered " + "\n" + "Cakes: " + productOfOrder + "\n" + "Total: " + order.getTotal() + " $";

        try {
            send(order.getEmail(), subject, content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
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



    public static void send(String to, String subject, String content) throws MessagingException {
        String username = "cakemakerfpt@gmail.com";
        String password = "cake123456789";

        Properties p = System.getProperties();
        String host = "smtp.gmail.com";
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", host);
        p.put("mail.smtp.user", username);
        p.put("mail.smtp.password", password);
        p.put("mail.smtp.port", "587");
        p.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(p);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));

        InternetAddress toAddress = new InternetAddress(to);

        message.addRecipient(Message.RecipientType.TO, toAddress);

        message.setSubject(subject);
        message.setText(content);

        Transport transport = session.getTransport("smtp");
        transport.connect(host, username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
