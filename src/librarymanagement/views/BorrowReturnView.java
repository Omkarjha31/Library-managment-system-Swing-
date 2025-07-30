package librarymanagement.views;
import librarymanagement.controllers.LibrarianController;
import librarymanagement.models.BorrowRecord;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.sql.*;
import librarymanagement.utils.DatabaseConnection;

public class BorrowReturnView extends JPanel {
    private JTable table; DefaultTableModel model;
    private Integer studentId;
    public BorrowReturnView() { this(null); }
    public BorrowReturnView(Integer studentId) {
        this.studentId = studentId;
        setLayout(new BorderLayout());
        model=new DefaultTableModel(new Object[]{"ID","Book","Student","Borrow","Due","Return"},0){public boolean isCellEditable(int r,int c){return false;}};
        table=new JTable(model);
        refresh(); add(new JScrollPane(table),BorderLayout.CENTER);
        JPanel p=new JPanel();
        JButton b1=new JButton(studentId==null?"Borrow":"Return"); p.add(b1); add(p,BorderLayout.SOUTH);
        b1.addActionListener(e->process());
    }
    private void refresh(){ model.setRowCount(0);
        try(Statement st=DatabaseConnection.getConnection().createStatement(); ResultSet rs=st.executeQuery("SELECT * FROM borrow_records")){
            SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("id"),rs.getInt("book_id"),rs.getInt("student_id"),f.format(rs.getDate("borrow_date")),f.format(rs.getDate("due_date")),rs.getDate("return_date")});
            }
        }catch(Exception e){e.printStackTrace();}
    }
    private void process(){ int r=table.getSelectedRow();
        if(studentId==null){ // borrow
            String bid=JOptionPane.showInputDialog(this,"Book ID:"); if(bid==null) return;
            String sid=JOptionPane.showInputDialog(this,"Student ID:"); if(sid==null) return;
            Date now=new Date(); Date due=new Date(now.getTime()+7L*24*3600*1000);
            LibrarianController.borrowBook(Integer.parseInt(bid),Integer.parseInt(sid),now,due);
        } else { // return
            if(r<0) return;
            int id=(int)model.getValueAt(r,0);
            LibrarianController.returnBook(id,new Date());
        }
        refresh();
    }
}