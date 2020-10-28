package com.example.aexam.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.aexam.R;
import com.example.aexam.adapter.GuidePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private List<View> viewList;
    private int[] bgId; // 背景图片资源id
    private List<ImageView> imgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        initViews();
        initViewList();
        initPager();
        initDots();
    }

    // 小圆点设置
    private void initDots() {
        int[] imgDotId = {R.id.iv_guide_dot1, R.id.iv_guide_dot2, R.id.iv_guide_dot3, R.id.iv_guide_dot4, R.id.iv_guide_dot5};
        imgList = new ArrayList<>();
        for (int i = 0; i < imgDotId.length; i++) {
            ImageView imageView = findViewById(imgDotId[i]);
            imgList.add(imageView);
        }

        imgList.get(0).setImageResource(R.drawable.purple_dot);

        for (int i = 0; i < imgList.size(); i++) {
            final int finalI = i;
            imgList.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(finalI);
                    }
                });
        }
    }

    private void initPager() {
        GuidePagerAdapter adapter = new GuidePagerAdapter(viewList, this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    private void initViewList() {
        bgId = new int[]{R.drawable.guide1, R.drawable.guide2, R.drawable.guide3, R.drawable.guide4, R.drawable.guide5};
        viewList = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < bgId.length; i++) {
            View view = inflater.inflate(R.layout.item_guide, null);
            view.setBackgroundResource(bgId[i]);
            viewList.add(view);
        }
    }

    private void initViews() {
        viewPager = findViewById(R.id.vp_guide);
    }

    // viewPager发生改变时候的事件
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 小圆点跟着viewPaper变
        for (int i = 0; i < imgList.size(); i++) {
            if (i == position) {
                imgList.get(i).setImageResource(R.drawable.purple_dot);
            } else {
                imgList.get(i).setImageResource(R.drawable.gray_dot);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}