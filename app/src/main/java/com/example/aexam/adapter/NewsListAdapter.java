package com.example.aexam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.aexam.R;
import com.example.aexam.bean.News;

import java.util.List;

public class NewsListAdapter extends BaseAdapter {
    private List<News> list;

    public NewsListAdapter(List<News> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_news, parent, false);
            holder = new ViewHolder();
            holder.iv_img = convertView.findViewById(R.id.iv_news_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.iv_img.setImageResource(list.get(position).getImgId());
        return convertView;
    }

     static class ViewHolder{
        private ImageView iv_img;
    }
}
