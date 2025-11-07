public class Player extends Character {
    int defense;
    int evasion;
    int hunger;
    int thrist;
    boolean DayorNight;

    public Player(int HP, int attackDMG,int defense,int evasion,int hunger,int thrist){
        super(HP,attackDMG);
        this.defense=defense;
        this.evasion=evasion;
        this.hunger=hunger;
        this.thrist=thrist;
        this.DayorNight=true;
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
}
