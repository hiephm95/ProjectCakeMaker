package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.activity.OrderActivity;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements View.OnClickListener {
    TextView tvDetailName;
    TextView tvDetailPrice;
    TextView tvDetailDescription;
    Button btnOrder;

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

    private void initView()
    {
        tvDetailName = (TextView) root.findViewById(R.id.tvDetailName);
        tvDetailPrice = (TextView) root.findViewById(R.id.tvDetailPrice);
        tvDetailDescription = (TextView) root.findViewById(R.id.tvDetailDescription);
        btnOrder = (Button) root.findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(this);

        Bundle bundle = this.getArguments();
        tvDetailName.setText(bundle.getString("p_Name"));
        tvDetailPrice.setText(bundle.getDouble("p_Price") + " $");
        tvDetailDescription.setText(bundle.getString("p_Description"));
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getActivity(), OrderActivity.class);
        startActivity(i);
    }
}
