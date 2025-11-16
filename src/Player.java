import java.util.Random;
import java.util.Scanner;

public class Player extends Character {
    int defense;
    int evasion;
    int hunger;
    int thrist;
    boolean DayorNight;
    private int hp;
    Weapon equippedWeapon;
    Armor equippedArmor;

    public Player(int HP, int attackDMG,int defense,int evasion,int hunger,int thrist){
        super(HP,attackDMG);
        this.HP=25;
        this.attackDMG=10;
        this.defense=defense;
        this.evasion=evasion;
        this.hunger=hunger;
        this.thrist=thrist;
        this.DayorNight=true;
        this.equippedWeapon=null;
        this.equippedArmor=null;

    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getThrist() {
        return thrist;
    }

    public void setThrist(int thrist) {
        this.thrist = thrist;
    }

    public boolean isDayorNight() {
        return DayorNight;
    }

    public void setDayorNight(boolean dayorNight) {
        DayorNight = dayorNight;
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

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(Armor equippedArmor) {
        this.equippedArmor = equippedArmor;
    }

    //for user input STATS
    public void showStats() {
        System.out.println("Your current stats are:");
        System.out.println("Health: " + hp + "/100");
        System.out.println("Attack Damage: " + this.getAttackDMG());
        if (equippedWeapon != null) {
            //System.out.println("Current weapon: " + this.getEquippedWeapon());
        }
    }

}