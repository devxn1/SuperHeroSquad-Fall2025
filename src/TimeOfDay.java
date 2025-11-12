public class TimeOfDay extends Puzzle{
    private int currentHour;
    private int currentDay;

    public TimeOfDay(int currentHour, int currentDay) {
        this.currentHour = currentHour;
        this.currentDay = currentDay;
    }

    int hour = 1;
    //move will likely be added in player class, might be subject to name change.
    public void updateTime(String move) {
        if (move.equals("NORTH") || move.equals("SOUTH") || move.equals("EAST") || move.equals("WEST")) {
            currentHour = currentHour + 1;

            if (hour > 24) {
                hour = 1;
                currentDay = currentDay + 1;
            }

            if (hour <= 12) {
                System.out.println("\nIt's day, the current hour is " + currentHour);
            } else {
                System.out.println("\nIt's night, the current hour is " + currentHour);
            }
        }
    }
}