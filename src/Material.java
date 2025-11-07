public class Material extends Item  {




    public Material(int id, String name, String description, int location) {
        super(id,name,description,location);

    }

    boolean isCraftable(){
        return true;
    }
}
