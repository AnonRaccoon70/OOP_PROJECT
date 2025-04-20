package com.example.oop_project;

import com.example.oop_project.model.Lutemon;
import java.util.ArrayList;
import java.util.List;

public class LutemonManager {
    //we use the singleton manager to set up anything that we do not need more of
    //like the lutemon manager instance
    private static LutemonManager instance;
    private final List<Lutemon> home;
    private final List<Lutemon> arena;
    private Lutemon trainingSlot;
    private long trainingEndTime;
    //we set up the home and arena lists to place the accordingly
    private LutemonManager() {
        home = new ArrayList<>();
        arena = new ArrayList<>();
    }
    //create a single LutmeonManager instance!
    public static synchronized LutemonManager getInstance() {
        if (instance == null) instance = new LutemonManager();
        return instance;
    }
    //the manager handles all the Lutemon placing tasks
    public List<Lutemon> getHome() { return home; }
    public void addToHome(Lutemon l) { home.add(l); }

    public void moveToHomeFromArena(Lutemon l) {
        if (arena.remove(l)) {
            l.heal();
            home.add(l);
        }
    }

    public boolean isTraining() { return trainingSlot != null; }
    public void startTraining(Lutemon l, long durationMillis) {
        if (!isTraining() && home.remove(l)) {
            trainingSlot = l;
            trainingEndTime = System.currentTimeMillis() + durationMillis;
        }
    }
    public void checkTrainingComplete() {
        if (isTraining() && System.currentTimeMillis() >= trainingEndTime) {
            trainingSlot.gainXp(1);
            trainingSlot.heal();
            home.add(trainingSlot);
            trainingSlot = null;
        }
    }

    public long getRemainingTrainingTime() {
        return isTraining() ? Math.max(0, trainingEndTime - System.currentTimeMillis()) : 0;
    }

    public void removeLutemon(Lutemon defender) {
        home.remove(defender);
    }
}
