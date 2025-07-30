package librarymanagement.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/librarydb";
    private static final String USER = "root";
    private static final String PASS = "Ok@may04$";
    private static Connection conn;

    public static void init() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}