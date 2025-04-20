package com.example.oop_project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.oop_project.LutemonManager;
import com.example.oop_project.R;
import com.example.oop_project.LutemonAdapter;

public class HomeFragment extends Fragment {

    private LutemonAdapter lutemonAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        lutemonAdapter = new LutemonAdapter(LutemonManager.getInstance().getHome());
        recyclerView.setAdapter(lutemonAdapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        lutemonAdapter.notifyDataSetChanged();
    }
}


