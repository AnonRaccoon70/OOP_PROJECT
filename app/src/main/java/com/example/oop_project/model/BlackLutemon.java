package com.example.oop_project.model;

import java.util.Random;

public class BlackLutemon extends Lutemon {
    private static final Random rand = new Random();

    public BlackLutemon(String name) {
        super(name, "Black", 120, 30, 15);
    }

    @Override
    public void useAbility(Lutemon opponent) {
        if (rand.nextInt(100) < 20) {
            opponent.defend(this.attack() * 2);
        } else {
            opponent.defend(this.attack());
        }
    }
}
