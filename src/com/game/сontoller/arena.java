package com.game.сontoller;
import com.game.model.Droid;

import java.util.Scanner;

public class arena {
    private Droid[] attackers;
    private Droid[] defenders;
    private int currentRound = 0;

    public arena(Droid[] attackers, Droid[] defenders){
        int length = attackers.length;
        this.defenders = new Droid[length];
        this.attackers = new Droid[length];

        for (int i = 0; i < length; i++) {
            this.attackers[i] = attackers[i];
            this.defenders[i] = defenders[i];
        }
        printSeparator();
        System.out.print("\nThe battle between droids is about to begin ");
        System.out.println('\n');
        System.out.println(this);
        printSeparator();
    }

    public arena(Droid attacker, Droid defender){
        this.defenders = new Droid[1];
        this.attackers = new Droid[1];

        this.attackers[0] = attacker;
        this.defenders[0] = defender;
        printSeparator();
        System.out.println("\nThe battle between " + attacker.getName() + " and " + defender.getName() + " is about to begin!");
        printSeparator();
    }

    public void fight() throws InterruptedException {
        do {
            swapOpponents();        // Swap opponents
            nextRound();            // Start a new round
        } while (anyoneIsAlive(defenders));     // Check if there is at least 1 working defender left

        printWinnerInfo();          // Print winner information
    }

    private boolean anyoneIsAlive(Droid[] def) {
        for (Droid i : def)
            if (i.getHealth() > 0)
                return true;
        return false;
    }

    private void nextRound() throws InterruptedException {
        System.out.println("ROUND " + ++currentRound);
        System.out.println("Actions for droids:");
        System.out.println("1.Heal");
        System.out.println("2.Hit");
        Scanner In = new Scanner(System.in);
        int choice;
        int opponent;           // Opponent
        for (int i = 0; i < attackers.length && anyoneIsAlive(defenders); i++) {
            if (attackers[i].getHealth() > 0) { // Check if droid is operational
                System.out.println("What will " + attackers[i].getName() + " do?");
                choice = In.nextInt();
                if (choice == 1)
                    attackers[i].toCure();
                else if (choice == 2) {
                    if (defenders[i].getHealth() > 0)
                        defenders[i].toHit(attackers[i]);
                    else {
                        opponent = findOpponent();      // Search for an opponent
                        if (opponent != -1) {           // If opponent found
                            defenders[opponent].toHit(attackers[i]);
                        } else
                            break;
                    }
                } else
                    System.out.println("You have selected a non-existent option! Turn skipped");
                Thread.sleep(1000);
            }
        }

        Thread.sleep(1500);
        System.out.println('\n');
        System.out.println(this);
        printSeparator();
        Thread.sleep(2500);
    }

    private int findOpponent() {
        for (int i = 0; i < defenders.length; i++) {
            if (defenders[i].getHealth() > 0)
                return i;
        }
        return -1;
    }

    private void swapOpponents() {
        Droid[] newDefender = attackers;
        Droid[] newAttacker = defenders;
        defenders = newDefender;
        attackers = newAttacker;
    }

    private void printWinnerInfo() {
        String winners = "";
        int k = 0;
        System.out.println("WINNER:");
        for (Droid i : attackers)
            if (i.getHealth() > 0)
                winners += checkCount(++k) + ": " + i + "\n";
        System.out.println(winners);
    }

    private void printSeparator() {
        System.out.println("--------------------------------------------");
    }

    private String checkCount(int k) {          // Check the number of droids
        int count = 0;
        for (int i = 1; i <= defenders.length; i *= 10)
            count++;
        return String.format("%" + count + "d", k);
    }

    @Override
    public String toString() {
        String def = "";
        String att = "";
        int k = 0;

        for (Droid i : defenders)
            if (i.getHealth() > 0)
                def += "\n" + checkCount(++k) + ": " + i;
        for (Droid i : defenders)
            if (i.getHealth() == 0)
                def += "\n" + i.getName() + " is dead";

        k = 0;
        for (Droid i : attackers)
            if (i.getHealth() > 0)
                att += "\n" + checkCount(++k) + ": " + i;
        for (Droid i : attackers)
            if (i.getHealth() == 0)
                att += "\n" + i.getName() + " is dead";

        return "Side 1: " + def + "\nSide 2: " + att;
    }
}
