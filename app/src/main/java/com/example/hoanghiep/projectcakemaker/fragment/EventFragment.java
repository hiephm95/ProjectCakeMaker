package com.example.hoanghiep.projectcakemaker.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.adapter.ItemAdapter;
import com.example.hoanghiep.projectcakemaker.interfaces.ScreenChangeListener;
import com.example.hoanghiep.projectcakemaker.job.EventAsync;
import com.example.hoanghiep.projectcakemaker.job.EventLocalAsync;
import com.example.hoanghiep.projectcakemaker.model.Event;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {


    View root;
    ItemAdapter adapter;
    ListView lsvItem;
    ScreenChangeListener screenChangeListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_event, container, false);
            lsvItem = (ListView) root.findViewById(R.id.lsvItem);
            screenChangeListener.setTitle("Event");

            EventLocalAsync eventLocalAsync = new EventLocalAsync(getActivity());
            eventLocalAsync.adapter = adapter;
            eventLocalAsync.lsvItem = lsvItem;
            eventLocalAsync.execute();
        }
        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        screenChangeListener = (ScreenChangeListener) activity;
    }
}
