import java.util.ArrayList;
import java.util.List;

class Recipe extends Item {
    int NumberOfMaterials;
    ArrayList<String> requiredMaterials;
    public Recipe(String id, String name, String description, List<String> location, ArrayList<String> requiredMaterials) {
        super(id, name, description, location);
;
        this.requiredMaterials = requiredMaterials;
    }


    void CheckRecipe(){
    }
}
