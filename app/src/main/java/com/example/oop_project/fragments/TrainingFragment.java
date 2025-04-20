package com.example.oop_project.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.oop_project.R;
import com.example.oop_project.LutemonManager;
import com.example.oop_project.model.Lutemon;

public class TrainingFragment extends Fragment {
    private TextView statusTv;
    private Button startBtn;
    private CountDownTimer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_training, container, false);
        statusTv = v.findViewById(R.id.training_status);
        startBtn = v.findViewById(R.id.start_training_button);

        updateUI();

        startBtn.setOnClickListener(view -> {
            Lutemon toTrain = LutemonManager.getInstance().getHome().isEmpty() ? null :
                    LutemonManager.getInstance().getHome().get(0);
            if (toTrain != null && !LutemonManager.getInstance().isTraining()) {
                long duration = 5 * 60 * 1000;
                LutemonManager.getInstance().startTraining(toTrain, duration);
                startCountdown(duration);
            }
        });
        return v;
    }

    private void startCountdown(long millis) {
        timer = new CountDownTimer(millis, 1000) {
            public void onTick(long msUntilFinished) {
                statusTv.setText("Training: " + msUntilFinished/1000 + "s remaining");
            }
            public void onFinish() {
                LutemonManager.getInstance().checkTrainingComplete();
                updateUI();
            }
        }.start();
        updateUI();
    }

    private void updateUI() {
        if (LutemonManager.getInstance().isTraining()) {
            long rem = LutemonManager.getInstance().getRemainingTrainingTime();
            statusTv.setText("Training: " + rem/1000 + "s remaining");
            startBtn.setEnabled(false);
        } else {
            statusTv.setText("No Lutemon training");
            startBtn.setEnabled(true);
        }
    }

    @Override public void onDestroyView() {
        if (timer != null) timer.cancel();
        super.onDestroyView();
    }
}

