package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.job.ProductAsync;
import com.example.hoanghiep.projectcakemaker.job.ShowProductAsync;
import com.example.hoanghiep.projectcakemaker.job.SubmitOrderAsync;
import com.example.hoanghiep.projectcakemaker.model.Cart;
import com.example.hoanghiep.projectcakemaker.model.Order;
import com.example.hoanghiep.projectcakemaker.model.Product;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements View.OnClickListener {


    public OrderFragment() {
        // Required empty public constructor
    }

    View root;
    EditText etOrderDeliveryDate;
    Calendar calendar = Calendar.getInstance();
    EditText etOrderName;
    EditText etOrderAddress;
    EditText etOrderPhone;
    EditText etOrderEmail;
    EditText etOrderTotal;
    Button btnOrderSubmit;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_order, container, false);
            MultiDex.install(getActivity());
            initView();
        }
        return root;
    }

    private void initView() {
        etOrderName = (EditText) root.findViewById(R.id.etOrderName);
        etOrderAddress = (EditText) root.findViewById(R.id.etOrderAddress);
        etOrderPhone = (EditText) root.findViewById(R.id.etOrderPhone);
        etOrderEmail = (EditText) root.findViewById(R.id.etOrderEmail);
        etOrderTotal = (EditText) root.findViewById(R.id.etOrderTotal);
        etOrderTotal.setText("" + Cart.total);
        btnOrderSubmit = (Button) root.findViewById(R.id.btnOrderSubmit);
        btnOrderSubmit.setOnClickListener(this);
        etOrderDeliveryDate = (EditText) root.findViewById(R.id.etOrderDeliveryDate);
        etOrderDeliveryDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etOrderDeliveryDate:
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(getActivity(), listener, year, month, day).show();
                break;
            case R.id.btnOrderSubmit:
                List<Product> productList = null;
                try {
                    productList = new ShowProductAsync().execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                SubmitOrderAsync async = new SubmitOrderAsync(getActivity(), productList);
                Order order = new Order();
                async.order = order;
                order.setName(etOrderName.getText().toString());
                order.setAddress(etOrderAddress.getText().toString());
                order.setPhone(etOrderPhone.getText().toString());
                order.setTotal(Double.valueOf(etOrderTotal.getText().toString()));
                order.setEmail(etOrderEmail.getText().toString());
                order.setDeliveryDate(etOrderDeliveryDate.getText().toString());
                async.execute();
                break;
        }

    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            etOrderDeliveryDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        }
    };
}
