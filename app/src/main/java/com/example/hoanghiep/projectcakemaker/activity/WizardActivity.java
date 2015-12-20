package com.example.hoanghiep.projectcakemaker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hoanghiep.projectcakemaker.R;
import com.example.hoanghiep.projectcakemaker.adapter.WizardAdapter;
import com.example.hoanghiep.projectcakemaker.interfaces.WizardChangeListener;

public class WizardActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener{

    private ViewPager viewPager_intro;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private WizardAdapter adapter;
    private TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);


        initProject();
        setUpViewPager();
        setUiPageViewController();
        setAction();

    }

    private void setAction() {
        tvSkip.setOnClickListener(this);
    }

    public void initProject() {
        viewPager_intro = (ViewPager) findViewById(R.id.viewPager_Intro);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        tvSkip = (TextView) findViewById(R.id.tvSkip);
        tvSkip.setOnClickListener(this);
    }

    public void setUpViewPager() {
        adapter = new WizardAdapter(getFragmentManager());
        viewPager_intro.setAdapter(adapter);
        viewPager_intro.setCurrentItem(0);
        viewPager_intro.setOnPageChangeListener(this);
    }

    private void setUiPageViewController() {
        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );


            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }
        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(getBaseContext(), MainActivity.class);
        startActivity(i);

    }

}
