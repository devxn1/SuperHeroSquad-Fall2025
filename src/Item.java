
public  class Item {
    private int id;
    private String name;
    private String description;
    private int roomID;

    public Item(int id, String name, String description, int roomID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.roomID = roomID;
    }

    public int getID(){
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


    public String toString(){
        return name + (" - ") + description;
    }
}