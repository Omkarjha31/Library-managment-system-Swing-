package librarymanagement.views;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import librarymanagement.controllers.NotificationController;
import librarymanagement.models.Notification;

public class NotificationsView extends JPanel {
    private JTable table; DefaultTableModel model;
    public NotificationsView() {
        setLayout(new BorderLayout());
        model=new DefaultTableModel(new Object[]{"ID","Message","Date"},0){public boolean isCellEditable(int r,int c){return false;}};
        table=new JTable(model);
        refresh(); add(new JScrollPane(table),BorderLayout.CENTER);
        JPanel p=new JPanel(); JButton add=new JButton("Add"); p.add(add); add(p,BorderLayout.SOUTH);
        add.addActionListener(e->{
            String m=JOptionPane.showInputDialog(this,"Message:"); if(m==null) return;
            NotificationController.addNotification(m); refresh();
        });
    }
    private void refresh(){ model.setRowCount(0); List<Notification> list=NotificationController.listAll(); for(Notification n:list) model.addRow(new Object[]{n.getId(),n.getMessage(),n.getDate()}); }
}
