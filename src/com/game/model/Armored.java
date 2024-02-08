package com.game.model;

import java.util.Random;

public class Armored extends Droid {
    private int armor;
    private static Random random = new Random();

    public Armored(){
        super();
        this.armor = random.nextInt(50); // Assign random armor value between 0 and 49
    }

    @Override
    public int toHit(Droid obj) {
        System.out.println("Droid "+ obj.getName() + " attacks " + this.getName() + " and ");
        if(armor > 0){
            this.armor -= (int)(obj.getDamage() * (0.4 + random.nextDouble(0.6)));
            if(this.armor < 0){
                System.out.println("the armor of the second is destroyed!");
                this.setArmor(0); // Set armor to 0 if it becomes negative
                return 1;
            }
            System.out.println("the armor of the second is now equal to " +  this.armor + "!");
            return 1;
        } else{
            int temp = this.getHealth()-(int)(obj.getDamage() * (0.5 + random.nextDouble(0.5)));
            if(temp > 0){
                System.out.println("the health of the second is now equal to "+  temp + "!");
                this.setHealth(temp);
                return 1;
            }
            this.setHealth(0);
            System.out.println("the second droid is killed!");
            return 0;
        }
    }

    @Override
    public int toCure(){
        if(armor > 0){
            System.out.println("Droid "+ this.getName() + " tried to heal, but heavy armor prevents it! ");
            return 1;
        }
        if(random.nextBoolean()){
            int temp = this.getHealth() + random.nextInt(5)+1;
            if(temp >= this.getMaxHealth()){
                System.out.println("Droid "+ this.getName() + " has healed!");
                this.setHealth(this.getMaxHealth());
                return 1;
            } else{
                System.out.println("Droid "+ this.getName() + " tried to heal!");
                this.setHealth(temp);
                return 1;
            }
        }else {
            int temp = this.getHealth() - random.nextInt(5)-1;
            if(temp >= 0){
                System.out.println("Droid "+this.getName()+" injured itself attempting to heal!");
                this.setHealth(temp);
                return 1;
            } else{
                System.out.println("Droid "+this.getName()+" injured itself attempting to heal and died :(");
                this.setHealth(0);
                return 0;
            }
        }
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public String toString(){
        return ("Droid "+this.getName()+ " :\nClan - Warrior\nHealth - "+this.getHealth() + "\nPower - " + this.getDamage() + "\nArmor - " + this.getArmor());
    }
}
