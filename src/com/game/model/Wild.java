package com.game.model;
import java.util.Random;

public class Wild extends Droid {

    private float wildness;
    private static Random random = new Random();

    public float getWildness() {
        return wildness;
    }

    public void setWildness(float wildness) {
        this.wildness = wildness;
    }

    public Wild() {
        super();
        this.wildness = random.nextFloat(); // Generates a wildness level between 0.0 and 1.0
    }

    public int toHit(Droid obj) throws InterruptedException {
        System.out.println("Droid " + obj.getName() + " attacks " + this.getName() + " and ");
        Thread.sleep(1500);
        int hit = (int)(obj.getDamage() * (0.2 + random.nextDouble(0.8)));
        int temp = this.getHealth() - hit;

        if (temp > 0) {
            System.out.println("... the health of the second now equals " + temp + "!");
            this.setHealth(temp);
            System.out.println("Wild " + this.getName() + " furiously retaliates!");
            obj.setHealth(obj.getHealth() - (int)(hit * this.getWildness()));
            if (obj.getHealth() <= 0) {
                System.out.println("Droid " + obj.getName() + " dies!");
            }
            return 1;
        } else {
            this.setHealth(0);
            System.out.println("... the second droid is killed!");
            return 0;
        }
    }

    @Override
    public String toString() {
        return ("Droid " + this.getName() + " :\nClan - Wild\nHealth - " + this.getHealth() + "\nPower - " + this.getDamage() + "\nWildness - " + (int)(this.getWildness() * 100) + "%");
    }
}
