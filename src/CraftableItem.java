import java.util.*;


public class CraftableItem  extends Weapon implements Craftable {
    private ArrayList<Material> materials;
    public CraftableItem(String id, String name, String description, List<String> roomID, int damage, int DOT, ArrayList<Material> materials) {
        super(id, name, description, roomID, damage, DOT);
        this.materials = materials;
    }

    @Override
    public void craft() {

    }
    @Override
    public void GetMaterials() {

    }
}
