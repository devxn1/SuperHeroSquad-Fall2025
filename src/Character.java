public class Character {

    int HP;
    int attackDMG;

    public Character(int HP, int attackDMG){
        this.HP = 100;
        this.attackDMG=attackDMG;
    }
    public int getHP() {
        return HP;
    }
    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttackDMG() {
        return attackDMG;
    }
    public void setAttackDMG(int attackDMG){
        this.attackDMG=attackDMG;
    }
}