package com.example.oop_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.DialogFragment;

public class PlayOptionsDialog extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_play_options, container, false);

        Button quickFightButton = view.findViewById(R.id.quick_fight_button);

        quickFightButton.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), QuickFight.class));
            dismiss();
        });


        return view;
    }
}


