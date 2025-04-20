package com.example.oop_project;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        Button playButton = findViewById(R.id.play_button);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0: tab.setText("Training"); break;
                        case 1: tab.setText("Home"); break;
                        case 2: tab.setText("Create"); break;
                    }
                }).attach();

        playButton.setOnClickListener(v -> {
            PlayOptionsDialog dialog = new PlayOptionsDialog();
            dialog.show(getSupportFragmentManager(), "PlayOptionsDialog");
        });
    }
}

