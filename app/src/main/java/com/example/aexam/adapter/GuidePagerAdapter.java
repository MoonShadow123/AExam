package com.example.aexam.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.PagerAdapter;

import com.example.aexam.R;
import com.example.aexam.activity.MainActivity;

import java.util.List;

public class GuidePagerAdapter extends PagerAdapter {
    private List<View> list;
    private Context context;
    private Button networkBtn, mainBtn,saveBtn,cancelBtn;
    private View dialogView;
    private AlertDialog dialog;

    public GuidePagerAdapter(List<View> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(list.get(position));
        if (position == list.size() - 1) {
            networkBtn = list.get(position).findViewById(R.id.btn_guide_network);
            mainBtn = list.get(position).findViewById(R.id.btn_guide_main);
            // 如果引导页进入最后一页，显示按钮
            networkBtn.setVisibility(View.VISIBLE);
            mainBtn.setVisibility(View.VISIBLE);
            mainBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            });

            dialog = new AlertDialog.Builder(context).create();
            dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_guide_network, null);
            dialog.setView(dialogView);
            cancelBtn = dialogView.findViewById(R.id.btn_guide_cancel);
            saveBtn = dialogView.findViewById(R.id.btn_guide_save);

            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            networkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.show();
                }
            });
        }

        return list.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
