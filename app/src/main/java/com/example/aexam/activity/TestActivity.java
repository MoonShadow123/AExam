package com.example.aexam.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.aexam.R;
import com.example.aexam.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout ll_dots;
    private List<ImageView> list;
    private int prePos = 0;
    private boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        viewPager = findViewById(R.id.vp_test);
        ll_dots = findViewById(R.id.ll_test);

        int[] imgs = {R.drawable.dang1, R.drawable.dang2, R.drawable.dang3, R.drawable.dang4, R.drawable.dang5};
        list = new ArrayList<>();

        ImageView imageView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < imgs.length; i++) {
            imageView = new ImageView(this);
            imageView.setImageResource(imgs[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            list.add(imageView);

            // 点
            View dot = new View(this);
            dot.setBackgroundResource(R.drawable.gray_dot);
            layoutParams = new LinearLayout.LayoutParams(18, 18);
            if (i != 0) {
                layoutParams.leftMargin = 10;
            }
            dot.setLayoutParams(layoutParams);
            ll_dots.addView(dot);
        }

        ll_dots.getChildAt(0).setBackgroundResource(R.drawable.purple_dot);

        viewPager.setAdapter(new TestAdapter(list));

        int m = (Integer.MAX_VALUE / 2) % list.size();
        int currentPos = (Integer.MAX_VALUE / 2) - m;
        viewPager.setCurrentItem(currentPos);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int newPos = position % list.size();
                ll_dots.getChildAt(newPos).setBackgroundResource(R.drawable.purple_dot);
                ll_dots.getChildAt(prePos).setBackgroundResource(R.drawable.gray_dot);
                prePos = newPos;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        running = false;
    }
}