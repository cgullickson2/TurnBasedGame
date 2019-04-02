package com.example.cj.fightyourfriends;

import java.io.Serializable;
import java.util.Random;

public class Game implements Serializable{
    Player player1;
    Player player2;

    public boolean isOver(Game game) {
        if(game.player1.health <= 0 || game.player2.health <= 0)
            return true;
        return false;
    }

    public void restartGame( Game game) {
        game.player1.restartPlayer();
        game.player2.restartPlayer();
    }

    public Game() {
        this.player1 = new Player(true);
        this.player2 = new Player();

    }

    public int[] chooseOrder() {

        int[] choice = new int[3];
        // Prompt for attack/block x3
        for (int i = 0; i < 3; i++) {
            System.out.print("Choice "+ (i + 1) +": ");
        }

        return choice;
    }

    public int[] chooseOrderBot() {

        int[] choice = new int[3];
        Random rand = new Random();
        // Prompt for attack/block x3
        for (int i = 0; i < 3; i++) {
            choice[i] = rand.nextInt(2);
        }

        return choice;
    }

    // Return amount of damage dealt
    public int executePhase( Player attacker, Player guesser) {

        // If wrong guess, perfectGuess is disabled
        boolean perfectGuess = true;
        int perfectAttack = 0;
        int damage = 0;
        for( int i = 0; i < 3; i++) {
            if (attacker.choices[i] != guesser.choices[i]) {
                guesser.takeDamage(10);
                damage += 10;
                perfectGuess = false;
                perfectAttack++;
            }
        }

        // Defender parries if guesses are perfect
        if (perfectGuess) {
            attacker.takeDamage(15);
            return -15;
            //System.out.println("\n-Perfect Guess! Guesser parries-");
        // If perfectAttack = 3, deal bonus damage
        } else if ( perfectAttack == 3) {
            guesser.takeDamage(15);
            return 45;
            //System.out.println("\n-Perfect attack! Dealing bonus damage-");
        }

        return damage;
    }

//    public static void main(String args[]) {
//        Game game = new Game();
//
//        Scanner scnr = new Scanner(System.in);
//
//        while(!game.isOver(game)) {
//
//            // Display current health and role
//            game.player1.stats();
//            System.out.println(game.player1.name + "'s Health = " + game.player1.health);
//            System.out.println(game.player2.name + "'s Health = " + game.player2.health);
//
//            // Player one in attack phase
//            if( game.player1.isAttacker()){
//
//                // Player one choose attack/block order
//                game.player1.choices = game.chooseOrder(scnr);
//
//                // Player two guess attack/block order
//                game.player2.choices = game.chooseOrderBot();
//
//                game.executePhase( game.player1, game.player2);
//
//            // Player two in attack phase
//            } else {
//
//                // Player one choose attack/block order
//                game.player1.choices = game.chooseOrder(scnr);
//
//                // Player two guess attack/block order
//                game.player2.choices = game.chooseOrderBot();
//
//                game.executePhase( game.player2, game.player1);
//            }
//            game.player1.attacker = !game.player1.attacker;
//            game.player2.attacker = !game.player2.attacker;
//        }
//
//        if ( game.player1.health <= 0) {
//            System.out.println("\n <--- YOU LOST --->");
//        } else {
//            System.out.println("\n <--- YOU WON --->");
//        }
//    }

}