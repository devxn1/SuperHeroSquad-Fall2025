public class Material extends Item  {

    public Material(String id, String name, String description, int location) {
        super(id,name,description,location);

    }

    @Override
    public void use(Object player) {

    }

    @Override
    public String getType() {
        return "";
    }

    boolean isCraftable(){
        return true;
    }
}
