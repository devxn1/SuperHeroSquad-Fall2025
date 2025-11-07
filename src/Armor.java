public class Armor  extends Item{
    int defense;
    int evasion;
    int HP;

    public  Armor(int id, String name, String description, int location, int defense, int evasion, int HP) {
        super(id,name,description,location);
        this.defense = defense;
        this.evasion = evasion;
        this.HP = HP;

    }

    void addArmor(){

    }
    void removeArmor(){

    }

}
