package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.interfaces.ScreenChangeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment implements View.OnClickListener{


    public ContactFragment() {
        // Required empty public constructor
    }

    View root;

    ScreenChangeListener screenChangeListener;
    EditText etContactName,etContactEmail,etContactPhone,etSubjectEmail,etContactMessage;
    String contactName, contactEmail, contactPhone, subjectEmail, contactMessage;
    Button btnSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_contact, container, false);
            MultiDex.install(getActivity());
            initView();
        }
        return root;
    }

    public void initView() {
        etContactName = (EditText) root.findViewById(R.id.etContactName);
        etContactEmail = (EditText) root.findViewById(R.id.etContactEmail);
        etContactPhone = (EditText) root.findViewById(R.id.etContactPhone);
        etSubjectEmail = (EditText) root.findViewById(R.id.etSubjectEmail);
        etContactMessage = (EditText) root.findViewById(R.id.etContactMessage);
        btnSend = (Button) root.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
        screenChangeListener.setTitle("Contact Us");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        screenChangeListener = (ScreenChangeListener) activity;
    }
}
