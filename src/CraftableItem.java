import java.util.*;


public class CraftableItem  extends Item Craftable {
    private ArrayList<Material> materials;
    public CraftableItem(int id, String name, String description, int roomID) {
        super(id, name, description, roomID);
    }


    @Override
    public void craft() {

    }
    @Override
    public void GetMaterials() {

    }
}
