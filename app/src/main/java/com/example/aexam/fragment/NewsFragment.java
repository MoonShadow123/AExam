package com.example.aexam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.aexam.R;
import com.example.aexam.activity.NewsDetailsActivity;
import com.example.aexam.adapter.LoopViewAdapter;
import com.example.aexam.adapter.NewsListAdapter;
import com.example.aexam.bean.News;
import com.example.aexam.util.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsFragment extends Fragment {

    private ViewPager viewPager;
    private LinearLayout ll_dots_container;
    private int[] mImg;
    private List<ImageView> mImgList;
    private int previousSelectedPosition;
    private boolean isRunning = true;
    private ListView listView;
    private RadioGroup radioGroup;
    private List<News> list;
    private int[] imgs = {R.drawable.dang1, R.drawable.dang2, R.drawable.dang3, R.drawable.dang4, R.drawable.dang5};
    private NewsListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.vp_fragment_news);
        ll_dots_container = view.findViewById(R.id.ll_dots_loop_news);
        listView = view.findViewById(R.id.lv_fragment_news);
        radioGroup = view.findViewById(R.id.rg_news);
        initLoopView();  //实现轮播图
        initList();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_news_tuijian:
                        getRandomData();
                        break;
                    case R.id.rb_news_zuixing:
                        getRandomData();
                        break;
                    case R.id.rb_news_kandian:
                        getRandomData();
                        break;
                    case R.id.rb_news_gengdou:
                        getRandomData();
                        break;
                }
            }
        });
    }

    private void getRandomData() {
        for (int i = 0; i < list.size(); i++) {
            Random random = new Random();
            list.get(i).setImgId(imgs[random.nextInt(list.size())]);
        }
        adapter.notifyDataSetChanged();

    }


    private void initList() {
        list = new ArrayList<>();
        list = News.getList();
        adapter = new NewsListAdapter(list);
        listView.setAdapter(adapter);
        // 让ListView失去焦点
        listView.setFocusable(false);
        // 动态修改listView子项高度
        ListUtils.setListViewHeightBasedOnChildren(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), NewsDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initLoopView() {
        mImg = new int[]{R.drawable.dang5, R.drawable.dang2, R.drawable.dang3, R.drawable.dang4, R.drawable.dang1};
        mImgList = new ArrayList<>();
        ImageView imageView;
        View dotView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < mImg.length; i++) {
            //初始化要显示的图片对象
            imageView = new ImageView(getContext());
            imageView.setBackgroundResource(mImg[i]);
            mImgList.add(imageView);
            //加引导点
            dotView = new View(getContext());
            dotView.setBackgroundResource(R.drawable.dot);
            layoutParams = new LinearLayout.LayoutParams(15, 15);
            if (i != 0) {
                layoutParams.leftMargin = 10;
            }
            //设置默认所有都不可用
            dotView.setEnabled(false);
            ll_dots_container.addView(dotView, layoutParams);
        }

        ll_dots_container.getChildAt(0).setEnabled(true);
        previousSelectedPosition = 0;
        //设置适配器
        viewPager.setAdapter(new LoopViewAdapter(mImgList));
        // 把ViewPager设置为默认选中Integer.MAX_VALUE / t2，从十几亿次开始轮播图片，达到无限循环目的;
        int m = (Integer.MAX_VALUE / 2) % mImgList.size();
        int currentPosition = Integer.MAX_VALUE / 2 - m;
        viewPager.setCurrentItem(currentPosition);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int newPosition = i % mImgList.size();
                ll_dots_container.getChildAt(previousSelectedPosition).setEnabled(false);
                ll_dots_container.getChildAt(newPosition).setEnabled(true);
                previousSelectedPosition = newPosition;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        // 开启轮询
        new Thread() {
            public void run() {
                while (isRunning) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //下一条
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }.start();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        isRunning = false;
    }
}
