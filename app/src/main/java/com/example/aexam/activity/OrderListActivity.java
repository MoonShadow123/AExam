package com.example.aexam.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aexam.R;
import com.example.aexam.adapter.OrderListAdapter;

public class OrderListActivity extends AppCompatActivity {

    private ListView listView;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        iv_back = findViewById(R.id.iv_order_back);
        listView = findViewById(R.id.lv_order);
        OrderListAdapter adapter = new OrderListAdapter();
        listView.setAdapter(adapter);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}