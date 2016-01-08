package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.multidex.MultiDex;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.interfaces.ScreenChangeListener;
import com.example.hoanghiep.projectcakemaker.job.SubmitFeedbackAsync;
import com.example.hoanghiep.projectcakemaker.model.Feedback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment implements View.OnClickListener, TextWatcher {


    public ContactFragment() {
        // Required empty public constructor
    }

    View root;

    ScreenChangeListener screenChangeListener;
    EditText etContactName, etContactEmail, etContactPhone, etSubjectEmail, etContactMessage;
    TextInputLayout textContactName, textContactEmail, textContactPhone, textContactSubject;
    String email;
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
        textContactName = (TextInputLayout) root.findViewById(R.id.textContactName);
        textContactEmail = (TextInputLayout) root.findViewById(R.id.textContactEmail);
        textContactPhone = (TextInputLayout) root.findViewById(R.id.textContactNum);
        textContactSubject = (TextInputLayout) root.findViewById(R.id.textSubjectEmail);
        btnSend = (Button) root.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        etContactName.addTextChangedListener(this);
        etContactEmail.addTextChangedListener(this);
        etContactPhone.addTextChangedListener(this);
        etSubjectEmail.addTextChangedListener(this);
        etContactMessage.addTextChangedListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSend:
                email = etContactEmail.getText().toString();
                if (etContactName.getText().toString().trim().equals("")) {
                    textContactName.setError("Input your name");
                }else if (!isValidEmail(email)) {
                    textContactEmail.setError("Input your address");
                }else if (etContactPhone.getText().toString().trim().equals("")) {
                    textContactPhone.setError("Input your phone");
                }else if (etContactMessage.getText().toString().trim().equals("")) {
                    etContactMessage.setError("Please input your message!");
                } else {
                    Feedback f = new Feedback();
                    f.setName(etContactName.getText().toString());
                    f.setEmail(etContactEmail.getText().toString());
                    f.setPhone(etContactPhone.getText().toString());
                    f.setSubject(etSubjectEmail.getText().toString());
                    f.setMessage(etContactMessage.getText().toString());
                    SubmitFeedbackAsync submitFeedbackAsync = new SubmitFeedbackAsync(f, getActivity());
                    submitFeedbackAsync.execute();
                }
                break;
        }
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
        email = etContactEmail.getText().toString();
        if (!etContactName.getText().toString().trim().equals("")) {
            textContactName.setErrorEnabled(false);
        }
        if (!etContactPhone.getText().toString().trim().equals("")) {
            textContactPhone.setErrorEnabled(false);
        }
        if (!isValidEmail(email)) {
            textContactEmail.setErrorEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
