//import java.util.Map;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
import java.util.Scanner;

public class Puzzle {
    private int roomId;
    private String description;
    private String solution;
    private boolean solved;
    private int maxAttempts;
    private int remainingAttempts;

    public Puzzle(int roomId, String description, String solution, int attempts) {
        this.roomId = roomId;
        this.description = description;
        this.solution = solution.trim().toUpperCase();
        this.solved = false;
        this.maxAttempts = Math.max(0, attempts);
        this.remainingAttempts = this.maxAttempts;
    }

    /*
    This was for hashmaps, the logic should still be the same but I have no idea how to relate it to
    a database.
     */
//    public static void loadPuzzles(String filename, Map<Integer, Room> rooms) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split("~");
//                if (parts.length < 4) continue;
//
//                int roomNumber = Integer.parseInt(parts[0].trim());
//                String description = parts[1].trim();
//                String solution = parts[2].trim();
//                int attempts = Integer.parseInt(parts[3].trim());
//
//                Room room = rooms.get(roomNumber);
//                if (room == null) continue;
//
//                Puzzle puzzle = new Puzzle(roomNumber, description, solution, attempts);
//                //setPuzzle will be a simple getter/setter in rooms
//                room.setPuzzle(puzzle);
//            }
//        } catch (IOException e) {
//            System.out.println("Puzzle loading error: " + e.getMessage());
//        }
//    }

    public int getRoomId() {
        return roomId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSolved() {
        return solved;
    }

    public void resetAttempts() {
        this.remainingAttempts = this.maxAttempts;
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    public boolean attempt(String answer) {
        if (solved) {
            System.out.println("You have already solved this puzzle.");
            return true;
        }

        if (answer.trim().equalsIgnoreCase(solution)) {
            solved = true;
            System.out.println("Correct!");
            return true;
        } else {
            if (maxAttempts > 0) {
                remainingAttempts = Math.max(0, remainingAttempts - 1);
                if (remainingAttempts > 0) {
                    System.out.println("Wrong answer, you still have " + remainingAttempts + " attempt(s). Try again.");
                } else {
                    System.out.println("Try again!");
                }
            } else {
                System.out.println("Wrong answer. Try again.");
            }
            return false;
        }
    }

    public void solvingPuzzle(Scanner scanner) {
        if (isSolved()) return;

        resetAttempts();
        System.out.println("\nPuzzle: " + getDescription());

        while (!isSolved()) {
            System.out.print("What is your answer? (Or Ignore?)\n");
            String answer = scanner.nextLine().trim().toUpperCase();

            if (answer.equals("IGNORE")) {
                System.out.println("You decide the puzzle's not worth your time.");
                break;
            }

            boolean solved = attempt(answer);
            if (solved) break;

            if (getRemainingAttempts() == 0) {
                System.out.println("Wrong answer.");
                resetAttempts();
            }
        }
    }

