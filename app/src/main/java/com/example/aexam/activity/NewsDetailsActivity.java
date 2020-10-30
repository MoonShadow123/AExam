package com.example.aexam.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aexam.R;
import com.example.aexam.adapter.NewsDetailsListAdapter;
import com.example.aexam.util.ListUtils;

public class NewsDetailsActivity extends AppCompatActivity {
    private ListView listView;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        iv_back = findViewById(R.id.iv_news_details_back);
        listView = findViewById(R.id.lv_news_details);
        NewsDetailsListAdapter adapter = new NewsDetailsListAdapter();
        listView.setAdapter(adapter);
        ListUtils.setListViewHeightBasedOnChildren(listView);
        listView.setFocusable(false);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}