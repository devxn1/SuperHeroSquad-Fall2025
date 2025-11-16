public abstract class Consumable extends Item{
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

    public void use(Object player){
        if (player == null) {
            return;
        }
            /*
            //cap at 100 health, change 100 to change max start hp
            int newHp = Math.min(player.getHP() + healing, 100);
            player.setHp(newHp);
            System.out.println(player.getName() + " healed for " + healing + " HP. Current HP: " + player.getHP());
             */
    }
}