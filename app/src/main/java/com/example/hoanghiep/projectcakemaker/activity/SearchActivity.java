package com.example.hoanghiep.projectcakemaker.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.adapter.RecyclerViewAdapter;
import com.example.hoanghiep.projectcakemaker.adapter.RecyclerViewSearchAdapter;
import com.example.hoanghiep.projectcakemaker.job.ProductByNameAsync;
import com.example.hoanghiep.projectcakemaker.model.Product;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivActionSearch;
    EditText etSearchInput;
    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ivActionSearch = (ImageView) findViewById(R.id.actionBackSearch);
        etSearchInput = (EditText) findViewById(R.id.etSearchInput);

        recyclerView = (RecyclerView) findViewById(R.id.rvSearch);

        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        ivActionSearch.setOnClickListener(this);

        etSearchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ProductByNameAsync productByNameAsync = new ProductByNameAsync(SearchActivity.this);
                productByNameAsync.recyclerView = recyclerView;
                productByNameAsync.adapter = adapter;
                if (etSearchInput.getText().toString().equals("")) {
                    productByNameAsync.execute("aaaaaaaa");
                } else {
                    productByNameAsync.execute(etSearchInput.getText().toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etSearchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ProductByNameAsync productByNameAsync = new ProductByNameAsync(SearchActivity.this);
                    productByNameAsync.recyclerView = recyclerView;
                    productByNameAsync.adapter = adapter;
                    productByNameAsync.execute(etSearchInput.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        finish();
        //overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
    }
}
