package librarymanagement.views;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import librarymanagement.utils.DatabaseConnection;

public class ReportsView extends JPanel {
    public ReportsView() {
        setLayout(new BorderLayout());
        JTextArea area=new JTextArea(); area.setEditable(false);
        generate(area);
        add(new JScrollPane(area));
    }
    private void generate(JTextArea a){
        try(Statement st=DatabaseConnection.getConnection().createStatement()){
            ResultSet c1=st.executeQuery("SELECT COUNT(*) FROM users WHERE role='STUDENT'"); c1.next();
            ResultSet c2=st.executeQuery("SELECT COUNT(*) FROM books"); c2.next();
            a.append("Total Students: "+c1.getInt(1)+"\n");
            a.append("Total Books: "+c2.getInt(1)+"\n");
        }catch(Exception e){e.printStackTrace();}
    }
}