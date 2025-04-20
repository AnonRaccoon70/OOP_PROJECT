package com.example.oop_project.model;

import java.util.Random;

public class YellowLutemon extends Lutemon {
    private static final Random rand = new Random();

    public YellowLutemon(String name) {
        super(name, "Yellow", 90, 20, 25);
    }

    @Override
    public void useAbility(Lutemon opponent) {
        opponent.defend(this.attack());
        if (rand.nextInt(100) < 30) {
            opponent.defend(this.attack());
        }
    }
}
