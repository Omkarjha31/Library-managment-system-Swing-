package librarymanagement.views;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import librarymanagement.controllers.AdminController;
import librarymanagement.models.User;

public class UserManagementView extends JPanel {
    private JTable table; DefaultTableModel model;
    public UserManagementView() {
        setLayout(new BorderLayout());
        model=new DefaultTableModel(new Object[]{"ID","Username","Name","Role"},0){public boolean isCellEditable(int r,int c){return false;}};
        table=new JTable(model);
        refresh(); add(new JScrollPane(table),BorderLayout.CENTER);
        JPanel p=new JPanel(); JButton add=new JButton("Add User"); p.add(add); add(p,BorderLayout.SOUTH);
        add.addActionListener(e->{
            String u=JOptionPane.showInputDialog(this,"Username:"); if(u==null) return;
            String pss=JOptionPane.showInputDialog(this,"Password:"); if(pss==null) return;
            String role=JOptionPane.showInputDialog(this,"Role (ADMIN/LIBRARIAN/STUDENT):"); if(role==null) return;
            String nm=JOptionPane.showInputDialog(this,"Name:"); if(nm==null) return;
            AdminController.addUser(u,pss,role,nm); refresh();
        });
    }
    private void refresh(){ model.setRowCount(0); List<User> list=AdminController.listUsers(null); for(User u:list) model.addRow(new Object[]{u.getId(),u.getUsername(),u.getName(),u.getRole()}); }
}
