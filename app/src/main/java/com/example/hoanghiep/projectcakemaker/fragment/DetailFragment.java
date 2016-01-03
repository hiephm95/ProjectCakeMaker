package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.model.Cart;
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements View.OnClickListener {
    TextView tvDetailName;
    TextView tvDetailPrice;
    TextView tvDetailDescription;
    Button btnCart;
    Spinner spinQuantity;
    RadioButton rbEggLess,rbEggWith;


    public DetailFragment() {
        // Required empty public constructor
    }

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_detail, container, false);
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

        String[] arraySpinner = new String[]
                {
                        "1", "2", "3", "4", "5", "6"
                };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arraySpinner);
        spinQuantity.setAdapter(adapter);

        Bundle bundle = this.getArguments();
        tvDetailName.setText(bundle.getString("p_Name"));
        tvDetailPrice.setText(bundle.getDouble("p_Price") + " $");
        tvDetailDescription.setText(bundle.getString("p_Description"));
    }

    @Override
    public void onClick(View v) {
//        boolean checked = ((RadioButton)v).isChecked();
        switch (v.getId()) {
            case R.id.btnCart:
                final Bundle bundle = this.getArguments();
                ParseQuery<Product> query = ParseQuery.getQuery(Product.class);
                query.fromLocalDatastore();
                query.getInBackground(bundle.getString("p_id"), new GetCallback<Product>() {
                    @Override
                    public void done(Product object, ParseException e) {
                        if (e == null) {
                            object.quantity = spinQuantity.getSelectedItemPosition();
                            Cart.list.add(object);
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
