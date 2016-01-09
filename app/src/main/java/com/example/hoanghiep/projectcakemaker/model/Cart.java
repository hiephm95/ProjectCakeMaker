package com.example.hoanghiep.projectcakemaker.model;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.activity.CakeActivityItem;
import com.example.hoanghiep.projectcakemaker.activity.DetailActivity;
import com.example.hoanghiep.projectcakemaker.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Cart {
    public static List<Product> list = new ArrayList<>();

    public static double total = 0.0;
    TextView tvToast;

    public Cart() {

    }

    public static List<Product> productList() {
        return list;
    }

    public static ListIterator<Product> listIterator() {
        return list.listIterator();
    }

    public void addProduct(Product product, Context context) {

        if (!list.contains(product)) {
            list.add(product);
            if (list.size() > 0) {
                DetailActivity.tvItemCart.setVisibility(View.VISIBLE);
                MainActivity.tvItemCartMain.setVisibility(View.VISIBLE);
            } else {
                DetailActivity.tvItemCart.setVisibility(View.INVISIBLE);
                MainActivity.tvItemCartMain.setVisibility(View.INVISIBLE);
            }
            DetailActivity.tvItemCart.setText(String.valueOf(list.size()));
            MainActivity.tvItemCartMain.setText(String.valueOf(list.size()));
            showToastCart(context);
            tvToast.setText("Add Complete!");
        } else {
            showToastCart(context);
            tvToast.setText("Product already in cart");
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
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setView(v);
        toast.show();
        return v;
    }

}
