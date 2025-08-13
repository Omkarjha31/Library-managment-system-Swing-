
import librarymanagement.utils.DatabaseConnection;
import librarymanagement.models.*;
import java.sql.*;


public class AdminController {
    public static boolean addUser(String username, String password, String role, String name) {
        String sql = "INSERT INTO users(username,password,role,name) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.setString(4, name);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    public static List<User> listUsers(String roleFilter) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users" + (roleFilter!=null?" WHERE role='"+roleFilter+"'":"");
        try (Statement stmt = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String user = rs.getString("username");
                String name = rs.getString("name");
                String role = rs.getString("role");
                list.add(new User(id,user,name,role));
            }
        } catch(SQLException e){e.printStackTrace();}
        return list;
    }
}
//hello
