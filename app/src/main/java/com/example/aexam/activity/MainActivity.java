package com.example.aexam.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.aexam.R;
import com.example.aexam.fragment.DiFragment;
import com.example.aexam.fragment.HomeFragment;
import com.example.aexam.fragment.NewsFragment;
import com.example.aexam.fragment.PersonFragment;
import com.example.aexam.fragment.ZiFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        initViews();
    }

    /**
     * 初始化Fragment并将Fragment添加到fragments中，此处的fragments是一个Fragment的列表
     */
    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new DiFragment());
        fragments.add(new ZiFragment());
        fragments.add(new NewsFragment());
        fragments.add(new PersonFragment());
        // 默认显示首页的fragment
        switchFragments(0);
    }

    /**
     * 切换fragment
     */
    private void switchFragments(int index) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if (i == index) {//显示fragment
                if (fragment.isAdded()) {//如果当前fragment已经创建,直接显示fragment
                    fragmentTransaction.show(fragment);
                } else {//第一创建fragment, 将fragment放入到容器中
                    fragmentTransaction.add(R.id.fl_main_container, fragment);
                }
            } else {//隐藏剩余fragment
                if (fragment.isAdded()) {
                    fragmentTransaction.hide(fragment);
                }
            }
        }

        fragmentTransaction.commitNowAllowingStateLoss();
    }

    private void initViews() {
        rg = findViewById(R.id.rg_main);
        rg.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_main_home:
                //  首页
                switchFragments(0);
                break;
            case R.id.rb_main_di:
                //  地铁
                switchFragments(1);
                break;
            case R.id.rb_main_zi:
                //  智慧党建
                switchFragments(2);
                break;
            case R.id.rb_main_news:
                // 新闻
                switchFragments(3);
                break;
            case R.id.rb_main_person:
                //  个人中心
                switchFragments(4);
                break;

        }
    }
}