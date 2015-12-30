package com.example.hoanghiep.projectcakemaker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by HoangHiep on 12/30/15.
 */
public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.MyViewHolder>  {
    private List<Product> products;
    private static MyClickListener myClickListener;

    public RecyclerViewHomeAdapter(List<Product> myData) {
        this.products = myData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_cakes, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(products.get(position).getName());
        holder.tvPrice.setText(String.valueOf(products.get(position).getPrice()));
        ImageLoader.getInstance().displayImage(products.get(position).getPicturesList().get(0).getFile().getUrl(), holder.ivProductMore);


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvPrice;
        ImageView ivProductMore;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            ivProductMore = (ImageView) itemView.findViewById(R.id.ivProductMore);
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
