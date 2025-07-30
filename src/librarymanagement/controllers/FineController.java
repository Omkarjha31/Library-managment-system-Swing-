package librarymanagement.controllers;
import librarymanagement.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import librarymanagement.models.Fine;

public class FineController {
    public static void generateFines(double ratePerDay) {
        String sql = "SELECT id,due_date,return_date FROM borrow_records WHERE due_date<return_date";
        try (Statement st = DatabaseConnection.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){ int borrowId=rs.getInt("id");
                java.sql.Date due=rs.getDate("due_date"); java.sql.Date ret=rs.getDate("return_date");
                long days=(ret.getTime()-due.getTime())/(1000*60*60*24);
                double amt=days*ratePerDay;
                String ins="INSERT INTO fines(borrow_id,amount) VALUES(?,?)";
                PreparedStatement pst=DatabaseConnection.getConnection().prepareStatement(ins);
                pst.setInt(1, borrowId); pst.setDouble(2, amt);
                pst.executeUpdate();
            }
        } catch(SQLException e){e.printStackTrace();}
    }
    public static List<Fine> listAll() {
        List<Fine> list=new ArrayList<>();
        try (Statement st = DatabaseConnection.getConnection().createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM fines")){
            while(rs.next()){ list.add(new Fine(rs.getInt("id"),rs.getInt("borrow_id"),rs.getDouble("amount"),rs.getBoolean("paid"))); }
        } catch(SQLException e){e.printStackTrace();}
        return list;
    }
    public static boolean payFine(int id) {
        String sql="UPDATE fines SET paid=TRUE WHERE id=?";
        try(PreparedStatement pst=DatabaseConnection.getConnection().prepareStatement(sql)){
            pst.setInt(1,id);
            return pst.executeUpdate()>0;
        }catch(SQLException e){e.printStackTrace(); return false;}
    }
}
