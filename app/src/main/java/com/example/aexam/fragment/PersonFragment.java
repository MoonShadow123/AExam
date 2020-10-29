package com.example.aexam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aexam.R;
import com.example.aexam.activity.PersonMsgActivity;
import com.example.aexam.adapter.PersonListAdapter;

import java.util.ArrayList;
import java.util.List;

public class PersonFragment extends Fragment {
    private List<String> list;
    private static final int PERSON_MSG = 0;
    private static final int PERSON_ORDER = 1;
    private static final int PERSON_PWD = 2;
    private static final int PERSON_FEEDBACK = 3;
    private static final int PERSON_EXIT = 4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        ListView listView = view.findViewById(R.id.lv_fragment_person);
        PersonListAdapter adapter = new PersonListAdapter(list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case PERSON_MSG:
                        // 个人信息
                        Intent intent = new Intent(getContext(), PersonMsgActivity.class);
                        startActivity(intent);
                        break;
                    case PERSON_ORDER:
                        // 订单列表
                        break;
                    case PERSON_PWD:
                        // 修改密码
                        break;
                    case PERSON_FEEDBACK:
                        // 意见反馈
                        break;
                    case PERSON_EXIT:
                        // 退出
                        getActivity().finish();
                        break;
                }
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        list.add("个人信息");
        list.add("订单列表");
        list.add("修改密码  ");
        list.add("意见反馈");
        list.add("退出");
    }
}
