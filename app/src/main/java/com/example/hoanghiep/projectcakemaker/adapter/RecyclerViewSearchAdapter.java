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
public class RecyclerViewSearchAdapter extends RecyclerView.Adapter<RecyclerViewSearchAdapter.MySearchViewHolder> {

    private static MyClickListener myClickListener;
    private List<Product> list;

    public RecyclerViewSearchAdapter(List<Product> list) {
        this.list = list;
    }

    @Override
    public MySearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_search, parent, false);

        MySearchViewHolder myViewHolder = new MySearchViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewSearchAdapter.MySearchViewHolder holder, int position) {
        holder.tvNameSearch.setText(list.get(position).getName());
        holder.tvPriceSearch.setText(String.valueOf(list.get(position).getPrice()) + " $");
        ImageLoader.getInstance().displayImage(list.get(position).getPicturesList().get(0).getFile().getUrl(), holder.ivSearchPicture);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MySearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNameSearch;
        TextView tvPriceSearch;
        ImageView ivSearchPicture;

        public MySearchViewHolder(View itemView) {
            super(itemView);
            tvNameSearch = (TextView) itemView.findViewById(R.id.tvNameSearch);
            tvPriceSearch = (TextView) itemView.findViewById(R.id.tvPriceSearch);
            ivSearchPicture = (ImageView) itemView.findViewById(R.id.ivSearchPicture);
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
