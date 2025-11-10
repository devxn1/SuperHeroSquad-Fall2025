public class InvalidRoomException extends Exception {
    public InvalidRoomException() {
        super("You can not go that way.");
    }

    public InvalidRoomException(String message) {
        super(message);
    }
}