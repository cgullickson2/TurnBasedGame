package com.example.cj.fightyourfriends;

public class Player {
    final int STARTING_HEALTH = 100;

    int health;
    boolean attacker;
    int[] choices;

    public Player() {
        this.health = STARTING_HEALTH;
        this.attacker = false;
    }

    public Player(boolean attacker) {
        this.health = STARTING_HEALTH;
        if (attacker)
            this.attacker = true;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public boolean isAttacker() {
        return attacker;
    }

    public void stats() {
        System.out.println("\n--- STATS ---");
        if (attacker) {
            System.out.println("You are attacking");
        } else {
            System.out.println("You are defending");
        }
    }

    public int getHealth() {
        return this.health;
    }

    public static void main(String[] args) {
    }
}