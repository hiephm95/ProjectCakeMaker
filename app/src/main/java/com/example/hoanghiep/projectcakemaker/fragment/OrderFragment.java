package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.multidex.MultiDex;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.activity.MainActivity;
import com.example.hoanghiep.projectcakemaker.job.ProductAsync;
import com.example.hoanghiep.projectcakemaker.job.ShowProductAsync;
import com.example.hoanghiep.projectcakemaker.job.SubmitOrderAsync;
import com.example.hoanghiep.projectcakemaker.model.Cart;
import com.example.hoanghiep.projectcakemaker.model.Order;
import com.example.hoanghiep.projectcakemaker.model.Product;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements View.OnClickListener, TextWatcher{


    public OrderFragment() {
        // Required empty public constructor
    }

    View root;
    TextView tvOrderDeliveryDate;
    Calendar calendar = Calendar.getInstance();
    EditText etOrderName;
    EditText etOrderAddress;
    EditText etOrderPhone;
    EditText etOrderEmail;
    EditText etOrderTotal;
    Button btnOrderSubmit;
    TextInputLayout textName, textAddress, textPhone, textEmail;
    ImageView ivDate;
    String email;



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
        etOrderTotal.setText(String.valueOf(Cart.total) + " $");
        textName = (TextInputLayout) root.findViewById(R.id.textCustomName);
        textAddress = (TextInputLayout) root.findViewById(R.id.textAddress);
        textPhone = (TextInputLayout) root.findViewById(R.id.textOrderNum);
        textEmail = (TextInputLayout) root.findViewById(R.id.textOrderEmail);
        btnOrderSubmit = (Button) root.findViewById(R.id.btnOrderSubmit);
        ivDate = (ImageView) root.findViewById(R.id.ivDate);
        tvOrderDeliveryDate = (TextView) root.findViewById(R.id.tvOrderDeliveryDate);
        btnOrderSubmit.setOnClickListener(this);
        ivDate.setOnClickListener(this);
        etOrderName.addTextChangedListener(this);
        etOrderAddress.addTextChangedListener(this);
        etOrderPhone.addTextChangedListener(this);
        etOrderEmail.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivDate:
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(getActivity(), listener, year, month, day).show();
                break;
            case R.id.btnOrderSubmit:
                email = etOrderEmail.getText().toString();
                if (etOrderName.getText().toString().trim().equals("")) {
                    textName.setError("Input your name!");
                }else if (etOrderAddress.getText().toString().trim().equals("")) {
                    textAddress.setError("Input your address");
                }else if (etOrderPhone.getText().toString().trim().equals("")) {
                    textPhone.setError("Input your phone");
                }else if (!isValidEmail(email)) {
                    textEmail.setError("This is not the type of email");
                } else {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Order")
                            .setMessage("Are you sure ?")
                            .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
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
                                    order.setTotal(Cart.total);
                                    order.setEmail(etOrderEmail.getText().toString());
                                    order.setDeliveryDate(tvOrderDeliveryDate.getText().toString());
                                    async.execute();

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();



                }

                break;
        }

    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            tvOrderDeliveryDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        }
    };

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        email = etOrderEmail.getText().toString();
        if (!etOrderName.getText().toString().trim().equals("")) {
            textName.setErrorEnabled(false);
        }
        if (!etOrderAddress.getText().toString().trim().equals("")) {
            textAddress.setErrorEnabled(false);
        }
        if (!etOrderPhone.getText().toString().trim().equals("")) {
            textPhone.setErrorEnabled(false);
        }
        if (!isValidEmail(email)) {
              textEmail.setErrorEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
