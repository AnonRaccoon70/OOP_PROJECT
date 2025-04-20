package com.example.oop_project.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.oop_project.LutemonManager;
import com.example.oop_project.R;
import com.example.oop_project.model.BlackLutemon;
import com.example.oop_project.model.BlueLutemon;
import com.example.oop_project.model.GreenLutemon;
import com.example.oop_project.model.Lutemon;
import com.example.oop_project.model.OrangeLutemon;
import com.example.oop_project.model.YellowLutemon;

public class CreateFragment extends Fragment {

    private EditText nameInput;
    private Spinner colorSpinner;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create, container, false);

        nameInput = root.findViewById(R.id.nameInput);
        colorSpinner = root.findViewById(R.id.colorSpinner);
        Button createButton = root.findViewById(R.id.createButton);

        createButton.setOnClickListener(view -> {
            String name = nameInput.getText().toString().trim();
            String color = colorSpinner.getSelectedItem().toString();

            if (name.isEmpty()) {
                Toast.makeText(getContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                return;
            }

            Lutemon newLutemon;
            switch (color.toLowerCase()) {
                case "black": newLutemon = new BlackLutemon(name); break;
                case "yellow": newLutemon = new YellowLutemon(name); break;
                case "orange": newLutemon = new OrangeLutemon(name); break;
                case "green": newLutemon = new GreenLutemon(name); break;
                case "blue": newLutemon = new BlueLutemon(name); break;
                default: Toast.makeText(getContext(), "Invalid color", Toast.LENGTH_SHORT).show(); return;
            }

            LutemonManager.getInstance().addToHome(newLutemon);
            Toast.makeText(getContext(), color + " Lutemon created!", Toast.LENGTH_SHORT).show();
            nameInput.setText("");
        });

        return root;
    }
}


