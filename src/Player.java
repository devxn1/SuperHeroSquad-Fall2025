import java.util.Random;
import java.util.Scanner;

public class Player extends Character {
    private int defense;
    private int evasion;
    private int hunger;
    private int thrist;
    private boolean DayorNight;
    //private int currentRoom;
    ItemInventory playerInventory;
    ArtifactInventory artifactInventory;
    Armor Parmor;
    Weapon Pweapon;

    public Player(int HP, int attackDMG, int defense, int evasion, int hunger, int thrist) {
        super(HP, attackDMG);
        this.HP=25;
        this.attackDMG=1;
        this.defense = 10;
        this.evasion = 2;
        this.hunger = 100;
        this.thrist = 100;
        this.DayorNight = true;
        //this.currentRoom = 1;
        this.playerInventory = null;
        this.artifactInventory = null;
        this.Parmor=null;
        this.Pweapon=null;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isDayorNight() {
        return DayorNight;
    }

    public void setDayorNight(boolean dayorNight) {
        DayorNight = dayorNight;
    }

    public int getThrist() {
        return thrist;
    }

    public void setThrist(int thrist) {
        this.thrist = thrist;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public ItemInventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(ItemInventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    public Armor getParmor() {
        return Parmor;
    }

    public void setParmor(Armor parmor) {
        Parmor = parmor;
    }

    public Weapon getPweapon() {
        return Pweapon;
    }

    public void setPweapon(Weapon pweapon) {
        Pweapon = pweapon;
    }

    public ArtifactInventory getArtifactInventory() {
        return artifactInventory;
    }

    public void setArtifactInventory(ArtifactInventory artifactInventory) {
        this.artifactInventory = artifactInventory;
    }

    void displayStats() {
        System.out.println("HP: " + getHP());
        System.out.println("AtkDamage: "+getAttackDMG());
        System.out.println("Defense: " + getDefense());
        System.out.println("Evasion: " + getEvasion());
        System.out.println("Thrist: " + getThrist());
        System.out.println("Hunger: " + getHunger());

    }

    void savePlayer() {

    }

    void loadPlayer() {

    }

    void sleep() {

    }

    void avoid() {

    }

    void equip(Item item) {

    }

    void unequip(){

    }

    //For Hashmaps, Key is id(integer),
    void pickup(Room Playerroom) {

    }

    void drop(Room Playerroom) {

    }

    void Combat(Monster tempMonster){
        System.out.println("Your HP" +getHP());
        System.out.println(tempMonster.displayerMonster());
        //System.out.println("Monster HP: " + tempMonster.getHP());
        int MonsterHP= tempMonster.getHP();
        Scanner UserInput=new Scanner(System.in);
        while(true) {
            System.out.println("Monster HP: " + MonsterHP);
            System.out.println("Commands:");
            String Command = UserInput.nextLine();
            if (Command.equalsIgnoreCase("Attack")) {
                MonsterHP -= this.getAttackDMG();
                setHP(this.getHP() - tempMonster.getAttackDMG());
                if(this.getHP()<=0){
                    break;
                }
            }
            else if(Command.equalsIgnoreCase("Stats")){
                displayStats();
            }
            else if(Command.equalsIgnoreCase("Run")){
                double randomRun=new Random().nextDouble(0,1);
                double playerRun=new Random().nextDouble(0,1);
                if(randomRun<playerRun){
                    break;
                }
            }
            else if(Command.equalsIgnoreCase("Inventory")){
                //Show inventory method here
                inventory();
            }
            else if(Command.equalsIgnoreCase("Equip")){
                //Put method to equip Items here
            }
            else if(Command.equalsIgnoreCase("Unequip")){
                //Put method to Unequip Items here
            }
            else if(Command.equalsIgnoreCase("Use")){
                //Put Method to use Items here
                //Healing or Combat (Strength)
            }
        }

    }

    void inventory() {
       System.out.println(playerInventory.getInventory());
    }
}
