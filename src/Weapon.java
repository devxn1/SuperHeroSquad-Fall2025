public class Weapon extends Item {
    private int damage;
    private int DOT;

    public Weapon(int id, String name, String description, int roomID, int damage, int DOT){
        super(id, name, description, roomID);
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


    public int getDOT(){
        return DOT;
    }

    public String getType() {
        return "Weapon";
    }

    public void use(Object player){
        if (player == null){
            System.out.println("?");
        }
    }



//    @Override
//    public String toString(){
//
//    }
}