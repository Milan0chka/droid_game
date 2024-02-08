package com.game.model;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Droid {
    private int health;
    private int maxHealth;
    private int damage;
    private String name;
    private static Random random = new Random();

    Droid() {
        this.maxHealth = this.health = random.nextInt(30) + 20;   // Health from 20 to 50
        this.damage = (int)((90 - this.health) * Math.random()); // Damage points are distributed randomly

        String[] nameStart = {"Blaster", "Strongman", "Titan", "Destroyer", "Giant", "Tiny", "Muscle"};
        String[] nameEnd = {"-ello", "-killer", "-terminator", "-avenger", "-cutie", "-chihuahua", "-beauty"};
        this.name = nameStart[random.nextInt(7)] + nameEnd[random.nextInt(7)] + " " + (random.nextInt(9) + 1) * 1000;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int toHit(Droid obj) throws InterruptedException {
        System.out.println("Droid " + obj.getName() + " attacks " + this.getName() + " and ");
        Thread.sleep(1500);
        int hit = (int)(obj.getDamage() * (0.2 + random.nextDouble(0.8)));
        int temp = this.getHealth() - hit;

        if (temp > 0) {
            System.out.println("... the health of the second now equals " + temp + "!");
            this.setHealth(temp);
            return 1;
        }
        this.setHealth(0);
        System.out.println("... the second droid is killed!");
        return 0;
    }

    public int toCure() throws InterruptedException {
        Thread.sleep(1500);
        if (this.getHealth() == this.getMaxHealth()) {
            System.out.println("Droid " + this.getName() + " is already at full health! There's nothing to heal!");
        } else {
            int temp = (1 + random.nextInt(7)) + this.getHealth();
            if (temp >= this.getMaxHealth()) {
                this.setHealth(this.getMaxHealth());
                System.out.println("Droid " + this.getName() + " is fully healed! Just like new!");
            } else {
                this.setHealth(temp);
                System.out.println("Droid " + this.getName() + " significantly healed up to " + this.getHealth() + " HP!");
            }
        }

        return 1;
    }
}
