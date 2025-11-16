abstract public class TimeOfDay extends Puzzle{
    private int currentHour;
    private int currentDay;


    public TimeOfDay(String puzzleID, String puzzleName, String roomID,
                     String puzzleDescription, int puzzleAttempts, String rewardID,int currentHour, int currentDay) {
        super(puzzleID,puzzleName,roomID,puzzleDescription,puzzleAttempts,rewardID);
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

    public int getCurrentHour() {
        return currentHour;
    }

    public void setCurrentHour(int currentHour) {
        this.currentHour = currentHour;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

//    void DoPuzzle(){}
//    int PuzzleClock=12;
//    if(getCurrentHour()<=12){
//        System.out.println("You are at a sunDial, there are 12 marks that resemable a clock with a long structure in the middle that make shadows of the hour hand.");
//        while(true){
//
//        }
//    }

}