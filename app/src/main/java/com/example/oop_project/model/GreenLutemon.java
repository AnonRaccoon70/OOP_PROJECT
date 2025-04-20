package com.example.oop_project.model;

public class GreenLutemon extends Lutemon {

    public GreenLutemon(String name) {
        super(name, "Green", 100, 18, 22);
    }

    private boolean healed = false;

    @Override
    public void useAbility(Lutemon opponent) {
        if (!healed) {
            this.hp += 10;
            if (this.hp > maxHp) this.hp = maxHp;
            healed = true;
        }
        opponent.defend(this.attack());
    }
}

