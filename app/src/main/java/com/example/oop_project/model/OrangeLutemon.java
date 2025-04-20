package com.example.oop_project.model;

public class OrangeLutemon extends Lutemon {
    private boolean debuffApplied = false;

    public OrangeLutemon(String name) {
        super(name, "Orange", 100, 25, 20);
    }

    @Override
    public void useAbility(Lutemon opponent) {
        if (!debuffApplied) {
            opponent.defense -= 5;
            if (opponent.defense < 0) opponent.defense = 0;
            debuffApplied = true;
        }
        opponent.defend(this.attack());
    }
}
