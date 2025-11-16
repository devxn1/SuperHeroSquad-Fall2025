
public abstract class Item {
    private String id;
    private String name;
    private String description;
    private int roomID;

    public Item(String id, String name, String description, int roomID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.roomID = roomID;
    }

    public String getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getRoomID(){
        return roomID;
    }

    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    //abstract use for different items
    public abstract void use(Object player);

    public abstract String getType();

    @Override
    public String toString(){
        return name + (" - ") + description;
    }
}