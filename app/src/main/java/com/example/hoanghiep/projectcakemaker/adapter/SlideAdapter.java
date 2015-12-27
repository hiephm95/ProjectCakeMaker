package com.example.hoanghiep.projectcakemaker.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hoanghiep.projectcakemaker.R;

/**
 * Created by HoangHiep on 12/27/15.
 */
public class SlideAdapter extends PagerAdapter {
    int[] slideShow = {R.mipmap.background, R.mipmap.banner_wedding_cake};

    Context context;
    LayoutInflater inflater;

    public SlideAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return slideShow.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.slide_pager, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.imagePager);
        imageView.setImageResource(slideShow[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
