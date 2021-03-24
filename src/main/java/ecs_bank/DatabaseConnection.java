package ecs_bank;

import java.sql.*;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class DatabaseConnection {

    private Connection connection;
    private Statement statement;

    // Singleton
    private static DatabaseConnection instance = null;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }


    public void connect() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/logindb?serverTimezone=UTC";
            String username = "root";
            String password = "";
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            System.out.println("Connected to database!");
        } catch (Exception e) {
            System.out.println("Connection fail");
        }
    }


    public void closeConnection() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                System.out.println("Error on closing DatabaseConnection statement");
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error on closing DatabaseConnection connection");
            }
        }
        System.out.println("Database connection terminated");
    }

    public String getHashPassword(String account) {
        String result = "";
        try {
            String querySQL = String.format("SELECT * FROM users WHERE username='%s'", account);
            ResultSet rs = statement.executeQuery(querySQL);
            rs.next();
            result = rs.getString(2);
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
