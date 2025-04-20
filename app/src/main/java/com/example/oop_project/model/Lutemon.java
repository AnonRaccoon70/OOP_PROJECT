package com.example.oop_project.model;

public abstract class Lutemon {
    protected String name;
    protected String color;
    protected int maxHp;
    protected int hp;
    protected int attack;
    protected int defense;
    protected int xp;

    public Lutemon(String name, String color, int maxHp, int attack, int defense) {
        this.name = name;
        this.color = color;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.xp = 0;
    }

    public int attack() {
        return attack + xp;
    }
    //we create a nice little mechanisms for the lutemons to calculate damage suffered
    public void defend(int incomingAttack) {
        int damage = incomingAttack - defense;
        if (damage < 0) {
            damage = 0;
        }
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public void gainXp(int amount) {
        xp += amount;
    }

    public void heal() {
        hp = maxHp;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getXp() {
        return xp;
    }

    public abstract void useAbility(Lutemon opponent);
}
