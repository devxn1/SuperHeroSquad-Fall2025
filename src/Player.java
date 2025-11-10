public class Player extends Character {
    private int defense;
    private int evasion;
    private int hunger;
    private int thrist;
    private boolean DayorNight;
    ItemInventory playerInventory;

    public Player(int HP, int attackDMG,int defense,int evasion,int hunger,int thrist){
        super(HP,attackDMG);
        this.defense=defense;
        this.evasion=evasion;
        this.hunger=hunger;
        this.thrist=thrist;
        this.DayorNight=true;
        this.playerInventory=null;
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

    void displayStats() {

    }
    void savePlayer(){

    }
    void loadPlayer(){

    }
    void sleep(){

    }
    void avoid(){

    }

    //For Hashmaps, Key is id(integer),
    void pickup(Room Playerroom) {

    }

    void drop(Room Playerroom){


    }
}
