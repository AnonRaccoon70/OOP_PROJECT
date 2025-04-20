package com.example.oop_project.model;

public class BlueLutemon extends Lutemon {
    private boolean shieldActive = true;

    public BlueLutemon(String name) {
        super(name, "Blue", 110, 22, 18);
    }

    @Override
    public void defend(int incomingAttack) {
        if (shieldActive) {
            shieldActive = false;
        } else {
            super.defend(incomingAttack);
        }
    }

    @Override
    public void useAbility(Lutemon opponent) {
        opponent.defend(this.attack());
    }
}
