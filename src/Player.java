import java.util.Random;
import java.util.Scanner;

public class Player extends Character {
    int defense;
    int evasion;
    int hunger;
    int thrist;
    boolean DayorNight;
    private int hp;

    public Player(int HP, int attackDMG,int defense,int evasion,int hunger,int thrist){
        super(HP,attackDMG);
        this.defense=defense;
        this.evasion=evasion;
        this.hunger=hunger;
        this.thrist=thrist;
        this.DayorNight=true;
    }
    void displayStats() {
        System.out.println("HP: " + getHP());
        System.out.println("AtkDamage: "+getAttackDMG());
        System.out.println("Defense: " + getDefense());
        System.out.println("Evasion: " + getEvasion());
        System.out.println("Thrist: " + getThrist());
        System.out.println("Hunger: " + getHunger());
    }

    public void showHelp() {
        System.out.println("Commands: North/n, South/s, East/e, West/w,");
        System.out.println("Look/Inspect, Take/Grab, Gather, Craft,");
        System.out.println("Build, Use, Map/m, Journal/j,");
        System.out.println("Inventory/i, Sleep, Save/Load, Help/?");
    }


    void savePlayer() {

    }

    void loadPlayer() {

    }

    void sleep() {

    }

    void avoid() {

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(100, hp));
    }

    //for user input STATS
    public void showStats() {
        System.out.println("Your current stats are:");
        System.out.println("Health: " + hp + "/100");
        System.out.println("Attack Damage: " + getAttackDamage());
        if (equippedWeapon != null) {
            System.out.println("Current weapon: " + equippedWeapon.getName());
        }
    }

    //for user input HELP
    public void showHelp() {
        System.out.println("List of Commands:");
        System.out.println("Commands: North/n, South/s, East/e, West/w,");
        System.out.println("Look/Inspect, Take/Grab, Gather,");
        System.out.println("Craft, Build, Use, Map/m, Journal/j,");
        System.out.println("Inventory/i, Sleep, Save/Load, Help/?");
    }
}