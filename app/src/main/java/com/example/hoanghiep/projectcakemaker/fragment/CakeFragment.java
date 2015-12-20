package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.adapter.ItemAdapter;
import com.example.hoanghiep.projectcakemaker.model.Category;
import com.example.hoanghiep.projectcakemaker.model.Product;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CakeFragment extends Fragment{


    View root;

    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();
    ListView lsvItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_cake, container, false);
            lsvItem = (ListView) root.findViewById(R.id.lsvItem);

            categories.add(new Category(1, "New + Hot"));
            categories.add(new Category(2, "BrithDay"));
            categories.add(new Category(3, "Wedding"));

            products.add(new Product(1, "ABC", 2));
            products.add(new Product(1, "ABCD", 3));
            products.add(new Product(2, "ABCDE", 4));
            products.add(new Product(2, "ABCDF", 5));
            products.add(new Product(3, "EEE", 6));
            products.add(new Product(3, "DDD", 7));

            ItemAdapter adapter = new ItemAdapter(products, categories);
            lsvItem.setAdapter(adapter);
            lsvItem.setDivider(null);

        }
        return root;
    }




}
