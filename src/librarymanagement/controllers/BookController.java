package librarymanagement.controllers;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import librarymanagement.models.Book;
import librarymanagement.utils.DatabaseConnection;

public class BookController {
    public static boolean addBook(Book b) {
        String sql = "INSERT INTO books(title,author,publisher,year,isbn,quantity) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1,b.getTitle()); 
            stmt.setString(2,b.getAuthor());
            stmt.setString(3,b.getPublisher()); 
            stmt.setInt(4,b.getYear());
            stmt.setString(5,b.getIsbn()); 
            stmt.setInt(6,b.getQuantity());
            return stmt.executeUpdate()>0;
        } catch(SQLException e){e.printStackTrace(); return false;}
    }
    public static boolean updateBook(Book b) {
        String sql = "UPDATE books SET title=?,author=?,publisher=?,year=?,isbn=?,quantity=? WHERE id=?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1,b.getTitle()); stmt.setString(2,b.getAuthor());
            stmt.setString(3,b.getPublisher()); stmt.setInt(4,b.getYear());
            stmt.setString(5,b.getIsbn()); stmt.setInt(6,b.getQuantity());
            stmt.setInt(7, b.getId());
            return stmt.executeUpdate()>0;
        } catch(SQLException e){e.printStackTrace(); return false;}
    }
    public static boolean deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id=?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1,id);
            return stmt.executeUpdate()>0;
        } catch(SQLException e){e.printStackTrace(); return false;}
    }
    public static List<Book> listAll() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement stmt = DatabaseConnection.getConnection().createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){ list.add(new Book(rs.getInt("id"),rs.getString("title"),rs.getString("author"),
                rs.getString("publisher"),rs.getInt("year"),rs.getString("isbn"),rs.getInt("quantity"))); }
        } catch(SQLException e){e.printStackTrace();}
        return list;
    }
}