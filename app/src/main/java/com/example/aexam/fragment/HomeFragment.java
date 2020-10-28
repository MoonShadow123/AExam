package com.example.aexam.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aexam.R;
import com.example.aexam.adapter.HomeGridAdapter;
import com.example.aexam.adapter.HomeListAdapter;

public class HomeFragment  extends Fragment {
    private GridView gridView;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = view.findViewById(R.id.gv_fragment_home);
        listView = view.findViewById(R.id.lv_fragment_home);
        initGrid();
    }

    private void initGrid() {
        HomeGridAdapter adapter = new HomeGridAdapter();
        gridView.setAdapter(adapter);

        HomeListAdapter adapter1 = new HomeListAdapter();
        listView.setAdapter(adapter1);
    }
}
