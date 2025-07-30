package librarymanagement.controllers;
import librarymanagement.utils.DatabaseConnection;
import librarymanagement.models.*;
import java.sql.*;

public class AuthController {
    public static User authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");
                switch (role) {
                    case "ADMIN": return new Admin(id, username, name);
                    case "LIBRARIAN": return new Librarian(id, username, name);
                    case "STUDENT": return new Student(id, username, name);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}