package librarymanagement.controllers;
import librarymanagement.utils.DatabaseConnection;
import librarymanagement.models.Notification;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationController {
    public static boolean addNotification(String msg) {
        String sql="INSERT INTO notifications(message) VALUES(?)";
        try(PreparedStatement pst=DatabaseConnection.getConnection().prepareStatement(sql)){
            pst.setString(1,msg);
            return pst.executeUpdate()>0;
        }catch(SQLException e){e.printStackTrace(); return false;}
    }
    public static List<Notification> listAll() {
        List<Notification> list=new ArrayList<>();
        try(Statement st=DatabaseConnection.getConnection().createStatement(); ResultSet rs=st.executeQuery("SELECT * FROM notifications ORDER BY date DESC")){
            while(rs.next()){ list.add(new Notification(rs.getInt("id"),rs.getString("message"),rs.getTimestamp("date"))); }
        }catch(SQLException e){e.printStackTrace();}
        return list;
    }
}