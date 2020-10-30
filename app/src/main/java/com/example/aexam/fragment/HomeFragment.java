package com.example.aexam.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.aexam.R;
import com.example.aexam.adapter.HomeGridAdapter;
import com.example.aexam.adapter.HomeListAdapter;
import com.example.aexam.adapter.LoopViewAdapter;
import com.example.aexam.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private GridView gridView;
    private ListView listView;
    private ViewPager viewPager;
    private LinearLayout ll_dots_container;
    private int[] mImg;
    private List<ImageView> mImgList;
    private int previousSelectedPosition;
    private boolean isRunning = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = view.findViewById(R.id.gv_fragment_home);
        listView = view.findViewById(R.id.lv_fragment_home);
        viewPager = view.findViewById(R.id.vp_fragment_home);
        ll_dots_container = view.findViewById(R.id.ll_dots_loop);
        initGrid();
        initLoopView();  //实现轮播图
    }

    private void initLoopView() {
        mImg = new int[]{R.drawable.focus4, R.drawable.focus2, R.drawable.focus3, R.drawable.focus1, R.drawable.focus5};
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

    private void initGrid() {
        HomeGridAdapter adapter = new HomeGridAdapter();
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(getContext(), "0", Toast.LENGTH_SHORT).show();
                }
            }
        });


        HomeListAdapter adapter1 = new HomeListAdapter();
        listView.setAdapter(adapter1);
        listView.setFocusable(false);
        ListUtils.setListViewHeightBasedOnChildren(listView);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isRunning = false;
    }
}
