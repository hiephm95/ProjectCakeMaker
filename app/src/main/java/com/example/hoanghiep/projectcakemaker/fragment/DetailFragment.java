package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.model.Cart;
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements View.OnClickListener{
    TextView tvDetailName;
    TextView tvDetailPrice;
    TextView tvDetailDescription;
    Button btnCart;
    Spinner spinQuantity;
    RadioButton rbEggLess, rbEggWith;
    Cart cart = new Cart();

    public DetailFragment() {
        // Required empty public constructor
    }

    View root;
    Bundle bundle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (root == null) {
            root = inflater.inflate(R.layout.fragment_detail, container, false);
            bundle = this.getArguments();
            initView();

        }
        return root;
    }

    private void initView() {
        rbEggLess = (RadioButton) root.findViewById(R.id.rbEggLess);
        rbEggWith = (RadioButton) root.findViewById(R.id.rbEggWith);
        tvDetailName = (TextView) root.findViewById(R.id.tvDetailName);
        tvDetailPrice = (TextView) root.findViewById(R.id.tvDetailPrice);
        tvDetailDescription = (TextView) root.findViewById(R.id.tvDetailDescription);
        spinQuantity = (Spinner) root.findViewById(R.id.spinQuantity);
        btnCart = (Button) root.findViewById(R.id.btnCart);
        btnCart.setOnClickListener(this);
        spinQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cart.total = Double.parseDouble(parent.getItemAtPosition(position).toString()) * bundle.getDouble("p_Price");
                tvDetailPrice.setText(String.valueOf(Cart.total) + " $");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setupSpinner();


    }

    public void setupSpinner() {
        String[] arraySpinner = new String[]
                {
                        "1", "2", "3", "4", "5", "6"
                };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arraySpinner);
        spinQuantity.setAdapter(adapter);


        tvDetailName.setText(bundle.getString("p_Name"));
        tvDetailPrice.setText(bundle.getDouble("p_Price") + " $");
        tvDetailDescription.setText(bundle.getString("p_Description"));
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnCart:
                final Bundle bundle = this.getArguments();
                ParseQuery<Product> query = ParseQuery.getQuery(Product.class);
                query.fromLocalDatastore();

                query.getInBackground(bundle.getString("p_id"), new GetCallback<Product>() {
                    @Override
                    public void done(Product object, ParseException e) {
                        if (e == null) {

                            if(rbEggLess.isChecked())
                            {
                                object.eggLess = true;
                            }else
                            {
                                object.eggLess = false;
                            }
                            object.quantity = spinQuantity.getSelectedItemPosition();
                            cart.addProduct(object, getActivity());

                        } else {
                            Log.d("Error:", e.toString());
                        }
                    }
                });
                break;
            case R.id.rbEggLess:
                break;
            case R.id.rbEggWith:
                break;
        }

    }

}
