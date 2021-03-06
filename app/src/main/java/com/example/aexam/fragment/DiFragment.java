package com.example.aexam.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aexam.R;
import com.example.aexam.adapter.DiListAdapter;
import com.example.aexam.util.ListUtils;

public class DiFragment extends Fragment {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_di,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.lv_fragment_di);
        DiListAdapter adapter = new DiListAdapter();
        listView.setAdapter(adapter);
        listView.setFocusable(false);
        ListUtils.setListViewHeightBasedOnChildren(listView);
    }
}
