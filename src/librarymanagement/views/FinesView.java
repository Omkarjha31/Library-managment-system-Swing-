package librarymanagement.views;
import librarymanagement.controllers.FineController;
import librarymanagement.models.Fine;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FinesView extends JPanel {
    private JTable table; DefaultTableModel model;
    private Integer studentId;
    public FinesView() { this(null); }
    public FinesView(Integer studentId) {
        this.studentId=studentId;
        setLayout(new BorderLayout());
        model=new DefaultTableModel(new Object[]{"ID","BorrowID","Amount","Paid"},0){public boolean isCellEditable(int r,int c){return false;}};
        table=new JTable(model);
        refresh(); add(new JScrollPane(table),BorderLayout.CENTER);
        if(studentId==null){ JButton pay=new JButton("Pay"); JPanel p=new JPanel(); p.add(pay); add(p,BorderLayout.SOUTH);
            pay.addActionListener(e-> payFine()); }
    }
    private void refresh(){ model.setRowCount(0); List<Fine> list=FineController.listAll(); for(Fine f:list) model.addRow(new Object[]{f.getId(),f.getBorrowId(),f.getAmount(),f.isPaid()}); }
    private void payFine(){ int r=table.getSelectedRow(); if(r<0) return;
        int id=(int)model.getValueAt(r,0);
        FineController.payFine(id); refresh(); }
}