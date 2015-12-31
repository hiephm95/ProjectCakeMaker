package com.example.hoanghiep.projectcakemaker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.activity.CartActivity;
import com.example.hoanghiep.projectcakemaker.model.Cart;
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by HoangHiep on 12/22/15.
 */
public class RecyclerViewCartAdapter extends RecyclerView.Adapter<RecyclerViewCartAdapter.MyViewHolderCart> {
    private List<Product> myCart;
    private static OnClickItemCart onClickItemCart;


    public RecyclerViewCartAdapter(List<Product> myCart) {
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
        ImageLoader.getInstance().displayImage(myCart.get(position).getPicturesList().get(0).getFile().getUrl(), holder.ivCartPicture);
        holder.spinQuantityCart.setSelection(myCart.get(position).quantity);
        holder.icRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
                for (Product p : myCart) {
                    Cart.total += p.getPrice() * (p.quantity + 1);
                }
                CartActivity.cartTotal.setText("" + Cart.total);
            }
        });

        holder.spinQuantityCart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int itemPostion, long id) {
                myCart.get(position).quantity = itemPostion;
                Cart.total = 0;
                for (Product p : myCart) {
                    Cart.total += p.getPrice() * (p.quantity + 1);
                }
                CartActivity.cartTotal.setText("" + Cart.total);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return myCart.size();
    }

    public class MyViewHolderCart extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvNameCart;
        TextView tvPriceCart;
        ImageView icRemove;
        Spinner spinQuantityCart;
        ImageView ivCartPicture;

        public MyViewHolderCart(View itemView) {
            super(itemView);
            tvNameCart = (TextView) itemView.findViewById(R.id.tvNameCart);
            tvPriceCart = (TextView) itemView.findViewById(R.id.tvPriceCart);
            icRemove = (ImageView) itemView.findViewById(R.id.ic_remove);
            spinQuantityCart = (Spinner) itemView.findViewById(R.id.spinQuantityCart);
            ivCartPicture = (ImageView) itemView.findViewById(R.id.ivCartPicture);
            itemView.setOnClickListener(this);
            String[] arraySpinner = new String[]
                    {
                            "1", "2", "3", "4", "5", "6"
                    };

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, arraySpinner);
            spinQuantityCart.setAdapter(adapter);

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
