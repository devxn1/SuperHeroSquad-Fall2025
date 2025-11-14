import java.util.ArrayList;
import java.util.List;

public class ItemInventory extends Inventory {

    private List<Item> inventory = new ArrayList<>();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public ItemInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void addItem(Item item) {

        if(item!=null || inventory.size()<=10) {
            inventory.add(item);
        }
        else{
            System.out.println("Invalid Item");
        }

    }

    public void removeItem(Item item){
            if(inventory == null){
                System.out.println("No items to remove");
            }
            inventory.remove(item);
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("You didn't pickup any items yet");
        } else {
            System.out.println("\t Inventory");
            for (Item items : inventory) {
                System.out.print(ANSI_CYAN + " [" + items.getName() + "]" + ANSI_RESET);
            }
            System.out.println();
        }
    }



    private Item findItemFromInventory(String itemName) {
        for (Item items : inventory) {
            if (items.getName().equalsIgnoreCase(itemName)) {
                return items;
            }
        }
        return null;
    }





}
