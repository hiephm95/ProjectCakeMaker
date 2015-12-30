package com.example.hoanghiep.projectcakemaker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;

/**
 * Created by HoangHiep on 12/30/15.
 */
public class RecyclerViewSearchAdapter extends RecyclerView.Adapter<RecyclerViewSearchAdapter.MyViewHolder> {

    private static MyClickListener myClickListener;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_search, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewSearchAdapter.MyViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNameSearch;
        TextView tvPriceSearch;
        TextView tvQuantitySearch;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNameSearch = (TextView) itemView.findViewById(R.id.tvNameSearch);
            tvPriceSearch = (TextView) itemView.findViewById(R.id.tvPriceSearch);
            tvQuantitySearch = (TextView) itemView.findViewById(R.id.tvQuantitySearch);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(itemView, getPosition());
        }
    }

    public interface MyClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
}
