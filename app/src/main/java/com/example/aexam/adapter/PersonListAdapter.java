package com.example.aexam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aexam.R;

import java.util.List;

public class PersonListAdapter extends BaseAdapter {
    private List<String> list;
    private static final String TAG = "PersonListAdapter";
    public PersonListAdapter(List<String> list) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_person, parent, false);
            holder = new ViewHolder();
            holder.tv_name = convertView.findViewById(R.id.tv_item_person_name);
            holder.iv_img = convertView.findViewById(R.id.iv_item_person_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(list.get(position));
        // 退出不显示进入箭头
        if (position != list.size() - 1) {
            holder.iv_img.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    static class ViewHolder{
        private TextView tv_name;
        private ImageView iv_img;
    }

}
