public class Consumable extends Item{
    private int healing;

    public Consumable(int id, String name, String description, int roomID, int healing){
        super(id, name, description, roomID);
        this.healing = healing;
    }

    public int getHealing(){
        return healing;
    }

    public String getType() {
        return "Consumable";
    }

//    @Override
//    public String toString() {
//
//    }

    public void use(Object player){
        if (player == null) {
            System.out.println("?");
        }
    }
}