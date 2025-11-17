//import java.util.*;
//
///*
// * Puzzle Mechanics:
// * - 4 positions in a circle (North, East, South, West)
// * - 4 weights (Heavy, Medium, Light, Feather)
// * - Wind blows strongest from the North (15 units)
// * - Player must place lightest weights where wind is strongest
// * - Solution: North=Feather, East=Light, South=Medium, West=Heavy
// */
//public class WindShrine extends Puzzle {
//
//    // Wind strength from each direction (North is strongest)
//    private static final int WIND_NORTH = 15;
//    private static final int WIND_EAST = 10;
//    private static final int WIND_SOUTH = 7;
//    private static final int WIND_WEST = 3;
//
//    // Weight values
//    private static final String HEAVY = "heavy";
//    private static final String MEDIUM = "medium";
//    private static final String LIGHT = "light";
//    private static final String FEATHER = "feather";
//
//    // Current arrangement (position -> weight)
//    private Map<String, String> currentArrangement;
//
//    // Correct solution
//    private Map<String, String> correctSolution;
//
//    /**
//     * Constructor - calls parent constructor and initializes puzzle state
//     */
//    public WindShrine(String puzzleID, String puzzleName, String roomID,
//                      String puzzleDescription, int puzzleAttempts,
//                      String rewardType, String rewardID) {
//        super(puzzleID, puzzleName, roomID, puzzleDescription,
//                puzzleAttempts, rewardType, rewardID);
//
//        currentArrangement = new HashMap<>();
//        correctSolution = new HashMap<>();
//
//        // Set up the correct solution
//        // Lightest where wind is strongest
//        correctSolution.put("north", FEATHER);  // Strongest wind
//        correctSolution.put("east", LIGHT);
//        correctSolution.put("south", MEDIUM);
//        correctSolution.put("west", HEAVY);     // Weakest wind
//    }
//
//    /**
//     * Attempt to solve the puzzle with player input
//     *
//     * Expected format: "north=feather,east=light,south=medium,west=heavy"
//     * Also accepts: "feather north, light east, medium south, heavy west"
//     *
//     * @param playerInput The player's arrangement
//     * @return true if correct, false otherwise
//     */
//    @Override
//    public boolean attemptSolve(String playerInput) {
//        if (playerInput == null || playerInput.trim().isEmpty()) {
//            System.out.println("Please provide an arrangement.");
//            System.out.println("Format: north=<weight>,east=<weight>,south=<weight>,west=<weight>");
//            System.out.println("Weights: heavy, medium, light, feather");
//            return false;
//        }
//
//        // Parse the player's input
//        Map<String, String> playerArrangement = parseInput(playerInput);
//
//        if (playerArrangement == null) {
//            System.out.println("Invalid format. Try:");
//            System.out.println("  north=feather,east=light,south=medium,west=heavy");
//            System.out.println("Or:");
//            System.out.println("  feather north, light east, medium south, heavy west");
//            return false;
//        }
//
//        // Check if all positions are filled
//        if (playerArrangement.size() != 4) {
//            System.out.println("You must place all 4 weights (heavy, medium, light, feather)");
//            System.out.println("In all 4 positions (north, east, south, west)");
//            return false;
//        }
//
//        // Check for duplicate weights
//        Set<String> usedWeights = new HashSet<>(playerArrangement.values());
//        if (usedWeights.size() != 4) {
//            System.out.println("Each weight can only be used once!");
//            return false;
//        }
//
//        // Store the current arrangement
//        currentArrangement = new HashMap<>(playerArrangement);
//
//        // Check if it matches the solution
//        if (checkSolution(playerArrangement)) {
//            System.out.println("\n✓ The wind flows perfectly through the shrine!");
//            System.out.println("The stones glow with acceptance.");
//            return true;
//        } else {
//            System.out.println("\n✗ The wind howls in protest.");
//            System.out.println("The arrangement blocks the natural flow.");
//
//            // Give feedback about wind flow
//            giveFeedback(playerArrangement);
//            return false;
//        }
//    }
//
//    /**
//     * Parse player input into a position->weight mapping
//     */
//    private Map<String, String> parseInput(String input) {
//        Map<String, String> arrangement = new HashMap<>();
//        String normalized = input.toLowerCase().trim();
//
//        try {
//            // Format 1: "north=feather,east=light,south=medium,west=heavy"
//            if (normalized.contains("=")) {
//                String[] pairs = normalized.split(",");
//                for (String pair : pairs) {
//                    String[] parts = pair.trim().split("=");
//                    if (parts.length == 2) {
//                        String position = parts[0].trim();
//                        String weight = parts[1].trim();
//
//                        if (isValidPosition(position) && isValidWeight(weight)) {
//                            arrangement.put(position, weight);
//                        }
//                    }
//                }
//            }
//            // Format 2: "feather north, light east, medium south, heavy west"
//            else if (normalized.contains(" ")) {
//                String[] parts = normalized.split(",");
//                for (String part : parts) {
//                    String[] tokens = part.trim().split("\\s+");
//                    if (tokens.length == 2) {
//                        String weight = tokens[0].trim();
//                        String position = tokens[1].trim();
//
//                        if (isValidPosition(position) && isValidWeight(weight)) {
//                            arrangement.put(position, weight);
//                        }
//                    }
//                }
//            }
//
//            return arrangement.isEmpty() ? null : arrangement;
//
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    /**
//     * Check if the arrangement matches the solution
//     */
//    private boolean checkSolution(Map<String, String> arrangement) {
//        for (String position : correctSolution.keySet()) {
//            String correctWeight = correctSolution.get(position);
//            String playerWeight = arrangement.get(position);
//
//            if (!correctWeight.equals(playerWeight)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * Give helpful feedback about the current arrangement
//     */
//    private void giveFeedback(Map<String, String> arrangement) {
//        int correctCount = 0;
//
//        for (String position : correctSolution.keySet()) {
//            if (correctSolution.get(position).equals(arrangement.get(position))) {
//                correctCount++;
//            }
//        }
//
//        if (correctCount > 0) {
//            System.out.println("Hint: " + correctCount + " weight(s) are in the correct position.");
//        }
//
//        System.out.println("\nRemember the hints:");
//        System.out.println("- Lightest weights where wind is strongest");
//        System.out.println("- Heaviest weights where wind is weakest");
//    }
//
//    /**
//     * Check if position is valid
//     */
//    private boolean isValidPosition(String position) {
//        return position.equals("north") || position.equals("east") ||
//                position.equals("south") || position.equals("west") ||
//                position.equals("n") || position.equals("e") ||
//                position.equals("s") || position.equals("w");
//    }
//
//    /**
//     * Check if weight is valid
//     */
//    private boolean isValidWeight(String weight) {
//        return weight.equals(HEAVY) || weight.equals(MEDIUM) ||
//                weight.equals(LIGHT) || weight.equals(FEATHER);
//    }
//
//    /**
//     * Display current state of the puzzle
//     */
//    public void displayState() {
//        System.out.println("\n=== Wind Shrine ===");
//        System.out.println("Wind Strength:");
//        System.out.println("  North: ████████████████ (Strongest)");
//        System.out.println("  East:  ██████████");
//        System.out.println("  South: ███████");
//        System.out.println("  West:  ███ (Weakest)");
//
//        System.out.println("\nAvailable Weights: Heavy, Medium, Light, Feather");
//        System.out.println("Positions: North, East, South, West");
//
//        if (!currentArrangement.isEmpty()) {
//            System.out.println("\nCurrent Arrangement:");
//            for (String pos : new String[]{"north", "east", "south", "west"}) {
//                String weight = currentArrangement.getOrDefault(pos, "empty");
//                System.out.println("  " + capitalize(pos) + ": " + capitalize(weight));
//            }
//        }
//    }
//
//    /**
//     * Helper to capitalize strings
//     */
//    private String capitalize(String str) {
//        if (str == null || str.isEmpty()) return str;
//        return str.substring(0, 1).toUpperCase() + str.substring(1);
//    }
//
//    /**
//     * Show solution (for debugging/testing)
//     */
//    public void showSolution() {
//        System.out.println("\n=== Solution ===");
//        System.out.println("North (strongest wind): Feather (lightest)");
//        System.out.println("East: Light");
//        System.out.println("South: Medium");
//        System.out.println("West (weakest wind): Heavy (heaviest)");
//    }
//}
