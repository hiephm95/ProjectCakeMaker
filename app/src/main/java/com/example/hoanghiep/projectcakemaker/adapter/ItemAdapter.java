package com.example.hoanghiep.projectcakemaker.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.activity.CakeActivityItem;
import com.example.hoanghiep.projectcakemaker.activity.DetailActivity;
import com.example.hoanghiep.projectcakemaker.model.Event;
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;


public class ItemAdapter extends BaseAdapter {
    List<Event> events;

    public ItemAdapter(List<Event> events) {
        this.events = events;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolderCategory vhCategory;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            ViewGroup layoutHorizontall = (ViewGroup) convertView.findViewById(R.id.layout_hori);
            vhCategory = new ViewHolderCategory(convertView);

            for (final Product p : events.get(position).productList) {
                final View view = View.inflate(parent.getContext(), R.layout.horizontal_item, null);
                ViewHolderProduct vhProduct = new ViewHolderProduct(view);
                vhProduct.tvName.setText(p.getName());
                vhProduct.tvPrice.setText(String.valueOf(p.getPrice()));
                ImageLoader.getInstance().displayImage(p.getPicturesList().get(0).getFile().getUrl(), vhProduct.ivHorizontalProduct);

                vhProduct.ivHorizontalProduct.setOnClickListener(new View.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View v) {
                        view.setTransitionName("transitionImage");
                        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) parent.getContext(), view, view.getTransitionName());
                        Intent i = new Intent(parent.getContext(), DetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("p_Avatar", p.getPicturesList().get(0).getFile().getUrl());
                        bundle.putString("p_Name", p.getName());
                        bundle.putDouble("p_Price", p.getPrice());
                        bundle.putString("p_Description", p.getDescription());
                        i.putExtras(bundle);

                        parent.getContext().startActivity(i, optionsCompat.toBundle());
                    }
                });

                layoutHorizontall.addView(view);
            }
            convertView.setTag(vhCategory);
        } else {
            vhCategory = (ViewHolderCategory) convertView.getTag();
        }
        vhCategory.tvBrand.setText(events.get(position).getName());
        vhCategory.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(parent.getContext(), CakeActivityItem.class);
                i.putExtra("event", events.get(position).getName());
                parent.getContext().startActivity(i);
            }
        });
        return convertView;
    }


    class ViewHolderCategory {
        TextView tvBrand;
        TextView tvMore;

        public ViewHolderCategory(View viewCate) {
            tvBrand = (TextView) viewCate.findViewById(R.id.tvBrand);
            tvMore = (TextView) viewCate.findViewById(R.id.tvMore);
        }
    }

    class ViewHolderProduct {
        TextView tvName;
        TextView tvPrice;
        ImageView ivHorizontalProduct;

        public ViewHolderProduct(View viewProduct) {
            tvName = (TextView) viewProduct.findViewById(R.id.tvName);
            tvPrice = (TextView) viewProduct.findViewById(R.id.tvPrice);
            ivHorizontalProduct = (ImageView) viewProduct.findViewById(R.id.ivHorizontalProduct);
        }
    }
}
