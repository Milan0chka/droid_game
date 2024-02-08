package com.game;

import com.game.model.Armored;
import com.game.model.Doctor;
import com.game.model.Droid;
import com.game.model.Wild;
import com.game.сontoller.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int menu(){
        System.out.print("\n\t");
        for(int i=0; i < 20; i++)
            System.out.print("*");
        System.out.print("\n\t*\tDROID BATTLE\t*");
        System.out.print("\n\t");
        for(int i=0; i < 20; i++)
            System.out.print("*");
        System.out.println("\nMENU :");
        System.out.println("1.Create droids");
        System.out.println("2.List of available droids");
        System.out.println("3.Fight 1 on 1");
        System.out.println("4.Battle-arena");
        System.out.println("5.Finish work");
        System.out.println("Enter the number of your choice =>");
        Scanner menuChoice = new Scanner(System.in);

        return menuChoice.nextInt();
    }

    public static Droid createDroid(int N){
        Droid created = null;
        System.out.println("DROID CLASS :");
        System.out.println("1.Warrior");
        System.out.println("2.Medic");
        System.out.println("3.Wild");
        System.out.println("Enter the number of your choice =>");
        Scanner droidChoice = new Scanner(System.in);
        switch(droidChoice.nextInt()){
            case 1:
                created = new Armored();
                break;
            case 2:
                created = new Doctor();
                break;
            case 3:
                created = new Wild();
                break;
            default:
                System.exit(0);
        }
        System.out.println("Droid №"+N+" created!");
        System.out.println(created);
        return created;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Droid> arr= new ArrayList<Droid>();
        Scanner In = new Scanner(System.in);
        int count =0;
        while (true){
            switch (menu()){
                case 1:
                    arr.add(count, createDroid(count+1));
                    count++;
                    break;
                case 2:
                    System.out.println("\nCreated droids:");
                    for(int i=0; i < count; i++){
                        System.out.println("\nDroid № " + (i+1) + " : ");
                        System.out.println(arr.get(i));
                    }
                    break;
                case 3:
                    if(arr.size() < 1){
                        System.out.println("You do not have enough droids to start the game! Create a new droid in the menu.");
                        break;
                    }

                    System.out.println("Enter the number of the first droid  - ");
                    int numberFirstDroid = In.nextInt() -1;

                    System.out.println("Enter the number of the second droid  - ");
                    int numberSecondDroid = In.nextInt() -1;
                    if(numberSecondDroid == numberFirstDroid){
                        System.out.println("This droid has already been selected for the game.");
                        break;
                    }
                    arena oneVsOneArena = new arena(arr.get(numberFirstDroid), arr.get(numberSecondDroid));
                    oneVsOneArena.fight();
                    break;
                case 4:
                    if(arr.size() < 1){
                        System.out.println("You do not have enough droids to start the game! Create a new droid in the menu.");
                        break;
                    }

                    System.out.println("Enter the team size  - ");
                    int size = In.nextInt();

                    Droid[] attackers = new Droid[size];
                    Droid[] defenders = new Droid[size];

                    for(int i=0; i < size; i++){
                        System.out.println("Enter number " + (i+1) + " droid of team 1  - ");
                        attackers[i] = arr.get(In.nextInt() -1);

                        System.out.println("Enter number " + (i+1) + " droid of team 2  - ");
                        defenders[i] = arr.get(In.nextInt() -1);
                    }
                    arena battle = new arena(attackers, defenders);
                    battle.fight();
                    break;

                case 5:
                    System.out.println("\nThank you for playing!");
                    System.exit(0);
            }
        }
    }
}
