/**Class: GameDemo
 * @author Devin Gomez
 * @version 1.0
 * Course:  ITEC3860 Fall 2025
 * Written: November 13, 2025
 * Purpose: This game is just to test the SQLite DB
 * in case we decide to use it
 */

import java.sql.*;

public class DBManager {

    private static DBManager instance;
    private Connection conn;
    private static final String dbURL= "jdbc:sqlite:./data/game.db";

    //creates actual database connection
    private DBManager() {
        try {
            conn = DriverManager.getConnection(dbURL);
            System.out.println("DB connected");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    //Meant to be used to return data or rows ask for in the query
    public ResultSet executeQuery(String sql, String... params) {
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            //for debugging purpose
            System.err.println("Error executing query: " + e.getMessage());
            return null;
        }
    }

    /*Meant for INSERT, UPDATE, etc.
    * return int since there no data to view
    */
    public int executeUpdate(String sql, String... params) {
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            //for debugging purpose
            System.err.println("Error executing update: " + e.getMessage());
            return 0;
        }
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                // debuging purpose
                System.out.println("DB connection closed");
            }

        } catch (SQLException e) {
            // debuging purpose
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }


}
