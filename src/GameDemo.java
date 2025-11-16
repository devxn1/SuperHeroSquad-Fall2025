/**Class: GameDemo
 * @author Devin Gomez
 * @version 1.0
 * Course:  ITEC3860 Fall 2025
 * Written: November 13, 2025
 * Purpose: This game is just to test the DBManager class
 * in case we decide to use it
 */

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class GameDemo {
    private Player player;
    private Map<String, Room> allRoom;
    private Room currentRoom;
    private DBManager dbManager;

    private void initializeGame() {
        allRoom = new HashMap<>();
    }

    private void getRoomTable() {
        String sql = "SELECT roomID, biome, name, description, north, east, south, west, isLockedBy FROM Room";
        ResultSet rs = dbManager.executeQuery(sql);
        try {
            while (rs.next()) {
                String roomID = rs.getString("roomID");
                String biome = rs.getString("biome");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String north = rs.getString("north");
                String east = rs.getString("east");
                String south = rs.getString("south");
                String west = rs.getString("west");
                String isLockedBy = rs.getString("isLockedBy");

                Room room = new Room (roomID, biome, name, description, north, east, south, west, isLockedBy);
            }
        } catch (SQLException e) {
            System.err.println("Error in room table method:" + e.getMessage());
        }
    }

    private void getItemTable() {

    }

    private void getMonsterTable() {
        String sql = "SELECT monsterID, name, description, roomID, hp, damage, type FROM Monster";
        ResultSet rs = dbManager.executeQuery(sql);

        try {
            while (rs.next()) {
                String monsterID = rs.getString("monsterID");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String roomID = rs.getString("roomID");
                int health = rs.getInt("health");
                int damage = rs.getInt("damage");
                String type = rs.getString("type");

                boolean isAlive = health > 0;



                if ("Predator".equalsIgnoreCase(type)) {
                    double ambushChance = 0.75;  // Default 75%

                    // Try to get from database if column exists
                    try {
                        ambushChance = rs.getDouble("ambushChance");
                        if (ambushChance == 0.0) {
                            ambushChance = 0.75;
                        }
                    } catch (SQLException e) {
                        // Column doesn't exist, use default
                    }

                    AmbushMonster AmbushMonster = new AmbushMonster(health, damage, monsterID,
                            name, description, roomID,
                            isAlive, ambushChance);

//                    System.out.println("Loaded ambush monster: " + name +
//                            " (" + (int)(ambushChance * 100) + "% ambush)");
                } else {
                    // Create regular Monster for all other types
                    int idLocation = 0;  // Not used
                    Monster monster = new Monster(health, damage, ID,
                            name, description, idLocation, isAlive);

                }

                // Add to room
                if (roomID != null && !roomID.isEmpty()) {
                    Room room = allRoom.get(roomID);
                    if (room != null) {
                        room.setMonster(monster);
                    }
                }
            }
            System.out.println("Monsters loaded successfully");
        } catch (SQLException e) {
            System.err.println("Error loading monsters: " + e.getMessage());
            e.printStackTrace();
        }
    }








}
