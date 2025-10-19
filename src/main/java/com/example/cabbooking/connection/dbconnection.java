
package com.example.cabbooking.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {

    private static final String URL = "jdbc:mysql://localhost:3306/cabbooking";
    private static final String USER = "root";
    private static final String PASSWORD = "harsha";
    private static Connection connection;

    // Get database connection
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Load MySQL Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database connected successfully!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Database connection failed!");
        }
        return connection;
    }
}

