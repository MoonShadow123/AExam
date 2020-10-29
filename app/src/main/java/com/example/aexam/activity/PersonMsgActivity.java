package com.example.aexam.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aexam.R;
import com.example.aexam.adapter.PersonMsgListAdapter;

import java.util.ArrayList;
import java.util.List;

public class PersonMsgActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> list;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_msg);

        initData();
        iv_back = findViewById(R.id.iv_person_msg);
        listView = findViewById(R.id.lv_person_msg);
        PersonMsgListAdapter adapter = new PersonMsgListAdapter(list);
        listView.setAdapter(adapter);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initData() {
        list = new ArrayList<>();
        list.add("昵称：月影");
        list.add("性别：男");
        list.add("联系电话：13142338645  ");
        list.add("证件号：430***********9635");
        list.add("地址：湖南xxxxxxxxxxxxxx");
    }
}