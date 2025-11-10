import java.util.ArrayList;

public class ItemInventory extends Inventory {
   private  ArrayList<Item> inventory;

    public ItemInventory(ArrayList<Item> inventory) {
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
