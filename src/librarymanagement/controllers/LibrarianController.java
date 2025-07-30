package librarymanagement.controllers;
import librarymanagement.utils.DatabaseConnection;
import java.sql.*;
import java.util.Date;

public class LibrarianController {
    public static boolean borrowBook(int bookId, int studentId, Date borrowDate, Date dueDate) {
        String sql = "INSERT INTO borrow_records(book_id,student_id,borrow_date,due_date) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1,bookId); stmt.setInt(2,studentId);
            stmt.setDate(3,new java.sql.Date(borrowDate.getTime()));
            stmt.setDate(4,new java.sql.Date(dueDate.getTime()));
            return stmt.executeUpdate()>0;
        } catch(SQLException e){e.printStackTrace(); return false;}
    }
    public static boolean returnBook(int borrowId, Date returnDate) {
        String sql = "UPDATE borrow_records SET return_date=? WHERE id=?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setDate(1,new java.sql.Date(returnDate.getTime()));
            stmt.setInt(2, borrowId);
            return stmt.executeUpdate()>0;
        } catch(SQLException e){e.printStackTrace(); return false;}
    }
}
