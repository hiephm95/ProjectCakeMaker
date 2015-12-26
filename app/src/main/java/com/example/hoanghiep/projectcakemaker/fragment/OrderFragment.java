package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.hoanghiep.projectcakemaker.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements View.OnClickListener{


    public OrderFragment() {
        // Required empty public constructor
    }

    View root;
    EditText edtDate;

    Calendar calendar = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_order, container, false);
            edtDate = (EditText) root.findViewById(R.id.edtDate);
            edtDate.setOnClickListener(this);


        }
        return root;
    }

    @Override
    public void onClick(View v) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(getActivity(), listener, year, month, day).show();
    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            edtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        }
    };
}
