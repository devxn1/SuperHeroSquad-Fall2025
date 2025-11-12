import java.util.ArrayList;
import java.util.List;

public class ItemInventory extends Inventory {
   private  List<Item> inventory = new ArrayList<>();

    public ItemInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    void addItem(Item item){
        if(item!=null){
            inventory.add(item);
        }
        else{
            System.out.println("Invalid Item");
        }

    }
    void removeItem(Item item){
            if(inventory == null){
                System.out.println("No items to remove");
            }
            inventory.remove(item);
    }
}