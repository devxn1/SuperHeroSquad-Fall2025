public class Consumable extends Item{
    int healing;

    public Consumable(int id, String name, String description, int location,int healing){
        super(id,name,description,location);
        this.healing = healing;
    }

    void heal(){

    }
}