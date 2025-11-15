public class Weapon extends Item{
    private int damage;
    private int DOT;

    public Weapon(int id, String name, String description, int location, int damage, int DOT) {
        super(id,name,description,location);
        this.damage = damage;
        this.DOT = DOT;
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    void addWeapon(){


    }

    void removeWeapon(){

    }


}
