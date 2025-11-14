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
            }
        } catch (SQLException e) {
            System.err.println("Error in room table method:" + e.getMessage());
        }



    }

    private void getItemTable() {

    }

    private void getMonsterTable() {

    }








}
