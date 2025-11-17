public class TimeOfDay extends Puzzle {

    private int currentHour = 1;
    private int currentDay = 1;

    public TimeOfDay() {
        super("TIME", "TimeOfDay", "NONE", "Tracks world time", 0, "none", "none");
    }

    //updateTime needs to be added into player class
    public void updateTime(String move) {
        if (move.equalsIgnoreCase("NORTH") ||
                move.equalsIgnoreCase("SOUTH") ||
                move.equalsIgnoreCase("EAST") ||
                move.equalsIgnoreCase("WEST")) {

            currentHour++;

            if (currentHour > 24) {
                currentHour = 1;
                currentDay++;
            }

            if (currentHour >= 6 && currentHour < 18) {
                System.out.println("It's day. The current hour is " + currentHour);
            } else {
                System.out.println("It's night. The current hour is " + currentHour);
            }
        }
    }

    public int getCurrentHour() {
        return currentHour;
    }

    public void setCurrentHour(int hour) {
        this.currentHour = hour;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int day) {
        this.currentDay = day;
    }
}
