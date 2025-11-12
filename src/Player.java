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

    }
    void savePlayer(){

    }
    void loadPlayer(){

    }
    void sleep(){

    }
    void avoid(){

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(100, hp));
    }
}