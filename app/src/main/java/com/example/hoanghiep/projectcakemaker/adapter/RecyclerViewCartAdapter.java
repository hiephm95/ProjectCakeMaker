package com.example.hoanghiep.projectcakemaker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.model.Product;

import java.util.ArrayList;

/**
 * Created by HoangHiep on 12/22/15.
 */
public class RecyclerViewCartAdapter extends RecyclerView.Adapter<RecyclerViewCartAdapter.MyViewHolderCart> {
    private ArrayList<Product> myCart;
    private static OnClickItemCart onClickItemCart;

    public RecyclerViewCartAdapter(ArrayList<Product> myCart) {
        this.myCart = myCart;
    }

    @Override
    public MyViewHolderCart onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);

        MyViewHolderCart viewHolderCart = new MyViewHolderCart(view);
        return viewHolderCart;
    }

    @Override
    public void onBindViewHolder(MyViewHolderCart holder, final int position) {
        holder.tvNameCart.setText(myCart.get(position).getName());
        holder.tvPriceCart.setText(String.valueOf(myCart.get(position).getPrice()));
        holder.icRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myCart.size();
    }

    public class MyViewHolderCart extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvNameCart;
        TextView tvPriceCart;
        ImageView icRemove;

        public MyViewHolderCart(View itemView) {
            super(itemView);
            tvNameCart = (TextView) itemView.findViewById(R.id.tvNameCart);
            tvPriceCart = (TextView) itemView.findViewById(R.id.tvPriceCart);
            icRemove = (ImageView) itemView.findViewById(R.id.ic_remove);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onClickItemCart.onItemClickCart(itemView, getPosition());
        }
    }

    public interface OnClickItemCart {
        public void onItemClickCart(View view, int position);
    }

    public void setOnClickItemCart(OnClickItemCart onClickItemCart) {
        this.onClickItemCart = onClickItemCart;
    }

    public void removeItem(int position) {
        myCart.remove(position);
        notifyItemRemoved(position);
    }
}
