package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.adapter.ItemAdapter;
import com.example.hoanghiep.projectcakemaker.job.ProductAsync;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment{


    View root;
    ItemAdapter adapter;
    ListView lsvItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_event, container, false);
            lsvItem = (ListView) root.findViewById(R.id.lsvItem);

            ProductAsync productAsync = new ProductAsync(getActivity());
            productAsync.adapter = adapter;
            productAsync.lsvItem = lsvItem;
            productAsync.execute();


        }
        return root;
    }




}
