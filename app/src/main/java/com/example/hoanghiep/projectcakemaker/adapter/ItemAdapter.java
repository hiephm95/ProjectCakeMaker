package com.example.hoanghiep.projectcakemaker.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.activity.CakeActivityOne;
import com.example.hoanghiep.projectcakemaker.activity.DetailActivity;
import com.example.hoanghiep.projectcakemaker.model.Category;
import com.example.hoanghiep.projectcakemaker.model.Product;

import java.util.ArrayList;

/**
 * Created by HoangHiep on 12/20/15.
 */
public class ItemAdapter extends BaseAdapter{
    ArrayList<Product> products;
    ArrayList<Category> categories;

    public ItemAdapter(ArrayList<Product> products, ArrayList<Category> categories) {
        this.products = products;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolderCategory vhCategory;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            ViewGroup layoutHorizontall = (ViewGroup) convertView.findViewById(R.id.layout_hori);
            vhCategory = new ViewHolderCategory(convertView);

            for (Product p: products) {
                if (categories.get(position).id == p.categoryId) {
                    final View view = View.inflate(parent.getContext(), R.layout.horizontal_item, null);
                    ViewHolderProduct vhProduct = new ViewHolderProduct(view);
                    vhProduct.tvName.setText(p.name);
                    vhProduct.tvPrice.setText(String.valueOf(p.price));
                    vhProduct.imgItem.setOnClickListener(new View.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View v) {
                            view.setTransitionName("transitionImage");
                            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) parent.getContext(), view, view.getTransitionName());
                            Intent i = new Intent(parent.getContext(), DetailActivity.class);
                            parent.getContext().startActivity(i, optionsCompat.toBundle());
                        }
                    });

                    layoutHorizontall.addView(view);
                }
            }
            convertView.setTag(vhCategory);
        } else {
            vhCategory = (ViewHolderCategory) convertView.getTag();
        }
        Category c = categories.get(position);
        vhCategory.tvBrand.setText(c.brand);
        vhCategory.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(parent.getContext(), CakeActivityOne.class);
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
        ImageView imgItem;
        public ViewHolderProduct(View viewProduct) {
            tvName = (TextView) viewProduct.findViewById(R.id.tvName);
            tvPrice = (TextView) viewProduct.findViewById(R.id.tvPrice);
            imgItem = (ImageView) viewProduct.findViewById(R.id.imageItem);

        }
    }
}
