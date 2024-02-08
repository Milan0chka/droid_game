package com.game.model;
import java.util.Random;

public class Doctor extends Droid {

    private int healthKits;
    private static Random random = new Random();

    public int getHealthKits() {
        return healthKits;
    }

    public void setHealthKits(int healthKits) {
        this.healthKits = healthKits;
    }

    public Doctor(){
        super();
        this.healthKits = random.nextInt(3); // Assign random number of health kits between 0 and 2
    }

    public int toCure() throws InterruptedException {
        float boost = 1;
        if(healthKits > 0){
            boost += random.nextFloat() * 3; // Boost healing effectiveness randomly between 1 to 3 times
            System.out.println("Droid " + this.getName() + " pulls out a medkit and heals more effectively by a factor of " + boost  + "!");
            healthKits--;
        }
        Thread.sleep(1500);
        if(this.getHealth() == this.getMaxHealth()){
            System.out.println("Droid " + this.getName() + " is already at full health! Nothing to heal!");
        } else {
            int temp = (int)(3 + random.nextInt(10) * boost) + this.getHealth();
            if(temp >= this.getMaxHealth()){
                this.setHealth(this.getMaxHealth());
                System.out.println("Droid " + this.getName() + " is fully healed! Just like new!");
            } else {
                this.setHealth(temp);
                System.out.println("Droid " + this.getName() + " significantly healed up to "+ this.getHealth() + " HP!");
            }
        }

        return 1;
    }

    @Override
    public String toString(){
        return ("Droid "+this.getName()+ " :\nClan - Medic\nHealth - "+this.getHealth() + "\nPower - " + this.getDamage() + "\nMedkits - " + this.getHealthKits());
    }
}
