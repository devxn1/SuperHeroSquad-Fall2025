public class Weapon extends Item{
    int damage;
    int DOT;

    public Weapon(int id, String name, String description, int location, int damage, int DOT) {
        super(id,name,description,location);
        this.damage = damage;
        this.DOT = DOT;
    }

    void addWeapon(){


    }

    void removeWeapon(){

    }


}
